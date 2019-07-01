package xgxt.rcgl.gzdx.lxgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class LxglDAO {
	/**
	 * 保存留校申请
	 * @param model
	 * @return
	 */
	public boolean saveLxsq(LxglForm model){
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		boolean flag = false;
		
		sql.append("insert into xg_rcsw_qjlxb(xh,kssj,jssj,ts,qsh,lxyy,jzlxfs,sfnyf,sqsj) ")
			.append("values(?,?,?,?,?,?,?,?,to_char(sysdate,'yyyyMMdd'))");
		
		String[] inputs = new String[]{model.getXh(), model.getKssj(), model.getJssj(),
									   model.getTs(), model.getQsh(), model.getLxyy(), 
									   model.getJzlxfs(), model.getSfnyf()};
		
		try {
			flag = dao.runUpdate(sql.toString(), inputs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 获取请假学生信息
	 * @param xh
	 * @return
	 */
	public Map<String, String> getStuInfo(String xh){
		String sql = "select a.* from view_xsjbxx a where a.xh=?";
		String[] colList = new String[]{"xh", "xm", "xymc", "zymc", "bjmc" , "sjhm", "lxdh"};
		
		DAO dao = DAO.getInstance();
		return dao.getMap(sql, new String[]{xh}, colList);
	}
	
	/**
	 * 获取请假学生信息
	 * @param xh
	 * @return
	 */
	public Map<String, String> getLxInfo(String pkValue){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.* from (select a.xh||a.kssj||a.jssj pkValue,a.*,a.kssj ||'-'||jssj lxsj,")
			.append("b.xm,b.zydm,b.xydm,b.bjdm,b.xymc,b.zymc,b.bjmc,b.nj,b.lxdh,b.sjhm from xg_rcsw_qjlxb a,view_xsbfxx b where a.xh=b.xh) a where a.pkValue=?");
		
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{pkValue});
	}
	
	/**
	 * 获取审核信息
	 * @param model
	 * @param userStatus
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]> getShList(LxglForm model, User user, String[] outPutList) throws Exception {
		String condition = "";
		
		MakeQuery makeQuery = new MakeQuery();
		
		String[] colList = new String[]{"xydm", "zydm", "bjdm"};
		String[] colLikeList = new String[]{"xh", "xm"};
		makeQuery.makeQuery(colList, colLikeList, model);
		
		condition = makeQuery.getQueryString();
		String[] inPutList = makeQuery.getInputList();

		
		if("xx".equalsIgnoreCase(user.getUserStatus())){
			condition += " and sh1='通过' " + user.getQueryCondition();
		}else {
			condition += user.getQueryCondition();
		}
		
		String kssj=model.getKssj();
		String jssj=model.getJssj();
		
		StringBuilder query=new StringBuilder();
		
		if(!Base.isNull(kssj)){
			query.append(" and sqsj>='"+kssj+"' ");
		}
		
		if(!Base.isNull(jssj)){
			query.append(" and sqsj<='"+jssj+"' ");
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from (select a.xh||a.kssj||a.jssj pkValue,a.*,a.kssj ||'-'||jssj lxsj,b.xm,b.zydm,b.xydm,b.bjdm,b.xymc,b.zymc,b.bjmc,b.nj ")
			.append("from xg_rcsw_qjlxb a,view_xsbfxx b where a.xh=b.xh) a ")
		   .append(condition).append(query);
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", inPutList, outPutList, model);
	}
	
	/**
	 * 获取审核信息
	 * @param model
	 * @param userStatus
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]> getCxList(LxglForm model, User user, String[] outPutList) throws Exception {
		String condition = "";
		
		MakeQuery makeQuery = new MakeQuery();
		
		String[] colList = new String[]{"xydm", "zydm", "bjdm", "sh1", "sh2"};
		String[] colLikeList = new String[]{"xh", "xm"};
		makeQuery.makeQuery(colList, colLikeList, model);
		
		condition = makeQuery.getQueryString();
		String[] inPutList = makeQuery.getInputList();
		
		String kssj=model.getKssj();
		String jssj=model.getJssj();
		
		StringBuilder query=new StringBuilder();
		
		if(!Base.isNull(kssj)){
			query.append(" and sqsj>='"+kssj+"' ");
		}
		
		if(!Base.isNull(jssj)){
			query.append(" and sqsj<='"+jssj+"' ");
		}
		
		condition += user.getQueryCondition();

		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from (select a.xh||a.kssj||a.jssj pkValue,a.*,a.kssj ||'-'||jssj lxsj,b.xm,b.zydm,b.xydm,b.bjdm,b.xymc,b.zymc,b.bjmc,b.nj ")
			.append("from (select * from XG_RCSW_QJLXB union all select * from xg_rcsw_qjlxb_2013bak a where not exists(select 1 from XG_RCSW_QJLXB b where a.xh = b.xh)) a,view_xsbfxx b where a.xh=b.xh) a ")
		   .append(condition).append(query);
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", inPutList, outPutList, model);
	}
	
	/**
	 * 批量审核
	 * @return
	 */
	public boolean batchSh(String[] pkValues, User user, String shjg, String yj){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		
		String shjb = null;
		String shr = null;
		String shyj = null;
		
		if("xx".equalsIgnoreCase(user.getUserStatus())){
			shjb = "sh2";
			shr = "shr2";
			shyj = "shyj2";
		}else{
			shjb = "sh1";
			shr = "shr1";
			shyj = "shyj1";
		}
		
		List<String[]> params = new ArrayList<String[]>();
		
		for(String pkVal : pkValues){
			params.add(new String[]{shjg,user.getRealName(),yj,pkVal});
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_rcsw_qjlxb set ").append(shjb).append("=?,")
			.append(shr).append("=?,").append(shyj).append("=? where xh||kssj||jssj=?");
		
		try {
			int[] rs = dao.runBatch(sql.toString(), params);
			flag = dao.checkBatchResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 批量删除
	 * @param pkValues
	 * @return
	 */
	public boolean batchDel(String[] pkValues){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String sql = "delete xg_rcsw_qjlxb where xh||kssj||jssj=?";
		
		List<String[]> params = new ArrayList<String[]>();
		
		for(String pk : pkValues){
			params.add(new String[]{pk});
		}
		try {
			dao.runBatch(sql, params);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 修改请假管理
	 * @param model
	 * @param pkValue
	 * @return
	 */
	public boolean updateLxgl(LxglForm model,String pkValue){
		boolean flag = false;
		
		String sql = "update xg_rcsw_qjlxb set ts=?,jzlxfs=?,lxyy=?,qsh=?," +
				"sfnyf=? where xh||kssj||jssj=?";
		String[] input = new String[]{model.getTs(),model.getJzlxfs(),model.getLxyy(),
							model.getQsh(),model.getSfnyf(), pkValue};
		
		DAO dao = DAO.getInstance();
		try {
			flag = dao.runUpdate(sql, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 获取导出数据
	 * @param model
	 * @param user
	 * @param outPutList
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getExpList(LxglForm model, User user) throws Exception {
		String condition = "";
		
		MakeQuery makeQuery = new MakeQuery();
		
		String[] colList = new String[]{"xydm", "zydm", "bjdm","sh1","sh2"};
		String[] colLikeList = new String[]{"xh", "xm"};
		makeQuery.makeQuery(colList, colLikeList, model);
		
		condition = makeQuery.getQueryString();
		String[] inPutList = makeQuery.getInputList();

		String kssj=model.getKssj();
		String jssj=model.getJssj();
		
		StringBuilder query=new StringBuilder();
		
		if(!Base.isNull(kssj)){
			query.append(" and sqsj>='"+kssj+"' ");
		}
		
		if(!Base.isNull(jssj)){
			query.append(" and sqsj<='"+jssj+"' ");
		}
		
		
		condition += user.getQueryCondition();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.* from (select a.xh||a.kssj||a.jssj pkValue,a.*,a.kssj ||'-'||jssj lxsj,b.xm,b.xb,b.zydm,b.xydm,b.bjdm,b.xymc,b.zymc,b.bjmc,b.nj,")
			.append("(select c.jtszd from xsfzxxb c where c.xh=a.xh)jtszd,b.lxdh,b.sjhm ")
			.append("from xg_rcsw_qjlxb a,view_xsbfxx b where a.xh=b.xh) a ")
		   .append(condition).append(query);
		
		return DAO.getInstance().getListNotOut(sql.toString(), inPutList);
	}
	
	/**
	 * 获取学生留校申请信息
	 * @param xh
	 * @return
	 * @author honglin
	 * @date 2013-01-24
	 */
	public Map<String, String> getXslxInfo(String xh){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.* from (select a.xh pkValue,a.*,a.kssj ||'-'||jssj lxsj,")
			.append("b.xm,b.zydm,b.xydm,b.bjdm,b.xymc,b.zymc,b.bjmc,b.nj,b.lxdh,b.sjhm from xg_rcsw_qjlxb a,view_xsbfxx b where a.xh=b.xh) a where a.pkValue=?");
		
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 修改请假申请管理
	 * @param model
	 * @param pkValue
	 * @return
	 * @author honglin
	 * @date 2013-01-25
	 */
	public boolean updateXslxgl(LxglForm model,String pkValue){
		boolean flag = false;
		
		String sql = "update xg_rcsw_qjlxb set ts=?,jzlxfs=?,lxyy=?,qsh=?," +
				"sfnyf=?,kssj=?,jssj=? where xh=?";
		String[] input = new String[]{model.getTs(),model.getJzlxfs(),model.getLxyy(),
							model.getQsh(),model.getSfnyf(),model.getKssj(),model.getJssj(), pkValue};
		
		DAO dao = DAO.getInstance();
		try {
			flag = dao.runUpdate(sql, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
