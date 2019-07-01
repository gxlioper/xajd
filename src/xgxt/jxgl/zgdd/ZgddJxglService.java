package xgxt.jxgl.zgdd;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;

public class ZgddJxglService {
	ZgddJxglDAO zgddDao = new ZgddJxglDAO();

	/**
	 * @author luo
	 * @describe �Ƿ������
	 */
	public boolean isGfs(String xh) throws SQLException {
		return zgddDao.isGfs(xh);
	}

	/**
	 * @author luo
	 * @describe �Ƿ������
	 */
	public boolean isGfb(String zgh) throws SQLException {
		return zgddDao.isGfb(zgh);
	}

	public ArrayList<String[]> getJxjffList(GfsModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO �Զ����ɷ������
		return zgddDao.getJxjffList(myModel,tableName);
	}

	public List<HashMap<String, String>> getJxjffTopTr(String tableName) {
		// TODO �Զ����ɷ������
		return zgddDao.getJxjffTopTr(tableName);
	}

	public HashMap<String, String> getGfsjxjglOne(String pk, String xh) {
		// TODO �Զ����ɷ������
		return zgddDao.getGfsjxjglOne(pk,xh);
	}

	public boolean GfsjxjglUpdate(String pk, GfsModel myModel, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return zgddDao.GfsjxjglUpdate(pk,myModel,request);
	}

