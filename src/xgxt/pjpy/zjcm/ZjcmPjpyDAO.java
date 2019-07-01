package xgxt.pjpy.zjcm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.PjpyTyDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class ZjcmPjpyDAO extends PjpyTyDAO {

	// -----------���� made by luojw----------------
	/**
	 * �����������(�ۺϷ�)�б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZhfList(String table, ZjcmPjpyModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		DAO dao = DAO.getInstance();

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq" };
		String[] queryLikeList = new String[] { "xh", "xm" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select t.*,rownum r from ");
		sql.append(table + " t )");		
		sql.append(" where r > ");
		sql.append(model.getPages().getStart());
		sql.append(" and r <= ");
		sql.append((model.getPages().getStart() + model.getPages().getPageSize()));

		ArrayList<String[]> list = dao.rsToVator(sql.toString(), myQuery.getInputList(), colList);
		String count = "select count(*) num from " + table;

		String num = dao.getOneRs(count, myQuery.getInputList(), "num");
		if (!Base.isNull(num)) {
			model.getPages().setMaxRecord(Integer.parseInt(num));
		}

		return list;
	}

	/**
	 * ����ۺ����ʷָ������
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getZhfBl(ZjcmPjpyModel model,
			String[] colList) {
		DAO dao = DAO.getInstance();
		String xn = model.getXn();
		String xq = model.getXq();
		String sql = "select * from zjcm_zhf_bl where xn = ? and xq= ? ";
		return dao.getMap(sql, new String[] { xn, xq }, colList);
	}
	
	/**
	 * �����ۺ����ʷ�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> jsZhf(ZjcmPjpyModel model) {

		DAO dao = DAO.getInstance();

		String xn = model.getXn();
		String xq = model.getXq();
		String dyfbl = model.getDyfbl();
		String zyfbl = model.getZyfbl();
		String tyfbl = model.getTyfbl();
		String nlfbl = model.getNlfbl();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();

		StringBuffer sql = new StringBuffer();

		sql.append("select t.*, t.dyzhf + t.zyzhf + t.tyzhf + t.nlzhf zhf ");
		sql.append("from (select a.xn, a.xq, a.xh, ");
		sql.append("a.dyf * " + dyfbl + " / 100 dyzhf, ");
		sql.append("a.zyf * " + zyfbl + " / 100 zyzhf, ");
		sql.append("a.tyf * " + tyfbl + " / 100 tyzhf, ");
		sql.append("a.nlf * " + nlfbl + " / 100 nlzhf  ");
		sql.append("from (select a.xn,a.xq, ");
		sql.append("a.pjxh xh,a.dyf,a.tyf,a.nlf,a.zhf, ");
		sql.append("nvl(b.zyf, 0) zyf from zjcm_zhf a ");
		sql.append("left join view_zjcm_zyf b on a.xn = b.xn ");
		sql.append("and a.xq = b.xq and a.pjxh = b.xh) a ");
		sql.append("where a.xn = ? and a.xq = ? ");
		sql.append("and exists (select 1 from view_xsjbxx b where a.xh = b.xh ");
		sql.append("and xydm like '%'||?||'%' and zydm like '%'||?||'%' and bjdm like '%'||?||'%')) t");
		//System.out.println(sql);
		return dao.getList(sql.toString(), new String[] { xn, xq, xydm, zydm,
				bjdm }, new String[] { "xh", "zhf" });
	}
	
	/**
	 * ����ۺϷ������б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZhfPmList(String table,
			ZjcmPjpyModel model) throws Exception {

		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "xh", "xm", "dyzhf", "zyzhf",
				"tyzhf", "nlzhf", "zhf", "dypm", "zypm", "typm", "nlpm",
				"zhpm", "xb", "dyf", "zyf", "tyf", "nlf", "xymc", "zymc",
				"bjmc" };
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq" };
		String[] queryLikeList = new String[] { "xh", "xm" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		StringBuffer sql = new StringBuffer();
		sql.append("select xh,xm,xb,dyzhf,zyzhf,tyzhf,nlzhf,zhf, ");
		sql.append("dyf,zyf,tyf,nlf,xymc,zymc,bjmc, ");
		sql.append("(rank() over(partition by xn, xq, ");
		sql.append("bjdm order by to_number(dyf) desc nulls last)) dypm, ");
		sql.append("(rank() over(partition by xn,xq, ");
		sql.append("bjdm order by to_number(zyf) desc nulls last)) zypm, ");
		sql.append("(rank() over(partition by xn,xq, ");
		sql.append("bjdm order by to_number(tyf) desc nulls last)) typm, ");
		sql.append("(rank() over(partition by xn,xq, ");
		sql.append("bjdm order by to_number(nlf) desc nulls last)) nlpm, ");
		sql.append("(rank() over(partition by xn,xq, ");
		sql.append("bjdm order by to_number(zhf) desc nulls last)) zhpm from ");
		sql.append(table);
		sql.append("order by zhpm");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),myQuery.getInputList(), colList);

		//System.out.println(sql);

		return list;
	}
	
	/**
	 * ��ý�ѧ������(һ������)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getJxjRs(ZjcmPjpyModel model) {
		
		String xn = model.getXn();
		String xq = model.getXq();
		String bjdm = model.getBjdm();
		
		DAO dao = DAO.getInstance();
		String[] colList = new String[] { "jxjmc", "num" };
		
		StringBuffer sql = new StringBuffer();
		sql.append("select jxjmc, count(xh) num from (select a.xn, a.xq, a.pjxh xh, a.xxsh,");
		sql.append("b.jxjmc, b.jxjlb, b.jxjlbmc,c.bjdm from zjcm_jxjsq a left join "); 
		sql.append("(select b.jxjdm, b.jxjmc, b.jxjlb, c.jxjlbmc from jxjdmb b, jxjlbdmb c ");
		sql.append("where b.jxjlb = c.jxjlbdm) b on a.jxjdm = b.jxjdm ");
		sql.append("left join view_xsjbxx c on a.pjxh = c.xh) ");
		sql.append("where xxsh = 'ͨ��' and xn = ? and xq = ? and bjdm = ? ");
		sql.append("and (jxjmc like '%һ��%' or jxjmc like '%����%' or jxjmc like '%����%') ");
		sql.append("group by jxjmc order by jxjmc desc");
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] { xn,xq,bjdm },
				colList);
		
		return list;
	}
	
	/**
	 * �������������
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public Boolean saveWlk(ZjcmPjpyModel model) throws Exception {
		DAO dao = DAO.getInstance();

		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String lx = model.getLx();

		StringBuffer query = new StringBuffer();
		query.append(Base.isNull(nj) ? "" : " and nj = '" + nj + "'");
		query.append(Base.isNull(xydm) ? "" : " and xydm = '" + xydm + "'");
		query.append(Base.isNull(zydm) ? "" : " and zydm = '" + zydm + "'");
		
		String sql = "delete from zjcm_wlkb a where exists(select 1 from view_njxyzybj b where a.wlkbjdm = b.bjdm"
				+ query.toString() + ")";
		boolean flg = dao.runUpdate(sql.toString(), new String[] {});
		if (flg) {
			sql = "insert into zjcm_wlkb (wlkbjdm,lx) select bjdm,'" + lx
					+ "' lx from view_njxyzybj where 1=1";
			sql += query.toString();
			flg = dao.runUpdate(sql.toString(), new String[] {});
		}
		
		return flg;
	}

	/**
	 * �������ƽ�����б�(ר��)
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSspjfList(ZjcmPjpyModel model) {
		
		DAO dao = DAO.getInstance();
		
		String xn = model.getXn();
		String xq = model.getXq();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xh = model.getXh();
		String xm = model.getXm();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select xh, round(zcj / num,3) sspjf from ");
		sql.append("(select a.xh, sum(a.cj) zcj, count(a.kcmc) num ");
		sql.append("from cjb a where a.xn = ? and a.xq = ? and exists ");
		sql.append("(select 1 from view_xsxxb b where a.xh = b.xh and ");
		sql.append("b.pycc = 'ר��' and b.xydm like '%' || ? || '%')group by xh) ");
		//System.out.println(sql);
		return dao.getList(sql.toString(), new String[] { xn, xq, xydm },
				new String[] { "xh", "sspjf" });
	}

	/**
	 * ���ƽ��ѧ�ּ����б�(����)
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPjxfjdList(ZjcmPjpyModel model) {
		
		DAO dao = DAO.getInstance();
		String xn = model.getXn();
		String xq = model.getXq();
		String xydm = model.getXydm();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select round(nvl(case when instr(pjxfjd,'.',1,1)=1 then '0'|| pjxfjd else to_char(pjxfjd) end, '0'),3) pjxfjd");
		sql.append(",xh from (select xh, case when sum(xf) is not null ");
		sql.append("and sum(xf) <> 0 then sum(zjd) / sum(xf) ");
		sql.append("else 0 end pjxfjd from (select a.xh, ");
		sql.append("a.xf, (a.xf * a.jd) zjd from cjb a ");
		sql.append("where xn = ? and xq = ? and exists ");
		sql.append("(select 1 from view_xsxxb b where a.xh = b.xh and b.pycc = '����' and b.xydm like '%' || ? || '%') ");
		sql.append(") group by xh )");
		//System.out.println(sql);
		return dao.getList(sql.toString(), new String[] { xn, xq, xydm },
				new String[] { "xh", "pjxfjd" });
	}
	
	/**
	 * ���ѧ���������Ŀ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getBjgList(ZjcmPjpyModel model) {

		DAO dao = DAO.getInstance();

		// ����ѧ��
		String xn = model.getXn();
		// ����ѧ��
		String xq = model.getXq();
		// ���ж�ѧ����Ŀ
		String[] pjxh = model.getPjxh();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select xh,count(xh) num from cjb ");
		sql.append("where xn = ? ");
		sql.append("and xq = ? ");
		sql.append("and cj < 60 ");
		
		if (pjxh != null && pjxh.length > 0) {
			for (int i = 0; i < pjxh.length; i++) {
				if (i == 0) {
					sql.append("and (xh ='" + pjxh[i] + "' ");
				} else if (i == pjxh.length - 1) {
					sql.append(" or xh ='" + pjxh[i] + "') ");
				} else {
					sql.append(" or xh ='" + pjxh[i] + "' ");
				}
			}
			if (pjxh.length == 1) {
				sql.append(" ) ");
			}
		}
		sql.append("group by xh ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq }, new String[] { "xh", "num" });

		return list;
	}
	
	/**
	 * ���ѧ��Υ�ʹ���
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjcfList(ZjcmPjpyModel model) {

		DAO dao = DAO.getInstance();

		// ����ѧ��
		String xn = model.getXn();
		// ����ѧ��
		String xq = model.getXq();
		// ���ж�ѧ����Ŀ
		String[] pjxh = model.getPjxh();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select xh,count(xh) num from wjcfb ");
		sql.append("where xn = ? ");
		sql.append("and xq = ? ");
		sql.append("and cfsj is not null ");
		sql.append("and cfwh is not null ");
		
		if (pjxh != null && pjxh.length > 0) {
			for (int i = 0; i < pjxh.length; i++) {
				if (i == 0) {
					sql.append("and (xh ='" + pjxh[i] + "' ");
				} else if (i == pjxh.length - 1) {
					sql.append(" or xh ='" + pjxh[i] + "') ");
				} else {
					sql.append(" or xh ='" + pjxh[i] + "' ");
				}
			}
			if (pjxh.length == 1) {
				sql.append(" ) ");
			}
		}
		sql.append("group by xh ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq }, new String[] { "xh", "num" });

		return list;
	}
	
	/**
	 * ���ѧ���ȼ��������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDjksList(ZjcmPjpyModel model,
			String[] pjxh) {

		DAO dao = DAO.getInstance();

		// ����ѧ��
		String xn = model.getXn();
		// ����ѧ��
		String xq = model.getXq();

		StringBuffer sql = new StringBuffer();
		sql.append("select xh, djksmc, cj from xsdjksb ");
		sql.append("where  xn = ? and xq = ?");
		
		if (pjxh != null && pjxh.length > 0) {
			for (int i = 0; i < pjxh.length; i++) {
				if (i == 0) {
					sql.append(" and (xh ='" + pjxh[i] + "' ");
				} else {
					sql.append(" or xh ='" + pjxh[i] + "' ");
				}
			}
			sql.append(")");
		}

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq }, new String[] { "xh", "djksmc", "cj" });

		return list;
	}
	
	/**
	 * ��ý�ѧ�������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjjdList(ZjcmPjpyModel model) {

		DAO dao = DAO.getInstance();

		// ����ѧ��
		String xn = model.getXn();
		// ����ѧ��
		String xq = model.getXq();
		// ����(��ѧ�������ƺ�)
		String lx = model.getLx();
		// ��ѧ����루�����ƺŴ��룩
		String jxjdm = "jxj".equalsIgnoreCase(lx) ? model.getJxjdm() : model
				.getRychdm();

		String sql = "select fjdlx, fjddm from zjcm_jdsz where "
				+ "xn= ? and xq= ? and lx = ? and jxjdm = ?";

		List<HashMap<String, String>> list = dao.getList(sql, new String[] {
				xn, xq, lx, jxjdm }, new String[] { "fjdlx", "fjddm" });

		return list;
	}
	
	/**
	 * ��ý�ѧ����ѧ��
	 * 
	 * @author luojw
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<String> getJxjjdXhList(ZjcmPjpyModel model, boolean jxjjd,
			boolean rychjd) throws SQLException {

		DAO dao = DAO.getInstance();

		// �����༶
		String bjdm = model.getBjdm();
		// ����ѧ��
		String xn = model.getXn();
		// ����ѧ��
		String xq = model.getXq();
		// ����(��ѧ�������ƺ�)
		String lx = model.getLx();
		// ��ѧ����루�����ƺŴ��룩
		String jxjdm = "jxj".equalsIgnoreCase(lx) ? model.getJxjdm() : model
				.getRychdm();
		// �Ǽ������
		String[] fjdlx = model.getFjdlx();
		// �Ǽ�ô���
		String[] fjddm = model.getFjddm();

		StringBuffer sql = new StringBuffer();

		//���ڽ�ѧ����
		if (jxjjd) {
			sql.append("select a.pjxh from zjcm_jxjsq a where exists (select 1 ");
			sql.append("from view_xsjbxx b where a.pjxh = b.xh and b.bjdm = ?) ");
			sql.append("and a.xxsh = 'ͨ��' and a.xn = ? and a.xq = ? ");

			if (fjdlx != null && fjdlx.length > 0) {
				boolean first = true;
				for (int i = 0; i < fjdlx.length; i++) {
					if ("jxj".equalsIgnoreCase(fjdlx[i])) {
						if (first) {
							sql.append("and (a.jxjdm = '" + fjddm[i] + "' ");
							first = false;
						} else {
							sql.append("or a.jxjdm = '" + fjddm[i] + "' ");
						}
					}
				}
				if (!first) {
					sql.append(") ");
				}
			}
		}
		
		if (jxjjd && rychjd) {
			sql.append("union");
		}
		
		// ���ڽ�ѧ����
		if (rychjd) {
			sql.append("select a.pjxh from zjcm_rychsqb a where exists (select 1 ");
			sql.append("from view_xsjbxx b where a.pjxh = b.xh and b.bjdm = ?) ");
			sql.append("and a.xxsh = 'ͨ��' and a.xn = ? and a.xq = ? ");

			if (fjdlx != null && fjdlx.length > 0) {
				boolean first = true;
				for (int i = 0; i < fjdlx.length; i++) {
					if ("rych".equalsIgnoreCase(fjdlx[i])) {
						if (first) {
							sql.append("and (a.rychdm = '" + fjddm[i] + "' ");
							first = false;
						} else {
							sql.append("or a.rychdm = '" + fjddm[i] + "' ");
						}
					}
				}
				if (!first) {
					sql.append(") ");
				}
			}
		}

		System.out.println(sql);
		List<String> list = dao.getList(sql.toString(), new String[] { bjdm,
				xn, xq  }, "pjxh");

		return list;
	}
	
	/**
	 * �ж��û��Ƿ��ǲ������û�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean isCpz(ZjcmPjpyModel model) {
		
		DAO dao = DAO.getInstance();
		
		boolean flag = false;
		
		String userName = model.getUserName();
		String xn = model.getXn();
		String xq = model.getXq();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) num from zjcm_cpz a where exists (select 1 ");
		sql.append("from view_bjgbxx b where a.zwdm = b.bjgbdm ");
		sql.append("and a.cpxy = b.xydm and b.xh = ?) ");
		sql.append("and xn = ? and xq = ?");

		String num = dao.getOneRs(sql.toString(), new String[] { userName, xn,
				xq }, "num");
		
		if (!Base.isNull(num) && !"0".equalsIgnoreCase(num)) {
			flag = true;
		}

		return flag;
	}
	
	/**
	 * ���潱ѧ���걨��Ϣ
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public Boolean saveJxjsb(ZjcmPjpyModel model, HttpServletRequest request)
			throws Exception {

		boolean flag = false;

		String pjxh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();
		String jxjdm = model.getJxjdm();

		String tableName = "zjcm_jxjsq";
		String pk = "pjxh||xn||xq||jxjdm";// ����
		String pkValue = pjxh + xn + xq + jxjdm;// ����ֵ

		String[] collist = new String[] { "pjxh", "xn", "xq", "jxjdm" };
		String[] vallist = new String[] { pjxh, xn, xq, jxjdm };

		flag = StandardOperation.delete(tableName, pk, pkValue, request);
		if (flag) {
			flag = StandardOperation.insert(tableName, collist, vallist,
					request);
		}

		return flag;
	}
	
	/**
	 * ��ò���������(���������ޣ�����)
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String getBjgs(ZjcmPjpyModel model) {

		DAO dao = DAO.getInstance();

		String xh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();

		StringBuffer sql = new StringBuffer();
		sql.append("select count(a.xh) num from cjb a where a.xn = ? ");
		sql.append("and a.xq = ? and a.xh = ? and a.cj < '60' ");
		sql.append("and ((a.kcxz is not null and a.kcxz <> '����'  ");
		sql.append("and a.kcxz <> '����') or a.kcxz is null) ");

		String num = dao.getOneRs(sql.toString(), new String[] { xn, xq, xh },
				"num");
		String bjgs = ("0".equalsIgnoreCase(num)) ? "��" : num + "��";

		return bjgs;
	}
	
	/**
	 * ��ñ�ѧ���Լ���ѧ�ڵĳɼ��������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCjPmQk(HashMap<String, String> map) {

		DAO dao = DAO.getInstance();
		
		String bxn = map.get("bxn"); // ��ѧ��
		String bxq = map.get("bxq"); // ��ѧ��
		String sxn = map.get("sxn"); // ��ѧ��
		String sxq = map.get("sxq"); // ��ѧ��
		String xh = map.get("xh"); // ѧ��
		
		String[] inputValue = new String[] { xh, bxn, bxq, xh, xh, sxn, sxq, xh };
		String[] outputValue = new String[] { "xq", "cjpm" };
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select 'bxq' xq, cjpm from (select a.xh,b.zcj, ");
		sql.append("(rank() over(order by to_number(b.zcj) desc nulls last)) cjpm ");
		sql.append("from (select a.xh from view_xsjbxx a where bjdm = (select b.bjdm ");
		sql.append("from view_xsjbxx b where b.xh = ? )) a left join ( ");
		sql.append("select a.xh, sum(cj) zcj from cjb a where a.xn = ? ");
		sql.append("and a.xq = ? and ((a.kcxz is not null and a.kcxz <> '����' and ");
		sql.append("a.kcxz <> '����') or a.kcxz is null) group by a.xh) b on a.xh = b.xh) ");
		sql.append("where xh = ? union all select 'sxq' xq, cjpm ");
		sql.append("from (select a.xh, b.zcj, ");
		sql.append("(rank() over(order by to_number(b.zcj) desc nulls last)) cjpm ");
		sql.append("from (select a.xh from view_xsjbxx a where bjdm = (select b.bjdm ");
		sql.append("from view_xsjbxx b where b.xh = ?)) a left join ( ");
		sql.append("select a.xh, sum(cj) zcj from cjb a where a.xn = ? ");
		sql.append("and a.xq = ? and ((a.kcxz is not null and a.kcxz <> '����' and ");
		sql.append("a.kcxz <> '����') or a.kcxz is null) group by a.xh) b on a.xh = b.xh) ");
		sql.append("where xh = ?) order by xq ");
		
		List<HashMap<String,String>> list = dao.getList(sql.toString(), inputValue, outputValue);
		
		return list;
	}
	
	/**
	 * ��ѧ���ѻ�ý�ѧ�������ƺ��б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjRyList(HashMap<String, String> map) {

		DAO dao = DAO.getInstance();

		String bxn = map.get("bxn"); // ��ѧ��
		String bxq = map.get("bxq"); // ��ѧ��
		String sxn = map.get("sxn"); // ��ѧ��
		String sxq = map.get("sxq"); // ��ѧ��
		String xh = map.get("xh"); // ѧ��

		String[] inputValue = new String[] { xh, bxn, bxq, xh, bxn, bxq, xh,
				sxn, sxq, xh, sxn, sxq };
		String[] outputValue = new String[] { "xq", "rymc" };
		
		StringBuffer sql = new StringBuffer();
		sql.append("select 'bxq' xq,jxjmc rymc from view_zjcm_jxjsq ");
		sql.append("where xh = ? and xn = ? and xq = ? and xxsh = 'ͨ��' ");
		sql.append("union all ");
		sql.append("select 'bxq' xq,rychmc rymc from view_zjcm_rychsq ");
		sql.append("where xh = ? and xn = ? and xq = ? and xxsh = 'ͨ��' ");
		sql.append("union all ");
		sql.append("select 'sxq' xq,jxjmc rymc from view_zjcm_jxjsq ");
		sql.append("where xh = ? and xn = ? and xq = ? and xxsh = 'ͨ��' ");
		sql.append("union all ");
		sql.append("select 'sxq' xq,rychmc rymc from view_zjcm_rychsq ");
		sql.append("where xh = ? and xn = ? and xq = ? and xxsh = 'ͨ��' ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;

	}
	
	/**
	 * ���ָ��ѧ��ѧ�ڵĽ�ѧ����ͳ���б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjjeList(ZjcmPjpyModel model) {

		DAO dao = DAO.getInstance();

		String xn = model.getXn();// ����ѧ��
		String xq = model.getXq();// ����ѧ��
		String jxjdm = model.getJxjdm();// ��ѧ�����
		String yhdm = model.getYhdm();// ���д���
		String xydm = model.getXydm();// ѧԺ����

		String[] inputValue = new String[] {  xn, xq, jxjdm,yhdm, xydm };
		String[] outputValue = new String[] { "xh", "xm", "xymc", "zymc",
				"jxjmc", "je", "yhkh" };

		StringBuffer sql = new StringBuffer();

		sql.append("select b.xh,b.xm,b.xymc,b.zymc, ");
		sql.append("(select c.jxjmc from jxjdmb c where a.jxjdm = c.jxjdm) jxjmc, ");
		sql.append("(select c.jlje from jxjdmb c where a.jxjdm = c.jxjdm) je, ");
		// sql.append("(select e.yhkh from view_xsxxb e where b.xh = e.xh and
		// e.yhdm = ? ) yhkh ");
		sql.append("b.yhkh from zjcm_jxjsq a, view_xsxxb b ");
		sql.append("where a.pjxh = b.xh and a.xxsh = 'ͨ��' and a.xn = ? ");
		sql.append("and a.xq = ? and a.jxjdm = ? and  ");
		sql.append("b.yhdm = ? and b.xydm like '%' || ? || '%' ");
		sql.append("order by b.xydm, b.xh ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		
		return list;
	}
	
	/**
	 * ��ý�ѧ�������ƺţ����������������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjhdList(ZjcmPjpyModel model) {
		
		DAO dao = DAO.getInstance();
		
		String bjdm = model.getBjdm();// �༶����
		String xn = model.getXn();// ����ѧ��
		String xq = model.getXq();// ����ѧ��

		String[] inputValue = new String[] { bjdm, xn, xq, xn, xq, xn, xq, xn,
				xq };
		String[] outputValue = new String[] { "xh", "xnjxj", "rychmc", "xwjxj",
				"bjgs" };
		
		StringBuffer sql = new StringBuffer();
		
		//ѧ��������Ϣ
		sql.append("select a.xh, a.xm, b.jxjmc xnjxj, c.rychmc, d.jxjmc xwjxj, e.bjgs ");
		sql.append("from (select a.xh, a.xm from view_xsjbxx a where a.bjdm = ?) a ");
		
		// У�ڽ�ѧ����Ϣ
		sql.append("left join (select t.xh, max(ltrim(sys_connect_by_path((t.jxjmc) || '', '��'), ");
		sql.append("'��')) jxjmc from (select distinct (t.xh),t.jxjmc, ");
		sql.append("row_number() over(partition by t.xh order by t.xh) pno, ");
		sql.append("row_number() over(partition by t.xh order by t.xh) - 1 sno ");
		sql.append("from (select b.pjxh xh,b.jxjdm,(select v.jxjmc ");
		sql.append("from view_jxjxx v where b.jxjdm = v.jxjdm) jxjmc ");
		sql.append("from zjcm_jxjsq b where not exists (select 1 from view_jxjxx v ");
		sql.append("where b.jxjdm = v.jxjdm and v.jxjlbmc like '%У��%') ");
		sql.append("and b.xxsh = 'ͨ��' and b.xn = ? and b.xq = ?) t ");
		sql.append("order by t.xh, t.jxjmc, pno) t start with pno = 1 ");
		sql.append("connect by prior pno = sno and prior t.xh = t.xh ");
		sql.append("group by t.xh order by t.xh) b on a.xh = b.xh ");

		// �����ƺ���Ϣ
		sql.append("left join (select t.xh,max(ltrim(sys_connect_by_path((t.rychmc) || '', '��'), ");
		sql.append("'��')) rychmc from (select distinct (t.xh),t.rychmc, ");
		sql.append("row_number() over(partition by t.xh order by t.xh) pno, ");
		sql.append("row_number() over(partition by t.xh order by t.xh) - 1 sno ");
		sql.append("from (select b.pjxh xh, b.rychdm, (select v.rychmc ");
		sql.append("from rychdmb v where b.rychdm = v.rychdm) rychmc ");
		sql.append("from zjcm_rychsqb b where exists (select 1 from rychdmb v ");
		sql.append("where b.rychdm = v.rychdm and (v.rychmc like '%����%' or ");
		sql.append("v.rychmc like '%��%��%')) and b.xxsh = 'ͨ��' ");
		sql.append("and b.xn = ? and b.xq = ?) t ");
		sql.append("order by t.xh, t.rychmc, pno) t start with pno = 1 ");
		sql.append("connect by prior pno = sno and prior t.xh = t.xh ");
		sql.append("group by t.xh order by t.xh) c on a.xh = c.xh ");

		//У�⽱ѧ����Ϣ
		sql.append("left join (select t.xh, max(ltrim(sys_connect_by_path((t.jxjmc) || '', '��'), ");
		sql.append("'��')) jxjmc from (select distinct (t.xh), ");
		sql.append("t.jxjmc,row_number() over(partition by t.xh order by t.xh) pno, ");
		sql.append("row_number() over(partition by t.xh order by t.xh) - 1 sno ");
		sql.append("from (select b.pjxh xh,b.jxjdm,(select v.jxjmc ");
		sql.append("from view_jxjxx v where b.jxjdm = v.jxjdm) jxjmc ");
		sql.append("from zjcm_jxjsq b where exists (select 1 ");
		sql.append("from view_jxjxx v where b.jxjdm = v.jxjdm ");
		sql.append("and v.jxjlbmc like '%У��%') and b.xxsh = 'ͨ��' ");
		sql.append("and b.xn = ? and b.xq = ?) t ");
		sql.append("order by t.xh, t.jxjmc, pno) t start with pno = 1 ");
		sql.append("connect by prior pno = sno and prior t.xh = t.xh ");
		sql.append("group by t.xh order by t.xh) d on a.xh = d.xh ");

		//����������
		sql.append("left join (select c.xh, count(xh) bjgs from cjb c ");
		sql.append("where c.cj < 60 and (c.kcxz is null or ");
		sql.append("(c.kcxz is not null and c.kcxz <> '����' and ");
		sql.append("c.kcxz <> '����')) and c.xn = ? ");
		sql.append("and c.xq = ? group by c.xh) e on a.xh = e.xh ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		
		return list;
	}

	/**
	 * ��ô��������б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCfmcList(ZjcmPjpyModel model) {
		
		DAO dao = DAO.getInstance();
		
		String bjdm = model.getBjdm();// �༶����
		String xn = model.getXn();// ����ѧ��
		String xq = model.getXq();// ����ѧ��

		String[] inputValue = new String[] { xn, xq };
		String[] outputValue = new String[] { "xh", "cflbmc" };
		
		StringBuffer sql = new StringBuffer();
		
		// Υ������
		sql.append("select t.xh,max(ltrim(sys_connect_by_path((t.cflbmc) || '', '��'), '��')) cflbmc ");
		sql.append("from (select distinct (t.xh), t.cflbmc, ");
		sql.append("row_number() over(partition by t.xh order by t.xh) pno, ");
		sql.append("row_number() over(partition by t.xh order by t.xh) - 1 sno ");
		sql.append("from (select a.xh,(select b.cflbmc from cflbdmb b where a.cflb = b.cflbdm) cflbmc ");
		sql.append("from wjcfb a where not exists (select 1 from wjcf_zjlg_lxckb b where a.xh = b.xh ");
		sql.append("and a.xn = b.cfxn and a.sbsj = b.cfsbsj) and a.xn = ? ");
		sql.append("and a.xq = ?) t order by t.xh, t.cflbmc, pno) t ");
		sql.append("start with pno = 1 connect by prior pno = sno and prior t.xh = t.xh ");
		sql.append("group by t.xh order by t.xh ");
	
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		
		return list;
	}
	
	/**
	 * ��õȼ�������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDjksList(ZjcmPjpyModel model) {
		
		DAO dao = DAO.getInstance();
		
		String bjdm = model.getBjdm();// �༶����
		//String xn = model.getXn();// ����ѧ��
		//String xq = model.getXq();// ����ѧ��

		String[] inputValue = new String[] { bjdm };
		String[] outputValue = new String[] { "xh", "lx", "ksqk" };
		
		StringBuffer sql = new StringBuffer();
		
		
		sql.append("select t.xh,t.lx,max(ltrim(sys_connect_by_path((t.djksmc) || '��' || (t.cj) || '',  ");
		sql.append("'��'),  '��')) ksqk  from (select distinct (t.xh),  ");
		sql.append("t.lx, t.kcm,t.djksmc, t.cj,     ");
		sql.append("row_number() over(partition by t.xh, t.lx order by t.xh, t.lx) pno,  ");
		sql.append("row_number() over(partition by t.xh, t.lx order by t.xh, t.lx) - 1 sno  ");
		sql.append("from (select a.xh, a.lx, a.kcm, a.djksmc, a.cj from view_zjcm_djksxx a  ");
		sql.append("where exists (select 1 from view_xsjbxx b where a.xh = b.xh  ");
		sql.append("and b.bjdm = ?)) t order by t.xh, t.lx, t.kcm, t.djksmc, t.cj, pno) t  ");
		sql.append("start with pno = 1 connect by prior pno = sno and prior t.xh = t.xh  ");
		sql.append("and prior t.lx = t.lx group by t.xh, t.lx order by t.xh  ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		
		return list;
	}
	
	/**
	 * ���ѧԺ�༶�����б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXyBjrsList(ZjcmPjpyModel model) {
		
		DAO dao = DAO.getInstance();
		
		String xydm = model.getXydm();// ѧԺ����

		String[] inputValue = new String[] { xydm };
		String[] outputValue = new String[] { "xymc", "bjmc", "num" };
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select a.*, nvl(b.num,0) num from (select a.xydm, a.xymc, a.zydm, a.zymc,   ");
		sql.append("a.bjdm, a.bjmc from view_njxyzybj a where a.xydm = ?) a  ");
		sql.append("left join (select bjdm, count(xh) num from view_xsjbxx   ");
		sql.append("group by bjdm) b on a.bjdm = b.bjdm  ");
		sql.append("order by a.xydm, a.zydm, a.bjdm  ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		
		return list;
	}
	
	/**
	 * ��ý�ѧ�������ƺţ������б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjRychTjList(ZjcmPjpyModel model) {
		
		DAO dao = DAO.getInstance();
		
		String xn = model.getXn();// ѧ��
		String xq = model.getXq();// ѧ��
		
		String[] inputValue = new String[] { xn, xq, xn, xq };
		String[] outputValue = new String[] { "tjzd", "tjz", "jxjmc" };
		
		StringBuffer sql = new StringBuffer();
		
		//��ѧ��
		sql.append("select a.tjzd,a.tjz, ");
		sql.append("(select b.jxjmc from jxjdmb b where a.jxjdm = b.jxjdm) jxjmc ");
		sql.append("from view_zjcm_pjpy_tjsz a  ");
		sql.append("where a.xn = ? and a.xq = ? and a.lx = 'jxj' ");
		
		sql.append("union all ");
		
		//�����ƺ�
		sql.append("select a.tjzd,a.tjz, ");
		sql.append("(select b.rychmc from rychdmb b where a.jxjdm = b.rychdm) jxjmc ");
		sql.append("from view_zjcm_pjpy_tjsz a  ");
		sql.append("where a.xn = ? and a.xq = ? and a.lx = 'rych' ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		
		return list;
	}
	
	/**
	 * ���潱ѧ�������ƺţ�����������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveJxjQtJd(String xn, String xq, String[] fjdlx,
			String[] fjddm, String lx, String jxjdm, String tableName)
			throws Exception {

		boolean flag = false;

		// ɾ��
		StringBuilder del = new StringBuilder();
		// ���
		StringBuilder insert = new StringBuilder();

		if (fjdlx != null && fjdlx.length > 0) {

			String[] delSql = new String[fjdlx.length];
			String[] insertSql = new String[fjdlx.length];
			
			for (int i = 0; i < fjdlx.length; i++) {

				del = new StringBuilder();
				del.append(" delete from ");
				del.append(tableName);
				del.append(" where xn = '" + xn + "' ");
				del.append(" and xq = '" + xq + "' ");
				del.append(" and lx = '" + fjdlx[i] + "' ");
				del.append(" and jxjdm = '" + fjddm[i] + "' ");
				del.append(" and fjdlx = '" + lx + "' ");
				del.append(" and fjddm = '" + jxjdm + "' ");

				delSql[i] = del.toString();
				
				insert = new StringBuilder();
				insert.append("insert into ");
				insert.append(tableName);
				insert.append("(xn,xq,lx,jxjdm,fjdlx,fjddm) ");
				insert.append("values( ");
				insert.append("'" + xn + "'");
				insert.append(",'" + xq + "'");
				insert.append(",'" + fjdlx[i] + "'");
				insert.append(",'" + fjddm[i] + "'");
				insert.append(",'" + lx + "'");
				insert.append(",'" + jxjdm + "'");
				insert.append(") ");
				
				insertSql[i] = insert.toString();
			}

			flag = saveArrDate(delSql);
			
			if(flag){
				flag = saveArrDate(insertSql);
			}
		}

		return flag;
	}
	
	/**
	 * ������ѧ�������ƺţ�����������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean cxCxjQtJd(String xn, String xq, String lx, String jxjdm,
			String tableName) throws Exception {

		boolean flag = false;

		DAO dao = DAO.getInstance();

		// ɾ��
		StringBuilder del = new StringBuilder();

		del = new StringBuilder();
		del.append(" delete from ");
		del.append(tableName);
		del.append(" where xn = ? ");
		del.append(" and xq = ? ");
		del.append(" and fjdlx = ? ");
		del.append(" and fjddm = ? ");

		flag = dao
				.runUpdate(del.toString(), new String[] { xn, xq, lx, jxjdm });

		return flag;
	}
	
	// -----------���� made by luojw----------------
	
		/**
	 * ��ѯҪ����Ľ�ѧ�������Ϣ
	 * @return
	 */
	public List<HashMap<String, String>> queryDmList() {
		DAO dao = DAO.getInstance();
		return dao.getList(new StringBuilder("select dm,mc,lx from (")
								.append("select a.jxjdm dm,a.jxjmc mc,'1' lx from jxjdmb a,jxjlbdmb b where a.jxjlb=b.jxjlbdm and b.jxjlbmc like 'У�ڽ�%'  union ")
								.append("select a.jxjdm dm,a.jxjmc mc,'4' lx from jxjdmb a,jxjlbdmb b where a.jxjlb=b.jxjlbdm and b.jxjlbmc like '%����%' union ")
								.append("select a.jxjdm dm,a.jxjmc mc,'5' lx from jxjdmb a,jxjlbdmb b where a.jxjlb=b.jxjlbdm and b.jxjlbmc like 'У��%' union ")
								.append("select rychdm dm,rychmc mc,'2' lx from rychdmb where rychmc ='����ѧ��' union ")
								.append("select rychdm dm,rychmc mc,'3' lx from rychdmb where rychmc like '����ѧ���ɲ�%'")
								.append(") order by lx,dm").toString(), new String[]{}, new String[]{"dm", "mc", "lx"});
	}
	
	/**
	 * ��ѯѧ�����еĻ񽱼�¼
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjjehzxx(ZjcmPjpyModel model) throws Exception{
		StringBuilder sql = new StringBuilder("select * from (")
		        .append("select a.xh,xn,xq,dm,mc,key,jlje,b.xydm,b.nj,b.xm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,(select yhkh from xsxxb b where a.xh=b.xh) yhkh,")
		        .append("(select yhdm from xsxxb b where a.xh=b.xh) yhlx from (")
		        .append("select a.pjxh xh,a.xn,a.xq,a.jxjdm dm,b.jxjmc mc,'jxj' key,jlje from zjcm_jxjsq a,jxjdmb b where a.jxjdm=b.jxjdm and xxsh='ͨ��' and xn=? and xq=?")
		        .append(" union all")
		        .append(" select a.pjxh xh,a.xn,a.xq,a.rychdm dm,b.rychmc mc,'rych' key,0 jlje from zjcm_rychsqb a,rychdmb b where a.rychdm=b.rychdm and xxsh='ͨ��' and xn=? and xq=?")
		        .append(") a,view_xsjbxx b where a.xh=b.xh)");
		//�꼶���������Ϊ��̬ƴ����
		String[] queryList = new String[] { "nj", "yhlx"};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, null, model);
		String[] queryInputList = queryObject.getInputList();
		String[] inputList = new String[4 + (queryInputList != null ? queryInputList.length : 0)];
		//ѧ��,ѧ������Ϊ��ѡ��
		inputList[0] = model.getXn();
		inputList[1] = model.getXq();
		inputList[2] = model.getXn();
		inputList[3] = model.getXq();
		if (queryInputList != null && queryInputList.length > 0) {
			for (int i=0;i<queryInputList.length;i++) {
				inputList[i+4] = queryInputList[i];
			}
		}
		String[] colList = new String[] { "bjmc", "xh", "xm", "dm", "mc", "jlje", "yhkh", "key", "yhlx"};
		return CommonQueryDAO.commonQueryNotFy(sql.toString(),
				queryObject.getQueryString() + " order by xydm,xh,key,dm", inputList,
				colList, model);
	}
	
	/**
	 * ��ѯ���л񽱼�¼��ѧ����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjjeXhxx(ZjcmPjpyModel model) throws Exception{
		StringBuilder sql = new StringBuilder("select distinct xh,xm,bjmc,yhkh from (")
		        .append("select a.xh,xn,xq,dm,mc,key,jlje,b.xydm,b.nj,b.xm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,(select yhkh from xsxxb b where a.xh=b.xh) yhkh,")
		        .append("(select yhdm from xsxxb b where a.xh=b.xh) yhlx from (")
		        .append("select a.pjxh xh,a.xn,a.xq,a.jxjdm dm,b.jxjmc mc,'jxj' key,jlje from zjcm_jxjsq a,jxjdmb b where a.jxjdm=b.jxjdm and xxsh='ͨ��' and xn=? and xq=?")
		        .append("union all")
		        .append(" select a.pjxh xh,a.xn,a.xq,a.rychdm dm,b.rychmc mc,'rych' key,0 jlje from zjcm_rychsqb a,rychdmb b where a.rychdm=b.rychdm and xxsh='ͨ��' and xn=? and xq=?")
		        .append(") a,view_xsjbxx b where a.xh=b.xh)");
		//�꼶���������Ϊ��̬ƴ����
		String[] queryList = new String[] { "nj", "yhlx"};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, null, model);
		String[] queryInputList = queryObject.getInputList();
		String[] inputList = new String[4 + (queryInputList != null ? queryInputList.length : 0)];
		//ѧ��,ѧ������Ϊ��ѡ��
		inputList[0] = model.getXn();
		inputList[1] = model.getXq();
		inputList[2] = model.getXn();
		inputList[3] = model.getXq();
		if (queryInputList != null && queryInputList.length > 0) {
			for (int i=0;i<queryInputList.length;i++) {
				inputList[i+4] = queryInputList[i];
			}
		}
		String[] colList = new String[] {"xh", "xm", "bjmc", "yhkh"};
		return CommonQueryDAO.commonQueryNotFy(sql.toString(),
				queryObject.getQueryString() + " order by bjmc,xh", inputList,
				colList, model);
	}
}
