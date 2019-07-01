package xsgzgl.xtwh.general.qxgl.yhgl;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;
import xgxt.form.User;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_权限管理_用户管理_init类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author zhanghui
 * @version 1.0
 */
public class YhglNewInit {
	
	public void initSearch(RequestForm rForm, YhglNewForm myForm, User user,
			HttpServletRequest request){
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xtwh_qxgl_yhglManage.do";
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setGnmk("xtwh");
		rForm.setMenu("qxgl");
	}
}
