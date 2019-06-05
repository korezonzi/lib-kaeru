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
   * Tweet情報の追加
   */
  def add(tweet:EntityWithNoId): Future[Id] = {
    RunDBAction(TweetTable) { slick =>
      slick returning slick.map(_.id) += tweet.v
    }
  }

  /**
   * Tweet情報の削除
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

  /**
   * Tweet情報更新
   */
  def update(data: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] = {
    RunDBAction(TweetTable) { slick =>
      val row = slick.filter(_.id === data.id)
      for {
        old <- row.result.headOption
        _   <- old match {
          case None    => DBIO.successful(0)
          case Some(_) => row.update(data.v)
        }
      } yield old
    }
  }
}
