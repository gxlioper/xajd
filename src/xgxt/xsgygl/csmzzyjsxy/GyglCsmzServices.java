package xgxt.xsgygl.csmzzyjsxy;

import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.csmz.xsDormDSCHModel;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��ɳ����ѧԺ��Ԣ����service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-11</p>
 */
public class GyglCsmzServices {
   /**
    * ���渨��Ա�������Ϣ
    * @param model
    * @return
 * @throws Exception 
    */
   GyglCsmzDAO gcDAO = null;
   public boolean saveFdytbxx(GyglCsmzModel model) throws Exception{
	   gcDAO = new GyglCsmzDAO();	   
	   return gcDAO.saveFdyXqInfo(model);
   }
   public List<HashMap<String,String>> fkxxcx(String userType,String userName){
	   List<HashMap<String,String>> csxxList;
	   gcDAO = new GyglCsmzDAO();	
	   if(userType.equalsIgnoreCase("student")){//ѧ���û�ִ�в�ѯ���
		   csxxList = gcDAO.stu_fdyxqxxCx(userName);
	   }else{//��ѧ���û���ѯ���
		   csxxList = null;
	   }
	   return csxxList;
   }
   public HashMap<String,String> stu_fdyXqXxView(String pkValue){
	   HashMap<String,String> xxTem = null;
	   gcDAO = new GyglCsmzDAO();	
	   xxTem = gcDAO.stu_fdyXqXxQer(pkValue);//�ݹؼ�ֵ��ѯ����Ա������Ϣ
	   return xxTem;
   }
   /**����ѧ����д������Ϣ*/
   public boolean saveStu_fkxx(String pkValue,GyglCsmzModel model) throws Exception{
	   gcDAO = new GyglCsmzDAO();	
	   return gcDAO.saveStu_fkxx(pkValue, model);
   }
   public  ArrayList<HashMap<String, String>> getFdyXqSearchTitle(){
	    ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
	    gcDAO = new GyglCsmzDAO();
	    //��ѯ��ͷ
		result = gcDAO.getFXSTitle();
		return result;
   }
   /**
	 * @param pjpyModel ����ȷ�����������model
	 * @return
	 */
	public ArrayList<String[]>  getFdyXqSearchResult(GyglCsmzFdyXqCxModel gcModel){
		gcDAO = new GyglCsmzDAO();
		ArrayList<String[]> result = new ArrayList<String[]>();		
		//ѧԺ�û���ѯ���
		result = gcDAO.getFXSResult(gcModel);		
		return result;
	}
	/**���ظ���Ա������ϢMap*/
	public HashMap<String,String>getFdyXqXxMap(String pkValue){
		HashMap<String,String> map = new HashMap<String,String>();
		gcDAO = new GyglCsmzDAO();	
		map = gcDAO.getFdyXqxx(pkValue);
		return map;
	}
	/**ɾ������Ա������Ϣ
	 * @throws Exception */
	public boolean delFdyXqXx(String pkValue) throws Exception{		
		gcDAO = new GyglCsmzDAO();	
		return gcDAO.delFdyXqXx(pkValue);
	}
	
