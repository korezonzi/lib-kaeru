package kaeru.app.persistence.db

import java.time.LocalDateTime
import slick.jdbc.JdbcProfile
import ixias.persistence.model.Table

import kaeru.udb.model.User
import kaeru.app.model.BlockUser

// テーブル定義
//~~~~~~~~~~~~~~
case class BlockUserTable[P <: JdbcProfile]()(implicit val driver: P)
  extends Table[BlockUser, P] with SlickColumnTypes[P] {
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
    class Table(tag: Tag) extends BasicTable(tag, "block_user") {
      // Columns
      /* @1 */ def id         = column[BlockUser.Id]      ("id",         O.UInt64, O.PrimaryKey, O.AutoInc)
      /* @2 */ def targetId   = column[User.Id]           ("taget_id",   O.Utf8Char255)
      /* @3 */ def updatedAt  = column[LocalDateTime]     ("update_at",  O.TsCurrent)
      /* @4 */ def createdAt  = column[LocalDateTime]     ("created_at", O.Ts)

      // All columns as a tuple
      type TableElementTuple = (
        Option[BlockUser.Id], User.Id, LocalDateTime, LocalDateTime
      )

    // The * projection of the table
    def * = (id.?, targetId, updatedAt, createdAt) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (t: TableElementTuple) => BlockUser(
        t._1, t._2, t._3, t._4,
      ),
      /** The bidirectional mappings : Model => Tuple(table) */
      (v: TableElementType) => BlockUser.unapply(v).map { t => (
        t._1, t._2, LocalDateTime.now(), t._4,
      ) }
    )
  }
}
