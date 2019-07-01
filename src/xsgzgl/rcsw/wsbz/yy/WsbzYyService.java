/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-5 ����09:59:33 
 */  
package xsgzgl.rcsw.wsbz.yy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-5-5 ����09:59:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsbzYyService extends SuperServiceImpl<WsbzYyForm, WsbzYyDao> {
	public boolean AddUpdateYyTimeCheck(){
		return dao.AddUpdateYyTimeCheck();
	}
	
	public boolean updateYyTimeCheck(String sqsj){
	    return dao.updateYyTimeCheck(sqsj);
	}
	
	public String produceHdplDay(){
		return dao.produceHdplDay();
	}
	
	public HashMap<String, String> produceHdplfWeek(){
		return dao.produceHdplfWeek();
	}
	
	public boolean isNowDateHaveYyjl(String xh){
	   return dao.isNowDateHaveYyjl(xh);
	}
	
	//ÿ��ѧ���� �������ֻ��������¼
	public boolean isEveryXhTwoRecode(String xh){
	   return dao.isEveryXhTwoRecode(xh);
	}
	
	public String getWeekZc(){
		HashMap<String, String> map = this.produceHdplfWeek();
		int dyzc = Integer.parseInt(map.get("zcnum"));
		String dy = map.get("dy");
		String weekofmon  = map.get("weekofmon");
		//���ؽ��
		String zcstr = "";
		//�ж��Ƿ���������֮ǰ
		boolean flag = this.checkPriviousFriday();
		int weeknum = Integer.parseInt(weekofmon);
		if(flag){
			if(weeknum+1>dyzc){
				
				zcstr= (Integer.parseInt(dy)+1 <= 12 ? (Integer.parseInt(dy)+1):1)+""+"��"+"��"+(weekofmon+1)+"��";
			}else{
				zcstr= Integer.parseInt(dy)+""+"��"+"��"+(weeknum+1)+"��";
			}
		}else{
			zcstr= Integer.parseInt(dy)+""+"��"+"��"+(weeknum)+"��";
		}
	    
	    return zcstr;
	}
	
	//�Ƿ񳬹���������
	public boolean isNotOverRssx(String fddm,String rs,String yyrq,String hdpl){
		return dao.isNotOverRssx(fddm, rs, yyrq,hdpl);
	}
	
	public List<HashMap<String, String>> getFdmcList(){
		return dao.getFdmcList();
	}
	
	
	public HashMap<String,String> getFdmcInfo(String fddm){
		return dao.getFdmcInfo(fddm);
	}
	
	public String getSyrs(String fddm,String yyrq) throws Exception{
		return dao.getSyrs(fddm, yyrq);
	}
	
	//ͬһ��ԤԼԤԼ�����ڣ�ѧ����������������
	public boolean isNotSameTwo(String fddm,String yyrq,String xh) throws Exception{
		return dao.isNotSameTwo(fddm, yyrq,xh);
	}
	
//	public boolean isNotOverRssx(String fddm,String rs,String yyrq,String xh){
//		return dao.isNotOverRssx(fddm, rs, yyrq, xh);
//	}
	
	/**
	 * @����:�ж��Ƿ���������֮ǰ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-21 ����03:31:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkPriviousFriday(){
	    return dao.checkPriviousFriday();
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:����yyrq�ֶ�Ϊ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-29 ����04:09:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateYyrqdaynull(String sqid) throws Exception{
		return dao.updateYyrqdaynull(sqid);
	}
	
	/**
	 * 
	 * @����:����yyzc�ֶ�Ϊ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-29 ����04:12:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateYyzcnull(String sqid) throws Exception{
		return dao.updateYyzcnull(sqid);
	}
	
	/**
	 * 
	 * @����: ��ȡԤԼ�ܴα�List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-27 ����06:47:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYyzcb(){
		List<HashMap<String, String>> yyzcList = dao.getYyzcb();
		for (int i = 0; i < yyzcList.size(); i++) {
			String yyrq = (yyzcList.get(i).get("yyzc").split("��"))[0];
			String compareDay = dao.getPriviousDay(yyrq);
			if(!dao.updateYyTimeCheck(compareDay)){
				yyzcList.remove(i);
				i = i-1;
			}

		}
		return yyzcList;
	}
	
	/**
	 * 
	 * @����:��ȡԤԼ���ڱ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-27 ����06:52:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getYyrqb(){
		List<HashMap<String, String>> yyrqList = dao.getYyrqb();
		for (int i = 0; i < yyrqList.size(); i++) {
			String yyrq = yyrqList.get(i).get("yyrq");
			String compareDay = yyrq;
			if(!dao.updateYyTimeCheck(compareDay)){
				yyrqList.remove(i);
				i = i-1;
			}

		}
		return yyrqList;
	}

	public boolean isExist(String yyrq) {
		// TODO Auto-generated method stub
		return dao.isExist(yyrq);
	}
}
