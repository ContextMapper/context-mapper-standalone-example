/*
 * Copyright 2019 The Context Mapper Project Team
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
package org.contextmapper.standalone.example;

import org.contextmapper.dsl.cml.CMLResource;
import org.contextmapper.dsl.generator.GenericContentGenerator;
import org.contextmapper.dsl.standalone.ContextMapperStandaloneSetup;
import org.contextmapper.dsl.standalone.StandaloneContextMapperAPI;

import java.io.File;

import static org.contextmapper.standalone.example.ReadingModelExample.INSURANCE_EXAMPLE_URI;

/**
 * This example shows how you can read your CML model and generate arbitrary textual
 * files by using Freemarker templates.
 *
 * @author Stefan Kapferer
 */
public class GenericGeneratorExample {

    public final static String FREEMARKER_TEMPLATE = "./src/main/resources/freemarker-sample-template.ftl";

    public static void main(String[] args) {
        // Setup and loading CML file:
        StandaloneContextMapperAPI contextMapper = ContextMapperStandaloneSetup.getStandaloneAPI();
        CMLResource resource = contextMapper.loadCML(INSURANCE_EXAMPLE_URI);

        // Create the generic generator (using Freemarker template)
        GenericContentGenerator generator = new GenericContentGenerator();
        generator.setFreemarkerTemplateFile(new File(FREEMARKER_TEMPLATE));
        generator.setTargetFileName("sample-output.md");

        // Use custom data if you want:
        generator.registerCustomModelProperty("customText", "hello freemarker world");

        // Generate the diagrams into 'src-gen'
        contextMapper.callGenerator(resource, generator);
    }

}
