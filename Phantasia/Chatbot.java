package Phantasia;

public class Chatbot {
	
	public final String greating = "Welcom to Phantasia the RP chat bot.";
	
	private Response[] responses  = {
		new Response( new Response[] {}  )	
	};
	
	public String getResponse(String inpt) {
		inpt = inpt.replaceAll("[.,'\"!?]" , " ");
		Response[] rsp = responses;
		
		inpt = inpt.replaceFirst("^0+(?!$)", "");
		for (Response r : rsp) {
			if ( r.is(this , inpt) ) {
				rsp = r.responses;
			}
		}
		
		return inpt;
	}
}