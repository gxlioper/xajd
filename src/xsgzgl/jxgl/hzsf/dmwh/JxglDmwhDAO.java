package xsgzgl.jxgl.hzsf.dmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;

/**
 * ��ѵ����-��������-����ά��
 * @author yeyipin
 * @since 2012.7.16
 */
public class JxglDmwhDAO {
	DAO dao = DAO.getInstance();
	/**
	 * ��ø�������List
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
	 * ����Ŷ�����List
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
	 * �ṩ��������ʹ�õĽӿڷ�����ø�������List<HashMap<String,String>>
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
	 * �ṩ��������ʹ�õĽӿڷ�������Ŷ�����List<HashMap<String,String>>
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
	 * ������������
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
	 * �Ŷ���������
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
	 * ������ĸ�����������
	 * @return
	 */
	public String getMaxGrrydm() {
		String sql = "select max(to_number(grrydm)) max from xg_jxgl_hzsf_grrydmb";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	/**
	 * ��������Ŷ���������
	 * @return
	 */
	public String getMaxTdrydm() {
		String sql = "select max(to_number(tdrydm)) max from xg_jxgl_hzsf_tdrydmb";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	/**
	 * ɾ��������������
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
	 * ɾ���Ŷ���������
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
	 * ��֤���������Ƿ��ѱ�ʹ��
	 * @param pkValue
	 * @return
	 */
	public boolean isGrryUsed(String pkValue) {
		String sql = "select count(1) num from xg_jxgl_hzsf_grryhjb where grrydm = ? ";
		String num = dao.getOneRs(sql, new String[]{pkValue}, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	/**
	 * ��֤�������������Ƿ��Ѵ���
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
	 * ��֤�Ŷ����������Ƿ��ѱ�ʹ��
	 * @param pkValue
	 * @return
	 */
	public boolean isTdryUsed(String pkValue) {
		String sql = "select count(1) num from xg_jxgl_hzsf_tdryhjb where tdrydm = ? ";
		String num = dao.getOneRs(sql, new String[]{pkValue}, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	/**
	 * ��֤�Ŷ����������Ƿ��Ѵ���
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
