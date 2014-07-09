package fitnesse;

public class WikiPage {
	
	private String name;
	
	public WikiPage () {
		name = "UKNOWN";
	}
	
	public WikiPage (String name) {
		this.name = name;
	}

	public PageCrawler getPageCrawler() {
		return new PageCrawlerImpl ();
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String arg) {
		name = arg;
	}

}
