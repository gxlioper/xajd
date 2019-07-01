package xgxt.xtwh.xtwhCriterion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �����û���¼ʱ��Ȩ�޹�ϵ</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LUNING</p>
 * <p>Version: 1.0</p>
 * <p>Time:2011-6-2</p>
 */
public class LoginCriterionService  extends CriterionService{
	
	/**
	 * ��session�����û���ɫ,�����������ݿ⽻��
	 * @param request
	 */
	public void setUserRole(HttpServletRequest request){
		//session�л�ȡ�õ��û���ɫ����ز���
		HttpSession session = request.getSession();
		String userName =(String)session.getAttribute("userName");
		String userType =(String)session.getAttribute("userType");
		String UserRoles="";
		if(userType.equalsIgnoreCase("stu")){
			UserRoles = getStuRoles(userName);
		}else{
			UserRoles = getCommUserRoles(userName);
		}
		session.setAttribute("userRoles", UserRoles);
	
	}
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	private String getCommUserRoles(String userName) {
		DAO dao = DAO.getInstance();
		String sql = "select jsdm from xg_xtwh_yhjsb where yhm=?";
		String[] jsArray = null;
		String yhjs="";
		try {
			jsArray = dao.getRs(sql, new String[]{userName}, "jsdm");
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		yhjs = getPurviewString(jsArray);
		return yhjs;
	}

	/**
	 * ��ȡѧ����ɫ
	 * @param userName
	 * @return
	 */
	private String getStuRoles(String userName){
		
		//�ж��Ƿ����Ÿɲ�������ǣ��������Ŵ���+ְ�������Ϊ���������Թ���ѧ����ɫ��
		DAO dao = DAO.getInstance();
		String sql ="select stv||zwdm xslxdm from xsh_cyglb a where xh = ?";
		String[] stgbdmz;
		String yhjs="";
		try {
			stgbdmz = dao.getRs(sql, new String[]{userName}, "xslxdm");
			
			//�ж��Ƿ�༶�ɲ�������ǣ�ȡ��ѧ�����εİ༶�ɲ�����
			sql = "select distinct bjgbdm from sxjy_bjgbxxb where xh = ?";
			String[] bjgbdmz;
			bjgbdmz = dao.getRs(sql, new String[]{userName}, "bjgbdm");
			
			//�õ�����ӵ�еĽ�ɫ������ģ��
			StringBuilder jsQuerySql = new StringBuilder("select jsdm from yhlxjsb where yhlxdm in ");
			jsQuerySql.append("(? ");
			for(int i = 0;i<(bjgbdmz.length+stgbdmz.length);i++){
				jsQuerySql.append(",?");
			}
			jsQuerySql.append(")");
			
			String[] jsArray = dao.getRs(sql, new String[]{}, "jsdm");
			yhjs = getPurviewString(jsArray);
			} catch (Exception e) {
			// TODO �Զ����� catch ��
				e.printStackTrace();
		}
		return yhjs;
	}

}
