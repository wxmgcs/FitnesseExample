package fitnesse;

import java.util.HashMap;
import java.util.Map;

public class PageData {
	
	private String content;
	private Map<String, String> attributes = new HashMap<> ();
	private WikiPage wikiPage;
	
	public PageData (String content, Map<String, String> attributes) {
		this.content = content;
		this.attributes = attributes;
		this.wikiPage = new WikiPage();
	}

	public WikiPage getWikiPage() {
		return wikiPage;
	}

	public boolean hasAttribute (String string) {
		return attributes.containsKey(string);
	}
	
	// Getters & Setters

	public String getContent() {
		return content;
	}

	public void setContent(String string) {
		content = string;
	}

	public String getHtml() {
		return content;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

}
