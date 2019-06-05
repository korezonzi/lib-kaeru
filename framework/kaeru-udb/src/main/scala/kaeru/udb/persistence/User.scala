package kaeru.udb.persistence

import slick.jdbc.JdbcProfile
import ixias.persistence.SlickRepository
import kaeru.udb.model.User
import scala.concurrent.Future

// ユーザ情報管理
//~~~~~~~~~~~~~~~~
case class UserRepository[P <: JdbcProfile]()(implicit val driver: P)
    extends SlickRepository[User.Id, User, P]
    with db.SlickColumnTypes[P]
    with db.SlickResourceProvider[P]
{
  import api._

  /**
   * ユーザー情報の取得
   */
   def get(id:Id):Future[Option[EntityEmbeddedId]] = {
    RunDBAction(UserTable, "slave") { _
      .filter(_.id === id)
      .result.headOption
    }
  }

  /**
   * 全ユーザー情報取得
   */
  def getAll():Future[Seq[EntityEmbeddedId]] = {
    RunDBAction(UserTable,"slave") { _
      .result
    }
  }

  /**
   * ユーザー情報の追加
   */
  def add(user:EntityWithNoId):Future[Id] = {
    RunDBAction(UserTable) { slick =>
      slick returning slick.map(_.id) += user.v
    }
  }

  /**
   * アプリから退会
   */
  def remove(id:User.Id):Future[Option[EntityEmbeddedId]] = {
    RunDBAction(UserTable) { slick =>
      val row = slick.filter(_.id === id)
      for {
        old <- row.result.headOption
        _   <- row.delete
      } yield old
    }
  }

  /**
   * 今まで登録されたことないemailか
   */
  def checkEmail(email: String): Future[Boolean] = {
    RunDBAction(UserTable, "slave") { _
      .filter(_.email === email)
      .exists.result
    }
  }

  /**
   * ユーザー情報更新
   */
  def update(data: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserTable) { slick =>
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
