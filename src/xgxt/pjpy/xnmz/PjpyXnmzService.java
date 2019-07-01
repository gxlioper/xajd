package xgxt.pjpy.xnmz;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.WSForCjJd.WebserviceClient;
import xgxt.utils.String.StringUtils;

public class PjpyXnmzService {

	private PjpyXnmzDAO dao = null;
	DAO daoo = DAO.getInstance();
	/**
	 * 学生成绩绩点同步
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean stucjjdTbResult() throws Exception {
		dao = new PjpyXnmzDAO();
		WebserviceClient wc = new WebserviceClient();
		HashMap<String, String> rs = Base.initProperties;
		String url = rs.get("XnmzWebserviceUri");
		if (StringUtils.isNull(url)) {
			return false;
		}
		wc.setEndPointUri(url);
		String[] xhList = dao.getStuxh();// 学号列表
		/*String[] xhList = new String[]{"06090243", "06090245", "06090246",
		 "06090247"};*/
		String stumm = dao.getStuMm();// 学生密码
		stumm = StringUtils.isNull(stumm) ? "0" : stumm;
		dao.xscjjdDel();// 先删除再插入
		ArrayList<String[]> tmpList = new ArrayList<String[]>();
		int k = 0;
		for (int i = 0; i < xhList.length; i++) {
			String[] tmp = wc.getJd(xhList[i], stumm);
			if (tmp == null || (null != tmp && tmp.length == 0)) {
				continue;
			}
			String[] stuList = dao.insertStucjjd(tmp);// 返回每个学生的成绩绩点
			tmpList.add(stuList);
			if ((i % 50) == 0 || (xhList.length == (i+1))) {
				String[] tmpInsertSql = null;
				for (int j = 0; j < tmpList.size(); j++) {
					tmpInsertSql = dao.unionArray(tmpList.get(j), tmpInsertSql);
				}
				daoo.runBatch(tmpInsertSql);
				tmpList = new ArrayList<String[]>();
				k++;
			}
		}
		return true;
	}

	public List<HashMap<String, String>> stucjjdTitle() throws Exception {
		dao = new PjpyXnmzDAO();
		return dao.stucjjdTitle();
	}

	public List<HashMap<String, String>> stucjjdTitle1() throws Exception {
		dao = new PjpyXnmzDAO();
		return dao.stucjjdTitle1();
	}

	public List<HashMap<String, String>> stucjjdTitle2() throws Exception {
		dao = new PjpyXnmzDAO();
		return dao.stucjjdTitle2();
	}

	/**
	 * 查询结果
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> stucjjdQry(CjjdModel model) throws Exception {
		dao = new PjpyXnmzDAO();
		String lx = model.getLx();
		if (!StringUtils.isNull(lx) && "1".equalsIgnoreCase(lx)) {
			return dao.stucjjdQry1(model);
		} else if (!StringUtils.isNull(lx) && "3".equalsIgnoreCase(lx)) {
			return dao.stucjjdQry2(model);
		}
		return dao.stucjjdQry(model);
	}

	/**
	 * 成绩表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cjTitle() throws Exception {
		dao = new PjpyXnmzDAO();
		return dao.cjTitle();
	}

	/**
	 * 成绩结果
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cjResult(CjjdModel model) throws Exception {
		dao = new PjpyXnmzDAO();
		return dao.cjResult(model);
	}

	public List<HashMap<String, String>> shcbcjjdTitle() throws Exception {
		dao = new PjpyXnmzDAO();
		return dao.shcbcjjdTitle();
	}

	public List<String[]> shcbCjjdResult(CjjdModel model) throws Exception {
		dao = new PjpyXnmzDAO();
		return dao.shcbCjjdResult(model);
	}

	public List<HashMap<String, String>> zhszcpTitle(CjjdModel model)
			throws Exception {
		dao = new PjpyXnmzDAO();
		String lx = model.getLx();
		if (!StringUtils.isNull(lx) && "0".equalsIgnoreCase(lx)) {
			return dao.zhszcpTitle2();
		} else if (!StringUtils.isNull(lx) && "1".equalsIgnoreCase(lx)) {
			return dao.zhszcpTitle1();
		} else {
			return dao.zhszcpTitle();
		}
	}

	public List<String[]> zhszcpResult(CjjdModel model) throws Exception {
		dao = new PjpyXnmzDAO();
		String lx = model.getLx();
		if (!StringUtils.isNull(lx) && "0".equalsIgnoreCase(lx)) {
			return dao.zhszcpResult2(model);
		} else if (!StringUtils.isNull(lx) && "1".equalsIgnoreCase(lx)) {
			return dao.zhszcpResult1(model);
		} else {
			return dao.zhszcpResult(model);
		}

	}

	public List<HashMap<String, String>> xfTitle() throws Exception {
		dao = new PjpyXnmzDAO();
		return dao.xfTitle();
	}

	public List<String[]> xfResult(CjjdModel model) throws Exception {
		dao = new PjpyXnmzDAO();
		return dao.xfResult(model);
	}

	public boolean xfsave(CjjdModel model, HttpServletRequest request)
			throws Exception {
		dao = new PjpyXnmzDAO();
		return dao.xfsave(model, request);
	}

	public HashMap<String, String> xfview(String pkValue) throws Exception {
		dao = new PjpyXnmzDAO();
		return dao.xfview(pkValue);
	}

	public boolean xfUpdate(String pkValue, CjjdModel model,
			HttpServletRequest request) throws Exception {
		dao = new PjpyXnmzDAO();
		return dao.xfUpdate(pkValue, model, request);
	}

	public String xfDel(String[] keys, HttpServletRequest request)
			throws Exception {
		dao = new PjpyXnmzDAO();
		return dao.xfDel(keys, request);
	}
}