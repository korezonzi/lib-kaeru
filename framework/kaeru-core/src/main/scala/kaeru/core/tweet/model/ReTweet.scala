package kaeru.core.tweet.model

import ixias.model._
import java.time.LocalDateTime

import kaeru.udb.user.model.User

/* ReTweet管理 */
import ReTweet._
case class ReTweet(
  id:             Option[Id],            // ツイートID
  uid:            User.Id,               // ツイートしたユーザーID
  updatedAt:      LocalDateTime = NOW,   // データ更新日
  createdAt:      LocalDateTime = NOW,   // データ作成日
) extends EntityModel[Id]

object ReTweet {
  val  Id  = Tweet.Id
  type Id  = Tweet.Id
}
