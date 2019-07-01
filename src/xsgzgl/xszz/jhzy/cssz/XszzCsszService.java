package xsgzgl.xszz.jhzy.cssz;

import java.util.HashMap;

import xgxt.form.User;
import xgxt.utils.date.DateUtils;
import xsgzgl.comm.BasicService;
import xsgzgl.xszz.jhzy.knsrd.KnsrdModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description:ѧ������-��������
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */
public class XszzCsszService extends BasicService {

	private XszzCsszDao dao = new XszzCsszDao();

	/**
	 * �������������Ϣ
	 * 
	 * @param form
	 * @return
	 * @throws Exceptioin
	 */
	public boolean bcCsszxx(XszzCsszActionForm form) throws Exception {
		boolean result = dao.scCsszxx(form);
		if (result) {
			result =  dao.bcCsszxx(form);
		}
		
		if (result) {
			copyKnsrd(form.getXn());
		}
		return result;
	}

	/**
	 * ��ѯ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCsszxx() throws Exception {
		return dao.cxCsszxx();
	}

	/**
	 * ��ȡ��ͥ�����������״̬,TRUE/FALSE
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean getJtqkdzsjzt() throws Exception {
		HashMap<String, String> rs = getCsszxx();
		if (rs == null || rs.isEmpty()) {
			return true;
		}
		String kssj = rs.get("jtqkdzkssj");
		String jssj = rs.get("jtqkdzjssj");
		String dqsj = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDayOfMonth();
		if ((Integer.parseInt(kssj) <= Integer.parseInt(dqsj))
				&& (Integer.parseInt(dqsj) <= Integer.parseInt(jssj))) {
			return true;
		}
		return false;
	}

	/**
	 * ��ȡ����������״̬,TRUE/FALSE
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean getKnssqzt() throws Exception {
		HashMap<String, String> rs = getCsszxx();
		if (rs == null || rs.isEmpty()) {
			return true;
		}
		String kssj = rs.get("knssqkssj");
		String jssj = rs.get("knssqjssj");
		String dqsj = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDayOfMonth();
		if ((Integer.parseInt(kssj) <= Integer.parseInt(dqsj))
				&& (Integer.parseInt(dqsj) <= Integer.parseInt(jssj))) {
			return true;
		}
		return false;
	}

	/**
	 * ��ȡ���������״̬,TRUE/FALSE
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean getKnsshzt() throws Exception {
		HashMap<String, String> rs = getCsszxx();
		if (rs == null || rs.isEmpty()) {
			return true;
		}
		String kssj = rs.get("knsshkssj");
		String jssj = rs.get("knsshjssj");
		String dqsj = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDayOfMonth();
		if ((Integer.parseInt(kssj) <= Integer.parseInt(dqsj))
				&& (Integer.parseInt(dqsj) <= Integer.parseInt(jssj))) {
			return true;
		}
		return false;
	}

	/**
	 * ��ȡ������־��ѧ������״̬,TRUE/FALSE
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean getGjlzjxjsqzt() throws Exception {
		HashMap<String, String> rs = getCsszxx();
		if (rs == null || rs.isEmpty()) {
			return true;
		}
		String kssj = rs.get("lzjxjsqkssj");
		String jssj = rs.get("lzjxjsqjssj");
		String dqsj = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDayOfMonth();
		if ((Integer.parseInt(kssj) <= Integer.parseInt(dqsj))
				&& (Integer.parseInt(dqsj) <= Integer.parseInt(jssj))) {
			return true;
		}
		return false;
	}

	/**
	 * ��ȡ������־��ѧ�����״̬,TRUE/FALSE
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean getGjlzjxjshzt() throws Exception {
		HashMap<String, String> rs = getCsszxx();
		if (rs == null || rs.isEmpty()) {
			return true;
		}
		String kssj = rs.get("lzjxjshkssj");
		String jssj = rs.get("lzjxjshjssj");
		String dqsj = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDayOfMonth();
		if ((Integer.parseInt(kssj) <= Integer.parseInt(dqsj))
				&& (Integer.parseInt(dqsj) <= Integer.parseInt(jssj))) {
			return true;
		}
		return false;
	}

	/**
	 * ��ȡ������ѧ������״̬,TRUE/FALSE
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean getGjzxjsqzt() throws Exception {
		HashMap<String, String> rs = getCsszxx();
		if (rs == null || rs.isEmpty()) {
			return true;
		}
		String kssj = rs.get("gjzxjsqkssj");
		String jssj = rs.get("gjzxjsqjssj");
		String dqsj = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDayOfMonth();
		if ((Integer.parseInt(kssj) <= Integer.parseInt(dqsj))
				&& (Integer.parseInt(dqsj) <= Integer.parseInt(jssj))) {
			return true;
		}
		return false;
	}

	/**
	 * ��ȡ������־��ѧ�����״̬,TRUE/FALSE
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean getGjzxjshzt() throws Exception {
		HashMap<String, String> rs = getCsszxx();
		if (rs == null || rs.isEmpty()) {
			return true;
		}
		String kssj = rs.get("gjzxjshkssj");
		String jssj = rs.get("gjzxjshjssj");
		String dqsj = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDayOfMonth();
		if ((Integer.parseInt(kssj) <= Integer.parseInt(dqsj))
				&& (Integer.parseInt(dqsj) <= Integer.parseInt(jssj))) {
			return true;
		}
		return false;
	}

	/**
	 * ������һѧ�����������Ϣ
	 * 
	 * @date 2012-12-06
	 * @author ΰ�����
	 */
	public void copyKnsrd(String xn) {

		boolean isExists = isExists("xg_xszz_jhzy_knssqb", "xn", xn);

		try {
			if (!isExists) {
				dao.copyKnsrd(xn);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
	/**
	 * ������һѧ��ļ�ͥ�����Ϣ
	 * @param xn
	 * @return
	 */
	public void copyJtqk(String dqxn,String xn) {
		// TODO �Զ����ɷ������
		boolean isExists=isExists("xg_xszz_jhzy_jtqkdzb", "xn", xn);
		try {
			if (!isExists) {
				dao.copyJtqk(dqxn,xn);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}		
	}
}