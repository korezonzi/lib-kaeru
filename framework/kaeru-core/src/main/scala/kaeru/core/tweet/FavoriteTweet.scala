package kaeru.core.tweet

import ixias.model._
import java.time.LocalDateTime

import kaeru.udb.user.User

/* Tweet情報 */
import FavoriteTweet._
case class FavoriteTweet(
  id:             Option[Id],            // 管理ID
  tid:            Tweet.Id,              // ツイートユーザー ID
  uid:            User.Id,               // ファボっているユーザーID
  updatedAt:      LocalDateTime = NOW,   // データ更新日
  createdAt:      LocalDateTime = NOW,   // データ作成日
) extends EntityModel[Id]

object FavoriteTweet {
  val  Id  = the[Identity[Id]]
  type Id  = Long @@ FavoriteTweet
}
