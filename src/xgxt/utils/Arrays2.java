package xgxt.utils;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;

public class Arrays2 {
	public static int search(String[] arr,String b ){
		int j = -1;
		for(int i=0;i<arr.length;i++){
			j = (arr[i] != null && arr[i].equalsIgnoreCase(b) == true) ? i : -1;
			if (j>=0) break;
		}
		return j;
	}
	/**
	 * @author Administrator 
	 * @param the array you want to make its strings lower
	 * */
	public static void toLower(String[] aa){
		for(int i=0;i<aa.length;i++){
			aa[i] = aa[i].toLowerCase();
		}
	}
	/**
	 * 
	 * @����:add element
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-22 ����02:23:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param source	the source array
	 * @param obj		the add element
	 * @return String[]
	 */
	public static String[] addObject(String[] source,String obj){
		String[] param=new String[source.length+1];
		copy(source, param, source.length, 0, 0);
		param[param.length-1]=obj;
		return param;
	}
	/**
	 * @author Administrator
	 * @param src is the source array
	 * @param target is the target array
	 * @param end is the end position of the source array for coping
	 * @param srcstart is the start position of the source array
	 * @param tarstart is start position of the target array for coping 
	 * */
	public static void copy(String[] src, String[] target,int end,int srcstart,int tarstart){
		int diffent = (end - srcstart) , max;
		max = (diffent > target.length) ? target.length : diffent; 
		for(int i=0;i< max;i++){
			target[tarstart+i] = StringUtils.isNotNull(src[srcstart+i]) ? src[srcstart+i].trim() : src[srcstart+i];
		}
	}
	
	/**
	 * @author Administrator
	 * @param src is the source array
	 * @param target is the target array
	 * @param end is the end position of the source array for coping
	 * @param srcstart is the start position of the source array
	 * @param tarstart is start position of the target array for coping 
	 * */
	public static void copyShort(String[] src, String[] target,int end,int srcstart,int tarstart){
		@SuppressWarnings("unused")
		int diffent = (end - srcstart) , max;
		max = target.length; 
		for(int i=0;i< max;i++){
			target[tarstart+i] = StringUtils.isNotNull(src[srcstart+i]) ? src[srcstart+i].trim() : src[srcstart+i];
		}
	}
	
	
	/**
	 * ת����������ı���ΪGBK
	 * @param arr
	 */
	public static void toGBKArray(String[] arr){
		for(int i=0;i<arr.length;i++){
			arr[i] = DealString.toGBK(arr[i]);
		}
	}
	
	/**
	 * ����������ת�����ַ���
	 * @param input Ҫ����ת��������
	 * @param splitFlag ���ɴ��ķָ���
	 * @return
	 */
	public static String changeArrayToString(Object[] input,String splitFlag){
		String result = "";
		for(int i=0;i<input.length;i++){
			result += (i== input.length-1) ? input[i].toString() : input[i].toString()+splitFlag;
		}
		return result;
	}
	
	/**
	 * ȥ�������е�ÿ���ַ����Ŀո�
	 * @param arg
	 */
	public static void trim(String[] arg){
		for(int i=0;i<arg.length;i++){
			arg[i] = arg[i].trim(); 
		}
	}
	
	public static void main(String[] args) {
		String[] a1 = {"1     ","               2","                                                   3","4","5"};
		trim(a1);
		for(String temp : a1){
			System.out.println(temp);
		}
	}
	
	/**
	 * ת����������ı���ΪGBK
	 * @param arr
	 */
	public static String[] toGBKArrays(String[] arr){
		if (arr == null || arr.length==0) {
			return null;
		} else {
			String[] list = new String[arr.length];
			for(int i=0;i<arr.length;i++){
				list[i] = DealString.toGBK(arr[i]);
			}
			return list;
		}		
	}
	
	/**
	 * ��int����תΪString��
	 * @param arr int ����
	 * @return
	 */
	public static String[] intArrToStrArr(int[] arr) {
		if (arr != null && arr.length > 0) {
			String[] rs = new String[arr.length];
			for (int i=0;i<arr.length;i++) {
				rs[i] = String.valueOf(arr[i]);
			}
			return rs;
		}
		return null;
	}
	
	/**
	 * �������飬������ֵ
	 * @param arr int ����
	 * @return
	 */
	public static String strArrToSum(String[] arr) {
		if (arr != null && arr.length > 0) {
			int sum = 0;
			for (int i=0;i<arr.length;i++) {
				sum += Integer.parseInt(arr[i]);
			}
			return ((Integer)sum).toString();
		}
		return null;
	}
}


