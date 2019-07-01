package xsgzgl.jxgl.hzsf.grry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
/**
 * 军训管理-军训获奖-个人荣誉
 * @author yeyipin
 * @since 2012.7.27
 */
public class JxglGrryDAO {
	DAO dao = DAO.getInstance();
	/**
	 * 个人荣誉查询
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> GrryCx(JxglGrryForm model,String searchTj) throws Exception {
		String[] colList =new String[]{ "pkValue", "xn", "xh","xm","tuanmc","yingmc","lianmc","bjmc","grrymc" };
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,rownum r from(select a.*,b.bzmc tuanmc from(select a.*,b.bzmc yingmc,b.sjdm tuandm from");
		sql.append("(select a.*,b.bzmc lianmc,b.sjdm yingdm from (select a.*,b.sjdm liandm from");
		sql.append("(select a.xn||'!!@@!!'||a.xh||'!!@@!!'||a.grrydm pkValue,a.xn,a.xh,b.xm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.grrydm,c.grrymc ");
		sql.append("from xg_jxgl_hzsf_grryhjb a left join view_xsjbxx b on a.xh = b.xh left join xg_jxgl_hzsf_grrydmb c on a.grrydm = c.grrydm)");
		sql.append("a left join jxbzdmb b on a.bjdm = b.bzdm and a.xn = b.xn and b.bzdj = '4')");
		sql.append("a left join jxbzdmb b on a.liandm = b.bzdm and a.xn = b.xn and b.bzdj = '3')");
		sql.append("a left join jxbzdmb b on a.yingdm = b.bzdm and a.xn = b.xn and b.bzdj = '2')");
		sql.append("a left join jxbzdmb b on a.tuandm = b.bzdm and a.xn = b.xn and b.bzdj = '1')");
		sql.append("a where 1 = 1 ");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj, new String[]{}, colList, model);
	}
	/**
	 * 获得军训学生名单列表
	 * @param model
	 * @param searchTj
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getJxxs(JxglGrryForm model, String searchTj) throws Exception {
		String[] colList =new String[]{"xh","xm","bjmc","tuanmc","yingmc","lianmc" };
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,rownum r from ");
		sql.append("(select a.*,b.bzmc tuanmc from ");
		sql.append("(select a.*,b.bzmc yingmc,sjdm tuandm from ");
		sql.append("(select a.*,b.bzmc lianmc,sjdm yingdm from  ");
		sql.append("(select a.xn,a.xh,b.xm,b.bjdm,b.bjmc,a.ldbh liandm from jxgl_jxmdb a left join view_xsjbxx b on a.xh = b.xh ) ");
		sql.append("a left join jxbzdmb b on a.liandm = b.bzdm) ");
		sql.append("a left join jxbzdmb b on a.yingdm = b.bzdm) ");
		sql.append("a left join jxbzdmb b on a.tuandm = b.bzdm order by tuandm,yingdm,liandm) ");
		sql.append("a where 1=1 ");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj, new String[]{}, colList, model);
	}
	/**
	 * 获得个人荣誉Map
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getGrryMap(JxglGrryForm model) {
		String sql = "select *　from  view_xg_jxgl_hzsf_grryhjb where xn=? and xh=? and grrydm=? and rownum=1 ";
		String[] inputValue = model.getPkValue().split("!!@@!!");
		String[] outputValue = {"xh","xn","xm","tuanmc","yingmc","lianmc","bjmc","grrydm","grrymc","bz"};
		return dao.getMap(sql, inputValue, outputValue);
	}
	/**
	 * 获得军训学生信息map
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxxsMap(JxglGrryForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.bzmc tuanmc from ");
		sql.append("(select a.*,b.bzmc yingmc,sjdm tuandm from ");
		sql.append("(select a.*,b.bzmc lianmc,sjdm yingdm from "); 
		sql.append("(select a.xn,a.xh,b.xm,b.bjdm,b.bjmc,a.ldbh liandm from jxgl_jxmdb a left join view_xsjbxx b on a.xh = b.xh ) ");
		sql.append("a left join jxbzdmb b on a.liandm = b.bzdm) ");
		sql.append("a left join jxbzdmb b on a.yingdm = b.bzdm) ");
		sql.append("a left join jxbzdmb b on a.tuandm = b.bzdm where a.xn =? and xh =? and rownum =1 ");
		String[] inputValue = {model.getXn(),model.getXh()};
		String[] outputValue = {"xh","xn","xm","tuanmc","yingmc","lianmc","bjmc"};
		return dao.getMap(sql.toString(), inputValue, outputValue);
	}
	/**
	 * 个人荣誉信息保存
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean grryBc(JxglGrryForm model) throws Exception {
		String sql = "insert into xg_jxgl_hzsf_grryhjb(xh,xn,grrydm,bz)values(?,?,?,?)";
		String[]input={model.getXh(),model.getXn(),model.getGrrydm(),model.getBz()};
		return dao.runUpdate(sql, input);
	}
	/**
	 * 更新个人荣誉修改信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean grryXg(JxglGrryForm model) throws Exception {
		boolean flag = false;
		String sql = "update xg_jxgl_hzsf_grryhjb set grrydm=?,bz=? where xn = ? and xh = ? and grrydm = ?";
		String[] pkV = model.getPkValue().split("!!@@!!");
		String[] input = new String[]{model.getGrrydm(),model.getBz(),pkV[0],pkV[1],pkV[2]};
		flag = dao.runUpdate(sql,input);
		return flag;
	}
	/**
	 * 个人荣誉批量删除
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean grrySc(List<String[]> params) throws SQLException {
		String sql = " delete from xg_jxgl_hzsf_grryhjb where xn=? and xh=? and grrydm=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 获得团营连列表
	 * @param type
	 * @param currXn
	 * @return
	 */
	public List<HashMap<String, String>> getTuanYingLianList(JxglGrryForm model) {
		if("1".equalsIgnoreCase(model.getBzdj())){
			String sql = "select bzdm dm,bzmc mc from jxbzdmb where bzdj = ? and xn = ? and sjdm is null ";
			return dao.getList(sql, new String[]{model.getBzdj(),model.getXn()}, new String[]{"dm","mc"});
		}
		String sql = "select bzdm dm,bzmc mc from jxbzdmb where bzdj = ? and xn = ? and sjdm = ? ";
		return dao.getList(sql, new String[]{model.getBzdj(),model.getXn(),model.getSjdm()}, new String[]{"dm","mc"});
	}
	/**
	 * 是否已存在
	 * @param model
	 * @return
	 */
	public boolean isExist(JxglGrryForm model) {
		boolean flag = false;
		String doType=model.getDoType();
		if("add".equalsIgnoreCase(doType)){
			String sql = "select count(1) num from xg_jxgl_hzsf_grryhjb where xn=? and xh=? and grrydm=? ";
			String[]inputValue={model.getXn(),model.getXh(),model.getGrrydm()};
			String num = dao.getOneRs(sql, inputValue, "num");
			flag = !"0".equalsIgnoreCase(num);
		}else if("update".equalsIgnoreCase(doType)){
			String sql = "select count(1) num from xg_jxgl_hzsf_grryhjb where xn=? and xh=? and grrydm=? and grrydm<>? ";
			String[] pkValue = model.getPkValue().split("!!@@!!");
			String[]inputValue={model.getXn(),model.getXh(),model.getGrrydm(),pkValue[2]};
			String num = dao.getOneRs(sql, inputValue, "num");
			flag = !"0".equalsIgnoreCase(num);
		}
		return flag;
	}





}
