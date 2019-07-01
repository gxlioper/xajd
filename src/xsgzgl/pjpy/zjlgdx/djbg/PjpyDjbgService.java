package xsgzgl.pjpy.zjlgdx.djbg;

import java.util.HashMap;
import java.util.List;

import xsgzgl.pjpy.general.djbg.PjpyDjbgModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ǼǱ��_�㽭����ѧ_Service��
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

		if ("ʡ�������ҵ��".equalsIgnoreCase(xmmc)) {
			djbg = "syxbys";
		} else if ("У�������ҵ��".equalsIgnoreCase(xmmc)) {
			djbg = "xyxbys";
		}

		return djbg;
	}

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

		String nowTime = dao.getNowTime("YYYY��MM��DD��");
		map.put("nowTime", nowTime);

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
}
