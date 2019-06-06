package kaeru.udb.persistence

import slick.jdbc.JdbcProfile
import ixias.persistence.SlickRepository
import kaeru.udb.model.User
import scala.concurrent.Future
import kaeru.udb.model.UserPassword

// パスワード管理
//~~~~~~~~~~~~~~~~
case class UserPasswordRepository[P <: JdbcProfile]()(implicit val driver: P)
    extends SlickRepository[User.Id, UserPassword, P]
    with db.SlickColumnTypes[P]
    with db.SlickResourceProvider[P]
{
  import api._

  /**
   * ユーザーパスワード情報の取得
   */
   def get(id:Id):Future[Option[EntityEmbeddedId]] = {
    RunDBAction(UserPasswordTable, "slave") { _
      .filter(_.id === id)
      .result.headOption
    }
  }

  /**
   * ユーザーパスワードの作成
   */
  def add(userpass:EntityWithNoId):Future[Id] = {
    RunDBAction(UserPasswordTable) { slick =>
      slick returning slick.map(_.id) += userpass.v
    }
  }

  /**
   * ユーザーパスワードの削除
   */
  def remove(id:User.Id):Future[Option[EntityEmbeddedId]] = {
    RunDBAction(UserPasswordTable) { slick =>
      val row = slick.filter(_.id === id)
      for {
        old <- row.result.headOption
        _   <- row.delete
      } yield old
    }
  }

  /**
   * ユーザーパスワード情報更新
   */
  def update(data: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserPasswordTable) { slick =>
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
