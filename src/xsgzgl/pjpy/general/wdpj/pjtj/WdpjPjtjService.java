package xsgzgl.pjpy.general.wdpj.pjtj;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Globals;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.pjpy.general.inter.wdpj.WdpjPjtjInterface;
import xsgzgl.pjpy.general.pjsz.bjdl.PjszBjdlService;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_��������_ͨ��_Service��
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

public class WdpjPjtjService extends CommService implements WdpjPjtjInterface {

	WdpjPjtjDAO dao = new WdpjPjtjDAO();

	// ===============�ṩ������˿��� begin===============================
	/**
	 * ��������ʸ�
	 * 
	 * @author ΰ�����
	 */
	public String checkSqzg(PjpyWdpjModel model, User user) throws Exception {

		// ѧ��
		String xh = model.getXh();
		// ��Ŀ����
		String xmdm = model.getXmdm();

		// ������ĿService
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);

		// ��ʾ��Ϣ
		String message = "";
		// ========== ���ѧ���Ƿ�����Ա����begin =====================

		message = checkRyk(xh);
		if (!Base.isNull(message)) {
			return message;
		}
		// ========== ���ѧ���Ƿ�����Ա����end========================
		
		
		// ==================�������ʱ��begin========================

		// ��Ŀʱ����Ϣ
		HashMap<String, String> xmsjInfo = dao.getXmsjInfo(xmdm);

		message = checkSqsj(xmsjInfo);
		if (!Base.isNull(message)) {
			return message;
		}
		// ==================�������ʱ��end========================

		// ==================�����������begin========================
		// ���������б�
		List<HashMap<String, String>> pjtjList = getPjtjList(model);

		message = checkSqtj(xh, xmdm, pjtjList);
		if (!Base.isNull(message)) {
			return message;
		}
		// ==================�����������end========================

		// ==================����������� begin========================
		// ��������
		String rssz = pjxmModel.getRssz();
		// ��������
		String rskz = pjxmModel.getRskz();

		if ("yes".equalsIgnoreCase(rssz) && "sqsb".equalsIgnoreCase(rskz)) {
			message = checkRskz(xh, model, pjxmModel);

			if (!Base.isNull(message)) {
				return message;
			}
		}
		// ==================�����������end========================

		// ==================�����Ŀ���begin========================
		// ��ÿ���
		String jdkz = pjxmModel.getJdkz();

		if ("sqsb".equalsIgnoreCase(jdkz) || Base.isNull(jdkz)) {
			message = checkXmjd(xh, model);
			if (!Base.isNull(message)) {
				return message;
			}
		}
		// ==================�����Ŀ���end========================

