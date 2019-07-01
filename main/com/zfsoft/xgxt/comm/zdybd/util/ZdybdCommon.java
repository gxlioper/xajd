package com.zfsoft.xgxt.comm.zdybd.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import com.zfsoft.xgxt.comm.zdybd.dao.ZdybdBaseDao;
import com.zfsoft.xgxt.comm.zdybd.model.DmmcModel;

public class ZdybdCommon {
	private ZdybdBaseDao dao = new ZdybdBaseDao();

	/**
	 * 
	 * @����:ȡֵ���ͣ�2:���ݿ�ȡֵ,������:����,���ơ�,����ʾ��ʽ����ѡ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-7-25 ����09:45:11
	 * @�޸ļ�¼:
	 * @param qzfw
	 * @return String ��������
	 * @throws
	 */
	public String getSjkqz(String szz) {
		String result = "";
		String bm = null;// ����
		String dm = null;// ����
		String mc = null;// ����
		List<DmmcModel> dmMcList = new ArrayList<DmmcModel>();
		List<HashMap<String, String>> dmMcMapList = null;
		if (szz != null) {
			try {
				String[] szzs = szz.split(":");
				if (szzs != null && szzs.length > 1) {
					bm = szzs[0];
					dm = szzs[1].split(",")[0];
					mc = szzs[1].split(",")[1];
					StringBuffer sb = new StringBuffer();
					sb.append("select distinct ");
					sb.append(dm);
					sb.append(" dm,");
					sb.append(mc);
					sb.append(" mc ");
					sb.append(" from  ");
					sb.append(bm);
					sb.append(" where ");
					sb.append(dm);
					sb.append(" is not null ");
					sb.append(" order by  ");
					sb.append(dm);
					dmMcMapList = dao.getListBySql(sb.toString());
					DmmcModel dmmcModel = null;
					for (HashMap<String, String> map : dmMcMapList) {
						if (map != null) {
							dmmcModel = new DmmcModel();
							dmmcModel.setDm(map.get("dm"));
							dmmcModel.setMc(map.get("mc"));
							dmMcList.add(dmmcModel);
						}
					}
					result = dmMcGsh(dmMcList);
				}
			} catch (Exception e) {
				e.printStackTrace();// /////////////////////////////
			}
		}
		return result;
	}

