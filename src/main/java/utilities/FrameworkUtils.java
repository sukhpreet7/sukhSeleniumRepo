package utilities;

import globals.RootClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class FrameworkUtils extends RootClass {


    public void addScreenshotToReport(String name) {

        //format of the screenshot
        File file = ((TakesScreenshot) getBrowser()).getScreenshotAs(OutputType.FILE);

        try {
            long time = System.nanoTime();
            //
            FileUtils.copyFile(file, new File(screenshotsFolder + time + ".png"));
            screenshotPath = extentTest.addScreenCapture(screenshotsFolder + time + ".png")
                    .replace(screenshotsFolder, "Screenshots\\")
                    .replace("file:///", "")
                    .replace("<img", "<img width = \"300\" height = \"140\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
