package kaeru.udb.model

import java.time.LocalDateTime
import ixias.model._

/* セッション管理 */
import UserSession._
case class UserSession(
  id:                Option[Id],            // 管理ID
  token:             Token,                 // ユーザーID
  expiryAt:          LocalDateTime,         // セッション 
  updatedAt:         LocalDateTime = NOW,   // データ更新日
  createdAt:         LocalDateTime = NOW,   // データ作成日
) extends EntityModel[Id]

/* コンパニオンオブジェクト */
object UserSession {
  val  Token      = the[Identity[Id]]
  type Token      = String @@ UserSession

  type Id         = User.Id

  type WithNoId   = Entity.WithNoId   [Id, UserSession]
  type EmbeddedId = Entity.EmbeddedId [Id, UserSession]
}
