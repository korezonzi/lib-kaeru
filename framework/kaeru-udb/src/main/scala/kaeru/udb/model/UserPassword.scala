package kaeru.udb.model

import ixias.model._
import java.time.LocalDateTime

/* パスワード情報 */
case class UserPassword(
  id:            Option[User.Id],                    // ユーザーID
  hashPass:      String,                             // ハッシュ化したPass
  updatedAt:     LocalDateTime   = NOW,              // データ更新日
  createdAt:     LocalDateTime   = NOW,              // データ作成日
) extends EntityModel[User.Id]

/* コンパニオンオブジェクト */
object UserPassword {

  type WithNoId   = Entity.WithNoId   [User.Id, UserPassword]
  type EmbeddedId = Entity.EmbeddedId [User.Id, UserPassword]

  def apply(
    id:         User.Id,
    hashPass:   String
  ): Entity.EmbeddedId[User.Id,UserPassword] =
  Entity.EmbeddedId[User.Id,UserPassword](
    new UserPassword(
      Some(id),
      hashPass
    )
  )
} 
