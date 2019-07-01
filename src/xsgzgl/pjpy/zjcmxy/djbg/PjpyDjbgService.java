package xsgzgl.pjpy.zjcmxy.djbg;

import java.util.HashMap;
import java.util.List;

import xsgzgl.pjpy.general.djbg.PjpyDjbgModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ǼǱ��_�㽭��ýѧԺ_Service��
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
	 * �ǼǱ������
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, Object> getDjbgInfo(PjpyDjbgModel model) {

		// ѧ��
		String xh = model.getXh();
		// ��Ŀ����
		String xmmc = model.getXmmc();
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("xh", xh);
		map.put("xn", xn);
		map.put("xmmc", xmmc);

		// ����ѧ����Ϣ
		setXsxxInfo(map);
		// ����ѧ���ɼ�
		setXscjInfo(map);

		return map;
	}

	/**
	 * ����ѧ����Ϣ��������Ա�⣩
	 * 
	 * @author ΰ�����
	 */
	private void setXsxxInfo(HashMap<String, Object> map) {

		// ѧ��
		String xh = (String) map.get("xh");
		// ѧ��
		String xn = (String) map.get("xn");

		HashMap<String, String> xsxx = dao.setXsxxInfo(xh, xn);

		map.putAll(xsxx);
	}

	/**
	 * ����ѧ��ɼ���Ϣ
	 * 
	 * @author ΰ�����
	 */
	private void setXscjInfo(HashMap<String, Object> map) {

		// ѧ��
		String xh = (String) map.get("xh");
		// ѧ��
		String xn = (String) map.get("xn");

		List<HashMap<String, String>> list = dao.getXscjList(xh, xn);

		if (list != null && list.size() > 0) {

			// �ɼ���
			int num = list.size();

			for (int i = 0; i < (num < 21 ? num : 21); i++) {
				String kcmc = list.get(i).get("kcmc");
				String cj = list.get(i).get("cj");

				map.put("kcmc" + i, kcmc);
				map.put("cj" + i, cj);
			}
		}
	}
}
