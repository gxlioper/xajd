package xgxt.sztz.csmzzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class SztzWhtlDAO {

	public List<HashMap<String, String>> getXmList(String xn, String xq) {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		List<String> inputV = new ArrayList<String>();
		sql.append(" select distinct a.xmid,xmmc ");
		sql.append(" from csmz_tzcgb a, csmz_tzxmb b ");
		sql.append(" where a.xmid = b.id ");

		if (!Base.isNull(xn)) {

			inputV.add(xn);
			sql.append("  and xn = ? ");

		}

		if (!Base.isNull(xq)) {
			inputV.add(xq);
			sql.append("  and xq =? ");
		}

		return dao.getList(sql.toString(), inputV.toArray(new String[] {}),
				new String[] { "xmid", "xmmc" });
	}

	public List<HashMap<String, String>> getJxList(String xmid) {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		List<String> inputV = new ArrayList<String>();
		
		sql.append(" select jxid,jxm ");
		sql.append(" from csmz_tzxmjxb ");
		sql.append(" where 1=1 ");
		
		if (!Base.isNull(xmid)) {
			inputV.add(xmid);
			sql.append("  and xmid =? ");
		}
		return dao.getList(sql.toString(), inputV.toArray(new String[] {}),
						new String[] { "jxid", "jxm" });
	}

}
