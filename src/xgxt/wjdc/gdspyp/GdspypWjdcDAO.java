package xgxt.wjdc.gdspyp;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class GdspypWjdcDAO {
	
	/**
	 * �������ͳ��
	 * @param model
	 * @return
	 */
	public List<String[]> jgfxQuery(GdspypWjdcForm model){
		List<String[]> list = null;
		String[] colList = new String[]{"nj", "xydm", "zydm", "bjdm"};
		String[] colLikeList = new String[]{"xh", "xm"};
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select rownum r,a.*,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc ")
		.append("from (select a.xh, sum(nvl(b.fs, 0)) zf ")
		.append("from xg_wjdc_gdspyp_mslbxsdab a ")
		.append("left join xg_wjdc_gdspyp_mslbpfb b on a.id = b.id ")
		.append("and a.zfh = b.zfh and a.zbf = b.zbf ")
		.append("group by a.xh) a  left join view_xsbfxx b on a.xh = b.xh ");
		
		MakeQuery makeQuery = new MakeQuery();
		
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
			
			String query = makeQuery.getQueryString();
			String[] inputs = makeQuery.getInputList();
			
			String[] outputs = new String[]{"xh", "xm", "xymc", "zymc", "bjmc", "zf"};
			list = CommonQueryDAO.commonQuery(sql.toString(), query, inputs, outputs, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * ɾ���������
	 * @param params
	 * @return
	 */
	public boolean delJgfx(List<String[]> params){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		
		String sql = "delete xg_wjdc_gdspyp_mslbxsdab where xh=?";
		String delSql = "delete xg_wjdc_gdspyp_mslbxspyb where xh=?";
		
		try {
			int[] rs = dao.runBatch(sql, params);
			flag = dao.checkBatchResult(rs);
			
			if(flag){
				flag = dao.checkBatchResult(dao.runBatch(delSql, params));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ��ȡ��������
	 * @return
	 */
	public List<HashMap<String, String>> getPy(){
		String sql = "select id,pynr from xg_wjdc_gdspyp_pyb";
		
		return DAO.getInstance().getListNotOut(sql, new String[]{});
	}
	
	/**
	 * ��ȡ�����������
	 * @param pk
	 * @return
	 */
	public Map<String, String> getJgfxOne(String pk){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.pynr ")
			.append("from (select a.xh, sum(nvl(b.fs, 0)) zf ")
			.append("from xg_wjdc_gdspyp_mslbxsdab a ")
			.append("left join xg_wjdc_gdspyp_mslbpfb b on a.id = b.id ")
			.append("and a.zfh = b.zfh and a.zbf = b.zbf ")
			.append("where a.xh=? group by xh) a  left join view_xsbfxx b on a.xh = b.xh ")
			.append("left join xg_wjdc_gdspyp_mslbxspyb c on a.xh=c.xh");
		
		return DAO.getInstance().getMapNotOut(sql.toString(), new String[]{pk});
	}
	
	/**
	 * ������������
	 * @param model
	 * @return
	 */
	public boolean savePynr(GdspypWjdcForm model){
		String sql = "insert into xg_wjdc_gdspyp_mslbxspyb(xh,pynr) values(?,?)";
		boolean flag = false;
		
		try {
			 flag = DAO.getInstance().runUpdate(sql, new String[]{model.getXh(), model.getPynr()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ɾ����������
	 * @param model
	 * @return
	 */
	public boolean delPynr(GdspypWjdcForm model){
		String sql = "delete xg_wjdc_gdspyp_mslbxspyb where xh=?";
		boolean flag = false;
		
		try {
			 flag = DAO.getInstance().runUpdate(sql, new String[]{model.getXh()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ��ȡ����������Ŀ��
	 * @return
	 */
	public List<HashMap<String,String>> getMslbtmb(){
		DAO dao=new DAO();
		String sql="select * from xg_wjdc_gdspyp_mslbtmb order by to_number(id)";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/**
	 * �����ʾ�ش�
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public boolean saveWjhd(String[] sql) throws Exception{
		CommDAO cdao=new CommDAO();
		return cdao.saveArrDate(sql);
	}
	
	/**
	 * ��ȡ�ʾ�ش�������
	 * @param userName
	 * @return
	 */
	public List<HashMap<String,String>> getWjhdda(String userName){
		DAO dao=new DAO();
		
		String sql="select a.*,b.fs from xg_wjdc_gdspyp_mslbxsdab a " +
				"left join xg_wjdc_gdspyp_mslbpfb b on a.id = b.id and a.zfh = b.zfh and a.zbf = b.zbf where xh=? ";
		
		return dao.getListNotOut(sql, new String[]{userName});
	}
}
