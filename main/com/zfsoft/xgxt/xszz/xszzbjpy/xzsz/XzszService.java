package com.zfsoft.xgxt.xszz.xszzbjpy.xzsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class XzszService extends SuperServiceImpl<XzszForm, XzszDao> {

	private XzszDao dao = new XzszDao();

	public XzszService() {
		super.setDao(dao);
	}
	/**
	 * 增加资助班级评议小组设置
	 */
	public List<HashMap<String, String>> getAddXzszList(XzszForm t, User user) throws Exception {
		return dao.getAddXzszList(t, user);
	}
	
	/**
	 * 保存增加资助班级评议小组设置
	 */
	public boolean addXzszBc(String[] xhs, String bjdm, User user) throws Exception {
		boolean rs = true;
		for (String xh : xhs) {
			XzszForm model = new XzszForm();
			model.setBjdm(bjdm);
			model.setXh(xh);
			model.setSfxsdb("0");
			model.setTjr(user.getUserName());
			model.setTjsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			model.setTjzt("0");
			if(rs){
				rs = super.runInsert(model);
			}
		}
		if(rs){
			rs = dao.sftjNotAll(bjdm);
		}
		return rs;
	}
	/**
	 * 修改资助班级评议小组设置
	 */
	public List<HashMap<String, String>> getUpdateXzszList(XzszForm t, User user) throws Exception {
		return dao.getUpdateXzszList(t, user);
	}
	
	/**
	 * 重置整个班级的提交状态
	 */
	public boolean sftjNotAll(String bjdm) throws Exception {
		return dao.sftjNotAll(bjdm);
	}
	/**
	 * 提交整个班级
	 */
	public boolean sftjYesAll(String bjdm, User user) throws Exception {
		return dao.sftjYesAll(bjdm, user);
	}
	/**
	 * 保存设置学生代表
	 */
	public boolean xsdbBc(String[] guids, String bjdm) throws Exception {
		return dao.xsdbBc(guids, bjdm);
	}
	/**
	 * 删除学生代表
	 */
	public int xzszDel(String[] guids) throws Exception {
		boolean rs = dao.delBjpyxzpy(guids);
		if(rs){
			int num = dao.runDelete(guids);
			return num;
		}
		return 0;
	}
	
}
