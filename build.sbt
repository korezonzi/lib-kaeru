/**
 *  Setting file
 */


 lazy val commonsetting = Seq(
   organiazation := "lib.flog",
   scalaVersion  := "2.12.8",
   resolvers ++= Seq(
     "Typesafe Releases"  at "http://repo.typesafe.com/typesafe/releases/",
     "Sonatype Release"   at "https://oss.sonatype.org/content/repositories/releases/",
     "Sonatype Snapshot"  at "https://oss.sonatype.org/content/repositories/snapshots/",
     "IxiaS Releases"     at "http://maven.ixias.net.s3-ap-northeast-1.amazonaws.com/releases",
     "IxiaS Snapshots"    at "http://maven.ixias.net.s3-ap-northeast-1.amazonaws.com/snapshots",
     "keyczar"            at "https://raw.githubusercontent.com/google/keyczar/master/java/maven/"
   )
 )
