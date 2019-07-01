package xsgzgl.qgzx.cxtj;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.Arrays2;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.comm.BasicService;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import common.Globals;

/**
 * �ڹ���ѧ-��ѯͳ��-���ͳ�Ƶ���
 * 
 * @author yeyipin
 * @since 2012.9.19
 */
public class QgzxCxtjService extends BasicService {

	QgzxCxtjDAO qgzxCxtjDAO = new QgzxCxtjDAO();

	/**
	 * ��ñ�ͷ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		DAO dao = DAO.getInstance();
		String qgzq = QgCommUtilf.getQgzq();
	
	   String[] en = new String[] { "", "r", "xn", "bmmc", "gwmc", "gwxzmc",
			"xqrs", "zgrs", "tgrs" };
	   String[] cn = new String[] { "", "�к�", "ѧ��", "���˲���", "��λ����", "��λ����",
			"��������", "�ڸ�����", "�˸�����" };

		if("xq".equals(qgzq)){
			   en = new String[] { "", "r", "xn","xqmc", "bmmc", "gwmc", "gwxzmc",
						"xqrs", "zgrs", "tgrs" };
			   cn = new String[] { "", "�к�", "ѧ��","ѧ��", "���˲���", "��λ����", "��λ����",
						"��������", "�ڸ�����", "�˸�����" };
		}
		
		if ("xsgw".equalsIgnoreCase(type)) {
			en = new String[] { "", "r", "xn", "yrbmmc","sgsj", "gwmc", "gwxzmc",
					"xh", "xm", "nj", "bjmc", "zgztmc","zymc","xymc","yhkh","zybjmc" };
			cn = new String[] { "", "�к�", "ѧ��", "���˲���","�ϸ�ʱ��", "��λ����", "��λ����", "ѧ��",
					"����", "�꼶", "�����༶", "�ڸ�״̬", "רҵ����", "ѧԺ����", "���п���","רҵ�༶" };
			if("xq".equals(qgzq)){
				en = new String[] { "", "r", "xn","xqmc", "yrbmmc","sgsj", "gwmc", "gwxzmc",
						"xh", "xm", "nj", "bjmc", "zgztmc","zymc","xymc","yhkh","zybjmc" };
				cn = new String[] { "", "�к�", "ѧ��","ѧ��", "���˲���","�ϸ�ʱ��", "��λ����", "��λ����", "ѧ��",
						"����", "�꼶", "�����༶", "�ڸ�״̬", "רҵ����", "ѧԺ����", "���п���","רҵ�༶" };
			}
		} else if ("jfhb".equalsIgnoreCase(type)) {
			en = new String[] { "", "r", "nd", "bmmc", "hbzje", "yffje", "syje" };
			cn = new String[] { "", "�к�", "���", "���˲���", "�����ܽ��<Ԫ>", "�ѷ��Ž��<Ԫ>",
					"ʣ����<Ԫ>" };
			if("1".equals(new QgzxCsszService().getJfhbfs())){
				cn = new String[] { "", "�к�", "����", "���˲���", "�����ܽ��<Ԫ>", "�ѷ��Ž��<Ԫ>", "ʣ����<Ԫ>" };
			}
		} else if ("cjff".equalsIgnoreCase(type)) {
			if("13855".equals(Base.xxdm)){
			en = new String[] { "", "xh", "xm", "bjmc", "yrdwmc", "gwmc",
					"gwxzmc", "ygmc", "yhkh", "bz", "ny", "je", "khdj" };
			cn = new String[] { "", "ѧ��", "����", "�༶����", "���˲���", "��λ����", "��λ����",
					"��������", "���п���", "��ע", "��������", "���<Ԫ>", "���˵ȼ�" };
			}
			else{
				en = new String[] { "", "xh", "xm", "bjmc", "yrdwmc", "gwmc",
						"gwxzmc", "ygmc", "yhkh", "bz", "ny", "je","zybjmc"};
				cn = new String[] { "", "ѧ��", "����", "�����༶", "���˲���", "��λ����", "��λ����",
						"��������", "���п���", "��ע", "��������", "���<Ԫ>","רҵ�༶"};
			}
		}
		return dao.arrayToList(en, cn);
	}

	/**
	 * ��λ��Ϣ��ѯ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> gwxxCx(QgzxCxtjForm model,User user) throws Exception {
		return qgzxCxtjDAO.gwxxCx(model,user);
	}
	public List<HashMap<String, String>> getExportList(QgzxCxtjForm t, User user)
			throws Exception {
		return qgzxCxtjDAO.getExportList(t,user);
	}
	public List<HashMap<String,String>> getGwRyList(String type,String gwdm){
		return qgzxCxtjDAO.getGwryList(type,gwdm);
	}
	/**
	 * ��λ��Ϣ�鿴
	 * 
	 * @param myForm
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, String> gwxxCk(QgzxCxtjForm model)
			throws SQLException {
		// ��λ��Ϣ
		HashMap<String, String> rs = qgzxCxtjDAO.gwxxCk(model);
		// ����ڸ���Ա������html
		model.setZgzt("zg");
		List<HashMap<String, String>> zgryxxList = getGwxsxxList(model);
		rs.put("zgryHtml", createRyxxHtml(zgryxxList, model));
		// ����˸���Ա������html
		model.setZgzt("tg");
		List<HashMap<String, String>> tgryxxList = getGwxsxxList(model);
		rs.put("tgryHtml", createRyxxHtml(tgryxxList, model));
		return rs;
	}

	/**
	 * ���ݸ�λ���ڸ�״̬���ѧ����Ϣ�б�
	 * 
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	private List<HashMap<String, String>> getGwxsxxList(QgzxCxtjForm model)
			throws SQLException {
		String[] xhs = qgzxCxtjDAO.getRyxhList(model);
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> ryxxList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < xhs.length; i++) {
			model.setXh(xhs[i]);
			HashMap<String, String> rs = qgzxCxtjDAO.getGwxsxx(model);
			if (rs != null && rs.size() != 0) {
				rs.put("r", String.valueOf(i + 1));
				// �ж��Ƿ�������
				rs.put("sfkns", dao.isKns(xhs[i]) ? "��" : "��");
				ryxxList.add(rs);
			}

		}
		return ryxxList;
	}

	/**
	 * ������Ա��ϢHTML
	 * 
	 * @param zgryxxList
	 * @param model
	 * @return
	 */
	private String createRyxxHtml(List<HashMap<String, String>> rsArrList,
			QgzxCxtjForm model) {
		StringBuilder html = new StringBuilder();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				HashMap<String, String> rs = rsArrList.get(i);
				html.append("<tr><td>" + rs.get("r") + "</td>");
				html.append("<td>" + rs.get("xh") + "</td>");
				html.append("<td>" + rs.get("xm") + "</td>");
				html.append("<td>" + rs.get("bjmc") + "</td>");
				html.append("<td>" + rs.get("sfkns") + "</td>");
				html.append("<td>" + rs.get("sgsj") + "</td>");
				if ("tg".equalsIgnoreCase(model.getZgzt())) {
					html.append("<td>" + rs.get("tgsj") + "</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * ѧ����λ��ѯ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> xsgwCx(QgzxCxtjForm model,User user) throws Exception {
		return qgzxCxtjDAO.xsgwCx(model,user);
	}
	public List<HashMap<String,String>> xsgzjlCx(QgzxCxtjForm model,User user) throws Exception {
		return qgzxCxtjDAO.xsgzjlCx(model,user);
	}
	public List<HashMap<String,String>> xsgzjlMxCx(QgzxCxtjForm model,User user) throws Exception {
		return qgzxCxtjDAO.xsgzjlMxCx(model,user);
	}
	public List<HashMap<String,String>> xsgzCx(QgzxCxtjForm model,User user) throws Exception {
		return qgzxCxtjDAO.xsgzCx(model,user);
	}
	public List<HashMap<String,String>> xsgzffCx(String xh) throws Exception {
		return qgzxCxtjDAO.xsgzffCx(xh);
	}
	public List<HashMap<String,String>> dwgzCx(QgzxCxtjForm model,User user) throws Exception {
		return qgzxCxtjDAO.dwgzCx(model,user);
	}
	/**
	 * ѧ����λ�鿴
	 * 
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, String> xsgwCk(QgzxCxtjForm model)
			throws SQLException {
		// ��λ��Ϣ
		DAO dao = DAO.getInstance();
		HashMap<String, String> rs = qgzxCxtjDAO.xsgwCk(model);
		rs.put("sfkns", dao.isKns(model.getPkValue().split("!!@@!!")[1]) ? "��"
				: "��");
		return rs;
	}
	
	/**
	 * ѧ����λ��Ϣ
	 */
	public HashMap<String, String> getXsgwxx(QgzxCxtjForm model) {
		return qgzxCxtjDAO.getXsgwxx(model);
	}
	
	/**
	 * ��ȡѧ����λ��Ϣ�б�
	 */
	public List<HashMap<String, String>> getXsgwxxList(String xh) {
		return qgzxCxtjDAO.getXsgwxxList(xh);
	}

	/**
	 * ���ѻ�����ѯ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> jfhbCx(QgzxCxtjForm model) throws Exception {
		return qgzxCxtjDAO.jfhbCx(model);
	}

	/**
	 * ���ѻ����鿴
	 * 
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, String> jfhbCk(QgzxCxtjForm model)
			throws SQLException {
		HashMap<String, String> rs = qgzxCxtjDAO.jfhbCk(model);
		List<HashMap<String, String>> jfhbxmList = qgzxCxtjDAO
				.jfhbxmList(model);
		String jfhbList = createjfhbHtml(jfhbxmList, model);
		rs.put("jfhbList", jfhbList);
		return rs;
	}

	/**
	 * �������ѻ���HTML
	 * 
	 * @param myForm
	 * @param list
	 * @return
	 */
	private String createjfhbHtml(List<HashMap<String, String>> list,
			QgzxCxtjForm model) {
		StringBuilder html = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			html.append("<tr><td width='5%'>");
			html.append(i + 1);
			html.append("</td><td width='15%'>");
			html.append(list.get(i).get("hbsj"));
			html.append("</td><td width='15%'>");
			html.append(list.get(i).get("hbje"));
			html.append("</td><td width='65%'>");
			if (list.get(i).get("bz") != null) {
				html.append(list.get(i).get("bz"));
			}
			html.append("</td></tr>");
		}

		return html.toString();
	}

	/**
	 * ��𷢷Ų�ѯ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> cjffCx(QgzxCxtjForm model) throws Exception {
		return qgzxCxtjDAO.cjffCx(model);
	}

	/**
	 * ��𷢷Ų鿴
	 * 
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, String> cjffCk(QgzxCxtjForm model)
			throws SQLException {
		return qgzxCxtjDAO.cjffCk(model);
	}

	/**
	 * ����ҳ��
	 * 
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		html.append("<input type = 'hidden' name ='rsSize' id = 'rsSize' value='"+rsArrList.size()+"'/>");
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html
						.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				html
						.append("<td align=\"left\" nowrap=\"nowrap\" width=\"10px\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				// ѧ����λ��ѯ�ͳ�𷢷Ų�ѯ��ͻ
				if (13 == rs.length) {
					html
							.append("<a href='javascript:void(0);' class='name' onclick='zxsxxView(&quot;"
									+ replaceHtml(rs[1])
									+ "&quot;);return false;'>"
									+ replaceHtml(rs[1]) + "</a>");
					html.append("</td>");
				} else {
					html.append(replaceHtml(rs[1]));
				}

				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 2; j < rs.length; j++) {
					if(j == 9){
						html.append("<td id='bz"+i+"' title='"+replaceHtml(rs[j])+"' align=\"left\" nowrap=\"nowrap\" width=\""
								+ 100 / (rs.length - 2) + "%\" ");
					}else{
						html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""
								+ 100 / (rs.length - 2) + "%\" ");
					}
					
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					// ����ѧ������ //ѧ����λ��ѯ�ͳ�𷢷Ų�ѯ��ͻ
					if (j == 7 && 14 == rs.length) {
						html
								.append("<a href='javascript:void(0);' class='name' onclick='zxsxxView(&quot;"
										+ replaceHtml(rs[j])
										+ "&quot;);return false;'>"
										+ replaceHtml(rs[j]) + "</a>");
					} else if(j==9){
						if(rs[j].length()>12){
							html.append(replaceHtml(rs[j].substring(0, 11)+"..."));
							
						}else{
							html.append(replaceHtml(rs[j]));
						}
					}else{
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
	 * ���Ĭ�ϲ�ѯ����
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getMrCs(QgzxCxtjForm model) {
		HashMap<String, String> rs = new HashMap<String, String>();
		User user = model.getUser();
		// Ȩ�޿���
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		// ��������ڹ�����Ա
		if (!qgzxGlyglService.sfQggly(user.getUserName())) {
			rs.put("dis", "true");
		}
		rs.put("nd", StringUtil.isNull(model.getNd()) ? Base.currNd : model
				.getNd());
		rs.put("yf", model.getYf());
		rs.put("bmdm", StringUtil.isNull(model.getBmdm()) ? user.getUserDep()
				: model.getBmdm());
		return rs;
	}

	/**
	 * ����·��б�
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getYfList() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String[] dm = new String[] { "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12" };
		HashMap<String, String> map = null;
		for (int i = 0; i < dm.length; i++) {
			map = new HashMap<String, String>();
			map.put("yf", dm[i]);
			list.add(map);
		}
		return list;
	}

	/**
	 * ��ò����б�
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getBmList() {
		return qgzxCxtjDAO.getBmList();
	}

	/**
	 * ���ų�𷢷�ͳ��
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> bmcjffTj(QgzxCxtjForm model) throws Exception {
		if (model.getBmdm() == null || "".equalsIgnoreCase(model.getBmdm())) {
			model.setBmdm("%");
		}
		if (null == model.getYf() || "".equalsIgnoreCase(model.getYf())) {
			// ���
			return qgzxCxtjDAO.bmcjffTjByNd(model);
		} else {
			// �·�
			return qgzxCxtjDAO.bmcjffTjByYf(model);
		}
	}

	/**
	 * @����:��λ��𷢷�ͳ��
	 * @���ߣ�cp[���ţ�1352]
	 * @���ڣ�2017-8-18 ����10:40:57
	 * @param model
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> �������� 
	 * @throws
	 */
	public ArrayList<String[]> gwcjffTj(QgzxCxtjForm model) throws Exception {
		if (model.getBmdm() == null || "".equalsIgnoreCase(model.getBmdm())) {
			model.setBmdm("%");
		}
		if (model.getGwmc() == null || "".equalsIgnoreCase(model.getGwmc())) {
			model.setGwmc("%");
		}
		if (null == model.getYf() || "".equalsIgnoreCase(model.getYf())) {
			// ���
			return qgzxCxtjDAO.gwcjffTjByNd(model);
		} else {
			// �·�
			return qgzxCxtjDAO.gwcjffTjByYf(model);
		}
	}
	/**
	 * ���˳�𷢷�ͳ��
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> grcjffTj(QgzxCxtjForm model) throws Exception {
		if (model.getBmdm() == null || "".equalsIgnoreCase(model.getBmdm())) {
			model.setBmdm("%");
		}
		if (model.getGwmc() == null || "".equalsIgnoreCase(model.getGwmc())) {
			model.setGwmc("%");
		}
		if (null == model.getYf() || "".equalsIgnoreCase(model.getYf())) {
			// ���
			return qgzxCxtjDAO.grcjffTjByNd(model);
		} else {
			// �·�
			return qgzxCxtjDAO.grcjffTjByYf(model);
		}
	}
	
	/**
	 * @���������˳�𷢷�ͳ���·ݵ���
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��5��23�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * File ��������
	 */
	public File getjffTjyf(QgzxCxtjForm model) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		List<HashMap<String,String>> jtfftjYfList=qgzxCxtjDAO.getjffTjByYf(model);
		data.put("jtfftjYfList", jtfftjYfList);
		data.put("xn",Base.currXn);
		data.put("xxmc",Base.xxmc);
		data.put("nd", model.getNd());
		data.put("yf", model.getYf());
		File file = FreeMarkerUtil.getExcelFile(data, "classpath:templates/qgzx", "qgzxfyff_10277.xml", "���˳�𷢷�ͳ��_"+model.getNd()+model.getYf());
		return file;
	}

