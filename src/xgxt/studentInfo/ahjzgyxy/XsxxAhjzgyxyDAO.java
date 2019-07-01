package xgxt.studentInfo.ahjzgyxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 安徽建筑职业技术学院学生信息Dao
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-09-08</p>
 */
public class XsxxAhjzgyxyDAO {
	/**
	 * @describe 获得表头
	 */
	public List<HashMap<String, String>> getTopTr(String[] arr1, String[] arr2) {
		// 将两个数组合并到一个List，两个数组大小一致，通常为中英文对照。参数要求英文在前，中文在后。
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		int len = (arr1.length > arr2.length) ? arr2.length : arr1.length;
		HashMap<String, String> map = null;
		for (int i = 0; i < len; i++) {
			map = new HashMap<String, String>();
			map.put("en", arr1[i]);
			map.put("cn", arr2[i]);
			list.add(map);
		}
		return list;
	}
	/**
	 * 学生分类管理查询表头
	 */
	public  List<HashMap<String, String>> getStuTypeManageTitle(){
        String[] colListCN = new String[]{"学号","姓名","性别","年级",Base.YXPZXY_KEY,"班级","类型"};
        String[] colListEN = new String[]{"xh","xm","xb","nj","xymc","bjmc","lx"};
        return getTopTr(colListEN,colListCN);
	}
	
	public ArrayList<List> getStuTypeManage(StuTypeModel model,String userType){
		DAO dao = DAO.getInstance(); 
		ArrayList<List> result = new ArrayList<List>();
		String nj=model.getNj();
		String xydm=model.getXydm();
		String zydm=model.getZydm();
		String bjdm=model.getBjdm();
		String xh=model.getXh();
		String xm=model.getXm();
		String[] colList= new String[]{"xh","xm","xb","nj","xymc","bjmc","lxdm","lxmc"};
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		if("isFdy".equalsIgnoreCase(userType)){//辅导员须选中班级
			querry.append(Base.isNull(bjdm)?" and 1=2":" and bjdm='"+bjdm+"' ");
		}else{
			querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		}
		querry.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
		querry.append(Base.isNull(xm)?"":" and xm='"+xm+"' ");
		querry.append("  order by xh,dm  ");
		String sql = "select xh,xm,xb,nj,xymc,bjmc,lxdm,lxmc from (select  xh,xm,xb,nj,xydm,zydm,bjdm,xymc,bjmc,a.xh||'/'||b.dm lxdm,b.mc lxmc,dm from view_xsjbxx a left join stu_lxdmb b on 1=1  )  ";
		List<HashMap<String, String>> rsTem= dao.getArrayList(sql+querry.toString(),new String[] {}, colList);
		List<String[]> oneGnmkdm = new ArrayList<String[]>();
		if(rsTem.size()>0){
    		for(int i=0;i<rsTem.size();i++){   			
    			String xhV = rsTem.get(i).get("xh");
    			xhV = Base.isNull(xhV)?" ":xhV;
    			String xmV = rsTem.get(i).get("xm");
    			xmV = Base.isNull(xmV)?" ":xmV;
    			String xbV = rsTem.get(i).get("xb");
    			xbV = Base.isNull(xbV)?" ":xbV;
    			String njV = rsTem.get(i).get("nj");
    			njV = Base.isNull(njV)?" ":njV;
    			String xymcV = rsTem.get(i).get("xymc");
    			xymcV = Base.isNull(xymcV)?" ":xymcV;
    			String bjmcV = rsTem.get(i).get("bjmc");
    			bjmcV = Base.isNull(bjmcV)?" ":bjmcV;
    			String lxdmV = rsTem.get(i).get("lxdm");
    			lxdmV = Base.isNull(lxdmV)?" ":lxdmV;
    			String lxmcV = rsTem.get(i).get("lxmc");
    			lxmcV = Base.isNull(lxmcV)?" ":lxmcV;
    			if(Base.isNull(xh)){
					xh =xhV;					
					String[] oneRow = {xhV,xmV,xbV,njV,xymcV,bjmcV,lxdmV,lxmcV};
					oneGnmkdm.add(oneRow);
				} else if(xh.equalsIgnoreCase(rsTem.get(i).get("xh"))){
					oneGnmkdm.add(new String[]{xhV,xmV,xbV,njV,xymcV,bjmcV,lxdmV,lxmcV});
				} else {
					result.add(oneGnmkdm);
					oneGnmkdm = new ArrayList<String[]>();
					oneGnmkdm.add(new String[]{xhV,xmV,xbV,njV,xymcV,bjmcV,lxdmV,lxmcV});
					xh = null;
				}
    		}
    		result.add(oneGnmkdm);
    	}
		return result;
	}
	/**
	 * 获取类型存在id
	 */
	public String getLxChecked(StuTypeModel model) throws Exception {
		DAO dao = DAO.getInstance();
		String bjdm = model.getBjdm();
		String xh = model.getXh();
		String xm = model.getXm();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
		querry.append(Base.isNull(xm)?"":" and xm='"+xm+"'");
		String sql = " select lxid from (select a.xh||'/'||a.dm lxid,b.xh,b.xm,b.bjdm from tsxsxxb a,view_xsxxb b where a.xh=b.xh ) ";
//		if(!pk.equalsIgnoreCase("")){
			String [] mkcds = dao.getArray(sql+querry.toString(), new String []{}, "lxid");
			StringBuffer mkcdTmp    =  new StringBuffer();
			for(int i = 0;i<mkcds.length;i++){
				mkcdTmp.append(mkcds[i]);
				if(i!=mkcds.length-1){
					mkcdTmp.append("!!");
					}
				}
				return mkcdTmp.toString();
//			}else{
//				return "";
//			}
	}
	/**
	 * 学生分类保存
	 * @throws SQLException 
	 */
	public boolean dao_stuTypeSave(StuTypeModel model) throws SQLException{
		DAO dao  = DAO.getInstance();
		boolean done = false;
		
		String[] lxids = model.getLxids();
		String[]delSql = new String[lxids.length];
		String[]insSql = new String[lxids.length];
		for(int i=0;i<lxids.length;i++){
			String xh = lxids[i].substring(0,lxids[i].indexOf("/"));
			String dm = lxids[i].replace(xh+"/","");
			delSql[i] = "delete from tsxsxxb where xh='"+xh+"'";
			insSql[i] = "insert into tsxsxxb(xh,dm)values('"+xh+"','"+dm+"')";
		}
		String[] exesql = dao.unionArray(delSql, insSql);
		int[] array = null;
		array = dao.runBatch(exesql);
		done = dao.checkBatch(array);
		return done;
	}
}
