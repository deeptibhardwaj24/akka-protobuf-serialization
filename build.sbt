import com.trueaccord.scalapb.compiler.Version.scalapbVersion

name := "akka-protobuf-serialization"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-remote" % "2.5.4",
  "com.trueaccord.scalapb"      %% "scalapb-runtime"  % scalapbVersion  % "protobuf"

)

licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0")))

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

        