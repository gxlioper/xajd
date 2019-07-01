package xgxt.pjpy.jhzy.pjsz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class PjszService {
	
	DAO dao = DAO.getInstance();
	PjszDAO myDao = new PjszDAO();

	public void saveDzzbsjhf(String zgh, String[] zydm, HttpServletRequest request) throws Exception {
		String sUName;

		String logMsg;
		
		String sql;
		
		Vector<HashMap<String, Object>> sqlToExe = new Vector<HashMap<String, Object>>();
		HashMap<String, Object> mapTmp;
		String[] tmp;
		
		boolean ok = false;
		
		HttpSession session = request.getSession();
		
		sUName = session.getAttribute("userName").toString();
		if (zydm == null || Base.isNull(zgh)) {
			sql = "delete from dzzbsjhfb where zgh=?";
			ok = dao.runUpdate(sql, new String[] { zgh });
			logMsg = "清空了代码为" + zgh + "的党总支部书记名下的班级";
		} else {
			mapTmp = new HashMap<String, Object>();
			sql = "delete from dzzbsjhfb where zgh=?";
			ok = dao.runUpdate(sql, new String[] { zgh });
			tmp = new String[] { zgh };
			mapTmp.put("sqlTxt", sql);
			mapTmp.put("sqlVal", tmp);
			sqlToExe.add(mapTmp);
			logMsg = "维护党总支部书记划分表，党总支部书记代码为" + zgh + "名下的专业有";
			for (int i = 0; i < zydm.length; i++) {
				mapTmp = new HashMap<String, Object>();
				sql = "insert into dzzbsjhfb(zgh,zydm) values(?,?)";
				tmp = new String[] { zgh, zydm[i] };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);
				logMsg += zydm[i] + ",";
			}
			ok = dao.runUpdate(sqlToExe);
		}
		if (ok) {
			Base.log(request, logMsg, sUName);
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
	}

	public List getFdyList(String jsbmdm) {
		return myDao.getFdyList(jsbmdm);
	}

	public List getZyList(String nj, String bmdm) {
		return myDao.getZyList(nj,bmdm);
	}

	public List getFdyZyList(String zgh) {
		return myDao.getFdyZyList(zgh);
	}

	public List fdyZyList(String zgh) {
		return myDao.getFdyZyList(zgh);
	}

	public HashMap<String, String> getFdyInfo(String zgh, String jsbmdm) {
		return myDao.getFdyInfo(zgh,jsbmdm);
	}

	public List<HashMap<String, String>> getBmList() {
		return dao.getBmList();
	}
	/**
	 * @author 
	 * @throws Exception
	 * @describe 删除条件
	 */
	public boolean delTj(String pk, HttpServletRequest request)
	throws Exception {
		return myDao.delTj(pk, request);
	}
	/**
	 * @author 
	 * @describe 获得条件
	 */
	public List<HashMap<String, String>> getTjList(PjszModel model,
			String szlx, String[] colList) throws SQLException {
		return myDao.getTjList(model, szlx, colList);
	}
	/**
	 * @author 
	 * @describe 获得条件字段列表
	 */
	public List<HashMap<String, String>> getZdList() throws SQLException {
		return myDao.getZdList();
	}
	/**
	 * 获取荣誉称号列表
	 * @return
	 */
	public List<HashMap<String,String>>serv_getRychList(){
		return myDao.getRychList();
	}
	/**
	 * 奖学金列表
	 * @param  
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList(String jxjlbdm) throws Exception {
		return myDao.getJxjList(jxjlbdm);
	}
	/**
	 * @author 
	 * @throws Exception
	 * @describe 保存条件设置
	 */
	public boolean saveTjsz(PjszModel myModel,HttpServletRequest request) throws Exception {
		return myDao.saveTjsz(myModel, request);
	}
	/**
	 * @author 
	 * @throws Exception
	 * @describe 是否符合条件
	 * @ xh(学号)
	 * @ xn(学年) 
	 * @ xq(学期)
	 * @ szlx(设置类型：1、荣誉称号：rych；2、奖学金：jxj；)
	 * @ dm(如果是奖学金，传奖学金代码，如果是荣誉称号，传荣誉称号代码)
	 * 	
	 */
	public boolean isTjsz(String xh,String xn,String xq,String szlx,String dm) throws Exception {
		boolean tjsz = true;
		String[] tjlx = myDao.getSxtj(xn, xq, szlx, dm);
		for(int i = 0;i<tjlx.length;i++){
			if(tjsz){
				tjsz = myDao.isTjsz(xh,xn, xq, szlx, dm, tjlx[i]);
			}
		}
		return tjsz;
	}
}
