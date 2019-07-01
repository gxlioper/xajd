package xgxt.gygl.gyfzr.gyfdy;

import java.util.HashMap;
import java.util.List;
import xgxt.DAO.DAO;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.GyglCommService;
import xgxt.utils.GetTime;

public class GyglGyfdyService extends GyglCommService{
	
	GyglGyfdyDAO dao=new GyglGyfdyDAO();
	
	
	public List<String[]>getGyfdy(GyglGyfdyForm myForm) throws Exception{
		
		return dao.getGyfdy(myForm);
	}
	
	/**
	 * ���л�ȡ
	 * @param mklx
	 * @return
	 */ 
	public List<HashMap<String, String>> getTopTr(String mklx) {

		DAO dao = DAO.getInstance();
		
		String[] colListEN = null;
		String[] colListCN = null;
		if ("gyfdy".equalsIgnoreCase(mklx)) {
			colListEN = new String[] { "�û���", "����", "���ڲ���", "��Ͻ¥����" };
			colListCN = new String[] { "�û���", "����", "���ڲ���", "��Ͻ¥����" };
		}else if ("fdyxx".equalsIgnoreCase(mklx)) {
			colListEN = new String[] { "�û���", "����", "�Ա�", "ְ��", "���ڲ���" };
			colListCN = new String[] { "�û���", "����", "�Ա�", "ְ��", "���ڲ���" };
		}

		return dao.arrayToList(colListEN, colListCN);

	}
	
	/**
	 * ��ȡ��Ԣ����Ա��Ͻ¥��
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getGyfdyGxld(String yhm){
		return dao.getGyfdyGxld(yhm);
	}
	
	/**
	 * ��ȡ����Ա��Ϣ
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getGyfdyxx(GyglGyfdyForm myForm) throws Exception{
		return dao.getGyfdyxx(myForm);
	}
	
	public List<HashMap<String,String>>getZwList(GyglGyfdyForm myForm){
		
		return dao.getZwList(myForm);
	}
	
	/**
	 * ��ȡ�����б�
	 * @param myForm
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getBmdmList(GyglGyfdyForm myForm){
		
		return dao.getBmdmList(myForm);
	}
	
	/**
	 * ��ȡ����Ա��Ϣ
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String>getGyfdyMap(GyglGyfdyForm myForm) throws Exception{
	
		return dao.getGyfdyMap(myForm);
	}
	
	/**
	 * ��ȡУ���б�
	 * @return
	 */
	public List<HashMap<String,String>>getXqxx(){

		return dao.getXqxx();
	}
	
	/**
	 * ��ȡ԰���б�
	 * @return
	 */
	public List<HashMap<String,String>>getYqxx(GyglGyfdyForm myForm){
		return dao.getYqxx(myForm);
	}
	
	/**
	 * ��ȡ԰���б�
	 * @return
	 */
	public List<HashMap<String,String>>getYqxxDWR(String xqdm){
		GyglGyfdyForm myForm=new GyglGyfdyForm();
		myForm.setXqdm(xqdm);
		return dao.getYqxx(myForm);
	}
	
	public List<HashMap<String,String>>getYfpldList(String yhm){

		return dao.getYfpldList(yhm);
	}
	
	public List<HashMap<String,String>>getWfpldList(String[]cxjg){

		return dao.getWfpldList(cxjg);
	}
	
	public List<HashMap<String,String>>getWfpldxxList(GyglGyfdyForm myForm){
		String[]cxjg=new String[5];
		cxjg[0]=myForm.getYhm();
		cxjg[1]=myForm.getSelect_xqdm();
		cxjg[2]=myForm.getSelect_yqdm();
		cxjg[3]=myForm.getXb();
		cxjg[4]=myForm.getLdmc();
		
		return dao.getWfpldList(cxjg);
	}
	
	/**
	 * ���湫Ԣ����Ա��Ͻ¥��
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean saveGxld(GyglGyfdyForm myForm) throws Exception{
		
		GyglGyfdyModel model=new GyglGyfdyModel();
		
		String tableName="xg_gygl_ldglb";
		
		String yhm=myForm.getYhm();
		String[]yfpldArr=myForm.getYfpldArr();
		String fprq=GetTime.getNowTime2();
		String[] arrzd = { "yhm", "lddm", "fprq" };
		
		String[]pkValue=new String[1];
		pkValue[0]=yhm;
		
		if(yfpldArr!=null && yfpldArr.length>0){
			String[]fprqArr=new String[yfpldArr.length];
			String[]yhmArr=new String[yfpldArr.length];
			for(int i=0;i<yfpldArr.length;i++){
				yhmArr[i]=yhm;
				fprqArr[i]=fprq;
			}
			model.setLddm(yfpldArr);
			model.setYhm(yhmArr);
			model.setFprq(fprqArr);
		}
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(" yhm ");
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		
		//ͨ�ñ��淽��
		User user=new User();
		return saveInfoToDb(saveForm, model, user);
	}
}
