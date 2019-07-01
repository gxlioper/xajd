package xsgzgl.gygl.wsjc.fscx;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
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
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xsgzgl.comm.BasicService;
import xsgzgl.gygl.comm.GyglNewInit;

import com.zfsoft.utils.StringUtil;
import common.Globals;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-28 ����11:29:22
 * </p>
 */
public class FscxService extends BasicService {

	/**
	 * ���ý����ѯ��ͷ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] {};
		String[] cn = new String[] {};
		
		//�㽭�����Ի�ҳ��
		if(Globals.XXDM_ZJLG.equals(Base.xxdm) || "1".equals(GyglNewInit.FSCX_XSFS)){
			en = new String[] { "", "ld","ch", "qsh", "ssnj", "ssxy", "cws", "rzrs", "jcrc", "fz", "dj"};
			cn = new String[] { "", "¥��", "���", "���Һ�", "�����꼶", "����ѧԺ", "��λ��", "��ס����", "����ճ�", "��ֵ", "�ȼ�" };
		}else{
			if (("0").equals(GyglNewInit.JFFS)) {
				if(Base.xxdm.equals("33333")){
					en = new String[] { "", "ld", "ch", "qsh", "ssnj", "ssxy", "cws", "rsrs", "jcrc","fz" ,"kfyj" ,"pfbz"};
					cn = new String[] { "", "¥��", "���", "���Һ�", "�����꼶", "����ѧԺ", "��λ��", "��ס����", "����ճ�", "�۷�" , "�۷�����" , "�۷ֱ�ע" };
				}else if("11647".equals(Base.xxdm)){
					en = new String[] { "", "ld", "ch", "qsh", "ssnj", "ssxy", "cws", "rzrs", "jcrc", "fz","dj" };
					cn = new String[] { "", "¥��", "���", "���Һ�", "�����꼶", "����ѧԺ", "��λ��", "��ס����", "����ճ�", "��ֵ","�ȼ�" };
				}else{					
					en = new String[] { "", "ld", "ch", "qsh", "ssnj", "ssxy", "cws", "rzrs", "jcrc", "fz" };
					cn = new String[] { "", "¥��", "���", "���Һ�", "�����꼶", "����ѧԺ", "��λ��", "��ס����", "����ճ�", "��ֵ" };
				}
			} else {
				en = new String[] { "", "ld", "ch", "qsh", "ssnj", "ssxy", "cws", "rzrs", "jcrc", "dj" };
				cn = new String[] { "", "¥��", "���", "���Һ�", "�����꼶", "����ѧԺ", "��λ��", "��ס����", "����ճ�", "�ȼ�" };
			}
		}
		return dao.arrayToList(en, cn);
	}

	/**
	 * ������飬�����������Ϣ
	 * 
	 * @param myForm
	 * @return
	 * @throws IllegalArgumentException , SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
	 */
	public ArrayList<String[]> getFscxCx(FscxForm myForm,HttpServletRequest request)
			throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		FscxDAO dao = new FscxDAO();
		return dao.getFscxCx(myForm,request);
	}

	/**
	 * ������飬��������ּ���ճ�
	 * 
	 * @param fscxForm
	 * @param pkValue
	 * @return
	 * @throws
	 */
	public HashMap<String, String> getFscxCz2(FscxForm fscxForm, String pkValue) {
		FscxDAO dao = new FscxDAO();
		return dao.getFscxCz2(fscxForm, pkValue);
	}

	/**
	 * ������飬����������޸�ǰ����Ϣ
	 * 
	 * @param fscxForm
	 * @return
	 * @throws
	 */
	public HashMap<String, String> getFscxMap(FscxForm fscxForm) {
		FscxDAO dao = new FscxDAO();
		return dao.getFscxMap(fscxForm);
	}
	
	public HashMap<String, String> getFscxAllMap(FscxForm fscxForm) {
		FscxDAO dao = new FscxDAO();
		return dao.getFscxAllMap(fscxForm);
	}

	/**
	 * ������飬��õȼ������б��
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Object getDjList(HttpServletRequest request) {
		FscxDAO dao = new FscxDAO();
		return dao.getDjList(request);
	}
	public Object getXjList(HttpServletRequest request) {
		FscxDAO dao = new FscxDAO();
		return dao.getXjList(request);
	}

	/**
	 * ������飬����������Ϣ���޸�
	 * 
	 * @param myForm
	 * @param pkValue
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public boolean fscxXg(FscxForm myForm, String pkValue, String username)throws Exception {
		FscxDAO dao = new FscxDAO();
		return dao.fscxXg(myForm, pkValue, username);
	}

	/**
	 * ������飬����������Ϣ��ɾ��
	 * 
	 * @param myForm
	 * @param valArr
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public boolean fscxSc(FscxForm myForm, String[] valArr, String username)throws SQLException {
		FscxDAO dao = new FscxDAO();
		return dao.fscxSc(myForm, valArr, username);
	}

	/**
	 * ���ý����ѯ��ͷ
	 * 
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,List<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("<input type='hidden' name='lddm'  ");
				html.append("  id='lddm_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[rs.length-1]) + "'/> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length - 1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""+ 100 / (rs.length - 2) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					if (j == 6) {
						html.append(" title=\"" + rs[7] + "\" ");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	/**
	 * �����ֲ�ѯ �Զ��嵼��
	 * 
	 * @param myForm
	 * @return
	 * @throws IllegalArgumentException , SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
	 */
	public List<HashMap<String, String>> getFscxExportCx(FscxForm myForm,HttpServletRequest request,User user)
			throws Exception {
		FscxDAO dao = new FscxDAO();
		return dao.getFscxExportCx(myForm,request,user);
	}
	
	/**
	 * �����ֲ�ѯ  ������ѧ����������
	 * 
	 * @param myForm
	 * @return
	 * @throws IllegalArgumentException , SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
	 */
	public List<HashMap<String, String>> getbjgmdExportCx(FscxForm myForm,HttpServletRequest request,User user)
			throws Exception {
		FscxDAO dao = new FscxDAO();
		return dao.getbjgmdExportCx(myForm,request,user);
	}
	
	/**
	 * @����: ���������ֵȼ� �� ���µȼ�
	 * @���ߣ��׽���[���ţ�781]
	 * @���ڣ�2013-8-30 ����02:55:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean pdQsDjXg(FscxForm myForm,HttpServletRequest request,User user)
		throws Exception{
		FscxDAO dao = new FscxDAO();
		//��ȡ���ҷ��������б�
		List<String[]> qsfszqList = dao.getQsfszqListByXnXq(myForm, request, user);
		
		//��ȡ���ҷ���
		List<String[]> dataList=dao.getFscxPfCx(myForm, request, user,qsfszqList);
		
		//����ѧ��ѧ��������ҵǼ�
		updateQsdjByXnxq("",myForm.getXn(), myForm.getXq());
		
		//����ǰ��  �������ҵȼ�
		//return false;
		return qsdjXg(dataList,qsfszqList);
	}
	
	/**
	 * 
	 * @����: �������ҵȼ� 
	 * @���ߣ��׽���[���ţ�781]
	 * @���ڣ�2013-8-29 ����10:33:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param List<String[]>  ��ʽ:{[guid||qsh,fs1,fs1,fs1],...}  qsh:���Һ�    fs*������
	 * @param qsfszqList ���ҷ������б�
	 * @return
	 * boolean ��������  �ɹ���true��ʧ�ܣ�false
	 * @throws
	 */
	private boolean qsdjXg(List<String[]> list,List<String[]> qsfszqList) throws Exception{
		if(list == null || list.size() == 0){
			return false;
		}
		FscxDAO dao = new FscxDAO();
		//���ҵȼ�����
		List<String[]> qsdjList=getQsdjList(list);
		
		/**
		System.out.println("---------------��������-----------------");
		for (int i = 0; i < list.size(); i++) {
			System.out.print((i+1) + "\t");
			for (int j = 0; j < list.get(i).length; j++) {
				System.out.print(list.get(i)[j] + "\t");
			}
			System.out.println();
			System.out.print("\t");
			for (int j = 0; j < qsdjList.get(i).length; j++) {
				System.out.print(qsdjList.get(i)[j] + "\t");
			}
			System.out.println();
		}**/
		//�����޸�����Ч��̫��
		return dao.qsdjPlXg(qsdjList,qsfszqList);
	}
	
	/**
	 * 
	 * @����: ��ȡ���ҵȼ�
	 * @���ߣ��׽���[���ţ�781]
	 * @���ڣ�2013-8-29 ����10:30:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param List<String[]>  ��ʽ:{[guid||qsh,fs1,fs1,fs1],...}  qsh:���Һ�    fs*������
	 * @return
	 * List<String[]> �������� ��ʽ:{[guid||qsh,dj1,dj2,dj3],...}
	 * @throws
	 */
	private List<String[]> getQsdjList(List<String[]> list){
		if(list == null || list.size() == 0){
			return null;
		}
		FscxFsjsUtil fscxFsjsUtil=new FscxFsjsUtil(list);
		//�������ӵ�һ�в��������
		fscxFsjsUtil.setNeglectNum(1);
		//���ҵȼ�����
		List<String[]> qsdjList=fscxFsjsUtil.grade();
		return qsdjList;
	}
	
	/**
	 * @����: ��֤����ճ��Ƿ��ύ
	 * @���ߣ��׽���[���ţ�781]
	 * @���ڣ�2013-9-2 ����08:51:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @param user
	 * @return
	 * boolean ��������   ���ύ��true��δ�ύ��false
	 * @throws
	 */
	public boolean checkJcrcSftj(FscxForm myForm,HttpServletRequest request,User user)
		throws Exception{
		FscxDAO dao = new FscxDAO();
		List<HashMap<String, String>> list = dao.getJcrcListByWtj(myForm, request, user);
		return list == null || list.size() == 0;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-10 ����01:57:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dj
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateQsdjByXnxq(String dj,String xn,String xq) throws Exception{
		if(StringUtil.isNull(dj) || StringUtil.isNull(xn) || StringUtil.isNull(xq)){
			return false;
		}
		FscxForm myForm=new FscxForm();
		myForm.setDj(dj);
		myForm.setXn(xn);
		myForm.setXq(xq);
		
		FscxDAO dao = new FscxDAO();
		return dao.updateQsdjByXnxq(myForm);
	}
	/**
	 * 
	 * @����:�´����������ָ��Ի�����
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-11-29 ����10:40:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws
	 */
	public void wsfDcOfWd(FscxForm model, HttpServletRequest request,OutputStream os, User user)
	throws Exception{
		FscxDAO dao = new FscxDAO();
		List<HashMap<String,String>> resultList =dao.getWsfTjList(model,request,user);
		// ���ñ���
		StringBuffer title = new StringBuffer();
		title.append(resultList.get(0).get("ldmc")+"��������Ʒ��ʵ���ֺ���");

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("Ʒ��ʵ����", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 12, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);

		ws.addCell(new Label(0, 2, "���", wcf2));
		ws.addCell(new Label(1, 2, "���Һ�", wcf2));
		ws.addCell(new Label(2, 2, "������", wcf2));
		ws.addCell(new Label(3, 2, "3��", wcf2));
		ws.addCell(new Label(4, 2, "4��", wcf2));
		ws.addCell(new Label(5, 2, "5��", wcf2));
		ws.addCell(new Label(6, 2, "6��", wcf2));
		ws.addCell(new Label(7, 2, "�ϼ�", wcf2));
		ws.addCell(new Label(8, 2, "9��", wcf2));
		ws.addCell(new Label(9, 2, "10��", wcf2));
		ws.addCell(new Label(10, 2, "11��", wcf2));
		ws.addCell(new Label(11, 2, "12��", wcf2));
		ws.addCell(new Label(12, 2, "�ϼ�", wcf2));
		if (resultList != null && resultList.size() > 0) {

			for (int i = 0; i < resultList.size(); i++) {

				HashMap<String, String> map = resultList.get(i);

				ws.setColumnView(0, 5);
				ws.setColumnView(1, 10);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 10);
				ws.setColumnView(4, 10);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 10);
				ws.setColumnView(7, 10);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 10);
				ws.setColumnView(10, 10);
				ws.setColumnView(11, 10);
				ws.setColumnView(12, 10);
				ws.addCell(new Label(0, 3 + i, map.get("r"), wcf2));// ���
				ws.addCell(new Label(1, 3 + i, map.get("qsh"), wcf2));
				ws.addCell(new Label(2, 3 + i, "80", wcf2));
				ws.addCell(new Label(3, 3 + i, map.get("month3"), wcf2));
				ws.addCell(new Label(4, 3 + i, map.get("month4"), wcf2));
				ws.addCell(new Label(5, 3 + i, map.get("month5"), wcf2));
				ws.addCell(new Label(6, 3 + i, map.get("month6"), wcf2));
				ws.addCell(new Label(7, 3 + i, map.get("count1"), wcf2));
				ws.addCell(new Label(8, 3 + i, map.get("month9"), wcf2));
				ws.addCell(new Label(9, 3 + i, map.get("month10"), wcf2));
				ws.addCell(new Label(10, 3 + i, map.get("month11"), wcf2));
				ws.addCell(new Label(11, 3 + i, map.get("month12"), wcf2));
				ws.addCell(new Label(12, 3 + i, map.get("count2"), wcf2));
				
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}
	
	
	/** 
	 * @����:��ͳ�Ƶ���(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-7 ����10:14:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param request
	 * @param os
	 * @param user
	 * @throws Exception
	 * void �������� 
	 * @throws 
	 */
	public void zTjdc(FscxForm model, HttpServletRequest request,OutputStream os, User user)
	throws Exception{
		FscxDAO dao = new FscxDAO();
		List<HashMap<String,String>> resultList =dao.getZTjList(model,request,user);
		String[] sj = model.getSearchModel().getSearch_tj_kssj();
		String[] sj_1 = model.getSearchModel().getSearch_tj_jssj();
		String ksNan = sj[0].substring(0,4);
		String ksYue = sj[0].substring(4,6);
		String ksRi = sj[0].substring(6,8);
		String jsNan = sj_1[0].substring(0,4);
		String jsYue = sj_1[0].substring(4,6);
		String jsRi = sj_1[0].substring(6,8);
		
		// ���ñ���
		StringBuilder title = new StringBuilder();
		title.append("����������һ��������ܱ�����ָ��վ��");
		// ���ø�����
		StringBuilder title_1 = new StringBuilder();
		title_1.append("  ��       ��            �����ˣ�" +user.getRealName()+"         "+ksNan+"��"+ksYue+"��"+ksRi+"����"+jsNan+"��"+jsYue+"��"+jsRi+"��");
		

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("sheet1", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(1, 0, 10, 0);
		ws.mergeCells(1, 1, 10, 1);
		ex.printToOneCell_multy(ws, title.toString(), 1, 0, 17, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ex.printToOneCell_multy(ws, title_1.toString(), 1, 1, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.NONE);
		
		ws.addCell(new Label(1, 2, "�༶", wcf2));
		ws.addCell(new Label(2, 2, "������", wcf2));
		ws.addCell(new Label(3, 2, "����", wcf2));
		ws.addCell(new Label(4, 2, "����", wcf2));
		ws.addCell(new Label(5, 2, "���������", wcf2));
		ws.addCell(new Label(6, 2, "�༶", wcf2));
		ws.addCell(new Label(7, 2, "������", wcf2));
		ws.addCell(new Label(8, 2, "����", wcf2));
		ws.addCell(new Label(9, 2, "����", wcf2));
		ws.addCell(new Label(10,2, "���������", wcf2));

		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);

				ws.setColumnView(1, 15);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 10);
				ws.setColumnView(4, 10);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 15);
				ws.setColumnView(7, 10);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 10);
				ws.setColumnView(10,10);

				if((1+i)%2 != 0){
					if(i>0){
						ws.addCell(new Label(1, 3 + i -(i/2), map.get("bjmc"), wcf2));
						ws.addCell(new Label(2, 3 + i -(i/2), map.get("bzr"), wcf2));
						ws.addCell(new Label(3, 3 + i -(i/2), "", wcf2));
						ws.addCell(new Label(4, 3 + i -(i/2), "", wcf2));
						ws.addCell(new Label(5, 3 + i -(i/2), map.get("fs"), wcf2));
					}else{					
						ws.addCell(new Label(1, 3 + i, map.get("bjmc"), wcf2));
						ws.addCell(new Label(2, 3 + i, map.get("bzr"), wcf2));
						ws.addCell(new Label(3, 3 + i, "", wcf2));
						ws.addCell(new Label(4, 3 + i, "", wcf2));
						ws.addCell(new Label(5, 3 + i, map.get("fs"), wcf2));				
					}
				}else{
					if(i>1){
						ws.addCell(new Label(6, 3 + i - ((i/2+1)) , map.get("bjmc"), wcf2));
						ws.addCell(new Label(7, 3 + i - ((i/2+1)), map.get("bzr"), wcf2));
						ws.addCell(new Label(8, 3 + i - ((i/2+1)), "", wcf2));
						ws.addCell(new Label(9, 3 + i - ((i/2+1)), "", wcf2));
						ws.addCell(new Label(10, 3 + i - ((i/2+1)), map.get("fs"), wcf2));
					}else{						
						ws.addCell(new Label(6, 2 + i , map.get("bjmc"), wcf2));
						ws.addCell(new Label(7, 2 + i , map.get("bzr"), wcf2));
						ws.addCell(new Label(8, 2 + i , "", wcf2));
						ws.addCell(new Label(9, 2 + i , "", wcf2));
						ws.addCell(new Label(10, 2 + i , map.get("fs"), wcf2));
					}
				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}
	
	/**
	 * @description	�� ¥����ͳ�Ʒֵ���
	 * @author 		�� ������1282��
	 * @date 		��2017-11-17 ����04:59:53
	 * @param model
	 * @param request
	 * @param os
	 * @param user
	 * @throws Exception
	 */
	public void gywsfdc(FscxForm model,OutputStream os)
	throws Exception{
		FscxDAO dao = new FscxDAO();
		List<HashMap<String,String>> wsfList = dao.getLdYwsfList(model);
		List<HashMap<String,String>> pjfList = dao.getLdYwsfPjfList(model);
		List<HashMap<String,String>> resultList = dealWithRsList(wsfList, pjfList);
		String ldmc = model.getLdmc();
		String yf = model.getYf();		
		// ���ñ���
		StringBuilder title = new StringBuilder();
		title.append(ldmc + yf + "�¼Ƿ�ͳ�Ʊ�");
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("sheet1", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 43, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		
		ws.mergeCells(0, 2, 0, 3);
		ws.mergeCells(1, 2, 1, 3);
		
		ex.printToOneCell_multy(ws, "����", 0, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		ex.printToOneCell_multy(ws, "�༶", 1, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		for(int i = 2; i < 33; i++){
			ws.mergeCells(i, 2, i, 3);
			ex.printToOneCell_multy(ws, String.valueOf(i-1), i, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		}
		
		ws.mergeCells(33, 2, 33, 3);
		
		ex.printToOneCell_multy(ws, "ƽ����", 33, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		ws.mergeCells(34, 2, 37, 2);
		
		ex.printToOneCell_multy(ws, "��ɨ��", 34, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		ex.printToOneCell_multy(ws, "һ", 34, 3, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);		
		ex.printToOneCell_multy(ws, "��", 35, 3, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);		
		ex.printToOneCell_multy(ws, "��", 36, 3, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		ex.printToOneCell_multy(ws, "��", 37, 3, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		ws.mergeCells(38, 2, 38, 3);
		ex.printToOneCell_multy(ws, "����۷�", 38, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		
		ws.mergeCells(39, 2, 39, 3);
		ex.printToOneCell_multy(ws, "�����μ��", 39, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		ws.mergeCells(40, 2, 40, 3);	
		ex.printToOneCell_multy(ws, "Υ�Ϳ۷�", 40, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		ws.mergeCells(41, 2, 41, 3);	
		ex.printToOneCell_multy(ws, "�����������", 41, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		ws.mergeCells(42, 2, 42, 3);	
		ex.printToOneCell_multy(ws, "�÷�", 42, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);
		
		ws.mergeCells(43, 2, 43, 3);	
		ex.printToOneCell_multy(ws, "�ȼ�", 43, 2, 12, false,Alignment.CENTRE,VerticalAlignment.CENTRE, false,400,Border.ALL);

		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);

				ws.setColumnView(0, 10);
				ws.setColumnView(1, 20);
				for(int j = 2; j< 33; j++){
					ws.setColumnView(j, 5);
				}
				ws.setColumnView(33, 8);
				ws.setColumnView(34, 5);
				ws.setColumnView(35, 5);
				ws.setColumnView(36, 5);
				ws.setColumnView(37, 5);
				ws.setColumnView(38, 7);
				ws.setColumnView(39, 7);
				ws.setColumnView(40, 7);
				ws.setColumnView(41, 7);
				ws.setColumnView(42, 7);
				ws.setColumnView(43, 7);

				ws.addCell(new Label(0, 4 + i, map.get("qsh"), wcf2));
				ws.addCell(new Label(1, 4 + i, map.get("bjmc"), wcf2));
				for(int k = 1;k < 32;k++){
					ws.addCell(new Label(k + 1, 4 + i, map.get(String.valueOf(k)), wcf2));
				}
				ws.addCell(new Label(33, 4 + i, map.get("pjf"), wcf2));
				for(int g = 34; g < 44;g++){
					ws.addCell(new Label(g, 4 + i, new String(""), wcf2));
				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}
	
	/**
	 * @description	�� ����Ԣ�������ֺ���ƽ�����б�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-17 ����05:04:29
	 */
	public List<HashMap<String,String>> dealWithRsList(List<HashMap<String,String>> fsList,List<HashMap<String,String>> pjfList){
		List<HashMap<String,String>> rsList = new ArrayList<HashMap<String,String>>();	
			HashMap<String,String> map = new HashMap<String, String>();
			for(int i = 0;i < fsList.size();i++){
				String bjmc = fsList.get(i).get("bjmc");
				String qsh = fsList.get(i).get("qsh");
				String fs = fsList.get(i).get("fs");
				String rq = fsList.get(i).get("rq");
				//���Ϊ��һ����ʼѭ��
				if(i == 0){
					map.put("bjmc", bjmc);
					map.put("qsh", qsh);
					map.put(rq, fs);
				}else{
					//�༶���ƻ������ҺŲ����ʱ
					if(!map.get("bjmc").equals(bjmc) || !map.get("qsh").equals(qsh)){
						//������Һ���༶����һ��
						for(HashMap<String,String> pjfMap : pjfList){
							if(pjfMap.get("bjmc").equals(map.get("bjmc")) && pjfMap.get("qsh").equals(map.get("qsh"))){
								map.put("pjf", pjfMap.get("pjf"));
								break;
							}
						}
						rsList.add(map);
						map = new HashMap<String, String>();
						map.put("bjmc", bjmc);
						map.put("qsh", qsh);
						map.put(rq, fs);
					}else{
						map.put(rq, fs);
					}
					//���Ϊ���һ����¼�Ļ�������뵽rsList
					if(i == fsList.size() - 1){
						//������Һ���༶����һ��
						for(HashMap<String,String> pjfMap : pjfList){
							if(pjfMap.get("bjmc").equals(map.get("bjmc")) && pjfMap.get("qsh").equals(map.get("qsh"))){
								map.put("pjf", pjfMap.get("pjf"));
								break;
							}
						}
						rsList.add(map);
					}
				}
			}
			if(rsList.size() > 0){
				for(int i = 1; i < 32; i++){
					for(HashMap<String,String> mapp : rsList){
						if(!mapp.containsKey(String.valueOf(i))){
							mapp.put(String.valueOf("i"), new String());
						}
					}
				}
			}
		return rsList;
	}
	
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date 		��2017-11-20 ����04:34:54
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getLdYwsfList(FscxForm model){
		FscxDAO dao = new FscxDAO();
		return dao.getLdYwsfList(model);
	}
	
	/**
	 * @description	�� ��ȡ¥���б�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-17 ����11:47:34
	 * @return
	 */
	public List<HashMap<String,String>> getLdList(){
		FscxDAO dao = new FscxDAO();
		return dao.getLdList();
	}
	
	
}