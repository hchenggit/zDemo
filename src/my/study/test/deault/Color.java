package my.study.test.deault;
public enum Color {  
    RED("��ɫ"), GREEN("��ɫ"), BLANK("��ɫ"), YELLO("��ɫ");  
    // ��Ա����  
    private String name;  
    // ���췽��  
    private Color(String name) {  
        this.name = name;  
    }  
    //���Ƿ���  
    @Override  
    public String toString() { 
    	System.out.println("----"+this.name);
        return this.name;  
    }  
    public static boolean contains(String _name){
    	Color[] season = values();
	         for(Color s:season){
	              if(s.name().equals(_name)){
	                  return true;
	              }
	          }
	         return false; 
	 }
}  
