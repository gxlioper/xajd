/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-27 ����04:11:06 
 */
package com.zfsoft.xgxt.gygl.gyjl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����ģ��
 * @�๦������: ��Ԣ������ϢService
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-4-27 ����04:09:18
 * @�汾�� V5.9.17
 * @�޸ļ�¼:
 */

public class GyjltjService extends SuperServiceImpl<GyjltjForm, GyjltjDAO> {

	private GyjltjDAO dao = new GyjltjDAO();

	public GyjltjService() {
		super.setDao(dao);
	}

	/**
	 * @����:��ȡ���������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-27 ����03:23:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<List<String>> ��������
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<List<String>> getGyjltj(User user, SearchModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// ��������
		List<List<String>> resultList = new ArrayList<List<String>>();
		String[][] yfStr = { { "9��", "09" }, { "10��", "10" }, { "11��", "11" },
				{ "12��", "12" }, { "1��", "01" }, { "2��", "02" },
				{ "3��", "03" }, { "4��", "04" }, { "5��", "05" }, { "6��", "06" },
				{ "7��", "07" }, { "8��", "08" }, { "�ܼ�", "qn" } };
		List<String> topList = getTopList(yfStr);
		resultList.add(topList);
		List<HashMap<String, String>> xswjList = dao.getXsWjlb(user, model);
		Map<String, String> tjmap = listToMap(dao.getXsWjTj());
		Map<String, String> dllbxx = listToMap(dao.getDllbxx());
		for (Map<String, String> map : xswjList) {
			List<String> xswj = new ArrayList<String>();
			String xh = "";
			String[] coms = { "rownum", "xymc", "zymc", "bjmc", "xh", "xm",
					"xb", "rxrq", "qsh" };
			for (String com : coms) {
				String val = map.get(com);
				if (com.equals("xh")) {
					xh = val;
				}
				xswj.add(val);
			}
			for (int i = 0; i < yfStr.length; i++) {
				Iterator<Entry<String, String>> ites = dllbxx.entrySet()
						.iterator();
				while (ites.hasNext()) {
					xswj.add(tjmap.get(xh + "," + yfStr[i][1] + ","
							+ ites.next().getKey()));
				}
			}
			resultList.add(xswj);
		}
		return resultList;
	}

	/**
	 * @����:��list ת���� Map
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-27 ����03:43:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return Map<String,String> ��������
	 * @throws
	 */
	private Map<String, String> listToMap(List<HashMap<String, String>> list) {
		Map<String, String> resultMap = new HashMap<String, String>();
		Iterator<HashMap<String, String>> ite = list.iterator();
		while (ite.hasNext()) {
			HashMap<String, String> map = ite.next();
			resultMap.put(map.get("pk"), map.get("val"));
		}
		return resultMap;
	}

	/**
	 * @����:��ȡ���������ͷ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-4-27 ����05:18:02
	 * @�޸ļ�¼:
	 * @return List<String> ��������
	 * @throws
	 */
	private List<String> getTopList(String[][] yfStr) {
		MessageResources message = MessageResources
				.getMessageResources("config.ApplicationResources");
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		// ��ȡ�����б�
		Map<String, String> dllbxx = listToMap(dao.getDllbxx());
		String[] topStr = { "���", Base.YXPZXY_KEY , "רҵ", "�༶", "ѧ��", "����", "�Ա�", "��ѧ����",
				"�����" };
		List<String> topList = new ArrayList<String>();
		for (int i = 0; i < topStr.length; i++) {
			topList.add(topStr[i]);
		}
		// ѭ���·�
		for (int i = 0; i < yfStr.length; i++) {
			Iterator<Entry<String, String>> ite = dllbxx.entrySet().iterator();
			while (ite.hasNext()) {
				Entry<String, String> en = ite.next();
				// ��̬��ӱ�ͷ
				topList.add(yfStr[i][0] + "" + en.getValue());
			}

		}
		return topList;
	}
}
