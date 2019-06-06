package kaeru.udb.model

import ixias.model._
import java.time.LocalDateTime

/* パスワード情報 */
case class UserPassword(
  id:            Option[User.Id],                    // ユーザーID
  hashPass:      String,                             // ハッシュ化したPass
  updatedAt:     LocalDateTime   = NOW,              // データ更新日
  createdAt:     LocalDateTime   = NOW,              // データ作成日
) extends EntityModel[User.Id] {
  /** パスワードの検証を行う */
  def verify(password: String): Boolean =
    UserPassword.verify(password, hashPass)
}

/* コンパニオンオブジェクト */
object UserPassword {

  type WithNoId   = Entity.WithNoId   [User.Id, UserPassword]
  type EmbeddedId = Entity.EmbeddedId [User.Id, UserPassword]

  // --[ パスワード文字列操作 ]-------------------------------------------------
  /** パスワードのハッシュ値化 */
  def hash(password: String): String =
    ixias.security.PBKDF2.hash(password)

  /** パスワードの検証を行う */
  def verify(password: String, hash: String): Boolean =
    ixias.security.PBKDF2.compare(password, hash)

  def apply(
    id:         User.Id,
    password:   String
  ): EmbeddedId =
  Entity.EmbeddedId(
    new UserPassword(
      Some(id),
      hash(password)
    )
  )
} 
