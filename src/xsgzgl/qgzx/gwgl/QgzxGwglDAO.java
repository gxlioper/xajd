package xsgzgl.qgzx.gwgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import common.XszzXmdm;
/**
 * 勤工助学-勤工岗位管理-岗位信息管理
 * @author yeyipin
 * @since 2012.7.17
 */
public class QgzxGwglDAO {
	DAO dao = DAO.getInstance();
	
	
	/**
	 * 获得岗位信息List
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwsqList(QgzxGwglForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		User user = myForm.getUser();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "gwdm","r","xn", "yrdwmc", "gwmc","gwxzmc", "xqrs", "knsrs", "sqsj", "shzt" };
		sql.append(" select a.*,rownum r,case when a.shzt='通过'or a.shzt='不通过' then 'disabled' end as dis from view_xg_qgzx_gwxxsqb a where 1=1 ");
		//	
		
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	
	
	/**
	 * 获得岗位信息List
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwxxList(QgzxGwglForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		User user = myForm.getUser();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}
		StringBuilder sql = new StringBuilder();
		String[] colList=null;
		if("10052".equals(Base.xxdm)){
			 colList = new String[] { "gwdm", "r", "xn", "yrdwmc", "gwxh","gwmc", "gwxzmc","xqrs", "zgrs","tgrs" };
		}else{
			 colList = new String[] { "gwdm", "r", "xn", "yrdwmc","gwmc", "gwxzmc","xqrs", "zgrs","tgrs" };
		}
		
		sql.append(" select a.*,rownum r from view_xg_qgzx_gwxxb a where 1=1 ");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	
	
	/**
	 * 获得岗位信息List
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwshList(QgzxGwglForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		User user = myForm.getUser();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "gwdm","dis", "r", "xn", "yrdwmc","gwmc","gwxzmc", "xqrs", "knsrs", "sqsj", "shzt" };
		sql.append(" select a.*,rownum r,case when (select count(1) from xg_qgzx_gwxxb b where a.gwdm = b.gwdm)<>'0' or a.shzt = '不通过'then'disabled' end as dis from view_xg_qgzx_gwxxsqb a where 1=1 ");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	
	
	/**
	 * 学生信息列表
	 * @param myForm
	 * @param searchTj2 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getStuList(QgzxGwglForm myForm,HttpServletRequest request) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "a","xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "xh", "r", "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc","sfkns" };
		
		// 困难生项目代码
		String xmdm = XszzXmdm.XSZZ_KNS;
		HashMap<String, String> xmInfo = dao.getMapNotOut("select * from view_xszz_comm_xmwh where pk=? ", new String[]{xmdm});
		// 困难生评奖周期
		String sqzq = xmInfo.get("sqzq");
		// 困难生审核级别
		String shjb = xmInfo.get("shjb");
		String xn = Base.currXn;
		String xq = Base.currXq;
		String nd = Base.currNd;
		sql.append(" select a.*,rownum r from (select a.*,  decode(b.xh ,null,'否','是') sfkns from view_xsjbxx a left join  ");
		if("10335".equals(Base.xxdm)){
			sql.append(" (select b.xh,max(b.sqsj) from view_knsjgb_fqxrd b group by b.xh ");
		}else{
			sql.append(" (select b.xh,max(b.sqsj) from xg_xszz_new_knsjgb b ");
			
			//加入学年查询条件， 使勤工岗位人员只能是当前岗位学院的困难生
			sql.append(" where b.xn='");
			sql.append(myForm.getXn());
			
			sql.append("' group by b.xh ");
		}
		if("yes".equals(request.getParameter("sfxyzgsc"))){
			sql.append(") b on a.xh = b.xh where not exists(select 1 from xg_qgzx_xsgwxxb c where a.xh = c.xh and zgzt='zg' and gwdm = '"+myForm.getPkValue()+"') and a.xh in(select xh from xg_qgzx_qgzxxsb)) a where 1=1");	
		}
		else{
			sql.append(") b on a.xh = b.xh where not exists(select 1 from xg_qgzx_xsgwxxb c where a.xh = c.xh and zgzt='zg' and gwdm = '"+myForm.getPkValue()+"') order by a.nj,a.xydm,a.zydm,a.bjdm,a.xh) a where 1=1");
		}
				
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	
	
	/**
	 * 获得岗位信息Map
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String> getGwxxMap(QgzxGwglForm myForm){
		String sql = "select * from view_xg_qgzx_gwxxb where gwdm =? ";
		String[] inputValue = {myForm.getPkValue()};
		return dao.getMapNotOut(sql, inputValue);
	}
	
	/**
	 * 获得岗位申请Map
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String> getGwsqMap(QgzxGwglForm myForm){
		String sql = "select * from view_xg_qgzx_gwxxsqb where gwdm =? ";
		String[] inputValue = {myForm.getPkValue()};
		return dao.getMapNotOut(sql, inputValue);
	}
	
	
	/**
	 * 保存岗位信息
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean gwxxBc(QgzxGwglForm myForm) throws Exception {
		//String gwdm = myForm.getGwdm();
		String xn = myForm.getXn();
		String yrbm = myForm.getYrbm();
		String gwmc = myForm.getGwmc();
		String gwxzdm = myForm.getGwxzdm();
		String xqrs = myForm.getXqrs();
		String knsrs = myForm.getKnsrs();
		String gwms = myForm.getGwms();
		String gwryyq = myForm.getGwryyq();
		String gwyqryxg = myForm.getGwyqryxg();
		String bz = myForm.getBz();
		String gwcjsx=myForm.getGwcjsx();
		String sql = "insert into xg_qgzx_gwxxb (gwxh,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,gwyqryxg,bz,gwcjsx) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		String[] input = { myForm.getGwxh(),xn, yrbm, gwmc, gwxzdm, xqrs, knsrs, gwms, gwryyq,gwyqryxg, bz,gwcjsx};
		return dao.runUpdate(sql,input);
	}
	
	
	/**
	 * 同一学年,同一部门是否存在相同岗位名称的岗位信息
	 * @param model
	 * @return
	 */
	public boolean isExist(QgzxGwglForm model) {
		String pkValue = Base.isNull(model.getPkValue())?" ":model.getPkValue();
		String xn = model.getXn();
		String yrbm = model.getYrbm();
		String gwmc = model.getGwmc();
		String sql ="select count(1) num from xg_qgzx_gwxxb where gwdm<>? and xn=? and yrdwdm=? and gwmc=? ";
		String[] inputValue = new String[]{pkValue,xn,yrbm,gwmc};
		String num = dao.getOneRs(sql, inputValue, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	
	
	/**
	 * 同一学年,同一部门是否存在相同岗位名称的岗位信息
	 * @param model
	 * @return
	 */
	public boolean checkZjGwsqInfoExist(QgzxGwglForm model) {
		String pkValue = Base.isNull(model.getPkValue())?" ":model.getPkValue();
		String xn = model.getXn();
		String yrbm = model.getYrbm();
		String gwmc = model.getGwmc();
		String sql ="select count(1) num from xg_qgzx_gwxxsqb where gwdm<>? and xn=? and yrdwdm=? and gwmc=? ";
		String[] inputValue = new String[]{pkValue,xn,yrbm,gwmc};
		String num = dao.getOneRs(sql, inputValue, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	
	
	
	/**
	 * 判断岗位是否已被使用
	 * @param pkValue
	 * @return
	 */
	public boolean isUsed(String pkValue) {
		String sql="select count(1) num from xg_qgzx_xsgwxxb where gwdm = ? ";
		String num = dao.getOneRs(sql, new String[]{pkValue}, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	
	
	/**
	 * 修改岗位信息
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean gwxxXg(QgzxGwglForm myForm) throws Exception {
		String pkValue = myForm.getPkValue();
		String gwmc = myForm.getGwmc();
		String gwxzdm = myForm.getGwxzdm();
		String xqrs = myForm.getXqrs();
		String knsrs = myForm.getKnsrs();
		String gwms = myForm.getGwms();
		String gwryyq = myForm.getGwryyq();
		String gwyqryxg = myForm.getGwyqryxg();
		String bz = myForm.getBz();
		String gwcjsx=myForm.getGwcjsx();
		String sql = "update xg_qgzx_gwxxb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,gwms=?,gwryyq=?,gwyqryxg=?,bz=?,gwcjsx=? where gwdm=?";
		String[] input = {gwmc,gwxzdm,xqrs, knsrs, gwms, gwryyq, gwyqryxg,bz,gwcjsx,pkValue};
		return dao.runUpdate(sql,input);
	}
	
	
	/**
	 * 修改岗位信息
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean bcXgGwsq(QgzxGwglForm myForm) throws Exception {
		String pkValue = myForm.getPkValue();
		String gwmc = myForm.getGwmc();
		String gwxzdm = myForm.getGwxzdm();
		String xqrs = myForm.getXqrs();
		String knsrs = myForm.getKnsrs();
		String gwms = myForm.getGwms();
		String gwryyq = myForm.getGwryyq();
		String gwyqryxg = myForm.getGwyqryxg();
		String bz = myForm.getBz();
		String gwcjsx=myForm.getGwcjsx();
		String sql = "update xg_qgzx_gwxxsqb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,gwms=?,gwryyq=?,gwyqryxg=?,bz=?,sqsj=to_char(sysdate,'yyyy-MM-dd hh:mi:ss'),gwcjsx=?,shzt='0' where gwdm=?";
		String[] input = {gwmc,gwxzdm,xqrs, knsrs, gwms, gwryyq, gwyqryxg, bz, gwcjsx,pkValue};
		return dao.runUpdate(sql,input);
	}
	
	/**
	 * 修改岗位信息（【用人单位岗位申请】的记录审核【通过】后，在【岗位人员维护】中进行信息修改， 【用人单位岗位申请】中相应的信息也被修改）
	 */
	public boolean bcXgGwsqByJg(QgzxGwglForm myForm) throws Exception {
		String pkValue = myForm.getPkValue();
		String gwmc = myForm.getGwmc();
		String gwxzdm = myForm.getGwxzdm();
		String xqrs = myForm.getXqrs();
		String knsrs = myForm.getKnsrs();
		String gwms = myForm.getGwms();
		String gwryyq = myForm.getGwryyq();
		String bz = myForm.getBz();
//		String gwcjsx=myForm.getGwcjsx();
		String sql = "update xg_qgzx_gwxxsqb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,gwms=?,gwryyq=?,bz=? where gwdm=?";
		String[] input = {gwmc,gwxzdm,xqrs, knsrs, gwms, gwryyq, bz,pkValue};
		return dao.runUpdate(sql,input);
	}
	
	
	/**
	 * 获得用人部门List
	 * @return
	 */
	public List<HashMap<String, String>> getYrbm(QgzxGwglForm myForm) {
		String sql = " select distinct bmdm,bmmc from view_xg_qgzx_yrdwdmb a where bmmc<>'未确定' order by bmdm ";
		String[] outputValue = {"bmdm","bmmc"};
		return dao.getList(sql, new String[]{}, outputValue);
	}
	
	
	/**
	 * 获得最大的岗位代码
	 * @return
	 */
	public String getMaxGwdm() {
		String sql = " select max(to_number(substr(gwdm,10,4 ))) num from xg_qgzx_gwxxb ";
		return dao.getOneRs(sql, new String[]{}, "num");
	}
	
	
	/**
	 * 批量删除岗位信息
	 * @param pkValue
	 * @return
	 * @throws SQLException 
	 */
	public boolean gwxxSc(List<String[]> params) throws SQLException {
		String sql = " delete from xg_qgzx_gwxxb where gwdm=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 批量删除岗位信息
	 * @param pkValue
	 * @return
	 * @throws SQLException 
	 */
	public boolean gwsqSc(List<String[]> params) throws SQLException {
		String sql = " delete from xg_qgzx_gwxxsqb where gwdm=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 批量保存岗位信息
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean gwxxSave(List<String[]> params) throws SQLException {
		String sql = null;
			 sql = "insert into xg_qgzx_gwxxb(gwxh,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,gwyqryxg,bz,gwcjsx) select gwxh,? xn ,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,gwyqryxg,bz,gwcjsx from xg_qgzx_gwxxb where gwdm = ?";
		int[] result = dao.runBatch(sql,params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 在岗人员信息List
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String[] getRyxhList(QgzxGwglForm model) throws SQLException {
		String sql = " select xh||'!!@@!!'||sgsj||'!!@@!!'||sjly pkValue from xg_qgzx_xsgwxxb where gwdm = ? and zgzt = ? order by sgsj desc,xh ";
		String[] inputValue = {model.getPkValue(),model.getZgzt()};
		return dao.getArray(sql, inputValue, "pkValue");
	}
	
	
	/**
	 * 获得岗位学生信息
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getGwxsxx(QgzxGwglForm model) {
		String sql = "select a.sgsj,a.tgsj,a.zgzt,a.sjly,a.sqbh,b.*,(select count(1) from xg_qgzx_xsgwxxb where xh = ? and zgzt='zg' )qggws from xg_qgzx_xsgwxxb a left join view_xsbfxx b on a.xh = b.xh where a.xh = ? and a.gwdm = ? and a.zgzt = ? and rownum = 1";
		String[] inputValue = {model.getXh(),model.getXh(),model.getPkValue(),model.getZgzt()};
		return dao.getMapNotOut(sql, inputValue);
	}

	
	/**
	 * 获得学生信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getXsxx(QgzxGwglForm model) {
		String sql = "select a.*,(select count(1) from xg_qgzx_xsgwxxb where xh = ? and zgzt='zg' )qggws from view_xsbfxx a where a.xh = ? and rownum = 1";
		String[] inputValue = {model.getXh(),model.getXh()};
		String[] outputValue = {"xh","xm","bjmc","qggws","xb","nj","xymc","zymc","lxdh"};
		return dao.getMap(sql, inputValue, outputValue);
	}

	
	/**
	 * 批量保存人员信息
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean bcZjryxx(List<String[]> params) throws SQLException {
		String sql = " insert into xg_qgzx_new_xsgwxxb(gwdm,xh,sgsj,zgzt)values(?,?,to_char(sysdate,'yyyyMMdd'),'zg')";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * 批量删除人员信息
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean scRyxx(List<String[]> params) throws SQLException {
		String sql = " delete from xg_qgzx_new_xsgwxxb where gwdm = ? and xh = ? and zgzt = 'zg'";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * 批量保存退岗人员信息
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean bcTgryxx(List<String[]> params) throws SQLException {
		String sql = " update xg_qgzx_new_xsgwxxb set zgzt='tg',tgsj=to_char(sysdate,'yyyyMMdd'),tgyy=? where gwdm=? and xh = ? and sgsj = ?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @描述: 批量设置数据为初始化数据（未审核通过）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-30 下午02:21:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 */
	public boolean hySqsj(List<String[]> params) throws SQLException {
		String sql = " update XG_QGZX_XSGWSQB set shgw='-1' where sqbh=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	/**
	 * 根据学号和在岗状态获得岗位信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getGwxx(QgzxGwglForm model) {
		String sql = "select a.*,rownum r from (select a.sgsj,b.*,c.bmmc,d.gwxzmc from xg_qgzx_xsgwxxb a " +
				"left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm " +
				"left join view_xg_qgzx_yrdwdmb c on b.yrdwdm = c.bmdm " +
				"left join xg_qgzx_gwxzdmb d on b.gwxzdm = d.gwxzdm where a.xh = ? and a.zgzt = ? order by a.sgsj desc,b.xn,b.yrdwdm,b.gwdm,b.gwxzdm) a";
		String[] inputValue = {model.getXh(),model.getZgzt()};
		String[] outputValue = {"r","xn","bmmc","gwmc","gwxzmc","sgsj"};
		return dao.getList(sql, inputValue, outputValue);
	}


	/**
	 * 获得最后退岗时间
	 * @param model
	 * @return
	 */
	public String getZhtgsj(String[] inputValue) {
		String sql = " select max(tgsj) tgsj from xg_qgzx_xsgwxxb where gwdm = ? and xh = ? ";
		return dao.getOneRs(sql, inputValue, "tgsj");
	}

	

	/**
	 * 获得岗位学生学号
	 * @param xn
	 * @param xsgws
	 * @return
	 * @throws SQLException 
	 */
	public String[] getGwxsxh(String xn, String xsgws) throws SQLException {
		String sql = "select xh from (select xh,count(1) num from xg_qgzx_xsgwxxb where substr(gwdm,0,9) = ? and zgzt = 'zg' group by xh) where num >=to_number(?)";
		return dao.getArray(sql, new String[]{xn,xsgws}, "xh");
	}


	/**
	 * 获得岗位数
	 * @param gwdm
	 * @param xh
	 * @return
	 */
	public String getGws(String gwdm, String xh) {
		String sql = "select count(1) num from xg_qgzx_xsgwxxb c where exists" +
				"(select 1 from xg_qgzx_gwxxb a where exists" +
				"(select 1 from xg_qgzx_gwxxb b where b.gwdm = ? " +
				"and a.xn = b.xn)	and a.gwdm = c.gwdm	) and zgzt = 'zg' and xh = ?  ";
		return dao.getOneRs(sql, new String[]{gwdm,xh}, "num");
	}


	/**
	 * 人员是否被发放过酬金
	 * @param gwdm
	 * @param xh
	 * @return
	 */
	public boolean isUsed(String gwdm, String xh) {
		String sql = "select count(1) num from xg_qgzx_jcffb where gwdm = ? and xh = ? ";
		return "0".equalsIgnoreCase(dao.getOneRs(sql, new String[]{gwdm,xh}, "num"))?false:true;
	}


	/**
	 * 获得用人单位名称
	 * @param yrdwdm
	 * @return
	 */
	public String getYrdwmc(String yrdwdm) {
		String sql = "select bmmc from view_xg_qgzx_yrdwdmb where bmdm = ? ";
		return dao.getOneRs(sql, new String[]{yrdwdm}, "bmmc");
	}


	/**
	 * 保存增加岗位申请
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean bcZjGwsq(QgzxGwglForm model) throws Exception {
		String xn = Base.currXn;
		String yrbm = model.getUser().getUserDep();
		String gwmc = model.getGwmc();
		String gwxzdm = model.getGwxzdm();
		String xqrs = model.getXqrs();
		String knsrs = model.getKnsrs();
		String gwms = model.getGwms();
		String gwryyq = model.getGwryyq();
		String gwyqryxg = model.getGwyqryxg();
		String bz = model.getBz();
		String sqr = model.getUser().getUserName();
		String gwcjsx=model.getGwcjsx();
		String sql = "insert into xg_qgzx_gwxxsqb (xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,gwyqryxg,bz,sqsj,sqr,gwcjsx) values (?,?,?,?,?,?,?,?,?,?,to_char(sysdate,'yyyy-MM-dd hh:mi:ss'),?,?)";
		String[] input = {xn, yrbm, gwmc, gwxzdm, xqrs, knsrs, gwms, gwryyq,gwyqryxg, bz, sqr,gwcjsx};
		return dao.runUpdate(sql,input);
	}

	
	/**
	 * 岗位信息审核保存
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean gwxxshBc(List<String[]> params) throws SQLException {
		String sql = " update xg_qgzx_gwxxsqb set shyj=?,shzt=?,shsj=to_char(sysdate,'yyyy-MM-dd hh:mi:ss'),shr=? where gwdm=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}


	/**
	 * 插入正式表
	 * @param paramsByZs
	 * @return
	 * @throws SQLException
	 */
	public boolean insertByZs(List<String[]> paramsByZs) throws SQLException {
		String sql = " insert into xg_qgzx_gwxxb(gwxh,gwdm,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,bz,gwcjsx,gwyqryxg)" +
				"select ?,gwdm,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,bz,gwcjsx,gwyqryxg from xg_qgzx_gwxxsqb where gwdm = ?";
		int[] result = dao.runBatch(sql, paramsByZs);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * 用人单位岗位申请 自定义导出
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGwsqExportList(QgzxGwglForm myForm,User user) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		//User user = myForm.getUser();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "gwdm", "r", "xn", "yrdwmc", "gwmc","gwxzmc", "xqrs", "knsrs", "sqsj", "shzt" };
		sql.append(" select a.*,rownum r,case when a.shzt='通过'or a.shzt='不通过' then 'disabled' end as dis from view_xg_qgzx_gwxxsqb a where 1=1 ");
		//sql.append("select rownum r ,k.* from VIEW_NEW_DC_QGZX_GWSQ k where 1=1 ");
		// ====================SQL拼装 end================================
		//List<HashMap<String, String>> 
		
		return CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	/**
	 * 
	 * @描述:获取岗位序号
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-10-21 下午04:49:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String GetGwxh(String yrdwdm,String xn) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select ( case when length(gwxh)=1 then '00'||gwxh when "); 
		sql.append(" length(gwxh)=2 then '0'||gwxh else to_char(gwxh) end) gwxh");
		sql.append(" from (select (to_number(nvl(max(to_number(gwxh)),0))+1) gwxh from xg_qgzx_gwxxb");
		sql.append(" where yrdwdm=? and xn=?)");
		return dao.getOneRs(sql.toString(), new String[]{yrdwdm,xn}, "gwxh");
}
}
