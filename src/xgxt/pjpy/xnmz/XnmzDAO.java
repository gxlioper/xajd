
package xgxt.pjpy.xnmz;

import xgxt.DAO.DAO;

public class XnmzDAO {
    public String[] getBlArr(String xn,DAO dao){
    	String sql = "select xn,cjbl,dybl,tybl,qtbl from zhcpblszb where xn=?";
    	return dao.getOneRs(sql, new String[]{xn}, new String[]{"cjbl","qtbl"});
    }
}
