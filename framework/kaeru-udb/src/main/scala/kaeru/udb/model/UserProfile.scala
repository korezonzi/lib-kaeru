package kaeru.udb.model

import ixias.model._
import java.time.{LocalDateTime,LocalDate}

/* ユーザー情報 */
import User._
case class UserProfile(
  id:            Option[Id],                 // 管理ID
  identity:      String,                     // 実際に表示するユーザーID
  name:          String,                     // ユーザーネーム
  address:       Option[String]     = None,  // プロフィール欄に表示する住所
  text:          Option[String]     = None,  // プロフィールの紹介文
  birth:         Option[LocalDate]  = None,  // プロフィールに表示する誕生日
  iconUrl:       Option[String]     = None,  // アイコンURL
  headerUrl:     Option[String]     = None,  // ヘッダーアイコンのURL
  updatedAt:     LocalDateTime      = NOW,   // データ更新日
  createdAt:     LocalDateTime      = NOW,   // データ作成日
) extends EntityModel[Id]

/* コンパニオンオブジェクト */
object UserProfile {
  val  Id         = User.Id
  type Id         = User.Id
  type WithNoId   = Entity.WithNoId   [Id, UserProfile]
  type EmbeddedId = Entity.EmbeddedId [Id, UserProfile]

  def apply(
    id:       Id,
    identity: String,
    name:     String
  ): EmbeddedId =
    Entity.EmbeddedId {
      new UserProfile(
        Some(id),
        identity,
        name
      )
    }
}
