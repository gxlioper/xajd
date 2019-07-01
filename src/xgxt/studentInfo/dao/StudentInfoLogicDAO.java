package xgxt.studentInfo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.struts.action.Action;

import xgxt.DAO.DAO;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Check_Input_Value;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

/**
 * @author Administrator
 * @category ������ѧ����Ϣ�����߼��࣬�й�ѧ����Ϣ�Ĵ��������ڴ�����
 */
public class StudentInfoLogicDAO extends Action {
	/**
	 * @category �������ݿ��ѯ���Ľ��ת����String����,<font color=red>�ã����ָ�һ����¼�����Ϣ����@@���������¼</font>
	 * @return
	 * @throws Exception
	 */
	public String getStudentXjydDiffInfo(String aa) throws Exception {// ����aaֻ������ռ��һ������λ��
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rsDiff = null;
		Thread.sleep(500);
		String[] cols_en = { "xh", "xm", "xymc", "zymc", "bjmc", "ydlb", "ydsj" };
		rsDiff = dao.rsToVator("select xh,xm,xymc,zymc,bjmc,ydlb,ydsj from view_stu_info_xjyd_diff order by xh,xydm,zydm",new String[] {}, cols_en);
		Object[] temRs = (rsDiff != null) ? rsDiff.toArray(): (new String[] {});
		StringBuffer rsStr = new StringBuffer("");
		if (temRs != null && temRs.length > 0) {
			for (int i = 0; i < temRs.length; i++) {
				String[] temArr = (String[]) temRs[i];
				for (int j = 0; j < temArr.length; j++) {
					rsStr.append(temArr[j]);
					rsStr.append((j == temArr.length - 1) ? "" : "!!");
				}
				rsStr.append((i == temRs.length - 1) ? "" : "@@");
			}
		}
		return rsStr.toString();
	}

	/**
	 * ����dwrʵ�ֿͻ������ѧ���춯ѧ����Ϣ
	 * @param String aa 
	 * @category ����aaֻ������ռ��һ������λ��
	 * @return String
	 * */ 
	public String getStudentXjydDiffTitle(String aa) {// ����aaֻ������ռ��һ������λ��
		DAO dao = DAO.getInstance();
		String tableName = "view_bks_stu_info_xjydb";
		String[] cols_en = { "xh", "xm", "xymc", "zymc", "bjmc", "ydlb", "ydsj" };
		String[] cols_cn = dao.getColumnNameCN(cols_en, tableName);
		StringBuffer title_cn = new StringBuffer("");
		for (int i = 0; i < cols_cn.length; i++) {
			title_cn.append(cols_cn[i]);
			title_cn.append(i == cols_cn.length - 1 ? "" : "!!");
		}
		return title_cn.toString();
	}

