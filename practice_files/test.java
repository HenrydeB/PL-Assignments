static class animal {

   public static void method(){ System.out.println("out");}
}

class bird extends animal{

	public static void method(){System.out.println("bird");}
	public static void  test(){ super.method();}
}

class canary extends bird{

	
}

class Main{

public static void main(String[] args){
	canary c = new canary();
	c.test();
}
}
