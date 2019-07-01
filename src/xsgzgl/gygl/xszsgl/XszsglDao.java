package xsgzgl.gygl.xszsgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewDAO;

import common.Globals;

public class XszsglDao extends GyglNewDAO{
	DAO dao = DAO.getInstance();
	
	/**
	 * 获取已入住学生信息数据集
	 * @author zhanghui
	 */
	public ArrayList<String[]> getTsglZsxxList(XszsglForm myForm, HttpServletRequest request) 
		throws Exception{		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		// 权限控制
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm");
		searchTj += searchTjByUser;
		
		String searchTjstr = "";
		String test = searchTj;
		if(searchTj!=null && !"".equalsIgnoreCase(searchTj)){
			String[] tj = searchTj.replace("?", "split").split("split");
			for(int i=0;i<inputV.length;i++){
				searchTjstr += tj[i]+inputV[i];
			}
			searchTjstr+=tj[inputV.length];
		}
		request.setAttribute("searchTjstr", searchTjstr);
		
//		String sql = "select rownum r,a.* from (select a.* from (select a.xh pk,a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc," +
//				"b.lddm,b.ldmc,b.ch,b.qsh,b.cwh,(case when b.xh is null then '是' else '否' end) sfzs " +
//				"from view_xsjbxx a left join view_xg_gygl_new_cwxx b on a.xh=b.xh " +
//				//" order by b.lddm, to_number(b.ch), b.qsh,b.cwh" +
//				") a ) a where 1=1 ";		
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from ( ");
		//重庆工商大学个性化增加是否走读高级查询条件
		if("11799".equals(Base.xxdm)){			
			sql.append("select a.*,e.*,(case when f.xh is not null then '是' else '否' end) sfzd from xg_view_gygl_new_xszsgl a ");
		}else{
			sql.append("select a.*,e.* from xg_view_gygl_new_xszsgl a ");				
		}
		// ====================班主任、辅导员 begin======================
		sql.append("left join ")
		.append("(select nvl(t1.bjdm,t2.bjdm) as bjdm2 , t1.fdyxm , t2.bzrxm from (select a.bjdm ,  WM_CONCAT(b.xm||b.lxdh) fdyxm from fdybjb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t1 ")
		.append(" full join ")
		.append("(select a.bjdm ,  WM_CONCAT(b.xm||b.lxdh) bzrxm from bzrbbb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t2 on t1.bjdm = t2.bjdm) e ")
		.append(" on a.bjdm = e.bjdm2 ");
		//重庆工商大学个性化增加是否走读高级查询条件
		if("11799".equals(Base.xxdm)){
			sql.append("left join xg_gygl_new_bzxbzb f on a.xh = f.xh and f.bzxbz is not null ");
		}
		// ====================班主任、辅导员 end======================
		sql.append(") a where 1=1 ");	
		String[] colList =null;
		if(Globals.XXDM_NTZYDX.equals(Base.xxdm)){//南通职业大学
			sql = new StringBuilder();
			sql.append("select rownum r,a.* from xg_view_gygl_new_xszsgl_ntzydx a where 1=1 ");
			colList = new String[]{"xh","xm","nj","xymc","xb","ldmc", "qsh", "cwh","rxfsmc","fdyxm"};
		}else{
			colList = new String[]{"xh","xm","nj","xymc","bjmc","xb","ldmc", "qsh", "cwh","sfzs","fdyxm","bzrxm"};
		}
		
		return  commonQuery(sql.toString(),searchTj,inputV,colList,myForm);
	}
	/**
	 * 获取不住校备注
	 * @param string
	 * @return
	 */
	public String getBzxbz(String string) {
		// TODO 自动生成方法存根
		String sql="select bzxbz from xg_gygl_new_bzxbzb where xh=?";
		return dao.getOneRs(sql, new String[]{string}, "bzxbz");
	}
	
	
	public void deleteBz(String[] xhstr) throws Exception {
		// TODO 自动生成方法存根
		ArrayList<String[]> params=new ArrayList<String[]>();
		for (String string : xhstr) {
			params.add(new String[]{string});
		}
		String sql="delete xg_gygl_new_bzxbzb where xh=?";
		dao.runBatch(sql, params);
	}
	public boolean insertBz(XszsglForm myForm, String[] xhstr) throws Exception {
		// TODO 自动生成方法存根
		String[] sqls=new String[xhstr.length+1];
		String bz=myForm.getBzxbz();
		for(int i=0;i<xhstr.length;i++){
			String sql="insert into xg_gygl_new_bzxbzb(xh,bzxbz) values('"+xhstr[i]+"','"+bz+"')";
			sqls[i]=sql;
		}
		return dao.saveArrDate(sqls);
	}
	
	/**
	 * 获取学生住宿信息
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXszsxx(String xh) {
		// TODO 自动生成方法存根
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, b.bzxbz, c.nj cwnj, c.bz, (select bmmc from zxbz_xxbmdm where bmdm = c.xydm) cwxymc, ");
		sql.append(" (select bjmc from bks_bjdm where bjdm = c.bjdm) cwbjmc, d.fdyxm fdyxm from xg_view_gygl_new_xszsgl a left join xg_gygl_new_bzxbzb b on a.xh = b.xh ");
        sql.append(" left join xg_gygl_new_cwxxb c on a.lddm = c.lddm and a.qsh = c.qsh and a.cwh = c.cwh ");
        sql.append(" left join (select bjdm, max(fdyxm) fdyxm from (select bjdm, to_char(WM_CONCAT(xm) over(partition by bjdm order by bjdm)) fdyxm ");
        sql.append(" from (select a.bjdm, b.xm from fdybjb a left join fdyxxb b on a.zgh = b.zgh)) group by bjdm) d on a.bjdm = d.bjdm where a.xh = ? ");
        
		return dao.getMap(sql.toString(), new String[]{xh}, new String[]{"xh","xm","nj","xymc","zymc","bjmc","sjhm","sfzs","ldmc","qsh","cwh","bzxbz","cwnj","bz","cwxymc","cwbjmc","fdyxm"});
	}
	
	/**
	 * 学生住宿信息 自定义导出
	 * @param myForm
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getExportPageList(XszsglForm myForm,User user,HttpServletRequest request)
			throws Exception {
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		// 权限控制
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm");
		searchTj += searchTjByUser;
		
		String searchTjstr = "";
		String test = searchTj;
		if(searchTj!=null && !"".equalsIgnoreCase(searchTj)){
			String[] tj = searchTj.replace("?", "split").split("split");
			for(int i=0;i<inputV.length;i++){
				searchTjstr += tj[i]+inputV[i];
			}
			searchTjstr+=tj[inputV.length];
		}
		request.setAttribute("searchTjstr", searchTjstr);
		
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from ( ");
		//重庆工商大学个性化增加是否走读高级查询条件
		if("11799".equals(Base.xxdm)){			
			sql.append("select a.*,e.*,(case when f.xh is not null then '是' else '否' end) sfzd from xg_view_gygl_new_xszsgl a ");
		}else if("12389".equals(Base.xxdm)){
			sql.append("select a.*,e.*,g.sfbz from xg_view_gygl_new_xszsgl a ");
		}else{
			sql.append("select a.*,e.* from xg_view_gygl_new_xszsgl a ");
		}

		// ====================班主任、辅导员 begin======================
		sql.append("left join ")
		.append("(select t1.bjdm as bjdm2 , t1.fdyxm , t2.bzrxm from (select a.bjdm ,  WM_CONCAT(b.xm||b.lxdh) fdyxm from fdybjb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t1 ")
		.append(" left join ")
		.append("(select a.bjdm ,  WM_CONCAT(b.xm||b.lxdh) bzrxm from bzrbbb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t2 on t1.bjdm = t2.bjdm) e ")
		.append(" on a.bjdm = e.bjdm2 ");
		//重庆工商大学个性化增加是否走读高级查询条件
		if("11799".equals(Base.xxdm)){
			sql.append("left join xg_gygl_new_bzxbzb f on a.xh = f.xh and f.bzxbz is not null ");
		}
		if("12389".equals(Base.xxdm)){
			sql.append("left join VIEW_XG_GYGL_NEW_CWXX g on a.xh = g.xh  ");
		}
		// ====================班主任、辅导员 end======================
		sql.append(") a where 1=1 ");	
		String[] colList =null;
		if(Globals.XXDM_NTZYDX.equals(Base.xxdm)){//南通职业大学
			sql = new StringBuilder();
			sql.append("select rownum r,a.* from xg_view_gygl_new_xszsgl_ntzydx a where 1=1 ");
			colList = new String[]{"xh","xm","nj","xymc","xb","ldmc", "qsh", "cwh","rxfsmc","fdyxm"};
		}else if("12389".equals(Base.xxdm)){//河北建材
			colList = new String[]{"xh","xm","nj","xymc","bjmc","xb","ldmc", "qsh", "cwh","sfzs","fdyxm","bzrxm","sfzh","sfbz"};
		}else{
			colList = new String[]{"xh","xm","nj","xymc","bjmc","xb","ldmc", "qsh", "cwh","sfzs","fdyxm","bzrxm"};
		}
		
		return commonQueryforExportList(sql.toString(),searchTj,inputV,colList,myForm);
	}
	
	
}
