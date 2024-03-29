package kaeru.udb.persistence.db

import java.time.LocalDateTime
import slick.jdbc.JdbcProfile
import ixias.persistence.model.Table

import kaeru.udb.model.User
import kaeru.udb.model.UserPassword

// テーブル定義
//~~~~~~~~~~~~~~
case class UserPasswordTable[P <: JdbcProfile]()(implicit val driver: P)
  extends Table[UserPassword, P] with SlickColumnTypes[P] {
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
    class Table(tag: Tag) extends BasicTable(tag, "user_password") {
      // Columns
      /* @1 */ def id         = column[User.Id]        ("id",         O.UInt64, O.PrimaryKey, O.AutoInc)
      /* @2 */ def hashPass   = column[String]         ("hash_pass",  O.Utf8Char255)
      /* @3 */ def updatedAt  = column[LocalDateTime]  ("update_at",  O.TsCurrent)
      /* @4 */ def createdAt  = column[LocalDateTime]  ("created_at", O.Ts)

      // All columns as a tuple
      type TableElementTuple = (
        Option[User.Id], String, LocalDateTime, LocalDateTime
      )

    // The * projection of the table
    def * = (id.?, hashPass, updatedAt, createdAt) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (t: TableElementTuple) => UserPassword(
        t._1, t._2, t._3, t._4
      ),
      /** The bidirectional mappings : Model => Tuple(table) */
      (v: TableElementType) => UserPassword.unapply(v).map { t => (
        t._1, t._2,LocalDateTime.now(), t._4,
      ) }
    )
  }
}
