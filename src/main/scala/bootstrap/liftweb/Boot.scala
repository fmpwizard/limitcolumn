package bootstrap.liftweb

import net.liftweb._

import common.{Box, Full}
import http._
import http.Html5Properties
import mapper._
import sitemap._
import code.model.MyModel

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot() {
    // where to search snippet
    LiftRules.addToPackages("code")

    // Configure db data source
    DefaultConnectionIdentifier.jndiName = "jdbc/test"

    // avoid an exception to be thrown when using limit as a db column name.
    // without this hack an exception will be thrown.
    DB.userReservedWords = Full(Set("limit"))

    // Use Lift's Mapper ORM to populate the database
    // you don't need to use Mapper to use Lift... use
    // any ORM you want
    Schemifier.schemify(true, Schemifier.infoF _,
     MyModel)

    // Build SiteMap
    val entries = List(Menu.i("Home") / "index")

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMap(SiteMap(entries: _*))

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // HTML 5
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))

    // Make a transaction span the whole HTTP request
    S.addAround(DB.buildLoanWrapper())
  }
}
