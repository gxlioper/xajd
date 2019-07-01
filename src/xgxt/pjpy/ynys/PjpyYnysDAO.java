
package xgxt.pjpy.ynys;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����������������DAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-17</p>
 */
public class PjpyYnysDAO {

	protected DAO dao = null;//�Ͳ����ݲ���
	
	/**
	 * ��ѧ������ʱ��
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjsqsj() throws Exception {
		dao = DAO.getInstance();
		return dao.getMapNotOut("select jxjsqxn,jxjsqxq,jxjsqnd from xtszb",
				new String[] {});
	} 
	
	/**
	 * ��ѯ��ͷ
	 * @param enList
	 * @param cnListĿ �����������,Ӣ����ǰ,�����ں�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQryTitle(String[] enList,
			String[] cnList) throws Exception {
		dao = DAO.getInstance();
		return dao.arrayToList(enList, cnList);
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		dao = DAO.getInstance();
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,(select sjhm from xsfzxxb " +
						"b where b.xh=view_xsjbxx.xh) sjhm,(select wysp from xsfzxxb b " +
						"where b.xh=view_xsjbxx.xh) wysp from view_xsjbxx where xh=?",
						new String[] { xh });
	}
	
	public List<String[]> getCjList(String xh) throws Exception {
		dao = DAO.getInstance();
		String xn = Base.getJxjsqxn();
		return dao.rsToVator("select kcmc,cj,kclx,(case when sfbxk='0' then '��' else '��' end) sfbxk from view_kxwhszfb where xh=? and xn=?", new String[]{xh, xn}, new String[]{"kcmc", "cj", "kclx", "sfbxk"});
	}
	
	public HashMap<String, String> getCjpm(String xh) throws Exception {
		dao = DAO.getInstance();
		String xn = Base.getJxjsqxn();
		return dao
				.getMapNotOut(
						"select (select distinct trunc(avg(cj) over (partition by xh),2) whkpjf from view_kxwhszfb a where kclx='�Ļ���' and xn=? and xh=?) whkpjf,(select distinct trunc(avg(cj) over (partition by xh),2) zykpjf from view_kxwhszfb a where kclx='רҵ��' and xn=? and xh=?) zykpjf from dual",
						new String[] {xn, xh, xn, xh});
	}
	
	public HashMap<String, String> getZhfpm(String xh) throws Exception {
		dao = DAO.getInstance();
		String xn = Base.getJxjsqxn();
		return dao
				.getMapNotOut(
						"select zf,pm from (select xh,nj,zydm,zhszcpzf zf,(dense_rank() over (partition by nj,zydm order by to_number(trim(zhszcpzf)) desc nulls last)) pm from view_ynys_zhszcp where xn=?) where xh=?",
						new String[] {xn, xh});
	}
}
