package com.StepDefinitions;

import com.Utils.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import com.Common.BaseClass;
import com.Common.TestConstants;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import static org.apache.cxf.helpers.IOUtils.UTF8_CHARSET;
import static org.junit.Assert.assertFalse;


public class ApiSteps extends BaseClass {

    private String requestURL;
    private String roleId;


    @Given("^I call Get Role API to get all role list and get \"([^\"]*)\" role id$")
    public void iGetAllRoleAndRoleId(String roleName) throws Exception {

        //Initial responseMassage
        responseMessage = "";

        //get url
        requestURL = getBaseURL(TestConstants.ServiceBaseUrls.ROLE_SERVICE_BASE_URL) + TestConstants.EndPoints.ROLE_PERMISSION_ENDPOINT;

        //set header with token
        RestApiHelper.SetDefaultHeaderWithAuthenticationToken("5OtjwgBqfINOmDLIKMila_wRtA8Fsm4tVOYYYoAT");

        //call url and get response
        response = RestApiHelper.HttpGet(requestURL , true);
        responseCode = response.getStatusLine().getStatusCode();

        if (responseCode != 200) {
            // Get response string from server
            responseMessage = EntityUtils.toString(response.getEntity(), UTF8_CHARSET);
            // Update Logs
            LogHelper.info(log, "GetAllRole RequestURL: " + requestURL);
            LogHelper.info(log, "GetAllRole ResponseCode:" + responseCode);
            LogHelper.info(log, "GetAllRole ResponseMessage:" + responseMessage);
        }else{
            String responseBody = EntityUtils.toString(response.getEntity());
            //convert response string to jsonobject
            JSONObject jsonResBd = new JSONObject(responseBody);
            JSONArray jsonArrayData = jsonResBd.getJSONArray("data");
            //get roldId for specific role
            for (int i = 0; i < jsonArrayData.length(); i++) {
                JSONObject roleData = jsonArrayData.getJSONObject(i);
                if (roleData.getString("name").equalsIgnoreCase(roleName))
                {
                    roleId = roleData.getString("id");
                    break;
                }
            }
        }

        // Update Logs
        LogHelper.info(log, "GetAllRole RequestURL: " + requestURL);
        LogHelper.info(log, "GetAllRole ResponseCode:" + responseCode);


    }

    @And("^I set \"([^\"]*)\" value as \"([^\"]*)\" for \"([^\"]*)\" role$")
    public void iUpdatePermissionForRole(String permissionName,String value, String roleName) throws Exception {

        //Initial responseMassage
        responseMessage = "";

        //create requestBody
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n"+"\"permissions\":"+"[\n" + "{\n");
        stringBuilder.append("\"name\":" + "\"" + permissionName + "\",\n");
        stringBuilder.append("\"value\":" + "0\n");
        stringBuilder.append("}\n]\n}");


        String requestBody = stringBuilder.toString();

        //get update permission for role api URL
        requestURL = getBaseURL(TestConstants.ServiceBaseUrls.ROLE_SERVICE_BASE_URL) + TestConstants.EndPoints.ROLE_PERMISSION_ENDPOINT+roleId;

        //set header with token
        RestApiHelper.SetDefaultHeaderWithAuthenticationToken("5OtjwgBqfINOmDLIKMila_wRtA8Fsm4tVOYYYoAT");

        //call url and get response
        response = RestApiHelper.HttpPatch(requestURL,true,requestBody);

        responseCode = response.getStatusLine().getStatusCode();

        if (responseCode != 200) {
            // Get response string from server
            responseMessage = EntityUtils.toString(response.getEntity(), UTF8_CHARSET);
            // Update Logs
            LogHelper.info(log, "GetAllRole RequestURL: " + requestURL);
            LogHelper.info(log, "GetAllRole ResponseCode:" + responseCode);
            LogHelper.info(log, "GetAllRole ResponseMessage:" + responseMessage);

        }

    }

    @Then("^I call Get Permissions for Role API to get all permission$")
    public void iGetAllPermissions() throws Exception {


    }


}
