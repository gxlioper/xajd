/**
 * @部门:学工产品事业部
 * @日期：2018-4-20 下午04:02:24 
 */  
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxfxwh;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.MoneyFormat;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.jqfxgl.jqfxjbsz.JqfxJbszForm;
import com.zfsoft.xgxt.rcsw.jqfxgl.jqfxjbsz.JqfxJbszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： lgx[工号:1553]
 * @时间： 2018-4-20 下午04:02:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JqfxFxwhService  extends SuperServiceImpl<JqfxFxwhForm, JqfxFxwhDao> {
	public static String JQFXWWHZT = "0";
	public static String JQFXWHZT = "1";
	
	/**
	 * 
	 * @描述:TODO(返校名称)
	 * @作者： lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:24 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getFxmc()throws Exception {
			return dao.getFxmc();
	}
		
	/**
	 * 
	 * @描述:TODO(未返校)
	 * @作者： lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:24 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneFxgljgList(String  xh) {
		    return dao.getOneFxgljgList(xh);
	}
	
	/**
	 * 
	 * @描述:TODO(获取未返校信息记录)
	 * @作者： lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:24 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneWfxxwhjgList(String  xh) {	
		 Map<String, String> newMap =  dao.getOneWfxxwhjgList(xh);
		 
		 //如果该学号的学生未处理
		 if(newMap.size() == 0){
			 newMap.put("fxztmc", "未处理");
		 }
		 if(newMap.get("wfxyy") == null){
			 newMap.put("wfxyy", "");		 	 
		 }
		 if(newMap.get("sfqdlx") == null){
			 newMap.put("sfqdlx", "是");		 	 
		 }
		  return newMap;
	}
	
	/**
	 * 
	 * @描述:TODO(保存返校维护)
	 * @作者： lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:24 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJqFxwh(JqfxFxwhForm model) throws Exception {	
		boolean flag=true;
		//model.setXq("春".equals(model.getXq())?"01":"02");
		
		model.setFxdm(getFxdm());		
		if(JQFXWWHZT.equals(model.getFxzt())||JQFXWHZT.equals(model.getFxzt())){
			//设置返校状态为返校
			model.setFxzt(JQFXWHZT);
			model.setWfxyy("");//置空未返校原因

			//修改假日未返校维护
			boolean updateResult = dao.runUpdate(model);
			flag = updateResult;
		}else {
			model.setFxzt(JQFXWHZT);	
			boolean insertResult = dao.runInsert(model);
			if(!insertResult){
				flag = insertResult;
			}
		}
		 
		/*if(JQFXWWHZT.equals(model.getFxzt())){
			//设置返校状态为返校
			model.setFxzt(JQFXWHZT);
			model.setWfxyy("");//置空未返校原因
			//修改假日未返校维护
			boolean updateResult = dao.runUpdate(model);
			flag = updateResult;
		}else{
			model.setFxzt(JQFXWHZT);	
			boolean insertResult = dao.runInsert(model);
			if(!insertResult){
				flag = insertResult;
			}
		}	*/
		return flag;
	}

	

	/**
	 * 
	 * @描述:TODO(保存假期未返校)
	 * @作者： lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:24 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJqWfxwh(JqfxFxwhForm model) throws Exception {	
		model.setXq("春".equals(model.getXq())?"01":"02");	
		model.setFxdm(getFxdm());
		//假日返校
		boolean flag=true;		
		if(JQFXWWHZT.equals(model.getFxzt())||JQFXWHZT.equals(model.getFxzt())){
					if(JQFXWHZT.equals(model.getFxzt())){
						model.setFxsj("");
						model.setFxzt(JQFXWWHZT);
			}
			//修改假日未返校维护
			boolean updateResult = super.runUpdate(model);
			flag = updateResult;
		}else{			
			//设置返校状态为返校
			model.setFxzt(JQFXWWHZT);	
			boolean insertResult = super.runInsert(model);
			if(!insertResult){
				flag = insertResult;
			}
		}		
		return flag;
	}
		
	/** 
	 * @描述:TODO(获取假期返校基础设置中的返校代码)
	 * @作者： lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:24 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * JqfxjcszForm 返回类型 
	 * @throws 
	 */
	public String getFxdm() throws Exception {
		JqfxJbszService jqfxjbszservice = new JqfxJbszService();
		JqfxJbszForm jcszModel = jqfxjbszservice.getModel();
		String fxdm = jcszModel.getFxdm();
		return fxdm;
	}
	
	/**
	 * 
	 * @描述:TODO(批量获取数据库里面的未返校记录)
	 * @作者： lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:24 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getCountNum(JqfxFxwhForm model,User user)throws Exception {
		return dao.getCountNum(model,user);
	}
	
	/**
	 * 
	 * @描述:TODO(批量保存假期维护)
	 * @作者： lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:24 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean plSaveJqwh(JqfxFxwhForm model,User user) throws Exception {					
		boolean flag = false;
		model.setFxdm(getFxdm());
		String currXn = Base.currXn; //当前学年	
		model.setXn(currXn);
		model.setXq(Base.currXq);	
		model.setFxzt(JQFXWHZT);
		flag = dao.plSaveJqwh(model,user);		
		return flag;
	}
	
	/**
	 * 
	 * @描述:TODO(批量保存多个假期返校)
	 * @作者： lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:24 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean pldgSaveJqwh(JqfxFxwhForm model,User user) throws Exception{
		 
			boolean flag = false;
			model.setFxdm(getFxdm());
			String currXn = Base.currXn; //当前学年	
			model.setXn(currXn);
			model.setXq("01".equals(Base.currXq)?"春":"秋");	
			model.setFxzt(JQFXWHZT);			
				
			flag = dao.pldgSaveJqwh(model,user);		
			return flag;
	 }
	
	/**
	 * 
	 * @描述:TODO(批量学生假期未返校维护)
	 * @作者： lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:24 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveplxsJqWfxwh(JqfxFxwhForm model) throws Exception {
		
		model.setXq("春".equals(model.getXq())?"01":"02");	
		model.setFxdm(getFxdm());		
		//假日返校
		boolean flag=true;
			
		model.setFxdm(getFxdm());
		String currXn = Base.currXn; //当前学年	
		model.setXn(currXn);
		model.setXq("01".equals(Base.currXq)?"春":"秋");	
		model.setFxzt(JQFXWWHZT);		
		dao.pldgSaveJqwfx(model);	
		return flag;
		
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-4-24 下午05:32:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getAllWfxyy() {
		
		return dao.getAllWfxyy();
	}
	public String getWfxyyMc(String dm) {
		
		return dao.getWfxyyMc(dm);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-4-27 下午02:26:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param resultList
	 * @param hashMap
	 * @return
	 * File 返回类型 
	 * @throws 
	 */
		public File getBdqktjFile(List<HashMap<String, String>> resultList) throws IOException, Exception {
			//画Excel
			String fileName = System.currentTimeMillis() + ".xls";
			File file = new File(System.getProperty("java.io.tmpdir"),fileName);
			
			
			if(!file.exists()){
				file.createNewFile();
			}	
			
			//创建工作簿
			WritableWorkbook  wwb = Workbook.createWorkbook(file);
			
			//设置相关格式
			WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置标题字体
			WritableFont headFont = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置表头字体
			WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//设置正文字体
			
			
			WritableCellFormat title_cf = new WritableCellFormat(tileFont);//设置标题单元格字体
			WritableCellFormat head_cf = new WritableCellFormat(headFont);//设置正文表头字体
			WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体
			body_cf.setWrap(true);
			head_cf.setWrap(true);//自动换行
			title_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置标题单元格对齐
			title_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//			title_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置标题单元格边框
//			title_cf.setBackground(Colour.YELLOW);	//设置标题背景色
//			body_hj_cf.setAlignment(jxl.format.Alignment.LEFT);
//			body_hj_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置垂直对齐
//			body_hj_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			head_cf.setAlignment(jxl.format.Alignment.CENTRE);
			head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置表头水平对齐
			head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
//			body_sp_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置水平对齐
//			body_sp_cf.setAlignment(jxl.format.Alignment.CENTRE);
			
			body_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置正文单元格对齐
			body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置水平对齐
			body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置正文单元格边框
//			body_bz_cf.setAlignment(jxl.format.Alignment.LEFT);
//			body_bz_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
				//List<Map<String,String>> list = iterator.next();	//班级对应的相关学生列表
				String title = Base.currXn+"-"+Base.currXq+"学期报到情况统计表";	//标题
				
				//创建工作表
				WritableSheet sheet = wwb.createSheet("sheet 1", 0);
				//设置各列列宽
				sheet.setColumnView(0, 20);
				sheet.setColumnView(1, 10);
				sheet.setColumnView(2, 10);
				sheet.setColumnView(3, 10);
				sheet.setColumnView(4, 10);
				sheet.setColumnView(5, 10);
				sheet.setColumnView(6, 10);
				sheet.setColumnView(7, 10);
				sheet.setColumnView(8, 10);
				sheet.setColumnView(9, 10);
				sheet.setColumnView(11, 10);
				sheet.setColumnView(12, 10);
				sheet.setColumnView(13, 10);
				sheet.setColumnView(14, 10);
				sheet.setColumnView(15, 10);
				sheet.setColumnView(16, 20);
				sheet.setRowView(0, 700, false);
				sheet.setRowView(1, 500, false);
				sheet.setRowView(2, 500, false);
				
				//合并单元格
				//第一个参数：要合并的单元格最左上角的列号，
				//第二个参数：要合并的单元格最左上角的行号，
				//第三个参数：要合并的单元格最右角的列号，
				//第四个参数：要合并的单元格最右下角的行
				sheet.mergeCells(0, 0, 16, 0);	//标题
				sheet.mergeCells(0, 1, 0, 2);//学院
				sheet.mergeCells(1, 1, 1, 2);//在籍人数
				sheet.mergeCells(2, 1, 2, 2);//已休学
				sheet.mergeCells(3, 1, 3, 2);//已出国（含赴台）
				sheet.mergeCells(7, 1,15, 1);//未到原因
				sheet.mergeCells(16, 1,16, 2);//备注
				//创建标题及表头
				Label t_0_0 = new Label(0, 0,title,title_cf);
				Label h_0_1 = new Label(0,1,"学院",head_cf);
				Label h_1_1 = new Label(1,1,"在籍人数",head_cf);
				Label h_2_1 = new Label(2,1,"已休学",head_cf);
				Label h_3_1 = new Label(3,1,"已出国（含赴台）",head_cf);
				Label h_4_1 = new Label(4,1,"应到",head_cf);
				Label h_4_2 = new Label(4,2,"人数",head_cf);
				Label h_5_1 = new Label(5,1,"实到",head_cf);
				Label h_5_2 = new Label(5,2,"人数",head_cf);
				Label h_6_1 = new Label(6,1,"未到",head_cf);
				Label h_6_2 = new Label(6,2,"人数",head_cf);
				Label h_7_1 = new Label(7,1,"未到原因",head_cf);
				Label h_7_2 = new Label(7,2,"事假",head_cf);
				Label h_8_2 = new Label(8,2,"病假",head_cf);
				Label h_9_2 = new Label(9,2,"欲退",head_cf);
				Label h_10_2 = new Label(10,2,"欲退率",head_cf);
				Label h_11_2 = new Label(11,2,"欲休",head_cf);
				Label h_12_2 = new Label(12,2,"欲休率",head_cf);
				Label h_13_2 = new Label(13,2,"在途中",head_cf);
				Label h_14_2 = new Label(14,2,"原因不明",head_cf);
				Label h_15_2 = new Label(15,2,"其他原因",head_cf);
				Label h_16_1 = new Label(16,1,"备注",head_cf);
				
				sheet.addCell(t_0_0);
				sheet.addCell(h_0_1);
				sheet.addCell(h_1_1);
				sheet.addCell(h_2_1);
				sheet.addCell(h_3_1);
				sheet.addCell(h_4_1);
				sheet.addCell(h_4_2);
				sheet.addCell(h_5_1);
				sheet.addCell(h_5_2);
				sheet.addCell(h_6_1);
				sheet.addCell(h_6_2);
				sheet.addCell(h_7_1);
				sheet.addCell(h_7_2);
				sheet.addCell(h_8_2);
				sheet.addCell(h_9_2);
				sheet.addCell(h_10_2);
				sheet.addCell(h_11_2);
				sheet.addCell(h_12_2);
				sheet.addCell(h_13_2);
				sheet.addCell(h_14_2);
				sheet.addCell(h_15_2);
				sheet.addCell(h_16_1);
				
				//遍历创建单元格
				int size = resultList.size();
				if(size>0){
					for(int j=0;j<size;j++){
						Map<String,String> map = resultList.get(j);
						Map<String,String> wbdqkMap = new HashMap<String, String>();
						wbdqkMap.put("事假", "");
						wbdqkMap.put("病假", "");
						wbdqkMap.put("欲退", "");
						wbdqkMap.put("欲休", "");
						wbdqkMap.put("在途中", "");
						wbdqkMap.put("原因不明", "");
						wbdqkMap.put("其他原因", "");
						wbdqkMap.put("已出国（含赴台）", "");
						wbdqkMap.put("已休学", "");
						String[] wbdqkArr = map.get("wbdqk").split(";");
						for (String s : wbdqkArr) {
							String[] temp = s.split("_");
							wbdqkMap.put(temp[0], temp[1]);
						}
						int xyzrsint = Integer.valueOf(map.get("xyzrs"));
						int yxint = Integer.valueOf("".equals(wbdqkMap.get("欲休"))?"0":wbdqkMap.get("欲休"));
						int ytint = Integer.valueOf("".equals(wbdqkMap.get("欲退"))?"0":wbdqkMap.get("欲退"));
						//DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();  
						 DecimalFormat df = new DecimalFormat("0.0000%");  
				        //模式 例如四舍五入  
				        df.setRoundingMode(RoundingMode.HALF_UP);  
				        String yxlStr="" , ytlStr="";
				       
						if(yxint!=0){
							 double yxl_d = (yxint*1.00) / (xyzrsint*1.00);  
							 yxlStr = df.format(yxl_d)+"";
						}
						if(yxint!=0){
							 double ytl_d = (ytint*1.00) /( xyzrsint*1.00);
							 ytlStr = df.format(ytl_d)+"";
						}
				       
				       
				        
						Integer sdrsInt = dao.getSdrs(map.get("xsxydm"));
						Label xymc = new Label(0, j+3, map.get("xymc"), body_cf);		//学院
						Label zjrs = new Label(1, j+3, map.get("yxjrs"), body_cf);		//在籍人数--
						Label ysx = new Label(2, j+3, wbdqkMap.get("已休学"), body_cf);		//已休学
						Label ycg = new Label(3, j+3, wbdqkMap.get("已出国（含赴台）"), body_cf);		//出国（含赴台）
						Label ydrs = new Label(4, j+3, map.get("xyzrs"), body_cf);		//应到人数
						Label sdrs = new Label(5, j+3, sdrsInt.toString(), body_cf);		//实到人数--
						Label wdrs = new Label(6, j+3, map.get("wbdzrs"), body_cf);		//未到人数
						Label sj = new Label(7, j+3, wbdqkMap.get("事假"), body_cf);		
						Label bj = new Label(8, j+3, wbdqkMap.get("病假"), body_cf);		
						Label yt = new Label(9, j+3, wbdqkMap.get("欲退"), body_cf);		
						Label ytl = new Label(10, j+3, ytlStr, body_cf);		//欲退率--
						Label yx = new Label(11, j+3, wbdqkMap.get("欲休"), body_cf);		
						Label yxl = new Label(12, j+3, yxlStr, body_cf);		//欲休率--
						Label ztz = new Label(13, j+3, wbdqkMap.get("在途中"), body_cf);
						Label yybm = new Label(14, j+3, wbdqkMap.get("原因不明"), body_cf);		
						Label qtyy = new Label(15, j+3, wbdqkMap.get("其他原因"), body_cf);		
						Label bz = new Label(16, j+3, "", body_cf);	//备注--	
						sheet.addCell(xymc);
						sheet.addCell(zjrs);
						sheet.addCell(ysx);
						sheet.addCell(ycg);
						sheet.addCell(ydrs);
						sheet.addCell(sdrs);
						sheet.addCell(wdrs);
						sheet.addCell(sj);
						sheet.addCell(bj);
						sheet.addCell(yt);
						sheet.addCell(ytl);
						sheet.addCell(yx);
						sheet.addCell(yxl);
						sheet.addCell(ztz);
						sheet.addCell(yybm);
						sheet.addCell(qtyy);
						sheet.addCell(bz);
						sheet.setRowView(j+3, 500, false);
					}
				}
			
			//如果数据为空
			if(resultList==null||resultList.size()==0){
				//创建工作表
				WritableSheet sheetNull = wwb.createSheet("本次导出数据为空", 0);
				sheetNull.setColumnView(0, 15);
				Label msg = new Label(0, 0,"暂无数据！");
				sheetNull.addCell(msg);
			}
			
			wwb.write();
			wwb.close();
				
			return file;
		}

	/**
	 * @throws Exception 
	 * @param model  
	 * @描述:获取未报到人数情况(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-4-27 下午02:35:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBdqktjList(JqfxFxwhForm model,User user) throws Exception {
		
		return dao.getBdqktjList(model,user);
	}

	
	
}
