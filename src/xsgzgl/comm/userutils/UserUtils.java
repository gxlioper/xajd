/**
 * @部门:学工产品事业部
 * @日期：2015-1-21 上午10:06:51 
 */  
package xsgzgl.comm.userutils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 获取用户信息工具类
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-21 上午10:06:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class UserUtils {
	/**
	 * 
	 * @描述:通过用户名获取用户信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-21 上午10:09:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param userName
	 * @return
	 * User 返回类型 
	 * @throws
	 */
	public static User getUser(String userName){
		User user  = new User();
		try {
			String sql;
			String userType = null;
			DAO dao = DAO.getInstance();
			String xhSql = "select xh userName from view_xsjbxx where xh = ?";
			String checkName = dao.getOneRs(xhSql, new String[] { userName },
					"userName");
			if (StringUtil.isNull(checkName)) {
				userType = "teacher";
			} else {
				userType = "stu";
				
			}
			String userStatus="";
			String[] userInfo = new String[2];
			if ("teacher".equalsIgnoreCase(userType)) {
				sql = "select xgbdm from xtszb where rownum=1";
				String adminDep = dao.getOneRs(sql, new String[] {},
						new String[] { "xgbdm" })[0];
				userInfo = dao.getOneRs(
						"select xm,szbm from yhb where yhm=?",
						new String[] { userName }, new String[] { "xm",
								"szbm" });
				if(!StringUtils.isArrayNotNull(userInfo)){
					return user;
				}
				user.setUserName(userName);
				user.setRealName(userInfo[0]);
				sql = "select (case bmjb when 1 then bmdm else bmfdm end) bmdm,bmmc,bmlb from zxbz_xxbmdm where bmdm=?";
				String[] userTmp = dao.getOneRs(sql, new String[] { userInfo[1] },
						new String[] { "bmdm", "bmmc", "bmlb" });
				user.setUserDep(userTmp[0]);
				if (userTmp[0].equalsIgnoreCase(adminDep)) {
					userType = "admin";
				} else if (userTmp[2].equalsIgnoreCase("5")) {
					userType = "xy";
				} else {
					userType = "xx";
				}
				user.setUserType(userType);
				String bzrQx = String.valueOf(Fdypd.bzrbjck(userName));
				String fdyQx = String.valueOf(Fdypd.fdybjck(userName));
				String gyglySql = "select count(1) num from xg_gygl_new_gyfdyb where yhm = ?";
				String num = dao.getOneRs(gyglySql, new String[] { userName },
						"num");
				String gyglyQx = !Base.isNull(num) && !"0".equalsIgnoreCase(num) ? "yes"
						: "no";
				user.setGyglyQx(gyglyQx);
				if (!Base.isNull(Fdypd.checkSfjrXy(userName).get("sfjryx"))) {
					user.setIsFdy(String.valueOf(Fdypd.isFdy(userName)));
					user.setBzrQx(bzrQx);
					user.setFdyQx(fdyQx);
				}else{
					user.setIsFdy(String.valueOf(false));
					user.setBzrQx(String.valueOf(false));
					user.setFdyQx(String.valueOf(false));
				}
				if (Boolean.parseBoolean(user.getBzrQx()) && Boolean.parseBoolean(user.getFdyQx())) {
					userStatus = "jd";// 班主任兼辅导员
				} else if (Boolean.parseBoolean(user.getFdyQx())) {
					userStatus = "fdy";// 辅导员
				} else if (Boolean.parseBoolean(user.getBzrQx())) {
					userStatus = "bzr";// 班主任
				} else if ("xy".equalsIgnoreCase(userType)) {
					userStatus = "xy";// 学院
				} else if ("sy".equalsIgnoreCase(userType)) {
					userStatus = "sy";// 书院
				} else if ("xx".equalsIgnoreCase(userType)
						|| "admin".equalsIgnoreCase(userType)) {
					userStatus = "xx";// 学校用户（管理员）
				} else {
					userStatus = "stu";// 学生
				}
				
				user.setUserStatus(userStatus);
			}else if ("stu".equalsIgnoreCase(userType)) {
				user.setUserType(userType);
				user.setUserName(userName);
				userInfo = dao.getOneRs(
						"select xm,bmdm szbm,sydm1 sydm from view_xsjbxx where xh=?",
						new String[] { userName }, new String[] { "xm",
								"szbm","sydm" });
				if(!StringUtils.isArrayNotNull(userInfo)){
					return user;
				}
				user.setRealName(userInfo[0]);
				user.setUserStatus("stu");
				user.setUserSyDep(userInfo[2]);

			}
			
			
			
		} catch (Exception e) {
		}
		return user;
			
		
	}
}


