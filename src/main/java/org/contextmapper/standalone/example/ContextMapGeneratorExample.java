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

import static org.contextmapper.standalone.example.ReadingModelExample.INSURANCE_EXAMPLE_URI;

import org.contextmapper.dsl.ContextMappingDSLStandaloneSetup;
import org.contextmapper.dsl.generator.ContextMapGenerator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.generator.GeneratorContext;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;

/**
 * This example shows how you can read your CML model and generate a graphical
 * Context Map from it. Note that for this example you need to have Graphviz
 * installed locally and your PATH environment variable must contain its bin
 * folder.
 * 
 * This example produces the file 'Insurance-Example-Model_ContextMap.png'
 * within the 'src-gen' folder.
 * 
 * @author Stefan Kapferer
 *
 */
public class ContextMapGeneratorExample {

	public static void main(String[] args) {
		// Setup and loading CML file:
		ContextMappingDSLStandaloneSetup.doSetup();
		Resource resource = new ResourceSetImpl().getResource(URI.createURI(INSURANCE_EXAMPLE_URI), true);

		// Create the PlantUML generator
		ContextMapGenerator generator = new ContextMapGenerator();

		// Generate the diagrams into 'src-gen'
		JavaIoFileSystemAccess javaIoFileSystemAccess = FileSystemHelper.getFileSystemAccess();
		javaIoFileSystemAccess.setOutputPath("./src-gen");
		generator.doGenerate(resource, javaIoFileSystemAccess, new GeneratorContext());
	}

}
