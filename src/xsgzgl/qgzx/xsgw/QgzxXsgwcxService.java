package xsgzgl.qgzx.xsgw;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
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
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-23 ����14:19:22
 * </p>
 */

public class QgzxXsgwcxService extends BasicService {

	/**
	 * ��ѯ�ҵ��ڹ���λ��ҳ��ͷ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "r", "xn", "gwmc", "gwxz", "yrbm", "zje" };
		String[] cn = new String[] { "", "�к�", "ѧ��", "��λ����", "��λ����", "���˲���", "�ܳ��"};
		if("gwxxCx".equalsIgnoreCase(type)){
			en = new String[] { "", "r", "xn", "yrbm", "gwmc", "xqrs", "knsrs", "ylyrs" };
			cn = new String[] { "", "�к�", "ѧ��",  "���˲���", "��λ����", "��������", "����������","��¼������" };
		}
		return dao.arrayToList(en, cn);
	}

	/**
	 * ��ѯ�ҵ��ڹ���λ��ҳ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getWdqggwCx(QgzxXsgwcxForm myForm,String username) throws Exception {
		QgzxXsgwcxDAO dao = new QgzxXsgwcxDAO();
		return dao.getWdqggwCx(myForm,username);
	}

	/**
	 * ƴ���ҵ��ڹ���λ��ҳ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
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
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * �鿴�ҵ��ڹ���λ��ҳ��һ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsgwCkmxMap(QgzxXsgwcxForm myForm,String userName) {
		QgzxXsgwcxDAO dao = new QgzxXsgwcxDAO();
		return dao.getXsgwCkmxMap(myForm,userName);
	}

	/**
	 * �鿴�ҵ��ڹ���λ��ҳ��һ�����ݣ���𷢷���ϸ���֣�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getXsgwCkcjmxMap(QgzxXsgwcxForm myForm,String userName) throws Exception {
		QgzxXsgwcxDAO dao = new QgzxXsgwcxDAO();
		return dao.getXsgwCkcjmxMap(myForm,userName);
	}

	/**
	 * ��λ��Ϣ��ѯ
	 * @param myForm
	 * @param userName
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> gwxxCx(QgzxXsgwcxForm myForm, String userName) throws Exception {
		QgzxXsgwcxDAO dao = new QgzxXsgwcxDAO();
		return dao.gwxxCx(myForm);
	}
	
	/**
	 * ��λ��Ϣ�鿴
	 * @param myForm
	 * @param request
	 * @return
	 * @throws SQLException 
	 */
	public HashMap<String, String> gwxxCk(QgzxXsgwcxForm model) throws SQLException {
		//��λ��Ϣ
		QgzxXsgwcxDAO dao = new QgzxXsgwcxDAO();
		return dao.gwxxCk(model);
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯѧ����λ�������Ϣ�б�
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, Object>> getStuGwxxCjxxList(String xh) {
		QgzxXsgwcxDAO dao = new QgzxXsgwcxDAO();
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		//��λ¼����� 
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<String[]> list = new ArrayList<String[]>();
		
		list.add(new String[]{"���˵�λ", "��λ����", "��λ����", "�ϸ�ʱ��", "�ڸ�״̬", "�˸�ʱ��", "�˸�ԭ��"});
		list.addAll(dao.getStuQgzxXsgwxxList(xh));
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "��λ¼�����");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
		rs.add(map);
		//��𷢷���� 
		map = new HashMap<String, Object>();
		list = new ArrayList<String[]>();
		list.add(new String[]{"���˵�λ","��λ����","��������",  "���Ž��", "��ʱ��(Сʱ)"});
		list.addAll(dao.getStuQgzxCjffList(xh));
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "��𷢷����");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
		rs.add(map);
		return rs;
	}
}