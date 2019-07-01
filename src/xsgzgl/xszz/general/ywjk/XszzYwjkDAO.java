package xsgzgl.xszz.general.ywjk;

import java.util.List;

import xgxt.DAO.DAO;
import xsgzgl.xszz.general.XszzGeneralDAO;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ������_�I�սӿ�_ͨ��_DAO��
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

public class XszzYwjkDAO extends XszzGeneralDAO {

	/**
	 * ���ѧ�������б�
	 * 
	 * @date 2013-01-30
	 * @author ΰ�����
	 */
	public List<String[]> getXszzList(String xh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.*, a.xn || a.xq || a.nd zzzq ");
		sql.append("from (select ");
		sql.append("b.xmmc,a.sqsj, ");
		sql.append("a.xmzzjb,a.xmzzje, ");
		// ѧ��
		sql.append("case ");
		sql.append("when a.xn is null then ");
		sql.append("'' ");
		sql.append("else ");
		sql.append(" a.xn || 'ѧ��' ");
		sql.append("end xn, ");
		// ѧ��
		sql.append("case ");
		sql.append("when a.xq is null then ");
		sql.append("'' ");
		sql.append("else ");
		sql.append("(select b.xqmc from xqdzb b where a.xq = b.xqdm) || 'ѧ��' ");
		sql.append("end xq, ");
		// ���
		sql.append("case ");
		sql.append("when a.nd is null then ");
		sql.append("'' ");
		sql.append("else ");
		sql.append(" a.nd || '���' ");
		sql.append("end nd ");

		sql.append("from xszz_shztb a, xszz_zzxmb b ");
		sql.append("where a.xmdm = b.xmdm ");
		sql.append("and (b.shjb = '�������'  ");
		sql.append("or (b.shjb = 'һ�����' and a.shzt1 = 'ͨ��')  ");
		sql.append("or (b.shjb = '�������' and a.shzt2 = 'ͨ��')  ");
		sql.append("or (b.shjb = '�������' and a.shzt3 = 'ͨ��')) ");
		sql.append("and xh = ?) a ");
		sql.append("order by a.xn, a.xq ,a.nd , a.sqsj ");

		return dao.rsToVator(sql.toString(), new String[] { xh }, new String[] {
				"zzzq", "xmmc", "xmzzjb", "xmzzje", "sqsj" });
	}
}
