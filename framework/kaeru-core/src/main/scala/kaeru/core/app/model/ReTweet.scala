package kaeru.app.model

import ixias.model._
import java.time.LocalDateTime

import kaeru.udb.model.User

/* ReTweet管理 */
import ReTweet._
case class ReTweet(
  id:             Option[Id],            // 管理ID
  tid:            Tweet.Id,              // ツイートID
  uid:            User.Id,               // ツイートしたユーザーID
  updatedAt:      LocalDateTime = NOW,   // データ更新日
  createdAt:      LocalDateTime = NOW,   // データ作成日
) extends EntityModel[Id]

/* コンパニオンオブジェクト */
object ReTweet {
  
  val  Id  =  the[Identity[Id]]
  type Id  =  Long @@ ReTweet

  type WithNoId   = Entity.WithNoId   [Id, ReTweet]
  type EmbeddedId = Entity.EmbeddedId [Id, ReTweet]
}
