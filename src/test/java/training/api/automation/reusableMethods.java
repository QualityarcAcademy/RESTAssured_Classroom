package training.api.automation;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class reusableMethods {
	RequestSpecBuilder reqBuilder;
	ResponseSpecBuilder resBuilder;
	RequestSpecification reqSpec;
	public long responseTime = 30000;
	ResponseSpecification resSpec;

	public reusableMethods() {
		setupRequest();
		setupResponse();

	}

	public RequestSpecification setupRequest() {

		//Basic Authentication
		BasicAuthScheme basicAuth = new BasicAuthScheme();
		basicAuth.setUserName("admin");
		basicAuth.setPassword("ErMiWa0E0mlP");

		reqBuilder = new RequestSpecBuilder();
		reqBuilder.setAuth(basicAuth);
		reqBuilder.setContentType(ContentType.JSON);
		reqBuilder.setBaseUri("https://dev84192.service-now.com/api/now/table");

		reqSpec = reqBuilder.build();
		return reqSpec;
	}

	public ResponseSpecification setupResponse() {
		resBuilder = new ResponseSpecBuilder();
		resBuilder.expectResponseTime(lessThan(responseTime));
		resBuilder.expectHeader("Server", "ServiceNow");
		resBuilder.expectContentType(ContentType.JSON);
		resBuilder.expectBody(notNullValue());
		resSpec = resBuilder.build();
		return resSpec;
		

	}

	//EOF
}
