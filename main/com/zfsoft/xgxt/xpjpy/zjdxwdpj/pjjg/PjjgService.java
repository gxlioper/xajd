/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-3-13 ����02:00:46 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ����������_�ҵ�����_�������
 * @���ߣ�  Meng.Wei[����:1186]
 * @ʱ�䣺 2017-3-13 ����02:00:46 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjjgService extends SuperServiceImpl<PjjgForm, PjjgDao>implements Constants {
	private PjjgDao dao = new PjjgDao();

	public PjjgService() {
		super.setDao(dao);
	}
	
	/**
	 * @����: ��Ŀ����list
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����03:55:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlx() throws Exception {
		return dao.getXmlx();
	}
	
	/**
	 * @����: ��Ŀ����list
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����03:56:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxz() throws Exception {
		return dao.getXmxz();
	}
	
	/** 
	 * @����: ���ӱ����ж�
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����03:56:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByPjjgAdd(PjjgForm model) throws Exception {
		String num = dao.checkExistForAddSave(model);
		return Integer.valueOf(num) > 0;
	}
	
	/**
	 * @����: �޸ı����ж�
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����03:56:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByPjjgUpdate(PjjgForm model) throws Exception {
		String num = dao.checkExistForUpdateSave(model);
		return Integer.valueOf(num) > 0;
	}
	
	/**
	 * @����: ������Ϣ������ѯ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����03:57:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getPjjgInfo(String xh, String xn){
		return dao.getPjjgInfo(xh, xn);
	}
	
	/**
	 * @����: �鿴��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����03:58:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOnePjjgList(String id) {

		return dao.getOnePjjgList(id);
	}
	
	/**
	 * @����: ����ѧ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-22 ����11:24:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZjXs(PjjgForm model, User user) throws Exception{
		return dao.getZjXs(model, user);
	}
	
	/**
	 * @����: �鿴��ͨ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-5 ����11:59:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rskzfw
	 * @param xmdm
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getShtgrs(String rskzfw, String xmdm,
			String xn) {
		if (StringUtils.isNull(rskzfw)) {
			return null;
		}
		if (RSKZFW_XY.equals(rskzfw)) {
			return dao.getZzmeByXy(xmdm, xn);
		}else {
			return dao.getZzmeByQx(xmdm, xn);
		}
	}
	/**
	 * @����: ��ȡƽ���ɼ�
	 * @���ڣ� 2018-1-3 ����05:10:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * String ��������
	 * @throws
	 */
	public String getAverage(List<HashMap<String, String>> list) {
		if (null == list || list.size() <= 0) {
			return "";
		}
		int kcs = 0;
		Float zfs = new Float(0);
		/*ѭ����ʽ������*/
		for (HashMap<String, String> hm : list) {
			kcs++;
			String cj = hm.get("cj");
			if (StringUtils.isNotNull(cj)) {
				zfs += Float.parseFloat(cj);
			}
		}
		Float pjfs = zfs / kcs;
		return pjfs.toString();
	}

	/**
	 * @����: ��ȡƽ���ɼ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-1-3 ����05:24:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String ��������
	 * @throws
	 */
	public String getBjgcjNum(String xh, String xn, String xq) {
		Map<String, String> map = dao.getBjgcjNum(xh, xn, xq);
		String bjgcjs = "";
		if (null != map && null != map.get("num")) {
			bjgcjs = map.get("num");
			if ("0".equals(bjgcjs)) {
				bjgcjs = "";
			}
		}
		return bjgcjs;
	}

	/**
	 * @����: ����ȡ��������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-3 ����05:47:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getHznydxPjpyMap(String xh) {
		return dao.getHznydxPjpyMap(xh);
	}

	/**
	 * �յ�list
	 */
	public void addBlankList(List<HashMap<String,String>> list, int blankSize){
		for (int i = 0 ; i < blankSize ; i++){
			list.add(new HashMap<String, String>());
		}
	}

	/**
	 * @����: ��ʽ���ǼǱ�����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-4 ����10:32:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> formatForDjb(List<HashMap<String, String>> list) {
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();

		int cols = 3;// ��
		int row = 7;// ��
		int i = 0;// ����������
		int j = 0;// ��������

		/*����������� ������*/
		int m = list.size() - cols * row;
		if (m < 0) {
			for (int n = 0; n < Math.abs(m); n++) {
				HashMap<String, String> addMap = new HashMap<String, String>();
				addMap.put("kcmc", "");
				addMap.put("cj", "");
				list.add(addMap);
			}
		}
		// ѭ����ʽ������
		for (HashMap<String, String> hm : list) {
			i++;
			map.put("kcmc" + i, hm.get("kcmc"));// ���ÿ�еĿγ����� ��ӦkeyΪ kcmc+����i
			map.put("cj" + i, hm.get("cj"));// ���ÿ�еĿγ̳ɼ� ��ӦkeyΪ cj+����i
			// ����
			if (i % cols == 0) { // cols����Ϊһ��
				newList.add(map);
				i = 0;
				map = new HashMap<String, String>();
			}
			if (cols * row < j) {// ��������������
				break;
			}
			j++;
		}
		return newList;
	}

	/**
	 * @����: �ṩ��ѧ����Ϣ��ʾ�����Ľӿ� �������صǼǱ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-4 ����11:13:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPjpyInfoMapForDjb(String xh) {
		List<HashMap<String, String>> list = dao.getHjqkByXhMap(xh);
		int ts;
		if (null == list) {
			ts = 0;
		}
		ts = 4 - list.size();
		for (int i = 0; i < ts; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("xmmc", "");
			list.add(map);
		}
		return list;
	}

	/**
	 * @����: ��ȡ�������list
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPjpyInfoList(String xh) {
		List<HashMap<String, String>> list = dao.getHjqkByXhMap(xh);
		return list;
	}
	

	/**
	 *  ��������ѧ��.
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-01-04 17:04
	 * @param
	 * @return boolean
	 * @throw Exception
	 */
	public boolean scyxxs() throws Exception {
		return dao.scyxxs("pro_zjdx_pjpy_scyxxs");
	}
	
	/**
	 * @����: ȡѧ�����һ��������Ϣ����Ҫ��ȡ
	 *������ˮƽ������绰��������Ṥ��ְ�񡢸���ѧϰ�������μӿ�����������轱��λ����ʶ��
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-31 ����05:01:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getLatestSqxx(String xh) throws Exception{
		return dao.getLatestSqxx(xh);
	}
}
