package phantasia;

public class Response {
	interface BoolFunc { boolean call(Response r, Chatbot c, String i); }
	
	public BoolFunc is = (Response r, Chatbot c, String i) -> { return i.indexOf(r.find) == 0; };
	public String find = "";
	public String ret = "";
	
	public Response[] responses = new Response[0];
	
	public Response() {}
	
	public Response(String s) {
		find = s;
	}
	
	public Response(String s, String r) {
		find = r;
	}
	
	public boolean is(Chatbot c, String i) {
		return is.call(this, c, i);
	}
	
	public String string(Chatbot c, String i) {
		return ret;
	}
}