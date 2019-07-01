package xgxt.pjpy.comm.pjpy.pjlc.xmsb;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.pjlc.PjpyPjlcService;
import xgxt.pjpy.comm.pjpy.pjlc.xmsh.PjpyXmshService;
import xgxt.pjpy.comm.pjpy.pjlc.xmsq.PjpyXmsqForm;
import xgxt.pjpy.comm.pjpy.pjlc.xmsq.PjpyXmsqService;
import xgxt.pjpy.comm.pjpy.tjsz.PjpyTjszUtils;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.utils.Pages;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_��Ŀ�ϱ�_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyXmsbService extends PjpyPjlcService {

	PjpyXmsbDAO dao = new PjpyXmsbDAO();

	/**
	 * �����Ŀ�ϱ�ѧ���б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXsList(PjpyXmsbForm model,
			User user) {
		return dao.getXsList(model, user);
	}
	
	/**
	 * ����¼���ȡ��Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXmOption(PjpyXmsbForm model) {
		return dao.getXmOption(model);
	}
	
	/**
	 * ������Ŀ������ʼ��ѧ���б�
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> defaultXsList(PjpyXmsbForm model,
			User user) {
		return null;
	}
	
	/**
	 * �����Ŀ��������
	 * 
	 * @author ΰ�����
	 */
	public String getXmszrs(PjpyXmsbForm model) {
		return dao.getXmszrs(model);
	}
	
	/**
	 * �����ʦ�ϱ�Html
	 * 
	 * @author ΰ�����
	 */
	public String getLssbHtml(SearchRsModel rsModel, PjpyXmsbForm model, User user) {
				
		PjpyTjszUtils tjszUtils = new PjpyTjszUtils();
		
		// ��Ŀ����model��ʼ��
		PjpyXmszModel xmszModel = model.getXmszModel();
		// �༶����
		String bjdm = model.getBjdm();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��������
		String rssz = xmszModel.getRssz();
		// ���Ʒ�Χ
		String kzfw = xmszModel.getKzfw();
		// �۲�����
		String zcpm = model.getZcpm();
		
		// ѧ����Ϣ�б�
		List<HashMap<String, String>> xsList = getXsList(model, user);
	
		// ���ʸ�������ѧ��
		String[] noPjzgXh = new String[]{};
		
		// �Ƿ����
		String search_condition = model.getSearch_condition();
		// �Ƿ������������
		if ("yes".equalsIgnoreCase(search_condition)) {
			// �ϱ��༶ѧ��
			String[] xh = getSbxh(xsList);
			// �ϱ���Ŀ�������������
			List<HashMap<String, String>> tjList = tjszUtils
					.getXmTj(bjdm, xmdm);
			if (tjList != null && tjList.size() > 0) {
				noPjzgXh = xh;
				for (int i = 0; i < tjList.size(); i++) {
					HashMap<String, String> tjMap = tjList.get(i);
					noPjzgXh = tjszUtils.getNoPjzgXh(noPjzgXh, tjMap, null);
				}
			}
		}
		
		StringBuilder spHtml = new StringBuilder();

		boolean defaultRs = false;
		//ȷ������
		String qdrs = "0";
		int count = 1;
		if ("��".equalsIgnoreCase(rssz) && "3".equalsIgnoreCase(zcpm)
				&& "bj".equalsIgnoreCase(kzfw)) {
			
			qdrs = xmszModel.getSzrs();
			qdrs = Base.isNull(qdrs) ? "0" : qdrs;
			defaultRs = true;
		}
		
		if (xsList != null && xsList.size() > 0) {

			for (int i = 0; i < xsList.size(); i++) {

				HashMap<String, String> rs = xsList.get(i);
				
				if ("yes".equalsIgnoreCase(search_condition)) {
					if (isExistInArr(noPjzgXh, rs.get("xh"))) {
						continue;
					}
				}
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\"");
				
				String color="";

				// Ĭ��ѡ��
				boolean checked = false;
				// ������
				boolean ysq = Base.isNull(rs.get("ysq")) ? false : true;
				
				if (defaultRs) {
					if (count <= Integer.parseInt(qdrs)) {
						//color="background-color: green";
						//checked = true;
					}
				}
				spHtml.append("/>");
				
				spHtml.append("<td align=\"center\" width=\"5px\" style=\"" + color + "\">");
				spHtml.append("<input type=\"checkbox\" name=\"checkVal\" ");
				spHtml.append("value=\"" + rs.get("xh") + "\"");
				
				// if (isExistInArr(noPjzgXh, rs.get("xh"))
				// || Base.isNull(rs.get("pm"))) {
				// spHtml.append("disabled=\"disabled\"");
				// } else if (ysq) {
				// spHtml.append("disabled=\"disabled\"");
				// }
				
				if (checked) {
					spHtml.append("checked=\"true\"");
				} else if (ysq) {
					spHtml.append("checked=\"true\" disabled=\"true\"");
				}
				
				spHtml.append("/>");
				spHtml.append("</td>");
				
				// ѧ��
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\""
						+ color + "\"> ");
				spHtml.append(rs.get("xh"));
				spHtml.append("</td>");
				// ����
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\""
						+ color + "\"> ");
				spHtml.append(rs.get("xm"));
				spHtml.append("</td>");
				// �Ա�
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\""
						+ color + "\"> ");
				spHtml.append(rs.get("xb"));
				spHtml.append("</td>");
				// �༶����
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\""
						+ color + "\"> ");
				spHtml.append(rs.get("pjbjmc"));
				spHtml.append("</td>");
				// �ۺϷ�
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\"" + color + "\"> ");
				if(Base.isNull(rs.get("zhf"))){
					spHtml.append("δ�������");
				}else{
					spHtml.append(rs.get("zhf"));
				}
				spHtml.append("</td>");
				// �ۺ�����
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\"" + color + "\"> ");
				if(Base.isNull(rs.get("pm"))){
					spHtml.append("δ��������");
				}else{
					spHtml.append(rs.get("pm"));
				}
				spHtml.append("</td>");
				
				
