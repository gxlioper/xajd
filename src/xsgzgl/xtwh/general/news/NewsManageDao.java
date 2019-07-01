package xsgzgl.xtwh.general.news;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import oracle.sql.CLOB;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

public class NewsManageDao extends CommDAO {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	/**
	 * 获取新闻列表
	 * 
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getNewsList(NewsManageForm myForm) throws Exception {
		// TODO 自动生成方法存根
		String[] colList = new String[] { "NEWSID", "title", "NEWSTITLE",
				"PUBTIME", "PUBER", "FBRBMMC", "TOWHO", "SFFB", "SFZD","YYDMD","TABLENAME" };
		String bt = myForm.getBt();
		String kssj = myForm.getKssj();
		String jssj = myForm.getJssj();
		String sfzd = myForm.getSfzd();
		String sffb = myForm.getSffb();
		// 查询条件拼接
		StringBuffer query = new StringBuffer(" where 1=1 ");
		String[] inputV;
		if (!Base.isNull(bt)) {
			query.append(" and NEWSTITLE like '%'||?||'%'");
			inputV = new String[] { bt };
		} else {
			inputV = new String[] {};
		}
		if (!Base.isNull(kssj) && !Base.isNull(jssj)) {
			query
					.append(" and to_date(pubtime,'yyyy-mm-dd hh24:mi:ss')>=to_date('"
							+ kssj
							+ "','yyyy-mm-dd') and to_date(pubtime,'yyyy-mm-dd hh24:mi:ss') <to_date('"
							+ jssj + "','yyyy-mm-dd')+1");
		} else if (!Base.isNull(jssj) && Base.isNull(kssj)) {
			query
					.append(" and to_date(pubtime,'yyyy-mm-dd hh24:mi:ss')<to_date('"
							+ jssj + "','yyyy-mm-dd')+1");
		} else if (!Base.isNull(kssj) && Base.isNull(jssj)) {
			query
					.append("  and to_date(pubtime,'yyyy-mm-dd hh24:mi:ss')>=to_date('"
							+ kssj + "','yyyy-mm-dd')");
		}
		if (!Base.isNull(sfzd)) {
			query.append(" and sfzd='" + sfzd + "'");
		}
		if (!Base.isNull(sffb)) {
			query.append(" and sffb='" + sffb + "'");
		}

		if (StringUtils.isNotNull(myForm.getTypeid())) {
			// 如果是通知通告，则查询无对应类型id的数据，也就是旧数据
			if (myForm.getTypeid().equals(NewsManageService._TYPE_TZTG)) {
				query.append(" and (typeid is null ");
				// 后续修改的数据通知通告的类型为 -1
				query.append(" or typeid='" + NewsManageService._TYPE_TZTG + "')");
			} else {
				query.append(" and typeid ='");
				query.append(myForm.getTypeid() + "'");
			}
		}
		String sql = "select rownum r, a.* from(select NEWSID,NEWSTITLE title,(case when length(NEWSTITLE) > 20 then substr(NEWSTITLE, 20) || '...' else NEWSTITLE end) NEWSTITLE,PUBTIME,(select xm from yhb a where a.yhm=puber) PUBER,(select b.bmmc from zxbz_xxbmdm b left join yhb a on (a.szbm=b.bmdm) where a.yhm = puber) FBRBMMC,decode(towho,'all_teastu','全校师生','some_teastu','指定师生','all_tea','全校教师','some_tea','指定教师','all_stu','全校学生','some_stu','指定学生','')TOWHO,SFFB, SFZD,NEWSID YYDMD,TABLENAME from newscontent"
				+ query + " order by sffb desc,sfzd desc,pubtime desc,xgsj desc)a ";
		return CommonQueryDAO.commonQuery(sql, "", inputV, colList, myForm);
	}

	/**
	 * 删除新闻
	 * 
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean delNews(NewsManageForm myForm) throws Exception {
		// TODO 自动生成方法存根
		DAO dao = DAO.getInstance();
		boolean flag = true;

		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		String[] tableArr = myForm.getTableArr().split(",");
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < primarykey_checkVal.length; i++) {
			params.add(new String[] { primarykey_checkVal[i] });
		}

		try {
			flag = saveArrDate("delete from newscontent where newsid = ?",
					params, "");
			String delSql = null;
			for (int i = 0; i < tableArr.length; i++) {
				if(!"".equals(tableArr[i])&&null!=tableArr[i]){
					delSql = "delete from "+tableArr[i]+" where newsid=?";
					dao.runDelete(delSql, new String[]{primarykey_checkVal[i]});
				}
				
				
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 新闻发布
	 * 
	 * @param myForm
	 * @return
	 */
	public boolean newFb(NewsManageForm myForm) {
		// TODO 自动生成方法存根
		boolean flag = true;
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		String fbsj = GetTime.getTimeByFormat(DATE_FORMAT);
		for (int i = 0; i < primarykey_checkVal.length; i++) {
			params.add(new String[] { fbsj, primarykey_checkVal[i] });
		}

		try {
			flag = saveArrDate(
					"update  newscontent set sffb='是', pubtime = ? where newsid = ?",
					params, "");
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 取消发布
	 * 
	 * @param myForm
	 * @return
	 */
	public boolean newsQxfb(NewsManageForm myForm) {
		// TODO 自动生成方法存根
		boolean flag = true;
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < primarykey_checkVal.length; i++) {
			params.add(new String[] { primarykey_checkVal[i] });
		}

		try {
			flag = saveArrDate(
					"update  newscontent set sffb='否' where newsid = ?",
					params, "");
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 置顶保存
	 * 
	 * @param myForm
	 * @return
	 */
	public boolean newsZd(NewsManageForm myForm) {
		// TODO 自动生成方法存根
		boolean flag = true;
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < primarykey_checkVal.length; i++) {
			params.add(new String[] { primarykey_checkVal[i] });
		}

		try {
			flag = saveArrDate(
					"update  newscontent set sfzd='是' where newsid = ?",
					params, "");
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 置顶取消
	 * 
	 * @param myForm
	 * @return
	 */
	public boolean newsQxzd(NewsManageForm myForm) {
		// TODO 自动生成方法存根
		boolean flag = true;
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < primarykey_checkVal.length; i++) {
			params.add(new String[] { primarykey_checkVal[i] });
		}

		try {
			flag = saveArrDate(
					"update  newscontent set sfzd='否' where newsid = ?",
					params, "");
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 新闻详细信息
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void newsIfo(HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		DAO dao = DAO.getInstance();

		String tagId = request.getParameter("newsId");
		String[] tit = new String[] { "newsid", "newstitle", "pubtime",
				"puber", "newscont" };
		String sql = "select newsid,newstitle,pubtime,(select xm from yhb c where a.puber=c.yhm)puber,newscont from "
				+ "newscontent a where newsid=?";
		String[] rs = dao.getOneRs(sql, new String[] { tagId }, tit);
		rs = (rs == null) ? new String[tit.length] : rs;
		for (int i = 0; i < tit.length; i++) {
			request.setAttribute(tit[i], Base.isNull(rs[i]) ? " " : rs[i]);
		}
		CLOB clob = dao.getOneClob(sql, new String[] { tagId }, "newscont");
		String msg = clob.getSubString(1L, (int) clob.length());
		request.setAttribute("newscont", msg);

	}

	/**
	 * 查询要修改的新闻
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void newsUpdate(HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		DAO dao = DAO.getInstance();
		String sql = "select newstitle,newscont,towho,xwlx newsType,fwfs,typeid from "
				+ "newscontent where newsid=?";
		String newsId = request.getParameter("newsId");
		CLOB clob = dao.getOneClob(sql, new String[] { newsId }, "newscont");
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getMap(sql, new String[] { newsId }, new String[] {
				"newstitle", "toWho", "newsType", "fwfs", "typeid" });
		request.setAttribute("editorid", clob.getSubString(1L, (int) clob
				.length()));
		String newsTit = map.get("newstitle");
		request.setAttribute("typeid",map.get("typeid"));
		request.setAttribute("newstit", newsTit);
		request.setAttribute("map", map);
		request.setAttribute("newsId", newsId);
	}

	/**
	 * 修改保存
	 * 
	 * @param request
	 * @throws Exception
	 */
	public boolean updateNews(HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		DAO dao = DAO.getInstance();
		String newsTitle = DealString.toGBK(request.getParameter("newsTitle"));
		String toWho = request.getParameter("toWho");
		String content = DealString.toGBK(request.getParameter("editorid"));
		String newsId = request.getParameter("newsId");
		request.setAttribute("newsId", newsId);
		String typeid = request.getParameter("typeid");
		String xgsj = GetTime.getTimeByFormat(DATE_FORMAT);
		String sql = "update newscontent set newstitle=?,towho=?,newscont=?,typeid=?,xgsj=? where newsid=?";
		boolean result = dao.runUpdate(sql, new String[] { newsTitle, toWho,
				content, typeid, xgsj, newsId });
		request.setAttribute("newstit", newsTitle);
		request.setAttribute("editorid", content);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("toWho", toWho);
		request.setAttribute("map", map);
		return result;
	}

	/**
	 * 添加保存
	 * 
	 * @param request
	 * @throws Exception
	 */
	public boolean saveNews(HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		DAO dao = DAO.getInstance();
		String newsTitle = DealString.toGBK(request.getParameter("newsTitle"));
		String toWho = request.getParameter("toWho");
		String content = DealString.toGBK(request.getParameter("editorid"));
		String newsId = (String) request.getAttribute("newsId");
		//request.setAttribute("newsId", newsId);
		String puber = (String) request.getSession().getAttribute("userName");
		String sffb = request.getParameter("sffb");
		String sfzd = request.getParameter("sfzd");
		String typeid = request.getParameter("typeid");
		String tableName = (String)request.getAttribute("tableName");
		String sql = "insert into newscontent(newstitle,towho,newscont,puber,newsId,sffb,sfzd,typeid,tablename) values(?,?,?,?,?,?,?,?,?)";
		boolean result = dao.insert(sql, new String[] { newsTitle, toWho,
				content, puber,newsId,sffb, sfzd, typeid,tableName });
		return result;
	}

	/**
	 * 
	 * @描述:获取新闻类型列表
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-18 上午10:14:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getNewsListType() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from newscontent_type  where xh in(1,2,3,4) order by xh desc");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[] {});
	}

	/**
	 * 查询新闻列表，用于首页加载
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getNewsList(NewsManageForm model,
			User user) {
		String[] colList = new String[] { "newsid","title","sfzd","newstitle","pubtime","sfnew","towho" };
		StringBuilder sql = new StringBuilder();

		sql.append("select NEWSID,title,SFZD,NEWSTITLE,to_char(to_date(PUBTIME,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') PUBTIME,sfnew,towho,r from (");
		sql.append("select a.*,rownum r from (select NEWSID,NEWSTITLE title,SFZD,(case when length(NEWSTITLE) > 40 then substr(NEWSTITLE, 1, 40) || '...' else NEWSTITLE end) NEWSTITLE,towho,");
		sql.append(" pubtime,");
		sql.append("case when to_char(to_date(PUBTIME,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') =");
		sql.append("to_char(sysdate,'yyyy-mm-dd') then '是' else '否' end sfnew ");
		sql.append("from newscontent where sffb='是' ");

		String kssj = model.getKssj();
		String jssj = model.getJssj();
		List<String> input = new ArrayList<String>();

		if (!Base.isNull(kssj) && !Base.isNull(jssj)) {
			sql.append(" and to_date(pubtime,'yyyy-mm-dd hh24:mi:ss')>=to_date(?,'yyyy-mm-dd') and to_date(pubtime,'yyyy-mm-dd hh24:mi:ss') <=to_date(?,'yyyy-mm-dd')+1");
			input.add(kssj);
			input.add(jssj);
		} else if (!Base.isNull(jssj) && Base.isNull(kssj)) {
			sql.append(" and to_date(pubtime,'yyyy-mm-dd hh24:mi:ss')<=to_date(?,'yyyy-mm-dd')+1");
			input.add(jssj);
		} else if (!Base.isNull(kssj) && Base.isNull(jssj)) {
			sql.append(" and to_date(pubtime,'yyyy-mm-dd hh24:mi:ss')>=to_date(?,'yyyy-mm-dd')");
			input.add(kssj);
		}

		if (!Base.isNull(model.getBt())) {
			sql.append(" and NEWSTITLE like '%'||?||'%'");
			input.add(model.getBt());
		}
		if (StringUtils.isNotNull(model.getTypeid())) {
			// 如果是通知通告，则查询无对应类型id的数据，也就是旧数据
			if (model.getTypeid().equals(NewsManageService._TYPE_TZTG)) {
				sql.append(" and (typeid is null or typeid=?)");
				input.add(model.getTypeid());
			} else {
				sql.append(" and typeid =?");
				input.add(model.getTypeid());
			}
		}
		sql.append("order by sfzd desc, pubtime desc) a) a where 1=1 ");
		return CommonQueryDAO.commonQueryforList(sql.toString(), "", input.toArray(new String[] {}), colList, model);
	}
	
	public List<HashMap<String, String>> getNewsPageList(NewsManageForm model,
			User user) {
		String[] colList = new String[] { "r","newsid","title","sfzd","newstitle","pubtime","sfnew","towho","typeid"};
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,rownum r from (");
		sql.append("select NEWSID,title,typeid,SFZD,NEWSTITLE,to_char(to_date(PUBTIME,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') PUBTIME,sfnew,towho from (");
		sql.append("select NEWSID,NEWSTITLE title,typeid,SFZD,(case when length(NEWSTITLE) > 40 then substr(NEWSTITLE, 1, 40) || '...' else NEWSTITLE end) NEWSTITLE,towho,");
		sql.append(" pubtime,");
		sql.append("case when to_char(to_date(PUBTIME,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') =");
		sql.append("to_char(sysdate,'yyyy-mm-dd') then '是' else '否' end sfnew from");
		sql.append("(select a.*,tzgg_sjfwcl('");

		if("parent".equalsIgnoreCase(user.getUserType())){
			sql.append(user.getChildId());
			sql.append("','");
			sql.append("stu");
		}else {
			sql.append(user.getUserName());
			sql.append("','");
			sql.append(user.getUserType());
		}

		sql.append("',a.newsid,a.towho) count from ");
		sql.append("newscontent a ) ");
		sql.append(" where sffb='是' and count='1' ");

		String kssj = model.getKssj();
		String jssj = model.getJssj();
		List<String> input = new ArrayList<String>();

		if (!Base.isNull(kssj) && !Base.isNull(jssj)) {
			sql.append(" and to_date(pubtime,'yyyy-mm-dd hh24:mi:ss')>=to_date(?,'yyyy-mm-dd') and to_date(pubtime,'yyyy-mm-dd hh24:mi:ss') <=to_date(?,'yyyy-mm-dd')+1");
			input.add(kssj);
			input.add(jssj);
		} else if (!Base.isNull(jssj) && Base.isNull(kssj)) {
			sql.append(" and to_date(pubtime,'yyyy-mm-dd hh24:mi:ss')<=to_date(?,'yyyy-mm-dd')+1");
			input.add(jssj);
		} else if (!Base.isNull(kssj) && Base.isNull(jssj)) {
			sql.append(" and to_date(pubtime,'yyyy-mm-dd hh24:mi:ss')>=to_date(?,'yyyy-mm-dd')");
			input.add(kssj);
		}

		if (!Base.isNull(model.getBt())) {
			sql.append(" and NEWSTITLE like '%'||?||'%'");
			input.add(model.getBt());
		}
		if (StringUtils.isNotNull(model.getTypeid())) {
			// 如果是通知通告，则查询无对应类型id的数据，也就是旧数据
			if (model.getTypeid().equals(NewsManageService._TYPE_TZTG)) {
				sql.append(" and (typeid is null or typeid=?)");
				input.add(model.getTypeid());
			} else {
				sql.append(" and typeid =?");
				input.add(model.getTypeid());
			}
		}
		sql.append(" order by sfzd desc, pubtime desc) a ) a where 1=1 ");
		return CommonQueryDAO.commonQueryforList(sql.toString(), "", input.toArray(new String[] {}), colList, model);
	}
	
	/**
	 * 
	 * @描述:查询通知公告关联的浏览记录表id
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-27 上午10:14:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getNewsTid() {
		StringBuilder sql = new StringBuilder();
		sql.append("select nvl(max(tid),0) tid from newscontent");
		return DAO.getInstance().getOneRs(sql.toString(), new String[]{}, "tid");
	}
	/**
	 * 
	 * @描述:查询通知公告浏览记录人所存储的表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-27 上午11:29:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getTableList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select  distinct tablename from (select distinct tablename from newscontent where tablename is not null");
		sql.append(" union select 'xg_tzgglljlb_1' tablename from dual) ");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{});
	}
	public HashMap<String,String> getTableName(List<HashMap<String,String>> tableList) {
		StringBuilder sql = new StringBuilder();
		String[] inputV=new String[tableList.size()];
		if(0==tableList.size()){
			return null;
		}
		sql.append("select * from( select table_name,con from(");
		for (int i = 0; i < tableList.size(); i++) {
			sql.append("select ? table_name,count(1) con from ").append(tableList.get(i).get("tablename"));
			inputV[i]=tableList.get(i).get("tablename");
			if(i!=tableList.size()-1){
			sql.append(" union ");
			}
		}
		sql.append(") order by con asc)  where rownum=1");
		return DAO.getInstance().getMapNotOut(sql.toString(), inputV);
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:创建新的浏览记录表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-27 下午04:49:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tableName
	 * void 返回类型 
	 * @throws
	 */
	public void createIableInfo (String tableName) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("create table ").append(tableName);
		sql.append(" as select * from xg_tzgglljlb_1  where 1=2");
		DAO.getInstance().runUpdate(sql.toString(), new String[] {});
	}
	/**
	 * 
	 * @描述:查询浏览记录表中通知公告数
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-28 上午10:59:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tableName
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public boolean getTzggCount (String tableName) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1)tzggNum from(select distinct newsid from ").append(tableName).append(")");
		String tzggNum=DAO.getInstance().getOneRs(sql.toString(), new String[]{}, "tzggNum");
		return Integer.parseInt(tzggNum)>2;
	}
}
