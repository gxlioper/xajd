package xsgzgl.pjpy.bjlhdx.djbg;

import java.util.HashMap;
import java.util.List;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.djbg.PjpyDjbgModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ǼǱ��_�������ϴ�ѧ_Service��
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

public class PjpyDjbgService extends xsgzgl.pjpy.general.djbg.PjpyDjbgService {

	PjpyDjbgDAO dao = new PjpyDjbgDAO();

	/**
	 * ��õǼǱ��
	 * 
	 * @author ΰ�����
	 */
	public String getDjbg(PjpyDjbgModel model) {

		// �ǼǱ��
		String djbg = "djbg";
		// ��Ŀ����
		String xmmc = model.getXmmc();
		// ��Ŀ����ǰ2λ
		String tjdj = xmmc.length() > 2 ? xmmc.substring(0, 2) : xmmc;

		if ("����ѧ��".equalsIgnoreCase(xmmc) || "����ѧ���ɲ�".equalsIgnoreCase(xmmc)
				|| "����ѧ����".equalsIgnoreCase(xmmc)||"ѧ�罨���Ƚ�����".equalsIgnoreCase(xmmc)) {
			djbg = "shxs";
		} else if ("һ��".equalsIgnoreCase(tjdj) || "����".equalsIgnoreCase(tjdj)
				|| "����".equalsIgnoreCase(tjdj) || "�ص�".equalsIgnoreCase(tjdj)) {
			djbg = "yxxsjxj";
		} else if ("�����ҵ��".equalsIgnoreCase(xmmc)) {
			djbg = "yxbysb";
		}

		return djbg;
	}

	/**
	 * �ǼǱ������
	 * 
	 * @date 2013-01-31
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getDjbgInfoList(PjpyDjbgModel model) {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		String[] xh = model.getPrint_xh();

		String pjxn = jbszModel.getPjxn();
		
		String lx = model.getLx();

		String xmmc = model.getXmmc();

		// ��������
		if ("bcpj".equalsIgnoreCase(lx)) {

			List<HashMap<String, String>> bcpjList = dao.getBcpjList(xh, xmmc);
			List<HashMap<String, String>> bcshList = dao.getBcshList(xh, xmmc);
			// �Ƽ��ȼ�
			String tjdj = xmmc.length() > 2 ? xmmc.substring(0, 2) : xmmc;

			if (bcshList != null && bcshList.size() > 0) {

				int maxLv = bcshList.size() / xh.length;

				for (int i = 0; i < bcpjList.size(); i++) {
					String isEnd = (i == bcpjList.size() - 1) ? "yes" : "no";
					bcpjList.get(i).put("tjdj", tjdj);
					bcpjList.get(i).put("isEnd", isEnd);
					bcpjList.get(i).put("pjnd", pjxn.split("-")[0]);
					
					for (int j = 0; j < bcshList.size(); j++) {
						if (bcpjList.get(i).get("xh").equalsIgnoreCase(
								bcshList.get(j).get("xh"))) {
							if (String.valueOf(maxLv).equalsIgnoreCase(
									bcshList.get(j).get("shjb"))) {
								bcpjList.get(i).put("xxyj",
										bcshList.get(j).get("shyj"));
							} else if (String.valueOf(maxLv - 1)
									.equalsIgnoreCase(
											bcshList.get(j).get("shjb"))) {
								bcpjList.get(i).put("xyyj",
										bcshList.get(j).get("shyj"));
							}
						}
					}
				}
			}

			return bcpjList;
		} else {

			String xn = model.getXn();
			String xq = model.getXq();
			String nd = "no";

			List<HashMap<String, String>> lspjList = dao.getLspjList(xh, xn,
					xq, nd, xmmc);
			List<HashMap<String, String>> lsshList = dao.getBcshList(xh, xmmc);
			
			// �Ƽ��ȼ�
			String tjdj = xmmc.length() > 2 ? xmmc.substring(0, 2) : xmmc;

			if (lspjList != null && lspjList.size() > 0) {

				int maxLv = lspjList.size() / xh.length;

				for (int i = 0; i < lspjList.size(); i++) {
					String isEnd = (i == lspjList.size() - 1) ? "yes" : "no";
					lspjList.get(i).put("tjdj", tjdj);
					lspjList.get(i).put("isEnd", isEnd);
					lspjList.get(i).put("pjnd", pjxn.split("-")[0]);
					
					for (int j = 0; j < lsshList.size(); j++) {
						if (lspjList.get(i).get("xh").equalsIgnoreCase(
								lsshList.get(j).get("xh"))) {
							if (String.valueOf(maxLv).equalsIgnoreCase(
									lsshList.get(j).get("shjb"))) {
								lspjList.get(i).put("xxyj",
										lsshList.get(j).get("shyj"));
							} else if (String.valueOf(maxLv - 1)
									.equalsIgnoreCase(
											lsshList.get(j).get("shjb"))) {
								lspjList.get(i).put("xyyj",
										lsshList.get(j).get("shyj"));
							}
						}
					}
				}
			}
			
			return lspjList;
		}
	}
}
