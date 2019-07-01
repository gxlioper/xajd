package xgxt.utils;

public class Check_Input_Value {

	public static int check (String input)
	{
		String [] test = input.split("'") ;
		int len=test.length;
		return len;
	}
	public static boolean check2 (String input)
	{
		String [] test = input.split("'");
		int len=test.length;
		return (len==1)? true : false;
	}
	
	public static boolean[] checkArrVal(String[] input){
		boolean[] result = new boolean[input.length];
		for(int i=0;i<result.length;i++){
			result[i] = check2(input[i]);
		}
		return result;
	}

}
