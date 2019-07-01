
package xgxt.studentInfo.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.search.SearchService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.User;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.studentInfo.dao.StudentInfoDao;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.model.StudentStatusModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

import common.Globals;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 学生信息模块Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-30</p>
 */
public class StudentInfoService {
	StudentInfoDao stuDao = new StudentInfoDao();
	DAO dao = DAO.getInstance();
	
	/**
	 * 异动后修改学生信息
	 * @param xh
	 * @return flag
	 * @throws Exception 
	 * **/
	public boolean modStudentBaseInfo(String xh,StudentInfoForm form,HttpServletRequest  request){
		boolean flag = true;
		//查询学生信息在xsxxb中是否已经有了，有就update，没有就将xsxxb中的所有字段用view_xsxxb中的信息插入
		try {
			if(!stuDao.isExistInXsxx(xh)){
				//在xsxxb表中没有记录			
				flag = stuDao.addStuInfoFromView(xh, request);			
			}
			if(flag){
				StudentStatusModel model = new StudentStatusModel();
				BeanUtils.copyProperties(model, form);
				flag = stuDao.modBaseXsxx(xh, model, request);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return flag;
	}
		
	/***************************************************************************
	 * 查询学籍异动信息
	 * 
	 * @param form
	 * @param cols_en
	 * @return list
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List getStatusDifferentInfo(String userName, StudentInfoForm form,
			String[] cols_en, HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		List<String[]> list = null;
		StuInfoDAO stuDao = new StuInfoDAO();
		String xxdm = StandardOperation.getXxdm();
		String tableName = "view_xjydjbxx";
		
		//获取页面输入的值
		String ydhnj = form.getYdhnj();
		String xh = form.getXh();
		String xm = DealString.toGBK(form.getXm());
		String ydhxydm = form.getYdhxydm();
		String ydhzydm = form.getYdhzydm();
		String ydhbjdm = form.getYdhbjdm();
		String ydlbdm = DealString.toGBK(form.getYdlbdm());
		String ydrqks = form.getYdrqks();
		String ydrqjs = form.getYdrqjs();
		String ydjzrqks = form.getYdjzrqks();
		String ydjzrqjs = form.getYdjzrqjs();
		String sffx = form.getSffx();
		
		form.setXm(xm);
		
		StringBuffer sb = new StringBuffer("select rownum r, ydxh,xh,xm,ydlbmc,ydrq,ydqbjmc,ydhxymc,ydhzymc,ydhbjmc from ");
				
		sb.append(tableName);
		sb.append(" a where 1=1");
		sb.append((ydhnj == null || ydhnj.trim().length() < 1) ? "": " and ydqnj='" + ydhnj.trim() + "'");
		sb.append(xh == null || xh.trim().length() < 1 ? "": " and xh like '%" + xh.trim() + "%'");
		sb.append(xm == null || xm.trim().length() < 1 ? "": " and xm like '%" + xm.trim() + "%'");		
		sb.append((ydhxydm == null || ydhxydm.trim().length() < 1) ? "": " and ydqxydm='" + ydhxydm + "'");		
		sb.append((ydhzydm == null || ydhzydm.trim().length() < 1) ? "": " and ydqzydm='" + ydhzydm + "'");
		sb.append((ydhbjdm == null || ydhbjdm.trim().length() < 1) ? "": " and ydqbjdm='" + ydhbjdm + "'");
		sb.append((ydlbdm == null || ydlbdm.trim().length() < 1) ? "": " and ydlbdm='" + ydlbdm+ "'");
		//异动日期开始
		sb.append( StringUtils.isNotNull(ydrqks) 
				   ? " and to_number(substr(ydrq,0,4)||substr(ydrq,6,2)||substr(ydrq,9,2)) >=" + ydrqks.replaceAll("-", "")
				   : "");
		//异动日期结束
		sb.append( StringUtils.isNotNull(ydrqjs) 
				   ? " and to_number(substr(ydrq,0,4)||substr(ydrq,6,2)||substr(ydrq,9,2)) <=" + ydrqjs.replaceAll("-", "")
				   : "");
		//异动截止日期开始
		sb.append( StringUtils.isNotNull(ydjzrqks) 
				   ? " and to_number(substr(ydjzrq,0,4)||substr(ydjzrq,6,2)||substr(ydjzrq,9,2)) >=" + ydjzrqks.replaceAll("-", "")
				   : "");
		//异动截止日期结束
		sb.append( StringUtils.isNotNull(ydjzrqjs) 
				   ? " and to_number(substr(ydjzrq,0,4)||substr(ydjzrq,6,2)||substr(ydjzrq,9,2)) <=" + ydjzrqjs.replaceAll("-", "")
				   : "");
		if("是".equalsIgnoreCase(sffx)){
			sb.append(" and exists(select 1 from view_xjydjbxx b where to_number(a.ydxh)<to_number(b.ydxh) and b.ydlbmc='复学' and a.xh=b.xh) and ydlbmc='休学'");
		}
		if("否".equalsIgnoreCase(sffx)){
			sb.append(" and not exists(select 1 from view_xjydjbxx b where to_number(a.ydxh)<to_number(b.ydxh) and b.ydlbmc='复学' and a.xh=b.xh) and ydlbmc='休学'");
		}
		if("true".equalsIgnoreCase(form.getIsFdy())){
			//辅导员登录
			sb.append(" and exists(select 1 from view_fdybbj b where (a.ydhbjdm=b.bjdm or a.ydqbjdm=b.bjdm) and b.zgh='" + userName + "')");
		}
		
		// 高级查询
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"ydqxydm", "ydqbjdm");

		String searchTj = SearchService.getSearchTj(form.getSearchModel());
		String[] inputV = SearchService.getTjInput(form.getSearchModel());
		sb.append(searchTj);
		sb.append(searchTjByUser);
		sb.append(" order by ydxh asc");
		
		StringBuffer sbff = new StringBuffer();
		sbff.append("select * from (select * from (");
		sbff.append(sb);
		sbff.append(") a  where r<=");
		sbff.append(form.getPages().getStart()+form.getPages().getPageSize());
		sbff.append(") where r>");
		sbff.append(form.getPages().getStart());
		
		list = dao.rsToVator(sbff.toString(), inputV, cols_en);
		
		//TODO 分页
		String sNum = dao.getOneRs("select count(*) count from("+sb.toString()+") a", inputV, "count");
		form.getPages().setMaxRecord(Integer.parseInt(sNum));
		
		return list;
	}
	
	
	/**
	 * 查询单条记录的异动信息
	 * @param pkValue
	 * @return map
	 * */
	public HashMap<String, String> getOneStatuDiffInfo(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();		

		map = stuDao.getOneStatuDiffInfo(pkValue);		
		return map;
	}
	
	/**
	 * 查询单条记录的异动信息
	 * @param pkValue
	 * @return map
	 * */
	public boolean delStatuDiffInfo(String xh,String ydxh,HttpServletRequest request){
		boolean flag = false;
		String primaryKey = "";
		String tableName = "bks_xjydxx";
		
		primaryKey = "xh||ydxh";
		try {
			//删除单条的异动信息
			flag = StandardOperation.delete(tableName, primaryKey, xh+ydxh, request);
//			if(flag){
//				//删除用于异动提醒的信息
//				primaryKey = "xh";
				//flag = StandardOperation.delete(tableName, primaryKey, xh, request);
//			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 * 增加学籍异动信息
	 * @param StudentInfoForm form
	 * @return boolean flag
	 * */
	public boolean addStatuStuInfo(StudentInfoForm form,HttpServletRequest request){
		boolean flag = false;		
		String xxdm = StandardOperation.getXxdm();
		StudentStatusModel model = new StudentStatusModel();
		try {
			BeanUtils.copyProperties(model, form);
			//将信息增加到学籍异动信息表中
			flag = stuDao.addStatuInfo(model,request);
			if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) || xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
				//上海工程 云南艺术 异动提醒
				if(flag){
					flag = stuDao.addStatuDiffInfo(model,request);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 * 修改学籍异动信息
	 * @param StudentInfoForm form
	 * @return boolean flag
	 * */
	public boolean modStatuStuInfo(StudentInfoForm form,HttpServletRequest request){
		boolean flag = false;
		String xxdm = StandardOperation.getXxdm();
		StudentStatusModel model= new StudentStatusModel();
		try {
			BeanUtils.copyProperties(model, form);
			//修改学籍异动信息
			flag = stuDao.modStatuInfo(model,request);
			if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) || xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
				//上海工程 云南艺术 异动提醒
				if(flag){
					flag = stuDao.modStatuDiffInfo(model, request);
				}
			}
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 * 将没有未毕业学生的班级删除，然后插入到毕业生班级中
	 * @param request
	 * @return boolean
	 * */
	@SuppressWarnings("unchecked")
	public boolean checkGraduateToFinish(HttpServletRequest request){
		boolean flag = true;
		//获取有毕业生班级的班级代码和总人数
		List list = stuDao.getGraduateInfo();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				//判断毕业的学生数是否等于所有的学生数，如果是就说明全班学生都毕业了，将该班级加入到毕业班中
				HashMap<String, String> map = (HashMap<String, String>)list.get(i);
				//班级总人数
				String sBjdm = map.get("bjdm");
				String sCount = map.get("count");
				int count = stuDao.getCountByBjdm(sBjdm);
				if(sCount!=null && Integer.parseInt(sCount)==count){
					flag = stuDao.addBybjInfo(sBjdm,request);
				}
			}
		}
		return flag;
	}
	
	/**
	 * 获取毕业班级信息
	 * @param condition
	 * @return List
	 * */
	public List getBjList(String condition,String type){
		List bjList = null;
		bjList = stuDao.getBybjList(condition,type);
		return bjList;
	}
	
	/**
	 * 组合学生集体信息查询sql
	 * @param whereSql
	 * @param city
	 * @return String
	 * */
	public String getSql(String whereSql,String city){
		String sql = "";
		sql = "select a.*,nvl(b.fdyxm,'') fdyxm,nvl(b.fdylxdh,'') fdylxdh from " + 
               "(" + 
               "select a.* ,nvl(b.bzrxm,'') bzrxm,nvl(b.bzrlxdh,'') bzrlxdh from " + 
               "(" + 
               "select a.*,nvl(b.bz,'') bzxm,nvl(b.lxdh,'') bzlxdh from " + 
               "(" + 
               "select a.*,nvl(b.snrs,0) snrs,nvl(nvl(zrs,0)-nvl(b.snrs,0),0) swrs from " + 
               "(" + 
               "select a.*,nvl(b.girlrs,0) girlrs from " + 
               "(" +
               "select a.*,nvl(b.boyrs,0) boyrs from " +  
               "(" + 

               "select b.*,nvl(a.zsrs,0) zsrs,nvl(nvl(b.zrs,0) - nvl(a.zsrs,0),0) wzrs from " +  
               "(" + 
               "select bjdm,nvl(count(*),0) zsrs from (select a.xh,a.bjdm from view_xsjbxx a,view_xszsxx b " + whereSql + " and a.xh=b.xh) group by bjdm " + 
               ") a right join " +  
               "(" + 
               "select max(bjdm)bjdm,max(xydm)xydm,max(zydm)zydm,max(nj)nj,max(bjmc)bjmc,max(xymc)xymc,max(zymc)zymc,nvl(count(*),0) zrs from view_xsjbxx a "+ whereSql +" group by bjdm " + 
               ") b on a.bjdm=b.bjdm " + 

               ") a " +  
               "left join " + 
               "(" + 
               "select bjdm,max(xb)xb,count(xb) boyrs from view_xsjbxx a" + whereSql+ " and xb='男' group by bjdm " + 
               ") b on a.bjdm=b.bjdm " + 
               ") a left join " + 
               "(" + 
               "select bjdm,max(xb)xb,count(xb) girlrs from view_xsjbxx a " + whereSql + " and xb='女' group by bjdm " + 
               ") b on a.bjdm=b.bjdm " + 
               ") a left join " + 
               "(" +  
               "select bjdm,count(*) snrs from xsxxb a " + whereSql+ " and jg like '" + city + "%' group by bjdm " + 
               ") b on a.bjdm=b.bjdm " + 
               ") a left join " + 
               "( " + 
               "select b.xm bz,a.lxdh,a.bjdm bjdm from view_xsjbxx a left join view_xsgbxx b on a.xh=b.xh " + whereSql + " and b.drzw like '%班长%' group by a.bjdm,b.xm,a.lxdh " + 
               ") b on a.bjdm=b.bjdm " + 
               ") a left join " + 
               "(" + 
               "select max(xm) bzrxm,bjdm,max(lxdh) bzrlxdh from view_fdybjdz where zw like '%班主任%' group by bjdm " + 
               ") b on a.bjdm=b.bjdm " + 
               ") a left join " + 
               "(" + 
               "select max(xm) fdyxm,bjdm,max(lxdh) fdylxdh from view_fdybjdz where zw like '%辅导员%' group by bjdm " + 
               ") b on a.bjdm=b.bjdm";
		return sql;
	}
	
	/**
	 * 获取要导入的字段
	 * @return sql 
	 * */
	public String getColumnOfXsxx(){
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String tabName = "xsxxb";
		if (xxdm.equalsIgnoreCase(Globals.XXDM_DBLYDX)) {
			//东北林业
			sql = "select xh,xm,xb,nj,xydm,xy,zydm,zymc,bjdm,bjmc,csrq,sfzh,sjhm,ah,tc,dzyx,sfdk,jrgcdsj," +
					"jrgqtsj,jtdz,jtyb,jtdh,jtjjqk,jtqkjj,jlcfjl,bz,shgxxm1,shgxgx1,shgxgzdw1,shgxzw1,shgxdwdh1," +
					"shgxsjhm1,shgxxm2,shgxgx2,shgxgzdw2,shgxzw2,shgxdwdh2,shgxsjhm2 from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
			//上海工程技术大学
			sql = "select xh,xm,xb,nj,xz,xmpy,cym,xjztm,xy,zymc,bjmc,bjdm,zydm,xydm,mz,zzmm,sg,tz,tc,syd,csrq," +
					"pyfs,pycc,rxfs,kslb,sfzh,lxdh,dzyx,jg,sjhm,jrgcdsj,jrgqtsj,byxx from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			//武汉理工
			sql = "select xh,xm,xb,nj,xydm,xy,zydm,zymc,bjdm,bjmc,xz,zslb,kh,xjztm,mz," +
				  "zzmm,sfzh,gj,jg,sg,tz,tc,sfjh,ccqj,lxdh,sjhm,dzyx,qqhm from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
			//云南艺术
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh," +
					"jg,lxdh,dzyx,sg,tz,tc,kslb,pycc,jg,xz,zw,xxfx,rxrq,byny,qqhm from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			//西北二民院
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,xydm,zydm,bjdm,nj,xjztm,xmpy,ksh,cym,zsjj,csrq,dqszj,pyfs,pycc" +
				  ",pyfx,csd,rxfs,kslb,sfzh,dzyx,syd,lxdh,jg,sjhm,xz,zyfx,rxrq,zylb,fxzy,fxzyfx,bxxs,bxlx,xxxs,byny,zsbh," +
				  "zsxlh,xw,xwzsbh,xwzsxlh,bjyjl,xxszd,xzxm,shbj,dybj,thbs,bz from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)){
			//安徽建筑工业学院
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,kh from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_XBMZDX)){
			//西北民族大学
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,sfzx from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
			//北京联合大学
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,kh,sfzfx,zjdm from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)){
			//南昌大学科技学院
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,xx,jtdzs,jtdzx,ssyq,ssld from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZZDX)){
			//中州大学
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,sfzx from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)){
			//广东女子职业技术学院
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,rxrq,fdyxm,gkcj,jtjjqk,jtdz,jkzk from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)){
			//广东白云学院
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,rxrq,kh,hkszd,hkxz,zyjb,whcd from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
			//浙江机电职业技术学院
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "sjhm,dzyx,sg,tz,tc,kslb,pycc,xz,kh from " + tabName;
		}else {
			//通用
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "sjhm,dzyx,sg,tz,tc,kslb,pycc,xz from " + tabName;
		}
		return sql ;
	}
	