	public boolean GfsjxjglDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return zgddDao.GfsjxjglDelete(pk,request);
	}
	
	
	/**
	 * @author luo
	 * @describe ��ñ�ͷ
	 */
	public List<HashMap<String, String>> getTopTr(String tableName,
			String[] colList) {
		DAO dao = DAO.getInstance();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		return topTr;
	}
	/**
	 * @author luo
	 * @describe ��ñ�ͷ
	 */
	public List<HashMap<String, String>> queryTitle() throws Exception {
		String[] enList = new String[]{"xh","xm","xn","xq","bjmc","zymc","zkcs","gkms","yxms","pjf","jd","cpzf"};
		String[] cnList = new String[]{"ѧ��","����", "���","רҵ","ѧ��","ѧ��", "�ܿγ���", 
				"�ҿ�����","��������","ƽ����","ѧ�ּ���","�༶�ۺϲ���"};
		return zgddDao.getTitle(enList, cnList);
	}
	/**
	 * @author luo
	 * @describe ���ȫ��ѧ����Ϣ
	 */
	public List<HashMap<String, String>> getXsList(GfsModel model,
			String[] colList) throws SQLException {
		return zgddDao.getXsList(model, colList);
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe �������������
	 */
	public boolean saveGfs(GfsModel model) throws Exception {
		return zgddDao.saveGfs(model);
	}
	
	/**
	 * ��ȡѧ�����Ϣ
	 */
	public HashMap<String, String> getStuXxForXh(String xh) {				
		return  zgddDao.dao_getInfo("view_xsxxb", " where xh= '"+xh+"'");		
	}
	/**
	 * @author luo
	 * @describe ��ù�������������б�
	 */
	public List<HashMap<String, String>> getWsList(GfsModel model,
			String[] colList) throws SQLException {
		return zgddDao.getWsList(model, colList);
	}
	
	/**
	 * @author luo
	 * @describe ��ù�����������Ϣ
	 */
	public HashMap<String, String> getWsListXx(String pk) throws SQLException {
		return zgddDao.getWsListXx(pk);
	}
	
	/**
	 * @author luo
	 * @describe ��ù��������������Ϣ
	 */
	public HashMap<String, String> getWsXx(String pk) throws SQLException {
		return zgddDao.getWsXx(pk);
	}
	/**
	 * @author luo
	 * @throws Exception 
	 * @describe �������������Ϣ
	 */
	boolean saveWs(GfsModel model, HttpServletRequest request) throws Exception {
		return zgddDao.saveWs(model, request);
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe ����ɾ��������������Ϣ
	 */
	public boolean delWs(String[] key) throws Exception {
		return zgddDao.delWs(key);
	}
	
	/**
	 * @author luo
	 * @describe �������
	 */
	public List<HashMap<String, String>> getZsList(){
		return zgddDao.getZsList();
	}
	
	/**
	 * @author luo
	 * @describe ���ѧ��
	 */
	public String getXq(String xq){
		return zgddDao.getXq(xq);
	}
	
	/**
	 * @author luo
	 * @describe ��ӡ����ͳ��
	 */
	@SuppressWarnings("unchecked")
	public void printWstj(WritableWorkbook wwb, String xn, String xq,
			String zs, String xh, String nj, String xm, String xydm,
			String zydm, String bjdm, String lddm, String wsqk,String kssj,String jssj) {

		List<HashMap<String, String>> list = zgddDao.getWsList(xn, xq, zs, xh,
				nj, xm, xydm, zydm, bjdm, lddm, wsqk,kssj, jssj);

		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, "�й����ʴ�ѧ���人����������" + zs + "���������ͳ�Ʊ�",
					wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			int rownum = 0;

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);
				String qk = map.get("wsqk");
				String nr = "��";

				ws.addCell(new Label(0, 4 + rownum, map.get("xh"), wcfTytle));
				ws.addCell(new Label(1, 4 + rownum, map.get("xm"), wcfTytle));
				ws.addCell(new Label(2, 4 + rownum, map.get("ldmc"), wcfTytle));
				ws.addCell(new Label(3, 4 + rownum, map.get("wsss"), wcfTytle));

				if ("��".equalsIgnoreCase(qk)) {
					ws.addCell(new Label(4, 4 + rownum, nr, wcfTytle));
					ws.addCell(new Label(5, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(6, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(7, 4 + rownum, "", wcfTytle));
				} else if ("��".equalsIgnoreCase(qk)) {
					ws.addCell(new Label(4, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(5, 4 + rownum, nr, wcfTytle));
					ws.addCell(new Label(6, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(7, 4 + rownum, "", wcfTytle));
				} else if ("��".equalsIgnoreCase(qk)) {
					ws.addCell(new Label(4, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(5, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(6, 4 + rownum, nr, wcfTytle));
					ws.addCell(new Label(7, 4 + rownum, "", wcfTytle));
				} else if ("��".equalsIgnoreCase(qk)) {
					ws.addCell(new Label(4, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(5, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(6, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(7, 4 + rownum, nr, wcfTytle));
				}

				ws.addCell(new Label(8, 4 + rownum, map.get("wsbz"), wcfTytle));

				rownum++;
			}
			
			System.out.println(rownum);
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.RIGHT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			ws.mergeCells(0, 4+rownum, 8, 4+rownum);
			ws.addCell(new Label(0, 4 + rownum, "���ʱ�䣺" + zgddDao.getTime(kssj)
					+ "-" + zgddDao.getTime(jssj), wcfTytle));

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * @author 
	 * @describe �ɼ�ͳ��
	 */
	public ArrayList<HashMap<String, String>> getCjtj(ZgddJxglForm myform,GfsModel model) throws SQLException {
		return zgddDao.getCjtj(myform,model);
	}
	/**
	 * ��ȡ�����б�
	 */
	public List<HashMap<String, String>> serv_getLdList(){
		return zgddDao.getLdList();
	}
	/**
	 * ��ȡ�����������б�
	 */
	public List<HashMap<String, String>> serv_getZcPfList(){
		return zgddDao.getZcPfList();
	}
	
	/**
	 *  ������Ϣɾ��
	 */
	public boolean ser_gfscqDelete(String delPk) throws Exception{
		return zgddDao.dao_gfscqDelete(delPk);
	}
	/**
	 * ��ȡѧ�������Ϣ
	 */
	public HashMap<String,String> serv_getXsInfo(String xh){
		String querry = " where xh='"+xh+"' ";
		return zgddDao.dao_getInfo("view_xsxxb", querry);
	}
	/**
	 * ��ȡѧ����س�����Ϣ
	 */
	public HashMap<String,String> serv_getXsCqInfo(String xh){
		String querry = " where xh||jcsj='"+xh+"' ";
		return zgddDao.dao_getInfo("view_zgdzdx_gfscqxx", querry);
	}
	/**
	 * ������Ϣ��ӱ���
	 */
	public boolean serv_gfscqAddSave(CqglModel model) throws Exception{
		return zgddDao.gfscqAddSave(model);
	}
	/**
	 * ������Ϣ�޸ı���
	 */
	public boolean serv_gfscqModiSave(CqglModel model,String pkValue) throws Exception{
		return zgddDao.gfscqModiSave(model,pkValue);
	}
	/**
	 * ��ȡ��ѯ��ͷ
	 */
	public ArrayList<HashMap<String, String>> dao_getSearchTitle( ) {
		return zgddDao.dao_getSearchTitle();
	}

	/**
	 * ������Ϣ��ѯ
	 */
	public ArrayList<String[]> serv_cqqkDefault(CqglModel model){
		return zgddDao.dao_cqqkDefault(model);
	}
	
	public void serv_expData(CqglModel model,WritableWorkbook wwb) throws Exception{
		WritableSheet ws = wwb.createSheet("��������ٳ���ͳ�Ʊ�", 0);
		
		WritableCellFormat wcf1 = new WritableCellFormat(); // ���쵥Ԫ���ʽ		
		wcf1 = ExcelMethods.getWcf(WritableFont.TIMES,20,true,Alignment.CENTRE,VerticalAlignment.CENTRE,true,Border.TOP);			

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ		
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,Border.ALL);			
		//������
		ws.mergeCells(0, 0, 9, 2);
		ws.setRowView(0, 650); // ����ָ���и�
		ws.addCell(new Label(0, 0,"�й����ʴ�ѧ���人����������ٳ���ͳ�Ʊ�" ,wcf1)); // �����ָ����ʽ�ĵ�Ԫ��ֵ	
		//����ͷ
		ws.mergeCells(0, 3, 0, 4);
		ws.addCell(new Label(0, 3,"ѧ��" ,wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.mergeCells(1, 3, 1, 4);
		ws.addCell(new Label(1, 3,"����" ,wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.mergeCells(2, 3, 2, 4);
		ws.addCell(new Label(2, 3,"����" ,wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.mergeCells(3, 3, 3, 4);
		ws.addCell(new Label(3, 3,"����" ,wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.mergeCells(4, 3, 7, 3);
		ws.addCell(new Label(4, 3,"������" ,wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(4, 4,"��" ,wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(5, 4,"��" ,wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(6, 4,"��" ,wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(7, 4,"��" ,wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.mergeCells(8, 3, 8, 4);
		ws.addCell(new Label(8, 3,"�������" ,wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.mergeCells(9, 3, 9, 4);
		ws.addCell(new Label(9, 3,"��ע" ,wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		//�������								
		List<HashMap<String,String>> listV = zgddDao.dao_expData(model);
		if(listV.size()==0||listV==null){
			//������
			ws.mergeCells(0, 5, 9, 5);
			ws.setRowView(0, 650); // ����ָ���и�
			ws.addCell(new Label(0, 5,"δ�ҵ��κ����ݣ�" ,wcf1)); // �����ָ����ʽ�ĵ�Ԫ��ֵ			
		}else{
			for(int i=0;i<listV.size();i++){
				ws.addCell(new Label(0,5+i ,listV.get(i).get("xh"),wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(1,5+i ,listV.get(i).get("xm"),wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(2,5+i ,listV.get(i).get("ldmc"),wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(3,5+i ,listV.get(i).get("szss"),wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				if("��".equalsIgnoreCase(listV.get(i).get("zcqk"))){
					ws.addCell(new Label(4,5+i ,"��",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
					ws.addCell(new Label(5,5+i ," ",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
					ws.addCell(new Label(6,5+i ," ",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
					ws.addCell(new Label(7,5+i ," ",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				}else if("��".equalsIgnoreCase(listV.get(i).get("zcqk"))){
					ws.addCell(new Label(4,5+i ,"",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
					ws.addCell(new Label(5,5+i ,"��",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
					ws.addCell(new Label(6,5+i ," ",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
					ws.addCell(new Label(7,5+i ," ",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				}else if("��".equalsIgnoreCase(listV.get(i).get("zcqk"))){
					ws.addCell(new Label(4,5+i ," ",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
					ws.addCell(new Label(5,5+i ," ",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
					ws.addCell(new Label(6,5+i ,"��",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
					ws.addCell(new Label(7,5+i ," ",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				}else{
					ws.addCell(new Label(4,5+i ," ",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
					ws.addCell(new Label(5,5+i ," ",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
					ws.addCell(new Label(6,5+i ," ",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
					ws.addCell(new Label(7,5+i ,"��",wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				}
				
				
				ws.addCell(new Label(8,5+i ,listV.get(i).get("cqqk"),wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(9,5+i ,listV.get(i).get("bz"),wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ				
			}
		}
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		String jcsj = "";
		if(!Base.isNull(kssj)&&!Base.isNull(jssj)){
			jcsj = kssj+"--"+jssj;
		}else if(!Base.isNull(kssj)){
			jcsj = kssj;
		}else if(!Base.isNull(jssj)){
			jcsj = jssj;
		}
		ws.mergeCells(0,listV.size()+6, 9, listV.size()+6);
		ws.addCell(new Label(0, listV.size()+6,"���ʱ�䣺"+jcsj )); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * @author 
	 * @describe �ɼ�ͳ��
	 */
	public ArrayList<HashMap<String, String>> getCjtjexp(ZgddJxglForm myform,GfsModel model) throws SQLException {
		return zgddDao.getCjtjexp(myform,model);
	}
	/**
	 * @author 
	 * @describe �ɼ�ͳ�Ʒ�ҳ
	 */
	public String getCjtjfy(ZgddJxglForm myform,GfsModel model) throws SQLException {
		return zgddDao.getCjtjfy(myform,model);
	}
	/**
	 * @author 
	 * @throws Exception 
	 * @describe ��ӡ
	 */
	public void cjPrint(WritableWorkbook wwb, ArrayList<HashMap<String, String>> list) throws Exception {

		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.RIGHT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.RIGHT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			int rownum = 0;

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) list.get(i);

				ws.addCell(new Label(0, 3 + rownum, map.get("xm"), wcfTytle));
				ws.addCell(new Label(1, 3 + rownum, map.get("xh"), wcfTytle));
				ws.addCell(new Label(2, 3 + rownum, map.get("bjmc"), wcfTytle));
				ws.addCell(new Label(3, 3 + rownum, map.get("zymc"), wcfTytle));
				ws.addCell(new Label(4, 3 + rownum, map.get("xn"), wcfTytle));
				ws.addCell(new Label(5, 3 + rownum, map.get("xq"), wcfTytle));
				ws.addCell(new Label(6, 3 + rownum, map.get("zkcs"), wcfTytle));
				ws.addCell(new Label(7, 3 + rownum, map.get("gkms"), wcfTytle));
				ws.addCell(new Label(8, 3 + rownum, map.get("yxms"), wcfTytle));
				ws.addCell(new Label(9, 3 + rownum, map.get("pjf"), wcfTytle));
				ws.addCell(new Label(10, 3 + rownum, map.get("jd"), wcfTytle));
				ws.addCell(new Label(11,3 + rownum, map.get("cpzf"), wcfTytle));
				
				rownum++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
