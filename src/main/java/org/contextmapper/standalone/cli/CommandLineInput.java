package org.contextmapper.standalone.cli;

import org.apache.commons.lang.StringUtils;
import org.contextmapper.dsl.generator.ContextMapGenerator;
import org.contextmapper.dsl.generator.GenericContentGenerator;
import org.contextmapper.dsl.generator.MDSLContractsGenerator;
import org.contextmapper.dsl.generator.PlantUMLGenerator;
import org.eclipse.xtext.generator.AbstractGenerator;

import java.io.File;

public class CommandLineInput {
    private final GeneratorTypeEnum generatorType;
    private final String filePath;

    protected CommandLineInput(String generatorType, String filePath) throws NullOrWhitespaceGeneratorTypeException, NullOrWhitespaceFilePath, IllegalArgumentException {
        if (StringUtils.isBlank(generatorType)) {
            throw new NullOrWhitespaceGeneratorTypeException();
        }

        if (StringUtils.isBlank(filePath)) {
            throw new NullOrWhitespaceFilePath();
        }

        this.generatorType = GeneratorTypeEnum.valueOf(generatorType.toUpperCase());
        this.filePath = filePath;
    }

    protected String filePath() {
        return filePath;
    }

    public AbstractGenerator generator() throws GeneratorTypeEnumNotDefinedException {
        switch (generatorType) {
            case CONTEXTMAP:
                return new ContextMapGenerator();
            case GENERIC:
                GenericContentGenerator generator = new GenericContentGenerator();
                generator.setFreemarkerTemplateFile(new File("./src/main/resources/freemarker-sample-template.ftl"));
                generator.setTargetFileName("sample-output.md");
                return generator;
            case MDSL:
                return new MDSLContractsGenerator();
            case PLANTUML:
                return new PlantUMLGenerator();
        }

        throw new GeneratorTypeEnumNotDefinedException();
    }
}
