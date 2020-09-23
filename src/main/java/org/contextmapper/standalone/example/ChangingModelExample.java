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
import org.contextmapper.dsl.contextMappingDSL.Aggregate;
import org.contextmapper.dsl.contextMappingDSL.BoundedContext;
import org.contextmapper.dsl.contextMappingDSL.ContextMappingDSLFactory;
import org.contextmapper.dsl.contextMappingDSL.ContextMappingModel;
import org.contextmapper.dsl.standalone.ContextMapperStandaloneSetup;
import org.contextmapper.dsl.standalone.StandaloneContextMapperAPI;
import org.eclipse.xtext.resource.SaveOptions;

import java.io.IOException;

import static org.contextmapper.standalone.example.ReadingModelExample.INSURANCE_EXAMPLE_URI;

;

/**
 * This example shows how you can read your CML model, change it, and unparse
 * the model back to you CML file. We use the CML model under
 * src/main/cml/Insurance-Example-Model.cml, change a Bounded Contexts name, and
 * parse it back to the file.
 * 
 * @author Stefan Kapferer
 *
 */
public class ChangingModelExample {

	public static void main(String[] args) throws IOException {
		// Setup and loading CML file:
		StandaloneContextMapperAPI contextMapper = ContextMapperStandaloneSetup.getStandaloneAPI();
		CMLResource resource = contextMapper.loadCML(INSURANCE_EXAMPLE_URI);
		ContextMappingModel model = resource.getContextMappingModel();

		// We search for a Bounded Context ...
		BoundedContext customerManagementBC = model.getBoundedContexts().stream()
				.filter(bc -> bc.getName().equals("CustomerManagementContext")).findFirst().get();
		
		// ... and add a new Aggregate to it
		Aggregate newAggregate = ContextMappingDSLFactory.eINSTANCE.createAggregate();
		newAggregate.setName("A_Super_New_Aggregate");
		customerManagementBC.getAggregates().add(newAggregate);

		// unparse model: (running this program changes the Insurance-Example-Model.cml file correspondingly!)
		resource.save(SaveOptions.defaultOptions().toOptionsMap());
	}

}
