/**
 * @部门:学工产品事业部
 * @日期：2013-6-4 下午05:06:08 
 */  
package com.zfsoft.xgxt.szdw.fdyrz;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.cssz.CsszUtil;
import com.zfsoft.xgxt.szdw.cssz.SzdwCsszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块 
 * @类功能描述: TODO 辅导员任职管理 任职申请
 * @作者： zhangjw 
 * @时间： 2013-6-4 下午04:56:01 
 * @版本： V5.8.16
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class FdyrzsqService extends SuperServiceImpl<FdyrzsqForm, FdyrzsqDAO>{

	private FdyrzsqDAO dao = new FdyrzsqDAO();
	
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	
	public FdyrzsqService(){
		super.setDao(dao);
	}
	
	public HashMap<String,String> getFdyjbxx(String zgh){
		return dao.getFdyxx(zgh);
	}
	/**
	 * @描述:TODO查询班级信息
	 * @作者：zhangjw
	 * @日期：2013-6-6 上午10:48:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @param bjlist
	 * @param type
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjxxBybjdm(String bjdm,List<HashMap<String,String>> bjlist,String type){
		if(type !=null){
			if(type.equals("sq")){
				HashMap<String,String> map = null;//new BjglService().getBjxxBybjdm(bjdm);
				bjlist.add(map);
			}else{
				Iterator<HashMap<String,String>> ite = bjlist.iterator();
				while(ite.hasNext()){
					HashMap<String,String> xmap = ite.next();
					if(bjdm.equals(xmap.get("bjdm"))){
						ite.remove();
					}
				}
			}
		}else{
			bjlist = new ArrayList<HashMap<String,String>>();
		}
		return bjlist;
	}
	/**
	 * @描述:
	 * @作者：zhangjw
	 * @日期：2013-6-6 下午02:09:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param bjList
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean fdyrzsq(FdyrzsqForm myForm,User user) throws Exception{
		String guid = UniqID.getInstance().getUniqIDHash();
		myForm.setSqid(guid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		myForm.setSqsj(sdf.format(new Date()));
		if(SUBMIT.equalsIgnoreCase(myForm.getType())){
			myForm.setShzt(Constants.YW_SHZ);
		}else{
			myForm.setShzt(Constants.YW_WTJ);
		}
		
		//获取审批流程
		SzdwCsszService cssz = new SzdwCsszService();
		String splc =cssz.getModel(CsszUtil.SZDW_FDYRZSQ).getSplc();
		myForm.setSplc(splc);
		
		boolean result = super.runInsert(myForm);
		dao.updateFdyxxZjz(myForm.getZgh(),myForm.getZjz());
		if(result && SUBMIT.equalsIgnoreCase(myForm.getType())){
			result = shlc.runSubmit(myForm.getSqid(),splc,myForm.getZgh(),"szdw_fdyrz_sh.do?method=gjcxRzsh","szdw_fdyrz_sq.do?method=gjcxWdsq");
		}
		return result;
	}
	/**
	 * @描述:获取班级列表
	 * @作者：zhangjw
	 * @日期：2013-6-6 下午02:11:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * List<String> 返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<String> getBjList(HttpServletRequest request){
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("bjList");
		List<HashMap<String,String>> bjlist =  new ArrayList<HashMap<String,String>>();
		List<String> bjList = new ArrayList<String>();
		if(obj!=null && obj instanceof List){
			bjlist =(List<HashMap<String,String>>) obj;
			Iterator<HashMap<String,String>> ite = bjlist.iterator();
			while(ite.hasNext()){
				HashMap<String,String> map = ite.next();
				bjList.add(map.get("bjdm"));
			}
		}
		return bjList;
	}
	/**
	 * @描述:取消申请
	 * @作者：zhangjw
	 * @日期：2013-6-8 下午05:04:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param spids
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean fdyrzqxsq(String[] spids) throws SQLException{
		if(spids == null){
			return false;
		}else{
			int[] result = dao.updateFdyrzsq(spids);
			for (int i = 0; i < result.length; i++) {
				if(result[i]!=-2){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * @描述:验证基本参数
	 * @作者：zhangjw
	 * @日期：2013-7-4 下午02:41:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * JSONObject 返回类型
	 */
	public JSONObject yzcssz(FdyrzsqForm myForm) throws Exception{
		Map<String,String> resultmap = new HashMap<String,String>();
		SzdwCsszService cssz = new SzdwCsszService();
		String message = cssz.yzCssz(CsszUtil.SZDW_FDYRZSQ);
		if(message.equals("true")){
			int count = dao.getFdyrzsqCount(myForm.getZgh());
			if(count>0){
				message=MessageUtil.getText("szdw_fdyrz_sqyz");
			}
		}
		resultmap.put("message", message);
		return JSONObject.fromObject(resultmap); 
	}
	
	/**
	 * 
	 * @描述:TODO(只有刚提交并且第一级未审核的前提下，申请人可以撤销)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-10 上午08:50:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	public boolean updateFdyrzsq(FdyrzsqForm model) throws Exception{
		return dao.updateFdyrzsq(model)>0?true:false;
	}
	
	/**
	 * 
	 * @描述:TODO(提交功能)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-10 上午09:32:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitFdyrzsq(FdyrzsqForm myForm) throws Exception{
		
		if(!Constants.YW_YTH.equalsIgnoreCase(myForm.getShzt())){
			//获取审批流程
			SzdwCsszService cssz = new SzdwCsszService();
			String splc =cssz.getModel(CsszUtil.SZDW_FDYRZSQ).getSplc();
			myForm.setSplc(splc);
		}
		
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.updateFdyrzsq(myForm)>0?true:false;
		//dao.updateFdyxxZjz(myForm.getZgh(),myForm.getZjz());
		if(result){
			//保存审核流程
			result = shlc.runSubmit(myForm.getSqid(),myForm.getSplc(),myForm.getZgh(),"szdw_fdyrz_sh.do?method=gjcxRzsh","szdw_fdyrz_sq.do?method=gjcxWdsq");
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:TODO(更新辅导员入职申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-31 下午04:22:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateFdyrzsq(FdyrzsqForm myForm,User user) throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		myForm.setSqsj(sdf.format(new Date()));
		
		if(SUBMIT.equalsIgnoreCase(myForm.getType())){
			//获取审批流程
			SzdwCsszService cssz = new SzdwCsszService();
			String splc =cssz.getModel(CsszUtil.SZDW_FDYRZSQ).getSplc();
			if(!Constants.YW_YTH.equals(myForm.getShzt())){
				myForm.setSplc(splc);
			}
			myForm.setShzt(Constants.YW_SHZ);
		}else{
			myForm.setShzt(Constants.YW_WTJ);
		}
		//获取审批流程
		//SzdwCsszService cssz = new SzdwCsszService();
		//String splc =cssz.getModel(CsszUtil.SZDW_FDYRZSQ).getSplc();
		//myForm.setSplc(splc);
		boolean result = super.runUpdate(myForm);
		dao.updateFdyxxZjz(myForm.getZgh(),myForm.getZjz());
		if(result && SUBMIT.equalsIgnoreCase(myForm.getType())){
			result = shlc.runSubmit(myForm.getSqid(),myForm.getSplc(),myForm.getZgh(),"szdw_fdyrz_sh.do?method=gjcxRzsh","szdw_fdyrz_sq.do?method=gjcxWdsq");
		}
		return result;
		
	}
	
	
	/**
	 * 
	 * @描述:验证单独时间
	 * @作者：cq [工号：785]
	 * @日期：2014-7-8 下午04:28:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * JSONObject 返回类型 
	 * @throws
	 */
	public JSONObject yzsqTime() throws Exception{
		Map<String,String> resultmap = new HashMap<String,String>();
		SzdwCsszService cssz = new SzdwCsszService();
		String message = cssz.yzCssz(CsszUtil.SZDW_FDYRZSQ);
		resultmap.put("message", message);
		return JSONObject.fromObject(resultmap); 
	}
	/**
	 * 
	 * @描述:时间开关验证
	 * @作者：cq [工号：785]
	 * @日期：2014-5-27 上午11:04:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * JSONObject 返回类型 
	 * @throws
	 */
	public JSONObject timeSwitch(FdyrzsqForm myForm) throws Exception{
		Map<String,String> resultmap = new HashMap<String,String>();
		SzdwCsszService cssz = new SzdwCsszService();
		String message = cssz.yzCssz(CsszUtil.SZDW_FDYRZSQ);
		resultmap.put("message", message);
		return JSONObject.fromObject(resultmap); 
		
	}
}
