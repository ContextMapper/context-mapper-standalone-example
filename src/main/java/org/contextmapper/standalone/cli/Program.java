package org.contextmapper.standalone.cli;

import org.apache.commons.cli.*;

public class Program {
    public static void main(String[] args) throws NullOrWhitespaceGeneratorTypeException, NullOrWhitespaceFilePath {
        CommandLineInput commandLineInput = retrieveCommandLineInput(args);
    }

    private static CommandLineInput retrieveCommandLineInput(String[] args) throws NullOrWhitespaceGeneratorTypeException, NullOrWhitespaceFilePath {
        Options options = new Options();

        Option input = new Option("t", "type", true, "type of generator");
        input.setRequired(true);
        options.addOption(input);

        Option file = new Option("f", "file", true, "CML file");
        file.setRequired(true);
        options.addOption(file);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        return new CommandLineInput(cmd.getOptionValue("type"), cmd.getOptionValue("file"));
    }
}
