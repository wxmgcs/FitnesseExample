package fitnesse;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class HtmlUtilTest {
	
	private String results;
	
	public void runHtmlUtil (Boolean includeSuiteSetup) {
		try {
			results = HtmlUtil.testableHtml(buildPageData(), includeSuiteSetup);
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
		
	}

	@Test
	public void page_content_is_present () {
		runHtmlUtil(false);
		assertTrue(results.contains("PAGE CONTENT"));
		runHtmlUtil(true);
		assertTrue(results.contains("PAGE CONTENT"));
	}
	
	@Test 
	public void suite_setup_is_present () {
		runHtmlUtil(true);
		assertTrue(results.contains("SUITE_SETUP_NAME"));
	}
	
	@Test
	public void suite_teardown_is_present () {
		runHtmlUtil(true);
		assertTrue(results.contains("SUITE_TEARDOWN_NAME"));
	}

	@Test
	public void page_setup_is_present () {
		runHtmlUtil(false);
		assertTrue(results.contains("SetUp"));
	}

	@Test
	public void page_teardown_is_present () {
		runHtmlUtil(false);
		assertTrue(results.contains("TearDown"));
	}

	@Test
	public void suit_setup_is_not_present () {
		runHtmlUtil(false);
		assertFalse(results.contains("SUITE_SETUP_NAME"));
	}

	@Test
	public void suit_teardown_is_present () {
		runHtmlUtil(false);
		assertFalse(results.contains("SUITE_TEARDOWN_NAME"));
	}

	// Helpers
	
	private PageData buildPageData () {
		return new PageData ("PAGE CONTENT", buildPageAttributes() );
	}
	
	private Map<String, String> buildPageAttributes () {
		Map<String, String> pageAttributes = new HashMap<> ();
		pageAttributes.put ("Test", "Test");
		return pageAttributes;
	}

}
