/**
 * @部门:学工产品事业部
 * @日期：2013-7-24 下午4:16:36 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.cssz.CsszUtil;
import com.zfsoft.xgxt.szdw.cssz.SzdwCsszForm;
import com.zfsoft.xgxt.szdw.cssz.SzdwCsszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:辅导员培训申请 service
 * @作者： zhangjw
 * @时间： 2013-7-24 下午4:15:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdypxXmsqService extends SuperServiceImpl<FdypxXmsqForm, FdypxXmsqDAO> {

	private FdypxXmsqDAO dao = new FdypxXmsqDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	
	public FdypxXmsqService() {
		// TODO 自动生成方法存根
		super.setDao(dao);
	}
	/**
	 * @描述:辅导员培训申请
	 * @作者：zhangjw
	 * @日期：2013-7-25 下午2:00:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean fdypxxmsq(FdypxXmsqForm myForm) throws Exception{
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
		String splc =cssz.getModel(CsszUtil.SZDW_FDYPXSQ).getSplc();
		myForm.setSplc(splc);
		boolean result = super.runInsert(myForm);
		if(result&& SUBMIT.equalsIgnoreCase(myForm.getType())){
			result = shlc.runSubmit(myForm.getSqid(),splc,myForm.getFbr(),"szdw_fdypxxmsh.do?method=fdypxxmList","szdw_fdypxxmsq.do?method=fdypxxmsqList");
		}
		return result;
	}
	/**
	 * @描述:验证辅导员培训申请
	 * @作者：zhangjw
	 * @日期：2013-7-25 下午2:11:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * JSONObject 返回类型
	 */
	public JSONObject yzFdypxsq(FdypxXmsqForm myForm) throws Exception{
		Map<String,String> resultmap = new HashMap<String,String>();
		SzdwCsszService cssz = new SzdwCsszService();
		//SzdwCsszForm csszForm =  cssz.getShlcsqkzb(CsszUtil.SZDW_XSGBZWSQ);
		SzdwCsszForm csszForm =  cssz.getShlcsqkzb(CsszUtil.SZDW_FDYPXSQ);
		//SzdwCsszForm cssz =
		String message = "true";
		if(csszForm.getSplc()==null || "".equals(csszForm.getSplc())){
			message = MessageUtil.getText("szdw_xsgbgl_zwsqyz");
		}
		if(message.equals("true")){
			//验证培训项目是否已经申请
			int i = dao.getSqCount(myForm.getSqr(),myForm.getXmdm());
			if(i>0){
				message = MessageUtil.getText("szdw_fdypx_sqyz");;
			}
		}
		resultmap.put("message", message);
		return JSONObject.fromObject(resultmap); 
	}
	/**
	 * @描述:取消辅导员培训申请
	 * @作者：zhangjw
	 * @日期：2013-7-25 下午3:15:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param spids
	 * @return
	 * @throws SQLException
	 * boolean 返回类型
	 */
	public boolean fdypxqxsq(String[] spids) throws SQLException{
		if(spids == null){
			return false;
		}else{
			int[] result = dao.updateFdypxsq(spids);
			for (int i = 0; i < result.length; i++) {
				if(result[i]!=-2){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * @描述:根据项目编号查询此项目是否被申请
	 * @作者：zhangjw
	 * @日期：2013-8-5 下午3:07:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws SQLException
	 * int 返回类型
	 */
	public int getSqCountByPxxm(String[] xmdm) throws SQLException{
		StringBuffer sql = new StringBuffer();
		if(xmdm.length>0){
			String tt= "";
			for (int i = 0; i < xmdm.length; i++) {
				tt = "'"+xmdm[i]+"',";
			}
			int m = tt.lastIndexOf(",");
			tt = tt.substring(0,m);
			sql.append(tt);
		}else{
			sql.append("''");
		}
		return dao.getSqCountByPxxm(sql.toString());
	}
	
	
	
	public boolean submitFdypxsq(FdypxXmsqForm myForm) throws Exception{
		
		if(!Constants.YW_YTH.equalsIgnoreCase(myForm.getShzt())){
			
			//获取审批流程
			SzdwCsszService cssz = new SzdwCsszService();
			String splc =cssz.getModel(CsszUtil.SZDW_FDYPXSQ).getSplc();
			myForm.setSplc(splc);
		}
		
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.updateFdypxsq(myForm)>0?true:false;
		if(result){
			result = shlc.runSubmit(myForm.getSqid(),myForm.getSplc(),myForm.getFbr(),"szdw_fdypxxmsh.do?method=fdypxxmList","szdw_fdypxxmsq.do?method=fdypxxmsqList");
		}
		return result;
	}
	
	/**
	 * @描述:辅导员培训申请
	 * @作者：zhangjw
	 * @日期：2013-7-25 下午2:00:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean fdypxxmsqXg(FdypxXmsqForm myForm) throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		myForm.setSqsj(sdf.format(new Date()));
		
		if(SUBMIT.equalsIgnoreCase(myForm.getType())&& !Constants.YW_YTH.equalsIgnoreCase(myForm.getShzt())){
			//获取审批流程
			SzdwCsszService cssz = new SzdwCsszService();
			String splc =cssz.getModel(CsszUtil.SZDW_FDYPXSQ).getSplc();
			myForm.setSplc(splc);
		}
		
		if(SUBMIT.equalsIgnoreCase(myForm.getType())){
			myForm.setShzt(Constants.YW_SHZ);
		}
		
		boolean result = super.runUpdate(myForm);
		if(result&& SUBMIT.equalsIgnoreCase(myForm.getType())){
			result = shlc.runSubmit(myForm.getSqid(),myForm.getSplc(),myForm.getFbr(),"szdw_fdypxxmsh.do?method=fdypxxmList","szdw_fdypxxmsq.do?method=fdypxxmsqList");
		}
		return result;
		
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
	
	public boolean updateFdypxsq(FdypxXmsqForm myForm) throws Exception{
		return dao.updateFdypxsq(myForm)>0?true:false;
	}
	
	/**
	 * 
	 * @描述:判断项目代码是否开启
	 * @作者：cq [工号：785]
	 * @日期：2014-5-29 下午06:25:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean getXmkg(String xmdm){
		
		return Integer.valueOf(dao.getXmkg(xmdm))>0;
	}
	
}
