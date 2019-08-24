package training.api.automation;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
public class FirstRestAPITest {

	@Test
	public void firstAPITest() {
		given().
		when().
				get("https://jsonplaceholder.typicode.com/todos").
		then().
				assertThat().statusCode(200).
				and().
				//assertThat().body("title",equalTo("delectus aut autem")).
				log().all();
	}

}
