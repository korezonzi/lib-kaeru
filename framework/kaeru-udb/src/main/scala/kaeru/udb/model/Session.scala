package kaeru.udb.model

import java.time.LocalDateTime
import ixias.model._

/* セッション管理 */
import Session._
case class Session(
  id:                Option[Id],            // 管理ID
  uid:               User.Id,               // ユーザーID
  updatedAt:         LocalDateTime = NOW,   // データ更新日
  createdAt:         LocalDateTime = NOW,   // データ作成日
) extends EntityModel[Id]

/* コンパニオンオブジェクト */
object Session {
  val  Id = the[Identity[Id]]
  type Id = Long @@ Session
}
