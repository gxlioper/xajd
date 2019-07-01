package xgxt.pjpy.comm.pjpy.mypj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.pjlc.PjpyPjlcService;
import xgxt.pjpy.comm.pjpy.pjlc.xmsb.PjpyXmsbForm;
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
 * Description: ��������_�ҵ�����_Service��
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

public class PjpyMypjService extends PjpyPjlcService {

	PjpyMypjDAO dao = new PjpyMypjDAO();

	/**
	 * ����ҵ�����Html(��ʦ)
	 * 
	 * @author ΰ�����
	 */
	public String getMypjTeaHtml(SearchRsModel rsModel, PjpyMypjForm model,
			User user) {

		// ��������Ŀͳ���б�
		List<HashMap<String, String>> tjList = dao.getMypjTeaList(model, user);
		// ��ҳ
		Pages pages = model.getPages();
		tjList = getResultList(tjList, pages);
		
		StringBuilder spHtml = new StringBuilder();
		
		if (tjList != null && tjList.size() > 0) {

			//����
			model.setRows(String.valueOf(tjList.size()));
			
			for (int i = 0; i < tjList.size(); i++) {

				HashMap<String, String> rs = tjList.get(i);
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\"");
				spHtml.append("/>");
				
				// ��Ŀ����
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:25%\"> ");
				spHtml.append(rs.get("xmmc"));
				spHtml.append("</td>");
				// �ϱ�����
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:25%\"> ");
				spHtml.append(rs.get("sbrs"));
				spHtml.append("</td>");
				// ��˼���
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:50%\"> ");
				spHtml.append(rs.get("lv"));
				spHtml.append("</td>");
				// ����
//				spHtml.append("<td align=\"left\" nowrap=\"nowrap\"> ");
//				spHtml.append(rs.get("lv"));
//				spHtml.append("</td>");
				
				spHtml.append("</tr>");
				
			}
		}

		return spHtml.toString();
	}	
	
	/**
	 * ����ҵ�����Html(ѧ��)
	 * 
	 * @author ΰ�����
	 */
	public String getMypjStuHtml(SearchRsModel rsModel, PjpyMypjForm model,
			User user) {

		// ��������Ŀͳ���б�
		List<HashMap<String, String>> tjList = dao.getMypjStuList(model, user);
		
		// ��ҳ
		Pages pages = model.getPages();
		tjList = getResultList(tjList, pages);
		
		StringBuilder spHtml = new StringBuilder();
		
		if (tjList != null && tjList.size() > 0) {

			//����
			model.setRows(String.valueOf(tjList.size()));
			
			for (int i = 0; i < tjList.size(); i++) {

				HashMap<String, String> rs = tjList.get(i);
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\"");
				
				String color="";
				spHtml.append("/>");
				
				// ��Ŀ����
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:25%\">");
				spHtml.append(rs.get("xmmc"));
				spHtml.append("</td>");
				// �ϱ�����
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:50%\"> ");
				spHtml.append(rs.get("shzt"));
				spHtml.append("</td>");
				// ��˼���
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:25%\"> ");
				spHtml.append("<a href=\"#\" onclick=\"showXmsqDetail('"+rs.get("pk")+"','"+rs.get("xmdm")+"','"+rs.get("shjb")+"');return false;\">");
				spHtml.append("<font color=\"blue\">����鿴��ϸ</font>");
				spHtml.append("</a>");
				spHtml.append("</td>");
				// ����
//				spHtml.append("<td align=\"left\" nowrap=\"nowrap\"> ");
//				spHtml.append(rs.get("lv"));
//				spHtml.append("</td>");
				
				spHtml.append("</tr>");
				
			}
		}

		return spHtml.toString();
	}	
}