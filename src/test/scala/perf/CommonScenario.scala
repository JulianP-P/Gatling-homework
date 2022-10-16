package perf

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

object CommonScenario{
  def apply() = new CommonScenario().scn
}

class CommonScenario {
    val scn: ScenarioBuilder = scenario("my scenario")
      .exec(Action.getMainPage)
      .exec(Action.getCookies)
      .exec(Action.getUserSession)
      .exec(Action.login)
      .exec(Action.flights)
      .exec(Action.getCities)
      .exec(Action.findFlight)
      .exec(Action.selectFlight)
      .exec(Action.pay)
      .exec(Action.home)
}
