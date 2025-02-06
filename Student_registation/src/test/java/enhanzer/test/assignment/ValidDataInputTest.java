package enhanzer.test.assignment;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidDataInputTest extends BaseTestSetup {

    @Test
    public void valid_inputs() {
        // Start test
        System.out.println("Starting test...");

        // First name
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
        firstNameField.sendKeys("Kumaran");
        assertEquals("Kumaran", firstNameField.getAttribute("value"), "First name is incorrect");

        // Last name
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName")));
        lastNameField.sendKeys("Thevapalan");
        assertEquals("Thevapalan", lastNameField.getAttribute("value"), "Last name is incorrect");

        // Email
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userEmail")));
        emailField.sendKeys("nothing@gmail.com");
        assertEquals("nothing@gmail.com", emailField.getAttribute("value"), "Email is incorrect");

        // Gender radio button
        WebElement radioButtonLabel = driver.findElement(By.xpath("//label[@for='gender-radio-1']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", radioButtonLabel);
        radioButtonLabel.click();
        WebElement radioButton = driver.findElement(By.id("gender-radio-1"));
        assertTrue(radioButton.isSelected(), "Radio button is not selected");

        // User number
        WebElement userNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userNumber")));
        userNumberField.sendKeys("0123456789");
        assertEquals("0123456789", userNumberField.getAttribute("value"), "User number is incorrect");

        // Date of Birth
        WebElement dateField = driver.findElement(By.id("dateOfBirthInput"));
        dateField.click();
        WebElement monthDropdown = driver.findElement(By.className("react-datepicker__month-select"));
        monthDropdown.sendKeys("January");
        WebElement yearDropdown = driver.findElement(By.className("react-datepicker__year-select"));
        yearDropdown.sendKeys("2000");
        WebElement day = driver.findElement(By.xpath("//div[contains(@class, 'react-datepicker__day--001')]"));
        day.click();
        String selectedDate = dateField.getAttribute("value");
        assertEquals("01 Jan 2000", selectedDate, "Date of birth is incorrect");

        // Subject
        WebElement subjectsField = driver.findElement(By.xpath("//input[@id='subjectsInput']"));
        subjectsField.sendKeys("Maths");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".subjects-auto-complete__option")));
        subjectsField.sendKeys(Keys.ENTER);
        String enteredSubject = driver.findElement(By.cssSelector(".subjects-auto-complete__multi-value__label")).getText();
        assertEquals("Maths", enteredSubject, "Entered subject is incorrect");

        // Hobbies
        WebElement sportsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='hobbies-checkbox-1']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sportsCheckbox);
        Actions actions = new Actions(driver);
        actions.moveToElement(sportsCheckbox).click().perform();
        WebElement sportsCheckboxInput = driver.findElement(By.id("hobbies-checkbox-1"));
        assertTrue(sportsCheckboxInput.isSelected(), "Checkbox is not selected");

        // Profile picture upload
        String filePath = new File("Profile/pp.jpg").getAbsolutePath();
        WebElement uploadElement = driver.findElement(By.id("uploadPicture"));
        uploadElement.sendKeys(filePath);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(uploadElement, "value"));
        String uploadedFileName = new File(uploadElement.getAttribute("value")).getName();
        assertEquals("pp.jpg", uploadedFileName, "Profile picture upload is incorrect");

        // Address
        WebElement addressField = driver.findElement(By.id("currentAddress"));
        addressField.sendKeys("123 Main Street, Delhi, India");
        String enteredAddress = addressField.getAttribute("value");
        assertEquals("123 Main Street, Delhi, India", enteredAddress, "Entered address is incorrect");

        // State and City
        WebElement stateDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-3-input")));
        stateDropdown.sendKeys("NCR");
        stateDropdown.sendKeys(Keys.ENTER);
        WebElement cityDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-4-input")));
        cityDropdown.sendKeys("Delhi");
        cityDropdown.sendKeys(Keys.ENTER);

        // Submit
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();

        // Verify submission success
        WebElement submissionMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));
        assertEquals("Thanks for submitting the form", submissionMessage.getText(), "Submission success message is incorrect");
    }
}
