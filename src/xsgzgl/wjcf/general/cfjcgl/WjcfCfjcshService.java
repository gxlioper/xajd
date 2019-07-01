package xsgzgl.wjcf.general.cfjcgl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.inter.WjcfCfjcshInterface;

public class WjcfCfjcshService extends CommService implements WjcfCfjcshInterface{

	private WjcfCfjcshDao dao = new WjcfCfjcshDao();
	/**
	 *����html
	 * @param model
	 * @return
	 *
	 */
	public String createWjcfCfjcshHTML(SearchRsModel rsModel,
			WjcfCfjcshModel model, ArrayList<String[]> rsArrList, User user) {
		// IE�汾
		String ie = rsModel.getIe();
		String stylePath = rsModel.getStylePath();
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String disabled1 = rs[11];// �¼���˿���
				String disabled2 = rs[10];// �ύ����
				html.append("<tr onclick=\"rowOnClick(this);\" >");

				html.append("<td  align=\"left\" nowrap=\"nowrap\" style=\"width:8px\">");

				html.append("<input type='checkbox' name='primarykey_checkVal' ");
				if (!Base.isNull(disabled1) || !Base.isNull(disabled2)) {
					html.append(" disabled=\"true\"");
				}

				html.append(" id='pkValue_" + i + "' ");
				html.append(" value='" + rs[0] + "'/> ");

				html.append("</td>");

				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length - 1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					if (j == 1) {
						html.append(" style=\"width:10%\"");
					}
					if (j == 2) {
						html.append(" style=\"width:5%\"");
					}
					if (j == 3) {
						html.append(" style=\"width:10%\"");
					}
					if (j == 4) {
						html.append(" style=\"width:10%\"");
					}
					if (j == 5) {
						html.append(" style=\"width:10%\"");
					}
					if (j == 6) {
						html.append(" style=\"width:10%\"");
					}
					if (j == 7) {
						html.append(" style=\"width:10%\"");
					}
					if (j == 8) {
						html.append(" style=\"width:10%\"");
					}
					if (j == 9) {

						if ("δ���".equalsIgnoreCase(rs[9])) {

							html.append("<p><img src=\""
											+ stylePath
											+ "images/ico_dsh.gif\" width=\"52\" height=\"18\" /></p>");

						} else if ("ͨ��".equalsIgnoreCase(rs[9])) {

							html.append("<p><img src=\""
											+ stylePath
											+ "images/ico_shtg.gif\" width=\"52\" height=\"18\" /></p>");

						} else if ("��ͨ��".equalsIgnoreCase(rs[9])) {

							html.append("<p><img src=\""
											+ stylePath
											+ "images/ico_shbtg.gif\" width=\"52\" height=\"18\" /></p>");

						} else if ("�˻�".equalsIgnoreCase(rs[9])) {

							html.append("<p><img src=\""
											+ stylePath
											+ "images/ico_shth.gif\" width=\"52\" height=\"18\" /></p>");

						} else if ("������".equalsIgnoreCase(rs[9])) {

							html.append("<p><img src=\""
											+ stylePath
											+ "images/ico_shxcs.gif\" width=\"52\" height=\"18\" /></p>");

						}
					}
					
					if (j == 10) {
						html.append(" style=\"width:10%\"");
						html.append(">");
						if ("".equalsIgnoreCase(rs[10])) {
							html.append("<font color=\"blue\">δ�ύ</font>");
						}else{
							html.append("<font color=\"red\">���ύ</font>");
						}
						
					}

					if (j != 9&&j != 10) {
						html.append(">");
						html.append(rs[j]);
					}

					html.append("</td>");
				}

