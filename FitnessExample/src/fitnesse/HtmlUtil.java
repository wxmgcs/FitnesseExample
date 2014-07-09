package fitnesse;

public class HtmlUtil {
	public static String testableHtml (PageData pageData, boolean includeSuiteSetup) throws Exception {
		return (new TestHtmlPageBuilder (pageData, includeSuiteSetup)).buildPage();
	}

}
