package xsgzgl.pjpy.general.zhcp;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ۺϲ���_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyZhcpService  extends CommService implements PjpyZhcpInterface{

	PjpyZhcpDAO dao = new PjpyZhcpDAO();
	/**
	 * ��ʼ���۲���Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getCshXmList(User user) {
		
		List<HashMap<String, String>>cshXmList=dao.getCshXmList(user);
		
		return cshXmList;
		
	}

	/**
	 * ��ñ�ͷ��Ϣ(�ۺϲ���_�۲���Ϣ)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getZhcpZcxxTop(PjpyZhcpModel model,
			User user) {

		// ��������Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// �۲�����
		String zczq = jbszModel.getZczq();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ����
		String xmmc = model.getXmmc();
		// ��Դ��
		String lyb =model.getLyb();
		// ��Ŀ����
		String xmjb=model.getXmjb();

		// ��չ�ֶ��б�
		List<HashMap<String, String>> kzzdList = null;
		
		//�۲�����
		List<HashMap<String, String>> zczxList = null;

		// �۲���չ��Ϣ
		kzzdList = dao.getKzzdList(xmdm, user);
		
		// ��ȡ�۲�������Ϣ
		zczxList = dao.getZczxList(xmdm, user);

		String[] en = new String[] { "xh", "xm", "nj", "bjmc" };
		String[] cn = new String[] { "ѧ��", "����", "�꼶", "�༶����" };

		ArrayList<String> enList = new ArrayList<String>(Arrays.asList(en));
		ArrayList<String> cnList = new ArrayList<String>(Arrays.asList(cn));
		
		// ----------------�۲����� begin-----------------
		if (zczxList != null && zczxList.size() > 0) {

			for (int i = 0; i < zczxList.size(); i++) {
				
				
					HashMap<String, String> map = zczxList.get(i);
					// ����Ŀ����
					String zxmdm = map.get("xmdm");
					// ����Ŀ��
					String zxmmc = map.get("xmmc");
	
					enList.add(zxmdm);
					cnList.add(zxmmc);
				
			}
		}
		// ----------------�۲����� end-----------------
		
		if (kzzdList != null && kzzdList.size() > 0) {

			for (int i = 0; i < kzzdList.size(); i++) {
				
				
					HashMap<String, String> map = kzzdList.get(i);
					// ��չ�ֶ�
					String kzzd = map.get("kzzd");
					// ��ʾ����
					String xsmc = map.get("xsmc");
	
					enList.add(kzzd);
					cnList.add(xsmc);
				
			}
		}

		en = enList.toArray(new String[] {});
		cn = cnList.toArray(new String[] {});

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ѯ�����(�ۺϲ���_�۲���Ϣ)
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getZhcpZcxxList(PjpyGeneralForm myForm,
			PjpyZhcpModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ��������Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// �۲�����
		String zczq = jbszModel.getZczq();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ����
		String xmmc = model.getXmmc();

		// ��չ�ֶ��б�
		List<HashMap<String, String>> kzzdList = null;
		
		// �۲�����
		List<HashMap<String, String>> zczxList = null;

		// �۲���չ��Ϣ
		kzzdList = dao.getKzzdList(xmdm, user);
		// ��ȡ�۲�������Ϣ
		zczxList = dao.getZczxList(xmdm, user);

		ArrayList<String[]> list = dao.getZhcpZcxxList(myForm, model, user,
				kzzdList,zczxList);
		// ��չ��Ϣ
		// List<HashMap<String, String>> kzxxList =new
		// ArrayList<HashMap<String,String>>();

		return list;
	}

	/**
	 * ���������(�ۺϲ���_�۲���Ϣ)
	 * 
	 * @author ΰ�����
	 */
	public String createZhcpZcxxHTML(SearchRsModel rsModel,
			PjpyZhcpModel model, ArrayList<String[]> rsArrList, User user) {
		
		BasicService service =new BasicService();
		// ��������Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// IE�汾
		String ie = rsModel.getIe();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// �۲�����
		String zczq = jbszModel.getZczq();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Դ��
		String lyb = model.getLyb();
		// ��Ŀ����
		String xmjb = model.getXmjb();
		
		StringBuilder html = new StringBuilder();

		List<HashMap<String, String>> kzzdList = null;
		
		List<HashMap<String, String>> zczxList = null;
		// ��չ�ֶ���
		int KzzdLen = 0;
		
		// �۲���չ��Ϣ
		kzzdList = dao.getKzzdList(xmdm, user);
		// ��ȡ�۲�������Ϣ
		zczxList = dao.getZczxList(xmdm, user);

		if (kzzdList != null && kzzdList.size() > 0) {
			KzzdLen = kzzdList.size();
		}

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String xh = rs[0];// ѧ��
				String fs = rs[4];// ����

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"display:none\">");
				html.append("<input type='hidden' name='div_pkValue'  style='width:80px' ");
				html.append("  id='pkValue_"+i+"' ");
				html.append(" value='" + service.replaceHtml(xh) + "'/> ");	
				html.append("</td>");
				
				
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 0; j < 4; j++) {
					if(j==0){
						html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
						// IE8
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}
						html.append(">");
						html.append("<a  class=\"name\" href=\"#\" ");
						html.append("onclick=\"showXsxxDetail('" + rs[j] + "')\" ");
						html.append(">");
						html.append("<font color=\"blue\">");
						html.append(service.replaceHtml(rs[j]));
						html.append("</font>");
						html.append("</a>");
						html.append("</td>");
					}else{
						html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
						// IE8
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}
						html.append(">");
						html.append(service.replaceHtml(rs[j]));
						html.append("</td>");
					}
				}
				
				for(int j=0;j<zczxList.size();j++){
					
					HashMap<String,String>zczxMap=zczxList.get(j);
					
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\">");
					
					String result = rs[5 + j];
					String result_fs = result.split("luojw")[0];
					String result_lyb = result.split("luojw")[1];
					
					if(!Base.isNull(zczxMap.get("lyb"))){
						html.append(service.replaceHtml(result_fs));
					}else{
						html.append("<input type='text'   style='width:50px' ");
						html.append("  id='"+zczxMap.get("xmdm")+"_" + i + "' ");
						html.append("  name='"+zczxMap.get("xmdm")+"' ");
						html.append(" onkeyup=\"checkInputNum(this);setHadEdit();\" ");
						html.append(" maxlength=\"5\" ");
						html.append(" onblur=\"checkInputNum(this)\" ");
						html.append(" value='" + service.replaceHtml(result_fs) + "'/> ");
					}
					html.append("</td>");
					
				}
			
				// ---------------��չ�ֶ�begin-----------------------
				for (int j = 0; j < KzzdLen; j++) {
					HashMap<String, String> kzzdMap = kzzdList.get(j);
					String kzzd = kzzdMap.get("kzzd");
					String checked = kzzdMap.get("checked");
					// ��������
					String maxlength = "";
					// �¼�
					String event = "";
					// ���ݱ�
					String source_table = "";
					// ���������
					String select_dm = kzzdMap.get("select_dm");
					// ����������
					String select_mc = kzzdMap.get("select_mc");

					if (!Base.isNull(checked)) {
						// �ı���󳤶�
						maxlength = checked.split("!!luojw!!")[0];
						// �Ƿ����¼�����
						event = checked.split("!!luojw!!")[1];
					}

					// --------����������롢���ƹ���Ϊ���� begin--------
					String[] dm = null;
					String[] mc = null;
					if (!Base.isNull(select_dm)) {
						dm = select_dm.split("!!luojw!!");
						mc = select_mc.split("!!luojw!!");
					}
					// --------����������롢���ƹ���Ϊ���� end--------

					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");

					if ("text".equalsIgnoreCase(kzzdMap.get("zdlx"))) {// text�ı������
						html.append("<input type='text' name='"+kzzd+"' ");
						html.append("  id='"+kzzd +"_"+i
								+ "' ");
						html.append(" maxlength='" + maxlength + "' ");
						html.append(" value='" + service.replaceHtml(rs[zczxList.size()+j + 5])
								+ "' style='width:80px'/> ");
					} else if ("select".equalsIgnoreCase(kzzdMap.get("zdlx"))) {// select���������
						
						html.append("<select  name='"+kzzd+"'  ");
						html.append("  id='" + kzzd + "_" + i
								+ "' > ");
						html.append("<option value=''></option>");
						for (int z = 0; z < dm.length; z++) {
							html.append("<option value='"+dm[z]+"' ");
							if(rs[j + 5].equalsIgnoreCase(dm[z])){
								
								html.append(" selected='true' ");
									
							}
							html.append(" >" + mc[z] + "</option>");
						
						}
						html.append(" </select> ");
					} else if ("textArea".equalsIgnoreCase(kzzdMap.get("zdlx"))) {// text�ı������
						html.append("");
						html.append("<input type='hidden' name='" + kzzd
										+ "' ");
						html.append("  id='" + kzzd + "_" + i
								+ "' ");
						html.append(" maxlength='" + maxlength + "'  ");
						html.append(" value='" + service.replaceHtml(rs[zczxList.size()+j + 5]) + "'/> ");
						html.append(" <a href='#' title='����ɲ鿴���޸���Ϣ' onclick=\"showEditDiv('"
										+ kzzd
										+ "','"
										+ kzzdMap.get("kzzd")
										+ "_" + i + "');return false;\"><font color=\"blue\">��ϸ</font></a>");
					}

					html.append("</td>");
					
				}
				
				html.append("</tr>");
			}
		}
		
		return html.toString();

	}

	/**
	 * ��ʾ�ֶ�����Div
	 * 
	 * @author qlj
	 * 
	 * @throws IOException
	 */
	public void showZdxgDiv(String zd, String zdid, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		// �ֶ��б�
		List<HashMap<String, String>> zdList = dao.getZdszList(zd);

		HashMap<String, String> zdInfo = new HashMap<String, String>();

		if (zdList != null && zdList.size() > 0) {
			zdInfo = zdList.get(0);
		}

		// �ֶ���
		String xsmc = zdInfo.get("xsmc");
		// �ֶ�����
		String zdlx = zdInfo.get("zdlx");
		// �ֶ���֤
		String checked = zdInfo.get("checked");
		// ���ݱ�
		String source_table = zdInfo.get("source_table");
		// �����
		String select_dm = zdInfo.get("select_dm");
		// ������
		String select_mc = zdInfo.get("select_mc");
		
		String length=checked.split("!!luojw!!")[0];

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>��Ϣ�޸�</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append(xsmc);
		html.append("</th>");
		html.append("<td>");

		if ("textArea".equalsIgnoreCase(zdlx)) {// �ı���

			html.append(" <textArea name=\"" + zd + "_name\" id=\"" + zd
					+ "_id\" ");
			html.append(" rows='4' style='word-break:break-all;width:96%' onblur=\"chLeng(this,"+length+")\" ;>  ");
			html.append(" </textArea>");
		}

		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"affirmValue('" + zd
				+ "','" + zdid + "');return false;\">");
		html.append("ȷ ��");
		html.append("</button>");

		html.append("<button type=\"button\"  id=\"btn_gb\" onclick=\"closeWindown();return false;\">");
		html.append("�� ��");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");

		html.append("</table>");
		html.append("</div>");

		response.getWriter().print(html.toString());
	}

	/**
	 * ��ȡ��չ�ֶ���Ϣ
	 */
	public List<HashMap<String, String>> getKzzdList(PjpyGeneralForm model,
			User user) {
		// ��������Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// �۲�����
		String zczq = jbszModel.getZczq();
		// ��Ŀ����
		String xmdm = model.getXmdm();

		// ��չ�ֶ��б�
		List<HashMap<String, String>> kzzdList = null;
		// �۲���չ��Ϣ
		kzzdList = dao.getKzzdList(xmdm, user);

		return kzzdList;
	}
	
	/**
	 * ��ȡ��չ�ֶ���Ϣ
	 */
	public List<HashMap<String, String>> getZczxList(PjpyGeneralForm model,
			User user) {
		// ��������Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// �۲�����
		String zczq = jbszModel.getZczq();
		// ��Ŀ����
		String xmdm = model.getXmdm();

		// ��չ�ֶ��б�
		List<HashMap<String, String>> zczxList = null;
		// ��ȡ�۲�������Ϣ
		zczxList = dao.getZczxList(xmdm, user);

		return zczxList;
	}
	
	/**
	 * ��ȡ��չ�ֶ���Ϣ
	 */
	public List<HashMap<String, String>> getDdwhList(PjpyGeneralForm model,
			User user) {
		
		// ��Ŀ����
		String xmdm = model.getXmdm();

		// ��չ�ֶ��б�
		List<HashMap<String, String>> ddwhxmList = null;
		// ����ά����Ŀ�б�
		ddwhxmList = dao.getDdwhList(xmdm, user);
		
		return ddwhxmList;
	}

	/**
	 * ����ֶ������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getZdszList(String zd, User user) {
		return dao.getZdszList(zd);
	}

	public String createZhcpMaintainRs(SearchRsModel rsModel,
			PjpyGeneralForm model, ArrayList<String[]> rsArrList, User user) {
		// TODO �Զ����ɷ������
		return null;
	}

	public ArrayList<String[]> getZhcpMaintainInfo(PjpyGeneralForm model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// TODO �Զ����ɷ������
		return null;
	}

	public List<HashMap<String, String>> getZhcpMaintainTop(
			PjpyGeneralForm model, User user) {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * �����ۺϲ�����Ϣ
	 */
	public boolean saveZhcpInfo(PjpyGeneralForm model,HttpServletRequest request, User user) {
		
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		//
		String pjzq = jbszModel.getPjzq();

		// ��ǰҳ��ѧ������(��ɾ��������ҳ��Ϣɾ��ʱ)
		String xh =request.getParameter("pkValue");
		String fs=request.getParameter("fs");

		//��Ŀ����
		String xmdm=request.getParameter("xmdm");
		model.setXmdm(xmdm);
		model.setPjxn(pjxn);
		model.setPjxq(pjxq);
		
		
		// ------------------��չ�ֶ� begin--------------------------
		List<HashMap<String,String>>kzzdList=getKzzdList(model, user);
		
		String[]kzzdInfo=new String[kzzdList.size()];
		for(int i=0;i<kzzdList.size();i++){
			
			HashMap<String,String>kzzdMap=kzzdList.get(i);
			String kzzd=kzzdMap.get("kzzd");
			String kzzdValue=Base.isNull(request.getParameter(kzzd))?"":request.getParameter(kzzd);
			kzzdInfo[i]=unicode2Gbk(kzzdValue);
			
		}   
		// ------------------��չ�ֶ� end--------------------------

		// ------------------��չ�ֶ� begin--------------------------
		List<HashMap<String,String>>zczxList=dao.getBczdList(xmdm, user);
		
		String[]zczxInfo=new String[zczxList.size()];
		for(int i=0;i<zczxList.size();i++){
			
			HashMap<String,String>zczxMap=zczxList.get(i);
			String zxmdm=zczxMap.get("xmdm");
			String zxmdmValue=request.getParameter(zxmdm);
			zczxInfo[i]=unicode2Gbk(zxmdmValue);
		}   
		// ------------------��չ�ֶ� end--------------------------
		
		boolean flag=false;
		if("xq".equalsIgnoreCase(pjzq)){
			
			flag=dao.saveZhcpByXq(kzzdInfo,zczxInfo,xh.split("!!@@!!"), model,user);
		}else if("xn".equalsIgnoreCase(pjzq)) {
			
			flag=dao.saveZhcpByXn(kzzdInfo, zczxInfo,xh.split("!!@@!!"), model,user);
		}
		
		return flag;
	}
	
	public String createKidneyDiv(SearchRsModel rsModel, PjpyZhcpModel model,
			ArrayList<String[]> rsArrList, User user) {

		// ��������Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// IE�汾
		String ie = rsModel.getIe();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// �۲�����
		String zczq = jbszModel.getZczq();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		StringBuilder html = new StringBuilder();
		
		jbszModel.setXmdm(xmdm);
		HashMap<String,String>butMap=getButMap(jbszModel, user);

		List<HashMap<String, String>> kzzdList = null;
		// �۲���չ��Ϣ
		kzzdList = dao.getKzzdList(xmdm, user);
		
		List<HashMap<String, String>> zczxList = null;
		// ��ȡ�۲�������Ϣ
		zczxList = dao.getZczxList(xmdm, user);
		// �۲Ᵽ���ֶ�
		List<HashMap<String,String>>bczdList=dao.getBczdList(xmdm, user);
		
		
		html.append(" <div name='div_kidneyDiv'>");
		
		// -----------------��չ�ֶ� begin-----------------------
		for(int i=0;i<kzzdList.size();i++){
			HashMap<String,String>kzzdMap=kzzdList.get(i);
			html.append(" <input type='hidden' name='kzzdArr' value="+kzzdMap.get("kzzd")+" />");
		}
		// -----------------��չ�ֶ� end-----------------------
		
		// -----------------�۲����� begin-----------------------
		for(int i=0;i<zczxList.size();i++){
			HashMap<String,String>zczxMap=zczxList.get(i);
			html.append(" <input type='hidden' name='zczxArr' value="+zczxMap.get("xmdm")+" />");
		}
		
		// �����ֶ���Ϣ
		for(int i=0;i<bczdList.size();i++){
			HashMap<String,String>bczdMap=bczdList.get(i);
			html.append(" <input type='hidden' name='bczdArr' value="+bczdMap.get("xmdm")+" />");
		}
		// -----------------�۲����� end-----------------------
		
		html.append("<input type=\"hidden\" name =\"hid_lrf\" id=\"hid_lrf\" value=\""+butMap.get("lrf")+"\"/> ");
		html.append("<input type=\"hidden\" name =\"hid_lyf\" id=\"hid_lyf\" value=\""+butMap.get("lyf")+"\"/> ");
		
		html.append(" </div> ");
		
		return html.toString();
	}
	
	/**
	 * �ۺϷּ���
	 * �۲�֡��۲�������������������
	 * @throws Exception 
	 */
	public boolean account(PjpyGeneralForm myForm, User user) throws Exception {
		
		boolean flag=false;
		
		//�۲��������(�۲�֡��Լ�ϵ��ּ���)
		flag = zcxmjs(myForm, user);
		
		if (flag) {
			//�۲��ܷ�����
			flag =getPlace(myForm, user);
		}
				
		if(flag){
			//��������������
			flag = getZyPlace(myForm, user);
		}
		
		if(flag){
			//��������������
			flag = getDyPlace(myForm, user);
		}
		
		return flag;
	}
	
	
	/**
	 * �۲���Ŀ�ּ���
	 * �۲��ܷ֡��۲�ϵ��ּ���
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean zcxmjs(PjpyGeneralForm model,User user) throws Exception {
		
		boolean blog=true;

		// =============������Ŀ����Ŀ�ּ��� begin===================
		List<HashMap<String,String>>zcjsList=dao.getZcjsSql(model, user);
		// =============������Ŀ����Ŀ�ּ��� end===================
		
		// -----------ִ�� begin---------------
		blog=zcxmfjs(zcjsList);
		// -----------ִ�� end---------------
		
		return blog;
	}
	
	/**
	 * �����۲�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean getPlace(PjpyGeneralForm model,User user) throws Exception{
		
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// ������ѡ����������㷽ʽ��������
		String zcpm = jbszModel.getZcpm();
		
		boolean blog=false;
		
		// ����������
		if("0".equalsIgnoreCase(zcpm)){
			blog=cpzpmjs(model,user);
		}else{
		
			//�꼶ѧԺ����
			blog=xypmjs(model,user);
		
			//�꼶רҵ����
			blog=zypmjs(model,user);
			
			//�༶����
			blog=bjpmjs(model,user);
		}
		
		return blog;
	}
	
	/**
	 * ������������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean getZyPlace(PjpyGeneralForm model,User user) throws Exception{

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// ������ѡ����������㷽ʽ��������
		String zypm = jbszModel.getZypm();
		
		boolean blog=false;
		
		
		if("0".equalsIgnoreCase(zypm)){
			blog=cpzZypmjs(model,user);
		}else {
		
			// �꼶ѧԺ����
			blog=xyZypmjs(model,user);
		
			//�꼶רҵ����
			blog=zyZypmjs(model,user);
		
			//�༶����
			blog=bjZypmjs(model,user);
		}
		
		return blog;
		
	}
	
	/**
	 * �����������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean getDyPlace(PjpyGeneralForm model,User user) throws Exception{

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// ������ѡ����������㷽ʽ��������
		String zypm = jbszModel.getZypm();
		
		boolean blog=false;
		
		
		if("0".equalsIgnoreCase(zypm)){
			blog=cpzZypmjs(model,user);
		}else {
		
//			// �꼶ѧԺ����
//			blog=xyZypmjs(model,user);
//		
//			//�꼶רҵ����
//			blog=zyZypmjs(model,user);
		
			//�༶����
			blog=bjDypmjs(model,user);
		}
		
		return blog;
		
	}
	
	/**
	 * �༶��������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean bjpmjs(PjpyGeneralForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.bjpmjs(model, user);
		if (flag) {
			flag = dao.updateBjpm(model, user);
		}
		return flag;
	}
	
	/**
	 * �꼶ѧԺ��������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean xypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.njxypmjs(model, user);
		if (flag) {
			flag = dao.updateXypm(model, user);
		}
		return flag;
	}
	
	/**
	 * ��������������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean cpzpmjs(PjpyGeneralForm model, User user) throws Exception {

		// ��������������
		boolean flag = dao.cpzpmjs(model, user);
		if (flag) {
			flag = dao.updateCpzpm(model, user);
		}
		return flag;
	}
	
	/**
	 * �꼶רҵ��������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean zypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.njzypmjs(model, user);
		if (flag) {
			flag = dao.updateZypm(model, user);
		}
		return flag;
	}
	
	/**
	 * �꼶ѧԺ��������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean xyZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.njxyZypmjs(model, user);
		if (flag) {
			flag = dao.updateNjXyZypm(model, user);
		}
		return flag;
	}
	

	/**
	 * �༶��������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean bjZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.bjZypmjs(model, user);
		if (flag) {
			flag = dao.updateBjZypm(model, user);
		}
		return flag;
	}
	
	/**
	 * �༶�������㡾�����֡�
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean bjDypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.bjDypmjs(model, user);
		if (flag) {
			flag = dao.updateBjDypm(model, user);
		}
		return flag;
	}
	
	/**
	 * �꼶רҵ��������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean zyZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.njzyZypmjs(model, user);
		if (flag) {
			flag = dao.updateNjZyZypm(model, user);
		}
		return flag;
	}
	
	/**
	 * ��������������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean cpzZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ��������������
		boolean flag = dao.cpzZypmjs(model, user);
		if (flag) {
			flag = dao.updateCpzZypm(model, user);
		}
		return flag;
	}

	public String createZhcpResultHTML(SearchRsModel rsModel, PjpyZhcpModel model, ArrayList<String[]> rsArrList, User user) {
		
		BasicService basicService=new BasicService();
		
		// IE�汾
		String ie = rsModel.getIe();
		
		StringBuilder html = new StringBuilder();
		
			if (rsArrList != null && rsArrList.size() > 0) {
	
				for (int i = 0; i < rsArrList.size(); i++) {
	
					String[] rs = rsArrList.get(i);
	
					html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
					
//					html.append("<td  align=\"left\" nowrap=\"nowrap\" style=\"width:8px\">");
//					
//					html.append("<input type='hidden' name='pkValue'   ");
//					html.append("  id='pkValue_"+i+"' ");
//					html.append(" value='" + rs[0] + "'/> ");	
//					
//					html.append("</td>");
					
					
					// --------------------����HTML��չ�ֶ����������------------------------
					for (int j = 0; j < rs.length; j++) {
						html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
						// IE8
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}
						html.append(">");
						html.append(basicService.replaceHtml(rs[j]));
						html.append("</td>");
					}
					
					html.append("</tr>");
				}
			}
			
			return html.toString();
	}

	public ArrayList<String[]> getZhcpResultList(PjpyGeneralForm myForm, PjpyZhcpModel model, User user) throws Exception{
		// TODO �Զ����ɷ������
		String[]colList=severTop(getZhcpResultTop(myForm,user),"en");
		
		return (ArrayList<String[]>)dao.getZhcpResultList(myForm, user, colList);
	}
	
	/**
	 * ����top�б� ���ݻ�ȡ���ͻ�ȡ��en,cn��
	 * 
	 * @param topList
	 * @param hqlx
	 * @return List<HashMap<String, String>>
	 */
	public String[] severTop(List<HashMap<String, String>> topList, String hqlx) {

		List<String> outList = new ArrayList<String>();
		for (int i = 0; i < topList.size(); i++) {
			HashMap<String, String> topMap = topList.get(i);
			outList.add(topMap.get(hqlx));
		}
		return outList.toArray(new String[] {});
	}

	public List<HashMap<String, String>> getZhcpResultTop( User user) {
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		String isCpz=jbszModel.getCpz();
		
		String zcpm=jbszModel.getZcpm();
		String zypm=jbszModel.getZypm();
		DAO commDao=DAO.getInstance();
		//��ȡ���ñ������õ��ֶΡ��ֶ�����
		List<HashMap<String,String>>colList=dao.getZhcpResultTop(user);
		//�ֶδ��루COLLIST��
		List<String>colArr=new ArrayList<String>();
		//�ֶ����ƣ�TOP��
		List<String>topArr=new ArrayList<String>();
		//��ȡ��չ�ֶ���Ϣ
		List<HashMap<String,String>>kzzdList=getKzzdList(user);
		
		colArr.add("xh");
		topArr.add("ѧ��");
		
		colArr.add("xm");
		topArr.add("����");
		
		colArr.add("nj");
		topArr.add("�꼶");
		
		colArr.add("xymc");
		topArr.add(Base.YXPZXY_KEY);
		
		colArr.add("zymc");
		topArr.add("רҵ");
		
		colArr.add("bjmc");
		topArr.add("�༶");
		
		// ������
		if("yes".equalsIgnoreCase(isCpz)){
			
			colArr.add("cpzmc");
			topArr.add("������");
		}
		
		for(int i=0;i<colList.size();i++){
			HashMap<String,String>outPutMap=colList.get(i);
			colArr.add(outPutMap.get("xmdm"));
			topArr.add(outPutMap.get("xmmc"));
		}
		
		for(int i=0;i<kzzdList.size();i++){
			HashMap<String,String>kzzdMap=kzzdList.get(i);
			colArr.add(kzzdMap.get("kzzd"));
			topArr.add(kzzdMap.get("xsmc"));
		}
		
		// ����������
		if("0".equalsIgnoreCase(zcpm)){
			colArr.add("cpzpm");
			topArr.add("�ۺϷֲ���������");
		}
		
		if("1".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjxypm");
			topArr.add("�ۺϷ��꼶"+Base.YXPZXY_KEY+"����");
		}
		
		if("2".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjzypm");
			topArr.add("�ۺϷ��꼶רҵ����");
		}
		
		if("3".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfbjpm");
			topArr.add("�ۺϷְ༶����");
		}
		
		if("0".equalsIgnoreCase(zcpm)){
			colArr.add("zyfcpzpm");
			topArr.add("�����ֲ���������");
		}
		
		if("1".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjxypm");
			topArr.add("�������꼶"+Base.YXPZXY_KEY+"����");
		}
		
		if("2".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjzypm");
			topArr.add("�������꼶רҵ����");
		}
		
		if("3".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfbjpm");
			topArr.add("�����ְ༶����");
		}
		
		//������
		if("0".equalsIgnoreCase(zypm)){
//			colArr.add("zyfcpzpm");
//			topArr.add("�����ֲ���������");
		}
		
		if("1".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("dyfnjxypm");
			topArr.add("�������꼶"+Base.YXPZXY_KEY+"����");
		}
		
		if("2".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("dyfnjzypm");
			topArr.add("�������꼶רҵ����");
		}
		
		if("3".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("dyfbjpm");
			topArr.add("�����ְ༶����");
		}
		
		return commDao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}
	
	public List<HashMap<String, String>> getZcResultTop(PjpyGeneralForm myForm, User user) {
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		String isCpz=jbszModel.getCpz();
		
		SearchModel searchModel=myForm.getSearchModel();
		
		String[]search_tj_zczq=searchModel.getSearch_tj_zczq();
		
		String tjxn = search_tj_zczq[0];
		String tjxq = search_tj_zczq[1];
		String tjnd = search_tj_zczq[2];
		
		String pjxn = jbszModel.getPjxn();	
		String pjxq = jbszModel.getPjxq();
		String pjnd = jbszModel.getPjnd();
		
		String zcpm=jbszModel.getZcpm();
		String zypm=jbszModel.getZypm();
		DAO commDao=DAO.getInstance();
		//��ȡ���ñ������õ��ֶΡ��ֶ�����
		List<HashMap<String,String>>colList=new ArrayList<HashMap<String,String>>();
		//�ֶδ��루COLLIST��
		List<String>colArr=new ArrayList<String>();
		//�ֶ����ƣ�TOP��
		List<String>topArr=new ArrayList<String>();
		//��ȡ��չ�ֶ���Ϣ
		List<HashMap<String,String>>kzzdList=getKzzdList(user);
		
		colArr.add("xh");
		topArr.add("ѧ��");
		
		colArr.add("xm");
		topArr.add("����");
		
		colArr.add("nj");
		topArr.add("�꼶");
		
		colArr.add("xymc");
		topArr.add(Base.YXPZXY_KEY);
		
		colArr.add("zymc");
		topArr.add("רҵ");
		
		colArr.add("bjmc");
		topArr.add("�༶");
		
		//  ������
		if("yes".equalsIgnoreCase(isCpz)){
			
			colArr.add("cpzmc");
			topArr.add("������");
		}
		
		if(tjxn.equalsIgnoreCase(pjxn) 
				&& tjxq.equalsIgnoreCase(pjxq) 
				&& tjnd.equalsIgnoreCase(pjnd) ){
			
			colList=dao.getZhcpResultTop(user);
			
			for(int i=0;i<colList.size();i++){
				HashMap<String,String>outPutMap=colList.get(i);
				colArr.add(outPutMap.get("xmdm"));
				topArr.add(outPutMap.get("xmmc"));
			}
			
			for(int i=0;i<kzzdList.size();i++){
				HashMap<String,String>kzzdMap=kzzdList.get(i);
				colArr.add(kzzdMap.get("kzzd"));
				topArr.add(kzzdMap.get("xsmc"));
			}
		}else{
			colList=dao.getZcxmByHistory(user);
		}
		
		// ����������
		if("0".equalsIgnoreCase(zcpm)){
			colArr.add("cpzpm");
			topArr.add("����������");
		}
		
		if("1".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjxypm");
			topArr.add("�꼶"+Base.YXPZXY_KEY+"����");
		}
		
		if("2".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjzypm");
			topArr.add("�꼶רҵ����");
		}
		
		if("3".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfbjpm");
			topArr.add("�༶����");
		}
		
		// ����������
		if("0".equalsIgnoreCase(zypm)){
			colArr.add("zyfcpzpm");
			topArr.add("�����ֲ���������");
		}
		
		if("1".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjxypm");
			topArr.add("�������꼶"+Base.YXPZXY_KEY+"����");
		}
		
		if("2".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjzypm");
			topArr.add("�������꼶רҵ����");
		}
		
		if("3".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfbjpm");
			topArr.add("�����ְ༶����");
		}
		return commDao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}
	
	/**
	 * ����۲���Ŀ��չ�ֶ���Ϣ�����������ڣ�
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getKzzdList(User user) {

		return dao.getKzzdList(user);
	}
	
	/**
	 * ��ȡ��ͷ��ʾ�ֶ�
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getZhcpResultTop(PjpyGeneralForm model, User user) {
		//��ȡָ���û���������ʾ��
		List<HashMap<String, String>> kindList = getCheckKind(model, user);
		List<HashMap<String, String>> top=getZhcpResultTop(user);
		List<HashMap<String, String>> topTr=new ArrayList<HashMap<String,String>>();
		HashMap<String, String>map=new  HashMap<String, String>();
		
		
		//ȡ����
		if (kindList != null && kindList.size() > 0) {
			for (int i = 0; i < kindList.size(); i++) {
				HashMap<String, String> kindMap = kindList.get(i);
				HashMap<String, String> topTrMap = new HashMap<String, String>();
				for (int j = 0; j < top.size(); j++) {
					HashMap<String, String> topMap = top.get(j);

					if (kindMap.get("zd").equalsIgnoreCase(topMap.get("en"))) {
						topTrMap.put("en", topMap.get("en"));
						topTrMap.put("cn", topMap.get("cn"));
						topTr.add(topTrMap);
						break;
					}
				}
				
			}
			
		}else{
			
			return top;
		}
		
		return topTr;
	}
	
	/**
	 * ��ȡָ���û���������ʾ��
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getCheckKind(PjpyGeneralForm model, User user) {
		// ��ȡָ���û�ѡ�е���
		HashMap<String, String> kind = dao.getCheckKind(model, user);
		List<HashMap<String, String>> checkKind = new ArrayList<HashMap<String, String>>();
		// ����ʾ�ֶ�
		String xszd = kind.get("xxszd");
		String[] xszdArr = null;
		if (!Base.isNull(xszd)) {
			xszdArr = xszd.split(",");
			for (int i = 0; i < xszdArr.length; i++) {
				HashMap<String, String> kindMap = new HashMap<String, String>();
				kindMap.put("zd", xszdArr[i]);
				checkKind.add(kindMap);
			}
		}
		return checkKind;
	}

	
	/**
	 * ��ȡ�۲��ģ����ѡ�ֶ�
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getKindChoose(PjpyGeneralForm model, User user) {
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		String isCpz=jbszModel.getCpz();
		
		String zcpm=jbszModel.getZcpm();
		String zypm=jbszModel.getZypm();
		DAO commDao=DAO.getInstance();
		//��ȡ���ñ������õ��ֶΡ��ֶ�����
		List<HashMap<String,String>>colList=dao.getZczfTop(model, user);
		//�ֶδ��루COLLIST��
		List<String>colArr=new ArrayList<String>();
		//�ֶ����ƣ�TOP��
		List<String>topArr=new ArrayList<String>();
		//��ȡ��չ�ֶ���Ϣ
		List<HashMap<String,String>>kzzdList=getKzzdList(user);
		// ------------------------Ĭ����ʾ�ֶ� begin------------------------------
		colArr.add("xh");
		topArr.add("ѧ��");
		
		colArr.add("xm");
		topArr.add("����");
		
		colArr.add("nj");
		topArr.add("�꼶");
		
		colArr.add("xymc");
		topArr.add(Base.YXPZXY_KEY);
		
		colArr.add("zymc");
		topArr.add("רҵ");
		
		colArr.add("bjmc");
		topArr.add("�༶");	

		//  ������
		if("yes".equalsIgnoreCase(isCpz)){
			
			colArr.add("cpzmc");
			topArr.add("������");
		}
		
		// ------------------------Ĭ����ʾ�ֶ� end------------------------------
		
		for(int i=0;i<colList.size();i++){
			HashMap<String,String>outPutMap=colList.get(i);
			colArr.add(outPutMap.get("xmdm"));
			topArr.add(outPutMap.get("xmmc"));
		}
		
		for(int i=0;i<kzzdList.size();i++){
			HashMap<String,String>kzzdMap=kzzdList.get(i);
			colArr.add(kzzdMap.get("kzzd"));
			topArr.add(kzzdMap.get("xsmc"));
		}
		
		// ---------------------�۲�������Ϣ ����flowControl.xml�ļ�����ȡ begin---------------------
		if("0".equalsIgnoreCase(zcpm)){
			colArr.add("cpzpm");
			topArr.add("�ۺϷֲ���������");
		}
		
		if("1".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjxypm");
			topArr.add("�ۺϷ��꼶"+Base.YXPZXY_KEY+"����");
		}
		
		
		
		if("2".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjzypm");
			topArr.add("�ۺϷ��꼶רҵ����");
		}
		
		if("3".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfbjpm");
			topArr.add("�ۺϷְ༶����");
		}
		
		if("0".equalsIgnoreCase(zypm)){
			colArr.add("zyfcpzpm");
			topArr.add("�����ֲ���������");
		}
		
		if("1".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjxypm");
			topArr.add("�������꼶"+Base.YXPZXY_KEY+"����");
		}
		
		if("2".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjzypm");
			topArr.add("�������꼶רҵ����");
		}
		
		if("3".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfbjpm");
			topArr.add("�����ְ༶����");
		}
		
		//������
		if("0".equalsIgnoreCase(zypm)){
//			colArr.add("zyfcpzpm");
//			topArr.add("�����ֲ���������");
		}
		
		if("1".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("dyfnjxypm");
			topArr.add("�������꼶"+Base.YXPZXY_KEY+"����");
		}
		
		if("2".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("dyfnjzypm");
			topArr.add("�������꼶רҵ����");
		}
		
		if("3".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("dyfbjpm");
			topArr.add("�����ְ༶����");
		}
		// ---------------------�۲�������Ϣ ����flowControl.xml�ļ�����ȡ end---------------------
		
		return commDao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}
	
	
	/**
	 * ������ѡ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveKindChoose(PjpyZhcpModel model,User user) throws Exception{
	
		DAO commDao=DAO.getInstance();
		String yhm=user.getUserName();
		String path="pjpy_general_zhcp_result.do";
		String yhlx="tea";
		StringBuilder xszd=new StringBuilder();
		
		String []bxszd={"xh","xm"};
		String []xszdArr=null;
		if("stu".equalsIgnoreCase(user.getUserType())){
			yhlx="stu";
		}
		
		xszdArr=commDao.unionArray(bxszd, model.getXszdArr());
		for(int i=0;i<xszdArr.length;i++){
			if(i!=0){
				xszd.append(",");
			}
			xszd.append(xszdArr[i]);
		}
		
		return checkBoolean(dao.saveKindChoose(yhm, yhlx, xszd.toString(), path));
	}
	
	/**
	 * �ж����������Ƿ�ɹ�
	 * @param returnV
	 * @return
	 */
	public boolean checkBoolean(int []returnV){
		boolean blog=true;
		for(int i=0;i<returnV.length;i++){
			if(returnV[i]==0){
				blog=false;
			}
		}
		return blog;	
	}
	
	/**
	 * ������Ŀ����Ŀ�ּ���
	 * @param zcjsList
	 * @return boolean
	 */
	public boolean zcxmfjs(List<HashMap<String,String>>zcjsList){
		
		List<HashMap<String, String>> xmjbList = dao.getXmjbList();
		
		boolean flag = false;
		
		for (int i = 0; i < xmjbList.size(); i++) {

			HashMap<String, String> xmjbMap = xmjbList.get(i);
			List<String> sql = new ArrayList<String>();
			for (int j = 0; j < zcjsList.size(); j++) {

				
				HashMap<String, String> zcjsMap = zcjsList.get(j);

				if (xmjbMap.get("xmjb").equalsIgnoreCase(zcjsMap.get("xmjb"))
						&& Base.isNull(zcjsMap.get("ytj"))) {
					zcjsMap.put("ytj", "yes");
					sql.add(zcjsMap.get("sql"));
				}
			}

			try {
				System.out.println(sql);
				flag = dao.saveArrDate(sql.toArray(new String[] {}));

			} catch (Exception e) {

				return false;

			}

		}

		return flag;	
	}
	
	/**
	 * ��ȡ��Դ���ݻ�������
	 * @return 
	 */
	public List<HashMap<String,String>>getLybInfo(){
		
		return dao.getLybInfo();
		 
	}
	
	/**
	 * ��ȡ��Դ���ݻ�������
	 * @return 
	 * @throws Exception 
	 */
	public boolean updateLybInfo(PjpyGeneralForm model,User user) throws Exception{
		
		return dao.updateLybInfo(model, user);
	}
	
	/**
	 * ������Ŀ���ܰ�ť
	 * @author qlj
	 */
	public HashMap<String, String> getButMap(PjpyGeneralForm model,
			User user) {

		return dao.getButMap(model, user);
	}
	
	public List<HashMap<String,String>>getZcInfo(String xh,String xn,String xq,String nd){
		
		// �۲�������Ϣ(en��cn)
		List<HashMap<String,String>>zcpmList=dao.getZcxxpmList();
		
		// �۲���Ŀ��Ϣ(en��cn)
		List<HashMap<String,String>>zcxmList=dao.getZcxmList(xn,xq,nd);
		
		zcxmList.addAll(zcpmList);
		
		// ---------2012.01.08 begin --------------------
		// ---------edit by ΰ����� --------------------
		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();
		String zcxn = jbszForm.getPjxn();
		String zcxq = jbszForm.getPjxq();
		String zcnd = jbszForm.getPjnd();

		if (!Base.isNull(xn)) {
			zcxn = xn;
		}

		if (!Base.isNull(xq)) {
			zcxq = xq;
		}

		if (!Base.isNull(nd)) {
			zcnd = nd;
		}
		// ---------2012.01.08 end --------------------
		
		HashMap<String,String>zhcpMap=dao.getZhcpInfo(xh,zcxn,zcxq,zcnd);
		
		List<HashMap<String,String>>rs=new ArrayList<HashMap<String,String>>();
		
		for(int i=0;i<zcxmList.size();i++){
			
			HashMap<String,String>zcxmMap=zcxmList.get(i);
			
			String xmdm=zcxmMap.get("en");
			
			String zcinfo=zhcpMap.get(xmdm);
			
			zcxmMap.put("zcinfo", zcinfo);
			
			rs.add(zcxmMap);
			
		}
		
		return rs;
	}
	
	
	public List<HashMap<String,Object>>getZclnInfo(String xh,String xn,String xq,String nd){
		
		List<HashMap<String,String>>zcInfo=getZcInfo(xh, xn, xq, nd);
		
		List<HashMap<String,Object>>zclnInfo=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<zcInfo.size();i+=2){
			
			HashMap<String,Object>zcMap=new HashMap<String,Object>();
			
			HashMap<String,String>leftMap=new HashMap<String,String>();
			HashMap<String,String>rightMap=new HashMap<String,String>();
			
			if(zcInfo.size()-1>=i){
				leftMap.putAll(zcInfo.get(i));
			}
			
			if(zcInfo.size()-1>=i+1){
				rightMap.putAll(zcInfo.get(i+1));
			}
			
			zcMap.put("left", leftMap);
			zcMap.put("right", rightMap);
			
			zclnInfo.add(zcMap);
		}
		
		return zclnInfo;
	}
	
	/**
	 * ��ȡ�����۲������Ϣ
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,Object>>getBczcInfo(String xh){
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		String xn=jbszModel.getPjxn();
		
		String xq=jbszModel.getPjxq();
		
		String nd=jbszModel.getPjnd();
		
		List<HashMap<String,String>>zcInfo=getZcInfo(xh, xn, xq, nd);
		
		List<HashMap<String,Object>>zclnInfo=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<zcInfo.size();i+=2){
			
			HashMap<String,Object>zcMap=new HashMap<String,Object>();
			
			HashMap<String,String>leftMap=new HashMap<String,String>();
			HashMap<String,String>rightMap=new HashMap<String,String>();
			
			if(zcInfo.size()-1>=i){
				leftMap.putAll(zcInfo.get(i));
			}
			
			if(zcInfo.size()-1>=i+1){
				rightMap.putAll(zcInfo.get(i+1));
			}
			
			zcMap.put("left", leftMap);
			zcMap.put("right", rightMap);
			
			zclnInfo.add(zcMap);
		}
		
		return zclnInfo;
	}
}
