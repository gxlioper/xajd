package xgxt.xtwh.comm.splc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.CommService;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.CommonUpdata;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.utils.db.GetSysData;

public class XtwhShlcService extends CommService{
	
	XtwhShlcDAO dao=new XtwhShlcDAO();
	
	//-------------------------------这是今天要中更多 的方法 大师写下面 begin----------------------------------
	/**
	 * 获取审批流程结果集
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSplcList(XtwhShlcForm model) throws Exception{

		return dao.getSplcList(model);
	}
	
	/**
	 * 获取审批流程结果集
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getTopTr(XtwhShlcForm model) throws Exception{

		DAO dao=DAO.getInstance();
		String[]en={"lcid","lcmc","mkmc","spnr","sycs"};
		String[]cn={"流程编号","审核流程","模块名称","流程内容","是否使用"};
		
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 根据审核流程加载审批岗位
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getSpgwByShl(XtwhShlcForm model) {
		
		return dao.getSpgwByShl(model);
	}
	
	/**
	 * 特殊岗位
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getTsgwByShl(XtwhShlcForm model) {

		return dao.getTsgwByShl(model);
	}
	
	/**
	 * 获取用户组
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getYhzInfo() {
		XtwhShlcForm model = new XtwhShlcForm();
		return dao.getYhzInfo(model);
	}
	
	/**
	 * 获取可选用户
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getKxyhInfo(XtwhShlcForm model) {
		

		Pages pages=new Pages();
		List<HashMap<String,String>>rs=new ArrayList<HashMap<String,String>>();
		// ========== 个性化 用户授权 begin ============
		List<HashMap<String, String>> rsList = null;
		String yhszlx = model.getYhszlx();
		if("rcxwwh".equals(yhszlx)){
			// 日常事务NEW-日常行为维护NEW-日常行为代码维护-日常行为类别
			rsList = dao.getKxyhInfoRcxwwh(model);
		}else{
			// 系统维护-审批流程维护-审批流程
			rsList = dao.getKxyhInfo(model);
		}
		// ========== 个性化 用户授权 end ============
		HashMap<String,String>hashMap=new HashMap<String,String>();
		int pageInt = 1;
		int minR=0;
		
		if (!Base.isNull(model.getPage())) {
			pageInt = Integer.parseInt(model.getPage());
			minR=(pageInt-1)*10+minR;
		}
		
		pages.setPageSize(10);
		pages.setStart(minR);
		
		hashMap.put("count",String.valueOf(rsList.size()));
		rs.add(hashMap);
		rs.addAll(getResultList(rsList, pages));
		return rs;
	}

	/**
	 * 获取已选用户
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getYxyhInfo(XtwhShlcForm model) {
		Pages pages=new Pages();
		List<HashMap<String,String>>rs=new ArrayList<HashMap<String,String>>();
		// ========== 个性化 用户授权 begin ============
		List<HashMap<String, String>> rsList = null;
		String yhszlx = model.getYhszlx();
		if("rcxwwh".equals(yhszlx)){
			// 日常事务NEW-日常行为维护NEW-日常行为代码维护-日常行为类别
			rsList = dao.getYxyhInfoRcxwwh(model);
		}else{
			// 系统维护-审批流程维护-审批流程
			rsList = dao.getYxyhInfo(model);
		}
		// ========== 个性化 用户授权 end ============
		HashMap<String,String>hashMap=new HashMap<String,String>();
		int pageInt = 1;
		int minR=0;
		
		if (!Base.isNull(model.getPage())) {
			pageInt = Integer.parseInt(model.getPage());
			minR=(pageInt-1)*10+minR;
		}
		
		pages.setPageSize(10);
		pages.setStart(minR);
		
		hashMap.put("count",String.valueOf(rsList.size()));
		rs.add(hashMap);
		rs.addAll(getResultList(rsList, pages));
		return rs;
	}
	
	/**
	 * 获取已选用户(所有)(系统维护-审批流程维护-审批流程)
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> loadAllYxyhInfo(XtwhShlcForm model) {
		return dao.getYxyhInfo(model);
	}
	/**
	 * 获取已选用户(所有)(日常事务NEW-日常行为维护NEW-日常行为代码维护-日常行为类别)
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> loadAllYxyhInfoRcxwwh(XtwhShlcForm model) {
		return dao.getYxyhInfoRcxwwh(model);
	}
	
	/**
	 * 获取已选用户(删除后)
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getYxyhByDel(XtwhShlcForm model) {
		Pages pages=new Pages();
		List<HashMap<String,String>>rs=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>rsList=dao.getYxyhByDel(model);
		HashMap<String,String>hashMap=new HashMap<String,String>();
		int pageInt = 1;
		int minR=0;
		
		if (!Base.isNull(model.getPage())) {
			pageInt = Integer.parseInt(model.getPage());
			minR=(pageInt-1)*10+minR;
		}
		
		pages.setPageSize(10);
		pages.setStart(minR);
		
		hashMap.put("count",String.valueOf(rsList.size()));
		rs.add(hashMap);
		rs.addAll(getResultList(rsList, pages));
		return rs;
	}
	
	/**
	 * 获取已选用户(删除后)
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getKxyhByDel(XtwhShlcForm model) {
		Pages pages=new Pages();
		List<HashMap<String,String>>rs=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>rsList=dao.getKxyhByDel(model);
		HashMap<String,String>hashMap=new HashMap<String,String>();
		int pageInt = 1;
		int minR=0;
		
		if (!Base.isNull(model.getPage())) {
			pageInt = Integer.parseInt(model.getPage());
			minR=(pageInt-1)*10+minR;
		}
		
		pages.setPageSize(10);
		pages.setStart(minR);
		
		hashMap.put("count",String.valueOf(rsList.size()));
		rs.add(hashMap);
		rs.addAll(getResultList(rsList, pages));
		return rs;
	}
	
	/**
	 * 批量保存审核岗位信息(系统维护-审批流程维护-审批流程)
	 * @param form
	 * @return
	 * @throws Exception 
	 */
	public boolean saveSpgw(XtwhShlcForm form) throws Exception{
		
		XtwhShlcModel model=new XtwhShlcModel();
		
		String realTable = "xg_xtwh_spgwyh";
		String spgw=form.getSpgw();
		String[]spyh=form.getYxyhArr();
		
		String[] arrzd = new String[] { "spyh"};
		String[] onezd = new String[] { "spgw"};
		
		String pk="spgw";
		String []pkValue={spgw};
		if(spyh!=null && spyh.length>0){
			pkValue=new String[spyh.length];
			for(int i=0;i<spyh.length;i++){
				pkValue[i]=spgw;
			}
		}
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		
		model.setSpgw(spgw);
		model.setSpyh(spyh);

		return saveInfoToDb(saveForm, model, form.getUser());
	}
	/**
	 * 批量保存审核岗位信息(日常事务NEW-日常行为维护NEW-日常行为代码维护-日常行为类别)
	 * @param form
	 * @return
	 * @throws Exception 
	 */
	public boolean saveSpgwRcxwwh(XtwhShlcForm form) throws Exception{
		
		XtwhShlcModel model=new XtwhShlcModel();
		
		String realTable = "XG_RCSW_NEW_RCXWSQB";
		String spgw=form.getSpgw();
		String[]spyh=form.getYxyhArr();
		
		String[] arrzd = new String[] { "zgh"};
		String[] onezd = new String[] { "rcxwlbdm"};
		
		String pk="rcxwlbdm";
		String []pkValue={spgw};
		if(spyh!=null && spyh.length>0){
			pkValue=new String[spyh.length];
			for(int i=0;i<spyh.length;i++){
				pkValue[i]=spgw;
			}
		}
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		
		model.setSpgw(spgw);
		model.setSpyh(spyh);
		
		return saveInfoToDb(saveForm, model, form.getUser());
	}
	
