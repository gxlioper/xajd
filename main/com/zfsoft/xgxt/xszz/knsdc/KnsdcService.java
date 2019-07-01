package com.zfsoft.xgxt.xszz.knsdc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwdmwh.RcxwdmwhForm;






/**
 * 
 * ����ά��service
 * 
 * @author maxh
 * 2013-4-17
 */
public class KnsdcService extends SuperServiceImpl<KnsdcForm, KnsdcDao> {

	private KnsdcDao dao = new KnsdcDao();
	
	public KnsdcService() {
		super.setDao(dao);
	}
	
	/**
	 * ��ȡ��һ�����δ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int getNextDcdm() throws Exception{
		int maxDcdm=0;
		maxDcdm=dao.getMaxDcdm()+1;
		return maxDcdm;
		
	}
	/**
	 * 
	 * @����:��ѯ���������Ƿ����
	 * @���ߣ�
	 * @���ڣ�2013-4-20 ����01:41:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����  wanghj����2014-1-16����String->int
	 * @param model
	 * @return
	 * @throws Exception
	 * JcszForm �������� 
	 * @throws
	 */
	 public boolean isExistByKnsdc(KnsdcForm model, String type)throws Exception{
	     boolean flag = false;
		if("save".equalsIgnoreCase(type)){
			int num=dao.checkExistForSave(model);
			if(num>0){
				flag = true;
			}
		}else if("update".equalsIgnoreCase(type)){
			int num=dao.checkExistForUpdate(model);
			if(num>1){
				flag = true;
			}	
		}
    	return  flag;
    		
    }
	 public List<HashMap<String, String>> knyyList(KnsdcForm model)throws Exception{
		   return dao.knyyList(model);
	    }
	 public boolean checkKnyy(KnsdcForm model)throws Exception{
	   return dao.checkKnyy(model);
    }
    /**
     * 
     * @����:����������list
     * @���ߣ�maxh
     * @���ڣ�2013-4-22 ����02:10:09
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param request
     * @return
     * List<HashMap<String,String>> �������� 
     * @throws
     */
    public List<HashMap<String, String>> getKnsdcList(){
		return dao.getKnsdcList();
	}
    /**
     * 
     * @����:�޳��������
     * @���ߣ�xiaxia[���ţ�1104]
     * @���ڣ�2015-4-18 ����09:44:48
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * List<HashMap<String,String>> �������� 
     * @throws
     */
    public List<HashMap<String, String>> getWczzList(){
		return dao.getWczzList();
	}
    /**
     * 
     * @����:����������������Ƿ�������
     * @���ߣ�maxh
     * @���ڣ�2013-4-24 ����11:01:56
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param value
     * @return
     * @throws Exception
     * String �������� 
     * @throws
     */
    public String  checkDcForKnsjg(String value)throws Exception{
    	String resultDcmc="";
    	String[] dcmc=dao.checkDcForKnsjg(value);
    	for(int i=0;i<dcmc.length;i++){
			if(i==dcmc.length-1){
				resultDcmc+=dcmc[i];
			}else{
			    resultDcmc+=dcmc[i]+",";
			}
			
		}
		return resultDcmc;
	}
    /**
     * 
     * @����:����������������Ƿ�������
     * @���ߣ�maxh
     * @���ڣ�2013-4-24 ����11:01:56
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param value
     * @return
     * @throws Exception
     * String �������� 
     * @throws
     */
    public String  checkDcForKnssh(String value)throws Exception{
    	 String resultDcmc="";
    	 String[] dcmc=dao.checkDcForKnssh(value);
    	for(int i=0;i<dcmc.length;i++){
			if(i==dcmc.length-1){
				resultDcmc+=dcmc[i];
			}else{
			    resultDcmc+=dcmc[i]+",";
			}
			
		}
		return resultDcmc;
	}
    
	
	/**
	 * 
	 * @����:�����������-����ѧ��ѧ�ڣ���ѯ�϶�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsjgRddc(String xn,String xq) throws Exception {
		return dao.getKnsjgRddc(xn,xq);
	}
	
	/**
	 * 
	 * @����:������������Ϣ�޸�
	 * @���ߣ�ligl
	 * @���ڣ�2014-1-16 ����11:24:16
	 * @�޸ļ�¼:
	 * @return boolean ��������
	 * @throws
	 */
	public boolean updateKnsdcInfo(String dcdm,KnsdcForm form) throws Exception{
		
		return dao.updateKnsdcInfo(dcdm, form);
	}
	public boolean saveKnyy(KnsdcForm model,String type) throws Exception{
		boolean result = false;
		if ("add".equals(type)) {
			return dao.addKnyy(model);
		} else if ("update".equals(type)) {
			return dao.updateKnyy(model);
		}
		return result;
		
	}
	public KnsdcForm getKnyy(KnsdcForm model) throws Exception{
		return dao.getKnyy(model);
	}
	
	//����ԭ���б�
	public List<HashMap<String, String>> getKnyyList() throws Exception{
		return dao.getKnyyList();
	}
	
	public boolean checkKnyyUsed(String[] values) throws Exception {
		return dao.checkKnyyUsed(values);
	}
	public int deleteKnyyInfo(String[] values) throws Exception{
		return dao.deleteKnyyInfo(values);
		
	}
	
}
