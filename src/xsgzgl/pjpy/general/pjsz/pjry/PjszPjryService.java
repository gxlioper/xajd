package xsgzgl.pjpy.general.pjsz.pjry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjryInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_������Ա_Service��
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

public class PjszPjryService extends CommService implements PjszPjryInterface {

	PjszPjryDAO dao = new PjszPjryDAO();

	/**
	 * ��ñ�ͷ�ļ�(��������_������Ա)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getPjszPjryTop(PjszPjryModel model,
			User user) {
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xh", "xm", "nj", "bjmc", "pjbjmc",
				"sfcp" };
		String[] cn = new String[] { "", "ѧ��", "����", "�꼶", "�༶����", "�����༶����",
				"�Ƿ����" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ý����(��������_������Ա)
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getPjszPjryList(PjpyGeneralForm myForm,
			PjszPjryModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getPjszPjryList(myForm, user);
	}

	/**
	 * ���������(��������_������Ա)
	 * 
	 * @author ΰ�����
	 */
	public String createPjszPjryHTML(SearchRsModel rsModel,
			PjszPjryModel model, ArrayList<String[]> rsArrList, User user) {
		
		// IE�汾
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String pk = rs[0];
				String pjbjmc = rs[5];//�����༶����
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td align=\"center\" width=\"5px\"");
				if(!"δ����".equalsIgnoreCase(pjbjmc)){
					html.append("bgcolor=\"#CCFFFF\"");
				}
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");
				
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					
					if(!"δ����".equalsIgnoreCase(pjbjmc)){
						html.append("bgcolor=\"#CCFFFF\"");
					}
					
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

				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 * ���������༶����
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveBjtz(PjszPjryModel model, User user) {

		// ѧ��
		String[] xh = model.getXh();
		// �༶����
		String bjdm = model.getBjdm();
		// �༶����
		String bjmc = model.getBjmc();

		boolean flag = false;

		try {
			//����༶����
			flag = dao.updatePjrykb(xh, bjdm, bjmc, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * �����������
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveSfcp(PjszPjryModel model, User user) {

		// ѧ��
		String[] xh = model.getXh();
		// �Ƿ����
		String sfcp = model.getSfcp();

		boolean flag = false;

		try {
			//�����������
			flag = dao.updatePjrykb(xh, sfcp, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ����������Ա�༶����
	 * @author qlj 
	 */
	public boolean disfrockPjry(PjszPjryModel model,User user) throws Exception{
		
		String[]xh= model.getXh(); 
		
		boolean flag = false;

		try {
			// �����༶����
			flag = dao.disfrockPjry(xh,user);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
}
