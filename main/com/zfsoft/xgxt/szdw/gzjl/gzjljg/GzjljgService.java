/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午04:32:43 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjljg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ResourceUtils;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;

import com.zfsoft.xgxt.szdw.gzjl.lbgl.GzjlLbglDao;

import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-6-25 下午04:32:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzjljgService extends SuperServiceImpl<GzjljgForm, GzjljgDao> {
	/**
	 * 
	 * @描述:获取教师基本信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-25 下午05:25:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getJsjbxx(String zgh){

		if (StringUtil.isNull(zgh)) {
			logger.error("zgh is null !");
			throw new NullPointerException();
		}

		try {
			return dao.getJsjbxx(zgh);
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}
	public boolean checkExistForSave(GzjljgForm model){
		return dao.checkExistForSave(model);
	}
	/**
	 * 
	 * @描述:工作记录结果保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 下午12:00:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean savegzjljg(GzjljgForm model) throws Exception{
		boolean flag=false;
		if("save".equals(model.getType())){
			flag=dao.runInsert(model);
		}else{
			flag=dao.runUpdate(model);
		}
		return flag;
	}
	
	public List<HashMap<String,String>> getJsxxList(GzjljgForm model,User user) throws Exception{
		return dao.getJsxxList(model,user);
	}
	/**
	 * 
	 * @描述:工作记录统计
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-2 下午04:26:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param exporModel
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGzjltjList(GzjljgForm exporModel,User user) throws Exception{
		List<HashMap<String, String>> lblist= new GzjlLbglDao().getGzjllbList();
		return dao.getGzjltjList(exporModel,lblist,user);
	}
	/**
	 * 
	 * @描述:excel生成
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-2 下午04:27:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param resultList
	 * @param os
	 * @param user
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public File gzjltjExport(List<HashMap<String,String>> resultList, OutputStream os, User user,String type,GzjljgForm exporModel) throws Exception {
		File file = createFile();
		FileOutputStream outputStream = new FileOutputStream(file);
		List<HashMap<String, String>> lblist= new GzjlLbglDao().getGzjllbList();
		WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
		String title = null;//表格标题
		String lkr = null;//落款人
		int xys=0;//学院数
		int xys_one=0;
		int k=0;
		int avg1=0;
		int avg2=0;
		int avg3=0;
		int avg4=0;
		int avg5=0;
		int avg6=0;
		int avg7=0;
		int avg8=0;
		int avg9=0;
		int avg10=0;
		int avg11=0;
		
		String kssj = null==exporModel.getSearchModel().getSearch_tj_kssj()?"":exporModel.getSearchModel().getSearch_tj_kssj()[0];
		String jssj = null==exporModel.getSearchModel().getSearch_tj_jssj()?"":exporModel.getSearchModel().getSearch_tj_jssj()[0];
		kssj=DateTranCnDate.fomartDateToCn(kssj,FomartDateType.day);
		jssj=DateTranCnDate.fomartDateToCn(jssj,FomartDateType.day);
		String xymc = dao.getXymc(user.getUserDep());
		
		if("js".equals(type)){
			title=user.getRealName()+kssj+"至"+jssj+"“九个一” 工作内容完成情况汇总表";
			lkr="本人签名：";
		}else if("xy".equals(type)){
			title=Base.xxmc+xymc+kssj+"至"+jssj+"辅导员（班主任）“九个一” 工作内容完成情况汇总表";
			lkr=xymc+"学生工作办公室（盖章）";
		}else{
			title=Base.xxmc+kssj+"至"+jssj+"辅导员（班主任）“九个一” 工作内容完成情况汇总表";
			lkr="党委学工部（学生处）（盖章）";
			
		}
		
		WritableSheet ws = wwb.createSheet("工作内容完成情况汇总表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 11, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ex.printToOneCell_multy(ws, title, 0, 0, 11, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		
		ws.mergeCells(0, 0, 12, 0);
		ws.mergeCells(1, 1, 1, 1);
		ws.mergeCells(2, 1, 2, 1);
		ws.mergeCells(3, 1, 3, 1);
		ws.mergeCells(4, 1, 4, 1);
		ws.mergeCells(5, 1, 5, 1);
		ws.mergeCells(6, 1, 6, 1);
		ws.mergeCells(7, 1, 7, 1);
		ws.mergeCells(8, 1, 8, 1);
		ws.mergeCells(9, 1, 9, 1);
		ws.mergeCells(10, 1, 10, 1);
		ws.mergeCells(11, 1, 11, 1);
		
		if("xx".equals(type)){
			ws.addCell(new Label(0, 1, "姓名", wcf2));
			ws.addCell(new Label(1, 1, "学院", wcf2));
			ws.addCell(new Label(2, 1, "工作类别1(次数）", wcf2));
			ws.addCell(new Label(3, 1, "工作类别2(次数）", wcf2));
			ws.addCell(new Label(4, 1, "工作类别3(次数）", wcf2));
			ws.addCell(new Label(5, 1, "工作类别4(次数）", wcf2));
			ws.addCell(new Label(6, 1, "工作类别5(次数）", wcf2));
			ws.addCell(new Label(7, 1, "工作类别6(次数）", wcf2));
			ws.addCell(new Label(8, 1, "工作类别7(次数）", wcf2));
			ws.addCell(new Label(9, 1, "工作类别8(次数）", wcf2));
			ws.addCell(new Label(10, 1, "工作类别9(次数）", wcf2));
			ws.addCell(new Label(11, 1, "工作类别10(次数）", wcf2));
			ws.addCell(new Label(12, 1, "工作类别11(次数）", wcf2));
			if (resultList != null && resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					HashMap<String, String> map = resultList.get(i);
					ws.setColumnView(0, 10);
					ws.setColumnView(1, 15);
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
					if(i!=0&&!map.get("xymc").equals(resultList.get(i-1).get("xymc"))){
						xys_one=0;
						xys++;
						ws.mergeCells(0,2 + i+k, 1, 2 + i+k);
						ex.printToOneCell_multy(ws, "平均数据统计分析", 0, 2 + i+k, 11, false,
								Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
								Border.ALL);
						ws.addCell(new Label(2, 2 + i+k, String.valueOf(Math.round((float)avg1/i)), wcf2));
						ws.addCell(new Label(3, 2 + i+k, String.valueOf(Math.round((float)avg2/i)), wcf2));
						ws.addCell(new Label(4, 2 + i+k, String.valueOf(Math.round((float)avg3/i)), wcf2));
						ws.addCell(new Label(5, 2 + i+k, String.valueOf(Math.round((float)avg4/i)), wcf2));
						ws.addCell(new Label(6, 2 + i+k, String.valueOf(Math.round((float)avg5/i)), wcf2));
						ws.addCell(new Label(7, 2 + i+k, String.valueOf(Math.round((float)avg6/i)), wcf2));
						ws.addCell(new Label(8, 2 + i+k, String.valueOf(Math.round((float)avg7/i)), wcf2));
						ws.addCell(new Label(9, 2 + i+k, String.valueOf(Math.round((float)avg8/i)), wcf2));
						ws.addCell(new Label(10, 2 + i+k, String.valueOf(Math.round((float)avg9/i)), wcf2));
						ws.addCell(new Label(11, 2 + i+k, String.valueOf(Math.round((float)avg10/i)), wcf2));
						ws.addCell(new Label(12, 2 + i+k, String.valueOf(Math.round((float)avg11/i)), wcf2));
						avg1=0;
						 avg2=0;
						 avg3=0;
						 avg4=0;
						 avg5=0;
						 avg6=0;
						 avg7=0;
						 avg8=0;
						 avg9=0;
						 avg10=0;
						 avg11=0;
						k++;
					}
					ws.addCell(new Label(0, 2 + i+k, map.get("xm"), wcf2));
					ws.addCell(new Label(1, 2 + i+k, null==map.get("xymc")?"":map.get("xymc"), wcf2));
					ws.addCell(new Label(2, 2 + i+k, null==map.get("gzlb0")?"":map.get("gzlb0"), wcf2));
					ws.addCell(new Label(3, 2 + i+k, null==map.get("gzlb1")?"":map.get("gzlb1"), wcf2));
					ws.addCell(new Label(4, 2 + i+k, null==map.get("gzlb2")?"":map.get("gzlb2"), wcf2));
					ws.addCell(new Label(5, 2 + i+k, null==map.get("gzlb3")?"":map.get("gzlb3"), wcf2));
					ws.addCell(new Label(6, 2 + i+k, null==map.get("gzlb4")?"":map.get("gzlb4"), wcf2));
					ws.addCell(new Label(7, 2 + i+k, null==map.get("gzlb5")?"":map.get("gzlb5"), wcf2));
					ws.addCell(new Label(8, 2 + i+k, null==map.get("gzlb6")?"":map.get("gzlb6"), wcf2));
					ws.addCell(new Label(9, 2 + i+k, null==map.get("gzlb7")?"":map.get("gzlb7"), wcf2));
					ws.addCell(new Label(10, 2 + i+k, null==map.get("gzlb8")?"":map.get("gzlb8"), wcf2));
					ws.addCell(new Label(11, 2 + i+k, null==map.get("gzlb9")?"":map.get("gzlb9"), wcf2));
					ws.addCell(new Label(12, 2 + i+k, null==map.get("gzlb10")?"":map.get("gzlb10"), wcf2));
					avg1+=Integer.parseInt(null==map.get("gzlb0")?"0":map.get("gzlb0"));
					avg2+=Integer.parseInt(null==map.get("gzlb1")?"0":map.get("gzlb1"));
					avg3+=Integer.parseInt(null==map.get("gzlb2")?"0":map.get("gzlb2"));
					avg4+=Integer.parseInt(null==map.get("gzlb3")?"0":map.get("gzlb3"));
					avg5+=Integer.parseInt(null==map.get("gzlb4")?"0":map.get("gzlb4"));
					avg6+=Integer.parseInt(null==map.get("gzlb5")?"0":map.get("gzlb5"));
					avg7+=Integer.parseInt(null==map.get("gzlb6")?"0":map.get("gzlb6"));
					avg8+=Integer.parseInt(null==map.get("gzlb7")?"0":map.get("gzlb7"));
					avg9+=Integer.parseInt(null==map.get("gzlb8")?"0":map.get("gzlb8"));
					avg10+=Integer.parseInt(null==map.get("gzlb9")?"0":map.get("gzlb9"));
					avg11+=Integer.parseInt(null==map.get("gzlb10")?"0":map.get("gzlb10"));
					xys_one++;
					
				}
				ws.mergeCells(0,2 + resultList.size()+k, 1, 2 + resultList.size()+k);
				ex.printToOneCell_multy(ws, "平均数据统计分析", 0, 2 + resultList.size()+k, 11, false,
						Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
						Border.ALL);
				ws.addCell(new Label(2, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg1/xys_one)), wcf2));
				ws.addCell(new Label(3, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg2/xys_one)), wcf2));
				ws.addCell(new Label(4, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg3/xys_one)), wcf2));
				ws.addCell(new Label(5, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg4/xys_one)), wcf2));
				ws.addCell(new Label(6, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg5/xys_one)), wcf2));
				ws.addCell(new Label(7, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg6/xys_one)), wcf2));
				ws.addCell(new Label(8, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg7/xys_one)), wcf2));
				ws.addCell(new Label(9, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg8/xys_one)), wcf2));
				ws.addCell(new Label(10, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg9/xys_one)), wcf2));
				ws.addCell(new Label(11, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg10/xys_one)), wcf2));
				ws.addCell(new Label(12, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg11/xys_one)), wcf2));
			}
		}else{
		ws.addCell(new Label(0, 1, "姓名", wcf2));
		ws.addCell(new Label(1, 1, "工作类别1(次数）", wcf2));
		ws.addCell(new Label(2, 1, "工作类别2(次数）", wcf2));
		ws.addCell(new Label(3, 1, "工作类别3(次数）", wcf2));
		ws.addCell(new Label(4, 1, "工作类别4(次数）", wcf2));
		ws.addCell(new Label(5, 1, "工作类别5(次数）", wcf2));
		ws.addCell(new Label(6, 1, "工作类别6(次数）", wcf2));
		ws.addCell(new Label(7, 1, "工作类别7(次数）", wcf2));
		ws.addCell(new Label(8, 1, "工作类别8(次数）", wcf2));
		ws.addCell(new Label(9, 1, "工作类别9(次数）", wcf2));
		ws.addCell(new Label(10, 1, "工作类别10(次数）", wcf2));
		ws.addCell(new Label(11, 1, "工作类别11(次数）", wcf2));
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				
				ws.setColumnView(0, 10);
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
				ws.addCell(new Label(0, 2 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(1, 2 + i, null==map.get("gzlb0")?"":map.get("gzlb0"), wcf2));
				ws.addCell(new Label(2, 2 + i, null==map.get("gzlb1")?"":map.get("gzlb1"), wcf2));
				ws.addCell(new Label(3, 2 + i, null==map.get("gzlb2")?"":map.get("gzlb2"), wcf2));
				ws.addCell(new Label(4, 2 + i, null==map.get("gzlb3")?"":map.get("gzlb3"), wcf2));
				ws.addCell(new Label(5, 2 + i, null==map.get("gzlb4")?"":map.get("gzlb4"), wcf2));
				ws.addCell(new Label(6, 2 + i, null==map.get("gzlb5")?"":map.get("gzlb5"), wcf2));
				ws.addCell(new Label(7, 2 + i, null==map.get("gzlb6")?"":map.get("gzlb6"), wcf2));
				ws.addCell(new Label(8, 2 + i, null==map.get("gzlb7")?"":map.get("gzlb7"), wcf2));
				ws.addCell(new Label(9, 2 + i, null==map.get("gzlb8")?"":map.get("gzlb8"), wcf2));
				ws.addCell(new Label(10, 2 + i, null==map.get("gzlb9")?"":map.get("gzlb9"), wcf2));
				ws.addCell(new Label(11, 2 + i, null==map.get("gzlb10")?"":map.get("gzlb10"), wcf2));
			}
		}
		}
		ws.mergeCells(0,5+k+ resultList.size(), 4, 5+k+ resultList.size());
		ex.printToOneCell_multy(ws, "工作类别备注:", 0, 5+k+ resultList.size(), 11, true,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		for (int i = 0; i < lblist.size(); i++) {
			ws.mergeCells(0,6+i+k+ resultList.size(), 4, 6+i+k+ resultList.size());
			ex.printToOneCell_multy(ws, i+1+"、"+lblist.get(i).get("gzlbmc"), 0, 6+i+k+ resultList.size(), 11, false,
					Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
					Border.NONE);
		}
		ws.mergeCells(9, resultList.size()+4+k, 12, resultList.size()+4+k);
		ws.mergeCells(9, resultList.size()+3+k, 12, resultList.size()+3+k);
		ex.printToOneCell_multy(ws, lkr, 9, resultList.size()+k+3, 11, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, false, 700,
				Border.NONE);
		ex.printToOneCell_multy(ws, GetTime.getNowTime(), 9, resultList.size()+4+k, 11, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		return file;
	}
	
	public File print(GzjljgForm myForm) throws Exception{
		GzjljgForm jljgForm=dao.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(jljgForm));
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("rs",myForm);
		return getWord(data,myForm);
	}
	private File getWord(Map<String, Object> data,GzjljgForm myForm) throws FileNotFoundException {
		String templatePath = Constants.TEP_DIR+"szdw/gzjldjb.xml";
		File wordFile = null;
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "szdw", "gzjldjb.xml", FreeMarkerUtil.getFileName(myForm.getZgh() +"["+myForm.getXm()+"]"));
			
		}catch (Exception e) {
			
			
		}

		return wordFile;
	
	}
	private File createFile() {
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		// 创建导出文件
		File file = new File(tempDir.getPath() + "/"
				+ "工作汇总" + ".xls");
		file.setWritable(true);
		return file;
	}
	
	/** 
	 * @描述:得到谈话对象
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-18 上午10:47:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getThdxList(String[] xhArr) {
		return dao.getThdxList(xhArr);		
	}
	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-4-19 下午01:49:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZghList(GzjljgForm model, User user) throws Exception {
		// TODO 自动生成方法存根
		return dao.getZghList(model, user);
	}
	/**
	 * @throws Exception  
	 * @描述:根据职工号获取条件时间内的工作记录结果(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：982]
	 * @日期：2018-4-19 下午02:14:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param searchModel
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGzjlListByZgh(String zgh,String lbmc,
			SearchModel searchModel, User user) throws Exception {
		return dao.getGzjlListByZgh(zgh,lbmc,searchModel, user);
	}
	


}
