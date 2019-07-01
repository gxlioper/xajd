package xsgzgl.wjcf.xscfcx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.comm.BasicService;
import xsgzgl.wjcf.cfssgl.WjcfCfssglForm;
import xsgzgl.wjcf.cfssgl.WjcfCfssglServices;


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
 * Time:2012-7-17 ����12:36:30
 * </p>
 */
public class WjcfXscfService  extends BasicService {

	/**
	 * ѧ����ѯ������Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> xscfCx(WjcfXscfActionForm model)
			throws Exception {
		WjcfXscfDao dao = new WjcfXscfDao();
		return dao.xscfCx(model);
	}
	
	/**
	 * ���ý����ѯ��ͷ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "xn", "xq", "cflbmc", "cfyymc", "fwsj",
				"fwwh", "fwjg", "cz" };
		String[] cn = new String[] { "", "ѧ��", "ѧ��", "�������", "����ԭ��", "����ʱ��","�����ĺ�", "���Ľ��", "����" };
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
		String[] en = new String[] { "cfid", "xn", "xq", "cflbmc", "cfyymc", "fwsj",
				"fwwh", "fwjg", "cz"  };
		// ������
		String[] cn = new String[] { "cfid", "xn", "xq", "cflbmc", "cfyymc", "fwsj",
				"fwwh", "fwjg", "cz" };
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
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"5%\" >");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'");
				html.append(" /> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length-6; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length-5) + "%\" ");
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
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"15%\" >");
				if ("���ֳ���".equalsIgnoreCase(rs[7])) {
					if (StringUtils.isNotNull(rs[9])) {
						if ("wsh".equalsIgnoreCase(rs[9])) {//δ���ǰ��ȡ��
							html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">�鿴</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='qxss(this);return false;' ><font color=\"blue\">ȡ������</font></a>");
						} else {//����˲��ܲ���
							html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">�鿴</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='return false;' disabled='true'><font color=\"blue\">ȡ������</font></a>");
						}
					} else {
						if (StringUtils.isNotNull(rs[11]) && rs[11].contains("xs")) {//���ؿ��ƿ�����
							String[] array = StringUtils.isNotNull(rs[13]) ? rs[13].split("!!") : new String[]{};
							if (array != null && array.length == 2) {
								double time = !StringUtils.isNull(array[0]) ? Double.parseDouble(array[0]) : 0;
								double c = !StringUtils.isNull(array[1]) ? Double.parseDouble(array[1]) : 0;
								if (time <= c) {//��ʱ�����ڿ�����
									html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">�鿴</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='cfsscl(this);return false;' ><font color=\"blue\">����</font></a>");
								} else {
									//����ʱ�����ڲ�������
									html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">�鿴</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='return false;' disabled='true'><font color=\"blue\">����</font></a>");
								}
							} else {//δ֪�������������
								html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">�鿴</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='cfsscl(this);return false;' ><font color=\"blue\">����</font></a>");
							}
						} else {//���ؿ��Ʋ�������
							html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">�鿴</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='return false;' disabled='true'>����</a>");
						}
					}
					
				} else if ("ά��ԭ����".equalsIgnoreCase(rs[7])
						|| "���Ĵ���".equalsIgnoreCase(rs[7])
						|| "��������".equalsIgnoreCase(rs[7])
						|| "��������".equalsIgnoreCase(rs[7])) {
					if (StringUtils.isNotNull(rs[10])) {
						if ("wsh".equalsIgnoreCase(rs[10])) {//δ���ǰ��ȡ��
							html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">�鿴</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='qxjc(this);return false;' ><font color=\"blue\">ȡ���������</font> </a>");
						} else {//����˲��ܲ���
							html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">�鿴</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='return false;' disabled='true'><font color=\"blue\">ȡ���������</font></a>");
						}
					} else {
						if (StringUtils.isNotNull(rs[12]) && rs[12].contains("xs")) {//���ؿ��ƿ�����
							html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">�鿴</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='cfjccl(this);return false;' ><font color=\"blue\">������</font></a>");
						} else {//���ؿ��Ʋ�������
							html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">�鿴</font></a>");
						}
					}
				} else {
					html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">�鿴</font></a>");
				}
				html.append("</td>");
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	
	/**
	 * �������߱���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean xsssSave(WjcfXscfActionForm model) throws Exception{
		boolean result = false;
		WjcfXscfDao dao = new WjcfXscfDao();
		String fjmc = StringUtils.isNotNull(model.getFjmc()) ? model.getFjmc()
				.substring(model.getFjmc().lastIndexOf("\\")+1,
						model.getFjmc().length()) : "";
		model.setFjmc(fjmc);
		result = dao.xsssSave(model);
		if (result) {
			WjcfCfssglServices service = new WjcfCfssglServices();
			WjcfCfssglForm form = new WjcfCfssglForm(); 
			form.setCfid(model.getCfid());
			result = service.cfssshZj(form);
		}
		return result;
	}
	
	/**
	 * ������������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfjcSave(WjcfXscfActionForm model) throws Exception {
		boolean result = false;
		WjcfXscfDao dao = new WjcfXscfDao();
		result = dao.cfjcSave(model);
		if (result) {
			List<HashMap<String, String>> shlcList=dao.getJcshpList();
			List<WjcfCfssglForm> modelList=new ArrayList<WjcfCfssglForm>();
			WjcfCfssglForm cf=null;
			for (int i=0;i< shlcList.size();i++) {
				cf=new WjcfCfssglForm();
				cf.setCfid(model.getCfid());
				cf.setXtgwid(shlcList.get(i).get("spgw"));
				cf.setShzt("wsh");
				modelList.add(cf);
			}
			result = dao.cfssshZj(modelList);
		}
		return result;
	}
	
	/**
	 * ȡ������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean xsssCx(WjcfXscfActionForm model) throws Exception{
		boolean result = false;
		WjcfXscfDao dao = new WjcfXscfDao();
		result = dao.xsssCx(model);
		if (result) {
			result = dao.cfsssplSc(model.getCfid());
		}
		return result; 
	}
	
	/**
	 * ȡ���������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean jcsqCx(WjcfXscfActionForm model) throws Exception{
		WjcfXscfDao dao = new WjcfXscfDao();
		boolean result = false;
		result = dao.jcsqCx(model);
		if (result) {
			result = dao.cfjcsplSc(model.getCfid());
		}
		return result;
	}
}
