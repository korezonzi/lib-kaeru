package kaeru.udb.persistence

import slick.jdbc.JdbcProfile
import ixias.persistence.SlickRepository
import kaeru.udb.model.User
import scala.concurrent.Future
import kaeru.udb.model.UserProfile

// ユーザープロフィール管理
//~~~~~~~~~~~~~~~~
case class UserProfileRepository[P <: JdbcProfile]()(implicit val driver: P)
    extends SlickRepository[User.Id, UserProfile, P]
    with db.SlickColumnTypes[P]
    with db.SlickResourceProvider[P]
{
  import api._

  /**
   * ユーザープロフィール情報の取得
   */
   def get(id:Id):Future[Option[EntityEmbeddedId]] = {
    RunDBAction(UserProfileTable, "slave") { _
      .filter(_.id === id)
      .result.headOption
    }
  }

  /**
   * ユーザープロフィール情報の追加
   */
  def add(userpass:EntityWithNoId):Future[Id] = {
    RunDBAction(UserProfileTable) { slick =>
      slick returning slick.map(_.id) += userpass.v
    }
  }

  /**
   * ユーザープロフィールの削除
   */
  def remove(id:User.Id):Future[Option[EntityEmbeddedId]] = {
    RunDBAction(UserProfileTable) { slick =>
      val row = slick.filter(_.id === id)
      for {
        old <- row.result.headOption
        _   <- row.delete
      } yield old
    }
  }

  /**
   * ユーザープロフィール情報更新
   */
  def update(data: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserProfileTable) { slick =>
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

