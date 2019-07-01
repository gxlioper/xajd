package xgxt.pjpy.nbcs;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.PjpyTyDAO;
import xgxt.pjpy.PjpyTyForm;
import xgxt.utils.MakeQuery;

public class PjpyNbcsDAO extends PjpyTyDAO {

	/**
	 * 获得最大试题编号
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
		
		String bzdm1 = dao.getOneRs(sql.toString(), new String[] {}, "bzdm");
		
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
	 * 获得题库信息列表(除去已经分配)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTkxxList(String[] inputValue) {
		
		DAO dao = DAO.getInstance();

		// 文件名称
		String wjmc = inputValue[0];
		// 试题类型
		String stlx = inputValue[1];
		// 试题所属
		String stss = inputValue[2];
		
		StringBuilder sql = new StringBuilder();
		sql.append("select '' dm, '----请选择-----'mc from dual union ");
		sql.append("select a.stbh dm, a.xsmc mc from view_wjdc_stxx a ");
		sql.append("where 1=1 ");
		if (!Base.isNull(wjmc)) {
			sql.append("and not exists (select 1 from wjdc_zjb b ");
			sql.append("where a.stbh = b.fpbh and b.id = '" + wjmc + "') ");
		}
		sql.append(Base.isNull(stlx) ? "" : "and a.stlx = '" + stlx + "'");
		sql.append(Base.isNull(stss) ? "" : "and a.stss = '" + stss + "'");
		sql.append(" order by dm nulls first");
		
		String[] outputValue = new String[] { "dm", "mc" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, outputValue);

		return list;
	}
	
	/**
	 * 获得指定问卷的试题情况
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWjstList(String wjmc) {
		
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.stbh dm, a.xsmc mc from view_wjdc_stxx a ");
		sql.append("where exists (select 1 from wjdc_zjb b ");
		sql.append("where a.stbh = b.fpbh and b.id = ?) ");

		String[] inputValue = new String[] {wjmc};
		String[] outputValue = new String[] { "dm", "mc" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * 获得指定问卷的试题类型
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
	 * 获得指定问卷的试题试题名称
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWjstmcList(String id, String stlx){

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.fpbh stbh, b.stmc,nvl(b.stss,'无') stss from wjdc_zjb a, wjdc_stxxb b ");
		sql.append("where a.id = ? and a.fpbh = b.stbh and b.stlx = ? ");
		sql.append("order by b.stss, a.fpbh");

		String[] inputValue = new String[] { id, stlx };
		String[] outputValue = new String[] { "stbh", "stmc", "stss" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * 获得试题答案信息
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
	 * 获得问卷回答情况
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> setWjHdInfo(PjpyTyForm model) {

		DAO dao = DAO.getInstance();

		// 学号职工号
		String xhzgh = model.getXhzgh();
		// 回答者类型
		String lx = model.getLx();
		// 问卷编号
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
	 * 获得试题答案统计
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDaTjList(PjpyTyForm model) {

		DAO dao = DAO.getInstance();

		// 问卷编号
		String id = model.getId();
		// 统计年级
		String nj = model.getNj();
		// 统计学院
		String xydm = model.getXydm();
		// 统计专业
		String zydm = model.getZydm();
		// 统计班级
		String bjdm = model.getBjdm();
		// 统计性别
		String xb = model.getXb();
		// 统计政治面貌
		String zzmm = model.getZzmm();
		// 统计条件
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
	 * 获得试题标准答案
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getBzdaList(PjpyTyForm model) {

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
	 * 获得所回答答案列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getHddaList(PjpyTyForm model) {

		DAO dao = DAO.getInstance();

		// 学号职工号
		String xhzgh = model.getXhzgh();
		// 回答者类型
		String lx = model.getLx();
		// 试题类型
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
	 * 获得试题所属列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getStssList(PjpyTyForm model) {

		DAO dao = DAO.getInstance();
		
		// 试题编号
		String id = model.getId();
		// 试题类型
		String stlx = model.getStlx();

		StringBuilder sql = new StringBuilder();

		sql.append("select distinct nvl(stss, '无') stss, ssmc ");
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
	 * 获得问卷名称列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWjmcList(String xn, String nd,
			String xq) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		sql.append("select '' id, '----请选择-----' wjmc from dual union ");
		sql.append("select a.id,a.wjmc from wjdc_wjxxb a ");
		sql.append("where sfkq = '是' ");
		sql.append(Base.isNull(xn) ? "" : "and a.xn = '" + xn + "' ");
		sql.append(Base.isNull(nd) ? "" : "and a.nd = '" + nd + "' ");
		sql.append(Base.isNull(xq) ? "" : "and a.xq = '" + xq + "' ");
		sql.append(" order by id nulls first");

		String[] inputValue = new String[] {};
		String[] outputValue = new String[] { "id", "wjmc" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * 保存问卷分配
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveWjfp(PjpyTyForm model) throws Exception {
		
		boolean flag = false;
		
		DAO dao = DAO.getInstance();
		// 问卷ID
		String id = model.getId();
		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 分配班级
		String[] bjdm = model.getFpbj();
			
		StringBuilder query = new StringBuilder();
		
		if (bjdm != null && bjdm.length > 0) {// 选择班级
			for (int i = 0; i < bjdm.length; i++) {
				if (i == 0) {
					query.append("where b.bjdm = '" + bjdm[i] + "' ");
				} else {
					query.append("or b.bjdm = '" + bjdm[i] + "' ");
				}
			}
		} else {// 未选择班级
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
	 * 获得学生回答问卷列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getWjhdList(PjpyTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		String xxdm = Base.xxdm;
		
		DAO dao = DAO.getInstance();
		// 学年
		model.setXn(model.getQueryequals_xn());
		// 学期
		model.setXq(model.getQueryequals_xq());
		// 年度
		model.setNd(model.getQueryequals_nd());
		// 组卷信息
		model.setZjxx(model.getQueryequals_zjxx());
		// 问卷代码
		model.setId(model.getQueryequals_id());
		//学号
		String xh = model.getXh();
		// 建立开始时间
		String kssj = model.getQuerygreaterequal_jlsj();
		// 建立结束时间
		String jssj = model.getQuerylessequal_jlsj();
		
		String[] queryList = new String[] { "xn", "nd", "xq", "zjxx", "id" };

		String[] queryLikeList = new String[] { };

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String[] inputValue = myQuery.getInputList();
		
		String[] outputValue = { "id", "xn", "nd", "xqmc", "wjmc", "jlsj",
				"zjxx","hdnum" };
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from view_wjdc_wjxx t ");
		sql.append(myQuery.getQueryString());
		sql.append(Base.isNull(kssj) ? "" : " and jlsj >='" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and jlsj <='" + jssj + "'");
		sql.append(" and t.sfkq = '是' ");
		//宁波城市
		if (!Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)) {
			sql.append(" and exists (select 1 from view_xsjbxx a, wjdc_wjfpb b ");
			sql.append(" where a.bjdm = b.fpbj and b.id = t.id  ");
			sql.append(" and a.xh = '" + xh + "' )");
		}else {
			sql.append(" and exists (select 1 from dmk_nbcs_wjsyb b ");
			sql.append(" where b.wjid = t.id  ");
			sql.append(" and b.xn = '" + model.getXn() + "' )");
		}
		
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue, outputValue);
		return list;

	}	
}
