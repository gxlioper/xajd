package xgxt.xszz.comm.xmtj;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommService;

public class XszzXmtjService extends XszzCommService {

	XszzXmtjDAO dao = new XszzXmtjDAO();

	/**
	 * ��õ������ݱ�ͷ
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String xxdm, String lx) {
		DAO dao = DAO.getInstance();

		String[] colListCN = null;
		String[] colListEN = null;

		if ("zzhz".equalsIgnoreCase(lx)) {// �й��ش�
			colListCN = new String[] { "ѧ��", "����", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����",
					"�༶����", "��Ŀ��", "�ܽ��", "ͳ�ƿ�ʼʱ��", "ͳ�ƽ�ֹʱ��" };
			colListEN = new String[] { "xh", "xm", "nj", "xymc", "zymc",
					"bjmc", "xmNum", "zje", "kssj", "jssj" };
		}

		return dao.arrayToList(colListEN, colListCN);
	}

	/**
	 * ������������б�
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public ArrayList<String[]> getZzhzList(XszzTyForm model) throws Exception {
		return getResultList(dao.getZzhzList(model), model);
	}

	/**
	 * �����������(ѧ������)
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getXsZzhzList(XszzTyForm model) {

		List<HashMap<String, String>> list = dao.getXsZzhzList(model);

		if (list != null && list.size() > 0) {

			HashMap<String, String> map = new HashMap<String, String>();

			// �ܽ��
			float zje = 0;

			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> info = list.get(i);
				String je = info.get("xmzzje");
				zje += Float.parseFloat(je);
			}

			map.put("zje", String.valueOf(zje));

			list.add(map);
		}

		return list;
	}

	/**
	 * ��������
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expZzhzInfo(XszzTyForm model, OutputStream os) throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ��Ŀ����
		String title = "��������";

		List<HashMap<String, String>> topTr = getTopTr(xxdm, "zzhz");
		ArrayList<String[]> list = dao.getZzhzList(model);

		expToExcel(title, topTr, list, os);
	}

	/**
	 * �ж��Ƿ񳬹���������
	 * 
	 * @author luojw
	 */
	public String isCgZzsx(HashMap<String, String> xmInfo, XszzTyForm model) {

		String message = "";
		// ��Ŀ����
		String xmdm = xmInfo.get("xmdm");
		// ���
		String nd = Base.currNd;
		// �������
		String hdsx = getOneValue("xszz_xmtjb", "xmtjz", "xmdm||xmtj", xmdm
				+ "hdsx");

		XszzTyForm tjModel = new XszzTyForm();

		if (!Base.isNull(hdsx)) {
			// ����ʱ��
			String sqsj = xmInfo.get("sqsj");
			// ��Ŀ�������
			// String xmzzje = xmInfo.get("xmzzje");
			// ѧ��
			tjModel.setXh(model.getXh());
			// ��ʼʱ��
			String kssj = nd + "0101";
			tjModel.setKssj(kssj);
			// ����ʱ��
			String jssj = nd + "1231";
			tjModel.setJssj(jssj);

			List<HashMap<String, String>> list = dao.getXsZzhzList(tjModel);

			if (list != null && list.size() > 0) {
				// �ܽ��
				float zje = 0;

				for (int i = 0; i < list.size(); i++) {
					HashMap<String, String> map = list.get(i);
					// �������
					String je = map.get("xmzzje");
					// ����ʱ��
					String sj = map.get("sqsj");
					// ��Ŀ����
					String dm = map.get("xmdm");

					if (!dm.equalsIgnoreCase(xmdm)) {
						zje += Float.parseFloat(je);
					} else if (dm.equalsIgnoreCase(xmdm)
							&& !sj.equalsIgnoreCase(sqsj)) {
						zje += Float.parseFloat(je);
					}
				}

				if (zje > Float.parseFloat(hdsx)) {
					message = "��������" + nd + "����Ѿ��������" + zje + "��Ԫ��\n"
							+ "��������Ŀ�����ƣ��޷����ͨ������ȷ��!";
				}
			}
		}

		return message;
	}

	/**
	 * �ж��Ƿ񳬹���������(����)
	 * 
	 * @author luojw
	 */
	public String isCgZzsxPl(HashMap<String, String> xmInfo, XszzTyForm model) {

		String message = "";
		// ��Ŀ����
		String xmdm = xmInfo.get("xmdm");
		// ���
		String nd = Base.currNd;
		// �������
		String hdsx = getOneValue("xszz_xmtjb", "xmtjz", "xmdm||xmtj", xmdm
				+ "hdsx");

		// ѧ��+����ʱ��
		String shpk = model.getShpk();

		String[] pkValue = null;

		if (!Base.isNull(shpk)) {

			pkValue = new String[shpk.length()];

			String[] arr_shpk = shpk.split("!!@@!!");

			if (arr_shpk != null && arr_shpk.length > 0) {

				pkValue = new String[arr_shpk.length];

				for (int i = 0; i < arr_shpk.length; i++) {
					pkValue[i] = arr_shpk[i];
				}
			}

		}

		XszzTyForm tjModel = new XszzTyForm();
		tjModel.setCheckVal(pkValue);

		if (!Base.isNull(hdsx)) {
			// ��ʼʱ��
			String kssj = nd + "0101";
			tjModel.setKssj(kssj);
			// ����ʱ��
			String jssj = nd + "1231";
			tjModel.setJssj(jssj);

			ArrayList<String[]> list = dao.getZzhzList(tjModel);

			if (list != null && list.size() > 0) {

				for (int i = 0; i < list.size(); i++) {
					String[] rs = list.get(i);
					// ѧ��
					String xh = rs[0];
					// ����
					String xm = rs[1];
					// ������Ŀ������������
					String je = rs[7];

					if (Float.parseFloat(je) > Float.parseFloat(hdsx)) {
						message = xm + "(" + xh + ")\n��" + nd + "����Ѿ��������" + je
								+ "��Ԫ��\n" + "��������Ŀ�����ƣ��޷����ͨ������ȷ��!";
					}
				}
			}
		}

		return message;
	}
}