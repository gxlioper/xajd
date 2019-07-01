package xgxt.wjcf.nbcs;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

public class WjcfNbcsDAO {

	private DAO dao = DAO.getInstance();
	
	private StringBuffer queryNcftz = new StringBuffer("select xh||xn||sbsj pk,xn,nd,cflbmc,cfyymc,sfsb,wjsj,xxsh,cfsj,cfwh,(case when xftz='yes' then '���·�' else 'δ�·�' end) xftz ")
								              .append(" from view_wjcf where xftz='yes' and xh=?"); 
	
	/**
	 * ����Ӣ�ģ���������  ��װ��ͷ��Ϣ
	 * @param en
	 * @param cn
	 * @return
	 */
	public List<HashMap<String, String>> getTitle(String[] en, String[] cn) {
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * �޸Ĵ����·�֪ͨ״̬
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public boolean updateXfcftz(String[] keys) throws Exception{
		if (keys == null) {
			return false;
		}
		String[] sqlArr = new String[keys.length];
		for (int i=0;i<keys.length;i++) {
			StringBuffer sql = new StringBuffer("update wjcfb set xftz='yes' where xh||xn||sbsj='");
			sql.append(keys[i]);
			sql.append("'");
			sqlArr[i] = sql.toString();
		}
		int[] result = dao.runBatch(sqlArr);
		
		return dao.checkBatch(result);
	}
	
	/**
	 * ��ѯ����ѧ���Ĵ��⴦����Ϣ
	 * @param xh
	 * @return
	 */
	public List<String[]> queryNcftzBystu(String xh) {
		String[] colList = {"pk","xn", "nd", "cflbmc", "cfyymc", "wjsj", "sfsb", "xftz", "xxsh", "cfsj", "cfwh"};
		return dao.rsToVator(queryNcftz.toString(), new String[] { xh },
				colList);
	}
	
	/**
	 * ����ѧ����д��֪ͨ����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveNcftzxx(WjcfNbcsModel model) throws Exception{
		return dao.runUpdate(
				"update wjcfb set sfsb=?, sbsy=? where xh||xn||sbsj=?",
				new String[] { model.getSfsb(), model.getSbsy(),
						model.getPkValue() });
	}
	
	/**
	 * ��ʾѧ����д��֪ͨ����Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewNcftzxx(String pkValue) throws Exception{
		return dao.getMapNotOut("select xh,xm,xymc,nj,xb,zymc,bjmc,xn,nd,cflbmc," +
				"cfyymc,bz,wjsj,sfsb,sbsy,xxsh,cfsj,cfwh,(case when cfsj is not null and cfwh is not null then 'true' else 'false' end) write from view_wjcf where xh||xn||sbsj=?", new String[]{pkValue});
	}
	
	/**
	 * ֪ͨ���ӡ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> tzsPrint(String pkValue) {
		if (StringUtils.isNull(pkValue)) {
			return new HashMap<String, String>();
		} else {
			return dao.getMapNotOut("select xh,xm,xymc,zymc,bjmc,bz,sfsb,sbsy," +
					"xyclyj,xxclyj from view_wjcf where xh||xn||sbsj = ?", new String[]{pkValue});
		}
	}
	
	/**
	 * �ʱ����ӡ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> cbbPrint(String pkValue, String cfpk) {
		if (StringUtils.isNull(pkValue)) {
			return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,cfyymc,bz from view_wjcf where xh||cflb||cfyy||xn||nd=?", new String[]{cfpk});
		} else {
			return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,cfyymc,bz,xxclyj,xyclyj from view_wjcf where xh||xn||sbsj=?", new String[]{pkValue});
		}
	}
}
