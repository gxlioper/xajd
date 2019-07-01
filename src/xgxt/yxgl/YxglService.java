package xgxt.yxgl;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class YxglService {

	private static final YxglService yxglService = new YxglService();

	public static YxglService getInstance() {
		return yxglService;
	}

	YxglService() {
	}

	/**
	 * 保存新生的所有信息，将插入到多张表中 <br>
	 * 分别为："newstusinfo","xybdzcb","stbdzcb","xyybdzcb","ssbdzcb","newstuskb","lstdxxb"
	 * 
	 * @return
	 */
	public boolean saveNewStuInfo(String[] values) throws Exception {
		// String [] values = new
		// String[]{ksh,xh,sfzh,xm,xb,sfdm,csrq,jg,xydm,zydm,bjdm};
		boolean isSaveOk = true;
		String xh = null;
		if (StringUtils.isNull(values[1])) {
			xh = "暂无";
			values[1] = "暂无";
		} else {
			xh = values[1];
		}
		YxglDAO yxglDao = new YxglDAO();
		String[] columns = new String[] { "ksh", "xh", "sfzh", "xm", "xb",
				"sfdm", "csrq", "jg", "xydm", "zydm", "bjdm", "nj" };
		isSaveOk = isSaveOk
				| yxglDao.saveNewStuInfo("newstusinfo", columns, values);// newstusinfo
		String[] tables = new String[] { "xybdzcb", "stbdzcb", "xyybdzcb",
				"ssbdzcb", "newstuskb", "lstdxxb" };
		String[] otherValues = new String[] { values[0], xh }; // ksh,xh
		String[] othercolumns = new String[] { "ksh", "xh" };
		for (String tableName : tables) {
			isSaveOk = isSaveOk
					| yxglDao.saveNewStuInfo(tableName, othercolumns,
							otherValues);
		}
		return isSaveOk;
	}
	
	/**
	 * 打印新生报到情况汇总表
	 * @param wwb
	 * */
	public void printXsbdhzb(WritableWorkbook wwb){
		YxglDAO dao = new YxglDAO();		
		String nd =  Base.currNd;
		List<String> pyccList = dao.getPyccList();
		
		WritableSheet ws = wwb.getSheet(0);
		WritableCellFormat wcfTytle = new WritableCellFormat();
		try{
			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);			
			//表头
			wcfTytle = new WritableCellFormat();
			wcfTytle.setAlignment(Alignment.CENTRE);
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);
			
			int lineNumber = 2;	
			int number = 0;
			List<String> xylist = dao.getXyList();
			for(int i=0; i<xylist.size();i++){
				String xymc = xylist.get(i);
				ws.addCell(new Label(0,2+i+1,String.valueOf(i+1),wcfTytle));//序号
				ws.addCell(new Label(1,2+i+1,xymc,wcfTytle));//学院名称	
				number = 2+i+1;	
			}
			for(int i=0; i<pyccList.size(); i++){				
				String pycc = pyccList.get(i);
				List<String> sdList = dao.getSdrsList(pycc,nd);
				List<HashMap<String, String>> ydList = dao.getYdrsList(pycc,nd);				
				
				ws.addCell(new Label(lineNumber+i,1,pycc,wcfTytle));//培养层次
				ws.mergeCells(lineNumber+i,1, lineNumber+i+2, 0);
				
				ws.addCell(new Label(lineNumber+i,2,"应到人数",wcfTytle));//应到人数
				ws.addCell(new Label(lineNumber+i+1,2,"实到人数",wcfTytle));//实到人数
				ws.addCell(new Label(lineNumber+i+2,2,"报到率",wcfTytle));//报到率
				if(ydList != null && ydList.size()>0){
					int ydTotal = 0;
					int sdTotal = 0;
					for(int k=0; k<ydList.size(); k++){
						HashMap<String, String> map = ydList.get(k);
						String ydrs = map.get("ydrs");
						String sdrs = "0";
						if(sdList.size()>k){
							sdrs = sdList.get(k);
						}
						sdrs = sdrs == null || "".equalsIgnoreCase(sdrs) ? "0" : sdrs;
						ydrs = ydrs == null || "".equalsIgnoreCase(ydrs) ? "0" : ydrs;
						ydTotal += Integer.parseInt(ydrs);
						sdTotal += Integer.parseInt(sdrs);
						
						double bdl =0;
						if(Integer.parseInt(sdrs) != 0 && Integer.parseInt(ydrs)  != 0){
							bdl = Float.parseFloat(sdrs)/Float.parseFloat(ydrs)*100;
						}
						BigDecimal big = new BigDecimal(bdl,new MathContext(4,RoundingMode.HALF_UP));
						bdl = big.doubleValue();
						
						ws.addCell(new Label((lineNumber+i),3+k,ydrs,wcfTytle));//应到人数
						ws.addCell(new Label(lineNumber+1+i,3+k,sdrs,wcfTytle));//实到人数
						ws.addCell(new Label(lineNumber+2+i,3+k,String.valueOf(bdl + "%"),wcfTytle));//报到率
						
						//TODO 写入到Excel
						if(k == ydList.size()-1){
							ws.addCell(new Label(1,number+1,"合计",wcfTytle));//合计
							ws.addCell(new Label(lineNumber+i,number+1,String.valueOf(ydTotal),wcfTytle));//合计应到人数
							ws.addCell(new Label(lineNumber+i+1,number+1,String.valueOf(sdTotal),wcfTytle));//合计实到人数
							
							bdl = Float.parseFloat(String.valueOf(sdTotal))/Float.parseFloat(String.valueOf(ydTotal))*100;
							bdl =0;
							if(Integer.parseInt(String.valueOf(sdTotal)) != 0 && Integer.parseInt(String.valueOf(ydTotal))  != 0){
								bdl = Float.parseFloat(String.valueOf(sdTotal))/Float.parseFloat(String.valueOf(ydTotal))*100;
							}
							big  = new BigDecimal(bdl,new MathContext(4,RoundingMode.HALF_UP));
							bdl = big.doubleValue();
							ws.addCell(new Label(lineNumber+i+2,number+1,String.valueOf(bdl) + "%",wcfTytle));//合计报到率
						}
					}				 
				}
				lineNumber += 2;
			}
			//TODO 写入合计信息到Excel
			List<String> ydrsTotalList = dao.getYdrsTotalList(nd);
			List<String> sdrsTotalList = dao.getSdrsTotalList(nd);
			int ydTotal = 0;
			int sdTotal = 0;
			
			ws.mergeCells(lineNumber + pyccList.size(),1, lineNumber+pyccList.size()+2, 0);
			
			ws.addCell(new Label(lineNumber + pyccList.size(),2,"应到人数",wcfTytle));//应到人数
			ws.addCell(new Label(lineNumber + pyccList.size()+1,2,"实到人数",wcfTytle));//实到人数
			ws.addCell(new Label(lineNumber + pyccList.size()+2,2,"报到率",wcfTytle));//报到率
			
			for(int k=0; k<ydrsTotalList.size(); k++){
				String ydrs = ydrsTotalList.get(k);
				String sdrs = "0";
				if(sdrsTotalList.size()>k){
					sdrs = sdrsTotalList.get(k);
				}
				
				sdrs = sdrs == null || "".equalsIgnoreCase(sdrs) ? "0" : sdrs;
				ydrs = ydrs == null || "".equalsIgnoreCase(ydrs) ? "0" : ydrs;
				double bdl =0;
				if(Integer.parseInt(sdrs) != 0 && Integer.parseInt(ydrs)  != 0){
					bdl = Float.parseFloat(sdrs)/Float.parseFloat(ydrs)*100;
				}
				BigDecimal big = new BigDecimal(bdl,new MathContext(4,RoundingMode.HALF_UP));
				bdl = big.doubleValue();
				
				ydTotal += Integer.parseInt(ydrs);
				sdTotal += Integer.parseInt(sdrs);
				
				//TODO 写入到Excel
				ws.addCell(new Label((lineNumber + pyccList.size()),1,"合计",wcfTytle));
				ws.addCell(new Label((lineNumber + pyccList.size()),3+k,ydrs,wcfTytle));
				ws.addCell(new Label((lineNumber + pyccList.size()+1),3+k,sdrs,wcfTytle));
				ws.addCell(new Label((lineNumber + pyccList.size()+2),3+k,String.valueOf(bdl)+ "%",wcfTytle));
				if(k == ydrsTotalList.size()-1){
					ws.addCell(new Label(lineNumber + pyccList.size(),number+1,String.valueOf(ydTotal),wcfTytle));//合计应到人数
					ws.addCell(new Label(lineNumber + pyccList.size()+1,number+1,String.valueOf(sdTotal),wcfTytle));//合计实到人数
					
					bdl = Float.parseFloat(String.valueOf(sdTotal))/Float.parseFloat(String.valueOf(ydTotal))*100;
					big = new BigDecimal(bdl,new MathContext(4,RoundingMode.HALF_UP));
					bdl = big.doubleValue();
					ws.addCell(new Label(lineNumber + pyccList.size()+2,number+1,String.valueOf(bdl) + "%",wcfTytle));//合计报到率
				}
			}
			//页头
			wcfTytle = new WritableCellFormat();
			wcfTytle.setAlignment(Alignment.CENTRE);	
			
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(20);
			wcfTytle.setFont(wfTytle);
			ws.mergeCells(0, 0, lineNumber + pyccList.size()+2, 0);
			
			ws.addCell(new Label(0, 0, nd+"级新生报到情况汇总表", wcfTytle));
			//向客户端输出
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
