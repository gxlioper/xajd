package xgxt.pjpy;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.zjcm.ZjcmPjpyModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

public class PjpyTyDAO extends CommonQueryDAO {

	// ---------------���� made by luojw-------------
	
	/**
	 * ���������������б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getPjpyListInfo(String tableName, Object model,
			String[] colList,String other_query) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq", "lx", "pycc", "jxjdm", "rychdm", "xxsh", "xqdm",
				"lddm", "cs", "qsh" };
		
		String[] queryLikeList = new String[] { "xh", "xm" };
		
		MakeQuery myQuery = new MakeQuery();
		
		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String query = myQuery.getQueryString();
		
		other_query = Base.isNull(other_query) ? "" : other_query;
		
		if(!Base.isNull(query)){
			query += " "+other_query;
		}else{
			query = other_query;
		}

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}
	
	/**
	 * ���������������б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getPjpyListInfo(PjpyTyForm model,
			String[] colList,String other_query) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		
		StringBuilder sql=new StringBuilder();
		
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq", "lx", "pycc", "jxjdm", "rychdm", "xxsh", "xqdm",
				"lddm", "cs", "qsh" };
		
		String[] queryLikeList = new String[] { "xh", "xm" };
		
		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String ksrq=model.getKsrq();
		
		String jsrq=model.getJsrq();
		
		String query = myQuery.getQueryString();
		
		sql.append(" select rownum r,a.* from(select a.xh || b.xn || b.xq pk,a.xh,a.xb,a.xm,a.nj,a.xydm,");
		sql.append(" a.xymc, a.zydm,a.zymc,a.bjdm,a.bjmc, a.sfzh,a.xqdm,");
		sql.append(" a.xqmc,a.lddm,a.ldmc,a.cs,a.qsh,a.ssbh,b.xn, b.xq,");
		sql.append(" (select c.xqmc from xqdzb c where b.xq = c.xqdm) xqm,");
		sql.append(" b.mrz,nvl(case  when instr(to_char(b.jiaf), '.', 1, 1) = 1 then '0' || to_char(b.jiaf)");
		sql.append(" else to_char(b.jiaf)  end,'0') jiaf,");
		sql.append(" nvl(case when instr(to_char(b.jianf), '.', 1, 1) = 1 then '0' || to_char(b.jianf)");
		sql.append(" else  to_char(b.jianf) end, '0') jianf,");
		sql.append(" nvl(case  when instr(to_char(b.mrz + b.jiaf - b.jianf), '.', 1, 1) = 1 then");
		sql.append(" '0' || to_char(b.mrz + b.jiaf - b.jianf)");
		sql.append(" else  to_char(b.mrz + b.jiaf - b.jianf) end, '0') cxf");
		sql.append(" from view_zjjt_cxfsbxs a,");
		sql.append(" (select a.xh,a.xn,a.xq,nvl((select b.mrz from zjjt_cxf_sz b");
		sql.append(" where a.xn = b.xn  and a.xq = b.xq), 0) mrz,");
		sql.append(" sum(jiaf) jiaf,sum(jianf) jianf");
		sql.append(" from (select xh, xn,xq,");
		sql.append(" case when jjf = '�ӷ�' then  fz else '0' end jiaf,");
		sql.append(" case when jjf = '����' then  fz  else '0' end jianf");
		sql.append(" from (select b.pjxh xh, b.xn, b.xq, b.jjf, b.fz");
		sql.append(" from zjjt_cxflrb b where 1=1  ");
		
		if(!Base.isNull(ksrq)){
			sql.append(" and rq >= '"+ksrq+"' ");
		}
		
		if(!Base.isNull(jsrq)){
			sql.append(" and rq <= '"+jsrq+"' ");
		}
		
		sql.append(" )) a  group by a.xh, a.xn, a.xq) b ");
		sql.append(" where a.xh = b.xh)a ");
		
		other_query = Base.isNull(other_query) ? "" : other_query;
		
		if(!Base.isNull(query)){
			query += " "+other_query;
		}else{
			query = other_query;
		}
		
		return CommonQueryDAO.commonQuery(sql.toString(), query, myQuery.getInputList(), colList, model);

	}
	
	/**
	 * ����������������Ϣ
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getPjpyInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * �����ά��������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * @param msg(��ʾ��Ϣ)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getPjpyList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();

		return dao.getWhList(tableName, dm, mc, msg, pk, pkValue);
	}
	
	/**
	 * �������ά��������ֵ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("sflx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��" };
		} else if ("bblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "zhbnoyj", "zhbyj", "zhblxy", "jxjjetj",
					"jxjhj", "rychhj", "jxjjehz" };
			mc = new String[] { "�ۺϲ�����(������Ӣ�������ȼ�)", "�ۺϲ�����(����Ӣ�������ȼ�)",
					"ѧԺ�ۺϲ���������", "��ѧ����ͳ��", "��ѧ�������", "�����ƺŻ�����", "��ѧ������ܱ�" };
		} else if ("wlk".equalsIgnoreCase(lx)) {
			dm = new String[] { "�Ŀ�", "���", "������" };
			mc = new String[] { "�Ŀ�", "���", "������" };
		} else if ("tjlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "", ">", ">=", "=", "<=", "<" };
			mc = new String[] { "-----��ѡ��-----", ">", ">=", "=", "<=", "<" };
		} else if ("ywlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "", "��", "��" };
			mc = new String[] { "-----��ѡ��-----", "��", "��" };
		} else if ("kglx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��" };
		} else if ("jxjorrych".equalsIgnoreCase(lx)) {
			dm = new String[] { "jxj", "rych" };
			mc = new String[] { "��ѧ��", "�����ƺ�" };
		}else if ("over".equalsIgnoreCase(lx)) {
			dm = new String[] { "δ��", "����" };
			mc = new String[] { "δ��", "����" };
		}else if ("zz".equalsIgnoreCase(lx)) {
			dm = new String[] { "�����", "δ���" };
			mc = new String[] { "�����", "δ���" };
		} else if ("xblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "Ů" };
			mc = new String[] { "��", "Ů" };
		} else if ("fplx".equalsIgnoreCase(lx)) {
			dm = new String[] { "δ����", "�ѷ���" };
			mc = new String[] { "δ����", "�ѷ���" };
		}
		
		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * ���ָ�����ָ���ֶ�
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk, String pkValue) {
		
		DAO dao = DAO.getInstance();

		return dao.getOneValue(tableName, dm, pk, pkValue);
	}

	/**
	 * ��ð༶����
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getBjRs(String bjdm) {
		DAO dao = DAO.getInstance();
		String[] colList = new String[] { "bjdm", "bjmc", "num" };
		String sql = "select bjdm,bjmc,count(xh) num from view_xsjbxx where bjdm = ? group by bjdm,bjmc";
		HashMap<String, String> map = dao.getMap(sql, new String[] { bjdm },
				colList);
		return map;
	}
	
	/**
	 * �����ύ
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql)
			throws Exception {

		DAO dao = new DAO();
		boolean flag = true;
		int[] res = dao.runBatch(sql);

		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		return flag;
	}
	
	/**
	 * ��ò����������Ϣ(��ýѧԺ)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getCpzInfoList(String xn,String xq,String xydm) {
		
		DAO dao = DAO.getInstance();
		String[] colList = new String[] { "zwdm", "zwmc", "zhfkg", "jxjkg" };
		StringBuffer sql = new StringBuffer();
		
		sql.append("select a.zwdm,a.zhfkg, a.jxjkg,");
		sql.append("(select b.bjgbmc from sxjy_bjgbzlb b where b.bjgbdm = a.zwdm) zwmc ");
		sql.append("from zjcm_cpz a ");
		sql.append("where a.xn = ? and a.xq = ? and a.cpxy = ?");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xydm }, colList);
		return list;
	}
	
	/**
	 * ��ý�ѧ���б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getJxjList(String query) {

		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "jxjdm", "jxjmc" };

		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select a.jxjdm, a.jxjmc, ");
		sql.append("a.jxjlb, b.jxjlbmc  from jxjdmb a ");
		sql.append("left join jxjlbdmb b on a.jxjlb = b.jxjlbdm) ");
		sql.append("where jxjlbmc like '%'||?||'%' ");
		sql.append("order by to_number(jxjdm)");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { query }, colList);
		
		return list;
	}
	
	/**
	 * ���ָ��ѧԺ��רҵ���༶��ѧ��ѧ��
	 * 
	 * @author luojw
	 * @throws SQLException 
	 */
	public String[] getZdXh(String lx, String dm) throws SQLException {
		
		DAO dao = DAO.getInstance();
		
		String[] xh = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select xh from view_xsjbxx ");
		sql.append("xy".equalsIgnoreCase(lx) ? "where xydm = ?" : "");
		sql.append("zy".equalsIgnoreCase(lx) ? "where zydm = ?" : "");
		sql.append("bj".equalsIgnoreCase(lx) ? "where bjdm = ?" : "");
		sql.append("xh".equalsIgnoreCase(lx) ? "where a.bjdm = (select b.bjdm from view_xsjbxx b where b.xh = ?)" : "");
		
		List<String> xhList = dao.getList(sql.toString(), new String[]{dm}, "xh");
		
		if(xhList != null && xhList.size()>0){
			xh = new String[xhList.size()];
			for (int i = 0; i < xhList.size(); i++) {
				xh[i] = xhList.get(i);
			}
		}
		
		return xh;
	}
	
