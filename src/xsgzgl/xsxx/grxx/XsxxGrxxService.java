package xsgzgl.xsxx.grxx;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.jygl.njjs.NjjsJyglService;
import xsgzgl.xsxx.cssz.grxx.XsxxCsszService;
import xsgzgl.xsxx.model.CsszModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_������Ϣ_Service��
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

public class XsxxGrxxService extends CommService {
	
	XsxxGrxxDAO dao = new XsxxGrxxDAO();
	
	/**
	 * ���ѧ����Ϣ
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public HashMap<String, String> getXsxxInfo(XsxxGrxxForm model, User user) {

		XsxxCsszService csszService = new XsxxCsszService();

		// �ֶ��б�
		List<HashMap<String, String>> zdList = csszService.getZdszList("",user);

		String userType = user.getUserType();

		if ("stu".equalsIgnoreCase(userType)) {
			model.setXh(user.getUserName());
		}

		//ѧ����Ϣ
		HashMap<String, String> xsInfo = dao.getXsxxInfo(model, zdList);

		HashMap<String, String> map = new HashMap<String, String>();

		if (zdList != null && zdList.size() > 0) {
			for (int i = 0; i < zdList.size(); i++) {
				
				HashMap<String, String> zdInfo = zdList.get(i);
				// �ֶ�
				String zd = zdInfo.get("zd");
				// �ֶ�����
				String zdlx = zdInfo.get("zdlx");
				// �ֶ���֤
				String checked = zdInfo.get("checked");
				// ��
				String source_table = zdInfo.get("source_table");
				// ���ֶ�
				String select_dm = zdInfo.get("select_dm");
				// ������
				String select_mc = zdInfo.get("select_mc");
				// ѧ��Ȩ��
				String xsqx = zdInfo.get("xsqx");
				// ��ʦȨ��
				String lsqx = zdInfo.get("lsqx");
				// �ɷ��޸���ɫ
				String kfxg = "";
				if ("stu".equalsIgnoreCase(userType)) {
					if ("1".equalsIgnoreCase(xsqx)) {
						kfxg = "<a href=\"#\" onclick=\"showEditDiv('"+zd+"');return false;\"><font color=\"blue\">�޸�</font></a>";
					}
				} else {
					if ("1".equalsIgnoreCase(lsqx)) {
						kfxg = "<a href=\"#\" onclick=\"showEditDiv('"+zd+"');return false;\"><font color=\"blue\">�޸�</font></a>";
					}
				}
				
				map.put(zd, xsInfo.get(zd));
				map.put(zd + "_zdlx", zdlx);
				map.put(zd + "_checked", checked);
				map.put(zd + "_source_table", source_table);
				map.put(zd + "_select_dm", select_dm);
				map.put(zd + "_select_mc", select_mc);
				map.put(zd + "_kfxg", kfxg);
				
				
			}
		}
		
		return map;
	}
	
	/**
	 * ��ʾ�ֶ�����Div
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	public void showZdxgDiv(String zd, User user,HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html;charset=gbk");

		XsxxCsszService csszService = new XsxxCsszService();

		// �ֶ��б�
		List<HashMap<String, String>> zdList = csszService
				.getZdszList(zd, user);

		HashMap<String, String> zdInfo = new HashMap<String, String>();

		if (zdList != null && zdList.size() > 0) {
			zdInfo = zdList.get(0);
		}
		
		// �ֶ���
		String zdm = zdInfo.get("zdm");
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
		
		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");
		
		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>ѧ����Ϣ�޸�</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width=\"30%\">");
		html.append(zdm);
		html.append("</th>");
		html.append("<td>");
		
		if("text".equalsIgnoreCase(zdlx)){//�ı��� 
			html.append("<input type=\"text\" name=\""+zd+"_name\" id=\""+zd+"_id\" ");	
			if (!Base.isNull(checked)) {
				String[] validate = checked.split("!!luojw!!");
				
				//����ַ���
				String maxlength = ("0".equalsIgnoreCase(validate[0])) ? ""
						: "maxlength=\"" + validate[0] + "\"";
				
				html.append(maxlength);
				
				// ������֤
				String sp_validate = validate[1];
				
				if("sfzh".equalsIgnoreCase(sp_validate)){//���֤��
					html.append("onblur=\"checkSfzh(this)\" ");
				}else if("num".equalsIgnoreCase(sp_validate)){//������֤
					html.append("onkeydown=\"return onlyNum(this,"+validate[0]+")\" ");
					html.append("onmousedown=\"return onlyNum(this,"+validate[0]+")\" ");
					html.append("style=\"ime-mode:disabled\" ");
				}else if("dzyx".equalsIgnoreCase(sp_validate)){//��������
					html.append("onblur=\"if(isEmail(this.value)){}else{this.value='';alertInfo('���������ʽ����ȷ����ȷ��')}\" ");
				}else if("money".equalsIgnoreCase(sp_validate)){//�����֤
					html.append("onkeyup=\"checkInputNum(this)\"");
					html.append("onblur=\"checkInputNum(this)\"");
					html.append("style=\"ime-mode:disabled;\"");
				}
			}
			html.append(">");
		}else if("calendar".equalsIgnoreCase(zdlx)){//���ڿؼ�
			html.append("<input type=\"text\" name=\""+zd+"_name\" id=\""+zd+"_id\" ");
			html.append("onblur=\"dateFormatChg(this)\" style=\"cursor:hand;\" ");
			html.append("onclick=\"return showCalendar('" + zd + "_id','yyyyMMdd');\" ");
			html.append(">");
		}else if("select".equalsIgnoreCase(zdlx)){//�����б�
			if(Base.isNull(source_table)){
				
				String[] dm = select_dm.split("!!luojw!!");
				String[] mc = select_mc.split("!!luojw!!");
				
				html.append("<select name=\""+zd+"_name\" id=\""+zd+"_id\"> ");
				html.append("<option value=\"\"></option>");
				for(int i=0;i<dm.length;i++){
					html.append("<option value=\"" + dm[i] + "\">" + mc[i] + "</option>");
				}
				html.append("</select> ");
				
			} else {
				List<HashMap<String, String>> optionList = getWhList(
						source_table, select_dm, select_mc, "", "", "", true);
				
				html.append("<select name=\""+zd+"_name\" id=\""+zd+"_id\"> ");
				
				if(optionList!=null && optionList.size()>0){
					for(int i=0;i<optionList.size();i++){
						
						String dm = optionList.get(i).get("dm");
						String mc = optionList.get(i).get("mc");
						
						html.append("<option value=\"" + dm + "\">" + mc + "</option>");
					}
				}
				
				html.append("</select> ");
			}
		}else if("ssx".equalsIgnoreCase(zdlx)){//ʡ����
			NjjsJyglService ssxService = new NjjsJyglService();
			//ʡ�б�
			List<HashMap<String, String>> shenList = ssxService.getShenList();
			
			html.append("<select name=\""+zd+"_shen_name\" id=\"shen\" style=\"width:200px\" onchange=\"changeShen()\"> ");
			html.append("<option value=\"\"></option>");
			
			if(shenList!=null && shenList.size()>0){
				for(int i=0;i<shenList.size();i++){
					
					String dm = shenList.get(i).get("dm");
					String mc = shenList.get(i).get("mc");
					
					html.append("<option value=\"" + dm + "\">" + mc + "</option>");
				}
			}
			
			html.append("</select> ");
			
			html.append("<span id=\"span_shi\" style=\"display:none\"><br/><br/></span>");
			html.append("<select name=\""+zd+"_shi_name\" id=\"shi\" style=\"width:200px;display:none\" onchange=\"changeShi()\"> ");
			html.append("<option value=\"\"></option>");		
			html.append("</select>");
			
			html.append("<span id=\"span_xian\" style=\"display:none\"><br/><br/></span>");
			html.append("<select name=\""+zd+"_xian_name\" id=\"xian\"  style=\"width:200px;display:none\"> ");
			html.append("<option value=\"\"></option>");		
			html.append("</select> ");	
		}else if("szbm".equalsIgnoreCase(zdlx)){//���ڲ���
			
			// �꼶�б�
			List<HashMap<String, String>> njList = Base.getNjList();
			html.append("<select name=\"nj\" id=\"nj\"  style=\"width:180px;\" onchange=\"initZyList();initBjList();\"> ");
			html.append("<option value=\"\"></option>");	
			if(njList!=null && njList.size()>0){
				for(int i=0;i<njList.size();i++){
					
					String dm = njList.get(i).get("nj");
					String mc = njList.get(i).get("nj");
					
					html.append("<option value=\"" + dm + "\">" + mc + "</option>");
				}
			}
			html.append("</select> ");
			html.append("(�꼶)");
			
			// ѧԺ�б�
			List<HashMap<String, String>> xyList = Base.getXyList();
			html.append("<br/><br/>");
			html.append("<select name=\"xy\" id=\"xy\"  style=\"width:180px;\" onchange=\"initZyList();initBjList();\"> ");
			html.append("<option value=\"\"></option>");	
			if(xyList!=null && xyList.size()>0){
				for(int i=0;i<xyList.size();i++){
					
					String dm = xyList.get(i).get("xydm");
					String mc = xyList.get(i).get("xymc");
					
					html.append("<option value=\"" + dm + "\">" + mc + "</option>");
				}
			}
			html.append("</select> ");
			html.append("(" + Base.YXPZXY_KEY + ")");
			
			// רҵ�б�
			List<HashMap<String, String>> zyList = (Base.getZyMap()).get("");
			
			html.append("<br/><br/>");
			html.append("<select name=\"zy\" id=\"zy\"  style=\"width:180px;\" onchange=\"initBjList();\"> ");
			html.append("<option value=\"\"></option>");	
			if(zyList!=null && zyList.size()>0){
				for(int i=0;i<zyList.size();i++){
					
					String dm = zyList.get(i).get("zydm");
					String mc = zyList.get(i).get("zymc");
					
					html.append("<option value=\"" + dm + "\">" + mc + "</option>");
				}
			}
			html.append("</select> ");
			html.append("(רҵ)");
			
			// �༶�б�
			List<HashMap<String, String>> bjList = (Base.getBjMap()).get("");
			
			html.append("<br/><br/>");
			html.append("<select name=\"bj\" id=\"bj\"  style=\"width:180px;\" onchange=\"\"> ");
			html.append("<option value=\"\"></option>");	
			if(bjList!=null && bjList.size()>0){
				for(int i=0;i<bjList.size();i++){
					
					String dm = bjList.get(i).get("bjdm");
					String mc = bjList.get(i).get("bjmc");
					
					html.append("<option value=\"" + dm + "\">" + mc + "</option>");
				}
			}
			html.append("</select> ");
			html.append("(�༶)");
		}
		
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
	
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td width=\"30%\" colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"saveZdxg('"+zd+"','"+zdm+"','"+zdlx+"');return false;\">");
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
	 * �����ֶ��޸�
	 * 
	 * @author luojw
	 */
	public boolean saveZdxg(XsxxGrxxForm model, User user) {
		return dao.saveZdxg(model, user);
	}
	
