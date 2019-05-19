# How does it work
## The framework
1. Use [Cucumber](https://cucumber.io/),which is follows the principles of Behavioural Driven Design and living documentation ,use Gherkin to write the tests in a “human readable” way.
2. Use [REST Assured](http://rest-assured.io/) to execute RESTFUL APIs' GET and PATCH request.
3. Use [Selenium](https://www.seleniumhq.org/) to capture web element to simulate UI test.
4. Use Junit and extend Assert class

## Get Started：

### Feature Demo
```
   Scenario Outline: change permission and verify on web

    Given I call Get Role API to get all role list and get "<roleName>" role id
    And  I set "<permissionName>" value as "<value>" for "<roleName>" role
    Then I should get API response with the Status code of "<ExpectedCode>"
    Then I login Vend "<url>" as manager use "<userName>" and "<Password>"
    Then I go to product page
    And I should not be able to add product

    Examples:
      | roleName | permissionName   | value | ExpectedCode  | url  | userName | Password |
      | Manager  | product.add_edit | 0     | 200           | xxxx | xxxx     | xxxx |
```
### Set test environment

1. go to environment file to set up API endpoint or whatever environment configuration you need to set up(../Data/Properties/Sit..)
2. point to specific environment like Sit/Prelive/Prod(according how many test environments you work on)
3. set default brower(chrome/firefox) and platform(mac/win)
 
### Set test data
  You can set up data in TestData folder, for exmaple: default test user for web test

### Use Junit to run feature file
  After environment and data set up, you can click "../src/test/java/com/Runner/TestRunner"file, you should be able to run feature files which you have write in runner files.
  Or you can run feature file when you just want to run specific scenario. In this case, you can run VerifyPermission.feature.

### Generate Cucumber report
  After execution will generate Cucumber report。
  
## Next：
### Test Case Design
  This demo is just an simple example,we should have specific test scenarios for API and WEB as well.
  API For example:
  1.after we disable or enable permission, should add assertion to verify json body.
  2.verify what's the behavior when we didnt pass token or token has expired or invalid.
  3.If the role ID not exists, what will happen to api
  Web for example:
  1.after we edit permission for specific role, verify if it will impact the other role's permission in same store
  2.As above, if it will impact the other role's permission which is belong to different store
### Framework Optimize
  1.Current demo framwork is just very simple framework, need to add more common specific method for web and api test
  2.Consider to change HttpClient to RestAssured to make api test more easier
