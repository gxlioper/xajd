package xsgzgl.pjpy.general.inter;

import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.action.Base;
import xgxt.pjpy.comm.pjpy.pjlc.jgcx.PjpyJgcxForm;
import xsgzgl.pjpy.general.djbg.PjpyDjbgModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_登记表格_Interface类
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

public interface PjpyDjbgInterface {

	// 获得登记表格名称
	public String getDjbg(PjpyDjbgModel model);

	// 获得登记表格内容
	public HashMap<String, Object> getDjbgInfo(PjpyDjbgModel model);

	// 获得登记表格内容
	public List<HashMap<String, String>> getDjbgInfoList(PjpyDjbgModel model);

	// 获取打印表路径
	public String getPrintJspForward(PjpyDjbgModel model) throws Exception;
}
