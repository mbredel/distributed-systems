#!/bin/bash

##
## Configure the GitLab access parameters.
##
export GITLAB_URL="https://gitlab.fbi.h-da.de"
export GITLAB_USR=""
export GITLAB_TKN=""

##
## The path to the Moodle group txt-file.
##
export MOODLE_FILE=""

##
## Configure the namespace-id, i.e. the
## id of the path (groups and sub-groups)
## of the newly created project. For the
## path /distributed-systems/lab 2017/2018
## this value is 150. 
##
export NAMESPACE_ID=9222

##
## A project description.
##
export PROJECT_DESCRIPTION="Loesungen zum Labor zu Verteilte Systeme."

##
## Configure the json templates used for
## creation and edit of projects. You may
## need to modify the template.
##
export CREATE_TMP="create-project-template.json"

##
## Parse the command-line arguments to get
## username and private GitLab token.
##
OPTS=`getopt -o u:t:i:f:h: -l user:,key:,namespace-id:,file:,help -n 'create_lab-projects.sh' -- "$@"`
if [ $? != 0 ] ; then echo "Failed parsing options." >&2 ; exit 1 ; fi
eval set -- "$OPTS"
while true; do
  case "$1" in
    -u|--user)
      case "$2" in
        *) GITLAB_USR=$2; shift 2 ;;
      esac ;;
    -t|--token)
      case "$2" in
        *) GITLAB_TKN=$2; shift 2 ;;
      esac ;;
    -i|--namespace-id)
      case "$2" in
        *) NAMESPACE_ID=$2; shift 2 ;;
      esac ;;
    -f|--file)
      case "$2" in
        *) MOODLE_FILE=$2; shift 2 ;;
      esac ;;
    -h|--help)
      echo "Usage:"
      echo "  test.sh -u <USERNAME> -t <PRIVATE_GITLAB_TOKEN> [-i <NAMESPACE_ID>]"
      shift ;;
    --) shift; break ;;
    *) break ;;
  esac
done

##
## Encode strings to URLs. Substituting
## special chars like ä,ö,ü,etc.
##
function urlencode() {
    local LANG=C
    for ((i=0;i<${#1};i++)); do
        if [[ ${1:$i:1} =~ ^[a-zA-Z0-9\.\~\_\-]$ ]]; then
            printf "${1:$i:1}"
        else
            printf '%%%02X' "'${1:$i:1}"
        fi
    done
}
export -f urlencode

##
## Create the repos from the
## Moodle groups txt-file
##
function create() {

    ##
    ## Extract the project name from the CSV-file.
    ## If not project name is given, continue with
    ## next user.
    ##
    project_name=$5
    [[ -z ${project_name} ]] && exit 0

    ##
    ## The project id to add users to.
    ##
    project_id=0

    ##
    ## Extract the user name.
    ## We use the i-st name as user-name
    ## However, some students have an st-name.
    ## Come back and check again.
    ##
    user_name=i$3
    [[ -z ${user_name} ]] && exit 0

    ##
    ## Search for user name in GitLab. If no user
    ## is found, return an provide an error message.
    ##
    curl -s --header "PRIVATE-TOKEN: ${GITLAB_TKN}" ${GITLAB_URL}/api/v4/users?username=${user_name} | python -m json.tool > /tmp/user.json
    if jq -e '..|select(type == "array" and length == 0)' < "/tmp/user.json" > /dev/null
    then
       echo -e "\033[0;31mNo GitLab-user found for \"$1, $2: ${user_name}\"\033[0m"
       exit 0
    fi

    ##
    ## Check if the project/repo already exists.
    ## If not, create the repo. Otherwise
    ## get the unique id of the existing repo.
    ##
    curl -s --header "PRIVATE-TOKEN: ${GITLAB_TKN}" ${GITLAB_URL}/api/v4/projects?search=${project_name} | python -m json.tool > /tmp/projects.json
    if ! jq -e '.[] | select(.namespace.id == '${NAMESPACE_ID}')' < "/tmp/projects.json" > /dev/null
    then
	##
	## If the project/repo does not exist yet,
	## create it.
	##
        echo "Creating Repo \"${project_name}\""

        ##
        ## Modify the template
        ##
        sed -e "s/%PROJECT_NAME%/${project_name}/g" -e "s/%PROJECT_PATH%/${project_name}/g" -e "s/%PROJECT_DESCRIPTION%/${PROJECT_DESCRIPTION}/g" -e "s/%NAMESPACE_ID%/${NAMESPACE_ID}/g" ${CREATE_TMP} > /tmp/${CREATE_TMP}

        ##
        ## Create a new project in a given namespace,
        ## that is identified by its id.
        ##
        curl -s --request POST --header "PRIVATE-TOKEN: ${GITLAB_TKN}" --header "Content-Type: application/json" --data @/tmp/${CREATE_TMP} ${GITLAB_URL}/api/v4/projects | python -m json.tool > /tmp/create-project-response.json

	##
        ## Get the id of the recently created project.
        ##
        project_id=`cat /tmp/create-project-response.json | jq -r '.id'`
    else
	##
	## If the project/repo exists already,
	## get its id and add user.
	##
	echo "Repo \"${project_name}\" exists already"

	##
        ##
	## Get the ID of the existing project
	##
	project_id=`jq -r '.[] | select(.namespace.id == '${NAMESPACE_ID}') .id' < /tmp/projects.json`
    fi

    ##
    ## Add (active) users to the project.
    ##
    for user_id in `cat /tmp/user.json | jq -r '.[] | select(.state=="active") .id'`; do
        ##
	##
	##
	echo "Adding user \"$1, $2 (${user_name})\" to repo \"${project_name}\""

        ##
        ## Add a user to the project.
        ##
        curl -s --request POST --header "PRIVATE-TOKEN: ${GITLAB_TKN}" --data "user_id=${user_id}&access_level=40" "${GITLAB_URL}/api/v4/projects/${project_id}/members" > /dev/null
    done

}
export -f create


##
## Remove the head line from the text file
##
tail -n +2 ${MOODLE_FILE} > /tmp/group.txt

##
## Create repositories for all students in the
## given TAB-separated text file.
##
csvtool -t TAB call create /tmp/group.txt 

