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
package org.contextmapper.standalone.example;

import org.contextmapper.dsl.cml.CMLResource;
import org.contextmapper.dsl.refactoring.SplitBoundedContextByOwner;
import org.contextmapper.dsl.standalone.ContextMapperStandaloneSetup;
import org.contextmapper.dsl.standalone.StandaloneContextMapperAPI;

/**
 * This example shows how you can read your CML model and apply a refactoring.
 * We use the CML model under src/main/cml/Insurance-Example-Model.cml and
 * apply 'Split Bounded Context by Owner' to the 'PolicyManagementContext'.
 *
 * @author Stefan Kapferer
 */
public class RefactoringExample {

    public final static String INSURANCE_EXAMPLE_URI = "./src/main/cml/Insurance-Example-Model.cml";

    public static void main(String[] args) {
        // Setup and loading CML file:
        StandaloneContextMapperAPI contextMapper = ContextMapperStandaloneSetup.getStandaloneAPI();
        CMLResource resource = contextMapper.loadCML(INSURANCE_EXAMPLE_URI);

        // apply refactoring
        contextMapper.applyRefactoring(resource, new SplitBoundedContextByOwner("PolicyManagementContext"));
    }

}
