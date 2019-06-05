package kaeru.app.persistence.db

import java.time.LocalDateTime
import slick.jdbc.JdbcProfile
import ixias.persistence.model.Table

import kaeru.udb.model.User
import kaeru.app.model.FollowRelation

// テーブル定義
//~~~~~~~~~~~~~~
case class FollowRelationTable[P <: JdbcProfile]()(implicit val driver: P)
  extends Table[FollowRelation, P] with SlickColumnTypes[P] {
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
    class Table(tag: Tag) extends BasicTable(tag, "follow_relation") {
      // Columns
      /* @1 */ def id         = column[FollowRelation.Id] ("id",         O.UInt64, O.PrimaryKey, O.AutoInc)
      /* @2 */ def fromUserId = column[User.Id]           ("taget_id",   O.UInt64)
      /* @3 */ def toUserId   = column[User.Id]           ("to_user_id", O.UInt64)
      /* @4 */ def updatedAt  = column[LocalDateTime]     ("update_at",  O.TsCurrent)
      /* @5 */ def createdAt  = column[LocalDateTime]     ("created_at", O.Ts)

      // All columns as a tuple
      type TableElementTuple = (
        Option[FollowRelation.Id], User.Id, User.Id, LocalDateTime, LocalDateTime
      )

    // The * projection of the table
    def * = (id.?, fromUserId, toUserId,updatedAt, createdAt) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (t: TableElementTuple) => FollowRelation(
        t._1, t._2, t._3, t._4,t._5
      ),
      /** The bidirectional mappings : Model => Tuple(table) */
      (v: TableElementType) => FollowRelation.unapply(v).map { t => (
        t._1, t._2, t._3, LocalDateTime.now(), t._5,
      ) }
    )
  }
}
