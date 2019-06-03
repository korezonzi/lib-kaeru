package kaeru.udb.persistence.db

import slick.jdbc.JdbcProfile
import kaeru.udb.model._

trait SlickColumnTypes[P <: JdbcProfile] {

  implicit val driver: P
  import driver.api._

  // -- [ ID定義 ] ------------------------------------------------------------
  implicit val udbIdT01 = MappedColumnType.base[User.Id,              Long](id => id,   User.Id(_))
  implicit val udbIdT02 = MappedColumnType.base[UserSession.Token,    String](token => token, UserSession.Token(_))
}
