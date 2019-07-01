/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-27 ����04:08:08 
 */  
package xgxt.xtwh.mmzh;

import com.zfsoft.ms.MessageBody;
import com.zfsoft.ms.MessageOutputProvider;
import com.zfsoft.ms.MessageType;
import com.zfsoft.ms.mail.EmailMessageOutputProvider;
import com.zfsoft.ms.sms.SMSMessageOutputProvider;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.DAO.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-7-27 ����04:08:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class MmZhService extends SuperServiceImpl<MmZhForm, MmZhDao> {

	DAO d = DAO.getInstance();

	//������������ѡ��list,���û�������������������ѡ��
	public List<HashMap<String, String>> getMbList(){
		return dao.getMbList();
	}
	
	//��ȡ��½�û������õ���������
	public  HashMap<String, String> getmbjl(String yhm){
		return dao.getmbjl(yhm);
	}
	
	//��֤�û������Ƿ���ڸü�¼
	public boolean checkYhbExits(String yhm){
		boolean flag = false;
		String result = dao.checkYhbExits(yhm);
		if((!result.trim().equals("")) && (result != null)){
			flag = true;
		}
		return flag;
	}
	
	//��֤ѧ����������Ƿ���ڸü�¼
	public boolean checkXsmmbExits(String xh){
		boolean flag = false;
		String result = dao.checkXsmmbExits(xh);
		if((!result.trim().equals("")) && (result != null)){
			flag = true;
		}
		return flag;
	}
	
	//��֤�����һر����Ƿ���ڸü�¼
	public boolean checkMbwtExits(String yhm){
		boolean flag = false;
		String result = dao.checkMbwtExits(yhm);
		if((!result.trim().equals("")) && (result != null)){
			flag = true;
		}
		return flag;
	}
	
	public HashMap<String, String> getMbmap(String yhm){
		return dao.getMbmap(yhm);
	}

	/**
	 * �����һط�ʽ������״̬
	 * @param mmZhForm
	 * @return
	 * @throws SQLException
	 */
	public boolean updateZhfs(MmZhForm mmZhForm) throws SQLException {

		String s = "delete from XG_MMWH_CSSZ where zhfs = ?";
		List<String[]> list = new ArrayList<String[]>();
		String[] x1 = {mmZhForm.getEmail()};
		String[] x2 = {mmZhForm.getPhone()};
		String[] x3 = {mmZhForm.getMb()};
		list.add(x1);
		list.add(x2);
		list.add(x3);
		int[] i = d.runBatch(s,list);

		String sql = "insert into XG_MMWH_CSSZ(zhfs,qyzt) VALUES(?,?)";
		List<String[]> list2 = new ArrayList<String[]>();
		String[] s1 = {mmZhForm.getEmail(),mmZhForm.getEmail_qyzt()};
		String[] s2 = {mmZhForm.getPhone(),mmZhForm.getPhone_qyzt()};
		String[] s3 = {mmZhForm.getMb(),mmZhForm.getMb_qyzt()};
		list2.add(s1);
		list2.add(s2);
		list2.add(s3);
		int[] j = d.runBatch(sql,list2);
		return j.length == 3;
	}

	/**
	 * ��ȡ�һط�ʽ
	 * @return
	 */
	public List<HashMap<String,String>> getzt(){
		String sql = "select * from XG_MMWH_CSSZ";
		return d.getListNotOut(sql,new String[]{});
	}

	/**
	 * ��ȡ���õ��һط�ʽ
	 * @return
	 */
	public List<HashMap<String,String>> getzhfs(){
		String sql = "select * from XG_MMWH_CSSZ where qyzt = 1";
		return d.getListNotOut(sql,new String[]{});
	}

	/**
	 * ��ȡ�����һط�ʽ
	 */
	public String getZhfs(String yhm,String zhfs){
		String zhfsResult;
		String sql = "select dzyx,lxdh from xsxxb where xh = ?";
		String[] result = d.getOneRs(sql,new String[]{yhm},new String[]{"dzyx","lxdh"});
		if(result == null){
			//��ʦ�û�
			String s = "select dzyx,lxdh from FDYXXB where zgh = ? ";
			result = d.getOneRs(s,new String[]{yhm},new String[]{"dzyx","lxdh"});
		}

		if("email".equals(zhfs)){
			zhfsResult = result[0];
		} else {//�绰
			zhfsResult = result[1];
		}
		return zhfsResult;
	}

	/**
	 * ������֤����û�
	 * @return
	 */
	public boolean sendMessage(String yzm,String zh,String zhfs) throws Exception {

		MessageBody message = new MessageBody(MessageType.EMAIL);
//		// �����ռ��˵�����
		message.getBody().put("sendTo", zh);
//		// �����ʼ�����
		message.getBody().put("subject", "����ѧ��ϵͳ�����һ�" );
		message.getBody().put("content", yzm);
		MessageOutputProvider provider;
		if("email".equals(zhfs)){
			provider = new EmailMessageOutputProvider();
		} else {
			provider = new SMSMessageOutputProvider();
		}
		boolean flag = provider.output(message);
		return flag;
	}
}
