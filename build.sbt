name := "demoPlayScala"

version := "1.0"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  Dependencies.play,
  Dependencies.playJson,
  Dependencies.slickPg,
  Dependencies.slickHikariCp,
  Dependencies.scalaGuice,
  Dependencies.h2database,
  Dependencies.scalatestplus,
  Dependencies.mockito
)

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")