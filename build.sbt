
name := "limitDbColumn"

version := "0.1.0"

organization := "eureka.inf.br"
 
scalaVersion := "2.9.1"

scalacOptions ++= DefaultOptions.scalac

scalacOptions in Compile += Opts.compile.deprecation

scalacOptions in Compile += Opts.compile.unchecked
 
seq(webSettings: _*)

env in Compile := Some(file("./jetty-env.xml") asFile)

resolvers ++= Seq(
  "Java.net Maven2 Repo" at "http://download.java.net/maven/2/",
  "Sonatype OSS Snapshot Repository" at "https://oss.sonatype.org/content/repositories/releases/",
  "Maven Central" at "http://repo1.maven.org/maven2"
)

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.0.0")

libraryDependencies ++= {
  val liftVersion = "2.4"
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default" withSources(),
    "net.liftweb" %% "lift-wizard" % liftVersion % "compile->default" withSources(),
    "net.liftweb" %% "lift-json-ext" % liftVersion % "compile->default" withSources(),
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile->default" withSources()
  )
}
  
libraryDependencies ++= Seq(
    "javax.servlet"         % "servlet-api"        % "2.5"              % "provided->default",
    "postgresql"            % "postgresql"         % "9.1-901-1.jdbc4"  % "compile",
    "ch.qos.logback"        % "logback-classic"    % "1.0.0",
    "org.eclipse.jetty"     % "jetty-webapp"       % "8.0.4.v20111024"  % "container",
    "org.eclipse.jetty"     % "jetty-plus"         % "8.0.4.v20111024"  % "container"
)
