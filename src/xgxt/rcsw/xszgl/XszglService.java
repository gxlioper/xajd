package xgxt.rcsw.xszgl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.exception.SystemException;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.audit.AuditUtil;
import xgxt.comm.CommDAO;
import xgxt.comm.CommService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.rcsw.RcswForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.zgmsxy.XszzZgmsxyActionForm;

public class XszglService extends CommService{
	
	private XszglDAO dao = new XszglDAO();
	
	
	
	/**
	 * ѧ��֤����ɾ��
	 * @param pkValues
	 * @return
	 */
	public boolean delXszbb(String[] pkValues){
		
		if (null != pkValues && pkValues.length>0){
			try {
				return dao.delXszbb(pkValues);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	/**
	 * ѧ��֤�����ѯ
	 * @param model
	 * @param gnmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXszbb(RcswForm model,String[] colList,String[] gwmc,HttpServletRequest request) throws Exception{
		
		if (null != gwmc && gwmc.length>0){
			String[] tempShzt = new String[gwmc.length]; 
			
			User user = getUser(request);
			StringBuilder query = new StringBuilder();
			for (int i = 0 ; i < gwmc.length ; i++){
				String gwmcValue = request.getParameter(gwmc[i]);
				tempShzt[i] = gwmc[i]+":"+gwmcValue;
				if (StringUtils.isNotNull(gwmcValue)){
					query.append(" and ").append(gwmc[i]).append("='").append(gwmcValue).append("' ");
				}
			}
			request.setAttribute("tempShzt", Arrays.toString(tempShzt));
			return dao.queryXszbb(user,model,StringUtils.joinStrArr(colList,gwmc),gwmc,query.toString());
		} else{
			throw new SystemException("Error-1023");
		}
	}
	
	
	
	
	/**
	 * �������ѧ��֤����
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public boolean plshXszbb(RcswForm model,String[] pkValues,String gnmc) throws SQLException{
		
		String nextGwmc = "";
		String[] gwmc = AuditUtil.getGwmcByGnmc(gnmc);
		
		if (null != gwmc && gwmc.length>0){
			int index = StringUtils.getStrIndexInArray(model.getShgw(),gwmc);
			
			if (index > -1 && index != gwmc.length-1){
				nextGwmc = gwmc[index+1];//��һ��������λ
			}
		}
		
		if (null != pkValues && pkValues.length>0 && StringUtils.isNotNull(gnmc)){
			try {
				
				if (StringUtils.isNotNull(nextGwmc) && "ͨ��".equals(model.getShjg())){
					//�������һ��������λΪ�˻ص���Ҫ����Ϊ��δ��ˡ�
					return dao.plshXszbb(model, pkValues, gnmc) && dao.resetAfterShzt(model, pkValues, gnmc, nextGwmc);
				} else {
					return dao.plshXszbb(model, pkValues, gnmc) ;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		return false;
	}
	
	
	/**
	 * ѧ��֤������˲�ѯ
	 * @param model
	 * @param colList
	 * @param gwmc ��ǰ������ϵ����и�λ
	 * @param shgw ��Ҫ��˵ĸ�λ
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,Object> queryXszbbsh(User user,RcswForm model,String[] colList,String[] topTr,String[] gwmc) throws Exception{
		
		String shgw = model.getShgw();
		String beforeGwmc="";
		String afterGwmc ="";
		int index = StringUtils.getStrIndexInArray(shgw,gwmc);
		
		if (index > 0){
			beforeGwmc = gwmc[index-1];
		}
		
		if (index > -1 && index != gwmc.length-1){
			afterGwmc = gwmc[index+1];//��һ��������λ
		}
		
		if (StringUtils.isNotNull(shgw)){
			colList = StringUtils.joinStrArr(colList,new String[]{shgw});
			topTr = StringUtils.joinStrArr(topTr,new String[]{shgw});
		}
		
		if (StringUtils.isNotNull(afterGwmc)){
			colList = StringUtils.joinStrArr(colList,new String[]{afterGwmc});
			topTr = StringUtils.joinStrArr(topTr,new String[]{afterGwmc});
		}
		
		List<String[]> result = dao.queryXszbbsh(user,model,colList, gwmc, beforeGwmc,afterGwmc);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("result", result);
		map.put("topTr", topTr);
		return map;
	}
	
	
	/**
	 * ѧ��֤���������Ϣ
	 * @param pkValue
	 * @param gnmc
	 * @return
	 */
	public List<HashMap<String,String>> getXszbbShxx(String pkValue,String gnmc){
		if (StringUtils.isNotNull(pkValue) && StringUtils.isNotNull(gnmc)){
			return dao.getXszbbShxx(pkValue, gnmc);
		}
		
		return null;
	}
	
	
	/**
	 * ��������ѧ��֤�����Ϣ
	 * @param xh
	 * @param sqsj
	 * @param spgw
	 * @return
	 * @throws SQLException
	 */
	public boolean saveXszbbsh(String xh,String sqsj,String gnmc){
		
		if (StringUtils.isNotNull(gnmc)){
			try {
				String[] spgw = AuditUtil.getSpgwByGnmc(gnmc);
				return dao.saveXszbbsh(xh, sqsj, spgw);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	/**
	 * ѧ��֤�����ѯ
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXszbl(RcswForm model) throws Exception{

		return dao.queryXszbl(model);
	}
	
	/**
	 * ѧ��֤ע��
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXszc(RcswForm model) throws Exception{

		return dao.queryXszc(model);
	}
	
	
	/**
	 * ȡ��ѧ��֤����(2011.12.23 qlj)
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delXszbl(RcswForm myForm) throws Exception {
		
		
		return dao.delXszbl(myForm);
	}
	
	/**
	 * ȡ��ѧ��֤ע��(2011.12.23 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delXszzc(RcswForm myForm) throws Exception {
		
		
		return dao.delXszzc(myForm);
	}
	
	/**
	 * ѧ��֤�����ѯ
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public boolean addXszbl(RcswForm model) throws Exception{
		
		return dao.addXszbl(model);
	}
	
	public boolean plXszzc(RcswForm model) throws Exception{
		
		return dao.plXszzc(model);
	}
	
	public boolean plXszzx(RcswForm model) throws Exception{
		
		return dao.plXszzx(model);
	}
	
	public boolean delXszzx(RcswForm model) throws Exception{
		
		return dao.delXszzx(model);
	}
	
	public List<HashMap<String, String>> getTopTr(String lx,RequestForm rForm) {

		DAO dao = DAO.getInstance();
		
		String[]en=null;
		String[]cn=null;
		if("xszffb".equalsIgnoreCase(lx)){
			 en =new String[] { "pkValue","xh","xm","nj","xymc","zymc","bjmc","jbrxm","ffsj","sfff"};
			 cn =new String[] { "����", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶", "����������",
				"����ʱ��","�Ƿ񷢷�" };
		}else if("xszzc".equalsIgnoreCase(lx)){
			 en =new String[] { "pkValue","xh","xm","nj","xymc","zymc","bjmc","czr","czsj","sfzc"};
			 cn =new String[] { "����", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶","������",
				"ע��ʱ��","�Ƿ�ע��" };
		}else if("xszzx".equalsIgnoreCase(lx)){
			 en =new String[] { "pkValue","xn","xq","xh","xm","nj","xymc","zymc","bjmc","zxyy","sfzx"};
			 cn =new String[] { "����", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶","ע��ԭ��","�Ƿ�ע��" };
		}
		rForm.setColList(en);
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ѧ��֤ע��(2011.12.23 qlj)
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addXszzc(RcswForm myForm) throws Exception {
		
		return dao.addXszzc(myForm);
	}
	
	/**
	 * ѧ��֤ע��(2011.12.23 qlj)
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addXszzx(RcswForm myForm) throws Exception {
		
		return dao.addXszzx(myForm);
	}
	
	/**
	 * ѧ��֤ע����ѯ
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXszzx(RcswForm model) throws Exception{
		return dao.queryXszzx(model);
	}
	
	/**
	 * ��ȡ�춯����б�
	 */
	public List<HashMap<String,String>>getYdlbList(){
		
		return dao.getYdlbList();
	}
	
	/**
	 * ��ȡѧ��֤ע����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String>getXszzcMap(RcswForm model){
		
		return dao.getXszzcMap(model);
	}
	
	/**
	 * ��ȡѧ��֤ע����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String>getXszzxMap(RcswForm model){
		
		return dao.getXszzxMap(model);
	}
	
	
	/**
	 * ��ȡ��������Ϣ
	 */
	public List<HashMap<String,String>>getJbrList(){
		DAO dao=DAO.getInstance();
		String sql = "select jbrgh,jbrxm from jbrxxb order by jbrgh";  //��������Ϣ��
		List<HashMap<String,String>>jbrList = dao.getList(sql, new String[] {}, new String[] {
				"jbrgh", "jbrxm" });
		return jbrList;
	}
	
}
