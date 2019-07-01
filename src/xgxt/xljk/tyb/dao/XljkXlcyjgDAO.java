/*
 * @className: XljkXlcyjgDAO.java
 * 
 * @time: 2010-4-30
 * 
 * @copyRight hz-zf
 * 
 */

package xgxt.xljk.tyb.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.tyb.model.XljkXlcyjgModel;

/**
 * ������ - ���������ά�����õ�DAO,
 * �����Դ�ѧ���˸�����������Խ���Ϳ��ض��˸����ز��Խ����ά��
 * @author  lt
 * @version 1.0 2010-4-30
 * @see     xgxt.DAO.DAO  
 */
public class XljkXlcyjgDAO {

	DAO dao = DAO.getInstance();
	
	//��ѯ��ѧ���˸����ز�����SQL
	private final StringBuilder QUERY_RGYSJG_SQL = new StringBuilder("select xh||cssj pk,")
	        .append("rownum r,xh,xm,xb,bjmc,cssj,qtzz,jsfl,yyz,sjz,zf from view_rgysyzdmb a");
	//��ѯ���ض��˸����ز�����SQL
	private final StringBuilder QUERY_KTEYSJG_SQL = new StringBuilder("select xh||cssj pk,rownum r,")
	        .append("xh,xm,xb,bjmc,cssj,ayz,byz,cyz,dyz,eyz from view_ktergysjcb a");
	
	//���Ӵ�ѧ���˸����ز�����ϢSQL
	private final StringBuilder INSERT_RGYSJG_SQL = new StringBuilder("insert into dxsrgyscyb")
	        .append("(xh,cssj,qtzz,jsfl,yyz,sjz,zf) values (?,?,?,?,?,?,?)");
	
	//���ӿ��ض���ѧ���˸����ز�����ϢSQL
	private final StringBuilder INSERT_KTESJG_SQL = new StringBuilder("insert into ktergysjcb")
	        .append(" (xh,cssj,ayz,byz,cyz,dyz,eyz,fyz,gyz,hyz,iyz,lyz,myz,nyz,oyz,q1yz,")
	        .append("q2yz,q3yz,q4yz,x1yz,x2yz,x3yz,x4yz,y1yz,y2yz,y3yz,y4yz) values ")
	        .append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	
	//�޸Ĵ�ѧ���˸����ز�����ϢSQL
	private final StringBuilder UPDATE_RGYSJG_SQL = new StringBuilder("update dxsrgyscyb")
	        .append(" set xh=?,cssj=?,qtzz=?,jsfl=?,yyz=?,sjz=?,zf=? where xh||cssj=?");
	
	//�޸Ŀ��ض���ѧ���˸����ز�����ϢSQL
	private final StringBuilder UPDATE_KTESJG_SQL = new StringBuilder("update ktergysjcb set")
	        .append(" xh=?,cssj=?,ayz=?,byz=?,cyz=?,dyz=?,eyz=?,fyz=?,gyz=?,hyz=?,iyz=?,")
	        .append("lyz=?,myz=?,nyz=?,oyz=?,q1yz=?,q2yz=?,q3yz=?,q4yz=?,x1yz=?,x2yz=?")
	        .append(",x3yz=?,x4yz=?,y1yz=?,y2yz=?,y3yz=?,y4yz=? where xh||cssj=?");
	
	//��ʾ��ѧ���˸����ز�����ϢSQL
	private final StringBuilder VIEW_RGYSJG_SQL = new StringBuilder("select xh,xm,xb,bjmc")
	        .append(",xymc,zymc,cssj,qtzz,jsfl,yyz,sjz,zf,nj,csrq from view_rgysyzdmb a where xh||cssj=?");
	
