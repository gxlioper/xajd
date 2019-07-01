/**
 * @部门:学工产品事业部
 * @日期：2014-9-28 下午02:13:05 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCssz;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;
import com.zfsoft.xgxt.zxdk.xyddk.hsdxd.HsdxdService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 助学贷款-贷款结果
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-9-28 下午02:13:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DkjgService extends SuperServiceImpl<XyddkModel, DkjgDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";

	/**
	 * 
	 * @描述: 按合同编号查询放贷记录
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-9 下午02:21:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param htbh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getFdxxList(String htbh){
		
		return dao.getFdxxList(htbh);
	}
	
	/**
	 * 
	 * @描述: 按学号查询贷款记录
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-11 下午02:25:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * XyddkModel 返回类型 
	 * @throws
	 */
	public XyddkModel getModelByXh(String xh) throws Exception{
		return dao.getModelByXh(xh);
	}
	
	/**
	 * 
	 * @描述: 查询可还款信息列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-11-6 上午09:18:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKhkList(String xh){
		
		return dao.getKhkList(xh);
	}
	
	
	
	/**
	 * 
	 * @描述: 按合同编号查询续贷申请列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-11 下午02:41:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param htbh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXdsqList(String htbh){
		return dao.getXdsqList(htbh);
	}
	
	/**
	 * 
	 * @描述: 保存续贷申请
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-11 下午04:08:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXdsq(XyddkModel model) throws Exception{
		return dao.saveXdsq(model);
	}
	
	
	/**
	 * 
	 * @描述: 按学号查询国家助学贷款
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-27 上午10:21:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getGjdkList(String xh){
		
		return dao.getGjdkList(xh);
	}

	/** 
	 * @描述:根据ID查询结果信息
	 * @作者：cq [工号：785]
	 * @日期：2014-12-25 下午04:24:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getDkxxSq(String id) {
		return dao.getDkxxSq(id);
	}
	
	
	/**
	 * 
	 * @描述: 按学号、学年查询记录总数
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-3-23 下午05:10:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCountByXhAndXn(XyddkModel t){
		
		return dao.getCountByXhAndXn(t);
	}
	
	/**
	 * 
	 * @描述:根据学号查询放贷信息
	 * @作者：cq [工号：785]
	 * @日期：2015-11-28 下午04:17:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getFdxxListForXh(String xh){
		return dao.getFdxxListForXh(xh);
	}

	/**
	 * @throws Exception  
	 * @描述:取得贷款结果统计表
	 * @作者：黄辰霁
	 * @日期：2015-11-30 下午02:45:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDkjgtj(XyddkModel t, User user) throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return dao.getDkjgtj(t,user);
	}

	/** 
	 * @描述:导出统计表
	 * @作者：黄辰霁
	 * @日期：2015-11-30 下午03:59:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dkjglist
	 * @param user
	 * @param response
	 * @param filePath
	 * void 返回类型 
	 * @throws 
	 */
	public void exportHzbNew(List<HashMap<String, String>> dkjglist, User user,
			HttpServletResponse response, String filePath) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		int row = 4;
		for (int i = 0; i < dkjglist.size(); i++) {
			HashMap<String, String> dataMap = dkjglist.get(i);
			HSSFRow hSSFRow = sheet.createRow(row);
			hSSFRow.createCell(0, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("rownum"));
			hSSFRow.createCell(1, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xm"));
			hSSFRow.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xh"));
			hSSFRow.createCell(3, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("sfzh"));
			hSSFRow.createCell(4, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xymc"));
			hSSFRow.createCell(5, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("dkje"));
			hSSFRow.createCell(6, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("sjhm"));
			hSSFRow.createCell(7, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("lxdzxx"));
			row++;
		}
		
		OutputStream os = response.getOutputStream();
		workbook.write(os);  
		os.flush();
		os.close();
	}
	
	/**
	 * 
	 * @描述: 续贷申请保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-11 下午02:15:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXdsq(XyddkModel model, User user) throws Exception {
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz csszModel = csszService.getModel();
		String splc = csszModel.getXydxdshlc();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
//		boolean flag = dao.saveZsjg(model, user);
		String id = "";
		if(!"10511".equals(Base.xxdm)){
			 id = UniqID.getInstance().getUniqIDHash().toUpperCase();
			model.setId(id);
		}else{
			id = model.getId();
		}
		boolean flag = dao.saveXdsq(model);
	    ShlcInterface shlc = new CommShlcImpl();
		if(model.getType().equals("submit")){
			if (flag) {
				/**
				 * 如果是华师大
				 */
				if("10511".equals(Base.xxdm)){
					flag = shlc.runSubmit(id, splc,user.getUserName(), "zxdk_gjdk_xdsh.do", "zxdk_gjdk_xdsqnew.do");
				}else{
					flag = shlc.runSubmit(id, splc,user.getUserName(), "zxdk_gjdk_xdsh.do", "zxdk_gjdk_xdsq.do");
				}
			
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:获取续贷信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-11 下午02:38:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXdxx(String id){
		return dao.getXdxx(id);
	}
	
	//提交
	public boolean submitBusi(HashMap<String, String> model, User user)  throws Exception {
		model.put("shzt", Constants.YW_SHZ);
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz csszModel = csszService.getModel();
		
		String splc = csszModel.getXydxdshlc();
		model.put("splc", splc);
		boolean flag = dao.updateXdxx(model);
		if(flag){
			ShlcInterface shlc = new CommShlcImpl();
			if("10511".equals(Base.xxdm)){
				 shlc.runSubmit(model.get("id"), splc, user.getUserName(), "zxdk_gjdk_xdsh.do", "zxdk_gjdk_xdsqnew.do");
			}else{
				 shlc.runSubmit(model.get("id"), splc, user.getUserName(), "zxdk_gjdk_xdsh.do", "zxdk_gjdk_xdsq.do");

			}
		}
		return flag;
	}
	
	//撤销
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		ShlcInterface shlc = new CommShlcImpl();
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 修改续贷信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-11 下午03:25:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xdxx
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXdxxYwzd(XyddkModel xdxx) throws Exception{
        return dao.updateXdxxYwzd(xdxx);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除续贷信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-11 下午06:39:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delxdxx(String[] ids) throws Exception{
		return dao.delxdxx(ids);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 续贷审核查询列表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-12 上午10:15:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXdshList(XyddkModel t,User user) throws Exception{
		return dao.getXdshList(t, user);
	}
	
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 *审核保存
	 */
	public boolean saveSh(XyddkModel form, User user) {
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplc());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		//model.setZd1("有效工时");
//		model.setZd2(form.getGs());
//		model.setZd3(form.getYxgs());
		// 业务ID(多为申请ID)
		model.setYwid(form.getId());
		model.setSqrid(form.getXh());
		model.setTzlj("zxdk_gjdk_xdsh.do");
		if("10511".equals(Base.xxdm)){
			model.setTzljsq("zxdk_gjdk_xdsqnew.do");
		}else{
			model.setTzljsq("zxdk_gjdk_xdsq.do");
		}
		
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			HashMap<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("splc", form.getSplc());
			paraMap.put("id", form.getId());
			paraMap.put("shzt", zhzt);
			reuslt = this.updateXdxx(paraMap);
			if(Constants.YW_TG.equals(zhzt) && "10511".equals(Base.xxdm)){
				HsdxdService service = new  HsdxdService();
				service.insertIntoFdb(form.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	//批量审核
	public String savePlsh(XyddkModel t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		XyddkModel model = new XyddkModel();
		String[] ids = t.getIds();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();
		List<String> failXhs = new ArrayList<String>();
		//要不要做验证有待研究
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setId(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setSplc(splcs[i]);

			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	public boolean cancel(XyddkModel myForm) throws Exception {
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("splc", myForm.getSplc());
		paraMap.put("id", myForm.getId());
		paraMap.put("shzt", Constants.YW_SHZ);
		boolean reuslt = this.updateXdxx(paraMap);
		/**
		 * 华师大个性化，续贷最后一级撤销之后，删除放贷表信息
		 */
		if("10511".equals(Base.xxdm)){
			/**
			 * 删除放贷表
			 */
			HsdxdService service = new HsdxdService();
			service.delFdb(myForm.getId());
		}
		return reuslt;
	}

	public String cxshnew(String ywid, XyddkModel model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("splc", model.getShlc());
		paraMap.put("id", ywid);
		paraMap.put("shzt", Constants.YW_SHZ);
		 this.updateXdxx(paraMap);
		return cancelFlag;

	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-11 下午02:45:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xdxx
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXdxx(HashMap<String, String> xdxx) throws Exception{
		return dao.updateXdxx(xdxx);
	}
	
	/**
	 * 
	 * @描述: 查看和单个审核时用于显示续贷信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-12 下午01:57:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getViewCk(String id){
	  return dao.getViewCk(id);
		
	}
	
	/**
	 * 
	 * @描述: 贷款结果查看页面显示续贷通过的记录
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-12 下午03:44:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param htbh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDkjgXdxxTg(String htbh){
		return dao.getDkjgXdxxTg(htbh);
	}
	
	/**
	 * 
	 * @描述: 续贷申请保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-11 下午02:15:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXdsqUpdate(XyddkModel model, User user) throws Exception {
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz csszModel = csszService.getModel();
		String splc = csszModel.getXydxdshlc();
		model.setSplc(splc);
		if(model.getType().equals("update")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
//		boolean flag = dao.saveZsjg(model, user);
		String id = model.getId();
		boolean flag = dao.updateXdxxYwzd(model);
	    ShlcInterface shlc = new CommShlcImpl();
		if(model.getType().equals("updatesubmit")){
			if (flag) {
				if("10511".equals(Base.xxdm)){
					flag = shlc.runSubmit(id, splc,user.getUserName(), "zxdk_gjdk_xdsh.do", "zxdk_gjdk_xdsqnew.do");
				}else{
					flag = shlc.runSubmit(id, splc,user.getUserName(), "zxdk_gjdk_xdsh.do", "zxdk_gjdk_xdsq.do");
				}
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述: 华师大提前还款
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-21 下午05:45:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKhkListHsd(String xh){
		return dao.getKhkListHsd(xh);
	}
	
	/**
	 * 
	 * @描述: 放贷记录
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-22 下午05:04:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param htbh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getFdxxListHsd(String htbh){
		return dao.getFdxxListHsd(htbh);
	}
	
	/**
	 * 
	 * @描述:验证合同编号是否重复
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-2 下午03:30:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param htbh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkHtbhIsNotExists(String id,String htbh){
		return dao.checkHtbhIsNotExists(id, htbh);
	}
	
	/** 
	 * @描述:是否可申请续贷(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-19 上午11:01:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean countBk(String xh){
		return dao.countBk(xh);
	}
	
	/** 
	 * @描述:取上学年单科最低分(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-19 下午04:38:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int getLowestLastYear(String xh){
		return dao.getLowestLastYear(xh);
	}
}
