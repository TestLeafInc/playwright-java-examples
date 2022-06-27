package demo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class Ui_API {

	@Test
	public void hybridTest() throws InterruptedException {

		Playwright pw = Playwright.create();

		
		Browser browser = pw.chromium().
			launch(
					new BrowserType.LaunchOptions()
						.setChannel("chrome")
						.setSlowMo(100)
						.setHeadless(false));

		Page page = browser.newPage();
		page.navigate("https://playwright-testleaf.atlassian.net/");

		page.locator("#username").type("babu.narayanan@itorizon.com");;
		page.locator("//span[text()='Continue']").click();
		Thread.sleep(2000);
		page.locator("#password").type("Leaf@123");
		page.locator("//span[text()='Log in']").click();
		page.locator("//span[text()='Projects']").click();
		page.locator("//span[text()='Playwright (PW)']").click();
		page.locator("//span[text()='Issues']").click();


		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", "Basic YmFidS5uYXJheWFuYW5AaXRvcml6b24uY29tOkhuN3hyRHloSlFKb1htVGE5RzJsRUNFRQ==");

		APIRequestContext request;

		request = pw.request().newContext(new APIRequest.NewContextOptions()
				.setBaseURL("https://playwright-testleaf.atlassian.net")
				.setExtraHTTPHeaders(headers));

		String body = "{\n"
				+ "    \"fields\": {\n"
				+ "       \"project\":\n"
				+ "       {\n"
				+ "          \"key\": \"PW\"\n"
				+ "       },\n"
				+ "       \"summary\": \"New Defect from Babu Using Playwright.\",\n"
				+ "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\n"
				+ "       \"issuetype\": {\n"
				+ "          \"name\": \"Bug\"\n"
				+ "       }\n"
				+ "   }\n"
				+ "}";
		APIResponse newIssue = request.post("/rest/api/2/issue/", RequestOptions.create().setData(body));
		System.out.println(newIssue.status());
		JsonElement json = new Gson().fromJson(newIssue.text(), JsonElement.class);
	    String issueKey = json.getAsJsonObject().get("key").getAsString();
	    System.out.println(issueKey);
	    
		page.locator("//input[@name='search']").type(issueKey);
		page.keyboard().press("Enter");


	    
	    

	}

}
