package common.newp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArrayUtil {
	
	private static final String BZ = "bz";//��ע�ֶ�
	/**
	 * �ж�һ�������Ƿ�Ϊnull,�������Ϊnull�����鳤��Ϊ0,�򷵻�true��
	 * ���򷵻�false
	 * @param str
	 * 
	 */
	public static boolean isNull(Object[] array) {
		return (array == null || (array != null && array.length == 0));
	}
	
	/**
	 * �����鷴ת����
	 * @param array ����Դ
	 * @param begin Ҫ��ת�Ŀ�ʼλ��
	 * @param end Ҫ��ת�Ľ���λ��
	 * @throws Exception
	 */
    public static void Reverse(String[] array, int begin, int end) throws Exception {
		if (isNull(array)) {
			throw new NullPointerException("����Ϊ��");// û���ҵ�����Ϊ�յ��쳣
		}

		if (begin < 0) {
			throw new ArrayIndexOutOfBoundsException("begin ����С��0");
		}

		if (end < begin) {
			throw new ArrayIndexOutOfBoundsException("begin ���ܴ��� end ");
		}

		if (end >= array.length) {
			throw new ArrayIndexOutOfBoundsException("end ���ܳ������鳤��");
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
	 * �����������Ϊһ������
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
	 * ��һ������copy����һ������
	 * @param fromArray Դ����
	 * @param toArray2��Ŀ������
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
	 * �������ֶ��б�����BZ����ֶ��򽫱�ע�������,��ע�������ǰ��һλ
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
		// ����������ϲ���һ��List�����������Сһ�£�ͨ��Ϊ��Ӣ�Ķ��ա�����Ҫ��Ӣ����ǰ�������ں�
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
	 * @����: ����ȥ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-24 ����02:57:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String[] �������� 
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
