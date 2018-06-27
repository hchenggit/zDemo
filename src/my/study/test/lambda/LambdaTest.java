package my.study.test.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaTest {
	public static void main(String[] args) {
		System.out.println("===============1=====jdk1.8�汾ǰ������Lambda,list����======================");
		String[] strArr={"one","tow","three"};
		List<String> strList = Arrays.asList(strArr);
		for (String str:strList){
			System.out.println(str);
		}
		System.out.println("====================jdk1.8�汾������Lambda,list����,���ʽһ======================");
		(int x, int y) -> x + y;
		
		strList.forEach((str)-> System.out.println(str+"; "));
		System.out.println("====================jdk1.8�汾������Lambda,list����,���ʽ��======================");
		strList.forEach(System.out::println);
		
	

	}
}