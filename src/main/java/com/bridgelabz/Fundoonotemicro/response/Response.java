package com.bridgelabz.Fundoonotemicro.response;

//import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.Fundoonotemicro.entity.*;

public class Response {
	
	public int value;
	
	public String message;
	
	public Object data;
	

	public Response(int value, String message, Object data) {
		// TODO Auto-generated constructor stub
		this.value=value;
		this.message=message;
		this.data=data;
	}

}
