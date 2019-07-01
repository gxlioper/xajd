package com.zfsoft.xgxt.xpjpy.pjpybjpy.jglr;

import java.util.HashMap;

import org.apache.struts.action.ActionForward;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jcsz.JcszForm;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jcsz.JcszService;


public class JglrService extends SuperServiceImpl<JglrForm, JglrDao> {

	private JglrDao dao = new JglrDao();

	public JglrService() {
		super.setDao(dao);
	}
	
	/**
	 * 获取评奖班级评议小组成员信息
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
	 * 保存评奖班级评议结果录入
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
	/**
	 * 
	 * @描述:班级评优权限
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-1-4 上午09:36:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean pjbjPyqx(User user) throws Exception{
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		HashMap<String,String> bjpyxzcyMap = dao.queryBjpyxzcy(user.getUserName());
		if(jcszModel == null||bjpyxzcyMap.get("tjzt") == null||"0".equals(bjpyxzcyMap.get("tjzt"))){
			return false;
		}
		return true;
	}
}
