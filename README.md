This example uses [ScalaPB](https://trueaccord.github.io/ScalaPB/) in order to obtain case class support for Scala when
generating code from `*.proto` files. ScalaPB uses the `protoc-jar` internally to compile your protocol buffer files.

Execute `sbt clean compile` to let scalaPB generate the scala case classes from the given .proto definitions. These case classes have the ability to convert to and from binary and are generated in the target folder of the project.

These case classes are further used in the actor and the protobuf serializer created namely `MyOwnProtobufSerializer`

These case classes are the messages passed between the two actors(Calculator and Lookup).

Once, scala case classes for the messages are generated, all you need to do is start the two actors, which is done like this:

Start the CalculatorSystem in the first terminal window with the following command:

    sbt "runMain sample.remote.calculator.LookupApplication Calculator"

Start the LookupSystem in the second terminal window with the following command:

    sbt "runMain sample.remote.calculator.LookupApplication Lookup"

Here, you can observe that the Serializer called `MyOwnProtobufSerializer` is used rather than the deafult java serializer and this is controlled by the application.conf.
