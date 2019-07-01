package xgxt.szdw.utils;

import java.lang.reflect.InvocationTargetException;

import xgxt.base.DealString;

public class MakeQuery {
	
	/**
	 * ����һ��Ӣ���ֶ����飬һ��MODEL����MODEL�е��������ֶ�ƴ��where������䡣 ��ת��
	 * @param colList
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static StringBuffer makeQuery(String [] colList,Object model) throws IllegalArgumentException,
	SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		//	    ���������model�����ݴ�����Ϣ���ز�ѯ�������
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1 ");
		String queryTemp = "";
		Class myClass = model.getClass();
		for(int i = 0 ;i<colList.length;i++){
			String methodName    = "get"+colList[i].substring(0, 1).toUpperCase()+colList[i].substring(1);
			String sT = (String) myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null);
			queryTemp 	     = DealString.toGBK(sT);
			if(queryTemp != null && !("".equalsIgnoreCase(queryTemp.trim()))){
				sb.append(" and ");
				sb.append(colList[i]);
				sb.append(" ='");
				sb.append(queryTemp);
				sb.append("' ");
			}
		}
		return sb;	
	}
	
	/**
	 * ����һ��Ӣ���ֶ����飬һ��MODEL����MODEL�е��������ֶ�ƴ��where������䡣 δת�� �÷�������ͬ��ֻ��δ����ת��
	 * 
	 * @param colList
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static StringBuffer makeQueryBywzm(String[] colList, Object model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// ���������model�����ݴ�����Ϣ���ز�ѯ�������
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1 ");
		String queryTemp = "";
		Class myClass = model.getClass();
		for (int i = 0; i < colList.length; i++) {
			String methodName = "get"
					+ colList[i].substring(0, 1).toUpperCase()
					+ colList[i].substring(1);
			String sT = (String) myClass.getMethod(methodName, (Class[]) null)
					.invoke(model, (Object[]) null);
			queryTemp = sT;
			if (queryTemp != null && !("".equalsIgnoreCase(queryTemp.trim()))) {
				sb.append(" and ");
				sb.append(colList[i]);
				sb.append(" ='");
				sb.append(queryTemp);
				sb.append("' ");
			}
		}
		return sb;
	}
	
	public static StringBuffer makeCommanQuery(String [] equalcol,String[] likecol,Object model) throws IllegalArgumentException,
	SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		//	    ���������model�����ݴ�����Ϣ���ز�ѯ�������
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1 ");
		String queryTemp = "";
		Class myClass = model.getClass();
		for(int i = 0 ;i<equalcol.length;i++){
			String methodName    = "get"+equalcol[i].substring(0, 1).toUpperCase()+equalcol[i].substring(1);
			String sT = (String) myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null);
			queryTemp 	     = DealString.toGBK(sT);
			if(queryTemp != null && !("".equalsIgnoreCase(queryTemp.trim()))){
				sb.append(" and ");
				sb.append(equalcol[i]);
				sb.append(" ='");
				sb.append(queryTemp);
				sb.append("' ");
			}
		}
		for(int i = 0 ;i<likecol.length;i++){
			String methodName    = "get"+likecol[i].substring(0, 1).toUpperCase()+likecol[i].substring(1);
			String sT = (String) myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null);
			queryTemp 	     = DealString.toGBK(sT);
			if(queryTemp != null && !("".equalsIgnoreCase(queryTemp.trim()))){
				sb.append(" and ");
				sb.append(likecol[i]);
				sb.append(" like '%");
				sb.append(queryTemp);
				sb.append("%' ");
			}
		}
		return sb;	
	}
}
