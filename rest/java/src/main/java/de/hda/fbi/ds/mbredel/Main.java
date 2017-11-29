package de.hda.fbi.ds.mbredel;

import akka.actor.ActorSystem;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.hda.fbi.ds.mbredel.configuration.CliParameters;
import de.hda.fbi.ds.mbredel.configuration.ConfigurationModule;
import de.hda.fbi.ds.mbredel.core.Application;
import de.hda.fbi.ds.mbredel.core.ApplicationModule;
import de.hda.fbi.ds.mbredel.core.Constants;
import de.hda.fbi.ds.mbredel.eventBus.EventBusModule;
import de.hda.fbi.ds.mbredel.store.StoreModule;
import org.apache.commons.cli.*;

/**
 * The main class starts the actual applications. To
 * this end, it handles command line options,
 * bootstraps the Guice modules, starts the Akka
 * actor system, and starts the application using
 * Guice.
 *
 * @author Michael Bredel
 */
public class Main {

    /**
     * Move it, move it, move it.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        // Parse the command line options.
        parseCliOptions(args);

        // Initialize Guice.
        Injector injector = initGuice();

        // Start the Akka actor system.
        initAkka(injector);

        // Start the app.
        injector.getInstance(Application.class)
                .start();
    }


    /**
     * Bootstrap the various Guice modules.
     */
    private static Injector initGuice() {
        return Guice.createInjector(
                new ApplicationModule(),
                new ConfigurationModule(),
                new StoreModule(),
                new EventBusModule()
        );
    }

    /**
     * Bootstrap the Akka actor system.
     */
    private static void initAkka(Injector injector) {
        // Create and start the health check actors.
        ActorSystem healthChecks = ActorSystem.create("HealthChecks");
        //system.actorOf(Props.create(GuiceInjectedActor.class, injector, HealthCheckSupervisor.class));
    }

    /**
     * Creates the command line options for the
     * program.
     *
     * @return An Options object containing all the command line options of the program.
     */
    private static Options createCliOptions() {
        // A helper option.
        Option help = Option.builder("h")
                .longOpt("help")
                .desc("Give this help list.")
                .build();
        // The configuration option.
        Option config = Option.builder("c")
                .longOpt("config")
                .desc("The URI to the configuration.")
                .hasArg()
                .build();
        // The username option
        Option username = Option.builder("u")
                .longOpt("user")
                .desc("The user name to access a remote configuration.")
                .hasArg()
                .build();
        // The username option
        Option password = Option.builder("p")
                .longOpt("password")
                .desc("The password to access a remote configuration.")
                .hasArg()
                .build();

        // Create options.
        Options options = new Options();
        options.addOption(help);
        options.addOption(config);
        options.addOption(username);
        options.addOption(password);

        // Return options.
        return options;
    }

    /**
     * Parses the command line arguments.
     *
     * @param args The command line arguments.
     */
    private static void parseCliOptions(String[] args) {
        // Command line options.
        Options options = createCliOptions();
        // Command line parser.
        CommandLineParser parser = new DefaultParser();

        try {
            // Parse the command line arguments
            CommandLine line = parser.parse(options, args);

            if (line.hasOption('h')) {
                printHelp(options);
                System.exit(0);
            }
            if (line.hasOption('c')) {
                CliParameters.getInstance().setUri(line.getOptionValue('c'));
            }
            if (line.hasOption('u')) {
                CliParameters.getInstance().setUsername(line.getOptionValue('u'));
            }
            if (line.hasOption('p')) {
                CliParameters.getInstance().setPassword(line.getOptionValue('p'));
            }
        } catch (MissingOptionException | MissingArgumentException e) {
            System.err.println("ERROR: " + e.getMessage() + "\n");
            printHelp(options);
            System.exit(Constants.EXIT_CODE_ERROR);
        } catch (ParseException e) {
            // Oops, something went wrong
            System.err.println("ERROR: Parsing CLI parameters failed. Reason: " + e.getMessage() + "\n");
            printHelp(options);
            System.exit(Constants.EXIT_CODE_ERROR);
        }
    }

    /**
     * Prints the help of the command.
     *
     * @param options The command's options.
     */
    private static void printHelp(Options options) {
        // A help formatter.
        HelpFormatter formatter = new HelpFormatter();
        // Print help.
        formatter.printHelp("rest-server [OPTIONS]", options);
    }

}
