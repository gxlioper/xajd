package xgxt.xtwh.portallet;

import java.util.ArrayList;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.CommanForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 系统维护提供给Portallet操作的DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-07-078</p>
 */
public class XtwhForPortalletDAO extends DAO {
	ArrayList<String> value = new ArrayList<String>();
	/**
	 * 组合查询条件
	 * @param model
	 * @return StringBuffer
	 * */
	private StringBuffer getWhereSql(CommanForm model){
		String gnmkdm = model.getGnmkdm();
		String title = model.getNewsTitle();
		
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		if(gnmkdm !=null && !gnmkdm.equalsIgnoreCase("")){
			sb.append( " and newspart=?");
			value.add(gnmkdm);
		}
		if(title !=null && !title.equalsIgnoreCase("")){
			sb.append( " and newstitle like '%' || ? || '%' ");
			value.add(title);
		}
		return sb;
	}
	/**
	 * 新闻信息查询
	 * @param model
	 * @return List
	 * */
	public List queryNews(CommanForm model){
		List rs = null;		
		String sql = "";		
		String[] colList = {"newsid","newspart","lesstitle","pubtime","puber","newstitle"};
		String whereSql = getWhereSql(model).toString();		
		sql = " select * from(select rownum r, newsid,trim(newstitle) newstitle,(case  when length(newstitle)>30 then substr(newstitle,0,30)||'...' else newstitle end)lesstitle,(select gnmkmc from gnmkdmb b where a.newspart=b.gnmkdm )newspart,substr(pubtime,0,10)pubtime,puber from newscontent a "+ whereSql + " and sffb='是' order by pubtime  desc)where rownum<6 ";
		rs = rsToVator(sql,value != null ? value.toArray(new String[0]) : new String[]{}, colList);		
		return rs;
	}
}