	/**
	 * ����ͳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTMLByBm(QgzxCxtjForm model,
			SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user)
			throws Exception {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			if (model.getYf() == null || "".equalsIgnoreCase(model.getYf())) {
				html
						.append("<thead align=\"center\"><tr><td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">���˲���</td>");
				for (int i = 1; i < 13; i++) {
					html
							.append("<td align=\"middle\" colspan=\"3\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">"
									+ i + "��</td>");
				}
				html
						.append("<td align=\"middle\" colspan=\"3\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�ϼ�</td></tr><tr>");
				for (int i = 0; i < 13; i++) {
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">��λ��</td>");
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�˴�</td>");
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">���Ž��<Ԫ></td>");
				}
				html.append("</tr></thead><tr>");
			} else {
				html
						.append("<thead align=\"center\"><tr><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">���˲���</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">�ڹ�����</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">�˴�</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">�ܹ�ʱ</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">�ܳ��<Ԫ></td>"
								+ "</tr></thead><tr>");
			}
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""
							+ 100 / (rs.length) + "%\" ");
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
	 * 
	 * @����:��λͳ��
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-8-18 ����10:40:18
	 * @param model
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String createSearchHTMLByGw(QgzxCxtjForm model,
			SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user)
			throws Exception {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			if (model.getYf() == null || "".equalsIgnoreCase(model.getYf())) {
				html.append("<thead align=\"center\"><tr><td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">���˲���</td>");
				html.append("<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">��λ</td>");
				for (int i = 1; i < 13; i++) {
					html.append("<td align=\"middle\" colspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">"
									+ i + "��</td>");
				}
				html.append("<td align=\"middle\" colspan=\"3\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�ϼ�</td></tr><tr>");
				for (int i = 0; i < 13; i++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�˴�</td>");
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">���Ž��<Ԫ></td>");
				}
				html.append("</tr></thead><tr>");
			} else {
				html.append("<thead align=\"center\"><tr><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">���˲���</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">��λ</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">�ڹ�����</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">�˴�</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">�ܹ�ʱ</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">�ܳ��<Ԫ></td>"
								+ "</tr></thead><tr>");
			}
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""
							+ 100 / (rs.length) + "%\" ");
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
	 * ����ͳ��
	 * 
	 * @param model
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTMLByGr(QgzxCxtjForm model,
			SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user) {

		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			if (model.getYf() == null || "".equalsIgnoreCase(model.getYf())) {
				if(model.getBmdm() != null && "%".equals(model.getBmdm())) {
					html
							.append("<thead align=\"center\"><tr><td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">ѧ��</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">����</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">ѧԺ</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">���˲���</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">��λ����</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">��������</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">���п���</td>"
									+ "<td align=\"middle\" colspan=\"13\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">��ϸͳ��</td></tr><tr>");
				}else {
					html
							.append("<thead align=\"center\"><tr><td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">ѧ��</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">����</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">ѧԺ</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">��λ����</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">��������</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">���п���</td>"
									+ "<td align=\"middle\" colspan=\"13\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">��ϸͳ��</td></tr><tr>");
				}
				for (int i = 1; i < 13; i++) {
					html
							.append("<td align=\"middle\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">"
									+ i + "��</td>");
				}
				html
						.append("<td align=\"middle\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�ϼ�</td></tr>");
				html.append("</thead></tr>");
			} else {
				if(model.getBmdm() != null && "%".equals(model.getBmdm())) {
					html
							.append("<thead align=\"center\"><td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">ѧ��</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">����</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">�꼶</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">ѧԺ</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">���˲���</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">��λ����</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">רҵ</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">�༶</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">��������</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">���п���</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">���<Ԫ></td></thead>");		
				}else {
					html
							.append("<thead align=\"center\"><td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">ѧ��</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">����</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">�꼶</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">ѧԺ</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">��λ����</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">רҵ</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">�༶</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">��������</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">���п���</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">���<Ԫ></td></thead>");
				}
			}
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr>");
				if (i == rsArrList.size()
						&& (model.getYf() == null || "".equalsIgnoreCase(model
								.getYf()))) {

					for (int j = 3; j < rs.length; j++) {
						html
								.append("<td align=\"left\" nowrap=\"nowrap\" width=\""
										+ 100 / (rs.length) + "%\" ");
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}
						html.append(">");
						html.append(replaceHtml(rs[j]));
						html.append("</td>");
					}
				} else {
					for (int j = 0; j < rs.length; j++) {
						html
								.append("<td align=\"left\" nowrap=\"nowrap\" width=\""
										+ 100 / (rs.length) + "%\" ");
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}
						html.append(">");
						html.append(replaceHtml(rs[j]));
						html.append("</td>");
					}
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * ���ų�𷢷ŵ���
	 * 
	 * @param outputStream
	 * @param model
	 * @throws Exception
	 */
	public void bmcjffDc(ServletOutputStream outputStream, QgzxCxtjForm model)
			throws Exception {

		if (model.getYf() != null && !"".equalsIgnoreCase(model.getYf())) {
			String title = model.getNd() + "���" + model.getYf() + "�·ݳ�𷢷�ͳ��";
			// �̶���
			String[] topTr = new String[] { "���˲���", "�ڹ�����", "�˴�","�ܹ�ʱ", "�ܳ��" };

			// ��������Ĺ̶�����
			ArrayList<String[]> gdlist = bmcjffTj(model);

			WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(title, 0);
			WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			// ����ͷ
			ExcelMB excel = new ExcelMB();
			excel.printTitle(ws, title, 5, 800);// ����
			for (int i = 0; i < topTr.length; i++) {
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 1, topTr[i], wcf2));
			}
			// �������
			if (gdlist != null && gdlist.size() > 0) {
				for (int i = 0; i < gdlist.size(); i++) {
					String[] info = gdlist.get(i);
					if (info != null && info.length > 0) {
						for (int j = 0; j < info.length; j++) {
							ws.addCell(new Label(j, i + 2, info[j], wcf2));
						}
					}
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		} else {
			String title = model.getNd() + "��Ȳ��ű��귢�Ż��ܱ�";

			// ��������Ĺ̶�����
			ArrayList<String[]> gdlist = bmcjffTj(model);

			WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(title, 0);
			WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			// ����ͷ
			ExcelMB excel = new ExcelMB();
			excel.printTitle(ws, title, 40, 800);// ����
			ws.mergeCells(0, 1, 0, 2);
			ws.addCell(new Label(0, 1, "���˲���", wcf2));
			for (int i = 1; i <= 13; i++) {
				if (i == 13) {
					ws.mergeCells((i - 1) * 3 + 1, 1, (i - 1) * 3 + 3, 1);
					ws.addCell(new Label((i - 1) * 3 + 1, 1, "�ϼ�", wcf2));
				} else {
					ws.mergeCells((i - 1) * 3 + 1, 1, (i - 1) * 3 + 3, 1);
					ws.addCell(new Label((i - 1) * 3 + 1, 1, i + "��", wcf2));
				}
			}
			for (int i = 1; i <= 13; i++) {
				ws.addCell(new Label((i - 1) * 3 + 1, 2, "��λ��", wcf2));
				ws.addCell(new Label((i - 1) * 3 + 2, 2, "�˴�", wcf2));
				ws.addCell(new Label((i - 1) * 3 + 3, 2, "���Ž��", wcf2));
			}
			// �������
			if (gdlist != null && gdlist.size() > 0) {
				for (int i = 0; i < gdlist.size(); i++) {
					String[] info = gdlist.get(i);
					if (info != null && info.length > 0) {
						for (int j = 0; j < info.length; j++) {
							ws.addCell(new Label(j, i + 3, info[j], wcf2));
						}
					}
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}

	}
	/**
	 * 
	 * @����:��λ��𷢷ŵ���
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-8-18 ����09:58:37
	 * @param outputStream
	 * @param model
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void gwcjffDc(ServletOutputStream outputStream, QgzxCxtjForm model)
	throws Exception {
		if (model.getYf() != null && !"".equalsIgnoreCase(model.getYf())) {
			String title = model.getNd() + "���" + model.getYf() + "�·ݳ�𷢷�ͳ��";
			// �̶���
			String[] topTr = new String[] { "���˲���", "��λ", "�ڹ�����", "�˴�", "�ܹ�ʱ",
					"�ܳ��" };

			// ��������Ĺ̶�����
			ArrayList<String[]> gdlist = gwcjffTj(model);

			WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(title, 0);
			WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			// ����ͷ
			ExcelMB excel = new ExcelMB();
			excel.printTitle(ws, title, 5, 800);// ����
			for (int i = 0; i < topTr.length; i++) {
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 1, topTr[i], wcf2));
			}
			// �������
			if (gdlist != null && gdlist.size() > 0) {
				for (int i = 0; i < gdlist.size(); i++) {
					String[] info = gdlist.get(i);
					if (info != null && info.length > 0) {
						for (int j = 0; j < info.length; j++) {
							ws.addCell(new Label(j, i + 2, info[j], wcf2));
						}
					}
				}
			}
	ExcelMethods.submitWritableWorkbookOperations(wwb);
} else {
	String title = model.getNd() + "��ȸ�λ��𷢷Ż��ܱ�";

	// ��������Ĺ̶�����
	ArrayList<String[]> gdlist = gwcjffTj(model);

	WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
	WritableSheet ws = wwb.createSheet(title, 0);
	WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
	wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
			Alignment.CENTRE, VerticalAlignment.CENTRE, true,
			Border.ALL);
	// ����ͷ
	ExcelMB excel = new ExcelMB();
	excel.printTitle(ws, title, 40, 800);// ����
	ws.mergeCells(0, 1, 0, 2);
	ws.mergeCells(1, 1, 1, 2);
	ws.addCell(new Label(0, 1, "���˲���", wcf2));
	ws.addCell(new Label(1, 1, "��λ", wcf2));
	for (int i = 1; i <= 13; i++) {
		if (i == 13) {
			ws.mergeCells((i - 1) * 2 + 2, 1, (i - 1) * 2 + 3, 1);
			ws.addCell(new Label((i - 1) * 2 + 2, 1, "�ϼ�", wcf2));
		} else {
			ws.mergeCells((i - 1) * 2 + 2, 1, (i - 1) * 2 + 3, 1);
			ws.addCell(new Label((i - 1) *2 + 2, 1, i + "��", wcf2));
		}
	}
	for (int i = 1; i <= 13; i++) {
		ws.addCell(new Label((i - 1) * 2 + 2, 2, "�˴�", wcf2));
		ws.addCell(new Label((i - 1) * 2 + 3, 2, "���Ž��", wcf2));
	}
	// �������
	if (gdlist != null && gdlist.size() > 0) {
		for (int i = 0; i < gdlist.size(); i++) {
			String[] info = gdlist.get(i);
			if (info != null && info.length > 0) {
				for (int j = 0; j < info.length; j++) {
					ws.addCell(new Label(j, i + 3, info[j], wcf2));
				}
			}
		}
	}
	ExcelMethods.submitWritableWorkbookOperations(wwb);
}

}
	/**
	 * ���˳�𷢷ŵ���
	 * 
	 * @param outputStream
	 * @param model
	 * @throws Exception
	 */
	public void grcjffDc(ServletOutputStream outputStream, QgzxCxtjForm model)
			throws Exception {
		
		String[] topTr = null;
		// �㽭��ý���·ݵ���
		if (Globals.XXDM_ZJCMXY.equals(Base.xxdm)
				&& !StringUtil.isNull(model.getYf())) {
			zjcmCjff(outputStream, model);

		} else if (!StringUtil.isNull(model.getYf())) {
			String title = model.getNd()
					+ "���"
					+ (Base.isNull(model.getBmdm()) ? "����" : getBmmc(model
							.getBmdm())) + "����" + model.getYf() + "�·ݳ�𷢷�ͳ��";
			// ���������ͷ
			if(model.getBmdm() != null && "".equals(model.getBmdm())) {
				topTr = new String[] { "ѧ��", "����", "�꼶", "ѧԺ", "���˲���", "��λ����", "רҵ", "�༶",
						"��������", "���п���", "���", "��ע" };
			}else {
				topTr = new String[] { "ѧ��", "����", "�꼶", "ѧԺ", "��λ����", "רҵ", "�༶",
						"��������", "���п���", "���", "��ע" };
			}

			// ��������Ĺ̶�����
			ArrayList<String[]> gdlist = grcjffTj(model);
			ArrayList<String[]> gdlistN = new ArrayList<String[]>();
			for (String[] str : gdlist) {
				gdlistN.add(Arrays2.addObject(str, ""));
			}
			gdlist = gdlistN;
			WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(title, 0);
			WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			// ����ͷ
			ExcelMB excel = new ExcelMB();
			if(model.getBmdm() != null && "%".equals(model.getBmdm())) {
				excel.printTitle(ws, title, 12, 800);// ����
			}else {
				excel.printTitle(ws, title, 11, 800);// ����
			}
			for (int i = 0; i < topTr.length; i++) {
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 1, topTr[i], wcf2));
			}
			// �������
			if (gdlist != null && gdlist.size() > 0) {
				for (int i = 0; i < gdlist.size(); i++) {
					String[] info = gdlist.get(i);
					if (info != null && info.length > 0) {
						for (int j = 0; j < info.length; j++) {
							ws.addCell(new Label(j, i + 2, info[j], wcf2));
						}
					}
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		} else {
			String title = model.getNd()
					+ "���"
					+ (Base.isNull(model.getBmdm()) ? "����" : getBmmc(model
							.getBmdm())) + "����ѧ��ÿ�±��귢����ϸ����";
			ArrayList<String[]> gdlistN = new ArrayList<String[]>();
			ArrayList<String[]> gdlist = grcjffTj(model);
			for (String[] str : gdlist) {
				gdlistN.add(Arrays2.addObject(str, ""));
			}
			gdlist = gdlistN;
			WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(title, 0);
			WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);

			ExcelMB excel = new ExcelMB();
			excel.printTitle(ws, title, 17, 800);// ����
			if(model.getBmdm() != null && "%".equals(model.getBmdm())) {
				ws.mergeCells(0, 1, 0, 2);
				ws.addCell(new Label(0, 1, "ѧ��", wcf2));
				ws.mergeCells(1, 1, 1, 2);
				ws.addCell(new Label(1, 1, "����", wcf2));
				ws.mergeCells(2, 1, 2, 2);
				ws.addCell(new Label(2, 1, "ѧԺ", wcf2));
				ws.mergeCells(3, 1, 3, 2);
				ws.addCell(new Label(3, 1, "���˲���", wcf2));
				ws.mergeCells(4, 1, 4, 2);
				ws.addCell(new Label(4, 1, "��λ����", wcf2));
				ws.mergeCells(5, 1, 5, 2);
				ws.addCell(new Label(5, 1, "��������", wcf2));
				ws.mergeCells(6, 1, 6, 2);
				ws.addCell(new Label(6, 1, "���п���", wcf2));
				ws.mergeCells(7, 1, 18, 1);
				ws.addCell(new Label(7, 1, "��ϸͳ��", wcf2));
				for (int i = 1; i < 14; i++) {
					ws.addCell(new Label(i + 6, 2, i + "��", wcf2));
				}
				ws.mergeCells(19, 1, 19, 2);
				ws.addCell(new Label(19, 1, "�ϼ�", wcf2));
				ws.mergeCells(20, 1, 20, 2);
				ws.addCell(new Label(20, 1, "��ע", wcf2));
				
			}else {
				ws.mergeCells(0, 1, 0, 2);
				ws.addCell(new Label(0, 1, "ѧ��", wcf2));
				ws.mergeCells(1, 1, 1, 2);
				ws.addCell(new Label(1, 1, "����", wcf2));
				ws.mergeCells(2, 1, 2, 2);
				ws.addCell(new Label(2, 1, "ѧԺ", wcf2));
				ws.mergeCells(3, 1, 3, 2);
				ws.addCell(new Label(3, 1, "��λ����", wcf2));
				ws.mergeCells(4, 1, 4, 2);
				ws.addCell(new Label(4, 1, "��������", wcf2));
				ws.mergeCells(5, 1, 5, 2);
				ws.addCell(new Label(5, 1, "���п���", wcf2));
				ws.mergeCells(6, 1, 17, 1);
				ws.addCell(new Label(6, 1, "��ϸͳ��", wcf2));
				for (int i = 1; i < 13; i++) {
					ws.addCell(new Label(i + 5, 2, i + "��", wcf2));
				}
				ws.mergeCells(18, 1, 18, 2);
				ws.addCell(new Label(18, 1, "�ϼ�", wcf2));
				ws.mergeCells(19, 1, 19, 2);
				ws.addCell(new Label(19, 1, "��ע", wcf2));
			}
			// �������
			if (gdlist != null && gdlist.size() > 0) {
				for (int i = 0; i < gdlist.size(); i++) {
					String[] info = gdlist.get(i);
					if (i == gdlist.size()) {
						for (int j = 3; j < info.length; j++) {
							ws.addCell(new Label(j, i + 3, info[j], wcf2));
						}
					} else {
						for (int j = 0; j < info.length; j++) {
							ws.addCell(new Label(j, i + 3, info[j], wcf2));
						}
					}
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}

	}

	/**
	 * �㽭��ý��𷢷ŵ������ܱ����·ݣ�
	 * 
	 * @param outputStream
	 * @param model
	 * @throws IOException
	 * @throws Exception
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private void zjcmCjff(ServletOutputStream outputStream, QgzxCxtjForm model)
			throws IOException, Exception, WriteException,
			RowsExceededException {
		String title = new StringBuilder("�㽭��ýѧԺ�ڹ���ѧ���ѷ����������ܱ�").append(
				model.getNd()).append("��").append(model.getYf()).append("�£�")
				.toString();

		String[] topTr = new String[] { "���", "���п���", "��������", "ѧ��", "ѧ������",
				"�ù�����/ѧԺ", "����ѧԺ", "��λ����", "ƶ���̶�", "ѧ�����", "ÿ Сʱ����", "�ù�ʱ��",
				"���" };
		List<String[]> dataList = qgzxCxtjDAO.zjcmCjffByYf(model);

		WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ����ͷ
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 13, 800);// ����

		for (int i = 0; i < topTr.length; i++) {
			// ws.setColumnView(i, 15);
			ws.addCell(new Label(i, 1, topTr[i], wcf2));
		}
		// �������
		if (dataList != null && dataList.size() > 0) {
			for (int i = 0; i < dataList.size(); i++) {
				String[] info = dataList.get(i);
				if (info != null && info.length > 0) {
					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 2, info[j], wcf2));
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * ���ݲ��Ŵ����ò�������
	 * 
	 * @param bmdm
	 * @return
	 */
	private String getBmmc(String bmdm) {
		return qgzxCxtjDAO.getBmmc(bmdm);
	}

	/**
	 * ��λ��Ϣ���Զ��嵼��
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> gwxxCxExport(QgzxCxtjForm model,
			User user) throws Exception {
		return qgzxCxtjDAO.gwxxCxExport(model, user);
	}

	/**
	 * ѧ����λ��ѯ�Զ��嵼��
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xsgwCxExport(QgzxCxtjForm model,
			User user) throws Exception {
		return qgzxCxtjDAO.xsgwCxExport(model, user);
	}

	/**
	 * ���ѻ�����ѯ�Զ��嵼��
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> jfhbCxExport(QgzxCxtjForm model,
			User user) throws Exception {
		return qgzxCxtjDAO.jfhbCxExport(model, user);
	}

	/**
	 * ��𷢷Ų�ѯ�Զ��嵼��
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> cjffCxExport(QgzxCxtjForm model,
			User user) throws Exception {
		return qgzxCxtjDAO.cjffCxExport(model, user);
	}
	
	/**
	 * @����:������ҵ��ѧ�����걨��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-19 ����09:30:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void cjffCxExportData_10022(QgzxCxtjForm model, OutputStream os, User user) throws Exception {
		// ����,12,CENTRE
		WritableCellFormat s12CentreFormat = new WritableCellFormat();
		WritableFont s12CentreFont = new WritableFont(WritableFont.createFont("����"),12);
		s12CentreFormat.setFont(s12CentreFont);
		s12CentreFormat.setAlignment(Alignment.CENTRE);
		// ����,12,BOLD,CENTRE
		WritableCellFormat k12CentreBoldFormat = new WritableCellFormat();
		WritableFont k12CentreBoldFont = new WritableFont(WritableFont.createFont("����"),12);
		k12CentreBoldFont.setBoldStyle(WritableFont.BOLD);
		k12CentreBoldFormat.setFont(k12CentreBoldFont);
		k12CentreBoldFormat.setAlignment(Alignment.CENTRE);
		// ����,12,Wrap,CENTRE
		WritableCellFormat s12CentreWrapFormat = new WritableCellFormat();
		WritableFont s12CentreWrapFont = new WritableFont(WritableFont.createFont("����"),12);
		s12CentreWrapFormat.setFont(s12CentreWrapFont);
		s12CentreWrapFormat.setAlignment(Alignment.CENTRE);
		s12CentreWrapFormat.setWrap(true);
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		// �����
		List<HashMap<String, String>> dataList = qgzxCxtjDAO.cjffCxExportData_10022(model, user);
		// =============== �������� begin =====================
		SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd��");
		String[] nd = model.getSearchModel().getSearch_tj_nd();
		String[] yf = model.getSearchModel().getSearch_tj_yf();
		Arrays.sort(yf);
		int ndInt = Integer.parseInt(nd[0]);
		int yfBeginInt = Integer.parseInt(yf[0]) - 1;
		int yfEndInt = Integer.parseInt(yf[yf.length - 1]) - 1;
		Calendar c = Calendar.getInstance();
		c.set(ndInt, yfBeginInt, 1);
		String yfBeginStr = format.format(c.getTime());
		c.set(Calendar.MONTH, yfEndInt);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		String yfEndStr = format.format(c.getTime());
		String ygqj = "�ù��ڼ䣺" + yfBeginStr + "��" + yfEndStr;
		// =============== �������� end =====================
		// ������֤�Ƿ���ͬ�����˲���
		HashMap<String,String> tempMap = new HashMap<String,String>();
		int sheetNum = 0;
		WritableSheet ws = null;
		// ������
		int columns  = 7;
		int index  = 0; // ���
		for (int m = 0; m < dataList.size(); m++) {
			HashMap<String,String> dataMap = dataList.get(m);
			if(!dataMap.get("yrdwdm").equals(tempMap.get("yrdwdm"))){
				tempMap = dataMap;
				ws = wwb.createSheet(tempMap.get("yrdwmc")+"�걨��", sheetNum);
				sheetNum++;
				index = 0; // �������
				// ���ã�ÿ���п��
				ws.setColumnView(0, 7); 
				ws.setColumnView(1, 15); 
				ws.setColumnView(2, 12); 
				ws.setColumnView(3, 34); 
				ws.setColumnView(4, 26); 
				ws.setColumnView(5, 27); 
				ws.setColumnView(6, 23); 
				// ��1��
				WritableCellFormat s18BoldCenterFormat = new WritableCellFormat();
				WritableFont s18BoldCenterFont = new WritableFont(WritableFont.createFont("����"),18);
				s18BoldCenterFont.setBoldStyle(WritableFont.BOLD);
				s18BoldCenterFormat.setFont(s18BoldCenterFont);
				s18BoldCenterFormat.setAlignment(Alignment.CENTRE);
				ws.addCell(new Label(0, 0, "������ҵ��ѧ�ڹ���ѧ�����걨��", s18BoldCenterFormat));
				ws.mergeCells(0, 0, (columns - 1), 0);
				// ��2��
				WritableCellFormat r2Format = new WritableCellFormat();
				WritableFont r2Font = new WritableFont(WritableFont.createFont("����"),18);
				r2Font.setBoldStyle(WritableFont.BOLD);
				r2Format.setFont(r2Font);
				r2Format.setAlignment(Alignment.CENTRE);
				ws.addCell(new Label(0, 1, "", r2Format));
				// ��3��
				ws.addCell(new Label(0, 2, "�ù����ţ�" + tempMap.get("yrdwmc") + "    " + ygqj + "    �������ݣ�", k12CentreBoldFormat));
				ws.mergeCells(0, 2, (columns - 1), 2);
				// ��4��
				ws.addCell(new Label(0, 3, "��  ��", s12CentreFormat));
				ws.addCell(new Label(1, 3, "ѧ  ��", s12CentreFormat));
				ws.addCell(new Label(2, 3, "��  ��", s12CentreFormat));
				ws.addCell(new Label(3, 3, "��  ��", s12CentreFormat));
				ws.addCell(new Label(4, 3, "��  ��  ��\n��û���ֻ�д����绰��", s12CentreWrapFormat));
				ws.addCell(new Label(5, 3, "��  ��  ��\n����λ��Сʱ/��/��/�Σ�", s12CentreWrapFormat));
				ws.addCell(new Label(6, 3, "��  ע", s12CentreFormat));
			}
			// ѧ����Ϣ
			tempMap = dataMap;
			index++;
			int rowNum = 3 + index;
			ws.addCell(new Label(0, rowNum, String.valueOf(index), s12CentreFormat));
			ws.addCell(new Label(1, rowNum, tempMap.get("xh"), s12CentreFormat));
			ws.addCell(new Label(2, rowNum, tempMap.get("xm"), s12CentreFormat));
			ws.addCell(new Label(3, rowNum, tempMap.get("bjmc"), s12CentreFormat));
			ws.addCell(new Label(4, rowNum, tempMap.get("qsdhsjhm"), s12CentreFormat));
			ws.addCell(new Label(5, rowNum, tempMap.get("gs"), s12CentreFormat));
			ws.addCell(new Label(6, rowNum, "", s12CentreFormat));
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//��ͻ������
	}

	/**
	 * 
	 * @����:��ý���Ի�����
	 * @���ߣ�cq[���ţ�982]
	 * @���ڣ�2013-12-26 ����05:09:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param os
	 * @throws Exception void ��������
	 * @throws
	 */
	public void cjffExpCm(QgzxCxtjForm model, OutputStream os, User user)
			throws Exception {

		// ѧУ����
		String xxmc = "�㽭��ýѧԺ";

		// ������ͳ���б�
		List<HashMap<String, String>> list = qgzxCxtjDAO.getCjffExpCm(model,
				user);

		// ���ñ���
		StringBuffer title = new StringBuffer();
		title.append(xxmc);
		title.append("�ڹ���ѧ���ѷ����������ܱ�");
		if (model.getFfnd() != null && model.getFfnd().length == 1
				&& StringUtils.isNotNull(model.getFfnd()[0])) {
			title.append("(" + model.getFfnd()[0] + "��");
			if (model.getFfyf() != null && model.getFfyf().length == 1
					&& StringUtils.isNotNull(model.getFfyf()[0])) {
				title.append(model.getFfyf()[0] + "��");
			}
			title.append(")");
		}
		// title.append(list != null && list.size() > 0 && list.get(0)!=null &&
		// list.get(0).get("xymc") !=null ? list.get(0).get("xymc") :"");

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("���ʱ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 12, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);

		// �ϲ���Ԫ��
		ws.mergeCells(1, 2, 3, 2);
		ws.mergeCells(8, 2, 10, 2);

		// ��õ�ǰ�û�����Ϣ
		HashMap<String, String> userInfo = qgzxCxtjDAO.getUserInfo(user);

		// ��õ�ǰʱ��

		String Dete = GetTime.getTimeByFormat("yyyy-mm-dd");

		ex.printToOneCell_multy(ws, "���Ż�ѧԺ���ƣ�" + userInfo.get("bmmc") + "�����£�",
				1, 2, 10, true, Alignment.CENTRE, VerticalAlignment.CENTRE,
				true, 700, Border.NONE);
		ex.printToOneCell_multy(ws, "�Ʊ����ڣ�" + Dete, 8, 2, 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);

		ws.addCell(new Label(0, 4, "���", wcf2));
		ws.addCell(new Label(1, 4, "���п���", wcf2));
		ws.addCell(new Label(2, 4, "��������", wcf2));
		ws.addCell(new Label(3, 4, "ѧ��", wcf2));
		ws.addCell(new Label(4, 4, "ѧ������", wcf2));
		ws.addCell(new Label(5, 4, "�ù�����/ѧԺ", wcf2));
		ws.addCell(new Label(6, 4, "����ѧԺ", wcf2));
		ws.addCell(new Label(7, 4, "��λ����", wcf2));
		ws.addCell(new Label(8, 4, "ƶ���̶�", wcf2));
		ws.addCell(new Label(9, 4, "ѧ�����", wcf2));
		ws.addCell(new Label(10, 4, "ÿСʱ����", wcf2));
		ws.addCell(new Label(11, 4, "�ù�ʱ��", wcf2));
		ws.addCell(new Label(12, 4, "���", wcf2));

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				ws.setColumnView(0, 5);
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 13);
				ws.setColumnView(4, 10);
				ws.setColumnView(5, 18);
				ws.setColumnView(6, 18);
				ws.setColumnView(7, 10);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 10);
				ws.setColumnView(10, 7);
				ws.setColumnView(11, 5);
				ws.setColumnView(12, 7);

				ws.addCell(new Label(0, 5 + i, map.get("r"), wcf2));// ���
				ws.addCell(new Label(1, 5 + i, map.get("yhkh"), wcf2));// ���п���
				ws.addCell(new Label(2, 5 + i, map.get("yhmc"), wcf2));// ��������
				ws.addCell(new Label(3, 5 + i, map.get("xh"), wcf2));// ѧ��
				ws.addCell(new Label(4, 5 + i, map.get("xm"), wcf2));// ѧ������
				ws.addCell(new Label(5, 5 + i, map.get("yrdwmc"), wcf2));// �ù�����/ѧԺ
				ws.addCell(new Label(6, 5 + i, map.get("xymc"), wcf2));// ����ѧԺ
				ws.addCell(new Label(7, 5 + i, map.get("gwxzmc"), wcf2));// ��λ����
				ws.addCell(new Label(8, 5 + i, map.get("pkdj"), wcf2));// ƶ���̶�
				ws.addCell(new Label(9, 5 + i, map.get("xlmc"), wcf2));// ѧ�����
				ws.addCell(new Label(10, 5 + i, map.get("mxsgj"), wcf2));// ÿСʱ����
				ws.addCell(new Label(11, 5 + i, map.get("gs"), wcf2));// �ù�ʱ��
				ws.addCell(new Label(12, 5 + i, map.get("je"), wcf2));// ���
			}

		}

		// �ײ���Ԫ��ϲ�
		ws.mergeCells(1, 6 + list.size(), 3, 5 + list.size());
		ws.mergeCells(8, 6 + list.size(), 10, 5 + list.size());

		ex.printToOneCell_multy(ws, "�Ʊ���ǩ����", 1, 6 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ex.printToOneCell_multy(ws, "�����쵼ǩ����", 8, 6 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 
	 * @����:���Ϲ���ְҵ����ѧԺ�ڹ���ѧ���˸��Ի�����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-6-30 ����05:39:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param os
	 * @param user
	 * @throws Exception void ��������
	 * @throws
	 */
	public void cjffExpJn(QgzxCxtjForm model, OutputStream os, User user)
			throws Exception {

		// �ڹ���ѧ��𷢷�ͳ���б�
		List<HashMap<String, String>> list = qgzxCxtjDAO.getCjffExpJn(model,
				user);

		// ���ñ���
		StringBuffer title = new StringBuffer();
		title.append("���Ϲ���ְҵ����ѧԺѧ���ڹ���ѧ���˱�");

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("���˱�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 10, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);

		ws.addCell(new Label(0, 4, "���", wcf2));
		ws.addCell(new Label(1, 4, "����", wcf2));
		ws.addCell(new Label(2, 4, "�Ա�", wcf2));
		ws.addCell(new Label(3, 4, "Ժ(ϵ)", wcf2));
		ws.addCell(new Label(4, 4, "�༶", wcf2));
		ws.addCell(new Label(5, 4, "��λְ��", wcf2));
		ws.addCell(new Label(6, 4, "����ʱ��", wcf2));
		ws.addCell(new Label(7, 4, "����ʱ��", wcf2));
		ws.addCell(new Label(8, 4, "���˵ȼ�", wcf2));
		ws.addCell(new Label(9, 4, "ѧ��ǩ��", wcf2));
		ws.addCell(new Label(10, 4, "��ע", wcf2));
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				ws.setColumnView(0, 5);
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 18);
				ws.setColumnView(4, 18);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 10);
				ws.setColumnView(7, 20);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 10);
				ws.setColumnView(10, 10);
				ws.addCell(new Label(0, 5 + i, map.get("r"), wcf2));// ���
				ws.addCell(new Label(1, 5 + i, map.get("xm"), wcf2));// ����
				ws.addCell(new Label(2, 5 + i, map.get("xb"), wcf2));// �Ա�
				ws.addCell(new Label(3, 5 + i, map.get("xymc"), wcf2));// ѧԺ����
				ws.addCell(new Label(4, 5 + i, map.get("bjmc"), wcf2));// �༶����
				ws.addCell(new Label(5, 5 + i, map.get("gwmc"), wcf2));// ��λ����
				ws.addCell(new Label(6, 5 + i, map.get("gs") + "Сʱ", wcf2));// ����ʱ��
				ws.addCell(new Label(7, 5 + i, "", wcf2));// ����ʱ��
				ws.addCell(new Label(8, 5 + i, map.get("khdj"), wcf2));// ���˵ȼ�
				ws.addCell(new Label(9, 5 + i, "", wcf2));// ѧ��ǩ��
				ws.addCell(new Label(10, 5 + i, map.get("bz"), wcf2));// ��ע
			}
		}
		// �ײ���Ԫ��ϲ�
		ws.mergeCells(1, 6 + list.size(), 10, 6 + list.size());
		ws.mergeCells(1, 7 + list.size(), 10, 7 + list.size());
		ws.mergeCells(1, 8 + list.size(), 10, 8 + list.size());
		ws.setRowView(6 + list.size(), 500);
		ws.setRowView(7 + list.size(), 500);
		ws.setRowView(8 + list.size(), 500);
		ex.printToOneCell_multy(ws, "˵��:", 0, 6 + list.size(), 10, true,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ex
				.printToOneCell_multy(
						ws,
						"1�������˵�λӦ���ڸ��ڹ���ѧѧ���ϸ���п��ˣ��Թ����������ѧ�������˵�λ����ʱ��ѧ�����ڹ���ѧ�칫�ң�����ʵ����ֹ���ڹ���ѧ���һ���ڲ����Ź�����",
						1, 6 + list.size(), 10, true, Alignment.LEFT,
						VerticalAlignment.CENTRE, true, 700, Border.NONE);
		ex
				.printToOneCell_multy(
						ws,
						"2��ѧ���ڹ���ѧ�ı����ɻ�������Ϳ��˱��갴6:4������ɡ���40%�����ɸ��ù���λ������־�������Ϊ���㡢�ϸ񡢲��ϸ����ȡ����㰴100%���ţ��ϸ�60%���ţ����ϸ񲻷��Ų���ֹ�ڹ���ѧ�����",
						1, 7 + list.size(), 10, true, Alignment.LEFT,
						VerticalAlignment.CENTRE, true, 700, Border.NONE);
		ex.printToOneCell_multy(ws, "3��������˲�����ϸ��¼�ϸ�ѧ���������������Ϊ�������ݡ�", 1,
				8 + list.size(), 10, true, Alignment.LEFT,
				VerticalAlignment.CENTRE, true, 700, Border.NONE);
		ex.printToOneCell_multy(ws, "���˵�λ(����)��", 1, 9 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ex.printToOneCell_multy(ws, "�����ˣ�", 6, 9 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 
	 * @����:����ְҵ����ѧԺ�ڹ���ѧ����ͳ�Ƽ����������嵥
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-23 ����07:09:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param os
	 * @param user
	 * @throws Exception void ��������
	 * @throws
	 */
	public List<File> cjffExpJzy(QgzxCxtjForm model, User user)
			throws Exception {
		// �ڹ���ѧ��𷢷�ͳ���б�
		List<HashMap<String, String>> cjfflist = qgzxCxtjDAO.getCjffExpJzy(
				model, user);
		Map<String, List<HashMap<String, String>>> cjffFzMap = new HashMap<String, List<HashMap<String, String>>>();
		List<HashMap<String, String>> list = null;
		// ����ѯ���ĳ��ͳ�ư��������·���
		for (HashMap<String, String> cjffMap : cjfflist) {
			if (cjffFzMap.containsKey(cjffMap.get("ffny"))) {
				cjffFzMap.get(cjffMap.get("ffny")).add(cjffMap);
			} else {
				list = new ArrayList<HashMap<String, String>>();
				list.add(cjffMap);
				cjffFzMap.put(cjffMap.get("ffny"), list);
			}
		}
		List<File> files = new ArrayList<File>();
		for (Map.Entry<String, List<HashMap<String, String>>> entry : cjffFzMap
				.entrySet()) {
			File file=cjffExpJzyModel(entry.getValue(), user);
			files.add(file);
		}
		return files;
	}
	/**
	 * 
	 * @����:���������ļ�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-25 ����10:45:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * File �������� 
	 * @throws
	 */
	private File createFile(List<HashMap<String, String>> list) {
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		// ���������ļ�
		File file = new File(tempDir.getPath() + "/"
				+ list.get(0).get("nd")+"-"+list.get(0).get("yf")+"�ڹ���ѧ����ͳ�Ƽ����������嵥" + ".xls");
		file.setWritable(true);
		return file;
	}
	/**
	 * 
	 * @����:��������ģ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-25 ����10:45:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @param os
	 * @param user
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File cjffExpJzyModel(List<HashMap<String, String>> list, User user) throws Exception {
		File file = createFile(list);
		FileOutputStream outputStream = new FileOutputStream(file);
		//��ѯ�·��ڹ���ѧ�ܽ����ܹ�ʱ
		HashMap<String,String> jeAndGs =qgzxCxtjDAO.getJeAndGs(list.get(0).get("ffny"));
		// ���ñ���
		StringBuffer title = new StringBuffer();
		title.append("����ְҵ����ѧԺ");
		title.append(list.get(0).get("nd")).append("��").append(list.get(0).get("yf")).append("�·�");
		title.append("�ڹ���ѧ����ͳ�Ƽ����������嵥");
		WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
		WritableSheet ws = wwb.createSheet("���˱�", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 7, 1);
		ws.mergeCells(0, 2, 7, 3);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 15, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ex
				.printToOneCell_multy(ws, "��Ժ���������ƣ����£���", 0, 2, 10, true,
						Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
						Border.ALL);

		ws.addCell(new Label(0, 4, "���", wcf2));
		ws.addCell(new Label(1, 4, "��λ����", wcf2));
		ws.addCell(new Label(2, 4, "��������", wcf2));
		ws.addCell(new Label(3, 4, "��ʱ(Сʱ)", wcf2));
		ws.addCell(new Label(4, 4, "���(Ԫ)", wcf2));
		ws.addCell(new Label(5, 4, "����", wcf2));
		ws.addCell(new Label(6, 4, "���п���", wcf2));
		ws.addCell(new Label(7, 4, "ǩ��", wcf2));
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				ws.setColumnView(0, 5);
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 18);
				ws.setColumnView(4, 18);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 20);
				ws.setColumnView(7, 20);

				ws.addCell(new Label(0, 5 + i, i+1+"", wcf2));// ���
				ws.addCell(new Label(1, 5 + i, map.get("gwmc"), wcf2));// ��λ����
				ws.addCell(new Label(2, 5 + i, map.get("ffny"), wcf2));// ��������
				ws.addCell(new Label(3, 5 + i, map.get("gs"), wcf2));// ��ʱ
				ws.addCell(new Label(4, 5 + i, map.get("je"), wcf2));// ���
				ws.addCell(new Label(5, 5 + i, map.get("xm"), wcf2));// ����
				ws.addCell(new Label(6, 5 + i, map.get("yhkh"), wcf2));// ���п���
				ws.addCell(new Label(7, 5 + i, "", wcf2));// ǩ��
			}
		}
		// �ײ���Ԫ��ϲ�
		ws.mergeCells(0, 5 + list.size(), 2, 5 + list.size());
		ws.mergeCells(5, 5 + list.size(), 7, 5 + list.size());
		ws.mergeCells(0, 6 + list.size(), 1, 6 + list.size());
		ws.mergeCells(4, 6 + list.size(), 6, 6 + list.size());
		ex.printToOneCell_multy(ws, "�ϼ�:", 0, 5 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ex.printToOneCell_multy(ws, jeAndGs.get("zgs"), 3, 5 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ex.printToOneCell_multy(ws, jeAndGs.get("zje"), 4, 5 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ex.printToOneCell_multy(ws, "", 5, 5 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);

		ex.printToOneCell_multy(ws, "�Ʊ���ǩ��(��ǩ)��", 0, 6 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ex.printToOneCell_multy(ws, "��Ժ�����ţ��쵼ǩ����", 4, 6 + list.size(), 10,
				true, Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		return file;
	}
	
	
	
	/**
	 * ��ø�λ�б�
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getGwList() {
		return qgzxCxtjDAO.getGwList();
	}
	
	/**
	 * ���ų�𷢷Ų�ѯ
	 */
	public ArrayList<String[]> bmcjffCx(QgzxCxtjForm model) throws Exception {
		return qgzxCxtjDAO.bmcjffCx(model);
	}
	
	/**
	 * ���ų�𷢷Ų�ѯhtml
	 */
	public String createBmcjffcxSearchHTML(QgzxCxtjForm model,
			SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user)
			throws Exception {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			if (model.getYf() == null || "".equalsIgnoreCase(model.getYf())) {
				html.append("<thead align=\"center\"><tr><td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"\" height=\"28.5px\">���˲���</td>");
				for (int i = 1; i < 13; i++) {
					html.append("<td align=\"middle\" colspan=\"1\" nowrap=\"nowrap\" width=\"\" height=\"28.5px\">" + i + "��</td>");
				}
				html.append("</tr></thead><tr>");
			}
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\" ");
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					String ztmc = replaceHtml(rs[j]);
					if("δ¼��".equals(ztmc)){
						html.append("<font color='#b6b6b6'>" + ztmc + "</font>");
					}else{
						html.append(ztmc);
					}
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	/**
	 * ���ų�𷢷Ų�ѯ ����
	 */
	public void bmcjffCxDc(ServletOutputStream outputStream, QgzxCxtjForm model)
			throws Exception {

		if (model.getYf() != null && !"".equalsIgnoreCase(model.getYf())) {
			String yf = model.getYf().replaceAll("0(\\d)", "$1");
			String title = model.getNd() + "���" + yf + "�·ݳ�𷢷����";
			// �̶���
//			String[] topTr = new String[] { "���˲���", yf + "��"};
			String[] topTr = new String[] { "���˲���", "����״̬"};

			// ��������Ĺ̶�����
			ArrayList<String[]> gdlist = bmcjffCx(model);

			WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(title, 0);
			WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			// ����ͷ
			ExcelMB excel = new ExcelMB();
			excel.printTitle(ws, title, 2, 800);// ����
			for (int i = 0; i < topTr.length; i++) {
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 1, topTr[i], wcf2));
			}
			// �������
			if (gdlist != null && gdlist.size() > 0) {
				for (int i = 0; i < gdlist.size(); i++) {
					String[] info = gdlist.get(i);
					if (info != null && info.length > 0) {
						for (int j = 0; j < info.length; j++) {
							ws.addCell(new Label(j, i + 2, info[j], wcf2));
						}
					}
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		} else {
			String title = model.getNd() + "��ȳ�𷢷����";

			// ��������Ĺ̶�����
			ArrayList<String[]> gdlist = bmcjffCx(model);

			WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(title, 0);
			WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			// ����ͷ
			ExcelMB excel = new ExcelMB();
			excel.printTitle(ws, title, 13, 800);// ����
//			ws.mergeCells(0, 1, 0, 2);
			ws.addCell(new Label(0, 1, "���˲���", wcf2));
			for (int i = 1; i < 13; i++) {
				ws.addCell(new Label(i, 1, i + "��", wcf2));
			}
			// �������
			if (gdlist != null && gdlist.size() > 0) {
				for (int i = 0; i < gdlist.size(); i++) {
					String[] info = gdlist.get(i);
					if (info != null && info.length > 0) {
						for (int j = 0; j < info.length; j++) {
							ws.addCell(new Label(j, i + 2, info[j], wcf2));
						}
					}
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}

	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:����excel�ļ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-4-19 ����04:47:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * File �������� 
	 * @throws
	 */
	public File getExcelFile(QgzxCxtjForm model) throws Exception{
		List<HashMap<String,String>> dataList = new QgzxCxtjDAO().getGrcjffDcList(model);
		List<HashMap<String,String>> gdList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> lsList = new ArrayList<HashMap<String,String>>();
		if(dataList != null && !dataList.isEmpty()){
			for (int i = 0; i < dataList.size(); i++) {
				HashMap<String, String> tempMap = dataList.get(i);
				if(tempMap.get("gwxzmc").contains("�̶�")){
					gdList.add(tempMap);
				}else{
					lsList.add(tempMap);
				}
			}
		}
		//ϵͳ����������һ���ļ�
		String fileName = "�̶�����ʱ���ù������������";
		if("12036".equals(Base.xxdm)){
			String bmmc="";
			if("".equals(model.getBmdm()) || model.getBmdm()==null ){
				bmmc = "ȫ��";
			} else if("yj".equals(model.getBmdm())){
				bmmc = "����Ժ��";
			}else if("xj".equals(model.getBmdm())){
				bmmc = "����У��";
			}else{
				bmmc = new QgzxCxtjService().getBmmc(model.getBmdm());
			}
			fileName = model.getYf() + "��" + bmmc + fileName;

		}
		File tempFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName+DateUtils.getTime()+".xls");
		if(!tempFile.exists()){
			tempFile.createNewFile();
		}
		//����һ��excel�ļ�
		WritableWorkbook wwb = Workbook.createWorkbook(tempFile);
		createSheet(wwb,"��ʱ",model,lsList);
		createSheet(wwb,"�̶�",model,gdList);
		wwb.write();
		wwb.close();
		return tempFile;
	}
	
	/**
	 * 
	 * @����: ����excelҳǩ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-4-19 ����05:02:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param wwb
	 * void �������� 
	 * @throws
	 */
	public void createSheet(WritableWorkbook wwb,String gwlx,QgzxCxtjForm model,List<HashMap<String,String>> data) throws Exception{
		String date = DateUtils.getLyr();
		int index = (wwb.getSheets() != null && wwb.getSheets().length-1 >= 0) ? wwb.getSheets().length-1 : 0;
		WritableSheet ws = wwb.createSheet(gwlx, index);
		/**�����п�*/
		ws.setColumnView(0, 9);
	    ws.setColumnView(1, 13);
	    ws.setColumnView(2, 40);
	    ws.setColumnView(3, 10);
	    ws.setColumnView(4, 10);
	    ws.setColumnView(5, 13);
	    ws.setColumnView(6, 13);
		WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 13, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�������
		WritableFont titleheadFont = new WritableFont(WritableFont.ARIAL, 12,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//������������
		WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 12,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//������������
		WritableFont bodyValFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//������������
		WritableCellFormat title_cf = new WritableCellFormat(tileFont);//���ñ��ⵥԪ������
		WritableCellFormat titlehead_cf = new WritableCellFormat(titleheadFont);
		WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������
		WritableCellFormat bodyval_cf = new WritableCellFormat(bodyValFont);//�������ĵ�Ԫ������
		
		title_cf.setAlignment(jxl.format.Alignment.CENTRE);//���ñ��ⵥԪ�����
		title_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		//title_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//���ñ��ⵥԪ��߿�
		titlehead_cf.setAlignment(jxl.format.Alignment.LEFT);
		titlehead_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		body_cf.setAlignment(jxl.format.Alignment.CENTRE);//�������ĵ�Ԫ�����
		body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//�������ĵ�Ԫ��߿�
		body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		bodyval_cf.setAlignment(jxl.format.Alignment.CENTRE);//�������ĵ�Ԫ�����
		bodyval_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//�������ĵ�Ԫ��߿�
		bodyval_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

		String bmmc="";
		if("".equals(model.getBmdm()) || model.getBmdm()==null ){
			bmmc = "ȫ��";
		} else if("yj".equals(model.getBmdm())){
			bmmc = "����Ժ��";
		}else if("xj".equals(model.getBmdm())){
			bmmc = "����У��";
		}else{
			bmmc = new QgzxCxtjService().getBmmc(model.getBmdm());
		}


		String title =  "�㽭��ְͨҵ����ѧԺ"+model.getNd()+"��"+model.getYf()+"��"+"�ڹ���ѧ"+gwlx+""+"�ù�����֧���嵥"+"("+bmmc+")";
		
		Label TitleLabel = new Label(0, 0, title, title_cf);
		ws.addCell(TitleLabel);
		ws.mergeCells(0, 0, 6, 0);
		Label titleheadbm = new Label(0, 1, "���Ų��ţ�ѧ����", titlehead_cf);
		ws.addCell(titleheadbm);
		ws.mergeCells(0, 1, 1, 1);
		Label titleheadrq = new Label(4, 1, "�Ƶ����ڣ�"+date, titlehead_cf);
		ws.addCell(titleheadrq);
		ws.mergeCells(4, 1, 6, 1);
		Label xmtitle =  new Label(0, 2, "����", body_cf);
		ws.addCell(xmtitle);
		Label xhtitle =  new Label(1, 2, "ѧ��", body_cf);
		ws.addCell(xhtitle);
		Label yhkhtitle =  new Label(2, 2, "���п���", body_cf);
		ws.addCell(yhkhtitle);
		Label lwsjtitle =  new Label(3, 2, "����ʱ��", body_cf);
		ws.addCell(lwsjtitle);
		Label sfstitle =  new Label(4, 2, "ʵ����", body_cf);
		ws.addCell(sfstitle);
		Label lkrtitle =  new Label(5, 2, "�����ǩ��", body_cf);
		ws.addCell(lkrtitle);
		Label bztitle =  new Label(6, 2, "��ע", body_cf);
		ws.addCell(bztitle);
		/**�����и�*/
		ws.setRowView(0, 500);
		ws.setRowView(1, 350);
		ws.setRowView(2, 500);
		//�б�����
		double lwsj = 0.0;
		double je = 0.0; 
		if(data != null && !data.isEmpty()){
			for (int i = 0; i < data.size(); i++) {
				Label xmcontent =  new Label(0, 3+i,data.get(i).get("xm"), bodyval_cf);
				ws.addCell(xmcontent);
				Label xhcontent =  new Label(1, 3+i, data.get(i).get("xh"), bodyval_cf);
				ws.addCell(xhcontent);
				Label yhkhcontent =  new Label(2, 3+i, data.get(i).get("yhkh"), bodyval_cf);
				ws.addCell(yhkhcontent);
				Label lwsjcontent =  new Label(3, 3+i, data.get(i).get("gs"), bodyval_cf);
				ws.addCell(lwsjcontent);
				Label sfscontent =  new Label(4, 3+i, data.get(i).get("e"), bodyval_cf);
				ws.addCell(sfscontent);
				Label lkrcontent =  new Label(5, 3+i, "", bodyval_cf);
				ws.addCell(lkrcontent);
				Label bzcontent =  new Label(6, 3+i, "", bodyval_cf);
				ws.addCell(bzcontent);
				lwsj = lwsj + Double.parseDouble(data.get(i).get("gs"));
				je = je + Double.parseDouble(data.get(i).get("e"));
				ws.setRowView(3+i, 500);
			}
		}else{
			for (int i = 0; i < 17; i++) {
				Label xmcontent =  new Label(0, 3+i,"", bodyval_cf);
				ws.addCell(xmcontent);
				Label xhcontent =  new Label(1, 3+i, "", bodyval_cf);
				ws.addCell(xhcontent);
				Label yhkhcontent =  new Label(2, 3+i, "", bodyval_cf);
				ws.addCell(yhkhcontent);
				Label lwsjcontent =  new Label(3, 3+i, "", bodyval_cf);
				ws.addCell(lwsjcontent);
				Label sfscontent =  new Label(4, 3+i, "", bodyval_cf);
				ws.addCell(sfscontent);
				Label lkrcontent =  new Label(5, 3+i, "", bodyval_cf);
				ws.addCell(lkrcontent);
				Label bzcontent =  new Label(6, 3+i, "", bodyval_cf);
				ws.addCell(bzcontent);
				ws.setRowView(3+i, 500);
			}
		}
		//����
		int cnt = ws.getRows();
		
		Label hj  =  new Label(0, cnt, "�ϼ�", bodyval_cf);
		ws.addCell(hj);
		ws.mergeCells(0, cnt, 2, cnt);
		Label gshj  =  new Label(3, cnt, lwsj+"", bodyval_cf);
		ws.addCell(gshj);
		Label jehj  =  new Label(4, cnt, je+"", bodyval_cf);
		ws.addCell(jehj);
		Label blank1  =  new Label(5, cnt, "", bodyval_cf);
		ws.addCell(blank1);
		Label blank2  =  new Label(6, cnt, "", bodyval_cf);
		ws.addCell(blank2);
		ws.setRowView(cnt, 500);
		
		Label blank3  =  new Label(0, cnt+2, "ѧ���������ˣ�                   �ù����Ÿ����ˣ�                       �ù����ž����ˣ�", titlehead_cf);
		ws.addCell(blank3);
	}
	
	/**
	 * 
	 * @����:��ȡ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-13 ����03:20:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bmdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public String getBmmc2(String bmdm){
		return new QgzxCxtjDAO().getBmmc2(bmdm);
	}
}
