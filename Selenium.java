package sjh;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.csvreader.CsvReader;

public class Selenium  {
    public static void main(String[] args) throws IOException {
    	// 如果你的 FireFox 没有安装在默认目录，那么必须在程序中设置
    	//System.setProperty("webdriver.firefox.marionette","F:\\Firefox\\geckodriver.exe");
    	System.setProperty("webdriver.firefox.bin", "F:\\Firefox\\firefox.exe");

        CsvReader r = new CsvReader("E://little shen//软件测试//Lab2Data.csv", ',',Charset.forName("GBK"));
        //读取表头
        r.readHeaders();
        //逐条读取记录，直至读完
        int ifCorrect = 1;
        
        //创建一个Firefox实例
        WebDriver driver = new FirefoxDriver();
        driver.get("http://121.193.130.195:8080/");
        // 获取 网页的 title
        System.out.println("Page title is: " + driver.getTitle());
        while (r.readRecord()) {
            //读取一条记录
           //System.out.println(r.getRawRecord());
            //按列名读取这条记录的值
        	String web_name = r.get("姓名");
        	String web_num = r.get("学号");
        	String web_pwd = web_num.substring(4);
        	String csv_url = r.get("github地址");
        	
        	// 通过 id 找到 input 的 DOM
            WebElement username = driver.findElement(By.id("name"));
            username.sendKeys(web_num);
            WebElement password = driver.findElement(By.id("pwd"));
            password.sendKeys(web_pwd);

            // 提交 input 所在的  form
            username.submit();
            
            //进入系统
            List<WebElement> cols =  driver.findElements(By.tagName("td"));
            String stu_num = cols.get(3).getText(); //得到学号
            String stu_url = cols.get(5).getText();	//得到git地址
            
            //关闭浏览器
            driver.get("http://121.193.130.195:8080/");
            
            //与csv中的数据进行比较
            if(csv_url.equals(stu_url)){
            	System.out.println(web_num+" "+web_name+"  "+stu_url+"  "+"学号与git地址对应正确");
            }
            else {
            	System.out.println(web_num+" "+web_name+"  "+stu_url+"  "+"学号与git地址对应错误");
            	ifCorrect = 0;
            	break;
			}
        }
        driver.close();
        r.close();
        if(ifCorrect == 1){
        	System.out.println("所有的学号与git地址都对应正确");
        }
        else {
        	System.out.println("存在某些学号与git地址对应错误");
		}
        
    }
}