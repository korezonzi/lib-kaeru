package kaeru.udb.persistence

import slick.jdbc.JdbcProfile
import ixias.persistence.SlickRepository
import kaeru.udb.model.User
import scala.concurrent.Future
import kaeru.udb.model.UserSession

// ユーザ情報管理
//~~~~~~~~~~~~~~~~
case class UserSessionRepository[P <: JdbcProfile]()(implicit val driver: P)
    extends SlickRepository[User.Id, UserSession, P]
    with db.SlickColumnTypes[P]
    with db.SlickResourceProvider[P]
{
  def get(id:User.Id):Future[Option[EntityEmbeddedId]]               = ???
  def add(user:EntityWithNoId):Future[User.Id]                       = ???
  def remove(id:User.Id):Future[Option[EntityEmbeddedId]]            = ???
  def update(user:EntityEmbeddedId):Future[Option[EntityEmbeddedId]] = ???
}