		return message;
	}

	/**
	 * �������ʸ�
	 * 
	 * @author ΰ�����
	 */
	public String checkShzg(PjpyWdpjModel model, User user) throws Exception {

		// ѧ��
		String[] xh = model.getSqr();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��˸�λID
		String gwid = model.getGwid();

		// ������ĿService
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);

		// ���������Ϣ
		List<HashMap<String, String>> bshrList = dao.getBshrList(xh);
		
		// ��ʾ��Ϣ
		String message = "";

		// ==================����������� begin========================
		// ��������
		String rssz = pjxmModel.getRssz();
		// ��������
		String rskz = pjxmModel.getRskz();

		if ("yes".equalsIgnoreCase(rssz) && gwid.equalsIgnoreCase(rskz)) {
			message = checkRskz(xh, bshrList, model, pjxmModel);
			if (!Base.isNull(message)) {
				return message;
			}
		}
		// ==================�����������end========================

		// ==================�����Ŀ���begin========================
		// ��ÿ���
		String jdkz = pjxmModel.getJdkz();

		if (gwid.equalsIgnoreCase(jdkz)) {
			message = checkXmjd(xh, bshrList, model, pjxmModel);
			if (!Base.isNull(message)) {
				return message;
			}
		}
		// ==================�����Ŀ���end========================

		//message = "1";
		return message;
	}
	// ===============�ṩ������˿��� end===============================

	
	/**
	 * ���ѧ���Ƿ�����Ա����
	 * 
	 * @author qlj
	 */
	private String checkRyk(String xh) {

		StringBuilder message=new StringBuilder();
		
		HashMap<String,String>rykMap=dao.checkRyk(xh);

		if (rykMap!=null && rykMap.size()>0) {// ָ��ѧ������Ա����
			
		} else {
			message.append("�Բ�����<font color=\"blue\">û���������Ų����ʸ�</font>�����������������Ա��ϵ��");
		}

		return message.toString();
	}
	
	// ===============ʱ���� begin===============================
	/**
	 * �������ʱ��
	 * 
	 * @author ΰ�����
	 */
	private String checkSqsj(HashMap<String, String> xmsjInfo) {

		// ��ʾ��Ϣ
		StringBuilder message = new StringBuilder();
		// Ŀǰʱ��
		String nowtime = xmsjInfo.get("nowtime");
		// ��ʼʱ��
		String kssj = xmsjInfo.get("sqkssj");
		// ����ʱ��
		String jssj = xmsjInfo.get("sqjssj");
		// ���ƿ���
		String kzkg = xmsjInfo.get("sqkzkg");
		// ��ע
		String bz = xmsjInfo.get("bz");

		if (!Base.isNull(kssj)
				&& (Integer.parseInt(kssj) > Integer.parseInt(nowtime))) {// ��ǰʱ���뿪ʼʱ��Ƚ�
			message.append("����Ŀ����ʱ�俪ʼʱ��Ϊ");
			message.append(kssj);
			message.append(",Ŀǰ�޷�����");
		} else if (!Base.isNull(jssj)
				&& (Integer.parseInt(jssj) <Integer.parseInt(nowtime))) {// ��ǰʱ�������ʱ��Ƚ�
			message.append("����Ŀ�����ֹʱ��Ϊ");
			message.append(jssj);
			message.append(",Ŀǰ���޷�����");
		} else if (!"yes".equalsIgnoreCase(kzkg)) {// ���ؿ���

			message.append("����ĿĿǰ�ر������У��޷���������");

			if (!Base.isNull(bz)) {// �жϱ�ע
				message.append("<br/>");
				message.append("��ע��");
				message.append(bz);
			}
		}

		return message.toString();
	}
	
	/**
	 * ������ʱ��
	 * 
	 * @author ΰ�����
	 */
	private String checkShsj(HashMap<String, String> xmsjInfo) {

		// ��ʾ��Ϣ
		StringBuilder message = new StringBuilder();
		// Ŀǰʱ��
		String nowtime = xmsjInfo.get("nowtime");
		// ��ʼʱ��
		String kssj = xmsjInfo.get("shkssj");
		// ����ʱ��
		String jssj = xmsjInfo.get("shjssj");
		// ���ƿ���
		String kzkg = xmsjInfo.get("shkzkg");
		// ��ע
		String bz = xmsjInfo.get("bz");

		if (!Base.isNull(kssj)
				&& (Float.parseFloat(kssj) > Float.parseFloat(nowtime))) {// ��ǰʱ���뿪ʼʱ��Ƚ�
			message.append("����Ŀ���ʱ�俪ʼʱ��Ϊ");
			message.append(kssj);
			message.append(",Ŀǰ�޷����");
		} else if (!Base.isNull(jssj)
				&& (Float.parseFloat(jssj) < Float.parseFloat(nowtime))) {// ��ǰʱ�������ʱ��Ƚ�
			message.append("����Ŀ��˽�ֹ��ʼʱ��Ϊ");
			message.append(jssj);
			message.append(",Ŀǰ���޷�����");
		} else if (!"yes".equalsIgnoreCase(kzkg)) {// ���ؿ���

			message.append("����ĿĿǰ�ر�����У��޷��������");

			if (!Base.isNull(bz)) {// �жϱ�ע
				message.append("<br/>");
				message.append("��ע��");
				message.append(bz);
			}
		}

		return message.toString();
	}
	 
	/**
	 * ���ָ����Ŀ�Ƿ�����
	 * @author qlj
	 */
	public boolean  checkShkz(String xmdm) {
		
		String message="";
		
		// ��Ŀʱ����Ϣ
		HashMap<String, String> xmsjInfo = dao.getXmsjInfo(xmdm);

		// ��Ŀ��˿����ж�(��˿��ء����ʱ��)
		message = checkShsj(xmsjInfo);
		if (!Base.isNull(message)) {
			return false;
		}
		
		return true;
		
	}
	// ===============ʱ���� end===============================

	// ===============������� begin===============================
	/**
	 * ������������б�
	 * 
	 * @author ΰ�����
	 */
	@SuppressWarnings("unchecked")
	private List<HashMap<String, String>> getPjtjList(PjpyWdpjModel model) {

		// �༶����Service
		PjszBjdlService bjdlService = new PjszBjdlService();
		// ѧ��
		String xh = model.getXh();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// �༶����
		String bjdl = bjdlService.getBjdl(xh);

		List<HashMap<String, String>> pjtjList = dao.getPjtjList(xmdm);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (pjtjList != null && pjtjList.size() > 0) {

			HashMap<String, Object> map = new HashMap<String, Object>();

			for (int i = 0; i < pjtjList.size(); i++) {
				HashMap<String, String> pjtjMap = pjtjList.get(i);
				String tjdm = pjtjMap.get("tjdm");
				String tjfw = pjtjMap.get("tjfw");

				if ("all".equalsIgnoreCase(tjfw)) {
					map.put(tjdm, pjtjMap);
				}

				if (tjfw.equalsIgnoreCase(bjdl)) {
					map.put(tjdm, pjtjMap);
				}

			}

			for (Map.Entry<String, Object> entry : map.entrySet()) {
				@SuppressWarnings("unused")
				String paramName = entry.getKey();
				HashMap<String, String> paramValue = (HashMap<String, String>) entry
						.getValue();
				list.add(paramValue);
			}

		}

		return list;
	}

	/**
	 * �����������
	 * 
	 * @author ΰ�����
	 */
	private String checkSqtj(String xh, String xmdm,
			List<HashMap<String, String>> pjtjList) {

		// ��ʾ��Ϣ
		String message = "";

		if (pjtjList != null && pjtjList.size() > 0) {
			for (int i = 0; i < pjtjList.size(); i++) {
				HashMap<String, String> tjMap = pjtjList.get(i);
				message = checkSqtj(xh, xmdm, tjMap);
				if (!Base.isNull(message)) {
					break;
				}
			}
		}

		return message;
	}

	/**
	 * ���������������ϸ��
	 * 
	 * @author ΰ�����
	 */
	@SuppressWarnings("unchecked")
	private String checkSqtj(String xh, String xmdm, Map<String, String> map) {

		// ��ʾ��Ϣ
		String message = "";
		// ��������
		String tjlx = map.get("tjlx");
		// ��������
		String tjmc = map.get("tjmc");
		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs)?"":tsgs;
		
		if ("no".equalsIgnoreCase(tjms)) {
			tjms = tjmc + gx + tjz + tsgs;
			map.put("tjms", tjms);
		}
		
		if ("logicRelation".equalsIgnoreCase(tjlx)) {// �߼���ϵ
			message = judgeLogicRelation(xh, map);
		} else if ("logicReverse".equalsIgnoreCase(tjlx)) {// �����߼���ϵ
			message = judgeLogicReverse(xh, map);
		} else if ("minRelation".equalsIgnoreCase(tjlx)) {// ��С�ֹ�ϵ
			message = judgeMinRelation(xh, map);
		} else if ("avgRelation".equalsIgnoreCase(tjlx)) {// ƽ���ֹ�ϵ
			message = judgeAvgRelation(xh, map);
		} else if ("instanceReverse".equalsIgnoreCase(tjlx)) {// ĳ�����ϵ
			message = judgeInstanceReverse(xh, xmdm, map);
		} else {// ���÷�����������
			try {
				map.put("xh", xh);
				WdpjPjtjMethod tjszMethod = new WdpjPjtjMethod();
				Class myClass = tjszMethod.getClass();
				message = (String) myClass.getMethod(tjlx,
						(Class[]) new Class[] { HashMap.class }).invoke(
						tjszMethod, map);
			} catch (Exception e) {
				System.out.println("������������,method:" + tjlx + "��������;");
				e.printStackTrace();
			}
		}

		return message;
	}

	/**
	 * �ж��߼���ϵ
	 * 
	 * @author ΰ�����
	 */
	private String judgeLogicRelation(String xh, Map<String, String> map) {

		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		String bjz = dao.getBjz(xh, map, "");
		bjz = Base.isNull(bjz) ? "0" : bjz;

		try {
			//////�ٷֱ�������������ʲ���������ѧУ���Ի����������ϴ�ѧ11417���������ٷֱ��滻Ϊ�༶����/////////////////////////////////////////////////////////
			if(Base.xxdm.equals(Globals.XXDM_BJLHDX) && tsgs.equals("%") && zd.split(",").length > 1){//11417
				message = judgeGxh(xh,map);
			}else{
				boolean flag = compareTo(bjz, tjz, gx, true);

				// �����������Ļ�
				if (!flag) {
					message = "�������Ŀ��Ҫ��" + tjms + ",������Ϊ" + bjz + tsgs
							+ ",����������������";
				}
			}
		} catch (Exception e) {
			System.out.println("��" + tablename + "���д��ڷ����֣�" + zd + "����¼������");
		}

		return message;
	}
	
	/*
	 * ѧУ���Ի�����
	 */
	private String judgeGxh(String xh, Map<String, String> map) {
		
		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		String bjz = dao.getBjz(xh, map, "");
		bjz = Base.isNull(bjz) ? "0" : bjz;
		

		String[] zds = zd.split(",");
		
		HashMap<String, String> bjzMap = dao.getBjzData(xh, map, "");
		String grpm = bjzMap.get(zds[0]);//��������
		grpm = Base.isNull(grpm) ? "0" : grpm;
		String num = bjzMap.get(zds[1]);//����
		long tjzpm = Math.round(Double.parseDouble(tjz) * Integer.parseInt(num)/100);//����ֵ����
		boolean flag = compareTo(grpm, tjzpm + "", gx, true);

		// �����������Ļ�
		if (!flag) {
			message = "�������Ŀ��Ҫ��" + tjms + "(������"+tjzpm+"),������Ϊ" + grpm
					+ ",����������������";
		}
		return message;
	}
	
	/**
	 * �жϷ����߼���ϵ
	 * 
	 * @author ΰ�����
	 */
	private String judgeLogicReverse(String xh, Map<String, String> map) {

		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		String bjz = dao.getBjz(xh, map, "");
		bjz = Base.isNull(bjz) ? "0" : bjz;

		try {			
			//////�ٷֱ�������������ʲ���������ѧУ���Ի����������ϴ�ѧ11417���������ٷֱ��滻Ϊ�༶����/////////////////////////////////////////////////////////
			if(Base.xxdm.equals(Globals.XXDM_BJLHDX) && tsgs.equals("%") && zd.split(",").length > 1){//11417
				message = judgeGxh(xh,map);
			}else{
				boolean flag = compareTo(bjz, tjz, gx, false);
	
				// �����������Ļ�
				if (!flag) {
					message = "�������Ŀ��Ҫ��" + tjms + ",������Ϊ" + bjz + tsgs
							+ ",����������������";
				}
			}
		} catch (Exception e) {
			System.out.println("��" + tablename + "���д��ڷ����֣�" + zd + "����¼������");
		}

		return message;
	}

	/**
	 * �ж���С�ֹ�ϵ
	 * 
	 * @author ΰ�����
	 */
	private String judgeMinRelation(String xh, Map<String, String> map) {

		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		String bjz = dao.getBjz(xh, map, "Min");
		bjz = Base.isNull(bjz) ? "0" : bjz;

		try {

			boolean flag = compareTo(bjz, tjz, gx, true);

			// �����������Ļ�
			if (!flag) {
				message = "�������Ŀ��Ҫ��" + tjms + ",������Ϊ" + bjz + tsgs
						+ ",����������������";
			}
		} catch (Exception e) {
			System.out.println("��" + tablename + "���д��ڷ����֣�" + zd + "����¼������");
		}

		return message;
	}

	/**
	 * �ж�ƽ���ֹ�ϵ
	 * 
	 * @author ΰ�����
	 */
	private String judgeAvgRelation(String xh, Map<String, String> map) {

		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		String bjz = dao.getBjz(xh, map, "AVG");
		bjz = Base.isNull(bjz) ? "0" : bjz;
		bjz = String.valueOf(Math.round(Double.parseDouble(bjz)));

		try {

			boolean flag = compareTo(bjz, tjz, gx, true);

			// �����������Ļ�
			if (!flag) {
				message = "�������Ŀ��Ҫ��" + tjms + ",������Ϊ" + bjz + tsgs
						+ ",����������������";
			}
		} catch (Exception e) {
			System.out.println("��" + tablename + "���д��ڷ����֣�" + zd + "����¼������");
		}

		return message;
	}

	/**
	 * �ж�ĳ�����ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeInstanceReverse(String xh, String xmdm,
			Map<String, String> map) {

		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		map.put("xmdm", xmdm);
		
		// �Ƚ�ֵ
		String bjz = dao.getBjz(xh, map, "count");
		bjz = Base.isNull(bjz) ? "0" : bjz;

		boolean flag = false;

		if (Integer.parseInt(bjz) > 0) {
			flag = true;
		}

		// �����������Ļ�
		if (!flag) {
			message = "�������Ŀ��Ҫ��" + tjms + ",�����߲������������";
		}

		return message;
	}
	

	/**
	 * Ŀ��ֵ��ȷ��ֵ�Ƚ�
	 * 
	 * @author ΰ�����
	 */
	 public boolean compareTo(String bjz, String tjz, String gx,
			boolean relation) {

		boolean flag = false;

		// �Ƚ�ֵ������ֵ�ǿ�
		if (StringUtils.isNotNull(bjz) && StringUtils.isNotNull(tjz)) {
			// ��ϵΪ"="
			if ("=".equalsIgnoreCase(gx)) {
				flag = bjz.equalsIgnoreCase(tjz) ? true : false;
			}
			// ��ϵΪ">"
			else if (">".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) > 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) > 0 ? true : false;
				}
			}
			// ��ϵΪ">="
			else if (">=".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) > 0
							|| bbjz.compareTo(btjz) == 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) > 0
							|| btjz.compareTo(bbjz) == 0 ? true : false;
				}
			}
			// ��ϵΪ"<"
			else if ("<".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) < 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) < 0 ? true : false;
				}

			}
			// ��ϵΪ"<="
			else if ("<=".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) < 0
							|| bbjz.compareTo(btjz) == 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) < 0
							|| btjz.compareTo(bbjz) == 0 ? true : false;
				}

			}
		}

		return flag;
	}
	// ===============������� end===============================

	// ===============������� begin===============================
	/**
	 * �����������
	 * 
	 * @author ΰ�����
	 */
	private String checkRskz(String xh, PjpyWdpjModel model,
			PjszPjxmModel pjxmModel) {

		// ��ʾ��Ϣ
		StringBuilder message = new StringBuilder();
		// ���Ʒ�Χ
		String kzfw = pjxmModel.getKzfw();
		// ���Ʒ�Χ
		String xmdm = model.getXmdm();
		// ��˸�λID
		String gwid = model.getGwid();
		// ��������
		String szrs = dao.getSzrs(xh, xmdm, kzfw);
		szrs = Base.isNull(szrs) ? "0" : szrs;
		// ͨ������
		String tgrs = dao.getTgrs(xh, xmdm, kzfw, gwid);
		tgrs = Base.isNull(tgrs) ? "0" : tgrs;
		
		if (Integer.parseInt(tgrs) >= Integer.parseInt(szrs)) {
			message.append("����Ŀ��������Ϊ");
			message.append(szrs);
			message.append("�����Ѵﵽ���ޣ��޷��ٽ�������");
		}

		return message.toString();
	}

	/**
	 * �����������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private String checkRskz(String[] xh,
			List<HashMap<String, String>> bshrList, PjpyWdpjModel model,
			PjszPjxmModel pjxmModel) throws Exception {

		// ���Ʒ�Χ
		String xmdm = model.getXmdm();
		// ���Ʒ�Χ
		String kzfw = pjxmModel.getKzfw();
		// ����˸�λID
		String gwid = model.getGwid();
		// ������˲��Ŵ���
		String[] bmdm = null;
		
		if ("cpz".equalsIgnoreCase(kzfw)) {
			bmdm = dao.getSqrCpz(bshrList);
		} else {
			bmdm = dao.getSqrbm(bshrList, kzfw);
		}
		
		// ��Ŀͨ������
		List<HashMap<String, String>> bmrsList = dao.getBmrsList(xmdm, gwid,
				xh, bmdm, kzfw);

		// �ж���Ŀ��������
		String message = judgeRsxz(model, pjxmModel, bmrsList, bshrList);
		
		return message;
	}
	
	/**
	 * �ж���Ŀ��������
	 * 
	 * @author ΰ�����
	 */
	private String judgeRsxz(PjpyWdpjModel model, PjszPjxmModel pjxmModel,
			List<HashMap<String, String>> bmrsList,
			List<HashMap<String, String>> bshrList) {

		// ���Ʒ�Χ
		String kzfw = pjxmModel.getKzfw();
		// ��������
		String rssz = pjxmModel.getRssz();

		if ("yes".equalsIgnoreCase(rssz)) {
			if (bmrsList != null && bmrsList.size() > 0) {
				for (int i = 0; i < bmrsList.size(); i++) {
					HashMap<String, String> bmInfo = bmrsList.get(i);
					// ���Ŵ���
					String dm = bmInfo.get("bmdm");
					// ���ű���������
					int bmrs = Integer.parseInt(bmInfo.get("bmrs"));
					// ��ͨ������
					int ytgrs = Integer.parseInt(bmInfo.get("ytgrs"));

					// ��������
					if (bshrList != null && bshrList.size() > 0) {
						for (int j = 0; j < bshrList.size(); j++) {
							HashMap<String, String> bshrInfo = bshrList.get(j);
							// �꼶
							String nj = bshrInfo.get("nj");
							// ѧԺ����
							String xydm = bshrInfo.get("xydm");
							// רҵ����
							String zydm = bshrInfo.get("zydm");
							// �༶����
							String bjdm = bshrInfo.get("bjdm");
							// ���������
							String cpzdm = bshrInfo.get("cpzdm");
							// ѧԺ����
							String xymc = bshrInfo.get("xymc");
							// רҵ����
							String zymc = bshrInfo.get("zymc");
							// �༶����
							String bjmc = bshrInfo.get("bjmc");
							// ����������
							String cpzmc = bshrInfo.get("cpzmc");
							// ��������
							String sybm = "";
							// ��������
							String bmmc = "";
							// ���Ʒ�Χ
							if ("nj".equalsIgnoreCase(kzfw)) {
								sybm = nj;
								bmmc = nj + "��";
							} else if ("xy".equalsIgnoreCase(kzfw)) {
								sybm = xydm;
								bmmc = xymc;
							} else if ("njxy".equalsIgnoreCase(kzfw)) {
								sybm = nj + xydm;
								bmmc = nj + "��" + xymc;
							} else if ("njzy".equalsIgnoreCase(kzfw)) {
								sybm = nj + zydm;
								bmmc = nj + "��" + zymc;
							} else if ("bj".equalsIgnoreCase(kzfw)) {
								sybm = bjdm;
								bmmc = bjmc;
							} else if ("cpz".equalsIgnoreCase(kzfw)) {
								sybm = cpzdm;
								bmmc = cpzmc;
							}

							if (sybm.equalsIgnoreCase(dm)) {
								// ������ͨ������
								ytgrs++;

								if (ytgrs > bmrs) {

									String xmmc = pjxmModel.getXmmc();

									StringBuilder message = new StringBuilder();
									message.append(bmmc);
									message.append("��" + xmmc + "��");
									message.append("���ͨ������Ϊ");
									message.append("<font color='blue'>");
									message.append(bmrs);
									message.append("</font>");
									message.append("��,\n");
									message.append("���<font color='blue'>ͨ����������</font>����ȷ�ϣ�");

									return message.toString();
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	// ===============������� end===============================

	// ===============�����Ŀ���begin========================
	/**
	 * �����Ŀ���
	 * 
	 * @author ΰ�����
	 */
	private String checkXmjd(String xh, PjpyWdpjModel model) {

		// ��ʾ��Ϣ
		StringBuilder message = new StringBuilder();
		// ���Ʒ�Χ
		String xmdm = model.getXmdm();
		// ��˸�λID
		String gwid = model.getGwid();
		// �Ǽ����Ŀ�б�
		List<HashMap<String, String>> jdxmList = dao.getJdxmList(xmdm);
		// �ѻ����Ŀ�б�
		List<HashMap<String, String>> hdxmList = dao.getHdxmList(xh, gwid);

		if (jdxmList != null && jdxmList.size() > 0) {
			for (int i = 0; i < jdxmList.size(); i++) {
				String fjdxm = jdxmList.get(i).get("fjddm");

				if (hdxmList != null && hdxmList.size() > 0) {
					for (int j = 0; j < hdxmList.size(); j++) {
						String hdxmdm = hdxmList.get(j).get("xmdm");
						String hdxmmc = hdxmList.get(j).get("xmmc");

						if (fjdxm.equalsIgnoreCase(hdxmdm)) {
							message.append("����Ŀ��");
							message.append("��");
							message.append(hdxmmc);
							message.append("��");
							message.append("���ɼ�ã�����ȷ��");
							return message.toString();
						}
					}
				}
			}
		}

		return message.toString();
	}

	/**
	 * �����Ŀ���
	 * 
	 * @author ΰ�����
	 */
	private String checkXmjd(String[] xh,
			List<HashMap<String, String>> bshrList, PjpyWdpjModel model,
			PjszPjxmModel pjxmModel) {

		// ��Ŀ�Ǽ���б�
		List<HashMap<String, String>> xmfjdList = dao.getXmjdList(pjxmModel);
		// ��������ѻ�ý�ѧ���б�
		List<HashMap<String, String>> yhdjxjList = dao.getYhdjxjList(pjxmModel,
				bshrList);

		// �ж���Ŀ�������
		String message = judgeJdxz(pjxmModel, xmfjdList, yhdjxjList);

		return message;
	}

	/**
	 * �ж���Ŀ�������
	 * 
	 * @author ΰ�����
	 */
	private String judgeJdxz(PjszPjxmModel pjxmModel,
			List<HashMap<String, String>> xmfjdList,
			List<HashMap<String, String>> yhdjxjList) {

		// ��Ŀ����
		String xmdm = pjxmModel.getXmdm();

		if (yhdjxjList != null && yhdjxjList.size() > 0) {
			for (int i = 0; i < yhdjxjList.size(); i++) {
				HashMap<String, String> yhInfo = yhdjxjList.get(i);
				// �ѻ����Ŀ����
				String yhddm = yhInfo.get("xmdm");
				// �������ѧ��
				String xh = yhInfo.get("xh");
				// �����������
				String xm = yhInfo.get("xm");

				if (xmfjdList != null && xmfjdList.size() > 0) {
					for (int j = 0; j < xmfjdList.size(); j++) {
						HashMap<String, String> xmfjdInfo = xmfjdList.get(j);
						// �Ի����Ŀ����
						String dm = xmfjdInfo.get("xmdm");
						// �Ǽ�ô���
						String fjddm = xmfjdInfo.get("fjddm");
						// ��Ŀ����
						String fjdmc = xmfjdInfo.get("fjdmc");

						if (xmdm.equalsIgnoreCase(dm)
								&& yhddm.equalsIgnoreCase(fjddm)) {

							StringBuilder message = new StringBuilder();
							message.append("<font color='blue'>");
							message.append(xh);
							message.append("(" + xm + ")");
							message.append("</font>");
							message.append("�ѻ��");
							message.append("��" + fjdmc + "��,\n");
							message.append("��������Ŀ");
							message.append("<font color='blue'>");
							message.append("���ɼ��");
							message.append("</font>");
							
							return message.toString();
						}
					}
				}
			}
		}

		return null;
	}
	// ===============�����Ŀ���end========================
}
