package kaeru.core.tweet.model

import ixias.model._
import java.time.LocalDateTime

import kaeru.udb.user.model.User

/* Tweet情報 */
import Tweet._
case class Tweet(
  id:             Option[Id],            // 管理ID
  uid:            User.Id,               // ツイートユーザー ID
  link:           String,                // リンクコピー用??
  imageUrl:       Option[String],        // イメージURL
  text:           String,                // tweet内容
  updatedAt:      LocalDateTime = NOW,   // データ更新日
  createdAt:      LocalDateTime = NOW,   // データ作成日
) extends EntityModel[Id]

object Tweet {
  val  Id  = the[Identity[Id]]
  type Id  = Long @@ Tweet
}
