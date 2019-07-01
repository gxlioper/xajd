package com.zfsoft.xgxt.xlzx.zxyyclnew;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xlzx.yysq.YysqDao;
import com.zfsoft.xgxt.xlzx.yysq.YysqForm;

/** 
 * 咨询预约处理
 */

public class ZxyyclService extends SuperServiceImpl<ZxyyclForm, ZxyyclDao> {
	
	private ZxyyclDao dao = new ZxyyclDao();

	public ZxyyclService() {
		super.setDao(dao);
	}
	
	/** 
	 * 查询咨询信息
	 */
	public HashMap<String, String> getXlzxInfoByYyId(String id)
	throws Exception {
		
		return dao.getXlzxInfoByYyId(id);
	}
	
	/** 
	 * 选择咨询信息
	 */
	public boolean saveXlzxInfo(ZxyyclForm model)
	throws Exception {
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setId(guid);
		return dao.saveXlzxInfo(model);
	}
	/**
	 * 删除咨询信息
	 */
	public int delZxInfoByYyid(String[] yyid) throws Exception {
		return dao.delZxInfoByYyid(yyid);
	}
	
	/** 
	 * 咨询师或管理员新增预约咨询信息
	 */
	public boolean saveYyzxInfo(ZxyyclForm model,HashMap<String,String> jzxxMap) throws Exception {
		//创建成功的预约信息
		YysqDao yysqDao = new YysqDao();
		YysqForm yysqModel = new YysqForm();
		
		String guid = UniqID.getInstance().getUniqIDHash();
		yysqModel.setId(guid);
		yysqModel.setYyzxrq(model.getZxrq());
		yysqModel.setStatus("2");
		yysqModel.setXh(model.getXh());
		yysqModel.setZgh(model.getZgh());
		yysqModel.setXstell(model.getXstell());
		yysqModel.setSjddm(model.getSjddm());
		yysqModel.setQssj(model.getQssj());
		yysqModel.setJssj(model.getJssj());
		yysqModel.setYyfs(model.getYyfs());
		Boolean yyflag = yysqDao.saveYysqInfo(yysqModel);
		//创建咨询信息
		String newGuid = UniqID.getInstance().getUniqIDHash();
		model.setId(newGuid);
		model.setYyid(guid);
		Boolean zxflag = dao.saveXlzxInfo(model);
		if(!jzxxMap.isEmpty() && yyflag && zxflag){
			jzxxMap.put("sqid",guid);
			dao.saveJzxx(jzxxMap);
		}
		return yyflag && zxflag;
	}
	/** 
	 * 修改咨询信息
	 */
	public boolean updateXlzxInfo(ZxyyclForm model,HashMap<String,String> jzxxMap)
	throws Exception {
		boolean rs = dao.updateXlzxInfo(model);
		if(!jzxxMap.isEmpty() && rs){
			rs = dao.deleteJzxx(new String[]{jzxxMap.get("sqid")});
			if(rs){
				dao.saveJzxx(jzxxMap);
			}
		}
		return rs;
	}
	
}
