public class Test1{


	public static void main(String[] args){
		System.out.println("HelloWorld!");
		
		if(args.length < 2){
			System.out.println("请按以下方式运行:java Test1 参数1 参数2");
		}
		String param1 = args[0];
		String param2 = args[1];
		
		
		System.out.println(param1+"你好，你的年龄是");
		
		System.out.println(2022 - Integer.parseInt(param2));
	}
}