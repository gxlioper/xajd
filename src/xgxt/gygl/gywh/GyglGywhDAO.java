package xgxt.gygl.gywh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_公寓维护_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public class GyglGywhDAO extends CommDAO {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	/**
	 * 校区数据获取
	 * (如果有楼栋或园区与校区相关联则不允许删除校区信息)
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 * @author qlj
	 */
	public List<String[]>getXqsjList(GyglGywhForm myForm,HttpServletRequest request) throws Exception{
		
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());	
		//高级查询条件值
		StringBuilder query=new StringBuilder();
		StringBuilder sql=new StringBuilder();
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
		
		String[]colList=myForm.getColList();
		
		sql.append(" select rownum r,a.dm pkValue, a.dm,a.xqmc,a.sjly, ");
		//判断数据来源是否为导入,如为导入就不可删除
		sql.append(" '' disabled ");
		sql.append("   from dm_zju_xq a  ");
		
		
		//查询条件
		query.append("where 1=1 ");
		query.append(searchTj);
		query.append(" order by dm ");
		
		return CommonQueryDAO.commonQuery(sql.toString(),  query.toString(), inputV,colList, myForm);
	}
	
	/**
	 * 园区数据获取
	 * (如果园区与楼栋有关联则不可删除园区)
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 * @author qlj
	 */
	public List<String[]>getYqsjList(GyglGywhForm myForm,HttpServletRequest request) throws Exception{
		
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		
		//查询语句
		StringBuilder sql=new StringBuilder();
		//查询条件
		StringBuilder query=new StringBuilder();
		//高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
		
		String[]colList=myForm.getColList();
		
		sql.append(" select rownum r,a.yqdm pkValue,a.yqdm,a.xqdm,b.xqmc, a.yqmc, a.sjly, ");
		//非导入数据不可删除
		sql.append(" ''  disabled  ");
		//判断是否有相关联的楼栋信息,如过有则不可删除园区
	
		sql.append(" from yqdmb a");
	    sql.append("  left join dm_zju_xq b on a.xqdm=b.dm ");     
		
		query.append(" where 1=1 ");
		query.append(searchTj);
		query.append(" order by yqdm ");
		return CommonQueryDAO.commonQuery(sql.toString(),  query.toString(), inputV,colList, myForm);
	}
	
	/**
	 * 楼栋数据获取
	 * 
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLdsjList(GyglGywhForm myForm, User user,
			HttpServletRequest request) throws Exception {
		
		SearchService searchService = new SearchService();
		
		// 构建过滤条件
		String searchTjByUser = searchService.getSearchTjByUser("a", user);
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());

		//查询语句
		StringBuilder sql=new StringBuilder();
		//高级查询条件值
		StringBuilder query=new StringBuilder();
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
		
		String[]colList=myForm.getColList();
		sql.append(" select * from ( ");
		sql.append(" select rownum r,a.lddm pkValue,a.lddm,ldmc,xqmc,xbxd,cs,yqmc,sjly, ");
		sql.append(" case when sjly <>'导入' then 'disabled' else '' end disabled, ");
		sql.append(" nvl(nanss, 0) nanss,nvl(nvss, 0) nvss,nvl(zrs, 0) zrs ");
		sql.append("   from xg_view_gygl_sslddm a  ");
		
		//男生数统计
		sql.append(" left join (select count(1) nanss, lddm from ssxxb where xb = '男' group by lddm) b on a.lddm = b.lddm ");
		//女生数统计
		sql.append(" left join (select count(1) nvss, xb, lddm from ssxxb where xb = '女' group by xb, lddm) c on a.lddm = c.lddm ");
		//总人数
		sql.append(" left join (select count(1) zrs,lddm from ssxxb group by lddm) d on a.lddm = d.lddm ");
		sql.append(" ) "); 
		query.append(" where 1=1 ");
		query.append(searchTj);
		query.append(" order by lddm ");
		return CommonQueryDAO.commonQuery(sql.toString(), query.toString(), inputV,colList, myForm);
	}
	
	/**
	 * 寝室数据获取
	 * 
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getQssjList(GyglGywhForm myForm, User user,
			HttpServletRequest request) throws Exception {

		SearchService searchService = new SearchService();

		// 构建过滤条件
		HashMap<String,String> notCtrlStatus = new HashMap<String,String>();
		// 不需要控制数据范围的权限
		notCtrlStatus.put("xy", "yes");
		notCtrlStatus.put("jd", "yes");
		notCtrlStatus.put("bzr", "yes");
		notCtrlStatus.put("fdy", "yes");
		
		user.setNotCtrlStatus(notCtrlStatus);
		String searchTjByUser = searchService.getSearchTjByUser("a", user);
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		searchTj += searchTjByUser;		

		//查询语句
		StringBuilder sql=new StringBuilder();
		//查询条件
		StringBuilder query=new StringBuilder();
		//高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
		
		String[]colList=myForm.getColList();
		
		sql.append(" select rownum r, a.* from (select b.lddm || '!!@!!' || b.cs || '!!@!!' || b.qsh pkValue, ");
		sql.append(" case when sjly <> '导入' then 'disabled' else  '' end disabled, ");
		sql.append(" b.lddm,b.ldmc,b.xbxd,b.cs,b.qsh,b.xqdm,b.xqmc,b.yqdm,b.yqmc, ");
		sql.append(" b.xb,b.cws,b.sfbz,b.sjly,b.fpbj,b.kfhz,nvl(rzrs,0)rzrs,");
		sql.append(" (to_number(cws) - nvl(rzrs,0) - qtcws) kcws,");
		sql.append(" case when nvl(rzrs,0) =  to_number(cws) then '满的'  when nvl(rzrs,0)=0  then ");
		sql.append("  '空的' else '闲的' end rzqk from (select b.*, ");
		sql.append("  a.cs,a.qsh,a.xb, a.cws,sfbz,a.sjly,a.fpbj,a.kfhz,nvl(d.qtcws, 0) qtcws ");
		sql.append("  from ssxxb a left join (select a.xbxd,a.ldmc, a.lddm, a.xqdm, a.yqdm, xqmc, yqmc ");
		sql.append(" from sslddmb a ");
		sql.append(" left join dm_zju_xq b on a.xqdm = b.dm ");
		sql.append(" left join yqdmb c on a.yqdm = c.yqdm) b on a.lddm =  b.lddm ");
		sql.append(" left join (select count(1) qtcws, lddm, cs, qsh ");
		sql.append("  from xg_gygl_qtcwxxb ");
		sql.append("   group by lddm, cs, qsh) d on b.lddm = d.lddm ");
		sql.append("  and a.cs = d.cs  and a.qsh = d.qsh) b ");
		sql.append("  left join (select count(1) rzrs, lddm, cs, qsh ");
		sql.append("   from xszsxxb ");
		sql.append("  group by lddm, cs, qsh) a on a.lddm = b.lddm and a.cs = b.cs and a.qsh = b.qsh) a ");
		
		query.append(" where 1=1 ");
		query.append(searchTj);
		query.append(" order by lddm,cs,qsh ");
		return CommonQueryDAO.commonQuery(sql.toString(), query.toString(), inputV,colList, myForm);
	}
	
	/**
	 * 床位数据获取
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getCwsjList(GyglGywhForm myForm,HttpServletRequest request) throws Exception{	
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());	
		//查询语句
		StringBuilder sql=new StringBuilder();
		//查询条件
		StringBuilder query=new StringBuilder();
		//高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
		
		String[]colList=myForm.getColList();
		sql.append("select  rownum r,a.* from( ");
		sql.append(" select a.lddm||a.cs||a.qsh||a.cwh pkValue,ldmc,a.lddm,a.cs,a.qsh,a.cwh,a.cwbj,a.sjly, ");
		sql.append(" (case when a.sjly<>'导入' then 'disabled' else '' end)disabled ");
		sql.append("from  xg_gygl_qtcwxxb a,sslddmb b where a.lddm=b.lddm)a  ");
		
		query.append(" where 1=1 ");
		query.append(searchTj);
		query.append(" order by lddm,cs,qsh,cwh ");
		return CommonQueryDAO.commonQuery(sql.toString(), query.toString(), inputV,colList, myForm);
	}
	
	/**
	 * 寝室分配数据获取
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getQsfpsjList(GyglGywhForm myForm,HttpServletRequest request) throws Exception{
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());

		StringBuilder query=new StringBuilder();
		//高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[]colList=myForm.getColList();
		StringBuilder sql=new StringBuilder();
		sql.append(" select pkValue,rownum r,a.lddm,a.ldmc,a.cs,a.qsh,a.nj,a.sjly,a.fpbm bmmc, ");
		sql.append(" decode(a.fpdx,'xy','"+Base.YXPZXY_KEY+"','njxy','年级+''"+Base.YXPZXY_KEY+"','njzy','年级+专业','bj','班级','') fpdx ");
		sql.append(" from (select a.lddm||a.cs||a.qsh pkValue, a.lddm, a.ldmc,a.cs,a.qsh,a.fpdx,a.nj,a.sjly,a.bmdm,a.fpbm from xg_view_gygl_qsfp a)a ");
		query.append(" where 1=1 ");
		query.append(searchTj);
		return CommonQueryDAO.commonQuery(sql.toString(),query.toString(), inputV,colList, myForm);
	}
	
	/**
	 * 寝室分配数据获取
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getCwfpsjList(GyglGywhForm myForm,HttpServletRequest request) throws Exception{
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());	

		StringBuilder query=new StringBuilder();
		//高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());		
		
		String[]colList=myForm.getColList();
		String sql=" select rownum r,xh pkValue, xh,xm,bjmc,ldmc,qsh,cs,cwh,sjly from xg_view_gygl_xszsxx ";
		query.append(" where 1=1 ");
		query.append(searchTj);
		return CommonQueryDAO.commonQuery(sql, query.toString(), inputV,colList, myForm);
	}
	
	/**
	 * 住宿历史信息数据获取
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getLsxxsjList(GyglGywhForm myForm,HttpServletRequest request) throws Exception{
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());	

		StringBuilder query=new StringBuilder();
		//高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
		String[]colList=myForm.getColList();
		String sql=" select rownum r,guid pkValue,xh,xm,bjmc,ldmc,qsh,cwh,sjly from xszslsxxb ";
		query.append(" where 1=1 ");
		query.append(searchTj);
		return CommonQueryDAO.commonQuery(sql,query.toString(), inputV,colList, myForm);
	}
	
	/**
	 * 获取单条校区信息
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String>getXqxx(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		
		String sql="select dm xqdm,xqmc from dm_zju_xq where dm=? ";
		return dao.getMap(sql, new String[]{myForm.getXqdm()}, new String[]{"xqdm","xqmc"});
	}
	
	/**
	 * 获取单条园区信息
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String>getYqxx(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		
		String sql="select xqdm,yqmc,yqdm from yqdmb where yqdm=? ";
		return dao.getMap(sql, new String[]{myForm.getYqdm()}, new String[]{"yqdm","yqmc","xqdm"});
	}
	
	/**
	 * 获取单条园区信息
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>>getYqList(){
		DAO dao=DAO.getInstance();
		String sql="select yqmc mc,yqdm dm from yqdmb ";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 获取单条园区信息
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String>getLdxx(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		List<String>inputList=new ArrayList<String>();
		sql.append(" select a.lddm, ldmc, xqdm, xqmc, yqmc, xbxd, cs, yqdm, xbxd, bz, ");
		sql.append(" nvl((case when b.lddm is not null then '有入住' end),'无入住')rzqk ");
		sql.append("   from xg_view_gygl_sslddm a left join ");
		sql.append(" (select lddm from sslddmb a where exists ");
		sql.append(" (select 1 from xszsxxb b where a.lddm=b.lddm) )b on a.lddm=b.lddm ");
		if(!Base.isNull(myForm.getLddm())){
			sql.append(" where   a.lddm=? ");
			inputList.add(myForm.getLddm());
		}
		return dao.getMap(sql.toString(), inputList.toArray(new String[]{}), 
				new String[]{"lddm","ldmc","xqdm","xbxd","cs","yqdm","xqmc","yqmc","xbxd","bz","rzqk"});
	}
	
	/**
	 * 获取单条园区信息
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String>getQsxx(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		List<String>inputList=new ArrayList<String>();
		
		String[]colList={"cws","yqdm","yqmc","xqdm","xqmc","lddm","ldmc","cs",
				"xbxd","qsh","xb","sfbz","bz","kfhz","fpbj","qsdh"};
		
		sql.append("select * from xg_view_gygl_ssxx where 1=1 ");
		if(!Base.isNull(myForm.getPkValue())){
			sql.append(" and  lddm||'!!@!!'||cs||'!!@!!'||qsh=? ");
			inputList.add(myForm.getPkValue());
		}
		
		return dao.getMap(sql.toString(), inputList.toArray(new String[]{}), colList);
	}
	
	/**
	 * 统计一个寝室已经入住的学生数
	 * @param myForm
	 * @return HashMap<String,String>
	 */
	public HashMap<String,String>getYzrCws(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		List<String>inputList=new ArrayList<String>();
		
		String[]colList={"yzrcws"};
		
		sql.append("  select count(1)yzrcws from xszsxxb where 1=1 ");
		if(!Base.isNull(myForm.getPkValue())){
			sql.append(" and  lddm||'!!@!!'||cs||'!!@!!'||qsh=? ");
			inputList.add(myForm.getPkValue());
		}
		
		return dao.getMap(sql.toString(), inputList.toArray(new String[]{}), colList);
	}
	
	/**
	 * 获取寝室内床位信息
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>>getCwxx(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		List<String>inputList=new ArrayList<String>();
		
		String[]colList={"lddm","cs","qsh","cwh","cwbj","xh","xm"};
		
		sql.append("  select a.*,b.xh,b.xm from ( ");
		sql.append("  select a.lddm,a.cs,a.qsh,a.cwh,");
		sql.append(" case when b.cwbj is null then '未入住' else b.cwbj end cwbj from xg_gygl_cwxxb a ");
		sql.append(" left join xg_gygl_qtcwxxb b on a.lddm = b.lddm  ");
		sql.append(" and a.cs = b.cs and a.qsh = b.qsh and a.cwh=b.cwh) a ");
		sql.append(" left join xg_view_gygl_xszsxx b on a.lddm=b.lddm ");
		sql.append(" and a.cs= b.cs and a.qsh=b.qsh and a.cwh=b.cwh where 1=1 ");
		if(!Base.isNull(myForm.getPkValue())){
			sql.append(" and   a.lddm||'!!@!!'||a.cs||'!!@!!'||a.qsh=? ");
			inputList.add(myForm.getPkValue());
		}
		
		return dao.getList(sql.toString(), inputList.toArray(new String[]{}), colList);
	}
	
	/**
	 * 获取单条园区信息
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>>getLdList(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		List<String>inputList=new ArrayList<String>();
		sql.append("select lddm,ldmc,xqdm,xbxd,cs,yqdm from sslddmb where 1=1 ");
		if(!Base.isNull(myForm.getLddm())){
			sql.append(" and lddm=? ");
			inputList.add(myForm.getLddm());
		}
		sql.append(" order by lddm ");
		return dao.getList(sql.toString(), inputList.toArray(new String[]{}), new String[]{"lddm","ldmc","xqdm","xbxd","cs","yqdm"});
	}
	
	/**
	 * 获取单条园区信息
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>>getQsList(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		List<String>inputList=new ArrayList<String>();
		sql.append(" select lddm,qsh,cs,cws from ssxxb  where 1=1 ");
		if(!Base.isNull(myForm.getLddm())){
			sql.append(" and lddm=? ");
			inputList.add(myForm.getLddm());
		}
		return dao.getList(sql.toString(), inputList.toArray(new String[]{}), new String[]{"lddm","qsh","cs"});
	}
	
	public List<HashMap<String,String>>getQsfpList(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		List<String>inputList=new ArrayList<String>();
		sql.append(" select lddm,qsh,cs,cws,fpbm from xg_view_gygl_qsfp  where 1=1 ");
		if(!Base.isNull(myForm.getLddm())){
			sql.append(" and lddm=? ");
			inputList.add(myForm.getLddm());
		}
		return dao.getList(sql.toString(), inputList.toArray(new String[]{}), new String[]{"lddm","qsh","cs","fpbm","cws"});
	}
	
	/**
	 * 获取楼栋入住情况
	 * @param myForm
	 * @return  List<HashMap<String,String>>
	 */ 
	public List<HashMap<String,String>>getLdrzqk(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		List<String>inputList=new ArrayList<String>();
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select b.* from(" );
		
		sql.append(" select a.lddm, a.ldmc,a.fpdx, a.cs, a.cws, a.qsh, a.fpbm,a.fpbj, ");
		sql.append(" (case when rzrs is null then '0' else   to_char(rzrs) end)rzrs");
		sql.append(" from (select a.lddm,a.ldmc,a.cs,a.cws,a.qsh,b.fpdx,a.fpbj, ");
		sql.append(" case when fpbm is not null then  fpbm else  '未分配'  end fpbm");
		sql.append(" from xg_view_gygl_ssxx a left join (select fpdx,lddm,cs,qsh,fpbm from xg_view_gygl_qsfp  where kffp = 'yes' or kffp ='null') b on a.lddm = b.lddm");
		sql.append(" and a.cs = b.cs  and a.qsh = b.qsh where 1=1  ");
		
		//判断是否只显示非保留寝室
		if("一般".equalsIgnoreCase(myForm.getFpbj())){
			
			sql.append(" and a.fpbj='一般' ");
		}
		
		//判断是否只显示已分配寝室
		if("未分配".equalsIgnoreCase(myForm.getFpbm())){
			
			sql.append(" and fpbm<>'未分配' ");
		}
		
		sql.append(" ) a left join (select lddm,cs,qsh,count(1)rzrs from xszsxxb group by lddm,cs,qsh   ) ");
		sql.append(" b on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh      "); 
		
		sql.append(" )b left join ( ");
		
		//角色权限判断
		sql.append(userStatusSql(myForm));
		
		
		
		sql.append("  )a on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh where 1=1 ");
		
		if("未分配".equalsIgnoreCase(myForm.getFpbm())){
			
			sql.append(" and b.rzrs<b.cws ");
		}
		
		
		if(!Base.isNull(myForm.getLddm())){
			inputList.add(myForm.getLddm());
			sql.append(" and b.lddm=? ");
		}  
		if(!Base.isNull(myForm.getCs())){
			inputList.add(myForm.getCs());
			sql.append(" and b.cs=? ");
		}  
		sql.append(" order by b.lddm,b.cs,b.qsh asc");
		System.out.println(sql);
		return dao.getList(sql.toString(), inputList.toArray(new String[]{}), new String[]{"cws","cs","qsh","lddm","fpbm","rzrs","fpdx","fpbj"});
	}
	
	/**
	 * 获取楼层分配情况
	 * @param myForm
	 * @return  List<HashMap<String,String>>
	 */ 
	public List<HashMap<String,String>>getLcfpqk(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		List<String>inputList=new ArrayList<String>();
		
		StringBuilder sql=new StringBuilder();
		sql.append("select * from(");
		sql.append("select lddm,ldmc,cs,count(1)yfps from( ");                        
		sql.append("select a.lddm, a.ldmc, a.cs, a.cws,a.qsh, ");
		sql.append(" case when fpbm is not null then  fpbm  else  '未分配' end fpbm");
		sql.append(" from xg_view_gygl_ssxx a  left join xg_view_gygl_qsfp b on a.lddm = b.lddm ");
		sql.append(" and a.cs = b.cs and a.qsh = b.qsh where fpbm<>'未分配' " );
		if("一般".equalsIgnoreCase(myForm.getFpbj())){
			sql.append(" and a.fpbj='一般' ");
		}
		sql.append(" )group by lddm,ldmc,cs ");        
		sql.append(")");
		if(!Base.isNull(myForm.getLddm())){
			inputList.add(myForm.getLddm());
			sql.append(" where lddm=? ");
		}                                             
		return dao.getList(sql.toString(), inputList.toArray(new String[]{}), new String[]{"lddm","cs","yfps"});
	}
	
	/**
	 * 获取楼层入住情况
	 * @param myForm
	 * @return  List<HashMap<String,String>>
	 */ 
	public List<HashMap<String,String>>getLcrzqk(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		List<String>inputList=new ArrayList<String>();
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select * from(select a.lddm,a.cs,count(1)rzrs");
		sql.append(" from (select a.lddm, a.ldmc, a.cs,b.xh ");
		sql.append(" from (select a.lddm,a.ldmc,a.qsh, a.cs,");
		sql.append(" case when fpbm is not null then fpbm else  '未分配'");
		sql.append(" end fpbm from xg_view_gygl_ssxx a ");
		sql.append(" left join xg_view_gygl_qsfp b on a.lddm = b.lddm ");
		sql.append(" and a.cs = b.cs and a.qsh = b.qsh where b.kffp='yes' or b.kffp ='null')a left join xszsxxb b ");
		sql.append("  on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh where b.xh is not null ");
		sql.append("  )a group by a.lddm,a.ldmc,a.cs)");
    
		if(!Base.isNull(myForm.getLddm())){
			inputList.add(myForm.getLddm());
			sql.append(" where lddm=? ");
		}      
	
		return dao.getList(sql.toString(), inputList.toArray(new String[]{}), new String[]{"lddm","cs","rzrs"});
	}
	
	/**
	 * 获取楼层寝室数量
	 * @param myForm
	 * @return  List<HashMap<String,String>>
	 */ 
	public List<HashMap<String,String>>getLcqss(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		List<String>inputList=new ArrayList<String>();
		
		StringBuilder sql=new StringBuilder();
		sql.append("  select * from (select a.lddm, a.ldmc, a.cs,count(1)qss ");
		sql.append(" from (select a.lddm, a.ldmc, a.cs,");
		sql.append(" case  when fpbm is not null then fpbm  else  '未分配' end fpbm ");
		sql.append(" from xg_view_gygl_ssxx a left join xg_view_gygl_qsfp b on a.lddm = b.lddm ");
		sql.append(" and a.cs = b.cs and a.qsh = b.qsh where 1=1  ");
		
		if("一般".equalsIgnoreCase(myForm.getFpbj())){
			sql.append(" and a.fpbj='一般' ");
		}
		
		sql.append(" )a group by lddm,ldmc,cs order by lddm,to_number(cs)) ");
		
		if(!Base.isNull(myForm.getLddm())){
			inputList.add(myForm.getLddm());
			sql.append(" where lddm=? ");
		}                        
		return dao.getList(sql.toString(), inputList.toArray(new String[]{}), new String[]{"lddm","cs","qss"});
	}
	
	
	public boolean addXqxx(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		
		String sql=" insert into dm_zju_xq(dm,xqmc,sjly) values(?,?,?)";
		
		try {
			return dao.insert(sql, new String[]{myForm.getXqdm(),myForm.getXqmc(),"手动"});
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return false;
		}	
		
	}
	
	public boolean modiXqxx(GyglGywhForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		
		String sql=" update dm_zju_xq set xqmc=? where dm=? ";
		
		return dao.runUpdate(sql, new String[]{myForm.getXqmc(),myForm.getXqdm()});
	}
	
	public boolean addYqxx(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		
		String sql=" insert into yqdmb(yqdm,yqmc,xqdm,sjly) values(?,?,?,?)";
		
		try {
			return dao.insert(sql, new String[]{myForm.getYqdm(),myForm.getYqmc(),myForm.getXqdm(),"手动"});
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return false;
		}	
	}
	
	public boolean modiYqxx(GyglGywhForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		
		String sql=" update yqdmb set yqmc=?,xqdm=? where yqdm=? ";
		
		return dao.runUpdate(sql, new String[]{myForm.getYqmc(),myForm.getXqdm(),myForm.getYqdm()});
	}
	
	public boolean modiLdxx(GyglGywhForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		
		String sql=" update sslddmb set ldmc=?,xqdm=?,xbxd=?,cs=?,yqdm=?,bz=? where lddm=? ";
		if(Base.isNull(myForm.getYqdm())){
			myForm.setYqdm("无");
		}
		
		if(Base.isNull(myForm.getXqdm())){
			myForm.setXqdm("无");
		}
		return dao.runUpdate(sql, new String[]{myForm.getLdmc(),myForm.getXqdm(),
				myForm.getXbxd(),myForm.getCs(),myForm.getYqdm(),myForm.getBz(),myForm.getLddm()});
	}
	
	public boolean modiQsxx(GyglGywhForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		
		String sql=" update ssxxb set cws=?,kfhz=?,fpbj=?,xb=?,qsdh=?,sfbz=?,bz=? where lddm=? and cs=? and qsh=?  ";
		
		return dao.runUpdate(sql, new String[]{myForm.getCws(),myForm.getKfhz(),
				myForm.getFpbj(),myForm.getXb(),myForm.getQsdh(),myForm.getSfbz(),myForm.getBz(),myForm.getLddm(),
				myForm.getCs(),myForm.getQsh()});
	}
	
	public boolean addLdxx(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		
		String sql=" insert into sslddmb(lddm,ldmc,xqdm,xbxd,cs,yqdm,sjly,bz) values(?,?,?,?,?,?,?,?)";
		if(Base.isNull(myForm.getYqdm())){
			myForm.setYqdm("无");
		}
		
		if(Base.isNull(myForm.getXqdm())){
			myForm.setXqdm("无");
		}
		
		try {
			return dao.insert(sql, new String[]{myForm.getLddm(),myForm.getLdmc(),myForm.getXqdm(),
					myForm.getXbxd(),myForm.getCs(),myForm.getYqdm(),"手动",myForm.getBz()});
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return false;
		}	
	}
	
	public boolean addQsxx(GyglGywhForm myForm) {
		DAO dao=DAO.getInstance();
		
		String sql=" insert into ssxxb(lddm,cs,qsh,cws,fpbj,qsdh,sfbz,xb,kfhz,bz,sjly) values(?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			return dao.insert(sql, new String[]{myForm.getLddm(),myForm.getCs(),myForm.getQsh(),myForm.getCws(),
					myForm.getFpbj(),myForm.getQsdh(),myForm.getSfbz(),myForm.getXb(),myForm.getKfhz(),myForm.getQsbz(),"手动"});
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return false;
		}	
	}
	
	
	/**
	 * 获取校区信息
	 * @return
	 */
	public List<HashMap<String,String>>getXqList(){
		String sql="select dm,xqmc mc from dm_zju_xq ";
		DAO dao=DAO.getInstance();
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 获取园区信息(DWR 楼栋维护)
	 * 
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getYqxxList(String xqdm) {
		String sql = "select yqdm dm,yqmc mc,xqdm from yqdmb where 1 = 1 ";
		DAO dao = DAO.getInstance();

		if (!Base.isNull(xqdm)) {
			sql += " and xqdm = '" + xqdm + "'";
		}
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	/**
	 * 获取园区信息(DWR 楼栋维护)
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getLdxxList(String yqdm,String xqdm){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		StringBuilder query=new StringBuilder();
		List<String>inputList=new ArrayList<String>();
		
		if(!"".equalsIgnoreCase(yqdm)){
			query.append(" and yqdm=? ");
			inputList.add(yqdm);
		}
		
		if(!"".equalsIgnoreCase(xqdm)){
			query.append(" and xqdm=? ");
			inputList.add(xqdm);
		}
		
		sql.append("select lddm dm,ldmc mc from sslddmb where 1=1 ");
		sql.append(query);
		return dao.getList(sql.toString(),inputList.toArray(new String[]{}), new String[]{"dm","mc"});
	}
	
	/**
	 * 删除基础数据
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public int[] delJcsj(GyglGywhForm myForm) throws Exception {
		
		DAO dao = DAO.getInstance();
		//表名
		String tableName = myForm.getTableName();
		//条件
		String query = myForm.getQuery();
		//主键
		String[]checkVal=myForm.getCheckVal();
		
		String[]sqlArr=new String[checkVal.length];
		for(int i=0;i<sqlArr.length;i++){
			sqlArr[i] = " delete from " + tableName + query+"'"+checkVal[i]+"'";
		}
		return dao.runBatch(sqlArr);
	}
	
	/**
	 * 根据传入的楼栋、层数、寝室号获取寝室住宿信息
	 * @param zsxxArr
	 * @return
	 */
	public List<HashMap<String,String>>getRzryList(String[]zsxxArr){
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh,xm,cwh from xg_view_gygl_xszsxx where lddm=? and cs=? and qsh=? order by cwh asc");
		return dao.getList(sql.toString(), zsxxArr, new String[]{"xh","xm","cwh"});
	}
	
	/**
	 * 楼栋楼层寝室数
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>>getLdlcQss(GyglGywhForm myForm){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		ArrayList<String>input=new ArrayList<String>();
		String[]outputValue={"qss","lddm","cs"};
		
		sql.append(" select count(1)qss,lddm,cs from ssxxb where 1=1   ");
		if(!Base.isNull(myForm.getLddm())){
			sql.append(" and lddm = ? ");
			input.add(myForm.getLddm());
		}
		
		sql.append(" group by lddm,cs order by lddm,to_number(cs) ");
		
		return dao.getList(sql.toString(),input.toArray(new String[]{}), outputValue);
	}
	
	/**
	 * 楼栋楼层信息列表
	 * @param lddm
	 * @return
	 */
	public List<HashMap<String, String>> getLdlcList(String lddm) {
		GyglGywhForm myForm = new GyglGywhForm();
		myForm.setLddm(lddm);
		HashMap<String, String> ldMap = getLdxx(myForm);
		ldMap.put("qss", "0");
		List<HashMap<String, String>> qssList = getLdlcQss(myForm);
		List<HashMap<String, String>> ldlcList = new ArrayList<HashMap<String, String>>();
		int cs = Integer.parseInt(ldMap.get("cs"));
		for (int i = 1; i <= cs; i++) {
			HashMap<String, String> ldlcMap = new HashMap<String, String>();
			ldlcMap.put("lddm", ldMap.get("lddm"));
			ldlcMap.put("ldmc", ldMap.get("ldmc"));
			ldlcMap.put("xqdm", ldMap.get("xqdm"));
			ldlcMap.put("xbxd", ldMap.get("xbxd"));
			ldlcMap.put("cs", ldMap.get("cs"));
			ldlcMap.put("xqmc", ldMap.get("xqmc"));
			ldlcMap.put("yqmc", ldMap.get("yqmc"));
			ldlcMap.put("qss", ldMap.get("qss"));
			ldlcMap.put("xb", ldMap.get("xbxd"));
			for (int j = 0; j < qssList.size(); j++) {
				HashMap<String, String> qssMap = qssList.get(j);
				if (String.valueOf(i).equalsIgnoreCase(qssMap.get("cs"))) {
					ldlcMap.put("qss", qssMap.get("qss"));
					ldlcMap.put("cs", qssMap.get("cs"));
					break;
				}
			}
			ldlcList.add(ldlcMap);
		}

		return ldlcList;
	}
	
	/**
	 * 获取学生详细住宿信息
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXszsxxInfo(GyglGywhForm myForm) throws Exception{
		StringBuilder sql= new StringBuilder();
		sql.append(" select a.xh,a.ydsj,  a.ydqrzsj, ");
		sql.append(" case when b.ldmc is null then '该床位不存在' else  ");
		sql.append(" b.ldmc || ydqcs || '楼' || ydqqsh || '寝室' || ydqcwh || '号床' end ydqcw,  ");
		sql.append(" a.ydhcw  from (select a.xh,a.xn,a.xq,a.ydsj,a.ydqrzsj,");
		sql.append(" case when b.ldmc is null then '该床位不存在' else ");
		sql.append(" b.ldmc || ydhcs || '楼' || ydhqsh || '寝室' || ydhcwh || '号床' end ydhcw, ");
		sql.append(" a.ydqlddm,a.ydqcs,a.ydqqsh,a.ydqcwh from ssydxxb a  ");
		sql.append(" left join sslddmb b on a.ydhlddm = b.lddm) a left join sslddmb b on a.ydqlddm = b.lddm ");
		sql.append(" where xh=?  ");
		sql.append(" union "); 
		sql.append("select xh,rzrq,tfrq,ldmc || '1' || '楼' || qsh || '寝室' || cwh || '号床'  ydqcw,'已经退宿' ydhcw from xszslsxxb ");
		sql.append("where xh=?  order by ydsj ");
		
		String []inPutList={myForm.getXh(),myForm.getXh()};
		String [] colList={"ydsj","ydqrzsj","ydqcw","ydhcw"};
		return CommonQueryDAO.commonQueryNotFy("", "", inPutList, colList, sql.toString(), myForm);
	}
	
	/**
	 * 根据楼栋性别修改情况修改寝室性别
	 * @param myForm
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean updateQsxb(GyglGywhForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" update ssxxb set xb=? where lddm=? ");
		return dao.runUpdate(sql.toString(), new String[]{myForm.getXbxd(),myForm.getLddm()});
	}
	
	/**
	 * 批量修改寝室信息
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean updateQsxx(GyglGywhForm myForm, User user) throws Exception {

		String[] hiddenArr = myForm.getRzqkHidArr();
		String[] xbxdArr = myForm.getXbxdHidArr();
		String[] sql = new String[hiddenArr.length];

		int n = 0;
		for (int i = 0; i < myForm.getCheckVal().length; i++) {
			boolean bolg = false;
			if ("空的".equalsIgnoreCase(hiddenArr[i])) {
				sql[n] ="";
				sql[n] += "  update ssxxb set ";
				if (!Base.isNull(myForm.getXb())
						&& !"不修改".equalsIgnoreCase(myForm.getXb())
						&& "混合".equalsIgnoreCase(xbxdArr[i])) {

					sql[n] += " xb='" + myForm.getXb() + "'";
					bolg = true;

				}
				if (!Base.isNull(myForm.getSfbz())) {
					if (bolg) {
						sql[n] += ",";
					}
					sql[n] += "sfbz='" + myForm.getSfbz() + "' ";
					bolg = true;
				}
				if (!Base.isNull(myForm.getFpbj())
						&& !"不修改".equalsIgnoreCase(myForm.getFpbj())) {
					if (bolg) {
						sql[n] += ",";
					}
					sql[n] += " fpbj='" + myForm.getFpbj() + "' ";
					bolg = true;
				}
				if (!Base.isNull(myForm.getKfhz())
						&& !"不修改".equalsIgnoreCase(myForm.getKfhz())) {
					if (bolg) {
						sql[n] += ",";
					}
					sql[n] += " kfhz='" + myForm.getKfhz() + "' ";
					bolg=true;
				}
				sql[n] += " where lddm||'!!@!!'||cs||'!!@!!'||qsh= '"
						+ myForm.getCheckVal()[i] + "' ";
				if(bolg){
					
					n++;
				}else{
					sql[n]="";
				}
			}
		}
		return saveArrDate(sql);
	}
	
	/**
	 * 获取空闲寝室数(根据楼栋,层数统计)
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getKxqss(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		List<String>inputV=new ArrayList<String>();
		sql.append("  select b.lddm,b.cs,nvl(count(1),0)kxqss from(  ");
		
		//角色权限判断
		sql.append(userStatusSql(myForm));
		
		sql.append(" )a left join  ");
		
		sql.append(" (select lddm,cs,qsh,cws,count(1)rzrs from( ");
		sql.append(" select a.lddm,a.cs,a.qsh,a.cws from ssxxb a   ");
		sql.append(" left join xszsxxb b on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh  ");
		sql.append(" where 1=1 and exists(select 1 from xg_gygl_qsfpb c where a.lddm=c.lddm and a.cs=c.cs and a.qsh=c.qsh and c.kffp='yes') ");
		sql.append(" )group by lddm,cs,qsh,cws)b on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh  ");
		sql.append(" where b.cws>b.rzrs  ");
		if(!Base.isNull(myForm.getLddm())){
			sql.append(" and  b.lddm=? ");
			inputV.add(myForm.getLddm());
		}
		sql.append(" group by b.lddm,b.cs order by b.lddm,b.cs  ");
		
		return dao.getList(sql.toString(), inputV.toArray(new String[]{}), new String[]{"lddm","cs","kxqss"});
	}
	
	/**
	 * 获取空床位(根据楼栋,层数统计)
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getWfpcws(GyglGywhForm myForm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		List<String>inputV=new ArrayList<String>();
		
		sql.append(" select count(1)wrzcw,b.lddm,b.cs from ( ");
		
		sql.append("   select * from xg_gygl_cwxxb a where not exists( ");
		sql.append("  select 1 from xszsxxb b where a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh and a.cwh=b.cwh) ");
		sql.append(" and exists(select 1 from xg_gygl_qsfpb c where a.lddm=c.lddm and a.cs=c.cs and a.qsh=c.qsh and c.kffp='yes')  ");
		sql.append(" and exists(select 1 from ssxxb c where a.lddm=c.lddm and a.cs=c.cs and a.qsh=c.qsh and c.fpbj='一般')  ");
		sql.append(" and not exists(select 1 from xg_gygl_qtcwxxb c where a.lddm=c.lddm and a.qsh=c.qsh and a.cs=c.cs and a.cwh=c.cwh)  ");
		
		
		sql.append(" )b left join ( ");
		
		sql.append(userStatusSql(myForm));
		sql.append(" )a on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh where 1=1 ");
		
		if(!Base.isNull(myForm.getLddm())){
			sql.append(" and  b.lddm=? ");
			inputV.add(myForm.getLddm());
		}
		
		sql.append(" group by b.lddm,b.cs order by b.lddm,b.cs  ");
		
		System.out.println(sql);
		return dao.getList(sql.toString(), inputV.toArray(new String[]{}), new String[]{"lddm","cs","wrzcw"});
	}
	
	/**
	 * 根据用户权限获取查询范围
	 * @param myForm
	 * @return
	 */
	public StringBuilder userStatusSql(GyglGywhForm myForm){

		StringBuilder sql=new StringBuilder();
		//分配对象
		String fpdx=myForm.getFpdx();
		//用户
		User user=myForm.getUser();
		
		String userStatus=user.getUserStatus();
		String userName=user.getUserName();
		String userDep=user.getUserDep();
		
			sql.append(" select lddm,cs,qsh from xg_gygl_qsfpb a ");
			sql.append("  where exists (select 1  from xg_gygl_ldglb b ");
			sql.append("  where b.yhm = '"+userName+"'  and a.lddm = b.lddm) ");
			sql.append(" union ");
		
		
		//============================学院用户访问权限判断===================================
		if(("xy".equalsIgnoreCase(fpdx) || "njxy".equalsIgnoreCase(fpdx))
				&& ("xy".equalsIgnoreCase(userStatus) || "fdy".equalsIgnoreCase(userStatus)
				|| "bzr".equalsIgnoreCase(userStatus))){
			sql.append(" select a.lddm,a.cs,a.qsh  from xg_gygl_qsfpb a, xg_gygl_jbszb b ");
			sql.append(" where a.fpdx = b.fpdx and (a.fpdx = 'xy' or a.fpdx = 'njxy') ");
			sql.append(" and a.kffp='yes' and a.bmdm = '"+userDep+"' ");
		}else if("njzy".equalsIgnoreCase(fpdx)
				&& ("xy".equalsIgnoreCase(userStatus) || "fdy".equalsIgnoreCase(userStatus)
				|| "bzr".equalsIgnoreCase(userStatus))){
			sql.append("  select a.lddm,a.cs,a.qsh   from xg_gygl_qsfpb a, xg_gygl_jbszb b  ");
			sql.append(" where a.fpdx = b.fpdx and a.fpdx = 'njzy' ");
			sql.append(" and a.kffp='yes' and exists(select 1 from view_njxyzybj c where xydm='"+userDep+"' and a.bmdm=c.zydm) ");
		}else if("bj".equalsIgnoreCase(fpdx)
				&& "xy".equalsIgnoreCase(userStatus)){
			sql.append("  select a.lddm,a.cs,a.qsh   from xg_gygl_qsfpb a, xg_gygl_jbszb b  ");
			sql.append(" where a.fpdx = b.fpdx and a.fpdx = 'njzy' ");
			sql.append(" and a.kffp='yes' and exists(select 1 from view_njxyzybj c where xydm='"+userDep+"' and a.bmdm=c.bjdm) ");
		}else if("bj".equalsIgnoreCase(fpdx)
				&& "fdy".equalsIgnoreCase(userStatus)){
			sql.append("  select a.lddm,a.cs,a.qsh   from xg_gygl_qsfpb a, xg_gygl_jbszb b  ");
			sql.append(" where a.fpdx = b.fpdx and a.fpdx = 'njzy' ");
			sql.append(" and a.kffp='yes' and exists(select 1 from fdybjb c where zgh='"+userName+"'  and a.bmdm=c.bjdm) ");
		}else if("bj".equalsIgnoreCase(fpdx)
				&& "bzr".equalsIgnoreCase(userStatus)){
			sql.append("  select a.lddm,a.cs,a.qsh   from xg_gygl_qsfpb a, xg_gygl_jbszb b  ");
			sql.append(" where a.fpdx = b.fpdx and a.fpdx = 'njzy' ");
			sql.append(" and a.kffp='yes' and exists(select 1 from bzrbbb c where zgh='"+userName+"'  and a.bmdm=c.bjdm) ");
		}else if("bj".equalsIgnoreCase(fpdx)
				&& "jd".equalsIgnoreCase(userStatus)){
			sql.append("  select a.lddm,a.cs,a.qsh   from xg_gygl_qsfpb a, xg_gygl_jbszb b  ");
			sql.append(" where a.fpdx = b.fpdx and a.fpdx = 'njzy' ");
			sql.append(" and a.kffp='yes' and exists ((select 1 from fdybjb c where zgh='"+userName+"'  and a.bmdm=c.bjdm) ");
			sql.append(" or (select 1 from bzrbbb c where zgh='"+userName+"'  and a.bmdm=c.bjdm)) ");
		}
		//============================学院用户访问权限判断end===================================
		
		if("xx".equalsIgnoreCase(userStatus)){	
			sql.append(" select a.lddm,a.cs,a.qsh  from ssxxb a");
		}

		return sql;
	}
}