//				 �ۺϷ�
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\"" + color + "\"> ");
				if(Base.isNull(rs.get("zyf"))){
					spHtml.append("δ�������");
				}else{
					spHtml.append(rs.get("zyf"));
				}
				spHtml.append("</td>");
				// �ۺ�����
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\"" + color + "\"> ");
				if(Base.isNull(rs.get("zyfpm"))){
					spHtml.append("δ��������");
				}else{
					spHtml.append(rs.get("zyfpm"));
				}
				spHtml.append("</td>");
				
				// ����
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\""
						+ color + "\"> ");
				spHtml.append("<a href=\"#\" onclick=\"showCjInfo('"+rs.get("xh")+"');\">");
				spHtml.append("<font color=\"blue\">");
				spHtml.append("�鿴�ɼ�");
				spHtml.append("</font>");
				spHtml.append("</a>");
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
				
				count++;
			}
		}

		return spHtml.toString();
	}	
	
	/**
	 * ����ϱ��༶��ѧ������
	 * 
	 * @author ΰ�����
	 */
	private String[] getSbxh(List<HashMap<String, String>> xsList){
		
		List<String> xhList = new ArrayList<String>();
		
		if (xsList != null && xsList.size() > 0) {
			for (int i = 0; i < xsList.size(); i++) {
				HashMap<String, String> map = xsList.get(i);
				String xh = map.get("xh");
				xhList.add(xh);
			}
		}
		
		return xhList.toArray(new String[]{});
	}
	
	/**
	 * ������Ŀ�ϱ�
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveXmsb(PjpyXmsbForm model,
			User user) {
		boolean flag = saveXmsq(model, user);
		
		if(flag){
			flag = saveXmsh(model, user);
		}
		
		return flag;
	}
		
	/**
	 * ������Ŀ�����
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveXmsq(PjpyXmsbForm model,
			User user) {
		
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		String pjxn = jbszModel.getPjxn();// ����ѧ��
		String pjxq = jbszModel.getPjxq();// ��ѧ��
		String pjnd = jbszModel.getPjnd();// �������
		String xmdm = model.getXmdm();// ��Ŀ����

		// ��Ŀ����model��ʼ��
		String pkValue = pjxn + pjxq + pjnd + xmdm;
		PjpyXmszModel xmszModel = getXmszForXmdm(pkValue);
		
		model.setXmszModel(xmszModel);
		
		return dao.saveXmsqb(model, user);
	}
	
	/**
	 * ������Ŀ��˱�
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveXmsh(PjpyXmsbForm model,
			User user) {
		
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		String pjxn = jbszModel.getPjxn();// ����ѧ��
		String pjxq = jbszModel.getPjxq();// ��ѧ��
		String pjnd = jbszModel.getPjnd();// �������
		String xmdm = model.getXmdm();// ��Ŀ����

		// ��Ŀ����model��ʼ��
		String pkValue = pjxn + pjxq + pjnd + xmdm;
		PjpyXmszModel xmszModel = getXmszForXmdm(pkValue);
		
		model.setXmszModel(xmszModel);
		
		return dao.saveXmshb(model, user);
	}
	
	/**
	 * �ж��ϱ��ʸ�
	 * 
	 * @author ΰ�����
	 */
	public String judgeSbzg(PjpyXmsbForm model) {

		PjpyTjszUtils tjszUtils = new PjpyTjszUtils();

		// ��Ŀ����model��ʼ��
		PjpyXmszModel xmszModel = model.getXmszModel();
		// �༶����
		String bjdm = model.getBjdm();
		// ��Ŀ����
		String xmdm = model.getXmdm();

		// �ϱ���Ŀ�������������
		List<HashMap<String, String>> tjList = tjszUtils.getXmTj(bjdm, xmdm);
		// ���ʸ�������ѧ��
		String[] noPjzgXh = new String[] {};
		// ����ѧ��
		String[] xh = model.getPjxh();
		String message = "";

		if (tjList != null && tjList.size() > 0) {
			noPjzgXh = xh;
			for (int i = 0; i < tjList.size(); i++) {
				HashMap<String, String> tjMap = tjList.get(i);
				message = tjszUtils.getNoPjzgXh(noPjzgXh, tjMap);
				if(!Base.isNull(message)){
					break;
				}
			}
		}

		return message;
	}
	
	/**
	 * ����ѧ���ɼ�HTML
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	public void createXscjHTML(PjpyXmsbForm model, HttpServletResponse response)
			throws IOException {

		// ѧ��
		String xh = model.getXh();

		// �ɼ��б�
		List<HashMap<String, String>> cjList = dao.getCjList(model);
		
		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();
		
		html.append("<table class=\"dateline\" width=\"100%\">");
		// =========================����===========================
		html.append("<tr>");
		html.append("<td>�γ�����</td>");
		html.append("<td>�γ�����</td>");
		html.append("<td>�ɼ�</td>");
		html.append("</tr>");
		// =========================���� end========================
		
		// =========================����===========================
		if (cjList != null && cjList.size() > 0) {
			for (int i = 0; i < cjList.size(); i++) {
				HashMap<String, String> map = cjList.get(i);
				html.append("<tr>");
				html.append("<td>" + map.get("kcmc") + "</td>");
				html.append("<td>" + map.get("kcxz") + "</td>");
				html.append("<td>" + map.get("cj") + "</td>");
				html.append("</tr>");
			}
		}else{
			html.append("<tr>");
			html.append("<td colspan=\"3\">");
			html.append("<font color=\"blue\">");
			html.append("��ѯ������ѧ���ĳɼ���Ϣ");
			html.append("</font>");
			html.append("</td>");
			html.append("</tr>");
		}
		// =========================���� end========================
		
		html.append("</table>");

		response.getWriter().print(html.toString());
	}
}