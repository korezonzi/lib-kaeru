package kaeru.udb.model

import ixias.model._
import java.time.LocalDateTime

/* ユーザー管理 */
import UserAdminData._
case class UserAdminData(
  id:            Option[Id],           // User Id
  email:         String,               // メールアドレス
  phone:         Option[Int],          // 電話番号
  updatedAt:     LocalDateTime = NOW,  // データ更新日
  createdAt:     LocalDateTime = NOW,  // データ作成日
) extends EntityModel[Id]

/* コンパニオンオブジェクト */
object UserAdminData {
  val  Id   = User.Id
  type Id   = User.Id
}
