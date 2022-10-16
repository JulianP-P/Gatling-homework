import io.gatling.core.Predef._
import io.gatling.http.Predef._

package object perf {
  val httpProtocol = http
    .baseUrl("http://www.load-test.ru:1080")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("ru,en;q=0.9")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.167 YaBrowser/22.7.5.1026 Yowser/2.5 Safari/537.36")
//    .disableFollowRedirect

}