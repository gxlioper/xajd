
package xgxt.pjpy.cqkjxy;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �Ϻ����̼�����ѧΥ�ʹ���DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-19</p>
 */
public class PjpyCqkjxyDAO  {
	DAO dao = DAO.getInstance();
	/**
	 * ���渨��Ա�����Ϣ
	 * @param cqkjxyfdyshModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveFdyShXs(PjpyCqkjxyFdyshModel cqkjxyfdyshModel, String pk, String pkValue, HttpServletRequest request) throws Exception {
		boolean flag = false;
		String fdysh = DealString.toGBK(cqkjxyfdyshModel.getYesNo());
		flag = StandardOperation.update("xsjxjb", new String[]{"fdysh"}, new String[]{fdysh}, pk, pkValue, request);
		return flag;
	}
	
	/**
	 * ����������ȡ��ѯ��������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String[] getFdyShQry(String pk, String pkVal, String[] colList) throws Exception {
		String [] rs = null;
		String sql = "select "
			+ pk
			+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,JXJMC,XXSH yesNo,XXSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW ,FDYYJ,XYPSWYHYJ,XYSHYJ,cjmc,zhpfmc,jfqk,xq from view_xsjxjb where "
			+ pk + "='" + pkVal + "'";
		rs = dao.getOneRs(sql, new String[] {}, colList);
		return rs;
	}
}
