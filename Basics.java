package demo;


import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Basics {
	
	@Test
	public void login() {
		
		Playwright pw = Playwright.create();
		
		Browser browser = pw.firefox().launch(
				new BrowserType.LaunchOptions()
				.setHeadless(false));
		
		
		Page page = browser.newPage();
		
		page.navigate("http://www.leafground.com/pages/frame.html");
		FrameLocator frame = page.frameLocator("(//div[@id='wrapframe']/iframe)[1]");
		frame.locator("#click").click();
		
		FrameLocator outerframe = page.frameLocator("(//div[@id='wrapframe']/iframe)[2]");
		FrameLocator innerframe = outerframe.frameLocator("#frame2");
		innerframe.locator("#Click1").click();

		
	}

}
