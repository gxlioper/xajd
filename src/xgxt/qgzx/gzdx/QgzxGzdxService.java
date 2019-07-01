package xgxt.qgzx.gzdx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.basic.BasicDAO;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.qgzx.QgzxTyForm;
import xgxt.qgzx.QgzxTyService;
import xgxt.utils.CommonQueryDAO;

public class QgzxGzdxService extends QgzxTyService {

	QgzxGzdxDAO dao = new QgzxGzdxDAO();
	
	/**
	 * 保存临时岗位
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveLsgw(QgzxTyForm model, String table) throws Exception {

		String[] arrzd = new String[] { "lsgwmc", "lsgwsbsj", "qgxh" };
		String[] onezd = new String[] { "xn", "xq", "nd", "gwsqsj" };

		String pk = "xn||nd||xq||lsgwmc||lsgwsbsj||qgxh";
		String[] pkValue = null;
		String[] lsgwmc = null;
		String[] lsgwsbsj = null;
		String[] qgxh = null;
		
		// 学年
		String xn = model.getXn();
		// 学年
		String xq = model.getXq();
		xq = Base.isNull(xq) ? "无" : xq;
		// 年度
		String nd = model.getNd();
		// 申请时间
		String gwsqsj = getNowTime("YYYYMMDD");
		// 临时岗位名称
		String[] gwmc = model.getLsgwmc();
		// 临时岗位申报时间
		String[] sbsj = model.getLsgwsbsj();
		// 勤工学号
		String[] checkVal = model.getPrimarykey_checkVal();

		// 构建主键
		if (checkVal != null && checkVal.length > 0 && gwmc != null
				&& gwmc.length > 0) {

			pkValue = new String[checkVal.length * gwmc.length];
			lsgwmc = new String[checkVal.length * gwmc.length];
			lsgwsbsj = new String[checkVal.length * gwmc.length];
			qgxh = new String[checkVal.length * gwmc.length];
			
			int n = 0;

			for (int i = 0; i < checkVal.length; i++) {

				// 学号
				String xh = checkVal[i];

				for (int j = 0; j < gwmc.length; j++) {
					pkValue[n] = xn + nd + xq + gwmc[j] + sbsj[j] + xh;
					lsgwmc[n] = gwmc[j];
					lsgwsbsj[n] = sbsj[j];
					qgxh[n] = xh;
					n++;
				}
			}
		}

		model.setLsgwmc(lsgwmc);
		model.setLsgwsbsj(lsgwsbsj);
		model.setQgxh(qgxh);
		model.setGwsqsj(gwsqsj);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return saveQgzx(saveForm, model);
	}
	
	/**
	 * 查询表头信息
	 * @param tableName
	 * @param outputValue
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTopTr(String tableName, 
			                                      String[] outputValue){
		DAO dao = DAO.getInstance();
		String[] colCN = dao.getColumnNameCN(outputValue, tableName);
		return dao.arrayToList(outputValue, colCN);
	}
	
	/**
	 * 查询用工部门学生信息列表
	 * @param model
	 * @param output
	 * @return List<HashMap<String, String>>
	 * */
	public List<String[]> queryYgbmxsList(QgzxTyForm model, String[] output){
		return dao.selectYgbmxsList(model, output);
	}
	
	/**
	 * 查询用工部门学生信息列表(用人单位)
	 * @param model
	 * @param output
	 * @return List<HashMap<String, String>>
	 * */
	public List<String[]> queryYgbmxs(QgzxTyForm model, String[] output){
		return dao.selectYgbmxs(model, output);
	}
	
	/**
	 * 保存勤工助学积极份子上报信息
	 * @param model
	 * @return boolean
	 * */
	public boolean saveQgzxjjfzsb(QgzxTyForm model){
		return dao.saveQgzxjjfzsb(model);
	}
	
	/**
	 * 获取审核查询中要自定义的字段
	 * @param yhlx
	 * @return String
	 * */
	public String getCustomAudiColumn(String yhlx){
		StringBuilder sb = new StringBuilder();
		if (yhlx.equalsIgnoreCase("xy")) {
			sb.append(" (case when xxsh='未审核' then '' else 'disabled' end) disabled,");
			sb.append(" (case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		}else if (yhlx.equalsIgnoreCase("fdy")){
			sb.append(" (case when xysh='未审核' then '' else 'disabled' end) disabled, ");
			sb.append(" (case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		}else{
			sb.append(" '' disabled, ");
			sb.append(" (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取列表数据
	 * @param int num
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getChkList(int num){
		DAO dao = DAO.getInstance();
		return dao.getChkList(num);
	}
	
	/**
	 * 2010.9.3 qlj
	 * 违纪处分次数
	 */
	public String getWjcs(String xn,String xh){
		DAO dao=DAO.getInstance();
		String sql="select count(xh)wjcs from wjcfb where xn=? and xh=?";
		List<HashMap<String,String>>list=dao.getList(sql,new  String[]{xn,xh}, new String[]{"wjcs"});
		String wjcs="";
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			wjcs=hashMap.get("wjcs");
		}else{
			wjcs="0";
		}
		return wjcs;
	}
	
	/**
	 * 2010.9.9 qlj
	 * 广州大学综合测排名
	 * xn取当前评奖XN
	 */
	public String getZcpm(String xn,String xh){
		DAO dao=DAO.getInstance();
		String sql="select bjpm zcpm from view_gzdx_zhszcp where xn=? and xh=?";
		List<HashMap<String,String>>list=dao.getList(sql,new  String[]{xn,xh}, new String[]{"zcpm"});
		String zcpm="";
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			zcpm=hashMap.get("zcpm");
		}else{
			zcpm="没有排名信息";
		}
		return zcpm;
	}
	
	
	/**
	 * 2010.9.3 qlj
	 * 不及格科目
	 */
	public String getBjgkms(String xn,String xh){
		DAO dao=DAO.getInstance();
		String sql="select count(xh)bjgkms from cjb where  cj<60  and xn=? and xh=?";
		List<HashMap<String,String>>list=dao.getList(sql,new  String[]{xn,xh}, new String[]{"bjgkms"});
		String bjgkms="";
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			bjgkms=hashMap.get("bjgkms");
		}else{
			bjgkms="0";
		}
		return bjgkms;
	}
	
	/**
	 * 2010.9.14 qlj
	 * 学生申请的勤工助学岗位
	 */
	public String getQgzxgw(String xh){
		DAO dao=DAO.getInstance();
		String sql="select * from qgzxsqb where xh=? and  zdgwdm is not null";
		StringBuilder zdgwdm=new StringBuilder();
		List<HashMap<String,String>>list=dao.getList(sql, new String[]{xh}, new String[]{"zdgwdm"});
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				HashMap<String,String>hashMap=list.get(i);
				if(i!=0){
					zdgwdm.append("、");
					zdgwdm.append(hashMap.get("zdgwdm"));
				}else{
					zdgwdm.append(hashMap.get("zdgwdm"));
				}
			}
		}else{
			zdgwdm.append("无");
		}
		return zdgwdm.toString();
	}
	
	/**
	 * 获取用人单位列表
	 * 
	 */
	public List<HashMap<String,String>>getYrdwList(){
		DAO dao=DAO.getInstance();
		String sql="select yrdwdm dm,yrdwmc mc from yrdwdmb";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
}
