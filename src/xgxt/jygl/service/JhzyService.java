package xgxt.jygl.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.jygl.dao.Dao_jhzy;
import xgxt.jygl.form.JhzyForm;
import xgxt.jygl.model.JhzyModel;
import xgxt.studentInfo.dao.StuInfoDAO;

public class JhzyService {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	Dao_jhzy dao = new Dao_jhzy();

	public HashMap<String, String> serv_jysbsjsz() {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			map = dao.dao_jysbsjsz();
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return map;
	}
	public boolean serv_savesjsz(JhzyModel model,HttpServletRequest request) {
		boolean result = false;
		try {
			result = dao.dao_savesjsz(model,request);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return result;
	}
	public HashMap<String, String> serv_querysjsz(String sqsj) {
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			result = dao.dao_querysjsz(sqsj);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return result;
	}
	public boolean serv_savejysb(JhzyModel model) {
		boolean result = false;
		try {
			result = dao.dao_savejysb(model);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return result;
	}
	public boolean serv_issavejysb(JhzyModel model) {
		boolean result = false;
		try {
			result = dao.dao_issavejysb(model);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return result;
	}
	public boolean serv_updatejysb(JhzyModel model,HttpServletRequest request) {
		boolean result = false; 
		try {
			result = dao.dao_updatejysb(model,request);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return result;
	}
	public HashMap<String, String> serv_jysbview(String pk,String sqllx) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			map = dao.dao_jysbview(pk,sqllx);
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return map;
	}
	public String serv_deljysb(String pks) {
		String result = ""; 
		try {
			result = dao.dao_jysbdel(pks);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return result;
	}
	public String serv_jysbXxsh(String pks,String xxsh,String xxshr) {
		String result = ""; 
		try {
			result = dao.dao_jysbXXsh(pks,xxsh,xxshr);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return result;
	}
	public boolean serv_delAlljysb() {
		boolean result = false; 
		try {
			result = dao.dao_jysbdelAll();
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return result;
	}
	public ArrayList<String[]> serv_jysbquery(JhzyForm myForm,JhzyModel model){
		return dao.dao_jysbquery(myForm,model);
	}
	public void serv_jysbExpdata(HttpServletResponse response,JhzyModel model){
	     try {
			dao.dao_jysbExpdata(response,model);
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
	
//	��ȡtable��ͷ
	public List<HashMap<String, String>> getTitleList(String [] colList, String realName){
		DAO dao = DAO.getInstance();
		String [] colListCN = dao.getColumnNameCN(colList,realName);
		return dao.arrayToList(colList, colListCN);
	}
	
	public ArrayList<String[]> getCphcs(String xydm, String nd) {
		return dao.getCphcs(xydm,nd);
	}
	
	public ArrayList<HashMap<String, String>> getZphcsTopTr() {
		String[] colListCN = new String[] { "����",Base.YXPZXY_KEY+"����", "���", "������Ƹ�����","������Ƹ�ᵥλ����","������Ƹ���λ����","�ٰ�С����Ƹ�����","С����Ƹ�ᵥλ����","С����Ƹ���λ����","�ٰ����˵�λ���������","�����ᵥλ����","�������λ����"};
		return dao.dao_getSearchTitle(colListCN);
	}
	
	public String ZphcsPlDel(String pks, HttpServletRequest request) throws SQLException {
		// TODO �Զ����ɷ������
		return dao.zphcsPlDel(pks,request);
	}
	
	public HashMap<String, String> getZphcsOne(String pk) {
		// TODO �Զ����ɷ������
		return dao.getZphcsOne(pk);
	}

	public boolean zphcsUpdate(String pk,JhzyModel myModel, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.zphcsUpdate(pk,myModel,request);
	}
	
	//��ҵ����Դ�أ�ʡ��
	@SuppressWarnings("unchecked")
	public List<HashMap<String,String>>getSyd_ser(){
		StuInfoDAO studao = new StuInfoDAO();
		return studao.getSsList();
	}
	
	//��ҵ����Դͳ��
	public void byssytj_ser(String userType,String xydm,String xymc,String syddm,String nd,ServletOutputStream os) throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("��ҵ����Դͳ�Ʊ�", 0);
		ExcelMB mb = new ExcelMB();
		String title = "";
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
		String tempzymc = "";
		List<String[]> sydlist = dao.getXsSyd(syddm,xydm,nd);
		ws.mergeCells(0, 0, sydlist.size()+2, 1);
		if(!Base.isNull(xydm)){
			title = "��ְҵ����ѧԺ"+nd+"��"+xymc+"��ҵ����Դͳ��";
		}else{
			title = "��ְҵ����ѧԺ"+nd+"���ҵ����Դͳ��";
		}
		mb.printToOneCell_multy(ws, title, 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 1000, Border.NONE);
		List<String[]>  list = dao.getXsSydRs(syddm, xydm,nd);
				
		mb.printToOneCell_multy(ws, "Ժϵ", 0, 2, 12, true,
				Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);
		mb.printToOneCell_multy(ws, "רҵ", 1, 2, 12,
				true, Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);
		mb.printToOneCell_multy(ws, "�ϼ�", 2, 2, 12,
				true, Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);
		for(int i=0;i<sydlist.size();i++){
			mb.printToOneCell_multy(ws,sydlist.get(i)[0], 3+i, 2, 12,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
					Border.NONE);	
		}
		String temp = "ȫУ";
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		if(!Base.isNull(xydm)){
			temp = "ȫ"+Base.YXPZXY_KEY;
		}
		mb.printToOneCell_multy(ws,temp , 0, 3, 12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);
		mb.printToOneCell_multy(ws, "�ϼ�", 1, 3, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);
		int num = 0;
		if(list.size() > 0){
			tempzymc = list.get(0)[2];
			mb.printToOneCell_multy(ws,list.get(0)[1], 0, 4+num, 12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
					Border.NONE);
			mb.printToOneCell_multy(ws,list.get(0)[2], 1, 4+num, 12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
					Border.NONE);
		}
		for(int i=0;i<list.size();i++){
			int position = Integer.parseInt(list.get(i)[4]);
			if(tempzymc != null && tempzymc.equals(list.get(i)[2])){
				mb.printToOneCell_multy(ws,list.get(i)[3], 2+position, 4+num, 12,
						false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				if(list.size() == i+1){
					mb.printToOneCell_multy(ws,list.get(i)[0], 2, 4+num, 12,
							false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
							Border.NONE);
				}	
			}else if(tempzymc != null && !tempzymc.equals(list.get(i)[2])){
				mb.printToOneCell_multy(ws,list.get(i-1)[0], 2, 4+num, 12,
						false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				tempzymc = list.get(i)[2];
				num++;
				mb.printToOneCell_multy(ws,list.get(i)[1], 0, 4+num, 12,
						false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				mb.printToOneCell_multy(ws,list.get(i)[2], 1, 4+num, 12,
						false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				mb.printToOneCell_multy(ws,list.get(i)[3], 2+position, 4+num, 12,
						false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				if(list.size() == i+1){
					mb.printToOneCell_multy(ws,list.get(i)[0], 2, 4+num, 12,
							false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
							Border.NONE);
				}
			}
		}
		List<String[]>  xssydlist = dao.getXsSydHz(syddm, xydm,nd);
		int zrs = 0;
		for(int i=0;i<xssydlist.size();i++){
			mb.printToOneCell_multy(ws,xssydlist.get(i)[0], 3+i, 3, 12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
					Border.NONE);
			zrs += Integer.parseInt(xssydlist.get(i)[0]);
		}
		mb.printToOneCell_multy(ws,String.valueOf(zrs), 2, 3, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);
		wwb.write();
		wwb.close();
	}
	/**
	 * ��ȡ�����б�
	 * @param tableName
	 * @param columns
	 * @return List
	 * */
	public List getDmList(String tableName,String[] columns){
		return dao.getDmList(tableName,columns);
	}
	public boolean serv_xmlgAdd(JhzyModel model) throws Exception{
		return dao.dao_xmlgAdd(model);
	}
	public ArrayList<String[]> serv_xmlgquery(JhzyForm myForm,JhzyModel model,String userType){
		return dao.dao_xmlgquery(myForm,model,userType);
	}
	public String serv_delxmlg(String pks) {
		String result = ""; 
		try {
			result = dao.dao_xmlgdel(pks);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return result;
	}
	public HashMap<String, String> serv_xmlgupdate(JhzyModel model) throws Exception{
		return dao.dao_xmlgupdate(model);
	}
	public HashMap<String, String> serv_getXsInfo(String xh){
		return dao.dao_getXsInfo(xh);
	}
	public boolean serv_xmlgshAdd(JhzyModel model,String userType) throws Exception{
		return dao.dao_xmlgshAdd(model,userType);
	}
}
