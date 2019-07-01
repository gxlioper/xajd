
package xgxt.utils;

@SuppressWarnings("serial")
public class CustomException extends Exception{
	public static void pringCustomExcInfo(String sql){
		System.out.println("错误发生时间：" + ToolClass.getSysDate());
		System.out.println("发生错误的SQL语句：" + sql);
	}
	
	public static void pringCustomExcInfo(String sql,String[] inputValues){
		System.out.println("错误发生时间：" + ToolClass.getSysDate());
//		System.out.println("发生错误的SQL语句：" + sql);
//		if(inputValues!=null&&inputValues.length>0){
//			System.out.print("发生错误的SQL语句中的set值：");
//			for(int i=0;i<inputValues.length-1;i++){
//				System.out.print(inputValues[i]);
//				System.out.print(",");
//			}
//			System.out.println(inputValues[inputValues.length-1]);
//		}
//      以下代码段在inputValues[i]的值为空是有异常，异常处理再出现异常直接导致我前台页面报错
		if(inputValues!=null&&inputValues.length>0){
			for(int i=0;i<inputValues.length;i++){
				sql = sql.replaceFirst("[?]", "'"+inputValues[i]+"'");
			}
		}
		System.out.println("发生错误的SQL语句：" + sql);
	}
}
