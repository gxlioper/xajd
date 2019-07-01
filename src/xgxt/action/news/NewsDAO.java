package xgxt.action.news;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class NewsDAO extends DAO {

	
	/**
	 * 新闻查询
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>> getNews(NewsModel model,User user){
		
		Pages pages = model.getPages();
		HashMap<String, Object> map = getQueryNewsSQL(model,user);
		
		String conut = getOneRs("select count(1) count from xg_view_newscontent where 1=1 "+map.get("sql"),(String[])map.get("input"),"count");
		pages.setMaxRecord(Integer.valueOf(conut));
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (")
		   .append("select a.*,rownum r from (select * from xg_view_newscontent order by pubtime desc) a where 1=1")
		   .append(map.get("sql"))
		   .append("  order by pubtime desc) where r > ")
		   .append(pages.getStart())
		   .append(" and r <= ")
		   .append((pages.getStart() + pages.getPageSize()));
		
		return getListNotOut(sql.toString(), (String[])map.get("input"));
	}
	
	
	/**
	 * 新闻查询条件
	 * @param model
	 * @param user
	 * @return
	 */
	private HashMap<String, Object> getQueryNewsSQL(NewsModel model,User user){
		StringBuilder sql = new StringBuilder();
		List<String> input = new ArrayList<String>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		if (StringUtils.isNotNull(model.getNewsTitle())){
			sql.append(" and newstitle like '%'||?||'%' ");
			input.add(model.getNewsTitle());
		}
		
		if (StringUtils.isNotNull(model.getPuber())){
			sql.append(" and puber like '%'||?||'%'");
			input.add(model.getPuber());
		}
		
		if (StringUtils.isNotNull(model.getPubKssj()) && StringUtils.isNull(model.getPubJssj())) {
			sql.append(" and (to_date(pubtime,'yyyy-mm-dd hh24:mi:ss') >= to_date(?,'yyyy-mm-dd'))");
			input.add(model.getPubKssj());
		}
		
		if (StringUtils.isNotNull(model.getPubJssj()) && StringUtils.isNull(model.getPubKssj()) ) {
			sql.append(" and (to_date(pubtime,'yyyy-mm-dd hh24:mi:ss') <= to_date(?,'yyyy-mm-dd'))");
			input.add(model.getPubJssj());
		}
		
		if (StringUtils.isNotNull(model.getPubKssj()) && StringUtils.isNotNull(model.getPubJssj())) {
			sql.append(" and (to_date(pubtime,'yyyy-mm-dd hh24:mi:ss') between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')+1)");
			input.add(model.getPubKssj());
			input.add(model.getPubJssj());
		}
		
		if ("stu".equals(user.getUserType())){
			sql.append(" and (towho='4' or towho='1')");
		} else if ("admin".equals(user.getUserType()) || "xx".equals(user.getUserType())){
			sql.append(" and (towho='2' or towho='1')");
		} else if ("xy".equals(user.getUserType())){
			sql.append(" and (towho='3' or towho='1') ");
		}
		
		if (StringUtils.isNotNull(model.getTagId()) && !"N01".equals(model.getTagId())){
			sql.append(" and newspart=?");
			input.add(model.getTagId());
		}
		
		map.put("sql", sql.toString());
		map.put("input", input.toArray(new String[]{}));
		
		return map;
	}
	
	
	
	
	/**
	 * 北京联合新闻查询
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>> getBjlhNews(NewsModel model,User user){
		
		Pages pages = model.getPages();
		HashMap<String, Object> map = getQueryBjlhNewsSQL(model,user);
		
		String conut = getOneRs("select count(1) count from xg_view_newscontent where 1=1 "+map.get("sql"),(String[])map.get("input"),"count");
		pages.setMaxRecord(Integer.valueOf(conut));
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (")
		   .append("select a.*,rownum r from (select * from xg_view_newscontent order by pubtime desc) a where 1=1")
		   .append(map.get("sql"))
		   .append("  order by pubtime desc) where r > ")
		   .append(pages.getStart())
		   .append(" and r <= ")
		   .append((pages.getStart() + pages.getPageSize()));
		
		return getListNotOut(sql.toString(), (String[])map.get("input"));
	}
	
	
	
	
	/**
	 * 北京联合新闻查询SQL
	 * @param model
	 * @param user
	 * @return
	 */
	private HashMap<String, Object> getQueryBjlhNewsSQL(NewsModel model,User user){
		StringBuilder sql = new StringBuilder();
		List<String> input = new ArrayList<String>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		if (StringUtils.isNotNull(model.getNewsTitle())){
			sql.append(" and newstitle like '%'||?||'%' ");
			input.add(model.getNewsTitle());
		}
		
		if (StringUtils.isNotNull(model.getPuber())){
			sql.append(" and puber like '%'||?||'%'");
			input.add(model.getPuber());
		}
		
		if (StringUtils.isNotNull(model.getPubKssj()) && StringUtils.isNull(model.getPubJssj())) {
			sql.append(" and (to_date(pubtime,'yyyy-mm-dd hh24:mi:ss') >= to_date(?,'yyyy-mm-dd'))");
			input.add(model.getPubKssj());
		}
		
		if (StringUtils.isNotNull(model.getPubJssj()) && StringUtils.isNull(model.getPubKssj()) ) {
			sql.append(" and (to_date(pubtime,'yyyy-mm-dd hh24:mi:ss') <= to_date(?,'yyyy-mm-dd'))");
			input.add(model.getPubJssj());
		}
		
		if (StringUtils.isNotNull(model.getPubKssj()) && StringUtils.isNotNull(model.getPubJssj())) {
			sql.append(" and (to_date(pubtime,'yyyy-mm-dd hh24:mi:ss') between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')+1)");
			input.add(model.getPubKssj());
			input.add(model.getPubJssj());
		}
		
		if ("stu".equals(user.getUserType())){
			sql.append(" and (towho='3' or towho='1' or ((towho='4' or towho='6') and bmdm=?))");
			input.add(user.getUserDep());
		} else if ("admin".equals(user.getUserType()) || "xx".equals(user.getUserType())){
			sql.append(" and (towho='2' or towho='1')");
		} else if ("xy".equals(user.getUserType())){
			sql.append(" and (towho='2' or towho='1' or ((towho='5' or towho='4') and bmdm=?)) ");
			input.add(user.getUserDep());
		}
		
		if (StringUtils.isNotNull(model.getTagId()) && !"N01".equals(model.getTagId())){
			sql.append(" and newspart=?");
			input.add(model.getTagId());
		}
		
		sql.append(" and (fwfs='2' or fwfs='3')");//访问方式内网、内外网
		
		map.put("sql", sql.toString());
		map.put("input", input.toArray(new String[]{}));
		
		return map;
	}
	
	
	
	
	/**
	 * 北京联合外网新闻
	 * @return
	 */
	public List<HashMap<String,String>> getWwkjNews(){
		
		String sql = "select * from (select newsid,newstitle,pubtime from newscontent where fwfs = '1' or fwfs='3' order by pubtime desc) where rownum<10";
		
		return getListNotOut(sql, new String[]{});
	}
}
