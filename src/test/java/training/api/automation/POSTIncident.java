package training.api.automation;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.apache.commons.collections4.map.HashedMap;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class POSTIncident extends reusableMethods {
	
	@Test
	public void createIncident() throws JsonParseException, JsonMappingException, IOException {
		
		//RestAssured.baseURI = "https://dev84192.service-now.com/api/now/table";
		
		/*
		 * 1. Using POJO - Plan old java object
		 */
		
		//Create a POJO object 
//		POJO pojoData = new POJO();
//		pojoData.setShort_description("POJO incident");
//		pojoData.setImpact(2);
		
		/*
		 * 2. Using Hashmap
		 */
		
//		Map<String, Object> mapData = new HashedMap<>();
//		mapData.put("caller_id", "Abel Tuter");
//		mapData.put("short_description", "Map data");
		
		/*
		 * 3. File as an object - preferred 
		 */
		
		 ObjectMapper mapper = new ObjectMapper();
		 File file = new File ("./data/post.json");
		 Map<String, Object> jsonData = mapper.readValue(file, new TypeReference<Map<String, Object>>(){});
		 
		ValidatableResponse response = 
		given().
				spec(reqSpec).
				pathParam("tableName", "incident").
				body(jsonData).
		when().
				post("/{tableName}").
		then().
				spec(resSpec).
				assertThat().statusCode(201);
				//log().all();
		
		//Get a specific value
		
		Map<String, ?> sysID = response.extract().path("result.company");
		System.err.println("The sys_ID is "+ sysID);
		
		mapper.writeValue(new File("./data/output.json"), sysID);
		
		
	}

}
