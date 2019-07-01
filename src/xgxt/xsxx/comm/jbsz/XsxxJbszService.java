package xgxt.xsxx.comm.jbsz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.xml.XMLReader;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.MakeQuery;
import xgxt.xsxx.comm.XsxxCommService;
import xgxt.xtwh.xtwhOther.DmwhSelectMethod;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_基本设置_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XsxxJbszService extends XsxxCommService {

	XsxxJbszDAO dao = new XsxxJbszDAO();

	// ====================字段设置=============================
	
	/**
	 * 获得字段设置查询结果列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZdszRsList(XsxxJbszForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "search_sjy", "search_xgwz",
				"search_lrxz", "search_wkxz", "search_lrxs", "search_sfqy" };
		String[] queryLikeList = new String[] { "search_zdm", "search_ymxs",
				"search_xsmc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// 表
		String tableName = "xg_view_xsxx_zdszb";
		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());

		// 输入值
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * 保存字段设置
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveZdsz(XsxxJbszForm model, User user,
			HttpServletRequest request) throws Exception {

		// 保存表
		String tableName = "xg_xsxx_zdszb";
		// 主键
		String pk = "zd";
		// 主键值
		String[] pkValue = model.getZd();
		// 批量字段
		String[] arrzd = new String[] { "zd", "sjy", "xgwz", "zdm", "xsmc",
				"lrxz", "wkxz", "lrxs", "lyb", "sfqy" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);

		return saveInfoToDb(saveForm, model, user);

	}

	/**
	 * 获得启用字段列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getQyzdList(XsxxJbszForm model) {

		String tableName = "xg_view_xsxx_zdszb";
		StringBuilder query = new StringBuilder();
		query.append(" where 1 = 1 ");
		query.append(" and search_sfqy = ? ");
		query.append(" and not exists(select 1 from ");
		query.append(" xg_xsxx_xsqwzb b where ");
		query.append(" xg_view_xsxx_zdszb.search_zd = b.zd) ");

		String[] inPutList = new String[] { "是" };
		String[] colList = new String[] { "search_zd", "search_ymxs" };
		String sql = "";
		return getRsList(tableName, query.toString(), inPutList, colList, sql);
	}

	/**
	 * 获得字段设置列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getZdszRsList() {

		// 表
		String tableName = "xg_view_xsxx_zdszb";
		// 输出值
		String[] colList = new String[] { "search_zd", "search_sjy",
				"search_xgwz", "search_ymxs", "search_lyb", "search_sfqy",
				"dm", "mc", "lydmwh" };

		return getRsList(tableName, "", new String[] {}, colList, "");
	}

	/**
	 * 根据字段设置创建新视图
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean createNewView(XsxxJbszForm model, User user)
			throws Exception {

		boolean flag = false;

		// 字段列表
		List<HashMap<String, String>> zdList = getZdszRsList();

		if (zdList != null && zdList.size() > 0) {

			// 字段
			String[] zd = new String[zdList.size()];
			// 数据源
			String[] sjy = new String[zdList.size()];
			// 学工为准
			String[] xgwz = new String[zdList.size()];
			// 页面显示
			String[] ymxs = new String[zdList.size()];
			// 来源表
			String[] lyb = new String[zdList.size()];
			// 是否启用
			String[] sfqy = new String[zdList.size()];
			// 来源表代码
			String[] dm = new String[zdList.size()];
			// 来源表名称
			String[] mc = new String[zdList.size()];
			// 来源代码维护
			String[] lydmwh = new String[zdList.size()];

			for (int i = 0; i < zdList.size(); i++) {

				HashMap<String, String> map = zdList.get(i);

				zd[i] = map.get("search_zd");
				// 数据源
				sjy[i] = map.get("search_sjy");
				// 学工为准
				xgwz[i] = map.get("search_xgwz");
				// 页面显示
				ymxs[i] = map.get("search_ymxs");
				// 来源表
				lyb[i] = map.get("search_lyb");
				// 是否启用
				sfqy[i] = map.get("search_sfqy");
				// 来源表代码
				dm[i] = map.get("dm");
				// 来源表名称
				mc[i] = map.get("mc");
				// 来源代码维护
				lydmwh[i] = map.get("lydmwh");
			}

			// 本科生学生信息表字段
			String[] bksxx_zd = getTableZd("bks_xsjbxx");

			// 本科生学生信息表字段（存在于配置表中的字段）
			String[] output_zd = getRepeatArrValue(zd, bksxx_zd);

			if (zd != null && zd.length > 0) {

				// 建视图的语句
				StringBuilder createSql = new StringBuilder();
				createSql.append("create or replace ");
				createSql.append("view xg_view_xsxxb as ");
				createSql.append("select ");

				// 学工为准的判断
				StringBuilder xgwzSql = new StringBuilder();
				xgwzSql.append("select ");

				// 本科生学生基本信息
				StringBuilder bksSql = new StringBuilder();
				bksSql.append("select ");

				// 学生信息
				StringBuilder stuSql = new StringBuilder();
				stuSql.append("union all select ");

				// 视图备注
				ArrayList<String> commentList = new ArrayList<String>();

				for (int i = 0; i < zd.length; i++) {
	
					createSql.append(getEspecialZd(zd[i], lyb[i], dm[i], mc[i],
							lydmwh[i]));

					// 是否学工为准
					if ("是".equalsIgnoreCase(xgwz[i])) {
						xgwzSql.append("b.");
					} else {
						xgwzSql.append("c.");
					}
					xgwzSql.append(zd[i]);

					// 字段在本科生学生信息表中是否存在
					if (isExistInArr(output_zd, zd[i])) {
						bksSql.append(getEspecialGs(zd[i]));
					} else {
						bksSql.append("'' " + zd[i]);
					}

					stuSql.append(zd[i]);

					if (i != zd.length - 1) {
						createSql.append(",");
						xgwzSql.append(",");
						bksSql.append(",");
						stuSql.append(",");
					}
					
					//视图备注
					getViewComment(commentList,zd[i], ymxs[i], lyb[i], lydmwh[i]);
				}

				bksSql.append(" from bks_xsjbxx a ");
				bksSql.append(" where not exists (select 1 from ");
				bksSql.append(" xsxxb b where a.xh = b.xh) ");

				stuSql.append(" from xsxxb a ");

				createSql.append(" from (");
				createSql.append(xgwzSql);
				createSql.append(" from (");
				createSql.append(bksSql);
				createSql.append(stuSql);
				createSql.append(") a ");
				createSql.append("left join xsxxb b on a.xh = b.xh ");
				createSql.append("left join bks_xsjbxx c on a.xh = c.xh ");
				createSql.append(") a where a.xh is not null");

				DAO dao = DAO.getInstance();

				// 执行建视图语句
				flag = dao.creatView(createSql.toString(), new String[]{});
				
				if (flag) {
					//执行视图备注
					if(commentList!=null && commentList.size()>0){
						for(int i=0;i<commentList.size();i++){
							dao.runUpdateTab(commentList.get(i));
						}
					}
				}
				//System.out.println(createSql);
				//System.out.println(commentSql);
			}
		}
		return flag;

	}

	/**
	 * 获得特殊字段(格式)
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private String getEspecialGs(String zd) {

		StringBuilder returnZd = new StringBuilder();
		// 年级
		if ("nj".equalsIgnoreCase(zd)) {
			returnZd.append("to_char(nj) nj");
		}
		// 学制
		else if ("xz".equalsIgnoreCase(zd)) {
			returnZd.append("substr(xz, 0, 1) xz");
		}
		// 其他
		else {
			returnZd.append(zd);
		}

		return returnZd.toString();
	}

	/**
	 * 获得特殊字段(取值)
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	private String getEspecialZd(String zd, String lyb, String dm, String mc,
			String lydmwh) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String zddm = "";
		String zdmc = "";

		if (zd.length() > 1) {
			zddm = zd.substring(zd.length() - 2, zd.length());
		}

		if ("dm".equalsIgnoreCase(zddm)) {
			zdmc = zd.substring(0, zd.length() - 2) + "mc";
		} else {
			zdmc = zd + "mc";
		}

		StringBuilder returnZd = new StringBuilder();
		
		// 性别
		if ("xb".equalsIgnoreCase(zd)) {

			// 从配置文件抽取男女的配置
			String man = XMLReader.getFlowControl("comm", "男");
			String woman = XMLReader.getFlowControl("comm", "女");

			returnZd.append("decode(a.xb,'" + man + "','男'");
			returnZd.append(",'" + woman + "','女',xb) xb");
		}
		// 年级
		else if ("nj".equalsIgnoreCase(zd)) {
			returnZd.append("a." + zd + " dqszj,");
			returnZd.append("a." + zd);
		}
		// 学院
		else if ("xydm".equalsIgnoreCase(zd)) {
			returnZd.append("(select distinct b.xymc from view_njxyzybj_all b where a.xydm = b.xydm) xymc,");
			returnZd.append("(select distinct b.xymc from view_njxyzybj_all b where a.xydm = b.xydm) xy,");
			returnZd.append("a.xydm bmdm,");
			returnZd.append("a.xydm");
		}
		// 专业
		else if ("zydm".equalsIgnoreCase(zd)) {
			returnZd.append("(select distinct b.zymc from view_njxyzybj_all b where a.zydm = b.zydm) zymc,");
			returnZd.append("(select distinct b.zymc from view_njxyzybj_all b where a.zydm = b.zydm) zy,");
			returnZd.append("a.zydm");
		}
		//班级
		else if ("bjdm".equalsIgnoreCase(zd)) {
			returnZd.append("(select distinct b.bjmc from view_njxyzybj_all b where a.bjdm = b.bjdm) bjmc,");
			returnZd.append("(select distinct b.bjmc from view_njxyzybj_all b where a.bjdm = b.bjdm) xzb,");
			returnZd.append("a.bjdm");
		}
		// 生源地,籍贯，户口所在地
		else if ("syd".equalsIgnoreCase(zd)||"jg".equalsIgnoreCase(zd)||"hkszd".equalsIgnoreCase(zd)) {
			
			//来源地区
			if ("syd".equalsIgnoreCase(zd)){
				returnZd.append("a." + zd + " lydq,");
				returnZd.append("(case when length(a.syd) > 6 then substr(a.syd, 0, 6) else '' end) syds,");
				returnZd.append("(select b.sydq from dmk_sydq b where b.sydqdm = substr(a.syd, 0, 6)) sydsmc,");
			}
			
			// 来源表非空（有地方维护）
			if (!Base.isNull(lyb)) {
				
				returnZd.append("a." + zd + ",");

				returnZd.append("(select b.sydq from dmk_sydq b where b.sydqdm = substr(a." + zd + ", 0, 6))||");
				returnZd.append("(select b.qxmc from dmk_qx b where b.qxdm = substr(a." + zd + ", 8, 6))||");
				returnZd.append("(select b.qxmc from dmk_qx b where b.qxdm = substr(a." + zd + ", 15, 6))"
								+ zdmc);

			} else {
				returnZd.append("a." + zd);
			}
		}
		// 其他
		else {
			// 数据来源于代码维护(死代码)
			if ("yes".equalsIgnoreCase(lydmwh)) {
				// 方法名
				String selmethod = lyb;
				ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>) DmwhSelectMethod.class
						.getMethod(selmethod, (Class[]) null).invoke(null,
								(Object[]) null);

				if (list != null && list.size() > 0) {

					returnZd.append("a." + zd + ",");
					returnZd.append(" case ");

					for (int i = 0; i < list.size(); i++) {

						HashMap<String, String> map = list.get(i);
						zddm = map.get("dm");
						String zd_mc = map.get("mc");

						returnZd.append(" when a." + zd + " = '" + zddm + "'");
						returnZd.append(" then '" + zd_mc+"'");
					}
					returnZd.append(" else a." + zd + " end " + zdmc);
				}
			} else {
				// 来源表非空（有地方维护）
				if (!Base.isNull(lyb)) {

					returnZd.append("a." + zd + ",");
					returnZd.append("(select distinct b." + mc + " from ");
					returnZd.append(lyb + " b where a." + zd);
					returnZd.append("= b." + dm + " and rownum = 1)  " + zdmc);
				} else {
					returnZd.append("a." + zd);
				}
			}
		}

		return returnZd.toString();
	}

	/**
	 * 获得视图备注
	 * 
	 * @author 伟大的骆
	 */
	private void getViewComment(List<String> commentList,String zd, String ymxs, String lyb,
			String lydmwh) {

		String zddm = "";
		String zdmc = "";

		if (zd.length() > 1) {
			zddm = zd.substring(zd.length() - 2, zd.length());
		}

		if ("dm".equalsIgnoreCase(zddm)) {
			zdmc = zd.substring(0, zd.length() - 2) + "mc";
		} else {
			zdmc = zd + "mc";
		}

		StringBuilder comment = new StringBuilder();
		comment.append("comment on column xg_view_xsxxb.");
		comment.append(zd);
		comment.append(" is '" + ymxs + "'");

		commentList.add(comment.toString());
		
		comment = new StringBuilder();

		// 性别
		if ("xb".equalsIgnoreCase(zd)) {

			comment.append("comment on column xg_view_xsxxb.xb");
			comment.append(" is '性别'");

			commentList.add(comment.toString());
		}
		// 年级
		else if ("nj".equalsIgnoreCase(zd)) {
			comment.append("comment on column xg_view_xsxxb.nj");
			comment.append(" is '年级'");

			commentList.add(comment.toString());
		}
		// 学院
		else if ("xydm".equalsIgnoreCase(zd)) {
			comment.append("comment on column xg_view_xsxxb.xy");
			comment.append(" is '"+Base.YXPZXY_KEY+"'");

			commentList.add(comment.toString());
			
			comment = new StringBuilder();
			
			comment.append("comment on column xg_view_xsxxb.xydm");
			comment.append(" is '"+Base.YXPZXY_KEY+"代码'");

			commentList.add(comment.toString());
			
			comment = new StringBuilder();
			
			comment.append("comment on column xg_view_xsxxb.xymc");
			comment.append(" is '"+Base.YXPZXY_KEY+"名称'");

			commentList.add(comment.toString());
			
			comment = new StringBuilder();
			
			comment.append("comment on column xg_view_xsxxb.bmdm");
			comment.append(" is '部门代码'");

			commentList.add(comment.toString());
			
			comment = new StringBuilder();
		}
		// 专业
		else if ("zydm".equalsIgnoreCase(zd)) {

			comment.append("comment on column xg_view_xsxxb.zydm");
			comment.append(" is '专业代码'");

			commentList.add(comment.toString());

			comment = new StringBuilder();

			comment.append("comment on column xg_view_xsxxb.zymc");
			comment.append(" is '专业名称'");

			commentList.add(comment.toString());

			comment = new StringBuilder();
		}
		// 班级
		else if ("bjdm".equalsIgnoreCase(zd)) {
			
			comment.append("comment on column xg_view_xsxxb.bjdm");
			comment.append(" is '班级代码'");

			commentList.add(comment.toString());
			
			comment = new StringBuilder();
			
			comment.append("comment on column xg_view_xsxxb.bjmc");
			comment.append(" is '班级名称'");

			commentList.add(comment.toString());
			
			comment = new StringBuilder();
			
			comment.append("comment on column xg_view_xsxxb.xzb");
			comment.append(" is '行政班'");

			commentList.add(comment.toString());
			
			comment = new StringBuilder();
		}
		//生源地,籍贯，户口所在地
		else if ("syd".equalsIgnoreCase(zd)||"jg".equalsIgnoreCase(zd)||"hkszd".equalsIgnoreCase(zd)) {
			
			if ("syd".equalsIgnoreCase(zd)){
				
				comment.append("comment on column xg_view_xsxxb.lydq is '来源地区'");
				commentList.add(comment.toString());
				
				comment = new StringBuilder();
				
				comment.append("comment on column xg_view_xsxxb.syds is '生源地省'");
				commentList.add(comment.toString());
				
				comment = new StringBuilder();
				comment.append("comment on column xg_view_xsxxb.sydsmc is '生源地省名称'");
				commentList.add(comment.toString());
				
				comment = new StringBuilder();
			}
			
			// 来源表非空（有地方维护）
			if (!Base.isNull(lyb)) {
				comment.append("comment on column xg_view_xsxxb.");
				comment.append(zdmc);
				comment.append(" is '生源地'");

				commentList.add(comment.toString());
			}
		}
		// 其他
		else {
			// 数据来源于代码维护(死代码)
			if ("yes".equalsIgnoreCase(lydmwh)) {
				comment.append("comment on column xg_view_xsxxb.");
				comment.append(zdmc);
				comment.append(" is '" + ymxs + "名称'");
				
				commentList.add(comment.toString());
			} else {
				// 来源表非空（有地方维护）
				if (!Base.isNull(lyb)) {
					comment.append("comment on column xg_view_xsxxb.");
					comment.append(zdmc);
					comment.append(" is '" + ymxs + "名称'");
					
					commentList.add(comment.toString());
				}
			}
		}
	}

	/**
	 * 验证字段设置可否保存
	 * 
	 * @author 伟大的骆
	 */
	public String checkSaveZdsz(XsxxJbszForm model) {
		
		//获得已应被分配字段列表（保存字段）
		List<HashMap<String, String>> list = dao.getYfpZdList(model);
		
		String message = "";
		
		if(list!=null && list.size()>0){
			
			message = "以下字段已经被应用于学生信息，\n不可设置为不启用：\n";
			message+="(";
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				String xsmc = map.get("xsmc");
				message += (i+1)+":"+xsmc;
				if (i != list.size() - 1) {
					message += ",";
				}
				
				if(i!=0&&i%4==0){
					message += "\n";
				}
			}
			message+=")";
		}
		return message;
	}
	
	// ====================字段设置 end=============================

	// ====================显示区设置=============================
	/**
	 * 获得显示区查询结果列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXsqRsList(XsxxJbszForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "xsqsfqy", "xsqsfzd", "xsqsfzk" };
		String[] queryLikeList = new String[] { "search_xsqmc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// 表
		String tableName = "xg_view_xsxx_xsqsz";
		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(" order by to_number(xsqxssx) ");

		// 输入值
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * 保存显示区设置
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveXsqsz(XsxxJbszForm model, User user) throws Exception {

		return dao.saveXsqsz(model, user);

	}

	/**
	 * 保存页面设置
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveYmsz(XsxxJbszForm model, User user) throws Exception {

		// 清空显示区
		boolean flag = delXsqInfo(user);

		String[] xsqdm = model.getXsqdm();

		// 保存显示区
		if (flag) {
			flag = saveXsq(model, user);
			model.setXsqdm(xsqdm);
		}

		// 保存显示区合并行
		if (flag) {
			flag = saveXsqHbh(model, user);
			model.setXsqdm(xsqdm);
		}

		// 保存显示区字段位置
		if (flag) {
			flag = saveXsqZdwz(model, user);
			model.setXsqdm(xsqdm);
		}
		return flag;
	}

	/**
	 * 清空显示区信息
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean delXsqInfo(User user) throws Exception {

		// 清空显示区
		boolean flag = dao.delXsq();

		// 清空合并行
		if (flag) {
			flag = dao.delHbh();
		}

		// 清空字段位置
		if (flag) {
			flag = dao.delZdwz();
		}

		return flag;
	}
	
	/**
	 * 保存显示区
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveXsq(XsxxJbszForm model, User user) throws Exception {

		boolean flag = true;

		if (model.getXsqdm() != null && model.getXsqdm().length > 0) {
			// 保存表
			String tableName = "xg_xsxx_xsqszb";
			// 主键
			String pk = "xsqdm";
			// 主键值
			String[] pkValue = model.getXsqdm();
			// 批量字段
			String[] arrzd = new String[] { "xsqdm", "xsqmc", "szhs", "zpxs",
					"zpwz", "zpszhs", "xssx" };

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);
			saveForm.setArrzd(arrzd);

			flag = saveInfoToDb(saveForm, model, user);
		}
		return flag;
	}

	/**
	 * 保存显示区合并行
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveXsqHbh(XsxxJbszForm model, User user) throws Exception {

		boolean flag = true;

		if (model.getXsqdm() != null && model.getXsqdm().length > 0) {
			// 保存表
			String tableName = "xg_xsxx_xsqhbb";
			// 主键
			String pk = "xsqdm";
			// 主键值
			String[] pkValue = model.getXsqdm();
			
			// 批量字段
			String[] arrzd = new String[] { "xsqdm", "hbh" };

			model.setXsqdm(model.getHbh_szxsq());

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);
			saveForm.setArrzd(arrzd);

			flag = saveInfoToDb(saveForm, model, user);
		}
		return flag;

	}

	/**
	 * 保存显示区字段位置
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveXsqZdwz(XsxxJbszForm model, User user) throws Exception {

		boolean flag = true;

		if (model.getXsqdm() != null && model.getXsqdm().length > 0) {
			// 保存表
			String tableName = "xg_xsxx_xsqwzb";
			// 主键
			String pk = "xsqdm";
			// 主键值
			String[] pkValue = model.getXsqdm();

			// 批量字段
			String[] arrzd = new String[] { "xsqdm", "zd", "szh", "szl" };

			model.setXsqdm(model.getZd_szxsq());

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);
			saveForm.setArrzd(arrzd);

			flag = saveInfoToDb(saveForm, model, user);
		}
		return flag;

	}

	/**
	 * 获得启用字段列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsqList(XsxxJbszForm model) {

		String tableName = "xg_view_xsxx_xsqsz";
		StringBuilder query = new StringBuilder();
		ArrayList<String> inPutList = new ArrayList<String>();

		// 显示区是否启用
		if (!Base.isNull(model.getXsqsfqy())) {
			query.append(" where xsqsfqy=? ");
			inPutList.add(model.getXsqsfqy());
		}

		query.append(" order by to_number(xsqxssx) ");

		String[] colList = new String[] { "xsqdm", "xsqmc", "szhs", "zpxs",
				"zpwz", "zpszhs", "xssx", "sfzd" ,"sfzk"};

		String sql = "";

		return getRsList(tableName, query.toString(), inPutList
				.toArray(new String[] {}), colList, sql);
	}

	/**
	 * 获得显示区情况
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, Object>> getXsqInfoList(XsxxJbszForm model) {

		// 显示区列表
		List<HashMap<String, String>> xsqList = getXsqList(model);
		// 显示区列表
		List<HashMap<String, String>> hbhList = getXsqhbhList(model);
		// 字段位置列表
		List<HashMap<String, String>> zdwzList = getXsqZdwzList(model);
		// 显示区列表
		List<HashMap<String, Object>> rsList = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < xsqList.size(); i++) {

			HashMap<String, String> xsqMap = xsqList.get(i);
			// 显示区代码
			String xsqdm = xsqMap.get("xsqdm");
			// 显示区名称
			String xsqmc = xsqMap.get("xsqmc");
			// 所占行数
			String szhs = xsqMap.get("szhs");
			// 照片显示
			String zpxs = xsqMap.get("zpxs");
			// 照片位置
			String zpwz = xsqMap.get("zpwz");
			// 照片所占行
			String zpszhs = xsqMap.get("zpszhs");
			// 显示顺序
			String xssx = xsqMap.get("xssx");

			HashMap<String, Object> map = new HashMap<String, Object>();

			// 行列表
			List<HashMap<String, Object>> rowList = new ArrayList<HashMap<String, Object>>();

			for (int j = 1; j <= Integer.parseInt(szhs); j++) {

				HashMap<String, Object> row = new HashMap<String, Object>();

				// 所在行
				row.put("szh", String.valueOf(j));

				// 需要照片显示且不是第一行
				if ("是".equalsIgnoreCase(zpxs) && j <= Integer.parseInt(zpszhs)) {
					if (j == 1) {
						// 是否首行
						row.put("isFirst", "yes");
					}
					row.put("isZp", "yes");
				} else {
					row.put("isZp", "no");
				}

				// ===================合并处理===========================
				// 需要合并
				boolean isHb = false;

				for (int k = 0; k < hbhList.size(); k++) {

					HashMap<String, String> hbhMap = hbhList.get(k);
					// 所在显示区
					String szxsq = hbhMap.get("xsqdm");
					// 所在行
					String hbh = hbhMap.get("hbh");

					if (xsqdm.equalsIgnoreCase(szxsq)
							&& hbh.equalsIgnoreCase(String.valueOf(j))) {
						isHb = true;
						break;
					}
				}

				if (isHb) {
					row.put("isHb", "yes");
				} else {
					row.put("isHb", "no");
				}
				// ===================合并处理end===========================

				// ===================字段位置===========================

				// 字段位置
				HashMap<String, String> zdMap = new HashMap<String, String>();
				zdMap.put("left", "");
				zdMap.put("right", "");

				for (int k = 0; k < zdwzList.size(); k++) {
					HashMap<String, String> zdwzMap = zdwzList.get(k);
					// 所在显示区
					String szxsq = zdwzMap.get("xsqdm");
					// 所在行
					String szh = zdwzMap.get("zdszh");

					if (xsqdm.equalsIgnoreCase(szxsq)
							&& szh.equalsIgnoreCase(String.valueOf(j))) {

						// 字段
						String zd = zdwzMap.get("zd");
						// 字段名
						String zdm = zdwzMap.get("zdm");
						// 所在列
						String szl = zdwzMap.get("zdszl");
						szl = "1".equalsIgnoreCase(szl) ? "left" : "right";

						zdMap.put(szl + "zd", zd);
						zdMap.put(szl + "zdm", zdm);
						zdMap.put(szl, "yes");
					}
				}

				row.put("zdMap", zdMap);

				// ===================字段位置 end===========================

				rowList.add(row);
			}

			map.put("xsqdm", xsqdm);
			map.put("xsqmc", xsqmc);
			map.put("szhs", szhs);
			map.put("zpxs", zpxs);
			map.put("zpwz", zpwz);
			map.put("zpszhs", zpszhs);
			map.put("xssx", xssx);
			map.put("rowList", rowList);

			rsList.add(map);
		}

		return rsList;
	}

	/**
	 * 获得最大显示区代码
	 * 
	 * @author 伟大的骆
	 */
	public String getMaxXsqdm() {
		return dao.getMaxXsqdm();
	}

	/**
	 * 获得合并行信息
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getXsqhbhList(XsxxJbszForm model) {

		String tableName = "xg_xsxx_xsqhbb";
		StringBuilder query = new StringBuilder();
		ArrayList<String> inPutList = new ArrayList<String>();

		String[] colList = new String[] { "xsqdm", "hbh" };
		String sql = "";

		return getRsList(tableName, query.toString(), inPutList
				.toArray(new String[] {}), colList, sql);
	}

	/**
	 * 获得显示区字段位置信息
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsqZdwzList(XsxxJbszForm model) {

		String tableName = "xg_view_xsxx_xsqzd";
		StringBuilder query = new StringBuilder();
		if (!Base.isNull(model.getSearch_sfqy())) {
			query.append(" where sfqy='" + model.getSearch_sfqy() + "' ");
		}

		query.append(" order by xsqdm,zdszh,zdszl ");

		ArrayList<String> inPutList = new ArrayList<String>();

		String[] colList = new String[] { "xsqdm", "zd", "zdm", "zdszh","xgwz",
				"zdszl", "lrxz", "wkxz", "lrxs", "sfqy", "sfzd", "sfzk" };
		String sql = "";

		return getRsList(tableName, query.toString(), inPutList
				.toArray(new String[] {}), colList, sql);
	}

	// ====================显示区设置 end=============================

}