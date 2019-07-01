/**
 * @部门:学工产品事业部
 * @日期：2013-7-24 下午4:16:36 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwsq;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import net.sf.json.JSONObject;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.cssz.CsszUtil;
import com.zfsoft.xgxt.szdw.cssz.SzdwCsszForm;
import com.zfsoft.xgxt.szdw.cssz.SzdwCsszService;
import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxDAO;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhDAO;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部职务申请
 * @作者： zhangjw
 * @时间： 2013-8-8 下午2:30:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZwsqService extends SuperServiceImpl<ZwsqForm, ZwsqDAO> {

	private ZwsqDAO dao = new ZwsqDAO();


	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	private ZwwhService zwwhservice = new ZwwhService();
	private ZwlxDAO zwlxdao = new ZwlxDAO();
	public ZwsqService() {
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
	public boolean zwsq(ZwsqForm myForm) throws Exception{
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
//		SzdwCsszService cssz = new SzdwCsszService();
		//------------审批流设置
		//String splc =cssz.getModel(CsszUtil.SZDW_FDYPXSQ).getSplc();
	
		String lxdm = zwwhservice.getModel(myForm.getZwid()).getLxdm();
		String splc = zwlxdao.getModel(lxdm).getSplc();
		myForm.setSplc(splc);
		boolean result = super.runInsert(myForm);
		if(result&& SUBMIT.equalsIgnoreCase(myForm.getType())){
			result = shlc.runSubmit(guid,splc,myForm.getXh(),"szdw_zwsh.do?method=zwshList","szdw_zwsq.do?method=zwsqList");
		}
		return result;
	}
	/**
	 * @描述:验证班干部职务申请
	 * @作者：zhangjw
	 * @日期：2013-8-9 下午3:44:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * JSONObject 返回类型
	 */
	public JSONObject yzZwsq(ZwsqForm myForm) throws Exception{
		Map<String,String> resultmap = new HashMap<String,String>();
		SzdwCsszService cssz = new SzdwCsszService();
		SzdwCsszForm csszForm =  cssz.getShlcsqkzb(CsszUtil.SZDW_XSGBZWSQ);
		
		String message = "true";
		
		if(myForm.getType()==null){
			if(csszForm.getSplc()==null || "".equals(csszForm.getSplc())){
				message = "false";
			}
			if(message.equals("true")){
				int i = dao.getSqCount(myForm.getXh(),myForm.getZwid());
				if(i>0){
					message = MessageUtil.getText("szdw_xsgbgl_zwsq");
				}
			}else{
				message = MessageUtil.getText("szdw_xsgbgl_zwsqyz");;
			}
		}
		resultmap.put("message", message);
		return JSONObject.fromObject(resultmap); 
	}
	/**
	 * @描述:取消申请
	 * @作者：zhangjw
	 * @日期：2013-8-8 下午2:38:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param spids
	 * @return
	 * @throws SQLException
	 * boolean 返回类型
	 */
	public boolean qxsq(String[] spids) throws SQLException{
		if(spids == null){
			return false;
		}else{
			int[] result = dao.updateSq(spids);
			for (int i = 0; i < result.length; i++) {
				if(result[i]!=-2){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * @描述:根据职务代码是否被申请
	 * @作者：zhangjw
	 * @日期：2013-8-8 下午2:36:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zwid
	 * @return
	 * @throws SQLException
	 * int 返回类型
	 */
	public int getSqCountByZwid(String[] zwid) throws SQLException{
		StringBuffer sql = new StringBuffer();
		if(zwid.length>0){
			String tt= "";
			for (int i = 0; i < zwid.length; i++) {
				tt = "'"+zwid[i]+"',";
			}
			int m = tt.lastIndexOf(",");
			tt = tt.substring(0,m);
			sql.append(tt);
		}else{
			sql.append("''");
		}
		return dao.getSqCountByZwid(sql.toString());
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
	
	public boolean updateZwsq(ZwsqForm model) throws Exception{
		return dao.updateZwsq(model)>0?true:false;
	}
	
	public boolean submitZwsq(ZwsqForm myForm) throws Exception{
		
		if(!Constants.YW_YTH.equalsIgnoreCase(myForm.getShzt())){
			//获取审批流程
			String zwid = new ZwsqDAO().getModel(myForm.getSqid()).getZwid();
			String lxdm = zwwhservice.getModel(zwid).getLxdm();
			String splc = zwlxdao.getModel(lxdm).getSplc();
			myForm.setSplc(splc); 
		}
		
		
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.updateZwsq(myForm)>0?true:false;
		if(result){
			result = shlc.runSubmit(myForm.getSqid(),myForm.getSplc(),myForm.getXh(),"szdw_zwsh.do?method=zwshList","szdw_zwsq.do?method=zwsqList");
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:TODO(修改学生干部职务申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-2 下午05:48:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean zwsqXg(ZwsqForm myForm) throws Exception{
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		myForm.setSqsj(sdf.format(new Date()));
		if(!Constants.YW_YTH.equalsIgnoreCase(myForm.getShzt())&&SUBMIT.equalsIgnoreCase(myForm.getType())){
			//获取审批流程
			String lxdm = zwwhservice.getModel(myForm.getZwid()).getLxdm();
			String splc = zwlxdao.getModel(lxdm).getSplc();
			myForm.setSplc(splc);
		}
		
		if(SUBMIT.equalsIgnoreCase(myForm.getType())){
			myForm.setShzt(Constants.YW_SHZ);
		}
		
		boolean result = super.runUpdate(myForm);
		if(result&& SUBMIT.equalsIgnoreCase(myForm.getType())){
			result = shlc.runSubmit(myForm.getSqid(),myForm.getSplc(),myForm.getXh(),"szdw_zwsh.do?method=zwshList","szdw_zwsq.do?method=zwsqList");
		}
		return result;
		
	}
	
	/**
	 * @描述:获取文档
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-13 下午03:35:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @return File 返回类型
	 * @throws
	 */
	public File getWord(Map<String, Object> data) throws FileNotFoundException {
//		File wordFile = FreeMarkerUtil.getWordFile(data,
//				"classpath://templates//xszz", "knsrdsq.xml");
		String templatePath = Constants.TEP_DIR+"szdw/zwsq_"+Base.xxdm+".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "szdw", "zwsq_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			
		}catch (Exception e) {
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "szdw", "zwsq.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
		}

		return wordFile;
	
	}
}
