package com.zfsoft.xgxt.xszz.xszzbjpy.jglr;

import java.util.HashMap;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class JglrService extends SuperServiceImpl<JglrForm, JglrDao> {

	private JglrDao dao = new JglrDao();

	public JglrService() {
		super.setDao(dao);
	}
	
	/**
	 * 获取资助班级评议小组成员信息
	 */
	public HashMap<String,String> queryBjpyxzcy(String xh) throws Exception {
		return dao.queryBjpyxzcy(xh);
	}
	/**
	 * 获取学生信息
	 */
	public HashMap<String,String> queryXsxx(String xh) throws Exception {
		return dao.queryXsxx(xh);
	}
	/**
	 * 保存资助班级评议结果录入
	 */
	public boolean bcJglr(JglrForm model, User user) throws Exception {
		String[] sqrs = model.getSqrs();
		if(sqrs.length == 0){
			return true;
		}
		boolean rs = true;
		for (int i = 0; i < sqrs.length; i++) {
			JglrForm temp = new JglrForm();
			temp.setXn(model.getXns()[i]);
			temp.setXq(model.getXqs()[i]);
			temp.setXmdm(model.getXmdms()[i]);
			temp.setSqr(model.getSqrs()[i]);
			temp.setBjpyr(user.getUserName());
			temp.setYlzd1(model.getYlzd1s()[i]);
			temp.setYlzd2(model.getYlzd2s()[i]);
			temp.setTjzt("0");
			rs = dao.delBjpyxzpy(temp.getXn(), temp.getXq(), temp.getXmdm(), temp.getSqr(), temp.getBjpyr());
			if(rs){
				rs = super.runInsert(temp);
			}
		}
		return rs;
	}
	
	public HashMap<String,String> getPyxx(JglrForm model){
		return dao.getPyxx(model);
	}
	
	
}
