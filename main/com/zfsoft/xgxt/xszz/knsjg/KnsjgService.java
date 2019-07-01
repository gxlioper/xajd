/**
 * @部门:学工产品事业部
 * @日期：2013-4-20 下午01:16:38 
 */  
package com.zfsoft.xgxt.xszz.knsjg;


import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.ExcelMB;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcDao;




/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 困难生查询管理模块
 * @类功能描述: Service
 * @作者： maxh
 * @时间： 2013-4-20 下午01:16:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KnsjgService extends SuperServiceImplExtend<KnsjgForm, KnsjgDao>{

     private KnsjgDao dao = new KnsjgDao();
	
	public KnsjgService(){
		super.setDao(dao);
	}
	/**
	 * 
	 * @描述:唯一性判断（学号，学年，学期）
	 * @作者：maxh
	 * @日期：2013-4-23 上午09:16:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	 public boolean isExistByKnsjg(KnsjgForm model, String type)throws Exception{
		    boolean flag = false;
			if("save".equalsIgnoreCase(type)){
				String num=dao.checkExistForSave(model);
				if(!"0".equalsIgnoreCase(num)){
					flag = true;
				}
			}else if("update".equalsIgnoreCase(type)){
				String num=dao.checkExistForUpdate(model);
				if(!"0".equalsIgnoreCase(num)){
					flag = true;
				}	
			}
	    	return  flag;
	}
	 /**
	 * @throws Exception 
	  * 
	  * @描述:获得单个学生认定信息
	  * @作者：maxh
	  * @日期：2013-4-23 下午03:13:06
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param xh
	  * @param wjsj
	  * @param request
	  * @return
	  * List<String[]> 返回类型 
	  * @throws
	  */
	public Map<String, String> getOneKnsjgList(String  guid) throws Exception {
		 
		return dao.getOneKnsjgList(guid);
	}
	 
	/**
	 *  
	 * @描述:TODO(获取取消困难生资格)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-28 上午11:08:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * @throws Exception
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneKnsqxjlList(String guid) throws Exception {
		 
		return dao.getOneKnsqxjlList(guid);
	}
	 
	 /**
	  * 
	  * @描述:按学号查询全部困难生信息
	  * @作者：Penghui.Qu
	  * @日期：2013-4-25 下午03:19:13
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param xh
	  * @return
	  * List<HashMap<String,String>> 返回类型 
	  * @throws
	  */
	 public List<HashMap<String,String>> getKnsInfoList(String xh){
		 
		 if (StringUtil.isNull(xh)){
			 logger.error("xh can't null !");
			 throw new NullPointerException();
		 }
		 
		 return dao.getKnsInfoList(xh);
	 }
	 	 		
		
		/**
		 * 
		 * @描述:困难生结果表-学年
		 * @作者：ligl
		 * @日期：2013-4-19 下午02:19:00
		 * @修改记录:
		 * @return List<HashMap<String,String>> 返回类型
		 * @throws
		 */
		public List<HashMap<String, String>> getKnsjgXn() throws Exception {
			return dao.getKnsjgXn();
		}
		
		/**
		 * 
		 * @描述:困难生结果表-学期
		 * @作者：ligl
		 * @日期：2013-4-19 下午02:19:00
		 * @修改记录:
		 * @return List<HashMap<String,String>> 返回类型
		 * @throws
		 */
		public List<HashMap<String, String>> getKnsjgXq() throws Exception {
			List<HashMap<String, String>> knsjqXq2 = new ArrayList<HashMap<String,String>>();
			List<HashMap<String, String>> knsjqXq = dao.getKnsjgXq();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "");
			map.put("mc", "");
	//		knsjqXq2.add(map);
			
			for (HashMap<String, String> hashMap : knsjqXq) {
				knsjqXq2.add(hashMap);
			}			
			return knsjqXq2;
		}
		
		/**
		 * 
		 * @描述:困难生结果表-周期
		 * @作者：cq [工号：785]
		 * @日期：2013-10-15 上午11:52:44
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @return
		 * @throws Exception
		 * List<HashMap<String,String>> 返回类型 
		 * @throws
		 */
		public List<HashMap<String, String>> getKnsjgZq() throws Exception {
			return dao.getKnsjgZq();
		}
		
		/**
		 * 
		 * @描述: 获取学生困难评定情况
		 * @作者：honglin
		 * @日期：2013-5-4
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param t
		 * @return
		 * String 返回类型 
		 * @throws
		 */
		public HashMap<String,String> getXsknsjg (String xh ,String xn ,String xq){
			
			if (StringUtil.isNull(xh)){
				logger.error("学号不能为空！");
				throw new NullPointerException();
			}
			return dao.getXsknsjg(xh,xn,xq);
		}
		
		/**
		 * 
		 * @描述:获取困难生结果表-周期，去掉特定周期
		 * @作者：陶钢军 [1075]
		 * @日期：2014-7-1 上午11:32:45
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param knsjgzq
		 * @return
		 * List<HashMap<String,String>> 返回类型 
		 * @throws
		 */
		public List<HashMap<String, String>> getKnsjgZqListNotIsHave(String knsjgzq) {
			List<HashMap<String, String>> list = dao.getKnsjgZqList();
			for (HashMap<String, String> hm : list) {
				if (hm.get("zqdm").equals(knsjgzq)) {
					list.remove(hm);
					break;
				}
			}
			return list;
		}
		
		/**
		 * 
		 * @描述:TODO(这里用一句话描述这个方法的作用)
		 * @作者：陶钢军 [1075]
		 * @日期：2014-7-1 下午03:12:32
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param lyxn
		 * @param lyxq
		 * @param xn
		 * @param xq
		 * @return
		 * @throws Exception
		 * boolean 返回类型 
		 * @throws
		 */
		public String copy(String lyxn, String lyxq, String xn, String xq)
				throws Exception {
			int okNum = 0; // 成功数量
			boolean result = true; // 操作结果
			StringBuilder notOk = new StringBuilder(); // 重复的记录
			List<HashMap<String, String>> list = dao.getKnsjgForXnXq(lyxn, lyxq);
			KnsjgForm model = new KnsjgForm();
			for (HashMap<String, String> hm : list) {
				BeanUtils.copyProperties(model, hm);
				model.setGuid(null);
				model.setXn(xn);
				model.setXq(xq);
				model.setSjly("0");
				// 是否重复
				String num=dao.checkExistForSave(model);
				if(!"0".equalsIgnoreCase(num)){
					notOk.append(model.getXh() + " " + model.getXm() + "；");
				}else{
					result = runInsert(model);
					if(result){
						okNum++;
					}
				}
			}
			String resultMsg = "";
			if(result){
				resultMsg = "复制成功"+okNum+"条！";
				if(list.size() - okNum > 0){
					resultMsg += "重复的记录：" + notOk.toString().substring(0, notOk.toString().length() - 1);
				}
			}else{
				resultMsg = "复制失败！";
			}
			return resultMsg;
		}
	/**
	 * 
	 * @描述:困难生统计导出（贵州大学）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-20 下午03:15:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param os
	 * @param user
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void knsrdTjExport(List<HashMap<String,String>> resultList, OutputStream os, User user) throws Exception {
		List<HashMap<String, String>> rddcList = new KnsdcDao().getKnsdcList();
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("家庭经济困难学生认定统计表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ex.printToOneCell_multy(ws, "", 0, 0, 10, true,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ex.printToOneCell_multy(ws, "", 11, 0, 10,
				true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ws.mergeCells(0, 0, 10, 0);
		ws.mergeCells(11, 0, 19, 0);
		ws.mergeCells(13, 1, 13+rddcList.size()-1, 1);
		ws.mergeCells(0, 1, 0, 2);
		ws.mergeCells(1, 1, 1, 2);
		ws.mergeCells(2, 1, 2, 2);
		ws.mergeCells(3, 1, 3, 2);
		ws.mergeCells(4, 1, 4, 2);
		ws.mergeCells(5, 1, 5, 2);
		ws.mergeCells(6, 1, 6, 2);
		ws.mergeCells(7, 1, 7, 2);
		ws.mergeCells(8, 1, 8, 2);
		ws.mergeCells(9, 1, 9, 2);
		ws.mergeCells(10, 1, 10, 2);
		ws.mergeCells(11, 1, 11, 2);
		ws.mergeCells(12, 1, 12, 2);
		ex.printToOneCell_multy(ws, "序号", 0, 1, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.ALL);
		ws.addCell(new Label(1, 1, "姓名", wcf2));
		ws.addCell(new Label(2, 1, "性别", wcf2));
		ws.addCell(new Label(3, 1, "学院", wcf2));
		ws.addCell(new Label(4, 1, "专业", wcf2));
		ws.addCell(new Label(5, 1, "学号", wcf2));
		ws.addCell(new Label(6, 1, "身份证号", wcf2));
		ws.addCell(new Label(7, 1, "工行一卡通", wcf2));
		ws.addCell(new Label(8, 1, "联系电话", wcf2));
		ws.addCell(new Label(9, 1, "政治面貌", wcf2));
		ws.addCell(new Label(10, 1, "户籍性质", wcf2));
		ws.addCell(new Label(11, 1, "民族", wcf2));
		ws.addCell(new Label(12, 1, "家庭详细地址", wcf2));
		ws.addCell(new Label(13, 1, "认定档次", wcf2));
		for (int i = 0; i < rddcList.size(); i++) {
			ws.addCell(new Label(i+13, 2, rddcList.get(i).get("dcmc"), wcf2));	
		}
	
		if (resultList != null && resultList.size() > 0) {
			
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 8);
				ws.setColumnView(2, 7);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 20);
				ws.setColumnView(5, 15);
				ws.setColumnView(6, 20);
				ws.setColumnView(7, 20);
				ws.setColumnView(8, 15);
				ws.setColumnView(9, 15);
				ws.setColumnView(10, 8);
				ws.setColumnView(11, 8);
				ws.setColumnView(12, 20);
				for (int j = 0; j < rddcList.size(); j++) {
					ws.setColumnView(j+13, 8);
				}
				ws.addCell(new Label(0, 3 + i, i+1+"", wcf2));// 序号
				ws.addCell(new Label(1, 3 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(2, 3 + i, map.get("xb"), wcf2));
				ws.addCell(new Label(3, 3 + i, map.get("xymc"), wcf2));
				ws.addCell(new Label(4, 3 + i, map.get("zymc"), wcf2));
				ws.addCell(new Label(5, 3 + i, map.get("xh"), wcf2));
				ws.addCell(new Label(6, 3 + i, map.get("sfzh"), wcf2));
				ws.addCell(new Label(7, 3 + i, map.get("yhkh"), wcf2));
				ws.addCell(new Label(8, 3 + i, map.get("sjhm"), wcf2));
				ws.addCell(new Label(9, 3 + i, map.get("zzmmmc"), wcf2));
				ws.addCell(new Label(10, 3 + i, map.get("zd5"), wcf2));
				ws.addCell(new Label(11, 3 + i, map.get("mzmc"), wcf2));
				ws.addCell(new Label(12, 3 + i, map.get("jtdz"), wcf2));
				for (int k = 0; k < rddcList.size(); k++) {
					if(map.get("dcmc").equals(rddcList.get(k).get("dcmc"))){
						ws.addCell(new Label(13+k, 3 + i, "是", wcf2));
					}else{
						ws.addCell(new Label(13+k, 3 + i, "", wcf2));
					}
					
				}
				
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * @描述：
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月30日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param resultList
	 * @param os
	 * @param user
	 * @throws Exception
	 * void 返回类型
	 */
	public File exportDashk(List<HashMap<String,String>> resultList,File file, User user) throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(file);
		WritableSheet ws = wwb.createSheet("浙江大学经济困难学生汇总表", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 10, 0);
		ws.mergeCells(0, 1, 10, 1);
		ex.printToOneCell_multy(ws, "浙江大学经济困难学生汇总表", 0, 0, 15, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,	Border.ALL);
		ex	.printToOneCell_multy(ws, "学院（系）、学园：                                                          负责人（公章）", 0, 1, 10, true,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,Border.ALL);
		ws.addCell(new Label(0, 2, "序号", wcf2));
		ws.addCell(new Label(1, 2, "学院（系）、学园", wcf2));
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 20);
				ws.addCell(new Label(0, 3 + i, i+1+"", wcf2));// 序号
				ws.addCell(new Label(1, 3 + i, map.get("xymc"), wcf2));
				ws.addCell(new Label(2, 3 + i, map.get("xh"), wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		return file;
	}
	
	public void knsExport(List<HashMap<String,String>> resultList, OutputStream os, User user) throws Exception {
		List<HashMap<String, String>> rddcList = new KnsdcDao().getKnsdcList();
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("浙江大学经济困难学生汇总表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 10, 0);
		ws.mergeCells(0, 1, 10, 1);
		ex.printToOneCell_multy(ws, "浙江大学经济困难学生汇总表", 0, 0, 15, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ex
				.printToOneCell_multy(ws, "学院（系）、学园：                                                          负责人（公章）", 0, 1, 10, true,
						Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
						Border.ALL);


		ws.addCell(new Label(0, 2, "序号", wcf2));
		ws.addCell(new Label(1, 2, "学院（系）、学园", wcf2));
		ws.addCell(new Label(2, 2, "学号", wcf2));
		ws.addCell(new Label(3, 2, "姓名", wcf2));
		ws.addCell(new Label(4, 2, "民族", wcf2));
		ws.addCell(new Label(5, 2, "生源地", wcf2));
		ws.addCell(new Label(6, 2, "家庭人均年收入", wcf2));
		ws.addCell(new Label(7, 2, "家庭子女（在读高中及以上）人数", wcf2));
		ws.addCell(new Label(8, 2, "学生情况", wcf2));
		ws.addCell(new Label(9, 2, "联系电话", wcf2));
		ws.addCell(new Label(10, 2, "困难等级", wcf2));
		
		if (resultList != null && resultList.size() > 0) {
			
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 15);
				ws.setColumnView(3, 10);
				ws.setColumnView(4, 10);
				ws.setColumnView(5, 15);
				ws.setColumnView(6, 10);
				ws.setColumnView(7, 10);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 15);
				ws.setColumnView(10, 10);
				ws.addCell(new Label(0, 3 + i, i+1+"", wcf2));// 序号
				ws.addCell(new Label(1, 3 + i, map.get("xymc"), wcf2));
				ws.addCell(new Label(2, 3 + i, map.get("xh"), wcf2));
				ws.addCell(new Label(3, 3 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(4, 3 + i, map.get("mzmc"), wcf2));
				ws.addCell(new Label(5, 3 + i, map.get("sydss"), wcf2));
				ws.addCell(new Label(6, 3 + i, map.get("jtrjsr"), wcf2));
				ws.addCell(new Label(7, 3 + i, "", wcf2));
				ws.addCell(new Label(8, 3 + i, map.get("sqly"), wcf2));
				ws.addCell(new Label(9, 3 + i, map.get("sjhm"), wcf2));
				ws.addCell(new Label(10, 3 + i, map.get("dcmc"), wcf2));
				
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	 
	
	public List<HashMap<String,String>> getKnsdcList(){
		return  dao.getKnsdcList();
	}
	
	public HashMap<String, String> getKnspd(String xh,String xn){
		return dao.getKnspd(xh, xn);
	}
	
	public List<HashMap<String,String>> knstksPercent(KnsjgForm model) throws Exception{
		return dao.knstksPercent(model);
	}
	/**
	 * @描述: 浙江大学学生资助申请表非取消认定的困难生信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-11-25 上午10:35:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getKnsxx (String xh){
		if (StringUtil.isNull(xh)){
			logger.error("学号不能为空！");
			throw new NullPointerException();
		}
		return dao.getKnsxx(xh);
	}
	
	/**
	 * @描述：查询条件下的困难学生的学院代码与名称
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月3日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getKnsjgXy(KnsjgForm t, User user) throws Exception{
		return dao.getKnsjgXy(t,user);
	}
	
	/**
	 * @描述：查询条件下的单个学院的困难学生
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月3日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @param xydm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getKnsjgListByxy(KnsjgForm t, User user, String xydm) throws Exception{
		return dao.getKnsjgListByxy(t,user,xydm);
	}
	
	public List<HashMap<String,String>> getKnsjgBj(KnsjgForm t, User user) throws Exception{
		return dao.getKnsjgBj(t,user);
	}
	
	public List<HashMap<String,String>> getKnsjgListBybj(KnsjgForm t, User user, String bjdm) throws Exception{
		return dao.getKnsjgListBybj(t,user,bjdm);
	}
	
	/**
	 * @描述：打印
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月4日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param datalist
	 * @param fileName
	 * @param template
	 * @return
	 * @throws Exception
	 * File 返回类型
	 */
	public File printFile(List<HashMap<String, String>> datalist,String fileName,String template) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("datalist", datalist);
		data.putAll(datalist.get(0));
		data.put("rs", datalist.size());
		data.put("sysdate", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
		File file=null;
		if("knsrdpxb_12898.xml".equals(template)){
			int blanksize=(16 - datalist.size())<0?0:(16 - datalist.size());
			List<HashMap<String,String>> blanklist = new ArrayList<HashMap<String,String>>(blanksize);
			for (int i = 0 ; i < blanksize ; i++){
				blanklist.add(new HashMap<String, String>());
			}
			data.put("blankList", blanklist);
			file = FreeMarkerUtil.getWordFile(data, "classpath:templates/xszz", template, fileName);
		}else{
			file = FreeMarkerUtil.getExcelFile(data, "classpath:templates/xszz", template, fileName);
		}
		return file;
	}
	
	public void gwcjffDc(ServletOutputStream outputStream, KnsjgForm model) throws Exception {
		String title = "家庭经济困难（特困生）学生情况统计表";

		List<HashMap<String,String>> dataList=dao.getKnstj_12688(model);
		List<HashMap<String,String>> dataList2=dao.getKnstj2_12688(model);
		WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
		
		WritableSheet ws = wwb.createSheet(title, 0);
		
		WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
		wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 11, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true,Border.ALL);
		//ws.setRowView(3, 1500, false);  
		// 填充表头
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 30, 800);// 标题
		ws.mergeCells(0, 1, 0, 2);
		ws.addCell(new Label(0, 1, "年级/人数/系部", wcf));
		
		List<String>nj=dao.getNj();
		List<String>xy=dao.getXy();
		
		for(int j=0;j<nj.size();j++){
			ws.addCell(new Label(0, j+3, nj.get(j)+"级", wcf));
		}
		xy.add("合计");
		for(int i=0; i < xy.size(); i++){
			ws.mergeCells(i*4+1, 1, i*4+4, 1);
			ws.addCell(new Label(i*4+1, 1, xy.get(i), wcf));
			ws.addCell(new Label(i*4+1, 2, "学生数", wcf));
			ws.mergeCells(i*4+2, 2, i*4+3, 2);
			ws.addCell(new Label(i*4+2, 2, "贫困生数", wcf));
			ws.addCell(new Label(i*4+4, 2, "特困生数", wcf));
			ws.mergeCells(i*4+1, nj.size()+4, i*4+4, nj.size()+4);
			ws.mergeCells(i*4+1, nj.size()+5, i*4+4, nj.size()+5);
		}
		
		ws.addCell(new Label(0, nj.size()+3, "小计", wcf));
		ws.addCell(new Label(0, nj.size()+4, "各院系部贫困生数占全院贫困生总数比例", wcf));
		ws.addCell(new Label(0, nj.size()+5, "各院系部特困生数占全院特困生总数比例", wcf));
		
		HashMap<String,String> temp=null;
		for(int j=0,count=0;j<nj.size()+1;j++){
			for(int i=0; i < xy.size(); i++,count++){
				temp=dataList.get(count);
				ws.addCell(new Label(i*4+1, j+3, temp.get("rs"), wcf));
				ws.addCell(new Label(i*4+2, j+3, temp.get("pks"), wcf));
				ws.addCell(new Label(i*4+3, j+3, temp.get("pksbfb"), wcf));
				ws.addCell(new Label(i*4+4, j+3, temp.get("tks"), wcf));
			}
		}
		//各院系贫困生/特困生占比
		for(int i=0,count=0; i < xy.size()-1; i++,count++){
			temp=dataList2.get(count);
			ws.addCell(new Label(i*4+1, nj.size()+4, temp.get("pksbfb"), wcf));
			ws.addCell(new Label(i*4+1, nj.size()+5, temp.get("tksbfb"), wcf));
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	
	}
	
	/**
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * 
	 * @描述: 获取徐州工程导出的困难生结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-26 上午11:33:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXzyyExportData(KnsjgForm t, User user) throws Exception{
		return dao.getXzyyExportData(t, user);
	}
	

	/**
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * 
	 * @描述: 浙江中医药个性化导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-26 上午11:33:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZjzyyExportData(KnsjgForm t, User user) throws Exception{
		return dao.getZjzyyExportData(t, user);
	}
	
	/** 
	 * @描述:获取学生困难认定档次(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-18 上午09:53:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getXsrddc(String xh){
		return dao.getXsrddc(xh);
	}
	/**
	 * @description	：生成江西陶瓷汇总表
	 * @author 		： CP（1352）
	 * @date 		：2017-12-1 下午02:25:15
	 * @param resultList
	 * @param os
	 * @param user
	 * @throws Exception
	 */
	public void rdhzbExport(List<HashMap<String, String>> resultList,
			OutputStream os, User user) throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("经济困难学生汇总表", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, Border.ALL);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 8, 0);
		ws.mergeCells(0, 1, 8, 1);
		ex.printToOneCell_multy(ws, "江西陶瓷工艺美术职业技术学院经济困难学生汇总表", 0, 0, 15, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ex.printToOneCell_multy(ws, "经办人：                                                   ", 0, 1, 8, true,
						Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
						Border.ALL);

		ws.addCell(new Label(0, 2, "序号", wcf2));
		ws.addCell(new Label(1, 2, "姓名", wcf2));
		ws.addCell(new Label(2, 2, "身份证件类型", wcf2));
		ws.addCell(new Label(3, 2, "身份证件号", wcf2));
		ws.addCell(new Label(4, 2, "认定困难级别名称", wcf2));
		ws.addCell(new Label(5, 2, "认定时间", wcf2));
		ws.addCell(new Label(6, 2, "认定原因", wcf2));
		ws.addCell(new Label(7, 2, "班级认定意见", wcf2));
		ws.addCell(new Label(8, 2, "年级认定意见", wcf2));
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 10);
				ws.setColumnView(2, 15);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 15);
				ws.setColumnView(5, 15);
				ws.setColumnView(6, 15);
				ws.setColumnView(7, 25);
				ws.setColumnView(8, 25);
				ws.addCell(new Label(0, 3 + i, i+1+"", wcf2));// 序号
				ws.addCell(new Label(1, 3 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(2, 3 + i, "居民身份证", wcf2));
				ws.addCell(new Label(3, 3 + i, map.get("sfzh"), wcf2));
				ws.addCell(new Label(4, 3 + i, map.get("dcmc"), wcf2));
				ws.addCell(new Label(5, 3 + i, DateUtils.getDayOfLongt(map.get("sqsj").substring(0,10)), wcf2));
				ws.addCell(new Label(6, 3 + i, map.get("ylzd9"), wcf2));
				ws.addCell(new Label(7, 3 + i, "家庭情况描述属实，给予认定", wcf2));
				ws.addCell(new Label(8, 3 + i, "家庭情况描述属实，给予认定", wcf2));
				
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 
	 * @描述: 获取学院名称
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-18 上午10:20:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXymc(String xydm){
		return dao.getXymc(xydm);
	}
	
	/**
	 * @description	： 武昌首义
	 * @author 		： CP（1352）
	 * @date 		：2018-1-15 下午01:50:42
	 * @param resultList
	 * @param outputStream
	 * @param user
	 * @throws Exception 
	 */
	public void rdhzbExport_12309(List<HashMap<String, String>> resultList,
			OutputStream os, User user) throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("困难学生认定汇总表", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, Border.ALL);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 8, 0);
		ws.mergeCells(0, 1, 8, 1);
		ex.printToOneCell_multy(ws, "武昌首义学院经济困难学生汇总表", 0, 0, 15, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ex.printToOneCell_multy(ws, "经办人：                                                   ", 0, 1, 8, true,
						Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
						Border.ALL);

		ws.addCell(new Label(0, 2, "序号", wcf2));
		ws.addCell(new Label(1, 2, "姓名", wcf2));
		ws.addCell(new Label(2, 2, "身份证件类型", wcf2));
		ws.addCell(new Label(3, 2, "身份证件号", wcf2));
		ws.addCell(new Label(4, 2, "认定困难级别名称", wcf2));
		ws.addCell(new Label(5, 2, "认定时间", wcf2));
		ws.addCell(new Label(6, 2, "认定原因", wcf2));
		ws.addCell(new Label(7, 2, "班级认定意见", wcf2));
		ws.addCell(new Label(8, 2, "年级认定意见", wcf2));
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 10);
				ws.setColumnView(2, 15);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 15);
				ws.setColumnView(5, 15);
				ws.setColumnView(6, 15);
				ws.setColumnView(7, 25);
				ws.setColumnView(8, 25);
				ws.addCell(new Label(0, 3 + i, i+1+"", wcf2));// 序号
				ws.addCell(new Label(1, 3 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(2, 3 + i, map.get("ylzd8"), wcf2));
				ws.addCell(new Label(3, 3 + i, map.get("sfzh"), wcf2));
				ws.addCell(new Label(4, 3 + i, map.get("dcmc"), wcf2));
				ws.addCell(new Label(5, 3 + i, DateUtils.getDayOfLongt(map.get("sqsj").substring(0,10)), wcf2));
				ws.addCell(new Label(6, 3 + i, map.get("sqly"), wcf2));
				ws.addCell(new Label(7, 3 + i, "家庭情况描述属实，给予认定", wcf2));
				ws.addCell(new Label(8, 3 + i, "家庭情况描述属实，给予认定", wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * @description	： 河北民族师范学院
	 * @author 		： CP（1352）
	 * @date 		：2018-1-15 下午01:50:42
	 * @param resultList
	 * @param outputStream
	 * @param user
	 * @throws Exception
	 */
	public void rdhzbExport_10098(List<HashMap<String, String>> resultList,
								  OutputStream os, User user) throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("困难学生认定汇总表", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, Border.ALL);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 8, 0);
		ex.printToOneCell_multy(ws, "河北民族师范学院家庭经济困难学生认定汇总表", 0, 0, 15, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ws.addCell(new Label(0, 1, "序号", wcf2));
		ws.addCell(new Label(1, 1, "姓名", wcf2));
		ws.addCell(new Label(2, 1, "身份证件类型", wcf2));
		ws.addCell(new Label(3, 1, "身份证件号", wcf2));
		ws.addCell(new Label(4, 1, "认定困难级别名称", wcf2));
		ws.addCell(new Label(5, 1, "认定时间", wcf2));
		ws.addCell(new Label(6, 1, "认定原因", wcf2));
		ws.addCell(new Label(7, 1, "班级认定意见", wcf2));
		ws.addCell(new Label(8, 1, "年级认定意见", wcf2));
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 10);
				ws.setColumnView(2, 15);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 15);
				ws.setColumnView(5, 15);
				ws.setColumnView(6, 15);
				ws.setColumnView(7, 25);
				ws.setColumnView(8, 25);
				ws.addCell(new Label(0, 2 + i, i+1+"", wcf2));// 序号
				ws.addCell(new Label(1, 2 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(2, 2 + i, map.get("ylzd8"), wcf2));
				ws.addCell(new Label(3, 2 + i, map.get("sfzh"), wcf2));
				ws.addCell(new Label(4, 2 + i, map.get("dcmc"), wcf2));
				ws.addCell(new Label(5, 2 + i, DateUtils.getDayOfLongt(map.get("sqsj").substring(0,10)), wcf2));
				ws.addCell(new Label(6, 2 + i, map.get("sqly"), wcf2));
				ws.addCell(new Label(7, 2 + i, "家庭情况描述属实，给予认定", wcf2));
				ws.addCell(new Label(8, 2 + i, "家庭情况描述属实，给予认定", wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * @description	： 困难生认定导出
	 * @author 		： lj（1282）
	 * @date 		：2018-4-24 下午05:52:48
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> knsrddc(KnsjgForm t,User user) throws Exception{
		return dao.knsrddc(t,user);
	}
	
	/**
	 * @description	： 家庭信息导出
	 * @author 		： lj（1282）
	 * @date 		：2018-4-25 上午11:02:00
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> jtxxdc(KnsjgForm t,User user) throws Exception{
		return dao.jtxxdc(t, user);
	}
	
	/**
	 * @description	： 困难生建档立卡导出
	 * @author 		： lj（1282）
	 * @date 		：2018-4-25 下午03:07:03
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> knsjdlkdc(KnsjgForm t,User user) throws Exception{
		return dao.knsjdlkdc(t, user);
	}
	public HashMap<String, String> getKnsInfo(String xh,String xn){
		return dao.getKnsInfo(xh,xn);
	}
}
