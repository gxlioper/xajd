package xsgzgl.gygl.gyjlxxglnew;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.gygl.gyjlxxgl.GyjlxxglForm;

public class GyjlxxglNewService extends CommService{
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	GyjlxxglNewDao GyjlxxglDAO = new GyjlxxglNewDao();
	
	/**
	 * ��ñ�ͷ
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "xh", "xm", "xb", "bjmc", "gwxzmc", "zsqs", "wjsj", "wjlb","cljg","shztmc","actionLink"};
		String[] cn = new String[] { "", "ѧ��", "����", "�Ա�", "�༶", "ס������", "Υ��ʱ��","�������","������" ,"���״̬", "����"};
		if("12309".equals(Base.xxdm)){
			en = new String[] { "", "xh", "xm", "xb", "bjmc", "gwxzmc", "zsqs", "wjsj", "wjlb","cljg","shztmc","czrxm","czrbm","czsj","actionLink"};
			cn = new String[] { "", "ѧ��", "����", "�Ա�", "�༶", "ס������", "Υ��ʱ��","�������","������" ,"���״̬","�����","��鲿��","���ʱ��", "����"};
		}
		//�����ʵ��ѧ���Ի�
		if("13627".equals(Base.xxdm)){
			 en = new String[] { "", "xh", "xm", "xb", "bjmc", "gwxzmc", "zsqs", "wjsj", "wjlb","cljg","shztmc","fdyxm","actionLink"};
			 cn = new String[] { "", "ѧ��", "����", "�Ա�", "�༶", "ס������", "Υ��ʱ��","�������","������" ,"���״̬","����Ա", "����"};
		}
		//���칤�̴�ѧ���Ի�
		if("11799".equals(Base.xxdm)){
			 en = new String[] { "", "xh", "xm", "xb", "bjmc", "gwxzmc", "zsqs", "wjsj", "wjlb","cljg","shztmc","actionLink"};
			 cn = new String[] { "", "ѧ��", "����", "�Ա�", "�༶", "ס������", "Υ��ʱ��","�������","������" ,"���״̬", "����"};
		}
		if("gyjlxscx".equalsIgnoreCase(type)){
			en = new String[] { "xh","xm","xb","nj","xy","bj","ldmc","qsh","cwh",""};
			cn = new String[] { "ѧ��","����","�Ա�","�꼶",Base.YXPZXY_KEY,"�༶","¥������","���Һ�","��λ��","����" };
		}else if("gyjlxxcl".equalsIgnoreCase(type)){
			//�����ʵ��ѧ
			if("13627".equals(Base.xxdm)){
				en = new String[] { "","xh","xm","xb","zsqs","wjsj","wjlb","cljg","shzt","ylzd3","fdyxm" };
				cn = new String[] { "","ѧ��","����","�Ա�","ס������","Υ��ʱ��","�������","������","���״̬" ,"����ʱ��","����Ա"};
			}else if("11799".equals(Base.xxdm)){
				en = new String[] { "","xh","xm","xb","zsqs","wjsj","wjlb","cljg","shzt" };
				cn = new String[] { "","ѧ��","����","�Ա�","ס������","Υ��ʱ��","�������","������","���״̬" };
			}else{
				en = new String[] { "","xh","xm","xb","zsqs","wjsj","wjlb","cljg","shzt" };
				cn = new String[] { "","ѧ��","����","�Ա�","ס������","Υ��ʱ��","�������","������","���״̬" };
			}
		}else if("gyjlxxsh".equalsIgnoreCase(type)){
			//�����ʵ��ѧ���Ի�
			if("13627".equals(Base.xxdm)){
				en = new String[] { "","xh","xm","xb","zsqs","wjsj","wjlb","cljg","shzt","ylzd3","shsj","fdyxm" };
				cn = new String[] { "","ѧ��","����","�Ա�","ס������","Υ��ʱ��","�������","������","���״̬","����ʱ��","���ʱ��","����Ա" };
			}else if("11799".equals(Base.xxdm)){
				en = new String[] { "","xh","xm","xb","zsqs","wjsj","wjlb","cljg","shzt" };
				cn = new String[] { "","ѧ��","����","�Ա�","ס������","Υ��ʱ��","�������","������","���״̬" };
			}else{
				en = new String[] { "","xh","xm","xb","zsqs","wjsj","wjlb","cljg","shzt" };
				cn = new String[] { "","ѧ��","����","�Ա�","ס������","Υ��ʱ��","�������","������","���״̬" };
			}
		}else if("xsjlxxcx".equalsIgnoreCase(type)){
			if("13033".equals(Base.xxdm)){
				en = new String[] { "wjxn","xqmc","gyjllbdlmc","gjllbmc","wjsj","gyjlcfmc","ylzd1",""};
				cn = new String[] { "ѧ��","ѧ��","���ɴ���","�������","Υ��ʱ��","������","�⳥���","����" };
			}
			else{
			en = new String[] { "wjxn","xqmc","gyjllbdlmc","gjllbmc","wjsj","gyjlcfmc",""};
			cn = new String[] { "ѧ��","ѧ��","���ɴ���","�������","Υ��ʱ��","������","����" };
			}
		}
		return dao.arrayToList(en, cn);
	}
	
	
	/**
	 * ��ѯΥ��ѧ��
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> gyjlxxwhCx(GyjlxxglNewForm myForm,HttpServletRequest request) throws Exception {
		return GyjlxxglDAO.gyjlxxwhCx(myForm,request);
	}
	
	
	
	
	/**
	 * 
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		// IE�汾
		String ie = rsModel.getIe();
		// V4·��
//		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"zjBcStu();\">");
				
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
				
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}

		return html.toString();
	}
	
	public String createSearchHTMLgyjlxxwh(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		// IE�汾
		String ie = rsModel.getIe();
		// V4·��
//		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				String shzt = rs[8];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"ShowView();\">");
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:5px\" ");		
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				if(shzt.equals("δ����")||shzt.equals("�˻�")){
					html.append("<input type=\"checkbox\" name=\"div_pkValue\" ");
					html.append("value=\"" + pk + "\" />");
				}else{
					html.append("<input type=\"checkbox\" name=\"div_pkValue\" ");
					html.append("value=\"" + pk + "\" disabled=\"disabled\" />");
				}
				
				html.append("</td>");
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				html.append(">");
				html.append("<a class=\"name\" href=\"#\" onclick=\"zxsxxView('"+rs[1]+"')\">");
				html.append(rs[1]);
				html.append("</a>");
				html.append("</td>");
				for (int j = 2; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}
				html.append("<td>").append("<a href=\"#\" onclick=\"ShowView();return false;\"  >").append("�鿴").append("</a>").append("</td>");
				html.append("</tr>");
			}
		}

		return html.toString();
	}
	
	public String createSearchHTML2(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		// IE�汾
		String ie = rsModel.getIe();
		// V4·��
//		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				String shzt = rs[8];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"ShowView();\">");
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:5px\" ");		
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"div_pkValue\" ");
				html.append("value=\"" + pk + "\" />");
				
				html.append("</td>");
				
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}

		return html.toString();
	}
	
	/**
	 * ��ѯѧ����Ϣ�����
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTMLXscx(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		// IE�汾
		String ie = rsModel.getIe();
		// V4·��
//		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"zjBcStu();\">");				
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");
					html.append("<input type=\"hidden\" name=\"div_pkValue\" ");
					html.append("value=\"" + pk + "\"/>");
					html.append(rs[j]);
					html.append("</td>");
				}
				html.append("<td width=\"10%\">");
				html.append("<button type=\"button\" id=\"select\" onclick=\"cz(this);\" style=\"cursor:hand\"  class=\"btn_01\" >ѡ��</button>");
				html.append("</td>");
				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 * ����ҳ���Ĭ�ϲ���
	 * @param request
	 * @return
	 */
	public HashMap<String, String> setZjmrCs(HttpServletRequest request) {
		HashMap<String,String> rs = new HashMap<String,String>();
		rs.put("xn", Base.currXn);
		rs.put("xq", Base.currXq);
		return rs;
	}

