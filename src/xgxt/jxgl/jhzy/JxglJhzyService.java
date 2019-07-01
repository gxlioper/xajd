package xgxt.jxgl.jhzy;

import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.ExcelMB;
import xgxt.base.DealString;
import xgxt.utils.ExcelMethods;

public class JxglJhzyService {
	JxglJhzyDAO dao = new JxglJhzyDAO();
	
	/**
	 * @describe �õ��ѽ����б�List
	 * @author luo
	 */
	public List<HashMap<String, String>> getLdList(String jxnd) {
		return dao.getLdList(jxnd);
	}

	/**
	 * @describe ���ְ���б�
	 * @author luo
	 */
	public List<HashMap<String, String>> getLdZwList() {
		return dao.getLdZwList();
	}

	/**
	 * @describe ����꼶�б�
	 * @author luo
	 */
	public List<HashMap<String, String>> getNjList() {
		return dao.getNjList();
	}

	/**
	 * @describe ��֯�쵼����
	 * 
	 * @throws SQLException
	 */
	public boolean addZzld(ZzldModel model, HttpServletRequest request)
			throws SQLException {
		return dao.addZzld(model, request);
	}

	/**
	 * ��֯�쵼��ѯ
	 */
	public ArrayList<String[]> getZzldList(ZzldModel model) {
		return dao.getZzldList(model);
	}

	/**
	 * @throws Exception
	 * @describe ��֯�쵼ɾ��
	 */
	public boolean delZzld(String pk, HttpServletRequest request)
			throws Exception {
		return dao.delZzld(pk, request);
	}
	/**
	 * �����֯�쵼��ϸ��Ϣ
	 */
	public HashMap<String, String> getZzld(String pk) {
		return dao.getZzld(pk);
	}
	/**
	 * ��֯�쵼��ѯ��ͷ��ѯ��ͷ
	 */
	public ArrayList<HashMap<String, String>> getZzTitle() {
		String[] colListCN = new String[] { "����", "�꼶", "�Ŷ�����", "��֯����", "��Ա����" };
		return dao.dao_getSearchTitle(colListCN);
	}
	
