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

import org.contextmapper.dsl.ContextMappingDSLStandaloneSetup;
import org.contextmapper.dsl.cml.CMLResourceContainer;
import org.contextmapper.dsl.refactoring.SemanticCMLRefactoring;
import org.contextmapper.dsl.refactoring.SplitBoundedContextByOwner;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

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
        ContextMappingDSLStandaloneSetup.doSetup();
        CMLResourceContainer resource = new CMLResourceContainer(new ResourceSetImpl().getResource(URI.createURI(INSURANCE_EXAMPLE_URI), true));

        // apply refactoring
        SemanticCMLRefactoring refactoring = new SplitBoundedContextByOwner("PolicyManagementContext");
        refactoring.refactor(resource);
        refactoring.persistChanges();
    }

}