	public boolean xsDorm_DistributeSave(String newMappingItems,String oldMappingItems) throws Exception{
		gcDAO = new GyglCsmzDAO();	
		return gcDAO.dormDistSave(newMappingItems,oldMappingItems);
	}
	public boolean xsBed_DistributeSave(String newMappingItems,String oldMappingItems) throws Exception{
		gcDAO = new GyglCsmzDAO();	
		return gcDAO.bedDistSave(newMappingItems,oldMappingItems);
		
	}
	public List<HashMap<String, String>> getXsDormSearchTitle(){
		gcDAO = new GyglCsmzDAO();	
		return gcDAO.getXsDSearchTit();
	}
	public List<String[]> getXsDormSearchResult(String userName, String userType, xsDormDSCHModel myModel) throws Exception {
		gcDAO = new GyglCsmzDAO();	
		return gcDAO.getXsDSearchRes(myModel);		
	}	
	public  HashMap<String,String> getxsXX(String ksh){
		gcDAO = new GyglCsmzDAO();
		return gcDAO.getxsXX(ksh);
	}
	public boolean xsZSADSave(String ksh,String ssbh,String cwh,String rzrq) throws Exception{
		gcDAO = new GyglCsmzDAO();
		return gcDAO.dao_XsZSADSave(ksh,ssbh,cwh,rzrq);
	}
	public boolean service_xsZsxxPlDel(HttpServletRequest request) throws Exception{
		gcDAO = new GyglCsmzDAO();
		return gcDAO.dao_xsZsxxPlDel(request);
	}
	public String[] serv_getXsSsInfo(String ksh){
		gcDAO = new GyglCsmzDAO();
		return gcDAO.dao_getXsSsInfo(ksh);
	}
	public String service_xhKshSynchro(String pkValue) throws SQLException{
		gcDAO = new GyglCsmzDAO();
		return gcDAO.dao_xhKshSynchro(pkValue);
	}
	public List serviec_hFDeTailView(){
		gcDAO = new GyglCsmzDAO();
		return gcDAO.dao_hFDeTailView();
	}
	