	/**
	 * ����������������б�
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getTjList() {

		DAO dao = DAO.getInstance();

		String sql = "select zdmc dm, zdsm mc from jxjtjzdb order by lsh";

		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	/**
	 * �����꼶������ѧԺ
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getXyList(String nj) {

		DAO dao = DAO.getInstance();

		String sql = "select distinct xydm dm, xymc mc, nj from view_njxyzybj where nj = ? order by xydm, nj";

		return dao.getList(sql, new String[] {nj}, new String[] { "dm", "mc" });
	}
	
	/**
	 * �����꼶������ѧԺ
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public String sswrValue(String value) {

		String num = "";
		
		if (!Base.isNull(value)) {

			DAO dao = DAO.getInstance();

			if (!Base.isNull(value)) {
				StringBuilder sql = new StringBuilder();
				sql.append("select nvl(case when instr(to_char(round('" + value
						+ "',2)),'.',1,1)=1 then ");
				sql.append("'0'||to_char(round('" + value + "',2)) ");
				sql.append(" else to_char(round('" + value
						+ "',2)) end, '0')  num from dual ");

				num = dao.getOneRs(sql.toString(), new String[] {}, "num");
			}

			num = setNumZero(num);
		}

		return num;
	}
	
	/**
	 * @param num
	 * @return
	 */
	public String setNumZero(String num) {
		int pointNum = 0;

		for (int i = 0; i < num.length(); i++) {

			if (".".equalsIgnoreCase(String.valueOf(num.charAt(i)))) {
				pointNum = i;
			}

		}

		if (pointNum == 1 && num.length() == 3) {
			num += "0";
		} else if (pointNum == 2 && num.length() == 4) {
			num += "0";
		} else if (pointNum == 0) {
			num += ".00";
		}
		return num;
	}
	// ---------------���� made by luojw-------------
	