	//��ʾ���ض���ѧ���˸����ز�����ϢSQL
	private final StringBuilder VIEW_KTEYSJG_SQL = new StringBuilder("select xh,cssj,xm,")
	        .append("xb,nj,(select csrq from view_xsxxb b where a.xh=b.xh) csrq,xymc,")
	        .append("zymc,bjmc,ayz,byz,cyz,dyz,eyz,fyz,gyz,hyz,iyz,lyz,myz,nyz,oyz,q1yz,")
	        .append("q2yz,q3yz,q4yz,x1yz,x2yz,x3yz,x4yz,y1yz,y2yz,y3yz,y4yz ")
	        .append("from view_ktergysjcb a where xh||cssj=?");
	
	//��ѯ��ѧ���˸�����������Ϣ
	private final StringBuilder VIEW_DXSRGPY_SQL = new StringBuilder(" select dm,yz,yzmc,bzf,py,dj from rgysyzdmb where yz = ? ")
	    .append(" and ((length(bzf)=3 and substr(bzf,2,1)='-' and to_number(substr(bzf,1,1)) <= ? and to_number(substr(bzf,3,1)) >= ? ) ")
	    .append(" or (length(bzf)=4 and substr(bzf,3,1)='-' and to_number(substr(bzf,1,2)) <= ? and to_number(substr(bzf,4,1)) >= ? ) ")
	    .append(" or (length(bzf)=5 and substr(bzf,3,1)='-' and to_number(substr(bzf,1,2)) <= ? and to_number(substr(bzf,4,2)) >= ? ) ")
	    .append(" or (length(bzf)=4 and substr(bzf,2,1)='-' and to_number(substr(bzf,1,1)) <= ? and to_number(substr(bzf,3,2)) >= ? )) and rownum<2  ");
	
	//��ѯ����������в����ڵĴ�ѧ����������
	private final StringBuilder QUERY_DXSYZNOTEXISTS_SQL = new StringBuilder("select yz from rgysyzdmb group by yz");
	
	//��ѯ����������в����ڵĿ��ض���������
	private final StringBuilder QUERY_KTEYZNOTEXISTS_SQL = new StringBuilder("select yx from zcbzpyb group by yx"); 
	
	/**
	 * ��ѯ��ѧ���˸����ز�����
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryDxsrgysjgResult(XljkXlcyjgModel model,
			String userName) throws Exception {
		setSpaceByModel(model);
		
		String[] colList = new String[] { "pk", "r", "xh", "xm", "xb", "bjmc",
				"cssj", "qtzz", "jsfl", "yyz", "sjz", "zf" };
		String[] likeQueryArr = new String[]{"xh", "xm", "cssj"};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryObject.baseQueryArr, likeQueryArr, model);
		
		String whereSql = StringUtils.isNull(userName) ? "" 
				: " and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"
						+ userName + "')";
		
		return CommonQueryDAO.commonQuery(getQUERY_RGYSJG_SQL(),
				queryObject.getQueryString() + whereSql, queryObject.getInputList(),
				colList, model);
	}
	
	/**
	 * ���Ӵ�ѧ���˸����ز�����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addDxsrgyscyXx(XljkXlcyjgModel model) throws Exception{
		return dao.runUpdate(getINSERT_RGYSJG_SQL(),
				new String[] { model.getXh(), model.getCssj(), model.getQtzz(),
						model.getJsfl(), model.getYyz(), model.getSjz(),
						model.getZf() });
	}
	
	/**
	 * �޸Ĵ�ѧ���˸����ز�����Ϣ
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean updateDxsrgyscyXx(XljkXlcyjgModel model, String pkValue)
			throws Exception {
		return dao.runUpdate(getUPDATE_RGYSJG_SQL(), new String[] {
				model.getXh(), model.getCssj(), model.getQtzz(),
				model.getJsfl(), model.getYyz(), model.getSjz(), model.getZf(),
				pkValue });
	}
	
	/**
	 * ��ʾ��ѧ���˸����ز�����Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewDxsrgyscyXx(String pkValue) {
		return dao.getMapNotOut(getVIEW_RGYSJG_SQL(), new String[]{pkValue});
	}

	/**
	 * ɾ����ѧ���˸����ز�����Ϣ
	 * @param sqlArr
	 * @return
	 * @throws Exception
	 */
	public boolean deleteDxsrgyscyXx(String[] sqlArr) throws Exception{
		return dao.checkBatch(dao.runBatch(sqlArr));
	}
	
