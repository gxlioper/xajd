package com.zfsoft.xgxt.xszz.knsdc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import xgxt.form.User;


import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;




/**
 * 档次维护DAO
 * @author maxh
 * 2013-4-17
 */
public class KnsdcDao extends SuperDAOImpl<KnsdcForm> {

	/**
	 * 普通查询
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPageList(KnsdcForm model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("select dcdm,dcmc,xmsm from xg_xszz_new_kndcdmb where 1=1");
		
		if (!StringUtil.isNull(model.getDcdm())){
			params.add(model.getDcdm());
			sql.append(" and dcdm = ? ");
		}
		
		if (!StringUtil.isNull(model.getDcmc())){
			params.add(model.getDcmc());
			sql.append(" and dcmc like '%'||?||'%'");
		}
		//sql.append(" order by dcdm");
		
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}

	public List<HashMap<String, String>> knyyList(KnsdcForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("select * from xg_xszz_new_knyydmb where 1=1");
		if (!StringUtil.isNull(model.getYymc())) {
			params.add(model.getYymc());
			sql.append(" and yymc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}
	/**
	 * 获取 最大档次代码，用于生成
	 * @return
	 * @throws SQLException
	 */
	public int getMaxDcdm() throws SQLException{
		
		String sql = "select nvl(max(dcdm),1) dcdm from xg_xszz_new_kndcdmb";
		
		return dao.getOneRsint(sql);
	}
	
