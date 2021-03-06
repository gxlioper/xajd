package xsgzgl.pjpy.general.djbg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xsgzgl.pjpy.general.inter.PjpyDjbgInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_登记表格_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyDjbgService extends CommService implements PjpyDjbgInterface {

	PjpyDjbgDAO dao = new PjpyDjbgDAO();

	/**
	 * 获得登记表格
	 * 
	 * @author 伟大的骆
	 */
	public String getDjbg(PjpyDjbgModel model) {

		// 登记表格
		String djbg = "djbg";

		return djbg;
	}

	/**
	 * 登记表格内容
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, Object> getDjbgInfo(PjpyDjbgModel model) {

		// 学号
		String xh = model.getXh();
		// 项目名称
		String xmmc = model.getXmmc();
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("xh", xh);
		map.put("xn", xn);
		map.put("xmmc", xmmc);

		// 设置学生信息
		setXsxxInfo(map);

		String nowTime = dao.getNowTime("YYYY年MM月DD日");
		map.put("nowTime", nowTime);
		
		return map;
	}

	/**
	 * 登记表格内容
	 * 
	 * @date 2013-01-31
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getDjbgInfoList(PjpyDjbgModel model) {
		// TODO 自动生成方法存根
		return null;
	}
	
	public String getPrintJspForward(PjpyDjbgModel model) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 设置学生信息（评奖人员库）
	 * 
	 * @author 伟大的骆
	 */
	private void setXsxxInfo(HashMap<String, Object> map) {

		// 学号
		String xh = (String) map.get("xh");
		// 学年
		String xn = (String) map.get("xn");

		HashMap<String, String> xsxx = dao.setXsxxInfo(xh, xn);

		map.putAll(xsxx);
	}
}
