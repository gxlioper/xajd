package com.zfsoft.xgxt.xszz.knsdc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwdmwh.RcxwdmwhForm;






/**
 * 
 * 档次维护service
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
	 * 获取下一个档次代码
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
	 * @描述:查询档次名称是否存在
	 * @作者：
	 * @日期：2013-4-20 下午01:41:41
	 * @修改记录: 修改者名字-修改日期-修改内容  wanghj——2014-1-16——String->int
	 * @param model
	 * @return
	 * @throws Exception
	 * JcszForm 返回类型 
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
     * @描述:困难生档次list
     * @作者：maxh
     * @日期：2013-4-22 下午02:10:09
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param request
     * @return
     * List<HashMap<String,String>> 返回类型 
     * @throws
     */
    public List<HashMap<String, String>> getKnsdcList(){
		return dao.getKnsdcList();
	}
    /**
     * 
     * @描述:无偿资助金额
     * @作者：xiaxia[工号：1104]
     * @日期：2015-4-18 上午09:44:48
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * List<HashMap<String,String>> 返回类型 
     * @throws
     */
    public List<HashMap<String, String>> getWczzList(){
		return dao.getWczzList();
	}
    /**
     * 
     * @描述:检验困难生结果中是否有数据
     * @作者：maxh
     * @日期：2013-4-24 上午11:01:56
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param value
     * @return
     * @throws Exception
     * String 返回类型 
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
     * @描述:检验困难生审核中是否有数据
     * @作者：maxh
     * @日期：2013-4-24 上午11:01:56
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param value
     * @return
     * @throws Exception
     * String 返回类型 
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
	 * @描述:困难生结果表-根据学年学期，查询认定档次
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsjgRddc(String xn,String xq) throws Exception {
		return dao.getKnsjgRddc(xn,xq);
	}
	
	/**
	 * 
	 * @描述:困难生档次信息修改
	 * @作者：ligl
	 * @日期：2014-1-16 上午11:24:16
	 * @修改记录:
	 * @return boolean 返回类型
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
	
	//困难原因列表
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
