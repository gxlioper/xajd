/**
 * @部门:学工产品事业部
 * @日期：2015-9-10 上午08:46:48 
 */  
package com.zfsoft.xgxt.zxdk.rwfbybc.dcjg;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import xgxt.form.User;

public class RwfbydcjgService extends SuperServiceImpl<RwfbydcjgModel, RwfbydcjgDao> {
  
	/**
	 * 
	 * @描述:验证是否已申请
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-9-7 下午06:16:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExists(RwfbydcjgModel model) {	
		return dao.isExists(model);
	}
	
	/**
	 *
	 * @描述:陕西师范大学兵役代偿结果list学生信息查看自定义表单配置用
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-12-7 上午09:14:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getRwdcjglist(String xh){
		return dao.getRwdcjglist(xh);
	}
	
	/**
	 * @throws SQLException 
	 * @描述:保存代偿结果发放次数的相关信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月13日 下午3:43:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ffcsList
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveDcjgffcs(String xh,String xn,List<DcjgffcsModel> ffcsList) throws SQLException{
		return dao.saveDcjgffsc(xh,xn,ffcsList);
	}
	
	/**
	 * @throws Exception 
	 * @描述:根据学号、学年的集合批量删除代偿结果发放次数的相关信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月16日 上午10:04:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xhxnList
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteDcjgffcs(List<HashMap<String,String>> xhxnList) throws Exception{
		return dao.deleteDcjgffsc(xhxnList);
	}

	/** 
	 * @描述:根据学号、学年查询代偿结果对应的发放次数信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月16日 上午8:47:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getDcjgffcsList(String xh,String xn) {
		return dao.getDcjgffcsList(xh,xn);
	}
	
	/**
	 * @throws Exception  
	 * @描述:更新代偿结果发放次数的相关信息（先删后增）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月16日 上午9:43:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param ffcsList
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateDcjgffcs(String xh, String xn, List<DcjgffcsModel> ffcsList) throws Exception {
		HashMap<String,String> map = new HashMap<String,String> ();
		map.put("xh", xh);
		map.put("xn", xn);
		List<HashMap<String,String>> xhxnList = new ArrayList<HashMap<String,String>>();
		xhxnList.add(map);
		boolean result = dao.deleteDcjgffsc(xhxnList);
		if(result){
			result = dao.saveDcjgffsc(xh, xn, ffcsList);
		}
		return result;
	}

	/** 
	 * @描述:根据代偿结果id数字查询相关学号，学年集合
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月16日 下午12:37:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param idArr
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXhxnList(String[] idArr) {
		return dao.getXhxnList(idArr);
	}
	
	
	//=================个性化导入导出，自定义多表=====================
	/** 
	 * @描述:查询导入模版信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月18日 上午9:59:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param drmkdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getDrmbxx(String drmkdm) {
		HashMap<String,String> drmbxx = dao.getDrmbxx(drmkdm);
		return drmbxx;
	}

	/** 
	 * @描述:查询导入规则列表信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月18日 上午9:59:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param drmkdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDrgzxxList(String drmkdm) {
		List<HashMap<String,String>> drgzxxList = dao.getDrgzxxList(drmkdm);
		List<HashMap<String,String>> zdExtraDrgzxxList = this.getZDExtraDrgzxxList();
		drgzxxList.addAll(zdExtraDrgzxxList);
		return drgzxxList;
	}
	
	/**
	 * @描述:获取浙大个性化额外字段导入规则列表
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月18日 上午11:09:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZDExtraDrgzxxList(){
		List<HashMap<String,String>> zdExtraDrgzxxList = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> ffsj = new HashMap<String,String> ();
		HashMap<String,String> ffje = new HashMap<String,String> ();
		HashMap<String,String> ffnr = new HashMap<String,String> ();
		
		//drl,drlmc,lsjgsh,gshxx,sfzj,sfbt,zdcd,sfwy
		ffsj.put("drl", "ffsj");
		ffsj.put("drlmc", "发放时间");
		ffsj.put("lsjgsh", "field:Datetime(yyyy-MM-dd)");
		ffsj.put("gshxx", "yyyy-MM-dd，如果有多次请用|隔开");
		ffsj.put("sfzj", "0");
		ffsj.put("sfbt", "0");
		ffsj.put("zdcd", "10");
		ffsj.put("sfwy", "0");
		
		ffje.put("drl", "ffje");
		ffje.put("drlmc", "发放金额");
		ffje.put("lsjgsh", "field:String");
		ffje.put("gshxx", "如果有多次请用|隔开");
		ffje.put("sfzj", "0");
		ffje.put("sfbt", "0");
		ffje.put("zdcd", "10");
		ffje.put("sfwy", "0");
		
		ffnr.put("drl", "ffnr");
		ffnr.put("drlmc", "发放内容");
		ffnr.put("lsjgsh", "field:String");
		ffnr.put("gshxx", "如果有多次请用|隔开，注意不要含有英文分号");
		ffnr.put("sfzj", "0");
		ffnr.put("sfbt", "0");
		ffnr.put("zdcd", "100");
		ffnr.put("sfwy", "0");
		
		zdExtraDrgzxxList.add(ffsj);
		zdExtraDrgzxxList.add(ffje);
		zdExtraDrgzxxList.add(ffnr);
		return zdExtraDrgzxxList;
	}

	/**
	 * @throws Exception 
	 * @描述:生成个性化导入时的excel模版
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月18日 下午3:28:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param drmkdm
	 * @return
	 * File 返回类型 
	 * @throws 
	 */
	public File getExcelTemplate(String path,String drmkdm){
		File file = new File(path,drmkdm+".xls");
		
		// 打开文件
		WritableWorkbook book = null;
		try {
			//获取导入规则，写入excel导入列批注
			List<HashMap<String,String>> drgzxxList = this.getDrgzxxList(drmkdm);
			
			 book = Workbook.createWorkbook(file);
			// 生成导入表，参数0表示sheet1，impor为其名称
			WritableSheet sheet1 = book.createSheet("import", 0);
			
			//填充导入列及批注提示到导入表
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.WHITE);
			WritableCellFormat cellFormat1 = new WritableCellFormat(font1);  
		    //设置背景颜色;  
		    cellFormat1.setBackground(Colour.GREEN);
			for(int i=0;i<drgzxxList.size();i++){
				//考虑根据内容自适应宽度，暂未实现...
				
				Label label = new Label(i,0, drgzxxList.get(i).get("drlmc"),cellFormat1);
//				sheet1.setColumnView(1, drgzxxList.get(i).get("drlmc").length());  
				
				/*
				 * 主键、唯一(sfzj/sfwy)：不能重复
				 * 必填(sfbt)：不可为空
				 * 最大长度(zdcd)：最大长度为?
				 */
				List<String> pznrList = new ArrayList<String>();
				if("1".equals(drgzxxList.get(i).get("sfwy"))){
					pznrList.add("不能重复");
				}
				if("1".equals(drgzxxList.get(i).get("sfbt"))){
					pznrList.add("不可为空");
				}
				if(!StringUtil.isNull(drgzxxList.get(i).get("zdcd"))){
					pznrList.add("最大长度为："+drgzxxList.get(i).get("zdcd"));
				}
				
				String pznr = "";
				for(int j=0;j<pznrList.size();j++){
					pznr = pznr+(j+1)+"."+pznrList.get(j);
					if(j!=pznrList.size()-1) pznr+="\r\n";
				}
				
				WritableCellFeatures cellFeatures = new WritableCellFeatures();  
				cellFeatures.setComment(pznr);  
				label.setCellFeatures(cellFeatures);  
				
				sheet1.addCell(label);
			}
			
			//获取辅助表信息，生成并填充辅助表
			List<HashMap<String,Object>> drfzxxAndFzdmxxList = this.getDrfzxxAndFzdmxxList(drmkdm);
			
			//循环生成辅助表sheet
			for(int k=0;k<drfzxxAndFzdmxxList.size();k++){
				HashMap<String,Object> drfzxxAndFzdmxx = drfzxxAndFzdmxxList.get(k);
				String dm = (String)drfzxxAndFzdmxx.get("fzdmxx_dm");
				String mc = (String)drfzxxAndFzdmxx.get("fzdmxx_mc");
				WritableSheet sheet = book.createSheet((String) drfzxxAndFzdmxx.get("fzmc"),k+1);
				List<HashMap<String,String>> fzdmxxList = (List<HashMap<String,String>>)drfzxxAndFzdmxx.get("fzdmxxList");
				for(int x=0;x<fzdmxxList.size();x++){
					Label label1 = new Label(0, x, fzdmxxList.get(x).get(dm));
					Label label2 = new Label(1, x, fzdmxxList.get(x).get(mc));
					sheet.addCell(label1);
					sheet.addCell(label2);
				}
			}
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			// 写入数据并关闭文件
			try {
				book.write();
				book.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		return file;
	}
	
	
	/**
	 * @描述:获取导入辅助表及导入辅助表中复制的辅助代码表信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月18日 下午6:30:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param drmkdm
	 * @return
	 * List<HashMap<String,Object>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,Object>> getDrfzxxAndFzdmxxList(String drmkdm){
		List<HashMap<String,Object>> drfzxxAndFzdmxxList = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String,String>> drfzxxList = dao.getDrfzxxList(drmkdm);
		for(HashMap<String,String> drfzxx:drfzxxList){
			String fzmc = drfzxx.get("fzmc");
			String pz = drfzxx.get("pz");
			
			String[] pzArr = pz.split("\\|");
			String tableName = pzArr[0].substring(pzArr[0].indexOf(":")+1);
			String [] outputValue = {pzArr[1],pzArr[2]};
			
			List<HashMap<String,String>> fzdmxxList = dao.getFzdmxxList(tableName,outputValue,outputValue[0]);
			HashMap<String,Object> drfzxxAndFzdmxx = new HashMap<String,Object>();
			drfzxxAndFzdmxx.put("fzmc", fzmc);
			drfzxxAndFzdmxx.put("fzdmxx_dm", pzArr[1]);
			drfzxxAndFzdmxx.put("fzdmxx_mc", pzArr[2]);
			drfzxxAndFzdmxx.put("fzdmxxList", fzdmxxList);
			
			drfzxxAndFzdmxxList.add(drfzxxAndFzdmxx);
			
		}
		return drfzxxAndFzdmxxList;
	}

	/**
	 * @throws SQLException  
	 * @描述:保存导入信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月19日 下午6:59:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param inputStream
	 * @param path
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws 
	 */
	public HashMap<String, Object> saveImport(InputStream inputStream, String path,String drmkdm) throws SQLException {
		HashMap<String, Object> resultMap = null;
		//获取导入列规则
		List<HashMap<String,String>> drgzxxList = this.getDrgzxxList(drmkdm);
		
		Workbook wb = null;
		Sheet sheet = null;
		try {
			wb = Workbook.getWorkbook(inputStream);
			sheet = wb.getSheet(0);
			//验证模版头部列  error:01
			resultMap = this.checkImportHeader(sheet, drgzxxList);
			if((Boolean) resultMap.get("result")){
				//模版无误后进行下面的操作
				//读取所有行存入List<HashMap<String,String>>  每行对应一个HashMap
				List<HashMap<String,String>> excelDataList = this.getExcelDataList(sheet,drgzxxList);
				
				//保存前的逐行逐列验证 error:02
				resultMap = checkExcelDataList(excelDataList,drgzxxList);
				if((Boolean) resultMap.get("result")){
					//验证通过，还需要判断excel数据中本身是否有重复
					resultMap = checkExcelDataRepeat(excelDataList);
					if((Boolean) resultMap.get("result")){
						//不存在重复则进行最后的插入数据到数据库的操作
						boolean insertResult = insertDataIntoDB(excelDataList);
						if(insertResult){
							resultMap.put("totalCount", excelDataList.size());
						}
					}else{
						//存在重复，根据错误数据提示生成excel文件到服务器error:03
						String errorTipsExcelName = this.createErrorTipsExcel(excelDataList,path,drmkdm,drgzxxList);
						resultMap.put("errorTipsExcelName", errorTipsExcelName);
					}
				}else{
					//验证不通过，根据错误数据提示生成excel文件到服务器
					String errorTipsExcelName = this.createErrorTipsExcel(excelDataList,path,drmkdm,drgzxxList);
					resultMap.put("errorTipsExcelName", errorTipsExcelName);
				}
			}
			
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/** 
	 * @描述:循环根据导入规则逐行逐列验证excel数据
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月20日 上午11:58:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param excelDataList
	 * @param drgzxxList
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws 
	 */
	private HashMap<String, Object> checkExcelDataList(List<HashMap<String, String>> excelDataList,
			List<HashMap<String, String>> drgzxxList) {
		//excelDataList中所有数据，对于验证错误的行，增加了改行所有列错误提示最为最后一列
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", true);
		Integer errorCount = 0;
		for(HashMap<String,String>excelData:excelDataList){
			boolean rowResult = true;
			StringBuilder rowError = new StringBuilder();
			for(HashMap<String,String>drgzxx:drgzxxList){
				String sfbt = drgzxx.get("sfbt");
				String sfwy = drgzxx.get("sfwy");
				Integer zdcd = Integer.parseInt(drgzxx.get("zdcd"));
				String lsjgsh = drgzxx.get("lsjgsh");
				String gshxx = drgzxx.get("gshxx");
				String drlmc = drgzxx.get("drlmc");
				String drl = drgzxx.get("drl");
				String cellContents = excelData.get(drl); 
				
				if("1".equals(sfbt)){
					//验证不可为空
					if(StringUtil.isNull(cellContents)){
						resultMap.put("result", false);
						rowResult = false;
						rowError.append("["+drlmc+"]不可为空； ");
						continue;
					}
				}
				
				if(!StringUtil.isNull(cellContents)){
					//单元格数据不为空时做下面的验证
					if("1".equals(sfwy)){
						//验证不可重复(对代偿结果表)
						if(dao.isRepeatForDr(drl,cellContents)){
							resultMap.put("result", false);
							rowResult = false;
							rowError.append("["+drlmc+"]不可重复； ");
							continue;
						}
					}
					//验证最大长度
					if(!checkLength(cellContents,zdcd)){
						resultMap.put("result", false);
						rowResult = false;
						rowError.append("["+drlmc+"]最大长度为:"+zdcd+"； ");
						continue;
					}
					//验证数据格式(来源于导入规则的格式配置)
					if(lsjgsh.startsWith("field")){
						if(lsjgsh.contains("Datetime")){
							String fmt = lsjgsh.substring(lsjgsh.indexOf("(")+1,lsjgsh.indexOf(")"));
							SimpleDateFormat sdf = new SimpleDateFormat(fmt);
							try {
								this.checkDateFormat(sdf,cellContents);
							} catch (ParseException e) {
								resultMap.put("result", false);
								rowResult = false;
								rowError.append("["+drlmc+"]格式为:"+fmt+"； ");
								continue;
							}
						}
					}
					if(lsjgsh.startsWith("json")){
						//这里将数据根据json替换
						HashMap<String,String> jsonMap = new HashMap<String,String>();
						String jsonString = lsjgsh.substring(lsjgsh.indexOf("{")+1,lsjgsh.indexOf("}"));
						String[] jsonArr = jsonString.split(",");
						for(String json:jsonArr){
							String [] keyValue = json.split(":");
							String key = keyValue[0].replaceAll("\"", "");
							String value = keyValue[1].replaceAll("\"", "");
							jsonMap.put(key, value);
						}
						if(jsonMap.keySet().contains(cellContents)){
							excelData.put(drl, jsonMap.get(cellContents));
						}else{
							resultMap.put("result", false);
							rowResult = false;
							rowError.append("["+drlmc+"]"+gshxx+"； ");
							continue;
						}
						
					}
					if(lsjgsh.startsWith("sql")){
						String sql = lsjgsh.substring(lsjgsh.indexOf(":")+1);
						String data = dao.changeCellData(sql,cellContents,drl);
						if(StringUtil.isNull(data)){
							resultMap.put("result", false);
							rowResult = false;
							rowError.append("["+drlmc+"]"+gshxx+"； ");
							continue;
						}else{
							excelData.put(drl, data);
						}
					}
				}
				
			}
			
			//验证发放时间、发放金额、发放内容次数是否一致
			String ffsj = (String)excelData.get("ffsj");
			String ffje = (String)excelData.get("ffje");
			String ffnr = (String)excelData.get("ffnr");
			if(!(ffsj.split("\\|").length==ffje.split("\\|").length&&ffje.split("\\|").length==ffnr.split("\\|").length)){
				resultMap.put("result", false);
				rowResult = false;
				rowError.append("[发放次数]发放时间、发放金额、发放内容次数不一致； ");
			}
			excelData.put("rowError", rowError.toString());
			if(!rowResult){
				errorCount++;
			}
		}
		
		if(!(Boolean)resultMap.get("result")){
			resultMap.put("error", "02");
			//resultMap.put("excelDataList", excelDataList);
			resultMap.put("errorCount", errorCount);
			resultMap.put("totalCount", excelDataList.size());
		}
		return resultMap;
	}

	/**
	 * @throws ParseException  
	 * @描述:验证时间格式，这里提取出方法是为了兼容发放时间等字段的可以有|分隔的不固定数目
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月23日 下午5:20:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sdf
	 * void 返回类型 
	 * @throws 
	 */
	private void checkDateFormat(SimpleDateFormat sdf,String cellContents) throws ParseException {
		if(cellContents.contains("|")){
			String [] cellContentsArr = cellContents.split("\\|");
			for(String str:cellContentsArr){
				sdf.parse(str);
			}
		}else{
			sdf.parse(cellContents);
		}
		
	}

	/** 
	 * @描述:验证字段长度，这里提取出方法是为了兼容发放时间等字段的可以有|分隔的不固定数目
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月23日 下午4:59:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cellContents
	 * @param zdcd
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	private boolean checkLength(String cellContents, Integer zdcd) {
		boolean result = true;
		if(cellContents.contains("|")){
			String [] cellContentsArr = cellContents.split("\\|");
			for(String str:cellContentsArr){
				if(str.length()>zdcd){
					result = false;
					break;
				}
			}
		}else{
			if(cellContents.length()>zdcd){
				result = false;
			}
		}
		return result;
	}

	/** 
	 * @描述:读取excel导入文件中的数据到list
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月20日 上午11:33:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sheet
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	private List<HashMap<String, String>> getExcelDataList(Sheet sheet,List<HashMap<String,String>> drgzxxList) {
		List<HashMap<String,String>> excelDataList = new ArrayList<HashMap<String,String>>();
		int rows = sheet.getRows();
		int cols = sheet.getColumns();
		for(int i=1;i<rows;i++){
			HashMap<String,String> excelRow = new HashMap<String,String>();
			for(int j=0;j<cols;j++){
				excelRow.put(drgzxxList.get(j).get("drl"), sheet.getCell(j, i).getContents().trim());
			}
			excelDataList.add(excelRow);
		}
		return excelDataList;
	}

	/**
	 * @描述:验证导入文件格式是否正确（是否按顺序包含导入列）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月20日 上午11:18:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sheet
	 * @param drgzxxList
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	private HashMap<String,Object> checkImportHeader(Sheet sheet,List<HashMap<String,String>> drgzxxList){
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		Integer rows = sheet.getRows();
		Integer cols = sheet.getColumns();
		
		List<String> excelHeaderList = new ArrayList<String>();
		for(int i=0;i<cols;i++){
			excelHeaderList.add(sheet.getCell(i, 0).getContents());
		}
		if(drgzxxList.size()!=cols){
			resultMap.put("result", false);
			resultMap.put("error", "01");
			resultMap.put("message", "导入文件中导入列不符合模版要求！");
			return resultMap;
		}else{
			for(int j=0;j<drgzxxList.size();j++){
				String drlmc = drgzxxList.get(j).get("drlmc");
				String excelHeaderCol = excelHeaderList.get(j);
				if(!drlmc.equals(excelHeaderCol)){
					resultMap.put("result", false);
					resultMap.put("error", "01");
					resultMap.put("message", "导入文件中导入列不符合模版要求！");
					return resultMap;
				}
			}
			resultMap.put("result", true);
			return resultMap;
		}
	}
	
	/** 
	 * @描述:生成Excel错误提示文件到服务器
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月22日 上午9:56:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param excelDataList
	 * @param path
	 * void 返回类型 
	 * @throws 
	 */
	private String createErrorTipsExcel(List<HashMap<String, String>> excelDataList, String path,
										String drmkdm,List<HashMap<String,String>> drgzxxList) {
		File file = new File(path,drmkdm+"_error.xls");
		
		// 打开文件
		WritableWorkbook book = null;
		try {
			 book = Workbook.createWorkbook(file);
			 WritableSheet sheet1 = book.createSheet("error", 0);
			
			//填充头部
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.WHITE);
			WritableCellFormat cellFormat1 = new WritableCellFormat(font1);  
			cellFormat1.setBackground(Colour.GREEN);
			
			WritableFont font2 = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			WritableCellFormat cellFormat2 = new WritableCellFormat(font2);  
			cellFormat2.setBackground(Colour.YELLOW);
			
			WritableCellFormat cellFormat3 = new WritableCellFormat(font1);  
			cellFormat3.setBackground(Colour.RED);
			
			for(int i=0;i<=drgzxxList.size();i++){
			    Label label = null;
			    if(drgzxxList.size()==i){
			    	label = new Label(i,0,"错误信息" ,cellFormat3);
			    }else{
			    	label = new Label(i,0,drgzxxList.get(i).get("drlmc"),cellFormat1);
			    }
				sheet1.addCell(label);
			}
			
			//填充内容
			for(int i=0;i<excelDataList.size();i++){
				for(int j=0;j<=drgzxxList.size();j++){
				    Label label = null;
				    if(drgzxxList.size()==j){
				    	String errorTip = excelDataList.get(i).get("rowError");
				    	if(StringUtil.isNull(errorTip)){
				    		//错误提示为空，无背景色
				    		label = new Label(j,i+1,errorTip);
				    	}else{
				    		//错误提示不为空，黄色背景色
				    		label = new Label(j,i+1,errorTip,cellFormat2);
				    	}
				    }else{
				    	label = new Label(j,i+1,excelDataList.get(i).get(drgzxxList.get(j).get("drl")));
				    }
					sheet1.addCell(label);
				}
			}
			
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			// 写入数据并关闭文件
			try {
				book.write();
				book.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file.getName();
	}
	
	/** 
	 * @描述:验证excel文件中的数据本身是否有重复，这里只需验证学号，采用java循环
	 * 		  如果验证字段较多较复杂可以考虑数据库临时表
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月22日 下午2:53:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param excelDataList
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws 
	 */
	private HashMap<String, Object> checkExcelDataRepeat(List<HashMap<String, String>> excelDataList) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		boolean result = true;
		int errorCount = 0;
		for(int i=0;i<excelDataList.size()-1;i++){
			for(int j=i+1;j<excelDataList.size();j++){
				HashMap<String,String> excelDataI = excelDataList.get(i);
				HashMap<String,String> excelDataJ = excelDataList.get(j);
				if(excelDataI.get("XH").equals(excelDataJ.get("XH"))){
					excelDataI.put("rowError", "[学号]excel中学号存在重复； ");
					excelDataJ.put("rowError", "[学号]excel中学号存在重复； ");
					result = false;
					errorCount++;
				}
			}
		}
		if(!result){
			resultMap.put("error", "03");
			resultMap.put("errorCount", errorCount+1);
			resultMap.put("totalCount", excelDataList.size());
		}
		resultMap.put("result", result);
		return resultMap;
	}
	
	/**
	 * @throws SQLException  
	 * @描述:将最终验证通过的数据批量导入数据库
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月22日 下午4:14:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param excelDataList
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	private boolean insertDataIntoDB(List<HashMap<String, String>> excelDataList) throws SQLException {
		List<String[]> dcjgParaList = new ArrayList<String[]> ();
		List<String[]> dcjg_ffcsParaList = new ArrayList<String[]> ();
		
		for(HashMap<String, String>excelData:excelDataList){
			String xh = excelData.get("XH");
			String xn = excelData.get("XN");
			String rwqsfsqdc = excelData.get("rwqsfsqdc");
			String xfje = excelData.get("XFJE");
			String dkbj = excelData.get("DKBJ");
			String yhdm = excelData.get("YHDM");
			String dkhth = excelData.get("DKHTH");
			String dkkssj = excelData.get("DKKSSJ");
			String dkjssj = excelData.get("DKJSSJ");
			String dclb = excelData.get("DCLB");
			String rwnf = excelData.get("rwnf");
			String twnf = excelData.get("twnf");
			String ffsj = excelData.get("ffsj");
			String ffje = excelData.get("ffje");
			String ffnr = excelData.get("ffnr");
			
			String [] ffsjArr = {};
			if(!StringUtil.isNull(ffsj)){
				ffsjArr = ffsj.split("\\|");
			}
			
			String [] ffjeArr = {};
			if(!StringUtil.isNull(ffje)){
				ffjeArr = ffje.split("\\|");
			}
			
			String [] ffnrArr = {};
			if(!StringUtil.isNull(ffnr)){
				ffnrArr = ffnr.split("\\|");
			}
			
			for(int i=0;i<ffsjArr.length;i++){
				String [] dcjg_ffcsPara = {xh,xn,ffsjArr[i],ffjeArr[i],ffnrArr[i]};
				dcjg_ffcsParaList.add(dcjg_ffcsPara);
			}
			
			String[] dcjgPara = {xh,xn,rwqsfsqdc,xfje,dkbj,yhdm,dkhth,dkkssj,dkjssj,dclb,rwnf,twnf};
			dcjgParaList.add(dcjgPara);
		}
		
		//批量插入代偿结果信息
		int [] dcjgResult = {};
		if(dcjgParaList.size()>0){
			dao.insertDcjgDataIntoDB(dcjgParaList);
		}
		//批量插入代偿结果发放次数信息
		int [] dcjg_ffcsResult = {};
		if(dcjg_ffcsParaList.size()>0){
			dao.insertDcjgFfcsDataIntoDB(dcjg_ffcsParaList);
		}
		
		return dcjgResult.length>=0&&dcjg_ffcsResult.length>=0;
	}

	/**
	 * @throws Exception  
	 * @描述:导出时查询所有记录，包含发放次数信息，对浙大
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月24日 上午10:49:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDcListForZD(RwfbydcjgModel model, User user) throws Exception {
		return dao.getDcListForZD(model,user);
	}
	
	
}
