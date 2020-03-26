/*
 * Copyright 2020 The Context Mapper Project Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

        generateDiagram(commandLineInput.generator(), commandLineInput.filePath(), commandLineInput.outputPath());
    }

    private static CommandLineInput retrieveCommandLineInput(String[] args) throws NullOrWhitespaceGeneratorTypeException, NullOrWhitespaceFilePath, IllegalArgumentException {
        Options options = new Options();

        Option generatorType = new Option("t", "type", true, "type of generator");
        generatorType.setRequired(true);
        options.addOption(generatorType);

        Option file = new Option("f", "file", true, "CML file");
        file.setRequired(true);
        options.addOption(file);

        Option output = new Option("o", "output", true, "output folder");
        file.setRequired(false);
        options.addOption(output);

        Option template = new Option("p", "template", true, "freemarker template");
        file.setRequired(false);
        options.addOption(template);

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

        return new CommandLineInput(cmd.getOptionValue("type"), cmd.getOptionValue("file"), cmd.getOptionValue("output"), cmd.getOptionValue("template"));
    }

    private static void generateDiagram(AbstractGenerator generator, String filePath, String outputPath) {
        ContextMappingDSLStandaloneSetup.doSetup();
        Resource resource = new ResourceSetImpl().getResource(URI.createURI(filePath), true);

        JavaIoFileSystemAccess javaIoFileSystemAccess = FileSystemHelper.getFileSystemAccess();
        javaIoFileSystemAccess.setOutputPath(outputPath);
        generator.doGenerate(resource, javaIoFileSystemAccess, new GeneratorContext());
    }
}
