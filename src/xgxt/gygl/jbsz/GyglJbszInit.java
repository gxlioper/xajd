package xgxt.gygl.jbsz;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;

public class GyglJbszInit {

	/**
	 * 公寓基本设置设置_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getGyjbszRForm(RequestForm rForm, GyglJbszForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "gyjbsz";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_jbsz_gyjbsz.do";
		// 表
		String tableName = "xg_view_xsxx_zdszb";

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);

	}
}
