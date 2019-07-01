package xsgzgl.gygl.xszstj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.gygl.comm.GyglNewDAO;

public class XszstjDao extends GyglNewDAO{

	DAO dao = DAO.getInstance();
	
	/**
	 * 获取学生住宿统计信息列表
	 * @return
	 */
	public List<HashMap<String, String>> getXszstjxxList(XszstjForm myForm,HttpServletRequest request) throws Exception{
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		// 权限控制
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm");
		searchTj += searchTjByUser;
		User user=searchService.getUser(request);
		
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// 班主任权限
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		if(fdyqx||bzrqx){
			String sql="select rownum r,a.*," +
			"(case when zrs<>0 then to_char(round(zxrs/zrs*100,2),'990.99') else '0' end) zxrsbfb, " +
			"(case when zrs<>0 then to_char(round(wzxrs/zrs*100,2),'990.99') else '0' end) wzxrsbfb, " +
			"(case when zrs<>0 then to_char(round(mrs/zrs*100,2),'990.99') else '0' end) mrsbfb, " +
			"(case when zrs<>0 then to_char(round(wrs/zrs*100,2),'990.99') else '0' end) wrsbfb, " +
			"(case when mrs<>0 then to_char(round(zxmrs/mrs*100,2),'990.99') else '0' end) zxmrsbfb, " +
			"(case when wrs<>0 then to_char(round(zxwrs/wrs*100,2),'990.99') else '0' end) zxwrsbfb, " +
			"(case when mrs<>0 then to_char(round(wzxmrs/mrs*100,2),'990.99') else '0' end) wzxmrsbfb, " +
			"(case when wrs<>0 then to_char(round(wzxwrs/wrs*100,2),'990.99') else '0' end) wzxwrsbfb ";
			//重庆工商大学个性化统计走读学生和非走读非住宿学生
			if(Base.xxdm.equals("11799")){
				sql+=",(case when zrs<>0 and fzfzrs <> 0 then to_char(round(fzfzrs/zrs*100,2),'990.99') else '0.00' end) fzfzrsbfb";
				sql+=",(case when zrs<>0 and zdrs <> 0 then to_char(round(zdrs/zrs*100,2),'990.99') else '0.00' end) zdrsbfb";
				sql+=",(case when mrs<>0 and fzfzmrs <> 0 then to_char(round(fzfzmrs/mrs*100,2),'990.99') else '0.00' end) fzfzmrsbfb";
				sql+=",(case when mrs<>0 and zdmrs <> 0 then to_char(round(zdmrs/mrs*100,2),'990.99') else '0.00' end) zdmrsbfb";
				sql+=",(case when wrs<>0 and fzfzwrs <> 0 then to_char(round(fzfzwrs/wrs*100,2),'990.99') else '0.00' end) fzfzwrsbfb";
				sql+=",(case when wrs<>0 and zdwrs <> 0 then to_char(round(zdwrs/wrs*100,2),'990.99') else '0.00' end) zdwrsbfb";
			}
			sql+=" from (select a.nj,a.xydm,a.xymc,a.bjdm,a.bjmc,count(1) zrs, "+
			"count(case when a.xb='男' then 1 end) mrs, "+
			"count(case when a.xb='女' then 1 end) wrs, "+
			"count(case when b.xh is not null then 1 end) zxrs, "+
			"count(case when a.xb='男' and b.xh is not null then 1 end) zxmrs, "+
			"count(case when a.xb='女' and b.xh is not null then 1 end) zxwrs, "+
			"count(case when b.xh is null then 1 end) wzxrs, "+
			"count(case when a.xb='男' and b.xh is null then 1 end) wzxmrs, "+
			"count(case when a.xb='女' and b.xh is null then 1 end) wzxwrs ";
			//重庆工商大学个性化统计走读学生和非走读非住宿学生
			if(Base.xxdm.equals("11799")){
				sql+=",count(case when b.xh is null and c.xh is null then 1 end) fzfzrs";
				sql+=",count(case when c.xh is not null then 1 end) zdrs";
				sql+=",count(case when a.xb='男' and b.xh is null and c.xh is null then 1 end) fzfzmrs";
				sql+=",count(case when a.xb='男' and c.xh is not null then 1 end) zdmrs";
				sql+=",count(case when a.xb='女' and b.xh is null and c.xh is null then 1 end) fzfzwrs";
				sql+=",count(case when a.xb='女' and c.xh is not null then 1 end) zdwrs";
			}
			sql+=" from view_xsjbxx a left join xg_gygl_new_cwxxb b on a.xh=b.xh ";
			//重庆工商大学个性化
			if(Base.xxdm.equals("11799")){
				sql+=" left join xg_gygl_new_bzxbzb c on a.xh = c.xh and c.bzxbz is not null";
			}
			sql+="group by a.nj,a.xydm,a.xymc,a.bjdm,a.bjmc "+
			"order by a.xydm,a.nj) a where 1=1 " +searchTj+"";
//			return dao.getListNotOut(sql, inputV);
			String[] colList = null;
			//重庆工商大学个性化
			if(Base.xxdm.equals("11799")){
				colList = new String[]{"xydm","xymc","bjdm","bjmc","nj","zrs","zxrs","zxrsbfb","wzxrs","wzxrsbfb","mrs","mrsbfb","zxmrs",
						"zxmrsbfb","wzxmrs","wzxmrsbfb","wrs","wrsbfb","zxwrs","zxwrsbfb","wzxwrs","wzxwrsbfb","fzfzrs","zdrs","fzfzmrs",
						"zdmrs","fzfzwrs","zdwrs","fzfzrsbfb","zdrsbfb","fzfzmrsbfb","zdmrsbfb","fzfzwrsbfb","zdwrsbfb"};
			}else{				
				colList = new String[]{"xydm","xymc","bjdm","bjmc","nj","zrs","zxrs","zxrsbfb","wzxrs","wzxrsbfb","mrs","mrsbfb","zxmrs",
						"zxmrsbfb","wzxmrs","wzxmrsbfb","wrs","wrsbfb","zxwrs","zxwrsbfb","wzxwrs","wzxwrsbfb"};
			}
			return CommonQueryDAO.commonQueryforList(sql, "", inputV, colList, myForm);
		}else{
			String sql="select rownum r,a.*," +
			"(case when zrs<>0 then to_char(round(zxrs/zrs*100,2),'990.99') else '0' end) zxrsbfb, " +
			"(case when zrs<>0 then to_char(round(wzxrs/zrs*100,2),'990.99') else '0' end) wzxrsbfb, " +
			"(case when zrs<>0 then to_char(round(mrs/zrs*100,2),'990.99') else '0' end) mrsbfb, " +
			"(case when zrs<>0 then to_char(round(wrs/zrs*100,2),'990.99') else '0' end) wrsbfb, " +
			"(case when mrs<>0 then to_char(round(zxmrs/mrs*100,2),'990.99') else '0' end) zxmrsbfb, " +
			"(case when wrs<>0 then to_char(round(zxwrs/wrs*100,2),'990.99') else '0' end) zxwrsbfb, " +
			"(case when mrs<>0 then to_char(round(wzxmrs/mrs*100,2),'990.99') else '0' end) wzxmrsbfb, " +
			"(case when wrs<>0 then to_char(round(wzxwrs/wrs*100,2),'990.99') else '0' end) wzxwrsbfb ";
			//重庆工商大学个性化统计走读学生和非走读非住宿学生
			if(Base.xxdm.equals("11799")){
				sql+=",(case when zrs<>0 and fzfzrs <> 0 then to_char(round(fzfzrs/zrs*100,2),'990.99') else '0.00' end) fzfzrsbfb";
				sql+=",(case when zrs<>0 and zdrs <> 0 then to_char(round(zdrs/zrs*100,2),'990.99') else '0.00' end) zdrsbfb";
				sql+=",(case when mrs<>0 and fzfzmrs <> 0 then to_char(round(fzfzmrs/mrs*100,2),'990.99') else '0.00' end) fzfzmrsbfb";
				sql+=",(case when mrs<>0 and zdmrs <> 0 then to_char(round(zdmrs/mrs*100,2),'990.99') else '0.00' end) zdmrsbfb";
				sql+=",(case when wrs<>0 and fzfzwrs <> 0 then to_char(round(fzfzwrs/wrs*100,2),'990.99') else '0.00' end) fzfzwrsbfb";
				sql+=",(case when wrs<>0 and zdwrs <> 0 then to_char(round(zdwrs/wrs*100,2),'990.99') else '0.00' end) zdwrsbfb";
			}
			sql+=" from (select a.nj,a.xydm,a.xymc,count(1) zrs, "+
			"count(case when a.xb='男' then 1 end) mrs, "+
			"count(case when a.xb='女' then 1 end) wrs, "+
			"count(case when b.xh is not null then 1 end) zxrs, "+
			"count(case when a.xb='男' and b.xh is not null then 1 end) zxmrs, "+
			"count(case when a.xb='女' and b.xh is not null then 1 end) zxwrs, "+
			"count(case when b.xh is null then 1 end) wzxrs, "+
			"count(case when a.xb='男' and b.xh is null then 1 end) wzxmrs, "+
			"count(case when a.xb='女' and b.xh is null then 1 end) wzxwrs ";
			//重庆工商大学个性化统计走读学生和非走读非住宿学生
			if(Base.xxdm.equals("11799")){
				sql+=",count(case when b.xh is null and c.xh is null then 1 end) fzfzrs";
				sql+=",count(case when c.xh is not null then 1 end) zdrs";
				sql+=",count(case when a.xb='男' and b.xh is null and c.xh is null then 1 end) fzfzmrs";
				sql+=",count(case when a.xb='男' and c.xh is not null then 1 end) zdmrs";
				sql+=",count(case when a.xb='女' and b.xh is null and c.xh is null then 1 end) fzfzwrs";
				sql+=",count(case when a.xb='女' and c.xh is not null then 1 end) zdwrs";
			}
			sql+=" from view_xsjbxx a left join xg_gygl_new_cwxxb b on a.xh=b.xh ";
			//重庆工商大学个性化
			if(Base.xxdm.equals("11799")){
				sql+=" left join xg_gygl_new_bzxbzb c on a.xh = c.xh and c.bzxbz is not null ";
			}
			sql+="group by a.nj,a.xydm,a.xymc "+
			"order by a.xydm,a.nj) a where 1=1 " +searchTj+"";
//		return dao.getListNotOut(sql, inputV);
			String[] colList = null;
			//重庆工商大学个性化
			if(Base.xxdm.equals("11799")){
				colList =new String[]{"xydm","xymc","nj","zrs","zxrs","zxrsbfb","wzxrs","wzxrsbfb","mrs","mrsbfb","zxmrs",
				"zxmrsbfb","wzxmrs","wzxmrsbfb","wrs","wrsbfb","zxwrs","zxwrsbfb","wzxwrs","wzxwrsbfb","fzfzrs","zdrs","fzfzmrs",
				"zdmrs","fzfzwrs","zdwrs","fzfzrsbfb","zdrsbfb","fzfzmrsbfb","zdmrsbfb","fzfzwrsbfb","zdwrsbfb"};
			}else{
				colList =new String[]{"xydm","xymc","nj","zrs","zxrs","zxrsbfb","wzxrs","wzxrsbfb","mrs","mrsbfb","zxmrs",
				"zxmrsbfb","wzxmrs","wzxmrsbfb","wrs","wrsbfb","zxwrs","zxwrsbfb","wzxwrs","wzxwrsbfb"};
			}
			return CommonQueryDAO.commonQueryforList(sql, "", inputV, colList, myForm);
		}
		
	}
	
