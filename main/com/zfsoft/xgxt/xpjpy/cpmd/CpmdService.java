/**
 * @部门:学工产品事业部
 * @日期：2013-7-20 下午01:34:47 
 */  
package com.zfsoft.xgxt.xpjpy.cpmd;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优2013版-参评学生名单
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-20 下午01:34:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CpmdService extends SuperServiceImpl<CpmdModel, CpmdDao> {
	
	private CpmdDao dao = new CpmdDao();
	
	//提交状态
	public static final String WTJ = "0";	//未提交
	public static final String YTJ = "1";	//已提交
	public static final String QXTJ = "2";	//取消提交

	
	public CpmdService(){
		super.setDao(dao);
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cq [工号：785]
	 * @日期：2013-7-23 下午02:16:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getTzjlList(CpmdModel model, User user) throws Exception {
		return dao.getTzjlList(model, user);
	}

	/**
	 * @throws Exception 
	 * @param user  
	 * @描述: 批量取消参评
	 * @作者：CQ [工号：785]
	 * @日期：2013-7-24 下午02:46:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param values
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean qxcp(String values, User user) throws Exception {

		if(values == null || "".equalsIgnoreCase(values)){
			logger.error("未选择取消参评人员！");
			throw new NullPointerException();
		}
		
			boolean qxcp = false;
		
			//插入调整记录
			qxcp=dao.insertTzjl(values,user);
		
			if(!qxcp){
				return false;
			}
			
			//更新评奖人员库
			qxcp=dao.updateCpmd(values,user);
			
			//学期综测，自动更新学年综测,如果是学年综测，则不更新
			if(CsszService.getZczq()){
				dao.updateXncpmd(values,user);
			}
		
			return qxcp;
	}

	/**
	 * @throws Exception  
	 * @描述:将学生从一个班级调整到另一个班级
	 * @作者：cq [工号：785]
	 * @日期：2013-7-26 上午10:44:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @param tzhbjdm
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean bjtz(String tzhbjdm, User user,String xh) throws Exception {
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		String xn = csszModel.getXn();
		String xq = csszModel.getXq();
		
		//判断ID是否在当前周期评奖名单内
		boolean isHavePjry = dao.isHavePjry(xh,xn,xq);
		
		//插入班级调整记录
		if(isHavePjry){
			
			 dao.insertbjTzjl(xh,xn,xq, tzhbjdm, user);
		}else{
			
			 dao.insertbjTzjl1(xn,xq,xh,tzhbjdm, user);
		}
		 
		//更新班级调整评奖人员库
		 if(isHavePjry){
			//从一个班级调整到另一个班级
			dao.updateBjtzCpmd(xh,xn,xq, tzhbjdm);
		 }else{
			//从不参评调整到参评
			dao.insertBjtzCpmd(xn,xq,xh,tzhbjdm);
		 }
		 
		 //如果是学期综测，自动更新学年综测
		 if(CsszService.getZczq()){
			 dao.updateXnzc(xn,xh);
		 }
		 
		return true;
		
	}
	
	/**
	 * 
	 * @描述:批量调整学生
	 * @作者：cq [工号：785]
	 * @日期：2015-2-12 下午02:46:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tzhbjdm
	 * @param user
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean bjtzs(String tzhbjdm, User user,String ids) throws Exception {
		
		String [] id = ids.split(",");
		
		//没有单独写方法，循环以前的
		for (int i = 0; i < id.length; i++) {
			bjtz(tzhbjdm, user, id[i]);
		}
		
		return true;
	}


	/** 
	 * @描述:返回调整信息
	 * @作者：cq [工号：785]
	 * @日期：2013-7-31 上午08:51:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getTzxx(String xh) {
		
		if (StringUtil.isNull(xh)){
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		return dao.getTzxx(xh);
	}

	/** 
	 * @描述:判断当前评奖周期内是否有参评人员
	 * @作者：cq [工号：785]
	 * @日期：2013-7-31 下午02:39:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean getSfcz() {
		
		return dao.getSfcz();
		
	}

	/**
	 * @throws Exception  
	 * @描述:评奖人员库执行初始化操作
	 * @作者：cq [工号：785]
	 * @日期：2013-7-31 下午02:55:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws 
	 */
	public void init() throws Exception {
		
		if(dao.initDel()){
			dao.init();
		}
		
	}
	
	/**
	 * 
	 * @描述:学期综测，自动初始化学年参评人数
	 * @作者：cq [工号：785]
	 * @日期：2014-8-5 下午03:51:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void xnInit() throws Exception {
		dao.xnInit();
	}

	
	/**
	 * 
	 * @描述: 查询增加参评学生列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 上午11:30:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getAddCpxsList(CpmdModel model) throws Exception{
		return dao.getAddCpxsList(model);
	}
	
	/** 
	 * @描述: 获取班级人数
	 * @作者：江水才 [工号：1150]
	 * @日期：2014-9-29 上午10:25:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getBjrs(String bjdm, String xn, String xq){
		return dao.getBjrs(bjdm, xn, xq);
	}
	
	/**
	 * 
	 * @描述:获取参评组人数
	 * @作者：taogj[工号：1075]
	 * @日期：2017-10-24 下午03:56:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCpzrs(String xh, String xn, String xq){
		return dao.getCpzrs(xh, xn, xq);
	}
	
	/**
	 * 
	 * @描述: 查询参评学生名单
	 * @作者：屈朋辉[工号：445]
	 * @日期：2016年10月14日 下午7:12:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdmArr
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCpmdList(String[] bjdmArr , String xn ,String xq){
		
		if (bjdmArr == null || bjdmArr.length == 0){
			return null;
		}
		return dao.getCpmdList(bjdmArr, xn, xq);
	}
	
}
