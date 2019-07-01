package xgxt.pjpy.zjkjxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.zjcm.cssz.PjpyZjcmCsszModel;
import xgxt.utils.String.StringUtils;

import common.Globals;
import common.GlobalsVariable;

public class PjpyZjkjxyService extends PjpyTyService{
	
	/**
	 * �޸Ľ�ѧ��������ƺ�����
	 * @param model
	 * @return boolean
	 * */
	public boolean updateJxjrs(PjpyZjcmCsszModel model){
		PjpyZjkjxyDAO dao = new PjpyZjkjxyDAO();
		boolean result = true;//�������
		
		try{
			String pjzq = getPjpySqzq();
			if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
				pjzq = getZhcpSqzq();
			}
			if("xn".equalsIgnoreCase(pjzq)){
				model.setNd("��");
				model.setXq("��");
			}else if("xq".equalsIgnoreCase(pjzq)){
				model.setNd("��");
			}else if("nd".equalsIgnoreCase(pjzq)){
				model.setXn("��");
				model.setXq("��");
			}
			//�޸�ѧԺ����
			dao.updateJxjrs(model);
			//�޸�רҵ����
			model.setCpfw("zy");
			dao.updateJxjrs(model);
			//�޸İ༶����
			model.setCpfw("bj");
			dao.updateJxjrs(model);
		} catch (Exception e){
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ��ѧ����������
	 * @param form
	 * @return String
	 * */
	public String jxjrstz(PjpyZjkjxyActionForm form){
		PjpyZjkjxyDAO dao = new PjpyZjkjxyDAO();
		String message = "";//������ʾ��Ϣ
		//��ȡ���������ĵ�λ��ѧԺ��רҵ��༶
		String tzbm = form.getFs();//
		String cpfw = dao.getTzrsbm(form);//��ȡ������Χ
		
		form.setXn(StringUtils.isNotNull(form.getXn()) ? form.getXn() : "��");
		form.setXq(StringUtils.isNotNull(form.getXq()) ? form.getXq() : "��");
		form.setNd(StringUtils.isNotNull(form.getNd()) ? form.getNd() : "��");
		if(StringUtils.isNotNull(tzbm) && StringUtils.isNotNull(cpfw)){
			int cxrs = 0;//��������
			int sjbmrs = 0;//�ϼ���������
			int wtztjbmrs = 0;//û�е���������ͬ����������
			int tzrszh = 0;//�����������ܺ�
			tzrszh = getTzrszh(form);
			//�жϵ��������Ƿ񳬳�����
			if("bj".equalsIgnoreCase(tzbm)){//�༶
				sjbmrs = dao.getBjsszyrs(form);
				wtztjbmrs = dao.getWtjbjrs(form);				
				
			}else if("zy".equalsIgnoreCase(tzbm)){//רҵ
				sjbmrs = dao.getZyssxyrs(form);
				wtztjbmrs = dao.getWtjzyrs(form);
				//רҵ�¼����ŵ�����������רҵ�����ٵ�������
				//xjbmrs = dao.getZyxjbmrs(form);
			}else if("xy".equalsIgnoreCase(tzbm)){//ѧԺ
				//ѧԺ����Ҫ�ж���������
				//ѧԺ�¼����ŵ�����������ѧԺ�����ٵ�������
				//xjbmrs = dao.getXyxjbmrs(form);
			}
			if(sjbmrs <0){//�ϼ�����δ��������
				cxrs = sjbmrs;
			}else{
				cxrs = (wtztjbmrs+tzrszh)-sjbmrs;//��������
				cxrs = cxrs<0 ? 0 : cxrs;
			}
			//��������������
			if(cxrs==0 || "xy".equalsIgnoreCase(tzbm)){
				try{
					message= dao.updateJxjrstz(form,tzbm);
				} catch (Exception e) {
					message = "����ʧ�ܣ�";
					e.printStackTrace();
				}
			}else{
				if(cxrs <0){
					message = "�ϼ����Ż�δ������������ʱ���ܵ�����";
				}else{
					message = "����������������ϼ����ŵ���������������Ϊ:" + cxrs + "�ˣ�";
				}
			}
		}else{
			message = "δ���ñ�������ʱ���ܵ���������";
		}
		
		return message;
	}
	
	/**
	 * ����������
	 * @param form
	 * @return int
	 * */
	public int getTzrszh(PjpyZjkjxyActionForm form){
		String[] tzrszh = form.getJxjtzrs();
		int zrs = 0;
		for(int i=0; i<tzrszh.length; i++){
			if(StringUtils.isNotNull(tzrszh[i])){
				zrs += Integer.parseInt(tzrszh[i]);
			}
		}
		return zrs;
	}
	
	/**
	 * ��ȡ�����б�ֵ
	 * @param type
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getChkList(int type){
		DAO dao = DAO.getInstance();
		return dao.getChkList(type);
	}
	
	/**
	 * ��ȡ��ѧ������б�
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getJxjList(){
		DAO dao = DAO.getInstance();
		String sql = "select jxjdm en,jxjmc cn from jxjdmb";
		String[] outputValue = {"en", "cn"};
		return dao.getList(sql, new String[]{}, outputValue);
	}
	
	/**
	 * ��ȡ��ѧ������б�
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getJxjlbList(boolean flag){
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();
		DAO dao = DAO.getInstance();
		String sql = "select dm ,mc from jxjlbxxdmb order by dm";
		if(flag){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "");
			map.put("mc", "--��ѡ��--");
			rs.add(map);
		}
		String[] outputValue = {"dm", "mc"};
		rs.addAll(dao.getList(sql, new String[]{}, outputValue));
		return rs;
	}
	
	
	
	/**
	 * ��ȡ�����ƺŴ����б�
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getRychList(){
		DAO dao = DAO.getInstance();
		String sql = "select rychdm en,rychmc cn from rychdmb";
		String[] outputValue = {"en", "cn"};
		return dao.getList(sql, new String[]{}, outputValue);
	}	
	
	/**
	 * ��ѧ�������������ò�ѯ
	 * @param model
	 * @param output
	 * @return List<String[]>
	 * */
	public List<String[]> queryJxjsqrssz(PjpyZjkjxyActionForm model,String[] output){
		PjpyZjkjxyDAO dao = new PjpyZjkjxyDAO();
		return dao.queryJxjsqrsz(model, output);
	}
	
	/**
	 * ��ѧ�������������ò�ѯ,�޷�ҳ
	 * @param model
	 * @param output
	 * @return List<String[]>
	 * */
	public List<String[]> queryJxjsqrsszNotP(PjpyZjkjxyActionForm model,String[] output){
		PjpyZjkjxyDAO dao = new PjpyZjkjxyDAO();
		return dao.queryJxjsqrsszNotP(model, output);
	}
	
	
	/**
	 * ��ȡ��ͷ��Ϣ
	 * @param lx
	 * @param output
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTopTr(String lx, String[] output){
		DAO dao = DAO.getInstance();
		String[] cnColumn = {};
		if("jxjsqxs".equalsIgnoreCase(lx)){
			cnColumn = dao.getColumnNameCN(output, "view_pjpy_jxjsqxs");			
		}
		
		return dao.arrayToList(output, cnColumn);
	}
	
	/**
	 * ��ȡ��ͷ��Ӧ����������
	 * @param list
	 * @return String[]
	 * */
	public String[] getCn(List<HashMap<String, String>> list){
		String[] colCN = new String[list.size()];
		for(int i=0; i<list.size(); i++){
			colCN[i] = list.get(i).get("cn");
		}
		
		return colCN;
	}
	
	
	/**
	 * ���潱ѧ������������
	 * @param model
	 * @return String
	 * */
	public String saveJxjsqrssz(PjpyZjkjxyActionForm model){
		PjpyZjkjxyDAO dao = new PjpyZjkjxyDAO();
		return dao.saveJxjsqxs(model);		
	}
	
	/**
	 * ��ѧ�����
	 * @param pkValue
	 * @param shzd
	 * @return String
	 * */
	public String auditingJxj(HashMap<String, String[]> pkValue, 
			                  String shzd,
			                  String tableName){
		DAO dao = DAO.getInstance();
		String resultMsg = "";
		String[] pkV = pkValue.get("cbv");
		String key = "typj_rychsq".equalsIgnoreCase(tableName) 
		                 ? GlobalsVariable.PJPY_RYCH 
		                 : GlobalsVariable.PJPY_JXJ;
		//����
		String pk = GlobalsVariable.PJPY_JXJ.equalsIgnoreCase(key) 
		                 ? "xh||xn||jxjdm||sqsj||xq||nd"
		                 : "xh||xn||rychdm||nd||xq";
		
		for(int i=0; i<pkV.length; i++){
			//�ж������Ƿ���
			String msg = checkJxjrs(pk,pkV[i], shzd,key);
			if(StringUtils.isNotNull(msg)){
				if(pkV.length<2){
					resultMsg += "����ʧ�ܣ�" + msg + "\n";
				}else{
					resultMsg += "��" + (i+1) + "�в���ʧ�ܣ�" + msg + "\n";
				}
				
			}else{
				try{
					dao.runUpdate("update " + tableName + " set " + shzd + "='ͨ��' where " + pk + "=?", 
							      new String[]{pkV[i]});
				} catch (Exception e) {
					if(pkV.length<2){
						resultMsg += "����ʧ�ܣ�\n";
					}else{
						resultMsg += "��" + (i+1) + "�в���ʧ�ܣ�\n";
					}                     
				}
			}
		}
		
		return resultMsg;
	}
	
	/**
	 * �жϽ�ѧ�������Ƿ񳬹�����
	 * @param pkV
	 * @param shzd
	 * @return String
	 * */
	public String checkJxjrs(String pk,String pkV,String shzd,String key){
		String result = "";
		PjpyZjkjxyDAO dao = new PjpyZjkjxyDAO();
		HashMap<String, String> rsfpMap = dao.getRsfpMap(pk,pkV,shzd,key);
			
		//��ѯ��ѧ������������ֵ
		String zjz = rsfpMap.get("pkV");
		//�Ѿ����ͨ��������������˼���Χ�����ͨ����������
		int tgrs = Integer.parseInt(rsfpMap.get("tgrs"));
		
		//��ѧ������������������
		String jxjrs = dao.getJxjrs(zjz,shzd,key);
		
		if(StringUtils.isNull(jxjrs)){
			result = "������δ���䣬��ʱ�������ͨ����";
		}else{
			int fprs = Integer.parseInt(jxjrs);
			if(fprs<=tgrs){
				result = "���ͨ�������Ѿ��ﵽ����������";
			}
		}
		return result;
	}
	
}