	/**
	 * ������ڲ���
	 * 
	 * @author ΰ�����
	 * 
	 */
	public String getSzbm(String bjdm) {
		return dao.getSzbm(bjdm);
	}
	
	/**
	 * ���ʡ����
	 * 
	 * @author ΰ�����
	 * 
	 */
	public String getSsx(String ssx) {

		List<HashMap<String, String>> list = dao.getSsx(ssx);

		String qxmc = "";

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				qxmc += " ";
				qxmc += list.get(i).get("qxmc");
			}
		}

		return qxmc;
	}
	
	/**
	 * ��ʼ���ֶ��޸ı�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public static Boolean initZdxgb(String xh) throws Exception {
		return XsxxGrxxDAO.initZdxgb(xh);
	}
	
	/**
	 * �������
	 * 
	 * @author ΰ�����
	 * 
	 */
	public String checkSqzg(CsszModel csszModel) {

		String message = "";

		String sfsh = csszModel.getSfsh();// '�Ƿ����';

		String lcid = csszModel.getLcid();// '����ID';

		String sqkssj = csszModel.getSqkssj();// '���뿪ʼʱ��';

		String sqjssj = csszModel.getSqjssj();// '�������ʱ��';

		String shkssj = csszModel.getShkssj();// '��˿�ʼʱ��';

		String shjssj = csszModel.getShjssj();// '��˽���ʱ��';

		String nowTime = getNowTime("YYYYMMDD");// ��ǰʱ��

		if (Base.isNull(sfsh)) {
			message = "������Ϣ�޸���ز�����δ���ã��޷��޸ģ�����ϵ��ع�����Ա";
		} else if (Integer.parseInt(sqkssj) > Integer.parseInt(nowTime)) {
			message = "��δ�������룬��Ҫ��" + sqkssj + "�ſ����޸�";
		} else if (Integer.parseInt(sqjssj) < Integer.parseInt(nowTime)) {
			message = "��������ʱ���ѽ������������Ҫ�޸ģ�����ϵ��ع�����Ա";
		}

		return message;
	}
	
	/**
	 * ��˿���
	 * 
	 * @author ΰ�����
	 * 
	 */
	public String checkShzg(CsszModel csszModel) {

		String message = "";

		String sfsh = csszModel.getSfsh();// '�Ƿ����';

		String lcid = csszModel.getLcid();// '����ID';

		String sqkssj = csszModel.getSqkssj();// '���뿪ʼʱ��';

		String sqjssj = csszModel.getSqjssj();// '�������ʱ��';

		String shkssj = csszModel.getShkssj();// '��˿�ʼʱ��';

		String shjssj = csszModel.getShjssj();// '��˽���ʱ��';

		String nowTime = getNowTime("YYYYMMDD");// ��ǰʱ��

		if (Base.isNull(sfsh)) {
			message = "������Ϣ�޸���ز�����δ���ã��޷��޸ģ�����ϵ��ع�����Ա";
		} else if ("��".equalsIgnoreCase(sfsh)) {
			message = "������Ϣ�޸�������ˣ�������������ϵ������Ա";
		} else if (Integer.parseInt(shkssj) > Integer.parseInt(nowTime)) {
			message = "��δ������ˣ���Ҫ��" + shkssj + "�ſ����޸�";
		} else if (Integer.parseInt(shjssj) < Integer.parseInt(nowTime)) {
			message = "�������ʱ���ѽ������������Ҫ�޸ģ�����ϵ��ع�����Ա";
		}

		return message;
	}
	
	/**
	 * �����ֶ��޸�
	 * 
	 * @author luojw
	 */
	public String saveXgsq(XsxxGrxxForm model, User user) {

		boolean flag = false;

		// ����ID
		String sqid = model.getSqid();

		// ����IDΪ�գ��µ����룩
		if (Base.isNull(sqid)) {
			try {
				flag = dao.insertXgsq(model, user);

				// �������ID
				sqid = getSqid(model, user);

				model.setSqid(sqid);

				if (flag) {
					//�����˼�¼
					flag = dao.insertXgsh(model, user);
				}
				
				if (flag) {
					//��������ID
					dao.saveSqid(model);
				}
				
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		} else {
			try {
				
				// �޸��޸�����
				flag = dao.updateXgsq(model, user);

				if (flag) {
					// �޸��޸����
					flag = dao.updateXgsh(model, user);
				}

				if (flag) {
					// ��������ID
					dao.saveSqid(model);
				}
				
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}

		return sqid;
	}
	
	/**
	 * �������ID
	 * 
	 * @author luojw
	 */
	public String getSqid(XsxxGrxxForm model, User user) {
		return dao.getSqid(model, user);
	}
	
	/**
	 * ��������Ϣ�б�
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getShInfoList(XsxxGrxxForm model) {

		CsszModel csszModel = model.getCsszModel();

		// ��λ�б�
		List<HashMap<String, String>> gwList = csszModel.getGwList();
		// ����б�
		List<HashMap<String, String>> shList = dao.getShInfoList(model);
		
		if (gwList != null && gwList.size() > 0) {
			for (int i = 0; i < gwList.size(); i++) {
				
				if (shList != null && shList.size() > 0) {
					for (int j = 0; j < shList.size(); j++) {
						if(gwList.get(i).get("gwid").equalsIgnoreCase(shList.get(j).get("gwid"))){
							gwList.get(i).put("shzt", shList.get(j).get("shzt"));
							gwList.get(i).put("shyj", shList.get(j).get("shyj"));
						}
					}
				}
			}
		}

		return gwList;
	}
	
	/**
	 * ����ֶ��޸���Ϣ�б�
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getXgInfoList(XsxxGrxxForm model) {
		return dao.getXgInfoList(model);
	}
	
	/**
	 * �ύ������Ϣ�޸�
	 * 
	 * @author ΰ�����
	 * 
	 */
	public boolean submitGrxx(XsxxGrxxForm model) {

		// �ֶ��޸��б�
		List<HashMap<String, String>> zdxgList = dao.getXgzdList(model);

		// �Ƿ���ѧ����Ϣ���д���
		boolean isXsxxExists = isExists("xsxxb", "xh", model.getXh());
		// �Ƿ���ѧ��������Ϣ���д���
		boolean isFzxxExists = isExists("xsfzxxb", "xh", model.getXh());
		
		boolean flag = true;
		
		if (!isXsxxExists) {
			try {
				flag = dao.copyToXsxxb(model.getXh());
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		
		if (!isFzxxExists) {
			try {
				flag = dao.copyToFzxxb(model.getXh());
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		
		flag = dao.submitGrxx(model, zdxgList);
		
		return flag;
	}
	
	/**
	 * ����޸�����б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXgshList(XsxxGrxxForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getXgshList(model, user);
	}
	
	/**
	 * ����޸����Html
	 * 
	 * @author ΰ�����
	 */
	public String getXgshHtml(SearchRsModel rsModel, XsxxGrxxForm model,
			ArrayList<String[]> rsArrList, User user) {
		
		// IE�汾
		String ie = rsModel.getIe();
		// ��ʽ·��
		String stylePath=rsModel.getStylePath();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {
			
			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				String sqid = rs[0];
				String xh = rs[1];
				String xm = rs[2];
				String bjmc = rs[3];
				String sqsj = rs[4];
				String shzt = rs[5];
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				spHtml.append("<td align=\"left\" width=\"5px\">");
				spHtml.append("<input type=\"checkbox\" name=\"checkVal\" ");
				spHtml.append("value=\"" + sqid + "\"/>");
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xh);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xm);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(bjmc);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(sqsj);
				spHtml.append("</td>");
				
				String pic_name = "";
				
				if ("δ���".equalsIgnoreCase(shzt)) {
					pic_name = "dsh";
				} else if ("ͨ��".equalsIgnoreCase(shzt)) {
					pic_name = "shtg";
				} else if ("��ͨ��".equalsIgnoreCase(shzt)) {
					pic_name = "shbtg";
				} else if ("�˻�".equalsIgnoreCase(shzt)) {
					pic_name = "shth";
				} else if ("������".equalsIgnoreCase(shzt)) {
					pic_name = "shxcs";
				}
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append("<p><img src=\""+stylePath+"images/ico_"+pic_name+".gif\" width=\"52\" height=\"18\" /></p>");
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
			}
		}
		return spHtml.toString();
	}
	
	/**
	 * ���ѧ����Ϣ
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public HashMap<String, String> getXgxxInfo(XsxxGrxxForm model, User user) {

		XsxxCsszService csszService = new XsxxCsszService();

		// �ֶ��б�
		List<HashMap<String, String>> zdList = csszService.getZdszList("",user);

		String userType = user.getUserType();

		//ѧ����Ϣ
		HashMap<String, String> xsInfo = dao.getXsxxInfo(model, zdList);

		HashMap<String, String> map = new HashMap<String, String>();

		if (zdList != null && zdList.size() > 0) {
			for (int i = 0; i < zdList.size(); i++) {
				
				HashMap<String, String> zdInfo = zdList.get(i);
				// �ֶ�
				String zd = zdInfo.get("zd");
				// �ֶ�����
				String zdlx = zdInfo.get("zdlx");
				// �ֶ���֤
				String checked = zdInfo.get("checked");
				// ��
				String source_table = zdInfo.get("source_table");
				// ���ֶ�
				String select_dm = zdInfo.get("select_dm");
				// ������
				String select_mc = zdInfo.get("select_mc");
				// ѧ��Ȩ��
				String xsqx = zdInfo.get("xsqx");
				// ��ʦȨ��
				String lsqx = zdInfo.get("lsqx");
				
				map.put(zd, xsInfo.get(zd));
			}
		}
		
		return map;
	}
	
	/**
	 * �������״̬
	 * 
	 * @author luojw
	 */
	public boolean saveShzt(XsxxGrxxForm model, User user) {

		boolean flag = false;

		CsszModel csszModel = model.getCsszModel();

		List<HashMap<String, String>> gwList = csszModel.getGwList();

		// ���״̬
		String shzt = model.getShzt();
		// ��˸�λ
		String shgw = model.getShgw();
		// ��˸�λ����
		String shgwmc = "";
		// �ϼ���λID
		String pre_gwid = "";
		// �¼���λID
		String next_gwid = "";
		// �Ƿ���С
		boolean isMin = false;
		// �Ƿ����
		boolean isMax = false;
		
		if (gwList != null && gwList.size() > 0) {
			for (int i = 0; i < gwList.size(); i++) {
				HashMap<String, String> gwInfo = gwList.get(i);
				String gwid = gwInfo.get("gwid");
				String gwmc = gwInfo.get("gwmc");
				String lv = gwInfo.get("lv");
				String maxlv = gwInfo.get("maxlv");
				
				if (shgw.equalsIgnoreCase(gwid)) {
					
					if ("1".equalsIgnoreCase(lv)) {
						isMin = true;
					}

					if (lv.equalsIgnoreCase(maxlv)) {
						isMax = true;
					} 
					
					shgwmc = gwmc;
					
					if (!isMin) {
						pre_gwid = gwList.get(i - 1).get("gwid");
					}

					if (!isMax && !"1".equalsIgnoreCase(maxlv)) {
						next_gwid = gwList.get(i + 1).get("gwid");
					}
					
					break;
				}
			}
		}
		
		model.setMax(isMax);
		model.setMin(isMin);
		model.setShgwmc(shgwmc);
		model.setPre_gwid(pre_gwid);
		model.setNext_gwid(next_gwid);
		
		try {

			// �޸����״̬
			flag = updateShzt(model, user);

			if ("�˻�".equalsIgnoreCase(shzt)) {		
				dao.updateCszt(model, user);
			}
					
			if ("ͨ��".equalsIgnoreCase(shzt)) {

				if (isMax) {
					submitGrxx(model);
				}else{
					dao.updateTgzt(model, user);
				}
			}
			
			if (flag) {
				// ����������
				flag = dao.updateSqjg(model, user);
			}
			
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * �޸����״̬
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public boolean updateShzt(XsxxGrxxForm model, User user) throws Exception {

		// ����ID
		String sqid = model.getSqid();
		// ��λID
		String gwid = model.getShgw();

		// ������Ϣ�޸���˱�
		String tableName = "xg_xsxx_grxx_xgshb";
		// ����
		String pk = "sqid||gwid";
		// ����ֵ
		String[] pkValue = new String[] { sqid + gwid };
		// �޸��ֶ�
		String[] onezd = new String[] { "shr", "shzt", "shsj", "shyj" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		// �����
		String shr = user.getUserName();
		model.setShr(shr);

		// ����ʱ��
		String shsj = dao.getNowTime("YYYYMMDD");
		model.setShsj(shsj);

		return updateInfoInDb(saveForm, model, user);
	}
	
	/**
	 * ��ý����ѯ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getJgcxList(XsxxGrxxForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getJgcxList(model, user);
	}
	
	/**
	 * ����޸Ľ��Html
	 * 
	 * @author ΰ�����
	 */
	public String getJgcxHtml(SearchRsModel rsModel, XsxxGrxxForm model,
			ArrayList<String[]> rsArrList, User user) {
		
		// IE�汾
		String ie = rsModel.getIe();
		// ��ʽ·��
		String stylePath=rsModel.getStylePath();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {
			
			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				String sqid = rs[0];
				String xh = rs[1];
				String xm = rs[2];
				String bjmc = rs[3];
				String sqsj = rs[4];
				String sqjg = rs[5];
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				spHtml.append("<td align=\"left\" width=\"5px\">");
				spHtml.append("<input type=\"checkbox\" name=\"checkVal\" ");
				spHtml.append("value=\"" + sqid + "\"/>");
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xh);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xm);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(bjmc);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(sqsj);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(sqjg);
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
			}
		}
		return spHtml.toString();
	}
	
	/**
	 * ����������Ϣ
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getShrList(XsxxGrxxForm model,
			SearchRsModel rsModel) {

		CsszModel csszModel = model.getCsszModel();

		// ��λ�б�
		List<HashMap<String, String>> gwList = csszModel.getGwList();
		// ������б�
		List<HashMap<String, String>> shrList = dao.getShrList(model);

		if (gwList != null && gwList.size() > 0) {
			for (int i = 0; i < gwList.size(); i++) {
				HashMap<String, String> gwInfo = gwList.get(i);

				for (int j = 0; j < shrList.size(); j++) {
					HashMap<String, String> shrInfo = shrList.get(j);

					if (gwInfo.get("gwid")
							.equalsIgnoreCase(shrInfo.get("gwid"))) {

						String shzt = shrInfo.get("shzt");
						String pic_name = "";

						if ("δ���".equalsIgnoreCase(shzt)) {
							pic_name = "dsh";
						} else if ("ͨ��".equalsIgnoreCase(shzt)) {
							pic_name = "shtg";
						} else if ("��ͨ��".equalsIgnoreCase(shzt)) {
							pic_name = "shbtg";
						} else if ("�˻�".equalsIgnoreCase(shzt)) {
							pic_name = "shth";
						} else if ("������".equalsIgnoreCase(shzt)) {
							pic_name = "shxcs";
						}

						String pic = "<p><img src=\"" + rsModel.getStylePath()
								+ "images/ico_" + pic_name
								+ ".gif\" width=\"52\" height=\"18\" /></p>";

						gwList.get(i).put("xm", shrInfo.get("xm"));
						gwList.get(i).put("gwmc", shrInfo.get("gwmc"));
						gwList.get(i).put("shzt", pic);
						gwList.get(i).put("shsj", shrInfo.get("shsj"));
						gwList.get(i).put("shyj", shrInfo.get("shyj"));
					}

				}
			}
		}

		return gwList;
	}
}
