package demo;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LearnFrame {
	
	@Test
	public void login() {
		
		Playwright pw = Playwright.create();
		
		Browser browser = pw.chromium().launch(
				new BrowserType.LaunchOptions()
				.setChannel("chrome")
				.setHeadless(false));
		
		BrowserContext context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos/")));
		
		Page page = context.newPage();
		
		page.navigate("http://leaftaps.com/opentaps");
		
		page.locator("#username").type("demosalesmanager");
		page.locator("#password").type("crmsfa");
		page.locator(".decorativeSubmit").click();
		page.locator("text=CRM/SFA").click();
		page.locator("//a[contains(text(),'Leads')]").click();
		page.locator("text=Create Lead").click();
		page.selectOption("#createLeadForm_dataSourceId", "LEAD_DIRECTMAIL");

		// to save recording
		context.close();
		
	}

}
