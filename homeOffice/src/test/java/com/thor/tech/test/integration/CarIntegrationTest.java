package com.thor.tech.test.integration;

import com.thor.tech.model.FileMimeTypes;
import com.thor.tech.model.FileModel;
import com.thor.tech.parser.FileParser;
import com.thor.tech.parser.model.DataModel;
import com.thor.tech.test.utils.ServiceLayerTest;
import com.thor.tech.utils.ServiceLayer;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class CarIntegrationTest extends TestCase {

    private WebDriver driver;
    private final String inputEndPoint = "https://vehicleenquiry.service.gov.uk/";
    private final URL url = ServiceLayerTest.class.getClassLoader().getResource("inputExamples");
    private final String inputDirectory = url.getFile();

    @Before
    public void setUp() throws Exception {

        System.setProperty("webdriver.gecko.driver", System.getProperty("webdriverLocation"));

        final FirefoxOptions options = new FirefoxOptions();
        final DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        options.setHeadless(true);

        capabilities.setCapability("moz:firefoxOptions", options);
        capabilities.setCapability("version", "latest");
        capabilities.setCapability("name", "Testing Selenium");


        // Create a new instance of the Firefox driver
        this.driver = new FirefoxDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(9000, TimeUnit.MICROSECONDS);
    }

    private void takeAnScreenshot(String fileName) {

        final File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        try{
            final File resultFile =new File("resultsImages");
            FileUtils.forceMkdir(resultFile);
            FileUtils.copyFile(scrFile, new File(resultFile.getAbsolutePath() + File.separator+ fileName));
        }catch (IOException iOExceptio){
            iOExceptio.printStackTrace();
        }
    }

    @Test
    public void testEndPoint() throws Exception {

        final String expectedTittle = "Check if a vehicle is taxed and has an MOT";

        this.driver.get(inputEndPoint);
        assertEquals(expectedTittle, this.driver.getTitle());
        takeAnScreenshot("testEndPoint.jpg");
    }

    @Test
    public void testOnePlateNumber() throws Exception {

        final String inputPlate = "NUX 205W";
        final String expectedColour = "BLACK";
        final String expectedMake = "EASY RIDER";
        evaluateNumberPlate(inputPlate,expectedColour,expectedMake);
        takeAnScreenshot("testOnePlateNumber.jpg");

    }

    @Test
    public void testNotFoundPlateNumber() throws Exception {

        final String inputPlate = "CU57ABC";
        String expectedColour = DataModel.notFound;
        final String expectedMake = DataModel.notFound;
        evaluateNumberPlate(inputPlate,expectedColour,expectedMake);
        takeAnScreenshot("testNotFoundPlateNumber.jpg");
    }

    @Test
    public void testAllInputDataFiles() throws Exception {

        final List<FileModel> results = ServiceLayer.getAllFilesPathsInADirectory(inputDirectory, FileMimeTypes.CSV);
        final int expectedFiles = 4;

        assertEquals(expectedFiles, results.size());

        for (FileModel fileModel : results){

            final FileParser fileParser = new FileParser(fileModel.getFileName());
            final ArrayList<DataModel> resultData = fileParser.populateDataModel();
            evaluatePlatesNumbers(resultData);
        }
    }

    /**
     * This method is going to evaluate each plate number obtained from the <code>DataModel</code> List passed as parameter.
     * @param resultData this is the passed data model list which is going to be used to obtain Vehicle details.
     */
    private void evaluatePlatesNumbers(ArrayList<DataModel> resultData){

        for ( DataModel dataModel : resultData){
            evaluateNumberPlate(dataModel.getPlateNumber(),dataModel.getColour(), dataModel.getMake());
            takeAnScreenshot("evaluatePlatesNumbers"+dataModel.getPlateNumber()+".jpg");
        }
    }

    private void evaluateNumberPlate(String inputPlate, String expectedColour, String make ){

        this.driver.get(inputEndPoint);
        driver.findElement(By.xpath("/html/body/main/form/div/div/div[2]/fieldset/div/input")).sendKeys(inputPlate);
        driver.findElement(By.xpath("/html/body/main/form/div/div/div[2]/fieldset/button")).click();

        if ( isTestElementPresent ("/html/body/main/h3")){
            //assert not found
            assertEquals(expectedColour, driver.findElement(By.xpath("/html/body/main/h3")).getText());
        }else{
            //assert founded
            assertEquals(expectedColour, driver.findElement(By.xpath("/html/body/main/form/div/div/ul/li[3]/span[2]/strong")).getText());

        }
    }

    @After
    public void tearDown() throws Exception {

        this.driver.quit();
    }

    public boolean isTestElementPresent(String elementXPath) {
        try {
            driver.findElement(By.xpath(elementXPath));
            return true;
        } catch (NoSuchElementException noSuchElementException) {
            return false;
        }
    }
}
