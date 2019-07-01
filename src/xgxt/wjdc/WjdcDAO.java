package xgxt.wjdc;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.wjdc.WjdcForm;
import xgxt.utils.MakeQuery;

public class WjdcDAO extends WjdcTyDAO {

	/**
	 * ������������
	 * 
	 * @author luojw
	 */
	public String getStbh() {
		
		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select d.stbh from (select rownum num, c.stbh, c.tempstbh ");
		sql.append("from (select a.stbh, (to_char(b.stbh) - to_char(a.stbh)) tempstbh ");
		sql.append("from (select rownum num, t.stbh from (select t.stbh from wjdc_stxxb t order by stbh) t) a, ");
		sql.append("(select rownum - 1 num, t.stbh from (select t.stbh from wjdc_stxxb t order by stbh) t) b ");
		sql.append("where a.num = b.num) c where c.tempstbh > 1) d where d.num = 1 ");
		
		String bzdm1 = dao.getOneRs(sql.toString(), new String[] {}, "stbh");
		
		int newDm = 0;
		if (bzdm1 != null && !"".equals(bzdm1)) {
			newDm = Integer.parseInt(bzdm1) + 1;
		}
		
		sql = new StringBuilder();
		sql.append(" select MAX(t.stbh)+1 stbh from wjdc_stxxb t");
		String bzdm2 = dao.getOneRs(sql.toString(), new String[] {}, "stbh");
		
		if (bzdm2 == null || "".equals(bzdm2)) {
			newDm = 1;
		}
		if ((bzdm1 == null || "".equals(bzdm1))
				&& (bzdm2 != null && !"".equals(bzdm2))) {
			newDm = Integer.parseInt(bzdm2);
		}
		String str = String.valueOf(newDm);
		
		if (str.length() == 1) {
			str = "000" + str;
		} else if (str.length() == 2) {
			str = "00" + str;
		} else if (str.length() == 3) {
			str = "0" + str;
		}
		return str;
	}
	
	/**
	 * ��������Ϣ�б�(��ȥ�Ѿ�����)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTkxxList(String[] inputValue) {
		
		DAO dao = DAO.getInstance();

		// �ļ�����
		String wjmc = inputValue[0];
		// ��������
		String stlx = inputValue[1];
		// ��������
		String stss = inputValue[2];
		// ������ʼʱ��
		String kssj = inputValue[3];
		// ��������ʱ��
		String jssj = inputValue[4];
		// ģ������
		String mklx = inputValue[5];
		
		StringBuilder sql = new StringBuilder();
		sql.append("select '' dm, '----��ѡ��-----'mc from dual union ");
		sql.append("select a.stbh dm, a.xsmc mc from view_wjdc_stxx a ");
		sql.append("where 1=1 ");
		if (!Base.isNull(wjmc)) {
			sql.append("and not exists (select 1 from wjdc_zjb b ");
			sql.append("where a.stbh = b.fpbh and b.id = '" + wjmc + "') ");
		}
		sql.append(Base.isNull(stlx) ? "" : "and a.stlx = '" + stlx + "'");
		sql.append(Base.isNull(stss) ? "" : "and a.stss = '" + stss + "'");
		sql.append(Base.isNull(kssj) ? "" : "and a.jlsj >= '" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : "and a.jlsj <= '" + jssj + "'");
		sql.append(Base.isNull(mklx) ? "" : "and a.mklx = '" + mklx + "'");
		sql.append(" order by dm nulls first");
		
		String[] outputValue = new String[] { "dm", "mc" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, outputValue);

		return list;
	}
	
	/**
	 * ���ָ���ʾ���������
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWjstList(String wjmc) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.stbh dm, a.xsmc mc from view_wjdc_stxx a, ");
		sql.append("wjdc_zjb b where a.stbh = b.fpbh and b.id = ? ");
		sql.append("order by to_number(b.zjsx)");
		// sql.append("where exists (select 1 from wjdc_zjb b ");
		// sql.append("where a.stbh = b.fpbh and b.id = ?) ");
		// sql.append("order by zjsx ");

		String[] inputValue = new String[] { wjmc };
		String[] outputValue = new String[] { "dm", "mc" };

		List<HashMap<String, String>> list = null;
		if (!Base.isNull(wjmc)) {
			list = dao.getList(sql.toString(), inputValue, outputValue);
		}

		return list;
	}
	
	/**
	 * ���ָ���ʾ����������
	 * 
	 * @author luojw
	 */
	public List<HashMap<String,String>> getWjstlxList(String id){

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct (select c.mc from wjdc_stxxb b,wjdc_stlxb c ");
		sql.append("where a.fpbh = b.stbh and b.stlx=c.dm) lxmc, ");
		sql.append("(select b.stlx from wjdc_stxxb b where a.fpbh = b.stbh) lxdm ");
		sql.append("from wjdc_zjb a where a.id = ? order by lxmc");
		
		String[] inputValue = new String[] { id };
		String[] outputValue = new String[] { "lxdm","lxmc" };

		List<HashMap<String,String>> list = dao
				.getList(sql.toString(), inputValue, outputValue);

		return list;
	}
	
	/**
	 * ���ָ���ʾ��������������
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWjstmcList(String id, String stlx){

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.fpbh stbh, b.stmc,nvl(b.stss,'��') stss from wjdc_zjb a, wjdc_stxxb b ");
		sql.append("where a.id = ? and a.fpbh = b.stbh and b.stlx = ? ");
		sql.append("order by b.stss, to_number(a.zjsx)");

		String[] inputValue = new String[] { id, stlx };
		String[] outputValue = new String[] { "stbh", "stmc", "stss" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * ����������Ϣ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWjstdaList(String id, String stlx){

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.stbh, a.dabh, a.damc, a.bzda from wjdc_stdab a ");
		sql.append("where exists (select 1 from wjdc_zjb b where a.stbh = b.fpbh ");
		sql.append("and b.id = ?) ");
		sql.append("and exists (select 1 from wjdc_stxxb c where a.stbh = c.stbh ");
		sql.append("and c.stlx = ?) order by a.stbh,a.dabh ");

		String[] inputValue = new String[] { id, stlx };
		String[] outputValue = new String[] { "stbh","dabh", "damc", "bzda" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}

	/**
	 * ����ʾ�ش����
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> setWjHdInfo(WjdcForm model) {

		DAO dao = DAO.getInstance();

		// ѧ��ְ����
		String xhzgh = model.getXhzgh();
		// �ش�������
		String lx = model.getLx();
		// �ʾ���
		String wjbh = model.getWjbh();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.fpbh, a.dabh,a.danr, ");
		sql.append("(select b.stlx from wjdc_stxxb b where a.fpbh = b.stbh) stlx ");
		sql.append("from wjdc_hdb a ");
		sql.append(" where a.xhzgh = ? ");
		sql.append(" and a.lx = ? ");
		sql.append(" and a.wjbh = ? ");
		sql.append(" order by stlx,fpbh ");

		String[] inputValue = new String[] { xhzgh, lx, wjbh };
		String[] outputValue = new String[] { "fpbh", "dabh", "danr" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * ��������ͳ��
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDaTjList(WjdcForm model) {

		DAO dao = DAO.getInstance();

		// �ʾ���
		String id = model.getId();
		// ͳ���꼶
		String nj = model.getNj();
		// ͳ��ѧԺ
		String xydm = model.getXydm();
		// ͳ��רҵ
		String zydm = model.getZydm();
		// ͳ�ư༶
		String bjdm = model.getBjdm();
		// ͳ���Ա�
		String xb = model.getXb();
		// ͳ��������ò
		String zzmm = model.getZzmm();
		// ͳ������
		StringBuilder query = new StringBuilder();

		query.append(" and exists (select 1 from view_stu_details t where t.xh = d.xhzgh ");
		query.append(Base.isNull(nj) ? "" : " and t.nj = '" + nj + "'");
		query.append(Base.isNull(xydm) ? "" : " and t.xydm = '" + xydm + "'");
		query.append(Base.isNull(zydm) ? "" : " and t.zydm = '" + zydm + "'");
		query.append(Base.isNull(bjdm) ? "" : " and t.bjdm = '" + bjdm + "'");
		query.append(Base.isNull(xb) ? "" : " and t.xb = '" + xb + "'");
		query.append(Base.isNull(zzmm) ? "" : " and t.zzmmm = '" + zzmm + "'");
		query.append(") ");

		StringBuilder sql = new StringBuilder();

		sql.append("select a.stbh,a.stlx,(select e.mc from wjdc_stlxb e where a.stlx = e.dm) lxmc, ");
		sql.append("b.dabh,nvl(c.num, 0) num,nvl((select count(1) rs from (select distinct d.xhzgh, d.lx, d.wjbh ");
		sql.append("from wjdc_hdb d where d.wjbh = ? ");
		sql.append(query);
		sql.append(") group by wjbh),0) rs from (select a.fpbh stbh, ");
		sql.append("(select b.stlx from wjdc_stxxb b where a.fpbh = b.stbh) stlx ");
		sql.append("from wjdc_zjb a where a.id = ?) a ");
		sql.append("left join wjdc_stdab b on a.stbh = b.stbh ");
		sql.append("left join (select d.fpbh stbh, d.dabh, count(d.dabh) num ");
		sql.append("from wjdc_hdb d where d.wjbh = ? ");
		sql.append(query);
		sql.append("group by fpbh, dabh) c on a.stbh = c.stbh and c.dabh = b.dabh ");
		sql.append("order by stlx, stbh, dabh ");

		String[] inputValue = new String[] { id, id, id };
		String[] outputValue = new String[] { "stbh", "stlx", "lxmc", "dabh",
				"num", "rs" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * ��������׼��
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getBzdaList(WjdcForm model) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.stbh, a.dabh from wjdc_stdab a ");
		sql.append("where a.bzda = 'yes' ");
		sql.append("order by a.stbh ");

		String[] inputValue = new String[] {};
		String[] outputValue = new String[] { "stbh", "dabh" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * ������ش���б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getHddaList(WjdcForm model) {

		DAO dao = DAO.getInstance();

		// ѧ��ְ����
		String xhzgh = model.getXhzgh();
		// �ش�������
		String lx = model.getLx();
		// ��������
		String stlx = model.getStlx();

		StringBuilder sql = new StringBuilder();

		sql.append("select b.stbh, a.dabh,a.danr from wjdc_hdb a, wjdc_stxxb b ");
		sql.append("where a.fpbh = b.stbh ");
		sql.append("and a.xhzgh = ? ");
		sql.append("and a.lx = ? ");
		sql.append("and b.stlx = ? ");
		sql.append("order by b.stbh, a.dabh ");

		String[] inputValue = new String[] { xhzgh, lx, stlx };
		String[] outputValue = new String[] { "stbh", "dabh", "danr" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * ������������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getStssList(WjdcForm model) {

		DAO dao = DAO.getInstance();
		
		// ������
		String id = model.getId();
		// ��������
		String stlx = model.getStlx();

		StringBuilder sql = new StringBuilder();

		sql.append("select distinct nvl(stss, '��') stss, ssmc ");
		sql.append("from view_wjdc_stxx a ");
		sql.append("where a.stlx = ? ");
		sql.append("and exists (select 1 from wjdc_zjb b ");
		sql.append("where a.stbh = b.fpbh and b.id = ?) ");
		sql.append("order by stss ");
		
		String[] inputValue = new String[] { stlx, id };
		String[] outputValue = new String[] { "stss", "ssmc" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * ����ʾ������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWjmcList(String xn, String nd,
			String xq,String mklx) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		sql.append("select '' id, '----��ѡ��-----' wjmc from dual union ");
		sql.append("select a.id,a.wjmc from wjdc_wjxxb a ");
		sql.append("where sfkq = '��' ");
		sql.append(Base.isNull(xn) ? "" : "and a.xn = '" + xn + "' ");
		sql.append(Base.isNull(nd) ? "" : "and a.nd = '" + nd + "' ");
		sql.append(Base.isNull(xq) ? "" : "and a.xq = '" + xq + "' ");
		sql.append(Base.isNull(mklx) ? "" : "and a.mklx = '" + mklx + "' ");
		sql.append(" order by id nulls first");

		String[] inputValue = new String[] {};
		String[] outputValue = new String[] { "id", "wjmc" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * �����ʾ����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveWjfp(WjdcForm model) throws Exception {
		
		boolean flag = false;
		
		DAO dao = DAO.getInstance();
		// �ʾ�ID
		String id = model.getId();
		// �꼶
		String nj = model.getNj();
		// ѧԺ
		String xydm = model.getXydm();
		// רҵ
		String zydm = model.getZydm();
		// ����༶
		String[] bjdm = model.getFpbj();
			
		StringBuilder query = new StringBuilder();
		
		if (bjdm != null && bjdm.length > 0) {// ѡ��༶
			for (int i = 0; i < bjdm.length; i++) {
				if (i == 0) {
					query.append("where b.bjdm = '" + bjdm[i] + "' ");
				} else {
					query.append("or b.bjdm = '" + bjdm[i] + "' ");
				}
			}
		} else {// δѡ��༶
			query.append("where 1=1 ");
			query.append(Base.isNull(nj) ? "" : "and b.nj = '" + nj + "' ");
			query.append(Base.isNull(xydm) ? "" : "and b.xydm = '" + xydm + "' ");
			query.append(Base.isNull(zydm) ? "" : "and b.zydm = '" + zydm + "' ");
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete from wjdc_wjfpb a where a.id = ?");
		
		flag = dao.runUpdate(sql.toString(), new String[] {id});
		
		if (flag) {
			
			sql = new StringBuilder();
			sql.append("insert into wjdc_wjfpb ");
			sql.append("select ? id, bjdm from view_njxyzybj b ");
			sql.append(query);
			
			flag = dao.runUpdate(sql.toString(), new String[] { id });
		}
		
		return flag;
	}

	/**
	 * ���ѧ���ش��ʾ��б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getWjhdList(WjdcForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		DAO dao = DAO.getInstance();
		// ѧ��
		model.setXn(model.getQueryequals_xn());
		// ѧ��
		model.setXq(model.getQueryequals_xq());
		// ���
		model.setNd(model.getQueryequals_nd());
		// �����Ϣ
		model.setZjxx(model.getQueryequals_zjxx());
		// �ʾ����
		model.setId(model.getQueryequals_id());
		// ģ������
		model.setMklx(model.getQueryequals_mklx());
		//ѧ��
		String xh = model.getXh();
		// ������ʼʱ��
		//String kssj = model.getQuerygreaterequal_jlsj();
		// ��������ʱ��
		//String jssj = model.getQuerylessequal_jlsj();
		
		String[] queryList = new String[] { "xn", "nd", "xq", "zjxx", "id",
				"mklx" };

		String[] queryLikeList = new String[] { };

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String[] inputValue = myQuery.getInputList();
		
		String[] outputValue = { "id", "xn", "nd", "xqmc", "wjmc", "jlsj",
				"zjxx", "hdnum", "yhd" };
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, ");
		sql.append(" case when (select count(1) num from wjdc_hdb c ");
		sql.append(" where c.wjbh = t.id and c.xhzgh = '" + xh + "') = 0 ");
		sql.append(" then '��δ�ش�' else '�ѻش�' end yhd ");
		sql.append(" from view_wjdc_wjxx t ");
		sql.append(myQuery.getQueryString());
		//sql.append(Base.isNull(kssj) ? "" : " and jlsj >='" + kssj + "'");
		//sql.append(Base.isNull(jssj) ? "" : " and jlsj <='" + jssj + "'");
		sql.append(" and t.sfkq = '��' ");
		sql.append(" and exists (select 1 from view_xsjbxx a, wjdc_wjfpb b ");
		sql.append(" where a.bjdm = b.fpbj and b.id = t.id  ");
		sql.append(" and a.xh = '" + xh + "' )");
		//System.out.println(sql);
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue, outputValue);
		return list;

	}	
}
