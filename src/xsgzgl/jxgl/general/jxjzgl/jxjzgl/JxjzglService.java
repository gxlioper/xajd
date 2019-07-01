package xsgzgl.jxgl.general.jxjzgl.jxjzgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.struts.util.MessageResources;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.db.GetSysData;
import xsgzgl.comm.BasicService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ѵ����_��ѵ���ƹ���_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author �׽���
 * @version 1.0
 */

public class JxjzglService extends CommService {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	JxjzglDAO dao=new JxjzglDAO();
	/**
	 * ���ӱ�����ƽڵ�
	 * @param model
	 * @return
	 */
	public boolean zjBcBzjd(JxjzglFrom model,User user){
		if(model == null){
			return false;
		}
		String guid = GetSysData.getGuid();
		model.setJzid(guid);
		boolean result=false;
		try {
			result = dao.insertJxjzwh(model, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * �޸ı�����ƽڵ�
	 * @param model
	 * @param user
	 * @return
	 */
	public boolean xgBcBzjd(JxjzglFrom model,User user){
		boolean result=false;
		try {
			result=dao.updateJxjzwh(model, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ɾ�����ƽڵ�  ��  ��ǰ�ڵ��  �ֽڵ�
	 * @param model
	 * @param user
	 * @return
	 */
	public boolean scBzjd(JxjzglFrom model,User user){
		boolean result = false;
		try {
			//ɾ�������������ݽ���id
			dao.deleteJzmdByJzid(model, user);
			//ɾ������ά��
			result=dao.deleteJxjzwh(model, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * ��ѯ��ѵ�ȼ��б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> cxJxdjList(JxjzglFrom model){
		List<HashMap<String, String>> jxdjList=null;
		try {
			jxdjList=dao.getJxdjList(model);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return jxdjList;
	}
	
	/**
	 * �鿴��ѵ�����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> ckJxdjZdModel(){
		HashMap<String, String> jxdjZdModel = null;
		try {
			jxdjZdModel = dao.getJxdjZdModel();
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return jxdjZdModel;
	}
	
	/**
	 * ��ѯ��ѵ���ƽڵ��б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> cxJxjzNodeList(JxjzglFrom model){
		List<HashMap<String, String>> jxjzList=null;
		try {
			jxjzList = dao.getJxjzNextNodeList(model);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return jxjzList;
	}
	
	/**
	 * ��ѯ��ѵ����model
	 * @param model
	 * @return
	 */
	public HashMap<String, String> ckJxjzModel(JxjzglFrom model){
		
		HashMap<String, String> jxjzModel = null;
		try {
			jxjzModel = dao.getJxjzModel(model);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return jxjzModel;
	}
	
	
	/**
	 * ��ѯ��ѵ���Ƹ��ϼ�id�ͽ���id
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> ckJxjzModelBySjid(JxjzglFrom model)
			throws Exception {
		if(model == null){
			return new HashMap<String, String>();
		}
		if(model.getJzid() !=null){
			if(model.getJzid().equals(model.getSjid())){
				return dao.getJxjzModel(model);
			}
		}
		return dao.getJxjzModelBySjid(model);
	}
	
	/**
	 * ��ѯ��ѵ����ѧ����Ϣ
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> cxJxjzXsxxList(JxjzglFrom model){
		
		return null;
	}
	
	
	/**
	 * ��ѯ��һ���ڵ�
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> cxOneNode(JxjzglFrom model){
		List<HashMap<String, String>> jxjzglList=cxJxjzNodeList(model);
		if(jxjzglList != null){
			for (int i = 0; i < jxjzglList.size(); i++) {
				jxjzglList.get(i).put("djjb", "0");
			}
			
		}
		return jxjzglList;
		
	}
	
	/**
	 * ��ѯ��ѵ�ȼ����߽ڵ�
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> cxJxdjByNode(JxjzglFrom model) {
		List<HashMap<String, String>> djList=new ArrayList<HashMap<String,String>>();
		HashMap<String, String> djModel=new HashMap<String, String>();
		try {
			List<HashMap<String, String>> jxdjList = dao.getJxdjList(model);
			if (model.getJzjb() == null || "".equals(model.getJzjb())) {
				List<HashMap<String, String>> nextJxjzNodeList=dao.getJxjzNextNodeList(model);
				if(nextJxjzNodeList == null || nextJxjzNodeList.size() == 0){
					djList = jxdjList;
				}else{
					for (int i = 0; i < jxdjList.size(); i++) {
						if(nextJxjzNodeList.get(0).get("djdm").equals(jxdjList.get(i).get("djdm"))){
							djModel.put("djdm", jxdjList.get(i).get("djdm"));
							djModel.put("djmc", jxdjList.get(i).get("djmc"));
							djModel.put("djdj", jxdjList.get(i).get("djdj"));
							djList.add(djModel);
							break;
						}
					}
				}
			}else{
				for (int i = 0; i < jxdjList.size(); i++) {
					if(model.getJzjb().equals(jxdjList.get(i).get("djdm"))){
						if(i == (jxdjList.size()-1)){
							djModel.put("djdm", jxdjList.get(i).get("djdm"));
							djModel.put("djmc", jxdjList.get(i).get("djmc"));
							djModel.put("djdj", jxdjList.get(i).get("djdj"));
						}else{
							djModel.put("djdm", jxdjList.get(i+1).get("djdm"));
							djModel.put("djmc", jxdjList.get(i+1).get("djmc"));
							djModel.put("djdj", jxdjList.get(i+1).get("djdj"));
						}
						djList.add(djModel);
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return djList;
	}
	
	/**
	 * ���ӽ���ά������html
	 * @return
	 */
	public String zjJzwhHtml(JxjzglFrom model){
		List<HashMap<String, String>> djList=cxJxdjByNode(model);
		StringBuffer html = new StringBuffer();
		html.append("<div class=\"open_win01\">");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>�����¼�����</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		html.append("���Ƽ���");
		html.append("</th>");
		html.append("<td width=\"\">");
		if ("10704".equals(Base.xxdm)) {
			html.append("<select id=\"djdm\" name=\"djdm\" style=\"width:145px;\" onchange=\"qhShow(this)\" >");
		}else{
			html.append("<select id=\"djdm\" name=\"djdm\" style=\"width:145px;\">");
		}
		if(djList != null){
			for (int i = 0; i < djList.size(); i++) {
				html.append("<option value=\""+djList.get(i).get("djdm")+"\">"+djList.get(i).get("djmc")+"</option>");
			}
		}
		html.append("</select>");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		html.append("<span style=\"color:red;\">*</span>��������");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"text\" id=\"jzmc\" name=\"jzmc\" onchange=\"checkOnlyJzmc(this);\" maxlength=\"20\"/>");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th id=\"th1\" width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if (djList.size()==1 && "Ӫ".equals(djList.get(0).get("djmc"))) {
				html.append("Ӫ��");
			}else if (djList.size()==1 && "��".equals(djList.get(0).get("djmc"))) {
				html.append("����");
			}else {
				html.append("�̹�����");
			}
		}else {
			html.append("�̹�����");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"text\" id=\"jgmc\" name=\"jgmc\" maxlength=\"50\" />");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th id=\"th2\"width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if (djList.size()==1 && "Ӫ".equals(djList.get(0).get("djmc"))) {
				html.append("Ӫ���绰");
			}else if (djList.size()==1 && "��".equals(djList.get(0).get("djmc"))) {
				html.append("�����绰");
			}else {
				html.append("�̹ٵ绰");
			}
		}else {
			html.append("�̹ٵ绰");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"text\" id=\"jgdh\" name=\"jgdh\" onkeyup=\"cehckInt(this);\" maxlength=\"50\"/>");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th id=\"th3\" width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if (djList.size()==1 && "Ӫ".equals(djList.get(0).get("djmc"))) {
				html.append("�̵�Ա");
			}else if (djList.size()==1 && "��".equals(djList.get(0).get("djmc"))) {
				html.append("ָ��Ա");
			}else {
				html.append("��ʦ����");
			}
		}else {
			html.append("��ʦ����");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"text\" id=\"jsmc\" name=\"jsmc\" maxlength=\"50\"/>");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th id=\"th4\" width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if (djList.size()==1 && "Ӫ".equals(djList.get(0).get("djmc"))) {
				html.append("�̵�Ա�绰");
			}else if (djList.size()==1 && "��".equals(djList.get(0).get("djmc"))) {
				html.append("ָ��Ա�绰");
			}else {
				html.append("��ʦ�绰");
			}
		}else {
			html.append("��ʦ�绰");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"text\" id=\"jsdh\" name=\"jsdh\" onkeyup=\"cehckInt(this);\" maxlength=\"50\"/>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"bz\">");
		html.append("</div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  onclick=\"saveZjBcXjjz();return false;\">");
		html.append("ȷ ��");
		html.append("</button>");
		html.append("<button type=\"button\"  onclick=\"closeWindown();return false;\">");
		html.append("�� ��");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");
		return html.toString();
	}
	
	/**
	 * �޸Ľ���ά������html
	 * @return
	 */
	public String xgJzwhHtml(JxjzglFrom model){
		//List<HashMap<String, String>> djList=cxJxdjByNode(model);
		HashMap<String, String> jxjzModel=null;
		try {
			jxjzModel = dao.getJxjzModel(model);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		if(jxjzModel == null){
			return "";
		}
		StringBuffer html = new StringBuffer();
		html.append("<div class=\"open_win01\">");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>�޸��¼�����</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		html.append("���Ƽ���");
		html.append("</th>");
		html.append("<td width=\"\">");
		
		html.append("<select id=\"djdm\" name=\"djdm\" style=\"width:145px;\">");
		html.append("<option value=\""+jxjzModel.get("djdm")+"\">"+jxjzModel.get("djmc")+"</option>");
		html.append("</select>");
		html.append("<input type=\"hidden\" name=\"jzid\" id=\"jzid\" value=\""+jxjzModel.get("jzid")+"\">");
		
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		html.append("<span style=\"color:red;\">*</span>��������");
		html.append("</th>");
		html.append("<td width=\"\">");
		if(jxjzModel.get("jzmc") == null){
			html.append("<input type=\"text\" id=\"jzmc\" name=\"jzmc\" onchange=\"checkOnlyJzmc(this);\" maxlength=\"20\" value=\"\"/>");
		}else{
			html.append("<input type=\"text\" id=\"jzmc\" name=\"jzmc\" onchange=\"checkOnlyJzmc(this);\" maxlength=\"20\" value=\""+jxjzModel.get("jzmc")+"\"/>");
		}
		
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if ("Ӫ".equals(jxjzModel.get("djmc"))) {
				html.append("Ӫ��");
			}else if ("��".equals(jxjzModel.get("djmc"))) {
				html.append("����");
			}else {
				html.append("�̹�����");
			}
		}else {
			html.append("�̹�����");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		if(jxjzModel.get("jgmc") == null){
			html.append("<input type=\"text\" id=\"jgmc\" name=\"jgmc\" maxlength=\"50\" value=\"\" />");
		}else{
			html.append("<input type=\"text\" id=\"jgmc\" name=\"jgmc\" maxlength=\"50\" value=\""+jxjzModel.get("jgmc")+"\" />");
		}
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if ("Ӫ".equals(jxjzModel.get("djmc"))) {
				html.append("Ӫ���绰");
			}else if ("��".equals(jxjzModel.get("djmc"))) {
				html.append("�����绰");
			}else {
				html.append("�̹ٵ绰");
			}
		}else {
			html.append("�̹ٵ绰");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		if(jxjzModel.get("jgdh") == null){
			html.append("<input type=\"text\" id=\"jgdh\" name=\"jgdh\" onkeyup=\"cehckInt(this);\" maxlength=\"50\" value=\"\"/>");
		}else{
			html.append("<input type=\"text\" id=\"jgdh\" name=\"jgdh\" onkeyup=\"cehckInt(this);\" maxlength=\"50\" value=\""+jxjzModel.get("jgdh")+"\"/>");
		}
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if ("Ӫ".equals(jxjzModel.get("djmc"))) {
				html.append("�̵�Ա");
			}else if ("��".equals(jxjzModel.get("djmc"))) {
				html.append("ָ��Ա");
			}else {
				html.append("��ʦ����");
			}
		}else {
			html.append("��ʦ����");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		if(jxjzModel.get("jsmc") == null){
			html.append("<input type=\"text\" id=\"jsmc\" name=\"jsmc\" maxlength=\"50\" value=\"\"/>");
		}else{
			html.append("<input type=\"text\" id=\"jsmc\" name=\"jsmc\" maxlength=\"50\" value=\""+jxjzModel.get("jsmc")+"\"/>");
		}
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if ("Ӫ".equals(jxjzModel.get("djmc"))) {
				html.append("�̵�Ա�绰");
			}else if ("��".equals(jxjzModel.get("djmc"))) {
				html.append("ָ��Ա�绰");
			}else {
				html.append("��ʦ�绰");
			}
		}else {
			html.append("��ʦ�绰");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		if(jxjzModel.get("jsdh") == null){
			html.append("<input type=\"text\" id=\"jsdh\" name=\"jsdh\" onkeyup=\"cehckInt(this);\" maxlength=\"50\" value=\"\"/>");
		}else{
			html.append("<input type=\"text\" id=\"jsdh\" name=\"jsdh\" onkeyup=\"cehckInt(this);\" maxlength=\"50\" value=\""+jxjzModel.get("jsdh")+"\"/>");
		}
		
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"bz\">");
		html.append("</div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  onclick=\"saveXgBcXjjz();return false;\">");
		html.append("ȷ ��");
		html.append("</button>");
		html.append("<button type=\"button\" onclick=\"closeWindown();return false;\">");
		html.append("�� ��");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");
		return html.toString();
	}
	
	/**
	 * ��֤��������
	 * @param model
	 * @return
	 */
	public boolean checkJzmc(JxjzglFrom model){
		if(model == null){
			return false;
		}
		HashMap<String, String> jxjzModel=null;;
		try {
			jxjzModel = dao.getJxjzglModelByJzmcAndJzid(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jxjzModel == null || jxjzModel.isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * ��ѯ��ѵ���ƹ���ѧ����Ϣ�б� ��ҳ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getJxjzXsxxList(JxjzglFrom model) throws Exception{
		return  dao.getJxjzXsxxList(model);
	}
	
	/**
	 * ��ѵ���Ʊ���
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type){
		DAO da = DAO.getInstance();
		String[] en = new String[]{};
		String[] cn = new String[]{};
		if("whjzmd".equalsIgnoreCase(type)){
			en = new String[]{"", "xh", "xm","xb", "nj","xymc", "zymc","bjmc","treejzmc"};
			cn = new String[] { "", "ѧ��", "����","�Ա�", "�꼶","Ժϵ", "רҵ","�༶","���ڽ���"};
		}else if("jxjzxsxx".equalsIgnoreCase(type)){
			en = new String[]{ "", "xh", "xm","xb", "bjmc","treejzmc"};
			cn = new String[] { "", "ѧ��", "����","�Ա�", "���ڰ༶","���ڽ���"};
		}
		return da.arrayToList(en, cn);
		
	}
	
	/**
	 * ����ҳ���ѯ���
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		BasicService basicService=new BasicService();
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + basicService.replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
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
	
	/**
	 * ��ѯ����ѧ����Ϣ�б� ����ҳ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cxJxjzXsxxListByJzid(JxjzglFrom model) throws Exception{
		
		//return dao.getJxjzXsxxListByJzid(model);
		return null;
	}
	
	/**
	 * ��ѯ����ѧ����Ϣ�б� ����ҳ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> cxJxjzXsxxListByJzid1(JxjzglFrom model) throws Exception{
		
		return dao.getJxjzXsxxListByJzid(model);
	}
	
	/**
	 * ������ѯhtml
	 * @param contentList
	 * @return
	 */
	public String createSearchHtml(List<HashMap<String, String>> contentList){
		StringBuffer html=new StringBuffer();
		
		html.append(" <table width=\"100%\" class=\"dateline nowrap\"> ");
		html.append(" <thead> ");
		html.append(" <tr> ");
		html.append(" <td> ");
		html.append(" ѧ�� ");
		html.append(" </td> ");
		html.append(" <td> ");
		html.append(" ���� ");
		html.append(" </td> ");
		html.append(" <td> ");
		html.append(" �Ա� ");
		html.append(" </td> ");
		html.append(" <td> ");
		html.append(" ���ڱ��� ");
		html.append(" </td> ");
		html.append(" <td> ");
		html.append(" ���ڽ��� ");
		html.append(" </td> ");
		html.append(" </tr> ");
		html.append(" </thead> ");
		html.append(" <tbody> ");
		for (int i = 0; i < contentList.size(); i++) {
			html.append(" <tr> ");
			html.append(" <td> ");
			html.append(contentList.get(i).get("xh"));
			html.append(" </td> ");
			html.append(" <td> ");
			html.append(contentList.get(i).get("xm"));
			html.append(" </td> ");
			html.append(" <td> ");
			html.append(contentList.get(i).get("xb"));
			html.append(" </td> ");
			html.append(" <td> ");
			html.append(contentList.get(i).get("bjmc"));
			html.append(" </td> ");
			html.append(" <td> ");
			html.append(contentList.get(i).get("treejzmc"));
			html.append(" </td> ");
			html.append(" </tr> ");
		}
		html.append(" </tbody> ");
		html.append(" </table> ");
		
		return html.toString();
	}
	
	/**
	 * ���ӱ����ѵ��������
	 * @param pks
	 * @param model
	 * @param user
	 * @return
	 */
	public boolean zjBcJxjzmd(String pks,JxjzglFrom model,User user){
		//ʵ��ɾ���ѱ����Ƶ�ѧ����Ϣ
		scJxjzmd(pks, user);
		
		List<JxjzglFrom> list=new ArrayList<JxjzglFrom>();
		String[] pk=pks.split("@@##");
		JxjzglFrom jxjzglFrom=null;
		for (int i = 0; i < pk.length; i++) {
			jxjzglFrom=new JxjzglFrom();
			jxjzglFrom.setXh(pk[i]);
			jxjzglFrom.setJzid(model.getJzid());
			list.add(jxjzglFrom);
		}
		boolean result = false;
		try {
			result = dao.insertJxjzmd(list, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ɾ����ѵ��������
	 * @param pks
	 * @return
	 */
	public boolean scJxjzmd(String pks, User user){
		List<JxjzglFrom> list=new ArrayList<JxjzglFrom>();
		String[] pk=pks.split("@@##");
		JxjzglFrom jxjzglFrom=null;
		for (int i = 0; i < pk.length; i++) {
			jxjzglFrom=new JxjzglFrom();
			jxjzglFrom.setXh(pk[i]);
			list.add(jxjzglFrom);
		}
		boolean result=false;
		try{
			result=dao.deleteJxjzmd(list, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * ��ѯ��ѵ����ͳ�Ʊ�
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> cxJxjzTjb(JxjzglFrom model) throws Exception{
		List<HashMap<String, String>> jzdjList=dao.getJxJzdj(model);
		
		if(jzdjList == null || jzdjList.size() == 0){
			return null;
		}
		List<HashMap<String, String>> jxjzList=dao.getJxjzTjb(jzdjList, model);
		return jxjzList;
	}
	
	/**
	 * ������ѵ����ͳ�Ʊ�html
	 * @param jxjzList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String createJxjzTjxHtml(List<HashMap<String, String>> jxjzList,JxjzglFrom model) throws Exception{
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		List<HashMap<String, String>> jzdjList=dao.getJxJzdj(model);
		if(jxjzList == null){
			return "";
		}
		//��ȡ�ϲ�����ֵ����
		List<Object> jxjzNnion=setJxjzTjxNnionTable(jxjzList, jzdjList);
		HashMap<String, Integer> jzNnion=(HashMap<String, Integer>)jxjzNnion.get(0);
		HashMap<String, Integer> jzRs=(HashMap<String, Integer>)jxjzNnion.get(1);
		HashMap<String, String> jzXymc=(HashMap<String, String>)jxjzNnion.get(2);
		HashMap<String, Integer> jzBjNnion=(HashMap<String, Integer>)jxjzNnion.get(3);
		
		//�ϲ�ʹ�ò���
		StringBuffer html=new StringBuffer();
		HashMap<String, String> unionMapMc=new HashMap<String, String>();
		HashMap<String, String> unionMapDm=new HashMap<String, String>();
		HashMap<String, Integer> addUpMap=new HashMap<String, Integer>();
		HashMap<String, String> joinMap=new HashMap<String, String>();
		String rowspanNum=new String("");
		String jzmcBh="";
		String key="";
		String bjdm="";
		//HashMap<String, Integer> rowspanNums=new HashMap<String, Integer>();
		
		html.append("<span class=\"formbox\">");
		html.append("<table id=\"table_rs\" class=\"dateline\" width=\"100%\">");
		html.append("<thead><tr>");
		//����ͷͷ��
		for (int i = 0; i < jzdjList.size(); i++) {
			html.append("<td width=\"5\" noWrap>");
			html.append(jzdjList.get(i).get("djmc"));
			html.append("</td>");
			
			html.append("<td width=\"5\" noWrap>");
			html.append(jzdjList.get(i).get("djmc"));
			html.append("��");
			html.append("</td>");
			
			html.append("<td width=\"5\" noWrap>��ʦ</td>");
			
			html.append("<td width=\"5\" noWrap>����</td>");
			
			if(i==0){
				html.append("<td width=\"40px\" noWrap>"+Base.YXPZXY_KEY+"</td>");
			}
			
			if(i==(jzdjList.size()-1)){
				html.append("<td width=\"5\" noWrap>�༶</td>");
				html.append("<td width=\"5\" noWrap>����</td>");
			}
		}
		html.append("</tr></thead>");
		//�����б�
		html.append("<tbody>");
		for (int i = 0; i < jxjzList.size(); i++) {
			html.append("<tr>");
			for (int j = 0; j < jzdjList.size(); j++) {
				jzmcBh="jzmc"+jzdjList.get(j).get("djdm");
				key=jxjzList.get(i).get("jzid"+jzdjList.get(j).get("djdm"));
				bjdm=jxjzList.get(i).get("bjdm");
				if((unionMapMc.get(jzmcBh) == null || !unionMapMc.get(jzmcBh).equals(jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm"))))
						|| (unionMapDm.get(jzmcBh) == null || !unionMapDm.get(jzmcBh).equals(key))){
					//rowspanNums.put(jzmcBh, Integer.valueOf(1));
					//��ʼ��
					//��������
					rowspanNum=new String();
					html.append("<td height=\"28\" noWrap align=\"left\"");
					html.append(" rowspan=\"");
					html.append(jzNnion.get(key));
					html.append("\">");
					html.append(nullToString(jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm"))));
					html.append("</td>");
					
					//�̹�����
					html.append("<td height=\"28\" noWrap align=\"left\"");
					html.append(" rowspan=\"");
					html.append(jzNnion.get(key));
					html.append("\">");
					html.append(nullToString(jxjzList.get(i).get("jgmc"+jzdjList.get(j).get("djdm"))));
					html.append("</td>");
					//��ʦ����
					html.append("<td height=\"28\" noWrap align=\"left\"");
					html.append(" rowspan=\"");
					html.append(jzNnion.get(key));
					html.append("\">");
					html.append(nullToString(jxjzList.get(i).get("jsmc"+jzdjList.get(j).get("djdm"))));
					html.append("</td>");
					
					//����
					html.append("<td height=\"28\" noWrap align=\"left\"");
					html.append(" rowspan=\"");
					html.append(jzNnion.get(key));
					html.append("\">");
					html.append("<a href=\"javascript:void(0);\" onclick=\"cxJzjzmdByJzid('");
					html.append(jxjzList.get(i).get("jzid"+jzdjList.get(j).get("djdm")));
					html.append("')\" class=\"name\">");
					html.append(jzRs.get(key));
					html.append("</a>");
					html.append("��");
					html.append("</td>");
					
					//Ժϵ
					if(j == 0){
						html.append("<td height=\"28\" style=\"width:80px;\" noWrap align=\"left\"");
						html.append(" rowspan=\"");
						html.append(jzNnion.get(key));
						html.append("\">");
						html.append(nullToString(jzXymc.get(key)));
						html.append("</td>");
					}
				}
//				else{
//					rowspanNums.get(jzmcBh);
//					rowspanNums.put(jzmcBh, rowspanNums.put(jzmcBh, Integer.valueOf(1)).intValue()+1);
//				}
				//���һ������
				if(j == (jzdjList.size()-1) && !Base.isNull(bjdm)){
					//�༶
					if(jzBjNnion.get(key+bjdm) != null){
						html.append("<td height=\"28\" noWrap align=\"left\"");
						html.append(" rowspan=\"");
						html.append(jzBjNnion.get(key+bjdm));
						html.append("\">");
						html.append(nullToString(jxjzList.get(i).get("bjmc")));
						html.append("</td>");
						jzBjNnion.remove(key+bjdm);
					}
					
					//����
					html.append("<td height=\"28\" noWrap align=\"left\">");
					html.append("<a href=\"#\" class=\"name\"  onclick=\"cxJxjzmdByJzidAndBjdm('");
					html.append(jxjzList.get(i).get("jzid"+jzdjList.get(j).get("djdm")));
					html.append("','");
					html.append(bjdm);
					html.append("','");
					html.append(jxjzList.get(i).get("xb"));
					html.append("')\" >");
					html.append(jxjzList.get(i).get("jzrs"));
					html.append("</a>");
					html.append("(");
					html.append(nullToString(jxjzList.get(i).get("xb")));
					html.append(")");
					html.append("</td>");
				}else if(j == (jzdjList.size()-1) && Base.isNull(bjdm)){
					
					html.append("<td ></td>");
					html.append("<td ></td>");
				}
				
				//
				unionMapMc.put(jzmcBh, jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm")));
				unionMapDm.put(jzmcBh, key);
			}
			html.append("</tr>");
		}
		html.append("</tbody>");
		html.append("</table>");
		html.append("</span>");
		return html.toString();
	}
	
	/**
	 * ��nullת���ַ�
	 * @param value
	 * @return
	 */
	private String nullToString(String value){
		if(value == null){
			return "";
		}
		return value;
	}
	
	/**
	 * ���þ�ѵͳ�Ʊ�
	 * @param jxjzList
	 * @param jzdjList
	 * @return
	 */
	private List<Object> setJxjzTjxNnionTable(List<HashMap<String, String>> jxjzList
			,List<HashMap<String, String>> jzdjList){
		HashMap<String, Integer> mapNnion=new HashMap<String, Integer>();//�ϲ�����
		HashMap<String, Integer> mapRs=new HashMap<String, Integer>();//�ϲ�����
		HashMap<String, String> mapXymc=new HashMap<String, String>();//�ϲ�ѧԺ����
		HashMap<String, Integer> mapBjNnion=new HashMap<String, Integer>();//�ϲ��༶����
		List<Object> jxjzNnion = new ArrayList<Object>();
		
		HashMap<String, String> unionMap=new HashMap<String, String>();
		Integer time=0;
		String key="";
		Integer rs=0;
		String xymc="";
		String bjdm="";
		for (int i = 0; i < jxjzList.size(); i++) {
			for (int j = 0; j < jzdjList.size(); j++) {
				key=jxjzList.get(i).get("jzid"+jzdjList.get(j).get("djdm"));
				rs=Integer.valueOf(jxjzList.get(i).get("jzrs"));
				xymc=jxjzList.get(i).get("xymc");
				bjdm=jxjzList.get(i).get("bjdm");
				if(mapNnion.get(key) != null){
					time=mapNnion.get(key);
					mapNnion.put(key, 
							(time.intValue()+1));
					
				}else{
					//��һ�����ֵ�ǰ����
					if(key != null){
						mapNnion.put(key, 
								Integer.valueOf("1"));
					}
				}
					
					
				unionMap.put(key, 
						"true");
				//������¼
				if(mapRs.get(key) != null){
					mapRs.put(key, Integer.valueOf(mapRs.get(key))+rs);
				}else{
					mapRs.put(key,rs);
				}
				
				//ѧԺ��¼
				if(mapXymc.get(key) != null){
					if(xymc != null && mapXymc.get(key).indexOf(xymc) <0){
						//mapXymc.put(key,mapXymc.get(key)+",<br/>"+xymc);
						mapXymc.put(key,mapXymc.get(key)+","+xymc);
					}
				}else{
					mapXymc.put(key,xymc);
				}
				//�༶��¼
				if(mapBjNnion.get(key+bjdm) != null){
					mapBjNnion.put(key+bjdm, mapBjNnion.get(key+bjdm)+1);
				}else{
					mapBjNnion.put(key+bjdm,1);
				}
				
			}
		}
		
		jxjzNnion.add(mapNnion);
		jxjzNnion.add(mapRs);
		jxjzNnion.add(mapXymc);
		jxjzNnion.add(mapBjNnion);
		return jxjzNnion;
	}
	
	/**
	 * ��ѯ��������ͳ�Ʊ�
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRszjTjb(JxjzglFrom model) throws Exception{
		
		return dao.getRszjTjb(model);
	}
	
	/**
	 * ��ѯ����ѧ����Ϣ�б� ����ҳ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getJxjzmdList(JxjzglFrom model)
			throws Exception {
		List<HashMap<String, String>> jxjzdjList = dao.getJxdjList(new JxjzglFrom());
		return dao.getJxjzmdList(model,jxjzdjList);
	}
	
	/**
	 * ������������
	 * @param outputStream
	 * @param model
	 * @throws Exception
	 */
	public void dcJzTjb(ServletOutputStream os, JxjzglFrom model,HashMap<String, String> title,
			List<HashMap<String, String>> jxjzList) throws Exception {
		List<HashMap<String, String>> jzdjList=dao.getJxJzdj(model);
		//��ȡ�ϲ�����ֵ����
		List<Object> jxjzNnion=setJxjzTjxNnionTable(jxjzList, jzdjList);
		HashMap<String, Integer> jzNnion=(HashMap<String, Integer>)jxjzNnion.get(0);//�ϲ�����
		HashMap<String, Integer> jzRs=(HashMap<String, Integer>)jxjzNnion.get(1);//����ͳ��
		HashMap<String, String> jzXymc=(HashMap<String, String>)jxjzNnion.get(2);//ѧԺ����
		HashMap<String, Integer> jzBjNnion=(HashMap<String, Integer>)jxjzNnion.get(3);//�༶���ƺϲ�
		
		//�ϲ�ʹ�ò���
		HashMap<String, String> unionMapMc=new HashMap<String, String>(); //���ڼ�¼��ͬ������
		HashMap<String, String> unionMapDm=new HashMap<String, String>(); //���ڼ�¼��ͬ�����
		
		String tjxx="";
		// ����excel����
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("ѧ������ͳ��", 0);
		//���õ�Ԫ�����
		ws.setColumnView(3, 35);
		try{
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			WritableCellFormat titleStyle = ExcelMB.getWritableCellFormat(16,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			WritableCellFormat leftTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			// ����
			ws.mergeCells(0, 0, (jzdjList.size()*4+2), 0); 
			ws.addCell(new Label(0, 0,title.get("jxmc")+"ͳ���", titleStyle));
			tjxx=title.get("jxmc")+"(��ѵ����:"+title.get("cxrs")+",�ѱ�������:"+title.get("ybzrs")+",δ��������:"+title.get("wbzrs")+")";
			ws.mergeCells(0, 1, (jzdjList.size()*4+2), 1); 
			ws.addCell(new Label(0, 1,tjxx, wcfTytle));
			
			//����ͷ��
			int index=0;
			for (int i = 0; i < jzdjList.size(); i++) {
				//�ȼ�����
				ws.addCell(new Label(index, 2,jzdjList.get(i).get("djmc"), wcfTytle));
				index++;
				//�ȼ��̹�����
				ws.addCell(new Label(index, 2,jzdjList.get(i).get("djmc")+"��", wcfTytle));
				index++;
				//��ʦ����
				ws.addCell(new Label(index, 2,"��ʦ", wcfTytle));
				index++;
				//����
				ws.addCell(new Label(index, 2,"����", wcfTytle));
				index++;
				
				if(i==0){
					//ѧԺ
					Base.YXPZXY_KEY = message.getMessage("lable.xb");
					ws.addCell(new Label(index, 2,Base.YXPZXY_KEY, wcfTytle));
					index++;
				}
				
				if(i==(jzdjList.size()-1)){
					//�༶
					ws.addCell(new Label(index, 2,"�༶", wcfTytle));
					index++;
					//����
					ws.addCell(new Label(index, 2,"����", wcfTytle));
					index++;
				}
			}
			
			String jzmcBh="";
			String key="";
			String bjdm="";
			//��������
			for (int i = 0; i < jxjzList.size(); i++) {
				index=0;
				for (int j = 0; j < jzdjList.size(); j++) {
					jzmcBh="jzmc"+jzdjList.get(j).get("djdm");
					key=jxjzList.get(i).get("jzid"+jzdjList.get(j).get("djdm"));
					bjdm=jxjzList.get(i).get("bjdm");
					
					if((unionMapMc.get(jzmcBh) == null || !unionMapMc.get(jzmcBh).equals(jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm"))))
							|| (unionMapDm.get(jzmcBh) == null || !unionMapDm.get(jzmcBh).equals(key))){
						//��������
						//��������
						ws.mergeCells(index, 3+i, index, 3+i+jzNnion.get(key).intValue()-1); 
						//ws.setRowView(0, 400); // ����ָ���и�
						ws.addCell(new Label(index, 3+i,jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm")), wcfTytle));
						index++;
						
						//�̹�����
						ws.mergeCells(index, 3+i, index, 3+i+jzNnion.get(key).intValue()-1);  
						ws.addCell(new Label(index, 3+i,jxjzList.get(i).get("jgmc"+jzdjList.get(j).get("djdm")), wcfTytle));
						index++;
						
						//��ʦ����
						ws.mergeCells(index, 3+i, index, 3+i+jzNnion.get(key).intValue()-1);  
						ws.addCell(new Label(index, 3+i,jxjzList.get(i).get("lsmc"+jzdjList.get(j).get("djdm")), wcfTytle));
						index++;
						
						//����
						ws.mergeCells(index, 3+i, index, 3+i+jzNnion.get(key).intValue()-1);  
						ws.addCell(new Label(index, 3+i,String.valueOf(jzRs.get(key)), wcfTytle));
						index++;
						
						//ѧԺ
						if(j == 0){
							ws.mergeCells(index, 3+i, index, 3+i+jzNnion.get(key).intValue()-1);  
							ws.addCell(new Label(index, 3+i,jzXymc.get(key), wcfTytle));
							index++;
						}
					}else{
						index=index+4;
						if(j == 0){
							index++;
						}
					}
						
					
					//�༶������
					if(j == (jzdjList.size()-1)){
						//�༶
						if(jzBjNnion.get(key+bjdm) != null){
							ws.mergeCells(index, 3+i, index, 3+i+jzBjNnion.get(key+bjdm)-1);  
							ws.addCell(new Label(index, 3+i,jxjzList.get(i).get("bjmc"), wcfTytle));
						}
						index++;
						jzBjNnion.remove(key+bjdm);
						
						
						ws.addCell(new Label(index, 3+i,jxjzList.get(i).get("jzrs")+"("+jxjzList.get(i).get("xb")+")", wcfTytle));
						index++;
					}
					
					//
					unionMapMc.put(jzmcBh, jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm")));
					unionMapDm.put(jzmcBh, key);
				}
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}
	
	/**
	 * ��ʼ����ѵ���Ƹ��ڵ�
	 * @param model
	 * @return
	 */
	public boolean initJxjzglRootNode(JxjzglFrom model , User user) throws Exception{
		boolean result=false;
		HashMap<String, String> jxjzglModel=dao.getJxjzModel(model);
		try {
			if(jxjzglModel == null || jxjzglModel.isEmpty()){
				result = dao.insertJxjzwh(model, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ����Ĭ�ϲ�ѯ����
	 * @return
	 */
	public SearchModel setDefaultSearchModel(String jzid,String bjdm) throws Exception{
		List<HashMap<String, String>> jxjzdjList = dao.getJxdjList(new JxjzglFrom());
		HashMap<String, String> jxjzModel= dao.getJxjzModelByJzid(jxjzdjList,jzid);
		SearchModel searchModel = new SearchModel();
		
		if(jxjzModel !=null){
			if(jxjzModel.get("tid") !=null && !"".equals(jxjzModel.get("tid"))){
				searchModel.setSearch_tj_tid(new String[]{jxjzModel.get("tid")});
			}
			
			if(jxjzModel.get("yid") !=null && !"".equals(jxjzModel.get("yid"))){
				searchModel.setSearch_tj_yid(new String[]{jxjzModel.get("yid")});
			}
			
			if(jxjzModel.get("lid") !=null && !"".equals(jxjzModel.get("lid"))){
				searchModel.setSearch_tj_lid(new String[]{jxjzModel.get("lid")});
			}
			
			if(jxjzModel.get("pid") !=null && !"".equals(jxjzModel.get("pid"))){
				searchModel.setSearch_tj_pid(new String[]{jxjzModel.get("pid")});
			}

			if(jxjzModel.get("bid") !=null && !"".equals(jxjzModel.get("bid"))){
				searchModel.setSearch_tj_bid(new String[]{jxjzModel.get("bid")});
			}
			
			if(jxjzModel.get("ssid") !=null && !"".equals(jxjzModel.get("ssid"))){
				searchModel.setSearch_tj_ssid(new String[]{jxjzModel.get("ssid")});
			}
		}
		
		if(bjdm !=null && !"".equals(bjdm)){
			searchModel.setSearch_tj_bj(new String[]{bjdm});
		}
		
		return searchModel;
	}
	
	/**
	 * ɾ��ѧ������
	 * @return
	 */
	public String scXsjz(boolean isJxcj,boolean isJxbx,String xhs,String jzids, User user) throws Exception{
		String message="";
		if(xhs == null || "".equals(xhs) || jzids==null || "".equals(jzids)){
			message="ѧ�Ż��߽��Ʊ��Ϊ��!";
			return message;
		}
		if(isJxcj){
			message="ѧ���ѻ�ȡ�ɼ�,����ȡ������!";
			return message;
		}
		
		if(isJxbx){
			message="ѧ���ѻ�ȡ��ѵ���֣�����ȡ������!";
			return message;
		}
		String[] xh=xhs.split("!!@@!!");
		String[] jzid=jzids.split("!!@@!!");
		boolean result=dao.deleteJxjzglMd(xh, jzid, user);
		if(result){
			message="ȡ�����Ƴɹ�!";
		}else{
			message="ȡ������ʧ��!";
		}
		return message;
	}
	
	/**
	 * ��ѯ��ѵ����ѧ�������б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getJxjzXsmdList(JxjzglFrom model){
		
		return dao.getJxjzXsmdList(model);
	}
	
	/**
	 * ����ѧ��ѧ�Ÿ���listmap
	 * @param list
	 * @return  xh!!@@!!xh
	 */
	public String setXsxhByListMap(List<HashMap<String, String>> list){
		String xhs="";
		if(list == null){
			return xhs;
		}
		for (int i = 0; i < list.size(); i++) {
			if("".equals(xhs)){
				xhs=list.get(i).get("xh");
			}else{
				xhs=xhs+"!!@@!!"+list.get(i).get("xh");
			}
		}
		return xhs;
	}
	
	/**
	 * ������ǰ��ѵ����Html
	 * @return
	 */
	public String createDqjxjzHtml(HashMap<String, String> jxxxwhModel,HashMap<String, String> rs){
		StringBuffer html=new StringBuffer();
		html.append(" ��ѵ���ƣ�");
		html.append(jxxxwhModel.get("jxmc"));
		html.append("&nbsp;&nbsp;");
		html.append("��ѵ������");
		html.append("<a href=\"javascript:void(0);\" style=\"color: blue;text-decoration:underline;\" class=\"name\" onclick=\"cxJzjzmdPage()\">");
		html.append(rs.get("cxrs"));
		html.append("</a>");
		html.append("��&nbsp;&nbsp;");
		html.append("�ѱ���������");
		html.append("<a href=\"javascript:void(0);\" style=\"color: blue;text-decoration:underline;\" class=\"name\" onclick=\"cxJzjzmdPageYbz()\">");
		html.append(rs.get("ybzrs"));
		html.append("</a>");
		html.append("��&nbsp;&nbsp;");
		html.append("��δ����������");
		html.append("<a href=\"javascript:void(0);\" style=\"color: blue;text-decoration:underline;\" class=\"name\" onclick=\"cxJzjzmdPageWbz()\">");
		html.append(rs.get("wbzrs"));
		html.append("</a>");
		html.append("��");
		return html.toString();
	}
	
	/**
	 * ��ѵ���ƹ����Զ��嵼�� 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjzmdExportDataList(JxjzglFrom model)
			throws Exception {
		List<HashMap<String, String>> jxjzdjList = dao.getJxdjList(new JxjzglFrom());
		return dao.getJxjzmdExportDataList(model,jxjzdjList);
	}
	
}