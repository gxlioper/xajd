package xsgzgl.gygl.gyglry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.gygl.comm.GyglNewDAO;
import xsgzgl.gygl.comm.GyglNewInit;
import xsgzgl.gygl.comm.GyglNewService;

public class GyglryDao extends GyglNewDAO{
	DAO dao = DAO.getInstance();
	/**
	 * 获取公寓管理人员信息列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGyglryList(GyglryForm model,HttpServletRequest request,User user) throws Exception{
		//高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员
		String searchTjQx;
		
		StringBuilder sql = new StringBuilder();
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
			sql.append("select rownum r,a.lddm||'!$$!'||a.ch||'!$$!'||a.qsh pkValue,a.* from xg_view_gygl_new_gyglryb a  where 1=1 ");
		}else{//用户非公寓辅导员，可能是学校管理员，可能是学院管理员
			searchTjQx = SearchService.getSearchTjByUser(user, "b", "xydm", "bjdm");
			if(Base.xxdm.equals("14008")){//重庆三峡个性化
				sql.append(" select d.* from (select rownum r,a.lddm||'!$$!'||a.ch||'!$$!'||a.qsh pkValue,a.* from xg_view_gygl_new_gyglryb a ");
				sql.append(" where exists (select 1 from view_xg_gygl_new_cwxx b where a.lddm = b.lddm and a.qsh = b.qsh and b.xh is not null");
				sql.append(searchTjQx);
				sql.append(")) d");
			}else{		
				sql.append("select rownum r,a.lddm||'!$$!'||a.ch||'!$$!'||a.qsh pkValue,a.* from xg_view_gygl_new_gyglryb a  where 1=1 ");
			}
		}
		String[] outPutString=new String[]{};
		if("10466".equals(Base.xxdm)){//河南农业大学
			outPutString=new String[]{"pkValue","ldmc","chmc","qsh","gllx","xb","xh","xm","sjhm","bz"};				
		}else {
			outPutString=new String[]{"pkValue","ldmc","chmc","qsh","gllx","xb","xh","xm","sjhm","qsdh","bz"};				
		}
		return CommonQueryDAO.commonQuery(sql.toString(),searchTj,inputV,outPutString,model);
	}
	
	/**
	 * 获取入住学生信息
	 * @param model
	 * @return
	 */
	public List<String[]> getRzxsxxList(GyglryForm model) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();
		
//		sql.append("select rownum r,a.* from (select a.xh pkValue,a.*,b.xm," +
//				"(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis "+
//				"from view_xg_gygl_new_cwxx a inner join view_xsjbxx b on a.xh=b.xh order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where " +
//				"lddm=? ");
		
		sql.append("select rownum r,a.* from (select a.xh pkValue,a.*,b.xm," +
				"(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis "+
				"from view_xg_gygl_new_cwxx a inner join "+Base.xsxxb+" b on a.xh=b.xh order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where " +
				"lddm=? ");
		
		ArrayList<String> inputV=new ArrayList<String>();
		inputV.add(model.getLddm());
		
