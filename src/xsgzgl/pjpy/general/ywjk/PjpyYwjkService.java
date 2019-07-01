package xsgzgl.pjpy.general.ywjk;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import common.Globals;
import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyYwjkInterface;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.inter.SzdwDwwhInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �u���u��_�I�սӿ�_ͨ��_Service��
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

public class PjpyYwjkService extends PjpyGeneralService implements
		PjpyYwjkInterface {

	PjpyYwjkDAO dao = new PjpyYwjkDAO();

	/**
	 * ��ÌW���u���б�
	 * 
	 * @date 2013-01-23
	 * @author ΰ�����
	 */
	public List<HashMap<String, Object>> getStuPjList(String xh) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		// ���W���б�
		List<String[]> jxjList = dao.getJxjList(xh);

		String[] title = { "��������", "��Ŀ����", "��Ŀ���", "���ʱ��" };
		List<String[]> rs = new ArrayList<String[]>();
		rs.add(title);
		rs.addAll(jxjList);

		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "��ѧ��");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, rs);
		list.add(map);

		// �����ƺ��б�
		List<String[]> rychList = dao.getRychList(xh);

		title = new String[] { "��������", "��Ŀ����", "��Ŀ���", "���ʱ��" };
		rs = new ArrayList<String[]>();
		rs.add(title);
		rs.addAll(rychList);

		map = new HashMap<String, Object>();
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "�����ƺ�");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, rs);
		list.add(map);
		
		// �ۺϲ����б�
		
		if(Globals.XXDM_CZZYJSXY.equals(Base.xxdm)){
			//����ְҵ����ѧԺѧ����Ϣ��ʾ���Ի�
			List<HashMap<String, String>> zhcpTop = dao.getCzzyTop();
			List<String[]> zhcpList = dao.getCzzyList(xh,zhcpTop);

			title = getCzzyTop(zhcpTop);
			rs = new ArrayList<String[]>();
			rs.add(title);
			rs.addAll(zhcpList);

			map = new HashMap<String, Object>();
			map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "�ۺϲ���");
			map.put(GlobalsVariable.XSXX_KTEYS_CXJG, rs);
			list.add(map);			
		}else{
			
			List<HashMap<String, String>> zhcpTop = dao.getZhcpTop();
			List<String[]> zhcpList = dao.getZhcpList(xh,zhcpTop);

			title = getZhcpTop(zhcpTop);
			rs = new ArrayList<String[]>();
			rs.add(title);
			rs.addAll(zhcpList);

			map = new HashMap<String, Object>();
			map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "�ۺϲ���");
			map.put(GlobalsVariable.XSXX_KTEYS_CXJG, rs);
			list.add(map);
		}
		
		return list;
	}

	/**
	 * ����۲��ͷ
	 * 
	 * @date 2013-01-30
	 * @author ΰ�����
	 */
	public String[] getZhcpTop(List<HashMap<String, String>> zhcpTop) {

		List<String> list = new ArrayList<String>();
		
		list.add("�۲�����");
		
		if (zhcpTop != null && zhcpTop.size() > 0) {
			for (int i = 0; i < zhcpTop.size(); i++) {
				list.add(zhcpTop.get(i).get("xmmc"));
			}
		}

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		String zcpm = jbszForm.getZcpm();
		
		if ("0".equalsIgnoreCase(zcpm)) {
			list.add("����������");
		}

		if ("1".equalsIgnoreCase(zcpm) || "5".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm) || "7".equalsIgnoreCase(zcpm)) {
			list.add("�۲���꼶ѧԺ����");
		}

		if ("2".equalsIgnoreCase(zcpm) || "4".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm) || "7".equalsIgnoreCase(zcpm)) {
			list.add("�۲���꼶רҵ����");
		}

		if ("3".equalsIgnoreCase(zcpm) || "5".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm) || "7".equalsIgnoreCase(zcpm)) {
			list.add("�۲�ְ༶����");
		}
		
		return list.toArray(new String[] {});
	}
	
	/**
	 * ����۲��ͷ---����ְҵ����ѧԺѧ����Ϣ��ʾ���Ի�
	 * 
	 * @date 2013-5-30
	 * @author ��ǿ
	 */
	public String[] getCzzyTop(List<HashMap<String, String>> zhcpTop) {

		List<String> list = new ArrayList<String>();
		
		list.add("�۲�����");
		
		if (zhcpTop != null && zhcpTop.size() > 0) {
			for (int i = 0; i < zhcpTop.size(); i++) {
				list.add(zhcpTop.get(i).get("xmmc"));
			}
		}
		
		list.add("�۲�����[��]");
		list.add("��������[��]");
		list.add("�ȼ�[��]");
		list.add("��ע[��]");
		list.add("�۲�����[��]");
		list.add("��������[��]");
		list.add("�ȼ�[��]");
		list.add("��ע[��]");
		
		return list.toArray(new String[] {});
	}
}