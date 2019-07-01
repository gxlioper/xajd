/**
 * @部门:学工产品事业部
 * @日期：2016-2-22 上午11:19:50 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.sq;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
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

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-2-22 上午11:19:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YpzlSqService extends SuperServiceImpl<YpzlSqForm, YpzlSqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private YpzlSqDao dao = new YpzlSqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	/**
	 * 
	 * @描述:得到学期名称
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-23 下午03:28:54
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
	 * @描述:判断同一学年内是否有申请记录
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-23 下午03:34:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isHaveRecord(YpzlSqForm form){
		return dao.isHaveRecord(form);
	}
	
	/** 
	 * @描述:保存增加结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-23 下午03:39:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSqjg(YpzlSqForm model, User user) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		String splc = dao.getShlcID();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "zxdk_ypzl_ypzlsh.do", "zxdk_ypzl_ypzlsq.do");
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * 保存修改结果
	 */
	public boolean saveSqjgUpdate(YpzlSqForm model, User user) throws Exception {
		boolean result = false;
		// 如果提交，插入审核状态
		if ("updatesubmit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				String splc = dao.getShlcID();
				model.setSplc(splc);
				result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "zxdk_ypzl_ypzlsh.do", "zxdk_ypzl_ypzlsq.do");
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
	public boolean submitYpzlsq(YpzlSqForm model) throws Exception {
			boolean result = false;
			model.setShzt(Constants.YW_SHZ);// 审核中
			String splc = dao.getShlcID();
			model.setSplc(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getXh(), "zxdk_ypzl_ypzlsh.do", "zxdk_ypzl_ypzlsq.do");
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
	
	
	/** 
	 * @描述:得到下载的word文件
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-26 上午08:40:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @return
	 * File 返回类型 
	 * @throws 
	 */
	public File getWord(Map<String, Object> data) throws FileNotFoundException{
		//String templatePath = Constants.TEP_DIR+"zxdk/ypzl.xml";
		String templatePath = Constants.TEP_DIR+"zxdk/ypzl_"+Base.xxdm+".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "ypzl_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
						
		}catch (Exception e) {
					
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "ypzl.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
		}

		return wordFile;
	}
	
	/** 
	 * @描述:打印
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-26 下午03:20:14
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
	 * @描述:得到用途类别
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-4 上午10:56:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYtlbList(){
		return dao.getYtlbList();
	}
	
	/** 
	 * @描述:得到用途名称
	 * @作者：柳俊[工号：982]
	 * @日期：2016-3-4 上午10:57:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ytdm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getYtmc(String ytdm){
		return dao.getYtmc(ytdm);
	}
	
	
	
}

