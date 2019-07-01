/**
 * @部门:学工产品事业部
 * @日期：2015-2-12 下午03:53:39 
 */
package com.zfsoft.xgxt.xszy.qsppgl;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xszy.xsqshf.XszyQshfDao;
import com.zfsoft.xgxt.xszy.xsqshf.XszyQshfForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-2-12 下午03:53:39
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XszyQsppService extends SuperServiceImpl<XszyQsppForm, XszyQsppDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private static final String FPZT="1";
	/**
	 * 
	 * @描述:获取新生之友选择列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-13 下午02:39:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getSgppXszyList(XszyQsppForm t,
			User user) throws Exception {
		return dao.getSgppXszyList(t, user);
	}
	/**
	 * 
	 * @描述:手动匹配保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-13 下午03:41:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSdpp(XszyQsppForm model, User user) throws Exception {
		boolean result = true;
		String id = UniqID.getInstance().getUniqIDHash();
		model.setId(id);
		model.setCzr(user.getUserName());
		model.setCzsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setFpczr(user.getUserName());
		//先删除后插入
		result = dao.delQsgx(model);
		if(result){
			result = dao.runInsert(model);
		}
		return result;
	}
	
	public boolean saveZdpp(XszyQsppForm model, User user) throws Exception {
		//获取男女寝室、男女新生之友
		List<HashMap<String,String>> man_qsxxList = dao.getQsxxOfMan(model, user);
		List<HashMap<String,String>> woman_qsxxList = dao.getQsxxOfWoman(model, user);
		List<HashMap<String,String>> man_xszyList = dao.getXszyOfMan(model, user);
		List<HashMap<String,String>> woman_xszyList = dao.getXszyOfWoman(model, user);
		model.setCzsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setCzr(user.getUserName());
		List<String[]> qsppList = new ArrayList<String[]>();
		String[] qsppxx = null;
		int manSize = man_xszyList.size()>man_qsxxList.size()?man_qsxxList.size():man_xszyList.size();
		int womanSize = woman_xszyList.size()>woman_qsxxList.size()?woman_qsxxList.size():woman_xszyList.size();
		for (int i = 0; i < manSize; i++) {
			qsppxx = new String[6];
			qsppxx[0] = model.getNj();
			qsppxx[1] = man_qsxxList.get(i).get("lddm");
			qsppxx[2] = man_qsxxList.get(i).get("qsh");
			qsppxx[3] = man_xszyList.get(i).get("zgh");
			qsppxx[4] = model.getCzr();
			qsppxx[5] = model.getCzsj();
			qsppList.add(qsppxx);
		}
		for (int i = 0; i < womanSize; i++) {
			qsppxx = new String[6];
			qsppxx[0] = model.getNj();
			qsppxx[1] = woman_qsxxList.get(i).get("lddm");
			qsppxx[2] = woman_qsxxList.get(i).get("qsh");
			qsppxx[3] = woman_xszyList.get(i).get("zgh");
			qsppxx[4] = model.getCzr();
			qsppxx[5] = model.getCzsj();
			qsppList.add(qsppxx);
		}
		boolean result = dao.qsppPlbc(qsppList);
		return result;
	}
	/**
	 * 
	 * @描述:查询学院新生之友和寝室信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-14 下午05:27:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getQsxxAndXszy(XszyQsppForm model, User user) throws Exception {
		return dao.getQsxxAndXszy(model, user);

	}
	/**
	 * @throws Exception 
	 * @throws IOException 
	 * 
	 * @描述:寝室统计导出
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-2 上午09:51:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws
	 */
	public void qstjExport(XszyQsppForm model, OutputStream os,
			List<HashMap<String, String>> resultList, User user) throws Exception {
		XszyQshfDao qshfDao = new XszyQshfDao();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("寝室统计情况", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		ws.addCell(new Label(0, 0, "序号", wcf2));
		ws.addCell(new Label(1, 0, "新生之友姓名", wcf2));
		ws.addCell(new Label(2, 0, "性别", wcf2));
		ws.addCell(new Label(3, 0, "职工号", wcf2));
		ws.addCell(new Label(4, 0, "单位", wcf2));
		ws.addCell(new Label(5, 0, "政治面貌", wcf2));
		ws.addCell(new Label(6, 0, "职务职称", wcf2));
		ws.addCell(new Label(7, 0, "联系电话", wcf2));
		ws.addCell(new Label(8, 0, "手机", wcf2));
		ws.addCell(new Label(9, 0, "邮件", wcf2));
		ws.addCell(new Label(10, 0, "大类要求", wcf2));
		ws.addCell(new Label(11, 0, "备注(如院士、首席科学家、长江、杰青、求是特聘、千人计划等)", wcf2));
		ws.addCell(new Label(12, 0, "其他备注", wcf2));
		ws.addCell(new Label(13, 0, "联系寝室号", wcf2));
		ws.addCell(new Label(14, 0, "人数", wcf2));
		ws.addCell(new Label(15, 0, "学生姓名", wcf2));
		ws.addCell(new Label(16, 0, "学号", wcf2));
		ws.addCell(new Label(17, 0, "学生大类", wcf2));
		ws.addCell(new Label(18, 0, "班级", wcf2));
		ws.addCell(new Label(19, 0, "性别", wcf2));
		ws.addCell(new Label(20, 0, "民族", wcf2));
		ws.addCell(new Label(21, 0, "联系电话", wcf2));
		ws.addCell(new Label(22, 0, "短号", wcf2));
		ws.addCell(new Label(23, 0, "邮箱", wcf2));
		ws.addCell(new Label(24, 0, "备注寝室长/党员", wcf2));
		ws.addCell(new Label(25, 0, "备注是否港澳台生、国防生/是否明确专业", wcf2));
		ws.addCell(new Label(26, 0, "班主任", wcf2));
		ws.addCell(new Label(27, 0, "派出院系", wcf2));
		ws.addCell(new Label(28, 0, "班主任电话", wcf2));
		ws.addCell(new Label(29, 0, "班主任手机", wcf2));
		ws.addCell(new Label(30, 0, "班主任邮箱", wcf2));
		ws.addCell(new Label(31, 0, "辅导员", wcf2));
		ws.addCell(new Label(32, 0, "辅导员电话", wcf2));
		ws.addCell(new Label(33, 0, "辅导员手机", wcf2));
		ws.addCell(new Label(34, 0, "辅导员邮箱", wcf2));
		if(resultList.size()>0){
			int cellNum = 0;
			XszyQshfForm qshfForm = new XszyQshfForm();
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				qshfForm.setLddm(map.get("lddm"));
				qshfForm.setQsh(map.get("qsh"));
				qshfForm.setNj(map.get("nj"));
				List<HashMap<String, String>> rzxsList = qshfDao.getRzxsList(qshfForm);
				cellNum =cellNum+1;
				ws.addCell(new Label(0, cellNum, String.valueOf(i+1), wcf2));// 序号
				ws.addCell(new Label(1, cellNum, map.get("xm"), wcf2));
				ws.addCell(new Label(2, cellNum, map.get("xb"), wcf2));
				ws.addCell(new Label(3, cellNum, map.get("zgh"), wcf2));
				ws.addCell(new Label(4, cellNum, map.get("dwmc"), wcf2));
				ws.addCell(new Label(5, cellNum, map.get("zzmmmc"), wcf2));
				ws.addCell(new Label(6, cellNum, map.get("zwzc"), wcf2));
				ws.addCell(new Label(7, cellNum, map.get("lxdh"), wcf2));
				ws.addCell(new Label(8, cellNum, "", wcf2));
				ws.addCell(new Label(9, cellNum, map.get("dzyx"), wcf2));
				ws.addCell(new Label(10, cellNum, map.get("dlyq"), wcf2));
				ws.addCell(new Label(11, cellNum, map.get("xszybz"), wcf2));
				ws.addCell(new Label(12, cellNum, "", wcf2));
				ws.addCell(new Label(13, cellNum, map.get("ldmc")+map.get("qsh"), wcf2));
				if(rzxsList.size()>0){
					//int cellNum+j = 0;
					for (int j = 0; j < rzxsList.size(); j++) {
						HashMap<String, String> rzxsMap = rzxsList.get(j);
						if(j!=0){
							ws.addCell(new Label(0, cellNum+j, "", wcf2));// 序号
							ws.addCell(new Label(1, cellNum+j, "", wcf2));
							ws.addCell(new Label(2, cellNum+j, "", wcf2));
							ws.addCell(new Label(3, cellNum+j, "", wcf2));
							ws.addCell(new Label(4, cellNum+j, "", wcf2));
							ws.addCell(new Label(5, cellNum+j, "", wcf2));
							ws.addCell(new Label(6, cellNum+j, "", wcf2));
							ws.addCell(new Label(7, cellNum+j, "", wcf2));
							ws.addCell(new Label(8, cellNum+j, "", wcf2));
							ws.addCell(new Label(9, cellNum+j, "", wcf2));
							ws.addCell(new Label(10,cellNum+j, "", wcf2));
							ws.addCell(new Label(11,cellNum+j, "", wcf2));
							ws.addCell(new Label(12,cellNum+j, "", wcf2));
							
						}
						ws.addCell(new Label(13, cellNum+j, map.get("ldmc")+map.get("qsh"), wcf2));
						ws.addCell(new Label(14, cellNum+j, "", wcf2));
						ws.addCell(new Label(15, cellNum+j, rzxsMap.get("xm"), wcf2));
						ws.addCell(new Label(16, cellNum+j, rzxsMap.get("xh"), wcf2));
						ws.addCell(new Label(17, cellNum+j, rzxsMap.get("dl"), wcf2));
						ws.addCell(new Label(18, cellNum+j, rzxsMap.get("bjmc"), wcf2));
						ws.addCell(new Label(19, cellNum+j, rzxsMap.get("xb"), wcf2));
						ws.addCell(new Label(20, cellNum+j, rzxsMap.get("mzmc"), wcf2));
						ws.addCell(new Label(21, cellNum+j, rzxsMap.get("lxdh"), wcf2));
						ws.addCell(new Label(22, cellNum+j, rzxsMap.get("dh"), wcf2));
						ws.addCell(new Label(23, cellNum+j, rzxsMap.get("dzyx"), wcf2));
						ws.addCell(new Label(24, cellNum+j, "", wcf2));
						ws.addCell(new Label(25, cellNum+j, rzxsMap.get("bz"), wcf2));
						ws.addCell(new Label(26, cellNum+j, rzxsMap.get("bzrxm"), wcf2));
						ws.addCell(new Label(27, cellNum+j, "", wcf2));
						ws.addCell(new Label(28, cellNum+j, rzxsMap.get("bzrlxdh"), wcf2));
						ws.addCell(new Label(29, cellNum+j, "", wcf2));
						ws.addCell(new Label(30, cellNum+j, rzxsMap.get("bzryx"), wcf2));
						ws.addCell(new Label(31, cellNum+j, rzxsMap.get("fdyxm"), wcf2));
						ws.addCell(new Label(32, cellNum+j, rzxsMap.get("fdylxdh"), wcf2));
						ws.addCell(new Label(33, cellNum+j, "", wcf2));
						ws.addCell(new Label(34, cellNum+j, rzxsMap.get("fdyyx"), wcf2));
					}
					cellNum+=rzxsList.size()-1;
				}
				else{
					ws.addCell(new Label(13, cellNum, map.get("ldmc")+map.get("qsh"), wcf2));
					ws.addCell(new Label(14, cellNum, "", wcf2));
					ws.addCell(new Label(15, cellNum, "", wcf2));
					ws.addCell(new Label(16, cellNum, "", wcf2));
					ws.addCell(new Label(17, cellNum, "", wcf2));
					ws.addCell(new Label(18, cellNum, "", wcf2));
					ws.addCell(new Label(19, cellNum, "", wcf2));
					ws.addCell(new Label(20, cellNum, "", wcf2));
					ws.addCell(new Label(21, cellNum, "", wcf2));
					ws.addCell(new Label(22, cellNum, "", wcf2));
					ws.addCell(new Label(23, cellNum, "", wcf2));
					ws.addCell(new Label(24, cellNum, "", wcf2));
					ws.addCell(new Label(25, cellNum, "", wcf2));
					ws.addCell(new Label(26, cellNum, "", wcf2));
					ws.addCell(new Label(27, cellNum, "", wcf2));
					ws.addCell(new Label(28, cellNum, "", wcf2));
					ws.addCell(new Label(29, cellNum, "", wcf2));
					ws.addCell(new Label(30, cellNum, "", wcf2));
					ws.addCell(new Label(31, cellNum, "", wcf2));
					ws.addCell(new Label(32, cellNum, "", wcf2));
					ws.addCell(new Label(33, cellNum, "", wcf2));
					ws.addCell(new Label(34, cellNum, "", wcf2));
				}
			}
		}
		wwb.write();
		wwb.close();
	}
	
	public void fwExport(XszyQsppForm model, OutputStream os,
			List<HashMap<String, String>> resultList, User user) throws Exception {
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("各部门新生之友导出", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		ws.addCell(new Label(0, 0, "序号", wcf2));
		ws.addCell(new Label(1, 0, "部门名称", wcf2));
		ws.addCell(new Label(2, 0, "新生之友姓名", wcf2));
		ws.addCell(new Label(3, 0, "寝室", wcf2));
		for (int i = 0; i < resultList.size(); i++) {
			HashMap<String, String> map = resultList.get(i);
			ws.setColumnView(0, 5);
			ws.setColumnView(1, 15);
			ws.setColumnView(2, 15);
			ws.setColumnView(3, 30);
			ws.addCell(new Label(0, 1+i, String.valueOf(i+1), wcf2));// 序号
			ws.addCell(new Label(1, 1+i, map.get("dwmc"), wcf2));
			ws.addCell(new Label(2, 1+i, map.get("xm"), wcf2));
			ws.addCell(new Label(3, 1+i, map.get("fpqs"), wcf2));
		}
		wwb.write();
		wwb.close();
	}
	public List<HashMap<String,String>> getExportData(XszyQsppForm model, User user) throws Exception {
		model.getPages().setPageSize(Integer.MAX_VALUE);
		return dao.getExportData(model, user);

	}
	/**
	 * 
	 * @描述:发文导出
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-15 下午02:44:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getfwExportData(XszyQsppForm model, User user) throws Exception {
		model.getPages().setPageSize(Integer.MAX_VALUE);
		return dao.getfwExportData(model, user);

	}
	/**
	 * 
	 * @描述:寝室分配退回
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-19 下午03:50:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @param lddms
	 * @param qshs
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qsppTh(String ids,String lddms,String qshs,String ssyxdms, User user) throws Exception {
		XszyQshfDao qshfDao = new XszyQshfDao();
		String[] idArr = ids.split(",");
		String[] lddmArr = lddms.split(",");
		String[] qshArr = qshs.split(",");
		String[] thxyArr = ssyxdms.split(",");
		String thsj=GetTime.getTimeByFormat(DATE_FORMAT);
		qshfDao.runDelete(idArr);
		List<String[]> qsList = new ArrayList<String[]>();
		String[] qsxx = null;
		for (int i = 0; i < idArr.length; i++) {
			qsxx = new String[6];
			qsxx[0] = FPZT;
			qsxx[1] = user.getRealName();
			qsxx[2] = thsj;
			qsxx[3] = thxyArr[i];
			qsxx[4] = lddmArr[i];
			qsxx[5] = qshArr[i];
			qsList.add(qsxx);
		}
		return dao.qsPlth(qsList);
	}
	
	/**
	 * 
	 * @描述:导出分组
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-24 下午06:30:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getExportDataGroup(XszyQsppForm t, User user)
	throws Exception {
		return dao.getExportDataGroup(t, user);
   }
	
	
	/**
	 * @throws Exception 
	 * @throws IOException 
	 * 
	 * @描述:寝室统计分组导出
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-2 上午09:51:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws
	 */
	public File qstjExportGroup(XszyQsppForm model, String dwmc,
			List<HashMap<String, String>> resultList, User user) throws Exception {
		XszyQshfDao qshfDao = new XszyQshfDao();
	  /*String tmPath = System.getProperty("java.io.tmpdir")+"\\"+Calendar.getInstance().getTimeInMillis()+user.getUserName()+"-"+dwmc+".xls"; */
		String tmPath = System.getProperty("java.io.tmpdir")+File.separator+Calendar.getInstance().getTimeInMillis()+user.getUserName()+"-"+dwmc+".xls";
		File file = new File(tmPath);
		if(!file.exists()){
			file.createNewFile();
		}
		WritableWorkbook wwb = Workbook.createWorkbook(file);
		WritableSheet ws = wwb.createSheet("寝室统计情况", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		ws.addCell(new Label(0, 0, "序号", wcf2));
		ws.addCell(new Label(1, 0, "新生之友姓名", wcf2));
		ws.addCell(new Label(2, 0, "性别", wcf2));
		ws.addCell(new Label(3, 0, "职工号", wcf2));
		ws.addCell(new Label(4, 0, "单位", wcf2));
		ws.addCell(new Label(5, 0, "政治面貌", wcf2));
		ws.addCell(new Label(6, 0, "职务职称", wcf2));
		ws.addCell(new Label(7, 0, "联系电话", wcf2));
		ws.addCell(new Label(8, 0, "手机", wcf2));
		ws.addCell(new Label(9, 0, "邮件", wcf2));
		ws.addCell(new Label(10, 0, "大类要求", wcf2));
		ws.addCell(new Label(11, 0, "备注(如院士、首席科学家、长江、杰青、求是特聘、千人计划等)", wcf2));
		ws.addCell(new Label(12, 0, "其他备注", wcf2));
		ws.addCell(new Label(13, 0, "联系寝室号", wcf2));
		ws.addCell(new Label(14, 0, "人数", wcf2));
		ws.addCell(new Label(15, 0, "学生姓名", wcf2));
		ws.addCell(new Label(16, 0, "学号", wcf2));
		ws.addCell(new Label(17, 0, "学生大类", wcf2));
		ws.addCell(new Label(18, 0, "班级", wcf2));
		ws.addCell(new Label(19, 0, "性别", wcf2));
		ws.addCell(new Label(20, 0, "民族", wcf2));
		ws.addCell(new Label(21, 0, "联系电话", wcf2));
		ws.addCell(new Label(22, 0, "短号", wcf2));
		ws.addCell(new Label(23, 0, "邮箱", wcf2));
		ws.addCell(new Label(24, 0, "备注寝室长/党员", wcf2));
		ws.addCell(new Label(25, 0, "备注是否港澳台生、国防生/是否明确专业", wcf2));
		ws.addCell(new Label(26, 0, "班主任", wcf2));
		ws.addCell(new Label(27, 0, "派出院系", wcf2));
		ws.addCell(new Label(28, 0, "班主任电话", wcf2));
		ws.addCell(new Label(29, 0, "班主任手机", wcf2));
		ws.addCell(new Label(30, 0, "班主任邮箱", wcf2));
		ws.addCell(new Label(31, 0, "辅导员", wcf2));
		ws.addCell(new Label(32, 0, "辅导员电话", wcf2));
		ws.addCell(new Label(33, 0, "辅导员手机", wcf2));
		ws.addCell(new Label(34, 0, "辅导员邮箱", wcf2));
		if(resultList.size()>0){
			int cellNum = 0;
			XszyQshfForm qshfForm = new XszyQshfForm();
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				qshfForm.setLddm(map.get("lddm"));
				qshfForm.setQsh(map.get("qsh"));
				qshfForm.setNj(map.get("nj"));
				List<HashMap<String, String>> rzxsList = qshfDao.getRzxsList(qshfForm);
				cellNum =cellNum+1;
				ws.addCell(new Label(0, cellNum, String.valueOf(i+1), wcf2));// 序号
				ws.addCell(new Label(1, cellNum, map.get("xm"), wcf2));
				ws.addCell(new Label(2, cellNum, map.get("xb"), wcf2));
				ws.addCell(new Label(3, cellNum, map.get("zgh"), wcf2));
				ws.addCell(new Label(4, cellNum, map.get("dwmc"), wcf2));
				ws.addCell(new Label(5, cellNum, map.get("zzmmmc"), wcf2));
				ws.addCell(new Label(6, cellNum, map.get("zwzc"), wcf2));
				ws.addCell(new Label(7, cellNum, map.get("lxdh"), wcf2));
				ws.addCell(new Label(8, cellNum, "", wcf2));
				ws.addCell(new Label(9, cellNum, map.get("dzyx"), wcf2));
				ws.addCell(new Label(10, cellNum, map.get("dlyq"), wcf2));
				ws.addCell(new Label(11, cellNum, map.get("xszybz"), wcf2));
				ws.addCell(new Label(12, cellNum, "", wcf2));
				ws.addCell(new Label(13, cellNum, map.get("ldmc")+map.get("qsh"), wcf2));
				if(rzxsList.size()>0){
					//int cellNum+j = 0;
					for (int j = 0; j < rzxsList.size(); j++) {
						HashMap<String, String> rzxsMap = rzxsList.get(j);
						if(j!=0){
							ws.addCell(new Label(0, cellNum+j, "", wcf2));// 序号
							ws.addCell(new Label(1, cellNum+j, "", wcf2));
							ws.addCell(new Label(2, cellNum+j, "", wcf2));
							ws.addCell(new Label(3, cellNum+j, "", wcf2));
							ws.addCell(new Label(4, cellNum+j, "", wcf2));
							ws.addCell(new Label(5, cellNum+j, "", wcf2));
							ws.addCell(new Label(6, cellNum+j, "", wcf2));
							ws.addCell(new Label(7, cellNum+j, "", wcf2));
							ws.addCell(new Label(8, cellNum+j, "", wcf2));
							ws.addCell(new Label(9, cellNum+j, "", wcf2));
							ws.addCell(new Label(10,cellNum+j, "", wcf2));
							ws.addCell(new Label(11,cellNum+j, "", wcf2));
							ws.addCell(new Label(12,cellNum+j, "", wcf2));
							
						}
						ws.addCell(new Label(13, cellNum+j, map.get("ldmc")+map.get("qsh"), wcf2));
						ws.addCell(new Label(14, cellNum+j, "", wcf2));
						ws.addCell(new Label(15, cellNum+j, rzxsMap.get("xm"), wcf2));
						ws.addCell(new Label(16, cellNum+j, rzxsMap.get("xh"), wcf2));
						ws.addCell(new Label(17, cellNum+j, rzxsMap.get("dl"), wcf2));
						ws.addCell(new Label(18, cellNum+j, rzxsMap.get("bjmc"), wcf2));
						ws.addCell(new Label(19, cellNum+j, rzxsMap.get("xb"), wcf2));
						ws.addCell(new Label(20, cellNum+j, rzxsMap.get("mzmc"), wcf2));
						ws.addCell(new Label(21, cellNum+j, rzxsMap.get("lxdh"), wcf2));
						ws.addCell(new Label(22, cellNum+j, rzxsMap.get("dh"), wcf2));
						ws.addCell(new Label(23, cellNum+j, rzxsMap.get("dzyx"), wcf2));
						ws.addCell(new Label(24, cellNum+j, "", wcf2));
						ws.addCell(new Label(25, cellNum+j, rzxsMap.get("bz"), wcf2));
						ws.addCell(new Label(26, cellNum+j, rzxsMap.get("bzrxm"), wcf2));
						ws.addCell(new Label(27, cellNum+j, "", wcf2));
						ws.addCell(new Label(28, cellNum+j, rzxsMap.get("bzrlxdh"), wcf2));
						ws.addCell(new Label(29, cellNum+j, "", wcf2));
						ws.addCell(new Label(30, cellNum+j, rzxsMap.get("bzryx"), wcf2));
						ws.addCell(new Label(31, cellNum+j, rzxsMap.get("fdyxm"), wcf2));
						ws.addCell(new Label(32, cellNum+j, rzxsMap.get("fdylxdh"), wcf2));
						ws.addCell(new Label(33, cellNum+j, "", wcf2));
						ws.addCell(new Label(34, cellNum+j, rzxsMap.get("fdyyx"), wcf2));
					}
					cellNum+=rzxsList.size()-1;
				}
				else{
					ws.addCell(new Label(13, cellNum, map.get("ldmc")+map.get("qsh"), wcf2));
					ws.addCell(new Label(14, cellNum, "", wcf2));
					ws.addCell(new Label(15, cellNum, "", wcf2));
					ws.addCell(new Label(16, cellNum, "", wcf2));
					ws.addCell(new Label(17, cellNum, "", wcf2));
					ws.addCell(new Label(18, cellNum, "", wcf2));
					ws.addCell(new Label(19, cellNum, "", wcf2));
					ws.addCell(new Label(20, cellNum, "", wcf2));
					ws.addCell(new Label(21, cellNum, "", wcf2));
					ws.addCell(new Label(22, cellNum, "", wcf2));
					ws.addCell(new Label(23, cellNum, "", wcf2));
					ws.addCell(new Label(24, cellNum, "", wcf2));
					ws.addCell(new Label(25, cellNum, "", wcf2));
					ws.addCell(new Label(26, cellNum, "", wcf2));
					ws.addCell(new Label(27, cellNum, "", wcf2));
					ws.addCell(new Label(28, cellNum, "", wcf2));
					ws.addCell(new Label(29, cellNum, "", wcf2));
					ws.addCell(new Label(30, cellNum, "", wcf2));
					ws.addCell(new Label(31, cellNum, "", wcf2));
					ws.addCell(new Label(32, cellNum, "", wcf2));
					ws.addCell(new Label(33, cellNum, "", wcf2));
					ws.addCell(new Label(34, cellNum, "", wcf2));
				}
			}
		}
		wwb.write();
		wwb.close();
		return file;
		
	}
	
}
