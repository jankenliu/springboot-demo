package com.jankin.springboot.demo.common.result;


/**
 * 创建人 lyy
 * 创建时间 2018-4-25 下午4:01:40
 */
public enum ResultStateEnum {
	/**
	 *
	 */
	SUCCESS(true, 0, "成功"),
	FAILURE(1, "失败"),
	PARAM_ERR(2, "参数错误"),

	;


	private boolean state;
    private int code;
	private String message;

	private ResultStateEnum(int code, String message){
		this.code = code;
		this.message = message;
	}
	private ResultStateEnum(boolean state, int code, String message){
		this.state = state;
		this.code = code;
		this.message = message;
	}

	public boolean isState() {
		return state;
	}
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
}
