package com.buzz.jwtdemo.enumerate;

/*************************************************************************************************************
 * 메시지처리를 위한 Enum
 * 리소스를 사용한 메시지 처리는 사용하기 번거롭기 때문에 Enum 으로 처리 한다
 *************************************************************************************************************/
public enum EnumMessage {
	
	ERROR_URL_NOTFOUND("요청하신 주소가 존재하지 않습니다.", "request url not found"), 
	ERROR_URL_FORBIDDEN("요청에대한 권한이 없습니다."), 
	ERROR_URL_NOTAUTH("정상적인 요청이 아닙니다."),
	ERROR_URL_PARAMETER("필수파라미터가 없습니다."),
	ERROR_JWT_INVALID("인증키에 문제가 발생 했습니다."),
	ERROR_JWT_EXPIRE("정상적인 요청이 아닙니다."),	
	ERROR_REQUEST("요청처리중 오류가 발생 했습니다."),
	OK_REQUEST("요청이 완료되었습니다");
		
	private String message;
	private String engMessage;

	//다국어 지원안함
    private EnumMessage(String message) {    
        this.message = message;
    }  
    
	//다국어 지원시 생성자에서 분리 한다
    private EnumMessage(String message,String engMessage) {    
        this.message = message;
        this.engMessage =  engMessage;
        //locale 체크하여 분기
        //this.engTitle = engTitle
    }
        
    public String getMessage() {
    	return this.message;
    }
}