	/**
	 * 删除审核流程
	 * @param form
	 * @return
	 * @throws Exception 
	 */
	public boolean delShlc(XtwhShlcForm form) throws Exception {
		//删除岗位下属的用户，注意：通用岗位下的用户是不会删除的
		dao.deleteYhByShlc(form);
		boolean blog = dao.delShlc(form);
		if (blog) {
			blog = dao.delLcxggw(form);
		}
		if (blog) {
			blog = dao.delShbz(form);
		}
		return blog;
	}
	
	/**
	 * 获取模块类型信息
	 * @return
	 */
	public List<HashMap<String,String>>getMklxInfo(){
		
		return dao.getMklxInfo();
	}
	
	/**
	 * 更新流程设置
	 *
	 */
	public void updateLcsz(){
		List<HashMap<String,String>>list=getMklxInfo();
		DAO dao=DAO.getInstance();
		for(int i=0;i<list.size();i++){
			HashMap<String,String>mklxMap=list.get(i);
			//评奖评优
			if("pjpy".equalsIgnoreCase(mklxMap.get("mkdm"))){
					try {
						dao.runProcuder("{call pro_xg_xtwh_splc_pjlcsz()}",new String[]{});
					} catch (Exception e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
			}else if("sztz".equalsIgnoreCase(mklxMap.get("mkdm"))){
					try {
						dao.runProcuder("{call pro_xg_xtwh_splc_sztzlcsz()}",new String[]{});
					} catch (Exception e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
			}else if("rcsw".equalsIgnoreCase(mklxMap.get("mkdm"))){
				try {
					dao.runProcuder("{call pro_xg_xtwh_splc_rcswsz()}",new String[]{});
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}else if("xsxx".equalsIgnoreCase(mklxMap.get("mkdm"))){
				try {
					dao.runProcuder("{call pro_xg_xtwh_splc_xsxx()}",new String[]{});
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
		}
	}
	
	
	//	-------------------------------这是今天要中更多 的方法大师写下面 end----------------------------------

//	-------------------------------这是今天要中的比楼上更多的方法 begin----------------------------------
	public List<HashMap<String, String>> getSsmkList() {
		String tableName="xg_xtwh_splcmkdzb";
		String[] colList = new String[]{"mkdm","mkmc"};
		return CommonQueryDAO.commonQueryforList(tableName,"", new String[]{}, colList, "");
	}
//	-------------------------------这是今天要中的比楼上更多的方法 end----------------------------------

	public List<HashMap<String, String>> getGdgwList() {
		String tableName="xg_xtwh_spgw";
		String[] colList = new String[]{"id","mc"};
		String query = " where gwlx='1'";
		return CommonQueryDAO.commonQueryforList(tableName,query, new String[]{}, colList, "");
	}

	public Boolean savelcxx(XtwhShlcModel model, HttpServletRequest request) {
		String tableName = "xg_xtwh_splc";
		String [] colList = new String []{"mc","djlx","ms","id"};
		CommDAO dao =new CommDAO();
		Boolean update = false;
		String lcid = model.getId();
		try {
			update = CommonUpdata.commonUpdata(colList, model, tableName, "mc", "", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(update){
			String sum = request.getParameter("jscmdm");
			ArrayList<String> sqlArr = new ArrayList<String>();
			String[] zdylclist = new String[Integer.parseInt(sum)]; 
			String sql="";
			for(int i=1;i<=Integer.parseInt(sum);i++){
				String lcgw = request.getParameter("lcgw"+i);
				String gwz = request.getParameter("spgwzdm"+i);
				String spgwdm = request.getParameter("lcgwdm"+i);
				if(lcgw!=null){
					String sid = GetSysData.getGuid();
					sql = "insert into xg_xtwh_spgw (id,mc,gwz) values ('"+sid+"','"+lcgw+"','"+gwz+"')";
					zdylclist[i-1]=sid;
				}else{
					sql = "update xg_xtwh_spgw set gwz= '"+gwz+"' where id='"+spgwdm+"'";
				}
				sqlArr.add(sql);
				
			}
			
			try {
				update = dao.saveArrDate(sqlArr.toArray(new String[sqlArr.size()]));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(update){
				String [] lcgwSql = new String[zdylclist.length]; 
				for(int i = 0;i<zdylclist.length;i++){
					if(zdylclist[i]!=null){
						lcgwSql[i] =  "insert into xg_xtwh_spbz (splc,xh,spgw) values ('"+lcid+"','"+(i+1)+"','"+zdylclist[i]+"')";
					}else{
						String tmp =  "lcgwdm"+(i+1);
						String yygwid = request.getParameter("lcgwdm"+(i+1));
						lcgwSql[i] =  "insert into xg_xtwh_spbz (splc,xh,spgw) values ('"+lcid+"','"+(i+1)+"','"+yygwid+"')";
					}
				}
				try {
					update = dao.saveArrDate(lcgwSql);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return update;
	}

	public HashMap<String, String> getLcxx(String lcid) {
		// TODO 自动生成方法存根
		String tableName = "xg_xtwh_splc";
		String [] colList = new String[]{"id","mc","ms","djlx"};
		return CommonQueryDAO.commonQueryOne(tableName, colList, "id", lcid);
	}
	
	public List<String[]> getLcgwxx(String lcid) {
		XtwhShlcDAO dao = new XtwhShlcDAO();
		return dao.getLcgw(lcid);
	}

	public String[] getGwxx(String gwlx, String gwdm,String lcid) {
		// 获取岗位名称及所属流程名称
		XtwhShlcDAO dao = new XtwhShlcDAO();
		String[] gwxx = new String[]{};
		if(gwlx!=null&&gwlx.equalsIgnoreCase("ty")){
			gwxx = dao.getTygwxx(gwdm);
		}else{
			gwxx = dao.getGwxx(gwdm,lcid);
		}
		return gwxx;
	}
	

	// -------------------------------伟大的luo  begin----------------------------------
	/**
	 * 获得审批流程列表
	 * 
	 * @author luojw
	 */
	public static List<HashMap<String, String>> getSplcList(String gnmk) {

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getWhList("xg_xtwh_splc",
				"id", "mc", "", "djlx", gnmk, false);

		return list;
	}
	
	/**
	 * 获得审批岗位列表
	 * 
	 * @author luojw
	 */
	public static List<HashMap<String, String>> getSpgwList(String lcid,String userName) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select b.spgw gwid, c.mc gwmc, b.xh lv,d.maxlv ");
		sql.append("from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c, ");
		sql.append("(select max(xh) maxlv, splc from xg_xtwh_spbz group by splc) d ");
		sql.append("where a.id = b.splc ");
		sql.append("and d.splc = b.splc ");
		sql.append("and b.spgw = c.id ");
		sql.append("and a.id = ? ");
		
		if (!Base.isNull(userName)) {
			sql.append("and exists(select 1 from xg_xtwh_spgwyh d ");
			sql.append("where c.id = d.spgw and spyh = '" + userName + "') ");
		}
		
		sql.append("order by to_number(lv) ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { lcid }, new String[] { "gwid", "gwmc", "lv",
						"maxlv" });

		return list;
	}
	
	/**
	 * 根据模块类型获取审核流程信息
	 * @param djlx
	 * @return splc,lcxx
	 */
	public List<HashMap<String,String>> getShlcByDjlx(String djlx){
		List<HashMap<String,String>> listmap = new ArrayList<HashMap<String,String>>(); 
		List<HashMap<String,String>> list =  dao.getShlcByDjlx(djlx);
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("sqlc", "wxsh");
//		map.put("lcxx", "无需审核");
//		listmap.add(map);
		if(null!=list&&list.size()>0){
			for(int i=0;i<list.size();i++){
				listmap.add(list.get(i));
			}
		}
		return listmap;
	}
	
	/**
	 * 创建通用岗位Div
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void createTygwDiv(XtwhShlcForm model, User user,
			HttpServletResponse response) throws Exception {

		List<HashMap<String, String>> list = getTsgwByShl(model);

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				html.append("<input type='radio' name='spgw' ");
				if(i==0){
					html.append("checked=\"checked\" ");
				}
				html.append("id=\"spgw" + i + list.get(i).get("gwlx") + "\" ");
				html.append("value=\"" + list.get(i).get("spgw") + "\" ");
				html.append("/> ");
				html.append(list.get(i).get("mc"));
				html.append("<br/>");
			}
		}
		response.getWriter().print(html.toString());
	}
	/**
	 * 
	 * @描述:返回创建的divHtml
	 * @作者：张昌路[工号：982]
	 * @日期：2013-7-10 下午02:25:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String createTygwDivStr(XtwhShlcForm model, User user,
			HttpServletResponse response) throws Exception {

		List<HashMap<String, String>> list = getTsgwByShl(model);

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				html.append("<input type='radio' name='spgw' ");
				if(i==0){
					html.append("checked=\"checked\" ");
				}
				html.append("id=\"spgw" + i + list.get(i).get("gwlx") + "\" ");
				html.append("value=\"" + list.get(i).get("spgw") + "\" ");
				html.append("/> ");
				html.append(list.get(i).get("mc"));
				html.append("<br/>");
			}
		}
		return html.toString();
	}
	/**
	 * 创建特殊岗位Div
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void createTsgwDiv(XtwhShlcForm model, User user,
			HttpServletResponse response) throws Exception {

		List<HashMap<String, String>> list = getSpgwByShl(model);
		
		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				html.append("<input type='radio' name='spgw' ");
				if (i == 0) {
					html.append("checked=\"checked\" ");
				}
				html.append("id=\"spgw" + i + list.get(i).get("gwlx") + "\" ");
				html.append("value=\"" + list.get(i).get("spgw") + "\" ");
				html.append("/> ");
				html.append(list.get(i).get("mc"));
				html.append("<br/>");
			}
		}
		response.getWriter().print(html.toString());
	}
	/**
	 * 
	 * @描述:返回创建的divHtml
	 * @作者：张昌路[工号：982]
	 * @日期：2013-7-10 下午02:25:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String createTsgwDivStr(XtwhShlcForm model, User user,
			HttpServletResponse response) throws Exception {

		List<HashMap<String, String>> list = getSpgwByShl(model);
		StringBuilder html = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				html.append("<input type='radio' name='spgw' ");
				if (i == 0) {
					html.append("checked=\"checked\" ");
				}
				html.append("id=\"spgw" + i + list.get(i).get("gwlx") + "\" ");
				html.append("value=\"" + list.get(i).get("spgw") + "\" ");
				html.append("/> ");
				html.append(list.get(i).get("mc"));
				html.append("<br/>");
			}
		}
		return html.toString();
	}
	// -------------------------------伟大的luo end----------------------------------
	
	/**
	 * 通用获取审核级别列表
	 * @param splcid
	 * 
	 * @return List<HashMap<String, String>>
	 *              HashMap.put(spgwdm) 返回
	 *              HashMap.put(spgwmc) 返回     
	 */
	public List<HashMap<String, String>> getSpjbListByGnmk(String splcid) {
		
		List<HashMap<String,String>> spgwList = dao.getSpjbListByGnmk(splcid);
		
//		HashMap<String,String> sqMap = new HashMap<String, String>();
//		sqMap.put("spgwdm", "sq");
//		sqMap.put("spgwmc", "申请");
//		
//		spgwList.add(0, sqMap);
		
		return spgwList;
	}

	/**
	 * @throws Exception  
	 * @描述:修改审批流程
	 * @作者：cq [工号：785]
	 * @日期：2014-1-2 下午07:27:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateShlc(XtwhShlcForm myForm,HttpServletRequest request,List<String[]> lcgwrs) throws Exception {
		
		boolean blog = dao.updateShlc(myForm);
		String sql ="";
		ArrayList<String> sqlArr = new ArrayList<String>();
		for(int i=0;i<lcgwrs.size();i++){
			String gwz = request.getParameter("spgwzdm"+i);
				sql = "update xg_xtwh_spgw set gwz= '"+gwz+"' where id='"+lcgwrs.get(i)[0]+"'";
			sqlArr.add(sql);
			
		}
		
		try {
			blog = dao.saveArrDate(sqlArr.toArray(new String[sqlArr.size()]));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return blog;
	}
	
	/**
	 * @throws SQLException  
	 * @描述:根据数据范围插入岗位用户(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-1 上午09:05:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertGwyhBySjfw(String splcid) throws SQLException {
		List<HashMap<String,String>> mapList = getSpgwAndGwz(splcid); 
		List<String[]> yhms = new ArrayList<String[]>();
		List<String> spgwList = new ArrayList<String>();
		for(int i = 0;i<mapList.size();i++) {
			HashMap<String,String> map = mapList.get(i);
			if(null == map.get("gwz") || "".equalsIgnoreCase(map.get("gwz"))){//如果当前审核岗位没有没有设定岗位组
				continue;
			}
			spgwList.add(map.get("spgw"));
			List<String> list = dao.getGwyhForInsert(map.get("spgw"), map.get("gwz"));//获取每个审批岗位能插入的用户名
			String[] yhmStrs = new String[list.size()];
			for(int j = 0;j<list.size();j++){
				yhmStrs[j] = list.get(j);//转换成每个审批岗位能插入的用户数组
			}
			yhms.add(yhmStrs);
		}
		if(yhms.size()>0){
			String[] spgwidArr = new String[spgwList.size()];
			for(int p = 0;p<spgwList.size();p++){
				spgwidArr[p] = spgwList.get(p);
			}			
			return dao.insertGwyh(spgwidArr, yhms);
		}else{//如果没有一级审批岗位有岗位组限定的情况下
			return true;
		}
	}
	
		/**
		 * @throws Exception  
		 * @描述:修改岗位组用户(这里用一句话描述这个方法的作用)
		 * @作者：柳俊[工号：1282]
		 * @日期：2017-3-7 下午03:47:57
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @return
		 * boolean 返回类型 
		 * @throws 
		 */
		public boolean updateGwzyh(List<HashMap<String,String>> oldList,List<HashMap<String,String>> newList,String splcId) throws Exception{
			List<String> gwidList = new ArrayList<String>();
			List<String> gwzList = new ArrayList<String>();
			for(int i = 0;i<oldList.size();i++){
				String oldSpgwz = oldList.get(i).get("gwz");//取出旧的审批岗位对应的审批岗位组
				String newSpgwz = newList.get(i).get("gwz");//取出新的审批岗位对应的审批岗位组
				if(StringUtils.isNull(newSpgwz)){
					continue;
				}else if(StringUtils.isNotNull(oldSpgwz) && oldSpgwz.equalsIgnoreCase(newSpgwz)){
					continue;
				}else{
					String newSpgwid = newList.get(i).get("spgw");
					gwidList.add(newSpgwid);
					gwzList.add(newSpgwz);
				}
			}
			boolean result = true;
			if(gwidList.size()>0){
				result = dao.deleteYhBySpgw(gwidList.toArray(new String[]{}));
				if(result){
					if(gwidList.size()>0 && gwzList.size()>0){
						List<String[]> yhms = new ArrayList<String[]>();
						for(int p = 0;p<gwidList.size();p++){
							List<String> list = dao.getGwyhForInsert(gwidList.get(p), gwzList.get(p));//获取每个审批岗位能插入的用户名
							String[] yhmStrs = new String[list.size()];
							for(int j = 0;j<list.size();j++){
								yhmStrs[j] = list.get(j);//转换成每个审批岗位能插入的用户数组
							}
							yhms.add(yhmStrs);
						}
						if(yhms.size()>0){			
							return dao.insertGwyh(gwidList.toArray(new String[]{}), yhms);
						}else{//如果没有一级审批岗位有岗位组限定的情况下
							return true;
						}
						
					}
				}
			}
			return result;			
		}
	
	/** 
	 * @描述:根据审批id获取审批岗位和组(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-7 下午04:21:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splcid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getSpgwAndGwz(String splcId){
		return dao.getSpgwAndGwz(splcId);
		
	}
	
	/**
	 * @throws Exception 
	 * @throws SQLException  
	 * @描述:岗位初始化(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-9 下午03:50:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean gwCsh(String lcid) throws Exception{
		List<HashMap<String,String>> list = getSpgwAndGwz(lcid);
		List<String> spgws = new ArrayList<String>();
		for(HashMap<String,String> map : list){
			if(StringUtils.isNull(map.get("gwz"))){
				continue;				
			}else{
				spgws.add(map.get("spgw"));
			}
		}
		if(spgws.size()>0){		
			dao.deleteYhBySpgw(spgws.toArray(new String[]{}));
		}
		List<String[]> yhms = new ArrayList<String[]>();		
		if(list.size()>0){
			for(HashMap<String,String> map : list){
				if(StringUtils.isNull(map.get("gwz"))){//岗位组没有限定不初始化
					continue;
				}
				List<String> lists = dao.getGwyhForInsert(map.get("spgw"), map.get("gwz"));//获取每个审批岗位能插入的用户名
				String yhmStrs[] = lists.toArray(new String[]{});
				yhms.add(yhmStrs);
			}
			return dao.insertGwyh(spgws.toArray(new String[]{}), yhms);				
		}else{
			return true;			
		}
		
	}
}
