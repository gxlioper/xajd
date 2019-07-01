package xsgzgl.xsxx.general.tjcx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;



import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

/**
 * 
 * <b>学生信息一览统计</b>
 * @author Penghui.Qu
 */
public class XsxxTjcxDao {

	private DAO dao = DAO.getInstance();
	
	
	/**
	 * 按年级统计全部在校生人数及比例
	 * @return
	 */
	public List<HashMap<String,String>> getTjcxByNj(User user){
		String sjfw = "" ; 
		String usertype = user.getUserType();
		if("xy".equals(usertype)){
			sjfw = " and xydm = '"+user.getUserDep()+"'";
		}
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nj,sum(man) man,sum(woman) woman,sum(man+woman) allRs,");
		sql.append("round((sum(man)/sum(man+woman))*100,1)||'%' manbl,");
		sql.append("round((1-(sum(man)/sum(man+woman)))*100,1)||'%' womanbl from (");
		sql.append("select nj,case when xb='男' then 1 else 0 end man,");
		sql.append("case when xb='女' then 1 else 0 end woman ");
		sql.append("from view_xsxxb  where nvl(sfzx,'在校')='在校' "+sjfw+"");
		//西安文理学院个性化，去掉无学籍学生
		if(Base.xxdm.equals("11080")){
			sql.append(" and xjztm = '有学籍' ");
		}
		sql.append(") where man+woman <> 0 group by nj order by nj");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 按年级、学院统计在校生人数及比例
	 * @param njArr
	 * @return
	 */
	public List<HashMap<String,String>> getTjcxByNjXy(String[] njArr,User user){
		
		StringBuilder sql = new StringBuilder();
		List<String> input = new ArrayList<String>();
		String sjfw = "" ; 
		String usertype = user.getUserType();
		if("xy".equals(usertype)){
			sjfw = " and xydm = '"+user.getUserDep()+"'";
		}
		sql.append("select xydm,xymc,");
		sql.append("sum(man) man,sum(woman) woman,sum(man+woman) allRs,");
		sql.append("round((sum(man)/sum(man+woman))*100,1)||'%' manbl,");
		sql.append("round((1-(sum(man)/sum(man+woman)))*100,1)||'%' womanbl from (");
		sql.append("select xydm,xymc,case when xb='男' then 1 else 0 end man,");
		sql.append("case when xb='女' then 1 else 0 end woman ");
		sql.append("from view_xsxxb  where nvl(sfzx,'在校')='在校' "+sjfw+"");
		
		if (njArr != null && njArr.length > 0){
			sql.append("and (");
			
			for (int i = 0,c = njArr.length ; i < c ; i++){
				sql.append("nj=?");
				input.add(njArr[i]);
				
				if (i != c-1){
					sql.append(" or ");
				}
			}
			
			sql.append(")");
		}
		//西安文理学院个性化，去掉无学籍学生
		if(Base.xxdm.equals("11080")){
			sql.append(" and xjztm = '有学籍' ");
		}
		sql.append(") group by xydm,xymc order by xydm,xymc");
		return dao.getListNotOut(sql.toString(), input.toArray(new String[]{}));
	}
	
	
	/**
	 * 按年级、学院统计专业总数
	 * @param njArr
	 * @return
	 */
	public List<HashMap<String,String>> getZyzsByNjXy(String[] njArr,User user){
		
		String sjfw = "" ; 
		String usertype = user.getUserType();
		if("xy".equals(usertype)){
			sjfw = " and xydm = '"+user.getUserDep()+"'";
		}
		List<String> input = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select xydm,count(1) zyzs from (select distinct t1.xydm,t1.zydm from view_njxyzybj t1 where 1= 1 "+sjfw+" ");
		//sql.append(" where exists (select 1 from view_xsxxb t2 where t1.xydm=t2.xydm and nvl(sfzx,'在校')='在校') ");
		
		if (njArr != null && njArr.length > 0){
			sql.append(" and (");
			
			for (int i = 0,c = njArr.length ; i < c ; i++){
				sql.append("nj=?");
				input.add(njArr[i]);
				
				if (i != c-1){
					sql.append(" or ");
				}
			}
			
			sql.append(")");
		}
		
		sql.append(") group by xydm");
		
		return dao.getListNotOut(sql.toString(), input.toArray(new String[]{}));
	}
	
	
	
	/**
	 * 按年级、学院统计班级总数
	 * @param nj
	 * @return
	 */
	public List<HashMap<String,String>> getBjzsByNjXy(String[] njArr,User user){
		String sjfw = "" ; 
		String usertype = user.getUserType();
		if("xy".equals(usertype)){
			sjfw = " and xydm = '"+user.getUserDep()+"'";
		}
		List<String> input = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xydm,count(1) bjzs from (select distinct t1.xydm,t1.zydm,t1.bjdm from view_njxyzybj t1 where 1= 1 "+sjfw+" ");
		//sql.append(" where exists (select 1 from view_xsxxb t2 where t1.xydm=t2.xydm and nvl(sfzx,'在校')='在校') ");
		
		if (njArr != null && njArr.length > 0){
			sql.append(" and (");
			
			for (int i = 0,c = njArr.length ; i < c ; i++){
				sql.append("nj=?");
				input.add(njArr[i]);
				
				if (i != c-1){
					sql.append(" or ");
				}
			}
			
			sql.append(")");
		}
		sql.append(") group by xydm");
		
		return dao.getListNotOut(sql.toString(), input.toArray(new String[]{}));
	}
	
	
	
	/**
	 * 按年级、学院、专业统计
	 * @param nj
	 * @param xydm
	 * @return
	 */
	public List<HashMap<String,String>> getTjcxByZy(String[] njArr , String[] xyArr){
		
		List<String> input = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select zydm,zymc,sum(man) man,sum(woman) woman,sum(man + woman) allRs,");
		sql.append("round((sum(man) / sum(man + woman)) * 100,1) || '%' manbl,");
		sql.append("round((1-(sum(man) / sum(man + woman))) * 100,1) || '%' womanbl ");
		sql.append("from (select zydm,zymc,");
		sql.append("case when xb = '男' then 1 else 0 end man,");
		sql.append("case when xb = '女' then 1 else 0 end woman ");
		sql.append("from view_xsxxb where nvl(sfzx,'在校')='在校' ");
		
		if (njArr != null && njArr.length > 0){
			sql.append("and (");
			
			for (int i = 0,c = njArr.length ; i < c ; i++){
				sql.append("nj=?");
				input.add(njArr[i]);
				
				if (i != c-1){
					sql.append(" or ");
				}
			}
			
			sql.append(")");
		}
		
		if (xyArr != null && xyArr.length > 0){
			sql.append("and (");
			
			for (int i = 0,c = xyArr.length ; i < c ; i++){
				sql.append("xydm=?");
				input.add(xyArr[i]);
				
				if (i != c-1){
					sql.append(" or ");
				}
			}
			
			sql.append(")");
		}
		//西安文理学院个性化，去掉无学籍学生
		if(Base.xxdm.equals("11080")){
			sql.append(" and xjztm = '有学籍' ");
		}
		sql.append(") group by zydm, zymc order by  zydm, zymc");
		
		return dao.getListNotOut(sql.toString(), input.toArray(new String[]{}));
	}
	
	
	
	
	/**
	 * 按专业统计班级总数
	 * @param nj
	 * @param xydm
	 * @return
	 */
	public List<HashMap<String,String>> getBjzsByZy(String[] njArr , String[] xyArr){
		
		List<String> input = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select zydm,count(1) bjzs from (");
		sql.append("select distinct t1.zydm,t1.bjdm from view_njxyzybj t1 ");
		sql.append("where exists (select 1 from view_xsxxb t2 where t1.zydm=t2.zydm) ");
		
		if (njArr != null && njArr.length > 0){
			sql.append("and (");
			
			for (int i = 0,c = njArr.length ; i < c ; i++){
				sql.append("nj=?");
				input.add(njArr[i]);
				
				if (i != c-1){
					sql.append(" or ");
				}
			}
			
			sql.append(")");
		}
		
		if (xyArr != null && xyArr.length > 0){
			sql.append("and (");
			
			for (int i = 0,c = xyArr.length ; i < c ; i++){
				sql.append("xydm=?");
				input.add(xyArr[i]);
				
				if (i != c-1){
					sql.append(" or ");
				}
			}
			
			sql.append(")");
		}
		
		sql.append(" ) group by zydm");
		
		return dao.getListNotOut(sql.toString(), input.toArray(new String[]{}));
	}
	
	
	
	/**
	 * 按年级、学院、专业、班级统计
	 * @param nj
	 * @param xydm
	 * @param zydm
	 * @return
	 */
	public List<HashMap<String,String>> getTjcxByBj(String[] njArr , String[] xyArr , String[] zyArr){
		
		List<String> input = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select bjdm,bjmc,sum(man) man,sum(woman) woman,sum(man + woman) allRs,");
		sql.append("round((sum(man) / sum(man + woman)) * 100,1) || '%' manbl,");
		sql.append("round((1-(sum(man) / sum(man + woman))) * 100,1) || '%' womanbl ");
		sql.append("from (select bjdm,bjmc,");
		sql.append("case when xb = '男' then 1 else 0 end man,");
		sql.append("case when xb = '女' then 1 else 0 end woman ");
		sql.append("from view_xsxxb where nvl(sfzx,'在校')='在校' ");
		
		if (njArr != null && njArr.length > 0){
			sql.append("and (");
			
			for (int i = 0,c = njArr.length ; i < c ; i++){
				sql.append("nj=?");
				input.add(njArr[i]);
				
				if (i != c-1){
					sql.append(" or ");
				}
			}
			
			sql.append(")");
		}
		
		if (xyArr != null && xyArr.length > 0){
			sql.append("and (");
			
			for (int i = 0,c = xyArr.length ; i < c ; i++){
				sql.append("xydm=?");
				input.add(xyArr[i]);
				
				if (i != c-1){
					sql.append(" or ");
				}
			}
			
			sql.append(")");
		}
		
		if (zyArr != null && zyArr.length > 0){
			sql.append("and (");
			
			for (int i = 0,c = zyArr.length ; i < c ; i++){
				sql.append("zydm=?");
				input.add(zyArr[i]);
				
				if (i != c-1){
					sql.append(" or ");
				}
			}
			
			sql.append(")");
		}
		//西安文理学院个性化，去掉无学籍学生
		if(Base.xxdm.equals("11080")){
			sql.append(" and xjztm = '有学籍' ");
		}
		sql.append(") group by bjdm, bjmc order by  bjdm, bjmc");
		
		return dao.getListNotOut(sql.toString(), input.toArray(new String[]{}));
	}
	
	/**
	 * 按班级查询学生数据
	 * @return
	 */
	public ArrayList<String[]> tjxsByBjdm(XsxxTjcxForm model)throws IllegalArgumentException, SecurityException, 
			IllegalAccessException, InvocationTargetException, 
			NoSuchMethodException{
		StringBuilder sql = new StringBuilder();
		String[]colList = new String[]{"xh", "xm","xb","sfzh","mzmc","zzmmmc","sjhm","nj","xymc","zymc","bjmc"};
		String query="";
		query=" where 1=1 ";
		sql.append("select rownum r,a.* from (select xh,xm,xb,sfzh,mzmc,zzmmmc,sjhm,nj,xymc,zymc,bjmc from view_xsxxb where nvl(sfzx,'在校')='在校'  ");
		//西安文理学院个性化，去掉无学籍学生
		if(Base.xxdm.equals("11080")){
			sql.append(" and xjztm = '有学籍' ");
		}
		sql.append(" and bjdm = '"+model.getBjdm()+"' ) a");
		return CommonQueryDAO.commonQuery(sql.toString(), query, new String[]{},colList, model);
	}
	
	/**
	 * 按学院统计全部在校生人数及比例
	 * @return
	 */
	public List<HashMap<String,String>> getTjcxByXy(User user){
		String sjfw = "" ; 
		String usertype = user.getUserType();
		if("xy".equals(usertype)){
			sjfw = " and xydm = '"+user.getUserDep()+"'";
		}
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xydm,xymc,sum(man) man,sum(woman) woman,sum(man+woman) allRs,");
		sql.append(" round((sum(man)/sum(man+woman))*100,1)||'%' manbl,");
		sql.append("round((1-(sum(man)/sum(man+woman)))*100,1)||'%' womanbl from (");
		sql.append("select xydm,xymc,case when xb='男' then 1 else 0 end man,");
		sql.append(" case when xb='女' then 1 else 0 end woman ");
		sql.append(" from view_xsxxb  where nvl(sfzx,'在校')='在校' "+sjfw+" ");
		//西安文理学院个性化，去掉无学籍学生
		if(Base.xxdm.equals("11080")){
			sql.append(" and xjztm = '有学籍' ");
		}
		sql.append(") where man+woman <> 0 group by xydm,xymc order by xydm,xymc");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 按学院统计专业总数
	 * @param 
	 * @return
	 */
	public List<HashMap<String,String>> getZyzsByXy(User user){
		String sjfw = "" ; 
		String usertype = user.getUserType();
		if("xy".equals(usertype)){
			sjfw = " and xydm = '"+user.getUserDep()+"'";
		}
		List<String> input = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select xydm,count(1) zyzs from (select distinct t1.xydm,t1.zydm from view_njxyzybj t1 where 1= 1 "+sjfw+") group by xydm");
		
		return dao.getListNotOut(sql.toString(), input.toArray(new String[]{}));
	}
	
	/**
	 * 按学院统计班级总数
	 * @param nj
	 * @return
	 */
	public List<HashMap<String,String>> getBjzsByXy(User user){
		String sjfw = "" ; 
		String usertype = user.getUserType();
		if("xy".equals(usertype)){
			sjfw = " and xydm = '"+user.getUserDep()+"'";
		}
		List<String> input = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xydm,count(1) bjzs from (select distinct t1.xydm,t1.zydm,t1.bjdm from view_njxyzybj t1  where 1= 1 "+sjfw+"");

		sql.append(") group by xydm");
		
		return dao.getListNotOut(sql.toString(), input.toArray(new String[]{}));
	}
	
	
	/**
	 * 按条件导出在校生人数及比例
	 * @return
	 */
	public ArrayList<String[]> getDcsjList(XsxxTjcxForm model,String type,User user) 
	throws IllegalArgumentException, SecurityException, 
	IllegalAccessException, InvocationTargetException, 
	NoSuchMethodException{
		String sjfw = "" ; 
		String usertype = user.getUserType();
		if("xy".equals(usertype)){
			sjfw = " and xydm = '"+user.getUserDep()+"'";
		}
		String query="";
		String[]colList = new String[]{};
		String[] input = new String[]{};
		StringBuilder sql = new StringBuilder();
		if("nj".equals(type)){
			colList = new String[]{"nj", "allRs","man","woman","manbl","womanbl"};
			sql.append("select  case when to_char(nj) is null then'合计' else to_char(nj) end nj,sum(man) man,sum(woman) woman,sum(man+woman) allRs,");
			sql.append("round((sum(man)/sum(man+woman))*100,1)||'%' manbl,");
			sql.append("round((1-(sum(man)/sum(man+woman)))*100,1)||'%' womanbl from (");
			sql.append("select nj,case when xb='男' then 1 else 0 end man,");
			sql.append("case when xb='女' then 1 else 0 end woman ");
			sql.append("from view_xsxxb  where nvl(sfzx,'在校')='在校' "+sjfw+"");
			sql.append(") group by rollup (nj) order by nj");
		}
		if("njxy".equals(type)){
			colList = new String[]{"xymc", "allRs","man","woman","manbl","womanbl","zyzs","bjzs"};
			sql.append("select case when b.bmmc is null then '合计' else b.bmmc end xymc, a.* from (select a.xydm, sum(a.man) man,sum(a.woman) woman, sum(a.allrs) allRs,");
			sql.append(" round((sum(a.man) / sum(a.man + a.woman)) * 100,1) || '%' manbl, round((1-(sum(a.man) / sum(a.man + a.woman))) * 100,1) || '%' womanbl,");
			sql.append(" sum(a.zyzs) zyzs, sum(a.bjzs) bjzs  from (select a.*, c.zyzs, d.bjzs from (select xydm, sum(man) man,sum(woman) woman,sum(man + woman) allRs,");
			sql.append(" round((sum(man) / sum(man + woman)) * 100,1) || '%' manbl, round((1-(sum(man) / sum(man + woman))) * 100,1) || '%' womanbl ");
			sql.append(" from (select xydm, xymc,case when xb = '男' then  1 else 0 end man,case when xb = '女' then 1 else 0 end woman ");
			sql.append(" from view_xsxxb where (nvl(sfzx,'在校')='在校' "+sjfw+" ) ");
			if(!StringUtil.isNull(model.getNj())){
				sql.append("and (");
				String [] Arrnj =  model.getNj().split("!!@!!");
				for(int i = 0;i < Arrnj.length;i++){
					if(i!=Arrnj.length-1){
						sql.append(" nj ='"+Arrnj[i]+"' or");
					}else{
						sql.append(" nj ='"+Arrnj[i]+"' ");
					}
				}
				sql.append(")");
			}
			sql.append(" )group by xydm order by xydm) a");
			sql.append(" left join (select xydm, count(1) zyzs  from (select distinct t1.xydm, t1.zydm from view_njxyzybj t1  where exists (select 1 ");
			sql.append(" from view_xsxxb t2 where t1.xydm = t2.xydm and (nvl(t2.sfzx,'在校')='在校' "+sjfw+" ))");
			if(!StringUtil.isNull(model.getNj())){
				sql.append("and (");
				String [] Arrnj =  model.getNj().split("!!@!!");
				for(int i = 0;i < Arrnj.length;i++){
					if(i!=Arrnj.length-1){
						sql.append(" nj ='"+Arrnj[i]+"' or");
					}else{
						sql.append(" nj ='"+Arrnj[i]+"'");
					}
				}
				sql.append(")");
			}
			sql.append(" )group by xydm) c on a.xydm = c.xydm ");
			sql.append(" left join (select xydm, count(1) bjzs from (select distinct t1.xydm, t1.zydm, t1.bjdm  from view_njxyzybj t1  where exists (select 1 ");
			sql.append(" from view_xsxxb t2 where t1.xydm = t2.xydm and (nvl(t2.sfzx,'在校')='在校' "+sjfw+")) ");
			if(!StringUtil.isNull(model.getNj())){
				sql.append("and (");
				String [] Arrnj =  model.getNj().split("!!@!!");
				for(int i = 0;i < Arrnj.length;i++){
					if(i!=Arrnj.length-1){
						sql.append(" nj ='"+Arrnj[i]+"' or");
					}else{
						sql.append(" nj ='"+Arrnj[i]+"' ");
					}
				}
				sql.append(")");
			}
			sql.append(")group by xydm) d ");
			sql.append(" on a.xydm = d.xydm) a group by rollup(xydm) ) a left join zxbz_xxbmdm b on a.xydm = b.bmdm");
			
			input = new String[]{};
			
		}
		if("njxyzy".equals(type)){
			colList = new String[]{"zymc", "allRs","man","woman","manbl","womanbl","bjzs"};
			sql.append("select case when b.zymc is null then '合计' else b.zymc end zymc, a.* from ( select a.zydm, sum(a.man) man,sum(a.woman) woman, sum(a.allrs) allRs,");
			sql.append("round((sum(a.man) / sum(a.man + a.woman)) * 100,1) || '%' manbl, round((1-(sum(a.man) / sum(a.man + a.woman))) * 100,1) || '%' womanbl,");
			sql.append("sum(a.bjzs) bjzs from  (select a.* ,b.bjzs from (select zydm,sum(man) man,sum(woman) woman,sum(man + woman) allRs,");
			sql.append(" round((sum(man) / sum(man + woman)) * 100,1) || '%' manbl,round((1-(sum(man) / sum(man + woman))) * 100,1) || '%' womanbl");
			sql.append(" from (select zydm,zymc,case when xb = '男' then 1 else 0 end man, case when xb = '女' then 1 else  0 end woman  from view_xsxxb ");
			sql.append(" where nvl(sfzx, '在校') = '在校' "+sjfw+"");
			if(!StringUtil.isNull(model.getNj())){
				sql.append("and (");
				String [] Arrnj =  model.getNj().split("!!@!!");
				for(int i = 0;i < Arrnj.length;i++){
					if(i!=Arrnj.length-1){
						sql.append(" nj ='"+Arrnj[i]+"' or");
					}else{
						sql.append(" nj ='"+Arrnj[i]+"'");
					}
				}
				sql.append(")");
			}
			if(!StringUtil.isNull(model.getXydm())){
				sql.append("and (");
				String [] Arrxydm =  model.getXydm().split("!!@!!");
				for(int i = 0;i < Arrxydm.length;i++){
					if(i!=Arrxydm.length-1){
						sql.append(" xydm ='"+Arrxydm[i]+"' or");
					}else{
						sql.append(" xydm ='"+Arrxydm[i]+"'");
					}
				}
				sql.append(")");
			}
			sql.append(" )group by zydm ) a left join (select zydm, count(1) bjzs  from (select distinct t1.zydm, t1.bjdm from view_njxyzybj t1 ");
			sql.append(" where exists (select 1 from view_xsxxb t2 where t1.zydm = t2.zydm)  ");
			if(!StringUtil.isNull(model.getNj())){
				sql.append("and (");
				String [] Arrnj =  model.getNj().split("!!@!!");
				for(int i = 0;i < Arrnj.length;i++){
					if(i!=Arrnj.length-1){
						sql.append(" nj ='"+Arrnj[i]+"' or");
					}else{
						sql.append(" nj ='"+Arrnj[i]+"'");
					}
				}
				sql.append(")");
			}
			if(!StringUtil.isNull(model.getXydm())){
				sql.append("and (");
				String [] Arrxydm =  model.getXydm().split("!!@!!");
				for(int i = 0;i < Arrxydm.length;i++){
					if(i!=Arrxydm.length-1){
						sql.append(" xydm ='"+Arrxydm[i]+"' or");
					}else{
						sql.append(" xydm ='"+Arrxydm[i]+"'");
					}
				}
				sql.append(")");
			}
			sql.append(" )group by zydm) b on a.zydm = b.zydm ) a group by rollup(zydm) ) a left join bks_zydm b on a.zydm = b.zydm");
			
		}
		if("njxyzybj".equals(type)){
			colList = new String[]{"bjmc", "allRs","man","woman","manbl","womanbl"};
			sql.append("select case when b.bjmc is null then '合计' else b.bjmc end bjmc ,a.* from (select bjdm,sum(man) man,sum(woman) woman,sum(man + woman) allRs,");
			sql.append("round((sum(man) / sum(man + woman)) * 100,1) || '%' manbl,");
			sql.append("round((1-(sum(man) / sum(man + woman))) * 100,1) || '%' womanbl ");
			sql.append("from (select bjdm,");
			sql.append("case when xb = '男' then 1 else 0 end man,");
			sql.append("case when xb = '女' then 1 else 0 end woman ");
			sql.append("from view_xsxxb where nvl(sfzx,'在校')='在校' "+sjfw+"");
			
			if(!StringUtil.isNull(model.getNj())){
				sql.append("and (");
				String [] Arrnj =  model.getNj().split("!!@!!");
				for(int i = 0;i < Arrnj.length;i++){
					if(i!=Arrnj.length-1){
						sql.append(" nj ='"+Arrnj[i]+"' or");
					}else{
						sql.append(" nj ='"+Arrnj[i]+"'");
					}
				}
				sql.append(")");
			}
			if(!StringUtil.isNull(model.getXydm())){
				sql.append("and (");
				String [] Arrxydm =  model.getXydm().split("!!@!!");
				for(int i = 0;i < Arrxydm.length;i++){
					if(i!=Arrxydm.length-1){
						sql.append(" xydm ='"+Arrxydm[i]+"' or");
					}else{
						sql.append(" xydm ='"+Arrxydm[i]+"'");
					}
				}
				sql.append(")");
			}
			if(!StringUtil.isNull(model.getZydm())){
				sql.append("and (");
				String [] Arrzydm =  model.getZydm().split("!!@!!");
				for(int i = 0;i < Arrzydm.length;i++){
					if(i!=Arrzydm.length-1){
						sql.append(" zydm ='"+Arrzydm[i]+"' or");
					}else{
						sql.append(" zydm ='"+Arrzydm[i]+"'");
					}
				}
				sql.append(")");
			}
			sql.append(") group by rollup(bjdm) order by  bjdm ) a left join bks_bjdm b on a.bjdm = b.bjdm");
		}
		if("xs".equals(type)){
			colList = new String[]{"xh", "xm","xb","sfzh","zzmmmc","mzmc","lxdh"};
			sql.append("select xh,xm,xb,sfzh,zzmmmc,mzmc,lxdh from view_xsxxb where nvl(sfzx,'在校')='在校' and bjdm = '"+model.getBjdm()+"'");
		}
	return CommonQueryDAO.commonQueryNotFy(sql.toString(), query, input,colList, model);
	}
	
	/**
	 * 根据用户部门过滤年级
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getNjList(User user) {
		String sjfw = "" ; 
		String usertype = user.getUserType();
		if("xy".equals(usertype)){
			sjfw = " and xydm = '"+user.getUserDep()+"'";
		}
		// 获取年级列表
		String sql = "select distinct nj from view_njxyzybj where 1=1 "+sjfw+" order by nj";
		return dao.getList(sql, new String[] {}, new String[] { "nj" });
	}
	
	/**
	 * 根据用户部门过滤学院
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getXyList(User user) {
		String sjfw = "" ; 
		String usertype = user.getUserType();
		if("xy".equals(usertype)){
			sjfw = " and xydm = '"+user.getUserDep()+"'";
		}
		// 获取学院列表
		String sql = "select distinct xydm,xymc,substr(nvl(f_pinyin(xymc),xymc),0,1) pyszm from view_njxyzybj where 1=1 "+sjfw+" order by pyszm";
		return dao.getList(sql, new String[] {}, new String[] { "xydm", "xymc" , "pyszm"});
	}
	
	/**
	 * 根据学院代码获取学院名称
	 * @param zydm
	 * @return
	 */
	public String getXymc(String xydm){
		return dao.getOneRs("select distinct(bmmc) from zxbz_xxbmdm a " +			
				"where bmdm = ? ", new String[]{xydm}, "bmmc");
	}
	
	/**
	 * 根据专业代码获取专业名称
	 * @param zydm
	 * @return
	 */
	public String getZymc(String zydm){
		return dao.getOneRs("select distinct(zymc) from bks_zydm a " +			
				"where zydm = ? ", new String[]{zydm}, "zymc");
	}
	
	/**
	 * 根据班级代码获取班级名称
	 * @param zydm
	 * @return
	 */
	public String getBjmc(String bjdm){
		return dao.getOneRs("select distinct(bjmc) from bks_bjdm a " +			
				"where bjdm = ? ", new String[]{bjdm}, "bjmc");
	}
	
}
