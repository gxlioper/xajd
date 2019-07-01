package xgxt.jygl.jyglnbty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.FormModleCommon;

// TODO: Auto-generated Javadoc
/**
 * The Class JycxzmService.
 */
public class SyxxService {

	/**
	 * Gets the table top.
	 * 
	 * @param lb the lb
	 * 
	 * @return the table top
	 */
	public List<HashMap<String, String>> getTableTop(String lb) {
		String[] ens = null;
		String[] cns = null;
		DAO dao = DAO.getInstance();
		if ("xjgr".equals(lb)) {
			ens = new String[] { "pk", "xn", "zgh", "xm", "xb", "bmmc", "zwmc",
					"xysh", "xxsh" };
			cns = new String[] { "pk", "学年", "职工号", "姓名", "性别", Base.YXPZXY_KEY+"名称", "职务名称",
					Base.YXPZXY_KEY+"审核", "学校审核" };
		}
		if ("jsxx".equals(lb)) {
			ens = new String[] { "zgh", "xm", "xb", "bmmc", "zwmc" };
			cns = new String[] { "职工号", "姓名", "性别", Base.YXPZXY_KEY+"名称", "职务名称" };
		}
		if ("yxbys".equals(lb)) {
			ens = new String[] { "pk", "xn", "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc", "xysh", "xxsh" };
			cns = new String[] { "pk", "学年", "学号", "姓名", "性别", "年级",Base.YXPZXY_KEY+"名称",
					"专业名称", "班级名称", Base.YXPZXY_KEY+"审核", "学校审核" };
		}

		return dao.arrayToList(ens, cns);
	}

	/**
	 * Gets the toptr title.
	 * 
	 * @param tableName the table name
	 * @param colList the col list
	 * 
	 * @return the toptr title
	 * 
	 * @throws Exception the exception
	 */
	public List<HashMap<String, String>> getToptrTitle(String tableName,
			String[] colList) throws Exception {
		SyxxDAO dao = new SyxxDAO();
		return dao.getToptrTitle(tableName, colList);
	}

	/**
	 * Ser_ jycczm query.
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * @param colList the col list
	 * 
	 * @return the array list< string[]>
	 * 
	 * @throws Exception the exception
	 */
	public ArrayList<String[]> ser_JycczmQuery(SyxxModel model,
			String tableName, String[] colList) throws Exception {
		SyxxDAO dao = new SyxxDAO();
		return dao.dao_JycczmQuery(model, tableName, colList);
	}

	/**
	 * Ser_ jycczm add.
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * 
	 * @return the boolean
	 * 
	 * @throws Exception the exception
	 */
	public Boolean ser_JycczmAdd(SyxxModel model, String tableName)
			throws Exception {
		SyxxDAO dao = new SyxxDAO();
		return dao.dao_JycczmAdd(model, tableName);
	}
	
	/**
	 * Ser_ jycczm is exists.
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * 
	 * @return the boolean
	 * 
	 * @throws Exception the exception
	 */
	public Boolean ser_JycczmIsExists(String xh)
	throws Exception {
		SyxxDAO dao = new SyxxDAO();
		return dao.dao_JycczmIsExists(xh);
	}
	
	/**
	 * Ser_ jycczm update.
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * 
	 * @return the boolean
	 * 
	 * @throws Exception the exception
	 */
	public Boolean ser_JycczmUpdate(SyxxModel model, String tableName)
	throws Exception {
		SyxxDAO dao = new SyxxDAO();
		return dao.dao_JycczmUpdate(model, tableName);
	}

	/**
	 * Ser_idfor query.
	 * 
	 * @param pk the pk
	 * @param tableName the table name
	 * 
	 * @return the hash map< string, string>
	 * 
	 * @throws Exception the exception
	 */
	public HashMap<String, String> ser_idforQuery(String pk, String tableName)
			throws Exception {
		SyxxDAO dao = new SyxxDAO();
		return dao.dao_idforQuery(pk, tableName);
	}

