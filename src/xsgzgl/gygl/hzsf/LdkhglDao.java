package xsgzgl.gygl.hzsf;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
/**
 * 公寓管理-湖州师范-楼栋考核管理
 * @author yeyipin
 * @since 2012.12.25 merry christmas
 */
public class LdkhglDao extends DAO{

	/**
	 * 获得每月最高分，最低分
	 * @return
	 */
	public List<HashMap<String, String>> ldkhgl(LdkhglForm model) {
		String ldmc = model.getSearchModel().getInput_mhcx();
		String searchTj = "";
		if(ldmc!=null && !"".equalsIgnoreCase(ldmc)){
			String[] ldmcs = ldmc.split(" ");
			searchTj += " and (ldmc like '%"+ldmcs[0]+"%' ";
			for(int i=1; i< ldmcs.length;i++){
				searchTj += " or ldmc like '%"+ldmcs[i]+"%' ";
			}
			searchTj += ") ";
		}
		String sql = "select yf,max(to_number(zf)) khzgf,min(to_number(zf)) khzdf from xg_gygl_ldkhcjb a group by yf";
		List<HashMap<String, String>>  getList = getList(sql, new String[]{}, new String[]{"yf","khzgf","khzdf"});
		sql = "select b.ldmc,a.zf,a.yf from xg_gygl_ldkhcjb a,xg_gygl_new_ldxxb b where a.lddm = b.lddm"+searchTj;
		List<HashMap<String, String>>  allList = getList(sql, new String[]{}, new String[]{"ldmc","zf","yf"});
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String,String>>();
		for(int i = 0; i < getList.size(); i++){
			HashMap<String, String> resultMap = new HashMap<String,String>();
			String yf = getList.get(i).get("yf");
			String khzgf = getList.get(i).get("khzgf");
			String khzdf = getList.get(i).get("khzdf");
			for(int j = 0; j < allList.size(); j++){
				HashMap<String,String> result = allList.get(j);
				if(yf.equalsIgnoreCase(result.get("yf"))){
					if(Base.isNull(resultMap.get("yf"))){
						resultMap.put("yf", result.get("yf"));
						resultMap.put("khzgf", khzgf);
						resultMap.put("khzdf", khzdf);
						resultMap.put("zgfld", "");
						resultMap.put("zdfld", "");
					}
					if(khzgf.equalsIgnoreCase(result.get("zf"))){
						if(Base.isNull(resultMap.get("zgfld"))){
							resultMap.put("zgfld",result.get("ldmc"));
						}else{
							resultMap.put("zgfld", resultMap.get("zgfld")+","+result.get("ldmc"));
						}
					}
					if(khzdf.equalsIgnoreCase(result.get("zf"))){
						if(Base.isNull(resultMap.get("zdfld"))){
							resultMap.put("zdfld", result.get("ldmc"));
						}else{
							resultMap.put("zdfld", resultMap.get("zdfld")+","+result.get("ldmc"));
						}
						
					}
				}
			}
			resultList.add(resultMap);
		}
		return resultList;
	}

	
	/**
	 * 考核成绩维护
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> khcjwh(LdkhglForm model) throws Exception{
		String pkValue = model.getPkValue();
		String ldmc = model.getSearchModel().getInput_mhcx();
		String searchTj = "";
		if(ldmc!=null && !"".equalsIgnoreCase(ldmc)){
			String[] ldmcs = ldmc.split(" ");
			searchTj += " and (ldmc like '%"+ldmcs[0]+"%' ";
			for(int i=1; i< ldmcs.length;i++){
				searchTj += " or ldmc like '%"+ldmcs[i]+"%' ";
			}
			searchTj += ") ";
		}
		//权限控制 	
		String searchTjQx = "";
		String[] colList = new String[] { "lddm", "ldmc", "lh", "zz", "xc", "hqjc", "dxjc", "sjjc", "lzfk", "xyzg","zf","pm" };
		String sql = "select a.* from (select a.lddm,a.ldmc,b.lh,b.zz,b.xc,b.hqjc,b.dxjc,b.sjjc,b.lzfk,b.xyzg,b.zf,b.yf,b.pm from xg_gygl_new_ldxxb a left join (select a.*,(rank()over(partition by yf order by to_number(a.zf) desc nulls last)) pm from xg_gygl_ldkhcjb a where yf = '"+pkValue+"') b on a.lddm = b.lddm order by pm)a where 1=1";
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQueryNotFy(sql, searchTj+searchTjQx, new String[]{}, colList, model);
	}


	/**
	 * 考核成绩保存
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean khcjBc(List<String[]> params) throws SQLException {
		String sql = "insert into xg_gygl_ldkhcjb(yf,lddm,lh,zz,xc,hqjc,dxjc,sjjc,lzfk,xyzg,zf)values(?,?,?,?,?,?,?,?,?,?,?) ";
		int[] result = runBatch(sql,params);
		return checkBatchResult(result);
	}


	/**
	 * 删除考核成绩
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public boolean delKhcj(String pkValue) throws Exception {
		String sql = "delete from xg_gygl_ldkhcjb where yf = ? ";
		return runUpdate(sql,new String[]{pkValue});
	}
}
