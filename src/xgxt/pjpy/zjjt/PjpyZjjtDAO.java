package xgxt.pjpy.zjjt;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.pjpy.PjpyTyDAO;
import xgxt.pjpy.PjpyTyForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;

public class PjpyZjjtDAO extends PjpyTyDAO {

	/**
	 * ��ò��з�ͳ���б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String,String>> getCxfTjList(PjpyTyForm model)
			throws Exception {

		DAO dao = DAO.getInstance();

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"xq", "lx", "pycc", "jxjdm", "rychdm", "xxsh", "xqdm",
				"lddm", "cs", "qsh","qy","lb","xx" };

		String[] queryLikeList = new String[] { "xh", "xm" };

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();

		String sql = "select a.*,rownum r from view_zjjt_cxfexp a "+query;
		
		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] { "xh", "bjdm", "bjmc", "cs",
				"cxbz", "fz", "jb1", "jb2", "jb3", "jjf", "lb", "lddm", "ldmc",
				"nj", "qsh", "qy", "rq", "sfzh", "ssbh", "xb", "xh", "xm",
				"xn", "xq", "xqdm", "xqmc", "xx", "xydm", "xymc", "zydm",
				"zymc","mrz" };
		List<HashMap<String,String>>  list = dao.getList(sql, inputValue, outputValue);

		return list;
	}
	
	/**
	 * ��ò��з�ѧ������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String,String>> getTjXhList(PjpyTyForm model)
			throws Exception {

		DAO dao = DAO.getInstance();
		String[] queryList = null;
		String sql = "";
		String[] outputValue = null;
		
		if("xq".equalsIgnoreCase(model.getTjfs())){
			queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq", "lx", "pycc", "jxjdm", "rychdm", "xxsh", "xqdm",
				"lddm", "cs", "qsh","qy","lb","xx" };
				
			sql = "select distinct xh,xm,xb,xn,xq,xqm,xymc,zymc,bjmc,xqmc,ldmc,cs,qsh,mrz from view_zjjt_cxfexp ";
			outputValue = new String[] { "xh", "xm", "xb", "xn", "xq",
					"xqm", "xymc", "zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh",
					"mrz" };
		} else{
			queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
					"nd", "lx", "pycc", "jxjdm", "rychdm", "xxsh", "xqdm",
					"lddm", "cs", "qsh","qy","lb","xx" };
			
			sql = "select distinct xh,xm,xb,xn,xymc,zymc,bjmc,xqmc,ldmc,cs,qsh from view_zjjt_cxfexp ";
			outputValue = new String[] { "xh", "xm", "xb", "xn", "xymc", "zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh"};
		}

		String[] queryLikeList = new String[] { "xh", "xm" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();
		sql += query;

		String[] inputValue = myQuery.getInputList();
		List<HashMap<String,String>>  list = dao.getList(sql, inputValue, outputValue);

		return list;
	}
	
	/**
	 * ��ò��з�ѧ������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getCxfXxList(PjpyTyForm model) {

		DAO dao = DAO.getInstance();

		// ѧ��
		String xh = model.getXh();
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();

		String sql = "select id,jb1,jb2,jb3,jjf,fz,rq,cxbz from view_zjjt_cxfexp where xh = ? and xn = ? and xq = ? and jjf is not null";

		String[] inputValue = new String[] { xh, xn, xq };
		String[] outputValue = new String[] { "id", "jb1", "jb2", "jb3", "jjf",
				"fz", "rq", "cxbz" };

		List<HashMap<String, String>> list = dao.getList(sql, inputValue,
				outputValue);

		return list;
	}
	
	public String getXnMrz(String xn){
		DAO dao = DAO.getInstance();
		String sql = "select sum(to_number(nvl(mrz,0))) mrz from zjjt_cxf_sz where xn=?";
		HashMap<String, String> map = dao.getMap(sql, new String[]{xn}, new String[]{"mrz"});
		return map.get("mrz");
	}
	
	
	
	/**
	 * ��ʼ��������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean initCxf(PjpyTyForm model) throws Exception{
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into zjjt_cxflrb (pjxh,xn,xq) ")
		   .append("select xh,'")
		   .append(model.getXn())
		   .append("','")
		   .append(model.getXq())
		   .append("' from (select a.xh from view_xsjbxx a where not exists ")
		   .append("(select 1 from zjjt_cxflrb c where a.xh=c.pjxh and c.xn='")
		   .append(model.getXn())
		   .append("' and c.xq='")
		   .append(model.getXq())
		   .append("'))");
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	
	/**
	 * ���з�ͳ��-��ѧ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxftjByXq(PjpyTyForm model,User user) throws Exception{
		String[] colList = new String[] { "xh", "xm", "xb", "xn", "xqm",
				"xymc", "zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh", "jfsm",
				"jffs", "kfsm", "kffs", "clzf", "mrz", "cxf" };
		String[] queryLikeArr = new String[] { "xh", "xm" };
		String[] queryArr = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				 "xqdm", "lddm","cs", "qsh","xq" };
		
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,rownum r from (")
		   .append("select a.xh,a.xn,a.xq,b.xm,b.xb,b.xqm,b.xymc,b.zymc,b.bjmc,b.xqmc,")
		   .append("b.ldmc,b.cs,b.qsh,a.jfsm,a.jffs,a.kfsm,a.kffs,a.clzf,b.xydm,b.zydm,")
		   .append("b.bjdm,b.nj,b.xqdm,b.lddm,b.mrz,b.mrz+a.clzf cxf ")
		   .append("from(select xh,xn,xq,sum(jiaf) jfsm,sum(jiaffz) jffs,sum(jianf) kfsm,")
		   .append("sum(jianffz) kffs,sum(jiaffz)-sum(jianffz) clzf from ")
		   .append("(select pjxh xh,xn,xq,case when jjf='�ӷ�' then 1 else 0 end jiaf,")
		   .append("nvl(case when jjf='�ӷ�' then fz end,0) jiaffz,")
		   .append("case when jjf='����' then 1 else 0 end jianf,")
		   .append("nvl(case when jjf='����' then fz end,0) jianffz ")
		   .append("from zjjt_cxflrb) group by xh,xn,xq")
		   .append(") a left join (")
		   .append(" select distinct xh,xm,xb,xn,xq,xqm,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,xqdm,xqmc,ldmc,lddm,cs,qsh,mrz from view_zjjt_cxfexp")
		   .append(") b on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) a where 1=1")
		   .append(CommonQueryDAO.getQuerySqlByUser(user, "a", "xydm", "bjdm"))
		   .append(map.get("sql"));
		
		return commonPageQuery(model.getPages(), sql.toString(), (String[])map.get("input"), colList);
	}
	
	
	/**
	 * ���з�ͳ��-��ѧ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxftjByXn(PjpyTyForm model,User user) throws Exception{
		String[] colList = new String[] { "xh", "xm", "xb", "xn", "xymc",
				"zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh", "jfsm", "jffs",
				"kfsm", "kffs", "clzf", "mrz", "cxf" };
		String[] queryLikeArr = new String[] { "xh", "xm" };
		String[] queryArr = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				 "xqdm", "lddm","cs", "qsh"};
		
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,rownum r from (")
		   .append("select a.xh,a.xn,b.xm,b.xb,b.xymc,b.zymc,b.bjmc,b.xqmc,")
		   .append("b.ldmc,b.cs,b.qsh,a.jfsm,a.jffs,a.kfsm,a.kffs,a.clzf,b.xydm,")
		   .append("b.zydm,b.bjdm,b.nj,b.xqdm,b.lddm,b.mrz,b.mrz+a.clzf cxf  ")
		   .append("from(select xh,xn,sum(jiaf) jfsm,sum(jiaffz) jffs,sum(jianf) kfsm,")
		   .append("sum(jianffz) kffs,sum(jiaffz)-sum(jianffz) clzf from ")
		   .append("(select pjxh xh,xn,case when jjf='�ӷ�' then 1 else 0 end jiaf,")
		   .append("nvl(case when jjf='�ӷ�' then fz end,0) jiaffz,")
		   .append("case when jjf='����' then 1 else 0 end jianf,")
		   .append("nvl(case when jjf='����' then fz end,0) jianffz ")
		   .append("from zjjt_cxflrb ) group by xh,xn")
		   .append(") a left join (")
		   .append("select distinct xh,xm,xb,xn,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,xqdm,xqmc,ldmc,lddm,cs,qsh,")
		   .append("nvl((select c.mrz from (select xn,sum(mrz) mrz from zjjt_cxf_sz group by xn) c where c.xn = b.xn),0) mrz")
		   .append(" from view_zjjt_cxfexp  b")
		   .append(") b on a.xh=b.xh and a.xn=b.xn) a where 1=1")
		   .append(CommonQueryDAO.getQuerySqlByUser(user, "a", "xydm", "bjdm"))
		   .append(map.get("sql"));
		
		return commonPageQuery(model.getPages(), sql.toString(), (String[])map.get("input"), colList);
	}
}
