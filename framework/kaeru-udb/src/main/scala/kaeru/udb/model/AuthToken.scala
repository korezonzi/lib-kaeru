package kaeru.udb.model

import ixias.model._
import java.time.LocalDateTime

/* 認証用のトークン */
import AuthToken._
case class AuthToken(
  id:          Option[Id],               // 認証用のトークン
  uid:         User.Id,                  // ユーザーID
  updatedAt:   LocalDateTime    = NOW,   // データ更新日
  createdAt:   LocalDateTime    = NOW,   // データ作成日
) extends EntityModel[Id]

/* コンパニオンオブジェクト */
object AuthToken {
  val  Id = the[Identity[Id]]
  type Id = String @@ AuthToken
}
