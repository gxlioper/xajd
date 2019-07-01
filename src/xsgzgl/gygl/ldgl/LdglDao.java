package xsgzgl.gygl.ldgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.xgxt.base.dao.SuperDAO;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import common.newp.StringUtil;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xsgzgl.gygl.comm.GyglNewInit;

public class LdglDao extends DAO{
	
	public List<String[]> getLdglInfoList(LdglModel model) throws Exception{
		
		//高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
//		String[] outColist=new String[]{"lddm","ldmc","ldxb","ldcs","qsch","sfhlc","xqdm","yqdm","bz"};
		String[] outPutString=null;
		
		if(Base.xxdm.equals("10351")){ // 温州大学
			if(!"0".equals(GyglNewInit.XQBJ)&&!"0".equals(GyglNewInit.YQBJ)){//校区、园区
				outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","xqmc","yqmc","gyfdy","gyfdyxx","bz"};
			}else if(!"0".equals(GyglNewInit.XQBJ)){//校区
				outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","xqmc","gyfdy","gyfdyxx","bz"};
			}else if(!"0".equals(GyglNewInit.YQBJ)){//园区
				outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","yqmc","gyfdy","gyfdyxx","bz"};
			}else{
				outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","gyfdy","gyfdyxx","bz"};
			}
		}else if(Base.xxdm.equals("12861")){ // 浙江机电职业技术学院
			if(!"0".equals(GyglNewInit.XQBJ)&&!"0".equals(GyglNewInit.YQBJ)){//校区、园区
				outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","xqmc","yqmc","gyfdy","gyfdyxx","gyfdy_12861","gyfdyxx_12861","bz"};
			}else if(!"0".equals(GyglNewInit.XQBJ)){//校区
				outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","xqmc","gyfdy","gyfdyxx","gyfdy_12861","gyfdyxx_12861","bz"};
			}else if(!"0".equals(GyglNewInit.YQBJ)){//园区
				outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","yqmc","gyfdy","gyfdyxx","gyfdy_12861","gyfdyxx_12861","bz"};
			}else{
				outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","gyfdy","gyfdyxx","gyfdy_12861","gyfdyxx_12861","bz"};
			}
		}else{
			if(!"0".equals(GyglNewInit.XQBJ)&&!"0".equals(GyglNewInit.YQBJ)){//校区、园区
				outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","sfhlc","xqmc","yqmc","gyfdy","gyfdyxx","bz"};
			}else if(!"0".equals(GyglNewInit.XQBJ)){//校区
				outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","sfhlc","xqmc","gyfdy","gyfdyxx","bz"};
			}else if(!"0".equals(GyglNewInit.YQBJ)){//园区
				outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","sfhlc","yqmc","gyfdy","gyfdyxx","bz"};
			}else{
				outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","sfhlc","gyfdy","gyfdyxx","bz"};
			}
		}
		String tableName="(select a.*,(select xqmc from dm_zju_xq x where x.dm = a.xqdm) xqmc," +
				"(select yqmc from zxbz_ssyqdm x where x.yqdm = a.yqdm) yqmc," +
				"b.gyfdy,b.gyfdyxx from xg_gygl_new_ldxxb a left join " +
		"(select lddm,to_char(wm_concat(yhm||lxdh)) gyfdyxx,to_char(wm_concat(yhm)) gyfdy from (select a.lddm,b.xm yhm,b.lxdh from xg_gygl_new_gyfdyb a left join fdyxxb b on a.yhm=b.zgh) group by lddm) b on a.lddm=b.lddm)";

		if(Base.xxdm.equals("12861")){ // 浙江机电职业技术学院
			tableName="(select a.*,(select xqmc from dm_zju_xq x where x.dm = a.xqdm) xqmc," +
					"(select yqmc from zxbz_ssyqdm x where x.yqdm = a.yqdm) yqmc," +
					"b.gyfdy,b.gyfdyxx,c.gyfdy_12861,c.gyfdyxx_12861 from xg_gygl_new_ldxxb a left join " +
			" (select lddm,to_char(wm_concat(yhm||lxdh)) gyfdyxx,to_char(wm_concat(yhm)) gyfdy from (select a.lddm,b.xm yhm,b.lxdh from xg_gygl_new_gyfdyb a left join fdyxxb b on a.yhm=b.zgh) group by lddm) b on a.lddm=b.lddm " +
			" left join (select lddm,to_char(wm_concat(yhm||lxdh)) gyfdyxx_12861,to_char(wm_concat(yhm)) gyfdy_12861 from (select a.lddm,b.xm yhm,b.lxdh from ZJJD_XG_GYGL_NEW_GYFDYB a left join fdyxxb b on a.yhm=b.zgh) group by lddm) c on a.lddm=c.lddm " +
			")";
		}
		return CommonQueryDAO.commonQueryNotFy(tableName," where 1=1 "+searchTj+" order by lddm",inputV,outPutString,"");
	}
	/**
	 * 保存楼栋信息
	 * @param request
	 * @param model
	 * @param sqlArr
	 * @return
	 */
	public boolean saveLdxx(HttpServletRequest request,LdglModel model,String[] sqlArr){
		boolean b=false;
		try {
			CommDAO dao=new CommDAO();
			b=dao.saveArrDate(sqlArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 获取单个楼栋信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getLdxxOne(LdglModel model){
//		String sql="select * from xg_gygl_new_ldxxb where lddm=?";
		String sql="select a.*, "+
					 "(select max(1) from view_xg_gygl_new_cwxx x where x.lddm=a.lddm and (x.qsnj is not null or x.qsxydm is not null or x.nj is not null or x.xydm is not null or x.xh is not null)) mark, "+
					 "(select max(1) from view_xg_gygl_new_cwxx x where x.lddm=a.lddm and x.qsxb='男' and (x.qsnj is not null or x.qsxydm is not null or x.nj is not null or x.xydm is not null or x.xh is not null)) m_mark, "+
					 "(select max(1) from view_xg_gygl_new_cwxx x where x.lddm=a.lddm and x.qsxb='女' and (x.qsnj is not null or x.qsxydm is not null or x.nj is not null or x.xydm is not null or x.xh is not null)) w_mark "+
					 "from xg_gygl_new_ldxxb a where lddm=?";
		return getMapNotOut(sql, new String[]{model.getLddm()});
	}
	/**
	 * 获取楼栋各层统计信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getLdtjxx(LdglModel model){
//		String sql="select lddm,ch,qsxb,qss,cws*qss cwzs from ( "+
//					"select lddm,ch,qsxb,cws,count(1) qss from xg_gygl_new_qsxxb where lddm=? group by lddm,ch,qsxb,cws "+
//					") order by to_number(ch)";
		String sql=" select lddm,ch,qsxb,count(distinct qsh) qss,count(1) cwzs, " +
					"count(case when qsnj is not null or qsxydm is not null or nj is not null or " +
					"xydm is not null or xh is not null then 1 end) mark " +
					"from view_xg_gygl_new_cwxx where lddm=? group by lddm,ch,qsxb order by to_number(ch) DESC";
		return getListNotOut(sql, new String[]{model.getLddm()});
	}
	/**
	 * 修改楼栋信息
	 * @param request
	 * @param model
	 * @param sqlArr
	 * @return
	 */
	public boolean updateLdxx(HttpServletRequest request,LdglModel model,String[] sqlArr){
		boolean b=false;
		try {
			CommDAO dao=new CommDAO();
			b=dao.saveArrDate(sqlArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 删除楼栋信息
	 * @param request
	 * @param model
	 * @return
	 */
	public boolean deleteLdxx(HttpServletRequest request,LdglModel model){
		StringBuffer pkstr=new StringBuffer();
		String[] pks=model.getCheckVal();
		for(int i=0;i<pks.length;i++){
			pkstr.append("'");
			pkstr.append(pks[i]);
			pkstr.append("',");
		}
		//判断楼栋是否分配住人，分配或住人则不可删除
		String lddm=pkstr.substring(0,pkstr.length()-1);
//		String sql="select count(1) num from xg_gygl_new_cwxxb where (xh is not null or xydm is not null) and lddm in("+lddm+")";
		String sql="select (select count(1) num from xg_gygl_new_cwxxb where (xh is not null or xydm is not null) and lddm in("+lddm+")) "+
					" num from dual";
		String num=getOneRs(sql, new String[]{}, "num");
		if(!"0".equals(num)){
			return false;
		}
		
		ArrayList<String> sqls=new ArrayList<String>();
		sqls.add("delete from xg_gygl_new_ldxxb where lddm in("+lddm+")");//楼栋
		sqls.add("delete from xg_gygl_new_qsxxb where lddm in("+lddm+")");//寝室
		sqls.add("delete from xg_gygl_new_cwxxb where lddm in("+lddm+")");//床位
		sqls.add("delete from xg_gygl_new_gyfdyb where lddm in("+lddm+")");//辅导员
		
		try {
			CommDAO dao=new CommDAO();
			return dao.saveArrDate(sqls.toArray(new String[]{}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 查询用户
	 * @return
	 */
	public List<String[]> queryYh(LdglForm myForm){
		String[]colList = {"yhm","xm" ,"bmmc"};
		
		List<String[]> list = new ArrayList<String[]>();
		
		StringBuilder sql = new StringBuilder();
		
		
		sql.append(" select rownum r,a.* from (select a.yhm,a.xm,b.bmmc,a.szbm bmdm,decode(c.xb,'1','男','2','女',c.xb)xb")
		.append(" from yhb a left join ")
		.append(" zxbz_xxbmdm b on a.szbm=b.bmdm left join fdyxxb c on a.yhm=c.zgh)a ");
		
		//String[] colLikeList = new String[]{"yhm", "xm"};
		String yhm=myForm.getYhm();
		String yhmsql="";
		if(!StringUtil.isNull(yhm)){
			List<String> yhmlist = new ArrayList<String>();
			String[] yhmstr=yhm.split(" ");
			for (int i=0;i<yhmstr.length;i++){
				yhmlist.add(" yhm like '%"+yhmstr[i]+"%'");
			}
			yhmsql="and ("+StringUtils.join(yhmlist,"or")+")";
		}
		String xm=myForm.getXm();
		String xmsql="";
		if(!StringUtil.isNull(xm)){
			List<String> xmlist = new ArrayList<String>();
			String[] xmstr=xm.split(" ");
			for (int i=0;i<xmstr.length;i++){
				xmlist.add(" xm like '%"+xmstr[i]+"%'");
			}
			xmsql="and ("+StringUtils.join(xmlist,"or")+")";
		}
		String[] columList = new String[]{"bmdm","xb"};
		
		MakeQuery makeQuery = new MakeQuery();
		
		try {
			makeQuery.makeQuery(columList, new String[]{}, myForm);
			String[] inputs = makeQuery.getInputList();

			sql.append(makeQuery.getQueryString());
			
			if(!StringUtil.isNull(yhmsql)){
				sql.append(yhmsql);
			}
			if(!StringUtil.isNull(xmsql)){
				sql.append(xmsql);
			}
			
			list = CommonQueryDAO.commonQuery(sql.toString(), "", inputs , colList, myForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 保存辅导员分配
	 * @param yhs
	 * @param lds
	 * @return
	 */
	public boolean saveFdyFp(List<String[]> params, List<String[]> delParams){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String sql = "insert into xg_gygl_new_gyfdyb(yhm,lddm) values(?,?)";
		String delSql = "delete xg_gygl_new_gyfdyb where lddm=?";
		
		try {
			flag = dao.checkBatchResult(dao.runBatch(delSql, delParams));
			
			if(flag){
				int[] rs = dao.runBatch(sql, params);
				flag = dao.checkBatchResult(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 保存辅导员分配
	 * @param yhs
	 * @param lds
	 * @return
	 */
	public boolean saveFdyFp_12861(List<String[]> params, List<String[]> delParams){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String sql = "insert into ZJJD_XG_GYGL_NEW_GYFDYB(yhm,lddm) values(?,?)";
		String delSql = "delete ZJJD_XG_GYGL_NEW_GYFDYB where lddm=?";
		
		try {
			flag = dao.checkBatchResult(dao.runBatch(delSql, delParams));
			
			if(flag){
				int[] rs = dao.runBatch(sql, params);
				flag = dao.checkBatchResult(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 获取楼栋寝室列表
	 * @param form
	 * @return
	 */
	public List<HashMap<String,String>> getLdqsxxList(LdglForm form){
		String sql="select qsh,ch,qsxb,sfbz,chmc,cws from view_xg_gygl_new_qsxx where lddm=? order by to_number(ch) desc,qsh";
		return this.getListNotOut(sql, new String[]{form.getLddm()});
	}
	
	/**
	 * 获取楼栋中层中最大的寝室数
	 * @param form
	 * @return
	 */
	public String getChMaxQss(LdglForm form){
		String sql="select max(qss) max_qss from (select ch,count(1) qss from xg_gygl_new_qsxxb where lddm=? group by ch)";
		String max_qss=this.getOneRs(sql, new String[]{form.getLddm()}, "max_qss");
		return max_qss;
	}
	
	/**
	 * 保存楼栋寝室信息
	 * @param sqlArr
	 * @return
	 */
	public boolean saveLdQsxx(String[] sqlArr){
		boolean b=false;
		try {
			CommDAO dao=new CommDAO();
			b=dao.saveArrDate(sqlArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 检验楼栋寝室信息是否可以修改
	 * @param form
	 * @return
	 */
	public boolean checkLdQsxxSfkxg(LdglForm form){
		String sql="select count(1) num from xg_gygl_new_qsxxb a left join xg_gygl_new_cwxxb b on a.lddm=b.lddm and a.qsh=b.qsh "+
					"where a.lddm=? and (a.xydm is not null or a.nj is not null or b.xydm is not null or b.nj is not null " +
					"or b.bjdm is not null or b.xh is not null)";
		String num=this.getOneRs(sql, new String[]{form.getLddm()}, "num");
		boolean b=false;
		if("0".equals(num)){
			b=true;
		}
		return b;
	}
	
	/**
	 * 楼栋信息管理自定义导出
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getLdglInfoExportList(LdglModel model) throws Exception{
		
		//高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
//		String[] outColist=new String[]{"lddm","ldmc","ldxb","ldcs","qsch","sfhlc","xqdm","yqdm","bz"};
		String[] outPutString=null;
		
		if(!"0".equals(GyglNewInit.XQBJ)&&!"0".equals(GyglNewInit.YQBJ)){//校区、园区
			outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","sfhlc","xqmc","yqmc","gyfdy","bz"};
		}else if(!"0".equals(GyglNewInit.XQBJ)){//校区
			outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","sfhlc","xqmc","gyfdy","bz"};
		}else if(!"0".equals(GyglNewInit.YQBJ)){//园区
			outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","sfhlc","yqmc","gyfdy","bz"};
		}else{
			outPutString =new String[]{"lddm","ldmc","ldxb","ldcs","qsch","sfhlc","gyfdy","bz"};
		}
		

		/*String tableName="(select a.*,(select xqmc from dm_zju_xq x where x.dm = a.xqdm) xqmc," +
				"(select yqmc from zxbz_ssyqdm x where x.yqdm = a.yqdm) yqmc," +
				"b.gyfdy from xg_gygl_new_ldxxb a left join " +
		"(select lddm,to_char(wm_concat(yhm)) gyfdy from xg_gygl_new_gyfdyb group by lddm) b on a.lddm=b.lddm)";*/
		
		String tableName = "(select * from VIEW_NEW_DC_GYGL_LDXXGL) ";
		
		//CommonQueryDAO.
		return CommonQueryDAO.commonQueryExportNotFy(tableName," where 1=1 "+searchTj+" order by lddm",inputV,outPutString,"");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存楼栋保存分配,采用批量执行的方法,删除和插入合在一起进行，以免出现删除成功，插入失败的情况
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-25 下午02:00:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ldglform
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public int[] saveLdfp(String[] lddmArray,String[] yhms) throws Exception{
		List<String> sqlArray = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from xg_gygl_new_gyfdyb where lddm in(");
		for (int i = 0; i < lddmArray.length; i++) {
			sql.append("'"+lddmArray[i]+"'");
			if(i != lddmArray.length-1){
				sql.append(",");
			}
		}
		sql.append(") and");
		sql.append(" yhm in(");
		for (int i = 0; i < yhms.length; i++) {
			sql.append("'"+yhms[i]+"'");
			if(i != yhms.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		sqlArray.add(sql.toString());
		sql.setLength(0);
		for (int i = 0; i < yhms.length; i++) {
			for (int j = 0; j < lddmArray.length; j++) {
				sql.append(" insert into xg_gygl_new_gyfdyb(yhm,lddm)values('"+yhms[i]+"','"+lddmArray[j]+"')");
				sqlArray.add(sql.toString());
				sql.setLength(0);
			}
		}
		
		return new DAO().runBatch(sqlArray.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 撤销
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-25 下午03:59:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddmArray
	 * @param yhms
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelFp(String lddm,String[] yhms) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" delete from xg_gygl_new_gyfdyb where lddm = ? and yhm in(");
		paraList.add(lddm);
		for (int i = 0; i < yhms.length; i++) {
			sql.append("?");
			if(i != yhms.length-1){
				sql.append(",");
			}
			paraList.add(yhms[i]);
		}
		sql.append(" )");
		return new DAO().runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}
	
}
