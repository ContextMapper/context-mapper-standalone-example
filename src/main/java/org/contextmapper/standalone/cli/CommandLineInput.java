package org.contextmapper.standalone.cli;

public class CommandLineInput {
    private final String generatorType;
    private final String filePath;

    public CommandLineInput(String generatorType, String filePath) {
        this.generatorType = generatorType;
        this.filePath = filePath;
    }
}
