/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����10:34:17 
 */
package com.zfsoft.xgxt.bzjl.wdbzjl.bzjljg;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsDao;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import common.GlobalsVariable;
import common.exception.SystemException;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.utils.date.MoneyFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �ҵ�����-�������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-30 ����10:34:17
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BzjljgService extends SuperServiceImpl<BzjljgModel, BzjljgDao> implements
		Constants {

	private BzjljgDao dao = new BzjljgDao();

	public BzjljgService() {
		super.setDao(dao);
	}

	/**
	 *
	 * @����:��ȡͬ��רҵ����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-11-29 ����05:06:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 *            ѧ��
	 * @param xn
	 *            ѧ��
	 * @param xq
	 *            ѧ��
	 * @return String ��������
	 * @throws
	 */
	public String getTjzyrs(String xh, String xn, String xq) {
		if (StringUtils.isNull(xh)) {
			return "";
		}

		return dao.getTjzyrs(xh, xn, xq);

	}

	public String getTbjrs(String xh) {
		if (StringUtils.isNull(xh)) {
			return "";
		}

		return dao.getTbjrs(xh);
	}

	/**
	 * �������㽱ѧ���㽭��ѧ��
	 */
	public boolean scyxjxj(User user) throws Exception {
		return dao.scyxjxj(user);
	}

	/**
	 * @����:Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ���Ŀ���ƣ�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-7 ����11:07:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param save
	 * @return boolean ��������
	 * @throws
	 */
	public boolean isExistByPjxmjg(BzjljgModel model, String type)
			throws Exception {
		if ("save".equalsIgnoreCase(type)) {
			String num = dao.checkExistForSave(model);
			return Integer.valueOf(num) > 0;
		} else {
			String num = dao.checkExistForUpdate(model);
			return Integer.valueOf(num) > 0;
		}

	}

	/**
	 *
	 * @����:��ȡƽ���ɼ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-10 ����09:33:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return Integer ��������
	 */
	public String getAverage(List<HashMap<String, String>> list) {
		if (null == list || list.size() <= 0) {
			return "";
		}
		int kcs = 0;
		Float zfs = new Float(0);
		// ѭ����ʽ������
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
	 *
	 * @����:��ʽ���ǼǱ�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-9 ����05:49:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> formatForDjb(
			List<HashMap<String, String>> list) {
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();

		int cols = 3;// ��
		int row = 7;// ��
		int i = 0;// ����������
		int j = 0;// ��������

		// ����������� ������
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
	 * @����:�����鿴����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-8 ����02:35:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return Object ��������
	 * @throws
	 */
	public Map<String, String> getOnePjxmjgList(String id) {

		return dao.getOnePjxmjgList(id);
	}

	// /**
	// * @����:������Ŀ���������List
	// * @���ߣ�cq [���ţ�785]
	// * @���ڣ�2013-8-14 ����09:36:20
	// * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	// * @return
	// * List<HashMap<String,String>> ��������
	// * @throws
	// */
	// public List<HashMap<String, String>> getxmmc() {
	//
	// return dao.getxmmc();
	// }

	/**
	 *
	 * @����: ��ѯ����ͳ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����09:19:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rskzfw
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getShtgrs(String rskzfw, String xmdm,
			String xn, String xq) {

		if (StringUtils.isNull(rskzfw)) {
			return null;
		}
		if (RSKZFW_NJXY.equals(rskzfw)) {
			return dao.getZzmeByNjxy(xmdm, xn, xq);
		} else if (RSKZFW_NJZY.equals(rskzfw)) {
			return dao.getZzmeByNjzy(xmdm, xn, xq);
		} else if (RSKZFW_XY.equals(rskzfw)) {
			return dao.getZzmeByXy(xmdm, xn, xq);
		} else if (RSKZFW_CPZ.equals(rskzfw)) {
			return dao.getZzmeByCpz(xmdm, xn, xq);
		} else if (RSKZFW_BJ.equals(rskzfw)) {
			return dao.getZzmeByBj(xmdm, xn, xq);
		} else {
			return dao.getZzmeByQx(xmdm, xn, xq);
		}
	}

	/**
	 * @����:������Ŀ���Ʋ�ѯ�����������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-14 ����03:30:38
	 * @�޸ļ�¼:
	 * @param xmmc
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> tjrs(String xmmc) throws Exception {
		if (xmmc == null) {
			logger.debug("��������Ϊ�գ�");
			throw new SystemException("��������Ϊ�գ�");
		} else {
			xmmc = xmmc.trim();
		}
		List<HashMap<String, String>> result = dao.tjrs(xmmc);
		return result;
	}

	/**
	 *
	 * @����: �ṩ��ѧ����Ϣ��ʾ�����Ľӿ�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-9-3 ����02:54:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,Object>> ��������
	 * @throws
	 */
	public List<HashMap<String, Object>> getPjpyInfo(String xh) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		String[] title = { "ѧ��", "ѧ��", "����", "������", "����ʱ��" };
		List<String[]> rs = new ArrayList<String[]>();
		rs.add(title);
		rs.addAll(dao.getHjqkByXh(xh));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "�����");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, rs);
		list.add(map);

		ZcfsDao zcfsDao = new ZcfsDao();
		String[] zcfTitle = { "ѧ��", "ѧ��", "�۲���Ŀ", "�۲����", "����������", "�꼶רҵ����",
				"�༶����" };
		List<String[]> zcfRs = new ArrayList<String[]>();
		zcfRs.add(zcfTitle);
		zcfRs.addAll(zcfsDao.getZcfsByXh(xh));
		HashMap<String, Object> zcfMap = new HashMap<String, Object>();
		zcfMap.put(GlobalsVariable.XSXX_KTEYS_GNMK, "�۲����");
		zcfMap.put(GlobalsVariable.XSXX_KTEYS_CXJG, zcfRs);
		list.add(zcfMap);

		return list;
	}

	/**
	 * ����ƽ����
	 * @param arr ȫ������
	 * @param xsnum С��������λ
	 */
	public String getPjf(String[] arr, int xsnum) {
		BigDecimal sum = new BigDecimal("0");
		for (String num : arr) {
			if(StringUtils.isNotNull(num)){
				sum = sum.add(new BigDecimal(num));
			}
		}
		return sum.divide(new BigDecimal(arr.length), xsnum, BigDecimal.ROUND_HALF_UP).toString();
	}
	/**
	 * ����ƽ����
	 * @param list ȫ������
	 * @param xsnum С��������λ
	 */
	public String getPjf(List<HashMap<String, String>> list, int xsnum) {
		BigDecimal sum = new BigDecimal("0");
		int i = 0;
		for (HashMap<String, String> map : list) {
			if(map.get("kcmc") != null){
				if(map.get("cj") != null){
					sum = sum.add(new BigDecimal(map.get("cj")));
				}
				i++;
			}
		}
		if(i == 0){
			return "";
		}
		return sum.divide(new BigDecimal(i), xsnum, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 *
	 * @����: �ṩ��ѧ����Ϣ��ʾ�����Ľӿ�
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-9-3 ����02:54:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,Object>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPjpyInfoMap(String xh) {
		return dao.getHjqkByXhMap(xh);
	}

	/**
	 *
	 * @����:��ȡ�������list
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPjpyInfoList(String xh) {
		List<HashMap<String, String>> list = dao.getHjqkByXhMap(xh);
		return list;
	}
	public List<HashMap<String, String>> getPjpyInfoList(String xh,String xn) {
		List<HashMap<String, String>> list = dao.getHjqkByXhXnMap(xh,xn);
		return list;
	}

	/**
	 *
	 * @����:�ṩ��ѧ����Ϣ��ʾ�����Ľӿ� �������صǼǱ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-22 ����01:47:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
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
	 * �յ�list
	 */
	public void addBlankList(List<HashMap<String,String>> list, int blankSize){
		for (int i = 0 ; i < blankSize ; i++){
			list.add(new HashMap<String, String>());
		}
	}
	public HashMap<String,String> getDjbZcfListByXhXn(String xh, String xn, String xmmcLike){
		ZcfsService zcfService = new ZcfsService();
		HashMap<String,String> rs = new HashMap<String,String>();
		List<HashMap<String,String>> zcfList = zcfService.getZcfListByXnXh(xh, xn);
		String sxqdycj = ""; // ��ѧ����ѧ��xmmcLike���˳ɼ�
		String xxqdycj = ""; // ��ѧ����ѧ��xmmcLike���˳ɼ�
		String bxncjbjpm = ""; // ��ѧ��ɼ��༶����
		for (HashMap<String, String> zcfMap : zcfList) {
			String xmmc = zcfMap.get("xmmc").trim();
			String xq = zcfMap.get("xq").trim();
			String fjdm = zcfMap.get("fjdm").trim();
			if(xmmc.contains(xmmcLike)){
				if("01".equals(xq)){
					sxqdycj = zcfMap.get("fs");
				}else if("02".equals(xq)){
					xxqdycj = zcfMap.get("fs");
				}
			}
			if(CsszService.XQKG.equals(xq) && "N".equals(fjdm)){
				bxncjbjpm = zcfMap.get("bjpm");
			}
		}
		String xndypjcj = getPjf(new String[]{ sxqdycj, xxqdycj }, 2); // ��ѧ��xmmcLike����ƽ���ɼ�
		rs.put("xndypjcj", xndypjcj);
		rs.put("sxqdycj", sxqdycj);
		rs.put("xxqdycj", xxqdycj);
		rs.put("bxncjbjpm", bxncjbjpm);
		return rs;
	}

	/**
	 * ����ѧ����ѯѧ�������������ѧ����飩
	 */
	public List<HashMap<String, String>> getPjjgGroupByXn(String xh){
		return dao.getPjjgGroupByXn(xh);
	}

	/**
	 *
	 * @����:����ѧ�Ų�ѯ�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-30 ����03:07:04
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkList(String xh) {
		return dao.getHjqkList(xh);
	}

	/**
	 *
	 * @����:��ȡƽ���ɼ�
	 * @���ߣ�HongLin[���ţ�707]
	 * @���ڣ�2013-11-21 ����09:33:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return Integer ��������
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
	 *
	 * @����:����ʦ�� ��ѧ���Ի�֤������ȡ(֤�����������+��Ŀ����+��ˮ�ţ�Ĭ�ϴ�0001��ʼ(���꼶��ѧԺ))
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-3 ����01:38:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return String ��������
	 * @throws
	 */
	public String getZsbm(BzjljgModel model) {
		StringBuffer zsbm = new StringBuffer();
		List<HashMap<String, String>> zsbmList = dao.getZsbm(model);
		if (zsbmList.size() > 0) {
			int zsbh=0;
			zsbh = Integer.parseInt(zsbmList.get(0).get("zsbm"))+1;
			StringBuffer zsbhstr=new StringBuffer();
			if(zsbh>99&&zsbh<1000){
				zsbhstr.append("0").append(zsbh);
			}
			else if(zsbh>9&&zsbh<100){
				zsbhstr.append("00").append(zsbh);
			}
			else{
				zsbhstr.append("000").append(zsbh);
			}
			zsbm.append(model.getXn()).append(model.getXmdm()).append(zsbhstr);
		} else {
			zsbm.append(model.getXn()).append(model.getXmdm()).append("0001");
		}
		return zsbm.toString();
	}
	/**
	 *
	 * @����:������Ŀ���ƻ�ȡ��Ŀ����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-3 ����03:22:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * string ��������
	 * @throws
	 */
	public String getXmdm(BzjljgModel model){
		return dao.getXmdm(model);
	}

	/**
	 * @����: ��ѯ����֤��ģ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-21 ����10:13:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param csdm
	 * @param user
	 * @return
	 * HashMap ��������
	 */
	public HashMap<String, String> cxRyzs(String csdm, User user) throws Exception{
		return dao.cxRyzs(csdm, user);
	}

	/**
	 * @����: ��������֤��ģ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-21 ����10:13:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param csdm
	 * @param csz
	 * @param user
	 * @return
	 * boolean ��������
	 */
	public boolean bcRyzs(String csdm, String csz, User user) throws Exception{
		return dao.bcRyzs(csdm, csz, user);
	}

	/**
	 *
	 * @����:����ѧ�Ų�ѯ�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-25 ����05:21:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Map<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getJxSum (String xh){
		return dao.getJxSum(xh);
	}
	/**
	 * @����: �㽭��ѧ������ܽ����ʾ�����Ի���
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-6-12 ����10:01:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getPjzje (String xh){
		return dao.getPjzje(xh);
	}
	/**
	 *
	 * @����:��ȡ�༶��Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-20 ����03:16:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getBjxx (String bjdm){
		return dao.getBjxx(bjdm);
	}


	/**
	 * @throws Exception
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-22 ����04:05:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * List<File> ��������
	 * @throws
	 */
	public List<File> getRyzsJxjFile_10264(String[] values) throws Exception {

		List<File> files = new ArrayList<File>();

		//��ѯ���
		List<HashMap<String, String>> priJgList = dao.getPriJg(values);
		List<HashMap<String, String>> priJgFzList =null;
		Map<String,List<HashMap<String, String>>> priJgMap = new HashMap<String,List<HashMap<String, String>>>();
		for (HashMap<String, String> hashMap : priJgList) {
			if(priJgMap.containsKey(hashMap.get("xh"))){
				priJgMap.get(hashMap.get("xh")).add(hashMap);
			}else{
				priJgFzList= new ArrayList<HashMap<String, String>>();
				priJgFzList.add(hashMap);
				priJgMap.put(hashMap.get("xh"), priJgFzList);
			}
		}

		for (Map.Entry<String, List<HashMap<String, String>>> entry :priJgMap.entrySet()) {
			File file=getJgFile_10264(entry.getKey(),entry.getValue());

			files.add(file);
		}

		return files;

	}



	/**
	 *
	 * @����:������Ի���ӡ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-22 ����04:46:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @param xmmc
	 * @return
	 * @throws Exception
	 * File ��������
	 * @throws
	 */
	private File getJgFile_10264(String id, List<HashMap<String, String>> xmList)
			throws Exception {

		Map<String,Object> data = new HashMap<String,Object>();
		data.put("mxMap", xmList.get(0));
		data.put("xmList", xmList);
		data.put("date", DateUtils.getLyr());

		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//pjpy", "zm_10264.xml", "֤��" +"["+id+" "+xmList.get(0).get("xm")+"]" );
		return wordFile;

		}

	/**
	 *
	 * @����:ɽ��������ҽְҵѧԺ�������ҵ���Ƽ�����ѧ��������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-6-4 ����06:12:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	private List<HashMap<String, String>> getXsxxlist_12947(String[] values){
		return dao.getXsxxlist(values);
	}

	//ɽ��������ҽְҵѧԺ���Ի���ӡ(��άѧ����ܱ�)
	public File getXmGxhDy_12947_shjxjhzexcel(String[] parameters)
		throws Exception {
	Map<String,Object> data = new HashMap<String,Object>();
	List<HashMap<String, String>> xsxxlist = dao.getShjxjHzbxxList(parameters);
	HashMap<String, String> totalxx = dao.getshjxjTotal(parameters);
	data.put("xsxxlist",xsxxlist);
	data.putAll(totalxx);
	File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//pjpy//excel", "sdxmsy_12947_shjxjhzb.xml", "��άѧ��ѧ�����ܱ�");
	return excelFile;

    }

	//ɽ��������ҽְҵѧԺ���Ի���ӡ(ʡ��־��ѧ����ܱ�)
	public File getXmGxhDy_12947_slzjhzexcel(String[] parameters,User user)
		throws Exception {
	Map<String,Object> data = new HashMap<String,Object>();
	List<HashMap<String, String>> xsxxlist = dao.getShjxjHzbxxList(parameters);
	data.put("xsxxlist",xsxxlist);
	String jbr = user.getRealName();
	String username = user.getUserName();
	String lxdh = (dao.getlxfs(username)).get("lxdh");
    data.put("jbr", jbr);
    data.put("lxdh", lxdh);
    data.put("xx",Base.xxmc);
    SimpleDateFormat datenow = new SimpleDateFormat("yyyy-MM-dd");
    String date = datenow.format(new Date());
    String year = date.substring(0, 4);
    String month = date.substring(5, 7);
    String day = date.substring(8, 10);
    data.put("year", year);
    data.put("month", month);
    data.put("day", day);
    data.put("xn", Base.currXn);
	File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//pjpy//excel", "sdxmsy_12947_slzjhz.xml", "ʡ��־��ѧ��ѧ�����ܱ�");
	return excelFile;

    }
	/**
	 *
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-17 ����08:41:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getSpxxInfo(String guid){
		if(StringUtils.isNull(guid)){
			return null;
		}
		return dao.getSpxxInfo(guid);
	}

	public String getPjxxByXhXnXq(String xh,String xn,String xq){
		return dao.getPjxxByXhXnXq(xh, xn, xq);
	}


	/**
	 * ��ý���Ի� ȡѧ�����޿����
	 */

	public HashMap<String, String> getBxk(String xh,String xn){
		return dao.getBxk(xh, xn);
	}

	public HashMap<String, String> getPm(String xh,String xn){
		return dao.getPm(xh, xn);
	}
	public HashMap<String, String> getCjPm(String xh,String xn){
		return dao.getCjPm(xh, xn);
	}


	/**
	 *
	 * @����: ������Ϣ������ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-11-20 ����04:05:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getPjjgInfo(String xh, String xn, String xq){
		return dao.getPjjgInfo(xh, xn, xq);
	}


	/**
	 *
	 * @����:�Y�����ӌW��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-11-20 ����06:14:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */

	public List<HashMap<String, String>> getZjXs(BzjljgModel model, User user) throws Exception{
		return dao.getZjXs(model, user);
	}

	/**
	 *
	 * @����:����ѧ�Ų�ѯ�ϰ�������Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-1-18 ����11:09:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkListOld(String xh) {
		return dao.getHjqkListOld(xh);
	}

	/**
	 *
	 * @����:�����ȵ���Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-1-21 ����10:15:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getDyddList(String xh) {
		return dao.getDyddList(xh);
	}

	/**
	 *
	 * @����: ������˼�������������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-16 ����08:36:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getAllShyjList(String xh, String xn, String xq, String xmmc) {
		return dao.getAllShyjList(xh, xn, xq, xmmc);
	}
	/**
	 * @����: ������ҵ��ѧѧ��������
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-5-11 ����09:30:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPjjgList(String xh,String xn) {
		return dao.getPjjgList(xh,xn);
	}

	/**
	 *
	 * @����: ȡ��ѧ���������н����̶�ȼ�������Ҫ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-27 ����09:22:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * String ��������
	 * @throws
	 */
	public String getXmmcAllByPjjg(String xh) throws Exception{
		return dao.getXmmcAllByPjjg(xh);
	}

	/**
	 *
	 * @����: ���޿γɼ��༶����ƽ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-6-29 ����03:35:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String, String> getPjfRank(BzjljgModel model, String xq, String kcxz){
		return dao.getPjfRank(model, xq, kcxz);
	}

	/**
	 *
	 * @����:�۲�ƽ���ɼ�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2015-6-29 ����04:55:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param xq
	 * @param kcxz
	 * @return
	 * HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getZcPjfRank(BzjljgModel model){
		HashMap<String, String> map01 =  dao.getZcPjfRank(model, "01");
		HashMap<String, String> map02 =  dao.getZcPjfRank(model, "02");
		map01.put("zcpjf02", map02.get("zcpjf"));
		map01.put("zcpm02", map02.get("zcpm"));
		return map01;
	}

	/**
	 *
	 * @����:��ȡѧ������������map
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-6-29 ����05:14:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String,String> getDjksMap(String xh){
		HashMap<String, String> mapcet4 =  dao.getDjksMap(xh, "CET4","�ļ�");
		HashMap<String, String> mapcet6 =  dao.getDjksMap(xh, "CET6","����");
		mapcet4.put("djksmc6", mapcet6.get("djksmc"));
		mapcet4.put("cj6", mapcet6.get("cj"));
		mapcet4.put("sj6", mapcet6.get("sj"));
		return mapcet4;
	}

	/**
	 *
	 * @����:����ѧ��ȡ����ѧ���ڸ�ѧ��Ļ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-6-30 ����10:32:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPjjgByxn(BzjljgModel model){
		return dao.getPjjgByxn(model);
	}

	/**
	 *
	 * @����:cjbѧ��ѧ��list
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-6-30 ����04:23:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param lx
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXnXqlist(String xh,String lx){
		return dao.getXnXqlist(xh,lx);
	}

	public List<HashMap<String, String>> getJsjdjkslist(String xh,String ksmc,String ksmc1){
		return dao.getJsjdjkslist(xh, ksmc, ksmc1);
	}

	/**
	 *
	 * @����:��ȡĳ����񽱴�������ʱ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2015-6-30 ����04:53:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param ksmc
	 * @param ksmc1
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>>getHjrycssj(String xh,String xmmc){
		return dao.getHjrycssj(xh, xmmc);
	}

	/**
	 *
	 * @����:���޿�ƽ���ɼ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-6-29 ����03:36:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String, String> getZyPjfRank(BzjljgModel model, String kcxz){
		return dao.getZyPjfRank(model, kcxz);
	}

	/**
	 *
	 * @����:��ȡרҵ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-1 ����02:55:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zydm
	 * @return
	 * HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getZyRs(String zydm){
		return dao.getZyRs(zydm);
	}

	public List<HashMap<String, String> > getzzxmjg(String xh,String xn){
		return dao.getzzxmjg(xh, xn);
	}

	/**
	 *
	 * @����:��ȡ�����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-2 ����03:11:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getSqcs(BzjljgModel model){
		return dao.getSqcs(model);
	}

	/**
	 *
	 * @����:��ȡ�����ô���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-2 ����03:18:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getJxcs(BzjljgModel model){
		return dao.getJxcs(model);
	}

	public List<HashMap<String, String>> getXnList(String xh){
		return dao.getXnList(xh);
	}

	/**
	 *
	 * @����:���޿�ƽ���ɼ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-3  ����03:36:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String, String> getNjPjfRank(BzjljgModel model, String nj, String kcxz){
		return dao.getNjPjfRank(model, nj, kcxz);
	}

	/**
	 *
	 * @����:��ȡ�꼶����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-3 ����02:55:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zydm
	 * @return
	 * HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getNjRs(String nj){
		return dao.getNjRs(nj);
	}

	/*
	 * @����: ����ũҵ����ȡ��������
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-10-26 ����10:00:19
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
	 * @������ͨ��sql������ȡ����n������Ϣ������n�����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��6��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param n
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getHjqkByXhMap(String xh,int n) {
		List<HashMap<String, String>> list = dao.getHjqkByXhMap(xh,String.valueOf(n));
		int m=n-list.size();
		for (int i = 0; i <m; i++) {
			list.add(new HashMap<String, String>());
		}
		return list;
	}

	/**
	 * @�������������ܱ�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��24��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * File ��������
	 */
	public File getHzbPrint(SearchModel searchmodel)throws Exception {
		//���ұ�����һ��xn��һ��xmmc
		String[] xns=searchmodel.getSearch_tj_xn();
		String[] xmmcs=searchmodel.getSearch_tj_xmmc();
		String xn=(xns==null?"":xns[0]);
		String xmmc=(xmmcs==null?"":xmmcs[0]);
		List<HashMap<String,String>> wshlist=dao.getPjjgList_10279(xn,xmmc);
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("wshlist", wshlist);
		data.put("xn", xn);
		data.put("xmmc", xmmc);
		File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//pjpy//excel", "pjpy_hzb_"+"10279"+".xml", xn+"ѧ����ܱ�");
		return excelFile;
	}

	public List<HashMap<String, String>> getPjjghzList(BzjljgModel model, User user)  throws Exception{
		return dao.getPjjghzList(model,user);
	}



	public List<HashMap<String, String>> getPjjghzMdList(BzjljgModel model)  throws Exception{
		return dao.getPjjghzMdList(model);
	}
	/**
	 * @������ͨ��sql������ȡ��4������Ϣ
	 * @���ߣ�����[����:1529]
	 * @���ڣ�2017��9��21��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param n
	 * @return
	 * List<HashMap<String,String>> ��������
	 * */
	//���ҽ�ѧ��
	public HashMap<String, String> getZjlyByXhMap(String xh,String xn) {
		return dao.getZjlyByXhMap(xh,xn);
	}
	//ȡ����
	public HashMap<String, String> getZjlyByPm(String xh,String xn){
		return dao.getZjlyByPm(xh, xn);
	}
	//��־��ѧ��
	public HashMap<String, String> getZjlylzByXhMap(String xh,String xn){
		return dao.getZjlylzByXhMap(xh, xn);
	}
	//ȡѧϰ�ɼ�
	public HashMap<String, String> getZjlyXxqkCj(String xh,String xn){
		return dao.getZjlyXxqkCj(xh, xn);
	}
	//�㽭ʡ������ѧ��
	public HashMap<String, String> getZjszfByXhMap(String xh,String xn){
		return dao.getZjszfByXhMap(xh, xn);
	}
	//�㽭����ְҵѧԺ��ѧ��
	public HashMap<String, String> getZjlyzyxyfByXhMap(String xh,String xn){
		return dao.getZjlyzyxyfByXhMap(xh, xn);
	}
	//�㽭ʡ�����ҵ��
	public HashMap<String, String> getZjlySjyxbys(String xh,String xn){
		return dao.getZjlySjyxbys(xh, xn);
	}
	//�㽭����ѧԺ�����ҵ��
	public HashMap<String, String> getZjlyxyyxbys(String xh,String xn){
		boolean flag = dao.isExist(xh,xn);
		if (flag) {
			return dao.getZjlyxyyxbys(xh, xn);
		}else {
			return dao.getZjlySjyxbys(xh, xn);
		}

	}

	//�㽭��ҽҩ ��ý�ѧ��ȼ�
    public String getJxjmcByXhXn(String xh, String xn) {
		return dao.getJxjmcByXhXn(xh,xn);
    }

    /**
	 * @����: �ൺ����ѧԺ���Ի�ȡ-ѧ���ۺϳɼ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-16 ����06:25:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getXnzhcj(String xh,String xn){
		return dao.getXnzhcj(xh,xn);
	}

	/**
	 * @����: ����ѧ��ȡ��ǰ�������ڵĲ����༶����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-18 ����07:05:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * String ��������
	 * @throws
	 */
	public String getXsszcpbjRsForxh (String xh,String xn){
		return dao.getXsszcpbjRsForxh(xh,xn);
	}

	/**
	 * @����: �����г��Ľ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-11-3 ����08:41:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * String ��������
	 * @throws
	 */
	public String getExcleZje (BzjljgModel model) throws Exception{
		return dao.getExcleZje(model);
	}
	/**
	 * @������ͨ��sql������ȥ�������еȼ���γ�����
	 * @���ߣ�����[����:1529]
	 * @���ڣ�2017��11��9��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param n
	 * @return
	 * List<HashMap<String,String>> ��������
	 * */
	//�����Ƽ���ѧ��ѧ������
	public List<HashMap<String, String>> getHjqk(String xh,String xn){
		return dao.getHjqk(xh, xn);
	}
	//�����Ƽ���ѧ�������м��Լ����޿�����
	public HashMap<String,String> getXakjdxylzjbxkms(String xh,String xn){
		return dao.getXakjdxylzjbxkms(xh, xn);
	}
	/**
	 *
	 * @����:��ȡ�ɼ�List
	 * @���ߣ�����[���ţ�1529]
	 * @���ڣ�2017-11-17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getCjsxqList(String xn, String xh){
		return dao.getCjsxqList(xn,xh);
	}
	public List<HashMap<String, String>> getCjxsqList(String xn, String xh){
		return dao.getCjxsqList(xn,xh);
	}
	//����ҽҩ
	public String getkssj (String xh) throws Exception{
		return dao.getkssj(xh);
	}
	public String gettyrs (String cpbjdm) throws Exception{
		return dao.gettyrs(cpbjdm);
	}

	public List<HashMap<String, String>> getHjqkInfoMap(String xh) {
		return dao.getHjqkInfoMap(xh);
	}
	public HashMap<String,String> getCjfsList(String xh, String xn){
		return dao.getCjfsList(xh,xn);
	}
	//���칤�̴�ѧ
	public List<HashMap<String, String>> getShrList(String xh, String xn, String xq, String xmdm) {
		return dao.getShrList(xh, xn, xq, xmdm);
	}

	/**
	 * @description	�� ��ȡ��������word
	 * @author 		�� ������1282��
	 * @date 		��2017-12-27 ����02:54:07
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public File createBzmdWord(BzjljgModel model) throws Exception{
		String path = System.getProperty("java.io.tmpdir");

		File file = new File(path+File.separator+"��������.doc");

		Calendar calendar = Calendar.getInstance();

		int year = calendar.get(Calendar.YEAR);

		int month = calendar.get(Calendar.MONTH) + 1;


		if(!file.exists()){
			file.createNewFile();
		}

		//��ȡȫ�����������б�
		List<HashMap<String,String>> bzmdList = dao.getBzmdList(model);
		//��ȡѧԺ�������б�
		List<HashMap<String,String>> xyhjrsList = dao.getXyhjrsList(model);
		//��ȡ�༶�������б�
		List<HashMap<String,String>> bjhjrsList = dao.getBjhjrsList(model);

		if(bzmdList != null && bzmdList.size() > 0){
			String xn = xyhjrsList.get(0).get("xn");
			String xqmc = xyhjrsList.get(0).get("xqmc");

			List<HashMap<String,Object>> list = getBzmdByXy(xyhjrsList, bjhjrsList);
			Document document = new Document(PageSize.A4);
			RtfWriter2.getInstance(document, new FileOutputStream(file));
			//������������
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			//���ñ�������
			Font titleFont = new Font(bfChinese,32,Font.BOLD);
			//����ѧУ��������
			Font xxmcFont = new Font(bfChinese,20,Font.BOLD);
			//������������
			Font bodyFont = new Font(bfChinese,14,Font.NORMAL);

			document.open();

			Paragraph blankParagraph = new Paragraph("");

			document.add(blankParagraph);

			//����ѧУ��
			Paragraph xxParagraph = new Paragraph(Base.xxmc, xxmcFont);

			xxParagraph.setAlignment(Element.ALIGN_CENTER);

			document.add(xxParagraph);

			document.add(blankParagraph);

			Paragraph xnxqParagraph = new Paragraph(xn+" ѧ��"+xqmc,titleFont);

			xnxqParagraph.setAlignment(Element.ALIGN_CENTER);

			document.add(xnxqParagraph);

			document.add(blankParagraph);

			Paragraph bzmdParagraph_1 = new Paragraph("��",titleFont);

			bzmdParagraph_1.setAlignment(Element.ALIGN_CENTER);

			document.add(bzmdParagraph_1);

			document.add(blankParagraph);

			Paragraph bzmdParagraph_2 = new Paragraph("��",titleFont);

			bzmdParagraph_2.setAlignment(Element.ALIGN_CENTER);

			document.add(bzmdParagraph_2);

			document.add(blankParagraph);

			Paragraph bzmdParagraph_3 = new Paragraph("��",titleFont);

			bzmdParagraph_3.setAlignment(Element.ALIGN_CENTER);

			document.add(bzmdParagraph_3);

			document.add(blankParagraph);

			Paragraph bzmdParagraph_4 = new Paragraph("��",titleFont);

			bzmdParagraph_4.setAlignment(Element.ALIGN_CENTER);

			document.add(bzmdParagraph_4);


			if(list.size() > 1){
				document.add(blankParagraph);
				document.add(blankParagraph);
				document.add(blankParagraph);
				Paragraph bzmdParagraph_date = new Paragraph(year+"��"+month+"��",xxmcFont);
				bzmdParagraph_date.setAlignment(Element.ALIGN_CENTER);
				document.add(bzmdParagraph_date);
			}

			document.add(blankParagraph);



			for(int i = 0;i < 3;i++){
				document.add(blankParagraph);
			}

			if(list.size() == 1){
				String xymc = (String) list.get(0).get("xymc");
				Paragraph xymc_paragraph = new Paragraph(xymc,xxmcFont);
				xymc_paragraph.setAlignment(Element.ALIGN_CENTER);
				xymc_paragraph.setSpacingAfter(12);
				document.add(xymc_paragraph);

				//document.add(blankParagraph);
				Paragraph bzmdParagraph_date = new Paragraph(year+"��"+month+"��",xxmcFont);
				bzmdParagraph_date.setAlignment(Element.ALIGN_CENTER);
				document.add(bzmdParagraph_date);
			}

			Font hzFont = new Font(bfChinese,26,Font.BOLD);

			for(int i = 0;i < list.size();i++){
				//ÿ��ѧԺ������һҳ
				document.newPage();

				//���ѧԺ��ͷ
				HashMap<String,Object> map = list.get(i);
				String xymc = (String) map.get("xymc");
				Paragraph xyPara = new Paragraph(xymc + "����ѧ������",hzFont);
				xyPara.setAlignment(Element.ALIGN_CENTER);
				xyPara.setSpacingAfter(12);
				document.add(xyPara);

				//��������б�
				List<HashMap<String,String>> jxList = (List<HashMap<String, String>>) map.get("xyhjList");

				//List<HashMap<String,String>> bjhjList = (List<HashMap<String, String>>) map.get("bjhjList");

				for(HashMap<String,String> mapp : jxList){
					Paragraph jxParagraph = new Paragraph(mapp.get("xmmc")+":"+mapp.get("rs")+"��",xxmcFont);
					jxParagraph.setIndentationLeft(5f);
					jxParagraph.setSpacingAfter(8);
					jxParagraph.setSpacingBefore(8);
					document.add(jxParagraph);
				}

				document.add(blankParagraph);

				Font topicFont = new Font(bfChinese,16,Font.BOLD);

				Paragraph topicXnXqPara = new Paragraph(xn.replaceAll("-", "/") + "ѧ��" + xqmc,topicFont);
				topicXnXqPara.setAlignment(Element.ALIGN_CENTER);
				topicXnXqPara.setSpacingAfter(8);
				topicXnXqPara.setSpacingBefore(8);
				document.add(topicXnXqPara);



				Paragraph xytjPara = new Paragraph(xymc + "ѧ���������ͳ������",topicFont);
				xytjPara.setAlignment(Element.ALIGN_CENTER);
				xytjPara.setSpacingAfter(8);
				xytjPara.setSpacingBefore(8);
				document.add(xytjPara);
				//document.add(blankParagraph);

				for(HashMap<String,String> mapp : jxList){
					document.add(blankParagraph);
					String xmmc = mapp.get("xmmc");
					Paragraph jxParagraph = new Paragraph(xmmc+" ("+mapp.get("rs")+"��)��",bodyFont);
					jxParagraph.setIndentationLeft(5f);
					jxParagraph.setSpacingBefore(4);
					jxParagraph.setSpacingAfter(4);
					document.add(jxParagraph);

					Iterator<HashMap<String,String>> bjIt = bzmdList.iterator();

					StringBuilder md = new StringBuilder();
					String bjmc = new String();
					int num = 0;
					List<HashMap<String,String>> bdList = new ArrayList<HashMap<String,String>>();
					while(bjIt.hasNext()){
						HashMap<String,String> mdMap = (HashMap<String, String>) bjIt.next();
						if(xymc.equals(mdMap.get("xymc"))){
							if(xmmc.equals(mdMap.get("xmmc"))){
								if(StringUtils.isNull(bjmc)){
									bjmc = mdMap.get("bjmc");
									if(bjmc.equals(mdMap.get("bjmc"))){
										md.append(mdMap.get("xm"));
										md.append(" ");
										num++;
										bjIt.remove();
									}else{
										bdList.add(mdMap);
										bjIt.remove();
										continue;
									}
								}else{
									if(!bjmc.equals(mdMap.get("bjmc"))){
										bdList.add(mdMap);
										bjIt.remove();
										continue;
									}else{
										md.append(mdMap.get("xm"));
										md.append(" ");
										num++;
										bjIt.remove();
									}
								}
							}else{
								break;
							}
						}else{
							break;
						}
					}
					//��Ӱ༶����
					Paragraph bdParagraph = new Paragraph(bjmc+": "+md.toString()+" "+num,bodyFont);
					bdParagraph.setIndentationLeft(5f);
					bdParagraph.setSpacingBefore(4);
					bdParagraph.setSpacingAfter(4);
					document.add(bdParagraph);

					while(bdList.size() > 0){
						num = 0;
						md = new StringBuilder();
						while(bdList.size() > 0){
							Iterator<HashMap<String,String>> it = bdList.iterator();
							HashMap<String,String> mdMap = (HashMap<String, String>) it.next();
							bjmc = new String();
							if(StringUtils.isNull(bjmc)){
								bjmc = mdMap.get("bjmc");
								if(bjmc.equals(mdMap.get("bjmc"))){
									md.append(mdMap.get("xm"));
									md.append(" ");
									num++;
									it.remove();
								}else{
									break;
								}
							}
						}
						//��Ӱ༶����
						Paragraph bddParagraph = new Paragraph(bjmc+": "+md.toString()+" "+num,bodyFont);
						bddParagraph.setIndentationLeft(5f);
						bddParagraph.setSpacingBefore(4);
						bddParagraph.setSpacingAfter(4);
						document.add(bddParagraph);
					}
				}
			}

			document.close();
		}
		return file;
	}

	/**
	 * @description	����ȡ��������������ѧԺ�ͽ�����ࣩ
	 * @author 		�� ������1282��
	 * @date 		��2017-12-28 ����09:28:35
	 * @param bzmdList
	 * @param xyhjrsList
	 * @param bjhjrsList
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,Object>> getBzmdByXy(List<HashMap<String,String>> xyhjrsList,List<HashMap<String,String>> bjhjrsList) throws Exception{
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> map = new HashMap<String, Object>();
		List<HashMap<String, String>> xyhjlist = new ArrayList<HashMap<String,String>>();

		for(int i = xyhjrsList.size();i>0;){
			String xydm = null;
			String xymc = null;
			//String bj_xmmc = null;
			//String bjmc = null;
			//ÿ��ѭ����ʼ����newһ��ѧԺ��map
			map = new HashMap<String, Object>();
			//ÿ��ѭ����ʼ����newѧԺ����ϸ�б��б�
			xyhjlist = new ArrayList<HashMap<String,String>>();
			Iterator<HashMap<String,String>> xyit = xyhjrsList.iterator();
			while(xyit.hasNext()){
				HashMap<String,String> xyMap = xyit.next();
				if(xydm == null){
					xydm = xyMap.get("xydm");
					xymc = xyMap.get("xymc");
					map.put("xymc", xymc);
				}
				if(xydm.equals(xyMap.get("xydm"))){
					//ȡ��ѧԺ�Ľ�������
					String xmmc = xyMap.get("xmmc");
					//ȡ��ѧԺ�Ļ�����
					String rs = xyMap.get("xyhjrs");
					HashMap<String,String> mapp = new HashMap<String, String>();
					//���뽱������
					mapp.put("xmmc", xmmc);
					//���뽱���Ӧ�Ļ�����
					mapp.put("rs", rs);
					xyhjlist.add(mapp);
					xyit.remove();
					i--;
				}
			}
			map.put("xyhjList", xyhjlist);
			list.add(map);
		}
		return list;

	}

	/**
	 * @description	�� �ܷ񵼳���������
	 * @author 		�� ������1282��
	 * @date 		��2018-1-4 ����02:25:35
	 * @return
	 * @throws Exception
	 */
	public boolean isExportBzMd(BzjljgModel model) throws Exception{
		List<HashMap<String,String>> bzmdList = dao.getBzmdList(model);
		return null != bzmdList && bzmdList.size() > 0 ? true:false;
	}

	/**
	 *
	 * @����: ��ѧ������˺�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-2-24 ����04:02:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File ��������
	 * @throws
	 */
	public File getJxjhzzhList(BzjljgModel model, User user) throws Exception{

		String path = System.getProperty("java.io.tmpdir");
		String[] tj_xn = model.getSearchModel().getSearch_tj_xn();
		String[] tj_xq = model.getSearchModel().getSearch_tj_xq();
		String xqmc = Base.getXqmcForXqdm(tj_xq[0]);
		File file = new File(path+File.separator+"��ѧ���˺Ż��ܱ�.xls");

		if(!file.exists()){
			file.createNewFile();
		}
		WritableWorkbook  wwb = Workbook.createWorkbook(file);
		  // ����������
        WritableSheet ws = wwb.createSheet("��ѧ���˺Ż��ܱ�", 0);
        //��ͷ
        WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 13,
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
                jxl.format.Colour.BLACK); // �����ʽ ���� �»��� б�� ���� ��ɫ
        WritableCellFormat wcf_title1 = new WritableCellFormat(wf_title); // ��Ԫ����
        wcf_title1.setAlignment(jxl.format.Alignment.CENTRE); // ���ö��뷽ʽ
        wcf_title1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //����
        Label labelTitle= new Label(0, 0, tj_xn[0]+"ѧ��"+xqmc+"��ѧ���˺Ż��ܱ�",wcf_title1);
        ws.addCell(labelTitle);
        //�ϲ���ͷ
        ws.mergeCells(0, 0, 8, 0);

        //�������
        WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 11,
        WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
        jxl.format.Colour.BLACK); // �����ʽ ���� �»��� б�� ���� ��ɫ
        WritableCellFormat wcf_table = new WritableCellFormat(wf_table); // ��Ԫ����
        wcf_table.setAlignment(jxl.format.Alignment.CENTRE); // ���ö��뷽ʽ
        wcf_table.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //����

        Label labelXuh = new Label(0,1,"���",wcf_table);
        Label labelXh = new Label(1, 1,"ѧ��",wcf_table);
        Label labelSfzh = new Label(2, 1,"���֤��",wcf_table);
        Label labelXm = new Label(3, 1,"����",wcf_table);
        Label labelBj = new Label(4, 1,"�༶",wcf_table);
        Label labelHjdj = new Label(5, 1,"�񽱵ȼ�",wcf_table);
        Label labelJe = new Label(6, 1,"���",wcf_table);
        Label labelBz = new Label(7, 1,"��ע",wcf_table);
        Label labelQr = new Label(8, 1,"ȷ��",wcf_table);

        ws.addCell(labelXuh);
        ws.addCell(labelXh);
        ws.addCell(labelSfzh);
        ws.addCell(labelXm);
        ws.addCell(labelBj);
        ws.addCell(labelHjdj);
        ws.addCell(labelJe);
        ws.addCell(labelBz);
        ws.addCell(labelQr);

        // �����еĿ��
        ws.setColumnView(0, 8);
        ws.setColumnView(1, 15);
        ws.setColumnView(2, 25);
        ws.setColumnView(3, 10);
        ws.setColumnView(4, 15);
        ws.setColumnView(5, 15);
        ws.setColumnView(6, 10);
        ws.setColumnView(7, 10);
        ws.setColumnView(8, 10);

      //��ȡ����
		List<HashMap<String,String>> pjjgList = dao.getJxjhzzhList(model, user);
		if(pjjgList != null && !pjjgList.isEmpty() && pjjgList.get(0) !=  null){
			for (int i = 0; i < pjjgList.size(); i++) {
				HashMap<String,String> tempMap = pjjgList.get(i);
				Label labelTemp0 = new Label(0, i+2,tempMap.get("rn"),wcf_table);
				Label labelTemp1 = new Label(1, i+2,tempMap.get("xh"),wcf_table);
				Label labelTemp2 = new Label(2, i+2,tempMap.get("sfzh"),wcf_table);
				Label labelTemp3 = new Label(3, i+2,tempMap.get("xm"),wcf_table);
				Label labelTemp4 = new Label(4, i+2,tempMap.get("bjmc"),wcf_table);
				Label labelTemp5 = new Label(5, i+2,tempMap.get("xmmc"),wcf_table);
				Label labelTemp6 = new Label(6, i+2,tempMap.get("xmje"),wcf_table);
				Label labelTemp7 = new Label(7, i+2,"",wcf_table);//tempMap.get("sqly")
				Label labelTemp8= new Label(8, i+2,"",wcf_table);
				ws.addCell(labelTemp0);
			    ws.addCell(labelTemp1);
			    ws.addCell(labelTemp2);
			    ws.addCell(labelTemp3);
			    ws.addCell(labelTemp4);
			    ws.addCell(labelTemp5);
			    ws.addCell(labelTemp6);
			    ws.addCell(labelTemp7);
			    ws.addCell(labelTemp8);
			}

		}
		  wwb.write();
		  wwb.close();
		  return file;

	}

	/**
	 * @throws Exception
	 * @throws IOException
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-3-9 ����02:27:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param resultList
	 * @return
	 * File ��������
	 * @throws
	 */
	public File getCwybFile(List<HashMap<String, String>> resultList,HashMap<String, String> hashMap) throws IOException, Exception {
		//��Excel
		String fileName = System.currentTimeMillis() + ".xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);


		if(!file.exists()){
			file.createNewFile();
		}

		//����������
		WritableWorkbook  wwb = Workbook.createWorkbook(file);

		//������ظ�ʽ
		WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�������
		WritableFont headFont = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�ͷ����
		WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//������������


		WritableCellFormat title_cf = new WritableCellFormat(tileFont);//���ñ��ⵥԪ������
		WritableCellFormat head_cf = new WritableCellFormat(headFont);//�������ı�ͷ����
		WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������
		WritableCellFormat body_bz_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������
		WritableCellFormat body_sp_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������������
		WritableCellFormat body_hj_cf = new WritableCellFormat(headFont);//�������ĵ�Ԫ�񣨺ϼƣ�����
		WritableCellFormat body_rq_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ�����ڣ�����
		body_cf.setWrap(true);
		body_rq_cf.setAlignment(jxl.format.Alignment.LEFT);
		title_cf.setAlignment(jxl.format.Alignment.CENTRE);//���ñ��ⵥԪ�����
		title_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//		title_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//���ñ��ⵥԪ��߿�
//		title_cf.setBackground(Colour.YELLOW);	//���ñ��ⱳ��ɫ
		body_hj_cf.setAlignment(jxl.format.Alignment.LEFT);
		body_hj_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//���ô�ֱ����
		body_hj_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		head_cf.setAlignment(jxl.format.Alignment.CENTRE);
		head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//���ñ�ͷˮƽ����
		head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		body_sp_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//����ˮƽ����
		body_sp_cf.setAlignment(jxl.format.Alignment.CENTRE);

		body_cf.setAlignment(jxl.format.Alignment.CENTRE);//�������ĵ�Ԫ�����
		body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//����ˮƽ����
		body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//�������ĵ�Ԫ��߿�
		body_bz_cf.setAlignment(jxl.format.Alignment.LEFT);
		body_bz_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			//List<Map<String,String>> list = iterator.next();	//�༶��Ӧ�����ѧ���б�
			String xmmc = hashMap.get("xmmc");	//�༶����
			String xn = hashMap.get("xn");		//ѧ��
			String title = xn+"����ͨ�ߵ�ѧУ"+xmmc+"���Ų����ñ�";	//����
			if(StringUtils.isNull(xmmc)) xmmc = "δ֪��Ŀ";

			//����������
			WritableSheet sheet = wwb.createSheet(xmmc, 0);
			//���ø����п�
			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 15);
			sheet.setColumnView(2, 25);
			sheet.setColumnView(3, 15);
			sheet.setColumnView(4, 25);
			sheet.setColumnView(5, 15);
			sheet.setColumnView(6, 10);
			sheet.setRowView(3, 500, false);

			//�ϲ���Ԫ��
			//��һ��������Ҫ�ϲ��ĵ�Ԫ�������Ͻǵ��кţ�
			//�ڶ���������Ҫ�ϲ��ĵ�Ԫ�������Ͻǵ��кţ�
			//������������Ҫ�ϲ��ĵ�Ԫ�����ҽǵ��кţ�
			//���ĸ�������Ҫ�ϲ��ĵ�Ԫ�������½ǵ���
			sheet.mergeCells(0, 0, 6, 2);	//xx����ͨ�ߵ�ѧУxx���Ų����ñ�
			//�������⼰��ͷ
			Label t_0_0 = new Label(0, 0,title,title_cf);
			Label h_0_2 = new Label(0,3,"���",head_cf);
			Label h_1_2 = new Label(1,3,"ѧ������",head_cf);
			Label h_2_2 = new Label(2,3,"רҵ",head_cf);
			Label h_3_2 = new Label(3,3,"ѧ��",head_cf);
			Label h_3_3 = new Label(4,3,"���֤��",head_cf);
			Label h_4_3 = new Label(5,3,"��Ԫ��",head_cf);
			Label h_5_3 = new Label(6,3,"��ע",head_cf);

			sheet.addCell(t_0_0);
			sheet.addCell(h_0_2);
			sheet.addCell(h_1_2);
			sheet.addCell(h_2_2);
			sheet.addCell(h_3_2);
			sheet.addCell(h_3_3);
			sheet.addCell(h_4_3);
			sheet.addCell(h_5_3);

			//����������Ԫ��
			int size = resultList.size();
			if(size>0){
				for(int j=0;j<size;j++){
					Map<String,String> map = resultList.get(j);
					Label xuhao = new Label(0, j+4,""+(j+1)+"", body_cf);	//���
					Label xm = new Label(1, j+4, map.get("xm"), body_cf);		//ѧ������
					Label zy = new Label(2, j+4, map.get("zymc"), body_cf);		//רҵ
					Label xh = new Label(3, j+4, map.get("xh"), body_cf);		//ѧ��
					Label sfzh = new Label(4, j+4, map.get("sfzh"), body_cf);		//���֤��
					Label je = new Label(5, j+4, map.get("xmje"), body_cf);		//��Ԫ��
					Label bz = new Label(6, j+4, map.get("bz"), body_cf);		//��ע
					sheet.addCell(xuhao);
					sheet.addCell(xm);
					sheet.addCell(zy);
					sheet.addCell(xh);
					sheet.addCell(sfzh);
					sheet.addCell(je);
					sheet.addCell(bz);
					sheet.setRowView(j+4, 500, false);
				}
				sheet.mergeCells(0, size+4, 2, size+4);//�ϼƣ�Сд������
				sheet.mergeCells(3, size+4, 6, size+4);//�ϼƣ�Сд��ֵ
				sheet.mergeCells(0, size+5, 2, size+5);//�ϼƣ���д������
				sheet.mergeCells(3, size+5, 6, size+5);//�ϼƣ���д��ֵ
				sheet.mergeCells(0, size+8, 3, size+8);//����
				sheet.mergeCells(0, size+12, 3, size+12);//���
				sheet.mergeCells(0, size+15, 3, size+15);//�Ʊ�
				sheet.mergeCells(0, size+16, 3, size+16);//����
				Label hj_xx = new Label(0,size+4,"�ϼƣ�Сд��",head_cf);
				Label hj_dx = new Label(0,size+5,"�ϼƣ���д��",head_cf);
				if(hashMap.get("hjje") != null && !"".equals(hashMap.get("hjje"))){
					double d = Double.parseDouble(hashMap.get("hjje"));
					DecimalFormat df = new DecimalFormat("#,##0.00");
					hashMap.put("hjje_1", "��"+String.valueOf(df.format(d)));
				}else{
					hashMap.put("hjje_1", "");
				}

				Label hjxx = new Label(3,size+4,hashMap.get("hjje_1"),body_hj_cf);
				Label hjdx = new Label(3,size+5,MoneyFormat.format(hashMap.get("hjje")),body_hj_cf);
				Label sp = new Label(0,size+8,"Ժ��������                                         �ֹ��쵼������",body_sp_cf);
				Label sh = new Label(0,size+12,"��ˣ�                                                 �������ܣ�",body_sp_cf);
				Label zb = new Label(0,size+15,"�Ʊ�",body_rq_cf);
				Label rq = new Label(0,size+16,"���ڣ�",body_rq_cf);

				sheet.addCell(hj_xx);
				sheet.addCell(hj_dx);
				sheet.addCell(hjxx);
				sheet.addCell(hjdx);
				sheet.addCell(sp);
				sheet.addCell(sh);
				sheet.addCell(zb);
				sheet.addCell(rq);

				sheet.setRowView(size+4, 400, false);
				sheet.setRowView(size+5, 400, false);
			}

		//�������Ϊ��
		if(resultList==null||resultList.size()==0){
			//����������
			WritableSheet sheetNull = wwb.createSheet("���ε�������Ϊ��", 0);
			sheetNull.setColumnView(0, 15);
			Label msg = new Label(0, 0,"�������ݣ�");
			sheetNull.addCell(msg);
		}

		wwb.write();
		wwb.close();

		return file;
	}

	/**
	 * @throws Exception
	 * @����:��ȡ�����ñ�������Ϣ(��������ְҵ����ѧԺ)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-3-9 ����03:20:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getCwybList(BzjljgModel model, User user) throws Exception {
		return dao.getCwybList(model, user);
	}
	public String getCwybSum(BzjljgModel model, User user) throws Exception {
		return dao.getCwybSum(model, user);
	}
	
	/**
	 * 
	 * @����: ����ϢList
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-21 ����05:19:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param n
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getHjxxList(String xh,String xn,String xq,String n){
		List<HashMap<String,String>> hjxxList = dao.getHjxxList(xh, xn, xq, n);
		int m = hjxxList == null ? 0 : hjxxList.size();
		for (int i = 0; i < Integer.parseInt(n)-m; i++) {
			hjxxList.add(new HashMap<String, String>());
		}
		return hjxxList;
	}
	
	/**
	 * 
	 * @����: ��ȡ���ʱ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-21 ����05:49:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String,String> getHjshsjList(String xmdm,String days){
		return dao.getHjshsjList(xmdm, days);
		
	}

	public String getXymcBydm(String xydm) {
		return dao.getXymcBydm(xydm);
	}
	
	//�й���Ժ ѧ�����β���Υ����Ϣ
	public HashMap<String,String> getKkbkxx(String xh){
		return dao.getKkbkxx(xh);
	}
}
