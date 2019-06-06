package kaeru.app.persistence

import slick.jdbc.JdbcProfile
import ixias.persistence.SlickRepository
import kaeru.app.model.Tweet
import scala.concurrent.Future
// ツイート情報管理
//~~~~~~~~~~~~~~~~
case class TweetRepository[P <: JdbcProfile]()(implicit val driver: P)
    extends SlickRepository[Tweet.Id, Tweet, P]
    with db.SlickColumnTypes[P]
    with db.SlickResourceProvider[P]
{
  import api._

  /**
   * Tweet情報の取得
   */
  def get(id:Id):Future[Option[EntityEmbeddedId]] = {
    RunDBAction(TweetTable, "slave") { _
      .filter(_.id === id)
      .result.headOption
    }
  }

  /**
   * Tweetする
   */
  def add(tweet:EntityWithNoId): Future[Id] = {
    RunDBAction(TweetTable) { slick =>
      slick returning slick.map(_.id) += tweet.v
    }
  }

  /**
   * Tweetの削除
   */
  def remove(id:Id):Future[Option[EntityEmbeddedId]] = {
    RunDBAction(TweetTable) { slick =>
      val row = slick.filter(_.id === id)
      for {
        old <- row.result.headOption
        _   <- row.delete
      } yield old
    }
  }

  /* Tweet情報を更新する仕様はないので作らない */
  @deprecated("use update method", "2.0")    
  def update(data: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] =
    Future.failed(new UnsupportedOperationException)    
}