				html.append("</tr>");
			}
		}

		return html.toString();
	}
	
	
	/**
	 * ��ʾ���û���λ�л�ģʽ����
	 * 
	 * @author xucy
	 * @throws IOException
	 */
	public void showShgwDiv(User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		List<HashMap<String, String>> spgwList = dao.getSpgwList(user);

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>��˸�λѡ��</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("��λѡ��");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%' >");

		for (int i = 0; i < spgwList.size(); i++) {

			HashMap<String, String> spgwMap = spgwList.get(i);
			html.append(" <input type=\"radio\" name=\"spgws\" ");
			if (i == 0) {
				html.append("  checked=\"true\" ");
			}
			html.append(" id=\"spgw_" + i + "\" value=\""
					+ spgwMap.get("spgw") + "\">");
			html.append(spgwMap.get("gwmc"));
			html.append("<br/>");
		}

		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"checkSpgw();return false;\">");
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
	 * �����ѯ
	 * 
	 * @author xucy
	 * 
	 */
	public ArrayList<String[]> getWjcfCfjcshList(WjcfGeneralForm myForm,
			WjcfCfjcshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		
		return dao.getCfjcshList(myForm, model, user);
	}

	/**
	 * ��������
	 * 
	 * @author xucy
	 * 
	 */
	public List<HashMap<String, String>> getWjcfCfjcshTop(WjcfCfjcshModel model,
			User user) {
		String[] en = new String[] { "pk", "xn", "xq", "xh", "xm", "cflb",
				"cfyy", "jcwh", "jcsj", "shjg" ,"tjzt"};
		String[] cn = new String[] { "", "ѧ��", "ѧ��", "ѧ��", "����", "�������",
				"����ԭ��", "����ĺ�", "���ʱ��",  "��˽��" ,"�ύ״̬"};
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		return topTr;
	}


	/**
	 * ��ȡ���ֽ������б�
	 * 
	 * @author xucy
	 * 
	 */
	public List<HashMap<String, String>> getCfjcshxx(String cfid, String spgw) {
		
		return dao.getCfjcshxx(cfid, spgw);
	}


	/**
	 * ��ȡ������Ϣ
	 * 
	 * @author xucy
	 * 
	 */
	public HashMap<String, String> getCfxx(String cfId) throws Exception {
		
		return dao.getCfxx(cfId);
	}


	/**
	 * ��ȡ��һ��λ
	 * 
	 * @author xucy
	 * 
	 */
	public HashMap<String, String> getHigherUpSpMap(WjcfGeneralForm model,
			User user) {
		
		return dao.getHigherUpSpMap(model, user);
	}


	/**
	 * ��ȡ��߸�λ
	 * 
	 * @author xucy
	 * 
	 */
	public HashMap<String, String> getMaxSpgw(WjcfGeneralForm model, User user)
			throws Exception {
		
		return dao.getMaxSpgw(model, user);
	}


	/**
	 * ��ȡһ�����ֽ�������Ϣ
	 * 
	 * @author xucy
	 * 
	 */
	public HashMap<String, String> getOnesCfjcsh(String cfid, String spgw)
			throws Exception {
		
		return dao.getOnesCfjcsh(cfid, spgw);
	}


	/**
	 * ��ȡһ�����ֽ��������Ϣ
	 * 
	 * @author xucy
	 * 
	 */
	public HashMap<String, String> getOnesCfjc(String cfId) throws Exception {
		return dao.getOnesCfjc(cfId);
	}


	/**
	 * ��ȡһ����������������Ϣ
	 * 
	 * @author xucy
	 * 
	 */
	public HashMap<String, String> getOnesCfss(String cfId) throws Exception {
		return dao.getOnesCfss(cfId);
	}

	/**
	 * 
	 * ���(��������Կ�)
	 * 
	 * @author xucy
	 */
	public boolean saveCfjcsh(WjcfGeneralForm form, User user) throws Exception {
		
		String shzt = form.getShzt();
		String pk[] = form.getCfId().split(",");

		boolean flag = dao.plXgDownshzt(pk, form, user);// �˻�ʱ�޸���һ��״̬

		flag = dao.plXgshzt(pk, form, user);// �޸����״̬

		if ("th".equalsIgnoreCase(shzt) && flag) {// �˻�ʱ�޸���һ��״̬

			flag = dao.plXgUpshzt(pk, form, user);
		}

		if (flag) {
			flag = dao.plXgshjg(pk, form, user);
		}
		return flag;
	}


	/**
	 * 
	 * �ύ������ʽ��
	 * 
	 * @author xucy
	 */
	public boolean tjSh() throws Exception {
		boolean flag = dao.tjSh();// �ύ���ͨ�������ݽ�����ʽ��

		if (flag) {// �޸�����Ƿ��ύ״̬
			flag = dao.xgSftj();
		}
		return flag;
	}

	/**
	 * 
	 * ͳ��
	 * 
	 * @author xucy
	 */
	public List<HashMap<String, String>> tongjiList() {
		return dao.tongjiList();
	}

	/**
	 * 
	 * ���ֽ������б�
	 * 
	 * @author xucy
	 */
	public List<HashMap<String, String>> getCfjcshxxList(String cfid) {
		// TODO �Զ����ɷ������
		return dao.getCfjcshxxList(cfid);
	}


	/**
	 * 
	 * ��˸�λ�б�
	 * 
	 * @author xucy
	 */
	public List<HashMap<String, String>> getSpgwList(User user) {
		// TODO �Զ����ɷ������
		return dao.getSpgwList(user);
	}
	
	/**
	 * 
	 * ��߼��û�
	 * 
	 * @author xucy
	 */
	public boolean isZgjyh(User user) throws Exception {
		
		return dao.isZgjyh(user);
	}

	/**
	 * 
	 *�ύ���(ֱ���ύ���)
	 * 
	 * @author xucy
	 */
	public boolean zjtjSh(WjcfGeneralForm form) throws Exception {
		String pks = form.getCfId();
		boolean flag  = false;
		if(null!=pks){
			String pk[] = pks.split(",");
			form.setJcwh(unicode2Gbk(form.getJcwh()));
			 flag = dao.tjShsj(pk,form);// �ύ���ͨ�������ݽ�����ʽ��
			if (flag) {// �޸�����Ƿ��ύ״̬
				flag = dao.xgTjzt(pk);
			}
		}
		return flag;
	}
	
}
