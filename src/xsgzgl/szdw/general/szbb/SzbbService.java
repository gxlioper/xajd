package xsgzgl.szdw.general.szbb;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.SzdwNewDAO;
import xsgzgl.szdw.general.inter.SzdwSzbbInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_˼�����_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class SzbbService extends CommService implements SzdwSzbbInterface {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	SzbbDAO dao = new SzbbDAO();
	SzdwNewDAO szdwNewDAO = new SzdwNewDAO();

	/**
	 * ��ȡ˼������ͷ
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getSzbbTop(SzbbModel model, User user) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao = DAO.getInstance();
		
		String[] en;
		String[] cn;
		
		if(Base.xxdm.equals("10264")){
			 en = new String[] { "pk", "nj", "xymc", "zymc", "bjmc",
					"rs", "fdyxm"};
			 cn = new String[] { "", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶",
					"�༶����", "����Ա"};
		}else if (Base.xxdm.equals("12834")) {
			 en = new String[] { "pk", "nj", "xymc", "zymc", "bjmc","rs", "fdyxm",
				"bzrxm" };
			 cn = new String[] { "", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶","�༶����", "��ӳ�",
			"�жӳ�" };
		}else if (Base.xxdm.equals("12303")) {
			en = new String[] { "pk", "nj", "xymc", "zymc", "bjmc","rs", "fdyxm",
					"bzrxm","qqqh" };
			cn = new String[] { "", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶","�༶����", "����Ա",
					"������","QQȺ��" };
			
		}else{
			 en = new String[] { "pk", "nj", "symc", "zymc", "bjmc","rs", "fdyxm",
					"bzrxm" };
			 cn = new String[] { "", "�꼶", "��Ժ", "רҵ", "�༶","�༶����", "����Ա",
				"������" };
		}

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	public List<HashMap<String, String>> getSzBzrbbTop(SzbbModel model, User user) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao = DAO.getInstance();

		String[] en;
		String[] cn;

		if(Base.xxdm.equals("10264")){
			en = new String[] { "pk", "nj", "xymc", "zymc", "bjmc",
					"rs", "fdyxm"};
			cn = new String[] { "", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶",
					"�༶����", "����Ա"};
		}else if (Base.xxdm.equals("12834")) {
			en = new String[] { "pk", "nj", "xymc", "zymc", "bjmc","rs", "fdyxm",
					"bzrxm" };
			cn = new String[] { "", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶","�༶����", "��ӳ�",
					"�жӳ�" };
		}else if (Base.xxdm.equals("12303")) {
			en = new String[] { "pk", "nj", "xymc", "zymc", "bjmc","rs", "fdyxm",
					"bzrxm","qqqh" };
			cn = new String[] { "", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶","�༶����", "����Ա",
					"������","QQȺ��" };

		}else{
			en = new String[] { "pk", "nj", "xymc", "zymc", "bjmc","rs", "fdyxm",
					"bzrxm" };
			cn = new String[] { "", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶","�༶����", "����Ա",
					"������" };
		}

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}
	
	/**
	 * ��ȡ˼���������ͷ
	 * @date 2013-01-09
	 * @author qlj
	 */
	public List<HashMap<String, String>> getSzbbSetTop1(SzbbModel model, User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "zgh", "xm","bmmc", "xb", "dbrs","bjxx","cz" };
		String[] cn = new String[] { "ְ����", "����","���ڲ���", "�Ա�", "����Ա����ѧ������","������","����" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}
	public List<HashMap<String, String>> getSzbbSetTop(SzbbModel model, User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "zgh", "xm","bmmc", "xb", "bjxx","cz" };
		String[] cn = new String[] { "ְ����", "����","���ڲ���", "�Ա�", "������","����" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ȡ˼�������Ϣ�����
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getSzbbList(SzdwGeneralForm myForm,
			SzbbModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getSzbbList(myForm, user);
	}

	/**
	 * ��ȡ˼�������Ϣ�����
	 * @date 2013-01-09
	 * @author wn
	 */
	public ArrayList<String[]> getSzBzrbbList(SzdwGeneralForm myForm,
										   SzbbModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getSzBzrbbList(myForm, user);
	}

	/**
	 * ����˼�������ϢHTML
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public String createSzbbHTML(SearchRsModel rsModel, SzbbModel model,
			ArrayList<String[]> rsArrList, User user,boolean isfdy) {
//		 TODO �Զ����ɷ������
		// IE�汾
		String ie = rsModel.getIe();
		// V4·��
		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:5px\" ");		
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"div_pkValue\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");
				
				for (int j = 1; j < 5; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"text-align: left;\" ");
					html.append(">");
					html.append(rs[j]);
					html.append("</td>");
				}
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"text-align: left;\" ");
				html.append(">");
				if(isfdy){
					html.append("<a href=\"#\" onclick=\"xsxxView('"+ pk +"','"+ rs[5] + "','fdy');return false;\" ");
				} else {
					html.append("<a href=\"#\" onclick=\"xsxxView('"+ pk +"','"+ rs[5] + "','bzr');return false;\" ");
				}
				html.append(">");
				html.append("<font color=\"blue\" ");
				html.append(">");
				html.append(rs[5]);
				html.append("</font>");
				html.append("</a>");
				html.append("</td>");
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"text-align: left;\" ");
				html.append(" title='"+rs[7]+"'>");
				html.append(rs[6]);
				html.append("</td>");
				
				if(!Base.xxdm.equals("10264")){ //�Ϻ������ѧ���Ի���ʾ
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"text-align: left;\" ");
					html.append(" title='"+rs[9]+"'>");
					html.append(rs[8]);
					html.append("</td>");
				}
				if(Base.xxdm.equals("12303")){ 
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"text-align: left;\" ");
					html.append(" title='"+rs[10]+"'>");
					html.append(rs[10]);
					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}

		return html.toString();
	}
	
	/**
	 * ���ݰ༶��ȡ�ѷ�����İ�����
	 * @param bjdm
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>>getYfpFdy(String bjdm){
		
		return dao.getYfpFdy(bjdm);
	}
	
	/**
	 * �����ѷ��丨��Աhtml
	 * @param bjdm
	 * @return
	 */
	public String createYfpFdyHTML(String bjdm){
		
		StringBuilder html=new StringBuilder();
		
		List<HashMap<String,String>>list=getYfpFdy(bjdm);
		
		for(int i=0;i<list.size();i++){
			
			HashMap<String,String>map=list.get(i);
			
			String zgh=!Base.isNull(map.get("zgh")) ? map.get("zgh"):"";
			String xm=!Base.isNull(map.get("xm")) ? map.get("xm"):"";
			String xb=!Base.isNull(map.get("xb")) ? map.get("xb"):"";
			String bmmc=!Base.isNull(map.get("bmmc")) ? map.get("bmmc"):"";
			String lxdh=!Base.isNull(map.get("lxdh")) ? map.get("lxdh"):"";
			String showbmmc=!Base.isNull(map.get("showbmmc")) ? map.get("showbmmc"):"";
			
			html.append("<tr style=\"22.5px\">");
			html.append("<td  style=\"text-align: center;\" >");
			html.append(zgh);
			html.append("</td>");
			html.append("<td  style=\"text-align: center;\" >");
			html.append(xm);
			html.append("</td>");
			html.append("<td  style=\"text-align: center;\" >");
			html.append(xb);
			html.append("</td>");
			html.append("<td  style=\"text-align: center;\" title=\""+bmmc+"\">");
			html.append(showbmmc);
			html.append("</td>");
			html.append("<td  style=\"text-align: center;\" >");
			html.append(lxdh);
			html.append("</td>");
			html.append("<td  style=\"text-align: center;\" >");
			html.append("<a hreft=\"#\" onclick=\"cancelFdybb('"+zgh+"','"+bjdm+"')\" style=\"cursor:hand\" ><font color=\"blue\"><U>ȡ��</U></font></a>");
			html.append("</td>");
			html.append("</tr>");
			
		}
		
		int length=0;
		if(list!=null){
			
			length=list.size();
		}
		
		if(list==null || list.size()<3){
			
			for(int i=0;i<3-length;i++){
				
				html.append("<tr style=\"22.5px\">");
				html.append("<td  style=\"text-align: center;\" >");
				html.append("&nbsp;");
				html.append("</td>");
				html.append("<td  style=\"text-align: center;\" >");
				html.append("&nbsp;");
				html.append("</td>");
				html.append("<td  style=\"text-align: center;\" >");
				html.append("&nbsp;");
				html.append("</td>");
				html.append("<td  style=\"text-align: center;\" >");
				html.append("&nbsp;");
				html.append("</td>");
				html.append("<td  style=\"text-align: center;\" >");
				html.append("&nbsp;");
				html.append("</td>");
				html.append("<td  style=\"text-align: center;\" >");
				html.append("&nbsp;");
				html.append("</td>");
				html.append("</tr>");
			}
			
		}
		
		return html.toString();
		
	}

	/**
	 * ���ݰ༶��ȡ�ѷ�����İ�����
	 * @param bjdm
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>>getYfpBzr(String bjdm){

		return dao.getYfpBzr(bjdm);
	}
	
	/**
	 * �����ѷ��������html
	 * @param bjdm
	 * @return
	 */
	public String createYfpBzrHTML(String bjdm){
		
		StringBuilder html=new StringBuilder();
		
		List<HashMap<String,String>>list=getYfpBzr(bjdm);
		
		for(int i=0;i<list.size();i++){
			
			HashMap<String,String>map=list.get(i);
			
			String zgh=!Base.isNull(map.get("zgh")) ? map.get("zgh"):"";
			String xm=!Base.isNull(map.get("xm")) ? map.get("xm"):"";
			String xb=!Base.isNull(map.get("xb")) ? map.get("xb"):"";
			String bmmc=!Base.isNull(map.get("bmmc")) ? map.get("bmmc"):"";
			String lxdh=!Base.isNull(map.get("lxdh")) ? map.get("lxdh"):"";
			String showbmmc=!Base.isNull(map.get("showbmmc")) ? map.get("showbmmc"):"";
			
			html.append("<tr>");
			html.append("<td style=\"text-align: center;\" >");
			html.append(zgh);
			html.append("</td>");
			html.append("<td style=\"text-align: center;\" >");
			html.append(xm);
			html.append("</td>");
			html.append("<td style=\"text-align: center;\" >");
			html.append(xb);
			html.append("</td>");
			html.append("<td  style=\"text-align: center;\" title=\""+bmmc+"\">");
			html.append(showbmmc);
			html.append("</td>");
			html.append("<td style=\"text-align: center;\" >");
			html.append(lxdh);
			html.append("</td>");
			html.append("<td style=\"text-align: center;\" >");
			html.append("<a hreft=\"#\" onclick=\"cancelBzrbb('"+zgh+"','"+bjdm+"')\" style=\"cursor:hand\" ><font color=\"blue\"><U>ȡ��</U></font></a>");
			html.append("</td>");
			html.append("</tr>");
			
		}
		
		int length=0;
		if(list!=null){
			
			length=list.size();
		}
		
		if(list==null || list.size()<3){
			
			for(int i=0;i<3-length;i++){
				
				html.append("<tr style=\"22.5px\">");
				html.append("<td  style=\"text-align: center;\" >");
				html.append("&nbsp;");
				html.append("</td>");
				html.append("<td  style=\"text-align: center;\" >");
				html.append("&nbsp;");
				html.append("</td>");
				html.append("<td  style=\"text-align: center;\" >");
				html.append("&nbsp;");
				html.append("</td>");
				html.append("<td  style=\"text-align: center;\" >");
				html.append("&nbsp;");
				html.append("</td>");
				html.append("<td  style=\"text-align: center;\" >");
				html.append("&nbsp;");
				html.append("</td>");
				html.append("<td  style=\"text-align: center;\" >");
				html.append("&nbsp;");
				html.append("</td>");
				html.append("</tr>");
			}
			
		}
		
		return html.toString();
		
	}

	/**
	 * ��ȡδ�����������Ϣ
	 */
	public List<HashMap<String, String>> getWfpBzr(String bjdm) {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * ��ȡδ���丨��Ա��Ϣ
	 */
	public List<HashMap<String, String>> getWfpFdy(String bjdm) {
		// TODO �Զ����ɷ������
		return null;
	}


	/**
	 * ��û��˼�����S�o�����
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public List<String[]> getWfpFdyList(SzdwGeneralForm myForm,
			SzbbModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getWfpFdyList(myForm, user);
	}

	/**
	 * ��û��˼�����S�o�����
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public List<String[]> getWfpBzrList(SzdwGeneralForm myForm,
			SzbbModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getWfpBzrList(myForm, user);
	}

	/**
	 * ����δ���丨��ԱHTML
	 * author qlj
	 */
	public String createWfpFdyHTML(SearchRsModel rsModel, SzbbModel model, ArrayList<String[]> rsArrList, User user) {
		// TODO �Զ����ɷ������
		// IE�汾
		String ie = rsModel.getIe();
		// V4·��
		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
//				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:5px\" ");		
//				// IE8
//				if ("5.8".equalsIgnoreCase(ie)) {
//					html.append("height=\"28.5px\"");
//				} else {
//					html.append("height=\"29px\"");
//				}
//				html.append(">");
//				html.append("<input type=\"checkbox\" name=\"div_pkValue\" ");
//				html.append("value=\"" + pk + "\"/>");
//				html.append("</td>");
				
				for (int j = 0; j < rs.length-1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"text-align: center;\" ");
					html.append(">");
					html.append(rs[j]);
					html.append("</td>");
				}
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"text-align: center;\" >");
				//��������ĸ���Ա��ʾ�����������⴦��
				if("12303".equals(Base.xxdm)){
					html.append("<a href=\"#\" onclick=\"showBjxx('"+rs[0]+"','fdy','"+rs[5]+"');return false;\"><font color='blue'><U>"+rs[5]+"</u></font></a>");
				}else{
					html.append("<a href=\"#\" onclick=\"showBjxx('"+rs[0]+"','fdy','"+rs[4]+"');return false;\"><font color='blue'><U>"+rs[4]+"</u></font></a>");
				}
				html.append("</td>");
				
				html.append("<td style=\"text-align: center;\" >");
				String fplx=model.getFplx();
				html.append("<a href=\"#\" onclick=\"setFdybb('"+rs[0]+"')\"><font color=\"blue\"><U>����</U></font></a>");
				
				html.append("</td>");
				
				html.append("</tr>");
			}
		}

		return html.toString();
	}
	
	/**
	 * ����δ���������HTML
	 * author qlj
	 */
	public String createWfpBzrHTML(SearchRsModel rsModel, SzbbModel model, ArrayList<String[]> rsArrList, User user) {
//		 TODO �Զ����ɷ������
		// IE�汾
		String ie = rsModel.getIe();
		// V4·��
		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
//				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:5px\" ");		
//				// IE8
//				if ("5.8".equalsIgnoreCase(ie)) {
//					html.append("height=\"28.5px\"");
//				} else {
//					html.append("height=\"29px\"");
//				}
//				html.append(">");
//				html.append("<input type=\"checkbox\" name=\"div_pkValue\" ");
//				html.append("value=\"" + pk + "\"/>");
//				html.append("</td>");
				
				for (int j = 0; j < rs.length-1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"text-align: center;\" ");
					html.append(">");
					html.append(rs[j]);
					html.append("</td>");
				}
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"text-align: center;\" >");
				html.append("<a href=\"#\" onclick=\"showBjxx('"+rs[0]+"','bzr','"+rs[4]+"');return false;\"><font color='blue'><U>"+rs[4]+"</u></font></a>");
				html.append("</td>");
				
				html.append("<td style=\"text-align: center;\" >");
				String fplx=model.getFplx();
				html.append("<a href=\"#\" onclick=\"setBzrbb('"+rs[0]+"')\"><font color=\"blue\"><U>����</U></font></a>");
				
				html.append("</td>");
				
				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 * ȡ�������α��
	 * author qlj
	 */
	public boolean cancelBzrbb(String zgh, String bjdm) throws Exception {
			
		return dao.cancelBzrbb(zgh, bjdm);
	}

	/**
	 * ȡ������Ա���
	 * author qlj
	 */
	public boolean cancelFdybb(String zgh, String bjdm) throws Exception {
		
		return dao.cancelFdybb(zgh, bjdm);
	}

	/**
	 * ������ ���
	 * author qlj
	 */
	public boolean setBzrbb(String zgh, String bjdm) throws Exception {
		
		return dao.setBzrbb(zgh, bjdm);
	}

	/**
	 * ����Ա���
	 * author qlj
	 */
	public boolean setFdybb(String zgh, String bjdm) throws Exception {
		
		return dao.setFdybb(zgh, bjdm);
	}
	
	/**
	 * ͨ���༶�����ȡ˼����Ϣ
	 */
	public List<HashMap<String, String>> getFdyBzrListByBjdm(String bjdm) {
		return dao.getFdyBzrListByBjdm(bjdm);
	}

	/*
	      ����: @see xsgzgl.szdw.general.inter.SzdwSzbbInterface#getSzdwbbExportList(xsgzgl.szdw.general.SzdwGeneralForm, xgxt.form.User)
	 */
	
	public List<HashMap<String, String>> getSzdwbbExportList(
			SzdwGeneralForm myForm, User user) throws Exception {
		return dao.getSzdwbbExportList(myForm, user);
	}

	public boolean setQQqh(SzdwGeneralForm model) throws Exception{
		return dao.setQQqh(model);
	}
	public String getBjmcForBjdm(String bjdm){
		return dao.getBjmcForBjdm(bjdm);
	}
	public String getCxQQqh(String bjdm){
		return dao.getCxQQqh(bjdm);
	}
}