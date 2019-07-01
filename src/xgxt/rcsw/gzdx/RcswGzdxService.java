package xgxt.rcsw.gzdx;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.pjpy.zjcm.ZjcmPjpyModel;
import xgxt.rcsw.RcswService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.date.MoneyFormat;

public class RcswGzdxService extends RcswService {

	RcswGzdxDAO dao = new RcswGzdxDAO();

	// ����ά�������ۼ�������sql��������ݶ�26������
	private String[] arrTable = new String[] { "a", "b", "c", "d", "e", "f",
			"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
			"t", "u", "v", "w", "x", "y", "z" };
	
	/**
	 * ��ø��Ի���ͷ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {
		DAO dao = DAO.getInstance();
		String[] colListCN = null;
		String[] colListEN = null;

		if ("hftj".equalsIgnoreCase(lx)) {
			
			String[] gdCNList = new String[] { "ְ����", "����", "��������", "ְ������" };
			String[] gdENList = new String[] { "zgh", "xm", "bmmc", "zwmc" };
			
			// ������۵ȼ��б�
			List<HashMap<String, String>> list = dao.getWhList("rcsw_lypjb",
					"dm", "mc", "", "", "");
			
			colListCN = new String[list.size() + 4];
			colListEN = new String[list.size() + 4];
			
			for (int i = 0; i < gdCNList.length; i++) {
				colListCN[i] = gdCNList[i];
				colListEN[i] = gdENList[i];
			}
			
			for (int i = 1; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				String pjdm = map.get("dm");//���۴���
				String pjmc = map.get("mc");//��������
				//ά��ֵ
				colListEN[3 + i] = pjdm;
				colListCN[3 + i] = pjmc+"��";
			}
			colListEN[3 + list.size()] = "num";
			colListCN[3 + list.size()] = "�ش���";
		}
		return dao.arrayToList(colListEN, colListCN);
	}
	
	/**
	 * ������Իظ��б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLyhfList(RcswGzdxModel model) {
		return dao.getLyhfList(model);
	}

	/**
	 * �޸Ļظ�����
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 * @throws Exception
	 */
	public Boolean editHfnr(RcswGzdxModel model, HttpServletRequest request)
			throws Exception {

		String bjlyxx = model.getBjlyxx();

		String realTable = "rcsw_lyhfb";
		String pk = "id";
		String pkValue = model.getId();
		String[] colList = new String[] { "id", "hfsj", "hfnr" };

		HashMap<String, String> map = dao.getRcswInfo(realTable, pk, pkValue,
				colList);
		String id = map.get("id");
		String hfsj = map.get("hfsj");
		String hfnr = map.get("hfnr");

		model = new RcswGzdxModel();
		model.setId(id);
		model.setHfsj(hfsj);
		model.setHfnr(hfnr);

		realTable = "rcsw_hflsb";
		pk = "id";
		pkValue = model.getId();
		// �����ֶ�
		String[] onezd = new String[] { "id", "hfsj", "hfnr" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });

		boolean flag = saveRcsw(saveForm, model, request);

		if (flag) {

			model = new RcswGzdxModel();
			model.setId(id);
			model.setHfnr(bjlyxx);

			flag = dao.updateHfnr(model);
		}

