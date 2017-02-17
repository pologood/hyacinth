package org.hyacinth.examples.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.beans.BeanMap;

import com.jd.risk.common.util.BeanUtil;

public class BeanTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Accout ac = new Accout();
		ac.setUid("baironglin");
		ac.setLevel("gold");
		List<String> list = new ArrayList<String>();
		list.add("Walking");
		list.add("Hiking");
		ac.setFavorates(list);
		Accout ac1 = new Accout();
		Accout ac2 = new Accout();
		ac2.setUid("bai");
		ac2.setNickName("sdsd");
		BeanUtil.merger(ac1, ac,ac2);
		System.out.println(ac1);
		
		
	}

}
