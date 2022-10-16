package perf

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

import scala.Console.println
import scala.util.Random

object Action {
  val getMainPage: HttpRequestBuilder = http("open main page")
    .get("/webtours/")
    .check(status is 200)

  val getCookies: HttpRequestBuilder = http("get Cookies")
    .get("/cgi-bin/welcome.pl?signOff=true")
    .check(status is 200)

  val getUserSession: HttpRequestBuilder = http("get userSession")
    .get("/cgi-bin/nav.pl?in=home")
    .check(status is 200)
    .check(regex("""name="userSession" value="(.*)"/>""").saveAs("userSession"))

  val login: HttpRequestBuilder = http ("login")
    .post("/cgi-bin/login.pl")
    .formParam("userSession", "${userSession}")
    .formParam("username", "jojo")
    .formParam("password", "bean")
    .formParam("login.x", "72")
    .formParam("login.y", "7")
    .formParam("JSFormSubmit", "off")
    .check(status is 200)

  //переход на страницу flights
  val flights: HttpRequestBuilder = http("flights")
    .get("/cgi-bin/nav.pl?page=menu&in=flights")
    .check(status is 200)


  val getCities: HttpRequestBuilder = http("get cities")
    .get("/cgi-bin/reservations.pl?page=welcome")
    .check(status is 200)
    .check(regex("""value="(.*)">\1</option>""").findRandom.saveAs("cityDepart"))
    .check(regex("""value="(.*)">\1</option>""").findRandom.saveAs("cityArrive"))

  // выбор городов
  val findFlight : HttpRequestBuilder = http ("find flight")
    .post("/cgi-bin/reservations.pl")
    .formParam("advanceDiscount", "0")
    .formParam("depart", "${cityDepart}")
    .formParam("departDate", "09/23/2022")
    .formParam("arrive", "${cityArrive}")
    .formParam("returnDate", "09/24/2022")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "Window")
    .formParam("seatType", "Coach")
    .formParam("findFlights.x", "35")
    .formParam("findFlights.y", "3")
    .formParam(".cgifields", "roundtrip")
    .formParam(".cgifields", "seatType")
    .formParam(".cgifields", "seatPref")
    .check(regex("""name="outboundFlight" value="(.*?)"""").findRandom.saveAs("outboundFlight"))
    .check(status is 200)

  val selectFlight: HttpRequestBuilder = http ("selectFlight")
    .post("/cgi-bin/reservations.pl")
    .formParam("outboundFlight", "${outboundFlight}")
    .formParam("numPassengers", "1")
    .formParam("advanceDiscount", "1")
    .formParam("seatType", "Coach")
    .formParam("seatPref","None")
    .formParam("reserveFlights.x","60")
    .formParam("reserveFlights.y","6")
    .check(status is 200)

  val pay: HttpRequestBuilder = http ("pay")
    .post("/cgi-bin/reservations.pl")
    .formParam("firstName","jojo1")
    .formParam("lastName","bean1")
    .formParam("address1","Street Address1")
    .formParam("address2","City")
    .formParam("pass1","123")
    .formParam("creditCard","123456")
    .formParam("expDate","12")
    .formParam("expDate","12")
    .formParam("oldCCOption", "")
    .formParam("numPassengers", "1")
    .formParam("seatType", "Coach")
    .formParam("seatPref", "None")
    .formParam("outboundFlight", "${outboundFlight}")
    .formParam("advanceDiscount", "0")
    .formParam("returnFlight", "")
    .formParam("JSFormSubmit", "off")
    .formParam("buyFlights.x", "55")
    .formParam("buyFlights.y", "9")
    .formParam(".cgifields", "saveCC")
    .check(status is 200)

  val home: HttpRequestBuilder = http ("home")
    .get("/cgi-bin/welcome.pl?page=menus")
    .check(status is 200)

}
