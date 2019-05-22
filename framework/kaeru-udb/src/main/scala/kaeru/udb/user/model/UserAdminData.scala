package kaeru.udb.user.model

import ixias.model._
import java.time.LocalDateTime

/* ユーザー管理 */
import AdminData._
case class AdminData(
  id:         Option[Id],           // User Id
  email:      String,               // メールアドレス
  phone:      Option[Int],          // 電話番号
  password:   String,               // パスワード(Hash化)
  updatedAt:  LocalDateTime = NOW,  // データ更新日
  createdAt:  LocalDateTime = NOW,  // データ作成日
) extends EntityModel[Id]

object AdminData {
  val  Id   = User.Id
  type Id   = User.Id
}


