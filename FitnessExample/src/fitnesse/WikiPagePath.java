package fitnesse;

public class WikiPagePath {
	
	private String path;

	public WikiPagePath (String suiteSetupName) {
		this.path = suiteSetupName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
