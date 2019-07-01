package xgxt.szdw.bjgm.bzrkh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewInit;

/**
 * 
* 
* �����ƣ�BjgmBzrkhService 
* ��������������ó�����ο���Service
* �����ˣ�yijd 
* ����ʱ�䣺2012-6-1 ����09:20:00 
* �޸ı�ע��  
* @version 
*
 */
public class BjgmBzrkhService extends CommService{
	BjgmBzrkhDAO dao=new BjgmBzrkhDAO();
	
	/**
	 * ��ҳ��ѯ
	 * @param model
	 * @param request
	 * @return
	 */
	public List<HashMap<String, String>> queryBzrkhxx(BjgmBzrkhForm model,HttpServletRequest request){
		try {
			return dao.queryBzrkhxx(model,request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ��ѯ�༶��Ϣ������ְ����
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> queryBjxxByZgh(BjgmBzrkhForm model) throws Exception{
		return dao.queryBjxxByZgh(model);
	}
	
	/**
	 * ��ȡtopTr
	 * @param mk
	 * @return
	 */
	public String[] getTopTr(String mk){
		String[] topTr = null;
		if("bjgm".equalsIgnoreCase(mk)){
			topTr = new String[]{"ѧ��","ѧ��","��������","ְ����","����","����","����","�꼶",
					"ѧ����","���˵÷�","�༶�÷�","�ܷ�"};
		}
		
		return topTr;
	}
	
	
	/**
	 * �������ְ����ο�����Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public int insertBzrshs(BjgmBzrkhForm model) throws Exception{
		List<HashMap<String, String>> list=getBjgmBzrkh(model);
		if(list!=null && list.size()>0){
			return -1;
		}
		int length=0;
		if(model!=null){
			if(model.getBb()!=null){
				length=model.getBb().length;
			}else{
				return -1;
			}
		}
		int[] rows=dao.insertBzrshs(model);
		int succeed=length - rows.length;
		return succeed;
	}
	
	/**
	 * ��ѯ�����ο������ݼ�����������
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getBjgmBzrkh(BjgmBzrkhForm model){
		return dao.getBjgmBzrkh(model);
	}
	
	/**
	 * ��ѯ�����ο������ݼ��������������������޸�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getBjgmBzrkhOnUpdate(BjgmBzrkhForm model) throws Exception{
		return dao.getBjgmBzrkhOnUpdate(model);
	}
	
	/**
	 * �޸İ����ο�����Ϣ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateArrBjgmBzrkh(BjgmBzrkhForm model,User user) throws Exception{
		List<HashMap<String, String>> list=getBjgmBzrkh(model);
		BjgmBzrkhForm czModel=new BjgmBzrkhForm();
		BeanUtils.copyProperties(czModel, model);
		//�����ǰ��ʦ����İ༶
		try {
			dao.deleteBjgmBzrkhByFutile(model);
			if(model!=null){
				//�༶Ϊ�ղ��ܱ���
				if(model.getBjdms()==null){
					return false;
				}
				if(list!=null){
					for (int i = 0; i < model.getBb().length; i++) {
						for (int j = 0; j < list.size(); j++) {
							if(model.getBjdms()[i].equals(list.get(j).get("bjdm"))){
								//�༶��������޸�����
								setAtrr(model, i, czModel);
								dao.updateBjgmBzrkh(czModel, user);
								break;
							}
							//��ѭ�����һ��
							if((j+1)==list.size()){
								//û���ҵ�ƥ��༶��˵����Ҫ�����༶�����Ϣ
								setAtrr(model, i, czModel);
								dao.insertBzrshs(czModel);
							}
						}
					}
				}else{
					//���ݿ��е�ǰѧ��û�е�ǰ��ʦ�����
					dao.insertBzrshs(model);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * ����ɾ������
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean deleteBjgmBzrkh(List<String[]> ids) throws Exception{
		return dao.deleteBjgmBzrkh(ids);
	}
	
	//�����࣬��������model
	public void setAtrr(BjgmBzrkhForm model,int i,BjgmBzrkhForm czModel){
		String[] array=null;
		array=new String[]{model.getBb()[i]};
		czModel.setBb(array);
		array=new String[]{model.getBjdf()[i]};
		czModel.setBjdf(array);
		array=new String[]{model.getBjdms()[i]};
		czModel.setBjdms(array);
		array=new String[]{model.getJl()[i]};
		czModel.setJl(array);
		array=new String[]{model.getJli()[i]};
		czModel.setJli(array);
		array=new String[]{model.getKjc()[i]};
		czModel.setKjc(array);
		array=new String[]{model.getRs()[i]};
		czModel.setRs(array);
		array=new String[]{model.getSq()[i]};
		czModel.setSq(array);
		array=new String[]{model.getWsaq()[i]};
		czModel.setWsaq(array);
	}
}