		if(!Base.isNull(model.getCh())){
			sql.append(" and ch=? ");
			inputV.add(model.getCh());
		}
		if(!Base.isNull(model.getQsh())){
			sql.append(" and qsh=? ");
			inputV.add(model.getQsh());
		}
		if(!Base.isNull(model.getXm())){
			sql.append(" and ( xh like '%'||?||'%' or xm like '%'||?||'%')");
			inputV.add(model.getXm());
			inputV.add(model.getXm());
		}
		String[] output = new String[]{"pkValue", "dis", "ldmc", "qsh", "cwh","qsxb", "nj", "xymc", "bjmc","xh", "xm","sfbl"};
		if("xydm".equals(GyglNewInit.CWFPDX)){
			output = new String[]{"pkValue", "dis", "ldmc", "qsh", "cwh","qsxb", "nj", "xymc", "xh", "xm","sfbl"};
		}
		try {
			list = CommonQueryDAO.commonQuery(sql.toString(), "" , inputV.toArray(new String[]{}), output, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取层号列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getChList(GyglryForm model){
		String sql="select distinct ch,(case when ch='#' then '#' when to_number(ch)>-1 then ch else 'B'||abs(ch) end) chmc " +
				"from xg_gygl_new_qsxxb where lddm=? order by to_number(ch)";
		return dao.getListNotOut(sql, new String[]{model.getLddm()});
	}
	
	/**
	 * 获取寝室号列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getQshList(GyglryForm model){
		String[] input=null;
		String sql="select qsh from xg_gygl_new_qsxxb where lddm=?";
		if(!Base.isNull(model.getCh())){
			sql+=" and ch=?";
			input=new String[]{model.getLddm(),model.getCh()};
		}else{
			input=new String[]{model.getLddm()};
		}
		sql+=" order by qsh";
		return dao.getListNotOut(sql, input);
	}
	/**
	 * 公寓管理人员分配
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String gyglryFp(GyglryForm model,Boolean flag) throws Exception{
		String lddm=model.getLddm();
		String ch=Base.isNull(model.getFp_ch())?"#":model.getFp_ch();
		String qsh=Base.isNull(model.getFp_qsh())?"#":model.getFp_qsh();
		String old_xh=model.getOld_xh();
		
		List<String> sqls=new ArrayList<String>();
		sqls.add(getGYglryTsyqUpdateSql(model));
		String sql=null;
		if(flag||"gx".equals(model.getCzfs())){//当楼长、层长人数为一人或更换寝室长或操作方式为更新时则直接替换
			sql="select xh from xg_gygl_new_gyglryb where nvl(rzzt,' ')<>'离任' and lddm=? and ch=? and qsh=?";
			String[] xhs=dao.getArray(sql, new String[]{lddm,ch,qsh}, "xh");
			if(xhs==null||xhs.length==0){
				sqls.add(getGyglryInsertSql(model));
			}else {
				if(xhs.length==1){
					old_xh=xhs[0];
				}
				sqls.add("update xg_gygl_new_gyglryb set rzzt='离任',rzjsrq='"+model.getRzksrq()+"' where " +
						"nvl(rzzt,' ')<>'离任' and lddm='"+lddm+"' and ch='"+ch+"' and qsh='"+qsh+"' and xh='"+old_xh+"'");
				sqls.add(getGyglryInsertSql(model));
			}
			
		}else{
			sqls.add(getGyglryInsertSql(model));
		}
		CommDAO dao=new CommDAO();
		boolean b=dao.saveArrDate(sqls.toArray(new String[]{}));
		if(b){
			return "操作成功！";
		}else{
			return "操作失败！";
		}
	}
	
	/**
	 * 获取公寓管理人员插入sql
	 * @param model
	 * @return
	 */
	private String getGyglryInsertSql(GyglryForm model){
		String lddm=model.getLddm();
		String ch=Base.isNull(model.getFp_ch())?"#":model.getFp_ch();
		String qsh=Base.isNull(model.getFp_qsh())?"#":model.getFp_qsh();
		String lxdh=Base.chgNull(model.getLxdh(), "", 0);
		String bz=Base.chgNull(model.getBz(), "", 0);
		String sql="insert into xg_gygl_new_gyglryb(xh,lddm,ch,qsh,rzzt,rzksrq,lxdh,bz) " +
				"values('"+model.getXh()+"','"+lddm+"','"+ch+"','"+qsh+"','在任','"+model.getRzksrq()+"'," +
						"'"+lxdh+"','"+bz+"')";
		return sql;
	}
	
	/**
	 * 过去公寓管理人员退宿或异动的修改语句
	 * @param model
	 * @return
	 */
	private String getGYglryTsyqUpdateSql(GyglryForm model){
		String sql="";
		if(Base.isNull(model.getFp_ch())){//楼长
			sql="update xg_gygl_new_gyglryb a set rzzt='离任' where rzzt='在任' and " +
					"not exists (select 1 from xg_gygl_new_cwxxb b where a.lddm=b.lddm and a.xh=b.xh)";
		}else if(Base.isNull(model.getFp_qsh())){//层长
			sql="update xg_gygl_new_gyglryb a set rzzt='离任' where rzzt='在任' and ch<>'#' and " +
					"not exists (select 1 from xg_gygl_new_cwxxb b left join xg_gygl_new_qsxxb c on b.lddm=c.lddm and b.qsh=c.qsh " +
					"where a.lddm=c.lddm and a.xh=b.xh)";
		}else{//寝室长
			sql="update xg_gygl_new_gyglryb a set rzzt='离任' where rzzt='在任' and ch<>'#' and qsh<>'#' and " +
					"not exists (select 1 from xg_gygl_new_cwxxb b where a.lddm=b.lddm and a.qsh=b.qsh and a.xh=b.xh)";
		}
		return sql;
	}
	
	/**
	 * 公寓管理人员取消分配
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String gyglryQxfp(GyglryForm model) throws Exception{
		String lddm=model.getLddm();
		String ch=Base.isNull(model.getFp_ch())?"#":model.getFp_ch();
		String qsh=Base.isNull(model.getFp_qsh())?"#":model.getFp_qsh();
		String old_xh=model.getOld_xh();
		
		String sql="update xg_gygl_new_gyglryb set rzzt='离任',rzjsrq=? where " +
		"nvl(rzzt,' ')<>'离任' and lddm=? and ch=? and qsh=? and xh=?";
		boolean b=dao.runUpdate(sql,new String[]{model.getRzjsrq(),lddm,ch,qsh,old_xh});
		dao.runUpdate(getGYglryTsyqUpdateSql(model), new String[]{});
		if(b){
			return "操作成功！";
		}else{
			return "操作失败！";
		}
	}

	/**
	 * 寝室长维护自定义导出设置
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getGyglryExportList(GyglryForm model,HttpServletRequest request,User user) throws Exception{
		//高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		String[] outPutString=new String[]{"pkValue","ldmc","chmc","qsh","gllx","xb","xh","xm","lxdh","qsdh","bz"};
		String sql="select rownum r,a.lddm||'!$$!'||a.ch||'!$$!'||a.qsh pkValue,a.* from xg_view_gygl_new_gyglryb a where 1=1 ";
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员
		String searchTjQx;
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
			searchTj+=searchTjQx;
		}else{//用户非公寓辅导员，可能是学校管理员，可能是学院管理员
			searchTjQx = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
			if(Base.xxdm.equals("14008")){//重庆三峡个性化
				searchTjQx = SearchService.getSearchTjByUser(user, "b", "xydm", "bjdm");
				StringBuilder sqll = new StringBuilder();
				sqll.append(" select d.* from (select rownum r,a.lddm||'!$$!'||a.ch||'!$$!'||a.qsh pkValue,a.* from xg_view_gygl_new_gyglryb a ");
				sqll.append(" where exists (select 1 from view_xg_gygl_new_cwxx b where a.lddm = b.lddm and a.qsh = b.qsh and b.xh is not null");
				sqll.append(searchTjQx);
				sqll.append(")) d");
				sql = sqll.toString();
			}else{
				searchTj+=searchTjQx;
			}
		}
		
		return CommonQueryDAO.commonQueryforExportList(sql,searchTj,inputV,outPutString,model);
	}
	
	/**
	 * 获取公寓管理人员楼长信息列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLzInfoList(String lddm) throws Exception{
		String sql="select * from xg_gygl_new_gyglryb a  where rzzt='在任' and lddm=? and ch='#' and qsh='#'";
		return dao.getListNotOut(sql, new String[]{lddm});
	}
	
	/**
	 * 获取公寓管理人员层长信息列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCzInfoList(String lddm,String ch) throws Exception{
		String sql="select * from xg_gygl_new_gyglryb a  where rzzt='在任' and lddm=? and ch=? and qsh='#'";
		return dao.getListNotOut(sql, new String[]{lddm,ch});
	}
	
	/** 
	 * @描述:获取公寓管理人员寝室长信息
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-31 上午10:48:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @param qsh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getQszInfo(String lddm,String qsh) {
		String sql="select b.sjhm qszlxdh,b.xm qszxm from xg_gygl_new_gyglryb a left join view_xsjbxx b on a.xh=b.xh  where a.rzzt='在任' and a.lddm=? and a.qsh=? ";
		return dao.getMapNotOut(sql, new String[]{lddm,qsh});
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @描述: 插入数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-19 下午04:26:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveDrData(List<String[]> params) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_gygl_new_gyglryb(xh,lddm,ch,qsh,rzzt,rzksrq,bz,lxdh) values(?,?,?,?,'在任',?,?,?)");
		return dao.runBatchBoolean(sql.toString(), params);
	}
	
	/**
	 * 
	 * @描述: 更新数据(将原来存在的数据覆盖掉)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-24 下午01:44:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveDrDataForUpdate(List<String[]> params) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_gygl_new_gyglryb set rzzt='离任',rzjsrq= to_char(sysdate,'yyyy-mm-dd') where  lddm = ? and ch = ? and qsh = ?");
		return dao.runBatchBoolean(sql.toString(), params);
	}
	
	/**
	 * 
	 * @描述: 验证是否重复
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-19 下午05:26:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIfExist(String xh,String lddm,String qsh,String ch){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_gygl_new_gyglryb where xh = ? and lddm = ? and qsh = ? and ch = ?");
		String rs = dao.getOneRs(sql.toString(), new String[]{xh,lddm,qsh,ch}, "cnt");
		return "0".equals(rs) ? false :true;
	}
	
	/**
	 * 
	 * @描述: 学号是否存在
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-23 下午02:23:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkXhIsExist(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xsxxb t where t.xh = ? and t.sfzx = '是'");
		String rs = dao.getOneRs(sql.toString(),new String[]{xh}, "cnt");
		return "0".equals(rs) ? false : true;
	}
	
	/**
	 * 
	 * @描述: 验证楼栋名称是否存在
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-23 下午03:27:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public String  checkLdmcIfExist(String ldmc,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct lddm from view_xg_gygl_new_cwxx where ldmc = ? and xh = ?");
		return  dao.getOneRs(sql.toString(), new String[]{ldmc,xh}, "lddm");
	}
	
	/**
	 * 
	 * @描述: 验证层号是否存在
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-23 下午03:42:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @param ch
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkChIfExist(String lddm,String ch,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from view_xg_gygl_new_cwxx where lddm = ? and ch = ? and xh =?");
		String rs = dao.getOneRs(sql.toString(), new String[]{lddm,ch,xh}, "cnt");
		return "0".equals(rs) ? false : true;
	}
	
	/**
	 * 
	 * @描述: 寝室号是否存在
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-23 下午05:01:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @param ch
	 * @param qsh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkQshIfExist(String lddm,String ch,String qsh,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from view_xg_gygl_new_cwxx where lddm = ? and ch = ? and qsh = ? and xh =?");
		String rs = dao.getOneRs(sql.toString(), new String[]{lddm,ch,qsh,xh}, "cnt");
		return "0".equals(rs)? false:true;
	}
	
}
