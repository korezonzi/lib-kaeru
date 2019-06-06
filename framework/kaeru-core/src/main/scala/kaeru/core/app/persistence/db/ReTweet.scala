package kaeru.app.persistence.db

import java.time.LocalDateTime
import slick.jdbc.JdbcProfile
import ixias.persistence.model.Table

import kaeru.udb.model.User
import kaeru.app.model.Tweet
import kaeru.app.model.ReTweet

// テーブル定義
//~~~~~~~~~~~~~~
case class ReTweetTable[P <: JdbcProfile]()(implicit val driver: P)
  extends Table[ReTweet, P] with SlickColumnTypes[P] {
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
    class Table(tag: Tag) extends BasicTable(tag, "re_tweet") {
      // Columns
      /* @1 */ def id         = column[ReTweet.Id]        ("id",         O.UInt64, O.PrimaryKey, O.AutoInc)
      /* @2 */ def tid        = column[Tweet.Id]          ("tweet_id",   O.UInt64)
      /* @3 */ def uid        = column[User.Id]           ("user_id",    O.UInt64)
      /* @4 */ def updatedAt  = column[LocalDateTime]     ("update_at",  O.TsCurrent)
      /* @5 */ def createdAt  = column[LocalDateTime]     ("created_at", O.Ts)

      // All columns as a tuple
      type TableElementTuple = (
        Option[ReTweet.Id], Tweet.Id, User.Id, LocalDateTime, LocalDateTime
      )

    // The * projection of the table
    def * = (id.?, tid, uid, updatedAt, createdAt) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (t: TableElementTuple) => ReTweet(
        t._1, t._2, t._3, t._4, t._5
      ),
      /** The bidirectional mappings : Model => Tuple(table) */
      (v: TableElementType) => ReTweet.unapply(v).map { t => (
        t._1, t._2, t._3, LocalDateTime.now(), t._5
      ) }
    )
  }
}
