package xsgzgl.gygl.wsjc.fscx;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xsgzgl.comm.BasicService;
import xsgzgl.gygl.comm.GyglNewInit;

import com.zfsoft.utils.StringUtil;
import common.Globals;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-28 上午11:29:22
 * </p>
 */
public class FscxService extends BasicService {

	/**
	 * 设置结果查询表头
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] {};
		String[] cn = new String[] {};
		
		//浙江理工个性化页面
		if(Globals.XXDM_ZJLG.equals(Base.xxdm) || "1".equals(GyglNewInit.FSCX_XSFS)){
			en = new String[] { "", "ld","ch", "qsh", "ssnj", "ssxy", "cws", "rzrs", "jcrc", "fz", "dj"};
			cn = new String[] { "", "楼栋", "层号", "寝室号", "所属年级", "所属学院", "床位数", "入住人数", "检查日程", "分值", "等级" };
		}else{
			if (("0").equals(GyglNewInit.JFFS)) {
				if(Base.xxdm.equals("33333")){
					en = new String[] { "", "ld", "ch", "qsh", "ssnj", "ssxy", "cws", "rsrs", "jcrc","fz" ,"kfyj" ,"pfbz"};
					cn = new String[] { "", "楼栋", "层号", "寝室号", "所属年级", "所属学院", "床位数", "入住人数", "检查日程", "扣分" , "扣分依据" , "扣分备注" };
				}else if("11647".equals(Base.xxdm)){
					en = new String[] { "", "ld", "ch", "qsh", "ssnj", "ssxy", "cws", "rzrs", "jcrc", "fz","dj" };
					cn = new String[] { "", "楼栋", "层号", "寝室号", "所属年级", "所属学院", "床位数", "入住人数", "检查日程", "分值","等级" };
				}else{					
					en = new String[] { "", "ld", "ch", "qsh", "ssnj", "ssxy", "cws", "rzrs", "jcrc", "fz" };
					cn = new String[] { "", "楼栋", "层号", "寝室号", "所属年级", "所属学院", "床位数", "入住人数", "检查日程", "分值" };
				}
			} else {
				en = new String[] { "", "ld", "ch", "qsh", "ssnj", "ssxy", "cws", "rzrs", "jcrc", "dj" };
				cn = new String[] { "", "楼栋", "层号", "寝室号", "所属年级", "所属学院", "床位数", "入住人数", "检查日程", "等级" };
			}
		}
		return dao.arrayToList(en, cn);
	}

	/**
	 * 卫生检查，获得卫生分信息
	 * 
	 * @param myForm
	 * @return
	 * @throws IllegalArgumentException , SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
	 */
	public ArrayList<String[]> getFscxCx(FscxForm myForm,HttpServletRequest request)
			throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		FscxDAO dao = new FscxDAO();
		return dao.getFscxCx(myForm,request);
	}

	/**
	 * 卫生检查，获得卫生分检查日程
	 * 
	 * @param fscxForm
	 * @param pkValue
	 * @return
	 * @throws
	 */
	public HashMap<String, String> getFscxCz2(FscxForm fscxForm, String pkValue) {
		FscxDAO dao = new FscxDAO();
		return dao.getFscxCz2(fscxForm, pkValue);
	}

	/**
	 * 卫生检查，获得卫生分修改前得信息
	 * 
	 * @param fscxForm
	 * @return
	 * @throws
	 */
	public HashMap<String, String> getFscxMap(FscxForm fscxForm) {
		FscxDAO dao = new FscxDAO();
		return dao.getFscxMap(fscxForm);
	}
	
	public HashMap<String, String> getFscxAllMap(FscxForm fscxForm) {
		FscxDAO dao = new FscxDAO();
		return dao.getFscxAllMap(fscxForm);
	}

	/**
	 * 卫生检查，获得等级下拉列表框
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Object getDjList(HttpServletRequest request) {
		FscxDAO dao = new FscxDAO();
		return dao.getDjList(request);
	}
	public Object getXjList(HttpServletRequest request) {
		FscxDAO dao = new FscxDAO();
		return dao.getXjList(request);
	}

	/**
	 * 卫生检查，对卫生分信息的修改
	 * 
	 * @param myForm
	 * @param pkValue
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public boolean fscxXg(FscxForm myForm, String pkValue, String username)throws Exception {
		FscxDAO dao = new FscxDAO();
		return dao.fscxXg(myForm, pkValue, username);
	}

	/**
	 * 卫生检查，对卫生分信息的删除
	 * 
	 * @param myForm
	 * @param valArr
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public boolean fscxSc(FscxForm myForm, String[] valArr, String username)throws SQLException {
		FscxDAO dao = new FscxDAO();
		return dao.fscxSc(myForm, valArr, username);
	}

	/**
	 * 设置结果查询表头
	 * 
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,List<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("<input type='hidden' name='lddm'  ");
				html.append("  id='lddm_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[rs.length-1]) + "'/> ");
				html.append("</td>");
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length - 1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""+ 100 / (rs.length - 2) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					if (j == 6) {
						html.append(" title=\"" + rs[7] + "\" ");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	/**
	 * 卫生分查询 自定义导出
	 * 
	 * @param myForm
	 * @return
	 * @throws IllegalArgumentException , SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
	 */
	public List<HashMap<String, String>> getFscxExportCx(FscxForm myForm,HttpServletRequest request,User user)
			throws Exception {
		FscxDAO dao = new FscxDAO();
		return dao.getFscxExportCx(myForm,request,user);
	}
	
	/**
	 * 卫生分查询  不及格学生名单导出
	 * 
	 * @param myForm
	 * @return
	 * @throws IllegalArgumentException , SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
	 */
	public List<HashMap<String, String>> getbjgmdExportCx(FscxForm myForm,HttpServletRequest request,User user)
			throws Exception {
		FscxDAO dao = new FscxDAO();
		return dao.getbjgmdExportCx(myForm,request,user);
	}
	
	/**
	 * @描述: 评定卫生分等级 并 更新等级
	 * @作者：易江东[工号：781]
	 * @日期：2013-8-30 下午02:55:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean pdQsDjXg(FscxForm myForm,HttpServletRequest request,User user)
		throws Exception{
		FscxDAO dao = new FscxDAO();
		//获取寝室分数周期列表
		List<String[]> qsfszqList = dao.getQsfszqListByXnXq(myForm, request, user);
		
		//获取寝室分数
		List<String[]> dataList=dao.getFscxPfCx(myForm, request, user,qsfszqList);
		
		//根据学年学期清空寝室登记
		updateQsdjByXnxq("",myForm.getXn(), myForm.getXq());
		
		//导出前先  更新寝室等级
		//return false;
		return qsdjXg(dataList,qsfszqList);
	}
	
	/**
	 * 
	 * @描述: 更新寝室等级 
	 * @作者：易江东[工号：781]
	 * @日期：2013-8-29 上午10:33:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param List<String[]>  格式:{[guid||qsh,fs1,fs1,fs1],...}  qsh:寝室号    fs*：分数
	 * @param qsfszqList 寝室分周期列表
	 * @return
	 * boolean 返回类型  成功：true，失败：false
	 * @throws
	 */
	private boolean qsdjXg(List<String[]> list,List<String[]> qsfszqList) throws Exception{
		if(list == null || list.size() == 0){
			return false;
		}
		FscxDAO dao = new FscxDAO();
		//寝室等级评分
		List<String[]> qsdjList=getQsdjList(list);
		
		/**
		System.out.println("---------------评分数据-----------------");
		for (int i = 0; i < list.size(); i++) {
			System.out.print((i+1) + "\t");
			for (int j = 0; j < list.get(i).length; j++) {
				System.out.print(list.get(i)[j] + "\t");
			}
			System.out.println();
			System.out.print("\t");
			for (int j = 0; j < qsdjList.get(i).length; j++) {
				System.out.print(qsdjList.get(i)[j] + "\t");
			}
			System.out.println();
		}**/
		//批量修改数据效率太慢
		return dao.qsdjPlXg(qsdjList,qsfszqList);
	}
	
	/**
	 * 
	 * @描述: 获取寝室等级
	 * @作者：易江东[工号：781]
	 * @日期：2013-8-29 上午10:30:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param List<String[]>  格式:{[guid||qsh,fs1,fs1,fs1],...}  qsh:寝室号    fs*：分数
	 * @return
	 * List<String[]> 返回类型 格式:{[guid||qsh,dj1,dj2,dj3],...}
	 * @throws
	 */
	private List<String[]> getQsdjList(List<String[]> list){
		if(list == null || list.size() == 0){
			return null;
		}
		FscxFsjsUtil fscxFsjsUtil=new FscxFsjsUtil(list);
		//设置无视第一行不计算分数
		fscxFsjsUtil.setNeglectNum(1);
		//寝室等级评分
		List<String[]> qsdjList=fscxFsjsUtil.grade();
		return qsdjList;
	}
	
	/**
	 * @描述: 验证检查日程是否提交
	 * @作者：易江东[工号：781]
	 * @日期：2013-9-2 上午08:51:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @param user
	 * @return
	 * boolean 返回类型   已提交：true，未提交：false
	 * @throws
	 */
	public boolean checkJcrcSftj(FscxForm myForm,HttpServletRequest request,User user)
		throws Exception{
		FscxDAO dao = new FscxDAO();
		List<HashMap<String, String>> list = dao.getJcrcListByWtj(myForm, request, user);
		return list == null || list.size() == 0;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-10 下午01:57:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dj
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateQsdjByXnxq(String dj,String xn,String xq) throws Exception{
		if(StringUtil.isNull(dj) || StringUtil.isNull(xn) || StringUtil.isNull(xq)){
			return false;
		}
		FscxForm myForm=new FscxForm();
		myForm.setDj(dj);
		myForm.setXn(xn);
		myForm.setXq(xq);
		
		FscxDAO dao = new FscxDAO();
		return dao.updateQsdjByXnxq(myForm);
	}
	/**
	 * 
	 * @描述:温大寝室卫生分个性化导出
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-11-29 上午10:40:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws
	 */
	public void wsfDcOfWd(FscxForm model, HttpServletRequest request,OutputStream os, User user)
	throws Exception{
		FscxDAO dao = new FscxDAO();
		List<HashMap<String,String>> resultList =dao.getWsfTjList(model,request,user);
		// 设置标题
		StringBuffer title = new StringBuffer();
		title.append(resultList.get(0).get("ldmc")+"寝室文明品行实践分核算");

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("品行实践分", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 12, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);

		ws.addCell(new Label(0, 2, "序号", wcf2));
		ws.addCell(new Label(1, 2, "寝室号", wcf2));
		ws.addCell(new Label(2, 2, "基础分", wcf2));
		ws.addCell(new Label(3, 2, "3月", wcf2));
		ws.addCell(new Label(4, 2, "4月", wcf2));
		ws.addCell(new Label(5, 2, "5月", wcf2));
		ws.addCell(new Label(6, 2, "6月", wcf2));
		ws.addCell(new Label(7, 2, "合计", wcf2));
		ws.addCell(new Label(8, 2, "9月", wcf2));
		ws.addCell(new Label(9, 2, "10月", wcf2));
		ws.addCell(new Label(10, 2, "11月", wcf2));
		ws.addCell(new Label(11, 2, "12月", wcf2));
		ws.addCell(new Label(12, 2, "合计", wcf2));
		if (resultList != null && resultList.size() > 0) {

			for (int i = 0; i < resultList.size(); i++) {

				HashMap<String, String> map = resultList.get(i);

				ws.setColumnView(0, 5);
				ws.setColumnView(1, 10);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 10);
				ws.setColumnView(4, 10);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 10);
				ws.setColumnView(7, 10);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 10);
				ws.setColumnView(10, 10);
				ws.setColumnView(11, 10);
				ws.setColumnView(12, 10);
				ws.addCell(new Label(0, 3 + i, map.get("r"), wcf2));// 序号
				ws.addCell(new Label(1, 3 + i, map.get("qsh"), wcf2));
				ws.addCell(new Label(2, 3 + i, "80", wcf2));
				ws.addCell(new Label(3, 3 + i, map.get("month3"), wcf2));
				ws.addCell(new Label(4, 3 + i, map.get("month4"), wcf2));
				ws.addCell(new Label(5, 3 + i, map.get("month5"), wcf2));
				ws.addCell(new Label(6, 3 + i, map.get("month6"), wcf2));
				ws.addCell(new Label(7, 3 + i, map.get("count1"), wcf2));
				ws.addCell(new Label(8, 3 + i, map.get("month9"), wcf2));
				ws.addCell(new Label(9, 3 + i, map.get("month10"), wcf2));
				ws.addCell(new Label(10, 3 + i, map.get("month11"), wcf2));
				ws.addCell(new Label(11, 3 + i, map.get("month12"), wcf2));
				ws.addCell(new Label(12, 3 + i, map.get("count2"), wcf2));
				
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}
	
	
	/** 
	 * @描述:周统计导出(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-7 上午10:14:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param request
	 * @param os
	 * @param user
	 * @throws Exception
	 * void 返回类型 
	 * @throws 
	 */
	public void zTjdc(FscxForm model, HttpServletRequest request,OutputStream os, User user)
	throws Exception{
		FscxDAO dao = new FscxDAO();
		List<HashMap<String,String>> resultList =dao.getZTjList(model,request,user);
		String[] sj = model.getSearchModel().getSearch_tj_kssj();
		String[] sj_1 = model.getSearchModel().getSearch_tj_jssj();
		String ksNan = sj[0].substring(0,4);
		String ksYue = sj[0].substring(4,6);
		String ksRi = sj[0].substring(6,8);
		String jsNan = sj_1[0].substring(0,4);
		String jsYue = sj_1[0].substring(4,6);
		String jsRi = sj_1[0].substring(6,8);
		
		// 设置标题
		StringBuilder title = new StringBuilder();
		title.append("各班生活区一周情况汇总表（生活指导站）");
		// 设置副标题
		StringBuilder title_1 = new StringBuilder();
		title_1.append("  第       周            汇总人：" +user.getRealName()+"         "+ksNan+"年"+ksYue+"月"+ksRi+"日至"+jsNan+"年"+jsYue+"月"+jsRi+"日");
		

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("sheet1", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(1, 0, 10, 0);
		ws.mergeCells(1, 1, 10, 1);
		ex.printToOneCell_multy(ws, title.toString(), 1, 0, 17, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ex.printToOneCell_multy(ws, title_1.toString(), 1, 1, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.NONE);
		
		ws.addCell(new Label(1, 2, "班级", wcf2));
		ws.addCell(new Label(2, 2, "班主任", wcf2));
		ws.addCell(new Label(3, 2, "表扬", wcf2));
		ws.addCell(new Label(4, 2, "批评", wcf2));
		ws.addCell(new Label(5, 2, "生活区打分", wcf2));
		ws.addCell(new Label(6, 2, "班级", wcf2));
		ws.addCell(new Label(7, 2, "班主任", wcf2));
		ws.addCell(new Label(8, 2, "表扬", wcf2));
		ws.addCell(new Label(9, 2, "批评", wcf2));
		ws.addCell(new Label(10,2, "生活区打分", wcf2));

		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);

				ws.setColumnView(1, 15);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 10);
				ws.setColumnView(4, 10);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 15);
				ws.setColumnView(7, 10);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 10);
				ws.setColumnView(10,10);

				if((1+i)%2 != 0){
					if(i>0){
						ws.addCell(new Label(1, 3 + i -(i/2), map.get("bjmc"), wcf2));
						ws.addCell(new Label(2, 3 + i -(i/2), map.get("bzr"), wcf2));
						ws.addCell(new Label(3, 3 + i -(i/2), "", wcf2));
						ws.addCell(new Label(4, 3 + i -(i/2), "", wcf2));
						ws.addCell(new Label(5, 3 + i -(i/2), map.get("fs"), wcf2));
					}else{					
						ws.addCell(new Label(1, 3 + i, map.get("bjmc"), wcf2));
						ws.addCell(new Label(2, 3 + i, map.get("bzr"), wcf2));
						ws.addCell(new Label(3, 3 + i, "", wcf2));
						ws.addCell(new Label(4, 3 + i, "", wcf2));
						ws.addCell(new Label(5, 3 + i, map.get("fs"), wcf2));				
					}
				}else{
					if(i>1){
						ws.addCell(new Label(6, 3 + i - ((i/2+1)) , map.get("bjmc"), wcf2));
						ws.addCell(new Label(7, 3 + i - ((i/2+1)), map.get("bzr"), wcf2));
						ws.addCell(new Label(8, 3 + i - ((i/2+1)), "", wcf2));
						ws.addCell(new Label(9, 3 + i - ((i/2+1)), "", wcf2));
						ws.addCell(new Label(10, 3 + i - ((i/2+1)), map.get("fs"), wcf2));
					}else{						
						ws.addCell(new Label(6, 2 + i , map.get("bjmc"), wcf2));
						ws.addCell(new Label(7, 2 + i , map.get("bzr"), wcf2));
						ws.addCell(new Label(8, 2 + i , "", wcf2));
						ws.addCell(new Label(9, 2 + i , "", wcf2));
						ws.addCell(new Label(10, 2 + i , map.get("fs"), wcf2));
					}
				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}
	
	/**
	 * @description	： 楼栋月统计分导出
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-17 下午04:59:53
	 * @param model
	 * @param request
	 * @param os
	 * @param user
	 * @throws Exception
	 */
	public void gywsfdc(FscxForm model,OutputStream os)
	throws Exception{
		FscxDAO dao = new FscxDAO();
		List<HashMap<String,String>> wsfList = dao.getLdYwsfList(model);
		List<HashMap<String,String>> pjfList = dao.getLdYwsfPjfList(model);
		List<HashMap<String,String>> resultList = dealWithRsList(wsfList, pjfList);
		String ldmc = model.getLdmc();
		String yf = model.getYf();		
		// 设置标题
		StringBuilder title = new StringBuilder();
		title.append(ldmc + yf + "月记分统计表");
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("sheet1", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 43, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		
		ws.mergeCells(0, 2, 0, 3);
		ws.mergeCells(1, 2, 1, 3);
		
		ex.printToOneCell_multy(ws, "宿舍", 0, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		ex.printToOneCell_multy(ws, "班级", 1, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		for(int i = 2; i < 33; i++){
			ws.mergeCells(i, 2, i, 3);
			ex.printToOneCell_multy(ws, String.valueOf(i-1), i, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		}
		
		ws.mergeCells(33, 2, 33, 3);
		
		ex.printToOneCell_multy(ws, "平均分", 33, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		ws.mergeCells(34, 2, 37, 2);
		
		ex.printToOneCell_multy(ws, "大扫除", 34, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		ex.printToOneCell_multy(ws, "一", 34, 3, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);		
		ex.printToOneCell_multy(ws, "二", 35, 3, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);		
		ex.printToOneCell_multy(ws, "三", 36, 3, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		ex.printToOneCell_multy(ws, "四", 37, 3, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		ws.mergeCells(38, 2, 38, 3);
		ex.printToOneCell_multy(ws, "公务扣分", 38, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		
		ws.mergeCells(39, 2, 39, 3);
		ex.printToOneCell_multy(ws, "班主任检查", 39, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		ws.mergeCells(40, 2, 40, 3);	
		ex.printToOneCell_multy(ws, "违纪扣分", 40, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		ws.mergeCells(41, 2, 41, 3);	
		ex.printToOneCell_multy(ws, "文明标兵宿舍", 41, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		ws.mergeCells(42, 2, 42, 3);	
		ex.printToOneCell_multy(ws, "得分", 42, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		ws.mergeCells(43, 2, 43, 3);	
		ex.printToOneCell_multy(ws, "等级", 43, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);

		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);

				ws.setColumnView(0, 10);
				ws.setColumnView(1, 20);
				for(int j = 2; j< 33; j++){
					ws.setColumnView(j, 5);
				}
				ws.setColumnView(33, 8);
				ws.setColumnView(34, 5);
				ws.setColumnView(35, 5);
				ws.setColumnView(36, 5);
				ws.setColumnView(37, 5);
				ws.setColumnView(38, 7);
				ws.setColumnView(39, 7);
				ws.setColumnView(40, 7);
				ws.setColumnView(41, 7);
				ws.setColumnView(42, 7);
				ws.setColumnView(43, 7);

				ws.addCell(new Label(0, 4 + i, map.get("qsh"), wcf2));
				ws.addCell(new Label(1, 4 + i, map.get("bjmc"), wcf2));
				for(int k = 1;k < 32;k++){
					ws.addCell(new Label(k + 1, 4 + i, map.get(String.valueOf(k)), wcf2));
				}
				ws.addCell(new Label(33, 4 + i, map.get("pjf"), wcf2));
				for(int g = 34; g < 44;g++){
					ws.addCell(new Label(g, 4 + i, new String(""), wcf2));
				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}
	
	/**
	 * @description	： 处理公寓月卫生分和月平均分列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-17 下午05:04:29
	 */
	public List<HashMap<String,String>> dealWithRsList(List<HashMap<String,String>> fsList,List<HashMap<String,String>> pjfList){
		List<HashMap<String,String>> rsList = new ArrayList<HashMap<String,String>>();	
			HashMap<String,String> map = new HashMap<String, String>();
			for(int i = 0;i < fsList.size();i++){
				String bjmc = fsList.get(i).get("bjmc");
				String qsh = fsList.get(i).get("qsh");
				String fs = fsList.get(i).get("fs");
				String rq = fsList.get(i).get("rq");
				//如果为第一个开始循环
				if(i == 0){
					map.put("bjmc", bjmc);
					map.put("qsh", qsh);
					map.put(rq, fs);
				}else{
					//班级名称或者寝室号不相等时
					if(!map.get("bjmc").equals(bjmc) || !map.get("qsh").equals(qsh)){
						//如果寝室号与班级名称一致
						for(HashMap<String,String> pjfMap : pjfList){
							if(pjfMap.get("bjmc").equals(map.get("bjmc")) && pjfMap.get("qsh").equals(map.get("qsh"))){
								map.put("pjf", pjfMap.get("pjf"));
								break;
							}
						}
						rsList.add(map);
						map = new HashMap<String, String>();
						map.put("bjmc", bjmc);
						map.put("qsh", qsh);
						map.put(rq, fs);
					}else{
						map.put(rq, fs);
					}
					//如果为最后一条记录的话，需加入到rsList
					if(i == fsList.size() - 1){
						//如果寝室号与班级名称一致
						for(HashMap<String,String> pjfMap : pjfList){
							if(pjfMap.get("bjmc").equals(map.get("bjmc")) && pjfMap.get("qsh").equals(map.get("qsh"))){
								map.put("pjf", pjfMap.get("pjf"));
								break;
							}
						}
						rsList.add(map);
					}
				}
			}
			if(rsList.size() > 0){
				for(int i = 1; i < 32; i++){
					for(HashMap<String,String> mapp : rsList){
						if(!mapp.containsKey(String.valueOf(i))){
							mapp.put(String.valueOf("i"), new String());
						}
					}
				}
			}
		return rsList;
	}
	
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-20 下午04:34:54
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getLdYwsfList(FscxForm model){
		FscxDAO dao = new FscxDAO();
		return dao.getLdYwsfList(model);
	}
	
	/**
	 * @description	： 获取楼栋列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-17 上午11:47:34
	 * @return
	 */
	public List<HashMap<String,String>> getLdList(){
		FscxDAO dao = new FscxDAO();
		return dao.getLdList();
	}
	
	
}