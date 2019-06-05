package kaeru.app.persistence

import slick.jdbc.JdbcProfile
import ixias.persistence.SlickRepository
import kaeru.app.model.ReTweet
import scala.concurrent.Future

// リツイート情報管理
//~~~~~~~~~~~~~~~~
case class ReTweetRepository[P <: JdbcProfile]()(implicit val driver: P)
    extends SlickRepository[ReTweet.Id, ReTweet, P]
    with db.SlickColumnTypes[P]
    with db.SlickResourceProvider[P]
{
  import api._

  /**
   * RT情報の取得
   */
   def get(id:Id):Future[Option[EntityEmbeddedId]] = {
    RunDBAction(ReTweetTable, "slave") { _
      .filter(_.id === id)
      .result.headOption
    }
  }

  /**
   * RT情報の追加
   */
  def add(userpass:EntityWithNoId):Future[Id] = {
    RunDBAction(ReTweetTable) { slick =>
      slick returning slick.map(_.id) += userpass.v
    }
  }

  /**
   * RT情報の削除
   */
  def remove(id:Id):Future[Option[EntityEmbeddedId]] = {
    RunDBAction(ReTweetTable) { slick =>
      val row = slick.filter(_.id === id)
      for {
        old <- row.result.headOption
        _   <- row.delete
      } yield old
    }
  }

  /**
   * RT情報更新
   */
  @deprecated("use update method", "2.0")  
  def update(data: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] = {
    RunDBAction(ReTweetTable) { slick =>
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
