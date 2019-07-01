package xsgzgl.xszz.general.ywjk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xsgzgl.xszz.general.XszzGeneralService;
import xsgzgl.xszz.general.inter.XszzYwjkInterface;

import common.GlobalsVariable;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生资助_業務接口_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XszzYwjkService extends XszzGeneralService implements
		XszzYwjkInterface {

	XszzYwjkDAO dao = new XszzYwjkDAO();

	/**
	 * 获得學生評獎列表
	 * 
	 * @date 2013-01-23
	 * @author 伟大的骆
	 */
	public List<HashMap<String, Object>> getStuZzList(String xh) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		// 资助项目列表
		List<String[]> zzxmList = dao.getXszzList(xh);

		String[] title = { "资助周期", "项目名称", "资助级别", "金额", "申请时间" };
		List<String[]> rs = new ArrayList<String[]>();
		rs.add(title);
		rs.addAll(zzxmList);

		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "资助项目");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, rs);
		list.add(map);

		return list;
	}
}