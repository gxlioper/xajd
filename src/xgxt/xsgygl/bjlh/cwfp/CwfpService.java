package xgxt.xsgygl.bjlh.cwfp;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.xsgygl.bjlh.BjlhGyglForm;

public class CwfpService {
	CwfpDAO dao = new CwfpDAO();

	/**
	 * 未分配学生总人数（这里针对的是学生人数）
	 * @param xydm
	 * @param fpbj 全日制，团委，体育教学部，科研处，成人教育处
	 * @return
	 */
	public String[] getCwFpZsData(String xydm, String zydm, String bjdm,
			String nj, String fpbj) {
		return dao.getCwFpZsData(xydm, zydm, bjdm, nj, fpbj);
	}

	/**
	 * 分配标记列表
	 * @return
	 */
	public List<HashMap<String, String>> getFpbjList() {
		return dao.getSelectList("fpbj");
	}

	/**
	 * List
	 * @return
	 * @throws Exception 
	 */
	public void setList(BjlhGyglForm myForm, HttpServletRequest request)
			throws Exception {
		dao.setList(myForm, request);
	}

	/**
	 * 已分配床位总数（这里针对的是床位总数）
	 * @param xydm
	 * @param fpbj 全日制，团委，体育教学部，科研处，成人教育处
	 * @return
	 */
	public String[] getCwFpYhfcws(String xydm, String zydm, String bjdm,
			String nj, String lddm, String cs, String fpbj) {
		return dao.getCwFpYhfcws(xydm, zydm, bjdm, nj, lddm, cs, fpbj);
	}

	/**
	 * 未分配床位总数（这里针对的是床位总数）
	 * 
	 * @param xydm
	 * @param fpbj
	 *            全日制，团委，体育教学部，科研处，成人教育处
	 * @return
	 */
	public String[] getCwFpWhfcws(String xydm, String lddm, String cs,
			String fpbj) {
		return dao.getCwFpWhfcws(xydm, lddm, cs, fpbj);
	}

	/**
	 * 未分配床位列表
	 * @param xqdm
	 * @param xb
	 * @param lddm
	 * @param cs
	 * @return
	 */
	public List<HashMap<String, String>> getCwFpCwXxList(String xqdm,
			String xb, String lddm, String cs, String fplx) {
		return dao.getCwFpCwXxList(xqdm, xb, lddm, cs, fplx);
	}

	/**
	 * 未分配学生列表
	 * @param xqdm
	 * @param xb
	 * @param lddm
	 * @param cs
	 * @return
	 */
	public List<HashMap<String, String>> getCwFpXsXxList(String xydm,
			String zydm, String bjdm, String nj, String xb, String fplx) {
		return dao.getCwFpXsXxList(xydm, zydm, bjdm, nj, xb, fplx);
	}

	/**
	 * 已分配学生列表
	 * @param xqdm
	 * @param xb
	 * @param lddm
	 * @param cs
	 * @return
	 */
	public List<HashMap<String, String>> getCwFpYfpXsXxList(String xydm,
			String zydm, String bjdm, String nj, String xb, String lddm,
			String cs, String fplx) {
		return dao.getCwFpYfpXsXxList(xydm, zydm, bjdm, nj, xb, lddm, cs, fplx);
	}

	/**
	 * 保存床位分配信息
	 * @param oldMappingItems
	 * @param newMappingItems
	 * @return
	 * @throws Exception
	 */
	public boolean saveCwfpxx(String oldMappingItems, String newMappingItems, String fplx)
			throws Exception {
		return dao.saveCwfpxx(oldMappingItems, newMappingItems, fplx);
	}
	
	/**
	 * 保存行李床位分配信息
	 * @param cwList
	 * @return
	 * @throws Exception
	 */
	public boolean saveXlCwfpxx(String cwList)
			throws Exception {
		return dao.saveXlCwfpxx(cwList);
	}
}
