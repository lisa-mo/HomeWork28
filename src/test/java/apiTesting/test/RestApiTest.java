// package apiTesting.test;

// import apiTesting.models.*;
// import apiTesting.utils.LogListener;
// import io.restassured.RestAssured;
// import org.testng.annotations.BeforeClass;
// import org.testng.annotations.Listeners;
// import org.testng.annotations.Test;

// import java.util.ArrayList;

// import static io.restassured.RestAssured.given;
// import static org.hamcrest.Matchers.*;
// import static org.testng.Assert.assertEquals;

// @Listeners(LogListener.class)
// public class RestApiTest {

//     @BeforeClass
//     public void statUp() {
//         RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
//     }

//     @Test
//     public void getAllEmployeesTestPositive() {
//         String endpoint = "employees";
//         given()
//                 .when()
//                 .get(endpoint)
//                 .then()
//                 .log().all()
//                 .statusCode(200)
//                 .assertThat()
//                 .body("status", equalTo("success"))
//                 .body("data.id", hasItems("1", "2", "3"))
//                 .body("data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"))
//                 .body("data.employee_salary", hasItems("320800", "170750"))
//                 .body("data.employee_age", hasItems("61", "63"));
//     }

//     @Test
//     public void getAllEmployeesTestNegative() {
//         //try to get all employees data from wrong endpoint
//         String endpoint = "employee";// correct is "employees"
//         given()
//                 .when()
//                 .get(endpoint)
//                 .then()
//                 .log().all()
//                 .statusCode(404)
//                 .assertThat()
//                 .body("message", equalTo("Error Occured! Page Not found, contact rstapi2example@gmail.com"));
//     }

//     @Test
//     public void getEmployeeIdTestPositive() {
//         String endpoint = "employee/1";
//         Employee expectedEmployee = new Employee("Tiger Nixon", 320800, 61, "");
//         EmployeeResponse expectedResponse = new EmployeeResponse("success", expectedEmployee, "Successfully! Record has been fetched.");

//         EmployeeResponse response = given()
//                 .when()
//                 .log().all()
//                 .get(endpoint)
//                 .then()
//                 .log().all()
//                 .statusCode(200)
//                 .assertThat()
//                 .extract()
//                 .as(EmployeeResponse.class);

//         assertEquals(response, expectedResponse);
//     }

//     @Test
//     public void getEmployeeIdTestNegative() {
//         //try to get employee data by wrong endpoint
//         String endpoint = "employeee/id";
//         given()
//                 .when()
//                 .get(endpoint)
//                 .then()
//                 .log().all()
//                 .statusCode(404)
//                 .assertThat()
//                 .body("message", equalTo("Error Occured! Page Not found, contact rstapi2example@gmail.com"));
//     }

//     @Test
//     public void postEmployeeTestPositive() {
//         String endpoint = "create";
//         PostEmployee employee = new PostEmployee("Thomas", "675432", "43");
//         EmployeeResponse expectedResponse = new EmployeeResponse("success", new Employee(), "Successfully! Record has been added.");
//         EmployeeResponse response = given()
//                 .with()
//                 .body(employee)
//                 .post(endpoint)
//                 .then()
//                 .log().all()
//                 .statusCode(200)
//                 .assertThat()
//                 .extract()
//                 .as(EmployeeResponse.class);
//         assertEquals(response, expectedResponse);
//     }

//     @Test
//     public void postEmployeeTestNegative() {
//         //try wrong method
//         String endpoint = "create";
//         PostEmployee employee = new PostEmployee("Thomas", "675432", "43");

//         given()
//                 .with()
//                 .body(employee)
//                 .put(endpoint)
//                 .then()
//                 .log().all()
//                 .statusCode(405);
//     }

//     @Test
//     public void putEmployeeTestPositive() {
//         String endpoint = "update/40";
//         PostEmployee employee = new PostEmployee("Thomas", "675432", "43");
//         PutEmployeeResponse expectedResponse = new PutEmployeeResponse("success", new ArrayList<>(), "Successfully! Record has been updated.");
//         PutEmployeeResponse response = given()
//                 .with()
//                 .body(employee)
//                 .put(endpoint)
//                 .then()
//                 .log().all()
//                 .statusCode(200)
//                 .assertThat()
//                 .extract()
//                 .as(PutEmployeeResponse.class);
//         assertEquals(response, expectedResponse);
//     }

//     @Test
//     public void putEmployeeTestNegative() {
//         //try wrong method
//         String endpoint = "update/id";
//         PostEmployee employee = new PostEmployee("Thomas", "675432", "43");

//         given()
//                 .with()
//                 .body(employee)
//                 .post(endpoint)
//                 .then()
//                 .log().all()
//                 .statusCode(405);
//     }


//     @Test
//     public void deleteEmployeeTestPositive() {
//         String endpoint = "delete/40";
//         String id = "40";
//         DeleteEmployee expectedResponse = new DeleteEmployee("success", id, "Successfully! Record has been deleted");
//         DeleteEmployee response = given()
//                 .with()
//                 .delete(endpoint)
//                 .then()
//                 .log().all()
//                 .statusCode(200)
//                 .assertThat()
//                 .extract()
//                 .as(DeleteEmployee.class);
//         assertEquals(response, expectedResponse);
//     }

//     @Test
//     public void deleteEmployeeTestNegative() {
//         //try wrong method
//         String endpoint = "delete/id";

//         given()
//                 .when()
//                 .post(endpoint)
//                 .then()
//                 .statusCode(405);
//     }
// }