	/**
	 * ��ѯ��ѧԺѧ�ڽ�ѧ�������
	 * @author lr
	 * @param userType
	 * @param ZjcmPjpyModel model
	 * @return List<String[]>
	 */
	public List<String[]> queryXyList(ZjcmPjpyModel model) {
		DAO dao = DAO.getInstance();
		String sql = "";
		
		sql = StringUtils.joinStr( "select xydm,xymc,jxjdm,rs from (",
				"select xydm,xymc,jxjdm," ,
				"(select count(*) from view_pjpy_zjcm_xsjxjb b where a.jxjdm=b.jxjdm and a.xydm=b.xydm and b.xn=? and b.xq=? and b.xxsh='ͨ��') rs " ,
				"from (" ,
					"select distinct a.xydm,a.xymc,b.jxjdm  from view_njxyzybj a," ,
					"(select jxjdm,jxjmc from jxjdmb a,jxjlbdmb b where a.jxjlb=b.jxjlbdm ) b" ,
				") a" ,
				") where rs is not null and rs <>'0'");
		if (!Base.isNull(model.getXydm())) {
			sql = StringUtils.joinStr(sql," and xydm='",model.getXydm(),"'");
		} 
		return dao.rsToVator(sql, new String[] { model.getXn(),model.getXq() }, 
					new String[] { "xydm", "xymc","jxjdm", "rs" });
	}
	
