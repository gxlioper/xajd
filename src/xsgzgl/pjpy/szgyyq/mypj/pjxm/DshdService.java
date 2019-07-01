package xsgzgl.pjpy.szgyyq.mypj.pjxm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.szgyyq.model.DshdModel;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuDAO;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaForm;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����(ѧ��)_������Ŀ_����_Service��
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

public class DshdService extends CommService {

	DshdDAO dao = new DshdDAO();

	/**
	 * ������������
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveDshdSqf(PjpyStuForm model, User user) {

		DshdModel dshdModel = model.getDshdModel();

		// ѧ��
		String xh = dshdModel.getXh();
		// ѧ��
		String xn = dshdModel.getXn();
		// ѧ��
		String xq = dshdModel.getXq();
		// ��������
		String[] dsmc = dshdModel.getDsmc();
		// ��������
		String[] dsrq = dshdModel.getDsrq();
		// �����ĵ�
		String[] dsxd = dshdModel.getDsxd();
		// �Ƿ��
		String[] sfhj = dshdModel.getSfhj();
		// �����
		String[] sqf = dshdModel.getSqf();

		String[] pkValue = null;

		// �������
		List<String[]> params = new ArrayList<String[]>();
		if (dsmc != null && dsmc.length > 0) {

			pkValue = new String[dsmc.length];

			for (int i = 0; i < dsmc.length; i++) {

				String[] values = new String[] { xn, xq, xh,
						unicode2Gbk(dsmc[i]), dsrq[i], unicode2Gbk(dsxd[i]),
						unicode2Gbk(sfhj[i]), sqf[i] };

				params.add(values);
				pkValue[i] = xn + xq + xh + unicode2Gbk(dsmc[i]) + dsrq[i];
			}
		}

		boolean flag = dao.delDshdSqf(model, pkValue, user);

		if (flag) {
			flag = dao.saveDshdSqf(model, params, user);
		}

		return flag;
	}

	/**
	 * ɾ�����������
	 * 
	 * @author ΰ�����
	 */
	public Boolean delDshdSqf(PjpyStuForm model, User user) {

		DshdModel dshdModel = model.getDshdModel();

		// ѧ��
		String xh = dshdModel.getXh();
		// ѧ��
		String xn = dshdModel.getXn();
		// ѧ��
		String xq = dshdModel.getXq();
		// ��������
		String[] dsmc = dshdModel.getDsmc();
		// ��������
		String[] dsrq = dshdModel.getDsrq();

		String pkValue = xn + xq + xh + unicode2Gbk(dsmc[0]) + dsrq[0];

		boolean flag = dao.delDshdSqf(model, new String[] { pkValue }, user);

		return flag;
	}

	/**
	 * ���������Ϣ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getDshdList(PjpyStuForm model) {
		return dao.getDshdList(model);
	}

	/**
	 * �������Html
	 * 
	 * @author ΰ�����
	 */
	public String getDshdHtml(SearchRsModel rsModel, PjpyStuForm model,
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
				} else if ("bzr".equalsIgnoreCase(yhlx)
						|| "xy".equalsIgnoreCase(yhlx)
						|| "xx".equalsIgnoreCase(yhlx)) {
					flag = true;
				}

				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

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

				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;background-color:"
								+ color + "\" > ");

				if (flag) {
					spHtml.append("<a href\"#\" onclick=\"showSqxxDetail('"
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
	 * ��ö�����Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getDshdList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getDshdList(model, user);
	}

	/**
	 * ��ö��������Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getDshdShList(PjpyTeaForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		PjpyTeaService service = new PjpyTeaService();
		
		// ����б�
		ArrayList<String[]> dshdList = dao.getDshdShList(model, user);
		// ���״̬�б�
		List<HashMap<String, String>> shztList = dao.getDshdShList(dshdList,
				model, user);
		// ѧ��Ͷ����Ϣ
		List<HashMap<String, String>> xstsList = service.getXstsInfo(dshdList,
				model, user);
		
		ArrayList<String[]> list = new ArrayList<String[]>();

		String yhlx = model.getYhlx();
		String xmdm = model.getXmdm();

		if (dshdList != null && dshdList.size() > 0) {
			for (int i = 0; i < dshdList.size(); i++) {
				String[] rs = dshdList.get(i);
				String xh = rs[0];
				String xm = rs[1];
				String bjmc = rs[2];
				String sqf = fillZero(rs[3]);
				String bzrshf = fillZero(rs[4]);
				String xyshf = fillZero(rs[5]);
				String xxshf = fillZero(rs[6]);

				// ��Ͷ����
				String btsr = "";
				// Ͷ����
				String tsr = "";
				// Ͷ������
				String tsnr = "";
				// Ͷ��ʱ��
				String tssj = "";

				//����Ͷ����Ϣ
				if (xstsList != null && xstsList.size() > 0) {
					for (int j = 0; j < xstsList.size(); j++) {
						HashMap<String, String> xstsInfo = xstsList.get(j);			
						if(xh.equalsIgnoreCase(xstsInfo.get("btsr"))){
							btsr = xstsInfo.get("btsr");
							tsr = xstsInfo.get("tsr");
							tsnr = xstsInfo.get("tsnr");
							tssj = xstsInfo.get("tssj");
						}
					}
				}
				
				// �����λ�೤
				if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {

					// ���״̬
					String shzt = service.getShztInfo(shztList, xh, yhlx,xmdm);

					String[] value = new String[] { xh, xm, bjmc, sqf, bzrshf,
							shzt };
					list.add(value);
				} else if ("xy".equalsIgnoreCase(yhlx)) {// ѧԺ
					// ���״̬
					String shzt =service.getShztInfo(shztList, xh, yhlx,xmdm);

					
					String[] value = new String[] { xh, xm, bjmc, sqf, bzrshf,
							xyshf, shzt, btsr, tsr, tsnr, tssj };
					
					list.add(value);
				} else if ("xx".equalsIgnoreCase(yhlx)) {// ѧУ
					// ���״̬
					String shzt = service.getShztInfo(shztList, xh, yhlx,xmdm);

					String[] value = new String[] { xh, xm, bjmc, sqf, bzrshf,
							xyshf, xxshf, shzt, btsr, tsr, tsnr, tssj };

					list.add(value);
				}
			}
		}

		return list;
	}

	/**
	 * ��ö�����Ϣ
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getDshdInfo(PjpyStuForm model,
			User user) {

		String sqid = model.getSqid();

		String[] colList = new String[] { "dsmc", "dsrq", "dsxd", "sfhj", "sqf" };
		HashMap<String, String> map = CommonQueryDAO.commonQueryOne(
				"szyq_dshdjzb", colList, "id", sqid);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list.add(map);

		return list;
	}
	
	/**
	 * �޸Ķ�����Ϣ
	 * 
	 * @author ΰ�����
	 */
	public Boolean editDshdInfo(PjpyStuForm model, User user) {
		
		boolean flag = false;
		
		try {
			flag = dao.editDshdInfo(model, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}
}
