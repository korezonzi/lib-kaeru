package kaeru.app.persistence

import slick.jdbc.JdbcProfile
import ixias.persistence.SlickRepository
import kaeru.app.model.FollowRelation
import scala.concurrent.Future

// ユーザ情報管理
//~~~~~~~~~~~~~~~~
case class FollowRelationRepository[P <: JdbcProfile]()(implicit val driver: P)
    extends SlickRepository[FollowRelation.Id, FollowRelation, P]
    with db.SlickColumnTypes[P]
    with db.SlickResourceProvider[P]
{
  import api._

  /**
   * FF情報の取得
   */
   def get(id:Id):Future[Option[EntityEmbeddedId]] = {
    RunDBAction(FollowRelationTable, "slave") { _
      .filter(_.id === id)
      .result.headOption
    }
  }

  /**
   * FF情報の追加
   */
  def add(userpass:EntityWithNoId):Future[Id] = {
    RunDBAction(FollowRelationTable) { slick =>
      slick returning slick.map(_.id) += userpass.v
    }
  }

  /**
   * FF情報の削除
   */
  def remove(id:Id):Future[Option[EntityEmbeddedId]] = {
    RunDBAction(FollowRelationTable) { slick =>
      val row = slick.filter(_.id === id)
      for {
        old <- row.result.headOption
        _   <- row.delete
      } yield old
    }
  }

  /**
   * FF情報更新
   */
  @deprecated("use update method", "2.0")  
  def update(data: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] = {
    RunDBAction(FollowRelationTable) { slick =>
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
