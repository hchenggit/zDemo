package my.study.test.javaDll;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public class TestJNA  {

	/**
	 * rundll32.exe libmatch.dll,seemmo_pvc_match 888
	 * 
	 * type �������� 0 δ֪����  1 ���� 2 ���г�
	 * score �÷�  0 --100
	 * proi �ֲ�ʶ��
	 * @param args
	 */
	public static void main(String[] args) {
		
		//String str =  "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";		
		//String str2 = "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";
		//String str2 = "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";
		//String str2 ="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";
		//String str = "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";
		String str2 = "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";
		String str = "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";
		//System.out.println( "~��ʼ �����ַ�������: str"+str.length() + ",str2:"+str2.length());
		byte[] by = GenerateByte(str); // Base64�ַ��������byte����  ~~~~~~~~~~
		//char[] charStr = getChars(by); // byte���� ת char����               ~~~~~~~~~
		
		byte[] by2 = GenerateByte(str2); // Base64�ַ��������byte����
		//char[] charStr2 = getChars(by2); // byte���� ת char ����
		
		String s1 = conver2HexStr(by);
		String s2 = conver2HexStr(by);
		//System.out.println("1 Base64�ַ�����"+Arrays.toString(by));
		//System.out.println("2 charStrתbyte[]��"+Arrays.toString(charStr));
		
		LibmatchJNADll instance=(LibmatchJNADll)Native.loadLibrary("libmatch", LibmatchJNADll.class);
		
		int i=0;
		long l = System.currentTimeMillis();
		instance.seemmo_match_init(true);
		NativeLong l2 ;
		float[] score1 = new float[]{};
		float[] score2 = new float[]{80};
		while( i < 2){
			//base64�ַ����� base64�ַ����Ա�
			//NativeLong l1 = instance.seemmo_pvc_match(str,str.length(),str2,str2.length(), 0 , score1,null);
			//ֱ�Ӵ�byte[]�����ǲ��У��ð�byte[]����תΪchar[]��
			l2 = instance.seemmo_pvc_match(by,by.length,by2,by2.length, 3, score2,null);
			//l2 = instance.seemmo_pvc_match(by,by.length,by2,by2.length, 1, score2,null);
			i++;
		}
		long l3 = System.currentTimeMillis();
		System.out.println(i+"�ζԱȺ�ʱ��"+(l3 - l)+"΢��,"+Arrays.toString(score2));
		//System.out.println("-����,����base64�ַ����ԱȽ��: "+l1 + ",���ƶȣ� "+Arrays.toString(score1));
		//System.out.println("-����,����byte[]תchar[]�ԱȽ��: "+l2 + ",���ƶȣ� "+Arrays.toString(score2));
		//
	}

	public static String conver2HexStr(byte [] b)
	{
		StringBuffer result = new StringBuffer();
		for(int i = 0;i<b.length;i++)
		{
			result.append(Long.toString(b[i] & 0xff, 2)+",");
		}
		return result.toString().substring(0, result.length()-1);
	}

	
	public static char byteToChar(byte[] b) {
	    char c = (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
	    return c;
    }
	
	
	public static String GetImageStr()  
    {//��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��  
		//str = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==";
		
		//data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==
        String imgFile = "e:\\2.jpg";//��������ͼƬ
                         // ��ַҲ��д��"F:/deskBG/86619-107.jpg"��ʽ��
        InputStream in = null;  
        byte[] data = null;  
        //��ȡͼƬ�ֽ�����  
        try   
        {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //���ֽ�����Base64����  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);//����Base64��������ֽ������ַ���  
    }
	public static byte[] GetImageByte()  
    {//��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��  
		//str = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==";
		
		//data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==
        String imgFile = "e:\\2.jpg";//��������ͼƬ
                         // ��ַҲ��д��"F:/deskBG/86619-107.jpg"��ʽ��
        InputStream in = null;  
        byte[] data = null;  
        //��ȡͼƬ�ֽ�����  
        try   
        {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
          
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        return data;//����Base64��������ֽ������ַ���  
    }
	
	public static byte[] GenerateByte(String imgStr) {// ���ֽ������ַ�������Base64����
		byte[] bytes = null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
				// Base64����
				bytes = decoder.decodeBuffer(imgStr);
				for (int i = 0; i < bytes.length; ++i) {
					if (bytes[i] < 0) {// �����쳣����
							bytes[i] += 256;
						}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return bytes;
	}
	private static char[] getChars (byte[] bytes) {
		Charset cs = Charset.forName("UTF-8");
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = cs.decode(bb);
		return cb.array();
	}
	public static boolean GenerateImage(String imgStr, String imgFilePath) {// ���ֽ������ַ�������Base64���벢����ͼƬ
		if (imgStr == null) // ͼ������Ϊ��
			return false;
			BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64����
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// �����쳣����
						bytes[i] += 256;
					}
			}
			// ����jpegͼƬ
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
			} catch (Exception e) {
				return false;
			}
	}
		
}