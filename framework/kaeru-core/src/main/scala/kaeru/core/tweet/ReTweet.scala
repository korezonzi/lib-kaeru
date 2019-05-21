package kaeru.core.tweet

import ixias.model._
import java.time.LocalDateTime

import kaeru.udb.user.User

/* ReTweet$B>pJs(B */
import ReTweet._
case class ReTweet(
  id:             Option[Id],            // $B4IM}(BID
  tid:            Tweet.Id,              // $B%D%$!<%H%f!<%6!<(B ID
  uid:            User.Id,               // $B%U%!%\$C$F$$$k%f!<%6!<(BID
  updatedAt:      LocalDateTime = NOW,   // $B%G!<%?99?7F|(B
  createdAt:      LocalDateTime = NOW,   // $B%G!<%?:n@.F|(B
) extends EntityModel[Id]

object ReTweet {
  val  Id  = the[Identity[Id]]
  type Id  = Long @@ FavoriteTweet
}
