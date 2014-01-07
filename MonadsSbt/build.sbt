name := "Monads"

version := "1.0"

scalaVersion := "2.10.2"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.11.1" % "test"

resolvers +=  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers +=  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.2")

