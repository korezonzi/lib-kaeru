package kaeru.udb.persistence.db

import java.time.LocalDateTime
import slick.jdbc.JdbcProfile
import ixias.persistence.model.Table

import kaeru.udb.model.UserProfile
import java.time.LocalDate

import kaeru.udb.model.User
// テーブル定義
//~~~~~~~~~~~~~~
case class UserProfileTable[P <: JdbcProfile]()(implicit val driver: P)
  extends Table[UserProfile, P] with SlickColumnTypes[P] {
    import api._
    
    // --[ DNS定義 ] -------------------------------------------------------------
    lazy val dsn = Map(
      "master" -> DataSourceName("ixias.db.mysql://master/kaeru_udb"),
        "slave"  -> DataSourceName("ixias.db.mysql://slave/kaeru_udb")
    )

    // --[ クエリー定義 ] --------------------------------------------------------
    class Query extends BasicQuery(new Table(_)) {}
    lazy val query = new Query

    // --[ テーブル定義 ] --------------------------------------------------------
    class Table(tag: Tag) extends BasicTable(tag, "user_profile") {
      // Columns
      /* @1  */ def id         = column[User.Id]           ("id",         O.UInt64, O.PrimaryKey, O.AutoInc)
      /* @2  */ def identity   = column[String]            ("indentity",  O.Utf8Char255)
      /* @3  */ def name       = column[String]            ("name",       O.Utf8Char255)
      /* @4  */ def address    = column[Option[String]]    ("address",    O.Utf8Char255)
      /* @5  */ def text       = column[Option[String]]    ("text",       O.Utf8Char255)
      /* @6  */ def birth      = column[Option[LocalDate]] ("birth",      O.Ts)
      /* @7  */ def iconUrl    = column[Option[String]]    ("icon_Url",   O.Utf8Char255)
      /* @8  */ def headerUrl  = column[Option[String]]    ("header_Url", O.Utf8Char255)
      /* @9  */ def updatedAt  = column[LocalDateTime]     ("update_at",  O.TsCurrent)
      /* @10 */ def createdAt  = column[LocalDateTime]     ("created_at", O.Ts)

      // All columns as a tuple
      type TableElementTuple = (
        Option[User.Id], String, String, Option[String], Option[String], Option[LocalDate],
        Option[String], Option[String], LocalDateTime, LocalDateTime, 
      )

    // The * projection of the table
    def * = (id.?, identity, name, address, text, birth,
             iconUrl, headerUrl,updatedAt, createdAt) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (t: TableElementTuple) => UserProfile(
        t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10
      ),
      /** The bidirectional mappings : Model => Tuple(table) */
      (v: TableElementType) => UserProfile.unapply(v).map { t => (
        t._1, t._2, t._3,t._4,t._5, t._6, t._7, t._8, LocalDateTime.now(), t._10
      ) }
    )
  }
}

