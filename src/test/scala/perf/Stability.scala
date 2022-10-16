package perf
import io.gatling.core.Predef._


class Stability extends Simulation{
  setUp(
    CommonScenario().inject(
      constantUsersPerSec(3).during(3600), // 1
    ).protocols(httpProtocol))
      .assertions(
      global.responseTime.max.lt(2000),
      global.successfulRequests.percent.gt(95)
  )
}
