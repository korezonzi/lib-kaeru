package kaeru.udb.user

import ixias.model._
import java.time.LocalDateTime

import FollowRelation._
case class FollowRelation (
  id:         Option[Id],            // User Id
  follow:     Seq[User.Id],          // フォローしているユーザーID
  updatedAt:  LocalDateTime = NOW,   // データ更新日
  createdAt:  LocalDateTime = NOW,   // データ作成日
) extends EntityModel[Id]

object FollowRelation {
  val  Id   = User.Id
  type Id   = User.Id
}
