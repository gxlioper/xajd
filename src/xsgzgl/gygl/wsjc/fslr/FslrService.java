package xsgzgl.gygl.wsjc.fslr;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
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
public class FslrService extends BasicService {
	//��򾭼�ó�׸��Ի�
	private ResourceBundle resource = ResourceBundle.getBundle("config/ApplicationResources");
	private final String cshfs = resource.getString("cshfs");
	/**
	 * ���ý����ѯ��ͷ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		
		String[] en = null;
		String[] cn = null;
		
		if (GyglNewInit.WSJC_XJQS){
			en = new String[] { "", "xn", "xq", "mc", "lxmc","kssj", "jssj", "qss", "ypfqss", "wpfqss" };
			cn = new String[] { "", "ѧ��", "ѧ��", "����","����", "��ʼʱ��", "����ʱ��", "������", "������������", "δ����������" };
		} else {
				en = new String[] { "", "xn", "xq", "mc","kssj", "jssj", "qss", "ypfqss", "wpfqss" };
				cn = new String[] { "", "ѧ��", "ѧ��", "����","��ʼʱ��", "����ʱ��", "������", "������������", "δ����������" };
		}
		if("12688".equals(Base.xxdm)){
			en = new String[] { "", "xn", "xq", "mc","pfjbmc","kssj", "jssj", "qss", "ypfqss", "wpfqss" };
			cn = new String[] { "", "ѧ��", "ѧ��", "����","���ַ�","��ʼʱ��", "����ʱ��", "������", "������������", "δ����������" };
			
		}
		return dao.arrayToList(en, cn);
	}

	/**
	 * ���ý����ѯ��ͷ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr2(String pkValue) {
		DAO dao = DAO.getInstance();
		HashMap<String,String> jcrc = new FslrDAO().getFslrCz2(null,pkValue);
		String jclx = jcrc.get("jclx");
		
		if (StringUtil.isNull(jclx)){
			jclx = GyglNewInit.JFFS;
		}
		
		String[] en = new String[] {};
		String[] cn = new String[] {};
		if ("0".equals(jclx)) {
			if(Base.xxdm.equals(Globals.XXDM_TJJDZYJSXY)){
				en = new String[] { "", "ldmc", "ch", "qss", "cws", "rs","","fz" ,"kfyj" ,"pfbz"};
			}else if(Base.xxdm.equals("33333")){
				en = new String[] { "", "ldmc", "ch", "qss", "cws", "rs","fz" ,"kfyj" ,"pfbz"};
			}else if("11647".equals(Base.xxdm)){
				en = new String[] { "", "ldmc", "ch", "qss", "cws", "rs","sfbyqs","fz" ,"pfbz"};
			}else{				
				en = new String[] { "", "ldmc", "ch", "qss", "cws", "rs","fz" ,"pfbz"};
			}
			if(Base.xxdm.equals(Globals.XXDM_TJJDZYJSXY)){
				cn = new String[] { "", "¥��", "���", "���Һ�",  "��λ��", "��ס����", "Ĭ�Ϸ�", "�۷�/�ӷֺϼ�" , "�۷�/�ӷ�����" , "�۷ֱ�ע" };
			}else if(Base.xxdm.equals("33333")){
				cn = new String[] { "", "¥��", "���", "���Һ�",  "��λ��", "��ס����", "�۷�" , "�۷�����" , "�۷ֱ�ע" };
			}else if("11647".equals(Base.xxdm)){
				cn = new String[] { "", "¥��", "���", "���Һ�",  "��λ��", "��ס����", "��ҵ����","��ֵ" , "��ֵ��ע" };
			}else{				
				cn = new String[] { "", "¥��", "���", "���Һ�",  "��λ��", "��ס����", "��ֵ" , "��ֵ��ע" };
			}
		} else if ("1".equals(jclx)){
			en = new String[] { "", "ldmc", "ch", "qss", "cws", "rs","pfbz", "dj" };
			cn = new String[] { "", "¥��", "���", "���Һ�",  "��λ��", "��ס����", "�ȼ�" ,"�ȼ���ע"};
		} else {
			en = new String[] { "", "ldmc", "ch", "qss", "cws", "rs","pfbz", "dj" };
			cn = new String[] { "", "¥��", "���", "���Һ�",  "��λ��", "��ס����", "�Ǽ�" ,"�Ǽ���ע"};
		}
		return dao.arrayToList(en, cn);
	}

	/**
	 * ������飬������¼����Ϣ�Ĳ�ѯ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFslrCx(FslrForm model,HttpServletRequest request) throws Exception {
		FslrDAO dao = new FslrDAO();
		return dao.getFslrCx(model,request);
	}

	/**
	 * ������飬������¼����Ϣ�Ĳ���
	 * 
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFslrCz(FslrForm model, String pkValue,HttpServletRequest request) throws Exception {
		FslrDAO dao = new FslrDAO();
		return dao.getFslrCz(model, pkValue,request);
	}

	/**
	 * ������飬������¼���ճ���Ϣ�Ĳ���
	 * 
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws
	 */
	public HashMap<String, String> getFslrCz2(FslrForm model, String pkValue) {
		FslrDAO dao = new FslrDAO();
		return dao.getFslrCz2(model, pkValue);
	}