	/**
	 * ��ѯ����ѧ�������
	 * @author lr
	 * @param userType
	 * @param ZjcmPjpyModel model
	 * @return List<String[]>
	 */
	public List<String[]> queryJxjmcjrs(ZjcmPjpyModel model) {
		DAO dao = DAO.getInstance();
		String sql =StringUtils.joinStr("select jxjdm,jxjmc,rs from (" ,
								"select jxjdm,jxjmc," ,
								"(select count(*) from view_pjpy_zjcm_xsjxjb c where a.jxjdm=c.jxjdm and c.xn=? and c.xq=? and c.xxsh='ͨ��'");
		//ѡ����ѧԺ 
		sql += !Base.isNull(model.getXydm()) ? StringUtils.joinStr(" and c.xydm='" , model.getXydm() , "'") : "";
		
		sql += StringUtils.joinStr(") rs from jxjdmb a,jxjlbdmb b where a.jxjlb=b.jxjlbdm) where rs is not null and rs <>'0' ");
		return dao.rsToVator(sql, new String[] { model.getXn(),model.getXq() }, 
				new String[] { "jxjdm", "jxjmc", "rs" });
	}
	
	/**
	 * ��ѯ����ѧ�������
	 * @author lr
	 * @param userType
	 * @param ZjcmPjpyModel model
	 * @return List<String[]>
	 */
	public List<String[]> queryJxjhjxmxx(ZjcmPjpyModel model) {
		DAO dao = DAO.getInstance();
		String sql = StringUtils.joinStr("select jxjdm,xydm,xm from view_pjpy_zjcm_xsjxjb where xn=? and xq=? and xxsh='ͨ��' ",
							!Base.isNull(model.getXydm()) ? " and xydm='" + model.getXydm() + "'" : ""," order by jxjdm,xydm,xm");
		
		return dao.rsToVator(sql.toString(), new String[] { model.getXn(), model.getXq() }, 
				new String[] {"jxjdm", "xydm", "xm"});
	}
	
	/**
	 * ��ѯ��ѧԺѧ�������ƺŻ�����
	 * @author lr
	 * @param userType
	 * @param ZjcmPjpyModel model
	 * @return List<String[]>
	 */
	public List<String[]> queryRychXyList(ZjcmPjpyModel model) {
		DAO dao = DAO.getInstance();
		String sql = "";
		
		sql = StringUtils.joinStr( "select xydm,xymc,rychdm,rs from (",
				"select xydm,xymc,rychdm," ,
				"(select count(*) from view_zjcm_rychsq b where a.rychdm=b.rychdm and a.xydm=b.xydm and b.xn=? and b.xq=? and b.xxsh='ͨ��') rs " ,
				"from (" ,
					"select distinct a.xydm,a.xymc,b.rychdm  from view_njxyzybj a," ,
					"(select a.rychdm,a.rychmc from rychdmb a,zjcm_rychsqb b where a.rychdm=b.rychdm and b.rychdm=?) b" ,
				") a" ,
				") where rs is not null and rs <>'0'");
		if (!Base.isNull(model.getXydm())) {
			sql = StringUtils.joinStr(sql," and xydm='",model.getXydm(),"'");
		} 
		return dao.rsToVator(sql, new String[] { model.getXn(),model.getXq(), model.getRychdm() }, 
					new String[] { "xydm", "xymc","rychdm", "rs" });
	}
	
	/**
	 * ��ѯ�����ƺŻ�����
	 * @author lr
	 * @param userType
	 * @param ZjcmPjpyModel model
	 * @return List<String[]>
	 */
	public List<String[]> queryRychhjxmxx(ZjcmPjpyModel model) {
		DAO dao = DAO.getInstance();
		String sql = StringUtils.joinStr("select rychdm,xydm,xm from view_zjcm_rychsq where xn=? and xq=? and rychdm=? and xxsh='ͨ��' ",
							!Base.isNull(model.getXydm()) ? " and xydm='" + model.getXydm() + "'" : ""," order by rychdm,xydm,xm");
		
		return dao.rsToVator(sql.toString(), new String[] { model.getXn(), model.getXq(), model.getRychdm()}, 
				new String[] {"rychdm", "xydm", "xm"});
	}
	
	/**
	 * ��ѯ��������б�
	 * @return
	 */
	public List<HashMap<String, String>> queryYhlxList() {
		DAO dao = DAO.getInstance();
		return dao.getList("select yhdm dm,yhmc mc from dmk_yh order by yhdm", new String[]{}, new String[]{"dm", "mc"});
	}
	
}
