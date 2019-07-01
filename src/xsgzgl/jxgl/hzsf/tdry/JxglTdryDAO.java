package xsgzgl.jxgl.hzsf.tdry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;

public class JxglTdryDAO {
	DAO dao = DAO.getInstance();
	/**
	 * 团队荣誉查询
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> tdryCx(JxglTdryForm model,String searchTj) throws Exception {
		String[] colList =new String[]{ "pkValue", "xn", "bzjbmc","bzmc","tdrymc" };
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,rownum r from (select a.xn||'!!@@!!'||a.bzbh||'!!@@!!'||a.tdrydm pkValue,a.xn,b.bzdm,b.bzmc,c.tdrydm,c.tdrymc,");
		sql.append(" case when a.bzjb='tj' then '1' when a.bzjb='yj' then '2' when a.bzjb='lj' then '3' else '' end as bzjbdm, ");
		sql.append(" case when a.bzjb='tj' then '团' when a.bzjb='yj' then '营' when a.bzjb='lj' then '连' else '' end as bzjbmc ");
		sql.append(" from xg_jxgl_hzsf_tdryhjb a left join jxbzdmb b on a.bzbh = b.bzdm  ");
		sql.append(" left join xg_jxgl_hzsf_tdrydmb c on a.tdrydm = c.tdrydm )a where 1=1");

		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj, new String[]{}, colList, model);
	}
	/**
	 * 获得团队荣誉Map
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getTdryMap(JxglTdryForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xn,a.bzbh bzdm,b.bzmc,c.tdrydm,c.tdrymc,a.bzjb bzjbdm,");
		sql.append("case when a.bzjb = 'tj' then'团'when a.bzjb = 'yj' then'营'when a.bzjb = 'lj' then'连'else''end as bzjbmc,");
		sql.append("a.bz from xg_jxgl_hzsf_tdryhjb a left join jxbzdmb b on a.bzbh = b.bzdm ");
		sql.append("left join xg_jxgl_hzsf_tdrydmb c on a.tdrydm = c.tdrydm where a.xn=? and a.bzbh=? and a.tdrydm=? and rownum =1");
		String[] inputValue = model.getPkValue().split("!!@@!!");
		String[] outputValue = {"xn","bzjbdm","bzjbmc","bzdm","bzmc","tdrydm","tdrymc","bz"};
		return dao.getMap(sql.toString(), inputValue, outputValue);
	}
	/**
	 * 团队荣誉信息保存
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean tdryBc(JxglTdryForm model) throws Exception {
		String sql = "insert into xg_jxgl_hzsf_tdryhjb(xn,bzbh,bzjb,tdrydm,bz)values(?,?,?,?,?)";
		String[]input={model.getXn(),model.getBzdm(),model.getBzjbdm(),model.getTdrydm(),model.getBz()};
		return dao.runUpdate(sql, input);
	}
	/**
	 * 更新团队荣誉修改信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean tdryXg(JxglTdryForm model) throws Exception {
		boolean flag = false;
		String sql = "update xg_jxgl_hzsf_tdryhjb set tdrydm=?,bz=? where xn = ? and bzbh = ? and tdrydm = ?";
		String[] pkV = model.getPkValue().split("!!@@!!");
		String[] input = new String[]{model.getTdrydm(),model.getBz(),pkV[0],pkV[1],pkV[2]};
		flag = dao.runUpdate(sql,input);
		return flag;
	}
	/**
	 * 团队荣誉批量删除
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean tdrySc(List<String[]> params) throws SQLException {
		String sql = " delete from xg_jxgl_hzsf_tdryhjb where xn=? and bzbh=? and tdrydm=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 获得团营连列表
	 * @param type
	 * @param currXn
	 * @return
	 */
	public List<HashMap<String, String>> getBzdmList(JxglTdryForm model) {
		String sql = "select bzdm dm,bzmc mc from jxbzdmb where bzdj = ? and xn = ? ";
		return dao.getList(sql, new String[]{model.getBzjbdm(),model.getXn()}, new String[]{"dm","mc"});
	}
	/**
	 * 获得编制级别代码列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getZjList(JxglTdryForm model) {
		String sql = "select jzdm dm,jzmc mc from jxjzdj where jzdm = 1 or jzdm = 2 or jzdm = 3 order by jzdm";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	/**
	 * 是否已存在
	 * @param model
	 * @return
	 */
	public boolean isExist(JxglTdryForm model) {
		boolean flag = false;
		String doType=model.getDoType();
		if("add".equalsIgnoreCase(doType)){
			String sql = "select count(1) num from xg_jxgl_hzsf_tdryhjb where xn=? and bzbh=? and tdrydm=? ";
			String[]inputValue={model.getXn(),model.getBzdm(),model.getTdrydm()};
			String num = dao.getOneRs(sql, inputValue, "num");
			flag = !"0".equalsIgnoreCase(num);
		}else if("update".equalsIgnoreCase(doType)){
			String sql = "select count(1) num from xg_jxgl_hzsf_tdryhjb where xn=? and bzbh=? and tdrydm=? and tdrydm<>? ";
			String[] pkValue = model.getPkValue().split("!!@@!!");
			String[]inputValue={model.getXn(),model.getBzdm(),model.getTdrydm(),pkValue[2]};
			String num = dao.getOneRs(sql, inputValue, "num");
			flag = !"0".equalsIgnoreCase(num);
		}
		return flag;
	}

}
