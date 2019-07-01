package xsgzgl.wjcf.general.cfjcgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.wjcf.general.WjcfGeneralForm;

public class WjcfCfjcDao extends DAO {
	DAO dao = DAO.getInstance();

	/**
	 * 违纪处分 处分解除管理
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCfjcList(WjcfGeneralForm myForm,
			WjcfCfjcModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] colArr = new String[] {"cfid","xn","xqmc","xh","xm","cflbmc","cfyymc", "jcwh","jcsj", "sqjg","jcjgmc","jcsqjg","ssyz"};
		
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		String[] input = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,rownum r from ( ");
		sql.append("select a.cfid,");
		sql.append("a.xh,");
		sql.append("b.xm,b.xydm,b.zydm,b.bjdm,b.nj,");
		sql.append("a.xn,");
		sql.append("a.xq,");
		sql.append("(select b.xqmc from xqdzb b where a.xq = b.xqdm) xqmc,");
		sql.append("a.cflbmc,");
		sql.append("a.cfyymc,");
		sql.append("a.cfsj,");
		sql.append("a.cfwh,");
		sql.append("a.wjsj,");
		sql.append("a.sbb,");
		sql.append("a.sbsj,");
		sql.append("a.wjssjg,");
		sql.append("a.bz,");
		sql.append("a.jcsj,");
		sql.append("a.jcwh,");
		sql.append("a.jcyj,");
		sql.append("c.sqjg,");
		sql.append("(case when c.sqjg='wsh' then '未审核' when c.sqjg = 'tg' then '通过' when c.sqjg = 'btg' ");
		sql.append( "then '不通过' when c.sqjg='shz' then '审核中' else '' end) jcjgmc,c.sqjg jcsqjg,");
		sql.append(" (case when ");
		sql.append(" instr(d.sfksqjc,?) >0 then 'y' else 'n' end) ssyz");
		
		sql.append(" from xg_wjcf_wjcfb a left join view_xsxxb b on a.xh = b.xh left join xg_wjcf_wjcfjcsqb c on a.cfid = c.cfid ");
		sql.append("left join xg_wjcf_cflbdmb d on a.cflbmc = d.cflbmc ");
		sql.append(" where d.sfksqjc <> 'no') a ");
		sql.append(query);
		String[] yhlbsjqx=new String[]{};
		sql.append(searchTjByUser);
		
		String userType ="";
		if("stu".equals(user.getUserType())){
			userType = "xs";
		}else{
			userType = "js";
		}
		String[] inputValue=arrayAppendArray(new String[]{userType},input);
		inputValue=arrayAppendArray(inputValue,yhlbsjqx);
		sql.append(searchTjByUser);
		return (ArrayList<String[]>)CommonQueryDAO
		.commonPageQuery(myForm.getPages(), sql.toString(), inputValue,colArr);
	}
	
	/**
	 * 保存
	 * 
	 * @param beginArray
	 * @param endArray
	 * @return
	 */
	public boolean saveJcSq(WjcfGeneralForm form) throws Exception{
		boolean flag = false;
		String sql = "insert into xg_wjcf_wjcfjcsqb (cfid,sqsj,sqly,sqjg) values(?,?,?,?)" ;
		flag =  dao.runUpdate(sql, new String[]{form.getCfId(),form.getSbsj(),form.getSqly(),"wsh"});
		
		if(flag){
			sql = " select b.spgw from xg_wjcf_ssjcsplb a left join  xg_xtwh_spbz b  on a.jcspl = b.splc ";
			String[] inputValue = new String[] {};
			 List<String> list = dao.getList(sql, inputValue, "spgw");
			 if(null!=list&&list.size()>0){
				 sql="insert into xg_wjcf_wjcfjcshb(cfid,xtgwid,shzt,sftj) values(?,?,?,?)";
				 List<String[]> params=new ArrayList<String[]>();
				 for(int i=0;i<list.size();i++){
					 String[] param=null;
					 param=new String[]{form.getCfId(),list.get(i),"wsh","0"};
					 params.add(param);
				 }
				 int[] rows = dao.runBatch(sql, params);
				 flag = dao.checkBatchResult(rows); 
			 }
		}
		return flag;
	}
	
