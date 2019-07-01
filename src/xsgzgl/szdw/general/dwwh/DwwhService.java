package xsgzgl.szdw.general.dwwh;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.inter.SzdwDwwhInterface;

;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_����ά��_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class DwwhService extends CommService implements SzdwDwwhInterface {

	private DwwhDAO dao = new DwwhDAO();

	/**
	 * ���˼�����S�o��ͷ
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getDwwhTop(DwwhModel model, User user) {

		DAO dao = DAO.getInstance();
		String[] en;
		String[] cn;
		if(Base.xxdm.equals("12834")){
			en = new String[] { "pk", "zgh", "xm", "xb", "lxdh", "bmmc", "fdydbs", "bzrdbs", "yhsf","dls"};
			cn = new String[] { "", "ְ����", "����", "�Ա�", "��ϵ�绰", "��������", "��ӳ�������", "�жӳ�������", "�û����","ϵͳ��¼����" };
		}else if(Base.xxdm.equals("10026")){
			en = new String[] { "pk", "zgh"  , "xm"  , "xb"  , "lxdh"    , "bmmc"   , "fdydbs"     , "bzrdbs"     , "yhsf"    ,"dls"        ,"jssf"};
			cn = new String[] { ""  , "ְ����", "����", "�Ա�", "��ϵ�绰", "��������", "����Ա������", "�����δ�����", "�û����","ϵͳ��¼����","��ʦ���" };
		}else {
			en = new String[] { "pk", "zgh", "xm", "xb", "lxdh", "bmmc", "fdydbs", "bzrdbs", "yhsf","dls"};
			cn = new String[] { "", "ְ����", "����", "�Ա�", "��ϵ�绰", "��������", "����Ա������", "�����δ�����", "�û����","ϵͳ��¼����" };
		}

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��û��˼�����S�o�����
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getDwwhList(SzdwGeneralForm myForm, DwwhModel model, User user) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getDwwhList(myForm, user);
	}

	/**
	 * ����˼�����S�oHTML
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public String createDwwhHTML(SearchRsModel rsModel, DwwhModel model, ArrayList<String[]> rsArrList, User user) {

		// IE�汾
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td align=\"center\" width=\"5px\"");
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");

				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");

				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}

				html.append("><a class='name' href='#' onclick='viewjgz(\"" + rs[1] + "\")'>");

				html.append(rs[1]);

				html.append("</a></td>");
				int length = rs.length-5;
				if("10026".equals(Base.xxdm)) length = rs.length-6;
				for (int j = 2; j < length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(rs[j]);
					html.append("</td>");
				}

				

				// ��������
				html.append("<td nowrap=\"nowrap\" title=\"" + rs[5] + "\">");
				if (rs[5].length() > 8) {
					html.append(rs[5].substring(0, 8) + "...");
				} else {
					html.append(rs[5]);
				}
				html.append("</td>");

				// ����Ա������
				html.append("<td nowrap=\"nowrap\" >");
				html.append("<input type='hidden' name='fdyDbs' value='").append(rs[6]).append("'/>");
				html.append("<a href=\"#\" onclick=\"showBjxx('" + pk + "','fdy','" + rs[6] + "');return false;\">");
				html.append("<font color=\"blue\">");
				html.append(rs[6]);
				html.append("</font>");
				html.append("</a>");
				html.append("</td>");

				// �����δ�����
				html.append("<td nowrap=\"nowrap\" >");
				html.append("<input type='hidden' name='bzrDbs' value='").append(rs[7]).append("'/>");
				html.append("<a href=\"#\" onclick=\"showBjxx('" + pk + "','bzr','" + rs[7] + "');return false;\">");
				html.append("<font color=\"blue\">");
				html.append(rs[7]);
				html.append("</font>");
				html.append("</a>");
				html.append("</td>");

				// �Ƿ��û�
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[8]);
				html.append("</td>");
				
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[9]);
				html.append("</td>");
				if("10026".equals(Base.xxdm)){
					html.append("<td nowrap=\"nowrap\" title=\"" + rs[10] + "\">");
					if (rs[10].length() > 8) {
						html.append(rs[10].substring(0, 8) + "...");
					} else {
						html.append(rs[10]);
					}
					html.append("</td>");
				}
				

				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * ͳ�Ƹ���Ա���༶��Ϣ
	 * 
	 * @param zgh
	 * @author qlj
	 * @time 2013-1-24
	 * @return
	 */
	public String countFdyDbj(String zgh) {

		return dao.countFdyDbj(zgh);
	}

	/**
	 * ͳ�Ƹ���Ա���༶��Ϣ
	 * 
	 * @param zgh
	 * @author qlj
	 * @time 2013-1-24
	 * @return
	 */
	public String countBzrDbj(String zgh) {

		return dao.countBzrDbj(zgh);
	}

	/**
	 * ��������ά��DIV html(���ڸ��ĵ�����ʽ)
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public String createDwwhDivStr(DwwhModel model, User user, HttpServletResponse response) throws Exception {

		// ����
		String lx = model.getLx();
		// ְ����
		String zgh = model.getZgh();
		// ˼��������Ϣ
		HashMap<String, String> dwwhMap = dao.getDwxx(zgh);

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>��ʦ��Ϣά��</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody>");
		html.append("<input type=\"hidden\" id=\"xxdm\" value=\"" + Base.xxdm + "\"> ");
		html.append("<tr>");
		html.append("<th width=\"30%\">");
		html.append("<font color=\"red\">*</font>ְ����");
		html.append("</th>");
		html.append("<td>");
		if ("insert".equalsIgnoreCase(lx)) {
			html.append("<input type=\"text\" id=\"text_zgh\" onkeyup=\"this.value=this.value.replace(/[^\\w]/g,'')\" ");
			html.append("maxlength=\"20\" style=\"width:200px\"/>");
		} else if ("update".equalsIgnoreCase(lx)) {
			html.append("<input type=\"hidden\" id=\"text_zgh\" ");
			html.append("value=\"" + dwwhMap.get("zgh") + "\"/> ");
			html.append(dwwhMap.get("zgh"));
		}
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<th>");
		html.append("<font color=\"red\">*</font>����");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" id=\"text_xm\" style=\"width:200px\"");

		String xm = Base.isNull(dwwhMap.get("xm")) ? "" : dwwhMap.get("xm");

		html.append("value=\"" + xm + "\" ");
		html.append("maxlength=\"20\" />");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<th>");
		html.append("<font color=\"red\">*</font>�Ա�");
		html.append("</th>");
		html.append("<td>");
		html.append("<select id=\"select_xb\" style=\"width:200px\">");
		html.append("<option value=\"1\" ");
		if ("1".equalsIgnoreCase(dwwhMap.get("xb"))) {
			html.append("selected=\"selected\"");
		}
		html.append(">��</option>");
		html.append("<option value=\"2\" ");
		if ("2".equalsIgnoreCase(dwwhMap.get("xb"))) {
			html.append("selected=\"selected\"");
		}
		html.append(">Ů</option>");
		html.append("</select>");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<th>");
		html.append("<font color=\"red\">*</font>��������");
		html.append("</th>");
		html.append("<td>");

		// �����б�
		List<HashMap<String, String>> bmList = dao.getBmList();
		List<HashMap<String, String>> syList =null;
		if ("10698".equals(Base.xxdm)) {
			syList = dao.getSyList();
		}
		
		
		String userType = user.getUserType();
		String userDep = user.getUserDep();
		if ("xy".equalsIgnoreCase(userType)) {
			html.append(" <select id=\"\" style=\"width:200px\" ");
			html.append(" disabled='true' value='" + userDep + "' ");
			html.append(" >");
			if (bmList != null && bmList.size() > 0) {
				for (int i = 0; i < bmList.size(); i++) {
					HashMap<String, String> bmMap = bmList.get(i);

					html.append("<option value=\"" + bmMap.get("bmdm") + "\"");
					if (bmMap.get("bmdm").equalsIgnoreCase(dwwhMap.get("bmdm"))) {
						html.append("selected=\"selected\"");
					}
					if (bmMap.get("bmdm").equalsIgnoreCase(userDep) && "xy".equalsIgnoreCase(userType)) {
						html.append("selected=\"selected\"");
					}
					html.append(">");
					html.append(bmMap.get("bmpy") + bmMap.get("bmmc"));
					html.append("</option>");
				}
			}
			html.append("</select>");
			html.append(" <input type=\"hidden\" name=\"select_bmdm\" id=\"select_bmdm\" value=\"" + userDep + "\">");
		} else {

			html.append(" <select id=\"select_bmdm\" style=\"width:200px\" ");
			html.append(" >");
			if (bmList != null && bmList.size() > 0) {
				for (int i = 0; i < bmList.size(); i++) {
					HashMap<String, String> bmMap = bmList.get(i);

					html.append("<option value=\"" + bmMap.get("bmdm") + "\"");
					if (bmMap.get("bmdm").equalsIgnoreCase(dwwhMap.get("bmdm"))) {
						html.append("selected=\"selected\"");
					}
					html.append(">");
					html.append(bmMap.get("bmpy") + bmMap.get("bmmc"));
					html.append("</option>");
				}
			}
			html.append("</select>");
		}

		html.append("</td>");
		html.append("</tr>");
		
		if ("10698".equals(Base.xxdm)) {//������Ժ��ѡ��
			html.append("<tr>");
			html.append("<th>");
			html.append("��Ժ");
			html.append("</th>");
			html.append("<td>");
			html.append(" <select id=\"select_sydm\" style=\"width:200px\" >");
			if (syList != null && syList.size() > 0) {
				for (int i = 0; i < syList.size(); i++) {
					HashMap<String, String> syMap = syList.get(i);

					html.append("<option value=\"" + syMap.get("sydm") + "\"");
					if (syMap.get("sydm").equalsIgnoreCase(dwwhMap.get("sydm"))) {
						html.append("selected=\"selected\"");
					}
					html.append(">");
					html.append(syMap.get("symc"));
					html.append("</option>");
				}
			}
			html.append("</select>");
			html.append("</td>");
			html.append("</tr>");
		}
		
		html.append("<tr>");
		html.append("<th>");
		html.append("��ϵ�绰");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" id=\"text_lxdh\" ");
		// html.append("onkeydown=\"return onlyNum(this,20)\"");
		// html.append("onmousedown=\"return onlyNum(this,20)\"");
		html.append("onkeyup=\"checkInputData(this)\"");
		html.append("maxlength=\"20\" ");

		String lxdh = Base.isNull(dwwhMap.get("lxdh")) ? "" : dwwhMap.get("lxdh");

		html.append("value=\"" + lxdh + "\" ");
		html.append("style=\"width:200px;ime-mode:disabled\"/>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"bz\">\"<span class=\"red\">*</span>\"Ϊ������</div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"����\" onclick=\"saveDwwh('" + lx + "');return false;\" id=\"buttonSave\">");
		html.append("�� ��");
		html.append("</button>");
		html.append("<button type=\"button\"  name=\"�ر�\" onclick=\"iFClose();return false;\" id=\"buttonClose\">�� ��</button>	");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");
		html.append("<script language=\"javascript\" src=\"js/xsgzgl/szdw/dwwh.js\"></script>");
		return html.toString();
	}

	/**
	 * ��������ά��DIV
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public void createDwwhDiv(DwwhModel model, User user, HttpServletResponse response) throws Exception {
		response.getWriter().print(createDwwhDivStr(model, user, response));
	}

	/**
	 * �������ά��
	 * 
	 * @date 2013-01-10
	 * @author ΰ�����
	 */
	public boolean saveDwwh(DwwhModel model, User user) {

		// ְ����
		String zgh = model.getZgh();
		// ����
		String xm = model.getXm();
		// �Ա�
		String xb = model.getXb();
		// ���Ŵ���
		String bmdm = model.getBmdm();
		// ��Ժ����
		String sydm ="";
		if ("10698".equals(Base.xxdm)) {
			 sydm = model.getSydm();
		}
		// ��ϵ�绰
		String lxdh = model.getLxdh();

		boolean isExists = isExists("fdyxxb", "zgh", zgh);

		boolean flag = false;

		try {
			if (isExists) {
				flag = dao.updateFdyxxb(zgh, xm, xb, bmdm, lxdh, user,sydm);
			} else {
				flag = dao.insertFdyxxb(zgh, xm, xb, bmdm, lxdh, user,sydm);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * ɾ������ά��
	 * 
	 * @date 2013-01-10
	 * @author ΰ�����
	 */
	public boolean deleteDwwh(DwwhModel model, User user) {

		boolean flag = false;

		String[] pkValue = model.getPkValue();

		try {

			flag = dao.deleteFdyxxb(pkValue, user);

			if (flag) {
				dao.deleteFdybjb(pkValue, user);
				dao.deleteBzrbbb(pkValue, user);
			}

		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * �����û���DIV
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public void createYhkDiv(DwwhModel model, User user, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>��ӵ��û���</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width=\"30%\">");
		html.append("<font color=\"red\">*</font>������");
		html.append("</th>");
		html.append("<td>");

		// �û����б�
		List<HashMap<String, String>> yhzList = dao.getTableListInfo("yhzb", new String[] { "zdm", "zmc" }, "", "", "");

		html.append("<select id=\"select_zdm\" style=\"width:200px\">");
		if (yhzList != null && yhzList.size() > 0) {
			for (int i = 0; i < yhzList.size(); i++) {
				HashMap<String, String> bmMap = yhzList.get(i);
				html.append("<option value=\"" + bmMap.get("zdm") + "\"");
				html.append(">");
				html.append(bmMap.get("zmc"));
				html.append("</option>");
			}
		}
		html.append("</select>");

		html.append("</td>");
		html.append("</tr>");

		html.append("</tbody>");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<div class=\"bz\">\"<span class=\"red\">*</span>\"Ϊ������</div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"����\" onclick=\"saveYhk();return false;\" id=\"buttonSave\">");
		html.append("�� ��");
		html.append("</button>");
		html.append("<button type=\"button\"  name=\"�ر�\" onclick=\"closeWindown();return false;\" id=\"buttonClose\">�� ��</button>	");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");

		response.getWriter().print(html.toString());
	}

	/**
	 * �����û����û���
	 * 
	 * @date 2013-01-10
	 * @author ΰ�����
	 */
	public boolean saveYhk(DwwhModel model, User user) {

		// ����
		String[] pkValue = model.getPkValue();
		// �����
		String zdm = model.getZdm();
		// �Ѵ��ڵ�ְ����
		String[] isExistsZgh = null;

		boolean flag = true;

		try {

			isExistsZgh = dao.getYh(pkValue);

			// �����ڵ�ְ����
			String[] noExistsZgh = getNoRepeatArrValue(pkValue, isExistsZgh);

			if (isExistsZgh == null || isExistsZgh.length == 0) {
				noExistsZgh = pkValue;
			}

			if (noExistsZgh != null && noExistsZgh.length > 0) {
				flag = dao.insertYhb(noExistsZgh, zdm, user);
			}

			if (isExistsZgh != null && isExistsZgh.length > 0) {
				flag = dao.updateYhb(isExistsZgh, zdm, user);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * �����û���DIV
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public String createYxjrDiv(DwwhModel model, User user, HttpServletResponse response) throws Exception {
		String pkValue = "";
		for (String str : model.getPkValue()) {
			pkValue += str + "!!array!!";
		}
		pkValue = pkValue.substring(0, pkValue.lastIndexOf("!!array!!"));
		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");
		html.append("<input type=\"hidden\" id=\"selectId\" value=" + pkValue + ">");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>����������á�ע��ֻ�е�����ʦ���������£������ò������á�</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width=\"20%\">");
		html.append("�������");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"radio\" name=\"radio_yxjr\" value=\"false\" checked=\"checked\"/>");
		html.append("������Ժϵ/ѧУ(���Դ�����ʦ��ݲ�������)");
		html.append("<br>");
		html.append("<input type=\"radio\" name=\"radio_yxjr\" value=\"true\"/>");
		html.append("����Ժϵ/ѧУ(�����л�ΪԺϵ/ѧУ��ݲ�������)");
		html.append("</td>");
		html.append("</tr>");

		html.append("</tbody>");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<div class=\"bz\"></div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"����\" onclick=\"saveYxjr();return false;\" id=\"buttonSave\">");
		html.append("�� ��");
		html.append("</button>");
		html.append("<button type=\"button\"  name=\"�ر�\" onclick=\"iFClose();return false;\" id=\"buttonClose\">�� ��</button>	");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");

		// response.getWriter().print(html.toString());
		return html.toString();
	}

	/**
	 * ����ԺУ����
	 * 
	 * @date 2013-01-10
	 * @author ΰ�����
	 */
	public boolean saveYxjr(DwwhModel model, User user) {

		// �Ƿ����ԺУ
		String sfjryx = model.getSfjryx();
		// ����
		String[] pkValue = model.getPkValue();

		boolean flag = false;

		try {
			flag = dao.updateFdyxxb(pkValue, sfjryx, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * �����༶��ϢDIV
	 * 
	 * @date 2013-01-15
	 * @author ΰ�����
	 */
	public void createBjxxDiv(DwwhModel model, User user, HttpServletResponse response) throws Exception {

		// ����
		String lx = model.getLx();
		// ��������
		String lxmc;
		if (Base.xxdm.equals("12834")) {
			 lxmc = "fdy".equalsIgnoreCase(lx) ? "��ӳ�" : "�жӳ�";
		}else {
			 lxmc = "fdy".equalsIgnoreCase(lx) ? "����Ա" : "������";
		}
		// ְ����
		String zgh = model.getZgh();

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		HashMap<String, String> map = getDwwh(model, user);

		html.append("<div>");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<tr>");
		html.append("<th width=\"25%\">ְ����</th>");
		html.append("<td width=\"25%\">" + map.get("zgh") + "</td>");
		html.append("<th width=\"25%\">����</th>");
		html.append("<td width=\"25%\">" + map.get("xm") + "</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<th width=\"25%\">��������</th>");
		html.append("<td width=\"25%\">" + map.get("bmmc") + "</td>");
		html.append("<th width=\"25%\">" + lxmc + "������</th>");
		html.append("<td width=\"25%\">" + map.get("num") + "</td>");
		html.append("</tr>");
		html.append("</table>");
		html.append("</div>");

		html.append("<div>");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<tr>");
		html.append("<th width=\"10%\" ><div align='left'>�꼶</div></th>");
		html.append("<th width=\"30%\"><div align='left'>Ժϵ</div></th>");
		html.append("<th width=\"30%\"><div align='left'>רҵ</div></th>");
		html.append("<th width=\"30%\"><div align='left'>�༶</div></th>");
		html.append("</tr>");
		html.append("</table>");
		html.append("</div>");

		List<HashMap<String, String>> bjList = null;

		if ("fdy".equalsIgnoreCase(lx)) {
			bjList = dao.getFdybjList(zgh);
		} else {
			bjList = dao.getBzrbjList(zgh);
		}

		html.append("<div style=\"width:100%;height:230px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table class=\"formlist\">");

		for (int i = 0; i < bjList.size(); i++) {
			html.append("<tr>");
			html.append("<td width=\"10%\">" + bjList.get(i).get("nj") + "</td>");
			html.append("<td width=\"30%\">" + bjList.get(i).get("xymc") + "</td>");
			html.append("<td width=\"30%\">" + bjList.get(i).get("zymc") + "</td>");
			html.append("<td width=\"30%\">" + bjList.get(i).get("bjmc") + "</td>");
			html.append("</tr>");
		}

		if (8 - bjList.size() > 0) {
			for (int i = 0; i < 8 - bjList.size(); i++) {
				html.append("<tr>");
				html.append("<td width=\"10%\">&nbsp;</td>");
				html.append("<td width=\"30%\">&nbsp;</td>");
				html.append("<td width=\"30%\">&nbsp;</td>");
				html.append("<td width=\"30%\">&nbsp;</td>");
				html.append("</tr>");
			}
		}

		html.append("</table>");
		html.append("</div>");

		html.append("<div>");
		html.append("<table class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<div class=\"bz\"></div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"�ر�\" onclick=\"closeWindown();return false;\" id=\"buttonClose\">�� ��</button>	");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");

		html.append("</div>");

		response.getWriter().print(html.toString());
	}

	/**
	 * ��ȡ˼��������Ϣ
	 * 
	 * @date 2013-01-11
	 * @author ΰ�����
	 */
	public HashMap<String, String> getDwwh(DwwhModel model, User user) {

		// �û�����
		String lx = model.getLx();
		// ְ����
		String zgh = model.getZgh();

		if ("fdy".equalsIgnoreCase(lx)) {
			return dao.getFdyInfo(zgh);
		} else if ("bzr".equalsIgnoreCase(lx)) {
			return dao.getBzrInfo(zgh);
		}

		return null;
	}

	/**
	 * �����꼶Div
	 * 
	 * @date 2013-01-11
	 * @author ΰ�����
	 */
	public void createNjLvDiv(DwwhModel model, User user, HttpServletResponse response) throws Exception {

		// ����
		String lx = model.getLx();
		// ְ����
		String zgh = model.getZgh();

		// ����꼶�б�
		List<HashMap<String, String>> njList = dao.getNjList(lx, zgh);

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();
		html.append("<h3><span>�꼶</span></h3>");
		html.append("<ul>");

		if (njList != null && njList.size() > 0) {

			boolean flag = false;

			for (int i = 0; i < njList.size(); i++) {
				HashMap<String, String> map = njList.get(i);

				// �꼶
				String nj = map.get("nj");
				// �Ƿ���
				String sfbb = map.get("sfbb");

				if (flag) {
					flag = false;
				} else {
					html.append("<li>");
				}

				html.append("<input type=\"checkbox\" ");
				html.append("onclick=\"clickNj(this,'" + nj + "','" + i + "','checkbox');\" ");
				html.append("value=\"" + nj + "\" ");
				html.append("id=\"checkbox_nj_" + nj + "\" ");
				// if("yes".equalsIgnoreCase(sfbb)){
				// html.append("checked=\"checked\" ");
				// }
				html.append("/> ");

				html.append("<a href=\"#\" id=\"a_nj_" + i + "\" ");
				html.append("onclick=\"clickNj(this,'" + nj + "','" + i + "','a');return false;\" >");
				html.append(nj);
				if ("yes".equalsIgnoreCase(sfbb)) {
					html.append("<font color=\"red\">������</font> ");
				}
				html.append("</a>");
				html.append("</li>");
			}
		}

		html.append("</ul>");

		response.getWriter().print(html.toString());
	}

	/**
	 * ������������Div
	 * 
	 * @date 2013-01-11
	 * @author ΰ�����
	 */
	public void createOtherLvDiv(DwwhModel model, User user, HttpServletResponse response) throws Exception {

		// ����
		String lx = model.getLx();
		// ְ����
		String zgh = model.getZgh();
		// ����
		String type = model.getType();

		List<HashMap<String, String>> bmList = dao.getBmList(lx, zgh);
		// ���ȼ�����
		String maxBz = "1";
		// �ڶ��ȼ�����
		String secBz = "2";
		// �����ȼ�����
		String thiBz = "3";
		// ���ĵȼ�����
		String fouBz = "4";

		// ���������б�
		List<HashMap<String, String>> secList = new ArrayList<HashMap<String, String>>();
		// ���������б�
		List<HashMap<String, String>> thiList = new ArrayList<HashMap<String, String>>();
		// �ļ������б�
		List<HashMap<String, String>> fouList = new ArrayList<HashMap<String, String>>();

		// ѡ�е��꼶
		String checkedBzdm = model.getNjV();
		// �꼶�Ƿ�ѡ��
		String checked_nj = model.getChecked_nj();

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		if (bmList != null && bmList.size() > 0) {
			html.append("<div id=\"div_fpbj_old\" style=\"display:none\">");
			for (int i = 0; i < bmList.size(); i++) {
				HashMap<String, String> map = bmList.get(i);
				// ���Ƶȼ�
				String bzdj = map.get("bzdj");
				// �ϼ�����
				String sjdm = map.get("sjdm");

				if ("2".equalsIgnoreCase(bzdj)) {
					secList.add(map);
				} else if ("3".equalsIgnoreCase(bzdj)) {
					thiList.add(map);
				} else if ("4".equalsIgnoreCase(bzdj)) {
					fouList.add(map);

					if ("checkbox".equalsIgnoreCase(type)) {
						if ("true".equalsIgnoreCase(checked_nj) && sjdm.substring(0, 4).equalsIgnoreCase(checkedBzdm)) {
							html.append("<input type=\"text\" ");
							html.append("name=\"hidden_bjdm\"");
							html.append("id=\"hidden_bjdm_" + map.get("bzdm") + "\"");
							html.append("value=\"" + map.get("bzdm") + "\"");
							html.append("/> ");
						}
					} else {
						if ("yes".equalsIgnoreCase(map.get("sfbb"))) {
							html.append("<input type=\"text\" ");
							html.append("name=\"hidden_bjdm\"");
							html.append("id=\"hidden_bjdm_" + map.get("bzdm") + "\"");
							html.append("value=\"" + map.get("bzdm") + "\"");
							html.append("/> ");
						}
					}
				}
			}
			html.append("</div>");
		}

		html.append("<table width=\"100%\" border=\"0\">");

		if (bmList != null && bmList.size() > 0) {

			for (int i = 0; i < bmList.size(); i++) {
				HashMap<String, String> map = bmList.get(i);
				// ���Ƶȼ�
				String bzdj = map.get("bzdj");
				// ���ƴ���
				String bzdm = map.get("bzdm");
				// ��������
				String bzmc = map.get("bzmc");
				// �ϼ�����
				String sjdm = map.get("sjdm");
				// �Ƿ���
				String sfbb = map.get("sfbb");
				// �¼���������
				int num = Integer.parseInt(map.get("num"));

				if (secBz.equalsIgnoreCase(bzdj) && checkedBzdm.equalsIgnoreCase(sjdm)) {

					html.append("<tbody id=\"tbody_" + bzdm + "\">");

					html.append("<tr id=\"" + bzdm + "\" ");
					html.append("class=\"tr_02\">");

					// ��������
					html.append("<td style=\"width:140px\" class=\"list_02\"");
					html.append("rowspan=\"" + num + "\" ");
					html.append(">");

					html.append("<input type=\"checkbox\" ");
					html.append("onclick=\"clickXyCheckBox('" + bzdm + "')\" ");
					if ("true".equalsIgnoreCase(checked_nj)) {
						html.append("checked=\"checked\" ");
					}
					html.append("value=\"" + bzdm + "\" ");
					html.append("name=\"checkbox_xy_" + sjdm + "\" ");
					html.append("id=\"checkbox_xy_" + bzdm + "\" ");
					html.append("/> ");

					html.append("<label>");
					html.append("<a href=\"#\" name=\"jxbz\" id=\"" + bzdj + "_" + bzdm + "\" title=\"" + bzmc + "\" onclick=\"return false;\">");
					if (bzmc.length() > 7) {
						html.append(bzmc.substring(0, 7) + "...");
					} else {
						html.append(bzmc);
					}
					if ("yes".equalsIgnoreCase(sfbb)) {
						html.append("<font color=\"red\">������</font> ");
					}
					html.append("</a></label>");
					html.append("</td>");

					boolean thi_flag = true;
					boolean had_thi = false;
					boolean had_fou = false;

					if (thiList != null && thiList.size() > 0) {
						// ��������
						for (int j = 0; j < thiList.size(); j++) {
							HashMap<String, String> thiMap = thiList.get(j);
							// ���Ƶȼ�
							String thi_bzdj = thiMap.get("bzdj");
							// ���ƴ���
							String thi_bzdm = thiMap.get("bzdm");
							// ��������
							String thi_bzmc = thiMap.get("bzmc");
							// �ϼ�����
							String thi_sjdm = thiMap.get("sjdm");
							// �Ƿ���
							String thi_sfbb = thiMap.get("sfbb");
							if (thi_sjdm.equalsIgnoreCase(bzdm)) {

								had_thi = true;
								boolean li_flag = false;

								if (thi_flag) {
									html.append("<td style=\"width:150px\" class=\"list_03\">");

									html.append("<input type=\"checkbox\" ");
									html.append("onclick=\"clickZyCheckBox('" + thi_bzdm + "','" + thi_sjdm + "')\" ");
									if ("true".equalsIgnoreCase(checked_nj)) {
										html.append("checked=\"checked\" ");
									}
									html.append("value=\"" + thi_bzdm + "\" ");
									html.append("name=\"checkbox_zy_" + thi_sjdm + "\" ");
									html.append("id=\"checkbox_zy_" + thi_bzdm + "\" ");
									html.append("/> ");

									html.append("<label>");
									html.append("<a href=\"#\" title=\"" + thi_bzmc + "\"name=\"jxbz\" id=\"" + thi_bzdj + "_" + thi_bzdm + "\" onclick=\"return false;\">");

									if (thi_bzmc.length() > 5) {
										html.append(thi_bzmc.substring(0, 5) + "...");
									} else {
										html.append(thi_bzmc);
									}
									if ("yes".equalsIgnoreCase(thi_sfbb)) {
										html.append("<font color=\"red\">������</font> ");
									}
									html.append("</a></label>");
									html.append("</td>");

									li_flag = true;
									thi_flag = false;
								}

								int four_num = 0;
								boolean four_flag = true;

								// �ļ�����
								if (fouList != null && fouList.size() > 0) {
									had_fou = false;
									for (int k = 0; k < fouList.size(); k++) {
										HashMap<String, String> fouMap = fouList.get(k);
										// ���Ƶȼ�
										String fou_bzdj = fouMap.get("bzdj");
										// ���ƴ���
										String fou_bzdm = fouMap.get("bzdm");
										// ��������
										String fou_bzmc = fouMap.get("bzmc");
										// �ϼ�����
										String fou_sjdm = fouMap.get("sjdm");
										// �Ƿ���
										String fou_sfbb = fouMap.get("sfbb");

										if (fou_sjdm.equalsIgnoreCase(thi_bzdm) && li_flag) {

											had_fou = true;

											if (four_flag) {
												html.append("<td>");
												html.append("<ul>");

												four_flag = false;
											}

											// if(four_num<8){
											html.append("<li>");

											html.append("<input type=\"checkbox\" ");
											html.append("onclick=\"clickBjCheckBox('" + fou_bzdm + "')\" ");

											if ("checkbox".equalsIgnoreCase(type)) {
												if ("true".equalsIgnoreCase(checked_nj)) {
													html.append("checked=\"checked\" ");
												}
											} else {
												if ("yes".equalsIgnoreCase(fou_sfbb)) {
													html.append("checked=\"checked\" ");
												}
											}

											html.append("value=\"" + fou_bzdm + "\" ");
											html.append("name=\"checkbox_bj_" + fou_sjdm + "\" ");
											html.append("id=\"checkbox_bj_" + fou_bzdm + "\" ");
											html.append("/> ");

											html.append("<a href=\"#\" name=\"minbz\" id=\"" + fou_bzdj + "_" + fou_bzdm + "\" onclick=\"return false;\" title=\"" + fou_bzmc + "\">");
											if (fou_bzmc.length() > 18) {
												html.append(fou_bzmc.substring(0, 18) + "...");
											} else {
												html.append(fou_bzmc);
											}
											html.append("</a>");
											html.append("</li>");
											// }

											four_num++;
										}
									}
								}

								if (!four_flag) {
									html.append("</ul>");
									html.append("</td>");
								}
							}
						}
					}

					if (!had_thi) {
						// ����
						html.append("<td style=\"\">");
						html.append("<label>");
						html.append("<a>");
						html.append("</a></label>");
						html.append("</td>");
					}

					if (!had_fou) {
						// ����
						html.append("<td style=\"\">");
						html.append("<label>");
						html.append("<a>");
						html.append("</a></label>");
						html.append("</td>");
					}

					html.append("</tr>");

					if (had_thi) {

						boolean thi_next_flag = false;

						if (thiList != null && thiList.size() > 0) {
							// ��������
							for (int j = 0; j < thiList.size(); j++) {
								HashMap<String, String> thiMap = thiList.get(j);
								// ���Ƶȼ�
								String thi_bzdj = thiMap.get("bzdj");
								// ���ƴ���
								String thi_bzdm = thiMap.get("bzdm");
								// ��������
								String thi_bzmc = thiMap.get("bzmc");
								// �ϼ�����
								String thi_sjdm = thiMap.get("sjdm");
								// �Ƿ���
								String thi_sfbb = thiMap.get("sfbb");

								if (thi_sjdm.equalsIgnoreCase(bzdm)) {

									if (thi_next_flag) {

										html.append("<tr id=\"" + bzdm + "\" ");
										html.append("class=\"tr_02\">");

										html.append("<td style=\"width:150px\" class=\"list_03\">");

										html.append("<input type=\"checkbox\" ");
										html.append("onclick=\"clickZyCheckBox('" + thi_bzdm + "','" + thi_sjdm + "')\" ");
										if ("true".equalsIgnoreCase(checked_nj)) {
											html.append("checked=\"checked\" ");
										}
										html.append("value=\"" + thi_bzdm + "\" ");
										html.append("name=\"checkbox_zy_" + thi_sjdm + "\" ");
										html.append("id=\"checkbox_zy_" + thi_bzdm + "\" ");
										html.append("/> ");

										html.append("<label>");
										html.append("<a href=\"#\" title=\"" + thi_bzmc + "\"name=\"jxbz\" id=\"" + thi_bzdj + "_" + thi_bzdm + "\" onclick=\"return false;\">");

										if (thi_bzmc.length() > 5) {
											html.append(thi_bzmc.substring(0, 5) + "...");
										} else {
											html.append(thi_bzmc);
										}
										if ("yes".equalsIgnoreCase(thi_sfbb)) {
											html.append("<font color=\"red\">������</font> ");
										}
										html.append("</a>");
										html.append("</label>");
										html.append("</td>");

										int four_num = 0;
										boolean four_flag = true;

										// �ļ�����
										if (fouList != null && fouList.size() > 0) {
											for (int k = 0; k < fouList.size(); k++) {
												HashMap<String, String> fouMap = fouList.get(k);
												// ���Ƶȼ�
												String fou_bzdj = fouMap.get("bzdj");
												// ���ƴ���
												String fou_bzdm = fouMap.get("bzdm");
												// ��������
												String fou_bzmc = fouMap.get("bzmc");
												// �ϼ�����
												String fou_sjdm = fouMap.get("sjdm");
												// �Ƿ���
												String fou_sfbb = fouMap.get("sfbb");

												if (fou_sjdm.equalsIgnoreCase(thi_bzdm)) {

													if (four_flag) {
														html.append("<td>");
														html.append("<ul>");

														four_flag = false;
													}

													// if(four_num<8){
													html.append("<li>");

													html.append("<input type=\"checkbox\" ");
													html.append("onclick=\"clickBjCheckBox('" + fou_bzdm + "')\" ");

													if ("checkbox".equalsIgnoreCase(type)) {
														if ("true".equalsIgnoreCase(checked_nj)) {
															html.append("checked=\"checked\" ");
														}
													} else {
														if ("yes".equalsIgnoreCase(fou_sfbb)) {
															html.append("checked=\"checked\" ");
														}
													}

													html.append("value=\"" + fou_bzdm + "\" ");
													html.append("name=\"checkbox_bj_" + fou_sjdm + "\" ");
													html.append("id=\"checkbox_bj_" + fou_bzdm + "\" ");
													html.append("/> ");

													html.append("<a href=\"#\" name=\"minbz\" id=\"" + fou_bzdj + "_" + fou_bzdm + "\" onclick=\"return false;\" title=\"" + fou_bzmc + "\">");
													if (fou_bzmc.length() > 18) {
														html.append(fou_bzmc.substring(0, 18) + "...");
													} else {
														html.append(fou_bzmc);
													}
													html.append("</a>");
													html.append("</li>");
													// }

													four_num++;
												}
											}
										}

										if (!four_flag) {
											html.append("</ul>");
											html.append("</td>");
										}

										if (!had_fou) {
											html.append("<td style=\"\">");
											html.append("<label>");
											html.append("<a>");
											html.append("</a></label>");
											html.append("</td>");
										}

										html.append("</tr>");
									}

									thi_next_flag = true;
								}

							}
						}
					}

					html.append("</tbody>");

				}
			}
		}

		html.append("</table>");
		// System.out.print(html);
		response.getWriter().print(html.toString());
	}

	/**
	 * �������༶
	 * 
	 * @date 2013-01-14
	 * @author ΰ�����
	 */
	public boolean saveFpbj(DwwhModel model, User user) {

		DAO commDAO = DAO.getInstance();

		// ����
		String[] bjdm = model.getBjdm();
		// ְ����
		String zgh = model.getZgh();
		// ����
		String lx = model.getLx();

		boolean flag = false;

		String[] bjdm_other = model.getBjdm_other();

		String[] bjdm_my = model.getBjdm_my();

		try {
			
			for (int i = 0; i < bjdm_other.length; i++) {
				bjdm_other[i] = URLDecoder.decode(bjdm_other[i],"UTF-8");
			}
			
			for (int i = 0; i < bjdm_my.length; i++) {
				bjdm_my[i] = URLDecoder.decode(bjdm_my[i],"UTF-8");
			}
			
			if ("fdy".equalsIgnoreCase(lx)) {
				flag = dao.deleteFdybjb(zgh, bjdm_my, user);
				// dao.deleteFdybjb(zgh, user);

				if (bjdm_my != null && bjdm_my.length > 0 && !Base.isNull(bjdm_my[0]) && flag) {
					flag = dao.insertFdybjb(zgh, bjdm_my, user);
					if(flag){
						dao.saveBbls(zgh, bjdm_my, "save", true);
					}
				}

			} else if ("bzr".equalsIgnoreCase(lx)) {
				flag = dao.deleteBzrbbb(zgh, bjdm_my, user);
				// dao.deleteBzrbbb(zgh, user);

				if (bjdm_my != null && bjdm_my.length > 0 && !Base.isNull(bjdm_my[0]) && flag) {
					flag = dao.insertBzrbbb(zgh, bjdm_my, user);
					if(flag){
						dao.saveBbls(zgh, bjdm_my, "save", false);
					}
				}
			}

		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * �������༶
	 * 
	 * @date 2013-01-24
	 * @author qlj
	 */
	public boolean disfrockFpbj(DwwhModel model, User user) {

		// ְ����
		String zgh = model.getZgh();
		// ����
		String lx = model.getLx();

		boolean flag = false;

		String[] bjdm_my = model.getBjdm_my();

		try {

			for (int i = 0; i < bjdm_my.length; i++) {
				bjdm_my[i] = URLDecoder.decode(bjdm_my[i],"UTF-8");
			}
			
			// ��������Ա���
			if ("fdy".equalsIgnoreCase(lx)) {

				flag = dao.disfrockFdybjb(zgh, bjdm_my, user);
				if(flag){
					dao.saveBbls(zgh, bjdm_my, "update", true);
				}

				// ���������α��
			} else if ("bzr".equalsIgnoreCase(lx)) {

				flag = dao.disfrockBzrbbb(zgh, bjdm_my, user);
				if(flag){
					dao.saveBbls(zgh, bjdm_my, "update", false);
				}
			}

		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * ��ʼ�����ܲ���
	 * 
	 * @date 2013-01-14
	 * @author ΰ�����
	 */
	public void initParameter() {

		String path = "szdw_general_dwwh.do";

		// �߼���ѯ�Ƿ�����
		boolean isSearch = isExists("xg_search_szb", "path", path);

		if (!isSearch) {
			initSearch();
		}
	}

	/**
	 * ��ʼ����ѯ����
	 * 
	 * @date 2013-01-14
	 * @author ΰ�����
	 */
	private void initSearch() {

		List<String[]> params = new ArrayList<String[]>();

		String path = "szdw_general_dwwh.do";
		String[] tj = new String[] { "zgh", "xm", "bm", "xb", "sf", "sflx", "yhsf" };
		String[] mc = new String[] { "ְ����", "����", "��������", "�Ա�", "�Ƿ񸨵�Ա", "�Ƿ������", "�û����" };
		String[] lx = new String[] { "mhcx", "mhcx", "mhcx", "djcx", "djcx", "djcx", "djcx" };
		String[] zd = new String[] { "zgh", "xm", "bmmc", "xb", "sffdy", "sfbzr", "yhsf" };
		String[] ssmk = new String[] { "szdw", "szdw", "szdw", "szdw", "szdw", "szdw", "szdw", };
		String[] xssx = new String[] { "1", "2", "3", "1", "2", "3", "4" };

		for (int i = 0; i < tj.length; i++) {
			String[] value = new String[] { path, tj[i], mc[i], lx[i], zd[i], ssmk[i], xssx[i] };
			params.add(value);
		}

		try {
			dao.initSearch(params);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}

	/**
	 * ��ò����б�
	 * 
	 * @date 2013-01-15
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getBmList() {
		return dao.getBmList();
	}

	/**
	 *������ְ��Option
	 * 
	 * @date 2013-01-18
	 * @author ΰ�����
	 */
	public String createJzgOption(DwwhModel model) throws Exception {

		// ����
		String lx = model.getLx();
		// ְ����
		String zgh = model.getZgh();
		// ����
		String xm = model.getXm();
		// ���Ŵ���
		String bmdm = model.getBmdm();
		// �������
		String dbqk = model.getDbqk();

		StringBuilder html = new StringBuilder();

		List<HashMap<String, String>> jzgList = dao.getJzgxxList(zgh, xm, bmdm, dbqk, lx);

		if (jzgList != null && jzgList.size() > 0) {
			for (int i = 0; i < jzgList.size(); i++) {
				html.append("<option ");
				html.append("value=\"" + jzgList.get(i).get("zgh") + "\" ");

				if (zgh.equalsIgnoreCase(jzgList.get(i).get("zgh"))) {
					html.append("selected=\"selected\"");
				}
				html.append(">");
				html.append(jzgList.get(i).get("xm"));
				html.append("(");
				html.append(jzgList.get(i).get("zgh"));
				html.append(")");
				html.append("</option>");
			}
		}

		return html.toString();

	}

	/**
	 * ���˼��������ͷ
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getSetupTop(DwwhModel model, User user) {

		DAO dao = DAO.getInstance();

		String lx = model.getLx();
		String lxmc ;
		if (Base.xxdm.equals("12834")) {
			lxmc = "fdy".equalsIgnoreCase(lx) ? "��ӳ�" : "�жӳ�";
		}else {
			lxmc = "fdy".equalsIgnoreCase(lx) ? "����Ա" : "������";
		}
		String[] en = new String[] { "nj", "xymc","symc", "bjmc", "lx" };
		String[] cn = new String[] { "�꼶", "Ժϵ", "��Ժ","�༶", lxmc + "��" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��û��˼�����������
	 * 
	 * @date 2013-01-18
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getSetupList(SzdwGeneralForm myForm, DwwhModel model, User user) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getSetupList(myForm, model, user);
	}

	/**
	 * ����˼�������HTML
	 * 
	 * @date 2013-01-18
	 * @author ΰ�����
	 */
	public String createSetupHTML(SearchRsModel rsModel, DwwhModel model, ArrayList<String[]> rsArrList, User user) {

		// IE�汾
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		String lx = model.getLx();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String pk = rs[0];
				String brdb = rs[rs.length - 1];

				html.append("<tr onclick=\"rowOnClick(this);\"  ");
				if ("yes".equalsIgnoreCase(brdb)) {
					html.append(" style=\"background:#7bb9e3\"");
				}
				html.append(" > ");
				html.append("<td align=\"center\" width=\"5px\"");
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"checkVal\" ");
				html.append("value=\"" + pk + "\" ");

				html.append("/>");
				html.append("</td>");

				String nj = rs[1];
				String xy = rs[2];
				String zy = rs[3];
				String bj = rs[4];
				String sy = rs[7];

				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				html.append(">");
				html.append(nj);
				html.append("</td>");

				// ѧԺ
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// html.append("title=\"" + xy + "\"");
				html.append(">");
				html.append(xy);
				html.append("</td>");

				//��Ժ
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// html.append("title=\"" + xy + "\"");
				html.append(">");
				html.append(sy);
				html.append("</td>");
				// רҵ
				// html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// html.append("title=\"" + zy + "\"");
				// html.append(">");
				// if (!Base.isNull(zy) && zy.length() > 10) {
				// html.append(zy.substring(0, 10) + "...");
				// } else {
				// html.append(zy);
				// }
				// html.append("</td>");

				// �༶
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// html.append("title=\"" + bj + "\"");
				html.append(">");
				html.append(bj);
				html.append("</td>");

				// ������
				html.append("<td nowrap=\"nowrap\" >");
				html.append("<a href=\"#\" onclick=\"showJzgxx('" + pk + "','" + lx + "','" + rs[5] + "');return false;\">");
				html.append("<font color=\"blue\">");
				html.append("<U>" + rs[5] + "</u>");
				html.append("</font>");
				html.append("</a>");
				html.append("</td>");
			}
		}

		String jsdbs = "0";
		if ("fdy".equalsIgnoreCase(lx)) {
			jsdbs = countFdyDbj(model.getZgh());
		} else if ("bzr".equalsIgnoreCase(lx)) {
			jsdbs = countBzrDbj(model.getZgh());
		}

		html.append("<div style=\"display:none\" >");
		html.append("<input type=\"text\" name=\"hid_dbs\" id=\"hid_dbs\" value=\"" + jsdbs + "\" >");
		html.append("<div style=\"display:none\" >");

		return html.toString();
	}

	/**
	 * ������ְ����ϢDIV
	 * 
	 * @date 2013-01-15
	 * @author ΰ�����
	 */
	public void createJzgxxDiv(DwwhModel model, User user, HttpServletResponse response) throws Exception {

		// ����
		String lx = model.getLx();
		// ��������
		String lxmc = "fdy".equalsIgnoreCase(lx) ? "����Ա" : "������";
		// �༶����
		String bjdm = model.getBjV();

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		HashMap<String, String> map = dao.getRsInfo("view_njxyzybj", "bjdm", bjdm, new String[] { "nj", "xymc", "zymc", "bjmc" });

		html.append("<div>");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<tr>");
		html.append("<th width=\"15%\">�꼶</td>");
		html.append("<td width=\"35%\">" + map.get("nj") + "</td>");
		html.append("<th width=\"15%\">Ժϵ</td>");
		html.append("<td width=\"35%\">" + map.get("xymc") + "</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<th>רҵ</td>");
		html.append("<td>" + map.get("zymc") + "</td>");
		html.append("<th>�༶</td>");
		html.append("<td>" + map.get("bjmc") + "</td>");
		html.append("</tr>");
		html.append("</table>");
		html.append("</div>");

		html.append("<div style=\"width:100%;height:230px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<tr>");
		html.append("<th width=\"10%\" style='text-align: center;'>ְ����</th>");
		html.append("<th width=\"30%\" style='text-align: center;'>����</th>");
		html.append("<th width=\"30%\" style='text-align: center;'>�Ա�</th>");
		html.append("<th width=\"30%\" style='text-align: center;'>��������</th>");
		html.append("</tr>");
//		html.append("</table>");
//		html.append("</div>");

		List<HashMap<String, String>> jzgList = dao.getJzgList(bjdm, lx);

//		html.append("<div style=\"width:100%;height:230px;overflow-x:hidden;overflow-y:auto;\">");
//		html.append("<table class=\"formlist\">");

		for (int i = 0; i < jzgList.size(); i++) {
			html.append("<tr>");
			html.append("<td width=\"10%\" style='text-align: center;'>" + jzgList.get(i).get("zgh") + "</td>");
			html.append("<td width=\"30%\" style='text-align: center;'>" + jzgList.get(i).get("xm") + "</td>");
			html.append("<td width=\"30%\" style='text-align: center;'>" + jzgList.get(i).get("xb") + "</td>");
			html.append("<td width=\"30%\" style='text-align: center;'>" + jzgList.get(i).get("bmmc") + "</td>");
			html.append("</tr>");
		}

		if (8 - jzgList.size() > 0) {
			for (int i = 0; i < 8 - jzgList.size(); i++) {
				html.append("<tr>");
				html.append("<td width=\"10%\">&nbsp;</td>");
				html.append("<td width=\"30%\">&nbsp;</td>");
				html.append("<td width=\"30%\">&nbsp;</td>");
				html.append("<td width=\"30%\">&nbsp;</td>");
				html.append("</tr>");
			}
		}

		html.append("</table>");
		html.append("</div>");

		html.append("<div>");
		html.append("<table class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<div class=\"bz\"></div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"�ر�\" onclick=\"closeWindown();return false;\" id=\"buttonClose\">�� ��</button>	");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");

		html.append("</div>");

		response.getWriter().print(html.toString());
	}

	/**
	 * ��ѯְ����Ϣ
	 */
	public DwwhJzgModle cxJzgxx(DwwhJzgModle model, User user, HttpServletRequest request) throws Exception {
		DAO d = new DAO();
		Map<String, String> map = dao.getSzDwxxByZgh(model.getZgh());
		// ����ʼ��ҳ���ѯ
		request.setAttribute("mzList", d.getMzList());
		request.setAttribute("zzmmList", d.getZzmmList());

		request.setAttribute("bmList", d.getYjbmList());
		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("ssList", stuDao.getSsList());// ��ȡʡ���б�
		return mapToModle(map, model);
	}

	/*
	 * public static void main(String[] args) { Map<String,String> map = new
	 * HashMap<String,String>(); map.put("ZGH", "1"); map.put("XM", "222");
	 * map.put("XB", "LXDH"); DwwhJzgModle modle = new DwwhJzgModle(); modle =
	 * new DwwhService().mapToModle(map, modle);
	 * System.out.println(modle.getZgh()+"="+modle.getXm()+modle.getLxdh()); }
	 */
	/**
	 * �޸Ľ�ְ����Ϣ
	 */
	public Boolean updateJzgxx(HttpServletRequest request) {
		DwwhJzgModle model = new DwwhJzgModle();
		model = requestToModle(request, model);
		DwwhJzgDAO superDao = new DwwhJzgDAO();
		try {
			return superDao.updateJzgxx(model);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Map<String��String> ת����Modle���� ���� getModelValue
	 * 
	 * @param <T>
	 * @author zhangjw
	 * @date 2013-4-13
	 * @param map
	 * @param t
	 * @return
	 */
	public <T> T mapToModle(Map<String, String> map, T t) {
		try {
			if (map != null && t != null) {
				Field[] fields = t.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					field.set(t, map.get(field.getName()));
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * ��requestֵ����Model ���� getModelValue ����
	 * 
	 * @author zhangjw
	 * @date 2013-4-13
	 * @param <T>
	 * @param request
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T requestToModle(HttpServletRequest request, T t) {
		Map<String, String[]> map = request.getParameterMap();
		try {
			if (map != null && t != null) {
				Field[] fields = t.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					String[] values = map.get(field.getName());
					if (values == null) {
						values = new String[1];
						values[0] = null;
					}
					field.set(t, values[0]);
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * ����Ա��Ϣά���Զ��嵼��
	 */
	public List<HashMap<String, String>> getDwwhExportList(SzdwGeneralForm myForm, User user) throws Exception {

		return dao.getDwwhExportList(myForm, user);
	}

	/**
	 * �޸ĵ�����ҳ��
	 */
	public ArrayList<String[]> createBjxx(DwwhModel model, User user) throws Exception {

		// ����
		String lx = model.getLx();
		// ��������
		String lxmc = "fdy".equalsIgnoreCase(lx) ? "����Ա" : "������";
		// ְ����
		String zgh = model.getZgh();
		ArrayList<String[]> bjList = null;

		if ("fdy".equalsIgnoreCase(lx)) {
			bjList = dao.getFdybjList1(zgh);
		} else {
			bjList = dao.getBzrbjList1(zgh);
		}
		return bjList;
	}

	public HashMap<String, String> ckJzgxx(String zgh) {

		return dao.getJzgxx(zgh);
	}
	/** 
	 * (�鿴��ְ����Ϣ)�㽭ҽѧ�ߵ�ר��ѧУ
	 */
	public HashMap<String, String> ckJzgxx_13023(String zgh) {
		return dao.ckJzgxx_13023(zgh);
	}
	public HashMap<String, String> ckJzgxxJxsf(String zgh) {

		return dao.getJzgxxJxsf(zgh);
	}

	/**
	 * 
	 * @����:�����Ƽ�ʦ����ѧ˼�����鸨��Ա��Ϣ�������Ի�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-8 ����02:25:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<File> ��������
	 * @throws
	 */
	public void fdyxxwhExportJxsf(SzdwGeneralForm model, OutputStream os, User user) throws Exception {
		// ��ȡ��ְ����Ϣ
		List<HashMap<String, String>> fdyList = dao.getJzgxxList(model, user);
		fdyxxwhExportJxsf(fdyList, os, user);

	}

	private HashMap<String,String> getSdbj(HashMap<String, String> fdyMap) {

		HashMap<String,String> sdbjMap=new HashMap<String,String>();
		List<HashMap<String,String>> fdyBjList = dao.getFdyBj(fdyMap.get("zgh"));
		List<HashMap<String,String>> bzrBjList = dao.getBzrBj(fdyMap.get("zgh"));
		StringBuffer sdbj = new StringBuffer();
		StringBuffer bjrs = new StringBuffer();
		int zrs=0;
		int bjgs=0;
		for (int i = 0; i < fdyBjList.size(); i++) {
			if(i!=0){
				sdbj.append(";");
				bjrs.append(";");
			}
			sdbj.append(fdyBjList.get(i).get("sdbj"));
			bjrs.append(fdyBjList.get(i).get("bjrs"));
			zrs+=Integer.parseInt(fdyBjList.get(i).get("zrs"));
			bjgs+=Integer.parseInt(fdyBjList.get(i).get("bjgs"));
		}
		for (int i = 0; i < bzrBjList.size(); i++) {
			if(StringUtils.isNotNull(sdbj.toString())){
				sdbj.append(";").append(bzrBjList.get(i).get("sdbj"));
				bjrs.append(";").append(bzrBjList.get(i).get("bjrs"));
			}
			else {
				if(i!=0){
				sdbj.append(";");
				bjrs.append(";");
				}
				sdbj.append(bzrBjList.get(i).get("sdbj"));
				bjrs.append(bzrBjList.get(i).get("bjrs"));
				
			}
			zrs+=Integer.parseInt(bzrBjList.get(i).get("zrs"));
			bjgs+=Integer.parseInt(bzrBjList.get(i).get("bjgs"));
		}
		sdbjMap.put("sdbj", sdbj.toString());
		sdbjMap.put("bjrs", bjrs.toString());
		sdbjMap.put("zrs", String.valueOf(zrs));
		sdbjMap.put("bjgs",String.valueOf(bjgs));
		return sdbjMap;
		
	}

	/**
	 * 
	 * @����:�����ļ�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-8 ����02:27:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return File ��������
	 * @throws
	 */
	private File createFile() {
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		// ���������ļ�
		File file = new File(tempDir.getPath() + "/" + String.valueOf(System.currentTimeMillis()) + ".xls");
		file.setWritable(true);
		return file;
	}

	/**
	 * 
	 * @����:��������ģ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-8 ����02:26:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @param user
	 * @return
	 * @throws Exception
	 *             File ��������
	 * @throws
	 */
	private void fdyxxwhExportJxsf(List<HashMap<String, String>> list, OutputStream os, User user) throws Exception {
		StringBuffer title = new StringBuffer();
		title.append("�����Ƽ�ʦ����ѧ");
		title.append(Base.currXn).append("ѧ��");
		title.append("����Ա�������Σ�����");
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("������", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 26, 1);
		// ws.mergeCells(0, 2, 7, 3);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 15, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.ALL);
		ws.addCell(new Label(0, 2, "���", wcf2));
		ws.addCell(new Label(1, 2, "����", wcf2));
		ws.addCell(new Label(2, 2, "����", wcf2));
		ws.addCell(new Label(3, 2, "���", wcf2));
		ws.addCell(new Label(4, 2, "����", wcf2));
		ws.addCell(new Label(5, 2, "֤��", wcf2));
		ws.addCell(new Label(6, 2, "���֤����", wcf2));
		ws.addCell(new Label(7, 2, "ũ�п���", wcf2));
		ws.addCell(new Label(8, 2, "�������", wcf2));
		ws.addCell(new Label(9, 2, "�����༶", wcf2));
		ws.addCell(new Label(10, 2, "�Ա�", wcf2));
		ws.addCell(new Label(11, 2, "��������", wcf2));
		ws.addCell(new Label(12, 2, "������ò", wcf2));
		ws.addCell(new Label(13, 2, "���ѧ��", wcf2));
		ws.addCell(new Label(14, 2, "���ѧ��������", wcf2));
		ws.addCell(new Label(15, 2, "���ѧλ", wcf2));
		ws.addCell(new Label(16, 2, "���ѧλ������", wcf2));
		ws.addCell(new Label(17, 2, "����ְ��", wcf2));
		ws.addCell(new Label(18, 2, "��ְʱ��", wcf2));
		ws.addCell(new Label(19, 2, "��ְͬ��ʱ��", wcf2));
		ws.addCell(new Label(20, 2, "ְ��", wcf2));
		ws.addCell(new Label(21, 2, "��Ƹʱ��", wcf2));
		ws.addCell(new Label(22, 2, "�μӹ���ʱ��", wcf2));
		ws.addCell(new Label(23, 2, "����ѧ������ʱ��", wcf2));
		ws.addCell(new Label(24, 2, "�ƶ��绰", wcf2));
		ws.addCell(new Label(25, 2, "�μ���ѵ", wcf2));
		ws.addCell(new Label(26, 2, "��������", wcf2));
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);
				// ��ȡ�����༶
				HashMap<String,String> sdbj = getSdbj(map);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 14);
				ws.setColumnView(4, 14);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 20);
				ws.setColumnView(7, 20);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 20);
				ws.setColumnView(10, 5);
				ws.setColumnView(11, 20);
				ws.setColumnView(12, 25);
				ws.setColumnView(13, 10);
				ws.setColumnView(14, 15);
				ws.setColumnView(15, 10);
				ws.setColumnView(16, 15);
				ws.setColumnView(17, 15);
				ws.setColumnView(18, 13);
				ws.setColumnView(19, 13);
				ws.setColumnView(20, 10);
				ws.setColumnView(21, 13);
				ws.setColumnView(22, 13);
				ws.setColumnView(23, 13);
				ws.setColumnView(24, 18);
				ws.setColumnView(25, 20);
				ws.setColumnView(26, 20);
				ws.addCell(new Label(0, 3 + i, String.valueOf(i+1), wcf2));// ���
				ws.addCell(new Label(1, 3 + i, map.get("bmmc"), wcf2));
				ws.addCell(new Label(2, 3 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(3, 3 + i, map.get("kzzd16"), wcf2));
				ws.addCell(new Label(4, 3 + i, map.get("bzmc"), wcf2));
				ws.addCell(new Label(5, 3 + i, "���֤", wcf2));
				ws.addCell(new Label(6, 3 + i, map.get("sfzh"), wcf2));
				ws.addCell(new Label(7, 3 + i, "", wcf2));
				ws.addCell(new Label(8, 3 + i, "", wcf2));
				ws.addCell(new Label(9, 3 + i, sdbj.get("sdbj"), wcf2));
				ws.addCell(new Label(10, 3 + i, map.get("xbmc"), wcf2));
				ws.addCell(new Label(11, 3 + i, map.get("csrq"), wcf2));
				ws.addCell(new Label(12, 3 + i, map.get("zzmmmc"), wcf2));
				ws.addCell(new Label(13, 3 + i, map.get("xl"), wcf2));
				ws.addCell(new Label(14, 3 + i, map.get("kzzd20"), wcf2));
				ws.addCell(new Label(15, 3 + i, map.get("xw"), wcf2));
				ws.addCell(new Label(16, 3 + i, map.get("kzzd13"), wcf2));
				ws.addCell(new Label(17, 3 + i, map.get("zwmc"), wcf2));
				ws.addCell(new Label(18, 3 + i, map.get("kzzd7"), wcf2));
				ws.addCell(new Label(19, 3 + i, map.get("kzzd8"), wcf2));
				ws.addCell(new Label(20, 3 + i, map.get("zcmc"), wcf2));
				ws.addCell(new Label(21, 3 + i, map.get("kzzd6"), wcf2));
				ws.addCell(new Label(22, 3 + i, map.get("cjgzrq"), wcf2));
				ws.addCell(new Label(23, 3 + i, map.get("szgzsj"), wcf2));
				ws.addCell(new Label(24, 3 + i, map.get("yddh"), wcf2));
				ws.addCell(new Label(25, 3 + i, map.get("pxqk"), wcf2));
				ws.addCell(new Label(26, 3 + i, map.get("kzzd4"), wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	public void dbqkbData(SzdwGeneralForm model, OutputStream os, User user) throws Exception {

		List<HashMap<String, String>> dbqkList = dao.getDbxx(model, user);
		// ���ñ���
		StringBuffer title = new StringBuffer();
		title.append(Base.currXn).append("ѧ��").append(Base.dqxqmc);
		title.append("�����δ������һ����");

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("�������һ����", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 10, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.ALL);

		ws.addCell(new Label(0, 3, "���", wcf2));
		ws.addCell(new Label(1, 3, "����", wcf2));
		ws.addCell(new Label(2, 3, "����", wcf2));
		ws.addCell(new Label(3, 3, "���", wcf2));
		ws.addCell(new Label(4, 3, "�����༶", wcf2));
		ws.addCell(new Label(5, 3, "ѧ����", wcf2));
		ws.addCell(new Label(6, 3, "����ѧ������", wcf2));
		ws.addCell(new Label(7, 3, "������(��)", wcf2));
		ws.addCell(new Label(8, 3, "����", wcf2));
		ws.addCell(new Label(9, 3, "�Ա�", wcf2));
		ws.addCell(new Label(10, 3, "�绰����", wcf2));
		int r1=0,r2=3;
		if (dbqkList != null && dbqkList.size() > 0) {
			for (int i = 0; i < dbqkList.size(); i++) {
				HashMap<String, String> map = dbqkList.get(i);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 25);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 18);
				ws.setColumnView(4, 30);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 10);
				ws.setColumnView(7, 10);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 10);
				ws.setColumnView(10, 15);
				ws.addCell(new Label(0, 4 + i, map.get("rn"), wcf2));// ���
				ws.addCell(new Label(1, 4 + i, map.get("bmmc"), wcf2));
				ws.addCell(new Label(2, 4 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(3, 4 + i, map.get("kzzd16"), wcf2));
				ws.addCell(new Label(4, 4 + i, map.get("bjmc"), wcf2));
				ws.addCell(new Label(5, 4 + i, map.get("bjrs"), wcf2));
				ws.addCell(new Label(6, 4 + i, map.get("zrs"), wcf2));
				ws.addCell(new Label(7, 4 + i, map.get("bjgs"), wcf2));
				ws.addCell(new Label(8, 4 + i, map.get("nl"), wcf2));
				ws.addCell(new Label(9, 4 + i, map.get("xbmc"), wcf2));
				ws.addCell(new Label(10, 4 + i, map.get("yddh"), wcf2));
				if(r1!=Integer.parseInt(map.get("rn"))){//rn��һ��ʱ����һ��rnһ�µ��н��кϲ�
					r1=Integer.parseInt(map.get("rn"));
					//if(i+4-r2>1){
						ws.mergeCells(0, r2, 0, i+3);//���
						ws.mergeCells(1, r2, 1, i+3);//����
						ws.mergeCells(2, r2, 2, i+3);//����
						ws.mergeCells(3, r2, 3, i+3);//���
						ws.mergeCells(6, r2, 6, i+3);//����ѧ������
						ws.mergeCells(7, r2, 7, i+3);//������
						ws.mergeCells(8, r2, 8, i+3);//���
						ws.mergeCells(9, r2, 9, i+3);//�Ա�
						ws.mergeCells(10 ,r2, 10, i+3);//�绰����
					//}
					r2=i+4;
				}
			}
		}
		//���һ��rn�ϲ�
		ws.mergeCells(0, r2, 0, dbqkList.size()+3);//���
		ws.mergeCells(1, r2, 1, dbqkList.size()+3);//����
		ws.mergeCells(2, r2, 2, dbqkList.size()+3);//����
		ws.mergeCells(3, r2, 3, dbqkList.size()+3);//���
		ws.mergeCells(6, r2, 6, dbqkList.size()+3);//����ѧ������
		ws.mergeCells(7, r2, 7, dbqkList.size()+3);//������
		ws.mergeCells(8, r2, 8, dbqkList.size()+3);//���
		ws.mergeCells(9, r2, 9, dbqkList.size()+3);//�Ա�
		ws.mergeCells(10 ,r2, 10, dbqkList.size()+3);//�绰����
		ws.mergeCells(0, 2, 10, 2);
		ws.setRowView(0, 500);
		ex.printToOneCell_multy(ws, "____________ѧԺ����ί���£���                 �ֹ��쵼ǩ�֣�     ��ί������ǩ�֣�      ���༶����    ����ѧ��������  ��", 0, 2, 10, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.ALL);
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void pbbExportData(SzdwGeneralForm model, OutputStream os, User user) throws Exception {
		List<HashMap<String, String>> dmList = dao.getFdySflx();
		List<HashMap<String, String>> pbqkList = dao.getPbxxList1(model, user,dmList);
		// ���ñ���
		StringBuffer title = new StringBuffer();
		title.append(Base.currXn).append("ѧ��").append(Base.dqxqmc);
		title.append("��ѧԺ����Ա���������䱸���һ��");

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("�䱸���һ����", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 15, 1);
		ws.mergeCells(2, 2, 2+dmList.size()-1, 2);
		
		ws.mergeCells(0, 2, 0, 3);
		ws.mergeCells(1, 2, 1, 3);
		ws.mergeCells(2+dmList.size(), 2, 2+dmList.size(), 3);
		ws.mergeCells(3+dmList.size(), 2, 3+dmList.size(), 3);
		ws.mergeCells(4+dmList.size(), 2, 4+dmList.size(), 3);
		ws.mergeCells(5+dmList.size(), 2, 5+dmList.size(), 3);
		ws.mergeCells(6+dmList.size(), 2, 6+dmList.size(), 3);
		ws.mergeCells(7+dmList.size(), 2, 7+dmList.size(), 3);
		ws.mergeCells(8+dmList.size(), 2, 8+dmList.size(), 3);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.ALL);
		ex.printToOneCell_multy(ws, "רְ����Ա", 2, 2, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.ALL);
		ex.printToOneCell_multy(ws, "���", 0, 2, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.ALL);
		ws.addCell(new Label(1, 2, "ѧԺ", wcf2));
		for (int i = 0; i < dmList.size(); i++) {
			ws.addCell(new Label(i+2, 3, dmList.get(i).get("lxmc"), wcf2));	
		}
		ws.addCell(new Label(dmList.size()+2, 2, "��ְ����Ա", wcf2));
		ws.addCell(new Label(dmList.size()+3, 2, "ѧ������", wcf2));
		ws.addCell(new Label(dmList.size()+4, 2, "�༶��", wcf2));
		ws.addCell(new Label(dmList.size()+5, 2, "������", wcf2));
		ws.addCell(new Label(dmList.size()+6, 2, "רְ����Ա������", wcf2));
		ws.addCell(new Label(dmList.size()+7, 2, "��ѧԺ��ְ����Ա������", wcf2));
		ws.addCell(new Label(dmList.size()+8, 2, "��ѧԺ������������", wcf2));
		if (pbqkList != null && pbqkList.size() > 0) {
			
			for (int i = 0; i < pbqkList.size(); i++) {
				HashMap<String, String> map = pbqkList.get(i);
				//��ȡ����������
				int bzrrs=0;
				if(null!=map.get("bzr")&&""!=map.get("bzr")){
					bzrrs=map.get("bzr").split(";").length;
				}
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 20);
				for (int j = 0; j < dmList.size(); j++) {
					ws.setColumnView(j+2, 10);
				}
				ws.setColumnView(dmList.size()+2, 20);
				ws.setColumnView(dmList.size()+3, 10);
				ws.setColumnView(dmList.size()+4, 10);
				ws.setColumnView(dmList.size()+5, 20);
				ws.setColumnView(dmList.size()+6, 10);
				ws.setColumnView(dmList.size()+7, 10);
				ws.setColumnView(dmList.size()+8, 10);
				ws.addCell(new Label(0, 4 + i, i+1+"", wcf2));// ���
				ws.addCell(new Label(1, 4 + i, map.get("xymc"), wcf2));
				int zzfdyrs=0;
				for (int j = 0; j < dmList.size(); j++) {
					ws.addCell(new Label(j+2, 4 + i, map.get("zzfdy"+j), wcf2));
					if(null!=map.get("zzfdy"+String.valueOf(j))){
						zzfdyrs=zzfdyrs+map.get("zzfdy"+String.valueOf(j)).split(";").length;
					}
				}
				int jzfdyrs=0;
				if(null!=map.get("jzfdy")){
					 jzfdyrs=map.get("jzfdy").split(";").length;
				}
				ws.addCell(new Label(dmList.size()+2, 4 + i, map.get("jzfdy"), wcf2));
				ws.addCell(new Label(dmList.size()+3, 4 + i, map.get("sumxs"), wcf2));
				ws.addCell(new Label(dmList.size()+4, 4 + i, map.get("sumbj"), wcf2));
				ws.addCell(new Label(dmList.size()+5, 4 + i, map.get("bzr"), wcf2));
				ws.addCell(new Label(dmList.size()+6, 4 + i, String.valueOf(zzfdyrs), wcf2));
				ws.addCell(new Label(dmList.size()+7, 4 + i, String.valueOf(jzfdyrs), wcf2));
				ws.addCell(new Label(dmList.size()+8, 4 + i, bzrrs+"", wcf2));
				
		
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * @����: ˼���������ñ���
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-12-10 ����02:39:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @param sf �Ƿ�˼������
	 * @return
	 * @throws Exception
	 */
	public boolean szdwSzSave(String ids, String sf) throws Exception {
		return dao.szdwSzSave(ids, sf);
	}
	
	/**
	 * 
	 * @����: ��������ѧ����Ϣ(��������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-10-12 ����02:12:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lx
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> exportDbStu(String zgh,String lx){
		return dao.exportDbStu(zgh, lx);
	}
}