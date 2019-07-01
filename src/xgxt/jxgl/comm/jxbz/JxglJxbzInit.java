package xgxt.jxgl.comm.jxbz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.jxgl.comm.JxglCommForm;
import xgxt.pjpy.comm.pjpy.PjxtszModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理_军训编制_Init类
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
public class JxglJxbzInit {

	/**
	 * 品德基本分_初始化数据(学年)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getJxbzRForm(RequestForm rForm, JxglJxbzForm model,
			HttpServletRequest request) {

		JxglCommForm jxszModel = JxglJxbzForm.jxglCommForm;

		// 功能模块
		String gnmk = "jxgl";
		// 系统字段设置
		String menu = "jxbz";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "jxgl_jxbz.do";
		// 表头
		List<HashMap<String, String>> topTr = getDefaultValue(model);
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";
		// 最小编制
		String minBz = jxszModel.getMinBz();
		// 最大编制
		String maxBz = jxszModel.getMaxBz();
		// 菜单ID
		String menuId = request.getParameter("menuId");
		model.setMenuId(menuId);
		// 学年
		String xn = request.getParameter("xn");
		if (Base.isNull(xn)) {
			xn = Base.currXn;
		}
		model.setXn(xn);
		// 操作类型
		String czlx = request.getParameter("czlx");
		model.setCzlx(czlx);
		// 编制代码
		String bzdm = request.getParameter("bzdm");
		model.setBzdm(bzdm);
		// 编制名称
		String bzmc = request.getParameter("bzmc");
		model.setBzmc(bzmc);
		// 编制等级
		String bzdj = request.getParameter("bzdj");
		model.setBzdj(bzdj);
		// 上级代码
		String sjdm = request.getParameter("sjdm");
		model.setSjdm(sjdm);
		// 教师代码
		String jsdm = request.getParameter("jsdm");
		model.setJsdm(jsdm);
		// 教官编号
		String jgbh = request.getParameter("jgbh");
		model.setJgbh(jgbh);
		
		String[] qtzd = new String[] { "xn", "minBz", "maxBz", "czlx" };
		String[] qtzdz = new String[] { xn, minBz, maxBz, czlx };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);

	}

	private List<HashMap<String, String>> getDefaultValue(JxglJxbzForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();
		
		map = new HashMap<String, String>();
		map.put("en", "");
		map.put("cn", "");
		topTr.add(map);
		
		map = new HashMap<String, String>();
		map.put("en", "nj");
		map.put("cn", "年级");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "xymc");
		map.put("cn", "院系");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "zymc");
		map.put("cn", "专业");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "bjmc");
		map.put("cn", "班级");
		topTr.add(map);

		return topTr;
	}
}
