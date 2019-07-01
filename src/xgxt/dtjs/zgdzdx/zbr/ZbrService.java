package xgxt.dtjs.zgdzdx.zbr;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;

public class ZbrService {

	ZbrDAO dao = new ZbrDAO();

	/**
	 * @author luo
	 * @describe ��ñ�ͷ
	 */
	public List<HashMap<String, String>> getTopTr(String tableName,
			String[] colList) {
		DAO dao = DAO.getInstance();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		return topTr;
	}

	/**
	 * @author luo
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @describe ���ֵ�����б�
	 */
	public ArrayList<String[]> getZbrList(String tableName, ZbrModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// �꼶
		String nj = DealString.toGBK(model.getNj());
		// ѧ��
		String xn = DealString.toGBK(model.getXn());
		// ѧ��
		String xq = DealString.toGBK(model.getXq());
		// ѧ��
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// ����
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		// ѧԺ����
		String xydm = DealString.toGBK(model.getXydm());
		// רҵ����
		String zydm = DealString.toGBK(model.getZydm());
		// �༶����
		String bjdm = DealString.toGBK(model.getBjdm());

		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");

		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='" + nj + "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='" + xn + "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='" + xq + "'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='" + xydm
				+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='" + zydm
				+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='" + bjdm
				+ "'");
		query.append(" and xh like ? and xm like ?");

		String[] inPutList = new String[] { xh, xm };

		return dao.commonQuery(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * @author luo
	 * @describe ����ֵ������Ϣ
	 */
	public boolean saveZbr(ZbrModel myModel, HttpServletRequest request)
			throws Exception {
		return dao.saveZbr(myModel, request);
	}

	/**
	 * @author luo
	 * @describe ���ֵ������Ϣ
	 */
	public HashMap<String, String> getZbr(String tableName,
			String[] colList, String pk, String pkValue) throws Exception {
		return dao.commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	/**
	 * @author luo
	 * @describe ɾ��ֵ������Ϣ
	 */
	public boolean delZbr(String xh, HttpServletRequest request)
			throws Exception {
		return dao.delZbr(xh, request);
	}
}
