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
    private final String outputPath;
    private final String freemarkerTemplate;

    protected CommandLineInput(String generatorType, String filePath, String outputPath, String freemarkerTemplate) throws NullOrWhitespaceGeneratorTypeException, NullOrWhitespaceFilePath, IllegalArgumentException {
        if (StringUtils.isBlank(generatorType)) {
            throw new NullOrWhitespaceGeneratorTypeException();
        }

        if (StringUtils.isBlank(filePath)) {
            throw new NullOrWhitespaceFilePath();
        }

        if (StringUtils.isBlank(outputPath)) {
            this.outputPath = "./src-gen";
        } else {
            this.outputPath = outputPath;
        }

        if (StringUtils.isBlank(freemarkerTemplate)) {
            this.freemarkerTemplate = "./src/main/resources/freemarker-sample-template.ftl";
        } else {
            this.freemarkerTemplate = freemarkerTemplate;
        }

        this.generatorType = GeneratorTypeEnum.valueOf(generatorType.toUpperCase());
        this.filePath = filePath;
    }

    protected String filePath() {
        return this.filePath;
    }

    public AbstractGenerator generator() throws GeneratorTypeEnumNotDefinedException {
        switch (this.generatorType) {
            case CONTEXTMAP:
                return new ContextMapGenerator();
            case GENERIC:
                GenericContentGenerator generator = new GenericContentGenerator();
                generator.setFreemarkerTemplateFile(new File(this.freemarkerTemplate));
                generator.setTargetFileName("sample-output.md");
                return generator;
            case MDSL:
                return new MDSLContractsGenerator();
            case PLANTUML:
                return new PlantUMLGenerator();
        }

        throw new GeneratorTypeEnumNotDefinedException();
    }

    public String outputPath() {
        return this.outputPath;
    }
}
