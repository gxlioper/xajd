/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-10-30 ����05:30:15 
 */  
package xgxt.dtjs.tsdzb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ɫ(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-10-30 ����05:30:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsdzbService extends SuperServiceImpl<TsdzbForm, TsdzbDao>{
	private TsdzbDao dao = new TsdzbDao();
	
	private static String WSH = "0";
	
	private static String TG = "1";
	
	private static String WTG = "2";
	
	/** 
	 * @����:���ӵ�֧��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-30 ����07:15:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean addTsdzb(TsdzbForm form) throws Exception{
		String unid = UniqID.getInstance().getUniqIDHash();
		form.setDzbid(unid);
		form.setShzt(WSH);
		boolean result = runInsert(form);
		if(null != form.getBjdms()){
			result = dao.plInsert(form.getBjdms(), form.getDzbid());
		}
		return result;
	}
	
	/** 
	 * @����:���µ�֧��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-31 ����03:22:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateTsdzb(TsdzbForm form) throws Exception{
		boolean result = dao.runUpdate(form);
		if(result && !"sh".equalsIgnoreCase(form.getType())){
			deleteGlByDzbId(form);
		}
		if(!"sh".equalsIgnoreCase(form.getType())){
			if(null != form.getBjdms() && form.getBjdms().length > 1){				
				dao.plInsert(form.getBjdms(), form.getDzbid());
			}
			if(null != form.getBjdmms() && form.getBjdmms().length > 1){
				if(null != form.getBjdms() && form.getBjdms().length > 0){					
					ArrayList<String> bjdms = new ArrayList<String>();
					for(String str : form.getBjdms()){
						bjdms.add(str);
					}
					
					ArrayList<String> bjdmms = new ArrayList<String>();
					for(String str : form.getBjdmms()){
						bjdmms.add(str);
					}
					
					for(Iterator<String> it = bjdmms.iterator();it.hasNext();){
						String s = it.next();
						for(String str : bjdms){
							if(str.equals(s)){
								it.remove();
							}
						}
					}
					if(bjdmms.size() > 0){					
						dao.plInsert(bjdmms.toArray(new String[]{}), form.getDzbid());
					}
				}else{
					dao.plInsert(form.getBjdmms(), form.getDzbid());
				}
			}
		}
		return result;		
	}
	
	/**
	 * @throws Exception  
	 * @����:(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-30 ����07:18:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean deleteGlByDzbId(TsdzbForm form) throws Exception{
		return dao.deleteByDzbId(form.getDzbid());
	}
	
	/** 
	 * @����:�����û���ݻ�ȡ�༶�б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-31 ����09:56:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getBjList(User user,String dzbid){
		return dao.getBjList(user,dzbid);
	}
	
	/**
	 * @throws SQLException  
	 * @����:���ݵ�֧��id��ȡ�༶�б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-31 ����03:59:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<String> �������� 
	 * @throws 
	 */
	public List<String> getBjListBydzbId(String dzbId) throws SQLException{
		return dao.getBjListByDzbId(dzbId);
	}
	
	/** 
	 * @����:����idsɾ������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-31 ����07:37:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @throws Exception
	 * void �������� 
	 * @throws 
	 */
	public void delGlByIds(String[] ids) throws Exception{
		dao.delGlByIds(ids);
	}
	
	/**
	 * @description	�� ��ȡ������֧����Ϣ
	 * @author 		�� ������1282��
	 * @date 		��2017-11-1 ����09:06:24
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public TsdzbForm getTsdzbxx(TsdzbForm model) throws Exception{
		TsdzbForm form = getModel(model);
		String bjxx = dao.getTsdzbXX(model.getDzbid());
		form.setBjxx(bjxx);
		return form;
		
	}
	
	/**
	 * @description	�� ��ȡ��֧������б�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-1 ����10:53:47
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getPageListForSh(TsdzbForm t, User user) throws Exception{
		return dao.getPageListForSh(t, user);
	}
	
	/**
	 * @description	�� ��������
	 * @author 		�� ������1282��
	 * @date 		��2017-11-1 ����03:30:16
	 * @return
	 * @throws SQLException 
	 */
	public boolean plCx(String[] dzbids) throws SQLException{
		return dao.cx(dzbids);
	}
	
	/**
	 * @description	�� ��ɫ��֧������
	 * @author 		�� ������1282��
	 * @date 		��2017-11-2 ����10:19:49
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getAllListForTsdzsh(TsdzbForm t, User user) throws Exception {
		return dao.getAllListForTsdzbSh(t, user);
	}
	
	/**
	 * @description	�� �Ƿ���ڵ�֧��
	 * @author 		�� ������1282��
	 * @date 		��2017-11-3 ����05:21:24
	 * @return
	 */
	public boolean isExist(String dzbmc,String dzbId){
		return dao.countTsdzbMc(dzbmc, dzbId);
	}
	
	/**
	 * @description	����ȡѧԺ�б�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-30 ����10:35:01
	 * @return
	 */
	public List<HashMap<String,String>> getXyList(String userStatus,String userName){
		return dao.getXyList(userStatus,userName);
	}
	
	/**
	 * @description	�� ��ȡרҵ�б�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-30 ����10:50:20
	 * @param xydm
	 * @return
	 */
	public List<HashMap<String,String>> getZyList(String xydm,String userStatus,String userName){
		return dao.getZyList(xydm,userStatus,userName);
	}
	
	/**
	 * @description	�� ��ȡ�༶�б�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-30 ����10:51:33
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return
	 */
	public List<HashMap<String,String>> getbjList(String dzbid,String xydm,String zydm,String nj,String userStatus,String userName){
		return dao.getbjList(dzbid,xydm,zydm,nj,userStatus,userName);
	}
	
	/**
	 * @description	�� ��ȡ�꼶�б�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-30 ����10:52:27
	 * @return
	 */
	public List<HashMap<String,String>> getNjList(){
		return dao.getNjList();
	}
}
