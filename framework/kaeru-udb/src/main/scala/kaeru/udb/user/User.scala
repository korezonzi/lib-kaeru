package kaeru.udb.user

import ixias.model._
import java.time.{LocalDateTime,LocalDate}


// ユーザー情報
import User._
case class User(
  id:         Option[Id],           // 管理ID
  uid:        String,               // 実際に表示するユーザーID
  name:       String,               // ユーザーネーム
  phone:      Option[Int],          // 電話番号
  address:    Option[String],       // 住所
  text:       Option[String],       // プロフィールの紹介文
  link:       Option[String],       // プロフィールにはっつけるリンク
  birth:      Option[LocalDate],    // 誕生日
  iconUrl:    Option[String],       // アイコンURL
  headerUrl:  Option[String],       // ヘッダーアイコンのURL
  updatedAt:  LocalDateTime = NOW,  // データ更新日
  createdAt:  LocalDateTime = NOW,  // データ作成日
) extends EntityModel[Id]

object User {
  val  Id = the[Identity[Id]]
  type Id = Long @@ User
}

