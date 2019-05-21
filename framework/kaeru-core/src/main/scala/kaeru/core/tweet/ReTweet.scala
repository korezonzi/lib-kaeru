package kaeru.core.tweet

import ixias.model._
import java.time.LocalDateTime

import kaeru.udb.user.User

/* ReTweet情報 */
import ReTweet._
case class ReTweet(
  id:             Option[Id],            // 管理ID
  tid:            Tweet.Id,              // ツイートユーザー ID
  uid:            User.Id,               // ファボっているユーザーID
  updatedAt:      LocalDateTime = NOW,   // データ更新日
  createdAt:      LocalDateTime = NOW,   // データ作成日
) extends EntityModel[Id]

object ReTweet {
  val  Id  = the[Identity[Id]]
  type Id  = Long @@ FavoriteTweet
}
