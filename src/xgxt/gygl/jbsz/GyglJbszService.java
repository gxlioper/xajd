package xgxt.gygl.jbsz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.GyglCommService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_基本设置_Service类
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

public class GyglJbszService extends GyglCommService {

	GyglJbszDAO dao = new GyglJbszDAO();

	/**
	 * 保存公寓基本设置
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveGyjbsz(GyglJbszForm model, User user,
			HttpServletRequest request) throws Exception {

		// 保存表
		String tableName = "xg_gygl_jbszb";
		// 主键
		String pk = "1";
		// 主键值
		String[] pkValue = new String[] { "1" };
		// 保存字段
		String[] onezd = new String[] { "czxq", "czyq", "csgx", "fpdx" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return saveInfoToDb(saveForm, model, request);

	}

	/**
	 * 设置公寓基本设置
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void setGyjbszInfo(GyglJbszForm model) {

		HashMap<String, String> map = getGyjbsz();

		// '存在校区';
		String czxq = map.get("czxq");
		if (!Base.isNull(czxq)) {
			model.setCzxq(czxq);
		}
		// '存在园区';
		String czyq = map.get("czyq");
		if (!Base.isNull(czyq)) {
			model.setCzyq(czyq);
		}
		// '从属关系';
		String csgx = map.get("csgx");
		if (!Base.isNull(csgx)) {
			model.setCsgx(csgx);
		}
		// '分配对象';
		String fpdx = map.get("fpdx");
		if (!Base.isNull(fpdx)) {
			model.setFpdx(fpdx);
		}
	}

	/**
	 * 获得公寓基本设置
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public HashMap<String, String> getGyjbsz() {

		// 保存表
		String tableName = "xg_gygl_jbszb";
		// 主键
		String pk = "rownum";
		// 主键值
		String pkValue = "1";
		// 输出字段
		String[] colList = new String[] { "czxq", "czyq", "csgx", "fpdx",
				"fpfs" };

		return getRsInfo(tableName, pk, pkValue, colList);
	}
	
	/**
	 * 获得公寓基本设置
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public GyglJbszForm getGyjbszModel() {

		GyglJbszForm model = new GyglJbszForm();
		HashMap<String, String> map = getGyjbsz();

		//是否已经设置
		if(!Base.isNull(map.get("fpdx"))){
			model.setSfsz("是");
		}
		
		// '存在校区';
		String czxq = map.get("czxq");
		if (!Base.isNull(czxq)) {
			model.setCzxq(czxq);
		}
		// '存在园区';
		String czyq = map.get("czyq");
		if (!Base.isNull(czyq)) {
			model.setCzyq(czyq);
		}
		// '从属关系';
		String csgx = map.get("csgx");
		if (!Base.isNull(csgx)) {
			model.setCsgx(csgx);
		}
		// '分配对象';
		String fpdx = map.get("fpdx");
		if (!Base.isNull(fpdx)) {
			model.setFpdx(fpdx);
		}
		// '分配方式';
		String fpfs = map.get("fpfs");
		if (!Base.isNull(fpfs)) {
			model.setFpfs(fpfs);
		}
		
		return model;
	}
}