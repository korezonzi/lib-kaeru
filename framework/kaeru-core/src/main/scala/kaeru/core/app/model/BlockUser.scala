package kaeru.app.model

import ixias.model._
import java.time.LocalDateTime

import kaeru.udb.model.User 

/* ブロックユーザー管理 */
import BlockUser._
case class BlockUser (
  id:             Option[Id],            // 管理D
  fromId:         User.Id,               // ブロックする側のアカウントID
  targetId:       User.Id,               // ブロックされる側のアカウントID
  updatedAt:      LocalDateTime = NOW,   // データ更新日
  createdAt:      LocalDateTime = NOW    // データ作成日
) extends EntityModel[Id]

/* コンパニオンオブジェクト */
object BlockUser {
  val  Id  =  the[Identity[Id]]
  type Id  =  Long @@ BlockUser

  type WithNoId   = Entity.WithNoId   [Id, BlockUser]
  type EmbeddedId = Entity.EmbeddedId [Id, BlockUser]
}
