package xgxt.pjpy.comm.pjpy.pjlc.xmsb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖流程_项目上报_Init类
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

public class PjpyXmsbInit {

	/**
	 * 评奖项目审核_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getPjxmsbRForm(RequestForm rForm, PjpyXmsbForm model,
			HttpServletRequest request) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		PjpyXmsbService service = new PjpyXmsbService();

		// 功能模块
		String gnmk = "pjpy";
		// 系统字段设置
		String menu = "pjxmsb";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "pjpy_pjlc_xmsb.do";
		// 表头
		List<HashMap<String, String>> topTr = getDefaultValue(model);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 评奖学期名称
		String pjxqmc = jbszModel.getPjxqmc();
		// 项目代码
		String xmdm = request.getParameter("xmdm");
		xmdm = Base.isNull(xmdm) ? model.getXmdm() : xmdm;
		// 项目代码
		String bjdm = request.getParameter("bjdm");
		// 项目设置model初始化
		String pk = pjxn + pjxq + pjnd + xmdm;
		PjpyXmszModel xmszModel = service.getXmszForXmdm(pk);
		model.setXmszModel(xmszModel);
		// 设置人数
		String szrs = service.getXmszrs(model);
		xmszModel.setSzrs(szrs);
		// 项目名称
		String xmmc = xmszModel.getXmmc();
		// 控制范围
		String kzfw = xmszModel.getKzfw();

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc",
				"xmdm", "bjdm", "szrs", "xmmc", "kzfw" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc, xmdm, bjdm,
				szrs, xmmc, kzfw };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setTopTr(topTr);

	}
	
	private List<HashMap<String, String>> getDefaultValue(PjpyXmsbForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("en", "xh");
		map.put("cn", "学号");
		topTr.add(map);
		
		map = new HashMap<String, String>();
		map.put("en", "xm");
		map.put("cn", "姓名");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "xb");
		map.put("cn", "性别");
		topTr.add(map);
		
		map = new HashMap<String, String>();
		map.put("en", "bjmc");
		map.put("cn", "班级名称");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "zhf");
		map.put("cn", "综测分");
		topTr.add(map);
		
		// 综测排名
		String zcpm = model.getZcpm();
		
		if("3".equalsIgnoreCase(zcpm)){
			map = new HashMap<String, String>();
			map.put("en", "zcfbjpm");
			map.put("cn", "班级排名");
			topTr.add(map);
		}else if("2".equalsIgnoreCase(zcpm)){
			map = new HashMap<String, String>();
			map.put("en", "zcfnjzypm");
			map.put("cn", "年级专业排名");
			topTr.add(map);
		}
		
		map = new HashMap<String, String>();
		map.put("en", "zyf");
		map.put("cn", "智育分");
		topTr.add(map);
		String zypm=model.getZypm();
		if("3".equalsIgnoreCase(zypm)){
			map = new HashMap<String, String>();
			map.put("en", "zyfbjpm");
			map.put("cn", "智育分班级排名");
			topTr.add(map);
		}else if("2".equalsIgnoreCase(zypm)){
			map = new HashMap<String, String>();
			map.put("en", "zcfnjzypm");
			map.put("cn", "智育分年级专业排名");
			topTr.add(map);
		}
		
		
		
		map = new HashMap<String, String>();
		map.put("en", "cz");
		map.put("cn", "操作");
		topTr.add(map);
		
		return topTr;
	}
}
