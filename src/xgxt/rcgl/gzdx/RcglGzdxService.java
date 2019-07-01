package xgxt.rcgl.gzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class RcglGzdxService {
	
	//获得表头
	public List<HashMap<String,String>> getTableTop(String lb){
		String[] ens = null;
		String[] cns = null;
		String xxdm=Base.xxdm;
		DAO dao = DAO.getInstance();
		if("yysh".equals(lb)){
			ens = new String[]{"guid","bmmc","cdmc","yyrq","yysjd","fzr","lxdh","sqr","xxsh"};
			cns = new String[]{"guid","使用部门","预约日期","预约时间段","负责人","联系电话","申请人","学校审核"};
			if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
				ens = new String[]{"guid","bmmc","cdmc","sqsj","yyrq","yysjd","fzr","lxdh","sqr","shpf","xxsh"};
				cns = new String[]{"guid","使用部门","申请时间","预约日期","预约时间段","负责人","联系电话","申请人","审核批复","学校审核"};
			}
		}else if("zbry".equals(lb)){
			ens = new String[]{"xh","xh","xm","xb","xymc","zymc","bjmc","bz"};
			cns = new String[]{"xh","学号","姓名","性别",Base.YXPZXY_KEY+"名称","专业名称","班级名称","备注"};
		}else if("xsgly".equals(lb)){
			ens = new String[]{"xh","xh","xm","xb","xymc","zymc","bjmc","sfqy","bz"};
			cns = new String[]{"xh","学号","姓名","性别",Base.YXPZXY_KEY+"名称","专业名称","班级名称","是否启用","备注"};
		}else if("zbsz".equals(lb)){
			ens = new String[]{"guid","bmmc","cdmc","yyrq","yysjd","fzr","lxdh","zbry","zbdh"};
			cns = new String[]{"guid","使用部门","场地名称","预约日期","预约时间段","负责人","联系电话","值班人员","值班电话"};
		}
		
		return dao.arrayToList(ens, cns);
	}
	
	/**
	 *获得部门列表
	 */
	public List<HashMap<String, String>> getXxbm_ser(){
		DAO dao = DAO.getInstance();
		return dao.getBmList();
	}
	
	/**
	 *获得预约资源场地
	 */
	public List<HashMap<String, String>> getZyyycd_ser(){
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.getZyyycd_db();
	}
	
	/**
	 *获得预约设备
	 */
	public List<HashMap<String, String>> getZyyysb_ser(){
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.getZyyysb_db();
	}
	
	/**
	 *保存资源申请
	 */
	public boolean saveZyyysq_ser(RcglGzdxForm model,String userType,String userName){
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.saveZyyysq_db(model,userType,userName);
	}
	
	/**
	 *获得小时list
	 */
	public List<HashMap<String, String>> getHours_ser(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for(int i=1;i<=24;i++){
			HashMap<String, String> map = new HashMap<String, String>();
			if(i<10){
				map.put("dm", "0"+i);
			}else{
				map.put("dm", ""+i);
			}
			list.add(map);
		}
		return list;
	}
	
	
	/**
	 * 2010.9.20 qlj
	 * 判断场地是否
	 * 可以被借出
	 */
	public boolean checkCdkj(RcglGzdxForm model){
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.checkCdkj(model);
	}
	
	/**
	 * 2010.9.20 qlj
	 * 判断场地是否
	 * 可以被借出
	 */
	public boolean checkSbkj(RcglGzdxForm model){
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.checkSbkj(model);
	}
	
	/**
	 *获得分钟list
	 */
	public List<HashMap<String, String>> getMinutes_ser(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for(int i=0;i<=50;i=i+10){
			HashMap<String, String> map = new HashMap<String, String>();
			if(i<10){
				map.put("dm", "0"+i);
			}else{
				map.put("dm", ""+i);
			}
			list.add(map);
		}
		return list;
	}
	
	/**
	 *预约审核
	 */
	public boolean zyyySh_ser(String pkvals,String xxsh) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.zyyySh_db(pkvals, xxsh);
	}
	
	/**
	 *广州大学
	 *预约审核
	 */
	public boolean zyyySh_gzdx(String pkvals,String xxsh,String shpf) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		//审核结果为通过
		if("tg".equalsIgnoreCase(xxsh)){
			if(dao.shCheck(pkvals)){
				return dao.zyyySh_gzdx(pkvals, xxsh,shpf);
			}else{
				return false;
			}
		}else{
			return dao.zyyySh_gzdx(pkvals, xxsh,shpf);
		}
	}
	
	/**
	 *查询预约审核数据
	 */
	public List<HashMap<String, String>> zyyyShQuery_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.zyyyShQuery_db(model);
	}
	
	/**
	 *获得单个预约信息
	 */
	public HashMap<String, String> getOneYyxx_ser(String pkval) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.getOneYyxx_db(pkval);
	}
	
	/**
	 *修改单个预约信息
	 */
	public boolean updateOneYyxx_ser(String pkval,RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.updateOneYyxx_db(pkval,model);
	}
	
	/**
	 *删除预约信息
	 */
	public boolean deleteYyxx_ser(String pkvals) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.deleteYyxx_db(pkvals);
	}
	
	/**
	 *检查是否为学生管理员
	 */
	public String isXsgly_ser(String xh) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.isXsgly_db(xh);
	}
	
	/**
	 *查询值班人员
	 */
	public List<HashMap<String, String>> queryZbry_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.queryZbry_db(model);
	}
	
	/**
	 *删除值班人员
	 */
	public boolean deleteZbry_ser(String pkvals) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.deleteZbry_db(pkvals);
	}
	
	/**
	 *获得某值班人员信息
	 */
	public HashMap<String,String> getOneZbryxx_ser(String xh) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.getOneZbryxx_db(xh);
	}
	
	/**
	 *修改某值班人员信息
	 */
	public boolean updateOneZbryxx_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.updateOneZbryxx_db(model);
	}
	
	/**
	 *增加某值班人员信息
	 */
	public boolean addOneZbryxx_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.addOneZbryxx_db(model);
	}
	
	/**
	 *获得某学生信息
	 */
	public HashMap<String,String> getOneXsxx_ser(String xh) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.getOneXsxx_db(xh);
	}
	
	/**
	 *设置值班人员
	 */
	public boolean zbrysz_ser(String pkvals,String guid) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.zbrysz_db(pkvals,guid);
	}
	
	/**
	 *查询值班人员
	 */
	public List<HashMap<String, String>> queryZbrysz_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.queryZbrysz_db(model);
	}
	
	/**
	 *删除学生管理员
	 */
	public boolean deleteXsgly_ser(String pkvals) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.deleteXsgly_db(pkvals);
	}
	
	/**
	 *查询学生管理员
	 */
	public List<HashMap<String, String>> queryXsgly_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.queryXsgly_db(model);
	}
	
	/**
	 *增加学生管理员信息
	 */
	public boolean addOneXsglyxx_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.addOneXsglyxx_db(model);
	}
	
	/**
	 *修改学生管理员信息
	 */
	public boolean updateOneXsglyxx_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.updateOneXsglyxx_db(model);
	}
	
	/**
	 *获得学生管理员信息
	 */
	public HashMap<String,String> getOneXsglyxx_ser(String xh) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.getOneXsglyxx_db(xh);
	}
	
	/**
	 * 通知公告显示
	 * */
	public String getHkxz_ser(String lb) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.getTzgg_db(lb);
	}
}
