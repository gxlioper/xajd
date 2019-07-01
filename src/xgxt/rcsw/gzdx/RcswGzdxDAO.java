package xgxt.rcsw.gzdx;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.PjpyTyDAO;

import xgxt.rcgl.xmlg.RcglDAO;
import xgxt.rcsw.RcswDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;

public class RcswGzdxDAO extends RcswDAO {

	/**
	 * 获得留言回复列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLyhfList(RcswGzdxModel model) {

		DAO dao = DAO.getInstance();

		String lymc = model.getLymc();// '留言名称';
		String lylx = model.getLylx();// '留言类型';
		String lysj = model.getLysj();// '留言时间';
		String lyr = model.getLyr();// '留言人';

		String[] collist = new String[] { "xm", "sj", "hfsj", "hfr", "hfnr",
				"hfpj", "id" };
		String[] valueList = new String[] { lymc, lylx, lysj, lyr };

		StringBuffer sql = new StringBuffer();
		sql.append("select xm,hfsj,hfr,hfnr,hfpj,id, ");
		sql.append("(to_char(sj, 'YYYY') || '年' || to_char(sj, 'MM') || '月' ||");
		sql.append("to_char(sj, 'DD') || '日 ' || to_char(sj, 'HH') || '时' ||");
		sql.append("to_char(sj, 'MI') || '分' || to_char(sj, 'SS') || '秒') sj ");
		sql.append("from view_rcsw_lyhf where ");
		sql.append("lymc = ? ");
		sql.append("and lylx = ? ");
		sql.append("and lysj = ? ");
		sql.append("and lyr = ? ");
		sql.append("order by hfls");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				valueList, collist);

		return list;
	}
	
	/**
	 * 修改回复内容
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean updateHfnr(RcswGzdxModel model) throws Exception {

		DAO dao = DAO.getInstance();

		String id = model.getId();
		String hfnr = model.getHfnr();

		String sql = "update rcsw_lyhfb set hfnr = ?,hfsj = to_char(SYSDATE,'YYYYMMDDHH24MISS') where id = ?";
		boolean flag = dao.runUpdate(sql, new String[] { hfnr, id });
		return flag;
	}
	
	/**
	 * 保存置顶信息
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveZdInfo(String pkValue,boolean flg) throws Exception {

		DAO dao = DAO.getInstance();

		boolean flag = false;
		
		//flg:true(置顶操作) false(取消操作)
		String[] exec = flg ? new String[2] : new String[1];
		
		String sql = "delete from rcsw_lyzdb where pk = '" + pkValue + "'";
		exec[0] = sql;
		
		if (flg) {
			sql = "insert into rcsw_lyzdb (pk) values('" + pkValue + "')";
			exec[1] = sql;
		}
		
		int[] res = dao.runBatch(exec);

		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		
		return flag;
	}
	
	/**
	 * 删除学生留言信息
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delXslyInfo(String[] pkValue) throws Exception {

		DAO dao = DAO.getInstance();

		boolean flag = false;
		
		String[] exec = new String[pkValue.length*3];
		
		StringBuffer xslySb = new StringBuffer();
		StringBuffer lyhfSb = new StringBuffer();
		StringBuffer hflsSb = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		
		xslySb.append("delete from rcsw_xslyb ");
		lyhfSb.append("delete from rcsw_lyhfb ");
		hflsSb.append("delete from rcsw_hflsb ");
		sql.append("select id from rcsw_lyhfb ");
		
		//获得所删除留言的ID
		for(int i=0;i<pkValue.length;i++){
			if(i==0){
				sql.append("where lyr||lymc||lylx||lysj = ? ");
			}else{
				sql.append("or lyr||lymc||lylx||lysj = ? ");
			}
		}
		
		List<String> list = dao.getList(sql.toString(), pkValue, "id");
		
		for(int i=0;i<pkValue.length;i++){
			//学生留言表
			if(i==0){
				xslySb.append("where lyr||lymc||lylx||lysj = '" + pkValue[i] + "' ");
			}else{
				xslySb.append("or lyr||lymc||lylx||lysj = '" + pkValue[i] + "' ");
			}
			
			//留言回复
			if(i==0){
				lyhfSb.append("where lyr||lymc||lylx||lysj = '" + pkValue[i] + "' ");
			}else{
				lyhfSb.append("or lyr||lymc||lylx||lysj = '" + pkValue[i] + "' ");
			}
		}
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String id = list.get(i);
				
				// 回复历史
				if (i == 0) {
					hflsSb.append("where id = '" + id + "' ");
				} else {
					hflsSb.append("or id = '" + id + "' ");
				}
			}
		}
		
		int n = 0;
		
		exec[n] = xslySb.toString();
		n++;
		exec[n] = lyhfSb.toString();
		n++;
		exec[n] = hflsSb.toString();
		
		int[] res = dao.runBatch(exec);

		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		
		return flag;
	}
	
	/**
	 * 获得学生留言列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]>  getXslyInfoList(RcswGzdxModel model,
			String userType) {

		DAO dao = DAO.getInstance();

		Pages pages = model.getPages();
		
		// 留言人
		String xhzgh = model.getLyr();

		String[] colList = new String[] { "pk", "lxmc", "lymc", "xm", "lysj",
				"hfqk" };
		String[] valueList = new String[] { xhzgh };

		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select t.* ,rownum r from (select * from ( ");
		if ("stu".equalsIgnoreCase(userType)) {
			sql.append("select * from view_rcsw_xsly where czsj <> 0 ");
			sql.append("union all ");
		}
		sql.append("select * from (");
		sql.append("select * from view_rcsw_xsly where xhzgh = ? order by lysj");
		sql.append("))) t");
		sql.append(") where r > "); 
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));

		ArrayList<String[]> list = dao.rsToVator(sql.toString(), valueList, colList);

		sql = new StringBuffer();
		sql.append("select count(*) count from (");
		sql.append("select * from view_rcsw_xsly where xhzgh = ? ");
		if ("stu".equalsIgnoreCase(userType)) {
			sql.append("union all ");
			sql.append("select * from view_rcsw_xsly where czsj <>0 ");
		}
		sql.append(")");
		
		String count = dao.getOneRs(sql.toString(), valueList, "count");
		if(!Base.isNull(count)){
			pages.setMaxRecord(Integer.parseInt(count));
		}
		
		return list;
	}	
	
	/**
	 * 获得老师回复留言列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getHflyInfoList(RcswGzdxModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();
		
		Pages pages = model.getPages();
		
		String[] queryList = new String[] { "lyr", "lylx", "xydm", "zydm",
				"bjdm", "lx", "nj" };

		String[] queryLikeList = new String[] { "xm" };

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();
		
		String[] colList = new String[] { "pk", "lxmc", "lymc", "lx", "xhzgh","xm",
				"lysj", "hfqk", "czsj" };
		String[] valueList = myQuery.getInputList();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select t.*,rownum r from (");
		sql.append("select pk, lxmc, lymc, lx, xhzgh,xm, lysj, hfqk, ");
		sql.append("czsj from view_rcsw_xsly where czsj <> 0 ");
		sql.append("union all ");
		sql.append("select pk, lxmc, lymc, lx, xhzgh,xm, lysj, hfqk, ");
		sql.append("czsj from view_rcsw_xsly ");
		sql.append(query);
		sql.append(" and czsj = 0");
		sql.append(") t ");
		sql.append(") where r > "); 
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));

		ArrayList<String[]> list = dao.rsToVator(sql.toString(), valueList, colList);

		sql = new StringBuffer();
		sql.append("select count(t.pk) count from (");
		sql.append("select pk, lxmc, lymc, lx, xm, lysj, hfqk, ");
		sql.append("czsj from view_rcsw_xsly where czsj <> 0 ");
		sql.append("union all ");
		sql.append("select pk, lxmc, lymc, lx, xm, lysj, hfqk, ");
		sql.append("czsj from view_rcsw_xsly ");
		sql.append(query);
		sql.append(" and czsj = 0");
		sql.append(") t ");
		
		String count = dao.getOneRs(sql.toString(), valueList, "count");
		if(!Base.isNull(count)){
			pages.setMaxRecord(Integer.parseInt(count));
		}
		
		return list;
	}
	
	/**
	 * 获得回复楼数
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public int getHfls(RcswGzdxModel model){
		
		DAO dao = DAO.getInstance();
		
		// 留言人
		String lyr = model.getLyr();
		// 留言名称
		String lymc = model.getLymc();
		// 留言类型
		String lylx = model.getLylx();
		// 留言时间
		String lysj = model.getLysj();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) num from rcsw_lyhfb ");
		sql.append("where lyr = ? ");
		sql.append("and   lymc = ? ");
		sql.append("and   lylx = ? ");
		sql.append("and   lysj = ? ");
		
		String[] inputValue = new String[] { lyr, lymc, lylx, lysj };
		String num = dao.getOneRs(sql.toString(), inputValue, "num");
		
		int ls = Base.isNull(num) ? 0 : Integer.parseInt(num);

		return ls;
	}
	
	/**
	 * 判断是否选择咨询师
	 */
	public HashMap<String,String> getXzZxs(String dm){
		DAO dao = DAO.getInstance();
		String sql="select xzzxs from rcsw_lylxb where dm=?";
		HashMap<String,String>hashMap=dao.getMap(sql, new String[]{dm}, new String[]{"xzzxs"});
		return hashMap;
	}
}
