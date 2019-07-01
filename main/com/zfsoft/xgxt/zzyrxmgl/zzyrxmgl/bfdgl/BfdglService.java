package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.bfdgl;


import java.util.HashMap;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;

/** 
 * @功能描述：资助育人项目管理-被辅导管理service
 * @author：Lu.Yao 【1271】
 * @date：2017-10-20 上午11:17:07 
 */
public class BfdglService extends SuperServiceImpl<ZzyrxmglActionForm, BfdglDao> {

	private BfdglDao dao = new BfdglDao();
	
	public BfdglService() {
		super.setDao(dao);
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
	 * @description：查询单条记录
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
	 * @description：批量删除
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
	 * @description：增加评价
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 下午02:35:58 
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean addFdpj(ZzyrxmglActionForm model) throws Exception{
		return dao.addFdjl(model);
	}

	/** 
	 * @description：判断能否删除
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 下午04:00:06 
	 * @param values
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkCandel(String values) {
		return dao.checkCandel(values);
	}

	/** 
	 * @description：判断-辅导记录2条以上才可评价
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 下午04:15:38 
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkFdjls(ZzyrxmglActionForm model) {
		return dao.checkFdjls(model);
	}
}
