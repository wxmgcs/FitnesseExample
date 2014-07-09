package fitnesse;

public class TestHtmlPageBuilder implements HtmlPageBuilder {
	
	private PageData pageData;
	private boolean includeSuiteSetup;
	private StringBuffer buffer = new StringBuffer();
	private WikiPage wikiPage;
	
	public TestHtmlPageBuilder (PageData pageData, boolean includeSuiteSetup) {
		this.pageData = pageData;
		this.includeSuiteSetup = includeSuiteSetup;
		this.wikiPage = pageData.getWikiPage();
	}

	@Override
	public String buildPage() throws Exception {
		addSuiteSetup();
		addPageSetup();
		addPageContent();
		addPageTeardown();
		addSuiteTeardown();
		updatePageContent();
		return getHtml();
	}

	private String getHtml() {
		return pageData.getHtml();
	}

	private void updatePageContent() {
		pageData.setContent(buffer.toString());
	}

	private void addSuiteSetup() {
		if (!pageData.hasAttribute("Test")) return;
		
		if (includeSuiteSetup) {
			WikiPage suiteSetup = PageCrawlerImpl.getInheritedPage(SuiteResponder.SUITE_SETUP_NAME, wikiPage);
			if (suiteSetup != null) {
				WikiPagePath pagePath = suiteSetup.getPageCrawler().getFullPath(suiteSetup);
				String pagePathName = PathParser.render(pagePath);
				buffer.append("!include -setup .").append(pagePathName).append("\n");
			}
		}
	}

	private void addPageSetup() {
		if (!pageData.hasAttribute("Test")) return;
		
		WikiPage setup = PageCrawlerImpl.getInheritedPage("SetUp", wikiPage);
		if (setup != null) {
			WikiPagePath setupPath = wikiPage.getPageCrawler().getFullPath(setup);
			String setupPathName = PathParser.render(setupPath);
			buffer.append("!include -setup .").append(setupPathName).append("\n");
		}
	}

	private void addPageContent() {
		buffer.append(pageData.getContent());
	}

	private void addPageTeardown() {
		if (!pageData.hasAttribute("Test")) return;

		WikiPage teardown = PageCrawlerImpl.getInheritedPage("TearDown", wikiPage);
		if (teardown != null) {
			WikiPagePath tearDownPath = wikiPage.getPageCrawler().getFullPath(teardown);
			String tearDownPathName = PathParser.render(tearDownPath);
			buffer.append("\n").append("!include -teardown .").append(tearDownPathName).append("\n");
		}
	}

	private void addSuiteTeardown() {
		if (!pageData.hasAttribute("Test")) return;

		if (includeSuiteSetup) {
			WikiPage suiteTeardown = PageCrawlerImpl.getInheritedPage(SuiteResponder.SUITE_TEARDOWN_NAME, wikiPage);
			if (suiteTeardown != null) {
				WikiPagePath pagePath = suiteTeardown.getPageCrawler().getFullPath(suiteTeardown);
				String pagePathName = PathParser.render(pagePath);
				buffer.append("!include -teardown .").append(pagePathName).append("\n");
			}
		}
	}


}
