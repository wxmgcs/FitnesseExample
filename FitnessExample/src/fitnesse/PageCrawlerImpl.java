package fitnesse;

public class PageCrawlerImpl implements PageCrawler {

	public static WikiPage getInheritedPage (String suiteSetupName, WikiPage wikiPage) {
		if (suiteSetupName.equals(SuiteResponder.SUITE_SETUP_NAME)) {
			return new WikiPage (SuiteResponder.SUITE_SETUP_NAME);
		}
		if (suiteSetupName.equals(SuiteResponder.SUITE_TEARDOWN_NAME)) {
			return new WikiPage (SuiteResponder.SUITE_TEARDOWN_NAME);
		}
		if (suiteSetupName.equals("SetUp")) {
			return new WikiPage ("SetUp");
		}
		if (suiteSetupName.equals("TearDown")) {
			return new WikiPage ("TearDown");
		}
		return new WikiPage ();
	}
	
	public WikiPagePath getFullPath(WikiPage suiteSetup) {
		if (suiteSetup.getName().equals(SuiteResponder.SUITE_SETUP_NAME)) {
			return new WikiPagePath (SuiteResponder.SUITE_SETUP_NAME);
		}
		if (suiteSetup.getName().equals(SuiteResponder.SUITE_TEARDOWN_NAME)) {
			return new WikiPagePath (SuiteResponder.SUITE_TEARDOWN_NAME);
		}
		if (suiteSetup.getName().equals("SetUp")) {
			return new WikiPagePath ("SetUp");
		}
		if (suiteSetup.getName().equals("TearDown")) {
			return new WikiPagePath ("TearDown");
		}
		return new WikiPagePath ("UNKNOWN");
	}



}
