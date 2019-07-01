package xgxt.rcsw.xszgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.rcsw.RcswForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.zgmsxy.XszzZgmsxyActionForm;

public class XszglDAO extends DAO {

	
	
	/**
	 * 学生证补办删除
	 * @param pkValues
	 * @return
	 * @throws Exception 
	 */
	public boolean delXszbb(String[] pkValues) throws Exception{
		StringBuilder delSqjl = new StringBuilder();//删除申请记录SQL
		String delShjl = "";//删除审核记录SQL
		
		delSqjl.append("delete from xszgl_xszbbsqb where xh||sqsj in (");
		
		for (int i = 0 ; i < pkValues.length ; i++){
			delSqjl.append("'")
				   .append(pkValues[i])
				   .append("'");
			
			if (i != pkValues.length-1){
				delSqjl.append(",");
			} 
		}
		delSqjl.append(")");
		delShjl = delSqjl.toString().replace("xszgl_xszbbsqb", "xg_rcsw_xszbbshb");
		
		return runUpdate(delSqjl.toString(), new String[]{}) && runUpdate(delShjl, new String[]{}) ;
	}
	
	
	/**
	 * 学生证补办查询
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXszbb(User user,RcswForm model,String[] colList,String[] gwmc,String query) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		String[] queryList = new String[]{"nj","xydm","zydm","bjdm","bblxdm"};
		String[] queryLikeList = new String[]{"xh","xm"};
		
		Map<String, Object> map = CommonQueryDAO.getQuerySQL(model,queryList,queryLikeList);
		
		sql.append(" select a.*,rownum r from(select a.xh||a.sqsj pkValue,a.*,")
		   .append(" case when ").append(gwmc[0]).append("='通过' or ").append(gwmc[0])
		   .append(" ='不通过' then 'disabled' end disabled,")
		   .append(" v.xm,v.nj,v.xydm,v.xymc,v.zydm,v.zymc,v.bjdm,v.bjmc,")
	       .append(" (select bblxmc from xszbblxdmb b where a.bblxdm=b.bblxdm)bblxmc from (")
		   .append(" select xh,sqly,sqsj,bblxdm,bz ");
		
		for (int i = 0 ; i < gwmc.length ; i++){
			sql.append(",max(")
			   .append(gwmc[i])
			   .append(") ")
			   .append(gwmc[i]);
		}
		
		sql.append(" from (select t.xh,t.sqly,t.sqsj,t.bblxdm,t.bz ");
		
		for (int i = 0 ; i < gwmc.length ; i++){
			sql.append(",case when b.mc='")
			   .append(gwmc[i])
			   .append("' then a.shzt end ")
			   .append(gwmc[i]);
		}
		sql.append(" from xszgl_xszbbsqb t")
		   .append(" left join xg_rcsw_xszbbshb a on t.xh=a.xh and t.sqsj=a.sqsj")
		   .append(" left join xg_xtwh_spgw b on a.xtgwid=b.id")
		   .append(" ) group by xh,sqly,sqsj,bblxdm,bz) a left join view_xsjbxx v on a.xh=v.xh) a")
		   .append(" where 1=1 ")
		   .append(map.get("sql"))
		   .append(query)
		   .append(CommonQueryDAO.getQuerySqlByUser(user, "a", "xydm", "bjdm"));
		//申请时间过滤
		HashMap<String,Object> sqsjSql = getSqsjSql(model);
		sql.append(sqsjSql.get("sql"));
		
		return CommonQueryDAO.commonQuery(model.getPages(),sql.toString(),
				StringUtils.joinStrArr((String[]) map.get("input"),(String[])sqsjSql.get("input")),colList);
	}
	
	
	/**
	 * 申请时间条件SQL
	 * @param model
	 * @return
	 */
	public HashMap<String,Object> getSqsjSql(RcswForm model){
		StringBuilder sql = new StringBuilder();
		List<String> input = new ArrayList<String>();
		
		if (StringUtils.isNotNull(model.getSqkssj()) && StringUtils.isNull(model.getSqjssj())) {
			sql.append(" and to_date(sqsj,'yyyymmdd') >= to_date(?,'yyyymmdd')");
			input.add(model.getSqkssj());
		}
		
		if (StringUtils.isNotNull(model.getSqjssj()) && StringUtils.isNull(model.getSqkssj()) ) {
			sql.append(" and to_date(sqsj,'yyyymmdd') <= to_date(?,'yyyymmdd')");
			input.add(model.getSqjssj());
		}
		
		if (StringUtils.isNotNull(model.getSqkssj()) && StringUtils.isNotNull(model.getSqjssj())) {
			sql.append(" and to_date(sqsj,'yyyymmdd') between to_date(?,'yyyymmdd') and to_date(?,'yyyymmdd')+1");
			input.add(model.getSqkssj());
			input.add(model.getSqjssj());
		}
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("sql", sql.toString());
		map.put("input", input.toArray(new String[]{}));
		return map;
	}
	
	
	
	
	/**
	 * 批量审核学生证补办
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean plshXszbb(RcswForm model,String[] pkValues,String gnmc) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" update xg_rcsw_xszbbshb set shzt=?,shr=?,shsj=?,shyj=? where xh||sqsj||xtgwid in (")
		   .append(" select a.xh||a.sqsj||a.xtgwid from xg_rcsw_xszbbshb a where a.xh||a.sqsj in (");
		
		for (int i = 0 ; i < pkValues.length; i++){
			sql.append("'").append(pkValues[i]).append("'");
			if (i == pkValues.length-1){
				sql.append(")");
			} else {
				sql.append(",");
			}
		}
		
		sql.append(" and exists (select 1 from (select b.id,b.mc from xg_xtwh_spbz a,xg_xtwh_spgw b ")
		   .append(" where a.spgw=b.id and a.splc= (select lcid from xg_ty_shlcszb where gnmc=?) ")
		   .append(" order by xh) b where a.xtgwid=b.id and b.mc=?))");
		
		return runUpdate(sql.toString(), new String[]{model.getShjg(),model.getShr(),model.getShsj(),model.getShyj(),gnmc,model.getShgw()});
	}
	
	
	/**
	 * 重置下一级审核状态为“需重审”
	 * @return
	 * @throws Exception 
	 */
	public boolean resetAfterShzt(RcswForm model,String[] pkValues,String gnmc,String nextShgw) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" update xg_rcsw_xszbbshb set shzt='需重审' where xh||sqsj||xtgwid in (")
		   .append(" select a.xh||a.sqsj||a.xtgwid from xg_rcsw_xszbbshb a where a.xh||a.sqsj in (");
		
		for (int i = 0 ; i < pkValues.length; i++){
			sql.append("'").append(pkValues[i]).append("'");
			if (i == pkValues.length-1){
				sql.append(")");
			} else {
				sql.append(",");
			}
		}
		
		sql.append(" and exists (select 1 from (select b.id,b.mc from xg_xtwh_spbz a,xg_xtwh_spgw b ")
		   .append(" where a.spgw=b.id and a.splc= (select lcid from xg_ty_shlcszb where gnmc=?) ")
		   .append(" order by xh) b where a.xtgwid=b.id and b.mc=?)) and shzt='退回'");
		return runUpdate(sql.toString(), new String[]{gnmc,nextShgw});
	}
	
	
	
	/**
	 * 学生证补办审核查询（前一级审核状态为通过）
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXszbbsh(User user,RcswForm model,String[] colList,String[] gwmc,String beforeGwmc,String afterGwmc) throws Exception{
		StringBuilder sql = new StringBuilder();
		String[] queryArr = new String[]{"nj","xydm","zydm","bjdm","bblxdm"};
		String[] queryLikeArr = new String[]{"xh","xm"};
		List<String> input = new ArrayList<String>();
		
		Map<String, Object> map = CommonQueryDAO.getQuerySQL(model,queryArr,queryLikeArr);
		
		sql.append(" select a.*,rownum r from(select a.xh||a.sqsj pkValue,a.*,");
		//判断下一级审批岗位是已审核（非退回）则disabled
		if (StringUtils.isNotNull(afterGwmc)){
			String shgw = model.getShgw();
			sql.append(" case when ").append(shgw).append(" ='通过' or ")
			   .append(shgw).append("='不通过' then 'disabled' when ").append(shgw)
			   .append("='退回' then 'th' end isdis,");
			sql.append(" case when ").append(afterGwmc).append(" ='通过' or ")
			   .append(afterGwmc).append("='不通过' then 'disabled' else '' end disabled");
		} else {
			sql.append("'' isdis,");
			sql.append("'' disabled");
		}
		   
		sql.append(",v.xm,v.nj,v.xydm,v.xymc,v.zydm,v.zymc,v.bjdm,v.bjmc,")
	       .append(" (select bblxmc from xszbblxdmb b where a.bblxdm=b.bblxdm)bblxmc from (")
		   .append(" select xh,sqly,sqsj,bblxdm,bz ");
		
		for (int i = 0 ; i < gwmc.length ; i++){
			sql.append(",max(")
			   .append(gwmc[i])
			   .append(") ")
			   .append(gwmc[i]);
		}
		
		sql.append(" from (select t.xh,t.sqly,t.sqsj,t.bblxdm,t.bz ");
		
		for (int i = 0 ; i < gwmc.length ; i++){
			sql.append(",case when b.mc='")
			   .append(gwmc[i])
			   .append("' then a.shzt end ")
			   .append(gwmc[i]);
		}
		sql.append(" from xszgl_xszbbsqb t")
		   .append(" left join xg_rcsw_xszbbshb a on t.xh=a.xh and t.sqsj=a.sqsj")
		   .append(" left join xg_xtwh_spgw b on a.xtgwid=b.id")
		   .append(" ) group by xh,sqly,sqsj,bblxdm,bz) a left join view_xsjbxx v on a.xh=v.xh");
		
		if (StringUtils.isNotNull(beforeGwmc)){
			sql.append(" where a.")
			   .append(beforeGwmc)
			   .append(" ='通过'");
		}
		   
		sql.append(" ) a where 1=1 ")
		   .append(map.get("sql"))
		   .append(CommonQueryDAO.getQuerySqlByUser(user, "a", "xydm", "bjdm"));
		
		//申请时间过滤
		HashMap<String,Object> sqsjSql = getSqsjSql(model);
		sql.append(sqsjSql.get("sql"));
		
		//审核状态过滤
		if (StringUtils.isNotNull(model.getShgw()) && StringUtils.isNotNull(model.getShzt())){
			sql.append(" and ").append(model.getShgw()).append("=?");
			input.add(model.getShzt());
		}
		
		return CommonQueryDAO.commonQuery(model.getPages(),sql.toString(),
				StringUtils.joinStrArr((String[]) map.get("input"),(String[])sqsjSql.get("input"),input.toArray(new String[]{})),colList);
	}
	
	/**
	 * 学生证补办审核信息
	 * @param pkValue
	 * @param gnmc
	 * @return
	 */
	public List<HashMap<String,String>> getXszbbShxx(String pkValue,String gnmc){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.xh,a.spgw,b.mc,c.shzt,c.shsj,c.shyj,c.shr from ")
		   .append(" (select * from xg_xtwh_spbz where splc=(select lcid from xg_ty_shlcszb where gnmc=?)) a ")
		   .append(" right join ")
		   .append(" (select b.id,b.mc from xg_xtwh_spbz a,xg_xtwh_spgw b where a.spgw=b.id and ")
		   .append(" a.splc= (select lcid from xg_ty_shlcszb where gnmc=?) order by xh) b on a.spgw=b.id")
		   .append(" left join (select * from xg_rcsw_xszbbshb where xh||sqsj=?) c on a.spgw=c.xtgwid")
		   .append(" order by a.xh");
		
		return getListNotOut(sql.toString(), new String[]{gnmc,gnmc,pkValue});
	}
	
	
	
	/**
	 * 批量保存学生证审核信息
	 * @param xh
	 * @param sqsj
	 * @param spgw
	 * @return
	 * @throws SQLException
	 */
	public boolean saveXszbbsh(String xh,String sqsj,String[] spgw) throws SQLException{
		
		String[] sqlArr = new String[spgw.length];

		for (int i = 0 ; i < spgw.length ; i++){
			StringBuilder sql = new StringBuilder();
			sql.append("insert into xg_rcsw_xszbbshb(xh,sqsj,xtgwid) values ('")
			   .append(xh).append("','").append(sqsj).append("','").append(spgw[i]).append("')");
			
			sqlArr[i] = sql.toString();
		}
		
		int[] result = runBatch(sqlArr);
		return checkBatch(result);
	}
	
	// =====================学生证办理 增、删、改==============================
	/**
	 * 学生证办理查询
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXszbl(RcswForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		StringBuilder query=new StringBuilder();
		String[] queryArr = new String[]{"nj","xydm","zydm","bjdm","sfff"};
		String[] queryLikeArr = new String[]{"xh","xm"};
		
		MakeQuery mQuery=new MakeQuery();
		mQuery.makeQuery(queryArr, queryLikeArr, model);
		User user=model.getUser();
		String isFdy=user.getFdyQx();
		String isBzr= user.getBzrQx();
		String userName=user.getUserName();
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		
		sql.append(" select rownum r,xh||ffsj pkValue,a.* from  ");
		sql.append(" (select a.xh,a.xm,a.nj,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc ");
		sql.append("  ,b.jbrxm,b.ffsj, ");
		sql.append(" case when b.xh is not null then '是' else '否' end sfff ");
		sql.append("  from view_xsjbxx a left join view_xszff b on a.xh=b.xh)a ");
		String []colList={"pkValue","xh","xm","nj","xymc","zymc","bjmc","jbrxm","ffsj","sfff"};
		
		if("true".equalsIgnoreCase(isFdy) || "true".equalsIgnoreCase(isBzr)){
			
			query.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh='"+userName+"' union ");
			query.append(" select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh='"+userName+"' ) ");
		}else if("true".equalsIgnoreCase(isFdy)){
				
			query.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh='"+userName+"' )");

		}else if("true".equalsIgnoreCase(isBzr)){
				
			query.append(" and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh='"+userName+"' )");

		}else if("xy".equalsIgnoreCase(userType)){
			
			query.append(" and xydm='"+userDep+"' ");
		}
		return CommonQueryDAO.commonQuery(sql.toString(), mQuery.getQueryString()+query,
				mQuery.getInputList(), colList, model);
	}
	
	/**
	 * 学生证办理查询
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public boolean addXszbl(RcswForm model) throws Exception{
		
		String[] pkValue = model.getPkV();
		String[] sql = new String[pkValue.length];
		User user=model.getUser();
		CommDAO dao = new CommDAO();
		
		for (int i = 0; i < pkValue.length; i++) {
			sql[i] = " insert into  xszffb(xn,xq,xh,bz,nd,ffsj,jbr)values('"+Base.currXn+"', ";
			sql[i]+=" '"+Base.currXq+"','"+pkValue[i]+"','"+model.getBz()+"','"+Base.currNd+"', ";
			sql[i]+=" '"+model.getFfsj()+"','"+model.getSelect_jbr()+"')";
		}
		
		return dao.saveArrDate(sql);
	}
	
	/**
	 * 学生证批量注册
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public boolean plXszzc(RcswForm model) throws Exception{
		
		String[] pkValue = model.getPkV();
		String[] sql = new String[pkValue.length];
		User user=model.getUser();
		CommDAO dao = new CommDAO();
		
		for (int i = 0; i < pkValue.length; i++) {
			sql[i] = " insert into  xg_rcsw_xszzcb(xn,xq,xh,bz,czsj,czr)values('"+Base.currXn+"', ";
			sql[i]+=" '"+Base.currXq+"','"+pkValue[i]+"','"+model.getBz()+"', ";
			sql[i]+=" to_char(sysdate,'yyyyMMdd'),'"+model.getSelect_jbr()+"')";
		}
		
		return dao.saveArrDate(sql);
	}
	
	/**
	 * 删除学生证办理(2011.12.23 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delXszbl(RcswForm myForm) throws Exception {

		String[] pkValue = myForm.getPkV();
		String[] sql = new String[pkValue.length];
		CommDAO dao = new CommDAO();
		
		for (int i = 0; i < pkValue.length; i++) {
			sql[i] = " delete from xszffb where xh||ffsj='"+pkValue[i]+"' ";
		}

		return dao.saveArrDate(sql);
	}
	
	//============================学生证注册=====================================
	/**
	 * 学生证办理查询
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXszc(RcswForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		StringBuilder query=new StringBuilder();
		String[] queryArr = new String[]{"nj","xydm","zydm","bjdm","sfzc"};
		String[] queryLikeArr = new String[]{"xh","xm"};
		
		MakeQuery mQuery=new MakeQuery();
		mQuery.makeQuery(queryArr, queryLikeArr, model);
		User user=model.getUser();
		String isFdy=user.getFdyQx();
		String isBzr= user.getBzrQx();
		String userName=user.getUserName();
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		
		String xn=model.getXn();
		
		String xq=model.getXq();
		
		sql.append(" select  rownum r,a.* from(select a.xh||xn||xq pkValue, a.xh,a.xm,a.nj,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc,");
		sql.append(" b.xn,b.xq, ");
		
		sql.append(" (select jbrxm from jbrxxb where jbrgh=czr)czrxm,b.czsj ");
		sql.append(" ,case when b.xh is null then '否' else '是' end  sfzc ");
		sql.append("  from view_xsjbxx  a left join (select a.* from xg_rcsw_xszzcb a");
		sql.append("  where " );
		sql.append(" xn='"+xn+"' and xq='"+xq+"' ");
		sql.append(" ) b on a.xh=b.xh )a ");
		String []colList={"pkValue","xh","xm","nj","xymc","zymc","bjmc","czrxm","czsj","sfzc"};
		if("true".equalsIgnoreCase(isFdy) || "true".equalsIgnoreCase(isBzr)){
			
			query.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh='"+userName+"' union ");
			query.append(" select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh='"+userName+"' ) ");
		}else if("true".equalsIgnoreCase(isFdy)){
				
			query.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh='"+userName+"' )");

		}else if("true".equalsIgnoreCase(isBzr)){
				
			query.append(" and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh='"+userName+"' )");

		}else if("xy".equalsIgnoreCase(userType)){
			
			query.append(" and xydm='"+userDep+"' ");
		}
		return CommonQueryDAO.commonQuery(sql.toString(), mQuery.getQueryString()+query,
				mQuery.getInputList(), colList, model);
	}
	
	/**
	 * 取消学生证注册(2011.12.23 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delXszzc(RcswForm myForm) throws Exception {

		String[] pkValue = myForm.getPkV();
		String[] sql = new String[pkValue.length];
		CommDAO dao = new CommDAO();
		
		for (int i = 0; i < pkValue.length; i++) {
			sql[i] = " delete from xg_rcsw_xszzcb where xh||xn||xq='"+pkValue[i]+"' ";
		}

		return dao.saveArrDate(sql);
	}
	
	/**
	 * 学生证注册(2011.12.23 qlj)
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addXszzc(RcswForm myForm) throws Exception {
		
		DAO dao=DAO.getInstance();
		String xn=Base.currXn;
		String xq=Base.currXq;
		String xh=myForm.getXh();
		String jbr=myForm.getSelect_jbr();
		String bz=myForm.getBz();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" insert into xg_rcsw_xszzcb(xn,xq,xh,czr,czsj,bz)" +
				"values(?,?,?,?,to_char(sysdate,'yyyyMMdd'),? ) ");
		
		return dao.runUpdate(sql.toString(),new String[]{xn,xq,xh,jbr,bz});
	}
	
	// ===========================学生证注销====================================
	/**
	 * 学生证注销查询
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXszzx(RcswForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		StringBuilder query=new StringBuilder();
		String[] queryArr = new String[]{"nj","xydm","zydm","bjdm","sfzx","zxyy","xn","xq"};
		String[] queryLikeArr = new String[]{"xh","xm"};
		
		MakeQuery mQuery=new MakeQuery();
		mQuery.makeQuery(queryArr, queryLikeArr, model);
		User user=model.getUser();
		String isFdy=user.getFdyQx();
		String isBzr= user.getBzrQx();
		String userName=user.getUserName();
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		
		
		sql.append(" select rownum r,a.* from ( ");
		sql.append(" select a.xh pkValue,a.xh,a.xn,a.xq, ");
		sql.append(" case when c.nj is null then b.nj else c.nj end nj, ");
		sql.append(" case when c.xydm is null then b.xydm else c.xydm end xydm, ");
		sql.append(" case when c.zydm is null then b.zydm else c.zydm end zydm, ");
		sql.append(" case when c.bjdm is null then b.bjdm else c.bjdm end bjdm, ");
		sql.append(" case when c.xymc is null then b.xymc else c.xymc end xymc, ");
		sql.append(" case when c.zymc is null then b.zymc else c.zymc end zymc, ");
		sql.append(" case when c.bjmc is null then b.bjmc else c.bjmc end bjmc, ");
		sql.append(" case when c.xm is null then b.xm else c.xm end xm, ");
		sql.append(" (select ydlbmc from dm_ydlb where ydlbm=c.zxyy)zxyymc, ");
		sql.append(" zxyy,case when c.xh is null then '否' else '是' end sfzx ");
		sql.append(" from  xg_rcsw_xszzcb a ");
		sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
		sql.append(" left join xg_rcsw_xszzxb c on a.xh=c.xh and a.xn=c.xn and a.xq=c.xq");
		sql.append(" )a ");
		String []colList={"pkValue","xh","xm","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","zxyymc","sfzx"};
		
		if("true".equalsIgnoreCase(isFdy) || "true".equalsIgnoreCase(isBzr)){
			
			query.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh='"+userName+"' union ");
			query.append(" select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh='"+userName+"' ) ");
		}else if("true".equalsIgnoreCase(isFdy)){
				
			query.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh='"+userName+"' )");

		}else if("true".equalsIgnoreCase(isBzr)){
				
			query.append(" and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh='"+userName+"' )");

		}else if("xy".equalsIgnoreCase(userType)){
			
			query.append(" and xydm='"+userDep+"' ");
		}
		return CommonQueryDAO.commonQuery(sql.toString(), mQuery.getQueryString()+query,
				mQuery.getInputList(), colList, model);
	}
	
	/**
	 * 学生证批量注册
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public boolean plXszzx(RcswForm model) throws Exception{
		
		String[] pkValue = model.getPkV();
		String[] sql = new String[pkValue.length];
		User user=model.getUser();
		CommDAO dao = new CommDAO();
		
		String xn=Base.currXn;
		String xq=Base.currXq;
		String czr=user.getUserName();
		String[]xh=pkValue;
		String[]xm=model.getXmArr();
		String[]nj=model.getNjArr();
		String[]xydm=model.getXydmArr();
		String[]xymc=model.getXymcArr();
		String[]zydm=model.getZydmArr();
		String[]zymc=model.getZymcArr();
		String[]bjdm=model.getBjdmArr();
		String[]bjmc=model.getBjmcArr();
		String zxyy=model.getSelect_zxyy();
		String bz=model.getBz();
		
		for (int i = 0; i < pkValue.length; i++) {
			
			sql[i] = " insert into xg_rcsw_xszzxb(xh,xn,xq,czr,czsj,nj,xm,xydm,xymc,zydm,zymc,bjdm,bjmc ";
			if(!Base.isNull(zxyy)){
				
				sql[i]+=",zxyy";
			}
			if(!Base.isNull(bz)){
				
				sql[i]+=",bz";
			}
			
			sql[i]+=" )values( ";
			sql[i]+="'"+xh[i]+"',";
			sql[i]+="'"+xn+"',";
			sql[i]+="'"+xq+"',";
			sql[i]+="'"+czr+"',";
			sql[i]+="to_char(sysdate,'yyyyMMdd'),";
			sql[i]+="'"+nj[i]+"',";
			sql[i]+="'"+xm[i]+"',";
			sql[i]+="'"+xydm[i]+"',";
			sql[i]+="'"+xymc[i]+"',";
			sql[i]+="'"+zydm[i]+"',";
			sql[i]+="'"+zymc[i]+"',";
			sql[i]+="'"+bjdm[i]+"',";
			sql[i]+="'"+bjmc[i]+"'";
			
			if(!Base.isNull(zxyy)){
				
				sql[i]+=",'"+zxyy+"'";
			}
			if(!Base.isNull(bz)){
				
				sql[i]+=",'"+bz+"'";
			}
			sql[i]+=")";
		}
		
		return dao.saveArrDate(sql);
	}
	
	/**
	 * 学生证取消注销
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean delXszzx(RcswForm model) throws Exception{
		
		String[] pkValue = model.getPkV();
		String[] sql = new String[pkValue.length];
		CommDAO dao = new CommDAO();
		
	
		
		for (int i = 0; i < pkValue.length; i++) {
			
			sql[i]=" delete from xg_rcsw_xszzxb where xh='"+pkValue[i]+"' ";
		}
		
		return dao.saveArrDate(sql);
	}
	
	/**
	 * 获取异动类别列表
	 */
	public List<HashMap<String,String>>getYdlbList(){
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select ydlbm dm,ydlbmc mc from dm_ydlb where sfzx='不在校' ");
		DAO dao=DAO.getInstance();
		return dao.getList(sql.toString(), new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 获取学生证注册信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String>getXszzcMap(RcswForm model){
		
		DAO dao=DAO.getInstance();
		String pkValue=model.getPkValue();
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh,xn,xq,czr,czsj,bz,  ");
		sql.append(" (select jbrxm from jbrxxb where jbrgh=czr)czrxm ");
		sql.append(" from xg_rcsw_xszzcb where xh||xn||xq =? ");
		return dao.getMap(sql.toString(), new String[]{pkValue}, new String[]{"xh",
			"xn","xq","czr","czsj","bz","czrxm"});
	}
	
	/**
	 * 获取学生证注销信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String>getXszzxMap(RcswForm model){
		
		DAO dao=DAO.getInstance();
		String pkValue=model.getPkValue();
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh,xn,xq,czr,czsj,bz, ");
		sql.append(" zxyy,(select ydlbmc from dm_ydlb where ydlbm=a.zxyy)zxyymc ");
		sql.append("  from xg_rcsw_xszzxb a where xh =?  ");
		return dao.getMap(sql.toString(), new String[]{pkValue}, new String[]{"xh",
			"xn","xq","czr","czsj","bz","zxyymc"});
	}
	
	/**
	 * 学生证注册(2011.12.23 qlj)
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addXszzx(RcswForm myForm) throws Exception {
		
		DAO dao=DAO.getInstance();
		User user = myForm.getUser();
		String xn=Base.currXn;
		String xq=Base.currXq;
		String xh=myForm.getXh();
		String czr=user.getUserName();
		String bz=myForm.getBz();
		String zxyy=myForm.getZxyy();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" insert into xg_rcsw_xszzxb(xh,xn,xq,czr,czsj,nj,xm,");
		sql.append(" xydm,xymc,zydm,zymc,bjdm,bjmc,zxyy,bz) ");
		sql.append(" select ?,?,?,?,to_char(sysdate,'yyyyMMdd'),");
		sql.append(" nj,xm,xydm,xymc,zydm,zymc,bjdm,bjmc,?,? from view_xsjbxx ");
		sql.append(" where xh=? ");
		
		return dao.runUpdate(sql.toString(),new String[]{xh,xn,xq,czr,zxyy,bz,xh});
	}
}
