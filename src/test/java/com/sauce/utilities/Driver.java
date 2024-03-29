package com.sauce.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {


    private Driver() {
    }
    // InheritableThreadLocal  --> this is like a container, bag, pool.
    // in this pool we can have separate objects for each thread
    // for each thread, in InheritableThreadLocal we can have separate object for that thread
    // driver class will provide separate webdriver object per thread

    // InheritableThreadLocal --> bu bir kap, çanta, havuz gibidir.
    // bu havuzda her iş parçacığı için ayrı nesnelere sahip olabiliriz
    // her iş parçacığı için, InheritableThreadLocal'da o iş parçacığı için ayrı bir nesneye sahip olabiliriz
    // sürücü sınıfı, iş parçacığı başına ayrı web sürücüsü nesnesi sağlayacaktır
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    public static WebDriver get() {
        //if this thread doesn't have driver - create it and add to pool
        //eğer bu iş parçacığının sürücüsü yoksa - onu oluştur ve havuza ekle
        if (driverPool.get() == null) {
//            if we pass the driver from terminal then use that one
//           if we do not pass the driver from terminal then use the one properties file
            // eğer sürücüyü uçbirimden geçirirsek onu kullanırız
            // sürücüyü uçbirimden aktarmazsak one property dosyasını kullanın
            String browser = System.getProperty("browser") != null ? browser = System.getProperty("browser") : ConfigurationReader.get("browser");
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Edge");
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverPool.set(new SafariDriver());
                    break;
                case "remote_chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setCapability("platform", Platform.ANY);

                    try {
                        driverPool.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    break;
                case "remote_firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setCapability("platform", Platform.ANY);

                    try {
                        driverPool.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;

            }
        }
        return driverPool.get();
    }

    public static void closeDriver() {
        driverPool.get().quit();
        driverPool.remove();
    }
}

