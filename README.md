![Context Mapper](https://raw.githubusercontent.com/wiki/ContextMapper/context-mapper-dsl/logo/cm-logo-github-small.png) 
# Context Mapping DSL (CML) Standalone Example Project [![Build Status](https://travis-ci.com/ContextMapper/context-mapper-standalone-example.svg?branch=master)](https://travis-ci.com/ContextMapper/context-mapper-standalone-example) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This is an example project illustrating how the [Context Mapper DSL (CML)](https://github.com/ContextMapper/context-mapper-dsl) language can be used as standalone library outside the Eclipse IDE. 

## Build
The Context Mapper DSL library releases are available via the Maven central repository and can therefore be integrated within your Maven or Gradle builds.

**Gradle:**
```gradle
implementation 'org.contextmapper:context-mapper-dsl:5.10.0'
```

**Maven:**
```xml
<dependency>
  <groupId>org.contextmapper</groupId>
  <artifactId>context-mapper-dsl</artifactId>
  <version>5.10.0</version>
</dependency>
```
If you want to use our SNAPSHOT's built from the master branch ([context-mapper-dsl](https://github.com/ContextMapper/context-mapper-dsl)) you have to use the following repository: https://oss.sonatype.org/content/repositories/snapshots

### Gradle Xtext Builder Plugin
This example project is built with Gradle. Have a look at [build.gradle](./build.gradle) how it is configured. With the Xtext builder plugin you ensure that your CML files are compiled at build time.

## Example Model
The CML models are configured to be in the source folder **src/main/cml** (see [build.gradle](./build.gradle)). This example project contains the insurance example model ([./src/main/cml/Insurance-Example-Model.cml](./src/main/cml/Insurance-Example-Model.cml)) which can also be found in our [examples repository](https://github.com/ContextMapper/context-mapper-examples).

## Examples how to use CML models in your code
This example project contains the following example classes written in Java which illustrate how to use CML models and our generators:
 * [ReadingModelExample](./src/main/java/org/contextmapper/standalone/example/ReadingModelExample.java): Example how to read the CML model
 * [ChangingModelExample](./src/main/java/org/contextmapper/standalone/example/ChangingModelExample.java): Illustrates how a model can be changed an then unparsed back to the CML file.
 * [PlantUMLGeneratorExample](./src/main/java/org/contextmapper/standalone/example/PlantUMLGeneratorExample.java): Shows how you can read your CML model and generate the [PlantUML diagrams](https://contextmapper.org/docs/plant-uml/) out of it easily.
 * [MDSLGeneratorExample](./src/main/java/org/contextmapper/standalone/example/MDSLGeneratorExample.java): Shows how to generate the [MDSL contracts](https://contextmapper.org/docs/mdsl/) out of the CML model.
 * [GenericGeneratorExample](./src/main/java/org/contextmapper/standalone/example/GenericGeneratorExample.java): Illustrates how you can use [Freemarker](https://freemarker.apache.org/) templates to generate arbitrary textual files out of your Context Map.
 
For details about the generators and the CML language please consult our [online documentation](https://contextmapper.org/docs) (currently we have no further documentation how to use the tools on code level in standalone applications).
If you have questions how to use specific features within your standalone Java application don't hesitate to [create an issue](https://github.com/ContextMapper/context-mapper-standalone-example/issues) or [contact us](https://contextmapper.org/getting-involved/).

## CLI
This example project also contains a `jar` that can be used from a CLI. To use it, you can build the project using any IDE, and use the generated `jar`. The available arguments are:
```
 -f,--file <arg>       CML file
 -o,--output <arg>     output folder
 -p,--template <arg>   freemarker template
 -t,--type <arg>       type of generator
```

The `file` and `type` arguments are mandatory. If you don't specify an `output` it uses `src-gen` folder.

The `type` of generators available are:
 * `contextmap`
 * `generic`
 * `mdsl`
 * `plantuml`

As example, to run from a command line:
```
java -jar context-mapper-standalone-example-1.0.0-SNAPSHOT.jar -f Insurance-Example-Model.cml -t contextmap
```

## Contributing
Contribution is always welcome! Here are some ways how you can contribute:
 * Create Github issues if you find bugs or just want to give suggestions for improvements.
 * This is an open source project: if you want to code, [create pull requests](https://help.github.com/articles/creating-a-pull-request/) from [forks of this repository](https://help.github.com/articles/fork-a-repo/). Please refer to a Github issue if you contribute this way.
 * If you want to contribute to our documentation and user guides on our website [https://contextmapper.org/](https://contextmapper.org/), create pull requests from forks of the corresponding page repo [https://github.com/ContextMapper/contextmapper.github.io](https://github.com/ContextMapper/contextmapper.github.io) or create issues [there](https://github.com/ContextMapper/contextmapper.github.io/issues).

## Licence
ContextMapper is released under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

