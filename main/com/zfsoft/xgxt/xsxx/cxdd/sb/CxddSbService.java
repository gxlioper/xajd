/**
 * @部门:学工产品事业部
 * @日期：2016-3-28 下午05:20:11 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.sb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import xgxt.action.Base;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.cxdd.cssz.CsszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-3-28 下午05:20:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxddSbService extends SuperServiceImpl<CxddSbForm, CxddSbDao> {
	CxddSbDao dao = new CxddSbDao();
//	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	public List<HashMap<String, String>> getXsPageList(CxddSbForm t, User user) 
	throws Exception{
		return dao.getXsPageList(t, user);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:更新班级调整记录，防止班级调班，数据范围为未提交或者已退回的数据
	 * @解释:为了后续扩展功能（类似批量多个班级操作或者不选班级操作）,该方法是传入参数是数组
	 * @作者：yxy[工号：1206]
	 * @日期：2016-4-27 下午02:02:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean UpdateBjtzRecode(String[] bjdms) throws Exception{
		return dao.UpdateBjtzRecode(bjdms);
	}
	
	//提交
	public boolean submitBusi(CxddSbForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		String splc =  new CsszService().getModel().getSplc();
		model.setSplc(splc);
		if(flag){
			 shlc.runSubmit(model.getBjid(), model.getSplc(), model.getXh(), "xsxx_cxdd_pysh.do", "xsxx_cxdd_pysb.do");
		}
		return flag;
	}
	
	//撤销
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	//是否有权限提交
	public boolean isHaveQxTj(String bjdm){
		return dao.isHaveQxTj(bjdm);
	}
	
	public  List<HashMap<String, String>> getCxdjdmList(){
		return dao.getCxdjdmList();
	}
	
	public boolean saveDataXs(CxddSbForm t,String type) throws Exception{
		return dao.saveDataXs(t, type);
	}
	
	//提交时删除学生上报表中不在校冗余数据
	public boolean delCxddbzx(String bjdm) throws Exception{
		return dao.delCxddbzx(bjdm);
	}
}
