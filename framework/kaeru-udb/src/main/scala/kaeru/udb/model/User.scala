package kaeru.udb.model

import ixias.model._
import java.time.LocalDateTime

/* ユーザー管理 */
import User._
case class User(
  id:            Option[Id],           // User Id
  email:         String,               // メールアドレス
  phone:         Option[Int],          // 任意の電話番号
  updatedAt:     LocalDateTime = NOW,  // データ更新日
  createdAt:     LocalDateTime = NOW,  // データ作成日
) extends EntityModel[Id]

/* コンパニオンオブジェクト */
object User {
  val  Id   = the[Identity[Id]]
  type Id   = Long @@ User

  type WithNoId   = Entity.WithNoId   [Id, User]
  type EmbeddedId = Entity.EmbeddedId [Id, User]

  def apply(
    id:         Id,
    email:      String
  ): Entity.EmbeddedId[User.Id,UserPassword] =
  Entity.EmbeddedId[User.Id,UserPassword](
    new UserPassword(
      Some(id),
      email
    )
  )
}
