package xsgzgl.comm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.SaveForm;
import xsgzgl.comm.BasicModel;

public class BasicService extends CommService{
	
	
	BasicDAO dao=new BasicDAO();
	/**
	 * 将传入得数组根据
	 * 指定的分割符拼接成一个字符串
	 * @param array 传入数组
	 * @param split 特定分割符
	 * @return
	 */
	public String ArrayToStr(String[]array,String split){
		
		StringBuilder str=new StringBuilder();
		
		if(array!=null){
		
			for(int i=0;i<array.length;i++){
				
				
				
				if(i!=0){
					str.append(split);
				}
				
				str.append(array[i]);
			}
		}
		
		return str.toString();
	}
	
	/**
	 * 将几种常用的HTML字符转换为转义符
	 * @param html
	 * @return
	 */
	public String replaceHtml(String html){
		
		if(!Base.isNull(html)){
			
			html=html.replaceAll("\"", "&quot;");
			
			html=html.replaceAll("&", "&amp;");
			
			html=html.replaceAll("<", "&lt;");
			
			html=html.replaceAll(">", "&gt;");
			
		}
		return html;
	}
	
	/**
	 * 根据传入得 键、值形式表字段信息进行保存
	 * 
	 * @param valueMap
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public boolean saveBatch(BasicModel model, Object saveModel)
			throws Exception {

		// 进行数据操作 的表名
		String realTable = model.getTableName();

		String[] pkValue = model.getPkValue();

		String[] pk = model.getPk();

		String[] arrzd = model.getArrayZd();

		String[] onezd = model.getOneZd();

		SaveForm saveForm = new SaveForm();
		
		saveForm.setTableName(realTable);
		
		saveForm.setPk(getString(pk));
		
		saveForm.setPkValue(pkValue);
		
		saveForm.setArrzd(arrzd);
		
		saveForm.setOnezd(onezd);

		return saveInfoToDb(saveForm, saveModel, model.getUser());

	}
	
	public void trimZd(Object model,String zd) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		String methodName="get"+zd.substring(0,1).toUpperCase()+zd.substring(1,zd.length());
		String[]array=(String[])model.getClass().getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null);
		
		for(int i=0;i<array.length;i++){
			
			array[i]=array[i].trim();
			
		}
		 methodName="set"+zd.substring(0,1).toUpperCase()+zd.substring(1,zd.length());
		model.getClass().getMethod(methodName, new Class[] { String[].class }).invoke(model, (Object) array);
	}
	
	
	public void trimZd(Object model,String[] zd) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		for(int i=0;i<zd.length;i++){
			String methodName="get"+zd[i].substring(0,1).toUpperCase()+zd[i].substring(1,zd[i].length());
			String[]array=(String[])model.getClass().getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null);
			
				for(int j=0;j<array.length;j++){
					
					array[j]=array[j].trim();
					
				}
			 methodName="set"+zd[i].substring(0,1).toUpperCase()+zd[i].substring(1,zd[i].length());
			model.getClass().getMethod(methodName, new Class[] { String[].class }).invoke(model, (Object) array);
		}
	}
	
	public String[] toGbk(String[] zd) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		for(int i=0;i<zd.length;i++){
			
			zd[i]=unicode2Gbk(zd[i]);
		
		}
		return zd;
	}
	
	public String getString(String[]array){
		
		StringBuilder str=new StringBuilder();
		
		for(int i=0;i<array.length;i++){
			if(i!=0){
				
				str.append("||'!!@@!!'||");
			}
			str.append(array[i]);
		}
		
		return str.toString();
	}
	
	/**
	 * 批量删除功能
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean batchDelete(BasicModel model){
		
		return dao.batchDelete(model);
	} 
	
	public boolean saveInfo(BasicModel model){
		return dao.saveInfo(model);
	}
	
	public boolean updateInfo(BasicModel model){
		return dao.updateInfo(model);
	}
	
	public boolean batchUpdate(BasicModel model){
		return dao.batchUpdate(model);
	}
	
	public HashMap<String,String>viewInfo(BasicModel model){
		return dao.viewInfo(model);
	}
	

}
