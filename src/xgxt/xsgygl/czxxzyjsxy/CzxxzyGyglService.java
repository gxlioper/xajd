/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2010-1-20 上午10:43:54</p>
 */
package xgxt.xsgygl.czxxzyjsxy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;

public class CzxxzyGyglService {
	public List<HashMap<String,String>> serv_dormcheckQery(WsjcModel model){
	     CzxxzyGyglDAO mydao = new CzxxzyGyglDAO();
	     return mydao.dormcheckQery(model);
	}
	/**
	 * 寝室卫生检查 表头
	 */
	public List<HashMap<String, String>> getQswsjcTopTr(String jcsj) {
		DAO dao = DAO.getInstance();
		
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"r","ldmc","cs","qsh","fs","zrrs","jcszrrs"}; 
		colListCN = new String[]{"行号","楼栋名称","楼层","寝室号","分数","当前入住人数",jcsj+"检查时所住人数"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	public boolean serv_dormCheckSave(WsjcModel model){
		CzxxzyGyglDAO mydao = new CzxxzyGyglDAO();
		return mydao.dormCheckSave(model);
	}
	public List<HashMap<String,String>>serv_dormcheckResult(WsjcModel model){
		CzxxzyGyglDAO mydao = new CzxxzyGyglDAO();
		return mydao.dormcheckResult(model);
	}	
	public List<HashMap<String, String>> getjcResultTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"ldmc","cs","qsh","jcjg"}; 
		colListCN = new String[]{"楼栋名称","楼层","寝室号","检查结果"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	public boolean serv_autocount(SsgffModel model){
		CzxxzyGyglDAO mydao = new CzxxzyGyglDAO();
		return mydao.autocount(model);
		
	}
	public ArrayList<String[]>serv_dpointResearch(SsgffModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		CzxxzyGyglDAO mydao = new CzxxzyGyglDAO();
		return mydao.dpointResearch(model);
	}
	public List<HashMap<String, String>> getDormpointTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"xh","xm","xymc","bjmc","nwf","glf","gff"}; 
		colListCN = new String[]{"学号","姓名","院系","班级","内务分","纪律分","规范分"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	public void serv_expShGFF(WritableWorkbook wwb,SsgffModel model) throws Exception  {
		CzxxzyGyglDAO mydao = new CzxxzyGyglDAO();
		List<HashMap<String,String>>  list = mydao.dao_expShGFF(model);
		String[] ColumnName = new String[]{"xh","xm","xymc","bjmc","nwf","glf","gff"}; ;
		String[] ColumnNameCN  =new String[]{"学号","姓名","院系","班级","内务分","纪律分","规范分"}; 		
		ExcelMB ex = new ExcelMB();
		WritableSheet ws = wwb.createSheet("学生宿舍生活规范分表", 0);
		ws.mergeCells(0, 0, 6, 1);
		String bjdm = model.getBjdm();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String nj = model.getNj();
		String clin="";
		
		if(!Base.isNull(bjdm)){
			clin = CommonQueryDAO.dao_getInfo("view_njxyzybj", new String[]{"bjmc"}," where bjdm='"+bjdm+"' and rownum=1").get("bjmc");
		}else{
			HashMap<String,String> map = new HashMap<String, String>();
			if(!Base.isNull(zydm)){				
				map = CommonQueryDAO.dao_getInfo("view_njxyzybj", new String[]{"zymc","nj","xymc"}," where zydm='"+zydm+"' and xydm like '"+(Base.isNull(xydm)?"%":xydm)+"' and  nj like '"+(Base.isNull(nj)?"%":nj)+"' and rownum=1");
				clin = map.get("nj")+" "+map.get("xymc")+" "+map.get("zymc");
			}else{				
				if(!Base.isNull(xydm)||!Base.isNull(nj)){
					clin  = Base.isNull(nj)?"":nj+"年级 ";
					map = CommonQueryDAO.dao_getInfo("view_njxyzybj", new String[]{"nj","xymc"}," where  xydm ='"+xydm+"' and nj like '"+(Base.isNull(nj)?"%":nj)+"' and rownum=1");
					clin +=map.get("xymc");
				}else{
					clin = "在校住宿信息";
				}
			}
		}
		
		ex.printToOneCell_multy(ws," "+clin, 0, 0,13, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
		wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		WritableCellFormat wcf1 = new WritableCellFormat(); // 构造单元格格式
		wcf1 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.NONE);
		ws.mergeCells(0,2,6,2);
		ws.addCell(new Label(0,2,model.getXn()+"学年"+" "+CommonQueryDAO.getXqMc(model.getXq())+" 学期",wcf1));
		for (int m = 0; m < ColumnNameCN.length; m++) {
			ws.addCell(new Label(m,3, ColumnNameCN[m],wcf));
		}
		for(int i=0;i<list.size();i++){
			for(int j=0;j<ColumnName.length;j++){
				ws.addCell(new Label(j,4+i,list.get(i).get(ColumnName[j]),wcf));
			}
		}
	}
}
