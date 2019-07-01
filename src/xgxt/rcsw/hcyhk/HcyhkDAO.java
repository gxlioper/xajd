package xgxt.rcsw.hcyhk;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

public class HcyhkDAO extends DAO {

	/**
	 * 批量保存火车优惠卡补办审核信息
	 * @param model
	 * @param spgw
	 * @return
	 * @throws SQLException
	 */
	public boolean saveHcyhkbbsh(HcyhkForm model,String[] spgw) throws SQLException{
		
		String[] sqlArr = new String[spgw.length];

		for (int i = 0 ; i < spgw.length ; i++){
			StringBuilder sql = new StringBuilder();
			sql.append("insert into xg_rcsw_hcyhkbbsh(xh,sqsj,xtgwid) values ('")
			   .append(model.getSave_xh())
			   .append("','")
			   .append(model.getSave_sqsj())
			   .append("','")
			   .append(spgw[i])
			   .append("')");
			
			sqlArr[i] = sql.toString();
		}
		int[] result = runBatch(sqlArr);
		return checkBatch(result);
	}
	
	
	/**
	 * 火车优惠卡补办查询
	 * @param model
	 * @param colList
	 * @param gwmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryHcyhkbb(User user,HcyhkForm model,String[] colList,String[] gwmc,String query) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		String[] queryList = new String[]{"nj","xydm","zydm","bjdm"};
		String[] queryLikeList = new String[]{"xh","xm"};
		
		Map<String, Object> map = CommonQueryDAO.getQuerySQL(model,queryList,queryLikeList);
		
		sql.append(" select a.*,rownum r from(select a.xh||a.sqsj pkValue,a.*,")
		   .append(" case when ").append(gwmc[0]).append("='通过' or ").append(gwmc[0])
		   .append(" ='不通过' then 'disabled' end disabled,")
		   .append(" v.xm,v.nj,v.xydm,v.xymc,v.zydm,v.zymc,v.bjdm,v.bjmc")
	       .append(" from (select xh,sqly,sqsj,bz ");
		
		for (int i = 0 ; i < gwmc.length ; i++){
			sql.append(",max(")
			   .append(gwmc[i])
			   .append(") ")
			   .append(gwmc[i]);
		}
		
		sql.append(" from (select t.xh,t.sqly,t.sqsj,t.bz ");
		
		for (int i = 0 ; i < gwmc.length ; i++){
			sql.append(",case when b.mc='")
			   .append(gwmc[i])
			   .append("' then a.shzt end ")
			   .append(gwmc[i]);
		}
		sql.append(" from xg_rcsw_hcyhkbb t")
		   .append(" left join xg_rcsw_hcyhkbbsh a on t.xh=a.xh and t.sqsj=a.sqsj")
		   .append(" left join xg_xtwh_spgw b on a.xtgwid=b.id")
		   .append(" ) group by xh,sqly,sqsj,bz) a left join view_xsjbxx v on a.xh=v.xh) a")
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
	public HashMap<String,Object> getSqsjSql(HcyhkForm model){
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
	 * 火车优惠卡补办审核
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryHcyhkbbsh(User user,HcyhkForm model,String[] colList,String[] gwmc,String beforeGwmc,String nextGwmc) throws Exception{
		StringBuilder sql = new StringBuilder();
		String[] queryArr = new String[]{"nj","xydm","zydm","bjdm"};
		String[] queryLikeArr = new String[]{"xh","xm"};
		List<String> input = new ArrayList<String>();
		
		Map<String, Object> map = CommonQueryDAO.getQuerySQL(model,queryArr,queryLikeArr);
		
		sql.append(" select a.*,rownum r from(select a.xh||a.sqsj pkValue,a.*,");
		//判断下一级审批岗位是已审核（非退回）则disabled
		if (StringUtils.isNotNull(nextGwmc)){
			String shgw = model.getShgw();
			sql.append(" case when ").append(shgw).append(" ='通过' or ")
			   .append(shgw).append("='不通过' then 'disabled' when ").append(shgw)
			   .append("='退回' then 'th' end isdis,");
			sql.append(" case when ").append(nextGwmc).append(" ='通过' or ")
			   .append(nextGwmc).append("='不通过' then 'disabled' else '' end disabled");
		} else {
			sql.append("'' isdis,");
			sql.append("'' disabled");
		}
		   
		sql.append(",v.xm,v.nj,v.xydm,v.xymc,v.zydm,v.zymc,v.bjdm,v.bjmc from (")
		   .append(" select xh,sqly,sqsj,bz ");
		
		for (int i = 0 ; i < gwmc.length ; i++){
			sql.append(",max(")
			   .append(gwmc[i])
			   .append(") ")
			   .append(gwmc[i]);
		}
		
		sql.append(" from (select t.xh,t.sqly,t.sqsj,t.bz ");
		
		for (int i = 0 ; i < gwmc.length ; i++){
			sql.append(",case when b.mc='")
			   .append(gwmc[i])
			   .append("' then a.shzt end ")
			   .append(gwmc[i]);
		}
		sql.append(" from xg_rcsw_hcyhkbb t")
		   .append(" left join xg_rcsw_hcyhkbbsh a on t.xh=a.xh and t.sqsj=a.sqsj")
		   .append(" left join xg_xtwh_spgw b on a.xtgwid=b.id")
		   .append(" ) group by xh,sqly,sqsj,bz) a left join view_xsjbxx v on a.xh=v.xh");
		
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
	 * 批量审核火车优惠卡补办
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean plshHcyhkbb(HcyhkForm model,String[] pkValues,String gnmc) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" update xg_rcsw_hcyhkbbsh set shzt=?,shr=?,shsj=?,shyj=? where xh||sqsj||xtgwid in (")
		   .append(" select a.xh||a.sqsj||a.xtgwid from xg_rcsw_hcyhkbbsh a where a.xh||a.sqsj in (");
		
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
	public boolean resetAfterShzt(HcyhkForm model,String[] pkValues,String gnmc,String nextShgw) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" update xg_rcsw_hcyhkbbsh set shzt='需重审' where xh||sqsj||xtgwid in (")
		   .append(" select a.xh||a.sqsj||a.xtgwid from xg_rcsw_hcyhkbbsh a where a.xh||a.sqsj in (");
		
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
	 *火车优惠卡补办审核信息
	 * @param pkValue
	 * @param gnmc
	 * @return
	 */
	public List<HashMap<String,String>> getHcyhkbbShxx(String pkValue,String gnmc){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.xh,a.spgw,b.mc,c.shzt,c.shsj,c.shyj,c.shr from ")
		   .append(" (select * from xg_xtwh_spbz where splc=(select lcid from xg_ty_shlcszb where gnmc=?)) a ")
		   .append(" right join ")
		   .append(" (select b.id,b.mc from xg_xtwh_spbz a,xg_xtwh_spgw b where a.spgw=b.id and ")
		   .append(" a.splc= (select lcid from xg_ty_shlcszb where gnmc=?) order by xh) b on a.spgw=b.id")
		   .append(" left join (select * from xg_rcsw_hcyhkbbsh where xh||sqsj=?) c on a.spgw=c.xtgwid")
		   .append(" order by a.xh");
		
		return getListNotOut(sql.toString(), new String[]{gnmc,gnmc,pkValue});
	}
	
	
	
	/**
	 * 火车优惠卡补办删除
	 * @param pkValues
	 * @return
	 * @throws Exception 
	 */
	public boolean delHcyhkbb(String[] pkValues) throws Exception{
		StringBuilder delSqjl = new StringBuilder();//删除申请记录SQL
		String delShjl = "";//删除审核记录SQL
		
		delSqjl.append("delete from xg_rcsw_hcyhkbb where xh||sqsj in (");
		
		for (int i = 0 ; i < pkValues.length ; i++){
			delSqjl.append("'")
				   .append(pkValues[i])
				   .append("'");
			
			if (i != pkValues.length-1){
				delSqjl.append(",");
			} 
		}
		delSqjl.append(")");
		delShjl = delSqjl.toString().replace("xg_rcsw_hcyhkbb", "xg_rcsw_hcyhkbbsh");
		
		return runUpdate(delSqjl.toString(), new String[]{}) && runUpdate(delShjl, new String[]{}) ;
	}
}
