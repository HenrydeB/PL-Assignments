name := "jlox"
version := "1.0"

// For Java
javacOptions ++= Seq("-source", "17", "-target", "17")

// For Junit5 https://junit.org/junit5/docs/current/user-guide/
libraryDependencies ++= Seq(
  "net.aichler" % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test
)
