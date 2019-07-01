package xsgzgl.xszz.general.ywjk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xsgzgl.xszz.general.XszzGeneralService;
import xsgzgl.xszz.general.inter.XszzYwjkInterface;

import common.GlobalsVariable;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ������_�I�սӿ�_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XszzYwjkService extends XszzGeneralService implements
		XszzYwjkInterface {

	XszzYwjkDAO dao = new XszzYwjkDAO();

	/**
	 * ��ÌW���u���б�
	 * 
	 * @date 2013-01-23
	 * @author ΰ�����
	 */
	public List<HashMap<String, Object>> getStuZzList(String xh) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		// ������Ŀ�б�
		List<String[]> zzxmList = dao.getXszzList(xh);

		String[] title = { "��������", "��Ŀ����", "��������", "���", "����ʱ��" };
		List<String[]> rs = new ArrayList<String[]>();
		rs.add(title);
		rs.addAll(zzxmList);

		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "������Ŀ");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, rs);
		list.add(map);

		return list;
	}
}