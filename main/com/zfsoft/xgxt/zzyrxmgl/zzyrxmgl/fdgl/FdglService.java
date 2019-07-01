package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.fdgl;


import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;

/** 
 * @功能描述：资助育人项目管理-辅导管理service
 * @author：Lu.Yao 【1271】
 * @date：2017-10-20 上午11:15:56 
 */
public class FdglService extends SuperServiceImpl<ZzyrxmglActionForm, FdglDao> {

	private FdglDao dao = new FdglDao();
	
	public FdglService() {
		super.setDao(dao);
	}

	/** 
	 * @description：获取我的辅导页面数据
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-17 上午09:28:00 
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageList2(ZzyrxmglActionForm model,
			User user) throws Exception {
		return dao.getPageList2(model,user);
	}

	/**
	 * @throws Exception  
	 * @description：增加
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-17 上午09:56:14 
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertFdgl(ZzyrxmglActionForm model, User user) throws Exception {
		boolean result = super.runInsert(model);
		if(result){
			String[] kfxydm = model.getKfxydm().split(",");
			for(int i =  0; i< kfxydm.length ; i++){
				dao.insertKfxydm(kfxydm[i],model.getFdfbid());			
			}
		}
		return result;
	}

	/** 
	 * @description：查看
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-17 下午03:41:54 
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getModelMap(ZzyrxmglActionForm t) throws Exception {
		return dao.getModelMap(t);
	}

	/** 
	 * @description：修改
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-17 下午03:45:55 
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateFdgl(ZzyrxmglActionForm model, User user) throws Exception {
		boolean result = super.runUpdate(model);
		if(result){
			String[] kfxydm = model.getKfxydm().split(",");
			dao.deleteKfxydm(model);
			for(int i =  0; i< kfxydm.length ; i++){
				dao.insertKfxydm(kfxydm[i],model.getFdfbid());			
			}
		}
		return result;
	}

	/** 
	 * @description：查询关联开放学院list
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-17 下午04:04:08 
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKfxydmByid(ZzyrxmglActionForm t) {
		return dao.getKfxydmByid(t);
	}

	/** 
	 * @description：删除
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-17 下午04:25:46 
	 * @param values
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public int runDelete(String values) throws Exception {
		int num = dao.runDelete(values.split(","));
		if(num > 0){
			String[] id = values.split(",");
			for(int i=0;i<id.length;i++){
				ZzyrxmglActionForm model = new ZzyrxmglActionForm();
				model.setFdfbid(id[i]);
				dao.deleteKfxydm(model);
			}
		}
		return num;
	}

	/** 
	 * @description：获取报名人员信息list
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-18 上午11:27:49 
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBmrsList(ZzyrxmglActionForm model) {
		return dao.getBmrsList(model);
	}

	/** 
	 * @description：辅导审核
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-18 下午02:00:04 
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateShzt(ZzyrxmglActionForm model) throws Exception{
		return dao.updateShzt(model);
	}

	/** 
	 * @description：查看
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-18 下午03:47:39 
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getModelMap2(ZzyrxmglActionForm model) {
		return dao.getModelMap2(model);
	}

	/** 
	 * @description：填写辅导记录
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 上午08:45:54 
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean addFdjl(ZzyrxmglActionForm model, User user) throws Exception {
		boolean flag = true;
		String[] fdrq = model.getFdrq().split(",");
		String[] fdnr = model.getFdnr().split(",");
		String[] fdbz = model.getFdbz().split(",");
		String[] fdjssj = model.getFdjssj().split(",");
		String[] gs = model.getGs().split(",");
		String[] fddd = model.getFddd().split(",");
		String[] fdpj = model.getFdpj().split(",");
		dao.deleteFdjlPl(model);
		for(int i= 0 ; i < fdrq.length ; i++){
			ZzyrxmglActionForm zmodel = new ZzyrxmglActionForm();
			if(!StringUtils.isNull(fdrq[i])){
				zmodel.setFdxxid(model.getFdxxid());
					zmodel.setFdrq(fdrq[i]);					
				if(fdnr.length > i){
					zmodel.setFdnr(fdnr[i]);				
				}
				if(fdjssj.length > i){
					zmodel.setFdjssj(fdjssj[i]);
				}
				if(gs.length > i){
					zmodel.setGs(gs[i]);
				}
				if(fddd.length > i){
					zmodel.setFddd(fddd[i]);
				}
				if(fdpj.length > i){
					zmodel.setFdpj(fdpj[i]);
				}
				if(fdbz.length > i){
					zmodel.setFdbz(fdbz[i]);
				}else{
					zmodel.setFdbz("");
				}
				zmodel.setFdlx(model.getFdlx());
				flag &= dao.insertFdjl(zmodel);
			}
		}
		return flag;
	}

	/** 
	 * @description：获取辅导记录list
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 上午08:58:05 
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdjlList(ZzyrxmglActionForm model) {
		return dao.getFdjlList(model);
	}

	/** 
	 * @description：删除辅导记录
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 上午09:40:12 
	 * @param model
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int deleteFdjl(ZzyrxmglActionForm model) throws Exception{
		return dao.deleteFdjl(model);
	}

	/** 
	 * @description：判断能否删除
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 下午04:40:08 
	 * @param values
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkCandel(String values) {
		return dao.checkCandel(values);
	}

	/** 
	 * @description：判断已同意辅导人数
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 下午04:57:56 
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkYtyfdrs(ZzyrxmglActionForm model) {
		return dao.checkYtyfdrs(model);
	}

	/** 
	 * @description：判断能否不同意辅导
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-23 下午02:49:50 
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkCancancel(ZzyrxmglActionForm model) {
		return dao.checkCancancel(model);
	}
	
	/**
	 * @description	： 只有每个月前五天才能申请
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-24 上午10:25:35
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public boolean sfksq(){
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		int day = today.getDate();
		if(day <= 5){
			return true;
		}else{
			return false;
		}
	}
	
}
