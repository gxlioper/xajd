package xgxt.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import xgxt.base.DealString;

public class MakeQuery {
	
	String[] inputList;       //查询条件中占位符实际值
	String queryString = "";  //查询条件,带占位符
	
	//通用的查询字段
	public final String[] baseQueryArr = {"xn", "xq", "xydm", "zydm", "bjdm", "nj"};
	//通用的模糊查询字段
	public final String[] baseListQueryArr = {"xh", "xm"};
	
	/**
	 * 该方法用于数据查询时,对MODEL中的查询条件值进行拼装处理
	 * @param colList
	 * @param colLikeList
	 * @param model
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void makeQuery(String [] colList,String [] colLikeList,Object model) throws IllegalArgumentException,
	SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		//	    传入数组和model，根据传入信息返回查询条件语句
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1 ");
		String queryTemp = "";
		Class myClass = model.getClass();
		StringBuffer inputString = new StringBuffer();
		for(int i = 0 ;i<colList.length;i++){
			String[] tmpCol = colList[i].split(".");
			String colValue = colList[i];
			if(tmpCol.length>1){
				colValue = tmpCol[tmpCol.length-1];
			}
			String methodName    = "get"+colValue.substring(0, 1).toUpperCase()+colValue.substring(1);
			String sT = (String) myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null);
			queryTemp 	     = DealString.toGBK(sT);
			if(queryTemp != null && !("".equalsIgnoreCase(queryTemp.trim()))){
				sb.append(" and ");
				sb.append(colList[i]);
				sb.append(" = ? ");
				
				inputString.append(queryTemp);
				inputString.append("!!#!!");
			}
		}
		if(colLikeList!=null){
			for(int i = 0 ;i<colLikeList.length;i++){
				String[] tmpCol = colLikeList[i].split("\\.");
				String colValue = colLikeList[i];
				if(tmpCol.length>0){
					colValue = tmpCol[tmpCol.length-1];
				}
				String methodName    = "get"+colValue.substring(0, 1).toUpperCase()+colValue.substring(1);
				String sT = (String) myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null);
				queryTemp 	     = DealString.toGBK(sT);
				if(queryTemp != null && !("".equalsIgnoreCase(queryTemp.trim()))){
					sb.append(" and ");
					sb.append(colLikeList[i]);
					sb.append(" like '%'||?||'%'");
				
					inputString.append(queryTemp);
					inputString.append("!!#!!");
				}
			}
		}
		if (inputString != null && inputString.length() > 0) {
			setInputList(inputString.toString().split("!!#!!"));
			setQueryString(sb.toString());
		}else{
			setInputList(new String[]{});
			setQueryString(sb.toString());
		}
	}
	
	
	
	public void makeQueryForRequest(String [] colList,String [] colLikeList,HttpServletRequest request) throws IllegalArgumentException,
	SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		//	    传入数组和model，根据传入信息返回查询条件语句
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1 ");
		String queryTemp = "";
		StringBuffer inputString = new StringBuffer();
		for(int i = 0 ;i<colList.length;i++){
			queryTemp = request.getParameter(colList[i]);
			if(queryTemp != null && !("".equalsIgnoreCase(queryTemp.trim()))){
				sb.append(" and ");
				sb.append(colList[i]);
				sb.append(" = ? ");
				
				inputString.append(queryTemp);
				inputString.append("!!#!!");
			}
		}
		queryTemp = "";
		if(colLikeList!=null){
			for(int i = 0 ;i<colLikeList.length;i++){
				queryTemp = request.getParameter(colLikeList[i]);
				if(queryTemp != null && !("".equalsIgnoreCase(queryTemp.trim()))){
					sb.append(" and ");
					sb.append(colLikeList[i]);
					sb.append(" like '%'||?||'%' ");
				
					inputString.append(queryTemp);
					inputString.append("!!#!!");
				}
			}
		}
		if (inputString != null && inputString.length() > 0) {
			setInputList(inputString.toString().split("!!#!!"));
			setQueryString(sb.toString());
		}else{
			setInputList(new String[]{});
			setQueryString(sb.toString());
		}
	}
	
	
	public String[] getInputList() {
		return inputList;
	}
	public void setInputList(String[] inputList) {
		this.inputList = inputList;
	}
	public String queryStr() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public String getQueryString() {
		return queryString;
	}
	
}
