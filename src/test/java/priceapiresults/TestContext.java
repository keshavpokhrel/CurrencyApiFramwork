package priceapiresults;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContext {
	
	private RequestSpecification rqspec;
	private Response resp;
	
	public RequestSpecification getRqspec() {
		return rqspec;
	}
	public void setRqspec(RequestSpecification rqspec) {
		this.rqspec = rqspec;
	}
	public Response getResp() {
		return resp;
	}
	public void setResp(Response resp) {
		this.resp = resp;
	}
	
	
}