	/**
	 * 
	 * @描述:增加操作唯一性验证
	 * @作者：maxh
	 * @日期：2013-4-25 下午05:21:02
	 * @修改记录: 修改者名字-修改日期-修改内容 wanghj——2014-1-16——String->int
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public int checkExistForSave(KnsdcForm model) {
		StringBuilder sql = new StringBuilder("select count(1) num from xg_xszz_new_kndcdmb where dcmc=?");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getDcmc()}, "num");
		return Integer.parseInt(num);
		
	}
	/**
	 * 
	 * @描述:修改操作唯一性验证
	 * @作者：maxh
	 * @日期：2013-4-25 下午05:21:02
	 * @修改记录: 修改者名字-修改日期-修改内容wanghj——2014-1-16——String->int、只验证名称改为代码和名称都需要验证是否存在。
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public int checkExistForUpdate(KnsdcForm model) {
		String dcdmSql="select count(1) num from xg_xszz_new_kndcdmb where dcdm=?";
		String dcdmNum=dao.getOneRs(dcdmSql.toString(), new String[]{model.getDcdm()}, "num");
		
		String dcmcSql="select count(1) num from xg_xszz_new_kndcdmb where dcmc=? and dcdm <> ?";
		String dcmcNum=dao.getOneRs(dcmcSql.toString(), new String[]{model.getDcmc(),model.getDcdm()}, "num");
		
		int num = Integer.parseInt(dcdmNum)+Integer.parseInt(dcmcNum);
		return num;
		
	}
	public boolean checkKnyy(KnsdcForm model) {
		StringBuffer  sql= new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select count(1) num from xg_xszz_new_knyydmb where 1=1 and yymc=?");
		params.add(model.getYymc());
		if(null!=model.getYydm()){
			sql.append(" and yydm<>?");
			params.add(model.getYydm());
		}
	String num= dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	return Integer.parseInt(num)>0;
	}
	public boolean checkKnyyUsed(String[] values) throws Exception {
		boolean used=false;
		for (int i = 0; i < values.length; i++) {
			StringBuilder sql = new StringBuilder();
			sql.append("select count(1) num from (select xh,ylzd5 from XG_XSZZ_NEW_KNSJGB union all select xh,ylzd5 from XG_XSZZ_NEW_KNSSQB) where ylzd5 like '%'||?||'%'");
			String num=dao.getOneRs(sql.toString(),new String[] {values[i]} , "num");
			if(Integer.parseInt(num)>0){
				used=true;
				break;
			}
		}
		return used;
	}
	public int deleteKnyyInfo(String[] values) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xszz_new_knyydmb where  yydm in(");
		for (int i = 0; i < values.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			params.add(values[i]);
		}
		sql.append(" ) ");
		return dao.runDelete(sql.toString(), params.toArray(new String[]{}));
		
	}
	public boolean addKnyy(KnsdcForm model) throws Exception {
		String sql = "insert into xg_xszz_new_knyydmb(yymc,xh) values(?,?)";
		return dao.runUpdate(sql, new String[] { model.getYymc(),model.getXh()});
	}
	public boolean updateKnyy(KnsdcForm model) throws Exception {
		String sql = "update xg_xszz_new_knyydmb set yymc=?,xh=? where yydm=?";
		return dao.runUpdate(sql, new String[] { model.getYymc(),model.getXh(),model.getYydm()});
	}
	
	public KnsdcForm getKnyy(KnsdcForm model) throws Exception {
		String sql = "select * from  xg_xszz_new_knyydmb where yydm=?";
		return getModel(model,sql, new String[]{model.getYydm()});
	}
	
	public List<HashMap<String, String>> getKnyyList() throws Exception {
		String sql = "select * from  xg_xszz_new_knyydmb order by to_number(xh)";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	

	@Override
	public List<HashMap<String, String>> getPageList(KnsdcForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_kndcdmb");
		super.setKey("dcdm");

	}
	/**
	 * 
	 * @描述:困难生档次list
	 * @作者：maxh
	 * @日期：2013-4-22 下午02:11:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsdcList() {
		String sql = "select dcdm ,dcmc from xg_xszz_new_kndcdmb order by dcdm";
		return dao.getList(sql, new String[]{},new String[]{"dcdm","dcmc"});
	}
	/**
	 * 
	 * @描述:无偿资助金额
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-18 上午09:45:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getWczzList() {
		String sql = "select distinct wczzje zzjedm, wczzje zzjemc from xg_xszz_wczzjeb order by wczzje";
		return dao.getList(sql, new String[]{},new String[]{"zzjedm","zzjemc"});
	}
	
	
	
	/**
	 * 
	 * @描述:检验困难生结果中是否有数据
	 * @作者：maxh
	 * @日期：2013-4-24 上午08:48:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public String[]  checkDcForKnsjg( String value) throws Exception{
			StringBuilder sql = new StringBuilder(" select distinct  b.dcmc from xg_xszz_new_knsjgb a,xg_xszz_new_kndcdmb b where  a.rddc= to_char(b.dcdm) and a.rddc in(" +value +")");
			String[] dcmc=dao.getRs(sql.toString(), new String[]{}, "dcmc");
		return dcmc;
	}
	/**
	 * 
	 * @描述:检验困难生审核中是否有数据
	 * @作者：maxh
	 * @日期：2013-4-24 上午10:09:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[]  checkDcForKnssh( String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct  b.dcmc from xg_xszz_new_knsshb  a,xg_xszz_new_kndcdmb b where  a.rddc= to_char(b.dcdm) and a.shzt<>'0' and a.rddc in("+value+")");
		String[] dcmc=dao.getRs(sql.toString(), new String[]{}, "dcmc");
	    return dcmc;
   }
	
	/**
	 * 
	 * @描述:困难生结果表-根据学年学期，查询认定档次
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsjgRddc(String xn,String xq) throws Exception {
		String[] inputValue = null;

		StringBuilder sb = new StringBuilder();
		sb.append("select distinct rddc dm,(select dcmc from xg_xszz_new_kndcdmb c where a.rddc=c.dcdm) mc  ");
		sb.append(" from xg_xszz_new_knsjgb a  ");
		sb.append("  where xn=? ");
		
		if(xq != null && !xq.trim().equals("")){
			sb.append("  and xq=? ");
			inputValue = new String[2];
			inputValue[0] = xn;
			inputValue[1] = xq;
		}else{
			inputValue = new String[1];
			inputValue[0] = xn;
		}
		
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	
	public boolean updateKnsdcInfo(String dcdm,KnsdcForm form) throws Exception{
		
		String[] input = {form.getDcdm(),form.getDcmc(),form.getXmsm(),dcdm};
		String sql = "update xg_xszz_new_kndcdmb set dcdm=?,dcmc=?,xmsm=? where dcdm=?";
		
		return dao.runUpdate(sql, input);
	}

}
