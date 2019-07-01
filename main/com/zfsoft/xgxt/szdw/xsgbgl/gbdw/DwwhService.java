/**
 * @部门:学工产品事业部
 * @日期：2013-8-12 上午10:06:05 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.gbdw;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部管理 队伍维护
 * @作者： zhangjw
 * @时间： 2013-8-12 上午10:05:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DwwhService extends SuperServiceImpl<DwwhForm,DwwhDAO>{

	private DwwhDAO dao = new DwwhDAO();
	
	/*
	 * 根据id获取班级干部
	 */
	public List<HashMap<String , String>> getZwListByBjdm(String bjdm) throws Exception{
		return dao.getZwListByBjdm(bjdm);
	}
	
	public DwwhService() {
		super.setDao(dao);
	}
	
	
	public boolean getZfmcExits(DwwhForm from){
		String num = dao.getZfmcExits(from);
		if(num.equals("0")){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @描述:根据学号查询学生当前担任的干部职务
	 * @作者：cq [工号：785]
	 * @日期：2014-8-25 下午02:00:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZwForXh(String xh){
		return dao.getZwForXh(xh);
	}
	
	/**
	 * 根据学年区间查询职务（例如：2013.09.01 - 2014.06.30期间担任职务）
	 */
	public List<HashMap<String , String>> getZwForXhXn(String xh, String xn){
		return dao.getZwForXhXn(xh, xn);
	}
	
	/**
	 * @描述:根据学号查询学生当前担任的所有职务
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-13 下午03:35:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String , String>> 返回类型
	 */
	public List<HashMap<String , String>> getAllZwListByXh(String xh){
		return dao.getAllZwListByXh(xh);
	}
	
	/**
	 * 
	 * @描述:获取学生曾经担任的职务
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-6-11 下午02:14:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCrZwForXh(String xh){
		return dao.getCrZwForXh(xh);
	}
	
	/**
	 * 
	 * @描述:获取学生所有担任的职务
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-6-11 下午02:14:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZwxx(String xh){
		return dao.getZwxx(xh);
	}
	
	
	public List<HashMap<String,String>> getXsxxList(DwwhForm myForm) throws Exception {
		return dao.getXsxxList(myForm);
	}

	public boolean save(DwwhForm myForm) throws Exception {
		List<DwwhForm> list = new ArrayList<DwwhForm>(5);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String[] zwids = myForm.getZwids();
		String[] xh = myForm.getXhs();
		DwwhForm form;
		String lrsj = sdf.format(now);
		for(int i=0;i<zwids.length;i++){
			if(!StringUtil.isNull(xh[i])){
				form = new DwwhForm();
				form.setZwid(zwids[i]);
				form.setXh(xh[i]);
				form.setLrsj(lrsj);
				list.add(form);
			}
		}

		boolean flag = false;
		for(int i=0;i<list.size();i++){
			flag = dao.insert(list.get(i));
		}

		return flag;
	}

	public HashMap<String,String> getBjxx(String bjdm){
		return dao.getBjxx(bjdm);
	}

	public List<HashMap<String,String>> getBgbData(String bjdm){
		return dao.getBgbData(bjdm);
	}

	public boolean update(DwwhForm myForm) throws Exception {
		boolean flag = delete(myForm.getBjdm());
		if(flag){
			flag = save(myForm);
		}
		return flag;
	}

	public boolean delete(String bjdm) throws Exception {
		return dao.delete(bjdm);
	}

	public boolean batchDelete(String[] bjdms) throws Exception {
		return dao.batchDelete(bjdms);
	}

	public List<HashMap<String,String>> export(DwwhForm model, User user)throws Exception {
		return dao.export(model,user);
	}
}
