package xsgzgl.wjcf.cfsjwh;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.comm.BasicService;

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
 * Author: ltt
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-17 ����03:45:48
 * </p>
 */
public class WjcfCfsjwhService extends BasicService {
	
	/**
	 * ���ý����ѯ��ͷ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "xn", "xqmc", "xh", "xm", "bjmc","cflbmc", "cfyymc", "fwsj", "fwjg","sjly" };
		String[] cn = new String[] { "", "ѧ��", "ѧ��", "ѧ��", "����", "�༶","�������", "����ԭ��", "����ʱ��", "���Ľ��" };
		return dao.arrayToList(en, cn);
	}

	/**
	 * ��ʼ�������ѯ��Ϣ
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initCfcxManage(RequestForm rForm, HttpServletRequest request) {
		DAO dao = DAO.getInstance();
		// ����ģ��
		String gnmk = "wjcf";
		// ����·��
		String path = "wjcfsjCx.do";
		// ========================����ֶ� begin=========================
		// �ֶ���
		String[] en = new String[] { "cfid", "xn", "xq", "xh", "xm", "cflbmc", "cfyymc", "cwsj", "cwwh", "fwjg" };
		// ������
		String[] cn = new String[] { "cfid", "xn", "xq", "xh", "xm", "cflbmc", "cfyymc", "cwsj", "cwwh", "fwjg" };
		// ��ͷ
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		// ========================����ֶ� end=========================
		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();
		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);
		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);
		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);
		// ===============ͨ������ end ============
		rForm.setQtzdz(new String[] {});// ������������
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(en);
		rForm.setTopTr(topTr);
		rForm.setSearch("true");
		rForm.setCommSetting(commSetting);
	}

	/**
	 * ��ȡ��������ճ̹�����Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCfjgList(WjcfCfsjwhActionForm model) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.getCfjgList(model);
	}

	

	/**
	 * ���������
	 * 
	 * @author qlj
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
				html.append(" value='" + replaceHtml(rs[0]) + "'");
				html.append(" /> ");
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[1]) + "'/> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 3) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
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
	 * ��������
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public boolean saveCfsb(WjcfCfsjwhActionForm form) throws Exception{
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		InputStream instream = form.getFj().getInputStream();
		String fjmc = StringUtils.isNotNull(form.getFjmc()) ? form.getFjmc()
				.substring(form.getFjmc().lastIndexOf("\\")+1,
						form.getFjmc().length()) : "";
		form.setFjmc(fjmc);
		form.setCflbmc(dao.cflbmc(form.getCflbdm()));
		form.setCfyymc(dao.cfyymc(form.getCfyydm()));
		return dao.saveCfsj(form, instream);
	}
	
	/**
	 * �鿴������Ϣ
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cfsjwhCk(String cfid) throws Exception{
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.cfsjwhCk(cfid);
	}
	/**
	 * 
	 * @����:��ѯ�Ƿ���Ա��ְ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-1 ����11:09:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfid
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public HashMap<String,String> getZwAndZzmm(String xh) throws Exception{
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.getZwAndZzmm(xh);
	}
	
	/**
	 * ɾ��������Ϣ
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfsjwhSc(String[] cfid) throws Exception {
		List<String[]> params = new ArrayList<String[]>();		
		for(String str : cfid){
			if (StringUtils.isNull(str)) continue;
			params.add(new String[]{str});
		}
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		boolean f =dao.cfsjwhSc(params); 
		f = dao.cfSbSsJcSc(cfid);
		return f;
	}
	
	/**
	 * �޸Ĵ�����Ϣ
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfsjwhXg(WjcfCfsjwhActionForm form) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.cfsjwhXg(form);
	}
	
	/**
	 * ���洦��������Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfssjgBc(WjcfCfsjwhActionForm form) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.cfssjgBc(form);
	}
	
	/**
	 * ���洦�ֽ����Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfjcjgBc(WjcfCfsjwhActionForm form) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.cfjcjgBc(form);
	}
	
	/**
	 * ���洦����ֹ��Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfzzjgBc(WjcfCfsjwhActionForm form) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.cfzzjgBc(form);
	}

	/**
	 * ��ѯ������Ϣ
	 * @param form
	 * @return
	 */
	public InputStream fjCx(WjcfCfsjwhActionForm form) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfb where cfid = ?", new String[]{form.getCfid()}, "fj");
		return blob.getBinaryStream();
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯѧ�������б�
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, Object>> getStuWjcfList(String xh) {
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "Υ�ʹ���");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, getStuWjcfAllList(xh));
		rs.add(map);
		return rs;
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯΥ�ʹ����б�
	 * @param xh
	 * @return
	 */
	public List<String[]> getStuWjcfAllList(String xh) {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		String[] title = {"ѧ��", "ѧ��", "�������", "����ԭ��", "����ʱ��", "�����ĺ�", "���ս��"};
		List<String[]> rs = new ArrayList<String[]>();
		rs.addAll(dao.getStuWjcfList(xh));
		rs.add(0, title);
		return rs;
	}
	/**
	 * ����ά���Զ��嵼��
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCfjgExportList(WjcfCfsjwhActionForm model,User user) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.getCfjgExportList(model,user);
	}
	
	/**
	 * 
	 * @����:TODO(����������Դhtml)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-9 ����09:20:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String createSearchSylyHTML(SearchRsModel rsModel,List<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'");
				html.append(" /> ");
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[1]) + "'/> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length-2; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 3) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				
				html.append("<td style=\"display:none\">");
				html.append("<input type='hidden' name='sjly'  ");
				html.append(" value='" + replaceHtml(rs[10]) + "'/> ");
				html.append("</td>");
				
				html.append("<td style=\"display:none\">");
				html.append("<input type='hidden' name='jcwh'  ");
				html.append(" value='" + replaceHtml(rs[11]) + "'/> ");
				html.append("</td>");
				
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	
	/**
	 * 
	 * @����:TODO(��ѯ���־�������Ϣ)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-14 ����01:58:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> cfjdsxx(String cfid) throws Exception{
		
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		
		return dao.cfjdsxx(cfid);
	}
	
	

}
