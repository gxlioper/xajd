package xsgzgl.xtwh.general.flow;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_�����_ͨ��_Service��
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

public class XtwhFlowService extends CommService {

	XtwhFlowDAO dao = new XtwhFlowDAO();

	/**
	 * �����û���Div
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void createYhzDiv(XtwhFlowModel model, User user,
			HttpServletResponse response) throws Exception {

		List<HashMap<String, String>> list = dao.getYhzList();

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();
		html.append("<ul id=\"tree\" class=\"ztree\" ");
		html.append("style=\"overflow-y: auto; overflow-x: auto; height: 350px; margin-top: 0px;\"> ");
		if (list != null && list.size() > 0) {
			html.append("<li id=\"all\"");
			html.append("class=\"Current\"> ");
			html.append("<a href=\"#\" class=\"Child\" ");
			html.append("title=\"ȫ��\" ");
			html.append("onclick=\"initKyyh();createKxyhDiv('');return false\" ");
			html
					.append("style='float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;'>");
			html.append("-------ȫ��-------</a>");
			html.append("</li>");
			for (int i = 0; i < list.size(); i++) {

				String dm = list.get(i).get("zdm");
				String mc = list.get(i).get("zmc");

				html.append("<li id=\"" + dm + "\"");
				html.append("> ");
				html.append("<a href=\"#\" class=\"Child\" ");
				html.append("title=\"" + mc + "\" ");
				html.append("onclick=\"initKyyh();createKxyhDiv('" + dm
						+ "');return false\" ");
				html.append("style='float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;'>"
								+ mc + "</a>");
				html.append("</li>");
			}
		}
		html.append("</ul>");
		response.getWriter().print(html.toString());
	}

	/**
	 * ������ѡ�û�Div
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void createKxyhDiv(XtwhFlowModel model, User user,
			HttpServletResponse response) throws Exception {

		// �û���
		String yhm = model.getYhm();
		// �����
		String zdm = model.getZdm();
		// ��λ����
		String gwdm = model.getGwdm();
		// ��ǰҳ
		String current = model.getCurrent();
		
		String sffdy=model.getSffdy();
		String sfbzr=model.getSfbzr();
		
		current = Base.isNull(current) ? "1" : current;
		// ��ʼ��
		String init = model.getInit();
		current = ("no".equalsIgnoreCase(init)) ? current : "1";
		// ========== ���Ի� �û���Ȩ begin ============
		List<HashMap<String, String>> list = null;
		String yhszlx = model.getYhszlx();
		if("rcxwwh".equals(yhszlx)){
			// �ճ�����NEW-�ճ���Ϊά��NEW-�ճ���Ϊ����ά��-�ճ���Ϊ���
			list = dao.getKxyhListRcxwwh(zdm, yhm, gwdm,sffdy,sfbzr);
		}else{
			// ϵͳά��-��������ά��-��������
			list = dao.getKxyhList(zdm, yhm, gwdm,sffdy,sfbzr);
		}
		// ========== ���Ի� �û���Ȩ end ============
		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<table id=\"ul_eligiblyUser\" ");
		html.append("class=\"bodyPerson\" ");
		html.append("style=\"overflow-y:hidden;height:350px\">");
		
		// ���ҳ
		String max = "1";
		// ������
		int space_num = 11;

		int start = (Integer.parseInt(current) - 1) * 11;
		int end = (Integer.parseInt(current)) * 11;
		
		if (list != null && list.size() > 0) {
			
			end = (list.size() - end) > 0 ? end : list.size();
			
			if (list.size() % 11 == 0) {
				max = String.valueOf(list.size() / 11);
			} else {
				max = String.valueOf((list.size() / 11) + 1);
			}		
					
			for(int i=start;i<end;i++){ 
				html.append("<tr id='"+list.get(i).get("yhm")+"' >");
				html.append("<td>");
				html.append("<input name='kxyhArr' id='kxyh_"+i+"' type='checkbox' value='"+list.get(i).get("yhm")+"'/>");
				html.append("<span>"+list.get(i).get("xm")+"("+list.get(i).get("yhm")+")</span>");
				html.append("<input name='kxyhxmArr' id='kxyhxm_"+i+"' type='hidden' value='"+list.get(i).get("xm")+"'/>");
				html.append("</td>");
				html.append("</tr>");
				
				space_num--;
			
			}	
		}
		
		for(int i=0;i<space_num;i++){
			html.append("<tr >");
			html.append("<td>&nbsp;");
			html.append("</td>");
			html.append("</tr>");
		}
		
		html.append("</table>");
			
		html.append("<input type=\"hidden\" id=\"kxyhCurrent\" value=\""
				+ current + "\">");
		
		html.append("<input type=\"hidden\" id=\"kxyhMax\" value=\""
				+ max + "\">");
			
		response.getWriter().print(html.toString());
	}
	
	/**
	 * ������ѡ�û�Div
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void createYxyhDiv(XtwhFlowModel model, User user,
			HttpServletResponse response) throws Exception {
		// �û���
		String yhm = model.getYhm();
		// ��λ����
		String gwdm = model.getGwdm();
		// ��ǰҳ
		String current = model.getCurrent();
		current = Base.isNull(current) ? "1" : current;
		
		// ========== ���Ի� �û���Ȩ begin ============
		List<HashMap<String, String>> list = null;
		String yhszlx = model.getYhszlx();
		if("rcxwwh".equals(yhszlx)){
			// �ճ�����NEW-�ճ���Ϊά��NEW-�ճ���Ϊ����ά��-�ճ���Ϊ���
			list = dao.getYxyhListRcxwwh(gwdm, yhm);
		}else{
			// ϵͳά��-��������ά��-��������
			list = dao.getYxyhList(gwdm, yhm);
		}
		// ========== ���Ի� �û���Ȩ end ============

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<table id=\"ul_eligiblyUser\" ");
		html.append("class=\"bodyPerson\" ");
		html.append("style=\"overflow-y:hidden;height:350px\">");
		// ���ҳ
		String max = "1";
		// ������
		int space_num = 11;
		
		int start = (Integer.parseInt(current) - 1) * 11;
		int end = (Integer.parseInt(current)) * 11;
		
		if(list!=null && list.size()>0){
			
			end = (list.size() - end) > 0 ? end : list.size();

			if (list.size() % 11 == 0) {
				max = String.valueOf(list.size() / 11);
			} else {
				max = String.valueOf((list.size() / 11) + 1);
			}	
			
			for (int i = start; i < end; i++) {
				html.append("<tr id='"+list.get(i).get("yhm")+"'>");
				html.append("<td>");
				html.append("<input name='yxyhArr' id='yxyh_"+(i-1)+"' type='checkbox' value='"+list.get(i).get("yhm")+"'/>");
				html.append("<span>"+list.get(i).get("xm")+"("+list.get(i).get("yhm")+")</span>");
				html.append("<input name='yxyhmArr' id='yxyhm_"+(i-1)+"' type='hidden' value='"+list.get(i).get("yhm")+"'/>");
				html.append("<input name='yxyhxmArr' id='yxyhxm_"+(i-1)+"' type='hidden' value='"+list.get(i).get("xm")+"'/>");
				html.append("</td>");
				html.append("</tr>");
				
				space_num--;
			}	
		}
		
		for(int i=0;i<space_num;i++){
			html.append("<tr >");
			html.append("<td>&nbsp;");
			html.append("</td>");
			html.append("</tr>");
		}
		
		html.append("</table>");
			
		html.append("</table>");
		
		html.append("<input type=\"hidden\" id=\"yxyhCurrent\" value=\""
				+ current + "\">");
		
		html.append("<input type=\"hidden\" id=\"yxyhMax\" value=\""
				+ max + "\">");
		
		response.getWriter().print(html.toString());
	}

	/**
	 * ������ѡ�û���ϵͳά��-��������ά��-�������̣�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean saveYxyh(XtwhFlowModel model, User user) {

		boolean flag = false;

		String gwdm = model.getGwdm();
		String[] spyh = model.getSpyh();

		try {
			flag = dao.saveYxyh(gwdm, spyh, user);
			
			if (flag){
				MyJobsManager manager = new MyJobsImpl();
				manager.copyWdgz(spyh, gwdm);
			}
			
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}
	/**
	 * ������ѡ�û����ճ�����NEW-�ճ���Ϊά��NEW-�ճ���Ϊ����ά��-�ճ���Ϊ���
	 */
	public boolean saveYxyhRcxwwh(XtwhFlowModel model, User user) {
		boolean flag = false;
		String gwdm = model.getGwdm();
		String[] spyh = model.getSpyh();
		try {
			flag = dao.saveYxyhRcxwwh(gwdm, spyh, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ɾ����ѡ�û�(ϵͳά��-��������ά��-��������)
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean deleteYxyh(XtwhFlowModel model, User user) {

		boolean flag = false;

		String gwdm = model.getGwdm();
		String[] spyh = model.getSpyh();

		try {
			flag = dao.deleteYxyh(gwdm, spyh, user);
			
			if (flag){
				MyJobsManager manager = new MyJobsImpl();
				manager.delWdgz(spyh, gwdm);
			}
			
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}
	/**
	 * ɾ����ѡ�û����ճ�����NEW-�ճ���Ϊά��NEW-�ճ���Ϊ����ά��-�ճ���Ϊ���
	 */
	public boolean deleteYxyhRcxwwh(XtwhFlowModel model, User user) {
		boolean flag = false;
		
		String gwdm = model.getGwdm();
		String[] spyh = model.getSpyh();
		
		try {
			flag = dao.deleteYxyhRcxwwh(gwdm, spyh, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}