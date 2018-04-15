import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "scala-selenium",
    libraryDependencies += scalaTest % Test,
//    libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "2.35.0" % "test"
      libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "3.11.0" % "test",
//      libraryDependencies += "net.sourceforge.htmlunit" % "htmlunit" % "2.30" % "test",
//      libraryDependencies += "org.seleniumhq.selenium" % "htmlunit-driver" % "2.30.0",
      libraryDependencies += "io.github.bonigarcia" % "webdrivermanager" % "1.7.1" % "test"

  )