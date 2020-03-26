package org.contextmapper.standalone.cli;

import org.apache.commons.lang.StringUtils;

public class CommandLineInput {
    private final GeneratorTypeEnum generatorType;
    private final String filePath;

    public CommandLineInput(String generatorType, String filePath) throws NullOrWhitespaceGeneratorTypeException, NullOrWhitespaceFilePath, IllegalArgumentException {
        if (StringUtils.isBlank(generatorType)) {
            throw new NullOrWhitespaceGeneratorTypeException();
        }

        if (StringUtils.isBlank(filePath)) {
            throw new NullOrWhitespaceFilePath();
        }

        this.generatorType = GeneratorTypeEnum.valueOf(generatorType.toUpperCase());
        this.filePath = filePath;
    }
}