	/**
	 * ��ѯ���ض��˸�������Ϣ���
	 * @param model
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryKtergysResult(XljkXlcyjgModel model,
			String userName) throws Exception {
		setSpaceByModel(model);
		
		String[] colList = new String[] { "pk", "r", "xh", "xm", "xb", "bjmc",
				"cssj", "ayz", "byz", "cyz", "dyz", "eyz" };
		String[] likeQueryArr = new String[]{"xh", "xm", "cssj"};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryObject.baseQueryArr, likeQueryArr, model);
		
		String whereSql = StringUtils.isNull(userName) ? "" 
				: " and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"
						+ userName + "')";
		
		return CommonQueryDAO.commonQuery(getQUERY_KTEYSJG_SQL(),
				queryObject.getQueryString() + whereSql, queryObject.getInputList(),
				colList, model);
	}
	
	/**
	 * ���ӿ��ض��˸�������Ϣ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addKtergysxx(XljkXlcyjgModel model) throws Exception{
		return dao.runUpdate(getINSERT_KTESJG_SQL(), new String[] {
				model.getXh(), model.getCssj(), model.getAyz(), model.getByz(),
				model.getCyz(), model.getDyz(), model.getEyz(), model.getFyz(),
				model.getGyz(), model.getHyz(), model.getIyz(), model.getLyz(),
				model.getMyz(), model.getNyz(), model.getOyz(),
				model.getQ1yz(), model.getQ2yz(), model.getQ3yz(),
				model.getQ4yz(), model.getX1yz(), model.getX2yz(),
				model.getX3yz(), model.getX4yz(), model.getY1yz(),
				model.getY2yz(), model.getY3yz(), model.getY4yz() });
	}
	
	/**
	 * �޸Ŀ��ض��˸�������Ϣ���
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean updateKtergysxx(XljkXlcyjgModel model, String pkValue)
			throws Exception {
		return dao.runUpdate(getUPDATE_KTESJG_SQL(), new String[] {
				model.getXh(), model.getCssj(), model.getAyz(), model.getByz(),
				model.getCyz(), model.getDyz(), model.getEyz(), model.getFyz(),
				model.getGyz(), model.getHyz(), model.getIyz(), model.getLyz(),
				model.getMyz(), model.getNyz(), model.getOyz(),
				model.getQ1yz(), model.getQ2yz(), model.getQ3yz(),
				model.getQ4yz(), model.getX1yz(), model.getX2yz(),
				model.getX3yz(), model.getX4yz(), model.getY1yz(),
				model.getY2yz(), model.getY3yz(), model.getY4yz(), pkValue });
	}
	
	/**
	 * ��ʾ���ض��˸�������Ϣ���
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewKtergysxx(String pkValue) {
		return dao.getMapNotOut(getVIEW_KTEYSJG_SQL(), new String[]{pkValue});
	}
	
	/**
	 * ��ѯ��ѧ��������Ϣ
	 * @param rs
	 * @return
	 */
	public String[] getDxsrgpy() {
		String[] yzArr={GlobalsVariable.XLJK_DXSRGYS_QTZZYZ,
			GlobalsVariable.XLJK_DXSRGYS_JSFLYZ,
			GlobalsVariable.XLJK_DXSRGYS_YYZYZ,
			GlobalsVariable.XLJK_DXSRGYS_SJZYZ,
		};
		return yzArr;
	}
	
	/**
	 * ��ѯ��ѧ��������Ϣ
	 * @param rs
	 * @return
	 */
	public String[] getDxsArr() {
		String[] yzArr={GlobalsVariable.XLJK_DXSRGYS_QTZZYW,
			GlobalsVariable.XLJK_DXSRGYS_JSFLYW,
			GlobalsVariable.XLJK_DXSRGYS_YYZYW,
			GlobalsVariable.XLJK_DXSRGYS_SJZYW,
		};
		return yzArr;
	}
	