	/**
	 * ��֯�쵼�����ӡ
	 */
	public void printZzldList(ZzldModel model, OutputStream os)
			throws Exception {

		String nj = DealString.toGBK(model.getNj());
		String bzdm = DealString.toGBK(model.getLddm());
		String zzmc = DealString.toGBK(model.getZzmc());

		String title =  "��֯�쵼�����";

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		// �������
		List<HashMap<String, String>> list =dao.getPrintZzldList(nj, bzdm, zzmc);
		
		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 6, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 20, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		ws.addCell(new Label(0, 2, "�꼶", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(1, 2, "��������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(2, 2, "��֯����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(3, 2, "ְ������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(4, 2, "����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(5, 2, "�Ա�", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(6, 2, "��ϵ�绰", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ws.addCell(new Label(0, 3+i, list.get(i).get("nj"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(1, 3+i, list.get(i).get("bzdm"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(2, 3+i, list.get(i).get("zzmc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(3, 3+i, list.get(i).get("zwmc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(4, 3+i, list.get(i).get("cyxm"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(5, 3+i, list.get(i).get("cyxb"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(6, 3+i, list.get(i).get("lxdh"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			}
			
			//excel�ϲ���Ԫ��
			int m = 1;
			int n = 1;
			int o = 1;
			
			boolean a = false;
			boolean b = false;
			boolean c = false;
			
			for (int i = 0; i <= list.size(); i++) {
				String a3 = "";
				String a4 = "";
				String b3 = "";
				String b4 = "";
				String c3 = "";
				String c4 = "";

				WritableCell a1 = ws.getWritableCell(0, 3 + i);
				WritableCell b1 = ws.getWritableCell(1, 3 + i);
				WritableCell c1 = ws.getWritableCell(2, 3 + i);
				if (i > 0) {
					WritableCell a2 = ws.getWritableCell(0, 3 + i - 1);
					WritableCell b2 = ws.getWritableCell(1, 3 + i - 1);
					WritableCell c2 = ws.getWritableCell(2, 3 + i - 1);
					a4 = a2.getContents();
					b4 = b2.getContents();
					c4 = c2.getContents();
				}
				a3 = a1.getContents();
				b3 = b1.getContents();
				c3 = c1.getContents();
				
				if (a3.equals(a4)) {
					m++;
					a = true;
				}
				if (b3.equals(b4)) {
					n++;
					b = true;
					if (c3.equals(c4)) {
						o++;
						c = true;
					}
				}else{
					c = true;
				}
				if ((!a3.equals(a4)) && a) {
					ws.mergeCells(0, 3 + i - m, 0, 3 + i - 1);
					m = 1;
					a=false;
				}
				
				if ((!b3.equals(b4)) && b) {
					ws.mergeCells(1, 3 + i - n, 1, 3 + i - 1);
					ws.addCell(new Label(1, 3 + i - n, list.get(i - 1).get(
							"bzmc"), wcf2));
					n = 1;
					b = false;
					
					ws.mergeCells(2, 3 + i - o, 2, 3 + i - 1);
					o = 1;
					c = false;
				}else{
				if ((!c3.equals(c4)) && c) {
						ws.mergeCells(2, 3 + i - o, 2, 3 + i - 1);
						o = 1;
						c = false;
					}
				}
			}
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��ȡʱ�䷶Χ�����Ϣ
	 */
	public HashMap<String,String> serv_getSjfwInfo(String pkValue){
		String querry = " where xn||xq||nd='"+pkValue+"' ";
		return dao.dao_getInfo("jxgl_ztsjfwb", querry);
	}

	/**
	 * ��ѵʱ�䷶Χ���ñ���
	 * @throws Exception 
	 */
	public boolean serv_jxztrcszSave(JsSjSzFwModel model) throws Exception{
		return dao.jxztrcszSave(model);
	}
	 /** ��ȡѧ������
	 * 
	 * @param xqdm
	 * @return
	 * @throws SQLException
	 */
	public String getXqmc(String xqdm) throws SQLException {
		return dao.getXqmc(xqdm);
	}
	/**
	 * ���尲�ű���
	 */
	public boolean serv_jxrcZtAddSave(ZtApModel model, HttpServletRequest request) throws SQLException{
		return dao.jxrcZtAddSave(model, request);
	}
	/**
	 * ���尲�Ų�ѯ��ͷ
	 */
	public ArrayList<HashMap<String, String>> getjxrcZtTitle() {
		String[] colListCN = new String[] { "����","ѧ��", "ѧ��", "���","��ѵʱ�䷶Χ" };
		return dao.dao_getSearchTitle(colListCN);
	}
	/**
	 * ���尲�Ų�ѯ��ͷ
	 */
	public ArrayList<String[]> serv_JxrcZtSearch(ZtApModel model) {	
		return dao.JxrcZtSearch(model);
	}
	/**
	 * ��ȡ���尲�������Ϣ
	 */
	public HashMap<String,String> serv_getZtInfo(String id){
		String querry = " where xn||xq||nd='"+id+"' ";
		return dao.dao_getInfo("jxgl_ztrcb", querry);
	}
	/**
	 * ���尲���ճ���Ϣɾ��
	 */
	public boolean serv_jxrcZtDel(String delPk) throws Exception{
		return dao.dao_jxrcZtDel(delPk);
	}
	/**
	 * ���尲�ű���
	 */
	public boolean serv_jxrcJtAddSave(JtApModel model, HttpServletRequest request) throws SQLException{
		return dao.jxrcJtAddSave(model, request);
	}
	/**
	 * ���尲�Ų�ѯ��ͷ
	 */
	public ArrayList<HashMap<String, String>> getjxrcJtTitle() {
		String[] colListCN = new String[] { "����","Ժϵ","ѧ��", "ѧ��","���","��ѵʱ�䷶Χ" };
		return dao.dao_getSearchTitle(colListCN);
	}
	/**
	 * ���尲�Ų�ѯ��ͷ
	 */
	public ArrayList<String[]> serv_JxrcJtSearch(JtApModel model) {	
		return dao.JxrcJtSearch(model);
	}
	/**
	 * ���尲���е������尲���ճ���Ϣ
	 */
	public List<HashMap<String, String>> serv_getZtApInfo(String pk) {
		return dao.dao_getZtApInfo(pk);
	}
	/**
	 * ��ȡ���尲�������Ϣ
	 */
	public HashMap<String,String> serv_getJtInfo(String id){
		String querry = " where xydm||xn||xq||nd='"+id+"' ";
		return dao.dao_getInfo("jxgl_jtrcb", querry);
	}
	/**
	 * ���尲���ճ���Ϣɾ��
	 */
	public boolean serv_jxrcJtDel(String delPk) throws Exception{
		return dao.dao_jxrcJtDel(delPk);
	}
	/**
	 * У�ճ̱������
	 * @throws Exception 
	 */
	public void excuteXrcbPrint(String xn,String xq,String nd,OutputStream os) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("У��ѵ�ճ̰��ű�", 0);		
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setWrap(true);
		ws.mergeCells(0, 0, 10, 1);
		mb.printToOneCell_multy(ws,nd+"��ȣ�У��ѵ�ճ̰��ű�" , 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 1000, Border.NONE);		
		mb.printToOneCell_multy(ws, "����", 0, 2, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.ALL);
		ws.mergeCells(1, 2, 2, 2);
		mb.printToOneCell_multy(ws, "ʱ��" , 1, 2, 10,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.ALL);
		ws.mergeCells(3, 2, 5, 2);
		mb.printToOneCell_multy(ws, "����", 3, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(6, 2, 8, 2);
		mb.printToOneCell_multy(ws, "�ص�", 6, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(9, 2, 10, 2);
		mb.printToOneCell_multy(ws, "��֯��", 9, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		List<HashMap<String,String>> list = dao.xxrcbPrint(xn, xq, nd);
		int tem = 0;
		for(int i=0;i<list.size();i++){	
			int num = Integer.parseInt(list.get(i).get("cout"));
			if((tem!=num)||i==0){				
				tem = num;
				ws.mergeCells(0, 3+i, 0, 3+i+tem-1);				
			}	
			ws.addCell(new Label(0, 3+i, list.get(i).get("rq"),wcf));
			ws.mergeCells(1, 3+i, 2, 3+i);
			ws.addCell(new Label(1, 3+i, list.get(i).get("sj"),wcf));
			ws.mergeCells(3, 3+i, 5, 3+i);
			ws.addCell(new Label(3, 3+i, list.get(i).get("nr"),wcf));
			ws.mergeCells(6, 3+i, 8, 3+i);
			ws.addCell(new Label(6, 3+i, list.get(i).get("dd"),wcf));
			ws.mergeCells(9, 3+i, 10, 3+i);
			ws.addCell(new Label(9, 3+i, list.get(i).get("zzry"),wcf));	
		}

		wwb.write();
		wwb.close();
	}
	/**
	 * Ժϵ�ճ̱������
	 * @throws Exception 
	 */
	public void excuteYxrcbPrint(String xydm,String xn,String xq,String nd,OutputStream os) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("Ժϵ��ѵ�ճ̰��ű�", 0);		
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf.setWrap(true);		
		ws.mergeCells(0, 0, 10, 1);
		mb.printToOneCell_multy(ws,nd+"��ȣ�"+dao.getXymc(xydm)+"��ѵ�ճ̰��ű�" , 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 1000, Border.NONE);		
		mb.printToOneCell_multy(ws, "����", 0, 2, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.ALL);
		ws.mergeCells(1, 2, 2, 2);
		mb.printToOneCell_multy(ws, "ʱ��" , 1, 2, 10,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 300,
				Border.ALL);
		ws.mergeCells(3, 2, 5, 2);
		mb.printToOneCell_multy(ws, "����", 3, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 300, Border.ALL);
		ws.mergeCells(6, 2, 8, 2);
		mb.printToOneCell_multy(ws, "�ص�", 6, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 300, Border.ALL);
		ws.mergeCells(9, 2, 10, 2);
		mb.printToOneCell_multy(ws, "��֯��", 9, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 300, Border.ALL);
		List<HashMap<String,String>> list = dao.yxcbPrint(xydm,xn, xq, nd);
		int tem = 0;
		for(int i=0;i<list.size();i++){	
			int num = Integer.parseInt(list.get(i).get("cout"));
			if((tem!=num)||i==0){				
				tem = num;
				ws.mergeCells(0, 3+i, 0, 3+i+tem-1);				
			}	
			ws.addCell(new Label(0, 3+i, list.get(i).get("rq"),wcf));
			ws.mergeCells(1, 3+i, 2, 3+i);
			ws.addCell(new Label(1, 3+i, list.get(i).get("sj"),wcf));
			ws.mergeCells(3, 3+i, 5, 3+i);
			ws.addCell(new Label(3, 3+i, list.get(i).get("nr"),wcf));
			ws.mergeCells(6, 3+i, 8, 3+i);
			ws.addCell(new Label(6, 3+i, list.get(i).get("dd"),wcf));
			ws.mergeCells(9, 3+i, 10, 3+i);
			ws.addCell(new Label(9, 3+i, list.get(i).get("zzry"),wcf));	
		}

		wwb.write();
		wwb.close();
	}
}
