/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-5-12 ����10:49:38</p>
 */
package xgxt.sztz.bjlhdx;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;

public class SztzBjlhdxDAO {
	public boolean  dao_csfInit(QuerryModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String csf = model.getCsf().trim();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		String sql = "";
		boolean done = false;
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");		
		sql= "delete from xyfcsb a where exists(select  xh from view_xsjbxx b "+querry+" and a.xh=b.xh )";
		done = dao.runUpdate(sql, new String[]{});
		if(done){
			sql= "insert into xyfcsb(xh,csf) select xh,'"+csf+"'xf from view_xsjbxx "+querry;
		}
		done = dao.runUpdate(sql, new String[]{});		
		return done;
	}
	public ArrayList<String[]> dao_csfInitQuerr(QuerryModel model) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String sql    =  "";
		String[] colList = null;
		StringBuffer querry = new StringBuffer();
		//��ѯ����	
		String nj = DealString.toGBK(model.getNj());
		String xydm = DealString.toGBK(model.getXydm());
		String zydm = DealString.toGBK(model.getZydm());
		String bjdm = DealString.toGBK(model.getBjdm());
		querry.append(" where 1=1 ");		
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append("  group by nj,xymc,bjmc,zymc,csf order by nj,xymc,zymc,bjmc ");
		colList = new String[]{"nj","xymc","zymc","bjmc","csf"};//��ѯ��ʾ�ֶ� 
		sql     = " select nj,xymc,zymc,bjmc,csf from view_xyfcsxx ";  
//		ִ�в�ѯ
		rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
		return rs;
	}
	public ArrayList<HashMap<String, String>> dao_csfStatTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		colList = new String[]{"prkey","xh","xm","xb","nj","bjmc","xn","xq","kfs","kfxq"};//��ѯ��ʾ�ֶ� 
		colListCN = new String[]{ "����","ѧ��","����","�Ա�","�꼶","�༶","ѧ��","ѧ��","�۷�ֵ","�۷�����(�༶/����/ѧ��/ѧ��/��Ŀ/����/����)"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	} 
	public ArrayList<String[]> dao_csfStatResult(QuerryModel model) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String sql    =  "";
		String[] colList = null;
		StringBuffer querry = new StringBuffer();
		//��ѯ����	
		String nj = DealString.toGBK(model.getNj());
		String xydm = DealString.toGBK(model.getXydm());
		String zydm = DealString.toGBK(model.getZydm());
		String bjdm = DealString.toGBK(model.getBjdm());
		String xh   = model.getXh();
		String xm   = model.getXm();
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm+"%' ");
		colList = new String[]{"prkey","xh","xm","xb","nj","bjmc","xn","xq","kfs","kfxq"};//��ѯ��ʾ�ֶ� 
		sql     = " select pk prkey,xh,xm,xb,nj,bjmc,xmxn xn,xqmc xq,kfs,kfxq from view_xykfxx ";  
//		ִ�в�ѯ
		rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
		return rs;
	}
	public boolean dao_kxfDel(String pkValue) throws Exception{
		DAO  dao = DAO.getInstance();		
		String[] pkValueA = pkValue.split("!!");		
		String[] delPkSql  = new String[pkValueA.length];		
	    for (int i = 0; i < pkValueA.length; i++) {
	    	delPkSql[i] = Base.isNull(pkValueA[i])?"":"delete xykfb where rowid= '"+pkValueA[i]+"'";							
	    }              	
	    boolean doFlag = false;       
        int[] array = dao.runBatch(delPkSql);
        doFlag = dao.checkBatch(array);           
		return doFlag;
	}
	public HashMap<String,String> dao_kxfXx(String pkValue) {
		DAO dao = DAO.getInstance();
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xmxn xn,xqmc xq,kfs,kfxq,kfsj from view_xykfxx where pk=?";
		String[] colList = new String[]{ "xh","xm","xb","nj","xymc","zymc","bjmc","xn","xq","kfs","kfxq","kfsj"};
		return dao.getMap(sql,new String[]{pkValue},colList);
	}
	public String dao_getXyCsf(String pkValue){
		DAO dao = DAO.getInstance();
		String sql = "select csf from xyfcsb where xh=(select xh from xykfb where rowid=?  )";
		return dao.getOneRs(sql,new String[]{pkValue},"csf");
	}
	public boolean dao_kfModiSave(String kfs,String pkValue) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "update xykfb set kfs=? where rowid=? ";
		return dao.runUpdate(sql,new String[]{kfs,pkValue});
	}
}