	/**
	 * 获取学生住宿统计信息列表(详情)
	 * @return
	 */
	public List<HashMap<String, String>> getXszstjDetailxxList(XszstjForm myForm){
		String sql="select a.*, " +
				"(case when zrs<>0 then to_char(round(zxrs/zrs*100,2),'990.99') else '0' end) zxrsbfb, " +
				"(case when zrs<>0 then to_char(round(wzxrs/zrs*100,2),'990.99') else '0' end) wzxrsbfb, " +
				"(case when zrs<>0 then to_char(round(mrs/zrs*100,2),'990.99') else '0' end) mrsbfb, " +
				"(case when zrs<>0 then to_char(round(wrs/zrs*100,2),'990.99') else '0' end) wrsbfb, " +
				"(case when zxrs<>0 then to_char(round(zxmrs/zxrs*100,2),'990.99') else '0' end) zxmrsbfb, " +
				"(case when zxrs<>0 then to_char(round(zxwrs/zxrs*100,2),'990.99') else '0' end) zxwrsbfb, " +
				"(case when wzxrs<>0 then to_char(round(wzxmrs/wzxrs*100,2),'990.99') else '0' end) wzxmrsbfb, " +
				"(case when wzxrs<>0 then to_char(round(wzxwrs/wzxrs*100,2),'990.99') else '0' end) wzxwrsbfb ";
				//重庆工商大学个性化统计走读学生和非走读非住宿学生
				if(Base.xxdm.equals("11799")){
					sql+=",(case when zrs<>0 and fzfzrs <> 0 then to_char(round(fzfzrs/zrs*100,2),'990.99') else '0.00' end) fzfzrsbfb";
					sql+=",(case when zrs<>0 and zdrs <> 0 then to_char(round(zdrs/zrs*100,2),'990.99') else '0.00' end) zdrsbfb";
					sql+=",(case when mrs<>0 and fzfzmrs <> 0 then to_char(round(fzfzmrs/mrs*100,2),'990.99') else '0.00' end) fzfzmrsbfb";
					sql+=",(case when mrs<>0 and zdmrs <> 0 then to_char(round(zdmrs/mrs*100,2),'990.99') else '0.00' end) zdmrsbfb";
					sql+=",(case when wrs<>0 and fzfzwrs <> 0 then to_char(round(fzfzwrs/wrs*100,2),'990.99') else '0.00' end) fzfzwrsbfb";
					sql+=",(case when wrs<>0 and zdwrs <> 0 then to_char(round(zdwrs/wrs*100,2),'990.99') else '0.00' end) zdwrsbfb";
				}
				sql+=" from ( select a.nj,a.xydm,a.xymc,a.bjdm,a.bjmc,count(1) zrs, "+
					"count(case when a.xb='男' then 1 end) mrs, "+
					"count(case when a.xb='女' then 1 end) wrs, "+
					"count(case when b.xh is not null then 1 end) zxrs, "+
					"count(case when a.xb='男' and b.xh is not null then 1 end) zxmrs, "+
					"count(case when a.xb='女' and b.xh is not null then 1 end) zxwrs, "+
					"count(case when b.xh is null then 1 end) wzxrs, "+
					"count(case when a.xb='男' and b.xh is null then 1 end) wzxmrs, "+
					"count(case when a.xb='女' and b.xh is null then 1 end) wzxwrs ";
				//重庆工商大学个性化统计走读学生和非走读非住宿学生
				if(Base.xxdm.equals("11799")){
					sql+=",count(case when b.xh is null and c.xh is null then 1 end) fzfzrs";
					sql+=",count(case when c.xh is not null then 1 end) zdrs";
					sql+=",count(case when a.xb='男' and b.xh is null and c.xh is null then 1 end) fzfzmrs";
					sql+=",count(case when a.xb='男' and c.xh is not null then 1 end) zdmrs";
					sql+=",count(case when a.xb='女' and b.xh is null and c.xh is null then 1 end) fzfzwrs";
					sql+=",count(case when a.xb='女' and c.xh is not null then 1 end) zdwrs ";
				}	
					sql+="from view_xsjbxx a left join xg_gygl_new_cwxxb b on a.xh=b.xh ";
					//重庆工商大学个性化
					if(Base.xxdm.equals("11799")){
						sql+=" left join xg_gygl_new_bzxbzb c on a.xh = c.xh and c.bzxbz is not null ";
					}
					sql+="where a.nj=? and a.xydm=? "+
					"group by a.nj,a.xydm,a.xymc,a.bjdm,a.bjmc "+
					"order by a.xydm,a.nj,bjdm) a";
		return dao.getListNotOut(sql, new String[]{myForm.getNj(),myForm.getXydm()});
	}
	
