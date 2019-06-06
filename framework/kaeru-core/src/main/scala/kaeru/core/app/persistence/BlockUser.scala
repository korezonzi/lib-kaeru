package kaeru.app.persistence

import scala.concurrent.Future
import slick.jdbc.JdbcProfile
import ixias.persistence.SlickRepository
import kaeru.app.model.BlockUser

import kaeru.udb.model.User

// ユーザ情報管理
//~~~~~~~~~~~~~~~~
case class BlockUserRepository[P <: JdbcProfile]()(implicit val driver: P)
    extends SlickRepository[BlockUser.Id, BlockUser, P]
    with db.SlickColumnTypes[P]
    with db.SlickResourceProvider[P]
{
  import api._

  /**
   * ブロックユーザー情報の取得
   */
   def get(id:Id):Future[Option[EntityEmbeddedId]] = {
    RunDBAction(BlockUserTable, "slave") { _
      .filter(_.id === id)
      .result.headOption
    }
  }

  /**
   * アカウントがアカウントをフォローしているか判定
   */
  def isBlock(to: User.Id, from: User.Id): Future[Option[BlockUser.Id]] = {
    RunDBAction(BlockUserTable,"slave") { _
      .filter(x => x.fromId === to && x.targetId === from)
      .map(_.id)
      .result
      .headOption
    }
  }

  /**
   * ユーザーがユーザーをブロックする
   */
  def add(userpass:EntityWithNoId):Future[Id] = {
    RunDBAction(BlockUserTable) { slick =>
      slick returning slick.map(_.id) += userpass.v
    }
  }

  /**
   * ブロックを解除
   */
  def remove(id:Id):Future[Option[EntityEmbeddedId]] = {
    RunDBAction(BlockUserTable) { slick =>
      val row = slick.filter(_.id === id)
      for {
        old <- row.result.headOption
        _   <- row.delete
      } yield old
    }
  }

  @deprecated("use update method", "2.0")  
  def update(data: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] = 
     Future.failed(new UnsupportedOperationException)
}
