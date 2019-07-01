package xsgzgl.gygl.wmqs.gzdx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.ahjg.CommanDAO;
import xsgzgl.gygl.comm.GyglNewDAO;
import xsgzgl.gygl.comm.GyglNewInit;
import xsgzgl.gygl.comm.GyglNewService;

public class GyglWmqsDao extends GyglNewDAO{
	
	/**
	 * 获取文明寝室个数维护信息列表
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> getWmqsgswhInfoList(GyglWmqsForm model,HttpServletRequest request) throws Exception{
//		DAO dao = DAO.getInstance();
		String wmqsbfb=GyglNewInit.gzdx_wmqsbfb.replace("%", "");
		String sql="select d.xydm,d.xymc,d.qsgs,round(qsgs*("+wmqsbfb+"/100)) wmqsbfbgs,nvl(e.wmqsgs,round(qsgs*("+wmqsbfb+"/100))) wmqsgs from "+
			 "( "+
			 "select c.xydm,c.xymc,count(1) qsgs "+
			 "from view_xg_gygl_new_qsxx a  "+
			 "left join xg_gygl_new_gyglryb b  "+
			 "on a.LDDM=b.lddm and a.QSH=b.qsh and b.rzzt='在任' "+
			 "left join view_xsjbxx c on b.xh=c.xh where c.xydm is not null "+
			 "group by c.xydm,c.xymc "+
			 ") d left join xg_gygl_new_gzdx_wmqsgsb e on d.xydm=e.xydm";
		String[] colList=new String[]{"xydm","xymc","qsgs","wmqsbfbgs","wmqsgs"};
		return this.commonQueryNotFy(sql,"" ,new String[]{}, colList, model);
	}
	
	/**
	 * 保存文明寝室个数维护
	 * @param model
	 * @return
	 */
	public boolean saveWmqsgs(GyglWmqsForm model){
		CommDAO dao=new CommDAO();
		boolean b=false;
		ArrayList<String> sqls=new ArrayList<String>();
		String[] xydms=model.getXydms();
		String[] wmqsgs=model.getWmqsgss();
		if(xydms==null||wmqsgs==null||xydms.length==0||xydms.length!=wmqsgs.length){
			return false;
		}
		sqls.add("delete from xg_gygl_new_gzdx_wmqsgsb where nd='"+Base.currNd+"'");
		for(int i=0;i<xydms.length;i++){
			sqls.add("insert into xg_gygl_new_gzdx_wmqsgsb(nd,xydm,wmqsgs) values('"+Base.currNd+"','"+xydms[i]+"','"+wmqsgs[i]+"')");
		}
		try {
			b= dao.saveArrDate(sqls.toArray(new String[]{}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 保存文明寝室申请信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveWmqssqxx(GyglWmqsForm m) throws Exception{
		boolean b=false;
		DAO dao=new DAO();
		//计算一下卫生平均分
		String sql="select round(avg(wsffs),2) wspjf from gygl_wsjc_wsfwhb where nd=? and jcld=? and jcqs=?";
		String wspjf=dao.getOneRs(sql, new String[]{Base.currNd,m.getLddm(),m.getQsh()}, "wspjf");
		//查询一下是否已申请
		sql="select count(1) num from xg_gygl_new_gzdx_wmqssqb where nd=? and lddm=? and qsh=?";
		String num=dao.getOneRs(sql, new String[]{Base.currNd,m.getLddm(),m.getQsh()}, "num");
		
		if("0".equals(num)){//没有记录直接插入
			sql="insert into xg_gygl_new_gzdx_wmqssqb(nd,lddm,qsh,wspjf,sqsm,sqr,sqsj) values(?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
			b=dao.runUpdate(sql, new String[]{Base.currNd,m.getLddm(),m.getQsh(),wspjf,m.getSqsm(),m.getSqr()});
		}else{//记录已存在进行更新
			sql="update xg_gygl_new_gzdx_wmqssqb set wspjf=?,sqsm=? where nd=? and lddm=? and qsh=?";
			b=dao.runUpdate(sql, new String[]{wspjf,m.getSqsm(),Base.currNd,m.getLddm(),m.getQsh()});
		}
		return b;
	}
	
	/**
	 * 获取文明寝室申请信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getWmqssqxx(GyglWmqsForm model,HttpServletRequest request){
		DAO dao=new DAO();
		String sql;
		if(!"teacher".equals(model.getUserType())){//如果是学生，首先过去对于的楼栋和寝室
			sql="select * from xg_gygl_new_gyglryb where qsh!='#' and rzzt='在任' and xh=?";
			HashMap<String,String> r=dao.getMap(sql, new String[]{model.getSqr()}, new String[]{"lddm","qsh"});
			model.setPkValue(r.get("lddm")+r.get("qsh"));
		}
		sql="select round(avg(wsffs),2) wspjf from gygl_wsjc_wsfwhb where nd=? and jcld||jcqs=?";
		String wspjf=dao.getOneRs(sql, new String[]{Base.currNd,model.getPkValue()}, "wspjf");
		sql="select a.*,b.sqsm," +
		" (select count(1) from xg_gygl_new_cwxxb x where a.lddm=x.lddm and a.qsh=x.qsh and a.xh is not null) qsrs " +
		" from view_xg_gygl_new_cwxx a left join xg_gygl_new_gzdx_wmqssqb b " +
		"on a.lddm=b.lddm and a.qsh=b.qsh where a.lddm||a.qsh=?";
		HashMap<String,String> rs= dao.getMapNotOut(sql, new String[]{model.getPkValue()});
		if(rs!=null&&!rs.isEmpty()){
			rs.put("wspjf", wspjf);
		}
		
		//获取设置对应寝室的学生列表
		sql="select a.* from view_xsjbxx a ,xg_gygl_new_cwxxb b where a.xh=b.xh and b.lddm||b.qsh=?";
		List<HashMap<String,String>> xsList=dao.getListNotOut(sql, new String[]{model.getPkValue()});
		request.setAttribute("xsList", xsList);
		return rs;
	}
	
	/**
	 * 获取文明寝室审核列表
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ArrayList<String[]> getWmqsshList(GyglWmqsForm model,HttpServletRequest request,User user) throws Exception{

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		//权限控制
		String searchTjQx="";
		
		SearchService searchService=new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "","xydm", null); 	//学院用户
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员
		
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
			request.setAttribute("shtype", "fdy");
		}else{//用户非公寓辅导员，可能是学校管理员，可能是学院管理员
			searchTjQx = searchTjByUser;
			request.setAttribute("shtype", "xx");
		}
		
		String sql="select rownum r,a.lddm||'!!one!!'||a.qsh pk,a.* from view_xg_gygl_new_gzdx_wmqssq a where 1=1 ";
				
		String[] colList=new String[]{"pk","ldmc","qsh","qsrs","wspjf","sqr","fdyshzt","xxshzt"};
		return this.commonQuery(sql, searchTj+searchTjQx, inputV, colList, model);
	}
	
	/**
	 * 保存文明寝室审核信息
	 * @param myForm
	 * @return
	 */
	public boolean saveWmqsshxx(GyglWmqsForm myForm,HttpServletRequest request,User user) throws Exception{
		boolean b=false;
		String[] pk=myForm.getPrimarykey_checkVal();
		if(pk==null||pk.length==0){
			return b;
		}
		//权限控制
		String type="";
		
		SearchService searchService=new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "","xydm", null); 	//学院用户
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员
				
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy.trim())){//用户为公寓辅导员
			type = "fdy";
		}else{//用户非公寓辅导员，可能是学校管理员，可能是学院管理员
			type = "xx";
		}		
		
		if("xx".equals(type)&&"通过".equals(myForm.getShzt())){
			DAO dao=new DAO();
			String wmqsbfb=GyglNewInit.gzdx_wmqsbfb.replace("%", "");//文明寝室百分比
			String sql="select d.xydm,d.xymc,d.qsgs,round(qsgs*("+wmqsbfb+"/100)) wmqsbfbgs,nvl(e.wmqsgs,round(qsgs*("+wmqsbfb+"/100))) wmqsgs from "+
			"( "+
			"select c.xydm,c.xymc,count(1) qsgs "+
			"from view_xg_gygl_new_qsxx a  "+
			"left join xg_gygl_new_gyglryb b  "+
			"on a.LDDM=b.lddm and a.QSH=b.qsh and b.rzzt='在任' "+
			"left join view_xsjbxx c on b.xh=c.xh where xydm is not null "+
			"group by c.xydm,c.xymc "+
			") d left join xg_gygl_new_gzdx_wmqsgsb e on d.xydm=e.xydm " +
			"where d.xydm in(select xydm from view_xg_gygl_new_gzdx_wmqssq where lddm||'!!one!!'||qsh=?)";
			String wmqsgs=dao.getOneRs(sql, new String[]{pk[0]}, "wmqsgs");//文明寝室个数
			if(wmqsgs==null||"".equals(wmqsgs)){
				request.setAttribute("message", "该寝室没有分配寝室长！");
				return false;
			}
//			sql="select count(1) wmqstgs from view_xg_gygl_new_gzdx_wmqssq a where a.xxshzt='通过' and "+
//			"a.xydm in(select xydm from view_xg_gygl_new_gzdx_wmqssq where lddm||'!!one!!'||qsh=?)";
			sql="select count(1) wmqstgs from view_xg_gygl_new_gzdx_wmqssq a where a.xxshzt='通过' and "+
				"exists (select 1 from view_xg_gygl_new_gzdx_wmqssq x where x.lddm||'!!one!!'||x.qsh=? and x.xydm=a.xydm)";
			String wmqstgs=dao.getOneRs(sql, new String[]{pk[0]}, "wmqstgs");
			if(Integer.parseInt(wmqsgs)-Integer.parseInt(wmqstgs)<=0){
				request.setAttribute("message", "该寝室所属学院文明寝室审核通过数已达到设定值！");
				return false;
			}
			
		}
//		String sql="update xg_gygl_new_gzdx_wmqssqb set "+type+"shzt=?,"+type+"shsj=?,"+type+"shbz=? where nd=? and lddm||qsh=?";
		ArrayList<String> sqls=new ArrayList<String>();
		for(int i=0;i<pk.length;i++){
			if("fdy".equals(type)){//辅导员
				sqls.add("update xg_gygl_new_gzdx_wmqssqb set fdyshzt='"+myForm.getShzt()+"',fdyshsj=to_char(sysdate,'yyyy-mm-dd'),"+
						"fdyshbz='"+myForm.getShbz()+"',fdyshr='"+user.getUserName()+"' where nd='"+Base.currNd+"' and lddm||'!!one!!'||qsh='"+pk[i]+"'");
			}else{//学校
				sqls.add("update xg_gygl_new_gzdx_wmqssqb set xxshzt='"+myForm.getShzt()+"',xxshsj=to_char(sysdate,'yyyy-mm-dd'),"+
						"xxshbz='"+myForm.getShbz()+"',xxshr='"+user.getUserName()+"' where nd='"+Base.currNd+"' and lddm||'!!one!!'||qsh='"+pk[i]+"'");
			}
		}
		return this.saveArrDate(sqls.toArray(new String[]{}));
	}
	
	/**
	 * 保存文明寝室审核信息
	 * @param myForm
	 * @return
	 */
	public boolean delWmqsshxx(GyglWmqsForm myForm) throws Exception{
		boolean b=false;
		String[] pk=myForm.getPrimarykey_checkVal();
		if(pk==null||pk.length==0){
			return b;
		}
		ArrayList<String> sqls=new ArrayList<String>();
		for(int i=0;i<pk.length;i++){
			sqls.add("delete from  xg_gygl_new_gzdx_wmqssqb where nd='"+Base.currNd+"' and lddm||qsh='"+pk[i]+"'");
		}
		return this.saveArrDate(sqls.toArray(new String[]{}));
	}
	
	/**
	 * 获取文明寝室管理列表
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ArrayList<String[]> getWmqsglList(GyglWmqsForm model,HttpServletRequest request) throws Exception{
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员
		
		String sql="select rownum r,a.lddm||a.qsh pk,a.* from view_xg_gygl_new_gzdx_wmqssq a where 1=1 ";
		String[] colList=new String[]{"pk","ldmc","qsh","qsrs","wspjf","sqr","fdyshzt","xxshzt"};
		return this.commonQuery(sql, searchTj+searchTjByGyfdy, inputV, colList, model);
	}
	
	/**
	 * 获取文明寝室百分比
	 * @param csName
	 * @return
	 */
	public List<HashMap<String,String>> getWmqsbfb(String[] csName){
		if(csName==null||csName.length==0){
			return null;
		}
		String sql="select csdm,csmc,csz,bz from xg_gygl_new_jbszb where csdm=?";
		return new DAO().getList(sql, new String[]{"gzdx_wmqsbfb"}, new String[]{"csdm","csmc","csz","bz"});
	}
	
	/**
	 * 保存文明寝室百分比
	 * @param csz
	 * @return
	 */
	public boolean saveWmqsbfb(String csz){
		boolean b=false;
		DAO dao=new DAO();
		String[] inputV;
		String sql="select count(1) num from xg_gygl_new_jbszb where csdm=?";
		String num=dao.getOneRs(sql, new String[]{"gzdx_wmqsbfb"}, "num");
		if("0".equals(num)){
			sql="insert into xg_gygl_new_jbszb values(?,?,?,?)";
			inputV=new String[]{"gzdx_wmqsbfb","贵州大学文明寝室百分比",csz,"输入值为XX%"};
		}else{
			sql="update xg_gygl_new_jbszb set csz=? where csdm=?";
			inputV=new String[]{csz,"gzdx_wmqsbfb"};
		}
		try {
			 b=dao.runUpdate(sql, inputV);
			 if(b){
				GyglNewInit.gzdx_wmqsbfb=csz; 
			 }
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 获取楼栋信息列表
	 * @param request
	 * @return
	 */
	public List<HashMap<String,String>> getLdxxList(HttpServletRequest request){
		GyglNewService gyglNewService = new GyglNewService();
		request.setAttribute("path", "gzdx_gygl_wmqs_qsgl.do");
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员
		String sql="select lddm,ldmc from xg_gygl_new_ldxxb where 1=1 "+searchTjByGyfdy;
		return new DAO().getList(sql, new String[]{}, new String[]{"lddm","ldmc"});
	}

}
