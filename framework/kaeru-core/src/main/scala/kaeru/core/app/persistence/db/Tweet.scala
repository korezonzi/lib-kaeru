package kaeru.app.persistence.db

import java.time.LocalDateTime
import slick.jdbc.JdbcProfile
import ixias.persistence.model.Table

import kaeru.app.model.Tweet

import kaeru.udb.model.User

// テーブル定義
//~~~~~~~~~~~~~~
case class TweetTable[P <: JdbcProfile]()(implicit val driver: P)
  extends Table[Tweet, P] with SlickColumnTypes[P] {
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
    class Table(tag: Tag) extends BasicTable(tag, "tweet") {
      // Columns
      /* @1 */ def id         = column[Tweet.Id]          ("id",         O.UInt64, O.PrimaryKey, O.AutoInc)
      /* @2 */ def uid        = column[User.Id]           ("user_id",    O.UInt64)
      /* @3 */ def imageUrl   = column[Option[String]]    ("image_url",  O.Utf8Char255)
      /* @4 */ def text       = column[String]            ("text",       O.Utf8Char255)
      /* @5 */ def reply      = column[Option[Tweet.Id]]  ("reply",      O.UInt64)
      /* @6 */ def favocount  = column[Int]               ("favo_count", O.UInt16)
      /* @7 */ def rtcount    = column[Int]               ("rt_count",   O.UInt16)
      /* @8 */ def updatedAt  = column[LocalDateTime]     ("update_at",  O.TsCurrent)
      /* @9 */ def createdAt  = column[LocalDateTime]     ("created_at", O.Ts)

      // All columns as a tuple
      type TableElementTuple = (
        Option[Tweet.Id], User.Id, Option[String], String, Option[Tweet.Id],
        Int,Int,LocalDateTime, LocalDateTime
      )

    // The * projection of the table
    def * = (id.?, uid, imageUrl, text, reply, favocount,rtcount,updatedAt, createdAt) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (t: TableElementTuple) => Tweet(
        t._1, t._2, t._3, t._4,t._5,t._6,t._7,t._8,t._9
      ),
      /** The bidirectional mappings : Model => Tuple(table) */
      (v: TableElementType) => Tweet.unapply(v).map { t => (
        t._1, t._2,t._3,t._4,t._5 ,t._6,t._7,LocalDateTime.now(), t._9
      ) }
    )
  }
}
