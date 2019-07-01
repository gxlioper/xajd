/**
 * @部门:学工产品事业部
 * @日期：2016-2-29 下午02:08:47 
 */  
package com.zfsoft.xgxt.gygl.zzdgl.sq;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.gygl.zzdgl.cssz.CsszDao;
import com.zfsoft.xgxt.zxdk.ypzl.sq.YpzlSqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-2-29 下午02:08:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzdsqService extends SuperServiceImpl<ZzdsqForm, ZzdsqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	//private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ZzdsqDao dao = new ZzdsqDao();
	private CsszDao csszDao = new CsszDao();
	
	
	/** 
	 * @描述:判断是否住宿
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-29 下午02:46:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean iszhusu(ZzdsqForm form){
		return dao.iszhusu(form);
	}
	
	/** 
	 * @描述:得到学期名称
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-29 下午02:47:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqdm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getXqmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
	
	/** 
	 * @描述:验证是否有申请记录
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-29 下午05:17:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isHaveRecord(ZzdsqForm form) throws Exception{
		return dao.isHaveRecord(form);
	}
	
	/** 
	 * @描述:保存结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-29 下午05:26:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSqjg(ZzdsqForm model, User user) throws Exception {
		String zzdid = UniqID.getInstance().getUniqIDHash();
		model.setZzdid(zzdid);
		String sqid = model.getZzdid();		
		String splc = csszDao.getModel().getSplcid();
		model.setSplcid(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "xgygl_zzdgl_zdsh.do", "xgygl_zzdgl_zdsq.do");
			}
		}
		return flag;
	}
	
	
	/** 
	 * @描述:保存修改
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-29 下午05:28:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSqjgUpdate(ZzdsqForm model, User user) throws Exception {
		boolean result = false;
		// 如果提交，插入审核状态
		if ("updatesubmit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				String splc = csszDao.getModel().getSplcid();
				model.setSplcid(splc);
				result = shlc.runSubmit(model.getZzdid(), model.getSplcid(), model.getXh(), "xgygl_zzdgl_zdsh.do", "xgygl_zzdgl_zdsq.do");
			}
		} else {
			result = runUpdate(model);
		}
		
		return result;
	}
	
	/**
	 * 
	 * 提交
	 */
	public boolean submiZzdsq(ZzdsqForm model) throws Exception {
			boolean result = false;
			model.setShzt(Constants.YW_SHZ);// 审核中
			String splc = csszDao.getModel().getSplcid();
			model.setSplcid(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getZzdid(), splc, model.getXh(), "xgygl_zzdgl_zdsh.do", "xgygl_zzdgl_zdsq.do");
			}
			return result;
	}
	
	/**
	 * 
	 * 撤销
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	public boolean isGlyqr(ZzdsqForm form){
		return dao.isGlyqr(form);
	}
	
	/**
	 * 
	 * 得到原寝室
	 */
	public String getQxmForPrint(String xh){
		return dao.getQxmForPrint(xh);
	}
	
	/** 
	 * @描述:打印
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-3 下午06:16:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xsjbxx
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws 
	 */
	public File printForWord(HashMap<String, String> xsjbxx) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		if(xsjbxx == null){
			xsjbxx = new HashMap<String, String>();
		}
		data.putAll(xsjbxx);
		return getWord(data);
	}
	
	/** 
	 * @描述:下载word文件
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-3 下午06:17:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @return
	 * @throws FileNotFoundException
	 * File 返回类型 
	 * @throws 
	 */
	public File getWord(Map<String, Object> data) throws FileNotFoundException{
		//String templatePath = Constants.TEP_DIR+"zxdk/ypzl.xml";
		String templatePath = Constants.TEP_DIR+"gygl/gygl_zzd_"+Base.xxdm+".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "gygl", "gygl_zzd_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
						
		}catch (Exception e) {
					
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "gygl", "gygl_zzd_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
		}

		return wordFile;
	}
}
