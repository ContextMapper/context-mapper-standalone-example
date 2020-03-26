package org.contextmapper.standalone.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.contextmapper.dsl.ContextMappingDSLStandaloneSetup;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.GeneratorContext;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;

public class Program {
    public static void main(String[] args) throws NullOrWhitespaceGeneratorTypeException, NullOrWhitespaceFilePath, IllegalArgumentException, GeneratorTypeEnumNotDefinedException {
        CommandLineInput commandLineInput = retrieveCommandLineInput(args);

        generateDiagram(commandLineInput.generator(), commandLineInput.filePath());
    }

    private static CommandLineInput retrieveCommandLineInput(String[] args) throws NullOrWhitespaceGeneratorTypeException, NullOrWhitespaceFilePath, IllegalArgumentException {
        Options options = new Options();

        Option generatorType = new Option("t", "type", true, "type of generator");
        generatorType.setRequired(true);
        options.addOption(generatorType);

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

    private static void generateDiagram(AbstractGenerator generator, String filePath) {
        ContextMappingDSLStandaloneSetup.doSetup();
        Resource resource = new ResourceSetImpl().getResource(URI.createURI(filePath), true);

        JavaIoFileSystemAccess javaIoFileSystemAccess = FileSystemHelper.getFileSystemAccess();
        javaIoFileSystemAccess.setOutputPath("./src-gen");
        generator.doGenerate(resource, javaIoFileSystemAccess, new GeneratorContext());
    }
}
