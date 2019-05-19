Feature: Change permission through API and verify role's permission


  @WebSanity @APISanity @RolePermissionVerification
  Scenario Outline: Validate Web permission after edit permission through API

    Given I call Get Role API to get all role list and get "<roleName>" role id
#   Then I call Get Permissions for Role API to get all permission
    And  I set "<permissionName>" value as "<value>" for "<roleName>" role
    Then I should get API response with the Status code of "<ExpectedCode>"
    Then I login Vend "<url>" as manager use "<userName>" and "<Password>"
    Then I go to product page
    And I should not be able to add product

    Examples:
      | roleName | permissionName   | value | ExpectedCode  | url                                    | userName | Password |
      | Manager  | product.add_edit | 0     | 200           | https://lillianshome.vendhq.com/signin | Lilly    | 88888888 |



