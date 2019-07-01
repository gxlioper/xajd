
package xgxt.pjpy.shList;

import xgxt.DAO.DAO;

public class ShListColsFactory {
	public String[] getCols(String xxdm,String viewName){
		String[] rs = null;
		switch(Integer.parseInt(xxdm)){
		case 12872: 
			if("view_pjpy_jtxmsqb".equalsIgnoreCase(viewName)){
				rs = new String[]{"xn","xq","xymc","hdmc","hjmc","xysh","xxsh"};
			}
		}
		return rs;
	}
	public String[] getColsCN(String xxdm,String viewName){
		String[] rs = null;
		DAO dao = DAO.getInstance();
		switch(Integer.parseInt(xxdm)){
		case 12872: 
			if("view_pjpy_jtxmsqb".equalsIgnoreCase(viewName)){
				rs = dao.getColumnNameCN(new String[]{"xn","xq","xymc","hdmc","hjmc","xysh","xxsh"}, viewName);
			}
		}
		return rs;
	}
}
