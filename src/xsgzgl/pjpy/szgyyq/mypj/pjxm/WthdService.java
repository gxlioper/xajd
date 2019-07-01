package xsgzgl.pjpy.szgyyq.mypj.pjxm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.szgyyq.model.WthdModel;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����(ѧ��)_������Ŀ_����_DAO��
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

public class WthdService extends CommService {

	WthdDAO dao = new WthdDAO();

	/**
	 * �������������
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveWthdSqf(PjpyStuForm model, User user) {

		WthdModel wthdModel = model.getWthdModel();

		// ѧ��
		String xh = wthdModel.getXh();
		// ѧ��
		String xn = wthdModel.getXn();
		// ѧ��
		String xq = wthdModel.getXq();
		// �����
		String[] hdnr = wthdModel.getHdnr();
		// ����
		String[] xthdrq = wthdModel.getXthdrq();
		// �����
		String[] jldj = wthdModel.getJldj();
		// �����
		String[] sqf = wthdModel.getSqf();

		String[] pkValue = null;

		// �������
		List<String[]> params = new ArrayList<String[]>();
		if (hdnr != null && hdnr.length > 0) {

			pkValue = new String[hdnr.length];

			for (int i = 0; i < hdnr.length; i++) {

				jldj[i] = Base.isNull(jldj[i]) ? "" : unicode2Gbk(jldj[i]);

				String[] values = new String[] { xn, xq, xh,
						unicode2Gbk(hdnr[i]), xthdrq[i], jldj[i], sqf[i] };

				params.add(values);
				pkValue[i] = xn + xq + xh + unicode2Gbk(hdnr[i]) + xthdrq[i];
			}
		}

		boolean flag = dao.delWthdSqf(model, pkValue, user);

		if (flag) {
			flag = dao.saveWthdSqf(model, params, user);
		}

		return flag;
	}

	/**
	 * ɾ�����������
	 * 
	 * @author ΰ�����
	 */
	public Boolean delWthdSqf(PjpyStuForm model, User user) {

		WthdModel wthdModel = model.getWthdModel();

		// ѧ��
		String xh = wthdModel.getXh();
		// ѧ��
		String xn = wthdModel.getXn();
		// ѧ��
		String xq = wthdModel.getXq();
		// �����
		String[] hdnr = wthdModel.getHdnr();
		// ����
		String[] xthdrq = wthdModel.getXthdrq();

		String pkValue = xn + xq + xh + unicode2Gbk(hdnr[0]) + xthdrq[0];

		boolean flag = dao.delWthdSqf(model, new String[] { pkValue }, user);

		return flag;
	}
	
	/**
	 * ���������Ϣ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getWthdList(PjpyStuForm model) {
		return dao.getWthdList(model);
	}
	
	/**
	 * ������Ա����Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getWthdList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getWthdList(model, user);
	}
	
	/**
	 * �������Html
	 * 
	 * @author qlj
	 */
	public String getWthdHtml(SearchRsModel rsModel, PjpyStuForm model,
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
	 * ���������Ϣ
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getWthdInfo(PjpyStuForm model,
			User user) {

		String sqid = model.getSqid();

		String[] colList = new String[] { "hdnr", "xthdrq", "jldj", "sqf" };
		HashMap<String, String> map = CommonQueryDAO.commonQueryOne(
				"szyq_xthddjb", colList, "id", sqid);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list.add(map);

		return list;
	}
	
	/**
	 * �޸�������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public Boolean editWthdInfo(PjpyStuForm model, User user) {
		
		boolean flag = false;
		
		try {
			flag = dao.editWthdInfo(model, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}
}
