package Phantasia;

public class Response {
	interface BoolFunc { boolean call(Response r, Chatbot c, String i); }
	
	public BoolFunc is = (Response r, Chatbot c, String i) -> { return i.indexOf(r.find) == 0; };
	public String find;
	
	public Response[] responses;
	
	public Response() {
		responses = new Response[0];
	}
	
	public Response(Response[] r) {
		responses = r;
	}
	
	public boolean is(Chatbot c, String i) {
		return is.call(this, c, i);
	}
}