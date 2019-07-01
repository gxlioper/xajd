/**
 * @部门:学工产品事业部
 * @日期：2017-7-19 上午08:54:03 
 */  
package com.zfsoft.xgxt.dekt.qnzyry;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 青年志愿人员service(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-7-19 上午08:54:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QnzyryService extends SuperServiceImpl<QnzyryForm, QnzyryDao>{
	private static String YTG = "1";
	private static String TH = "2";
	private QnzyryDao dao = new QnzyryDao();
	
	
	/** 
	 * @描述:检验该学生是否已报名该项目(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-19 下午01:44:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param from
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(QnzyryForm from){
		if(dao.countJl(from)>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @throws Exception  
	 * @描述:获取项目人员(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-21 上午11:43:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param hdid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXmryList(QnzyryForm form) throws Exception{
		return dao.getXmryList(form);
	}
	
	/**
	 * @throws SQLException  
	 * @描述:批量审核报名状态(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-21 下午05:28:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @param bmzt
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public String plshBmzt(QnzyryForm form) throws SQLException{
		if(form.getBmzt().equalsIgnoreCase(QnzyryAction.BMSHTG)){
			//获取该项目限定人数和报名已通过人数
			HashMap<String,String> map = dao.countYtgbmAndXdrs(form.getHdid());
			Integer tgs = form.getIds().length;
			//限定人数
			Integer xdrs = Integer.valueOf(map.get("xdrs"));
			//已审核通过的人数
			Integer tgrs = Integer.valueOf(map.get("tgrs"));
			if(tgs+tgrs > xdrs){
				return MessageKey.JHZT_DEKT_BM_OVERSIZE;
			}
		}
		return dao.plshBmzt(form.getIds(), form.getBmzt())?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
	}
	
	/** 
	 * @描述:获取志愿活动报名信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-26 上午10:16:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String,String> getZyhdbmxx(String id){
		return dao.getZyhdbmxx(id);
	}
	
	public boolean upDateRy(QnzyryForm form) throws Exception{
		return dao.upDateRy(form);
	}
	
	/**
	 * @throws SQLException  
	 * @描述:；批量审核(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-26 下午03:59:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plsh(QnzyryForm form) throws SQLException{
		String[] ids = form.getIds();
		String gsshzt = form.getGsshzt();
		String gsshyj = form.getGsshyj();
		//String sftj = form.getSftj();
		String[] fwjgs = form.getFwjg().split("_");
		String js = fwjgs[1];
		List<HashMap<String,String>> fsList = dao.getJbfByIds(ids);
		for(HashMap<String,String> map:fsList){
			String gs = String.valueOf(gsjs(map.get("jbfwgs"),js));
			map.put("gs", gs);
			map.put("gsshzt", gsshzt);
			map.put("gsshyj", gsshyj);
			map.put("fwjg", fwjgs[0]);
		}
		//form.setFwjg(fwjgs[0]);
		return dao.plsh(fsList);
	}
	
	/** 
	 * @描述:获取结果(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-27 上午09:21:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJgPageList(QnzyryForm t, User user) throws Exception{
		return dao.getJgPageList(t, user);
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runUpdate(java.lang.Object)
	 */
	public boolean runUpdate(QnzyryForm t) throws Exception{
		//如果为通过
		if(t.getGsshzt().equals(QnzyryAction.GSSHTG)){
			String[] fwjgs = t.getFwjg().split("_");
			String jbgs = t.getJbfwgs();
			t.setFwjg(fwjgs[0]);
			long jg = gsjs(jbgs,fwjgs[1]);
			t.setGs(String.valueOf(jg));
		}else{
			t.setFwjg(null);
		}
		return super.runUpdate(t);
	}
	
	/** 
	 * @描述:工时计算(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-29 上午11:32:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jbgs
	 * @param js
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public long gsjs(String jbgs,String js){
		int a = Integer.valueOf(jbgs);
		double b = Double.valueOf(js);
		long jg = Math.round(a*b);
		return jg;
		
	}
	
	/** 
	 * @描述:批量撤销(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-30 下午05:35:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plcx(String[] ids) throws Exception{
		//获取对应志愿者活动相应的基本分数
		List<HashMap<String,String>> list = dao.getJbfByIds(ids);
		return dao.plcx(list);
	}
	
	/** 
	 * @描述:计算学分(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-10 下午02:36:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public String countXf(String xh) throws Exception{
		return dao.countTotalXf(xh);
	}
	
	/**
	 * @description	：青年志愿活动评价修改
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-18 上午09:13:08
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean runUpdateForPf(QnzyryForm model) throws Exception{
		return super.runUpdate(model);
	}
}