	/**
	 * ����dwrʵ�ֿͻ������ѧ���춯ѧ����Ϣ
	 * @return HashMap<String, String>
	 * @throws Exception
	 * */ 
	public HashMap<String, String> getStuXjYdXx() throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String aa = "";
		map.put("title", this.getStudentXjydDiffTitle(aa));
		map.put("source", this.getStudentXjydDiffInfo(aa));
		return map;
	}

	/**
	 * ����dwr�ж��Ƿ����ת��
	 * @param String xh
	 * @return String 
	 * */ 
	public String checkArchivesExist(String xh) {		
		DAO dao = DAO.getInstance();
		String message = "";
		String xsxh = "";
		int count = 0;
		count = Integer.parseInt(dao.getOneRs("select count(*) num from stu_gdzlb", new String[]{}, "num"));
		String sql = "select xsxh from jygl_jyxy where xsxh=?";
		xsxh = dao.getOneRs(sql, new String[] { xh }, "xsxh");
		if (xsxh.equalsIgnoreCase("")) {
			message = "��ҵЭ�黹û�еǼǣ�����ת����";
		} 
		sql = "select count(*) num from gdzltjb where xh=? and sftj='��'";
		int num = Integer.parseInt(dao.getOneRs(sql, new String[]{xh}, "num"));
		if(num<count){
			message = "�鵵����û���ύ��ȫ������ת����";
		}
		return message;
	}

	/**
	 * dwr�ͻ����ж��춯��� 
	 * @param String ydlbdm
	 * @return boolean
	 * */ 
	public boolean getYdlbStyle(String ydlbdm) {
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String xj = "";
		String sql_ydlb = "select sfzx from dm_ydlb where ydlbm='" + ydlbdm+ "'";
		xj = dao.getOneRs(sql_ydlb, new String[] {}, "sfzx");
		flag = ("��У".equalsIgnoreCase(xj)) ? true : false;
		return flag;
	}

	/**
	 * dwr�ͻ����ж��춯��� 
	 * @param String ydlbdm
	 * @return boolean
	 * */ 
	public String getXjydInfo(String ydlbdm) {
		DAO dao = DAO.getInstance();
	
		String xj = "";
		String sql_ydlb = "select xjzt from dm_ydlb where ydlbm='" + ydlbdm+ "'";
		xj = dao.getOneRs(sql_ydlb, new String[] {}, "xjzt");
		
		return xj;
	}

	
	/**
	 * dwr�ͻ��˵õ�δ�ύ���ϱ��ѧ��
	 * @param String xh
	 * @return HashMap<String, String>
	 * */ 
	public HashMap<String, String> getGdzlxx(String xh) {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<String[]> rsDiff = null;
		String tableName = "view_stu_gdzlb";
		String[] cols_en = { "xh", "xm", "zlbmc", "sftj" };
		String[] cols_cn = dao.getColumnNameCN(cols_en, tableName);
		StringBuffer title_cn = new StringBuffer("");
		for (int i = 0; i < cols_cn.length; i++) {
			title_cn.append(cols_cn[i]);
			title_cn.append(i == cols_cn.length - 1 ? "" : "!!");
		}
		rsDiff = dao.rsToVator("select xh,xm,zlbmc,sftj from view_stu_gdzlb order by xh,xm",new String[] {}, cols_en);
		Object[] temRs = (rsDiff != null) ? rsDiff.toArray(): (new String[] {});
		StringBuffer rsStr = new StringBuffer("");
		for (int i = 0; i < temRs.length; i++) {
			String[] temArr = (String[]) temRs[i];
			for (int j = 0; j < temArr.length; j++) {
				rsStr.append(temArr[j]);
				rsStr.append((j == temArr.length - 1) ? "" : "!!");
			}
			rsStr.append((i == temRs.length - 1) ? "" : "@@");
		}
		map.put("title", title_cn.toString());
		map.put("data", rsStr.toString());
		return map;
	}

	/**
	 * �жϼ�¼�Ƿ��Ѿ�����
	 * @param String tableName
	 * @param String column
	 * @param String columnValue
	 * @return boolean
	 * */ 
	public boolean getColumnEx(String tableName, String column, String columnValue) {
		DAO dao = DAO.getInstance();
		boolean flag = false;
		int result = 0;
		String sql = "select count(*) num from " + tableName + " where "+ column + "=?";
		result = Integer.parseInt(dao.getOneRs(sql,new String[] { columnValue }, "num"));
		if (result > 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * dwrϵͳά���ڹ���ѧ--���ݵ�λ����Ͳ��Ŵ����ȡ��ϵ����Ϣ
	 * @param String dwdm
	 * @param String bmdm
	 * @return String
	 * */ 
	public String getLxr(String dwdm, String bmdm) {
		DAO dao = DAO.getInstance();
		String str = "";
		String sql = "select yhm ,xm from yhb where dwdm=? and szbm=?";
		str = dao.getStringToSplit(sql, new String[] { dwdm, bmdm },new String[] { "yhm", "xm" });
		return str;
	}

	/**
	 * dwrϵͳά���ڹ���ѧ--���ݵ�λ����Ͳ��Ŵ����ȡ��ϵ����Ϣ
	 * @param String yhm
	 * @return String
	 * */ 
	public String getLxrInfo(String yhm) {
		DAO dao = DAO.getInstance();
		String[] temp = null;
		String str = "";
		String sql = "select dwdm,szbm from yhb where yhm=?";
		str = dao.getStringToSplit(sql, new String[] { yhm }, new String[] {"dwdm", "szbm" });
		temp = str.split("!!SplitSignOne!!");
		temp = temp[1].split("!!SplitSignTwo!!");
		str = temp[1];
		str += "!!#!!" + temp[2];
		return str;
	}

	/**
	 * @param nj
	 * @param xydm
	 * @param zydm
	 * @param xn
	 * @param bjdm
	 * @param xh
	 * @param xm
	 * @return ���ز�ѯ����
	 */
	public static String getQuerry(String nj, String xydm, String zydm,
			String xn, String bjdm, String xh, String xm) {
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1");
		if ("".equalsIgnoreCase(nj) || nj == null) {
			querry.append(" and 1=1");
		} else {
			if (Check_Input_Value.check2(nj)) {
				querry.append(" and nj='");
				querry.append(nj);
				querry.append("'");
			}
		}
		if ("".equalsIgnoreCase(xydm) || xydm == null) {
			querry.append(" and 1=1");
		} else {
			if (Check_Input_Value.check2(xydm)) {
				querry.append(" and xydm='");
				querry.append(xydm);
				querry.append("'");
			}
		}
		if ("".equalsIgnoreCase(zydm) || zydm == null) {
			querry.append(" and 1=1");
		} else {
			if (Check_Input_Value.check2(zydm)) {
				querry.append(" and zydm='");
				querry.append(zydm);
				querry.append("'");
			}
		}
		if ("".equalsIgnoreCase(bjdm) || bjdm == null) {
			querry.append(" and 1=1");
		} else {
			if (Check_Input_Value.check2(bjdm)) {
				querry.append(" and bjdm='");
				querry.append(bjdm);
				querry.append("'");
			}
		}
		if ("".equalsIgnoreCase(xn) || xn == null) {
			querry.append(" and 1=1");
		} else {
			if (Check_Input_Value.check2(xn)) {
				querry.append(" and xn='");
				querry.append(xn);
				querry.append("'");
			}
		}
		if ("".equalsIgnoreCase(xh) || xh == null) {
			xh = "%";
			querry.append(" and xh like ?");
		} else {
			if (Check_Input_Value.check2(xh)) {
				xh += "%";
				querry.append(" and xh like ?");
			}
		}
		if ("".equalsIgnoreCase(xm) || xm == null) {
			xm = "%";
			querry.append(" and xm like ?");
		} else {
			if (Check_Input_Value.check2(xm)) {
				xm = "%" + xm + "%";
				querry.append(" and xm like ?");
			}
		}
		return querry.toString();
	}	
	
	/**
	 * ��ѯ�༶�ĸ�����Ϣ
	 * @param String bjdm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryBjParentInfo(String bjdm){
		XsxxglService service = new XsxxglService();
		return service.queryBjParentInfo(bjdm);
	}
	
	public String getWfxxx(String userType, String userDep,
			String isFdy, String userName){
		DAO dao = DAO.getInstance();
		StringBuilder whereSql = new StringBuilder();
		String sql = "select count(*) num  from yhqxb where yhm=? and gnmkdm = 'N110301' and dxq='1'";
		String count = dao.getOneRs(sql, new String[]{userName}, "num");
		//������û�û�и�������ܲ˵�Ȩ�ޣ���ô�Ͳ�����ʾ����ѧ����Ϣ
		if ("0".equalsIgnoreCase(count) || StringUtils.isNull(count)) {
			return "";
		}
		String nowTime = GetTime.getNowTime2();
		sql = StringUtils.joinStr("select count(*) num from view_xjydjbxx a " ,
				                  "where not exists(" ,
				                  "select 1 from view_xjydjbxx b where a.xh=b.xh " ,
				                  "and to_number(b.ydxh)>to_number(a.ydxh) and b.ydlbmc='��ѧ'" ,
				                  ") and ydlbmc='��ѧ' and " ,
				                  "to_number(substr(ydjzrq,0,4)||substr(ydjzrq,6,2)||substr(ydjzrq,9,2))<=" , 
				                  nowTime );
		
		if("true".equalsIgnoreCase(isFdy)){//����Ա
			whereSql.append(" and exists(select 1 from fdybjb b ");
			whereSql.append("where (a.ydqbjdm=b.bjdm or a.ydhbjdm=b.bjdm) and b.zgh='");
			whereSql.append(userName);
			whereSql.append("')");
		}else if("xy".equalsIgnoreCase(userType)){//ѧԺ
			whereSql.append(" and ( ydhxydm='");
			whereSql.append(userDep);
//			whereSql.append("' or ydqxydm='");
//			whereSql.append(userDep);
			whereSql.append("')");
		}
		return dao.getOneRs(sql+whereSql, new String[]{}, "num");
	}
	
	
	/**
	 * ���ʴ�ѧ�ڹ���ѧ��Ϣ��ʾ
	 * @throws SQLException 
	 *
	 */
	public List<String> getQgzxTsxx() throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select * from zgdzdx_qgzx_xxts where to_number(to_char(sysdate,'yyyymmdd')) between kssj and jssj";
		return dao.getList(sql, new String[] {}, "tsnr");
		
	}
	
	
	/**
	 * ��������У�鿴�����Ϣ��ʾ
	 * @param xh
	 * @return
	 */
	public String isWjcfJcsj(String xh) {
		DAO dao = DAO.getInstance();
		StringBuilder sb = new StringBuilder();
		
		sb.append("select * from view_wjcf where xh=? and cflb = '04' and xxsh='ͨ��' ");
		sb.append("and to_number(to_char(sysdate,'yyyymmdd'))-cxclsj<31 and cxjg!='�����У�쿴' ");
		
		return dao.getOneRs(sb.toString(), new String[] {xh}, "xh");
	}
	
	/**
	 * ��ѯѧ���춯�����Ϣ
	 * @param ydlbdm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXjydlbxx(String ydlbdm){
		DAO dao = DAO.getInstance();
		String sql = "select * from dm_ydlb where ydlbm=?";
		return dao.getMap(sql, new String[]{ydlbdm},new String[]{"ydlbmc","xjzt","sfzx"}) ;
	}
}
