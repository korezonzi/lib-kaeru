package kaeru.core.app.model

import ixias.model._
import java.time.LocalDateTime

import kaeru.udb.model.User

/* ReTweet管理 */
import ReTweet._
case class ReTweet(
  id:             Option[Id],            // ツイートID
  uid:            User.Id,               // ツイートしたユーザーID
  updatedAt:      LocalDateTime = NOW,   // データ更新日
  createdAt:      LocalDateTime = NOW,   // データ作成日
) extends EntityModel[Id]

/* コンパニオンオブジェクト */
object ReTweet {
  val  Id  = Tweet.Id
  type Id  = Tweet.Id

  type WithNoId   = Entity.WithNoId   [Id, ReTweet]
  type EmbeddedId = Entity.EmbeddedId [Id, ReTweet]
}
