package xgxt.pjpy.nblg;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class PjpyNblgRychDAO {

	DAO dao = DAO.getInstance();
	
	ArrayList<String> values = null;
	
	public static PjpyNblgRychDAO mydao = new PjpyNblgRychDAO();
	
	public static PjpyNblgRychDAO getInstance() {
		return mydao;
	}
	
	/**
	 * ������ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> tjTitle() throws Exception {
		String[] enList = new String[]{"pk", "rownum", "xn", "rychmc", "tj", "pm"};
		String[] cnList = new String[]{"pk", "�к�", "ѧ��", "�����ƺ�", "�ֶ�", "����"};
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * �������
	 * @return
	 * @throws Exception
	 */
	public List<String[]> tjResult(String xn, String rychdm) throws Exception {
		StringBuffer sql = new StringBuffer(
				"select xn||rychdm||tj pk, rownum,xn,(select rychmc from rychdmb b where" +
				" a.rychdm=b.rychdm) rychmc,(case tj when 'dycj' then '���������༶����'" +
				" when 'tycj' then '�����ɼ�' when 'jxj' then '��������ѧ��' else '' end) " +
				"tj,pm||bz pm from nblg_rychtjszb a where 1=1");
		String[] opList = new String[] { "pk", "rownum", "xn", "rychmc", "tj",
				"pm" };
		if (!StringUtils.isNull(xn)) {
			sql.append(" and xn='");
			sql.append(xn);
			sql.append("'");
		}
		if (!StringUtils.isNull(rychdm)) {
			sql.append(" and rychdm='");
			sql.append(rychdm);
			sql.append("'");
		}
		return dao.rsToVator(sql.toString(), new String[] {}, opList);
	}
	
	/**
	 * ��������
	 * @param model
	 * @param request
	 * @return �ɹ�Ϊtrue,ʧ��Ϊfalse
	 * @throws Exception
	 */
	public boolean saveTj(PjpyNblgModel model, HttpServletRequest request) throws Exception {
		String bz = "";
		String tj = model.getTj();
		if ("dycj".equalsIgnoreCase(tj)) {
			bz = "(%)";
		} else if ("tycj".equalsIgnoreCase(tj)) {
			bz = "(�ּ�����)";
		} else if ("jxj".equalsIgnoreCase(tj)) {
			bz = "(ͬ�ȼ�����)";
			String jxjmc = dao.getOneRs(
					"select jxjmc from jxjdmb where jxjdm=?", new String[] {model.getPm()},
					"jxjmc");
			model.setPm(jxjmc);
		}
		return StandardOperation.insert("nblg_rychtjszb", new String[] { "xn",
				"rychdm", "tj", "pm", "bz" }, new String[] { model.getXn(),
				model.getRychdm(), model.getTj(),
				model.getPm(), bz }, request);
	}
	
	/**
	 * ����ɾ��
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delTj(String[] keys) throws Exception {
		String res = "";
		for (int i = 0; i < keys.length; i++) {
			boolean bFlag = dao.runUpdate(
					"delete from nblg_rychtjszb where xn||rychdm||tj=?",
					new String[] { keys[i] });
			if (!bFlag) {
				res += (i + 1) + ",";
			}
		}
		return StringUtils.isNull(res) ? "" : res
				.substring(0, res.length() - 1);
	}
	
	/**
	 * �����ƺŲ�ѯ��ͷ(ѧԺ,ѧУ)
	 *    ������������
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> rychqryTit(String userType) throws Exception {
		String[] enList = new String[] { "pk", "dis", "rownum", "xh", "xm",
				"xn", "bjmc", "rychmc", "dycj","dypm", "tycj",  "xxsh" };
		String[] cnList = new String[] { "pk", "dis", "�к�", "ѧ��", "����", "ѧ��",
				"�༶", "�����ƺ�", "�����ɼ�","�༶����", "�����ɼ�",  "ѧУ���" };
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "pk", "dis", "rownum", "xh", "xm", "xn",
					"bjmc", "rychmc", "dycj","dypm", "tycj", "xysh" };
			cnList = new String[] { "pk", "dis", "�к�", "ѧ��", "����", "ѧ��", "�༶",
					"�����ƺ�", "�����ɼ�","�༶����", "�����ɼ�",  "ѧԺ���" };
		} 
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * �����ƺŲ�ѯ���(ѧԺ)
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xyrychqryRes(PjpyNblgModel model) throws Exception {
		values = new ArrayList<String>();
		List<String[]> rs = new ArrayList<String[]>();
		List<String[]> tjList = dao.rsToVator(
				"select tj,pm from nblg_rychtjszb where xn=? and rychdm=?",
				new String[] {model.getXn(), model.getRychdm()}, new String[] {"tj", "pm"});
		int bjrs = dao.getOneRsint("select count(xh) from view_xsjbxx where bjdm='"+model.getBjdm()+"'");
		StringBuffer whereSql = new StringBuffer("");
		if (tjList != null) {
			for (int i=0;i<tjList.size();i++) {
				String[] tmpList = tjList.get(i);
				if ("dycj".equalsIgnoreCase(tmpList[0])) {
					whereSql.append(" and dypm<=");
					bjrs = bjrs * (StringUtils.isNull(tmpList[1]) ? 1 : Integer.parseInt(tmpList[1])) / 100;
 					whereSql.append(tmpList[1]);
				} else if ("tycj".equalsIgnoreCase(tmpList[0])) {
					whereSql.append(" and tycj>=");
					whereSql.append(tmpList[1]);
				} else if ("jxj".equalsIgnoreCase(tmpList[0])) {
					String jxjjb = dao.getOneRs(
							"select jxjjb from jxjdmb where jxjmc =?",
							new String[] { tmpList[1] }, "jxjjb");
					if (!StringUtils.isNull(jxjjb)) {
						whereSql.append(" and jxjzdjb<=");
						whereSql.append(jxjjb);
					}
				}
			}
		}
		String sql = "select a.xh||b.xn||b.rychdm pk,(case b.xxsh when 'ͨ��' then 'disabled' else '' end) dis,rownum,a.xh,a.xm,'"
				+ model.getXn()
				+ "' xn,a.bjmc,(select rychmc from rychdmb where rychdm='"
				+ model.getRychdm()
				+ "') rychmc,a.dycj,a.dypm,a.tycj,b.xysh from view_nblg_rychtjb a left join xsrychb b on a.xh=b.xh and b.xn=? and b.rychdm=? where 1=1";
		String[] opList = new String[] { "pk", "dis", "rownum", "xh", "xm",
				"xn", "bjmc", "rychmc", "dycj", "dypm", "tycj", "xysh" };
		values.add(model.getXn());
		values.add(model.getRychdm());
		
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and a.nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and a.xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and a.zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and a.bjdm = ?");
			values.add(model.getBjdm());
		}
		rs = dao
				.rsToVator(
						"select * from ("
								+ sql
								+ whereSql.toString()
								+ ") a where 1=1 and not exists (select 1 from xsrychb b where a.xh=b.xh and b.xn='"
								+ model.getXn() + "' and b.rychdm<>'"
								+ model.getRychdm() + "')",
						values != null ? values.toArray(new String[0])
								: new String[] {}, opList);//��SQL�����������ƺż������
		return rs;
	}
	
	/**
	 * �����ƺŲ�ѯ���(ѧУ)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xxrychqryRes(PjpyNblgModel model) throws Exception {
		String sql = "select xh||xn||rychdm pk,'' dis,rownum,xh,xm,xn,bjmc,rychmc,(select b.dycj from view_nblg_rychtjb b where a.xh=b.xh) " +
				"dycj,(select b.dypm from view_nblg_rychtjb b where a.xh=b.xh) dypm,(select b.tycj from view_nblg_rychtjb b where a.xh=b.xh) tycj,xxsh from view_xsrychb a where xysh='ͨ��'";
		String[] opList = new String[] { "pk", "dis", "rownum", "xh", "xm", "xn",
				"bjmc", "rychmc", "dycj", "dypm", "tycj", "xxsh" };
		StringBuffer whereSql = getWhereSql(model);
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public StringBuffer getWhereSql(PjpyNblgModel model) throws Exception {
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
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
		if (!StringUtils.isNull(model.getRychdm())) {
			whereSql.append(" and rychdm = ?");
			values.add(model.getRychdm());
		}
		return whereSql;
	}

	/**
	 * ���������ƺŴ��� ��ȡ�������ƺ������������
	 * 
	 * @param rychdm
	 * @return
	 * @throws Exception
	 */
	public int rychXzrs(String rychdm) throws Exception {
		String rychpxbl = dao.getOneRs(
				"select pxbl from rychdmb where rychdm = ?",
				new String[] { rychdm }, "pxbl");//�����ƺű���
		String xsrs = dao.getOneRs("select count(*) num from view_xsjbxx",
				new String[] {}, "num");//��ѧ������
		int pxbl = 0;
		int ixsrs = !StringUtils.isNull(xsrs) ? Integer.parseInt(xsrs) : 0;
		if (!StringUtils.isNull(rychpxbl) && rychpxbl.lastIndexOf("%") > 0) {
			pxbl = Integer.parseInt(rychpxbl
					.substring(0, rychpxbl.length() - 1))
					* ixsrs / 100;
		} else if (!StringUtils.isNull(rychpxbl)
				&& rychpxbl.lastIndexOf("%") <= 0) {
			pxbl = Integer.parseInt(rychpxbl) * ixsrs / 100;
		} else {
			pxbl = 0;
		}
		return pxbl;
	}
	
	/**
	 * ѧԺ��������ƺŽ��
	 *    ���ʱ����������,���������
	 *    keys�д�ŵĿ���������,Ҳ������ѧ��
	 * @param keys
	 * @param rychdm
	 * @return
	 * @throws Exception
	 */
	public String xyrychshRes(String[] keys, String rychdm, String type) throws Exception {
		String xn = Base.getJxjsqxn();
		String rs = "";
		String jd = "";
		String cb = "";
		int rychxzrs = rychXzrs(rychdm);//�����ƺ������������
		int tgrs = dao
		.getOneRsint("select nvl(count(*),0) from xsrychb where xn='"
				+ Base.getJxjsqxn() + "' and rychdm='" + rychdm
				+ "' and xysh='ͨ��'");//ѧԺ���ͨ��������
		type = StringUtils.isNull(type) ? "δ���"
				: ("tg".equalsIgnoreCase(type) ? "ͨ��" : ("btg"
						.equalsIgnoreCase(type) ? "��ͨ��" : "δ���"));
		for (int i = 0; i < keys.length; i++) {
			if ("ͨ��".equalsIgnoreCase(type)) {//״̬Ϊͨ��
				if (tgrs >= rychxzrs && rychxzrs != 0) {// ͨ���������������������¼������
					cb += (i + 1) + ",";
					continue;
				} else {
					boolean bFlag = chkDataExists(keys[i]);//��������Ƿ����
					if (bFlag) {// �������������״̬
						int num = dao
								.getOneRsint("select nvl(count(*),0) from xsrychb where xh=(select xh from xsrychb where xh||xn||rychdm='"
										+ keys[i]
										+ "') and xn='"
										+ xn
										+ "' and xysh='ͨ��'");// ��ѧ���Ƿ��м�����������ƺ�
						if (num > 0) {// �м�����¼������
							jd += (i + 1) + ",";
							continue;
						} else {// ��������״̬
							boolean bRes = dao
									.runUpdate(
											"update xsrychb set xysh=? where xh||xn||rychdm=?",
											new String[] { type, keys[i] });
							if (!bRes) {
								rs += (i+1) + ",";
							}
						}
					} else {// ������ֱ�Ӳ��뵽��
						int num = dao
								.getOneRsint("select nvl(count(*),0) from xsrychb where xh='"
										+ keys[i]
										+ "' and xn='"
										+ xn
										+ "' and xysh='ͨ��'");// ��ѧ���Ƿ��м�����������ƺ�
						if (num > 0) {// �м�����¼������
							jd += (i + 1) + ",";
							continue;
						} else {// ��������
							boolean bRes = dao
									.runUpdate(
											"insert into xsrychb (xh,xn,rychdm,xysh) values (?,?,?,?)",
											new String[] { keys[i], xn, rychdm, type });
							if (!bRes) {
								rs += (i + 1) + ",";
							}
						}
					}
				}
			} else {//״̬Ϊ��ͨ��
				boolean bFlag = chkDataExists(keys[i]);//��������Ƿ����
				if (bFlag) {//�������������״̬
					boolean bRes = dao.runUpdate(
							"update xsrychb set xysh=? where xh||xn||rychdm=?",
							new String[] { type, keys[i] });
					if (!bRes) {
						rs += (i+1) + ",";
					}
				} else {//������ֱ�Ӳ��뵽��
					boolean bRes = dao
					.runUpdate(
							"insert into xsrychb (xh,xn,rychdm,xysh) values (?,?,?,?)",
							new String[] { keys[i], xn, rychdm, "��ͨ��" });
					if (!bRes) {
						rs += (i+1) + ",";
					}
				}
			}
			tgrs ++;
		}
		jd = StringUtils.isNull(jd) ? "" : "���е�"
				+ jd.substring(0, jd.length() - 1) + "�����ݲ���ʧ��,ԭ���Ǹ����Ѽ�����������ƺ�!";
		cb = StringUtils.isNull(cb) ? "" : "���е�"
			+ cb.substring(0, cb.length() - 1) + "�����ݲ���ʧ��,ԭ����ͨ�������ѳ���������!";
		if (StringUtils.isNull(jd) && StringUtils.isNull(cb)) {
			return "";
		}
		rs = "�������:" + jd + "\n          " + cb;
		return rs;
	}
	
	/**
	 * ѧУ�����ƺ���˽��
	 * @param keys
	 * @param rychdm
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String xxrychshRes(String[] keys, String rychdm, String type) throws Exception {
		String xn = Base.getJxjsqxn();
		String rs = "";
		int rychxzrs = rychXzrs(rychdm);//�����ƺ������������
		int tgrs = dao
		.getOneRsint("select nvl(count(*),0) from xsrychb where xn='"
				+ Base.getJxjsqxn() + "' and rychdm='" + rychdm
				+ "' and xxsh='ͨ��'");//ѧԺ���ͨ��������
		type = StringUtils.isNull(type) ? "δ���"
				: ("tg".equalsIgnoreCase(type) ? "ͨ��" : ("btg"
						.equalsIgnoreCase(type) ? "��ͨ��" : "δ���"));
		String cb = "";
		String jd = "";
		for (int i=0;i<keys.length;i++) {
			if ("ͨ��".equalsIgnoreCase(type)) {
				if (tgrs >= rychxzrs && rychxzrs != 0) {// ͨ���������������������¼������
					cb += (i + 1) + ",";
					continue;
				} else {
					int num = dao
							.getOneRsint("select nvl(count(*),0) from xsrychb where xh=(select xh from xsrychb where xh||xn||rychdm='"
									+ keys[i]
									+ "') and xn='"
									+ xn
									+ "' and xxsh='ͨ��'");// ��ѧ���Ƿ��м�����������ƺ�
					if (num > 0) {// �м�����¼������
						jd += (i + 1) + ",";
						continue;
					} else {// ��������״̬
						dao
								.runUpdate(
										"update xsrychb set xxsh=? where xh||xn||rychdm=?",
										new String[] { type, keys[i] });
					}
				}
			} else {
				dao.runUpdate(
						"update xsrychb set xxsh=? where xh||xn||rychdm=?",
						new String[] { type, keys[i] });
			}
			tgrs++;
		}
		jd = StringUtils.isNull(jd) ? "" : "���е�"
			+ jd.substring(0, jd.length() - 1) + "�����ݲ���ʧ��,ԭ���Ǹ����Ѽ�����������ƺ�!";
		cb = StringUtils.isNull(cb) ? "" : "���е�"
		+ cb.substring(0, cb.length() - 1) + "�����ݲ���ʧ��,ԭ����ͨ�������ѳ���������!";
		if (StringUtils.isNull(jd) && StringUtils.isNull(cb)) {
			return "";
		}
		rs = "�������:" + jd + "\n          " + cb;
		return rs;
	}
	
	/**
	 * ���������Ƿ����
	 *    ���ڷ���true,��֮Ϊfalse
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataExists(String pkValue) throws Exception {
		int num = dao
				.getOneRsint("select nvl(count(*),0) from xsrychb where xh||xn||rychdm='"
						+ pkValue + "'");
		if (num>0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ͨ���༶�����ȡ�꼶
	 * @param bjdm
	 * @return
	 * @throws Exception
	 */
	public String getNjBybjdm(String bjdm) throws Exception {
		return dao.getOneRs("select distinct nj from bks_xsjbxx where bjdm=?", new String[]{bjdm}, "nj");
	}
	
	/**
	 * �ɼ����������б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjtjszList() throws Exception {
		return dao.rsToVator("select tjdm,tjmc,(case tjlx when 'kcmc' then '�γ�����' when 'kcxz' then '�γ�����' else tjlx end) tjlx from pjpy_cjtjglb",
				new String[] {}, new String[] { "tjlx","tjmc" });
	}
	
	/**
	 * ����ɼ�����
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveCjtj(PjpyNblgModel model, HttpServletRequest request)
			throws Exception {
		return StandardOperation.insert("pjpy_cjtjglb", new String[] { "tjmc",
				"tjlx" }, new String[] { DealString.toGBK(model.getTjmc()),
				model.getTjxz() }, request);
	}
	
	/**
	 * ��ʾ�ɼ�������Ϣ
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewCjtj(String pkValue) throws Exception {
		return dao.getMapNotOut(
				"select tjdm,tjmc,tjlx from pjpy_cjtjglb where tjdm=?",
				new String[] { pkValue });
	}
	
	/**
	 * �޸ĳɼ�����
	 * @param model
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateCjtj(PjpyNblgModel model, String pkValue,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("pjpy_cjtjglb", new String[] { "tjmc",
				"tjlx" }, new String[] { DealString.toGBK(model.getTjmc()),
				model.getTjxz() }, "tjdm", pkValue, request);
	}
	
	/**
	 * ɾ���ɼ�������Ϣ
	 * 
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String deleteCjtj(String[] keys, HttpServletRequest request)
			throws Exception {
		String rs = "";
		for (int i = 0; i < keys.length; i++) {
			boolean bFlag = dao.runUpdate(
					"delete from pjpy_cjtjglb where tjdm=?",
					new String[] { keys[i] });
			if (!bFlag) {
				rs += (i + 1) + ",";
			}
		}
		return StringUtils.isNull(rs) ? "" : rs.substring(0, rs.length() - 1);
	}
}
