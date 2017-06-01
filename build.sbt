name := "GrabEverything"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies += "org.jsoup" % "jsoup" % "1.10.2"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.2",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.2" % Test
)