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
import org.contextmapper.dsl.contextMappingDSL.ContextMappingModel;
import org.contextmapper.dsl.contextMappingDSL.Partnership;
import org.contextmapper.dsl.contextMappingDSL.SharedKernel;
import org.contextmapper.dsl.contextMappingDSL.UpstreamDownstreamRelationship;
import org.contextmapper.dsl.standalone.ContextMapperStandaloneSetup;
import org.contextmapper.dsl.standalone.StandaloneContextMapperAPI;

/**
 * This example shows how you can read your CML model. We use the CML model
 * under src/main/cml/Insurance-Example-Model.cml and just printout some model
 * content...
 *
 * @author Stefan Kapferer
 */
public class ReadingModelExample {

    public final static String INSURANCE_EXAMPLE_URI = "./src/main/cml/Insurance-Example-Model.cml";

    public static void main(String[] args) {
        // Setup and loading CML file:
        StandaloneContextMapperAPI contextMapper = ContextMapperStandaloneSetup.getStandaloneAPI();
        CMLResource resource = contextMapper.loadCML(INSURANCE_EXAMPLE_URI);
        ContextMappingModel model = resource.getContextMappingModel();

        // Reading from our CML model:
        System.out.println("The insurance example contains the following Bounded Contexts:");
        model.getBoundedContexts().forEach(bc -> {
            System.out.println(bc.getName());
        });

        System.out.println("And relationships between these contexts exist:");
        model.getMap().getRelationships().forEach(relationship -> {
            if (relationship instanceof UpstreamDownstreamRelationship) {
                UpstreamDownstreamRelationship upDownRel = (UpstreamDownstreamRelationship) relationship;
                System.out.println(upDownRel.getUpstream().getName() + " -> " + upDownRel.getDownstream().getName());
            } else if (relationship instanceof Partnership) {
                Partnership partnership = (Partnership) relationship;
                System.out.println(partnership.getParticipant1().getName() + " [P]<->[P] "
                        + partnership.getParticipant2().getName());
            } else if (relationship instanceof SharedKernel) {
                SharedKernel sharedKernel = (SharedKernel) relationship;
                System.out.println(sharedKernel.getParticipant1().getName() + " [SK]<->[SK] "
                        + sharedKernel.getParticipant2().getName());
            }
        });
    }

}
