/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �й����ʴ�ѧ��Ԣ����SERVICE</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-8-6 ����04:56:31</p>
 */
package xgxt.xsgygl.zgdzdx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GyglZgdzdxService {
	/**
	 * �����ţ����Һţ���λ������
	 * @param myModel
	 * @return
	 * @throws SQLException
	 */
	public boolean serv_RoomAutoCreate(GyglZgdzdxModel myModel) throws SQLException{
		GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		return myDAO.dao_RoomAutoCreate(myModel);
	}
	public String[] serv_QshBpGzQrr(){
		GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		return myDAO.dao_QshBpGzQrr();
	}
	/**
	 * ������¥���ѯ
	 * @param lddm
	 * @return
	 */
	public List<String[]> serv_alreadyCTLCQrr(String lddm,String cs){
		GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		return myDAO.dao_alreadyCTLCQrr(lddm,cs);
	}
	public List<HashMap<String,String>> serv_getCTLCQrrTit(){
		GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		return myDAO.dao_getCTLCQrrTit();
	}
	public boolean serv_roomBatchDel(String delPk) throws Exception{
		GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		return myDAO.dao_roomBatchDel(delPk);
	}
	public HashMap<String,String> serv_roomInfoMod(String pkValue){
		GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		return myDAO.dao_roomInfoMod(pkValue);
	}
	public boolean serv_roomInfoModSave(GyglZgdzdxModel model) throws Exception{
		GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		return myDAO.dao_roomInfoModSave(model);
	}
	public boolean serv_roomInfoModRelatedSave(GyglZgdzdxModel model) throws Exception{
		GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		return myDAO.dao_roomInfoModRelatedSave(model);
	}
	/** 
	 * �Ƿ�ʿ��
	 * Method sfbss
	 * @param username �û���
	 * @return boolean
	 */
	 public boolean sfbss(String username){
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.sfbss(username);
	 }
	 /** 
	  * �Ƿ�����ס
	  * Method sfyrz
	  * @param username �û���
	  * @return boolean
	  */
	 public boolean sfyrz(String username){
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.sfyrz(username);
	 }
	 /** 
	  * ����Ա�
	  * Method getXb
	  * @param username �û���
	  * @return String
	  */
	 public String getXb(String username){
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.getXsXb(username);
	 }
	 /** 
	  * ƴ�Ĳ�ѯ����
	  * Method getCondition
	  * @param model GyglZgdzdxModel����
	  * @return String
	  */
	 public String getCondition(GyglZgdzdxModel model){
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.getCondition(model);
	 }
	 /** 
	  * ���������Ϣlist
	  * Method getSsxxList
	  * @param condition ��ѯ����
	  * @param xb �Ա�
	  * @return List<String[]>
	  */
	 public List<String[]> getSsxxList(String condition,String xb){
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.getSsxxList(condition,xb);
	 } 
	 /** 
	  * ��ò�ʿ��ͷ��Ϣlist
	  * Method getTopTr
	  * @return List<HashMap<String,String>>
	  */
	 public List<HashMap<String,String>> getTopTr(){
		 String[] en = new String[]{"����","xqmc","ldmc","cs","qsh","fpbj","sffqfj","cws","yrzrs","krzrs","sfbz"};
		 String[] cn = new String[]{"ѡ��","У������","¥������","����","���Һ�","������","�Ƿ���޷���","�ܹ���ס����",
				 "����ס����","������ס����","�շѱ�׼"};
		 List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		 for(int i=0;i<en.length;i++){
			 HashMap<String,String> map = new HashMap<String,String>();
			 map.put("en", en[i]);
			 map.put("cn", cn[i]);
			 list.add(map);
		 }
		 return list;
	 }
	 /** 
	  * ��ʿ����ѡ�����ύ
	  * Method saveDorm
	  * @param xh ѧ��
	  * @param ssbh ������
	  * @return ActionForward
	  * @throws Exception 
	  */
	 public boolean saveDorm(String xh,String ssbh) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.saveDorm(xh,ssbh);
	 }
	 /** 
	  * �鿴��½ѧ������ʿ����ס����
	  * Method viewYzInfo
	  * @param xh ѧ��
	  * @return HashMap<String,String>
	  */
	 public HashMap<String,String> viewYzInfo(String xh) {
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.viewYzInfo(xh);
	 }
	 public boolean serv_roomCompartSave(String newMappingItems,String oldMappingItems) throws SQLException{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.dao_roomCompartSave(newMappingItems,oldMappingItems);
	 }
	 public boolean  serv_roomCodeSave(String chsfbl,String fjhws) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.dao_roomCodeSave(chsfbl,fjhws);		 
	 }
	 /** 
	  * �������ֲ�ͼ��
	  * Method getDormImageWiew
	  * @param xqdm У������
	  * @param lddm ¥������
	  * @param qsh  ���Һ�
	  * @return List
	  */
	 public List getDormImageWiew(String xqdm,String yqdm,String lddm,String lc,String qsh,String nj,String xy,String userName) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.getDormImageWiew(xqdm,yqdm,lddm,lc,qsh,nj,xy,userName);
	 }
	 /** 
	  * ���ĳ���������Ϣ
	  * Method getOneSsInfo
	  * @param ssbh ������
	  * @return HashMap
	  */
	 public HashMap getOneSsInfo(String ssbh) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.getOneSsInfo(ssbh);
	 }
	 /** 
	  * ����ĳ���������Ϣ
	  * Method saveInfo
	  * @param myModel GyglZgdzdxModel����
	  * @return boolean
	  */
	 public boolean saveInfo(GyglZgdzdxModel myModel) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.saveInfo(myModel);
	 }
	 /** 
	  * У����Ϣ
	  * Method validateInfo
	  * @param String xh ѧ��
	  * @param String strSysDatetime ����ʱ��
	  * @param String verify ��֤
	  * @return int
	  */
	 public int validateInfo(String xh,String strSysDatetime,String verify)
	 throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.validateInfo(xh,strSysDatetime,verify);
	 }
	 /** 
	  * ���ĳ��ѧ���������������Ϣ
	  * Method viewSsInfo
	  * @param xh ѧ��
	  * @param tableName �����ͼ��
	  * @return HashMap
	  */
	 public HashMap viewSsInfo(String xh,String tableName) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.viewSsInfo(xh,tableName);
	 }

	 public boolean serv_bedCompartSave(String newMappingItems,String oldMappingItems) throws SQLException{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.dao_bedCompartSave(newMappingItems,oldMappingItems);
	 }

	 public String[] getStuInF(String userName,String xh){
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();    	 
		 return myDAO.dao_getStuInF(userName,xh);
	 }
	 public boolean serv_yqManagerAdd(GyglYqManagerModel model) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_yqManagerAdd(model);
	 }
	 /**԰������Ա��ѯ�����ͷ*/
	 public ArrayList<HashMap<String, String>>  getyqManagerTitle(){
		 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO(); 
		 result  = myDAO.dao_getyqManagerTitle();
		 return result;
	 }
	 /**԰������Ա��ѯ���ؽ��*/
	 public ArrayList<String[]>  getyqManagerResult(String yqdm,String xm,String sfzz){
		 ArrayList<String[]> result = new ArrayList<String[]>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 result =  myDAO.dao_getyqManagerResult(yqdm,xm,sfzz);
		 return result;
	 }
	 public HashMap<String,String> yqManagerInfo(String pkValue) {
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.dao_yqManagerInfo(pkValue);
	 }
	 public boolean serv_yqManagerModi(GyglYqManagerModel model,String pkValue) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_yqManagerModi(model,pkValue);
	 }
	 public boolean serv_yqManagerDel(String pkValue) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_yqManagerDel(pkValue);
	 }
	 public boolean serv_ldManagerAdd(GyglLdManagerModel model) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_ldManagerAdd(model);
	 }
	 /**¥����ѯ�����ͷ*/
	 public ArrayList<HashMap<String, String>>  getldManagerTitle(){
		 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO(); 
		 result  = myDAO.dao_getldManagerTitle();
		 return result;
	 }
	 /**¥����ѯ���ؽ��*/
	 public ArrayList<String[]>  getldManagerResult(GyglLdManagerModel model ){
		 ArrayList<String[]> result = new ArrayList<String[]>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 result =  myDAO.dao_getldManagerResult(model);
		 return result;
	 }
	 public boolean serv_ldManagerModi(GyglLdManagerModel model,String pkValue) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_ldManagerModi(model,pkValue);
	 }
	 public HashMap<String,String> ldManagerInfo(String pkValue) {
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.dao_ldManagerInfo(pkValue);
	 }
	 public boolean serv_ldManagerDel(String pkValue) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_ldManagerDel(pkValue);
	 }
	 public boolean serv_lcManagerAdd(GyglLcManagerModel model) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_lcManagerAdd(model);
	 }
	 /**�㳤��ѯ�����ͷ*/
	 public ArrayList<HashMap<String, String>>  getlcManagerTitle(){
		 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO(); 
		 result  = myDAO.dao_getlcManagerTitle();
		 return result;
	 }
	 /**�㳤��ѯ���ؽ��*/
	 public ArrayList<String[]>  getlcManagerResult(GyglLcManagerModel model ){
		 ArrayList<String[]> result = new ArrayList<String[]>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 result =  myDAO.dao_getlcManagerResult(model);
		 return result;
	 }
	 public HashMap<String,String> lcManagerInfo(String pkValue) {
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.dao_lcManagerInfo(pkValue);
	 }
	 public boolean serv_lcManagerModi(GyglLcManagerModel model,String pkValue) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_lcManagerModi(model,pkValue);
	 }
	 public boolean serv_lcManagerDel(String pkValue) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_lcManagerDel(pkValue);
	 }
	 public boolean serv_yqzbManagerAdd(GyglYqzbModel model) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_yqzbManagerAdd(model);
	 }
	 /**԰��ֵ���ѯ�����ͷ*/
	 public ArrayList<HashMap<String, String>>  getyqzbManageTitle(){
		 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO(); 
		 result  = myDAO.dao_getyqzbManageTitle();
		 return result;
	 }
	 /**԰��ֵ���ѯ���ؽ��*/
	 public ArrayList<String[]>  getyqzbManageResult(GyglYqzbModel model ){
		 ArrayList<String[]> result = new ArrayList<String[]>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 result =  myDAO.dao_getyqzbManageResult(model);
		 return result;
	 }
	 public HashMap<String,String> yqzbManageInfo(String pkValue) {
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.dao_yqzbManageInfo(pkValue);
	 }
	 public boolean serv_yqzbManageModi(GyglYqzbModel model,String pkValue) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_yqzbManageModi(model,pkValue);
	 }
	 public boolean serv_yqzbManageDel(String pkValue) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_yqzbManageDel(pkValue);
	 }
	 public boolean serv_yqhdManagerAdd(GyglYqhdModel model) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_yqhdManagerAdd(model);
	 }
	 /**԰�����ѯ�����ͷ*/
	 public ArrayList<HashMap<String, String>>  getyqhdManageTitle(){
		 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO(); 
		 result  = myDAO.dao_getyqhdManageTitle();
		 return result;
	 }
	 /**԰�����ѯ���ؽ��*/
	 public ArrayList<String[]>  getyqhdManageResult(GyglYqhdModel model ){
		 ArrayList<String[]> result = new ArrayList<String[]>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 result =  myDAO.dao_getyqhdManageResult(model);
		 return result;
	 }
	 public HashMap<String,String> yqhdManageInfo(String pkValue) {
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.dao_yqhdManageInfo(pkValue);
	 }
	 public boolean serv_yqhdManageModi(GyglYqhdModel model,String pkValue) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_yqhdManageModi(model,pkValue);
	 }
	 public boolean serv_yqhdManageDel(String pkValue) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_yqhdManageDel(pkValue);
	 }
	 public List<HashMap<String,String>>  serv_getWxsSSList(String xb) {
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.getWxsSSList(xb);
	 }
	 public List<HashMap<String,String>>  serv_getWxsCwList(String ssbh){
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.getWxsCwList(ssbh);
	 }
	 public boolean serv_wxsDormUserAdd(GyglWxsDormUModel model) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_wxsDormUserAdd(model);
	 }
	 /**��У��ס����Ϣ��ѯ�����ͷ*/
	 public ArrayList<HashMap<String, String>>  getWxsDUManageTitle(){
		 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO(); 
		 result  = myDAO.dao_getWxsDUManageTitle();
		 return result;
	 }	 
	 /**��У��ס����Ϣ��ѯ���*/
	 public ArrayList<String[]>  getWxsDUManageResult(GyglWxsDormUModel model ){
		 ArrayList<String[]> result = new ArrayList<String[]>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 result =  myDAO.dao_getWxsDUManageResult(model);
		 return result;
	 }
	 public HashMap<String,String> wxsDormUserInfo(String pkValue) {
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.dao_wxsDormUserInfo(pkValue);
	 }
	 public boolean serv_wxsDormUserModi(GyglWxsDormUModel model,String pkValue) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();   	  
		 return myDAO.dao_wxsDormUserModi(model,pkValue);
	 }
	 public boolean serv_wxsDormUserDel(String toHistory,String delPk,String tssj) throws SQLException{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO(); 
		 return myDAO.dao_wxsDormUserDel(toHistory,delPk,tssj);
	 }
	 /**��Ա���Ҳ�ѯ�����ͷ*/
	 public ArrayList<HashMap<String, String>>  getdyDManageTitle(){
		 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO(); 
		 result  = myDAO.dao_getdyDManageTitle();
		 return result;
	 }
	 /**��Ա���Ҳ�ѯ���*/
	 public ArrayList<String[]>  getdyDManageResult(String xqdm,String yqdm,String lddm ){
		 ArrayList<String[]> result = new ArrayList<String[]>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 result =  myDAO.dao_getdyDManageResult(xqdm,yqdm,lddm);
		 return result;
	 }
	 public boolean serv_jswmhdSqSave(GyglJswmhdModel model,String userName) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return   myDAO.dao_jswmhdSqSave(model, userName);
	 }
	 public ArrayList<String[]> ser_hdShMResult(GyglJswmhdModel model,String userType){
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return   myDAO.dao_hdShMResult(model, userType);
	 }
	 public ArrayList<HashMap<String, String>> ser_hdShMTitle(String userType){
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return   myDAO.dao_hdShMTitle(userType);
	 }
	 public HashMap<String,String> ser_jswmhdShInfo(String pkValue){
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return   myDAO.dao_jswmhdShInfo(pkValue);
	 }
	 public boolean ser_jswmhdSh(String rid,String userType,String shValue) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return   myDAO.dao_jswmhdSh(rid,userType,shValue);
	 }
	 //������������ܷ��ж�
	 public boolean ser_jswmhdShJudge(String userType,String rid){
		 boolean czyf=false;
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 if("xy".equalsIgnoreCase(userType)){
			 czyf = myDAO.getjswmShJudge("xxsh", rid);
		 }
		 return czyf;
	 }
	 public ArrayList<HashMap<String, String>>  getjswmhdMTitle(){
		 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO(); 
		 result  = myDAO.dao_jswmhdMTitle();
		 return result;
	 }
	 public ArrayList<String[]>  ser_jswmhdMResult(GyglJswmhdModel model){
		 ArrayList<String[]> result = new ArrayList<String[]>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 result =  myDAO.dao_jswmhdMResult(model);
		 return result;
	 }
	 public boolean ser_jswmhdSave(GyglJswmhdModel model,String userName) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return  myDAO.dao_jswmhdSave(model,userName);
	 }
	 public HashMap<String,String> ser_getJsWmHdInfo(String pkValue){
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return  myDAO.getJsWmHdInfo(pkValue);
	 }
	 public boolean ser_jswmhdModi(GyglJswmhdModel model,String pkValue) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return  myDAO.dao_jswmhdModi(model, pkValue);
	 }
	 public boolean ser_jswmhdDel(String pkValue) throws Exception{
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return  myDAO.dao_jswmhdDel(pkValue);
	 }
	 public ArrayList<HashMap<String, String>>ser_FeeChgTitle(){
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return   myDAO.dao_FeeChgTitle();
	 }
	 public ArrayList<String[]>  serv_FeeChgResult(GyglZsfBgTjModel model){
		 ArrayList<String[]> result = new ArrayList<String[]>();
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 result =  myDAO.dao_FeeChgResult(model);
		 return result;
	 }
	 public List<HashMap<String, String>>ser_getGyFdyCsList(String lddm){
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return   myDAO.dao_getGyFdyCsList(lddm);
	 }
	 public List<HashMap<String, String>>  ser_getGyFdySsList(String lddm){
		 GyglZgdzdxDAO myDAO = new GyglZgdzdxDAO();
		 return myDAO.dao_getGyFdySsList(lddm);		
	 }
}
