/**
 * @部门:学工产品事业部
 * @日期：2016-1-20 上午10:32:00 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.xxxg.zjgydxjk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xgxt.utils.date.DateUtils;

import common.newp.StringUtil;

public class ZjgyDxjkService{
	
	private static ResourceBundle resource = ResourceBundle.getBundle("config/dxfs");
	
	private static final String format = "yyyy-MM-dd HH:mm:ss";// 时间戳格式
	
	private static final Log logger =LogFactory.getLog(ZjgyDxjkService.class);
	
	private static final int timeout = 5;// 超时时间(分钟)
	
	
	
	/**
	 * 
	 * @描述:生成随机验证码
	 * @作者：ChenQ[工号：856]
	 * @日期：2016-1-20 上午11:53:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getYzm(){
		String base = "1234567890abcdefghkmnopqrstuvwxyz";
		String yzm = "";
		for (int i=0;i<5;i++){
		   yzm = yzm + base.charAt(new Random().nextInt(base.length()))+"";
		}
		return yzm;
	}
	
	/**
	 * 
	 * @描述:容器里面放入验证码
	 * @作者：ChenQ[工号：856]
	 * @日期：2016-1-20 下午01:26:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param sjhm
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static boolean sendCord(HttpServletRequest request,String sjhm,String xh){
		if(StringUtil.isNull(sjhm)||StringUtil.isNull(xh)||sjhm.length()!=11){
			return false;
		}
		String code =  getYzm();
		String sendTime  = DateUtils.getCurrTime();
		Map<String,Object> result = (Map<String, Object>) 
		     request.getSession().getServletContext().getAttribute("sjCord");
		if(null==result){
			result = new HashMap<String,Object>();
			request.getSession().getServletContext().setAttribute("sjCord", result);
		}
		Map<String,String> rs = new HashMap<String,String>();
		rs.put("codeTime",sendTime);
		rs.put("code",code);
		result.put(xh,rs);
		String msg = resource.getString("msg")+code;
		System.out.println(msg);
		//String sismsid = UUID.randomUUID().toString();
		int res = insert2DB(msg,sjhm);
		if(res>0) return true;
		return false;
	}
	
	/**
	 * 
	 * @描述:验证手机验证码是否有效
	 * @作者：ChenQ[工号：856]
	 * @日期：2016-1-20 下午01:53:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param sjhm
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static boolean checkCord(HttpServletRequest request,String code,String xh)
	     throws Exception{
		if(StringUtil.isNull(code)||StringUtil.isNull(xh)||
				code.length()!=5){
			return false;
		}
		Map<String,Object> result = (Map<String, Object>) 
	     request.getSession().getServletContext().getAttribute("sjCord");
		if(null!=result){
			boolean flag = result.containsKey(xh);
			if(!flag) return false;
			Map<String,String> rs = (Map<String,String>) result.get(xh);
			if(!rs.containsKey("codeTime")||!rs.containsKey("code")){
				return false;
			}
			String codeTime = replaceNull(rs.get("codeTime"));
			String oldCode =  replaceNull(rs.get("code"));
			DateFormat df = new SimpleDateFormat(format);
			Date curTime = new Date();
			if(StringUtil.isNull(codeTime)) return false;
			long interval = (curTime.getTime() - df.parse(codeTime).getTime()) / 1000 / 60;
			if (interval >= timeout) {
				return false;
			}
			if(oldCode.equalsIgnoreCase(code)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @描述:排除Null
	 * @作者：ChenQ[工号：856]
	 * @日期：2016-1-20 下午02:04:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param code
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String replaceNull(String code){
		if(StringUtil.isNull(code)){
			return "";
		}
		return code;
	}
	
	public static int insert2DB(String msg,String sjhm) {
		Connection conn = null;
		PreparedStatement ps = null;

		StringBuffer sb = new StringBuffer();
		sb.append("insert into api_mt_zfsoft(mobiles,content,send_time)");
		sb.append("values");
		sb.append("(?,?,current_timestamp)");

		int res = -1;
		try {
			conn = C3PODataSource.getInstance().getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sb.toString());
			ps.setString(1, new String((sjhm+",").getBytes(),"ISO8859-1"));
			ps.setString(2, new String(msg.getBytes(),"ISO8859-1"));
			res = ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("SQL语句执行异常",e); 
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("SQL语句执行异常回滚",e); 
			}
			return -1;
		} finally {
			close(ps);
			close(conn);
		}
		return res;
	}
	
	public static void close(PreparedStatement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		System.out.println(getYzm());
	}
}
