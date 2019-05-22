package kaeru.core.tweet.model

import ixias.model._
import java.time.LocalDateTime

import kaeru.udb.user.model.User

/* いいね管理 */
import FavoriteTweet._
case class FavoriteTweet(
  id:             Option[Id],            // ツイートID
  uid:            User.Id,               // いいねしているユーザーID
  updatedAt:      LocalDateTime = NOW,   // データ更新日
  createdAt:      LocalDateTime = NOW,   // データ作成日
) extends EntityModel[Id]

object FavoriteTweet {
  val  Id  = Tweet.Id
  type Id  = Tweet.Id
}
