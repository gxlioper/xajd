package xsgzgl.xsxx.general.jcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class XsxxJcszDao extends DAO{

	public boolean bcjcSz(XsxxJcszForm model) throws Exception {
		String sql = " insert into xg_xsxx_xxxgcsszb(kgzt,shlid,sqcs,sqkssj,sqjssj,shkg,shkssj,shjssj)"
				+ " values(?,?,?,?,?,?,?,?) ";
		return runUpdate(sql, new String[] {model.getKgzt(), model.getShlid(), model.getSqcs(),
				model.getSqkssj(),model.getSqjssj(),model.getShkg(),model.getShkssj(),model.getShjssj()});
	}
	
	public boolean scjcSz(XsxxJcszForm model) throws Exception {
		String sql = " delete from xg_xsxx_xxxgcsszb ";
		return runUpdate(sql, new String[] {});
	}
	
	
	/**
	 * 获取审核流程
	 * @param zydm
	 * @return
	 */
	public String getShlc(){
		return getOneRs("select shlid from xg_xsxx_xxxgcsszb a ", new String[]{}, "shlid");
	}
	
	/**
	 * 获取参数设置
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getOnesCssz()throws Exception{
		String sql = "select * from xg_xsxx_xxxgcsszb ";
		String[] inputValue = new String[] { };
		return getMapNotOut(sql, inputValue);
	}
	
	/**
	 * 查询审批岗位
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSpgwList(String yhm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.spgw,a.gwmc from (select b.spgw,(select c.mc from xg_xtwh_spgw c where b.spgw = c.id) gwmc, b.xh ");
		sql.append(" from xg_xsxx_xxxgcsszb a, xg_xtwh_spbz b ");
		sql.append(" where  ");
		sql.append(" a.shlid = b.splc) a ,(select * from xg_xtwh_spgwyh where spyh = ?) c ");
		sql.append("  where a.spgw = c.spgw order by a.xh ");
		return getList(sql.toString(), new String[] {yhm}, new String[] { "spgw", "gwmc" });
	}
	
	/**
	 * 查询使用中的审核流
	 * @param 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splCx() throws Exception{
		String sql=" select * from (select shlid,rownum r," +
				"(select count(*) ssjg from xg_xsxx_xxxgsqb  " +
				" where (shjg <> 'tg' and shjg <> '1')) shjg " +
				" from xg_xsxx_xxxgcsszb) a where a.r=1 ";
		return getMap(sql, new String[]{}, new String[]{"shlid","shjg"});
	}
	
	/**
	 * 查询审批岗位
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSpgwBySplc(String splc) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.mc gwmc from xg_xtwh_spbz a left join xg_xtwh_spgw b on a.spgw = b.id where splc = ?  order by to_number(xh) asc");
		return getList(sql.toString(), new String[] {splc}, new String[] { "spgw", "gwmc" ,"xh"});
	}
}
