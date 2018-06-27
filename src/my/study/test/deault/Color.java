package my.study.test.deault;
public enum Color {  
    RED("红色"), GREEN("绿色"), BLANK("白色"), YELLO("黄色");  
    // 成员变量  
    private String name;  
    // 构造方法  
    private Color(String name) {  
        this.name = name;  
    }  
    //覆盖方法  
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