		return flag;

	}

	/**
	 * ���ѧ�������б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getXslyInfoList(RcswGzdxModel model,
			String userType) {
		return dao.getXslyInfoList(model, userType);
	}

	/**
	 * �����ö���Ϣ
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveZdInfo(String pkValue, boolean flag) throws Exception {
		return dao.saveZdInfo(pkValue, flag);
	}

	/**
	 * ɾ��ѧ��������Ϣ
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delXslyInfo(String[] pkValue) throws Exception {
		return dao.delXslyInfo(pkValue);
	}

	/**
	 * �����ʦ�ظ������б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getHflyInfoList(RcswGzdxModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();
		
		Pages pages = model.getPages();
		
		String[] queryList = new String[] { "bmdm", "zw" };

		String[] queryLikeList = new String[] { "zgh", "xm" };

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();
		
		// ������۵ȼ��б�
		List<HashMap<String, String>> list = dao.getWhList("rcsw_lypjb", "dm",
				"mc", "", "", "");
		
		StringBuffer sql = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		
		String[] colList = null;
		String[] valueList = myQuery.getInputList();
		// ͳ�ƹ̶�ֵ
		String[] gdList = new String[] { "zgh", "xm", "bmmc", "zwmc" };
		if (list != null && list.size() > 0) {

			colList = new String[list.size() + 4];
			for (int i = 0; i < gdList.length; i++) {
				colList[i] = gdList[i];
			}

			sb.append("select * from (select t.* ,rownum r from ( ");
			sql.append("select t.*");
			for (int i = 1; i < list.size(); i++) {
				sql.append(",case when t.num <> 0 and t." + arrTable[i]);
				sql.append(" <> 0 then round(t." + arrTable[i]);
				sql.append("/t.num*100,2)||'%' else '0' end " + arrTable[i]);
				sql.append("bfb ");
			}
			sql.append(" from (");
			sql.append("select a.zgh,a.xm,a.bmdm,a.bmmc,a.zw,a.zwmc,");

			for (int i = 1; i < list.size(); i++) {
				sql.append("sum(" + arrTable[i] + ") " + arrTable[i] + ",");
				// ά��ֵ
				colList[3 + i] = arrTable[i]+"bfb";
			}
			// �ش���
			colList[3 + list.size()] = "num";
			
			sql.append("count(a.pk) num from (select a.zgh, a.xm, a.bmdm, a.bmmc, a.zw, a.zwmc,");
			
			for (int i = 1; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				String pjdm = map.get("dm");//���۴���
				String pjmc = map.get("mc");//��������
				sql.append("case when hfpj = '"+pjdm+"' then 1 else 0 end "+arrTable[i]+",");
			}
			sql.append("a.pk from (select a.zgh,a.xm,a.bmdm,a.bmmc,a.zw,a.zwmc,b.pk,b.hfpj ");
			sql.append("from view_fdyxx a left join (select hfr, pk, hfpj from  ");
			sql.append("view_rcsw_lyhf b) b on a.zgh = b.hfr order by zgh) a) a ");
			sql.append("group by a.zgh, a.xm, a.bmdm, a.bmmc, a.zw, a.zwmc");
			sql.append(") t ");
		}
		sb.append(sql);
		sb.append(") t ");
		sb.append(query);
		sb.append(") where r > "); 
		sb.append(pages.getStart());
		sb.append(" and r <= ");
		sb.append((pages.getStart() + pages.getPageSize()));
		
		System.out.println(model.getZw()+sb.toString());
		ArrayList<String[]> rsList = dao.rsToVator(sb.toString(), valueList,
				colList);
		
		sb = new StringBuffer();
		sb.append("select count(*) count from (");
		sb.append(sql);	
		sb.append(query);
		sb.append(")");
		
		String count = dao.getOneRs(sb.toString(), valueList, "count");
		if(!Base.isNull(count)){
			pages.setMaxRecord(Integer.parseInt(count));
		}
		
		return rsList;
		
	}
	
	/**
	 * ��ӡͳ�Ʊ���
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void printTjbb(RcswGzdxModel model, OutputStream os)
			throws Exception {

		DAO dao = DAO.getInstance();

		// ���ñ���
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("ͳ�Ʊ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);	

		// ����ͷ
		String[] colListCN = null;
		String[] colListEN = null;

		String[] gdCNList = new String[] { "ְ����", "����", "��������", "ְ������" };
		String[] gdENList = new String[] { "zgh", "xm", "bmmc", "zwmc" };

		// ������۵ȼ��б�
		List<HashMap<String, String>> list = dao.getWhList("rcsw_lypjb", "dm",
				"mc", "", "", "");

		colListCN = new String[list.size() + 4];
		colListEN = new String[list.size() + 4];

		for (int i = 0; i < gdCNList.length; i++) {
			colListCN[i] = gdCNList[i];
			colListEN[i] = gdENList[i];
		}

		for (int i = 1; i < list.size(); i++) {
			HashMap<String, String> map = list.get(i);
			String pjdm = map.get("dm");// ���۴���
			String pjmc = map.get("mc");// ��������
			// ά��ֵ
			colListEN[3 + i] = pjdm;
			colListCN[3 + i] = pjmc + "��";
		}
		colListEN[3 + list.size()] = "num";
		colListCN[3 + list.size()] = "�ش���";

		for (int i = 0; i < colListCN.length; i++) {
			ws.addCell(new Label(i, 0, colListCN[i], wcf2));
		}

		// ���ͳ�Ʊ���
		
		String[] queryList = new String[] { "bmdm", "zw" };

		String[] queryLikeList = new String[] { "zgh", "xm" };

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String sql = getTjSql(list);
		
		String query = myQuery.getQueryString();

		String[] inPutList = myQuery.getInputList();
		
		String[] colList = null;
		
		// ͳ�ƹ̶�ֵ
		String[] gdList = new String[] { "zgh", "xm", "bmmc", "zwmc" };
		
		if (list != null && list.size() > 0) {

			colList = new String[list.size() + 4];
			for (int i = 0; i < gdList.length; i++) {
				colList[i] = gdList[i];
			}
			
			for (int i = 1; i < list.size(); i++) {
				// ά��ֵ
				colList[3 + i] = arrTable[i]+"bfb";
			}
			// �ش���
			colList[3 + list.size()] = "num";
		}
		// ��ѧ����ͳ���б�
		ArrayList<String[]> tjList = CommonQueryDAO.commonQueryNotFy(sql, query, inPutList, colList, model);

		if (tjList != null && tjList.size() > 0) {
			for (int i = 0; i < tjList.size(); i++) {
				
				String[] tjInfo = tjList.get(i);
				
				ws.addCell(new Label(0, 1 + i, tjInfo[0], wcf2));// ְ����
				ws.addCell(new Label(1, 1 + i, tjInfo[1], wcf2));// ����
				ws.addCell(new Label(2, 1 + i, tjInfo[2], wcf2));// ����
				ws.addCell(new Label(3, 1 + i, tjInfo[3], wcf2));// ְ��
				
				// �Զ������۵ȼ�
				int size = list.size() - 1;
				for (int j = 0; j < size; j++) {
					ws.addCell(new Label(4 + j, 1 + i, tjInfo[4 + j], wcf2));// ������
				}
				
				ws.addCell(new Label(4 + size, 1 + i, tjInfo[4 + size], wcf2));// �ش���
				
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��ûظ�����ͳ��table
	 * 
	 * @author luo
	 */
	public String getTjSql(List<HashMap<String, String>> list){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select t.*");
		for (int i = 1; i < list.size(); i++) {
			sql.append(",case when t.num <> 0 and t." + arrTable[i]);
			sql.append(" <> 0 then round(t." + arrTable[i]);
			sql.append("/t.num*100,2)||'%' else '0' end " + arrTable[i]);
			sql.append("bfb ");
		}
		sql.append(" from (");
		sql.append("select a.zgh,a.xm,a.bmdm,a.bmmc,a.zw,a.zwmc,");

		for (int i = 1; i < list.size(); i++) {
			sql.append("sum(" + arrTable[i] + ") " + arrTable[i] + ",");

		}
		
		sql.append("count(a.pk) num from (select a.zgh, a.xm, a.bmdm, a.bmmc, a.zw, a.zwmc,");
		
		for (int i = 1; i < list.size(); i++) {
			HashMap<String, String> map = list.get(i);
			String pjdm = map.get("dm");//���۴���
			String pjmc = map.get("mc");//��������
			sql.append("case when hfpj = '"+pjdm+"' then 1 else 0 end "+arrTable[i]+",");
		}
		sql.append("a.pk from (select a.zgh,a.xm,a.bmdm,a.bmmc,a.zw,a.zwmc,b.pk,b.hfpj ");
		sql.append("from view_fdyxx a left join (select hfr, pk, hfpj from  ");
		sql.append("view_rcsw_lyhf b) b on a.zgh = b.hfr order by zgh) a) a ");
		sql.append("group by a.zgh, a.xm, a.bmdm, a.bmmc, a.zw, a.zwmc");
		sql.append(") t ");
	
		return sql.toString();
	}
	

	/**
	 * ��ûظ�¥��
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public int getHfls(RcswGzdxModel model){
		return dao.getHfls(model);
	}
	
	/**
	 * �ж��Ƿ�ѡ����ѯʦ
	 */
	public String  getXzZxs(String dm){
		HashMap<String,String>hashMap=dao.getXzZxs(dm);
		String xzzxs=hashMap.get("xzzxs");
		if("".equalsIgnoreCase(xzzxs) || xzzxs==null){
			return "no";
		}
		return xzzxs;
	}
}