	/**
	 * @param bzStr 
	 * @param sfsdj
	 *            ������飬��������¼����Ϣ�ı���
	 * @param model
	 * @param pkValue
	 * @param valArr
	 * @param username
	 * @return
	 * @throws
	 */
	public boolean fslrBc(FslrForm model, String pkValue, String[] valArr, String[] bzStr, String username, String sfsdj) throws Exception {
		FslrDAO dao = new FslrDAO();
		return dao.fslrBc(model, pkValue, valArr, bzStr, username, sfsdj);
	}

	/**
	 * ���������
	 * 
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel, List<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "' ");
				if("1".equals(rs[rs.length-1])){
					html.append(" disabled='disabled'");
				}
				html.append(" /> ");
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[1]) + "'/> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length-1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 2) + "%\" ");
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
	 * ���������
	 * 
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public String createSearchHTML2(SearchRsModel rsModel,List<String[]> rsArrList, User user,String rcid)
			throws IllegalArgumentException, SecurityException,IllegalAccessException, InvocationTargetException,NoSuchMethodException {
		FslrDAO dao = new FslrDAO();
		List<String[]> fslrdjArr = dao.getFslrDj();
		List<String[]> fslrxjArr = dao.getFslrXj();
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		
		HashMap<String,String> jcrc = new FslrDAO().getFslrCz2(null,rcid);
		String jclx = jcrc.get("jclx");
		
		if (StringUtil.isNull(jclx)){
			jclx = GyglNewInit.JFFS;
		}
		
		
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" id=\"tr"+i+"\" ");
				if(rs[7]!=""){
					html.append(" style=\"background:rgb(255,222,173)\" ");
				}
				html.append(" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' onclick='checkIss(this)' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 2) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
//					if (j == 6) {
//						html.append(" title=\"" + rs[7] + "\" ");
//					}
					html.append(">");
					if (j == rs.length - 2) {
						if ("0".equals(jclx)) {
							html.append("<input onblur='checkIs(this)' id='fz_" + i + "'" + " value=\"" + rs[j] + "\" onkeyup=\"if(event.keyCode==13) {skipNext(this)}\"/>");
						} else if("1".equals(jclx)){
							//----�ȼ�---
							html.append("<select onblur='checkIn(this)' style=\"width: 80px\">");
							html.append("<option value=\"\"></option>");
							for (String[] fslrdj : fslrdjArr) {
								if (rs[j].equals(fslrdj[0])) {
									html.append("<option value=\"" + fslrdj[0] + "\" selected=\"selected\">" + fslrdj[0] + "</option>");
								} else {
									html.append("<option value=\"" + fslrdj[0] + "\">" + fslrdj[0] + "</option>");
								}
							}
							html.append("</select>");
						} else {
							//----�Ǽ�---
							html.append("<select onblur='checkIn(this)' style=\"width: 80px\">");
							html.append("<option value=\"\"></option>");
							for (String[] fslrdj : fslrxjArr) {
								if (rs[j].equals(fslrdj[0])) {
									html.append("<option value=\"" + fslrdj[0] + "\" selected=\"selected\">" + fslrdj[0] + "</option>");
								} else {
									html.append("<option value=\"" + fslrdj[0] + "\">" + fslrdj[0] + "</option>");
								}
							}
							html.append("</select>");
						}
					} else if(j == rs.length - 1){
						
						html.append("<input id='bz_" + i + "'" + " value=\"" + rs[j] + "\" maxlength=\"50\" onblur='checkBz(this)'/>");
					}else {
						html.append(replaceHtml(rs[j]));
					}
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	/** 
	 * @����:�㽭��ҵ��ʦ���Ի�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-1 ����10:12:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @param rcid
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * String �������� 
	 * @throws 
	 */
	public String createSearchHTMLForZjsyjs(SearchRsModel rsModel,List<String[]> rsArrList, User user,String rcid)
	throws IllegalArgumentException, SecurityException,IllegalAccessException, InvocationTargetException,NoSuchMethodException {
		FslrDAO dao = new FslrDAO();
		List<String[]> fslrdjArr = dao.getFslrDj();
		List<String[]> fslrxjArr = dao.getFslrXj();
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		
		HashMap<String,String> jcrc = new FslrDAO().getFslrCz2(null,rcid);
		String jclx = jcrc.get("jclx");
		
		if (StringUtil.isNull(jclx)){
			jclx = GyglNewInit.JFFS;
		}
		
		
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"\" id=\"tr"+i+"\" ");
				String st = rs[7];
				if(Base.xxdm.equals(Globals.XXDM_TJJDZYJSXY)){
					st = rs[8];
				}
				if(st!=""){
					html.append(" style=\"background:rgb(255,222,173)\" ");
				}
				html.append(" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					if(jclx.equals("0") && j == rs.length-3){
						html.append("<td align=\"left\" nowrap=\"nowrap\" style='width:60px'");
					}else{						
						html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 2) + "%\" ");					
					}
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					if(j == rs.length-3){
						if("0".equals(jclx)){
							html.append("<input id='fz_" + i + "'" + " value=\"" + rs[j] + "\" style='width:50px' readonly='true'/>");
						}						
					}
					if (j == rs.length - 2) {
						if ("0".equals(jclx)) {//����Ϊ����
							html.append("<input id='kfyj_" + i + "'" + " value=\"" + rs[j] + "\" maxlength=\"200\" style='width:260px' onclick='kfmx(this)' title=\"" + rs[j] + "\" readonly='true'/>");
						} else if("1".equals(jclx)){
							//----�ȼ�---
							html.append("<select onblur='checkIn(this)' style=\"width: 80px\">");
							html.append("<option value=\"\"></option>");
							for (String[] fslrdj : fslrdjArr) {
								if (rs[j].equals(fslrdj[0])) {
									html.append("<option value=\"" + fslrdj[0] + "\" selected=\"selected\">" + fslrdj[0] + "</option>");
								} else {
									html.append("<option value=\"" + fslrdj[0] + "\">" + fslrdj[0] + "</option>");
								}
							}
							html.append("</select>");
						} else {
							//----�Ǽ�---
							html.append("<select onblur='checkIn(this)' style=\"width: 80px\">");
							html.append("<option value=\"\"></option>");
							for (String[] fslrdj : fslrxjArr) {
								if (rs[j].equals(fslrdj[0])) {
									html.append("<option value=\"" + fslrdj[0] + "\" selected=\"selected\">" + fslrdj[0] + "</option>");
								} else {
									html.append("<option value=\"" + fslrdj[0] + "\">" + fslrdj[0] + "</option>");
								}
							}
							html.append("</select>");
						}
					} else if(j == rs.length - 1){
						if("0".equals(jclx)){
							html.append("<input id='bz_" + i + "'" + " value=\"" + rs[j] + "\" style='width:80px' maxlength=\"50\" onblur='checkBz(this)'/>");
						}else{							
							html.append("<input id='bz_" + i + "'" + " value=\"" + rs[j] + "\" maxlength=\"50\" onblur='checkBz(this)'/>");
						}
					}else {
						if(j == rs.length-3 && "0".equals(jclx)){
													
						}else{							
							html.append(replaceHtml(rs[j]));
						}
					}
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	
	/**
	 * 
	 * @����:
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2017-8-11 ����09:31:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @param rcid
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * String �������� 
	 * @throws
	 */
	public String createSearchHTMLForTjjdzyjsxy(SearchRsModel rsModel,List<String[]> rsArrList, User user,String rcid)
	throws IllegalArgumentException, SecurityException,IllegalAccessException, InvocationTargetException,NoSuchMethodException {
		FslrDAO dao = new FslrDAO();
		List<String[]> fslrdjArr = dao.getFslrDj();
		List<String[]> fslrxjArr = dao.getFslrXj();
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		
		HashMap<String,String> jcrc = new FslrDAO().getFslrCz2(null,rcid);
		String jclx = jcrc.get("jclx");
		
		if (StringUtil.isNull(jclx)){
			jclx = GyglNewInit.JFFS;
		}
		
		
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" id=\"tr"+i+"\" ");
				if(rs[7]!=""){
					html.append(" style=\"background:rgb(255,222,173)\" ");
				}
				html.append(" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					if(jclx.equals("0") && j == rs.length-3){
						html.append("<td align=\"left\" nowrap=\"nowrap\" style='width:60px'");
					}else{						
						html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 2) + "%\" ");					
					}
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					if(j == rs.length-3){
						if("0".equals(jclx)){
							html.append("<input id='fz_" + i + "'" + " value=\"" + rs[j] + "\" style='width:50px' readonly='true'/>");
						}						
					}
					if (j == rs.length - 2) {
						if ("0".equals(jclx)) {//����Ϊ����
							html.append("<input id='kfyj_" + i + "'" + " value=\"" + rs[j] + "\" maxlength=\"200\" style='width:260px' onclick='kfmx(this)' title=\"" + rs[j] + "\" readonly='true'/>");
						} else if("1".equals(jclx)){
							//----�ȼ�---
							html.append("<select onblur='checkIn(this)' style=\"width: 80px\">");
							html.append("<option value=\"\"></option>");
							for (String[] fslrdj : fslrdjArr) {
								if (rs[j].equals(fslrdj[0])) {
									html.append("<option value=\"" + fslrdj[0] + "\" selected=\"selected\">" + fslrdj[0] + "</option>");
								} else {
									html.append("<option value=\"" + fslrdj[0] + "\">" + fslrdj[0] + "</option>");
								}
							}
							html.append("</select>");
						} else {
							//----�Ǽ�---
							html.append("<select onblur='checkIn(this)' style=\"width: 80px\">");
							html.append("<option value=\"\"></option>");
							for (String[] fslrdj : fslrxjArr) {
								if (rs[j].equals(fslrdj[0])) {
									html.append("<option value=\"" + fslrdj[0] + "\" selected=\"selected\">" + fslrdj[0] + "</option>");
								} else {
									html.append("<option value=\"" + fslrdj[0] + "\">" + fslrdj[0] + "</option>");
								}
							}
							html.append("</select>");
						}
					} else if(j == rs.length - 1){
						if("0".equals(jclx)){
							html.append("<input id='bz_" + i + "'" + " value=\"" + rs[j] + "\" style='width:80px' maxlength=\"50\" onblur='checkBz(this)'/>");
						}else{							
							html.append("<input id='bz_" + i + "'" + " value=\"" + rs[j] + "\" maxlength=\"50\" onblur='checkBz(this)'/>");
						}
					}else {
						if(j == rs.length-3 && "0".equals(jclx)){
													
						}else{							
							html.append(replaceHtml(rs[j]));
						}
					}
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	public String checkJcrq(String jcrckssj, String jcrcjssj, String jcrq) {
		long kssj = Long.parseLong(jcrckssj);
		long jssj = Long.parseLong(jcrcjssj);
		long jcrqsj = Long.parseLong(jcrq);
		if(jcrqsj<kssj || jcrqsj>jssj){
			return "������ڲ��ڼ���ճ�ʱ�䷶Χ�ڣ�";
		}
		return "������ڿ��ã�";
	}

	public String importData(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO �Զ����ɷ������
		FslrDAO dao = new FslrDAO();
		return dao.importData(request,response);
	}
	
	/**
	 * 
	 * @����: �㽭��ý���Ի�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-6-28 ����07:26:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @param rcid
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * String �������� 
	 * @throws
	 */
	public String createSearchHTML2ForZjCm(SearchRsModel rsModel,List<String[]> rsArrList, User user,String rcid)
	throws IllegalArgumentException, SecurityException,IllegalAccessException, InvocationTargetException,NoSuchMethodException {
	FslrDAO dao = new FslrDAO();
	List<String[]> fslrdjArr = dao.getFslrDj();
	List<String[]> fslrxjArr = dao.getFslrXj();
	StringBuilder html = new StringBuilder();
	String ie = rsModel.getIe();
	
	HashMap<String,String> jcrc = new FslrDAO().getFslrCz2(null,rcid);
	String jclx = jcrc.get("jclx");
	
	if (StringUtil.isNull(jclx)){
		jclx = GyglNewInit.JFFS;
	}


	if (rsArrList != null && rsArrList.size() > 0) {
		for (int i = 0; i < rsArrList.size(); i++) {
		String[] rs = rsArrList.get(i);
		html.append("<tr onclick=\"rowOnClick(this);\" id=\"tr"+i+"\" ");
		if(rs[7]!=""){
			html.append(" style=\"background:rgb(255,222,173)\" ");
		}
		html.append(" ondblclick=\"\">");
		html.append("<td style=\"width:5px\">");
		html.append("<input type='checkbox' onclick='checkIss(this)' name='div_pkValue'  ");
		html.append("  id='pkValue_" + i + "' ");
		html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
		html.append("</td>");
		// --------------------����HTML��չ�ֶ����������------------------------
		for (int j = 1; j < rs.length; j++) {
			html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 2) + "%\" ");
			// IE8
			if ("5.8".equalsIgnoreCase(ie)) {
				html.append("height=\"28.5px\"");
			} else {
				html.append("height=\"29px\"");
			}
//			if (j == 6) {
//				html.append(" title=\"" + rs[7] + "\" ");
//			}
			html.append(">");
			if (j == rs.length - 2) {
				if ("0".equals(jclx)) {
					html.append("<input onblur='checkIs(this)' id='fz_" + i + "'" + " value=\"" + rs[j] + "\" onkeyup=\"if(event.keyCode==13) {skipNext(this)}\"/>");
				} else if("1".equals(jclx)){
					//----�ȼ�---
					html.append("<select onblur='checkIn(this)' style=\"width: 80px\">");
					html.append("<option value=\"\"></option>");
					for (String[] fslrdj : fslrdjArr) {
						if (rs[j].equals(fslrdj[0])) {
							html.append("<option value=\"" + fslrdj[0] + "\" selected=\"selected\">" + fslrdj[0] + "</option>");
						} else {
							html.append("<option value=\"" + fslrdj[0] + "\">" + fslrdj[0] + "</option>");
						}
					}
					html.append("</select>");
				} else {
					//----�Ǽ�---
					html.append("<select onblur='checkIn(this)' style=\"width: 80px\">");
					html.append("<option value=\"\"></option>");
					for (String[] fslrdj : fslrxjArr) {
						if (rs[j].equals(fslrdj[0])) {
							html.append("<option value=\"" + fslrdj[0] + "\" selected=\"selected\">" + fslrdj[0] + "</option>");
						} else {
							html.append("<option value=\"" + fslrdj[0] + "\">" + fslrdj[0] + "</option>");
						}
					}
					html.append("</select>");
				}
			} else if(j == rs.length - 1){
				
				html.append("<input id='bz_" + i + "'" + " value=\"" + rs[j] + "\" maxlength=\"50\" onblur='checkBz(this)'/>");
			}else {
				if("11647".equals(Base.xxdm) && j == rs.length-3){
					html.append(replaceHtml(rs[j])+"<input type='hidden' name='byqs' value='"+rs[j]+"'>");
				}else{
					html.append(replaceHtml(rs[j]));
				}
				
			}
			html.append("</td>");
		}
		html.append("</tr>");
		}
		}
		return html.toString();
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:�㽭��ý���Ի��޸��Ƿ��ҵ�����ֶ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-6-30 ����03:11:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkvalue
	 * @param byqs
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateByqsForZjCm(String pkvalue,String[] byqs) throws Exception{
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < byqs.length; i++) {
			String[] temp = new String[]{byqs[i].split("!!@@")[1],pkvalue,byqs[i].split("!!@@")[0]};
			params.add(temp);
		}
		return new FslrDAO().updateByqsForZjCm(params);
	}
	
	public List<HashMap<String, String>>  searchForZjcmTjCx(FslrForm t, User user) 
		throws Exception{
		return new FslrTjcxDAO().getPageList(t, user);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��򾭼�ó��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-10-16 ����03:33:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @param rcid
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * String �������� 
	 * @throws
	 */
	public String createSearchHTML2Tjjm(SearchRsModel rsModel,List<String[]> rsArrList, User user,String rcid)
	throws Exception {
		FslrDAO dao = new FslrDAO();
		List<String[]> fslrdjArr = dao.getFslrDj();
		List<String[]> fslrxjArr = dao.getFslrXj();
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		List<HashMap<String,String>>  pfbzList = dao.getFzBz();
		HashMap<String,String> jcrc = new FslrDAO().getFslrCz2(null,rcid);
		String jclx = jcrc.get("jclx");
		
		if (StringUtil.isNull(jclx)){
			jclx = GyglNewInit.JFFS;
		}
		
		
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" id=\"tr"+i+"\" ");
				if(rs[7]!=""){
					html.append(" style=\"background:rgb(255,222,173)\" ");
				}
				html.append(" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' onclick='checkIss(this)' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 /7 + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
		//			if (j == 6) {
		//				html.append(" title=\"" + rs[7] + "\" ");
		//			}
					html.append(">");
					if (j == rs.length - 2) {
						if ("0".equals(jclx)) {
							if(StringUtils.isNull(rs[j])){
								rs[j] = cshfs;
							}
							html.append("<input style='width:70%' onblur='checkIs(this)' id='fz_" + i + "'" + " value=\"" + rs[j] + "\" onkeyup=\"if(event.keyCode==13) {skipNext(this)}\"/>");
						} else if("1".equals(jclx)){
							//----�ȼ�---
							html.append("<select onblur='checkIn(this)' style=\"width: 80px\">");
							html.append("<option value=\"\"></option>");
							for (String[] fslrdj : fslrdjArr) {
								if (rs[j].equals(fslrdj[0])) {
									html.append("<option value=\"" + fslrdj[0] + "\" selected=\"selected\">" + fslrdj[0] + "</option>");
								} else {
									html.append("<option value=\"" + fslrdj[0] + "\">" + fslrdj[0] + "</option>");
								}
							}
							html.append("</select>");
						} else {
							//----�Ǽ�---
							html.append("<select onblur='checkIn(this)' style=\"width: 80px\">");
							html.append("<option value=\"\"></option>");
							for (String[] fslrdj : fslrxjArr) {
								if (rs[j].equals(fslrdj[0])) {
									html.append("<option value=\"" + fslrdj[0] + "\" selected=\"selected\">" + fslrdj[0] + "</option>");
								} else {
									html.append("<option value=\"" + fslrdj[0] + "\">" + fslrdj[0] + "</option>");
								}
							}
							html.append("</select>");
						}
					} else if(j == rs.length - 1){
						if(pfbzList != null && pfbzList.size() >0){
							html.append("<input type='checkbox' name='allsel' onclick='selectOrCancelAll(this)'  />ȫѡ/��ѡ<br/>");
							for (int j2 = 0; j2 < pfbzList.size(); j2++) {
								if (rs[j].contains(pfbzList.get(j2).get("kfdm"))) {
									html.append("<input type='checkbox' name='pfbz' onclick='selectBz(this)' value='"+pfbzList.get(j2).get("kfdm")+"-"+pfbzList.get(j2).get("kffz")+"' checked='checked' />"+pfbzList.get(j2).get("kfmc")+"<br/>");
								}else{
									html.append("<input type='checkbox' name='pfbz' onclick='selectBz(this)' value='"+pfbzList.get(j2).get("kfdm")+"-"+pfbzList.get(j2).get("kffz")+"'  />"+pfbzList.get(j2).get("kfmc")+"<br/>");
								}
							}
						}
					}else {
						html.append(replaceHtml(rs[j]));
					}
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * @throws Exception  
	 * @����:ѧ������������¼��(��������ְҵ����ѧԺ)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-8 ����10:23:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param pkValue
	 * @param valArr
	 * @param bzStr
	 * @param username
	 * @param sfsdj
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveXsFslr(FslrForm model, String pkValue, String[] valArr,
			String[] bzStr, String username, String sfsdj,String rcmc) throws Exception {
		FslrDAO dao = new FslrDAO();
		return dao.saveXsFslr(model, pkValue, valArr, bzStr, username, sfsdj,rcmc);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-9 ����04:31:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkValue
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getRcmcById(String id) {
		FslrDAO dao = new FslrDAO();
		return dao.getRcmcById(id);
	}
}