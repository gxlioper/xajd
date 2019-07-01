package xgxt.xtwh;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XtwhDAO extends CommDAO {

	/**
	 * �����ݷ�ʽ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveKjfsOne(String yhm, String picpath, String lx)
			throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "select max(xssx) + 1 xssx from xtwh_kjfsszb where yhm = ? ";

		// ��ʾ˳��
		String xssx = dao.getOneRs(sql, new String[] { yhm }, "xssx");
		xssx = Base.isNull(xssx) ? "0" : xssx;

		String[] arrSql = null;

		if ("yy".equalsIgnoreCase(lx)) {// Ӧ��

			arrSql = new String[2];

			arrSql[0] = "delete from xtwh_kjfsszb where yhm = '" + yhm
					+ "' and pic = '" + picpath + "'";

			arrSql[1] = "insert into xtwh_kjfsszb (yhm,pic,xssx) values('"
					+ yhm + "','" + picpath + "','" + xssx + "')";
		} else {// ȡ��

			arrSql = new String[1];

			arrSql[0] = "delete from xtwh_kjfsszb where yhm = '" + yhm
					+ "' and pic = '" + picpath + "'";
		}

		User user = new User();
		user.setUserName(yhm);

		boolean flag = dao.saveArrDate(arrSql, user);

		return flag;
	}

	/**
	 * ɾ����ݷ�ʽ 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean delKjfsOne(String yhm, String picpath)
			throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "delete from xtwh_kjfsszb where yhm = ? and pic = ?";

		boolean flag = dao.runUpdate(sql, new String[] { yhm, picpath });

		return flag;
	}
	
	/**
	 * ������ҳ����
	 * 
	 * @param yhm(�û���)
	 * @param dcid(����ID)
	 * @param xxid(ѡ��ID)
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveSydc(String yhm, String dcid, String xxid)
			throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "insert into xg_xtwh_sydcjgb(dcid,xxid,bdcr) values(?,?,?)";

		boolean flag = dao.runUpdate(sql, new String[] { dcid, xxid, yhm });

		return flag;
	}
	/** 
	 * @����:��ѯԤ��״̬������ʦ����ѧ��
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-3-21 ����04:55:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getYjzt() {
		DAO dao = DAO.getInstance();
		String  sql = "select yjzt from xtszb where rownum=1";
		return dao.getOneRs(sql, new String[]{}, "yjzt");
	}
}
