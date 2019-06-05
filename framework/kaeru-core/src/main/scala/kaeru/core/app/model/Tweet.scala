package kaeru.app.model

import ixias.model._
import java.time.LocalDateTime

import kaeru.udb.model.User

/* Tweet情報 */
import Tweet._
case class Tweet(
  id:             Option[Id],               // 管理ID
  uid:            User.Id,                  // ツイートユーザー ID
  imageUrl:       Option[String]   = None,  // イメージURL
  text:           String,                   // tweet内容
  reply:          Option[Tweet.Id] = None,  // リプライかどうか
  favocount:      Int              = 0,     // Favoカウント
  rtcount:        Int              = 0,     // RTカウント
  updatedAt:      LocalDateTime    = NOW,   // データ更新日
  createdAt:      LocalDateTime    = NOW,   // データ作成日
) extends EntityModel[Id]

/* コンパニオンオブジェクト */
object Tweet {
  val  Id  = the[Identity[Id]]
  type Id  = Long @@ Tweet

  type WithNoId   = Entity.WithNoId   [Id, Tweet]
  type EmbeddedId = Entity.EmbeddedId [Id, Tweet]

  object WithNoId {
    def apply(
      id:     Option[Id],
      uid:    User.Id,
      imageUrl: Option[String],
      text:   String,
    ): WithNoId =
    Entity.WithNoId (
      new Tweet(
        None,
        uid,
        None,
        text
      )
    )
  }
}
