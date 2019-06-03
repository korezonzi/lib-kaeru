package kaeru.udb.persistence.db

import slick.jdbc.JdbcProfile
import kaeru.udb.model.UserSession

trait SlickResourceProvider[P <: JdbcProfile] {

  implicit val driver: P

  // --[ テーブル定義 ] --------------------------------------------------------
  object UserTable                 extends UserTable
  object UserPasswordTable         extends UserPasswordTable
  object UserProfileTable          extends UserProfileTable
  object UserSessionTable          extends UserSessionTable

  // --[ 全てのテーブル定義 ] --------------------------------------------------
  lazy val AllTables = Seq(
    UserTable,
    UserPasswordTable,
    UserProfileTable,
    UserSession
  )
}

