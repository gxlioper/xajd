package xgxt.xszz.portallet;

import java.util.HashMap;
import java.util.List;

import xgxt.shgz.model.common.CommonModel;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 学生资助提供Portal查询Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-04-08</p>
 */

public class XszzForPortalletService {
	XszzForPortalletDAO dao = new XszzForPortalletDAO();
	
	/**
	 * 获取学生资助情况信息
	 * @param model
	 * @return List
	 * */
	public List getXszz(String xh){		
		return dao.getXszz(xh);
	}
	
	/**
	 * 获取学生资助情况表头
	 * @return List
	 * */
	public List getXszzTortr(){
		String[] col = {"nd", "xmmc", "zzje"};
		String[] colCN = {"年度", "资助名称", "资助金额"};
		return dao.arrayToList(col, colCN);
	}
	
	/**
	 * 获取国家助学贷款信息
	 * @param model
	 * @return List
	 * */
	public List getGjzxdk(String xh){		
		return dao.getGjzxdk(xh);
	}
	
	/**
	 * 获取国家助学贷款表头
	 * @return List
	 * */
	public List getGjzxdkTortr(){
		String[] col = {"nd", "hth", "dkje"};
		String[] colCN = {"年度", "合同号", "贷款金额"};
		return dao.arrayToList(col, colCN);
	}
	
	
	/**
	 * 学生信息
	 * @param xh
	 * @return
	 */
	public HashMap<String,String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}
	
	
	/**
	 * 受资助情况
	 * @param model
	 * @return
	 */
	public List<String[]> getSzzqk(CommonModel model) {
		return dao.getSzzqk(model);
	}
}