	/**
	 * ��ѯ���ڻ���������еĴ�ѧ���˸���������
	 * @return
	 * @throws Exception
	 */
	public String[] queryDxsyzNotExistsDB() throws Exception{
		return dao.getArray(getQUERY_DXSYZNOTEXISTS_SQL(), new String[] {}, "yz");
	}
	
	/**
	 * ��ѯ���ڻ���������еĿ��ض��˸���������
	 * @return
	 * @throws Exception
	 */
	public String[] queryKteyzNotExistsDB() throws Exception{
		return dao.getArray(getQUERY_KTEYZNOTEXISTS_SQL(), new String[]{}, "yx");
	}
	
	/**
	 * ��ѯ���ض��˸�����������Ϣ
	 * @param rs
	 * @return
	 */
	public List<String[]> queryDxsrgpyResult(HashMap<String, String> rs) {
		String[] yzArr = getDxsArr();//Ĭ�ϵ����Ӵ���
		String[] dxsrgpy=getDxsrgpy();
		List<String> values = new ArrayList<String>();
		if (!rs.isEmpty()) {
			for (String s : yzArr) {
				values.add(rs.get(s));
			}
		}
		
		List<String> array = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		/* ѭ��ƴ��ÿ�����Ӵ����������Ϣ */
		for (int i=0;i<yzArr.length;i++) {
			if (i==0) {
				sql.append("select a.*,rownum sx from (");
			}
			sql.append(VIEW_DXSRGPY_SQL);
			if (i != yzArr.length - 1) {				
				sql.append(" union all ");
			} else {
				sql.append(") a");
			}
			array.add(dxsrgpy[i].replaceAll("yz", "").toUpperCase());
			for(int j=0;j<4;j++){
				if (!values.isEmpty()) {	
					array.add(values.get(i));
					array.add(values.get(i));
				}
			}
			System.out.println(values.get(i));
		}
		System.out.println(sql);
		return dao.rsToVator(sql.toString(), array.isEmpty() ? new String[] {}
				: array.toArray(new String[0]), new String[]
				{"sx", "yz", "yzmc", "bzf", "py", "dj"  });
	}
	
	
	/**
	 * ��ѯ���ض��˸�����������Ϣ
	 * @param rs
	 * @return
	 */
	public List<String[]> queryKteyzpyResult(HashMap<String, String> rs) {
		String[] yzArr = getKteYzArr();//Ĭ�ϵ����Ӵ���
		List<String> values = new ArrayList<String>();
		if (!rs.isEmpty()) {
			for (String s : yzArr) {
				values.add(rs.get(s));
			}
		}
		
		List<String> array = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		/* ѭ��ƴ��ÿ�����Ӵ����������Ϣ */
		for (int i=0;i<yzArr.length;i++) {
			if (i==0) {
				sql.append("select a.*,rownum sx from (");
			}
			sql.append("select yx,yxmc,bzf,bzpy,dj from zcbzpyb  where yx = ? ")
				    .append(" and ((length(bzf)=3 and substr(bzf,2,1)='-' and to_number(substr(bzf,1,1)) <= ? and to_number(substr(bzf,3,1)) >= ? ) ")
				    .append(" or (length(bzf)=4 and substr(bzf,3,1)='-' and to_number(substr(bzf,1,2)) <= ? and to_number(substr(bzf,4,1)) >= ? ) ")
				    .append(" or (length(bzf)=5 and substr(bzf,3,1)='-' and to_number(substr(bzf,1,2)) <= ? and to_number(substr(bzf,4,2)) >= ? ) ")
				    .append(" or (length(bzf)=4 and substr(bzf,2,1)='-' and to_number(substr(bzf,1,1)) <= ? and to_number(substr(bzf,3,2)) >= ? )) and rownum<2 ");
			if (i != yzArr.length - 1) {				
				sql.append(" union all ");
			} else {
				sql.append(") a");
			}
			array.add(yzArr[i].replaceAll("yz", "").toUpperCase());
			for(int j=0;j<4;j++){
				if (!values.isEmpty()) {	
					array.add(values.get(i));
					array.add(values.get(i));
				}
			}
		}
		return dao.rsToVator(sql.toString(), array.isEmpty() ? new String[] {}
				: array.toArray(new String[0]), new String[] { "sx", "yx", "yxmc",
				"bzf", "bzpy", "dj" });
	}

