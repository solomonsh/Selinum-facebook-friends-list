/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autotest1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
 
import javax.swing.*;
import org.openqa.selenium.By;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
 
import org.openqa.selenium.interactions.Actions;
 
public class Autotest1  extends JFrame implements ActionListener
  {
 JButton SUBMIT;
  JPanel panel;
  JLabel label1,label2;
  final JTextField  text1,text2;
   Autotest1()
   {
   label1 = new JLabel();
   label1.setText("Username:");
   text1 = new JTextField(15);
 
   label2 = new JLabel();
   label2.setText("Password:");
   text2 = new JPasswordField(15);
  
   SUBMIT=new JButton("SUBMIT");
   
   panel=new JPanel(new GridLayout(3,1));
   panel.add(label1);
   panel.add(text1);
   panel.add(label2);
   panel.add(text2);
   panel.add(SUBMIT);
   add(panel,BorderLayout.CENTER);
   SUBMIT.addActionListener(this);
   setTitle("LOGIN FORM");
   }
   public static void getFriends(String user,String pass){
       System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
       
       WebDriver driver = new ChromeDriver();
       
        driver.get("https://www.facebook.com");
        
         try{
         Thread.sleep(2000);
        }catch(Exception e){
       }
        
        WebElement phone = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("pass"));
        WebElement button = driver.findElement(By.id("loginbutton"));
        phone.sendKeys(user);
        password.sendKeys(pass);
        button.click();
        
        try{
         Thread.sleep(2000);
        }catch(Exception e){
       }
     
       WebElement profile =driver.findElement(By.xpath("//a[@title='Profile']"));
       profile.click();
       
        try{
         Thread.sleep(1000);
        }catch(Exception e){
       }
         
       WebElement friends =driver.findElement(By.xpath("//a[@data-tab-key='friends']"));
       friends.click();
       
       
      try{
         Thread.sleep(1000);
        }catch(Exception e){
       }
       List<WebElement> friendsList = driver.findElements(By.xpath(".//*[@id='pagelet_timeline_medley_friends']/div[2]"));
        //This is path for the friends in the chat box, but its saying not able to find element
       System.out.println("Total friends is = " +friendsList.size());
       int newFriends = friendsList.size();
       Actions act = new Actions(driver);
       int friendsBefore = 0;
       
       while(newFriends != 100){
           WebElement lastFriend = friendsList.get(friendsList.size()-1);
           act.moveToElement(lastFriend).build().perform();
            try{
               Thread.sleep(1000);
            }catch(Exception e){ 
           }
           friendsList = driver.findElements(By.xpath("//div[@class='fsl fwb fcb']/a"));
           newFriends = friendsList.size();
       }
       
           System.out.println("FINAL Total friends is = " +friendsList.size());
   PrintWriter pw = null;
   StringBuilder builder = new StringBuilder();
   String ColumnNamesList = "Name";
   builder.append(ColumnNamesList +"\n");
       for(int i=0;i<friendsList.size();i++){
try {
    pw = new PrintWriter(new File("friends.csv"));
} catch (FileNotFoundException e) {
    e.printStackTrace();
}
String x=friendsList.get(i).getText();
builder.append(x);
builder.append('\n');
pw.write(builder.toString());
            System.out.println(friendsList.get(i).getText());
                }
pw.close(); 
  try{
               Thread.sleep(1000);
            }catch(Exception e){ 
           }
      driver.close();
   }
    public static void main(String[] args) {
          
         try
   {
   Autotest1 frame=new Autotest1();
   frame.setSize(300,100);
   frame.setVisible(true);
   }
   catch(Exception e)
   {JOptionPane.showMessageDialog(null, e.getMessage());}
   }
    

    @Override
    public void actionPerformed(ActionEvent e) {
   String value1=text1.getText();
   String value2=text2.getText();;
   getFriends(value1,value2);
   
 }  }
    