	/**
	 * ��ü��ɴ���list
	 * @param request
	 * @return
	 */
	public List<HashMap<String, String>> getJldlList(HttpServletRequest request){
		return GyjlxxglDAO.getJldlList(request);
	}

	/**
	 * ��ѯס��ѧ��
	 * @param myForm 
	 * @return
	 */
	public ArrayList<String[]> gyjlxscx(GyjlxxglNewForm myForm,HttpServletRequest request) throws Exception {
		return GyjlxxglDAO.gyjlxscx(myForm,request);
	}
	
	
	/**
	 * ���ѧ��ס����Ϣ
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getStuInfo(GyjlxxglNewForm myForm) throws Exception{
		return GyjlxxglDAO.getStuInfo(myForm);
	
	}

	/**
	 * ��Ԣ������Ϣ����
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> gyjlxxclCx(GyjlxxglNewForm myForm,HttpServletRequest request) throws Exception {
		return GyjlxxglDAO.gyjlxxclCx(myForm,request);
	}

	/**
	 * ��Ԣ������Ϣ���
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> gyjlxxshCx(GyjlxxglNewForm myForm,HttpServletRequest request) throws Exception {
		return GyjlxxglDAO.gyjlxxshCx(myForm,request);
	}

	/**
	 * ��Ԣ������ʷ��Ϣ
	 * @param myForm
	 * @param request
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getWjxxList(GyjlxxglNewForm myForm) throws Exception {
		return GyjlxxglDAO.getWjxxList(myForm);
	}


	/**
	 * ��Ԣ������������
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String gyjlxxPlcl(GyjlxxglNewForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!@@");
		String cljg = model.getCljg();
		String dcqk = model.getDcqk();
		String clsj = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");
		List<String[]> params = new ArrayList<String[]>();
		if(pkValue.length!=0){
			for(int i = 0; i < pkValue.length; i++){
				String[] el = new String[]{cljg,dcqk,model.getYlzd1(),pkValue[i]};
				//�����ʵ��ѧ��ͨѧԺ
				if("13627".equals(Base.xxdm)){
					el = new String[]{cljg,dcqk,model.getYlzd1(),clsj,pkValue[i]};
				}
				params.add(el);
			}
		}
		return GyjlxxglDAO.gyjlxxPlcl(params)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}

	/**
	 * ɾ����Ԣ������Ϣ
	 * @param myForm
	 * @param valArr
	 * @param username
	 * @return
	 * @throws SQLException 
	 */
	public boolean gyjlSc(GyjlxxglForm myForm, String[] valArr, String username) throws SQLException {
		return GyjlxxglDAO.gyjlSc(myForm,valArr,username);
	}
	/**
	 * ������Ԣ���ɴ���
	 */
	public boolean gyjlCancelCl(GyjlxxglForm myForm, String[] valArr, String username) throws SQLException {
		return GyjlxxglDAO.gyjlCancelCl(myForm,valArr,username);
	}
	/**
	 * ������Ԣ���ɴ���
	 */
	public boolean gyjlCancelSh(GyjlxxglForm myForm, String[] valArr, String username) throws SQLException {
		return GyjlxxglDAO.gyjlCancelSh(myForm,valArr,username);
	}

