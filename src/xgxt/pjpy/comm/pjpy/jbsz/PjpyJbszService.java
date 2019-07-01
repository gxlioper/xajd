package xgxt.pjpy.comm.pjpy.jbsz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.gywh.DelDetectModel;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_基本设置_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyJbszService extends PjpyCommService {

	PjpyJbszDAO dao = new PjpyJbszDAO();

	/**
	 * 查询基本设置信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getPjpyJbsz() {

		// 保存表
		String tableName = "xg_pjpy_xtszb";
		// 主键
		String pk = "rownum";
		// 主键值
		String pkValue = "1";
		// 单一字段
		String[] colList = new String[] { "pjxn", "pjxq", "pjnd", "rsszfs" };

		HashMap<String, String> map = getRsInfo(tableName, pk, pkValue, colList);

		// 人数分配方式
		String rsszfs = map.get("rsszfs");

		if (Base.isNull(rsszfs)) {
			map.put("rsszfs", "xx");
		}

		return map;
	}

	/**
	 * 保存评奖评优基本设置
	 * 
	 * @author 伟大的骆
	 */
	public Boolean savePjpyJbsz(PjpyJbszForm model, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {

		boolean flag = savePjpySzxx(model, rForm, request);

		if (flag) {
			// ------设置评奖系统设置对象----by 鲁大-------
			// PjxtszModel.pjxtszModel.setPjxn(model.getPjxn());
			// PjxtszModel.pjxtszModel.setPjnd(model.getPjnd());
			// PjxtszModel.pjxtszModel.setPjxq(model.getPjxn());
			PjxtszModel.pjxtszModel.setRsszfs(model.getRsszfs());
			// ----------------end-------------------
			
			flag = savePjpyTjk(model, rForm, request);
		}

		return flag;
	}

	/**
	 * 保存评奖评优设置信息
	 * 
	 * @author 伟大的骆
	 */
	public Boolean savePjpySzxx(PjpyJbszForm model, RequestForm rForm,
			HttpServletRequest request) throws Exception {

		// 保存表
		String tableName = "xg_pjpy_xtszb";
		// 主键
		String pk = "1";
		// 主键值
		String[] pkValue = new String[] { "1" };
		// 单一字段
		String[] onezd = new String[] { "pjxn", "pjxq", "pjnd", "rsszfs" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return saveInfoToDb(saveForm, model, request);
	}

	/**
	 * 保存评奖评优条件库
	 * 
	 * @author 伟大的骆
	 */
	public Boolean savePjpyTjk(PjpyJbszForm model, RequestForm rForm,
			HttpServletRequest request) throws Exception {

		return dao.savePjpyTjk(model);
	}
	
	
	/**
	 * 查询评奖周期设置状态
	 * @return
	 */
	public String getPjpyPjzqSfsz(){
		
		return dao.getPjpyPjzqSfsz();
	}
	
	
	
	/**
	 * 修改评奖周期是否设置
	 * @param sfsz
	 * @return
	 */
	public boolean updatePjzqSfsz(String sfsz){
		
		try {
			return dao.updatePjzqSfsz(sfsz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	/**
	 * 开始新评奖咯~
	 */
	public boolean startPjpy(){
		//开始新评奖，设置状态为‘0’;
		boolean result = updatePjzqSfsz("0");
		//人数设置初始化
		return result ? dao.rsszCsh() : result;
	}
	
	
	/**
	 * 当前评奖周期综测设置记录数
	 * @param zczq
	 * @return
	 */
	public String getZczqSfsz(String zczq){
		
		return dao.getZczqSfsz(zczq);
	}
	
	
	/**
	 * 当前评奖周期的评奖项目个数
	 * @return
	 */
	public String getPjxmSfwh(){
		
		return dao.getPjxmSfwh();
	}
	
	
	 /**
	  * 当前评奖周期的评奖人员是否设置
	 * @return
	 */
	public String getPjrySfsz(){
		
		return dao.getPjrySfsz();
	}
	
	/**
	 * 删除人数设置
	 * @return
	 * @throws Exception 
	 */
	public boolean delRssz(DelDetectModel model){
		return dao.delRssz(model);
	}
}