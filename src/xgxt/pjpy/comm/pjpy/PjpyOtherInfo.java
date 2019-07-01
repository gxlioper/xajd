package xgxt.pjpy.comm.pjpy;

import java.sql.SQLException;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

/**
 * 评奖评优-其它字段信息
 */
public class PjpyOtherInfo {

	DAO dao = DAO.getInstance();
	
	
	/**
	 * 根据学号查询学生年龄
	 * @param xh
	 * @return
	 */
	public String getAge(String xh){
		
		String age = "";
		if (StringUtils.isNotNull(xh)){
			String sql = "select xh,to_char(sysdate,'yyyy') dqnd,substr(csrq,0,4) csnd from view_xsxxb where xh=?";
			HashMap<String,String> map = dao.getMapNotOut(sql, new String[]{xh});
			
			String dqnd = map.get("dqnd");//当前年度
			String csnd = map.get("csnd");//出生年度
			
			if (StringUtils.isNotNull(csnd)){
				age = String.valueOf(Integer.parseInt(dqnd)-Integer.parseInt(csnd));
			}
		}
		return age;
	}
	
	/**
	 * 同级专业人数
	 * @param xh
	 * @return
	 */
	public String getTjzyrs(String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from view_xsjbxx ")
		   .append("where nj=(select nj from view_xsjbxx where xh=?) ")
		   .append("and zydm=(select zydm from view_xsjbxx where xh=?)");
		
		return dao.getOneRs(sql.toString(), new String[]{xh,xh}, "count");
	}
	
	
	
	/**
	 * 是否党团员
	 * @param xh
	 * @return
	 * @throws SQLException 
	 */
	public String getDtInfo(String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from dyxxb where zzzt='yes' and xh=? union all ")
		   .append("select count(1) count from ybdyxxb where zzzt='yes' and xh=? union all ")
		   .append("select count(1) count from tyxxb where xh=?");
		
			try {
				String[] count = dao.getRs(sql.toString(), new String[]{xh,xh,xh}, "count");
				return "0".equalsIgnoreCase(count[0]) ? ("0".equalsIgnoreCase(count[1]) ? ("0".equalsIgnoreCase(count[2]) ? "" : "团员") : "预备党员") : "党员";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
	}
	
	
	/**
	 * 担任何种干部
	 * @param xh
	 * @return
	 */
	public String getBgbInfo(String xh){
		
		String sql = "select to_char(WM_CONCAT(bjgbmc)) bgbmc from (select a.xh,b.bjgbmc from sxjy_bjgbxxb a left join sxjy_bjgbzlb b on a.bjgbdm = b.bjgbdm where a.xh=?) ";
		
		return dao.getOneRs(sql, new String[]{xh}, "bgbmc");
	}
}
