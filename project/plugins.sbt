resolvers += "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"

resolvers += Classpaths.typesafeResolver

resolvers += Resolver.url("Typesafe repository", new java.net.URL("http://typesafe.artifactoryonline.com/typesafe/ivy-releases/"))(Resolver.defaultIvyPatterns)

libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v+"-0.2.11"))

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.1.0")

