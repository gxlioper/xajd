package xsgzgl.jxgl.hzsf.dmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;

/**
 * 军训管理-基础设置-代码维护
 * @author yeyipin
 * @since 2012.7.16
 */
public class JxglDmwhDAO {
	DAO dao = DAO.getInstance();
	/**
	 * 获得个人荣誉List
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getGrryList(JxglDmwhForm model) throws Exception {
		String sql = "select * from (select grrydm,grrymc,rownum r from xg_jxgl_hzsf_grrydmb order by grrydm) where 1=1 ";
		String[] output = new String[] {"grrydm","r","grrydm","grrymc"};
		return CommonQueryDAO.commonQuery(sql, "", new String[]{},
				output, model);
	}
	/**
	 * 获得团队荣誉List
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> getTdryList(JxglDmwhForm model) throws Exception{
		String sql = "select * from (select tdrydm,tdrymc,rownum r from  xg_jxgl_hzsf_tdrydmb order by tdrydm) where 1=1 ";
		String[] output = new String[] {"tdrydm","r","tdrydm","tdrymc"};
		return CommonQueryDAO.commonQuery(sql, "", new String[]{},
				output, model);
	}
	/**
	 * 提供其他程序使用的接口方法获得个人荣誉List<HashMap<String,String>>
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGrrydmList() {
		String sql = "select grrydm,grrymc from  xg_jxgl_hzsf_grrydmb order by grrydm ";
		String[] output = new String[] {"grrydm","grrymc"};
		return dao.getList(sql, new String[]{}, output);
	}
	/**
	 * 提供其他程序使用的接口方法获得团队荣誉List<HashMap<String,String>>
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTdrydmList() {
		String sql = "select tdrydm,tdrymc from  xg_jxgl_hzsf_tdrydmb order by tdrydm ";
		String[] output = new String[] {"tdrydm","tdrymc"};
		return dao.getList(sql, new String[]{}, output);
	}
	/**
	 * 个人荣誉保存
	 * @param model
	 * @param doType
	 * @return
	 * @throws Exception 
	 */
	public boolean grryBc(JxglDmwhForm model, String type) throws Exception {
		boolean flag = false;
		if("add".equalsIgnoreCase(type)){
			String sql = "insert into xg_jxgl_hzsf_grrydmb(grrydm,grrymc) values(?,?)";
			String[] input = {model.getGrrydm(),model.getGrrymc()};
			flag = dao.runUpdate(sql, input);
		}else if("update".equalsIgnoreCase(type)){
			String sql = "update xg_jxgl_hzsf_grrydmb set grrymc = ? where grrydm = ?";
			String[] input = {model.getGrrymc(),model.getGrrydm()};
			flag = dao.runUpdate(sql, input);
		}
		return flag;
	}
	/**
	 * 团队荣誉保存
	 * @param model
	 * @param doType
	 * @return
	 * @throws Exception 
	 */
	public boolean tdryBc(JxglDmwhForm model, String type) throws Exception {
		boolean flag = false;
		if("add".equalsIgnoreCase(type)){
			String sql = "insert into xg_jxgl_hzsf_tdrydmb(tdrydm,tdrymc) values(?,?)";
			String[] input = {model.getTdrydm(),model.getTdrymc()};
			flag = dao.runUpdate(sql, input);
		}else if("update".equalsIgnoreCase(type)){
			String sql = "update xg_jxgl_hzsf_tdrydmb set tdrymc = ? where tdrydm = ?";
			String[] input = {model.getTdrymc(),model.getTdrydm()};
			flag = dao.runUpdate(sql, input);
		}
		return flag;
	}
	/**
	 * 获得最大的个人荣誉代码
	 * @return
	 */
	public String getMaxGrrydm() {
		String sql = "select max(to_number(grrydm)) max from xg_jxgl_hzsf_grrydmb";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	/**
	 * 获得最大的团队荣誉代码
	 * @return
	 */
	public String getMaxTdrydm() {
		String sql = "select max(to_number(tdrydm)) max from xg_jxgl_hzsf_tdrydmb";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	/**
	 * 删除个人荣誉代码
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean grrySc(List<String[]> params) throws SQLException {
		String sql ="delete from xg_jxgl_hzsf_grrydmb where grrydm=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 删除团队荣誉代码
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean tdrySc(List<String[]> params) throws SQLException {
		String sql ="delete from xg_jxgl_hzsf_tdrydmb where tdrydm=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 验证个人荣誉是否已被使用
	 * @param pkValue
	 * @return
	 */
	public boolean isGrryUsed(String pkValue) {
		String sql = "select count(1) num from xg_jxgl_hzsf_grryhjb where grrydm = ? ";
		String num = dao.getOneRs(sql, new String[]{pkValue}, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	/**
	 * 验证个人荣誉名称是否已存在
	 * @param grrymc
	 * @param doType
	 * @return
	 */
	public boolean isGrryExist(JxglDmwhForm model, String type) {
		boolean flag = false;
		if("add".equalsIgnoreCase(type)){
			String sql="select count(1) num from xg_jxgl_hzsf_grrydmb where grrymc=?";
			String num=dao.getOneRs(sql, new String[]{model.getGrrymc()}, "num");
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}
		}else if("update".equalsIgnoreCase(type)){
			String sql="select count(1) num from xg_jxgl_hzsf_grrydmb where grrymc=? and grrydm<>?";
			String num=dao.getOneRs(sql, new String[]{model.getGrrymc(),model.getGrrydm()}, "num");
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * 验证团队荣誉名称是否已被使用
	 * @param pkValue
	 * @return
	 */
	public boolean isTdryUsed(String pkValue) {
		String sql = "select count(1) num from xg_jxgl_hzsf_tdryhjb where tdrydm = ? ";
		String num = dao.getOneRs(sql, new String[]{pkValue}, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	/**
	 * 验证团队荣誉名称是否已存在
	 * @param model
	 * @param doType
	 * @return
	 */
	public boolean isTdryExist(JxglDmwhForm model, String type) {
		boolean flag = false;
		if("add".equalsIgnoreCase(type)){
			String sql="select count(1) num from xg_jxgl_hzsf_tdrydmb where tdrymc=?";
			String num=dao.getOneRs(sql, new String[]{model.getTdrymc()}, "num");
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}
		}else if("update".equalsIgnoreCase(type)){
			String sql="select count(1) num from xg_jxgl_hzsf_tdrydmb where tdrymc=? and tdrydm<>?";
			String num=dao.getOneRs(sql, new String[]{model.getTdrymc(),model.getTdrydm()}, "num");
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}
		}
		return flag;
	}



}
