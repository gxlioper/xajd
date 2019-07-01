/**
 * @部门:学工产品事业部
 * @日期：2013-12-30 上午10:35:32 
 */  
package com.zfsoft.xgxt.rcsw.jqlx;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqDao;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqDAO;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import org.springframework.util.ResourceUtils;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务-假期留校
 * @类功能描述: 假期留校service
 * @作者： 945
 * @时间： 2013-12-30 上午10:35:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JqlxService extends SuperServiceImpl<JqlxModel, JqlxDao> {
	
	private JqlxDao dao = new JqlxDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public JqlxService() {
		super.setDao(dao);
	}
	
	public JqlxModel getModel(JqlxModel t) throws Exception {
		return dao.getModel(t);
	}
	
	public boolean allowUpdateSetting() {
		try {
			return dao.getDshCount() == 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getSfcfCount(JqlxModel t){
		try {
			return dao.getSfcfCount(t);
		}catch (SQLException e) {
			return -1;
		}
	}
	
	/** 
	 * 床位列表
	 */
	public List<HashMap<String, String>> getCwxxList(JqlxModel myForm, User user) throws Exception{
		return dao.getCwxxList(myForm,user);
	}
	
	/**
	 * 导入数据
	 */
	public String importData(HttpServletRequest request,HttpServletResponse response){
		return dao.importData(request,response);
	}
	/**
	 * 下载导入模版
	 */
	public void downloadXls(JqlxModel t, User user, HttpServletRequest request, WritableWorkbook wwb) throws Exception{
		List<HashMap<String, String>> dataList = getDownloadResultList(t, user);
		WritableSheet ws = wwb.getSheet(0);
		int row = 1;
		// 数据集写入
		for (int m = 0; m < dataList.size(); m++) {
			HashMap<String, String> dataMap = dataList.get(m);
			ws.addCell(new Label(0, row, dataMap.get("xn")));
			ws.addCell(new Label(1, row, dataMap.get("xqmc")));
			ws.addCell(new Label(2, row, dataMap.get("xh")));
			ws.addCell(new Label(3, row, dataMap.get("lxkssj")));
			ws.addCell(new Label(4, row, dataMap.get("lxjzsj")));
			ws.addCell(new Label(5, row, dataMap.get("ldmc")));
			ws.addCell(new Label(6, row, dataMap.get("qsh")));
			ws.addCell(new Label(7, row, dataMap.get("cwh")));
			row++;
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 
	 * @描述:保存假期留校申请
	 * @作者：945
	 * @日期：2013-12-31 下午04:51:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean savaJqlxsq(JqlxModel model) throws Exception {
		
		if(model.getType().equals("submit") && !Constants.YW_YTH.equalsIgnoreCase(model.getSqzt())){
			// 获取新审批流程
			String splc = dao.getShlcID();
			model.setLcid(splc);
		}
		
		//用户新增保存审批增加
		if(StringUtil.isNull(model.getLcid())){
			// 获取新审批流程
			String splc = dao.getShlcID();
			model.setLcid(splc);
			model.setSqzt(Constants.YW_WTJ);
		}
		
		if(model.getType().equals("submit")){
			model.setSqzt(Constants.YW_SHZ);//审核中
		}
		
		boolean insertResult = false;
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		model.setSjlx("1");//工作流
		if(model.getSqid()==null||model.getSqid().equals("")){
			String bbsqid = UniqID.getInstance().getUniqIDHash();
			model.setSqid(bbsqid);
			insertResult = super.runInsert(model);
		}else{
			insertResult = super.runUpdate(model);
		}
		if (insertResult && "submit".equalsIgnoreCase(model.getType())) {
			//保存审核流程
			shlc.runSubmit(model.getSqid(),model.getLcid(),model.getXh(),"rcsw_jqlxsh.do","rcsw_jqlxsq.do");
		}
		return insertResult;
	}
	
	/**
	 * 
	 * @描述:保存假期留校结果维护
	 * @作者：945
	 * @日期：2014-1-3 上午08:54:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean savaJqlxJg(JqlxModel model) throws Exception {
		model.setSqzt(Constants.YW_TG);//pass
		// 获取审批流程
		String splc = dao.getShlcID();
		model.setLcid(splc);
		boolean insertResult = false;
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		model.setSjlx("0");//直接录入

		if(!"10026".equals(Base.xxdm)){
			// =========== 处理床位信息 begin =======
			String cwxx = model.getCwxx();
			if(cwxx!=null && !"".equals(cwxx)){
				String[] cwxxArr = cwxx.split("_");
				YdsqDao ydsqDao = new YdsqDao();
				HashMap<String, String> cwxxMap = ydsqDao.getCwxx(cwxxArr[0], cwxxArr[1], cwxxArr[2]);
				model.setLddm(cwxxMap.get("lddm"));
				model.setQsh(cwxxMap.get("qsh"));
				model.setCwh(cwxxMap.get("cwh"));
			}
			// =========== 处理床位信息 end =======
		}

		if(model.getSqid()==null||model.getSqid().equals("")){
			String bbsqid = UniqID.getInstance().getUniqIDHash();
			model.setSqid(bbsqid);
			insertResult = super.runInsert(model);
		}else{
			insertResult = super.runUpdate(model);
		}
		return insertResult;
	}
	/**
	 * 修改审核流的床位信息
	 */
	public boolean savaJqlxJgShlc(JqlxModel model) throws Exception {
		// =========== 处理床位信息 begin =======
		String cwxx = model.getCwxx();
		if(cwxx!=null && !"".equals(cwxx)){
			String[] cwxxArr = cwxx.split("_");
			YdsqDao ydsqDao = new YdsqDao();
			HashMap<String, String> cwxxMap = ydsqDao.getCwxx(cwxxArr[0], cwxxArr[1], cwxxArr[2]);
			model.setLddm(cwxxMap.get("lddm"));
			model.setQsh(cwxxMap.get("qsh"));
			model.setCwh(cwxxMap.get("cwh"));
		}
		// =========== 处理床位信息 end =======
		return super.runUpdate(model);
	}
	
	/**
	 * 
	 * @描述:删除假期留校申请
	 * @作者：945
	 * @日期：2013-12-31 下午04:51:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] deleteJqlxsq(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//记录删除id
			}else{
				HashMap<String, String> hm=dao.getBbsq(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?jqlxsqDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:"-1";
		return new String[]{String.valueOf(i),str};
	}
	
	public String[] deleteJqlxjg(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.jgCanDel(str)){
				delId.add(str);//记录删除id
			}else{
				HashMap<String, String> hm=dao.getBbsq(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?jqlxsqDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:"-1";
		return new String[]{String.valueOf(i),str};
	}
	
	private int jqlxsqDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}
	
	/**
	 * 
	 * @描述:更新状态
	 * @作者：945
	 * @日期：2013-12-31 下午04:51:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateModel(JqlxModel model) throws Exception {
		return super.runUpdate(model);
	}
	
	/**
	 * 
	 * @描述:提交申请
	 * @作者：945
	 * @日期：2013-12-31 下午04:51:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitSq(JqlxModel model) throws Exception {
		
		//除退回以外都重新获取审批流程
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getSqzt())){
			String lcid = dao.getShlcID();
			model.setLcid(lcid);
		}
		
		model.setShzt(Constants.YW_SHZ);
		boolean resultBbsq = dao.updateSq(model);
		boolean result = false;
		if(resultBbsq){
			result = shlc.runSubmit(model.getSqid(), model.getLcid(),model.getXh(),"rcsw_jqlxsh.do","rcsw_jqlxsq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:申请能否撤销
	 * @作者：945
	 * @日期：2013-12-31 下午04:52:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	/**
	 * 
	 * @描述:审核列表
	 * @作者：945
	 * @日期：2014-1-2 上午10:57:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAuddingList(JqlxModel t, User user) throws Exception {
		return dao.getAuddingList(t, user);
	}
	@TransactionControl
	public String savePlsh(JqlxModel t, User user) throws Exception {
		String[] ids = t.getId();
		String[] gwids = t.getGwids();
		String[] xhs = t.getXhs();
		String[] lcids = t.getLcids();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			JqlxModel model = new JqlxModel();
			model.setSqid(ids[i]);
			model.setGwid(gwids[i]);
			model.setXh(xhs[i]);
			model.setLcid(lcids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());

			boolean isSuccess = singleSh(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}

		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json
					.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}
	
	/**
	 * 
	 * @描述:单个审核保存
	 * @作者：945
	 * @日期：2014-1-2 下午05:37:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean singleSh(JqlxModel form,User user) throws Exception{
		// 审核操作Model初始化
		ShxxModel model = new ShxxModel();
		model.setShlc(form.getLcid());
		model.setShr(user.getUserName());
		model.setShyj(form.getShyj());
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		model.setGwid(form.getGwid());
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("rcsw_jqlxsh.do");
		model.setTzljsq("rcsw_jqlxsq.do");
		
		boolean result = false;
		try {
			String zhzt = shlc.runAuditingNotCommit(model);
			form.setSqzt(zhzt);
			result = dao.runUpdateNotCommit(form);
			// 最后一级审核通过时
			if(result && zhzt.equals(Constants.SH_TG)){
				if(!"10026".equals(Base.xxdm)){
					JqlxModel jgForm = new JqlxModel();
					jgForm.setSqid(form.getSqid());
					WdgwsqDAO wdgwsqDAO = new WdgwsqDAO();
					HashMap<String, String> qsxx = wdgwsqDAO.getQsxx(form.getXh());
					if("10344".equals(Base.xxdm)){
						if(qsxx.get("lddm") != null && qsxx.get("qsh") != null && qsxx.get("cwh") != null){
							jgForm.setLddm(qsxx.get("lddm"));
							jgForm.setQsh(qsxx.get("qsh"));
							jgForm.setCwh(qsxx.get("cwh"));
							result = dao.runUpdateNotCommit(jgForm);
						}
					} else {
						jgForm.setLddm(qsxx.get("lddm") == null ? "" : qsxx.get("lddm"));
						jgForm.setQsh(qsxx.get("qsh") == null ? "" : qsxx.get("qsh"));
						jgForm.setCwh(qsxx.get("cwh") == null ? "" : qsxx.get("cwh"));
						result = dao.runUpdateNotCommit(jgForm);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}
	
	//更新业务状态
	private boolean updateBsiness(String sqid,String shzt) throws Exception{
		JqlxModel upForm = new JqlxModel();
		upForm.setSqid(sqid);
		upForm.setSqzt(shzt);
		return dao.runUpdate(upForm);
	}
	
	/**
	 * 
	 * @描述:审核过程中的撤销
	 * @作者：945
	 * @日期：2014-1-2 下午05:43:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(String sqid) throws Exception{
		boolean result = updateBsiness(sqid,Constants.YW_SHZ);
		if(result){
			if(!"10026".equals(Base.xxdm)){
				result = dao.cancelQsxx(sqid);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:结果库列表
	 * @作者：945
	 * @日期：2014-1-2 下午05:38:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getResultList(JqlxModel t, User user) throws Exception {
		return dao.getResultList(t, user);
	}
	/**
	 * 下载模版填充数据
	 */
	public List<HashMap<String, String>> getDownloadResultList(JqlxModel t, User user) throws Exception {
		return dao.getDownloadResultList(t, user);
	}
	
	/**
	 * 
	 * @描述:结果库的导出
	 * @作者：945
	 * @日期：2014-1-3 上午10:04:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getResultAllList(JqlxModel t, User user)
		throws Exception {
		return dao.getResultAllList(t, user);
	}
	
	/**
	 * 审核导出
	 */
	public List<HashMap<String, String>> getResultAllListSqsh(JqlxModel t, User user) throws Exception {
		return dao.getResultAllListSqsh(t, user);
	}

	/** 
	 * @描述:TODO打印留校申请表
	 * @作者：柳俊[工号：1282]
	 * @日期：2015-12-24 下午03:40:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xsjbxx
	 * @return
	 * File 返回类型 
	 * @throws 
	 */
	public File printForWord(HashMap<String, String> xsjbxx) {
		Map<String, Object> data = new HashMap<String, Object>();
		if(xsjbxx == null){
			xsjbxx = new HashMap<String, String>();
		}
		data.putAll(xsjbxx);
		return getWord(data);
	}

	/** 
	 * @描述:得到下载的word文件
	 * @作者：柳俊[工号：1282]
	 * @日期：2015-12-24 下午03:44:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @return
	 * File 返回类型 
	 * @throws 
	 */
	File getWord(Map<String, Object> data) {
		String templatePath = Constants.TEP_DIR+"rcsw/jqlx_"+Base.xxdm+".xml";
		
		File wordFile = null;
		String filename=data.get("xh") +"["+data.get("xm")+"]";
		if("12309".equals(Base.xxdm)){
			filename="临时通行证";
		}
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "rcsw", "jqlx_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(filename));
			
		}catch (Exception e) {
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "rcsw", "jqlx.xml", FreeMarkerUtil.getFileName(filename));
		}

		return wordFile;
	}
	
	/** 
	 * @描述:得到留校原因列表（衢州学院）
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-15 上午09:28:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getLxyyList(){
		return dao.getLxyyList();
	}
	public List<HashMap<String, String>> getLxtjList(){
		return dao.getLxtjList();
	}
	/**
	 * @描述:获取留宿校区代码名称（浙江传媒学院）
	 * @作者：孟威[工号：1186]
	 * @日期：2016-4-27 上午10:26:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> LsxqList(){
		return dao.getLsxqList();
	}
	
	/**
	 * 
	 * @描述: 获取留校申请类型列表（温大个性化）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-10 下午04:02:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLxsqList(){
		return dao.getLxsqList();
	}
	
	public List<HashMap<String, String>> getJgdcList(String id){
		return dao.getJgdcList(id);
	}
	
	/**
	 * @description	： 获取园区列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-23 上午11:19:38
	 * @return
	 */
	public List<HashMap<String,String>> getYqList(){
		return dao.getYqList();
	}

	public String importData_10344(HttpServletRequest request,
			HttpServletResponse response) {
		return dao.importData_10344(request,response);
	}

	public String getKnsxx(String xh) {
		return dao.getKnsxx(xh);
	}

}
