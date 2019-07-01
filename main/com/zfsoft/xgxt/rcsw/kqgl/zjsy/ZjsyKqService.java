/**
 * @部门:学工产品事业部
 * @日期：2015-6-17 下午02:43:37 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.zjsy;

import java.io.File;
import java.util.ArrayList;
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
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 浙江商业考勤管理
 * @作者： ChenQ[工号:856]
 * @时间： 2015-6-17 下午02:43:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZjsyKqService extends SuperServiceImpl<ZjsyKqForm, ZjsyKqDAO> {
	
	private ZjsyKqDAO dao = new ZjsyKqDAO();
	
    private final String MonthWithXx = "monthwithxx"; 
    private final String MonthWithXy = "monthwithxy"; 
    private final String ZcWithXx = "zcwithxx"; 
    private final String ZcWithXy = "zcwithxy"; 
    
	@SuppressWarnings("deprecation")
	public ZjsyKqService() {
		super.setDao(dao);
	}
	
	
	public ZjsyKqForm getModel(String id) throws Exception{
		return dao.getModel(id);
	}
	
	public File getExportFile(ZjsyKqForm model,User user) throws Exception{
		//生成Excle文件
		File tempxls = new File(System.getProperty("java.io.tmpdir")+"/"+System.currentTimeMillis()+".xls");
		WritableWorkbook book = Workbook.createWorkbook(tempxls);
		WritableSheet sheet = book.createSheet("Sheet1", 0);
		
		//设置标题样式
		WritableCellFormat title = new WritableCellFormat(); // 构造单元格格式
		title = ExcelMethods.getWcf(WritableFont.ARIAL,18, true, Alignment.CENTRE, VerticalAlignment.CENTRE, false, Border.NONE);
		
		//设置中部样式
		WritableCellFormat center = new WritableCellFormat(); // 构造单元格格式
		center = ExcelMethods.getWcf(WritableFont.ARIAL,12, true, Alignment.LEFT, VerticalAlignment.CENTRE, false, Border.NONE);
		
		//设置表头样式
		WritableCellFormat top = new WritableCellFormat(); // 构造单元格格式
		top = ExcelMethods.getWcf(WritableFont.ARIAL,12, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		//设置内容样式
		WritableCellFormat content = new WritableCellFormat(); // 构造单元格格式
		content = ExcelMethods.getWcf(WritableFont.ARIAL,12, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		//设置内容样式
		WritableCellFormat bottom = new WritableCellFormat(); // 构造单元格格式
		bottom = ExcelMethods.getWcf(WritableFont.ARIAL,12, true, Alignment.LEFT, VerticalAlignment.CENTRE, false, Border.NONE);
		
		
		
		//考勤标题信息数据集
		List<HashMap<String,String>> titleresult = dao.getMonthZcXyData(model, user);
		
		
		
		StringBuilder titlename = new StringBuilder();
		StringBuilder exportuser = new StringBuilder();
		
		if(ZcWithXy.equals(model.getType())||MonthWithXy.equals(model.getType())){
			//考勤信息数据集
			List<HashMap<String,String>> result = dao.getExpoertData(model, user);
			//考勤合计信息数据集
			List<HashMap<String,String>> hjresult = dao.getHjExpoertData(model, user);
			//构建汇总表的Title
			titlename.append(titleresult.get(0).get("xymc"));
			titlename.append("学生出勤情况");
			if(ZcWithXy.equals(model.getType())){
				titlename.append(titleresult.get(0).get("tozc"));
			}else{
				titlename.append(titleresult.get(0).get("toyf"));
			}
			titlename.append("汇总表");
			//构建汇总表的填表人
			exportuser.append("       ");
			exportuser.append("填表人:     ");
			exportuser.append(user.getRealName());
			exportuser.append("                               ");
			exportuser.append("                               ");
			exportuser.append("填表时间：");
			exportuser.append( DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));
			
			sheet.mergeCells(0, 0, 7, 0);
			addCell(sheet,0,0,title,result.get(0).get("xn")+titleresult.get(0).get("xqmc"));
			sheet.setColumnView(0, 11);
			sheet.setColumnView(6, 43);
			sheet.setColumnView(7, 12);
			sheet.mergeCells(0, 1, 7, 1);
			addCell(sheet,0,1,title,titlename.toString());
			sheet.mergeCells(0, 2, 7,2);
			sheet.setRowView(2,400);
			addCell(sheet,0,2,center,exportuser.toString());
			addCell(sheet,0,3,top,"班   级");
			addCell(sheet,1,3,top,"应出勤人 数");
			addCell(sheet,2,3,top,"病假      人数");
			addCell(sheet,3,3,top,"事假      人数");
			addCell(sheet,4,3,top,"旷课      人数");
			addCell(sheet,5,3,top,"出勤率");
			addCell(sheet,6,3,top,"旷课名与累计节数");
			addCell(sheet,7,3,top,"备 注");
			int rindex = 4;
			int cindex = 0;
			for(int i=0;i<result.size();i++){
				addCell(sheet,cindex++,rindex,content,result.get(i).get("bjmc"));
				addCell(sheet,cindex++,rindex,content,result.get(i).get("cqrs"));
				addCell(sheet,cindex++,rindex,content,result.get(i).get("bjrs"));
				addCell(sheet,cindex++,rindex,content,result.get(i).get("sjrs"));
				addCell(sheet,cindex++,rindex,content,result.get(i).get("kkrs"));
				addCell(sheet,cindex++,rindex,content,result.get(i).get("cql")+"%");
				addCell(sheet,cindex++,rindex,content,result.get(i).get("kkxq"));
				addCell(sheet,cindex++,rindex,content,result.get(i).get("bz"));
				rindex = rindex+1;
				cindex = 0;
			}
			
			
			if(hjresult.size()>0){
				addCell(sheet,cindex++,rindex,content,"合计");
				addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("cqrs"));
				addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("bjrs"));
				addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("sjrs"));
				addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("kkrs"));
				addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("cql")+"%");
				addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("kkxq"));
				addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("bz"));
			}
			addCell(sheet,0,rindex+2,bottom,"审核人：_______________             审核日期：_______________     ");
		}else if(ZcWithXx.equals(model.getType())||MonthWithXx.equals(model.getType())){
			
			titlename.append("各学院学生出勤情况汇总表");
			exportuser.append("      ");
			if(ZcWithXx.equals(model.getType())){
				exportuser.append(titleresult.get(0).get("tozc"));
			}else{
				exportuser.append("第");
				exportuser.append(titleresult.get(0).get("toyf"));
			}
			exportuser.append("                               ");
			exportuser.append("                               ");
			exportuser.append("    ");
			exportuser.append("填表人:     ");
			exportuser.append(user.getRealName());
			exportuser.append("    填表时间：");
			exportuser.append( DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));
		
			if(ZcWithXx.equals(model.getType())){
				//考勤信息数据集
				List<HashMap<String,String>> result = getZcWithXxData(model, user);
				//考勤合计信息数据集
				List<HashMap<String,String>> hjresult = getHjZcWithXxData(model, user);
				sheet.mergeCells(0, 0, 8, 0);
				addCell(sheet,0,0,title,titlename.toString());
				sheet.mergeCells(0, 1, 8, 1);
				addCell(sheet,0,1,center,exportuser.toString());
				sheet.setColumnView(0, 18);
				sheet.setColumnView(1, 5);
				sheet.setColumnView(2, 5);
				sheet.setColumnView(3, 5);
				sheet.setColumnView(4, 5);
				sheet.setColumnView(5, 5);
				sheet.setColumnView(6, 5);
				sheet.setColumnView(7, 43);
				sheet.setColumnView(8, 18);
				sheet.setRowView(2, 660);
				sheet.mergeCells(0, 2, 0, 3);
				addCell(sheet,0,2,top,"学院");
				sheet.mergeCells(1, 2, 2, 2);
				addCell(sheet,1,2,top,"病假      人数");
				sheet.mergeCells(3, 2, 4, 2);
				addCell(sheet,3,2,top,"事假      人数");
				sheet.mergeCells(5, 2, 6, 2);
				addCell(sheet,5,2,top,"旷课      人数");
				sheet.mergeCells(7, 2, 7, 3);
				addCell(sheet,7,2,top,"旷课名与累计节数");
				sheet.mergeCells(8, 2, 8, 3);
				addCell(sheet,8,2,top,"备 注");
				int rindex = 3;
				int cindex = 1;
				for(int i=0;i<3;i++){
					addCell(sheet,cindex,rindex,content,"上  周");
					addCell(sheet,cindex+1,rindex,content,"本  周");
					cindex = cindex+2;
				}
				rindex = 4;
				cindex = 0;
				for(int i=0;i<result.size();i++){
					addCell(sheet,cindex++,rindex,content,result.get(i).get("xymc"));
					addCell(sheet,cindex++,rindex,content,result.get(i).get("szbjrs"));
					addCell(sheet,cindex++,rindex,content,result.get(i).get("bjrs"));
					addCell(sheet,cindex++,rindex,content,result.get(i).get("szsjrs"));
					addCell(sheet,cindex++,rindex,content,result.get(i).get("sjrs"));
					addCell(sheet,cindex++,rindex,content,result.get(i).get("szkkrs"));
					addCell(sheet,cindex++,rindex,content,result.get(i).get("kkrs"));
					addCell(sheet,cindex++,rindex,content,result.get(i).get("kkxq"));
					addCell(sheet,cindex++,rindex,content,result.get(i).get("bz"));
					rindex = rindex+1;
					cindex = 0;
				}
				
				
				if(hjresult.size()>0){
					addCell(sheet,cindex++,rindex,content,"合计");
					addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("szbjrs"));
					addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("bjrs"));
					addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("szsjrs"));
					addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("sjrs"));
					addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("szkkrs"));
					addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("kkrs"));
					addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("kkxq"));
					addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("bz"));
				}
				addCell(sheet,0,rindex+3,bottom,"如有异议、请到学生处核对。");
				addCell(sheet,0,rindex+4,bottom,"复核人、______________  审核人、______________   审核日期、________________");
			}else{
				//考勤信息数据集
				List<HashMap<String,String>> result = dao.getExpoertData(model, user);
				//考勤合计信息数据集
				List<HashMap<String,String>> hjresult = dao.getHjExpoertData(model, user);
				sheet.mergeCells(0, 0, 6, 0);
				addCell(sheet,0,0,title,titlename.toString());
				sheet.mergeCells(0, 1, 6, 1);
				addCell(sheet,0,1,center,exportuser.toString());
				sheet.setColumnView(0, 16);
				sheet.setColumnView(5, 36);
				sheet.setColumnView(6, 18);
				sheet.setRowView(2, 660);
				sheet.setRowView(3, 420);
				sheet.mergeCells(0, 2, 0, 3);
				addCell(sheet,0,2,top,"学院");
				addCell(sheet,1,2,top,"病假      人数");
				addCell(sheet,2,2,top,"事假      人数");
				addCell(sheet,3,2,top,"旷课      人数");
				addCell(sheet,4,2,top,"出勤率");
				sheet.mergeCells(5, 2, 5, 3);
				addCell(sheet,5,2,top,"旷课名与累计节数");
				sheet.mergeCells(6, 2, 6, 3);
				addCell(sheet,6,2,top,"备 注");
				for(int i=0;i<=3;i++){
					addCell(sheet,i+1,3,content,"本月");
				}
				
				int rindex = 4;
				int cindex = 0;
				for(int i=0;i<result.size();i++){
					addCell(sheet,cindex++,rindex,content,result.get(i).get("xymc"));
					addCell(sheet,cindex++,rindex,content,result.get(i).get("bjrs"));
					addCell(sheet,cindex++,rindex,content,result.get(i).get("sjrs"));
					addCell(sheet,cindex++,rindex,content,result.get(i).get("kkrs"));
					addCell(sheet,cindex++,rindex,content,result.get(i).get("cql")+"%");
					addCell(sheet,cindex++,rindex,content,result.get(i).get("kkxq"));
					addCell(sheet,cindex++,rindex,content,result.get(i).get("bz"));
					rindex = rindex+1;
					cindex = 0;
				}
				
				
				if(hjresult.size()>0){
					addCell(sheet,cindex++,rindex,content,"合计");
					addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("bjrs"));
					addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("sjrs"));
					addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("kkrs"));
					addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("cql")+"%");
					addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("kkxq"));
					addCell(sheet,cindex++,rindex,content,hjresult.get(0).get("bz"));
				}
				addCell(sheet,0,rindex+3,bottom,"如有异议、请到学生处核对。");
				addCell(sheet,0,rindex+4,bottom,"复核人、______________  审核人、______________   审核日期、________________");
			}
			
		}
		book.write();
		book.close();
		return tempxls;
	}
	
	public void addCell(WritableSheet sheet, int c, int r, WritableCellFormat cellFormat, String str) throws RowsExceededException, WriteException{
		Label label = new Label(c,r,str);
		label.setCellFormat(cellFormat);
		sheet.addCell(label);
	}
	
	public boolean cshKqdj(ZjsyKqForm model) throws Exception{
		return dao.cshKqdj(model);
	}
	
	public List<HashMap<String, String>> getXsxxList(ZjsyKqForm model, User user,String xhArr) throws Exception {
		if("".equals(xhArr)){
			model.setXhArr(new String[]{});
		}else{
		String[] xhs = xhArr.split(",");
		model.setXhArr(xhs);
		}
		return dao.getXsxxList(model, user);

	}
	
	public boolean sqveKqinfo(ZjsyKqForm model,List<ZjsyKqForm> list) throws Exception{
		boolean flag = runUpdate(model);
		if(flag){
			flag = dao.delKqInfo(model.getId());
			if(flag){
				List<String[]> params = new ArrayList<String[]>();
				for(int i=0;i<list.size();i++){
					String[] info = new String[6];
					info[0] = UniqID.getInstance().getUniqIDHash();
					info[1] = model.getId();
					info[2] = list.get(i).getXh();
					info[3] = list.get(i).getBjcs();
					info[4] = list.get(i).getSjcs();
					info[5] = list.get(i).getKkjs();
					params.add(info);
				}
				flag = dao.saveKqInfo(params);
			}
		}
		return flag;
	}
	
	public List<HashMap<String,String>> getKqinfo(String kqid){
		return dao.getKqinfo(kqid);
	}
	
	public boolean delKq(String values) throws Exception {
		boolean flag = false;
		String[] ids = values.split(",");
		int num = runDelete(ids);
		if(num>0){
			flag = true;
			for(String id:ids){
				flag = dao.delKqInfo(id);
				if(!flag){
					break;
				}
			}
			
		}
		return flag;
	}
	
	public List<HashMap<String,String>> getZcWithXxData(ZjsyKqForm model, User user)  throws Exception{
		List<HashMap<String,String>> result = dao.getExpoertData(model, user);
		List<HashMap<String,String>> szresult = dao.getSzExpoertData(model, user);
		for(int i =0;i<result.size();i++){
			for(int j =0 ;j<szresult.size();j++){
				if(result.get(i).get("xn").equals(szresult.get(i).get("xn"))
					&&result.get(i).get("xq").equals(szresult.get(i).get("xq"))
					&&result.get(i).get("yf").equals(szresult.get(i).get("yf"))
					&&result.get(i).get("xydm").equals(szresult.get(i).get("xydm"))){
					result.get(i).put("szbjrs", szresult.get(i).get("bjrs"));
					result.get(i).put("szsjrs", szresult.get(i).get("sjrs"));
					result.get(i).put("szkkrs", szresult.get(i).get("kkrs"));
				}
			}
		}
		return result;
	}
	public List<HashMap<String,String>> getHjZcWithXxData(ZjsyKqForm model, User user)  throws Exception{
		List<HashMap<String,String>> result = dao.getHjExpoertData(model, user);
		List<HashMap<String,String>> szresult = dao.getSzHjExpoertData(model, user);
		for(int i =0;i<result.size();i++){
			for(int j =0 ;j<szresult.size();j++){
				if(result.get(i).get("xn").equals(szresult.get(i).get("xn"))
					&&result.get(i).get("xq").equals(szresult.get(i).get("xq"))
					&&result.get(i).get("yf").equals(szresult.get(i).get("yf"))
					){
					result.get(i).put("szbjrs", szresult.get(i).get("bjrs"));
					result.get(i).put("szsjrs", szresult.get(i).get("sjrs"));
					result.get(i).put("szkkrs", szresult.get(i).get("kkrs"));
				}
			}
		}
		return result;
	}
	public String getDqZc(){
		return dao.getDqZc();
	}
}
