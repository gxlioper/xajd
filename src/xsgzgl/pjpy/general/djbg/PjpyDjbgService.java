package xsgzgl.pjpy.general.djbg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xsgzgl.pjpy.general.inter.PjpyDjbgInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ǼǱ��_ͨ��_Service��
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

public class PjpyDjbgService extends CommService implements PjpyDjbgInterface {

	PjpyDjbgDAO dao = new PjpyDjbgDAO();

	/**
	 * ��õǼǱ��
	 * 
	 * @author ΰ�����
	 */
	public String getDjbg(PjpyDjbgModel model) {

		// �ǼǱ��
		String djbg = "djbg";

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
	 * �ǼǱ������
	 * 
	 * @date 2013-01-31
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getDjbgInfoList(PjpyDjbgModel model) {
		// TODO �Զ����ɷ������
		return null;
	}
	
	public String getPrintJspForward(PjpyDjbgModel model) throws Exception {
		// TODO �Զ����ɷ������
		return null;
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
