package xgxt.qgzx.comm.gwsqwh;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ڹ���ѧ_ѧ����λ����-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */
public class QgzxXsgwsqDao {
	
	DAO dao = DAO.getInstance();
	ArrayList<String> whereList = new ArrayList<String>();
	
	/**
	 * ͨ��������ѯ�б����ݣ������������б�����
	 * @param tableName
	 * @param outZdArray
	 * @param wheresql
	 * @return
	 */
	public List<HashMap<String, String>> getLbList(String tableName, String[] outZdArray, String wheresql) {
		if (StringUtils.isNull(tableName)) {//tableName Ϊ����ֱ�ӷ��ؿ�
			return new ArrayList<HashMap<String, String>>(){};
		}
		
		wheresql = StringUtils.isNull(wheresql) ? "" : wheresql;
		String sql = "select * from " + tableName + wheresql;
		if (outZdArray != null && outZdArray.length > 0) {
			return dao.getList(sql, new String[]{}, outZdArray);
		} else {
			return dao.getListNotOut(sql, new String[]{});
		}
	}
	
	/**
	 * ��ѯ��λ������Ϣ
	 * 
	 * @param qgzxForm
	 * @return
	 */
	public List<HashMap<String, String>> queryXsgwsqxx(
			QgzxXsgwsqForm qgzxForm) {
		
		return dao.getList(
				"select * from (select gwdm||gwsbsj pk,gwxzmc,yrdwmc,gwdm,sqsyrs,nvl(sqsyknss,'0') sqsyknss,sqjssj,gwxz,sqdw,gzksrq,gzjsrq," +
				"'ksq' cz from view_gwxx a where xn=? and nd=? and xueqi=? and sfyx='��Ч' and sqsyrs > 0)" + getWhereSql(qgzxForm),
				new String[] { qgzxForm.getXn(), qgzxForm.getNd(), qgzxForm.getXq() },
						new String[] {"pk",  "gwxzmc", "yrdwmc", "gwdm",
						"sqsyrs", "sqsyknss", "sqjssj", "cz"  });
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯѧ���ĸ�λ������Ϣ
	 * �����λ>0 ���� ���н��ĸ�λ �Ĳ���ʾ����
	 * 
	 * @param qgzxForm
	 * @return
	 */
	public List<HashMap<String, String>> queryXsgwxxByStu(
			QgzxXsgwsqForm qgzxForm) {
		String sql = "select * from (select a.*,nvl(b.xyyj,'') xyyj,nvl(b.xxyj,'') xxyj,(case when b.xyyj is null and b.xxyj is null then 'δ����' else '���˵�λ('||b.xyyj||')<br/>ѧУ('||b.xxyj||')'" +
				" end) shzt,'ksq' cz from (select gwdm||gwsbsj pk,gwsbsj,gwxzmc,yrdwmc,gwdm,nvl(sqsyrs,'0') sqsyrs,nvl(sqsyknss,'0') sqsyknss,sqjssj,a.gwxz,a.gzksrq,a.gzjsrq,a.sqdw " +
				" from view_gwxx a where xn=? and nd=? and xueqi=? and sfyx='��Ч' and sqsyrs > 0) a left join (select * from xsgwxxb b where " +
				" xn=? and nd=? and xq=? and xh= ?) b on a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj)" + getWhereSql(qgzxForm);
		return dao.getList(sql, new String[] { qgzxForm.getXn(),
				qgzxForm.getNd(), qgzxForm.getXq(), qgzxForm.getXn(),
				qgzxForm.getNd(), qgzxForm.getXq(), qgzxForm.getXh() },
				new String[] { "pk", "gwxzmc", "yrdwmc", "gwdm", "sqsyrs",
						"sqsyknss", "xyyj", "xxyj", "sqjssj", "shzt", "cz" });
	}
	
	/**
	 * ���ƴװ��ѯ��������Ϊ�漰��ʱ��ֵ���Բ��ܲ���ͨ�õĲ�ѯ����
	 * @param qgzxForm
	 * @return
	 */
	public String getWhereSql(QgzxXsgwsqForm qgzxForm) {
		StringBuffer sql = new StringBuffer(" where 1=1 ");
		if (!StringUtils.isNull(qgzxForm.getGwxz())) {
			sql.append(" and gwxz = '");
			sql.append(qgzxForm.getGwxz());
			sql.append("'");
		}
		if (!StringUtils.isNull(qgzxForm.getSqdw())) {
			sql.append(" and sqdw = '");
			sql.append(qgzxForm.getSqdw());
			sql.append("'");
		}
		if (!StringUtils.isNull(qgzxForm.getGzksrq())) {
			sql.append(" and to_date(gzksrq,'yyyy-mm-dd hh24:mi') >= to_date('");
			sql.append(qgzxForm.getGzksrq());
			sql.append("','yyyy-mm-dd')");
		}
		if (!StringUtils.isNull(qgzxForm.getGzjsrq())) {
			sql.append(" and to_date(gzjsrq,'yyyy-mm-dd hh24:mi') <= to_date('");
			sql.append(qgzxForm.getGzjsrq());
			sql.append("','yyyy-mm-dd')");
		}
		return sql.toString();
	}
	
	/**
	 * ��ȡ��λ��ϸ��Ϣ
	 * @param pkValue ��λ����+��λ�ϱ�ʱ��
	 * @return
	 */
	public Map<String, String> viewGwxx(String pkValue){
		return dao.getMapNotOut("select a.gwdm||a.gwsbsj gwdmsbsj,a.* from view_gwxx a where gwdm||gwsbsj = ?", new String[]{pkValue});
	}
	
	/**
	 * ͨ��ѧ�Ż�ȡѧ����Ϣ
	 * @param xh ѧ��
	 * @return
	 */
	public Map<String, String> viewXsxx(String xh){
		return dao.getMapNotOut("select * from view_xsjbxx a where xh = ?", new String[]{xh});
	}
	
	/**
	 * ��ȡѧ����λ������Ϣ
	 * @param xh ѧ��
	 * @param pkValue ��λ����+��λ�ϱ�ʱ��
	 * @return
	 */
	public Map<String, String> viewSqxx(String xh,String pkValue){
		return dao.getMapNotOut("select * from xsgwxxb a where xh = ? and gwdm||gwsbsj=?", new String[]{xh,pkValue});
	}
	
	/**
	 * ����ѧ�Ż�ȡ�����Ƿ��Ѿ���ʼ��ˣ���Ϊ0��δ��ʼ��ˣ������޸�������Ϣ���������޸�������Ϣ
	 * @param xh ѧ��
	 * @return
	 */
	public String getShCount(String xh,String pkValue){
		return dao.getOneRs("select count(*) cont from view_xsgwxx  where xh=? and gwdm||gwsbsj=? and (nvl(xyyj,'δ���')<>'δ���' or nvl(xxyj,'δ���')<>'δ���')", new String[]{xh,pkValue}, "cont");
	}
	
	/**
	 * ѧ����λ��������ѯ�б�
	 * 
	 * @param qgzxForm
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<String[]> queryXsgwsqxxByStu(QgzxXsgwsqForm qgzxForm)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		MakeQuery makeQuery = new MakeQuery();
		try {
			makeQuery.makeQuery(new String[]{"xh", "xn", "xq", "nd", "yrdwdm", "gwxz"}, new String[]{"gwdm"}, qgzxForm);
		} catch (IllegalArgumentException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return CommonQueryDAO.commonQuery("select xh||'!!split!!'||gwdm||gwsbsj pk,a.*,rownum r,(case when xyyj <> 'δ���' or xxyj <> 'δ���' then 'disabled'"
								+ " else '' end) dis from view_xsgwxx a"
								,makeQuery.getQueryString(), makeQuery
								.getInputList(), new String[] { "pk","dis", "r", "xn",
								"nd", "xqmc", "xh", "xm", "bjmc", "gwdm", "sfpks", "xyyj", "xxyj" }, qgzxForm);
	}
	
	/**
	 * ���˵�λ��ѯѧ����λ��������Ϣ
	 * 
	 * @param form
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<String[]> queryXsgwsqxxByYrdw(QgzxXsgwsqForm form)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		MakeQuery makeQuery = new MakeQuery();
		try {
			makeQuery.makeQuery(new String[] { "xh", "xn", "xq", "nd",
					"yrdwdm", "gwxz", "xydm", "zydm", "bjdm", "nj" },
					new String[] { "xh", "xm", "gwdm" }, form);
		} catch (IllegalArgumentException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return CommonQueryDAO.commonQuery("select xh||'!!split!!'||gwdm||gwsbsj pk,a.*,rownum r,(case when xxyj <> 'δ���' then 'disabled'"
								+ " else '' end) dis from view_xsgwxx a "
								,makeQuery.getQueryString(), makeQuery
								.getInputList(), new String[] {"pk", "dis", "r","xn",
									"nd", "xqmc", "xh", "xm", "bjmc", "gwdm", "sfpks", "xyyj", "xxyj" }, form);
	}
	
	/**
	 * ѧУ�û���ѯѧ����λ��������Ϣ
	 * 
	 * @param form
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<String[]> queryXsgwsqxxByxx(QgzxXsgwsqForm form)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		MakeQuery makeQuery = new MakeQuery();
		try {
			makeQuery.makeQuery(new String[] { "xh", "xn", "xq", "nd",
					"yrdwdm", "gwxz", "xydm", "zydm", "bjdm", "nj" },
					new String[] { "xh", "xm", "gwdm" }, form);
		} catch (IllegalArgumentException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return CommonQueryDAO.commonQuery(
						"select xh||'!!split!!'||gwdm||gwsbsj pk,a.*,rownum r,'' dis from view_xsgwxx a ",
						makeQuery.getQueryString(), makeQuery.getInputList(),
						new String[] { "pk","dis",  "r", "xn", "nd", "xqmc", "xh",
								"xm", "bjmc", "gwdm", "sfpks", "xyyj",
								"xxyj" }, form);
	}
	
	/**
	 * ɾ��ѧ����λ������Ϣ
	 * @param form
	 * @return
	 * @throws SQLException
	 */
	public int[] deleteXsgwsqxx(QgzxXsgwsqForm form) throws SQLException {
		String[] pk = form.getPrimarykey_cbv();
		StringBuffer sql = new StringBuffer();
		for (String s : pk) {
			sql.append("delete from xsgwxxb where xh||'!!split!!'||gwdm||gwsbsj = '");
			sql.append(s);
			sql.append("'!@");
		}
		return dao.runBatch(sql.toString().split("!@"));
	}
	
	public HashMap<String, String> queryGwsqsjb() {
		return dao.getMapNotOut("select * from gwsqsjb where rownum <2", new String[]{});
	}
}
