package com.zfsoft.xgxt.comm.zdybd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.zdybd.dao.ZddyDao;
import com.zfsoft.xgxt.comm.zdybd.model.ZddyModel;
import com.zfsoft.xgxt.comm.zdybd.util.ZdybdCommon;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �Զ����
 * @�๦������: �ֶζ���
 * @���ߣ� ligl
 * @ʱ�䣺 2013-11-26 ����03:56:07
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class ZddyService extends SuperServiceImpl<ZddyModel, ZddyDao> {

	private ZddyDao dao = new ZddyDao();

	public ZddyService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����:���ݹ��ܴ����ȡ���з����µ��ֶ��б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-26 ����04:07:46
	 * @�޸ļ�¼:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndm(String gndm)
			throws Exception {
		List<HashMap<String, String>> list = null;
		if (gndm == null) {
			list = new ArrayList<HashMap<String, String>>();
		} else {
			list = dao.getListByGndm(gndm);
			/*
			 * 1.�̶�ֵ����ʽΪ��1:��,2:Ů 2:���ݿ�ȡֵ,������:����,���ơ�,
			 * 3:����ȫ��#������|����:����,���ƣ����У����в������������֧��һ��String���ͣ�
			 */
			String szlx = null;
			String szz = null;
			String zdlx = null;
			String ssxJson = null;
			ZdybdCommon zdybdCommon = new ZdybdCommon();
			for (HashMap<String, String> map : list) {
				szlx = map.get("szlx");
				szz = map.get("szz");
				zdlx = map.get("zdlx");
				if (zdlx != null && zdlx.equals("22")) {
					if (ssxJson == null) {
						ssxJson = zdybdCommon.getSsxJson();
					}
					map.put("szz", ssxJson);
				}

				if (szlx == null) {
					continue;
				}
				if (szlx.trim().equals("1") || szlx.trim().equals("10")) {
					map.put("szz", zdybdCommon.dmmcToJson(szz));
				} else if (szlx.trim().equals("2") || szlx.trim().equals("20")) {
					map.put("szz", zdybdCommon.getSjkqz(szz));
				} else if (szlx.trim().equals("3") || szlx.trim().equals("30")) {
					map.put("szz", zdybdCommon.getFfqz(szz));
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @����:���ݹ��ܴ����ȡ���з����µ��ֶ��б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-26 ����04:07:46
	 * @�޸ļ�¼:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndmOfXszdsz(String gndm)
			throws Exception {
		String zddyid = null;
		List<HashMap<String, String>> list = getListByGndm(gndm);

		List<HashMap<String, String>> stuList = dao.getListByGndmOfXszdsz(gndm);
		if (stuList != null && stuList.size() > 0) {
			for (HashMap<String, String> stuMap : stuList) {
				zddyid = stuMap.get("zddyid");
				if (zddyid == null) {
					continue;
				}
				for (HashMap<String, String> map : list) {
					if (zddyid.equals(map.get("zddyid"))) {
						map.put("yxxg", stuMap.get("yxxg"));
						map.put("yxwk", stuMap.get("yxwk"));
						break;
					}
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @����:���ݹ��ܴ����ȡ���з����µ��ֶ��б�ȫ��Ϊ����ʾ״̬
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-9 ����10:41:52
	 * @�޸ļ�¼:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndmOfXs(String gndm)
			throws Exception {
		List<HashMap<String, String>> list = getListByGndm(gndm);
		for (HashMap<String, String> map : list) {
			map.put("yxxg", "0");
			map.put("yxwk", "1");
		}
		return list;
	}

	/**
	 * 
	 * @����:��ȡ�ֶζ��壬����ʾ������ѧ������
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-10 ����04:27:31
	 * @�޸ļ�¼:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndmOfSimple(String gndm)
			throws Exception {
		List<HashMap<String, String>> list = dao.getSimpleListByGndm(gndm);
		return list;
	}

	public boolean updateSimple(ZddyModel model) throws Exception {
		boolean result = true;
		String lb = model.getLb();
		String gndm = model.getGndm();
		String dataJson = model.getDataJson();
		List list = JsonUtil.jsonToList(dataJson, ZddyModel.class);
		if (lb != null && lb.equals("js")) {
			result = dao.updateSimple(list);
		} else {
			result = dao.deleteStuByGnmk(gndm);
			if (result) {
				result = dao.insertSimpleForXs(list);
			}
		}
		return result;
	}

}
