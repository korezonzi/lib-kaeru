package kaeru.udb.block

import ixias.model._
import java.time.LocalDateTime

import kaeru.udb.user.User 

/* ブロックしているユーザーの管理 */
import BlockUser._
case class BlockUser (
  id:         Option[Id],            // 管理ID
  own:        User.Id,               // ブロックする側のユーザーID
  target:     User.Id,               // ブロックされる側のユーザーID
  updatedAt:  LocalDateTime = NOW,   // データ更新日
  createdAt:  LocalDateTime = NOW    // データ作成日
) extends EntityModel[Id]

object BlockUser {
  val  Id  =  the[Identity[Id]]
  type Id  =  Long @@ BlockUser
}
