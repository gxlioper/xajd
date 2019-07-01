package xgxt.zxdk.gzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.zjlg.WjcfZjlgActionForm;
import xgxt.wjcf.zjlg.WjcfZjlgModel;

/**
 *
 */
public class ZxdkGzdxDao {

	DAO dao = DAO.getInstance();
	private ArrayList<String> values = new ArrayList<String>();
	/**
	 *  ��ѯ��ѧ��������ά����ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryZxdkxxTitle() {
		String[] enList = new String[]{ "pk","dis", "xh", "xm",
				 "xn", "nj","xymc", "zymc", "bjmc","zxdkmc","dkje" };
		String[] cnList = new String[]{ "pk", "dis","ѧ��", "����",
				 "ѧ��","�꼶", "ѧԺ", "רҵ", "�༶", "��ѧ��������","������" };
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * ��ѯ��where����
	 * @param model
	 * @return
	 */
	public StringBuffer getWhereSql(ZxdkGzdxModel model) {
		StringBuffer whereSql = new StringBuffer();
		values = new ArrayList<String>();
		if (model != null) {
			if (!StringUtils.isNull(model.getXh())) {
				whereSql.append(" and xh like '" + "%" + DealString.toGBK(model.getXh()) + "%'");
			}
			if (!StringUtils.isNull(model.getXn())) {
				whereSql.append(" and xn = ?");
				values.add(model.getXn());
			}
			if (!StringUtils.isNull(model.getNj())) {
				whereSql.append(" and nj = ?");
				values.add(model.getNj());
			}
			if (!StringUtils.isNull(model.getXydm())) {
				whereSql.append(" and xydm = ?");
				values.add(model.getXydm());
			}
			if (!StringUtils.isNull(model.getZydm())) {
				whereSql.append(" and zydm = ?");
				values.add(model.getZydm());
			}
			if (!StringUtils.isNull(model.getBjdm())) {
				whereSql.append(" and bjdm = ?");
				values.add(model.getBjdm());
			}
			if (!StringUtils.isNull(model.getXm())) {
				whereSql.append(" and xm like '" + "%" + DealString.toGBK(model.getXm()) + "%'");
			}
			if (!StringUtils.isNull(model.getNd())) {
				whereSql.append(" and nd = ?");
				values.add(model.getNd());
			}
			if (!StringUtils.isNull(model.getZxdkmc())) {
				whereSql.append(" and zxdkmc = ?");
				values.add(model.getZxdkmc());
			}
			if (!StringUtils.isNull(model.getDkje_ks())) {
				whereSql.append(" and to_number(dkje) >=to_number(?)");
				values.add(model.getDkje_ks());
			}
			if (!StringUtils.isNull(model.getDkje_js())) {
				whereSql.append(" and to_number(dkje) <=to_number(?)");
				values.add(model.getDkje_js());
			}
		}
		return whereSql;
	}
	/**
	 * ��ѯ��ѧ�������ݽ��byѧԺ
	 * @param model
	 * @param zxdkForm
	 * @param userType
	 * @param userName
	 * @return
	 */
	public ArrayList<String[]> queryZxdkxxResultByxy(ZxdkGzdxModel model, ZxdkGzdxActionForm zxdkForm,String userType, String userName) {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||xn||zxdkmc pk,'' dis,rownum r,xh,xm,xn,xb,nj,xymc,zymc,bjmc,zxdkmc,dkje from xg_view_gzdx_zxdk_xszxdkb a where 1=1";
		String[] colList = new String[]{ "pk", "dis","xh", "xm",
			 "xn","nj","xymc", "zymc", "bjmc", "zxdkmc","dkje" };
		String FDYSql = "";
		if ("fdy".equalsIgnoreCase(userType)) {
			FDYSql = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		}
		return dao.rsToVator("select * from (" + sql + whereSql.toString() + FDYSql + " order by xh,xymc,zymc,bjmc,xn desc" + ") where r<="
				+ (zxdkForm.getPages().getStart() + zxdkForm
						.getPages().getPageSize()) + " and r> "
				+ zxdkForm.getPages().getStart(), values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
	}
	/**
	 * ��ѯ��ѧ�������ݽ��
	 * @param model
	 * @param zxdkForm
	 * @return
	 */
	public ArrayList<String[]> queryZxdkxxResult(ZxdkGzdxModel model, ZxdkGzdxActionForm zxdkForm) {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||xn||zxdkmc pk,'' dis,rownum r,xh,xm,xn,xb,nj,xymc,zymc,bjmc,zxdkmc,dkje from xg_view_gzdx_zxdk_xszxdkb where 1=1";
		String[] colList = new String[]{ "pk", "dis","xh", "xm",
				 "xn","nj","xymc", "zymc", "bjmc", "zxdkmc","dkje" };
		return dao.rsToVator("select * from (" + sql + whereSql.toString() +"  order by xh,xymc,zymc,bjmc,xn desc" + ") where r<="
				+ (zxdkForm.getPages().getStart() + zxdkForm
						.getPages().getPageSize()) + " and r> "
				+ zxdkForm.getPages().getStart(), values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
	}
	/**
	 * ��ѯ��ѧ�������ݼ�¼��byѧԺ
	 * @param model
	 * @param userType
	 * @param userName
	 * @return
	 */
	public int queryZxdkxxResultNumByxy(ZxdkGzdxModel model, String userType, String userName) {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||xn||zxdkmc pk,'' dis,xh,xm,xn,xb,nj,xymc,zymc,bjmc,zxdkmc,dkje from xg_view_gzdx_zxdk_xszxdkb a where 1=1";
		String[] colList = new String[]{ "pk", "dis","xh", "xm",
				 "xn","nj","xymc", "zymc", "bjmc", "zxdkmc","dkje" };
		String FDYSql = "";
		if ("fdy".equalsIgnoreCase(userType)) {
			FDYSql = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		}
		return dao.rsToVator(sql + whereSql.toString() + FDYSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList).size();
	}
	
	/**
	 * ��ѯ��ѧ�������ݼ�¼��
	 * @param model
	 * @return
	 */
	public int queryZxdkxxResultNum(ZxdkGzdxModel model) {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||xn||zxdkmc pk,'' dis,xh,xm,xn,xb,nj,xymc,zymc,bjmc,zxdkmc,dkje from xg_view_gzdx_zxdk_xszxdkb where 1=1";
		String[] colList = new String[]{ "pk","dis", "xh", "xm",
				 "xn","nj","xymc", "zymc", "bjmc", "zxdkmc","dkje" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, colList).size();
	}
	
	/**
	 *������ʾ��ϸ��Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryZxdkxxOne(String pkValue) {
		String sql = "select xh,xm,xymc,zymc,bjmc,nj,xb from view_xsxxb a where xh=?";
		return dao.getMapNotOut(sql, new String[]{pkValue});
	}
	/**
	 * ����ǰ��ѯ��Ϣ�Ƿ��Ѿ�����
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryAddZxdk(String pkValue) {
		String sql = "select xh,xn,zxdkmc,dkje,bz from xg_gzdx_zxdk_xszxdkb a where xh||xn||zxdkmc=?";
		return dao.getMapNotOut(sql, new String[]{pkValue});
	}
	/**
	 * ��ѧ������Ϣ��������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxdkxx(ZxdkGzdxModel model) throws Exception {
		return dao.runUpdate("insert into xg_gzdx_zxdk_xszxdkb(xh,xn,zxdkmc,dkje,bz) " +
				"values (?,?,?,?,?)", new String[] {
						model.getXh(), model.getXn(), model.getZxdkmc(),
						model.getDkje(), model.getBz() });
	}
	/**
	 * ��ѯѧ���������ݱ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryZxdkdkxxTitle() {
		String[] enList = new String[]{ "pk", "xh", "xm",
				"xymc", "zymc", "nj", "bjmc"};
		String[] cnList = new String[]{ "pk", "ѧ��", "����",
				"ѧԺ", "רҵ", "�꼶","�༶" };
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * ��ѯѧ����Ϣ���
	 * @param zxdkForm
	 * @param userType
	 * @param userName
	 * @return
	 */
	public ArrayList<String[]> queryZxdkxxResultnotExis(
			ZxdkGzdxActionForm zxdkForm, String userType, String userName) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		String sql = "select xh||xn||zxdkmc pk,xh,xm,xymc,zymc,nj,bjmc from view_xsxxb where 1=1";
		if (!StringUtils.isNull(zxdkForm.getXydm())) {
			sql += " and xydm='";
			sql += zxdkForm.getXydm();
			sql += "'";
		}
		if (!StringUtils.isNull(zxdkForm.getXh())) {
			sql += " and xh like '%";
			sql += zxdkForm.getXh().toString();
			sql += "%'";
		}
		if (!StringUtils.isNull(zxdkForm.getXm())) {
			sql += " and xm like '%";
			sql += zxdkForm.getXm().toString();
			sql += "%'";
		}
		if (!StringUtils.isNull(zxdkForm.getDkje_ks())) {
			sql += " and to_number(dkje) >=to_number('";
			sql += zxdkForm.getDkje_ks();
			sql += "')";
		}
		if (!StringUtils.isNull(zxdkForm.getDkje_js())) {
			sql += " and to_number(dkje) >=to_number('";
			sql += zxdkForm.getDkje_js();
			sql += "')";
		}
		String FDYSql = "";
		if ("fdy".equalsIgnoreCase(userType)) {
			FDYSql = " and exists (select 1 from fdybjb b where zgh like '"
					+ userName + "' and a.bjdm = b.bjdm)";
		}
		String[] colList = new String[] { "pk", "xh", "xm",
				"xymc", "zymc", "nj", "bjmc" };
		list = dao.rsToVator(sql + FDYSql, new String[] {}, colList);
		return list;
	}
	
	/**
	 * �޸���ѧ��������ά����Ϣ
	 * @param pkValue
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateZxdkSjwh(String pkValue, ZxdkGzdxModel model) throws Exception{
		return dao
				.runUpdate(
						"update xg_gzdx_zxdk_xszxdkb set xn=?,zxdkmc=?,dkje=?,bz=? where xh||xn||zxdkmc=?",
						new String[] {model.getXn(), DealString.toGBK(model.getZxdkmc()),model.getDkje(),model.getBz(), pkValue});
	}
	
	/**
	 * �鿴��ѧ������Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewZxdkxx(String pkValue) {
		return dao.getMapNotOut("select xh,xm,xn,xb,nj,xymc,zymc,bjmc,zxdkmc,dkje,bz from xg_view_gzdx_zxdk_xszxdkb where xh||xn||zxdkmc=?", new String[]{pkValue});
	}
	
	/**
	 *  ɾ����ѧ������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean delZxdkSjwh(String[] pkValue) throws Exception {
		if (pkValue == null) {
			return false;
		} else {
			String[] sqlArr = new String[pkValue.length];
			for (int i=0;i<pkValue.length;i++) {
				StringBuffer sql = new StringBuffer("delete from xg_gzdx_zxdk_xszxdkb where xh||xn||zxdkmc='");
				sql.append(pkValue[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
			int[] res = dao.runBatch(sqlArr);
			return dao.checkBatch(res);
		}
	}
}
