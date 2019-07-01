package xgxt.pjpy.ycsfxy;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.pjpy.ahjg.PjpyAhjgService;
import xgxt.utils.String.StringUtils;

public class PjpyYcsfxyService {

	//���ݲ���DAO
	private PjpyYcsfxyDAO dao = PjpyYcsfxyDAO.getInstance();

	public static PjpyYcsfxyService getInstance() {
		
		return new PjpyYcsfxyService();
	}
	
	/**
	 * ƽʱ���׶ο��˳ɼ���ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> getPsjdkhQueryTitle() {	
		return dao.getPsjdkhQueryTitle();
	}
	
	/**
	 * ƽʱ���׶ο��˳ɼ���ѯ���,�ӷ�ҳ
	 * @param model
	 * @param userType
	 * @param userName
	 * @return
	 */
	public ArrayList<String[]> getPsjdkhQueryResult(PjpyYcsfxyModel model,
			String userType, String userName,
			PjpyYcsfxyActionForm dataSearchForm) {
		return dao.getPsjdkhQueryResult(model, userType, userName, dataSearchForm);
	}

	/**
	 * ƽʱ���׶ο��˳ɼ���ѯ���,���ܼ�¼��
	 * @param model
	 * @param userType
	 * @param userName
	 * @return
	 */
	public int getPsjdkhQueryResultCount(PjpyYcsfxyModel model,
			String userType, String userName) {
		return dao.getPsjdkhQueryResultCount(model, userType, userName);
	}
	
	/**
	 * ����ƽʱ,�׶ο��˳ɼ���Ϣ
	 * @param pkList
	 * @param pjkhcj
	 * @param jdkhcj
	 * @return
	 * @throws Exception
	 */
	public boolean savePjjdkhcjxx(String[] pkList, String[] pjkhcj, String[] jdkhcj, String[] dis) throws Exception {
		return dao.savePjjdkhcjxx(pkList, pjkhcj, jdkhcj, dis);
	}
	
	/**
	 * ɾ��ƽʱ�׶�,���˳ɼ���Ϣ,���ز���ʧ�ܵ�����
	 * @param pkList
	 * @return
	 * @throws Exception
	 */
	public String deletePjjdkhcjxx(String[] pkList) throws Exception{
		return dao.deletePjjdkhcjxx(pkList);
	}
	
	//�ۺ����ʲ�����������
	public boolean setZhszcpBl_ser(PjpyYcsfxyModel model) throws Exception{
		return dao.setZhszcpBl_db(model);
	}
	
	//����ۺ����ʲ�������
	public PjpyYcsfxyModel getZhszcpBl_ser(){
		PjpyYcsfxyModel model = dao.getZhszcpBl_db(); 
		HashMap<String, String> rs = dao.queryZhszcpxmBlxx();
		model.setDyjcf(rs.get("dyjcf"));
		model.setDyjcfbl(rs.get("dyjcfbl"));
		model.setDyssgffbl(rs.get("dyssgffbl"));
		model.setDyfjfbl(rs.get("dyfjfbl"));
		model.setZykskmcjbl(rs.get("zykskmcjbl"));
		model.setZykckmcjbl(rs.get("zykckmcjbl"));
		model.setZyfjfbl(rs.get("zyfjfbl"));
		model.setTycjbl(rs.get("tycjbl"));
		model.setTykwtydlbl(rs.get("tykwtydlbl"));
		model.setTyfjfbl(rs.get("tyfjfbl"));
		return model;
	}
	
	//��ѯ�ۺ����ʲ�����سɼ�
	public List<String[]> zhszcpQuery_ser(PjpyYcsfxyActionForm form,PjpyYcsfxyModel model,String userName,String isFdy){
		return dao.zhszcpQuery_db(form,model,userName,isFdy);
	}
	
	//��ѯ�ۺ����ʲ�������
	public int zhszcpQueryCount_ser(PjpyYcsfxyModel model,String userName,String isFdy){
		return dao.zhszcpQueryCount_db(model, userName, isFdy);
	}
	
