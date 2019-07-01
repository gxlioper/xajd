package xgxt.pjpy.comm.zhcp.pdbx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_Ʒ�±���_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class ZhcpPdbxService extends PjpyCommService {
	
	ZhcpPdbxDAO dao = new ZhcpPdbxDAO();
	
	/**
	 * ��ȡ������A����Ҫ���۵�����ѧ��
	 * @param model
	 * @param user
	 * @param colList
	 * @return  List<String[]>
	 * @throws Exception
	 */
	public List<String[]>getXspjPdbx(ZhcpPdbxForm model,User user) throws Exception{
		//��ȡ��ʾ�ֶε�EN
		String[]colList=severTop(getTop(model,user),"en");
		
		return dao.getXspjPdbx(model, user, colList);
	}
	
	/**
	 * ��ȡ��ͷ�ֶ�
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getTop(ZhcpPdbxForm model, User user) {
		
		DAO dao=DAO.getInstance();
		ZhcpPdbxDAO pdbxDAO=new ZhcpPdbxDAO();
		//��ȡ���ñ������õ��ֶΡ��ֶ�����
		List<HashMap<String,String>>colList=pdbxDAO.getTop(model, user);
		//�ֶδ��루COLLIST��
		List<String>colArr=new ArrayList<String>();
		//�ֶ����ƣ�TOP��
		List<String>topArr=new ArrayList<String>();
		
//		colArr.add("xn");
//		topArr.add("ѧ��");
//		
//		colArr.add("xqmc");
//		topArr.add("ѧ��");
//		
//		colArr.add("nd");
//		topArr.add("���");
	
		colArr.add("pkValue");
		topArr.add("����");
		
		colArr.add("disabled");
		topArr.add("����");
		
		colArr.add("bpfr");
		topArr.add("ѧ��");
		
		colArr.add("bpfrxm");
		topArr.add("����");
		
		colArr.add("zf");
		topArr.add("�ܷ�");
		
		for(int i=0;i<colList.size();i++){
			HashMap<String,String>outPutMap=colList.get(i);
			colArr.add(outPutMap.get("xmdm"));
			topArr.add(outPutMap.get("xmmc"));
		}
		
		return dao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}
	
	/**
	 * ����top�б� ���ݻ�ȡ���ͻ�ȡ��en,cn��
	 * 
	 * @param topList
	 * @param hqlx
	 * @return List<HashMap<String, String>>
	 */
	public String[] severTop(List<HashMap<String, String>> topList, String hqlx) {

		List<String> outList = new ArrayList<String>();
		for (int i = 0; i < topList.size(); i++) {
			HashMap<String, String> topMap = topList.get(i);
			outList.add(topMap.get(hqlx));
		}
		return outList.toArray(new String[] {});
	}
	
	/**
	 * �޸�Ʒ�±��ַ�
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updatePdbxf(ZhcpPdbxForm model, User user) throws Exception {

		return dao.updatePdbxf(model,user,"xiugai");
	}
	
	/**
	 * �޸�ѧ���ύ״̬
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateXstjzt(ZhcpPdbxForm model, User user) throws Exception {
		dao.updatePdbxf(model,user,"tijiao");
		return dao.updateXstjzt(model, user);
	}
	
	/**
	 * �޸�ѧ���ύ״̬
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsckpf(ZhcpPdbxForm model, User user) throws Exception {
		
		return dao.getLsckpf(model, user);
	}
	
	public List<HashMap<String,String>>getLsckTop(ZhcpPdbxForm model, User user){
		
		DAO dao=DAO.getInstance();
		
		String[]en={"disabled","xh","xm","nj","xymc","zymc","bjmc","sfpf","sfqr","qrr"};
		String[]cn={"disabled","ѧ��","����","�꼶",Base.YXPZXY_KEY+"����","רҵ����","�༶����","�Ƿ�����","�Ƿ�ȷ��","ȷ����"};
		return dao.arrayToList(en, cn);
		
	}
	
	
	/**
	 * ��ȡ������A����Ҫ���۵�����ѧ��(��ʦ�鿴)
	 * @param model
	 * @param user
	 * @param colList
	 * @return  List<String[]>
	 * @throws Exception
	 */
	public List<String[]>getLcckPdbx(ZhcpPdbxForm model,User user) throws Exception{
		
		String[]colList=severTop(getTop(model,user),"en");
		
		return dao.getLcckPdbx(model, user, colList);
	}
	
	/**
	 * ��������ѧ�����۽��ȷ��
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateQrzt(ZhcpPdbxForm model,User user) throws Exception{
		
		return dao.updateQrzt(model, user);
	}
	
	/**
	 * ��ȡ�ɷ����֣� false����������true�����ԣ�
	 * @param model
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	public boolean getDypfsz (ZhcpPdbxForm model, User user) throws Exception {
		
		String kfpf = dao.getDypfsz(model, user);
		
		if ("no".equalsIgnoreCase(kfpf)) {
			
			return false;
		
		} else {

			return true;
		}
	}
	
	/**
	 * ��������ѧ�����۽��ȷ��(ȡ��ȷ��)
	 * ����ǰ̨��ֵ
	 * @param model
	 * @param user
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateZt(ZhcpPdbxForm model,User user) throws Exception{
		
		return dao.updateZt(model, user);
	}
	
	/**
	 * ��������Ϣ
	 * @param model
	 * @param user
	 * @return
	 */
	public HashMap<String,String>getPjrxx(ZhcpPdbxForm model,User user){
		
		
		return dao.getPjrxx(model, user);
	} 
	
	/**
	 * ��ȡָ���۲�������Ŀ��Ϣ
	 * @param model
	 * @param user
	 * @return  HashMap<String,String>
	 * @throws Exception 
	 */
	public boolean pdfjs(ZhcpPdbxForm model,User user) throws Exception {
	
		return dao.pdfjs(model,user);
	}
	
	/**
	 * ��ȡ����Ա�����༶�������
	 * @param model
	 * @param user
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>pdbxfTj(ZhcpPdbxForm model,User user){
		return dao.pdbxfTj(model, user);
	}
	
	/**
	 * ��ȡƷ�±��ַ�������Ϣ
	 * @param model
	 * @param user
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getPdbxSz(ZhcpPdbxForm model,User user){
		
		
		return dao.getPdbxSz(model, user);
	}

}