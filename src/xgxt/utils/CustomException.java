
package xgxt.utils;

@SuppressWarnings("serial")
public class CustomException extends Exception{
	public static void pringCustomExcInfo(String sql){
		System.out.println("������ʱ�䣺" + ToolClass.getSysDate());
		System.out.println("���������SQL��䣺" + sql);
	}
	
	public static void pringCustomExcInfo(String sql,String[] inputValues){
		System.out.println("������ʱ�䣺" + ToolClass.getSysDate());
//		System.out.println("���������SQL��䣺" + sql);
//		if(inputValues!=null&&inputValues.length>0){
//			System.out.print("���������SQL����е�setֵ��");
//			for(int i=0;i<inputValues.length-1;i++){
//				System.out.print(inputValues[i]);
//				System.out.print(",");
//			}
//			System.out.println(inputValues[inputValues.length-1]);
//		}
//      ���´������inputValues[i]��ֵΪ�������쳣���쳣�����ٳ����쳣ֱ�ӵ�����ǰ̨ҳ�汨��
		if(inputValues!=null&&inputValues.length>0){
			for(int i=0;i<inputValues.length;i++){
				sql = sql.replaceFirst("[?]", "'"+inputValues[i]+"'");
			}
		}
		System.out.println("���������SQL��䣺" + sql);
	}
}