	/**
	 * 修改
	 * 
	 * @param beginArray
	 * @param endArray
	 * @return
	 */
	public boolean updateJcSq(WjcfGeneralForm form) throws Exception{
		boolean flag = false;
		String sql = "update xg_wjcf_wjcfjcsqb set sqly = ? where cfid=?" ;
		flag =  dao.runUpdate(sql, new String[]{form.getSqly(),form.getCfId()});
		
		return flag;
	}

	/**
	 * 查询一条数据
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public HashMap<String, String> getJcSq(String cfid) {
		String sql = "select a.cfid,a.sqly from xg_wjcf_wjcfjcsqb a where a.cfid = ?" ;
		String[] inputValue = new String[] { cfid };
		return getMapNotOut(sql, inputValue);
	}
	
	/**
	 * 删除
	 * @throws Exception 
	 * */
	public boolean delJcSq(String cfId) throws Exception{
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_wjcf_wjcfjcsqb where cfid=?");
		flag = dao.runUpdate(sql.toString(), new String[]{cfId});
		if(flag){
			 sql = new StringBuilder();
			sql.append("delete from xg_wjcf_wjcfjcshb where cfid=?");
			flag = dao.runUpdate(sql.toString(), new String[]{cfId});
		}
		
		return flag; 
	}
	
	/**
	 * 工具类 用于合并数组
	 * 
	 * @param beginArray
	 * @param endArray
	 * @return
	 */
	public String[] arrayAppendArray(String[] beginArray, String[] endArray) {
		if (beginArray == null || endArray == null) {
			return null;
		}
		String[] newArray = new String[(beginArray.length + endArray.length)];
		int p = 0;
		for (int i = 0; i < beginArray.length; i++) {
			newArray[p] = beginArray[i];
			p++;
		}
		for (int i = 0; i < endArray.length; i++) {
			newArray[p] = endArray[i];
			p++;
		}
		return newArray;
	}
	
	/**
	 * 自定义导出【处分解除维护】
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>>  getCfjcwhExportList(WjcfGeneralForm myForm,
			WjcfCfjcModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		//String[] colArr = new String[] {"cfid","xn","xqmc","xh","xm","cflbmc","cfyymc", "jcwh","jcsj", "sqjg","jcjgmc","jcsqjg","ssyz"};
		
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		String[] input = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,rownum r from ( ");
		sql.append("select a.cfid,");
		sql.append("a.xh,");
		sql.append("b.xm,b.xydm,b.zydm,b.bjdm,b.nj,");
		sql.append("a.xn,");
		sql.append("a.xq,");
		sql.append("(select b.xqmc from xqdzb b where a.xq = b.xqdm) xqmc,");
		sql.append("a.cflbmc,");
		sql.append("a.cfyymc,");
		sql.append("a.cfsj,");
		sql.append("a.cfwh,");
		sql.append("a.wjsj,");
		sql.append("a.sbb,");
		sql.append("a.sbsj,");
		sql.append("a.wjssjg,");
		sql.append("a.bz,");
		sql.append("a.jcsj,");
		sql.append("a.jcwh,");
		sql.append("a.jcyj,");
		sql.append("c.sqjg,");
		sql.append("(case when c.sqjg='wsh' then '未审核' when c.sqjg = 'tg' then '通过' when c.sqjg = 'btg' ");
		sql.append( "then '不通过' when c.sqjg='shz' then '审核中' else '' end) jcjgmc,c.sqjg jcsqjg,");
		sql.append(" (case when ");
		sql.append(" instr(d.sfksqjc,?) >0 then 'y' else 'n' end) ssyz");
		
		sql.append(" from xg_wjcf_wjcfb a left join view_xsxxb b on a.xh = b.xh left join xg_wjcf_wjcfjcsqb c on a.cfid = c.cfid ");
		sql.append("left join xg_wjcf_cflbdmb d on a.cflbmc = d.cflbmc ");
		sql.append(" where d.sfksqjc <> 'no') a ");
		sql.append(query);
		String[] yhlbsjqx=new String[]{};
		sql.append(searchTjByUser);
		
		String userType ="";
		if("stu".equals(user.getUserType())){
			userType = "xs";
		}else{
			userType = "js";
		}
		String[] inputValue=arrayAppendArray(new String[]{userType},input);
		inputValue=arrayAppendArray(inputValue,yhlbsjqx);
		sql.append(searchTjByUser);
		return (List<HashMap<String, String>> )CommonQueryDAO.commonPageQueryForExportMap(myForm.getPages(), sql.toString(), inputValue);
		
		
	}
}