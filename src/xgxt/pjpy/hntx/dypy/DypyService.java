package xgxt.pjpy.hntx.dypy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;


/**
 * Title: 学生工作管理系统 Description:德育评议service
 * Copyright: Copyright (c)
 * 2010 Company: zfsoft Author: sjf Version: 1.0 Time: 2010-8-17
 */
public class DypyService extends BasicExtendService{
	
	
	
//	PUBLIC BOOLEAN INSERTDYPY(DYPYMODEL MODEL){
//		RETURN SAVEDYPY(MODEL) && SAVEDYPYZFB(MODEL);
//	}
	
	/**
	 * 保存德育评育成绩
	 * @param model
	 * @return
	 */
	public boolean saveDypy(DypyModel model){
		boolean flag = false;
		
		
		String pk = "xh||xn";
		String[] pkValue = new String[]{model.getXh()+model.getXn()};
		
		String[] onezd = new String[]{"xh","xn"};
		String[] arrzd = new String[]{"xmdm", "xmjf"};
	
		SaveForm saveForm = new SaveForm();
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		
		saveForm.setTableName("hndx_dypyxxb");
		
		DAO dao = DAO.getInstance();
		
		try {
			flag = dao.saveData(saveForm, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public boolean updateDypy(String pkValue, String xmjf){
		boolean flag = false;
		DAO dao = DAO.getInstance();
		
		String sql = "update hndx_dypyxxb set xmjf=? where xh||xn||xmdm=?";
		try {
			flag = dao.runUpdate(sql, new String[]{xmjf, pkValue});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public List<HashMap<String, String>> getDypyzf(String xn){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xh,sum(to_number(Nvl((select b.bl from hndx_dypyxmb b where b.xmdm=a.xmdm),0))*0.01*a.xmjf) dyzf")
			.append(" from hndx_dypyxxb a").append(" where xn=? group by xh");
		
		DAO dao = DAO.getInstance();
		
		return dao.getList(sql.toString(),new String[]{xn}, new String[]{"xh", "dyzf"});
		
	}
	
	/**
	 * 获得单个德育评育信息
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getDypyInfo(String pkValue){
		String[] colList = new String[]{"xh","xm","xb","xymc","zymc","bjmc","xq","xmdm","xmmc","xmjf","nd","xn"};
		return CommonQueryDAO.commonQueryOne("view_hndx_dypyxxb", colList, "xh||xn||xmdm", pkValue);
	}
//	public boolean saveDypyzfb(DypyModel model){
//		boolean flag = false;
//		double zf = 0;
//		String[] xmjfs = model.getXmjf();
//		String[] bls = model.getBl();
//		
//		
//		for(int i=0; i<xmjfs.length; i++){
//			int jf = 0;
//			double bl = 0;
//			if(StringUtils.isNotNull(xmjfs[i]) ){
//				jf = Integer.parseInt(xmjfs[i]);
//			}
//			
//			if(StringUtils.isNotNull(bls[i])){
//				bl = Double.parseDouble(bls[i]) * 0.01;
//			}
//			
//			zf += jf*bl;
//		}
//		
//		zf = Math.rint(zf);
//		
//		StringBuilder delSql = new StringBuilder();
//		delSql.append("delete from hndx_dypyzfb").append(" where xh='").append(model.getXh())
//			.append("' and xn='").append(model.getXn()).append("'");
//		
//		StringBuilder insSql = new StringBuilder();
//		insSql.append("insert into hndx_dypyzfb").append(" values('").append(model.getXh())
//			.append("','").append(model.getXn()).append("','").append(zf).append("')");
//
//		String[] sqlArr = new String[]{delSql.toString(), insSql.toString()};
//
//		DAO dao = DAO.getInstance();		
//		try {
//			dao.runBatch(sqlArr);
//			flag = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return flag;
//	}
}
