package org.hyacinth.examples.ucc;

public class ConfClientTest {

	public static void main(String[] args) throws Exception {
		
		String value = ConfClientUtil.getConf("chat_risk_service");
		System.out.println(value);

	}

}
