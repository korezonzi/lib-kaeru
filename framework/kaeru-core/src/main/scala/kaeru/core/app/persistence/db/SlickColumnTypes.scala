package kaeru.app.persistence.db

import slick.jdbc.JdbcProfile
import kaeru.udb.model._
import kaeru.app.model._
trait SlickColumnTypes[P <: JdbcProfile] {

  implicit val driver: P
  import driver.api._

  // -- [ ID定義 ] ------------------------------------------------------------
  implicit val udbIdT01 = MappedColumnType.base[Tweet.Id,             Long](id => id,   Tweet.Id(_))
  implicit val udbIdT02 = MappedColumnType.base[ReTweet.Id,           Long](id => id,   ReTweet.Id(_))
  implicit val udbIdT03 = MappedColumnType.base[BlockUser.Id,         Long](id => id,   BlockUser.Id(_))
  implicit val udbIdT04 = MappedColumnType.base[User.Id,              Long](id => id,   User.Id(_))
  implicit val udbIdT05 = MappedColumnType.base[TweetFavorite.Id,     Long](id => id,   TweetFavorite.Id(_))
  implicit val udbIdT06 = MappedColumnType.base[FollowRelation.Id,    Long](id => id,   FollowRelation.Id(_))
}
