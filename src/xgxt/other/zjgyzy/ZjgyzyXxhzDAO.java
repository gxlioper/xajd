package xgxt.other.zjgyzy;

import java.util.ArrayList;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xsgzgl.comm.BasicDAO;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class ZjgyzyXxhzDAO extends BasicDAO{
	
	/**
	 * 学生人数一览
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXsrsList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		MakeQuery mQuery=new MakeQuery();
		
		String[]queryList={"nj","xydm","zydm","bjdm"};
		
		mQuery.makeQuery(queryList, null, myForm);
		
		mQuery.getQueryString();
		
		//用户对象
		User user=model.getUser();
		
		String []colList=model.getColList();
		
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.*,rownum r from ( ");
		sql.append(" select nj,xydm,zydm,bjdm,xymc,bjmc,nsrs,nvsrs,(nsrs+nvsrs)zrs from ( ");
		sql.append(" select nj,xydm,zydm,bjdm,xymc,bjmc,sum(nsrs)nsrs,sum(nvsrs)nvsrs from( ");
		sql.append(" select nj,xydm,zydm,bjdm,xymc,bjmc,case when xb='男' then rs else 0 end nsrs, ");
		sql.append(" case when xb='女' then rs else 0 end nvsrs from ( ");
		sql.append(" select nj,zydm,xydm, xymc, bjdm, bjmc, xb, count(1) rs ");
		sql.append(" from view_xsjbxx ");
		sql.append(" group by nj,zydm,xydm, xymc, bjdm, bjmc, xb))group by xymc,bjmc,nj,zydm,xydm,bjdm  order by xymc,bjmc) ");
		sql.append(" )a ");
		
		sql.append(mQuery.getQueryString());
		sql.append(getUserSql(user));
		sql.append(" order by r ");
		
		
		// ====================SQL拼装 end================================
		ArrayList<String[]> list =new ArrayList<String[]>();
		
		if("cx".equalsIgnoreCase(lx)){
			list=(ArrayList<String[]>) CommonQueryDAO.
				commonQuery(sql.toString(),"", mQuery.getInputList(), colList, model);
		}else {
			
			list=(ArrayList<String[]>) CommonQueryDAO.
			commonQueryNotFy(sql.toString(),"", mQuery.getInputList(), colList, model);
		}
		
		return list;
	}
	
	/**
	 * 学生住宿一览
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXszsList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{

		MakeQuery mQuery=new MakeQuery();
		
		String[] queryList={"nj","xydm","zydm","bjdm"};
		
		mQuery.makeQuery(queryList, null, myForm);
		
		mQuery.getQueryString();
		
		//用户对象
		User user=model.getUser();
		
		String[] colList=model.getColList();
		
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.*,rownum r from ( ");
		sql.append(" select a.nj,a.xydm,a.zydm,a.bjdm,a.xymc,a.bjmc,nsrs,nvsrs, ");
		sql.append(" nvl(wrznsrs,'0') wrznsrs,nvl(wrznvsrs,'0') wrznvsrs,(nsrs + nvsrs) zrs,(nsrs - nvl(wrznsrs,'0')) rznsrs,(nvsrs - nvl(wrznvsrs,'0')) rznvsrs from (");
		sql.append(" select nj,xydm,zydm,bjdm,xymc,bjmc,sum(nsrs)nsrs,sum(nvsrs)nvsrs from(  ");
		sql.append(" select nj,xydm,zydm,bjdm,xymc,bjmc,case when xb='男' then rs else 0 end nsrs,  ");
		sql.append(" case when xb='女' then rs else 0 end nvsrs from (  ");
		sql.append(" select nj,zydm,xydm, xymc, bjdm, bjmc, xb, count(1) rs  ");
		sql.append(" from view_xsjbxx  ");
		sql.append(" group by nj,zydm,xydm, xymc, bjdm, bjmc, xb))group by nj,xydm,zydm,bjdm,xymc,bjmc  order by  nj,xydm,zydm,bjdm,xymc,bjmc)a ");
		
		sql.append(" left join ( ");
		sql.append(" select xymc,bjmc,sum(nsrs)wrznsrs,sum(nvsrs)wrznvsrs from(  ");
		sql.append(" select xymc,bjmc,case when xb='男' then rs else 0 end nsrs,  ");
		sql.append(" case when xb='女' then rs else 0 end nvsrs from (  ");
		sql.append(" select xydm, xymc, bjdm, bjmc, xb, count(1) rs  ");
		sql.append(" from (select * from view_xsjbxx a ");
		sql.append(" where not exists(select 1 from xg_gygl_new_cwxxb b where a.xh=b.xh))  ");
		sql.append(" group by xydm, xymc, bjdm, bjmc, xb))group by xymc,bjmc  order by xymc,bjmc ");
		
		sql.append(" )b on a.bjmc=b.bjmc ");
		sql.append(" order by xymc,bjmc)a ");
				
		sql.append(mQuery.getQueryString());
		sql.append(getUserSql(user));
		//sql.append(" and (select count(*) from VIEW_NJXYZYBJ_all t where t.bjdm=a.bjdm)>0");//班级需在bks_bjdm班级表中

		// ====================SQL拼装 end================================
		ArrayList<String[]> list =new ArrayList<String[]>();
		
		if("cx".equalsIgnoreCase(lx)){
			list=(ArrayList<String[]>) CommonQueryDAO.
				commonQuery(sql.toString(),"", mQuery.getInputList(), colList, model);
		}else {
			
			list=(ArrayList<String[]>) CommonQueryDAO.
			commonQueryNotFy(sql.toString(),"", mQuery.getInputList(), colList, model);
		}
		
		return list;
	}
	
	/**
	 * 学生档案一览
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXsdaList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		MakeQuery mQuery=new MakeQuery();
		
		String[]queryList={"nj","xydm","zydm","bjdm"};
		
		mQuery.makeQuery(queryList, null, myForm);
		
		mQuery.getQueryString();
		
		//用户对象
		User user=model.getUser();
		
		String []colList=model.getColList();
		
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.*,rownum r from ( ");
		sql.append(" select nj,zydm,xydm,bjdm,xymc,bjmc,nsrs,nvsrs,(nsrs+nvsrs)zrs from ( ");
		sql.append(" select nj,zydm,xydm,bjdm,xymc,bjmc,sum(nsrs)nsrs,sum(nvsrs)nvsrs from( ");
		sql.append(" select nj,zydm,xydm,bjdm,xymc,bjmc,case when xb='男' then rs else 0 end nsrs, ");
		sql.append(" case when xb='女' then rs else 0 end nvsrs from ( ");
		sql.append(" select nj,zydm,xydm, xymc, bjdm, bjmc, xb, count(1) rs ");
		sql.append(" from view_stu_archives ");
		sql.append(" group by nj,zydm,xydm, xymc, bjdm, bjmc, xb))group by nj,zydm,xydm,bjdm,xymc,bjmc) ");
		sql.append(" )a ");
		
		sql.append(mQuery.getQueryString());
		sql.append(getUserSql(user));
		
		sql.append(" order by xymc,bjmc ");
		
		ArrayList<String[]> list =new ArrayList<String[]>();
		
		if("cx".equalsIgnoreCase(lx)){
			list=(ArrayList<String[]>) CommonQueryDAO.
				commonQuery(sql.toString(),"", mQuery.getInputList(), colList, model);
		}else {
			
			list=(ArrayList<String[]>) CommonQueryDAO.
			commonQueryNotFy(sql.toString(),"", mQuery.getInputList(), colList, model);
		}
		
		return list;
	}
	
	/**
	 * 违纪处分一览
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWjcfList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		MakeQuery mQuery=new MakeQuery();
		
		String[] queryList={"xn","xq","nj","xydm","zydm","bjdm"};
		
		mQuery.makeQuery(queryList, null, myForm);
		
		mQuery.getQueryString();
		
		//用户对象
		User user=model.getUser();
		
		String[] colList=model.getColList();
		
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.*,rownum r from ( ");
		sql.append(" select substr(xn,6,8) nd,xn,xq,xqmc,xh,xm,cflbmc, ");
		sql.append(" cfsj,cfyymc,cfwh,bjmc,xydm,zydm,bjdm,nj from xg_view_wjcf_wjcfb  order by bjmc  ");
		sql.append(" )a ");
		
		sql.append(mQuery.getQueryString());
		sql.append(getUserSql(user));
		
		ArrayList<String[]> list =new ArrayList<String[]>();
		
		if("cx".equalsIgnoreCase(lx)){
			list=(ArrayList<String[]>) CommonQueryDAO.
				commonQuery(sql.toString(),"", mQuery.getInputList(), colList, model);
		}else {
			
			list=(ArrayList<String[]>) CommonQueryDAO.
			commonQueryNotFy(sql.toString(),"", mQuery.getInputList(), colList, model);
		}
		
		return list;
	}
	
	/**
	 * 聘用情况一览
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getPyqkList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		MakeQuery mQuery=new MakeQuery();
		
		String[]queryList={"nj","xydm","zydm","bjdm"};
		
		mQuery.makeQuery(queryList, null, myForm);
		
		mQuery.getQueryString();
		
		//用户对象
		User user=model.getUser();
		
		String []colList=model.getColList();
		
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.*,rownum r from ( ");
		sql.append(" select '"+Base.currXn+"' xn,'"+Base.getDqxqmc()+"' xqmc,'"+Base.currNd+"' nd, xydm,xymc,a.bjdm,bjmc,b.xm fdy, ");
		sql.append(" c.xm bzr,zydm,nj from view_njxyzybj a left join ");
		sql.append(" (select bjdm,max(xm)xm from ( ");
		sql.append(" select bjdm,to_char(WM_CONCAT(xm)over(  ");
		sql.append(" partition by bjdm  order by bjdm))xm from( ");
		sql.append(" select distinct a.zgh,a.bjdm,b.xm from fdybjb a, ");
		sql.append(" yhb b where a.zgh=b.yhm)) group by bjdm)b on a.bjdm=b.bjdm ");
		sql.append(" left join ");
		sql.append(" (select bjdm,max(xm)xm from ( ");
		sql.append(" select bjdm,to_char(WM_CONCAT(xm)over(  ");
		sql.append(" partition by bjdm  order by bjdm))xm from( ");
		sql.append(" select distinct a.zgh,a.bjdm,b.xm from bzrbbb a, ");
		sql.append(" yhb b where a.zgh=b.yhm)) group by bjdm ");
		sql.append(" )c on a.bjdm=c.bjdm order by xymc,bjmc,xydm,zydm,bjdm,nj ");
		sql.append(" )a ");
		
		sql.append(mQuery.getQueryString());
		sql.append(getUserSql(user));
		
		
		sql.append(" order by r ");
		
		ArrayList<String[]> list =new ArrayList<String[]>();
		
		if("cx".equalsIgnoreCase(lx)){
			list=(ArrayList<String[]>) CommonQueryDAO.
				commonQuery(sql.toString(),"", mQuery.getInputList(), colList, model);
		}else {
			
			list=(ArrayList<String[]>) CommonQueryDAO.
			commonQueryNotFy(sql.toString(),"", mQuery.getInputList(), colList, model);
		}
		
		return list;
	}
	
	/**
	 * 辅导员信息一览
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getFdyList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		BasicService service =new BasicService();
		
		MakeQuery mQuery=new MakeQuery();
		
		String[] queryList={"xb"};

		String[] queryLikeList={"zgh","xm","zwmc"};
		
		mQuery.makeQuery(queryList, queryLikeList, myForm);
		
		mQuery.getQueryString();
		
		//用户对象
		User user=model.getUser();
		
		String[] colList=model.getColList();
		
		
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.*,rownum r from ( ");
		sql.append(" select zgh,xm,(select zzmmmc from zzmmdmb b  ");
		sql.append(" where a.zzmm=b.zzmmdm)zzmm,kzzd3,csrq,xl,xw,sxzy,byyx, ");
		sql.append(" (select qxmc from dmk_qx b where a.jgxs=b.qxdm)jg,");
		sql.append(" case when xb='1' then '男'   when xb='2' then '女' else xb end xb,");
		sql.append(" zc,(select zwmc from zwb b where b.zwdm=a.zw) zwmc,cjgzrq,kzzd1,(select bmmc from zxbz_xxbmdm b  ");
		sql.append(" where a.bmdm=b.bmdm)bmmc,bmdm,zyzz,pxqk,zyyjjg,grhjqk, ");
		sql.append(" kzzd2,bz,fblw,szgzsj,dwlbdm,kzzd4 from fdyxxb a ");
		sql.append(" where exists (select 1 from zwb b where a.zw = b.zwdm and b.zwmc = '辅导员') ");
		sql.append(" )a ");
		
		sql.append(mQuery.getQueryString());
		
		sql.append(" order by r ");
		
		ArrayList<String[]> list =new ArrayList<String[]>();
		
		if("cx".equalsIgnoreCase(lx)){
			list=(ArrayList<String[]>) CommonQueryDAO.
				commonQuery(sql.toString(),"",service.toGbk(mQuery.getInputList()), colList, model);
		}else {
			
			list=(ArrayList<String[]>) CommonQueryDAO.
			commonQueryNotFy(sql.toString(),"", service.toGbk(mQuery.getInputList()), colList, model);
		}
		
		return list;
	}
}
