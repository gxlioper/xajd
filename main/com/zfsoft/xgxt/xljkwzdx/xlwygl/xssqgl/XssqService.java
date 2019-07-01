/**
 * @部门:学工产品事业部
 * @日期：2014-5-23 上午10:02:52 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xssqgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生授权管理
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-23 上午10:02:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XssqService extends SuperServiceImpl<XssqForm, XssqDao> {

	public XssqService(){
		setDao(new XssqDao());
	}
	
	/**
	 * 
	 * @描述:删除
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-26 上午10:00:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pks
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public int del(String[] pks){
		if(pks == null || pks.length == 0){
			return 0;
		}
		List<String[]> inptVal = new ArrayList<String[]>();
		for (String s : pks) {
			String xh = s.split("@@")[0];
			String lx = s.split("@@")[1];
			inptVal.add(new String[]{xh , lx});
		}
		try {
			int[] v = dao.del(inptVal);
			return pks.length;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 
	 * @描述:查询
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-26 上午10:54:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getModelData(String xh , String lx){
		return dao.getModelData(xh, lx);
	}
	
	
	/**
	 * 
	 * @描述:查询
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-26 上午10:54:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public XssqForm getModel(String xh , String lx){
		HashMap<String,String> data = dao.getModelData(xh, lx);
		XssqForm model = new XssqForm();
		model.setLx(data.get("lx"));
		model.setRzjsrq(data.get("rzjsrq"));
		model.setRzksrq(data.get("rzksrq"));
		model.setSfxypssb(data.get("sfxypssb"));
		model.setXh(data.get("xh"));
		return model;
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @描述:检查数据库是否存在
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-26 上午10:54:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public boolean checkExist(String xh , String lx) throws SQLException{
		int i = dao.checkExist(xh, lx);
		return i >= 1;
	}
	
	/**
	 * 1.是否为班级心理委员
	 * 2.楼长/层长
	 * 3.授权学生
	 * @描述:获取学生授权情况
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-27 上午08:33:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> xssqCheck(String xh){
		return dao.xssqCheck(xh);
	}
	
	/**
	 * 
	 * @描述:更新
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-26 上午11:25:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateModel(XssqForm  model) throws Exception{
		return dao.updateModel(model);
	}
}