	public ArrayList<String[]> getXszstjXsxxList(XszstjForm myForm, HttpServletRequest request) 
	throws Exception{		
		// 高级查询条件
//		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
//		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		// 权限控制
//		SearchService searchService = new SearchService();
//		String searchTjByUser = searchService.getSearchTjByUser(request, "",
//				"xydm", null);
//		searchTj += searchTjByUser;
		
//		String searchTjstr = "";
//		if(searchTj!=null && !"".equalsIgnoreCase(searchTj)){
//			String[] tj = searchTj.replace("?", "split").split("split");
//			for(int i=0;i<inputV.length;i++){
//				searchTjstr += tj[i]+inputV[i];
//			}
//			searchTjstr+=tj[inputV.length];
//		}
//		request.setAttribute("searchTjstr", searchTjstr);
		
		
		String searchTj="";
		if(!Base.isNull(myForm.getNj())&&!Base.isNull(myForm.getXydm())){
			searchTj+=" and nj='"+myForm.getNj()+"' and xydm='"+myForm.getXydm()+"' ";
		}
		
		if(!Base.isNull(myForm.getBjdm())){
			searchTj+=" and bjdm='"+myForm.getBjdm()+"'";
		}
		
		if("yes".equals(myForm.getRzzt())){
			searchTj+=" and cwh is not null";
		}else if("no".equals(myForm.getRzzt())){
			searchTj+=" and cwh is null";
		}
		
		if("m".equals(myForm.getXb())){
			searchTj+=" and xb='男'";
		}else if("w".equals(myForm.getXb())){
			searchTj+=" and xb='女'";
		}
		
		
		String sql = "select rownum r,a.* from xg_view_gygl_new_xszsgl a where 1=1 ";		
		String[] colList = new String[]{"xh","xm","nj","xymc","xb","ldmc", "qsh", "cwh"};		
		
		return  commonQuery(sql,searchTj,new String[]{},colList,myForm);
	}