	/**
	 * ѧ��������Ϣ��ѯ
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> xsjlxxcx(GyjlxxglNewForm myForm) throws Exception {
		return GyjlxxglDAO.xsjlxxcx(myForm);
	}


	public String createSearchHTMLXsjl(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		// IE�汾
		String ie = rsModel.getIe();
		// V4·��
//		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				html.append("<tr onclick=\"rowOnClick(this);\" >");
/*				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"zjBcStu();\">");
				
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
				html.append("</td>");*/
				
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				html.append(">");

				html.append("<a href=\"#\"  onclick=\"ckxsjl('"+rs[0]+"');return false;\"><font color=\"blue\">�鿴</font></a>");
				html.append("</td>");
				
				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 * ��Ԣ������Ϣ�������
	 * @param model
	 * @return
	 */
	public String gyjlxxPlsh(GyjlxxglNewForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!@@");
		String shzt = model.getShzt();
		String shyj = model.getShyj();
		String shr = model.getShr();
		String shsj = model.getShsj();
		List<String[]> params = new ArrayList<String[]>();
		if(pkValue.length!=0){
			for(int i = 0; i < pkValue.length; i++){
				String[] el = new String[]{shzt,shyj,shr,shsj,pkValue[i]};
				params.add(el);
			}
		}
		return GyjlxxglDAO.gyjlxxPlsh(params)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	/**
	 * ��Ԣ������Ϣ�����Զ�������
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> gyjlxxwhExportCx(GyjlxxglNewForm myForm,HttpServletRequest request) throws Exception {
		return GyjlxxglDAO.gyjlxxwhExportCx(myForm,request);
	}


	/**
	 * @throws Exception  
	 * @����:��Ԣ������Ϣ���  �Զ��嵼��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-12-30 ����03:29:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> gyjlxxshExportCx(
			GyjlxxglNewForm model, HttpServletRequest request) throws Exception {

		return GyjlxxglDAO.gyjlxxshExportCx(model,request);
	}
}
