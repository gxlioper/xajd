package xgxt.qgzx.shgc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.daoActionLogic.StandardOperation;
import xgxt.qgzx.form.QgzxForm;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �ڹ���ѧģ��Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-03-16</p>
 */
public class QgzxShgcService {
	QgzxShgcDAO dao = new QgzxShgcDAO();
	
	/**
	 * ��ȡ���˵�λ�б�
	 * @return List
	 * */
	public List getYrdwList(){
		return dao.getYrdwList();
	}
	
	/**
	 * ��ȡ���˵�λ�����ϱ�ʱ��������Ϣ
	 * @param model
	 * @return List
	 * */
	public List getYrdwkhsbsj(QgzxForm model){
		return dao.getYrdwkhsbsj(model);
	}
	
	/**
	 * ��ȡ�����ϱ�ʱ���ͷ
	 * @return List
	 * */
	public List getKhsbsjToptr(){
		String[] column =  {"yrdwdm", "yrdwmc", "kssj", "jssj", "dlm"};
		String[] colCN = dao.getColumnNameCN(column, "view_yrdwkhsbsj");
		return dao.arrayToList(column, colCN);
	}
	
	/**
	 * �������˵�λ��ѯ�����ϱ�ʱ����Ϣ
	 * @param pk
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getKhsbsjOfYrdw(String pk){
		return dao.getKhsbsjOfYrdw(pk);
	}
	
	/**
	 * ���濼���ϱ�ʱ��������Ϣ
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveKhsbsjInfo(QgzxForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String pk = model.getPk();
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		String message = "";
		String tableName = "qgzx_yrdwkhsbsjb";
		
		String[] pkValue = pk.split("!!");
		for(int i=0; i<pkValue.length; i++){
			if(pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])){
				if(dao.checkExists(tableName, "yrdwdm", pkValue[i])){//��¼����
					//update
					flag = StandardOperation.update(tableName, new String[]{"kssj","jssj"}, new String[]{kssj,jssj}, "yrdwdm", pkValue[i], request);
				}else{//��¼������ 
					//insert
					flag = StandardOperation.insert(tableName, new String[]{"yrdwdm","kssj","jssj"}, new String[]{pkValue[i],kssj,jssj}, request);
				}		
				if(!flag){//����ʧ��
					message += "��" + (i+1) + "�����ݲ���ʧ�ܣ�\n";
				}
			}
		}
		request.setAttribute("mes", message);//����ʧ�ܵļ�¼��Ϣ
		return flag;
	}
	
	/**
	 * ��ѯѧ������ʱ�俼�������Ϣ
	 * @param model
	 * @return List
	 * */
	public List getXskhInfo(QgzxForm model){
		return dao.getXskhInfo(model);
	}
	
	/**
	 * ���ݴ����ѯ����
	 * @param tableName
	 * @param pk
	 * @param value
	 * @param str
	 * @return String
	 * */
	public String getXmmc(String tableName, String pk,String str, String value){
		return dao.getXmmc(tableName,pk,str,value);
	}
	
	/**
	 * ��ȡ��ӡ����ʱ����Ϣ��ͷ
	 * @return List
	 * */
	public List getPrintXskhTortr(){
		String[] column = {"xn","nd","yf","gwdm","xh","xm","bjmc","sqsj","gzsj","gzbx","gzqk"};
		String[] colCN ={"ѧ��","���","�·�","��λ����","ѧ��","����","�༶","����ʱ��","����ʱ��","��������","�������"};
		return dao.arrayToList(column, colCN);
	}
}
