package com.buzz.jwtdemo.enumerate;

public enum ActiveStatus implements EnumMapperType {
	OFF("비활동멤버"), // 비활동
	ON("활동멤버"),  // 활동
	HOLD("유예상태"); // 유예상태
		
	private String title;

	//다국어 지원시 생성자에서 분리 한다
    private ActiveStatus(String title) {    
        this.title = title;
        //locale 체크하여 분기
        //this.engTitle = engTitle
    }   
    
    @Override
    public String getCode() {
    	return name();
    }    
    @Override
    public String getTitle() {
    	return this.title;
    }
}
