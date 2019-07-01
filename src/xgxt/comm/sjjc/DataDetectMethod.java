package xgxt.comm.sjjc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

public class DataDetectMethod {
	
	/**
	 * 判断是否为数值型字符
	 * @param myForm
	 */
	public static void checkNum(DataDetectForm myForm){
		DataDetectDAO dao=new DataDetectDAO();
		DataDetectMethod method=new DataDetectMethod();
		
		List<HashMap<String,String>>zdList=dao.getTableZd(myForm);
		List<HashMap<String,String>>pkValue=new ArrayList<HashMap<String,String>>();
		int n=0;
		for(int i=0;i<zdList.size();i++){
			HashMap<String,String>zdMap=zdList.get(i);
			//判断是否为double
			if(!Base.isNull(zdMap.get(myForm.getZd()))
					&& !method.isDouble(zdMap.get(myForm.getZd()))){
				n++;
				if(!Base.isNull(myForm.getPk())){
					HashMap<String,String>pkMap=new HashMap<String,String>();
					pkMap.put("pkValue", zdMap.get("pkValue"));
					pkValue.add(pkMap);
				}
			}
		}
		myForm.setPkValue(pkValue);
		myForm.setCount(String.valueOf(n));
	}
	
	/**
	 * 判断数值型字段值是否满足配置条件
	 * （pdlxone,pdlxtwo,pdzone,pdztwo）
	 * @param myForm
	 */
	public static void checkNumZdz(DataDetectForm myForm){
		
		DataDetectDAO dao=new DataDetectDAO();
		DataDetectMethod method=new DataDetectMethod();
		List<HashMap<String,String>>pkValue=new ArrayList<HashMap<String,String>>();
		
		List<HashMap<String,String>>zdList=dao.getTableZd(myForm);
		//判断类型1
		String pdlxone=myForm.getPdlxone();
		//判断类型2
		String pdlxtwo=myForm.getPdlxtwo();
		
		//判断值1
		double pdzone=0.0;
		if(!Base.isNull(myForm.getPdzone())){
			pdzone=Double.parseDouble(myForm.getPdzone());
		}
		//判断值2
		double pdztwo=0.0;
		if(!Base.isNull(myForm.getPdztwo())){
			pdztwo=Double.parseDouble(myForm.getPdztwo());
		}
		
		int n=0;
		boolean blog=false;
		for(int i=0;i<zdList.size();i++){
			HashMap<String,String>zdMap=zdList.get(i);
			//判断是否为double
			if(!Base.isNull(zdMap.get(myForm.getZd()))
					&& method.isDouble(zdMap.get(myForm.getZd()))){
				double zdz=Double.parseDouble(zdMap.get(myForm.getZd()));
				if(!Base.isNull(pdlxone)){
					if(method.checkNumZdz(pdlxone,pdzone,zdz)){
						blog=true;
					}else{
						
						blog=false;
					}
				}
				if(!Base.isNull(pdlxtwo)){
					if(!method.checkNumZdz(pdlxtwo,pdztwo,zdz)){
						
						blog=false;
					}
				}
				
				if(!blog){
					n++;
					if(!Base.isNull(myForm.getPk())){
						HashMap<String,String>pkMap=new HashMap<String,String>();
						pkMap.put("pkValue", zdMap.get("pkValue"));
						pkValue.add(pkMap);
					}
				}
			}
		}
		myForm.setPkValue(pkValue);
		myForm.setCount(String.valueOf(n));
	}
	
	/**
	 * 判断传入的值是否为数值型
	 * 返回: true 是,false 否
	 * author qlj
	 */
	@SuppressWarnings("finally")
	public boolean isDouble(String str) {
		
		boolean blog = true;
		
		try {
			
			Double.parseDouble(str);

		} catch (Exception e) {

			blog=false;
		
		} finally {
			
			return blog;
		}

	}
	
	/**
	 * 判断传入的值是否为整型
	 * 返回: true 是,false 否
	 * author qlj
	 */
	@SuppressWarnings("finally")
	public boolean isInteger(String str) {
		
		boolean blog = true;
		
		try {
			
			Integer.parseInt(str);

		} catch (Exception e) {

			blog=false;
		
		} finally {
			
			return blog;
		}

	}
	
	/**
	 * 判断传入的值是否为数值型
	 * 返回: true 满足判断条件,false 不满足条件
	 * pdlx 判断类型,pdz 判断值,zdz 字段值
	 * author qlj
	 */
	public boolean checkNumZdz(String pdlx, Double pdz, Double zdz) {
		
		if (">".equalsIgnoreCase(pdlx)) {
			if (zdz > pdz) {
				return true;
			}
		} else if (">=".equalsIgnoreCase(pdlx)) {
			if (zdz >= pdz) {
				return true;
			}
		} else if ("=".equalsIgnoreCase(pdlx)) {
			if (zdz == pdz) {
				return true;
			}
		} else if ("<=".equalsIgnoreCase(pdlx)) {
			if (zdz <= pdz) {
				return true;
			}
		} else if ("<".equalsIgnoreCase(pdlx)) {
			if (zdz < pdz) {
				return true;
			}
		}
		
		return false;
	}
}
