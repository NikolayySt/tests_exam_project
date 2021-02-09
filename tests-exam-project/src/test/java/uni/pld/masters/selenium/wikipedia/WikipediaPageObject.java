package uni.pld.masters.selenium.wikipedia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WikipediaPageObject {

    private static final String URL = "https://bg.wikipedia.org/wiki/%D0%9D%D0%B0%D1%87%D0%B0%D0%BB%D0%BD%D0%B0_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0";

    private WebDriver driver;

    @FindBy(id = "searchInput")
    private WebElement searchInput;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    @FindBy(id = "ca-nstab-main")
    private WebElement mainPage;

    @FindBy(id = "firstHeading")
    private WebElement firstHeading;

    @FindBy(id = "pt-login")
    private WebElement login;

    @FindBy(id = "wpName1")
    private WebElement nameField;

    @FindBy(id = "wpPassword1")
    private WebElement passwordField;

    @FindBy(id = "wpLoginAttempt")
    private WebElement loginAttempt;

    @FindBy(className = "errorbox")
    private WebElement errorBox;

    @FindBy(id = "pt-createaccount")
    private WebElement registerButton;

    @FindBy(id = "wpName2")
    private WebElement registerNameField;

    @FindBy(id = "wpPassword2")
    private WebElement registerPasswordField;

    @FindBy(id = "wpRetype")
    private WebElement registerRepeatPasswordField;

    @FindBy(id = "wpEmail")
    private WebElement registerEmailField;

    @FindBy(id = "pt-anoncontribs")
    private WebElement contributionsButton;

    @FindBy(id = "pt-anontalk")
    private WebElement conversationButton;

    public void navigateToMainUrl() {
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);

        driver.navigate()
                .to(URL);
    }

    public void enterSearch(String value) {
        searchInput.sendKeys(value);
    }

    public boolean isMainPageLoaded() {
        return mainPage.isDisplayed();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public WebElement getFirstHeading() {
        return firstHeading;
    }

    public void loginClick() {
        login.click();
    }

    public boolean isNameFieldLoaded() {
        return nameField.isDisplayed();
    }

    public boolean isPasswordFieldLoaded() {
        return passwordField.isDisplayed();
    }

    public void enterName(String name) {
        nameField.sendKeys(name);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginAttempt() {
        loginAttempt.click();
    }

    public boolean isErrorBoxDisplayed() {
        return errorBox.isDisplayed();
    }

    public void clickRegister() {
        registerButton.click();
    }

    public boolean isRegisterNameFieldDisplayed() {
        return registerNameField.isDisplayed();
    }

    public boolean isRegisterPasswordFieldDisplayed() {
        return registerPasswordField.isDisplayed();
    }

    public boolean isRegisterRepeatPasswordFieldDisplayed() {
        return registerRepeatPasswordField.isDisplayed();
    }

    public boolean isRegisterEmailFieldDisplayed() {
        return registerEmailField.isDisplayed();
    }

    public void clickContributionsButton() {
        contributionsButton.click();
    }

    public void clickConversationButton() {
        conversationButton.click();
    }

    public void waitForLoginToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(driver -> nameField.isEnabled() || passwordField.isEnabled() || loginAttempt.isEnabled());
    }

    public void close() {
        driver.close();
    }

}