	//���ض�Ĭ�������б�
	public String[] getKteYzArr() {
		String[] yzArr = { GlobalsVariable.XLJK_KTEYS_AYZ,
				GlobalsVariable.XLJK_KTEYS_BYZ, GlobalsVariable.XLJK_KTEYS_CYZ,
				GlobalsVariable.XLJK_KTEYS_DYZ, GlobalsVariable.XLJK_KTEYS_EYZ,
				GlobalsVariable.XLJK_KTEYS_FYZ, GlobalsVariable.XLJK_KTEYS_GYZ,
				GlobalsVariable.XLJK_KTEYS_HYZ, GlobalsVariable.XLJK_KTEYS_IYZ,
				GlobalsVariable.XLJK_KTEYS_LYZ, GlobalsVariable.XLJK_KTEYS_MYZ,
				GlobalsVariable.XLJK_KTEYS_NYZ, GlobalsVariable.XLJK_KTEYS_OYZ,
				GlobalsVariable.XLJK_KTEYS_Q1YZ,
				GlobalsVariable.XLJK_KTEYS_Q2YZ,
				GlobalsVariable.XLJK_KTEYS_Q3YZ,
				GlobalsVariable.XLJK_KTEYS_Q4YZ,
				GlobalsVariable.XLJK_KTEYS_X1YZ,
				GlobalsVariable.XLJK_KTEYS_X2YZ,
				GlobalsVariable.XLJK_KTEYS_X3YZ,
				GlobalsVariable.XLJK_KTEYS_X4YZ,
				GlobalsVariable.XLJK_KTEYS_Y1YZ,
				GlobalsVariable.XLJK_KTEYS_Y2YZ,
				GlobalsVariable.XLJK_KTEYS_Y3YZ,
				GlobalsVariable.XLJK_KTEYS_Y4YZ };
		return yzArr;
	}
	
	//ȥ��ѧ�ţ�����������еĿո�
	public void setSpaceByModel(XljkXlcyjgModel model) {
		if (StringUtils.isNotNull(model.getXh())) {
			model.setXh(model.getXh().trim());
		}
		if (StringUtils.isNotNull(model.getXm())) {
			model.setXm(model.getXm().trim());
		}
	}
	
	public String getQUERY_RGYSJG_SQL() {
		return QUERY_RGYSJG_SQL.toString();
	}

	public String getINSERT_RGYSJG_SQL() {
		return INSERT_RGYSJG_SQL.toString();
	}

	public String getUPDATE_RGYSJG_SQL() {
		return UPDATE_RGYSJG_SQL.toString();
	}

	public String getVIEW_RGYSJG_SQL() {
		return VIEW_RGYSJG_SQL.toString();
	}

	public String getQUERY_KTEYSJG_SQL() {
		return QUERY_KTEYSJG_SQL.toString();
	}

	public String getINSERT_KTESJG_SQL() {
		return INSERT_KTESJG_SQL.toString();
	}

	public String getUPDATE_KTESJG_SQL() {
		return UPDATE_KTESJG_SQL.toString();
	}

	public String getVIEW_KTEYSJG_SQL() {
		return VIEW_KTEYSJG_SQL.toString();
	}

	public String getVIEW_DXSRGPY_SQL() {
		return VIEW_DXSRGPY_SQL.toString();
	}

	public String getQUERY_DXSYZNOTEXISTS_SQL() {
		return QUERY_DXSYZNOTEXISTS_SQL.toString();
	}

	public String getQUERY_KTEYZNOTEXISTS_SQL() {
		return QUERY_KTEYZNOTEXISTS_SQL.toString();
	}
}
