/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2010-1-20 ����10:43:54</p>
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
	 * ����������� ��ͷ
	 */
	public List<HashMap<String, String>> getQswsjcTopTr(String jcsj) {
		DAO dao = DAO.getInstance();
		
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"r","ldmc","cs","qsh","fs","zrrs","jcszrrs"}; 
		colListCN = new String[]{"�к�","¥������","¥��","���Һ�","����","��ǰ��ס����",jcsj+"���ʱ��ס����"}; 		
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
		colListCN = new String[]{"¥������","¥��","���Һ�","�����"}; 		
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
		colListCN = new String[]{"ѧ��","����","Ժϵ","�༶","�����","���ɷ�","�淶��"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	public void serv_expShGFF(WritableWorkbook wwb,SsgffModel model) throws Exception  {
		CzxxzyGyglDAO mydao = new CzxxzyGyglDAO();
		List<HashMap<String,String>>  list = mydao.dao_expShGFF(model);
		String[] ColumnName = new String[]{"xh","xm","xymc","bjmc","nwf","glf","gff"}; ;
		String[] ColumnNameCN  =new String[]{"ѧ��","����","Ժϵ","�༶","�����","���ɷ�","�淶��"}; 		
		ExcelMB ex = new ExcelMB();
		WritableSheet ws = wwb.createSheet("ѧ����������淶�ֱ�", 0);
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
					clin  = Base.isNull(nj)?"":nj+"�꼶 ";
					map = CommonQueryDAO.dao_getInfo("view_njxyzybj", new String[]{"nj","xymc"}," where  xydm ='"+xydm+"' and nj like '"+(Base.isNull(nj)?"%":nj)+"' and rownum=1");
					clin +=map.get("xymc");
				}else{
					clin = "��Уס����Ϣ";
				}
			}
		}
		
		ex.printToOneCell_multy(ws," "+clin, 0, 0,13, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		WritableCellFormat wcf1 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf1 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.NONE);
		ws.mergeCells(0,2,6,2);
		ws.addCell(new Label(0,2,model.getXn()+"ѧ��"+" "+CommonQueryDAO.getXqMc(model.getXq())+" ѧ��",wcf1));
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
