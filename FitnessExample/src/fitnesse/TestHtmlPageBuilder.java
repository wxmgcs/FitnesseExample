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
			addSection(SuiteResponder.SUITE_SETUP_NAME, "!include -setup .");
		}
	}

	private void addPageSetup() {
		if (!pageData.hasAttribute("Test")) return;
		addSection("SetUp", "!include -setup .");
	}

	private void addPageContent() {
		buffer.append(pageData.getContent()).append("\n");
	}

	private void addPageTeardown() {
		if (!pageData.hasAttribute("Test")) return;
		addSection("TearDown", "!include -teardown .");
	}

	private void addSuiteTeardown() {
		if (!pageData.hasAttribute("Test")) return;

		if (includeSuiteSetup) {
			addSection(SuiteResponder.SUITE_TEARDOWN_NAME, "!include -teardown .");
		}
	}
	
	// Helper Methods

	private void addSection (String inheritedPageName, String appendString) {
		WikiPage section = PageCrawlerImpl.getInheritedPage(inheritedPageName, wikiPage);
		if (section != null) {
			WikiPagePath pagePath = section.getPageCrawler().getFullPath(section);
			String pagePathName = PathParser.render(pagePath);
			buffer.append(appendString).append(pagePathName).append("\n");
		}
	}

}
