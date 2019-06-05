package kaeru.app.persistence.db

import slick.jdbc.JdbcProfile

trait SlickResourceProvider[P <: JdbcProfile] {

  implicit val driver: P

  // --[ テーブル定義 ] --------------------------------------------------------
  object BlockUserTable                extends BlockUserTable
  object FollowRelationTable           extends FollowRelationTable
  object TweetTable                    extends TweetTable
  object ReTweetTable                  extends ReTweetTable
  object TweetFavoriteTable            extends TweetFavoriteTable

  // --[ 全てのテーブル定義 ] --------------------------------------------------
  lazy val AllTables = Seq(
    BlockUserTable,
    FollowRelationTable,
    TweetTable,
    ReTweetTable,
    TweetFavoriteTable
  )
}

