package kaeru.udb.follow

import ixias.model._
import java.time.LocalDateTime

import kaeru.udb.user.User

/* フォロー関係 */
import FollowRelation._
case class FollowRelation (
  id:         Option[Id],            // 管理ID
  userId:     User.Id,               // フォローしている側のユーザーID
  toUserId:   User.Id,               // フォローされている側のユーザーID
  updatedAt:  LocalDateTime = NOW,   // データ更新日
  createdAt:  LocalDateTime = NOW,   // データ作成日
) extends EntityModel[Id]

object FollowRelation {
  val  Id   = the[Identity[Id]]
  type Id   = Long @@ FollowRelation
}
