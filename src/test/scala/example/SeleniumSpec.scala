package example

import java.util.concurrent.TimeUnit

import io.github.bonigarcia.wdm.ChromeDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.scalatest._
import org.scalatest.concurrent.Eventually
import org.scalatest.selenium.WebBrowser
import org.scalatest.time.{Millis, Seconds, Span}

//SEE http://www.scalatest.org/user_guide/using_selenium
class SeleniumSpec extends FlatSpec with Matchers with ConfiguredEventually with OptionValues with GivenWhenThen with WebBrowser {
  val host = "http://www.tweakers.nl/"
  implicit val webDriver: WebDriver = {
      // Set up the selenium webdriver.
      ChromeDriverManager.getInstance.setup()
      implicit val driver: WebDriver = new ChromeDriver
      // Sometimes tests fail on different devices due to different browser window sizes,
      // so let's make sure they're always the same.
      driver.manage.window.setSize(new org.openqa.selenium.Dimension(1290, 1024))
      // Disable Selenium's implicit wait because it often waits long times for no reason.
      driver.manage.timeouts.implicitlyWait(Span(3, Seconds).totalNanos, TimeUnit.NANOSECONDS)
      // Close the browser when the tests are done.
      sys.addShutdownHook { driver.quit() }
      // Return.
      driver
  }

  "Tweakers.nl" should "have the correct title" in {
    go to (host)
    click on name("decision")
    eventually {
        pageTitle should be ("Tweakers - Wij stellen technologie op de proef")
    }
  }
}
trait ConfiguredEventually extends Eventually {

    override implicit val patienceConfig: PatienceConfig = PatienceConfig(timeout = Span(5, Seconds), interval = Span(50, Millis))

}