package kaeru.udb.model

import ixias.model._
import java.time.LocalDateTime

/* パスワード情報 */
import Password._
case class Password(
  id:            Option[Id],                         // ユーザーID
  hashPass:      String,                             // ハッシュ化したPass
  //state:,  // 初ログインかどうかを持つ
  updatedAt:     LocalDateTime   = NOW,              // データ更新日
  createdAt:     LocalDateTime   = NOW,              // データ作成日
) extends EntityModel[Id]

/* コンパニオンオブジェクト */
object Password {
  val   Id = User.Id
  type  Id = User.Id
}
