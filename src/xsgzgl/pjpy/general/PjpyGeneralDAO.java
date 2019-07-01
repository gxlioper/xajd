package xsgzgl.pjpy.general;

import java.util.ArrayList;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyGeneralDAO extends CommDAO{

	/**
	 * 获得流程是否提交
	 * 
	 * @author 伟大的骆
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
	 * 获得上级流程是否提交
	 * 
	 * @author 伟大的骆
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
