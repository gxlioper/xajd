package common.newp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArrayUtil {
	
	private static final String BZ = "bz";//备注字段
	/**
	 * 判断一个数组是否为null,如果数组为null或数组长度为0,则返回true，
	 * 否则返回false
	 * @param str
	 * 
	 */
	public static boolean isNull(Object[] array) {
		return (array == null || (array != null && array.length == 0));
	}
	
	/**
	 * 数据组反转操作
	 * @param array 数组源
	 * @param begin 要反转的开始位置
	 * @param end 要反转的结束位置
	 * @throws Exception
	 */
    public static void Reverse(String[] array, int begin, int end) throws Exception {
		if (isNull(array)) {
			throw new NullPointerException("数组为空");// 没有找到数组为空的异常
		}

		if (begin < 0) {
			throw new ArrayIndexOutOfBoundsException("begin 不能小于0");
		}

		if (end < begin) {
			throw new ArrayIndexOutOfBoundsException("begin 不能大于 end ");
		}

		if (end >= array.length) {
			throw new ArrayIndexOutOfBoundsException("end 不能超过数组长度");
		}

		while (begin < end) {
			String temp = array[begin];
			array[begin] = array[end];
			array[end] = temp;

			begin++;
			end--;
		}
	} 
    
    /**
	 * 将两个数组和为一个数组
	 * @param array1
	 * @param array2
	 */
	public String[] unionArray(String[] array1, String[] array2) {
		if (!isNull(array1)) {
			if (!isNull(array2)) {
				String array[] = new String[array1.length + array2.length];
				copyArray(array1, array);
				for (int i = 0; i < array2.length; i++) {
					array[array1.length + i] = array2[i];
				}
				return array;
			} else {
				return array1;
			}
		} else {
			return array2;
		}
	}

	 /**
	 * 将一个数组copy到另一数组中
	 * @param fromArray 源数组
	 * @param toArray2　目标数组
	 */
	public String[] copyArray(String[] fromArray, String[] toArray2) {
		if (!isNull(fromArray) && !isNull(toArray2)) {
			int min = fromArray.length <= toArray2.length ? fromArray.length
					: toArray2.length;
			for (int i = 0; i < min; i++) {
				toArray2[i] = fromArray[i];
			}
			return toArray2;
		} else {
			if (isNull(toArray2)) {
				return fromArray;
			} else {
				return toArray2;
			}
			
		}
	}
	
	/**
	 * 如果输出字段列表中有BZ这个字段则将备注换到最后,备注后面的向前移一位
	 * @param zdList  example: []=a b c bz d  >> a b c d bz
	 * @return
	 */
	public static String[] changeBzAfter(String[] zdList) throws Exception {
		if (zdList != null) {
			int j=-1;
			for (int i=0;i<zdList.length;i++) {
				if (BZ.equalsIgnoreCase(zdList[i])) {
					j=i;
					break;
				}
			}
			if (j > -1) {
				for (int k=j;k<zdList.length;k++) {
					if (k<zdList.length-1) {
						zdList[k] = zdList[k+1];
					}
				}
				zdList[zdList.length-1] = BZ;
			}
		}
		return zdList;
	}
	
	public static List<HashMap<String, String>> arrayToList(String[] arr1, String[] arr2) {
		// 将两个数组合并到一个List，两个数组大小一致，通常为中英文对照。参数要求英文在前，中文在后。
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		int len = (arr1.length > arr2.length) ? arr2.length : arr1.length;
		HashMap<String, String> map = null;
		for (int i = 0; i < len; i++) {
			map = new HashMap<String, String>();
			map.put("en", arr1[i]);
			map.put("cn", arr2[i]);
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 
	 * @描述: 数组去重
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-24 下午02:57:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String[] 返回类型 
	 * @throws
	 */
	public static String[] removeRepeatElementInArray(String[] originals){
		List<String> list = new ArrayList<String>();
		list.add(originals[0]);  
		for(int i=1;i<originals.length;i++){  //originals[i]
		    if(!list.contains(originals[i])){  
		            list.add(originals[i]);  
		    }  
		}
		return  list.toArray(new String[]{});
	}
	
	
}
