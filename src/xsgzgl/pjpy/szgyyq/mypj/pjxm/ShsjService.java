package xsgzgl.pjpy.szgyyq.mypj.pjxm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.szgyyq.model.ShsjModel;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����(ѧ��)_������Ŀ_���ʵ��_Service��
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

public class ShsjService extends CommService {

	ShsjDAO dao = new ShsjDAO();
	
	/**
	 * �������ʵ�������
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveShsjSqf(PjpyStuForm model, User user) {

		ShsjModel shsjModel = model.getShsjModel();

		// ѧ��
		String xh = shsjModel.getXh();
		// ѧ��
		String xn = shsjModel.getXn();
		// ѧ��
		String xq = shsjModel.getXq();
		// ��ص�
		String[] hddd = shsjModel.getHddd();
		// �����
		String[] hdrq = shsjModel.getHdrq();
		// �����
		String[] hdnr = shsjModel.getHdnr();
		// �ʱ��
		String[] hdsj = shsjModel.getHdsj();
		// �����
		String[] sqf = shsjModel.getSqf();

		String[] pkValue = null;

		// �������
		List<String[]> params = new ArrayList<String[]>();
		if (hddd != null && hddd.length > 0) {

			pkValue = new String[hddd.length];

			for (int i = 0; i < hddd.length; i++) {

				String[] values = new String[] { xn, xq, xh,
						unicode2Gbk(hddd[i]), hdrq[i], unicode2Gbk(hdnr[i]),
						hdsj[i].trim(), sqf[i] };

				params.add(values);
				pkValue[i] = xn + xq + xh + unicode2Gbk(hddd[i]) + hdrq[i];
			}
		}

		boolean flag = dao.delShsjSqf(model, pkValue, user);

		if (flag) {
			flag = dao.saveShsjSqf(model, params, user);
		}

		return flag;
	}

	/**
	 * ɾ�����ʵ�������
	 * 
	 * @author ΰ�����
	 */
	public Boolean delShsjSqf(PjpyStuForm model, User user) {

		ShsjModel shsjModel = model.getShsjModel();

		// ѧ��
		String xh = shsjModel.getXh();
		// ѧ��
		String xn = shsjModel.getXn();
		// ѧ��
		String xq = shsjModel.getXq();
		// ������Ŀ
		String[] hddd = shsjModel.getHddd();
		// ����
		String[] hdrq = shsjModel.getHdrq();

		String pkValue = xn + xq + xh + unicode2Gbk(hddd[0]) + hdrq[0];

		boolean flag = dao.delShsjSqf(model, new String[] { pkValue }, user);

		return flag;
	}
	
	/**
	 * ���������Ϣ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getShsjList(PjpyStuForm model) {
		return dao.getShsjList(model);
	}
	
	/**
	 * ������ʵ���б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getShsjList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getShsjList(model, user);
	}
	
	/**
	 * �������Html
	 * 
	 * @author qlj
	 */
	public String getShsjHtml(SearchRsModel rsModel, PjpyStuForm model,
			ArrayList<String[]> rsArrList, User user) {

		// ������Ŀ
		String czxm = model.getCzxm();
		// IE�汾
		String ie = rsModel.getIe();
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ��Ŀ
		String xmdm = model.getXmdm();
		// �û�����
		String yhlx = model.getYhlx();

		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String xh = rs[0];

				String userName = user.getUserName();

				// ������ɫ
				String color = "";
				// �Ƿ���
				boolean flag = false;
				if (xh.equalsIgnoreCase(userName)) {
					flag = true;
					color = "pink";
				}else if ("bzr".equalsIgnoreCase(yhlx)
						|| "xy".equalsIgnoreCase(yhlx)
						|| "xx".equalsIgnoreCase(yhlx)) {
					flag = true;
				}

				spHtml
						.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				for (int j = 0; j < rs.length - 6; j++) {
					spHtml
							.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;background-color:"
									+ color + "\" ");

					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						spHtml.append("height=\"28.5px\"");
					} else {
						spHtml.append("height=\"29px\"");
					}
					spHtml.append(">");

					spHtml.append(rs[j]);
					spHtml.append("</td>");
				}

				// ����
				String cz = rs[rs.length - 1];
				// Ͷ������
				String tsnr = rs[rs.length - 2];
				// �������
				String clyj = rs[rs.length - 3];
				// ����������
				String clrxm = rs[rs.length - 4];

				spHtml
						.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;background-color:"
								+ color + "\" > ");

				if (flag) {
					spHtml
							.append("<a href\"#\" onclick=\"showSqxxDetail('"
									+ xn
									+ "','"
									+ xq
									+ "','"
									+ xh
									+ "','"
									+ xmdm
									+ "','view');return false;\" style=\"cursor:hand\">");
					spHtml.append("<font color=\"blue\">�鿴</font>");
					spHtml.append("</a>");
				} else {

					if ("bz".equalsIgnoreCase(yhlx)) {
						spHtml.append("���ɲ鿴");
					} else {
						spHtml.append("<a href\"#\" onclick=\"showXstsDiv('"
								+ cz + "','" + xh + "','" + tsnr + "','"
								+ clrxm + "','" + clyj
								+ "');return false;\" style=\"cursor:hand\">");
						spHtml.append("<font color=\"blue\">" + cz + "</font>");
						spHtml.append("</a>");
					}
				}
				spHtml.append("</td>");

				spHtml.append("</tr>");
			}
		}

		return spHtml.toString();
	}
	
	/**
	 * ������ʵ����Ϣ
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getShsjInfo(PjpyStuForm model,
			User user) {

		String sqid = model.getSqid();

		String[] colList = new String[] { "hddd", "hdrq", "hdnr", "hdsj", "sqf" };
		HashMap<String, String> map = CommonQueryDAO.commonQueryOne(
				"szyc_shsjfzb", colList, "id", sqid);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list.add(map);

		return list;
	}
	
	/**
	 * �޸����ʵ����Ϣ
	 * 
	 * @author ΰ�����
	 */
	public Boolean editShsjInfo(PjpyStuForm model, User user) {
		
		boolean flag = false;
		
		try {
			flag = dao.editShsjInfo(model, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}
}