	/**
	 * ���¥����ס��Ϣ��ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTableTitle() throws Exception {
		gcDAO = new GyglCsmzDAO();
		return gcDAO.getTableTitle();
	}
	
	/**
	 * ����������ס��ϸ��Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSxRzqk(String pkValue) throws Exception {
		gcDAO = new GyglCsmzDAO();
		return gcDAO.getSxRzqk(pkValue);
	}
	
	public List<HashMap<String, String>> getSxRxqkTitle() throws Exception {
		gcDAO = new GyglCsmzDAO();
		return gcDAO.getSxRxqkTitle();
	}
	@SuppressWarnings("unchecked")
	public List<HashMap<String,String>> service_FpDeTailView(String userDep,String nj,String xydm,String zydm,String bjdm){
		gcDAO = new GyglCsmzDAO();
		return gcDAO.dao_FpDeTailView(userDep,nj,xydm,zydm,bjdm);
	}
	public void serv_wxsyzy_xsrzqk_Tj(WritableWorkbook wwb,GyglCsmzModel model)throws Exception {
		gcDAO = new GyglCsmzDAO();
		String nj = model.getNj();
		String zydm =model.getZydm();
		String xydm = model.getXydm();
		String bjdm = model.getBjdm();
		String clin = "";
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
		WritableSheet ws = wwb.createSheet(clin, 0);
		ArrayList<String[]> vec = new ArrayList<String[]>();
		String[] ColumnName = new String[]{"xh","xm","xb","xymc","nj","bjmc","sfzh","ldmc","qsh","rzrq","jzrq","sfbz","xqdm","qsdh","bz"};
		String[] ColumnNameCN  =new String[] { "ѧ��","����", "�Ա�", "Ժϵ����","�꼶","�༶����","���֤��", "����¥��","���Һ�","��ס����","�˷�����","ס�޷�","У������","���ҵ绰","��ע"};
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 14, 1);
		ex.printToOneCell_multy(ws, Base.xxmc+"ѧ��ס�����ͳ�Ʊ�", 0, 0,20, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		
		WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		for (int m = 0; m < ColumnNameCN.length; m++) {
			ws.addCell(new Label(m, 2, ColumnNameCN[m],wcf));
		}
		vec = gcDAO.dao_wxsyzy_xsrzqk_Tj(model);
		int k = ColumnName.length;
		for (int i = 0; i < vec.size(); i++) {
			String[] tmp = (String[]) vec.toArray()[i];
			for (int j = 0; j < k; j++) {
				ws.addCell(new Label(j, i + 3, tmp[j],wcf));
			}
		}
	}
	
	/**
	 * ��ҳ
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getResultList(List<String[]> list,
			GyglCsmzActionForm model) {

		// ��ҳ
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		if (list != null && list.size() > 0) {

			Pages pages = model.getPages();
			pages.setMaxRecord(list.size());
			int start = pages.getStart();
			int size = pages.getPageSize();

			for (int i = start; i < start + size; i++) {
				if (i < list.size()) {
					rsList.add(list.get(i));
				}
			}
		}

		return rsList;
	}
	
	
	
	
	
	protected void printRzqk(GyglCsmzActionForm model,OutputStream os) {
		
		GyglCsmzDAO dao = new GyglCsmzDAO();
		
		String title = "ѧ����ס���ͳ��";
		List<String[]> data = dao.rzqktj(model);
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("ѧ����ס���ͳ��", 0);
		
		try {
			excel.printTitle(ws, title, 9, 800);// ����
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			ws.addCell(new Label(0, 1, "Ժϵ", wcfTytle));
			ws.addCell(new Label(1, 1, "�꼶", wcfTytle));
			ws.addCell(new Label(2, 1, "רҵ", wcfTytle));
			ws.addCell(new Label(3, 1, "�༶", wcfTytle));
			ws.addCell(new Label(4, 1, "�ձ�", wcfTytle));
			ws.addCell(new Label(5, 1, "������", wcfTytle));
			ws.addCell(new Label(6, 1, "��ס����", wcfTytle));
			ws.addCell(new Label(7, 1, "δ��ס����", wcfTytle));
			ws.addCell(new Label(8, 1, "δ��ס����", wcfTytle));
			ws.addCell(new Label(9, 1, "��Ů����", wcfTytle));
			
			int row = 2;
			int count = 0 ;
			int zrs = 0 ;
			int rzrs = 0;
			boolean flag = true;
			
			for (int i = 0 ; i < data.size() ; i++) {
				
				zrs += Integer.parseInt(data.get(i)[5]);
				rzrs += Integer.parseInt(data.get(i)[6]);
				
				for (int j = 0 ; j < data.get(i).length ; j++) {
					ws.addCell(new Label(j, i+row, data.get(i)[j], wcfTytle));
				}
				
				if (i<data.size()-1 && !data.get(i)[4].equals(data.get(i+1)[4]) && data.get(i)[3].equals(data.get(i+1)[3]) && flag) {
					flag = false;
					ws.mergeCells(9, row + i, 9, row + i+1);
					ws.addCell(new Label(9,row + i, String.valueOf(Integer.parseInt(data.get(i)[5])+Integer.parseInt(data.get(i+1)[5])), wcfTytle));
				} else {
					ws.addCell(new Label(9,row + i, data.get(i)[5], wcfTytle));
					flag = true;
				}
				
				
				if ((i<data.size()-1 
						&& !data.get(i)[0].equals(data.get(i+1)[0])) || i==data.size()-1) {
					
					ws.mergeCells(0, row + i+1, 4, row + i+1);
					ws.addCell(new Label(0,row + i+1, "�ϼ�", wcfTytle));
					ws.addCell(new Label(5,row + i+1, String.valueOf(zrs), wcfTytle));
					ws.addCell(new Label(6,row + i+1, String.valueOf(rzrs), wcfTytle));
					ws.addCell(new Label(7,row + i+1, String.valueOf(zrs-rzrs), wcfTytle));
					ws.addCell(new Label(8,row + i+1, String.valueOf(
							new java.text.DecimalFormat("##0.00").format(Float.valueOf(zrs-rzrs)/zrs*100))+"%", wcfTytle));
					ws.addCell(new Label(9,row + i+1, String.valueOf(zrs), wcfTytle));
					rzrs = 0;
					zrs=0;
					count++;
					row+=1;
				}
				
				
				
			}
			
			ExcelMB.mergeCells(ws, row*2, 0, 2);
			ExcelMB.mergeCells(ws, row*2, 1, 2);
			ExcelMB.mergeCells(ws, row*2, 2, 2);
			ExcelMB.mergeCells(ws, row*2, 3, 2);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