	//����ѧҵ���˳ɼ����ۺ����ʲ����ɼ�
	public String computeZhszcp_ser(String userType,String xydm,String userName){
		try {
			return dao.computeZhszcp_db(userType,xydm,userName);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}

	//��ϱ�ͷ
	public List<HashMap<String, String>> getTabName_ser(String[] en,String[] cn){
		DAO comdao = DAO.getInstance();
		return comdao.arrayToList(en, cn);
	}
	
	//��ѯ�ۺ����ʲ���ȫ��ͨ��
	public String zhszcpShAll_ser(){
		try {
			return dao.zhszcpShAll_db();
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	//�ۺ����ʲ�������ͨ��
	public String zhszcpShPart_ser(String[] pks,String act){
		String pkVals = "!@!";
		if(pks != null){
			for(int i=0;i<pks.length;i++){
				pkVals += pks[i]+"!@!";
			}
		}
		try {
			return dao.zhszcpShPart_db(pkVals,act);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	//�鿴ѧ���ɼ�
	public List<String[]> getXscj_ser(String pk){
		PjpyAhjgService service = new PjpyAhjgService();
		String jwflag = service.getJwFlag();
		jwflag = StringUtils.isNull(jwflag) ? "" : jwflag.trim();//1ѧ��0����
		try{
			if ("1".equalsIgnoreCase(jwflag)) {
				return dao.getCjListByxf_db(pk);
			} else {
				return dao.getCjList_db(pk);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	//���ѧ��������Ϣ���ɼ�������Ϣ
	public HashMap<String,String> getXsxxAndCjhz_ser(String pk){
		try{
			return dao.getXsxxAndCjhz_db(pk);
		}catch(Exception ex){
			ex.printStackTrace();
			return new HashMap<String,String>();
		}
	}
	
	//���ѧ��������Ϣ��ƽʱ���׶γɼ���Ϣ
	public HashMap<String,String> getXsxxAndPsjdcj_ser(String pk){
		try{
			return dao.getXsxxAndPsjdcj_db(pk);
		}catch(Exception ex){
			ex.printStackTrace();
			return new HashMap<String,String>();
		}
	}
	
	/**
	 * ѧ����ѯ�ۺ����ʲ�����Ϣ
	 * @param xh
	 * @return
	 */
	public List<String[]> stuQueryZhszcpxx(String xh) {
		return dao.stuQueryZhszcpxx(xh);
	}
	
	/**
	 * ѧ���ۺ����ʲ�����ʾ��Ϣ
	 * @param pk
	 * @return
	 */
	public HashMap<String, String> stuZhszcpView(String pk) {
		return dao.stuZhszcpView(pk);
	}
	
	/**
	 * ѧ���γ̳ɼ���Ϣ
	 * @param pk
	 * @return
	 */
	public List<String[]> stuCjInfo(String pk) {
		return dao.stuCjInfo(pk);
	}
	
	//��ѧ�������ƺ����
	public List<String[]> getJxjOrRychQueryList_ser(PjpyYcsfxyActionForm form,PjpyYcsfxyModel model){
		return dao.getJxjOrRychQueryList_db(form,model);
	}
	//��ѧ�������ƺ����
	public List<String[]> getJxjOrRychList_ser(PjpyYcsfxyActionForm form,PjpyYcsfxyModel model,String userType){
		return dao.getJxjOrRychList_db(form,model,userType);
	}
	
	//��ѧ�������ƺ���˼�¼��
	public int getJxjOrRychCount_db(PjpyYcsfxyModel model){
		return dao.getJxjOrRychCount_db(model);
	}
	
	//��Ͻ�ѧ�������ƺ���˱�ͷ
	public List<HashMap<String,String>> getJxjOrRychTabName(String lb){
		String[] colEn = null;
		String[] colCn = null;
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			if("jxj".equals(lb)){
				colEn = new String[] { "pk", "xh", "xm", "xn", "xqmc",
						"nj", "bjmc", "pjf","pm","jxjmc","jlje","xysh","xxsh" };
				colCn = new String[] { "pk", "ѧ��", "����", "ѧ��", "ѧ��",
						"�꼶", "�༶����", "�ܿ�ƽ����","�༶����","��ѧ������","�������", "ѧԺ���", "ѧУ���" };
			}else{
				colEn = new String[] { "pk", "xh", "xm", "xn", "xqmc",
						"nj", "bjmc", "zhszcpzf","pm","jxjmc","xxsh" };
				colCn = new String[] { "pk", "ѧ��", "����", "ѧ��", "ѧ��",
						"�꼶", "�༶����", "�۲�ɼ�","�۲�༶����","��ѧ������", "ѧУ���" };
			}
		}else{
			if("jxj".equals(lb)){
				colEn = new String[] { "pk", "xh", "xm", "xn", "xqmc",
						"nj", "bjmc","xykhcj","xyzfpm", "zhszcpzf","pm","jxjmc","jlje","xxsh" };
				colCn = new String[] { "pk", "ѧ��", "����", "ѧ��", "ѧ��",
						"�꼶", "�༶����","ѧҵ�ɼ�","ѧҵ�༶����", "�۲�ɼ�","�۲�༶����","��ѧ������","�������", "ѧУ���" };
			}else{
				colEn = new String[] { "pk", "xh", "xm", "xn", "xqmc",
						"nj", "bjmc","xykhcj","xyzfpm", "zhszcpzf","pm","jxjmc","xxsh" };
				colCn = new String[] { "pk", "ѧ��", "����", "ѧ��", "ѧ��",
						"�꼶", "�༶����","ѧҵ�ɼ�","ѧҵ�༶����", "�۲�ɼ�","�۲�༶����","��ѧ������", "ѧУ���" };
			}
		}	
		return getTabName_ser(colEn, colCn);
	}
	
	//���ȫ��ͨ��
	public String jxjOrRychShAll_ser(String userType,String userDep){
		try {
			return dao.jxjOrRychShAll_db(userType,userDep);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	//�������
	public String jxjOrRychShPart_ser(String[] pks,String act,String lb,String userType,String userDep){
		String pkVals = "!@!";
		if(pks != null){
			for(int i=0;i<pks.length;i++){
				pkVals += pks[i]+"!@!";
			}
		}
		try {
			return dao.jxjOrRychShPart_ser(pkVals, act, lb,userType,userDep);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	/**
	 * ��ѯѧ����Ϣ�����ۺ����ʲ�����Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getStuInfo(String xh,String xxdm) {
		return dao.getStuInfo(xh,xxdm);
	}
	
	/**
	 * ѧ���γ̳ɼ���Ϣ
	 * @param pk
	 * @return
	 */
	public List<String[]> stuCjInfo(String xh, String xn, String xq) {
		return dao.stuCjInfo(xh, xn,xq);
	}
	
	/**
	 * ���浥��¼��Ļ�������Ϣ
	 * @param model
	 * @param userType
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean saveHjmdxx(PjpyYcsfxyModel model, String userType, String type) throws Exception {	
		return dao.saveAddHjmdxx(model, userType,type);
	}
	
	/**
	 * ɾ����������Ϣ
	 * @param pkList
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean deleteHjmdxx(String[] pkList, String type) throws Exception{
		return dao.deleteHjmdxx(pkList, type);
	}
	
	/**
	 * �޸Ļ�������Ϣ ��ѧԺ,ѧУ�û�
	 * @param xm
	 * @param userType
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewHjmdxx(String xm, String userType, String pkValue) {	
		return dao.viewModifyHjmdxx(xm, pkValue,userType);
	}
	
	/**
	 * �޸Ļ�������Ϣ ��ѧԺ,ѧУ�û�
	 * @param userType
	 * @param xm
	 * @param pkValue
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiHjmdxx(String userType, String xm, String pkValue,
			PjpyYcsfxyModel model) throws Exception {
			return dao.saveModiHjmdxx(userType,xm, pkValue, model);
	}
	
	/**
	 * ��ѯ��������Ϣ
	 * @param model
	 * @param userType
	 * @param userName
	 * @param dataSearchForm
	 * @return
	 */
	public ArrayList<String[]> queryHjmdxxResult(PjpyYcsfxyModel model,
			String userType, String userName, PjpyYcsfxyActionForm dataSearchForm) {
		return dao.queryHjmdxxResult(model, userType, userName, dataSearchForm);
	}
	
	/**
	 * ��ѯ����Ϣ�ܼ�¼��
	 * @param model
	 * @param userType
	 * @param userName
	 * @param dataSearchForm
	 * @return
	 */
	public int queryHjmdxxResultCount(PjpyYcsfxyModel model,
			String userType, String userName, PjpyYcsfxyActionForm dataSearchForm) {
		return dao.queryHjmdxxResultCount(model, userType, userName, dataSearchForm);
	}
	
	/**
	 * �������ϱ���ѯ��ͷ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> queryHjmdxxTitle() {
		return dao.queryHjmdxxTitle();
	}
	
	/**
	 * �û������ϱ�������
	 * @param xhList
	 * @param model
	 * @return
	 */
	public boolean hjmdPlsb(String userType, PjpyYcsfxyModel model, String[] xhList) throws Exception {
		return dao.hjmdPlsb_db(userType,xhList, model);
	}
	
	/**
	 * ��ѯ��������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> viewHjmdxx(PjpyYcsfxyModel model, String pkValue, String userType, String xm) {
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = dao.viewModifyHjmdxx(xm, pkValue,userType);
		if (rs == null || StringUtils.isNull(rs.get("dm"))) {
			return dao.viewHjmdxx(model); 
		}
		return rs;
	}
	
	//���������
	public HashMap<String, String> getJxjOrRychShxx_ser(String lb,String pk){
		return dao.getJxjOrRychShxx_db(lb, pk);
	}
	
	//�������
	public String jxjOrRychDgsh_ser(String pk,PjpyYcsfxyModel model,String userType){
		try {
			return dao.jxjOrRychDgsh_db(pk, model,userType);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	//��ѯ��˽��(��ҳ)
	public List<String[]> queryShResult_ser(PjpyYcsfxyActionForm form,PjpyYcsfxyModel model,String userName,String isFdy){
		return dao.queryShResult_db(form, model, userName, isFdy);
	}
	
	//��ѯ��˽������
	public int queryShResultCount_ser(PjpyYcsfxyModel model,String userName,String isFdy){
		return dao.queryShResultCount_db(model, userName, isFdy);
	}
	
	//ѧ����ѧ������
	public List<String[]> queryXsJxj_ser(String userName){
		return dao.queryXsJxj_db(userName);
	}
	
	//ѧ�������ƺŻ����
	public List<String[]> queryXsRych_ser(String userName){
		return dao.queryXsRych_db(userName);
	}
	
	//�༶�ɼ�����
	public void expBjcj_ser(PjpyYcsfxyModel model,OutputStream os) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("���ݵ���", 0);
		ExcelMB mb = new ExcelMB();
		List<String[]> kclist = dao.queryBjxsKcmc_db(model);
		ws.mergeCells(0, 0, kclist.size()+7, 1);
		String xq = dao.getXqmc_db(model.getXq());
		mb.printToOneCell_multy(ws, model.getXn()+xq+"ѧ��"+DealString.toGBK(model.getBjmc())+"�༶�ɼ�һ����" , 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 1000, Border.NONE);
		mb.printToOneCell_multy(ws, "ѧ��", 0, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "����", 1, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		for(int i=0;i<kclist.size();i++){
			mb.printToOneCell_multy(ws, kclist.get(i)[0], 2+i, 2, 10, false, Alignment.CENTRE,
					VerticalAlignment.CENTRE, false, 300, Border.ALL);
		}
		mb.printToOneCell_multy(ws, "ƽʱ���˳ɼ�", kclist.size()+2, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "�׶ο��˳ɼ�", kclist.size()+3, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "ѧҵ���˳ɼ�", kclist.size()+4, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "ѧҵ�ɼ�����", kclist.size()+5, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "�ۺϲ����ɼ�", kclist.size()+6, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "�۲�ɼ�����", kclist.size()+7, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		List<String[]> xslist = dao.getBjxs_db(model.getBjdm());
		for(int i=0;i<xslist.size();i++){
			model.setXh(xslist.get(i)[0]);
			List<HashMap<String,String>> xscj = dao.getOneXscj_db(model);
			for(int j=0;j<xscj.size();j++){
				HashMap<String,String> tempcj = xscj.get(j);
				mb.printToOneCell_multy(ws, tempcj.get("cj"), Integer.valueOf(tempcj.get("r"))+1, 3+i, 10, false, Alignment.CENTRE,
						VerticalAlignment.CENTRE, false, 300, Border.ALL);
			}
			HashMap<String,String> tempcj = dao.getOneXshzcj_db(model);
			mb.printToOneCell_multy(ws, tempcj.get("xh"), 0, 3+i, 10, false, Alignment.CENTRE,
					VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, tempcj.get("xm"), 1, 3+i, 10, false, Alignment.CENTRE,
					VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, tempcj.get("pjkhcj"), kclist.size()+2, 3+i, 10, false, Alignment.CENTRE,
						VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, tempcj.get("jdkhcj"), kclist.size()+3, 3+i, 10, false, Alignment.CENTRE,
						VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, tempcj.get("xykhcj"), kclist.size()+4, 3+i, 10, false, Alignment.CENTRE,
						VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, tempcj.get("xyzfpm"), kclist.size()+5, 3+i, 10, false, Alignment.CENTRE,
						VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, tempcj.get("zhszcpzf"), kclist.size()+6, 3+i, 10, false, Alignment.CENTRE,
						VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, tempcj.get("pm"), kclist.size()+7, 3+i, 10, false, Alignment.CENTRE,
						VerticalAlignment.CENTRE, false, 300, Border.ALL);
		}
		wwb.write();
		wwb.close();
	}
}
