package kaeru.app.model

import ixias.model._
import java.time.LocalDateTime

import kaeru.udb.model.User

/* いいね管理 */
import TweetFavorite._
case class TweetFavorite(
  id:             Option[Id],             // 管理ID
  tweetId:        Tweet.Id,               // ツイートID
  uid:            User.Id,                // いいねしているユーザーID
  updatedAt:      LocalDateTime = NOW,    // データ更新日
  createdAt:      LocalDateTime = NOW,    // データ作成日
) extends EntityModel[Id]

/* コンパニオンオブジェクト */
object TweetFavorite {
  val  Id  = the[Identity[Id]]
  type Id  = Long @@ TweetFavorite

  type WithNoId   = Entity.WithNoId   [Id, TweetFavorite]
  type EmbeddedId = Entity.EmbeddedId [Id, TweetFavorite]
}
