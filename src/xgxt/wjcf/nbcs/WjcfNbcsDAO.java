package xgxt.wjcf.nbcs;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

public class WjcfNbcsDAO {

	private DAO dao = DAO.getInstance();
	
	private StringBuffer queryNcftz = new StringBuffer("select xh||xn||sbsj pk,xn,nd,cflbmc,cfyymc,sfsb,wjsj,xxsh,cfsj,cfwh,(case when xftz='yes' then '已下发' else '未下发' end) xftz ")
								              .append(" from view_wjcf where xftz='yes' and xh=?"); 
	
	/**
	 * 传入英文，中文数组  组装表头信息
	 * @param en
	 * @param cn
	 * @return
	 */
	public List<HashMap<String, String>> getTitle(String[] en, String[] cn) {
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 修改处分下发通知状态
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
	 * 查询单个学生的待拟处分信息
	 * @param xh
	 * @return
	 */
	public List<String[]> queryNcftzBystu(String xh) {
		String[] colList = {"pk","xn", "nd", "cflbmc", "cfyymc", "wjsj", "sfsb", "xftz", "xxsh", "cfsj", "cfwh"};
		return dao.rsToVator(queryNcftz.toString(), new String[] { xh },
				colList);
	}
	
	/**
	 * 保存学生填写的通知书信息
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
	 * 显示学生填写的通知书信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewNcftzxx(String pkValue) throws Exception{
		return dao.getMapNotOut("select xh,xm,xymc,nj,xb,zymc,bjmc,xn,nd,cflbmc," +
				"cfyymc,bz,wjsj,sfsb,sbsy,xxsh,cfsj,cfwh,(case when cfsj is not null and cfwh is not null then 'true' else 'false' end) write from view_wjcf where xh||xn||sbsj=?", new String[]{pkValue});
	}
	
	/**
	 * 通知书打印
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
	 * 呈报表打印
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
