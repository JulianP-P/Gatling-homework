package perf
import io.gatling.core.Predef._


class MaxPerf extends Simulation{

  setUp(
    CommonScenario().inject(
      incrementUsersPerSec(1)
      // Количество ступеней
      .times(5)
      // Длительность полки
      .eachLevelLasting(600)
      // Длительность разгона
      .separatedByRampsLasting(10)
      // Начало нагрузки с
      .startingFrom(0),

  ).protocols(httpProtocol))
    .assertions(
    global.responseTime.max.lt(2000),
    global.successfulRequests.percent.gt(95)
  )
}