	public Object getXyList(HttpServletRequest request) {
		String sql = "select distinct xymc from view_xg_gygl_jhzy_qsfbb order by xymc ";
		return new DAO().getList(sql, new String[] {}, new String[] { "xymc","xymc" });
	}

	/**
	 * 导出寝室分布统计表
	 * @author wujian
	 */
	public ArrayList<String[]> expQsfbb(XszstjForm model,String xymc) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select xymc,ldmc,QSH,QSXB,QSRS,BZ,nj||' '||zymc||' '||bjmc bjmc,FDY from view_xg_gygl_jhzy_qsfbb where xymc=? order by ldmc,qsh ");
		String[] colList = null;
		colList = new String[] { "xymc", "ldmc", "QSH", "QSXB", "QSRS", "BZ", "BJMC", "FDY" };
		ArrayList<String[]> list = commonQueryNotFy(sql.toString(),"",new String[]{xymc}, colList, model);

		return list;
	}
	
public List<HashMap<String, String>> getXszstjxxExportList(XszstjForm myForm,HttpServletRequest request) throws Exception{
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		// 权限控制
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm");
		searchTj += searchTjByUser;
		User user=searchService.getUser(request);
		
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// 班主任权限
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		if(fdyqx||bzrqx){
			String sql="select rownum r,a.*," +
			"(case when zrs<>0 then to_char(round(zxrs/zrs*100,2),'990.99') else '0' end) zxrsbfb, " +
			"(case when zrs<>0 then to_char(round(wzxrs/zrs*100,2),'990.99') else '0' end) wzxrsbfb, " +
			"(case when zrs<>0 then to_char(round(mrs/zrs*100,2),'990.99') else '0' end) mrsbfb, " +
			"(case when zrs<>0 then to_char(round(wrs/zrs*100,2),'990.99') else '0' end) wrsbfb, " +
			"(case when mrs<>0 then to_char(round(zxmrs/mrs*100,2),'990.99') else '0' end) zxmrsbfb, " +
			"(case when wrs<>0 then to_char(round(zxwrs/wrs*100,2),'990.99') else '0' end) zxwrsbfb, " +
			"(case when mrs<>0 then to_char(round(wzxmrs/mrs*100,2),'990.99') else '0' end) wzxmrsbfb, " +
			"(case when wrs<>0 then to_char(round(wzxwrs/wrs*100,2),'990.99') else '0' end) wzxwrsbfb " +
			"from (select a.nj,a.xydm,a.xymc,a.bjdm,a.bjmc,count(1) zrs, "+
			"count(case when a.xb='男' then 1 end) mrs, "+
			"count(case when a.xb='女' then 1 end) wrs, "+
			"count(case when b.xh is not null then 1 end) zxrs, "+
			"count(case when a.xb='男' and b.xh is not null then 1 end) zxmrs, "+
			"count(case when a.xb='女' and b.xh is not null then 1 end) zxwrs, "+
			"count(case when b.xh is null then 1 end) wzxrs, "+
			"count(case when a.xb='男' and b.xh is null then 1 end) wzxmrs, "+
			"count(case when a.xb='女' and b.xh is null then 1 end) wzxwrs "+
			"from view_xsjbxx a left join xg_gygl_new_cwxxb b on a.xh=b.xh "+
			"group by a.nj,a.xydm,a.xymc,a.bjdm,a.bjmc "+
			"order by a.xydm,a.nj) a where 1=1 " +searchTj+"";
//			return dao.getListNotOut(sql, inputV);
			String[] colList =new String[]{"xydm","xymc","bjdm","bjmc","nj","zrs","zxrs","zxrsbfb","wzxrs","wzxrsbfb","mrs","mrsbfb","zxmrs",
								"zxmrsbfb","wzxmrs","wzxmrsbfb","wrs","wrsbfb","zxwrs","zxwrsbfb","wzxwrs","wzxwrsbfb"};
			return CommonQueryDAO.commonQueryforList(sql, "", inputV, colList, myForm);
		}else{
			String sql="select rownum r,a.*," +
			"(case when zrs<>0 then to_char(round(zxrs/zrs*100,2),'990.99') else '0' end) zxrsbfb, " +
			"(case when zrs<>0 then to_char(round(wzxrs/zrs*100,2),'990.99') else '0' end) wzxrsbfb, " +
			"(case when zrs<>0 then to_char(round(mrs/zrs*100,2),'990.99') else '0' end) mrsbfb, " +
			"(case when zrs<>0 then to_char(round(wrs/zrs*100,2),'990.99') else '0' end) wrsbfb, " +
			"(case when mrs<>0 then to_char(round(zxmrs/mrs*100,2),'990.99') else '0' end) zxmrsbfb, " +
			"(case when wrs<>0 then to_char(round(zxwrs/wrs*100,2),'990.99') else '0' end) zxwrsbfb, " +
			"(case when mrs<>0 then to_char(round(wzxmrs/mrs*100,2),'990.99') else '0' end) wzxmrsbfb, " +
			"(case when wrs<>0 then to_char(round(wzxwrs/wrs*100,2),'990.99') else '0' end) wzxwrsbfb " +
			"from (select a.nj,a.xydm,a.xymc,count(1) zrs, "+
			"count(case when a.xb='男' then 1 end) mrs, "+
			"count(case when a.xb='女' then 1 end) wrs, "+
			"count(case when b.xh is not null then 1 end) zxrs, "+
			"count(case when a.xb='男' and b.xh is not null then 1 end) zxmrs, "+
			"count(case when a.xb='女' and b.xh is not null then 1 end) zxwrs, "+
			"count(case when b.xh is null then 1 end) wzxrs, "+
			"count(case when a.xb='男' and b.xh is null then 1 end) wzxmrs, "+
			"count(case when a.xb='女' and b.xh is null then 1 end) wzxwrs "+
			"from view_xsjbxx a left join xg_gygl_new_cwxxb b on a.xh=b.xh "+
			"group by a.nj,a.xydm,a.xymc "+
			"order by a.xydm,a.nj) a where 1=1 " +searchTj+"";
//		return dao.getListNotOut(sql, inputV);
			String[] colList =new String[]{"xydm","xymc","nj","zrs","zxrs","zxrsbfb","wzxrs","wzxrsbfb","mrs","mrsbfb","zxmrs",
					"zxmrsbfb","wzxmrs","wzxmrsbfb","wrs","wrsbfb","zxwrs","zxwrsbfb","wzxwrs","wzxwrsbfb"};
			
			return CommonQueryDAO.commonQueryforExportList(sql, "", inputV, colList, myForm);
		}
		
	}
	
	/**
	 * 
	 * @描述:图表统计
	 * @作者：cq [工号：785]
	 * @日期：2016-5-17 下午04:08:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param xymc
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTbtj() throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select lddm,ldmc,yrz,kcw,round(yrz/(kcw+yrz)*100,2) rzl from ( ");
		sql.append("select a.lddm,b.ldmc,sum(case when xh is null then 0 else 1 end) yrz,sum(case when xh is null then 1 else 0 end) kcw  ");
		sql.append("from xg_gygl_new_cwxxb a left join xg_gygl_new_ldxxb b on a.lddm=b.lddm group by a.lddm,b.ldmc) ");

		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/** 
	 * @描述:住宿概况统计
	 * @作者：cq [工号：785]
	 * @日期：2016-5-18 下午05:21:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getZsgktj() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select sslzs,fjzs,cws,case when rzl like '%.' then substr(rzl,0,length(rzl)-1) else rzl end rzl,");
		sql.append("kcws,kapns,kapnv from(");
		sql.append("select count(distinct a.lddm) sslzs,");
		sql.append("count(distinct a.lddm||'$'||a.qsh) fjzs,count(distinct a.lddm||'$'||a.qsh||'$'||a.cwh) cws,");
		sql.append("to_char(round(sum(case when a.xh is null then 0 else 1 end)/count(1)*100,2),'fm9999990.9999') ");
		sql.append("rzl,sum(case when a.xh is null then 1 else 0 end) kcws, ");
		sql.append("sum(case when xh is null and b.qsxb in ('1','男') then 1 else 0 end) kapns,");
		sql.append("sum(case when xh is null and b.qsxb in ('2','女') then 1 else 0 end) kapnv ");
		sql.append("from xg_gygl_new_cwxxb a left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh)");
		
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @描述:获取宿舍楼信息
	 * @作者：cq [工号：785]
	 * @日期：2016-5-19 上午10:12:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSslInfo(){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select lddm,ldmc,cs,qss,cw,case when rzl like '%.' then substr(rzl,0,length(rzl)-1) else rzl end rzl,kcw,ldxb,hbh from ( ");
		sql.append("select '$_'||c.lddm lddm,c.ldmc,count(distinct b.ch) cs,count(distinct b.ch||b.qsxb) hbh, ");
		sql.append("count(distinct b.qsh) qss,count(1) cw, ");
		sql.append("to_char(round(sum(case when a.xh is null then 0 else 1 end)/count(1)*100,2),'fm9999990.9999') rzl, ");
		sql.append("sum(case when a.xh is null then 1 else 0 end) kcw,c.ldxb ");
		sql.append("from xg_gygl_new_cwxxb a left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh ");
		sql.append("left join xg_gygl_new_ldxxb c on a.lddm=c.lddm and b.lddm=c.lddm group by c.lddm,c.ldmc,c.ldxb order by lddm )");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @描述:获取宿舍楼层信息
	 * @作者：cq [工号：785]
	 * @日期：2016-5-19 上午10:14:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSslcInfo(){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select '$_'||b.lddm lddm,b.ch,count(distinct b.qsh) ss,count(1) cw,sum(case when xh is null then 1 else 0 end) kcw,b.qsxb ");
		sql.append("from xg_gygl_new_cwxxb a left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh ");
		sql.append("group by b.lddm,b.ch,b.qsxb order by lddm,to_number(b.ch) desc ");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/** 
	 * @描述:各系宿舍分布图
	 * @作者：cq [工号：785]
	 * @日期：2016-6-1 上午10:29:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGxsstj() throws Exception {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select t1.*,nvl(t2.lddm,'无')lddm,nvl(t2.ldmc,'无')ldmc,nvl(t2.ch,'无')ch, ");
		sql.append(" nvl(t2.sss,0)sss,nvl(t2.kcw,0)kcw,nvl(t2.rzrs,0)rzrs ");
		sql.append(" from ( ");
		sql.append("  select a.xydm,a.xymc,nvl(a.xb,c.qsxb) xb, ");
		sql.append("  sum(count(1)) over(partition by a.xydm) xyzrs,");
		sql.append("  sum(case when b.xh is null then 0 else 1 end) rzzrs, ");
		sql.append("  count(1) zrs, ");
		sql.append("  round(sum(sum(case when b.xh is null then 0 else 1 end))over(partition by a.xydm)/ ");
		sql.append("   sum(count(1)) over (partition by a.xydm)*100,2) zrzl, ");
		sql.append("  round(sum(case when b.xh is null then 0 else 1 end)/count(1)*100,2) rzl ");
		sql.append("  from view_xsjbxx a left join xg_gygl_new_cwxxb b on a.xh=b.xh ");
		sql.append("  left join xg_gygl_new_qsxxb c on b.lddm=c.lddm and b.qsh=c.qsh ");
		sql.append("  where a.xydm is not null ");
		sql.append("  group by a.xydm,a.xymc,nvl(a.xb,c.qsxb)) t1 ");
		sql.append(" left join ");
		sql.append(" (select xydm,qsxb xb,lddm,ldmc,ch,count(distinct qsh) sss, ");
		sql.append(" sum(case when xh is null then 1 else 0 end) kcw, ");
		sql.append(" sum(case when xh is null then 0 else 1 end) rzrs ");
		sql.append(" from view_xg_gygl_new_cwxx where xydm is not null ");
		sql.append(" group by xydm,qsxb,lddm,ldmc,ch) t2 on t1.xydm=t2.xydm and t1.xb=t2.xb ");
		sql.append(" where t1.xb is not null order by t1.xydm,t1.xb,t2.lddm,to_number(t2.ch)");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/** 
	 * 各系空床位信息
	 */
	public List<HashMap<String, String>> getGxkcwxx() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select xydm,(select t2.bmmc from zxbz_xxbmdm t2 where t1.xydm=t2.bmdm) bmmc, ");
		sql.append("lddm,ldmc,ch,qsh,qsxb,count(1) cws,sum(case when xh is null then 1 else 0 end) sycw ");
		sql.append("from view_xg_gygl_new_cwxx t1 where xydm is not null ");
		sql.append("group by xydm,lddm,ldmc,ch,qsh,qsxb) where sycw >0 order by xydm,lddm,to_number(ch),qsh ");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
}