	/**
	 * 
	 * @����:ȡֵ���ͣ�3:����ȫ��#������|����:����,���ƣ����У����в������������֧��һ��String���͡���ʾ��ʽ����ѡ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-7-25 ����09:44:56
	 * @�޸ļ�¼:
	 * @param qzfw
	 * @return String ��������
	 * @throws
	 */
	public String getFfqz(String szz) {
		String result = "";
		String lm = null;// ����
		String ffms = null;// ������|����
		String ffm = null;// ������
		String param = null;// ����
		if (szz != null) {
			try {
				String[] szzs = szz.split(":");
				if (szzs != null) {
					lm = szzs[0].split("#")[0];// ����
					ffms = szzs[0].split("#")[1];// ������|����
					ffm = ffms.split("[|]")[0];
					if (ffms.split("[|]").length > 1) {
						param = ffms.split("[|]")[1];
					}
					Class t = Class.forName(lm);
					Object o = t.newInstance();
					Method method = null;
					if (param == null) {
						method = t.getMethod(ffm);
						method = t.getMethod(ffm);
					} else {
						method = t.getMethod(ffm, String.class);
					}
					if (szzs.length == 1) {// ������ ����ȫ��#�������������޲Σ�����Ϊ���߷ָ���ַ���
						result = (String) method.invoke(o);
					} else if (szzs.length > 1) {// ����ȫ��#������:����,���ƣ������޲Σ�����ΪList<hashMap<String,String>>��ʽ
						List<HashMap<String, String>> list = null;
						String dm = szzs[1].split(",")[0];
						String mc = szzs[1].split(",")[1];
						if (param == null) {
							list = (List) method.invoke(o);
						} else {
							list = (List) method.invoke(o, param);
						}

						result = dmMcGsh(list, dm, mc);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();// /////////////////////////////
			}
		}
		return result;
	}

	/*
	 * ��list�д������Ƹ�ʽ��,ת����1:��,2:Ů
	 */
	private String dmMcGsh(List<HashMap<String, String>> list, String dm,
			String mc) {
		String result = "";
		boolean flag = false;
		if (list != null) {
			for (HashMap map : list) {
				String key = map.get(dm).toString();
				String value = map.get(mc).toString();
				if (value != null) {
					if (flag) {
						result += ",";
					} else {
						flag = true;
					}
					result += "{dm:'" + key + "',mc:'" + value + "'}";
				}
			}
		}
		result = "[" + result + "]";
		return result;
	}

	/*
	 * ��list�д������Ƹ�ʽ��,ת����1:��,2:Ů
	 */
	private String dmMcGsh(List<DmmcModel> list) {
		String result = "";
		boolean flag = false;
		if (list != null) {
			for (DmmcModel model : list) {
				if (model != null && model.getDm() != null) {
					String key = model.getDm();
					String value = model.getMc();
					if (value != null) {
						if (flag) {
							result += ",";
						} else {
							flag = true;
						}
						result += "{dm:'" + key + "',mc:'" + value + "'}";
					}
				}
			}
		}
		result = "[" + result + "]";
		return result;
	}

	/**
	 * 
	 * @����:��1:��,2:Ů ת����json��ʽ
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-28 ����07:16:05
	 * @�޸ļ�¼:
	 * @param dmmc
	 * @return String ��������
	 * @throws
	 */
	public String dmmcToJson(String dmmc) {
		String dm = null;
		String mc = null;
		String result = "";
		boolean flag = false;

		if (dmmc != null && !dmmc.trim().equals("")) {
			String[] dmmcs = dmmc.split(",");
			for (String str : dmmcs) {
				if (str == null || str.trim().equals("")) {
					continue;
				}
				String[] strs = str.split(":");
				if (strs != null) {
					if (strs.length == 0) {
						dm = "";
					} else {
						dm = strs[0];
					}
					if (strs.length > 1) {
						mc = strs[1];
					} else {
						mc = dm;
					}
				}

				if (flag) {
					result += ",";
				} else {
					flag = true;
				}
				result += "{dm:'" + dm + "',mc:'" + mc + "'}";
			}
		}
		result = "[" + result + "]";
		return result;
	}

	/**
	 * 
	 * @����:�õ�ʡ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-2 ����04:58:28
	 * @�޸ļ�¼:
	 * @return String ��������
	 * @throws
	 */
	public String getSsxJson() throws Exception {
		String jsonStr = "";
		List<HashMap<String, String>> shList = dao.getShList();
		List<HashMap<String, String>> qxList = dao.getQxList();

		List<HashMap> list = new ArrayList<HashMap>();
		List<HashMap> resultShiList = null;
		List<HashMap> resultXiList = null;
		HashMap resultShMap = null;
		HashMap resultShiMap = null;
		HashMap resultXiMap = null;
		for (HashMap<String, String> shMap : shList) {
			String shdm = shMap.get("dm");
			String shmc = shMap.get("mc");

			resultShMap = new HashMap();
			resultShiList = new ArrayList<HashMap>();
			resultShMap.put("treeNode", shmc);
			resultShMap.put("value", shdm);
			resultShMap.put("childNode", resultShiList);
			if(qxList == null || qxList.size() == 0){
				continue;
			}
			for (HashMap<String, String> qxMap : qxList) {
				String qxdm = qxMap.get("dm");
				String qxmc = qxMap.get("mc");
				if (qxdm == null || qxdm.length() < 6 || !qxdm.substring(0, 2).equals(shdm.substring(0, 2)) || qxdm.equals(shdm)) {
					continue;
				}
				if (qxdm.substring(4, 6).equals("00")) {
					resultXiList = new ArrayList<HashMap>();
					resultShiMap = new HashMap();
					resultShiMap.put("treeNode", qxmc);
					resultShiMap.put("value", qxdm);					
					resultShiMap.put("childNode", resultXiList);
					resultShiList.add(resultShiMap);
				} else {
					resultXiMap = new HashMap();
					resultXiMap.put("treeNode", qxmc);
					resultXiMap.put("value", qxdm);
					resultXiList.add(resultXiMap);
				}
			}
			list.add(resultShMap);
		}
		jsonStr = JSONArray.fromCollection(list).toString();
		return jsonStr;

	}

}
