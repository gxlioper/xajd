
package xgxt.studentInfo.zzdx;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.utils.ExcelMethods;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中州大学高基报表Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-26</p>
 */
public class XsxxZzdxService {
	
	XsxxZzdxDao zzdxDao = new XsxxZzdxDao();
	
	/**
	 * Method getReportList 获取报表列表 
	 * @return list
	 */
	public List getReportList(){		
		List list= zzdxDao.getReportList();
		return list;
	}
	
	/**
	 *Method printGjbb222 生成Excel报表
	 * @param WritableWorkbook
	 * **/
	@SuppressWarnings("unchecked")
	public void printGjbb222(WritableWorkbook wwb){
		//横行总计数据集合
		List levCountList = zzdxDao.getLevCountListOfGjbb222();
		//纵行总计数据集合
		List verCountList = zzdxDao.getVerCountListOfGjbb222("");
		//除去港澳台的地区数据集合
		List sydList = zzdxDao.getSydListOfGjbb222();
		//港澳台地区数据集合
		List gatList = null;
		//各地区各培养层次总人数集合
		List standardList = null;
        //各地区除港澳台的普通本、专科招生数
		List bzkGatList = null;
		
		HashMap<String, String> hmap = new HashMap<String, String>();
		HashMap<String, String> tmap = new HashMap<String, String>();
		String sTemp = "";
		
		//除港澳台总的在校学生数
		String sTotalStu = zzdxDao.getTotalStudentCount();
		//港澳台总的在校学生学生数
		String sGatTotal = zzdxDao.getGatTotalStuCount();
		//总的普通本、专科招生数
		String sBzkTotal = zzdxDao.getBzkTotalStuCount();
		//港澳台总的普通本、专科招生数
		String sBzkGatTotal = zzdxDao.getBzkGatTotal();
		
		try {
		    WritableSheet ws = wwb.getSheet(0);
		    WritableCellFormat wcfTytle = new WritableCellFormat();
		    
		    //除港澳台总的在校学生数
			ws.addCell(new Label(2,5,sTotalStu,wcfTytle));
			//港澳台总的在校学生学生数
			ws.addCell(new Label(2,37,sGatTotal,wcfTytle));
            //总的普通本、专科招生数
			ws.addCell(new Label(9,5,sBzkTotal,wcfTytle));
			//港澳台总的普通本、专科招生数
			ws.addCell(new Label(9,37,sBzkGatTotal,wcfTytle));
			
			//Excel表横行总计数据
			for(int i=0; i<levCountList.size(); i++){
				hmap = (HashMap<String, String>) levCountList.get(i);
				if(i<levCountList.size()-2){
					tmap = (HashMap<String, String>) levCountList.get(i+1);
				}
				sTemp = String.valueOf((Integer.parseInt(hmap.get("count")) + Integer.parseInt(tmap.get("count"))));
				if(i==3){
					ws.addCell(new Label(6,5,sTemp,wcfTytle));
				}else if(i==4){
					
				}else if(i==5){					
					ws.addCell(new Label(7,5,sTemp,wcfTytle));
				}else if(i==6){
					
				}else if(i==7){					
					ws.addCell(new Label(8,5,sTemp,wcfTytle));
				}else if(i==8){
					
				}else{
					ws.addCell(new Label(3+i,5,hmap.get("count"),wcfTytle));
				}
			}
			
			//Excel表除港澳台的地区列
			for(int i=0;i<sydList.size();i++){
				hmap = (HashMap<String, String>) sydList.get(i);				
				sTemp = (i<8) ? "0"+(i+Integer.parseInt("2")) : String.valueOf(i+Integer.parseInt("2"));
				ws.addCell(new Label(0,6+i,hmap.get("sydq"),wcfTytle));
				ws.addCell(new Label(1,6+i,sTemp,wcfTytle));
			}
			
			//Excel表纵行总计数据
			for(int i=0; i<verCountList.size(); i++){
				hmap = (HashMap<String, String>) verCountList.get(i);
				ws.addCell(new Label(2,6+i,hmap.get("count"),wcfTytle));
			}
			
			//Excel表各培养层次学生总数据
			
			String[] condition = new String[]{" where pycc='博士'"," where pycc='硕士'"," where pycc='研究生'", 
				                  " where pycc='普通本科' or pycc='普通专科'"," where pycc='成人本科' or pycc='成人专科'",
				                  " where pycc='网络本科' or pycc='网络专科'"};			
			for(int j=0; j<condition.length; j++){
				standardList = zzdxDao.getVerCountListOfGjbb222(condition[j]);
				for(int i=0; i<31; i++){					
				hmap = (HashMap<String, String>) standardList.get(i);
				ws.addCell(new Label(3+j,6+i,hmap.get("count"),wcfTytle));
			    }
		    }
			
			//Excel表港澳台地区数据
			gatList = zzdxDao.getGatListOfGjbb222();
			for(int i=0; i<gatList.size(); i++){
			    hmap = (HashMap<String, String>) gatList.get(i);
			    if(i<gatList.size()-2){
			    	tmap = (HashMap<String, String>) gatList.get(i+1);
			    }
			    if(i==3){
					ws.addCell(new Label(6,37,hmap.get("count"),wcfTytle));
				}else if(i==4){
					
				}else if(i==5){					
					ws.addCell(new Label(7,37,hmap.get("count"),wcfTytle));
				}else if(i==6){
					
				}else if(i==7){					
					ws.addCell(new Label(8,37,hmap.get("count"),wcfTytle));
				}else if(i==8){
					
				}else{
					ws.addCell(new Label(3+i,37,hmap.get("count"),wcfTytle));
				}			    
			}
			
			//除港澳台的普通本、专科招生数
			bzkGatList = zzdxDao.getBzkListOfGjbb222();
			for(int i=0;i<bzkGatList.size(); i++){
				hmap = (HashMap<String, String>)bzkGatList.get(i);
				ws.addCell(new Label(9,6+i,hmap.get("count"),wcfTytle));
			}
			
			//向客户端输出
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}
	
	/**
	 *Method printGjbb24 生成Excel报表 在校学生中其他情况
	 * @param WritableWorkbook
	 * **/
	@SuppressWarnings("unchecked")
	public void printGjbb24(WritableWorkbook wwb){
		List list = null;
		HashMap<String, String> map = new HashMap<String, String>();
		int iCount = 0;
		
		try {
		    WritableSheet ws = wwb.getSheet(0);
		    WritableCellFormat wcfTytle = new WritableCellFormat();
		    
		    //共产党员 共青团员  民主党派
		    //TODO 民主党派是否是除共产党和共青团的
		    String[] condition = {" and zzmm = '01'"," and zzmm='02'"};
		    for(int j=0 ;j<condition.length; j++){
			    list = zzdxDao.getOtherInfo(condition[j]);
			    for(int i =0; i<list.size(); i++){
			    	map = (HashMap<String, String>)list.get(i);
			    	iCount += Integer.parseInt(map.get("count"));
			    	ws.addCell(new Label(2+j,5+i,map.get("count"),wcfTytle));
			    }
			    ws.addCell(new Label(2+j,4,String.valueOf(iCount),wcfTytle));
			    iCount = 0;
		    }
		   		        
		    
		    //TODO 华侨
		    
		    
		    //TODO 港澳台
		    
		    //少数民族
		    list = zzdxDao.getOtherInfo(" and '01'<mz and mz<'57'");
		    for(int i =0; i<list.size(); i++){
		    	map = (HashMap<String, String>)list.get(i);
		    	iCount += Integer.parseInt(map.get("count"));
		    	ws.addCell(new Label(7,5+i,map.get("count"),wcfTytle));
		    }
		    ws.addCell(new Label(7,4,String.valueOf(iCount),wcfTytle));
		    iCount = 0;
		   
		    
		    //TODO 残疾人
		    //特教学院的学生数
		    list = zzdxDao.getCjrInfo();
		    for(int i=0; i<list.size(); i++){
		    	map = (HashMap<String, String>) list.get(i);
		    	iCount += Integer.parseInt(map.get("count"));
		    	ws.addCell(new Label(8,5+i,map.get("count"),wcfTytle));
		    }
		    ws.addCell(new Label(8,4,String.valueOf(iCount),wcfTytle));
		    iCount = 0;
		    //向客户端输出
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		    
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 *Method printGjbb221 生成Excel报表 在校学生年龄情况
	 * @param WritableWorkbook
	 * **/
	@SuppressWarnings("unchecked")
	public void printGjbb221(WritableWorkbook wwb){
		List list = null;
		List othList = null;
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> tmap = new HashMap<String, String>();
		String condition = "";
		
		int iCell = 0;
		int iRow = 0;
		int iRowCount = 0;
		int iCellCount = 0;
		
		try {
		    WritableSheet ws = wwb.getSheet(0);
		    WritableCellFormat wcfTytle = new WritableCellFormat();		    
		    
		    //各年龄段各层次的学生数
		    for(int i=0; i<16; i++){
		    	iRowCount = 0;
			    iCellCount = 0;
		    	condition = " and nl=" + (Integer.parseInt("17")+i-1);
		    	if(i==0){
		    		condition = " and (nl>0 and nl<200)";
		    	}
		    	if(i==1){
		    		condition = " and (nl<=17 and nl>0)";
		    	}
		    	if(i==15){
		    		condition = " and (nl>30 and nl<200)";
		    	}
		    	list = zzdxDao.getStuAgeInfo(condition);
		    	othList = zzdxDao.getStuAgeInfo(condition + " and (xb='2' or xb='女')");
		    	for(int j=0; j<9; j++){
		    		map = (HashMap<String, String>)list.get(j);
		    		tmap = (HashMap<String, String>)othList.get(j);
		    		iRowCount = iRowCount + Integer.parseInt(map.get("count"));
		    		iCellCount = iCellCount + Integer.parseInt(tmap.get("count"));
		    		
		    		if(j==0){
		    			//奇数行
		    			ws.addCell(new Label(2+i,5+j+1,map.get("count"),wcfTytle));
		    			ws.addCell(new Label(2+i,7,tmap.get("count"),wcfTytle));
		    			iRow = 5+j+1;
		    			iCell = 5+j+1;
		    		}else{
		    			//偶数行
		    			iRow = iRow+2;
		    			iCell = iRow + 1;
		    			ws.addCell(new Label(2+i,iRow,map.get("count"),wcfTytle));
		    			ws.addCell(new Label(2+i,iCell,tmap.get("count"),wcfTytle));
		    		}
		    	}
		    	ws.addCell(new Label(2+i,4, String.valueOf(iRowCount),wcfTytle));
		    	ws.addCell(new Label(2+i,5,String.valueOf(iCellCount),wcfTytle));
		    }
		    
		    //向客户端输出
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		    
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *Method printGjbb212 生成Excel报表 普通本科、专科分专业学生数
	 * @param WritableWorkbook
	 * **/
	@SuppressWarnings("unchecked")
	public void printGjbb212(WritableWorkbook wwb){
		HashMap<String, String> map = new HashMap<String, String>();
		int iCount = 0;
		try {
		    WritableSheet ws = wwb.getSheet(0);
		    WritableCellFormat wcfTytle = new WritableCellFormat();
		    //专业学制信息
		    List zymcList = zzdxDao.getZymcList();
		    //毕业生数据
		    List bysList = zzdxDao.getBysCountByZy();// byjr='毕业'
		    //授予学位数据
		    List xwCountList = zzdxDao.getXwCountList();//xwzsh is not null
		    //授予学位女生数
		   // int iTotalGirlOfXw = zzdxDao.getTotalGirlOfXw();
		    //招生数据
		    List zsList = zzdxDao.getZsList(Base.currNd);
		    //应届生
		    List yjsList = zzdxDao.getYjsList(Base.currNd);
		    //春季招生
		    List cjzsList = zzdxDao.getCjzsList(Base.currNd);
		    //分专业在校学生数
		    List zxxsList = zzdxDao.getZxListByZy(Base.currNd);
		    //一年级 
		    List firstGrade = zzdxDao.getZxxsByGrade(" and dqnj = 1");
		    //二年级
		    List secondeGrade = zzdxDao.getZxxsByGrade(" and dqnj = 2");
		    //三年级 
		    List threeGrade = zzdxDao.getZxxsByGrade(" and dqnj = 3");
		    //四年级
		    List fourGrade = zzdxDao.getZxxsByGrade(" and dqnj = 4");
		    //五年级
		    List fiveGrade = zzdxDao.getZxxsByGrade(" and dqnj >= 5");
		    //预计毕业生数据
		    List yjbysList = zzdxDao.getYjbysList();
		    //TODO查询数据写入到Excel表
		    //写专业名称,专业代码,学制列
		    for(int i=0; i<zymcList.size(); i++){
		    	map = (HashMap<String, String>)zymcList.get(i);
		    	ws.addCell(new Label(0,8+i, String.valueOf(map.get("zymc")==null ? "" : map.get("zymc")),wcfTytle));
		    	ws.addCell(new Label(1,8+i, String.valueOf(map.get("zydm")==null ? "" : map.get("zydm")),wcfTytle));
		    	ws.addCell(new Label(2,8+i, String.valueOf(map.get("xz")==null ? "" : map.get("xz")),wcfTytle));
		    }
		    
		    //TODO分专业毕业生数
		    for(int i=0; i<bysList.size(); i++){
		    	map = (HashMap<String, String>)bysList.get(i);
		    	ws.addCell(new Label(3,8+i, String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCount += Integer.parseInt(map.get("count"));		    	
		    }		
		    //总毕业生数
		    ws.addCell(new Label(3,6, String.valueOf(iCount),wcfTytle));
		    //总毕业女生数
		    ws.addCell(new Label(3,7, String.valueOf(zzdxDao.getByCountOfGirl()),wcfTytle));
		    iCount= 0;
		   
		    //TODO分专业授予学位数
		    for(int i=0; i<xwCountList.size(); i++){
		    	map = (HashMap<String, String>)xwCountList.get(i);
		    	ws.addCell(new Label(4,8+i, String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCount += Integer.parseInt(map.get("count"));		    	
		    }
		    //总授予学位数
		    ws.addCell(new Label(4,6, String.valueOf(iCount),wcfTytle));
		    //授予女生学位数
		    ws.addCell(new Label(4,7, String.valueOf(zzdxDao.getTotalGirlOfXw()),wcfTytle));
		    iCount = 0;
		    
		    //分专业招生数
		    for(int i=0; i<zsList.size(); i++){
		    	map = (HashMap<String, String>)zsList.get(i);
		    	ws.addCell(new Label(5,8+i, String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCount += Integer.parseInt(map.get("count"));
		    }
		    //招生总数
		    ws.addCell(new Label(5,6, String.valueOf(iCount),wcfTytle));
		    //招女生总数
		    ws.addCell(new Label(5,7, String.valueOf(zzdxDao.getTotalGirlOfZs(Base.currNd)),wcfTytle));
		    iCount = 0;
		    
		    //应届生招生数
		    for(int i=0; i<yjsList.size(); i++){
		    	map = (HashMap<String, String>) yjsList.get(i);
		    	ws.addCell(new Label(6,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCount += Integer.parseInt(map.get("count"));
		    }
		    //总招收应届生数
		    ws.addCell(new Label(6,6, String.valueOf(iCount),wcfTytle));
		    //总招收应届女生数
		    ws.addCell(new Label(6,7, String.valueOf(zzdxDao.getTotalGirlOfZsyjs(Base.currNd)),wcfTytle));
		    iCount = 0;
		    
		    //春季招生
		    for(int i=0; i<cjzsList.size(); i++){
		    	map = (HashMap<String, String>) cjzsList.get(i);
		    	ws.addCell(new Label(7,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCount += Integer.parseInt(map.get("count"));
		    }
		    //春季招生总数
		    ws.addCell(new Label(7,6, String.valueOf(iCount),wcfTytle));
		    //春季招生女生总数
		    ws.addCell(new Label(7,7, String.valueOf(zzdxDao.getTotalGirlOfCjzs(Base.currNd)),wcfTytle));
		    iCount = 0;
		    
		    //在校学生数
		    for(int i=0; i<zxxsList.size(); i++){
		    	map = (HashMap<String, String>) zxxsList.get(i);
		    	ws.addCell(new Label(8,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCount += Integer.parseInt(map.get("count"));
		    }
		    //在校学生总数
		    ws.addCell(new Label(8,6, String.valueOf(iCount),wcfTytle));
		    //在校女生总数
		    ws.addCell(new Label(8,7, String.valueOf(zzdxDao.getTotalGirlOfZx()),wcfTytle));
		    iCount = 0;
		    
		    //分年级数据
		    int iCountFir = 0;
		    int iCountSec = 0;
		    int iCountThr = 0;
		    int iCountFou = 0;
		    int iCountFiv = 0;
		    for(int i=0; i<firstGrade.size(); i++){
		    	//一年级数据
		    	map = (HashMap<String, String>)firstGrade.get(i);
		    	ws.addCell(new Label(9,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCountFir += Integer.parseInt(map.get("count"));
		    	//二年级数据
		    	map = (HashMap<String, String>)secondeGrade.get(i);
		    	ws.addCell(new Label(10,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCountSec += Integer.parseInt(map.get("count"));
		    	//三年级数据
		    	map = (HashMap<String, String>)threeGrade.get(i);
		    	ws.addCell(new Label(11,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCountThr += Integer.parseInt(map.get("count"));
		    	//四年级数据
		    	map = (HashMap<String, String>)fourGrade.get(i);
		    	ws.addCell(new Label(12,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCountFou += Integer.parseInt(map.get("count"));
		    	//五年级及以上数据
		    	map = (HashMap<String, String>)fiveGrade.get(i);
		    	ws.addCell(new Label(13,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCountFiv += Integer.parseInt(map.get("count"));
		    }
		    //一年级在校总数
		    ws.addCell(new Label(9,6, String.valueOf(iCountFir),wcfTytle));
		    //一年级在校女生总数
		    ws.addCell(new Label(9,7, String.valueOf(zzdxDao.getTotalGrilByGrade(" and dqnj = 1")),wcfTytle));
		    //二年级在校总数
		    ws.addCell(new Label(10,6, String.valueOf(iCountSec),wcfTytle));
		    //二年级在校女生总数
		    ws.addCell(new Label(10,7, String.valueOf(zzdxDao.getTotalGrilByGrade(" and dqnj = 2")),wcfTytle));
		    //三年级在校总数
		    ws.addCell(new Label(11,6, String.valueOf(iCountThr),wcfTytle));
		    //三年级在校女生总数
		    ws.addCell(new Label(11,7, String.valueOf(zzdxDao.getTotalGrilByGrade(" and dqnj = 3")),wcfTytle));
		    //四年级在校总数
		    ws.addCell(new Label(12,6, String.valueOf(iCountFou),wcfTytle));
		    //四年级在校女生总数
		    ws.addCell(new Label(12,7, String.valueOf(zzdxDao.getTotalGrilByGrade(" and dqnj = 4")),wcfTytle));
		    //五年级及以上在校总数
		    ws.addCell(new Label(13,6, String.valueOf(iCountFiv),wcfTytle));
		    //五年级及以上在校女生总数
		    ws.addCell(new Label(13,7, String.valueOf(zzdxDao.getTotalGrilByGrade(" and dqnj >= 5")),wcfTytle));
		    
		    //预计毕业生数
		    for(int i=0; i<yjbysList.size();i++){
		    	map = (HashMap<String, String>) yjbysList.get(i);
		    	ws.addCell(new Label(14,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCount += Integer.parseInt(map.get("count"));
		    }
		    //预计毕业生总数
		    ws.addCell(new Label(14,6, String.valueOf(iCount),wcfTytle));
		    //预计毕业生女生总数
		    ws.addCell(new Label(14,7, String.valueOf(zzdxDao.getGirlOfYjby()),wcfTytle));
		    
		    //向客户端输出
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *Method printGjbb231 生成Excel报表 学生变动情况表
	 * @param WritableWorkbook
	 * **/
	@SuppressWarnings("unchecked")
	public void printGjbb231(WritableWorkbook wwb){
		HashMap<String, String> map = new HashMap<String, String>();
		try {
		    WritableSheet ws = wwb.getSheet(0);
		    WritableCellFormat wcfTytle = new WritableCellFormat();		    
		    //TODO查询数据写入到Excel表
		    //上学年初报表在校学生数
		    List superYear = zzdxDao.getSuperData();
		    //增加学生数总计
		    List increaseList = zzdxDao.getIncrease();
		    //分层次招生数
		    List zsList = zzdxDao.getZszxByCc();
		    //复学学生数
		    List fxList = zzdxDao.getYdxxByCc(" and ydlbmc='复学'");
		    //转入学生数
		    List zrList = zzdxDao.getYdxxByCc(" and ydlbmc='转入'");
		    //减少学生数总计
		    List jsxxList = zzdxDao.getJsxxList();
		    //毕业学生数
		    List byList = zzdxDao.getBjyList(" and byjr = '毕业'");
		    //结业学生数
		    List jyList = zzdxDao.getBjyList(" and byjr = '结业'");
		    //休学学生数
		    List xxList = zzdxDao.getYdxxByCc(" and ydlbmc = '休学'");
		    //退学学生数
		    List txList = zzdxDao.getYdxxByCc(" and ydlbmc = '退学'");
		    //开除学生数
		    List kcList = zzdxDao.getYdxxByCc(" and ydlbmc = '开除'");
		    //死亡学生数
		    List swList = zzdxDao.getYdxxByCc(" and ydlbmc = '死亡'");
		    //转出学生数
		    List zcList = zzdxDao.getYdxxByCc(" and ydlbmc = '转出'");
		    //本学年初报表在校学生数
		    List currList = zzdxDao.getCurrYear();
		    
		    for(int i=0; i<superYear.size(); i++){
		    	//上学年初报表在校学生数
		    	map = (HashMap<String, String>) superYear.get(i);
		    	ws.addCell(new Label(2,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//增加学生数总计
		    	map = (HashMap<String, String>) increaseList.get(i);
		    	ws.addCell(new Label(3,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//招生数
		    	map = (HashMap<String, String>) zsList.get(i);
		    	ws.addCell(new Label(4,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//复学学生数
		    	map = (HashMap<String, String>) fxList.get(i);
		    	ws.addCell(new Label(5,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//转入学生数
		    	map = (HashMap<String, String>) zrList.get(i);
		    	ws.addCell(new Label(6,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//减少学生数总计
		    	map = (HashMap<String, String>) jsxxList.get(i);
		    	ws.addCell(new Label(8,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//毕业学生数
		    	map = (HashMap<String, String>) byList.get(i);
		    	ws.addCell(new Label(9,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//结业学生数
		    	map = (HashMap<String, String>) jyList.get(i);
		    	ws.addCell(new Label(10,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//休学学生数
		    	map = (HashMap<String, String>) xxList.get(i);
		    	ws.addCell(new Label(11,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//退学学生数
		    	map = (HashMap<String, String>) txList.get(i);
		    	ws.addCell(new Label(12,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//开除学生数
		    	map = (HashMap<String, String>) kcList.get(i);
		    	ws.addCell(new Label(13,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//死亡学生数
		    	map = (HashMap<String, String>) swList.get(i);
		    	ws.addCell(new Label(14,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//转出学生数
		    	map = (HashMap<String, String>) zcList.get(i);
		    	ws.addCell(new Label(15,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//本学年初报表在校学生数
		    	map = (HashMap<String, String>) currList.get(i);
		    	ws.addCell(new Label(17,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    }
		    //向客户端输出
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
