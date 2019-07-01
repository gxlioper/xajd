package xgxt.comm.sjjc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

public class DataDetectMethod {
	
	/**
	 * �ж��Ƿ�Ϊ��ֵ���ַ�
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
			//�ж��Ƿ�Ϊdouble
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
	 * �ж���ֵ���ֶ�ֵ�Ƿ�������������
	 * ��pdlxone,pdlxtwo,pdzone,pdztwo��
	 * @param myForm
	 */
	public static void checkNumZdz(DataDetectForm myForm){
		
		DataDetectDAO dao=new DataDetectDAO();
		DataDetectMethod method=new DataDetectMethod();
		List<HashMap<String,String>>pkValue=new ArrayList<HashMap<String,String>>();
		
		List<HashMap<String,String>>zdList=dao.getTableZd(myForm);
		//�ж�����1
		String pdlxone=myForm.getPdlxone();
		//�ж�����2
		String pdlxtwo=myForm.getPdlxtwo();
		
		//�ж�ֵ1
		double pdzone=0.0;
		if(!Base.isNull(myForm.getPdzone())){
			pdzone=Double.parseDouble(myForm.getPdzone());
		}
		//�ж�ֵ2
		double pdztwo=0.0;
		if(!Base.isNull(myForm.getPdztwo())){
			pdztwo=Double.parseDouble(myForm.getPdztwo());
		}
		
		int n=0;
		boolean blog=false;
		for(int i=0;i<zdList.size();i++){
			HashMap<String,String>zdMap=zdList.get(i);
			//�ж��Ƿ�Ϊdouble
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
	 * �жϴ����ֵ�Ƿ�Ϊ��ֵ��
	 * ����: true ��,false ��
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
	 * �жϴ����ֵ�Ƿ�Ϊ����
	 * ����: true ��,false ��
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
	 * �жϴ����ֵ�Ƿ�Ϊ��ֵ��
	 * ����: true �����ж�����,false ����������
	 * pdlx �ж�����,pdz �ж�ֵ,zdz �ֶ�ֵ
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
