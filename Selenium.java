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
    	// ������ FireFox û�а�װ��Ĭ��Ŀ¼����ô�����ڳ���������
    	//System.setProperty("webdriver.firefox.marionette","F:\\Firefox\\geckodriver.exe");
    	System.setProperty("webdriver.firefox.bin", "F:\\Firefox\\firefox.exe");

        CsvReader r = new CsvReader("E://little shen//�������//Lab2Data.csv", ',',Charset.forName("GBK"));
        //��ȡ��ͷ
        r.readHeaders();
        //������ȡ��¼��ֱ������
        int ifCorrect = 1;
        
        //����һ��Firefoxʵ��
        WebDriver driver = new FirefoxDriver();
        driver.get("http://121.193.130.195:8080/");
        // ��ȡ ��ҳ�� title
        System.out.println("Page title is: " + driver.getTitle());
        while (r.readRecord()) {
            //��ȡһ����¼
           //System.out.println(r.getRawRecord());
            //��������ȡ������¼��ֵ
        	String web_name = r.get("����");
        	String web_num = r.get("ѧ��");
        	String web_pwd = web_num.substring(4);
        	String csv_url = r.get("github��ַ");
        	
        	// ͨ�� id �ҵ� input �� DOM
            WebElement username = driver.findElement(By.id("name"));
            username.sendKeys(web_num);
            WebElement password = driver.findElement(By.id("pwd"));
            password.sendKeys(web_pwd);

            // �ύ input ���ڵ�  form
            username.submit();
            
            //����ϵͳ
            List<WebElement> cols =  driver.findElements(By.tagName("td"));
            String stu_num = cols.get(3).getText(); //�õ�ѧ��
            String stu_url = cols.get(5).getText();	//�õ�git��ַ
            
            //�ر������
            driver.get("http://121.193.130.195:8080/");
            
            //��csv�е����ݽ��бȽ�
            if(csv_url.equals(stu_url)){
            	System.out.println(web_num+" "+web_name+"  "+stu_url+"  "+"ѧ����git��ַ��Ӧ��ȷ");
            }
            else {
            	System.out.println(web_num+" "+web_name+"  "+stu_url+"  "+"ѧ����git��ַ��Ӧ����");
            	ifCorrect = 0;
            	break;
			}
        }
        driver.close();
        r.close();
        if(ifCorrect == 1){
        	System.out.println("���е�ѧ����git��ַ����Ӧ��ȷ");
        }
        else {
        	System.out.println("����ĳЩѧ����git��ַ��Ӧ����");
		}
        
    }
}