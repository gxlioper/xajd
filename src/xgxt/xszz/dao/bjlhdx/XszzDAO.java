package xgxt.xszz.dao.bjlhdx;

import java.util.List;

import xgxt.DAO.DAO;

public class XszzDAO  {
	private static XszzDAO xszzDAO = new XszzDAO();
	
	private XszzDAO() {
		
	}
	
	public static XszzDAO getInstance() {
		if (xszzDAO != null) {
			return new XszzDAO(); 
		} else {
			return xszzDAO;
		}
	}//每个DAO的实现都和上面的相同，下面的方法是各ACTION业务需要实现的功能
	 
	
	public List fsbzmx(String bzffny, String cxxydmDest, String flag) {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer("select a.xymc,a.bjdm,a.xh,a.xm,a.kh,a.fsbyyfbz,a.fsbybfbz,a.fsbysfbz,a.lsbyyfbz,a.lsbybfbz,a.lsbysfbz,a.ze from (select f.xymc,f.bjdm,f.xh,f.xm,f.kh,f.byyfbz fsbyyfbz,f.bybfbz fsbybfbz,f.bysfbz fsbysfbz,nvl(l.byyfbz,'0') lsbyyfbz,nvl(l.bybfbz,'0') lsbybfbz,nvl(l.bysfbz,'0') lsbysfbz,to_char(f.bysfbz+nvl(l.bysfbz,'0')) ze,f.xydm,f.bzffny from view_fsbzb f,view_lsbzxx l where f.xh=l.xh(+) union select l.xymc,l.bjdm,l.xh,l.xm,l.kh,'0' fsbyyfbz,'0' fsbybfbz,'0' fsbysfbz,l.byyfbz lsbyyfbz,l.bybfbz lsbybfbz,l.bysfbz lsbysfbz,l.bysfbz ze,l.xydm,l.bzffny from view_lsbzxx l where not exists (select 1 from view_fsbzb f where f.xh=l.xh)) a where 1=1");
		 
		if (cxxydmDest!=null && !cxxydmDest.trim().equals("")) {
			sql.append("and a.xydm in (");
			String[] pcdmArray = cxxydmDest.split("-");
			for (int i=0; i<pcdmArray.length; i++) {
				if (i==(pcdmArray.length-1)) {
					sql.append("'"+pcdmArray[i]+"')");
				} else {
					sql.append("'"+pcdmArray[i]+"', ");
				}
			}
		}
		
		if (flag.equals("年")) {
			sql.append(" and substr(a.bzffny, 0, 4) = ? ");
		} else if (flag.equals("月")) {
			sql.append(" and a.bzffny = ? ");
		} else if (flag.equals("学年")) {
			if (bzffny!=null && !bzffny.trim().equals("")){
				String[] bzffnyArray = bzffny.split("-");
				sql.append(" and a.bzffny >= '"+bzffnyArray[0]+"-09' and a.bzffny <= '"+bzffnyArray[1]+"-07'");
			}
			return dao.getListNotOut(sql.toString(), null);
		}else if(flag.trim().equalsIgnoreCase("")){
			sql.append(" and a.bzffny = ? ");
		} 
		return dao.getListNotOut(sql.toString(), new String[]{bzffny});
	}

	public List zjFsbzmx(String bzffny, String cxxydmDest, String flag) {
		DAO dao = DAO.getInstance();
		StringBuffer parentSql = new StringBuffer("SELECT SUM(fsbyyfbz) fsbyyfbz, SUM(fsbybfbz) fsbybfbz, SUM(fsbysfbz) fsbysfbz, SUM(lsbyyfbz) lsbyyfbz, SUM(lsbybfbz) lsbybfbz, SUM(lsbysfbz) lsbysfbz, SUM(ze) ze from ( ");
		StringBuffer sql = new StringBuffer("select a.xymc,a.bjdm,a.xh,a.xm,a.kh,a.fsbyyfbz,a.fsbybfbz,a.fsbysfbz,a.lsbyyfbz,a.lsbybfbz,a.lsbysfbz,a.ze from (select f.xymc,f.bjdm,f.xh,f.xm,f.kh,f.byyfbz fsbyyfbz,f.bybfbz fsbybfbz,f.bysfbz fsbysfbz,nvl(l.byyfbz,'0') lsbyyfbz,nvl(l.bybfbz,'0') lsbybfbz,nvl(l.bysfbz,'0') lsbysfbz,to_char(f.bysfbz+nvl(l.bysfbz,'0')) ze,f.xydm,f.bzffny from view_fsbzb f,view_lsbzxx l where f.xh=l.xh(+) union select l.xymc,l.bjdm,l.xh,l.xm,l.kh,'0' fsbyyfbz,'0' fsbybfbz,'0' fsbysfbz,l.byyfbz lsbyyfbz,l.bybfbz lsbybfbz,l.bysfbz lsbysfbz,l.bysfbz ze,l.xydm,l.bzffny from view_lsbzxx l where not exists (select 1 from view_fsbzb f where f.xh=l.xh)) a where 1=1");
		 
		if (cxxydmDest!=null && !cxxydmDest.trim().equals("")) {
			sql.append("and a.xydm in (");
			String[] pcdmArray = cxxydmDest.split("-");
			for (int i=0; i<pcdmArray.length; i++) {
				if (i==(pcdmArray.length-1)) {
					sql.append("'"+pcdmArray[i]+"')");
				} else {
					sql.append("'"+pcdmArray[i]+"', ");
				}
			}
		}
		
		if (flag.equals("年")) {
			sql.append(" and substr(a.bzffny, 0, 4) = ? ");
		} else if (flag.equals("月")) {
			sql.append(" and a.bzffny = ? ");
		} else if (flag.equals("学年")) {
			if (bzffny!=null && !bzffny.trim().equals("")){
				String[] bzffnyArray = bzffny.split("-");
				sql.append(" and a.bzffny >= '"+bzffnyArray[0]+"-09' and a.bzffny <= '"+bzffnyArray[1]+"-07'");
			}
			parentSql.append(sql).append(" )");
			return dao.getListNotOut(parentSql.toString(), new String[]{});
		} else if (flag.trim().equalsIgnoreCase("")){
			sql.append(" and a.bzffny = ? ");
		}
		parentSql.append(sql).append(" )");
		return dao.getListNotOut(parentSql.toString(), new String[]{bzffny});
	}
}
