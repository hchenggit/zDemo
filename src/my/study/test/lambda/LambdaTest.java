package my.study.test.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaTest {
	public static void main(String[] args) {
		System.out.println("===============1=====jdk1.8版本前新特性Lambda,list遍历======================");
		String[] strArr={"one","tow","three"};
		List<String> strList = Arrays.asList(strArr);
		for (String str:strList){
			System.out.println(str);
		}
		System.out.println("====================jdk1.8版本新特性Lambda,list遍历,表达式一======================");
		(int x, int y) -> x + y;
		
		strList.forEach((str)-> System.out.println(str+"; "));
		System.out.println("====================jdk1.8版本新特性Lambda,list遍历,表达式二======================");
		strList.forEach(System.out::println);
		
	

	}
}