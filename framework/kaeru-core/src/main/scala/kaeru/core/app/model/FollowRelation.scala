package kaeru.app.model

import ixias.model._
import java.time.LocalDateTime

import kaeru.udb.model.User

/* フォロー情報管理 */
import FollowRelation._
case class FollowRelation (
  id:           Option[Id],            // 管理ID
  fromUserId:   User.Id,               // フォローする側のユーザーID
  toUserId:     User.Id,               // フォローされている側のユーザーID
  updatedAt:    LocalDateTime = NOW,   // データ更新日
  createdAt:    LocalDateTime = NOW,   // データ作成日
) extends EntityModel[Id]

/* コンパニオンオブジェクト */
object FollowRelation {
  val  Id         = the[Identity[Id]]
  type Id         = Long @@ FollowRelation

  type WithNoId   = Entity.WithNoId   [Id, FollowRelation]
  type EmbeddedId = Entity.EmbeddedId [Id, FollowRelation]
}
