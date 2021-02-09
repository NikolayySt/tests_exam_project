package uni.pld.masters.selenium.wikipedia;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;

/**
 * 
 * Test class that contains tests for simple wikipedia functionalities
 * 
 * @author nstoilov
 *
 */
public class WikipediaSeleniumTest {

    private WikipediaPageObject pageObject = new WikipediaPageObject();

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
    }

    @Before
    public void before() {
        pageObject.navigateToMainUrl();
    }

    @After
    public void after() {
        pageObject.close();
    }

    /**
     * Test that tests if the wikipedia main page is loaded successfully
     */
    @Test
    public void wikipediaLoadedSuccessully_Test() {
        MatcherAssert.assertThat(pageObject.isMainPageLoaded(), Is.is(true));
    }

    /**
     * Test to test wikipedia search, that it finds what is written in the search field
     */
    @Test
    public void wikipediaSearch_test() {
        String searchValue = "Куентин Тарантино";

        pageObject.enterSearch(searchValue);
        pageObject.clickSearchButton();

        WebElement firstHeading = pageObject.getFirstHeading();
        MatcherAssert.assertThat(firstHeading.getTagName(), Is.is("h1"));
        MatcherAssert.assertThat(firstHeading.getText(), Is.is(searchValue));
    }
    
    /**
     * Test that tests if the login button opens the login form successfully
     * 
     */
    @Test
    public void wikipediaLogin_LoginFormOpened_Test() {
        pageObject.loginClick();

        WebElement firstHeading = pageObject.getFirstHeading();
        MatcherAssert.assertThat(firstHeading.getTagName(), Is.is("h1"));
        MatcherAssert.assertThat(firstHeading.getText(), Is.is("Влизане"));
        MatcherAssert.assertThat(pageObject.isNameFieldLoaded(), Is.is(true));
        MatcherAssert.assertThat(pageObject.isPasswordFieldLoaded(), Is.is(true));
    }

    /**
     * Test that tests the login form with empty credentials. Expected behavior is to stay on the same page.
     */
    @Test
    public void wikipediaLogin_WithEmptyCredentials_Test() {
        pageObject.loginClick();

        pageObject.clickLoginAttempt();

        WebElement firstHeading = pageObject.getFirstHeading();
        MatcherAssert.assertThat(firstHeading.getText(), Is.is("Влизане"));
    }

    /**
     * Test that tests if the register button opens the register form successfully
     */
    @Test
    public void wikipediaRegisterLoaded_Test() {
        pageObject.clickRegister();

        MatcherAssert.assertThat(pageObject.isRegisterNameFieldDisplayed(), Is.is(true));
        MatcherAssert.assertThat(pageObject.isRegisterPasswordFieldDisplayed(), Is.is(true));
        MatcherAssert.assertThat(pageObject.isRegisterRepeatPasswordFieldDisplayed(), Is.is(true));
        MatcherAssert.assertThat(pageObject.isRegisterEmailFieldDisplayed(), Is.is(true));
    }

    /**
     * Test that tests if click on contributions button opens the contributions page successfully
     */
    @Test
    public void wikipediaContributionsLoaded_Test() {
        pageObject.clickContributionsButton();

        WebElement firstHeading = pageObject.getFirstHeading();
        MatcherAssert.assertThat(firstHeading.getText(), Is.is("Потребителски приноси"));
    }

    /**
     * Test that tests if click on conversations button opens the conversation page successfully
     */
    @Test
    public void wikipediaConversationLoaded_Test() {
        pageObject.clickConversationButton();

        WebElement firstHeading = pageObject.getFirstHeading();
        MatcherAssert.assertThat(firstHeading.getText(), CoreMatchers.containsString("Потребител беседа"));
    }


}
