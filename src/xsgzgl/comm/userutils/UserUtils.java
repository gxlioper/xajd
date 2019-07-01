/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-21 ����10:06:51 
 */  
package xsgzgl.comm.userutils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ȡ�û���Ϣ������
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-21 ����10:06:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class UserUtils {
	/**
	 * 
	 * @����:ͨ���û�����ȡ�û���Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-21 ����10:09:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param userName
	 * @return
	 * User �������� 
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
					userStatus = "jd";// �����μ渨��Ա
				} else if (Boolean.parseBoolean(user.getFdyQx())) {
					userStatus = "fdy";// ����Ա
				} else if (Boolean.parseBoolean(user.getBzrQx())) {
					userStatus = "bzr";// ������
				} else if ("xy".equalsIgnoreCase(userType)) {
					userStatus = "xy";// ѧԺ
				} else if ("sy".equalsIgnoreCase(userType)) {
					userStatus = "sy";// ��Ժ
				} else if ("xx".equalsIgnoreCase(userType)
						|| "admin".equalsIgnoreCase(userType)) {
					userStatus = "xx";// ѧУ�û�������Ա��
				} else {
					userStatus = "stu";// ѧ��
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


