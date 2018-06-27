package my.study.test.javaDll;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
public interface LibmatchJNADll extends Library{

	 public long seemmo_match_init(boolean beta_swith);
	 public NativeLong seemmo_pvc_match(
			    char[] b
	            ,int feature_src_len
	            ,char[] a
	            ,int feature_to_len
	            ,int type
	            ,float[] score
	            ,float[] proi);
	 public NativeLong seemmo_pvc_match(
			 	 String b
	            ,int feature_src_len
	            ,String a
	            ,int feature_to_len
	            ,int type
	            ,float[] score
	            ,float[] proi);
	 public NativeLong seemmo_pvc_match(
			 byte[] b
            ,int feature_src_len
            ,byte[] a
            ,int feature_to_len
            ,int type
            ,float[] score
            ,float[] proi);
};
	
	
/**long 
			const char* feature_src,
            int feature_src_len,
            const char* feature_to,
            int feature_to_len,
            int type,    0-8
            float* score,
            float* proi=NULL
*/