	/**
	 * Dao_ all del list.
	 * 
	 * @param pks the pks
	 * @param tableName the table name
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public String dao_AllDelList(String pks, String tableName) throws Exception {
		SyxxDAO dao = new SyxxDAO();
		return dao.dao_AllDelList(pks, tableName);
	}
	
	/**
	 * Serv_jycczm sh.
	 * 
	 * @param pks the pks
	 * @param userName the user name
	 * @param chkVal the chk val
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public String serv_jycczmSh(String pks, String userName,String chkVal) throws Exception {
		SyxxDAO dao = new SyxxDAO();
		return dao.dao_jycczmSh(pks, userName,chkVal);
	}

	/**
	 * Ser_get xb list.
	 * 
	 * @return the list< hash map< string, string>>
	 * 
	 * @throws Exception the exception
	 */
	public List<HashMap<String, String>> ser_getXbList() throws Exception {
		SyxxDAO dao = new SyxxDAO();
		return dao.dao_getXbList();
	}
	
	/**
	 * Serv_get yh list.
	 * 
	 * @return the list< hash map< string, string>>
	 * 
	 * @throws Exception the exception
	 */
	public List<HashMap<String, String>> serv_getYhList() throws Exception {
		SyxxDAO dao = new SyxxDAO();
		return dao.dao_getYhList();
	}

	/**
	 * Gets the xsxx_ser.
	 * 
	 * @param xh the xh
	 * 
	 * @return the xsxx_ser
	 */
	public HashMap<String, String> getXsxx_ser(String xh) {
		SyxxDAO dao = new SyxxDAO();
		return dao.getXsxx(xh);
	}
	
	/**
	 * .数据导出
	 * 
	 * @param tabName the tab name
	 * @param response the response
	 * @param model the model
	 * 
	 * @throws Exception the exception
	 */
	public void dao_expData(String tabName,HttpServletResponse response,SyxxModel model) throws Exception {
		SyxxDAO dao = new SyxxDAO();
//		dao.dao_expData(tabName,response,model);

		String xm = model.getXm();
		String xh = model.getXh();
		
		StringBuffer query = new StringBuffer();
		String[] queryColList = {"xn","nd","xq","nj","xydm","zydm","bjdm"};
		query = FormModleCommon.makeQuery(queryColList, model);
		
		query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
		query.append(Base.isNull(xh) ? " and 1=1" : " and xh='"+xh.trim()+ "'");	
//		query.append(Base.isNull(xxsh) ? " and 1=1" : " and xxsh='"+xxsh.trim()+ "'");
		
		Vector<?> vec = new Vector<Object>();
		String sql = "";
//		if ("zjlg_xlcb".equals(tabName)) {
//			sql = "select xh,xm,nj,xb,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) xydm,(select distinct bjmc from view_njxyzybj b where a.bjdm=b.bjdm) bjdm,jg,nl,mz,sfdszn,csny,xlwtlx,qthbzf,jlbzf,yybzf,zbbzf,sjtsbzf,xxlzabzf,pzbzf,qpbzf,ynbzf,cdbzf,jsbqxbzf,wtgs,zxjy,zzjl from zjlg_xlcb a "+query.toString();
//		}else {
//			sql = "select * from "+tabName+query.toString();
//		}
		String[] outputValue = new String[]{"nd","xn","xqmc","nj","xh","xm","xb","xymc","zymc","bjmc","xxshsj","yzyh","yzzh","sfzh","zqsj","xxsh","xxshr"};
		sql = "select * from view_czxx_jycyzmb a "+query;
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("数据导出", 0);
		try {
//			String ColumnName[] = dao.getColumnName(sql);
//			String ColumnNameCN[] = dao.getColumnNameCN(ColumnName, tabName.toUpperCase());
//			String ColumnNameCN[] = dao.getColumnNameCN(outputValue, "view_czxx_jycyzmb");
			String[] ColumnNameCN = (String[]) dao.dao_expData(sql, outputValue, model).get("ColumnNameCN");
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
//			vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
//			vec.addAll(dao.rsToVator(sql, new String[] {}, outputValue));
			vec = (Vector<?>) dao.dao_expData(sql, outputValue, model).get("vec");
//			int k = ColumnName.length;
			int k = outputValue.length;
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
		/**
		 * Gets the xsxx_ser.
		 * 
		 * @param xh the xh
		 * 
		 * @return the xsxx_ser
		 */
		public List<HashMap<String, String>> getSydList() {
			SyxxDAO dao = new SyxxDAO();
			return dao.getSydList();
		}
}
