
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class myTask {
    public static void main(String[] args) throws InterruptedException {

        /*
        Test case:

        Go to https://www.webstaurantstore.com/
        Search for 'stainless work table'.
        Check the search result ensuring every product has the word 'Table' in its title.
        Add the last of found items to Cart.
        Empty Cart.

       */

        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
         WebDriver driver = new ChromeDriver();


        driver.navigate().to("https://www.webstaurantstore.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000,TimeUnit.MILLISECONDS);

     driver.findElement(By.xpath("//*[@id='searchval']")).sendKeys("stainless work table");
      List<WebElement> list =  driver.findElements(By.xpath("//ul[@role='listbox']//li//descendant::span[@class='result']"));
        System.out.println(list.size());
        for(int i = 0 ; i < list.size();i++){
         String listItem  = list.get(i).getText();
         System.out.println(listItem);
         if(listItem.contains("table")){
             list.get(i).getText();
             break;
         }
        }
       driver.findElement(By.xpath("//*[@id=\"searchForm\"]/div/button")).click();
       List<WebElement> p = driver.findElements(By.xpath("//*[@id=\"ProductBoxContainer\"]/div[1]/a/span"));
        for(int i = 0; i < p.size(); i++){
            String lst = p.get(i).getText();
            System.out.println(lst);
            System.out.println(lst);
            if(lst.contains("Table")){
                p.get(i).getText();
                break;

            }
        }
        List<WebElement> products = driver.findElements(By.xpath("//*[@id='product_listing']"));
        for(WebElement pr : products){
          System.out.println(pr.getText());
      }
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"paging\"]/nav/ul/li[7]/a")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"ProductBoxContainer\"]/div[4]/form/div/div/input[2]")) ;
        elements.forEach(e -> {
            js.executeScript("arguments[0].click();", e);

        });
       Thread.sleep(10000);
        try {
            WebElement ele = driver.findElement(By.xpath("//span[@id='cartItemCountSpan']"));
            JavascriptExecutor j = (JavascriptExecutor)driver;
            j.executeScript("arguments[0].click();", ele);

        }catch(Exception e){
          e.printStackTrace();
        }
        Thread.sleep(10000);
      try{
          WebElement e = driver.findElement(By.xpath("//*[text()='Empty Cart']"));
         JavascriptExecutor jsc = (JavascriptExecutor)driver;
          jsc.executeScript("arguments[0].click();", e);
      }catch(Exception e){
          e.printStackTrace();
      }
     Thread.sleep(10000);
       String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler); // switch to popup window
        try{
            WebElement elem = driver.findElement(By.xpath("//*[@id=\"td\"]/div[11]/div/div/div/footer/button[1]"));
           JavascriptExecutor jsce = (JavascriptExecutor)driver;
           jsce.executeScript("arguments[0].click();", elem);

        }catch (Exception e){
            e.printStackTrace();
        }
        Thread.sleep(10000);
        driver.switchTo().window(parentWindowHandler);
        try {
            String text = driver.findElement(By.xpath("//div[@class='empty-cart__text']//p[1]")).getText();
            System.out.println("The text is: "+ text);
        }catch (Exception exception){
            exception.printStackTrace();
        }


        driver.quit();
    }
}
