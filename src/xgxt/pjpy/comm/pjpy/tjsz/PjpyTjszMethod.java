package xgxt.pjpy.comm.pjpy.tjsz;

import java.sql.SQLException;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;

public class PjpyTjszMethod extends CommService{
	
	/**
	 * 前置奖学金个性化修改
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String attainBurseByXn(HashMap<String,Object>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//申请类型
		String sqlx=(String)map.get("sqlx");
		//消息
		String message="";
		//申请或者上报学生学号
		String []xhArr=(String[])map.get("xh");
		
		// -------------------个性化业务 sql begin-------------------------
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from xg_view_pjpy_xmsh a ");
		sql.append(" where a.shjb = (select max(b.xh) maxLv from xg_xtwh_spbz b ");
		sql.append(" where b.splc = a.lcid) and a.shzt = '通过' ");
		
		sql.append(" and xh in( ");
		for(int i=0;i<xhArr.length;i++){
			if(i!=0){
				
				sql.append(",");
			}
			
			sql.append(xhArr[i]);
			
		}
		sql.append(" ) ");
		
		sql.append(" and (xmmc ='优秀学生标兵' or xmmc='三好学生' or xmmc='优秀团员' ");
		sql.append(" or xmmc ='优秀学生干部' or xmmc='优秀共青团干部' or xmmc='优秀党员')  ");
		// -------------------个性化业务 sql end-------------------------
		
		String[]pjzgXh=dao.getArray(sql.toString(), new String[]{}, "xh");
		
		String[]nozgXh=getNoRepeatArrValue(pjzgXh,xhArr);
		
		//条件说明
		String tjms = (String)map.get("tjms");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? false :true );
		
		if (!flag) {
			if("sq".equalsIgnoreCase(sqlx)){
				//不满足条件的话
				message = "申请该项目需要：" + tjms + ",申请者不满足申请条件！";

			}else{
				
				message=nozgXh[0];
			}
		}
		return message;
	}
	
	/**
	 * 判断评奖学年是否有违纪处分
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkWjcfByPjXn(HashMap<String,Object>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//申请类型
		String sqlx=(String)map.get("sqlx");
		//消息
		String message="";
		//申请或者上报学生学号
		String []xhArr=(String[])map.get("xh");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from wjcfb ");
		sql.append(" where xn = (select pjxn from xg_pjpy_xtszb where rownum = 1) ");
		
		sql.append(" and xh in( ");
		for(int i=0;i<xhArr.length;i++){
			if(i!=0){
				
				sql.append(",");
			}
			
			sql.append("'"+xhArr[i]+"'");
			
		}
		sql.append(" ) ");
		
		sql.append(" and xxsh = '通过'  ");
		sql.append(" group by xh  ");
		
		String[]nozgXh=dao.getArray(sql.toString(), new String[]{}, "xh");
		
		//条件说明
		String tjms = (String)map.get("tjms");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? true : false);
		
		if (!flag) {
			if("sq".equalsIgnoreCase(sqlx)){
				//不满足条件的话
				message = "申请该项目需要：" + tjms + ",申请者不满足申请条件！";

			}else{
				
				message=nozgXh[0];
			}
		}
		return message;
	}
	
	/**
	 * 判断是否有违纪处分
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkWjcf(HashMap<String,Object>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//申请类型
		String sqlx=(String)map.get("sqlx");
		//消息
		String message="";
		//申请或者上报学生学号
		String []xhArr=(String[])map.get("xh");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from wjcfb ");
		sql.append(" where xh in( ");
		for(int i=0;i<xhArr.length;i++){
			if(i!=0){
				
				sql.append(",");
			}
			
			sql.append("'"+xhArr[i]+"'");
			
		}
		sql.append(" ) ");
		
		sql.append(" and xxsh = '通过'  ");
		sql.append(" group by xh  ");
		
		String[]nozgXh=dao.getArray(sql.toString(), new String[]{}, "xh");
		
		//条件说明
		String tjms = (String)map.get("tjms");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? true : false);
		
		if (!flag) {
			if("sq".equalsIgnoreCase(sqlx)){
				//不满足条件的话
				message = "申请该项目需要：" + tjms + ",申请者不满足申请条件！";

			}else{
				
				message=nozgXh[0];
			}
		}
		return message;
	}
	
	/**
	 * 是否困难生
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkKns(HashMap<String,Object>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//申请类型
		String sqlx=(String)map.get("sqlx");
		//消息
		String message="";
		//申请或者上报学生学号
		String []xhArr=(String[])map.get("xh");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from xszz_knsb ");
		sql.append(" where xh not in( ");
		for(int i=0;i<xhArr.length;i++){
			if(i!=0){
				
				sql.append(",");
			}
			
			sql.append("'"+xhArr[i]+"'");
			
		}
		sql.append(" ) ");
		
		
		String[]nozgXh=dao.getArray(sql.toString(), new String[]{}, "xh");
		
		//条件说明
		String tjms = (String)map.get("tjms");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? true : false);
		
		if (!flag) {
			if("sq".equalsIgnoreCase(sqlx)){
				//不满足条件的话
				message = "申请该项目需要：" + tjms + ",申请者不满足申请条件！";

			}else{
				
				message=nozgXh[0];
			}
		}
		return message;
	}
}
