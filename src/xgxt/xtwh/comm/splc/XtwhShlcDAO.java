package xgxt.xtwh.comm.splc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class XtwhShlcDAO extends CommDAO {
	
//	-------------------------------这是今天要中更多 的方法大师写下面 begin----------------------------------
	/**
	 * 获取审批流程结果集
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSplcList(XtwhShlcForm model) throws Exception{

		MakeQuery mQuery = new MakeQuery();
		StringBuilder sql = new StringBuilder();

		String[] colList = {"ssmk"};
		String[] colLikeList = {"lcmc"};

		mQuery.makeQuery(colList, colLikeList, model);
		
		// ================审核流基本信息========================
		sql.append(" select rownum r,a.*,replace(b.spnr,';','->')spnr,  ");
//		sql.append(" case when c.sycs is null then '0' else c.sycs end sycs, ");
//		sql.append(" case when c.sycs is null then '' else 'disabled' end dis ");
		sql.append(" decode(nvl(c.sycs,0),0,'否','是') as sycs, decode(c.sycs,null,'','disabled') as dis ");
		sql.append(" from (select id lcid,djlx ssmk,mc lcmc,mkmc  from  ");
		sql.append(" xg_xtwh_splc a left join xg_xtwh_splcmkdzb b on b.mkdm=a.djlx ");
		sql.append(" )a   ");
		
		// ================审核流审核内容========================
		// edit by hujia ，reason is 11g WM_CONCAT禁止使用外面嵌套2层及以上的循环，最好别使用max函数
		sql.append(" left join ");
		sql.append(" (select splc, spnr from (select a.splc, ");
		sql.append(" to_char(WM_CONCAT(b.mc) over(partition by a.splc order by xh)) spnr,xh,row_number() over(partition by a.splc order by xh desc ) as ddd ");
		sql.append(" from xg_xtwh_spbz a left join xg_xtwh_spgw b on a.spgw = b.id) b where ddd = 1 )b on a.lcid=b.splc  ");
		
		// ================审核流使用情况========================
//		sql.append(" left join( select splc,to_char(count(1))sycs from ( ");
//		sql.append(getSplcSql(model));
//		sql.append(" ) group by splc)c on a.lcid=c.splc ");
		sql.append("left join ( select lcid,count(*) as sycs from XG_XTWH_SHZTB group by lcid ) c on a.lcid=c.lcid  ");
		
		return CommonQueryDAO.commonQuery(sql.toString(), mQuery
				.getQueryString(), mQuery.getInputList(), new String[]{"dis","lcid","lcmc","mkmc","spnr","sycs"}, model);
	}
	
	/**
	 * 根据审核流程加载审批岗位
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getSpgwByShl(XtwhShlcForm model) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.splc,a.xh,a.spgw,a.mc,");
			
		sql.append("case when gwlx is null then '' else 'disabled' end ");
		
		sql.append("  gwlx from( ");
		sql.append(" select b.splc,b.xh,b.spgw,c.mc,c.gwlx from( ");
		sql.append(" select id from xg_xtwh_splc where 1=1 ");
		
		sql.append(" and id='"+model.getPkValue()+"'");
		
		sql.append(" )a left join xg_xtwh_spbz b on a.id=b.splc ");
		sql.append(" left join xg_xtwh_spgw c on b.spgw=c.id ");
		sql.append(" )a order by xh ");

		return dao.getList(sql.toString(), new String[] {},
				new String[] { "splc", "xh", "spgw", "mc","gwlx" });
	}
	
	/**
	 * 特殊岗位
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getTsgwByShl(XtwhShlcForm model) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("  select id spgw,mc,gwlx from xg_xtwh_spgw where gwlx='1' ");

		return dao.getList(sql.toString(), new String[] {},
				new String[] {"spgw", "mc","gwlx" });
	}
	
	/**
	 * 获取用户组
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getYhzInfo(XtwhShlcForm model) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select zdm,zmc from yhzb  order by xssx ");

		return dao.getList(sql.toString(), new String[] {},
				new String[] { "zdm", "zmc"});
	}
	
	/**
	 * 获取可选用户(系统维护-审批流程维护-审批流程)
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getKxyhInfo(XtwhShlcForm model) {
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		List<String>inputV=new ArrayList<String>();
		inputV.add(model.getZdm());
		inputV.add(model.getSpgw());
		sql.append(" select rownum r,yhm,xm  from yhb a  where zdm = ? ");
		sql.append(" and not exists (select 1  from xg_xtwh_spgwyh b");
		sql.append(" where spgw =? and a.yhm = b.spyh) ");
		
		String[]yhm=model.getYhm();
		if(yhm!=null && yhm.length>0){
			for(int i=0;i<yhm.length;i++){
				
				sql.append(" and  yhm<>? ");
				inputV.add(yhm[i]);
				
			}
			
		}
		return dao.getList(sql.toString(), inputV.toArray(new String[]{}) ,
				new String[] { "yhm", "xm" });
	}
	/**
	 * 获取可选用户(日常事务NEW-日常行为维护NEW-日常行为代码维护-日常行为类别)
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getKxyhInfoRcxwwh(XtwhShlcForm model) {
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		List<String>inputV=new ArrayList<String>();
		inputV.add(model.getZdm());
		inputV.add(model.getSpgw());
		sql.append(" select rownum r,yhm,xm  from yhb a  where zdm = ? ");
		sql.append(" and not exists (select 1  from XG_RCSW_NEW_RCXWSQB b");
		sql.append(" where rcxwlbdm =? and a.yhm = b.zgh) ");
		
		String[]yhm=model.getYhm();
		if(yhm!=null && yhm.length>0){
			for(int i=0;i<yhm.length;i++){
				
				sql.append(" and  yhm<>? ");
				inputV.add(yhm[i]);
				
			}
			
		}
		return dao.getList(sql.toString(), inputV.toArray(new String[]{}) ,
				new String[] { "yhm", "xm" });
	}
	
	/**
	 * 获取已选用户(系统维护-审批流程维护-审批流程)
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getYxyhInfo(XtwhShlcForm model) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String>inputV=new ArrayList<String>();
		inputV.add(model.getSpgw());
		sql.append(" select a.yhm,a.xm,(case when shr is not null then 'disabled' else '' end)dis from   ");
		sql.append(" (select yhm,xm  from yhb a  where exists (select 1  from xg_xtwh_spgwyh b ");
		sql.append(" where spgw =? and a.yhm = b.spyh))a ");
		sql.append(" left join(select shr from ( ");
		sql.append(getGwkzSql(model));
		sql.append(" )where shr is not null group by shr  ");
		sql.append(" )b on a.yhm=b.shr ");
		
		String[]yhm=model.getYhm();
		if(yhm!=null && yhm.length>0){
			for(int i=0;i<yhm.length;i++){
				
				sql.append(" or  yhm=? ");
				inputV.add(yhm[i]);
				
			}
			
		}
		
		return dao.getList(sql.toString(),inputV.toArray(new String[]{}) ,
				new String[] { "yhm", "xm","dis"});
	}
	/**
	 * 获取已选用户(日常事务NEW-日常行为维护NEW-日常行为代码维护-日常行为类别)
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getYxyhInfoRcxwwh(XtwhShlcForm model) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String>inputV=new ArrayList<String>();
		inputV.add(model.getSpgw());
		sql.append(" select a.yhm,a.xm,(case when shr is not null then 'disabled' else '' end)dis from   ");
		sql.append(" (select yhm,xm  from yhb a  where exists (select 1  from XG_RCSW_NEW_RCXWSQB b ");
		sql.append(" where rcxwlbdm =? and a.yhm = b.zgh))a ");
		sql.append(" left join(select shr from ( ");
		sql.append(getGwkzSql(model));
		sql.append(" )where shr is not null group by shr  ");
		sql.append(" )b on a.yhm=b.shr ");
		
		String[]yhm=model.getYhm();
		if(yhm!=null && yhm.length>0){
			for(int i=0;i<yhm.length;i++){
				
				sql.append(" or  yhm=? ");
				inputV.add(yhm[i]);
				
			}
			
		}
		
		return dao.getList(sql.toString(),inputV.toArray(new String[]{}) ,
				new String[] { "yhm", "xm","dis"});
	}
	
	/**
	 * 获取已选用户(删除后)
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getYxyhByDel(XtwhShlcForm model) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String>inputV=new ArrayList<String>();
		
		sql.append(" select a.yhm,a.xm,(case when shr is not null then 'disabled' else '' end)dis from( ");
		sql.append(" select yhm,xm  from yhb a  ");
		sql.append(" where 1=1 ");
		String[]yhm=model.getYhm();
		if(yhm!=null && yhm.length>0){
			for(int i=0;i<yhm.length;i++){
				if(i==0){
					sql.append(" and ( ");
				}else {
					sql.append(" or  ");
				}				
				sql.append(" yhm=?  ");
				inputV.add(yhm[i]);
				
			}
			sql.append(")");
		}else{
			
			sql.append(" and 1=2 ");
		}
		sql.append(" ) a ");
		sql.append(" left join(select shr from ( ");
		sql.append(getGwkzSql(model));
		sql.append(" )where shr is not null group by shr ");
		sql.append(" )b on a.yhm=b.shr");

		return dao.getList(sql.toString(),inputV.toArray(new String[]{}) ,
				new String[] { "yhm", "xm","dis"});
	}
	
	/**
	 * 获取已选用户(删除后)
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getKxyhByDel(XtwhShlcForm model) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String>inputV=new ArrayList<String>();
		
		inputV.add(model.getZdm());
		
		sql.append(" select rownum r,yhm,xm  from yhb a  where zdm = ? ");
		
		String[]yhm=model.getYhm();
		if(yhm!=null && yhm.length>0){
			for(int i=0;i<yhm.length;i++){
				
				sql.append(" and  yhm<>? ");
				inputV.add(yhm[i]);
				
			}
			
		}
		
		if(!Base.isNull(model.getUserName())){
			
			sql.append(" and (yhm like '%"+model.getUserName()+"%' ");
			sql.append(" or xm like '%"+model.getUserName()+"%' ) ");
		}
		
		return dao.getList(sql.toString(),inputV.toArray(new String[]{}) ,
				new String[] { "yhm", "xm"});
	}
	
	/**
	 * 获取所属模块列表
	 * @return
	 */
	public List<HashMap<String,String>>getSsmkList(){
		DAO dao = DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select mkdm,mkmc from xg_xtwh_splcmkdzb order by mkdm ");
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"mkdm","mkmc"});
	}
	
	/**
	 * 获取审核流程应用设置表 
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getShlcszList(){
		DAO dao = DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select gnmc,tablename,querysql,xn,xq,nd ");
		sql.append(" ,dzgwid,dzxn,dzxq,dznd,dzshr,dzxmid ");
		sql.append(" from xg_ty_shlcszb where tablename is not null ");
		
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"gnmc","tablename", "querysql", "xn", "xq", "nd","dzgwid","dzxn","dzxq","dznd","dzshr","dzxmid" });
	}
	
	/**
	 * 审批流程模块应用信息获取（SQL）
	 * @param model
	 * @return
	 */
	public String getSplcSql(XtwhShlcForm model){
		List<HashMap<String,String>>shlcszList=getShlcszList();
		
		StringBuilder sql=new StringBuilder();
		for(int i=0;i<shlcszList.size();i++){
			HashMap<String,String>shlcszMap=shlcszList.get(i);
			
			String tableName=shlcszMap.get("tablename");
			String gnmc=shlcszMap.get("gnmc");
			
			String xn=shlcszMap.get("xn");
			String xq=shlcszMap.get("xq");
			String nd=shlcszMap.get("nd");
			
			String dzxn=shlcszMap.get("dzxn");
			String dzxq=shlcszMap.get("dzxq");
			String dznd=shlcszMap.get("dznd");
			String dzxmdm=shlcszMap.get("dzxmid");
			
			if(i!=0){
				sql.append(" union all ");
			}
			sql.append(" select a.lcid splc ");
			
			sql.append("  from xg_ty_shlcszb a ");
			if (!Base.isNull(dzxmdm)) {
				sql.append(" left join " + tableName + " b on a.gnmc = b."
						+ dzxmdm);
				sql.append("  where b." + dzxmdm + " = '" + gnmc + "' ");
			} else {
				sql.append(" where gnmc='"+gnmc+"' ");
			}
			
			if(!Base.isNull(xn)){
				
				if("dqxn".equalsIgnoreCase(xn)){
					sql.append(" and "+dzxn+"=(select dqxn b from xtszb where rownum=1 )");
				}else if("pjxn".equalsIgnoreCase(xn)){
					sql.append(" and "+dzxn+"=(select pjxn b from xg_pjpy_xtszb where rownum=1 )");
				}
				
			}
			if(!Base.isNull(xq)){
				if("dqxq".equalsIgnoreCase(xq)){
					sql.append(" and "+dzxq+"=(select dqxq b from xtszb where rownum=1 )");
				}else if("pjxq".equalsIgnoreCase(xq)){
					sql.append(" and "+dzxq+"=(select pjxq b from xg_pjpy_xtszb where rownum=1 )");
				}
			}
			if(!Base.isNull(nd)){
				if("dqnd".equalsIgnoreCase(nd)){
					sql.append(" and "+dznd+"=(select dqnd b from xtszb where rownum=1 )");
				}else if("pjnd".equalsIgnoreCase(nd)){
					sql.append(" and "+dznd+"=(select pjnd b from xg_pjpy_xtszb where rownum=1 )");
				}
			}
			
			sql.append(" group by  ");
			if(!Base.isNull(dzxmdm)){
				sql.append("b."+dzxmdm+",");
			}
			sql.append(" a.lcid");
			
		}
		
		if(shlcszList.size()==0){
			
			sql.append(" select lcid splc from xg_ty_shlcszb ");
		}
		return sql.toString();
	}
	
	/**
	 * 获取岗位设置应用信息（sql）
	 * @param model
	 * @return
	 */
	public String getGwkzSql(XtwhShlcForm model){
		List<HashMap<String,String>>shlcszList=getShlcszList();
		
		StringBuilder sql=new StringBuilder();
		for(int i=0;i<shlcszList.size();i++){
			HashMap<String,String>shlcszMap=shlcszList.get(i);
			String tableName=shlcszMap.get("tablename");
			String spgwname=shlcszMap.get("dzgwid");
			String dzshr=shlcszMap.get("dzshr");
			
			String xn=shlcszMap.get("xn");
			String xq=shlcszMap.get("xq");
			String nd=shlcszMap.get("nd");
			
			String dzxn=shlcszMap.get("dzxn");
			String dzxq=shlcszMap.get("dzxq");
			String dznd=shlcszMap.get("dznd");
			if(i!=0){
				sql.append(" union all ");
			}
			sql.append("select "+dzshr+" shr  from "+tableName+" a where "+spgwname+"='"+model.getSpgw()+"' ");
			if(!Base.isNull(xn)){
				
				if("dqxn".equalsIgnoreCase(xn)){
					sql.append(" and "+dzxn+"=(select dqxn b from xtszb where rownum=1 )");
				}else if("pjxn".equalsIgnoreCase(xn)){
					sql.append(" and "+dzxn+"=(select pjxn b from xg_pjpy_xtszb where rownum=1 )");
				}
				
			}
			if(!Base.isNull(xq)){
				if("dqxq".equalsIgnoreCase(xq)){
					sql.append(" and "+dzxq+"=(select dqxq b from xtszb where rownum=1 )");
				}else if("pjxq".equalsIgnoreCase(xq)){
					sql.append(" and "+dzxq+"=(select pjxq b from xg_pjpy_xtszb where rownum=1 )");
				}
			}
			if(!Base.isNull(nd)){
				if("dqnd".equalsIgnoreCase(nd)){
					sql.append(" and "+dznd+"=(select dqnd b from xtszb where rownum=1 )");
				}else if("pjnd".equalsIgnoreCase(nd)){
					sql.append(" and "+dznd+"=(select pjnd b from xg_pjpy_xtszb where rownum=1 )");
				}
			}
		
		}
		
		if(shlcszList.size()==0){
			
			sql.append(" select shr from   ( select '' shr from dual) where shr is not null ");
		}
		return sql.toString();
	}
	
	/**
	 * 删除审核流程
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean delShlc(XtwhShlcForm form) throws Exception{
		
		String[]pkValue=form.getCheckVal();
		String[]delSql=new String[pkValue.length];
		for(int i=0;i<pkValue.length;i++){
			
			delSql[i]=" delete from xg_xtwh_splc where id='"+pkValue[i]+"' ";
		}
		return saveArrDate(delSql);
	}
	
	/**
	 * 删除审核流程相关岗位（通用岗位信息除外）
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean delLcxggw(XtwhShlcForm form) throws Exception{
		
		String[]pkValue=form.getCheckVal();
		String[]delSql=new String[pkValue.length];
		for(int i=0;i<pkValue.length;i++){
			delSql[i]=" delete from xg_xtwh_spgw a  where exists (select 1 ";
			delSql[i]+=" from xg_xtwh_spbz b left join xg_xtwh_spgw c on b.spgw = c.id ";
			delSql[i]+="  where b.splc = '"+pkValue[i]+"' and a.id = b.spgw and c.gwlx<>'1') ";
		}
		return saveArrDate(delSql);
	}
	
	/**
	 * 删除审核流与审核岗位关联表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean delShbz(XtwhShlcForm form) throws Exception{
		
		String[]pkValue=form.getCheckVal();
		String[]delSql=new String[pkValue.length];
		for(int i=0;i<pkValue.length;i++){
			delSql[i]=" delete from xg_xtwh_spbz where splc='"+pkValue[i]+"'";
		}
		return saveArrDate(delSql);
	}
	
	/**
	 * 获取模块类型信息
	 * @return
	 */
	public List<HashMap<String,String>>getMklxInfo(){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select b.mkdm,b.mkmc from  xg_xtwh_splc a,");
		sql.append(" xg_xtwh_splcmkdzb b where a.djlx=b.mkdm  ");
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"mkdm","mkmc"});
	}
	
	//	-------------------------------这是今天要中更多 的方法大师写下面 end----------------------------------

	public List<String[]> getLcgw(String lcid) {
		DAO dao = DAO.getInstance();
		String[] colList = new String[]{"id","mc","gwlx","yhs","gwz"};
		String sql ="select b.id,b.mc,b.gwlx,b.gwz,(select count(*) from xg_xtwh_spgwyh c where c.spgw=a.spgw) yhs from xg_xtwh_spbz a, xg_xtwh_spgw b  where a.spgw=b.id and a.splc=? order by xh";
		return dao.rsToVator(sql, new String[]{lcid},colList);
	}

	public String[] getTygwxx(String gwdm) {
		// TODO 自动生成方法存根
		DAO dao = DAO.getInstance();
		String sql = "select '固定岗位' lcmc, mc gwmc from xg_xtwh_spgw where id = ? and gwlx = '1'";
		return dao.getOneRs(sql, new String[]{gwdm},new String[]{"lcmc","gwmc"});
	}

	public String[] getGwxx(String gwdm,String lcid) {
//		 TODO 自动生成方法存根
		DAO dao = DAO.getInstance();
		String sql = "select a.mc lcmc,c.mc gwmc,case when c.gwlx is null then '自定义岗位' else '通用岗位' end gwlxmc from xg_xtwh_splc a,xg_xtwh_spbz b,xg_xtwh_spgw c  where a.id=b.splc and b.spgw = c.id and c.id = ? and a.id=?";
		return dao.getOneRs(sql, new String[]{gwdm,lcid},new String[]{"lcmc","gwmc","gwlxmc"});
	}
	
	
	/**
	 * 
	 * @描述:根据流程ID返回审批岗位 
	 * @作者：Penghui.Qu
	 * @日期：2013-5-8 下午06:47:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lcid
	 * @return
	 * @throws SQLException
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getSpgwByLcid(String lcid) throws SQLException{
		
		String sql = "select spgw from xg_xtwh_spbz where splc=? order by xh";
		
		return DAO.getInstance().getArray(sql, new String[]{lcid}, "spgw");
	}
	
	
	
	
	
	
	
	/**
	 * 根据模块类型获取审核流程信息
	 * @param djlx
	 * @return
	 */
	public List<HashMap<String,String>>getShlcByDjlx(String djlx){
		
		StringBuilder sql=new StringBuilder();
		DAO dao=DAO.getInstance();
		sql.append(" select splc,mc||'：'||replace(max(lcxx),';','->')lcxx from( ");
		sql.append("select splc,a.mc,to_char(WM_CONCAT(c.mc)over(  ");
		sql.append("partition by splc  order by xh))lcxx from xg_xtwh_splc a, ");
		sql.append("xg_xtwh_spbz b, ");
		sql.append("xg_xtwh_spgw c ");
		sql.append("where  djlx=? and a.id=b.splc and b.spgw=c.id) group by splc,mc ");
		
		return dao.getListNotOut(sql.toString(), new String[]{djlx});
	}
	
	/**
	 * 通用获取审核级别列表
	 * @param splcid
	 * @return
	 */
	public List<HashMap<String, String>> getSpjbListByGnmk(String splcid) {
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(
						"select a.spgw spgwdm,b.mc spgwmc from xg_xtwh_spbz a,xg_xtwh_spgw b where a.spgw=b.id and a.splc=? order by a.xh",
						new String[] { splcid });
	}

	/**
	 * @throws Exception  
	 * @描述:修改审批流程
	 * @作者：cq [工号：785]
	 * @日期：2014-1-2 下午07:31:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateShlc(XtwhShlcForm myForm) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_xtwh_splc set ms = ? where id = ?");
		
		return DAO.getInstance().runUpdate(sql.toString(), new String[]{myForm.getMs(),myForm.getId()});
	}
	
	/**
	 * 
	 * @描述:获取shlc名称
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-30 下午04:58:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param djlx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
   public HashMap<String,String> getShlcMap(String splc){
		
		StringBuilder sql=new StringBuilder();
		DAO dao=DAO.getInstance();
		sql.append(" select splc, mc || '：' || replace(max(lcxx), ';', '->') lcxx");
		sql.append(" from (select splc,");
		sql.append(" a.mc,");
		sql.append(" to_char(WM_CONCAT(c.mc) over(partition by splc order by xh)) lcxx");
		sql.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c");
		sql.append(" where ");
		sql.append(" a.id = b.splc");
		sql.append(" and b.spgw = c.id)  where splc = ?");
		sql.append("  group by splc, mc");
		sql.append(" ");
		sql.append(" ");
		return dao.getMapNotOut(sql.toString(), new String[]{splc});
	}
   
	   /** 
	 * @描述:根据现有岗位下的用户获取能插入的用户名(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-2-28 下午05:55:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param spgwid
	 * @param userStatus
	 * @return
	 * @throws SQLException
	 * List<String> 返回类型 
	 * @throws 
	 */
   public List<String> getGwyhForInsert(String spgwid,String userStatus) throws SQLException {
	   StringBuilder sql = new StringBuilder();
	   DAO dao=DAO.getInstance();
	   if(userStatus.equalsIgnoreCase("fdy")){
		   sql.append(" select distinct a.zgh from fdybjb a");
		   sql.append(" where not exists (select 1 from");
		   sql.append(" (select spyh from xg_xtwh_spgwyh where spgw = ?) b where b.spyh = a.zgh) ");
	   } else if(userStatus.equalsIgnoreCase("bzr")) {
		   sql.append(" select distinct a.zgh from bzrbbb a");
		   sql.append(" where not exists (select 1 from");
		   sql.append(" (select spyh from xg_xtwh_spgwyh where spgw = ?) b where b.spyh = a.zgh) ");
	   } else {
		   sql.append(" select distinct a.zgh from (select zgh from fdybjb union all select zgh from bzrbbb) a");
		   sql.append(" where not exists (select 1 from");
		   sql.append(" (select spyh from xg_xtwh_spgwyh where spgw = ?) b where b.spyh = a.zgh) ");
	   }
	   List<String> list = dao.getList(sql.toString(), new String[]{spgwid},"zgh");
	   return list;
   }
   
	   /** 
	 * @描述:根据选择的数据范围插入岗位用户(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-1 上午09:00:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param spgws
	 * @param list
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws 
	 */
   public boolean insertGwyh(String[] spgws,List<String[]> list) throws SQLException {
	   List<String[]> params = new ArrayList<String[]>();
	   for(int i = 0;i<spgws.length;i++){
		   String[] strs = list.get(i);
		   for(int j=0;j<strs.length;j++){			   
			   String[] paramss = new String[2];
			   paramss[0] = spgws[i];
			   paramss[1] = strs[j];
			   params.add(paramss);
		   }		   
	   }
	   DAO dao = DAO.getInstance();
	   String sql = "insert into xg_xtwh_spgwyh values(?,?)";
	   int[] num = dao.runBatch(sql,params);
	   return num.length>0;	   
   }
	   
	 /** 
	 * @描述:根据流程id获取相关的审批岗位和岗位组(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-6 下午04:00:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<String[]> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getSpgwAndGwz(String lcid){
		StringBuilder sb = new StringBuilder();
		sb.append("select a.spgw,b.gwz from xg_xtwh_spbz a ");
		sb.append("left join xg_xtwh_spgw b on a.spgw = b.id ");
		sb.append("where a.splc=? order by a.xh");
		DAO dao = DAO.getInstance();
		return dao.getList(sb.toString(), new String[]{lcid}, new String[]{"spgw","gwz"});
	}
	
	/**
	 * @throws Exception  
	 * @描述:根据审批岗位删除用户(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-7 下午03:52:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean deleteYhBySpgw(String[] spgwIds) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("delete from xg_xtwh_spgwyh where spgw in ('");
		for(int i=0;i<spgwIds.length;i++){
			sb.append(spgwIds[i]);
			if(i != spgwIds.length-1){
				sb.append("','");
			}
		}
		sb.append("')");
		DAO dao = DAO.getInstance();
		return dao.runUpdate(sb.toString(), new String[]{});
	}
	
	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-8 上午10:24:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shlcid
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public void deleteYhByShlc(XtwhShlcForm form) throws Exception{
		String[]pkValue=form.getCheckVal();
		StringBuilder sb = new StringBuilder();
		sb.append("delete from xg_xtwh_spgwyh where spgw in ");
		sb.append("(select a.spgw from xg_xtwh_spbz a,xg_xtwh_spgw b ");
		sb.append("where a.spgw = b.id and b.gwlx is null ");
		sb.append("and a.splc in (");
		for(int i=0;i<pkValue.length;i++){
			sb.append("'"+pkValue[i]+"'");
			if(i != pkValue.length-1){
				sb.append(",");
			}
		}
		sb.append("))");
		DAO dao = DAO.getInstance();
		dao.runUpdate(sb.toString(), new String[]{});
	}
}
