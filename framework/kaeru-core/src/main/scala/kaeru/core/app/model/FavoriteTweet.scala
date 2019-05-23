package kaeru.core.app.model

import ixias.model._
import java.time.LocalDateTime

import kaeru.udb.model.User

/* いいね管理 */
import FavoriteTweet._
case class FavoriteTweet(
  id:             Option[Id],            // 管理ID
  tweetIt:        Tweet.Id,              // ツイートID
  uid:            User.Id,               // いいねしているユーザーID
  updatedAt:      LocalDateTime = NOW,   // データ更新日
  createdAt:      LocalDateTime = NOW,   // データ作成日
) extends EntityModel[Id]

/* コンパニオンオブジェクト */
object FavoriteTweet {
  val  Id  = the[Identity[Id]]
  type Id  = Long @@ FavoriteTweet
}
