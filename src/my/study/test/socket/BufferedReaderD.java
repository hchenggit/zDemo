package my.study.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderD {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("«Î ‰»Î£∫");
        String message = "";
        while ((message = bufferedReader.readLine()) != null) {
        	System.out.println("-----------------message");
           
            System.out.print("«Î ‰»Î£∫");
        }
	}
}
