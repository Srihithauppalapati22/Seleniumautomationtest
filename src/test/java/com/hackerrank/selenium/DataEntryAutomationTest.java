package com.hackerrank.selenium;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.hackerrank.selenium.server.JettyServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataEntryAutomationTest {
    private static JettyServer server = null;
    private static int TEST_PORT = 8001;
    private static WebDriver driver = null;

    private static String pagUrl = "http://localhost:" + TEST_PORT + "/home.html";

    static Map<String, String> data = null;

    @BeforeClass
    public static void setup() {
        driver = new HtmlUnitDriver(BrowserVersion.CHROME, true) {

            @Override
            protected WebClient newWebClient(BrowserVersion version) {
                WebClient webClient = super.newWebClient(version);
                webClient.getOptions().setThrowExceptionOnScriptError(false);

                Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
                Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);

                return webClient;
            }
        };
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        server = new JettyServer(TEST_PORT);
        server.start();

        data = new HashMap() {
            {
                put("11", "day");
                put("July", "month");
                put("1990", "year");
                put("favorite_language", "Java,Python");
                put("favorite_os", "Linux,Mac OSX");
                put("favorite_idea", "IntelliJ IDEA");
            }
        };
    }

    @Test
    public void testFillDateOfBirth() {
        DataEntryAutomation.fillDateOfBirth(driver, pagUrl);
        List<WebElement> selects = driver.findElements(By.tagName("select"));

        assertEquals(3, selects.size());
        for (WebElement element : selects) {
            Select select = new Select(element);
            assertEquals(data.get(select.getFirstSelectedOption().getAttribute("value")), element.getAttribute("id"));
        }
    }

    @Test
    public void testAnswerQuestions() {
        DataEntryAutomation.answerQuestions(driver, pagUrl);

        List<WebElement> checkBoxes = driver.findElements(By.tagName("input"));
        assertEquals(11, checkBoxes.size());

        Set<WebElement> checked = new HashSet<>();
        for (WebElement select : checkBoxes) {
            if (select.isSelected()) {
                checked.add(select);
            }
        }

        assertEquals(5, checked.size());
        for (WebElement element : checked) {
            assertTrue(data.get(element.getAttribute("name")).contains(element.getAttribute("value")));
        }
    }


    @AfterClass
    public static void tearDown() {
        driver.close();
    }
}
