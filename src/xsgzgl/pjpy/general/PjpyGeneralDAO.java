package xsgzgl.pjpy.general;

import java.util.ArrayList;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͨ��_DAO��
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

public class PjpyGeneralDAO extends CommDAO{

	/**
	 * ��������Ƿ��ύ
	 * 
	 * @author ΰ�����
	 */
	public String getLcdmSftj(String lcdm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select sftj from xg_pjpy_pjlcb ");
		sql.append("where 1=1 ");
		sql.append("and lcdm=? ");

		String sftj = dao.getOneRs(sql.toString(), new String[] { lcdm },
				"sftj");

		return sftj;
	}
	
	/**
	 * ����ϼ������Ƿ��ύ
	 * 
	 * @author ΰ�����
	 */
	public String getLcdmNfcz(String lcdm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select sftj from xg_pjpy_pjlcdjb ");
		sql.append("where 1=1 ");
		sql.append("and lcdj = ( ");
		sql.append("select lcdj from xg_pjpy_pjlcb ");
		sql.append("where 1=1 ");
		sql.append("and lcdm=? ");
		sql.append(") - 1");

		String sftj = dao.getOneRs(sql.toString(), new String[] { lcdm },
				"sftj");

		return sftj;
	}
}
