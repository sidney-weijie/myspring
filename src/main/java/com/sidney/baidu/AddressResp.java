package com.sidney.baidu;



public class AddressResp extends BaseResp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AddressResult result = new AddressResult();

	public AddressResult getResult() {
		return result;
	}

	public void setResult(AddressResult result) {
		this.result = result;
	}

}
