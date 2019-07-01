/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-10-10 ����09:31:23</p>
 */
package xgxt.pjpy.jhzy.rych;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.pjpy.jhzy.JhzyPjpyDAO;

public class RychDAO extends JhzyPjpyDAO {
	
	/**
	 * ʡ�������ҵ�������ƺ����뱣��
	 * @throws Exception 
	 */
	public boolean sjyxbysSqSave(sjyxbysRychModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String xn = DealString.toGBK(model.getXn());
		String xh = DealString.toGBK(model.getXh());
		String brjl = DealString.toGBK(model.getBrjl());
		String zysj = DealString.toGBK(model.getZysj());
		String zxqjhjqk = DealString.toGBK(model.getZxqjhjqk());
		String bz   = DealString.toGBK(model.getBz());
		boolean done = false;
		String flag = dao.returntag("select * from jhzy_sjyxbysb where xh||xn=?", new String[]{xh+xn});
		String sql= "";
		if("empty".equalsIgnoreCase(flag)){
			sql="insert into jhzy_sjyxbysb(xn,xh,brjl,zysj,zxqjhjqk,bz)values(?,?,?,?,?,?)";
			done = dao.runUpdate(sql,new String[]{xn,xh,brjl,zysj,zxqjhjqk,bz});
		}else{
			sql="update jhzy_sjyxbysb set brjl=?,zysj=?,zxqjhjqk=?,bz=?,fdysh='δ���',fdyshyj='',xysh='δ���',xyshyj='',xxsh='δ���',xxshyj='' where xh||xn=? ";
			done = dao.runUpdate(sql,new String[]{brjl,zysj,zxqjhjqk,bz,xh+xn});			
		}
		return done;
	}
	/**
	 * ʡ�������ҵ����˲�ѯ
	 */
	public  ArrayList<String[]> getSjyxbysList(String userType,String userName, sjyxbysRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		// ѧԺ����
		String xydm = DealString.toGBK(model.getXydm());
		// רҵ����
		String zydm = DealString.toGBK(model.getZydm());
		// �༶����
		String bjdm = DealString.toGBK(model.getBjdm());
		// �꼶
		String nj = DealString.toGBK(model.getNj());
		// ѧ��
		String xn = DealString.toGBK(model.getXn());
		// ѧ��
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		// ����
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		String fdyQuerry = "isFdy".equalsIgnoreCase(userType) ? " and bjdm in (select bjdm from fdybjb b where zgh like '"+ userName + "' )":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");	
		if("xy".equalsIgnoreCase(userType)){
			query.append(" and fdysh = 'ͨ��'");	
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			query.append(" and xysh='ͨ��' ");	
		}
		String[] colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 		
		return commonQuery("view_sjyxbysxx", query.toString(), new String[] {xh,xm}, colList,"", model);		
	}
	/**
	 * �����ƺ��������
	 */
	public boolean dao_rychCk(String userType,String check,String xmk,String pkVStr) throws SQLException{
		DAO dao = DAO.getInstance();
		String pk = "";
		String shType = "";
		String realTable= "";
		if("sjyxbys".equalsIgnoreCase(xmk)){
			realTable= "jhzy_sjyxbysb";
			pk = "xh||xn";
		}else if("xjyxbys".equalsIgnoreCase(xmk)){
			realTable= "jhzy_xjyxbysb";
			pk = "xh||xn";			
		}else if("shxs".equalsIgnoreCase(xmk)){
			realTable= "jhzy_shsb";
			pk = "xh||xn";	
		}else if("dxyxxs".equalsIgnoreCase(xmk)){
			realTable= "jhzy_dxyxxsb";
			pk = "xh||xn";	
		}
		check = "yes".equalsIgnoreCase(check)?"ͨ��":"��ͨ��";
		if("xy".equalsIgnoreCase(userType)){//Ժϵ�û����ʱ��������������ƺ������ƿ���
			shType = "xysh";			
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){	
			shType = "xxsh";
		}else{
			shType = "fdysh";
		}			
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"update "+realTable+" set "+shType+"='"+check+"'  where "+pk+"= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	/**
	 * ʡ�������ҵ����ѯ
	 */
	public  ArrayList<String[]> getSjyxbysCxList(String userType,String userName, sjyxbysRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// ѧԺ����
		String xydm = DealString.toGBK(model.getXydm());
		// רҵ����
		String zydm = DealString.toGBK(model.getZydm());
		// �༶����
		String bjdm = DealString.toGBK(model.getBjdm());
		// �꼶
		String nj = DealString.toGBK(model.getNj());
		// ѧ��
		String xn = DealString.toGBK(model.getXn());
		// ѧ��
		String xh =model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		// ����
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		String fdyQuerry = "isFdy".equalsIgnoreCase(userType) ? " and bjdm in (select bjdm from fdybjb b where zgh like '"+ userName + "' )":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");			
		String[] colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 		
		return commonQuery("view_sjyxbysxx", query.toString(), new String[] {xh,xm}, colList,"", model);		
	}
	/**
	 * �����ƺ�����ɾ��
	 */
	public boolean dao_rychDel(String userType,String xmk,String pkVStr) throws SQLException{
		DAO dao = DAO.getInstance();
		String pk = "";
//		String shType = "";
		String realTable= "";
		if("sjyxbys".equalsIgnoreCase(xmk)){
			realTable= "jhzy_sjyxbysb";
			pk = "xh||xn";
		}else if("xjyxbys".equalsIgnoreCase(xmk)){
			realTable= "jhzy_xjyxbysb";
			pk = "xh||xn";
		}else if("shxs".equalsIgnoreCase(xmk)){
			realTable= "jhzy_shsb";
			pk = "xh||xn";
		}else if("dxyxxs".equalsIgnoreCase(xmk)){
			realTable= "jhzy_dxyxxsb";
			pk = "xh||xn";
		}
		String querry="";
		if("xy".equalsIgnoreCase(userType)){
			querry=" and xxsh='δ���'";
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			
		}else{
			querry="and xysh='δ���' and xxsh='δ���'";
		}
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"delete "+realTable+" where "+pk+"= '"+pkValueA[i]+"'  "+querry;							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	/**
	 * ��ȡ�����������Ϣ
	 */
	public List<HashMap<String, String>>  dao_getChkList(){
		DAO dao = DAO.getInstance();		
		return dao.getChkList(3);
	}
	/**
	 * �����ƺ����
	 * @throws Exception 
	 */
	public boolean dao_rychChk(String userType,String pkValue,String xmk,String fdyshyj,String xyshyj,String xxshyj,String yesNo) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "";
		boolean doFlag=false;
		String realTable="";
		String pk="";
		if("sjyxbys".equalsIgnoreCase(xmk)){			
			realTable="jhzy_sjyxbysb";
			pk="xh||xn";
		}else if("xjyxbys".equalsIgnoreCase(xmk)){
			realTable="jhzy_xjyxbysb";
			pk="xh||xn";
		}else if("shxs".equalsIgnoreCase(xmk)){
			realTable="jhzy_shsb";
			pk="xh||xn";			
		}else if("dxyxxs".equalsIgnoreCase(xmk)){
			realTable="jhzy_dxyxxsb";
			pk="xh||xn";			
		}
		if("xy".equalsIgnoreCase(userType)){
			sql=" update "+realTable+" set xysh=? , xyshyj=? where "+pk+"=? ";
			doFlag = dao.runUpdate(sql, new String[]{yesNo,xyshyj,pkValue});
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			sql=" update "+realTable+" set xxsh=? , xxshyj=? where "+pk+"=?";
			doFlag = dao.runUpdate(sql, new String[]{yesNo,xxshyj,pkValue});
		}else{
			sql=" update "+realTable+" set fdysh=? ,fdyshyj=? where "+pk+"=?";
			doFlag = dao.runUpdate(sql, new String[]{yesNo,fdyshyj,pkValue});
		}
		return doFlag;
	}
	/**
	 * ����ѧ��ѧ����ѯ
	 */
	public  ArrayList<String[]> getShxsList_stu(String xh) {
		DAO  dao = DAO.getInstance();
		String[] colList = new String[]{"xn","xh","xm","bjmc","fdysh","xysh","xxsh"}; 		
		String sql = "select xn,xh,xm,bjmc,fdysh,xysh,xxsh from view_shsxx where xh=? order by xn";	
		return dao.rsToVator(sql, new String[]{xh}, colList);
	}
	/**
	 * ��������ѧ��ѧ����ѯ
	 */
	public  ArrayList<String[]> getDxyxbysList_stu(String xh) {
		DAO  dao = DAO.getInstance();
		String[] colList = new String[]{"xn","xh","xm","bjmc","fdysh","xysh","xxsh"}; 		
		String sql = "select xn,xh,xm,bjmc,fdysh,xysh,xxsh from view_dxyxxsxx where xh=? order by xn";	
		return dao.rsToVator(sql, new String[]{xh}, colList);
	}
	/**
	 * ʡ�������ҵ��ѧ����ѯ
	 */
	public  ArrayList<String[]> getSjyxbysList_stu(String xh) {
		DAO  dao = DAO.getInstance();
		String[] colList = new String[]{"xn","xh","xm","bjmc","fdysh","xysh","xxsh"}; 		
		String sql = "select xn,xh,xm,bjmc,fdysh,xysh,xxsh from view_sjyxbysxx where xh=? order by xn";	
		return dao.rsToVator(sql, new String[]{xh}, colList);
	}
	/**
	 * У�������ҵ��ѧ����ѯ
	 */
	public  ArrayList<String[]> getXjyxbysList_stu(String xh) {
		DAO  dao = DAO.getInstance();
		String[] colList = new String[]{"xn","xh","xm","bjmc","fdysh","xysh","xxsh"}; 		
		String sql = "select xn,xh,xm,bjmc,fdysh,xysh,xxsh from view_xjyxbysxx where xh=? order by xn";	
		return dao.rsToVator(sql, new String[]{xh}, colList);
	}
	/**
	 * У�������ҵ�������ƺ����뱣��
	 * @throws Exception 
	 */
	public boolean xjyxbysSqSave(XjyxbysRychModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String xn = DealString.toGBK(model.getXn());
		String xh = DealString.toGBK(model.getXh());
		String brjl = DealString.toGBK(model.getBrjl());
		String zysjjhjqk = DealString.toGBK(model.getZysjjhjqk());
		String bz   = DealString.toGBK(model.getBz());
		boolean done = false;
		String flag = dao.returntag("select * from jhzy_xjyxbysb where xh||xn=?", new String[]{xh+xn});
		String sql= "";
		if("empty".equalsIgnoreCase(flag)){
			sql="insert into jhzy_xjyxbysb(xn,xh,brjl,zysjjhjqk,bz)values(?,?,?,?,?)";
			done = dao.runUpdate(sql,new String[]{xn,xh,brjl,zysjjhjqk,bz});
		}else{
			sql="update jhzy_xjyxbysb set brjl=?,zysjjhjqk=?,bz=?,fdysh='δ���',fdyshyj='',xysh='δ���',xyshyj='',xxsh='δ���',xxshyj='' where xh||xn=? ";
			done = dao.runUpdate(sql,new String[]{brjl,zysjjhjqk,bz,xh+xn});			
		}
		return done;
	}
	/**
	 * ʡ�������ҵ����ѯ
	 */
	public  ArrayList<String[]> getXjyxbysCxList(String userType,String userName, XjyxbysRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// ѧԺ����
		String xydm = DealString.toGBK(model.getXydm());
		// רҵ����
		String zydm = DealString.toGBK(model.getZydm());
		// �༶����
		String bjdm = DealString.toGBK(model.getBjdm());
		// �꼶
		String nj = DealString.toGBK(model.getNj());
		// ѧ��
		String xn = DealString.toGBK(model.getXn());
		// ѧ��
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		// ����
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		String fdyQuerry = "isFdy".equalsIgnoreCase(userType) ? " and bjdm in (select bjdm from fdybjb b where zgh like '"+ userName + "')":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");			
		String[] colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 		
		return commonQuery("view_xjyxbysxx", query.toString(), new String[] {xh,xm}, colList,"", model);		
	}
	/**
	 * ʡ�������ҵ����˲�ѯ
	 */
	public  ArrayList<String[]> getXjyxbysList(String userType,String userName, XjyxbysRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// ѧԺ����
		String xydm = DealString.toGBK(model.getXydm());
		// רҵ����
		String zydm = DealString.toGBK(model.getZydm());
		// �༶����
		String bjdm = DealString.toGBK(model.getBjdm());
		// �꼶
		String nj = DealString.toGBK(model.getNj());
		// ѧ��
		String xn = DealString.toGBK(model.getXn());
		// ѧ��
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		// ����
		String xm =model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		String fdyQuerry = "isFdy".equalsIgnoreCase(userType) ? " and bjdm in (select bjdm from fdybjb b where zgh like '"+ userName + "' )":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");	
		if("xy".equalsIgnoreCase(userType)){
			query.append(" and fdysh = 'ͨ��'");	
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			query.append(" and xysh='ͨ��' ");	
		}
		String[] colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 		
		return commonQuery("view_xjyxbysxx", query.toString(), new String[] {xh,xm}, colList,"", model);		
	}
	/**
	 * ��ȡѧ���ɼ�������ݼ�
	 */
	public List<HashMap<String,String>> dao_getCjList(String xh){
		return getFzPmList(xh,"","");
	}
	/**
	 * �����������ƺ����뱣��
	 * @throws Exception 
	 */
	public boolean shsSqSave(ShsRychModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String xn = DealString.toGBK(model.getXn());
		String xh = DealString.toGBK(model.getXh());
		String brjl = DealString.toGBK(model.getBrjl());
		String zysj = DealString.toGBK(model.getZysj());
		String zxqjhjqk = DealString.toGBK(model.getZxqjhjqk());
		String bz   = DealString.toGBK(model.getBz());
		boolean done = false;
		String flag = dao.returntag("select * from jhzy_shsb where xh||xn=?", new String[]{xh+xn});
		String sql= "";
		if("empty".equalsIgnoreCase(flag)){
			sql="insert into jhzy_shsb(xn,xh,brjl,zysj,zxqjhjqk,bz)values(?,?,?,?,?,?)";
			done = dao.runUpdate(sql,new String[]{xn,xh,brjl,zysj,zxqjhjqk,bz});
		}else{
			sql="update jhzy_shsb set brjl=?,zysj=?,zxqjhjqk=?,bz=?,fdysh='δ���',fdyshyj='',xysh='δ���',xyshyj='',xxsh='δ���',xxshyj='' where xh||xn=? ";
			done = dao.runUpdate(sql,new String[]{brjl,zysj,zxqjhjqk,bz,xh+xn});			
		}
		return done;
	}
	/**
	 * ��������ѯ
	 */
	public  ArrayList<String[]> getShsCxList(String userType,String userName, ShsRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException  {
		// ѧԺ����
		String xydm = DealString.toGBK(model.getXydm());
		// רҵ����
		String zydm = DealString.toGBK(model.getZydm());
		// �༶����
		String bjdm = DealString.toGBK(model.getBjdm());
		// �꼶
		String nj = DealString.toGBK(model.getNj());
		// ѧ��
		String xn = DealString.toGBK(model.getXn());
		// ѧ��
		String xh =model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		// ����
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		String fdyQuerry = "isFdy".equalsIgnoreCase(userType) ? " and bjdm in (select bjdm from fdybjb b where zgh like '"+ userName + "' )":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");			
		String[] colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 		
		return commonQuery("view_shsxx ", query.toString(), new String[] {xh,xm}, colList,"", model);		
	}
	/**
	 * ����ѧ����˲�ѯ
	 */
	public  ArrayList<String[]> getShxsList(String userType,String userName, ShsRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
//		DAO dao = DAO.getInstance();
		// ѧԺ����
		String xydm = DealString.toGBK(model.getXydm());
		// רҵ����
		String zydm = DealString.toGBK(model.getZydm());
		// �༶����
		String bjdm = DealString.toGBK(model.getBjdm());
		// �꼶
		String nj = DealString.toGBK(model.getNj());
		// ѧ��
		String xn = DealString.toGBK(model.getXn());
		// ѧ��
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		// ����
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		String fdyQuerry = "isFdy".equalsIgnoreCase(userType) ? " and bjdm in (select bjdm from fdybjb b where zgh like '"+ userName + "' )":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");	
		if("xy".equalsIgnoreCase(userType)){
			query.append(" and fdysh = 'ͨ��'");	
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			query.append(" and xysh='ͨ��' ");	
		}
//		String sql = "select * from view_shsxx ";
		String[] colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 
//		return dao.rsToVator(sql+query.toString(), new String[] {xh,xm},  colList);
		return commonQuery("view_shsxx", query.toString(), new String[] {xh,xm}, colList,"", model);		
	}
	/**
	 *   ��������ѧ�������ƺ����뱣��
	 * @throws Exception 
	 */
	public boolean dxyxxsSqSave(DxyxxsModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String xn = DealString.toGBK(model.getXn());
		String xh = DealString.toGBK(model.getXh());
		String brjl = DealString.toGBK(model.getBrjl());
		String zysj = DealString.toGBK(model.getZysj());
		String zxqjhjqk = DealString.toGBK(model.getZxqjhjqk());
		String bz   = DealString.toGBK(model.getBz());
		boolean done = false;
		String flag = dao.returntag("select * from jhzy_dxyxxsb where xh||xn=?", new String[]{xh+xn});
		String sql= "";
		if("empty".equalsIgnoreCase(flag)){
			sql="insert into jhzy_dxyxxsb(xn,xh,brjl,zysj,zxqjhjqk,bz)values(?,?,?,?,?,?)";
			done = dao.runUpdate(sql,new String[]{xn,xh,brjl,zysj,zxqjhjqk,bz});
		}else{
			sql="update jhzy_dxyxxsb set brjl=?,zysj=?,zxqjhjqk=?,bz=?,fdysh='δ���',fdyshyj='',xysh='δ���',xyshyj='',xxsh='δ���',xxshyj='' where xh||xn=? ";
			done = dao.runUpdate(sql,new String[]{brjl,zysj,zxqjhjqk,bz,xh+xn});			
		}
		return done;
	}
	/**
	 * ��������ѧ����Ϣ��ѯ
	 */
	public  ArrayList<String[]> getDxyxxsCxList(String userType,String userName, DxyxxsModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException  {
		// ѧԺ����
		String xydm = DealString.toGBK(model.getXydm());
		// רҵ����
		String zydm = DealString.toGBK(model.getZydm());
		// �༶����
		String bjdm = DealString.toGBK(model.getBjdm());
		// �꼶
		String nj = DealString.toGBK(model.getNj());
		// ѧ��
		String xn = DealString.toGBK(model.getXn());
		// ѧ��
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		// ����
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		String fdyQuerry = "isFdy".equalsIgnoreCase(userType) ? " and bjdm in (select bjdm from fdybjb b where zgh like '"+ userName + "' )":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");			
		String[] colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 		
		return commonQuery("view_dxyxxsxx", query.toString(), new String[] {xh,xm}, colList,"", model);		
	}
	/**
	 * ��������ѧ����Ϣ��˲�ѯ
	 */
	public  ArrayList<String[]> getDxyxxsList(String userType,String userName, DxyxxsModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		// ѧԺ����
		String xydm = DealString.toGBK(model.getXydm());
		// רҵ����
		String zydm = DealString.toGBK(model.getZydm());
		// �༶����
		String bjdm = DealString.toGBK(model.getBjdm());
		// �꼶
		String nj = DealString.toGBK(model.getNj());
		// ѧ��
		String xn = DealString.toGBK(model.getXn());
		// ѧ��
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		// ����
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		String fdyQuerry = "isFdy".equalsIgnoreCase(userType) ? " and bjdm in (select bjdm from fdybjb b where zgh like '"+ userName + "' )":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");	
		if("xy".equalsIgnoreCase(userType)){
			query.append(" and fdysh = 'ͨ��'");	
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			query.append(" and xysh='ͨ��' ");	
		}
		String[] colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 		
		return commonQuery("view_dxyxxsxx", query.toString(), new String[] {xh,xm}, colList,"", model);		
	}
	/**
	 * ����ѧ���б�
	 */
	public List<HashMap<String,String>>getShJxjList(String xh){
		DAO dao = DAO.getInstance();
		String sql="select xn ,jxjmc from view_xsjxjb where xh=? and xxsh = 'ͨ��' order by xn";
		return dao.getList(sql,new String[]{xh},new String[]{"xn","jxjmc"});
	}
	/**
	 * ����ȼ��б�
	 */
	public List<HashMap<String,String>>getShDjList(String xh){
		DAO dao = DAO.getInstance();
		String sql="select xn,(select xqmc from xqdzb b where a.xq=b.xqdm )xqmc,djksmc,cj from xsdjksb a where xh=? order by xn,xq";
		return dao.getList(sql,new String[]{xh},new String[]{"xn","xqmc","djksmc","cj"});
	}
}