//	/**
//	 * 获取要导出的字段
//	 * @return String 
//	 * */
//	public String getXsxxToExp(){
//		String xxdm = StandardOperation.getXxdm();
//		String sql = "";
//		String tabName = "view_xsxxb";
//		if (xxdm.equalsIgnoreCase(Globals.XXDM_DBLYDX)) {
//			//东北林业
//			sql = "select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,csrq,sfzh,sjhm,ah,tc,dzyx,sfdk," +
//					"jrgcdsj,jrgqtsj,jtdz,jtyb,jtdh,jtjjqk,jtqkjj,jlcfjl,bz,shgxxm1,shgxgx1,shgxgzdw1," +
//					"shgxzw1,shgxdwdh1,shgxsjhm1,shgxxm2,shgxgx2,shgxgzdw2,shgxzw2,shgxdwdh2,shgxsjhm2,sfbys from " +
//					tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
//			//上海工程技术大学
//			sql = "select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,mz,mzmc,xjlb,xmpy,cym,csrq,pyfs,pycc," +
//			      "zzmm,zzmmmc,sfzh,jg,sg,tz,tc,rxfs,kslb,dzyx,syd,lxdh,sjhm,xz,jrgcdsj,jrgqtsj,byxx,ssbh," +
//			      "ssch,zsrq,zsjzrq,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
//			//武汉理工
//			sql = "select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xz,zslb,kh,xjlb,mz,mzmc," +
//				  "zzmm,zzmmmc,sfzh,gj,jg,sg,tz,tc,sfjh,ccqj,lxdh,sjhm,dzyx,qqhm,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
//			//云南艺术
//			sql = "select xh,xm,xb,mz,zzmm,xyMC,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,zw,xxfx,rxrq,byny,qqhm,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
//			//西北二民院
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,xydm,zydm,bjdm,nj,xjlb,xmpy,ksh,cym,zsjj,csrq,dqszj,pyfs,pycc" +
//				  ",pyfx,csd,rxfs,kslb,sfzh,dzyx,syd,lxdh,jg,sjhm,xz,zyfx,rxrq,zylb,fxzy,fxzyfx,bxxs,bxlx,xxxs,byny,zsbh," +
//				  "zsxlh,xw,xwzsbh,xwzsxlh,bjyjl,xxszd,xzxm,shbj,dybj,thbs,bz,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)){
//			//安徽建筑工业学院
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,kh,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_XBMZDX)){
//			//西北民族大学
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,sfzx,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
//			//北京联合大学
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,kh,sfzfx,zjdm,zjmc,hkszd,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)){
//			//南昌大学科技学院
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,xx,jtdzs,jtdzx,ssbh,ssch,ssyq,ssld,qsdh,zsrq,zsjzrq,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZZDX)){
//			//中州大学
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,zzdx,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)){
//			//广东女子职业技术学院
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,rxrq,fdyxm,gkcj,jtjjqk,jtdz,jkzk,ssbh,ssch,zsrq,zsjzrq,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)){
//			//广东白云学院
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,rxrq,kh,hkszd,hkxz,zyjb,whcd,ssbh,ssch,zsrq,zsjzrq,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
//			//浙江机电职业技术学院
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,xz,sfbys,bysj,zkzh,sfcj,yhkh,yhmc,grjl,jlcfjl,bz from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJGSZYJSXY)){
//			//浙江工商专业技术学院
//			sql = "select xh,xm,xb,mz,mzmc,zzmm,zzmmmc,yxdm,yxmc,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,xjztm,sfzh,xw,xwmc,syd,sydmc,sfzz,sfsf," +
//					"sfdl,xz,dxhwp,rxrq,bysj,zsbh,zxwyyzdm,zxwyyzmc,wydj,jsjdj,sjhm,qqhm,lxdz,yzbm,shzw,jypx,xmsj,zgzs,jljn," +
//					"sybz1,sybz2,sybz3,xldm,xlmc,zslb,zslbmc,pyfs,pyfsmc,dzyx,lxdh,kh,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)){
//			//浙江经济职业技术学院
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxrq,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,xz,jlcfjl,bz,zkzh,sfcj,grjl,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
//			//宁波理工
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,xz,sfyby,sfzx,sfbys from " + tabName;
//		}else if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
//			//中国地质大学
//			sql = "select xh,xm,xb,mz,mzmc,zzmm,zzmmmc,xymc,zymc,bjmc,bjdm,zydm," +
//				  "xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,dzyx," +
//				  "sg,tz,tc,kslb,pycc,xz,rxqdw,rxrq,rxnj,sfzc,xslb,ksh,sfzx,nfby," +
//				  "byny,xslbmc,xslx,xslxmc,bz,ydlbm,ydlbmc from " + tabName;
//		}else if(Globals.XXDM_LSSFXY.equalsIgnoreCase(xxdm)){
//			//乐山师范学院
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,xz,jtdz,rxqdw,qqhm,ssbh,sjhm,kh,byny,sfbys from " + tabName;			
//		}else if(Globals.XXDM_NJJS.equalsIgnoreCase(xxdm)){
//			//南京技师
//			sql = "select xh,xm,xb,mz,mzmc,zzmm,zzmmmc,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,xz,yhdm,yhmc,kh,sfbys,byny,hkshen,hkshi,hkxian,zcsxhm,rxqwhcd,hkxz from " + tabName;
//		}else if(Globals.XXDM_NNZYJSXY.equalsIgnoreCase(xxdm)){
//			//南宁职业
//			sql = "select xh,xm,xb,mz,mzmc,zzmm,zzmmmc,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,xz,yhdm,yhmc,kh,sfbys,byny,ksh from " + tabName;
//		}else{
//			//通用
//			sql = "select xh,xm,xb,mz,mzmc,zzmm,zzmmmc,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,xz,yhdm,yhmc,kh,sfbys,byny from " + tabName;
//		}
//		return sql ;
//	}
	
	/**
	 * 获取要导入的家庭信息字段
	 * @return String 
	 * */
	public String getColumnOfXsfzxx(){
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String tabName = "xsfzxxb";
		sql = "select xh,jtszd,yb,jjzk,jtcy1_xm,jtcy1_nl,jtcy1_zw,jtcy1_gx,jtcy1_zy,jtcy1_mz,jtcy1_zzmm,"
			+ "jtcy1_gzdz,jtcy1_lxdh1,jtcy1_lxdh2,jtcy2_xm,jtcy2_zw,jtcy2_gx,jtcy2_zy,jtcy2_mz,jtcy2_zzmm,"
			+ "jtcy2_gzdz,jtcy2_nl,jtcy2_lxdh1,jtcy2_lxdh2,jtcy3_xm,jtcy3_zw,jtcy3_gx,jtcy3_zy,jtcy3_mz,jtcy3_zzmm,"
			+ "jtcy3_gzdz,jtcy3_nl,jtcy3_lxdh1,jtcy3_lxdh2 from "
			+ tabName;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			//武汉理工大学
			sql = "select xh,jtszd,yb,jtcy1_xm,jtcy1_gx,jtcy1_lxdh1,jtcy1_gzdz,jtcy1_jtdz," +
					"jtcy2_xm,jtcy2_gx,jtcy2_lxdh1,jtcy2_gzdz,jtcy2_jtdz,jtcy3_xm,jtcy3_gx," +
					"jtcy3_lxdh1,jtcy3_gzdz,jtcy3_jtdz from " + tabName;
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_DBLYDX)){
			//东北林业大学
			sql = "select xh,jtszd,yb,jjzk,jtcy1_xm,jtcy1_zw,jtcy1_gx,"
				+ "jtcy1_gzdz,jtcy1_lxdh1,jtcy1_lxdh2,jtcy2_xm,jtcy2_zw,jtcy2_gx,"
				+ "jtcy2_gzdz,jtcy2_lxdh1,jtcy2_lxdh2,jtcy3_xm,jtcy3_zw,jtcy3_gx,"
				+ "jtcy3_gzdz,jtcy3_lxdh1,jtcy3_lxdh2 from "
				+ tabName;
		}
		return sql;
	}
	
	/**
	 * 获取家庭信息导出的字段
	 * @return String
	 * */
	public String getXsfzxxToExp(){
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String tabName = "view_xsjtxx";
		
		sql = "select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,jtszd,jtyb,jjzk,jtcy1_xm,jtcy1_nl,jtcy1_zw,jtcy1_gx,jtcy1_zy,jtcy1_mz,jtcy1_zzmm,"
			+ "jtcy1_gzdz,jtcy1_lxdh1,jtcy1_lxdh2,jtcy2_xm,jtcy2_zw,jtcy2_gx,jtcy2_zy,jtcy2_mz,jtcy2_zzmm,"
			+ "jtcy2_gzdz,jtcy2_nl,jtcy2_lxdh1,jtcy2_lxdh2,jtcy3_xm,jtcy3_zw,jtcy3_gx,jtcy3_zy,jtcy3_mz,jtcy3_zzmm,"
			+ "jtcy3_gzdz,jtcy3_nl,jtcy3_lxdh1,jtcy3_lxdh2 from "
			+ tabName;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			//武汉理工大学 
			sql = "select xh,xm,xb,xymc,zymc,bjmc,nj,jtszd,jtyb,jtcy1_xm,jtcy1_gx,jtcy1_lxdh1,jtcy1_gzdz,jtcy1_jtdz," +
					"jtcy2_xm,jtcy2_gx,jtcy2_lxdh1,jtcy2_gzdz,jtcy2_jtdz,jtcy3_xm,jtcy3_gx,jtcy3_lxdh1,jtcy3_gzdz,jtcy3_jtdz from " + tabName;
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_DBLYDX)){
			//东北林业大学
			sql = "select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,jtszd,jtyb,jjzk,jtcy1_xm,jtcy1_zw,jtcy1_gx,"
				+ "jtcy1_gzdz,jtcy1_lxdh1,jtcy1_lxdh2,jtcy2_xm,jtcy2_zw,jtcy2_gx,"
				+ "jtcy2_gzdz,jtcy2_lxdh1,jtcy2_lxdh2,jtcy3_xm,jtcy3_zw,jtcy3_gx,"
				+ "jtcy3_gzdz,jtcy3_lxdh1,jtcy3_lxdh2 from "
				+ tabName;
		}
		if(xxdm.equalsIgnoreCase("10649")){
			//东北林业大学
			sql = "select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,jtszd,jtyb,jjzk,jtcy1_xm,jtcy1_nl,jtcy1_zw,jtcy1_gx,jtcy1_zy,jtcy1_mz,jtcy1_zzmm,"
				+ "jtcy1_gzdz,jtcy1_lxdh1,jtcy1_lxdh2,jtcy2_xm,jtcy2_zw,jtcy2_gx,jtcy2_zy,jtcy2_mz,jtcy2_zzmm,"
				+ "jtcy2_gzdz,jtcy2_nl,jtcy2_lxdh1,jtcy2_lxdh2,jtcy3_xm,jtcy3_zw,jtcy3_gx,jtcy3_zy,jtcy3_mz,jtcy3_zzmm,"
				+ "jtcy3_gzdz,jtcy3_nl,jtcy3_lxdh1,jtcy3_lxdh2,zyshgxxx1,zyshgxxx2,zyshgxxx3 from "
				+ tabName;
		}
		return sql;
	}
	
	/**
	 * 判断记录是否存在
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName,String pk,String pkValue){
		String sql = "select count(*)num from " + tableName + " where " + pk + "=?";
		String result = dao.getOneRs(sql, new String[]{pkValue}, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		return Integer.parseInt(result)>0 ? true : false;
	}
	
	/**
	 * 修改学生信息
	 * @param xh
	 * @param col 要修改的列
	 * @param colValue 要修改的列的新值
	 * @param request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean modiStuinfo(String xh, String[] col, String[] colValue, HttpServletRequest request) throws Exception{
		boolean flag = false;
		if(stuDao.checkExists("xsxxb", "xh", xh)){//记录在xsxxb中存在直接修改
			flag = StandardOperation.update("xsxxb", col, colValue, "xh", xh, request);
		}else{//记录在xsxxb中不存在先添加后再修改
			flag = StandardOperation.update("xsxxb", "insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,rzrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx)(select xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,zsrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx from view_xsxxb where xh='" + xh + "')" , request);
			if(flag){
				flag = StandardOperation.update("xsxxb", col, colValue, "xh", xh, request);
			}
		}		
		return flag;
	}
	
	/**
	 * 获取新生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getNewStu(String xh) throws Exception {
		return stuDao.getNewStu(xh);
	}
	
	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		return stuDao.getStu(xh);
	}
	
	/**
	 * 获取入学日期
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getRxrq(String xh) throws Exception {
		return stuDao.getRxrq(xh);
	}
	
	/**
	 * 是否提交新生登记
	 * 
	 * @return
	 * @throws Exception
	 */
	public String isNewXs(String xh) throws Exception {
		return stuDao.getNewXs(xh);
	}
	
	/**
	 * 保存新生情况登记
	 * 
	 * @return
	 * @throws Exception
	 */
	public Boolean jsxxXsdjbSave(String xh, HttpServletRequest request)
			throws Exception {
		return stuDao.jsxxXsdjbSave(xh,request);
	}
	
	/**
	 * 获取参数设置返回的页面
	 * */
	public String getReturnPage(String xxdm){
		String page = "";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(Globals.XXDM_HZZY, "hzzy_conf");
		page = map.get(xxdm);
		
		page = StringUtils.isNull(page) ? "success"  : page;
		return page;
	}
	
	/**
	 * 获取新生登记列表
	 * @author luojw
	 */

	public ArrayList<String[]> getNewStuList(String tableName, CommanForm form,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return stuDao.getNewStuList(tableName, form, colList);
	}
	
	/**
	 * 获得新生综合素质记载
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getNewStuInfo(String xh) {
		return stuDao.getNewStuInfo(xh);
	}
	
	/**
	 * 获得学生基本信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}
	
	/**
	 * 获得新生综合素质记载
	 * 
	 * @author luojw
	 * @throws SQLException 
	 */
	public boolean saveNewStuInfo(CommanForm myForm) throws SQLException {
		return stuDao.saveNewStuInfo(myForm);
	}
	
	/**
	 * 删除新生情况登记
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean delNewStuInfo(String[] key)
			throws Exception {
		return stuDao.delNewStuInfo(key);
	}
	
	@SuppressWarnings("unchecked")
	public void printPayReport(WritableWorkbook wwb,String xydm,String nj){		
		StudentInfoDao dao = new StudentInfoDao();
		List<Object> o = dao.getGdzlData(xydm,nj);
		List<String> top = (List<String>) o.get(0);
		List<String[]> rs = (List<String[]>) o.get(1);
		String xymc = (String) o.get(2);
		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(18);
		 wcfTytle.setFont(wfTytle);
		 
		 
		 
		 if(Globals.XXDM_HBJJXY.equalsIgnoreCase(Base.xxdm)){
			 ws.mergeCells(0, 2, top.size()+4, 2);
		 }else{
			 
			 ws.mergeCells(0, 2, top.size()+2, 2);
			 
		 }
		 ws.addCell(new Label(0,2,xymc+"(系)"+nj+"届毕业生档案送交清单" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
//		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,3,"毕业生人数： "+rs.size()+ " 人",wcfTytle));
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,4,"序号",wcfTytle));
		 ws.addCell(new Label(1,4,"学号",wcfTytle));
		 ws.addCell(new Label(2,4,"姓名",wcfTytle));
		 for(int i=0;i<top.size();i++){
			 ws.addCell(new Label(i+3,4,top.get(i),wcfTytle));
		 }
		
		 if(Globals.XXDM_HBJJXY.equalsIgnoreCase(Base.xxdm)){
			 ws.addCell(new Label(top.size()+3,4,"是否提交",wcfTytle));
			 ws.addCell(new Label(top.size()+4,4,"在校档案情况",wcfTytle));;
		 }
		 
		 
		 for(int i = 0;i<rs.size();i++){
			 ws.addCell(new Label(0,5+i,String.valueOf(i+1),wcfTytle));
			 for(int g = 0;g<rs.get(i).length;g++){
				 ws.addCell(new Label(g+1,5+i,rs.get(i)[g],wcfTytle));
			 }
		 }
		 
		 
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 if(Globals.XXDM_HBJJXY.equalsIgnoreCase(Base.xxdm)){
			
			 ws.mergeCells(0, rs.size()+5, top.size()+4, rs.size()+5);
		
		 }else{
			 
			 ws.mergeCells(0, rs.size()+5, top.size()+2, rs.size()+5);
			 
		 }
		 
		 ws.addCell(new Label(0,rs.size()+5,"送交人(签字):                               接收人(签字):                        日期:												",wcfTytle));
		 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 获取要导出的字段
	 * @param zd
	 * @param tableName
	 * */
	public String getDczd(String zd, String tableName){		
		if(stuDao.checkExists("dcb", "zdssb||xxdm", tableName+Base.xxdm)){
			List<String[]> list = stuDao.getDrzdList(tableName);
			if(list != null && list.size()>0){
				zd="";
				int i=0;
				for(String[] arr : list){
					if(i!=0){
						zd += "," + arr[0];
					}else{
						zd += arr[0];
					}
					i++;
				}
			}
		}
		return zd;
	}
	/**
	 * 获取要导出的字段
	 * @param zd
	 * @param tableName
	 * */
	public String getDczd(String expColumnStr){
		StringBuilder zd = new StringBuilder();
		String[] columns = expColumnStr.split("!!SplitOne!!");
		if(columns != null && columns.length>0){
			for(String str : columns){
				if(StringUtils.isNotNull(str)){
					zd.append(str);
					zd.append(",");
				}
			}
		}
		if(StringUtils.isNotNull(zd.toString())){
			zd.deleteCharAt(zd.length()-1);
		}
		return zd.toString();
	}
	
	/**
	 * 获取页面表头
	 * @param act
	 * @return
	 */
	public String[] getTopTr(String act){
		String[] topTr=new String[]{};
		if("xxjy".equals(act)){
			topTr=new String[]{"学号","姓名","年级","身份证号","性别","证件性别","出生年月","证件出生年月","籍贯","证件籍贯"};
		}
		return topTr;
	}
	
	/**
	 * 获取身份证号校验信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getSfzhJyInfoList(CommanForm model) throws Exception{
		return stuDao.getSfzhJyInfoList(model);
	}
	
	/**
	 * 更新身份证号校验信息
	 * @param updateType
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateSfzhJyInfo(String[] updateType,CommanForm model,HttpServletRequest request) throws Exception{
		return stuDao.updateSfzhJyInfo(updateType, model, request);
	}
	
	/**
	 * 昆明学院个性化，住宿费查询，获取页面表头
	 * @return
	 * @author honglin
	 * @date 2012-9-10-22
	 */
	public List<HashMap<String, String>> getZsfTopTr(){
		String[] colList = new String[]{};
		colList = new String[]{"pk","xn","xh","xm","nj","xymc","zymc","bjmc","ldmc","qsh","yjje","sjje","sfjq"};
		String[] colListCN = new String[]{};
		colListCN = new String[]{"","学年","学号","姓名","年级","学院","专业","班级","楼栋名称","寝室号","应收金额","实收金额","是否缴清"};
		List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);
		return topTr;
	}
	
	/**
	 * 昆明学院个性化，住宿费查询（查询从教务推送过来的数据）
	 * @param model
	 * @return
	 * @throws Exception 
	 * @author honglin
	 * @date 2012-9-10-22
	 */
	public List<String[]>getZsfInfoList(CommanForm model,User user) throws Exception{
		return stuDao.getZsfInfoList(model,user);
	}
}
