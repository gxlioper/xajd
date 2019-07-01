/**
 * @部门:学工产品事业部
 * @日期：2016-9-5 上午10:25:27 
 */  
package xsgzgl.gygl.ydgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.comm.xml.XMLReader;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 用电管理模块
 * @作者： caopei[工号:1352]
 * @时间： 2016-9-5 上午10:25:27 
 */

public class YdxxDao extends SuperDAOImpl<YdxxForm>{
	@Override
	public List<HashMap<String, String>> getPageList(YdxxForm t)
			throws Exception {
		return null;
	}
	/**
	 * 获取公寓管理员的条件
	 */
	public String getSearchTjByGyfdy(YdxxForm t, User user){		
		String gyglyQx = user.getGyglyQx();
		String username = user.getUserName();
		String gygly_path = XMLReader.getFlowControl("gygl", "gygly_path");
		String path = t.getPath();
		if(path!=null && gygly_path.contains(path) && "yes".equalsIgnoreCase(gyglyQx)){
			return " and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+username+"')";
		}else{
			return "";
		}
	}
	@Override
	public List<HashMap<String, String>> getPageList(YdxxForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		String searchTjByGyfdy = getSearchTjByGyfdy(t,user); // 公寓辅导员数据范围过滤条件
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			sql.append(" select * from (select t1.*,t2.ldmc,t2.cws,t2.ch,t2.xydm,t2.xymc,t2.nj,t2.qsxb ldxb,t2.xh" +
							" from xg_gygl_new_ydxxb t1 left join view_xg_gygl_new_cwxx t2 " +
								" on t1.lddm =t2.lddm and t1.qsh=t2.qsh) where xh='"+user.getUserName()+"'");
		}else {
			sql.append(" select * from (select t1.*,t2.ldmc,t2.cws,t2.ch,t2.xydm,t2.xymc,t2.nj,t2.ldxb from  " +
							" xg_gygl_new_ydxxb t1 left join  view_xg_gygl_new_qsxx t2 " +
								" on t1.qsh =t2.qsh and t1.LDDM=t2.lddm ) ");
			sql.append(" where 1=1");
			sql.append(" ");
			if (searchTjByGyfdy != null && !"".equalsIgnoreCase(searchTjByGyfdy)) {// 用户为公寓辅导员
				sql.append(" "+searchTjByGyfdy+" ") ;
			}
			sql.append(searchTj);
		}
		return getPageList(t, sql.toString(),inputV);
	}
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_gygl_new_ydxxb");
		super.setKey("ydxxid");
		super.setClass(YdxxForm.class);
	}
	/** 
	 * @描述:检查有无重复(这里是要求同一楼栋同一寝室同一月份只有一条数据)
	 * @作者：caopei[工号：1352]
	 * @日期：2016-9-5 下午03:22:48
	 * @param myForm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String checkExistForSave(YdxxForm form){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_gygl_new_ydxxb ");
		sql.append(" where qsh = ?  and lddm = ? and ydyf = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{form.getQsh(),form.getLddm(),form.getYdyf()}, "num");
		return num;
	}

	public List<HashMap<String, String>> getRsList(String tableName, Map<String, String> queryMap, String[] colList){
		StringBuilder query = new StringBuilder(" where 1=1 ");
		List<String> inputList = new ArrayList<String>();
		// 拼写where条件
		if(queryMap != null){
			for(Map.Entry<String, String> entry : queryMap.entrySet()){
				if(!"orderBy".equalsIgnoreCase(entry.getKey()) && StringUtils.isNotNull(entry.getValue())){
					query.append(" and ").append(entry.getKey()).append("=?");
					inputList.add(entry.getValue());
				}
			}
			if(StringUtils.isNotNull(queryMap.get("orderBy"))){
				query.append(" ").append(queryMap.get("orderBy"));
			}
		}
		return CommonQueryDAO.commonQueryforList(tableName, query.toString(), (String[])inputList.toArray(new String[]{}), colList, "");
	}

	/** 
	 * @描述:床位数
	 */
	public String getCwhForQs(String lddm, String qsh) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select cws from view_xg_gygl_new_qsxx where lddm=? and qsh =? ");
		return dao.getOneRs(sb.toString(), new String[] { lddm,qsh }, "cws");
	}
	/**
	 *获得单个寝室信息
	 */
	public Map<String, String> getQsForPk(String pk){
		StringBuilder sql = new StringBuilder();
		sql.append("select b.lddm||b.qsh pkValue,a.ldmc,a.ldxb,a.ldcs,a.qsch,a.sfhlc,b.* ")
		.append("from xg_gygl_new_ldxxb a,(select a.*,b.bmmc xymc,(case when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) chmc from xg_gygl_new_qsxxb a ")
		.append("left join zxbz_xxbmdm b on a.xydm=b.bmdm)b where a.lddm=b.lddm and b.lddm||b.qsh=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{pk});
	}
	/** 
	 * @描述:获得床位信息
	 */
	public List<HashMap<String, String>> getCwForQs(String[] inputValue, String[] outputValue){
		StringBuilder sql = new StringBuilder();
		//辽宁机电职业技术学院 床位号存在中文，个性化修改
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "a.cwh";
		}else{
			sb = "to_number(a.cwh)";
		}
		sql.append("select a.cwh," +
				"case when a.xh is null then ' ' else  a.xh end xh," +
				"case when b.xm is null then ' ' else  b.xm end xm," +
				"case when b.bjmc is null then ' ' else  b.bjmc end xsbjmc ")
			.append("from (select * from view_xg_gygl_new_cwxx a where lddm||qsh=?)a ")
			.append("left join view_xsbfxx b on a.xh=b.xh order by "+sb+" ");
		return dao.getList(sql.toString(), inputValue, outputValue);
	}
	public List<String[]> getCwForQs1(String[] inputValue, String[] outputValue){
		StringBuilder sql = new StringBuilder();
		//辽宁机电职业技术学院 床位号存在中文，个性化修改
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "a.cwh";
		}else{
			sb = "to_number(a.cwh)";
		}
		sql.append("select a.*,b.xm,b.bjmc xsbjmc  ")
			.append("from (select * from view_xg_gygl_new_cwxx a where lddm||qsh=?)a ")
			.append("left join view_xsbfxx b on a.xh=b.xh order by "+sb+" ");
		
		return dao.rsToVator(sql.toString(), inputValue, outputValue);
	}
	/** 
	 * @描述:插入
	 */
	public boolean saveDataYdxx(YdxxForm t)throws Exception {
		StringBuilder sql = new StringBuilder();
		ArrayList<String> parameter = new ArrayList<String>();
			sql.append(" insert into xg_gygl_new_ydxxb(lddm,qsh,ydyf,ds,df,dfye,jlr,bz)");
			sql.append(" values(?,?,?,?,?,?,?,?)");
			parameter.add(t.getLddm());
			parameter.add(t.getQsh());
			parameter.add(t.getYdyf());
			parameter.add(t.getDs());
			parameter.add(t.getDf());
			parameter.add(t.getDfye());
			parameter.add(t.getJlr());
			parameter.add(t.getBz());
		return dao.runUpdate(sql.toString(),parameter.toArray(new String[]{}));
	}

	/** 
	 * @描述:更新
	 */
	public boolean updateDataYdxx(YdxxForm t)throws Exception {
		StringBuilder sql = new StringBuilder();
		ArrayList<String> parameter = new ArrayList<String>();
			sql.append(" update xg_gygl_new_ydxxb set lddm=?, qsh=?, ydyf=?, ds=?, df=?, dfye=?, jlr=?, bz=?  where ydxxid =? ");
			parameter.add(t.getLddm());
			parameter.add(t.getQsh());
			parameter.add(t.getYdyf());
			parameter.add(t.getDs());
			parameter.add(t.getDf());
			parameter.add(t.getDfye());
			parameter.add(t.getJlr());
			parameter.add(t.getBz());
			parameter.add(t.getYdxxid());
			return dao.runUpdate(sql.toString(),parameter.toArray(new String[]{}));
	}

	/**
	 * @描述:获取登陆用户权限内的楼栋信息
	 */
	public List<HashMap<String, String>> getLdxxList(YdxxForm t) {
		String sql = "select * from xg_gygl_new_ldxxb t1 left join xg_gygl_new_gyfdyb t2 " +
				" on t1. lddm = t2.lddm where t2.yhm='"+t.getJlr()+"' ";
		return dao.getListNotOut(sql, new String[] {});

	}

}
