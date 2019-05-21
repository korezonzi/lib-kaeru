package kaeru.udb.user

import ixias.model._
import java.time.LocalDateTime

import AdminData._
case class AdminData(
  id:         Option[Id],           // User Id
  email:      String,               // メールアドレス
  password:   String,               // パスワード(Hash化)
  updatedAt:  LocalDateTime = NOW,  // データ更新日
  createdAt:  LocalDateTime = NOW,  // データ作成日
) extends EntityModel[Id]

object AdminData {
  val  Id   = User.Id
  type Id   = User.Id
}
