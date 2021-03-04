#!/bin/bash

##
## Configure the GitLab access parameters
##
GITLAB_URL="https://gitlab.fbi.h-da.de"
GITLAB_USR=""
GITLAB_TKN=""

##
## Configure the namespace-id, i.e. the
## id of the path (groups and sub-groups)
## of the newly created project. For the
## path /distributed-systems/lab 2017/2018
## this value is 150. 
##
NAMESPACE_ID=150

##
## Configure the major base groups.
##
BASE_GROUPS="a b c d e f g"

##
## Configure the sub-groups within a single
## base group.
##
SUB_GROUPS="1 2 3 4 5 6 7 8"

##
## Configure the json templates used for
## creation and edit of projects. You may
## need to modify the template.
##
CREATE_TMP="create-project-template.json"

##
## Parse the command-line arguments to get
## username and private GitLab token.
##
OPTS=`getopt -o u:t:i:h --l user:,key:,namespace-id:,help -n 'create_lab-projects.sh' -- "$@"`
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
    -h|--help)
      echo "Usage:"
      echo "  test.sh -u <USERNAME> -t <PRIVATE_GITLAB_TOKEN> [-i <NAMESPACE_ID>]"
      shift ;;
    --) shift; break ;;
    *) break ;;
  esac
done

##
## Get all projects of GitLab.
##
#curl --header "PRIVATE-TOKEN: ${GITLAB_TKN}" ${GITLAB_URL}/api/v4/projects | python -m json.tool

##
## Get all namespaces in GitLab
##
#curl --header "PRIVATE-TOKEN: ${GITLAB_TKN}" ${GITLAB_URL}/api/v4/namespaces | python -m json.tool

##
## Create all projects for the respective
## student groups and sub-groups
##
for BASE_GROUP in ${BASE_GROUPS}
do

  ##
  ## The base group in capital letters
  ##
  BASE_GROUP_CAP=$(echo ${BASE_GROUP} | tr '[:lower:]' '[:upper:]')
  
  for SUB_GROUP in ${SUB_GROUPS} 
  do

    ##
    ## Modify the template
    ##
    project_name="group_${BASE_GROUP}_${SUB_GROUP}"
    project_path="group_${BASE_GROUP}_${SUB_GROUP}"
    project_description="Verteilte Systeme im Wintersemester 2017/2018. Gruppe ${BASE_GROUP_CAP}, Sub-Gruppe ${SUB_GROUP}"
    sed -e "s/%PROJECT_NAME%/${project_name}/g" -e "s/%PROJECT_PATH%/${project_path}/g" -e "s/%PROJECT_DESCRIPTION%/${project_description}/g" -e "s/%NAMESPACE_ID%/${NAMESPACE_ID$/g" ${CREATE_TMP} > /tmp/${CREATE_TMP}

    ##
    ## Create a new project in a given namespace,
    ## that is identified by its id.
    ##
    curl --request POST --header "PRIVATE-TOKEN: ${GITLAB_TKN}" --header "Content-Type: application/json" --data @/tmp/${CREATE_TMP} ${GITLAB_URL}/api/v4/projects | python -m json.tool
    
  done
done
