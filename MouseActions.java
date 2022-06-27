package demo;

import java.nio.file.Paths;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class MouseActions {
	
	@Test
	public void mouse() {
		
		
		Playwright pw = Playwright.create();
		
		Browser browser = pw.chromium().launch(new BrowserType.LaunchOptions()
				.setChannel("chrome").setSlowMo(2000).setHeadless(false));
		BrowserContext context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos/")));

		Page page = context.newPage();
		page.navigate("https://flipkart.com");
		page.locator("//button[text()='✕']").click();
		page.locator("//div[contains(text(),'Fashion')]").hover();
		
		page.locator("//a[text()='Women Ethnic']").hover();
		page.locator("//a[text()='Women Sarees']").click();
		
		context.close();
	}

}
