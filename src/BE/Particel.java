package BE;

import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Particel {
	static WebDriver driver;

	public Particel() {
		// Khởi tạo WebDriver

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://pioneer.particle.network/");
	}

	public void action(String address) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		switchToParticleWallet();
		for (int i = 0; i < 202; i++) {
			if(i==102) {break;}
			try {
				WebElement sendButton = driver.findElement(
						By.xpath("//*[@id=\"keep-alive-container\"]/div/div/div/div[2]/div[1]/div[4]/div[1]/div"));
				sendButton.click();
			} catch (Exception e) {
				// TODO: handle exception
				WebElement backToHome = driver.findElement(By.cssSelector("div[class='back-btn-wrap']"));
				backToHome.click();
				i--;
				
				Thread.sleep(2000);
				WebElement sendButton = driver.findElement(
						By.xpath("//*[@id=\"keep-alive-container\"]/div/div/div/div[2]/div[1]/div[4]/div[1]/div"));
				sendButton.click();
			}
			Thread.sleep(2000);

			WebElement addressSend = driver.findElement(By.cssSelector("textarea[id='send_to']"));
			addressSend.sendKeys(address);

			WebElement inputAmount = driver.findElement(By.cssSelector("input[id='send_amount']"));
			inputAmount.sendKeys(amout());

			Thread.sleep(1000);

			WebElement stepSend1 = driver.findElement(By.cssSelector("button[type='submit']"));
			stepSend1.click();

			try {
				WebElement stepSend2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.cssSelector("button[class='ant-btn ant-btn-primary btn-user-paid']")));
				stepSend2.click();
				System.out.println(LocalDateTime.now());
				// sign metamask
				// connectMetamask();
				Thread.sleep(40000);
				Actions actions = new Actions(driver);
				actions.sendKeys(Keys.ESCAPE).perform();
				System.out.println(i);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}

		}
	}

	private static String amout() {
		// TODO Auto-generated method stub
		Random rd = new Random();
		int amout = rd.nextInt(87)+2;
		return "0.000"+amout;
	}

	private void connectMetamask() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		// TODO Auto-generated method stub
		Set<String> windows = driver.getWindowHandles();

		// Lặp qua danh sách các cửa sổ
		for (String window : windows) {
			// Chuyển đến từng cửa sổ
			driver.switchTo().window(window);

			// Kiểm tra tiêu đề của cửa sổ để xác định cửa sổ MetaMask
			if (driver.getTitle().contains("MetaMask")) {
				// Thực hiện các thao tác trong cửa sổ MetaMask
				System.out.println(driver.getTitle());
				try {

					WebElement clickSubmit2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
							"button[class='button btn--rounded btn-primary page-container__footer-button']")));
					JavascriptExecutor jsExecutor2 = (JavascriptExecutor) driver;
					jsExecutor2.executeScript("arguments[0].click();", clickSubmit2);

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Loi metamask");
					System.out.println(e);
					reset();
					switchToParticleWallet();
					WebElement cancel = wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.cssSelector("div[class='ant-drawer-extra']")));
					cancel.click();
				}

				break;
			}
		}

	}

	public void reset() {
		driver.switchTo().defaultContent();
	}

	private void switchToParticleWallet() {
		// TODO Auto-generated method stub
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"particle-auth-core-iframe-wallet\"]")));
	}
	
}