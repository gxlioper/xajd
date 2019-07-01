package xgxt.wjcf.hhgxy;

import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

public class WjcfHhgxyDAO {

	DAO dao = DAO.getInstance();
	
	public static WjcfHhgxyDAO mydao = new WjcfHhgxyDAO();
	
	public static WjcfHhgxyDAO getInstance() {
		return mydao;
	}
	
	/**
	 * 违纪处分预先告知书查询结果
	 * @param xh
	 * @param cflb
	 * @param cfyy
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> wjcfysbPrint(String xh, String cflb,
			String cfyy, String pkValue) throws Exception {
		if (!StringUtils.isNull(xh)) {
			return dao
					.getMapNotOut(
							"select xh,xb,xm,nj,xymc,zymc,bjmc,cflbmc,cfyymc,bz from view_wjcf where xh=? and cflb=? and cfyy=? and xn=? and nd=?",
							new String[] { xh, cflb, cfyy, Base.currXn,
									Base.currNd });
		}
		if (!StringUtils.isNull(pkValue)) {
			
		}
		return null;
	}
	
	/**
	 * 违纪处分申诉决定查询结果
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> wjcfssjdPrint(String pkValue) throws Exception {
		HashMap<String, String> rs = new HashMap<String, String>();
		return rs;
	}
	
	/**
	 * 批量发文
	 * @param pkVal
	 * @param cfwh
	 * @param cfsj
	 * @return
	 * @throws Exception
	 */
	public boolean plfw(String pkVal, String cfwh, String cfsj) throws Exception{
		if (!StringUtils.isNull(pkVal)) {
			String[] sql = new String[]{};
			String[] list = pkVal.split("-");
			for (int i=0;i<list.length;i++) {
				StringBuffer sqlBuf = new StringBuffer("");
				sqlBuf.append(" update wjcfb set cfsj='");
				sqlBuf.append(cfsj);
				sqlBuf.append("',cfwh='");
				sqlBuf.append(cfwh);
				sqlBuf.append("' where xh||xn||nd||sbsj='");
				sqlBuf.append(list[i]);
				sqlBuf.append("'");
				sql[i] = sqlBuf.toString();
			}
			int[] flag = dao.runBatch(sql);
			for (int i=0;i<flag.length;i++) {
				if(flag[i] <= -1){
					return false;
				}
			}
		}
		return false;
	}
}
