package xgxt.qgzx.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.User;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.dao.XsgwglDAO;
import xgxt.qgzx.form.QgzxForm;
import xgxt.qgzx.hngydx.service.HngydxXsqgzxService.saveFreeTime;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学岗位发布管理Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2010-12-07</p>
 */
public class QgzxGwfbService {
	QgzxDao dao = new QgzxDao();
	
	/**
	 * 初始化下拉列表数据
	 * @param type
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getSelectData(String type){
		if("xiaoqu".equalsIgnoreCase(type)){//校区
			return dao.getWhList("dm_zju_xq", "dm", "xqmc", "", "", "");
		}else if("gwxz".equalsIgnoreCase(type)){//岗位性质
			return dao.getWhList("gwxzdmb", "gwxzdm", "gwxzmc", "", "", "");
		}else if("jcfs".equalsIgnoreCase(type)){//计酬方式
			return dao.getWhList("qgzx_jcfsdmb", "jcfsdm", "jcfsmc", "", "", "");
		}
		return new ArrayList<HashMap<String,String>>();
	}
	
	/**
	 * 用人单位数据
	 * @param user
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getYrdwList(User user){
		if(dao.isYrdwUser(user.getUserName())){//用人单位用户
			return dao.getWhList("yrdwdmb", "yrdwdm", "yrdwmc", "", "dlm", user.getUserName());
		}else{
			return dao.getWhList("yrdwdmb", "yrdwdm", "yrdwmc", "", "", "");
		}
	}
	
	/**
	 * 用人单位数据
	 * @param user
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getYrdwListNotOption(User user){
		if(dao.isYrdwUser(user.getUserName())){//用人单位用户
			return dao.getWhList("yrdwdmb", "yrdwdm", "yrdwmc", "", "dlm", user.getUserName(), false);
		}else{
			return dao.getWhList("yrdwdmb", "yrdwdm", "yrdwmc", "", "", "", false);
		}
	}
	
	/**
	 * 根据岗位名称获取最近的上报时间
	 * @param gwdm
	 * @return String
	 * */
	public String getZjgwsbsj(String gwdm){
		return dao.queryZjgwsbsj(gwdm);
	}
}
