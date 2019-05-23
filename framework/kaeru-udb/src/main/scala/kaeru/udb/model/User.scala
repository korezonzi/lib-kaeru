package kaeru.udb.model

import ixias.model._
import java.time.{LocalDateTime,LocalDate}

/* ユーザー情報 */
import User._
case class User(
  id:            Option[Id],           // 管理ID
  identityId:    String,               // 実際に表示するユーザーID
  name:          String,               // ユーザーネーム
  address:       Option[String],       // 住所
  text:          Option[String],       // プロフィールの紹介文
  birth:         Option[LocalDate],    // 誕生日
  iconUrl:       Option[String],       // アイコンURL
  headerUrl:     Option[String],       // ヘッダーアイコンのURL
  updatedAt:     LocalDateTime = NOW,  // データ更新日
  createdAt:     LocalDateTime = NOW,  // データ作成日
) extends EntityModel[Id]

/* コンパニオンオブジェクト */
object User {
  val  Id = the[Identity[Id]]
  type Id = Long @@ User
}
