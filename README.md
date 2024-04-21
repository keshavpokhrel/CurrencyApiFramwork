# API Automation Framwork on BDD style for GET Currency API

This project contains API framwork build on BDD approach. Tools used are :
</br> . Coding language  : Java
</br> . Build Tool : Maven
</br> . BDD sechmentics : Cucumber
</br> . API framwork : Rest Assured
</br> . Design pattern : Dependancy Injection
</br> . Test Runner : Junit
</br> . IDE used : Eclipse

## Project Structure 

Below is the screen shot of structure of prioject :
![image](https://github.com/keshavpokhrel/CurrencyApiFramwork/assets/6346814/684f4feb-c02e-45e1-b897-47a960b96840)


<br>Building project : POM.XML</br>
<br>Test Runner class: bddApiTest</br>
<br>Feature implementation : feature > priceapitest</br>
<br>Step defination implementation : priceapiresult > stepdefs</br>

## Way to execute Tests
There are multiple ways to execute test cases :</br>
<heading4>1. Running as a junit test on eclipse</heading4>  
</br>Expend project then right click on bddApiTest.class 
</br>Then from context menues select Run as > JunitTest

<heading4>2. From Command line using Maven commends</heading4>
<p> Got to project directory > then on address bar wright CMD > press Enter</p>
In command prompt enter below command to run all the test cases which are define under @CucumberOptions > tags property:
</br>mvn test
</br>To run specific test :
</br>mvn test -Dcucumber.filter.tags="@getvalidprice"






