package xsgzgl.pjpy.general.xmsz.rssz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.xmsz.XmszRsszInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��������_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XmszRsszService extends CommService implements XmszRsszInterface {

	XmszRsszDAO dao = new XmszRsszDAO();

	/**
	 * ��������Ƿ񳬹�����Ϣ
	 * 
	 * @author ΰ�����
	 */
	public String getRsszMessage(XmszRsszModel model, User user) {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * ��ñ�ͷ�ļ�(��Ŀ����_��������)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXmszRsszTop(XmszRsszModel model,
			User user) {
		
		PjszPjxmModel pjxmModel = PjpyGeneralForm.getPjxmModel();
		// ���Ʒ�Χ
		String kzfw = pjxmModel.getKzfw();
		
		DAO dao = DAO.getInstance();

		String[] en = null;
		String[] cn = null;
	
		if ("nj".equalsIgnoreCase(kzfw)) {// �����꼶����
			en = new String[] { "pk", "nj", "bmrs", "szbl", "jsrs", "mrrs",
					"qdrs" };
			cn = new String[] { "", "�꼶", "��������", "���ñ���", "��������", "Ĭ������",
					"��������" };
		} else if ("xy".equalsIgnoreCase(kzfw)) {// ����ѧԺ����
			en = new String[] { "pk", "xymc", "bmrs", "szbl", "jsrs", "mrrs",
					"qdrs" };
			cn = new String[] { "", "Ժϵ����", "��������", "���ñ���", "��������", "Ĭ������",
					"��������" };
		} else if ("njxy".equalsIgnoreCase(kzfw)) {// �����꼶ѧԺ����
			en = new String[] { "pk", "nj", "xymc", "bmrs", "szbl", "jsrs",
					"mrrs", "qdrs" };
			cn = new String[] { "", "�꼶", "Ժϵ����", "��������", "���ñ���", "��������",
					"Ĭ������", "��������" };
		} else if ("njzy".equalsIgnoreCase(kzfw)) {// �����꼶רҵ ����
			en = new String[] { "pk", "nj", "xymc", "zymc", "bmrs", "szbl",
					"jsrs", "mrrs", "qdrs" };
			cn = new String[] { "", "�꼶", "Ժϵ����", "רҵ����", "��������", "���ñ���",
					"��������", "Ĭ������", "��������" };
		} else if ("bj".equalsIgnoreCase(kzfw)) {// ���ư༶����
			en = new String[] { "pk", "nj", "bjmc", "bmrs", "szbl", "jsrs",
					"mrrs", "qdrs" };
			cn = new String[] { "", "�꼶", "�༶", "��������", "���ñ���", "��������", "Ĭ������",
					"��������" };
		}else if ("cpz".equalsIgnoreCase(kzfw)) {// ���ư༶����
			en = new String[] { "pk","cpzmc", "bmrs", "szbl", "jsrs",
					"mrrs", "qdrs" };
			cn = new String[] { "", "������", "��������", "���ñ���", "��������", "Ĭ������",
					"��������" };
		}
		
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		
		return topTr;
	}

	/**
	 * ��ý����(��Ŀ����_��������)
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getXmszRsszList(PjpyGeneralForm myForm,
			XmszRsszModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = null;

		PjszPjxmModel pjxmModel = PjpyGeneralForm.getPjxmModel();
		// ��Ŀ����
		String xmdm = pjxmModel.getXmdm();
		// ���Ʒ�Χ
		String kzfw = pjxmModel.getKzfw();

		if ("nj".equalsIgnoreCase(kzfw)) {// �����꼶����
			list = dao.getXmszRsszListByNj(myForm, xmdm, user);
		} else if ("xy".equalsIgnoreCase(kzfw)) {// ����ѧԺ����
			list = dao.getXmszRsszListByXy(myForm, xmdm, user);
		} else if ("njxy".equalsIgnoreCase(kzfw)) {// �����꼶ѧԺ����
			list = dao.getXmszRsszListByNjXy(myForm, xmdm, user);
		} else if ("njzy".equalsIgnoreCase(kzfw)) {// �����꼶רҵ����
			list = dao.getXmszRsszListByNjZy(myForm, xmdm, user);
		} else if ("bj".equalsIgnoreCase(kzfw)) {// ���ư༶����
			list = dao.getXmszRsszListByBj(myForm, xmdm, user);
		} else if ("cpz".equalsIgnoreCase(kzfw)) {// ���ư༶����
			list = dao.getXmszRsszListByCpz(myForm, xmdm, user);
		}

		return list;
	}

	/**
	 * ���������(��Ŀ����_��������)
	 * 
	 * @author ΰ�����
	 */
	public String createXmszRsszHTML(SearchRsModel rsModel,
			XmszRsszModel model, ArrayList<String[]> rsArrList, User user) {
		
		// IE�汾
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String pk = rs[0];//����
//				String nj = rs[1];// �꼶
//				String bjmc = rs[2];// �༶
//				String bmrs = rs[3];// ��������
//				String szbl = rs[4];// ����
//				String jsrs = rs[5];// ��������
//				String mrrs = rs[6];// Ĭ������
				String qdrs = rs[rs.length - 1];// ��������
				
				html.append("<tr onclick=\"rowOnClick(this);\" style=\"cursor:hand\" ondblclick=\"\">");
				html.append("<td align=\"center\" width=\"5px\"");
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");
				
				for (int j = 1; j < rs.length - 1; j++) {
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

				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				html.append(">");
				html.append("<input type=\"text\" name=\"array_qdrs\" ");
				html.append("id=\"input_qdrs_" + pk + "\" ");
				html.append("onkeydown=\"return onlyNum(this,3)\" ");
				html.append("onmousedown=\"return onlyNum(this,3)\" ");
				html.append("maxlength=\"3\"  ");
				html.append("style=\"width:50px;ime-mode:disabled\" ");
				html.append("value=\""+qdrs+"\"/>");
				html.append("</td>");
				
				html.append("</tr>");
			}
		}
			
		return html.toString();
	}
	
	/**
	 * ��ʼ���������ñ�
	 * 
	 * @author ΰ�����
	 */
	public void initRsszb(String xmdm, String szfw, String tsrq, User user) {
		try {
			if ("bj".equalsIgnoreCase(szfw)) {// ���ư༶����
				dao.initRsszbByBj(xmdm, tsrq, user);
			} else if ("nj".equalsIgnoreCase(szfw)) {// �����꼶����
				dao.initRsszbByNj(xmdm, tsrq, user);
			} else if ("xy".equalsIgnoreCase(szfw)) {// ����ѧԺ����
				dao.initRsszbByXy(xmdm, tsrq, user);
			} else if ("njxy".equalsIgnoreCase(szfw)) {// �����꼶ѧԺ����
				dao.initRsszbByNjXy(xmdm, tsrq, user);
			} else if ("njzy".equalsIgnoreCase(szfw)) {// �����꼶רҵ����
				dao.initRsszbByNjZy(xmdm, tsrq, user);
			} else if("cpz".equalsIgnoreCase(szfw)){// ���ư༶����
				dao.initRsszbByCpz(xmdm, tsrq, user);
			} else {// ���ư༶����
				dao.initRsszbByBj(xmdm, tsrq, user);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}

	/**
	 * �������ñ���
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveSzbl(PjpyGeneralForm myForm, XmszRsszModel model,
			User user, String saveLx) {

		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		PjszPjxmModel pjxmModel = PjpyGeneralForm.getPjxmModel();
		SearchModel searchModel = myForm.getSearchModel();
		
		// �Ƿ������
		String isCpz = jbszModel.getCpz();

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ���ñ���
		String szbl = model.getSzbl();
		// ���Ʒ�Χ
		String szfw = pjxmModel.getKzfw();
		// ����
		String[] pkValue = model.getPkValue();
		
		// �꼶
		String[] nj = searchModel.getSearch_tj_nj();
		// ѧԺ
		String[] xy = searchModel.getSearch_tj_xy();
		// רҵ
		String[] zy = searchModel.getSearch_tj_zy();
		// �༶
		String[] bj = searchModel.getSearch_tj_bj();

		boolean flag = false;

		try {
			if ("checked".equalsIgnoreCase(saveLx)) {// �������ñ�������ѡ��
					
				flag = dao.updateRsszb(pkValue, xmdm, szfw, szbl, user);
				
			} else if ("no_checked".equalsIgnoreCase(saveLx)) {// �������ñ�����δ��ѡ��
				
				if("yes".equalsIgnoreCase(isCpz)){
					flag = dao.updateRsszByCpz(nj, xy, zy, bj, xmdm, szfw, szbl, user);
				}else{
					flag = dao.updateRsszb(nj, xy, zy, bj, xmdm, szfw, szbl, user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ������������
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveQdrs(XmszRsszModel model, User user) {

		// ����
		String[] pkValue = model.getPkValue();
		// ȷ������
		String[] qdrs = model.getQdrs();

		boolean flag = false;

		try {
			flag = dao.updateRsszbQdrs(pkValue, qdrs, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}
}
