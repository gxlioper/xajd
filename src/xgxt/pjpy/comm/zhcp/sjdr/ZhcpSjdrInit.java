package xgxt.pjpy.comm.zhcp.sjdr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_数据导入_Init类
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
public class ZhcpSjdrInit {

	/**
	 * 品德基本分_初始化数据(学年)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getSjdrRForm(RequestForm rForm, ZhcpSjdrForm model,
			HttpServletRequest request) {

		// 操作项目
		String czxm = model.getCzxm();
		czxm = Base.isNull(czxm) ? "zd5" : czxm;
		model.setCzxm(czxm);
		
		// 功能模块
		String gnmk = "pjpy";
		// 系统字段设置
		String menu = "jcsjdr";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "pjpy_general_zhcp_maintain.do";
		// 输出值
		String[] colList = new String[] { "xh", "xm", "nj", "bjmc" };
		// 表头
		List<HashMap<String, String>> topTr = getDefaultValue(model);
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = String.valueOf(4);
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "czxm" };
		String[] qtzdz = new String[] { czxm };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	private List<HashMap<String, String>> getDefaultValue(ZhcpSjdrForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		// 操作项目
		String czxm = model.getCzxm();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("en", "pjsj");
		map.put("cn", "评奖时间");
		topTr.add(map);
		
		map = new HashMap<String, String>();
		map.put("en", "xh");
		map.put("cn", "学号");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "xm");
		map.put("cn", "姓名");
		topTr.add(map);
		
		map = new HashMap<String, String>();
		map.put("en", "nj");
		map.put("cn", "年级");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "bjmc");
		map.put("cn", "班级名称");
		topTr.add(map);

		DAO dao = DAO.getInstance();
		String xmmc = dao.getOneValue("xg_pjpy_zcxmb", "xmmc", "xmdm", czxm);
		map = new HashMap<String, String>();
		map.put("en", czxm);
		map.put("cn", xmmc);
		topTr.add(map);
		
		model.setXmmc(xmmc);
		
		return topTr;
	}
}
