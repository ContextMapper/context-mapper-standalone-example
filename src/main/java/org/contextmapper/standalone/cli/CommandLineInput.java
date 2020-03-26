package org.contextmapper.standalone.cli;

import org.apache.commons.lang.StringUtils;

public class CommandLineInput {
    private final String generatorType;
    private final String filePath;

    public CommandLineInput(String generatorType, String filePath) throws NullOrWhitespaceGeneratorTypeException, NullOrWhitespaceFilePath {
        if (StringUtils.isBlank(generatorType)) {
            throw new NullOrWhitespaceGeneratorTypeException();
        }

        if (StringUtils.isBlank(filePath)) {
            throw new NullOrWhitespaceFilePath();
        }

        this.generatorType = generatorType;
        this.filePath = filePath;
    }
}
