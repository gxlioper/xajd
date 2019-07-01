/**
 * @部门:学工产品事业部
 * @日期：2014-5-4 下午02:30:12 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpjsh;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjjg.JtpjJgForm;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjjg.JtpjJgService;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjsq.JtpjSqForm;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjsq.JtpjSqService;
import com.zfsoft.xgxt.pjpy.jtpj.jtpzsz.JtpjSzForm;
import com.zfsoft.xgxt.pjpy.jtpj.jtpzsz.JtpjSzService;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-5-4 下午02:30:12
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JtpjShservice extends JtpjSqService {
	private JtpjShDao jsd = new JtpjShDao();
	private ShlcInterface shlc = new CommShlcImpl();
	private BaseDbcz dbcz = new BaseDbcz();

	public JtpjShservice() {
		setDao(jsd);
		dbcz.setShPath("jtpjshbase.do");
		dbcz.setGnmkMc("集体评奖申请");
		dbcz.setXmmc("集体评奖");
	}

	/**
	 * 
	 * @描述:审核
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-17 上午10:47:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean saveSh(JtpjShForm form, User user) {
		// 审核操作Model初始化
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplcid());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getSqid());
		// model.setSqrid(form.getXh());
		model.setTzlj("jtpjshbase.do");
		model.setTzljsq("jtpjsqbase.do");
		
		if(Base.xxdm.equalsIgnoreCase("10704")){
			model.setZd1("评定分数");
			model.setZd3(form.getRdfs());
		}

		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			form.setShzt(zhzt);
			reuslt = jsd.updateShzt(form);
			// 审核状态为通过
			if (zhzt.equalsIgnoreCase(Constants.SH_TG)) {
				// 获取数据库申请数据
				JtpjSqForm data = getModel(form.getSqid());
				JtpjJgForm jjf = new JtpjJgForm();
				BeanUtils.copyProperties(jjf, data);
				JtpjJgService jjs = new JtpjJgService();
				JtpjSzService jss = new JtpjSzService();
				JtpjSzForm jsf = jss.getModel(jjf.getJxid());
				jjf.setPdxn(jsf.getPdxn());
				jjf.setPdxq(jsf.getPdxq());
				if(Base.xxdm.equalsIgnoreCase("10704")){//西安科技大学个性化
					jjf.setRdfs(form.getRdfs());
				}
				jjs.shtgSave(jjf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

public String savePlsh(JtpjShForm form, User user) {
		
		String[] sqids = form.getSqids();
		String[] gwids = form.getGwids();
		String[] splcs = form.getSplcs();
		String[] pjjtmcs=form.getPjjtmcs();

		List<String> failPjjtmcs = new ArrayList<String>();

		for (int i = 0, n = sqids.length; i < n; i++) {
			JtpjShForm model=new JtpjShForm();
			model.setSqid(sqids[i]);
			model.setGwid(gwids[i]);
			model.setSplcid(splcs[i]);
			
			model.setShyj(form.getShyj());
			model.setShjg(form.getShzt());

			boolean isSuccess = saveSh(model, user);

			if (!isSuccess) {
				failPjjtmcs.add(pjjtmcs[i]);
			}
		}

		JSONArray json = JSONArray.fromObject(pjjtmcs);

		if (failPjjtmcs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(form.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json	.toString());
		}
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-5 下午03:17:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shlc
	 * @param sqid
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean cancel(String shlc, String sqid) throws Exception {
		boolean result = false;
		// 请假申请状态更改
		JtpjSqForm upForm = new JtpjSqForm();
		upForm.setSqid(sqid);
		upForm.setShzt(Constants.YW_SHZ);
		JtpjJgService jjs = new JtpjJgService();
		result = dao.runUpdate(upForm, sqid)
				&& jjs.deleteForCx(upForm.getSqid()) ? true : false;

		upForm = dao.getModel(sqid);
		// 设置代办信息
		dbcz.cancel(upForm.getSqid(), upForm.getSplcid());
		return result;
	}
	
	/** 
	 * @描述:获取最新评定分数（西安科技大学个性化）(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-20 上午10:39:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getPdfs(JtpjShForm model){
		return jsd.getPdfs(model);
	}
	
	/**
	 * @description	： 获取导出文件list
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-10 上午09:55:59
	 * @return
	 * @throws Exception 
	 */
	public List<File> getDcFileList(JtpjShForm model) throws Exception{
		List<File> fileList = new ArrayList<File>();
		List<HashMap<String, String>> jtpjmd = jsd.getJtpjmd(model);
		String xydm = null;
		List<HashMap<String, String>> mdList = null;
		for(int i = 0;i < jtpjmd.size();i++){
			HashMap<String,String> map = jtpjmd.get(i);
			if(StringUtils.isNull(xydm)){
				xydm = map.get("xydm");
				mdList = new ArrayList<HashMap<String,String>>();
				mdList.add(map);
			}else{				
				if(xydm.equals(map.get("xydm"))){
					mdList.add(map);
				}else{
					File file = getExcelFile(mdList);
					fileList.add(file);
					xydm = map.get("xydm");
					mdList = new ArrayList<HashMap<String,String>>();
					mdList.add(map);
				}
			}				
		}
		File file = getExcelFile(mdList);
		fileList.add(file);
		return fileList;		
	}
	
	/**
	 * @description	： 获取导出excel文件
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-10 上午10:35:03
	 * @return
	 */
	public File getExcelFile(List<HashMap<String,String>> list) throws Exception{
		String xxmc = Base.xxmc;
		String jxmc = list.get(0).get("jxmc");
		String xymc = list.get(0).get("xymc");
		String xn = list.get(0).get("sqxn");
		String xq = list.get(0).get("sqxqmc");
		
		String fileName = xymc + ".xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);
		
		//获取年月
		Calendar instance = Calendar.getInstance();
		int year = instance.get(Calendar.YEAR);
		int month = instance.get(Calendar.MONTH) + 1;
		int date = instance.get(Calendar.DAY_OF_MONTH);
		
		if(!file.exists()){
			file.createNewFile();
		}
		
		 WritableWorkbook  wwb = Workbook.createWorkbook(file);
		 WritableSheet sheet = wwb.createSheet(xymc, 0);
		 
		 WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置标题字体
		 WritableFont headFont = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置表头字体
		 WritableFont headFont_secondary = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置表头字体
		 WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//设置正文字体
		 
		 WritableCellFormat title_cf = new WritableCellFormat(tileFont);//设置标题单元格字体
		 WritableCellFormat head_cf = new WritableCellFormat(headFont);//设置正文表头字体
		 WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体			 
		 WritableCellFormat title_secondary = new WritableCellFormat(headFont_secondary);//设置第二级标题单元格字体
		 WritableCellFormat title_third = new WritableCellFormat(headFont_secondary);
		 WritableCellFormat body_sqly = new WritableCellFormat(bodyFont);
		 WritableCellFormat body_bottom = new WritableCellFormat(headFont_secondary);
		 
		 title_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置标题单元格对齐
		 title_cf.setWrap(true);
		 
		 head_cf.setAlignment(jxl.format.Alignment.CENTRE);
		 head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置表头水平对齐
		 head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		 head_cf.setWrap(true);
		 
		 
		 body_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置正文单元格对齐
		 body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置正文单元格边框
		 body_cf.setVerticalAlignment(VerticalAlignment.CENTRE);
		 body_cf.setWrap(true);
		 
		 body_bottom.setAlignment(jxl.format.Alignment.LEFT);//设置正文单元格对齐
		 body_bottom.setBorder(jxl.format.Border.NONE, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置正文单元格边框
		 body_bottom.setVerticalAlignment(VerticalAlignment.CENTRE);
		 body_bottom.setWrap(true);
		 
		 title_secondary.setAlignment(jxl.format.Alignment.GENERAL);
		 title_secondary.setVerticalAlignment(VerticalAlignment.CENTRE);
		 title_secondary.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		 title_secondary.setWrap(true);
		 
		 title_third.setAlignment(jxl.format.Alignment.CENTRE);
		 title_third.setVerticalAlignment(VerticalAlignment.CENTRE);
		 title_third.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		 title_third.setWrap(true);
		 
		 body_sqly.setAlignment(jxl.format.Alignment.LEFT);//设置正文单元格对齐
		 body_sqly.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置正文单元格边框
		 body_sqly.setVerticalAlignment(VerticalAlignment.CENTRE);
		 body_sqly.setWrap(true);
		 
		 sheet.setRowView(0, 600, false);
		 
		 sheet.setRowView(1, 400, false);
		 
		 sheet.setRowView(2, 300, false);
		 
		 //设置各列列宽
		 sheet.setColumnView(0, 6);
		 sheet.setColumnView(1, 20);
		 sheet.setColumnView(2, 12);
		 sheet.setColumnView(3, 16);
		 sheet.setColumnView(4, 60);
		 
		 sheet.mergeCells(0, 0, 4, 0);
		 Label label_title0 = new Label(0, 0, xxmc,title_cf);
		 sheet.addCell(label_title0);
		 
		 sheet.mergeCells(0, 1, 4, 1);
		 Label label_title1 = new Label(0, 1, jxmc+"审批表",head_cf);
		 sheet.addCell(label_title1);
		 
		 sheet.mergeCells(0, 2, 4, 2);
		 Label label_title2 = new Label(0, 2, xymc+"              "+xn.substring(2,4)+"/"+xn.substring(7, 9)+"学年                                   "+xq+"                                  "+String.valueOf(year)+"年"+String.valueOf(month)+"月"+String.valueOf(date)+"日",title_secondary);
		 sheet.addCell(label_title2);
		 
		 
		 Label label_head0 = new Label(0, 3, "序号",title_third);
		 Label label_head1 = new Label(1, 3, "班级",title_third);
		 Label label_head2 = new Label(2, 3, "班主任",title_third);
		 Label label_head3 = new Label(3, 3, "学期考核成绩",title_third);
		 Label label_head4 = new Label(4, 3, "主要事迹简介",title_third);
		 
		 sheet.addCell(label_head0);
		 sheet.addCell(label_head1);
		 sheet.addCell(label_head2);
		 sheet.addCell(label_head3);
		 sheet.addCell(label_head4);
		 
		 for(int i = 0;i < list.size(); i++){
			 //sheet.mergeCells(4, 4+i, 8, 4+i);
			 
			 HashMap<String,String> mapp = list.get(i);
			 HashMap<String,String> map= (HashMap<String, String>) StringUtils.formatData(mapp);
			 Label label_xh = new Label(0, 4+i, String.valueOf(i+1),body_cf);
			 Label label_bjmc = new Label(1, 4+i, map.get("bjmc"),body_cf);
			 Label label_bzr = new Label(2, 4+i, map.get("bzr"),body_cf);
			 Label label_cj = new Label(3, 4+i, "",body_cf);
			 Label label_sqly = new Label(4, 4+i, map.get("sqly"),body_sqly);
			 
			 sheet.addCell(label_xh);
			 sheet.addCell(label_bjmc);
			 sheet.addCell(label_bzr);
			 sheet.addCell(label_cj);
			 sheet.addCell(label_sqly);
		 }
		 
		 sheet.mergeCells(0, 4+list.size(), 6, 4+list.size());
		 sheet.addCell(new Label(0, 4+list.size(),"",body_bottom));
		 
		 
		 sheet.mergeCells(0, 5+list.size(), 6, 5+list.size());
		 Label bottom = new Label(0, 4+list.size(), "系主任：            年      月      日               学工部审核：              年      月      日                 校长室审批：               年     月     日",body_bottom);
		 sheet.addCell(bottom);
		 wwb.write();
		 wwb.close();
		 
		 return file;
	}
	
	/**
	 * @description	： 验证集体评奖是否能导出
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-10 下午05:10:22
	 * @return
	 * @throws Exception 
	 */
	public boolean checkJtpjshdc(JtpjShForm form) throws Exception{
		List<HashMap<String,String>> list = jsd.getJtpjmd(form);
		return null != list && list.size() > 0 ? true:false;
	}
}
