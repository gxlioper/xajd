/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package xgxt.gygl.sspy.pysq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @className	�� SspysqService
 * @description	�� ������������Service��
 * @author 		��CP��1352��
 * @date		�� 2018-4-27 ����03:15:46
 * @version 	V1.0
 */
public class SspysqService extends SuperServiceImpl<SspysqForm, SspysqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";
	public boolean isExist(SspysqForm model) {
		boolean flag = false;
		if("save".equals(model.getType())) {
			 flag =  dao.isExist(model);
		}else{
			 flag = dao.isExistforUpdate(model);
		}
		return flag;
	}

	public boolean saveSspy(SspysqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		// ��ȡ��������
		String splc = dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		if( SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			result = shlc.runSubmit(model.getSqid(),splc,model.getXh(),"sspy_sh.do","sspy_sq.do");//�����������
		}
		return result;
	}

	public List<HashMap<String, String>> getPyxmList() {
		return dao.getPyxmList();
	}

	public Map<String, String> getQsForPk(String ldqsxx) {
		return dao.getQsForPk(ldqsxx);
	}

	public List<HashMap<String, String>> getCwForQs(String ldqsxx) {
		String[] inputValue = new String[]{ldqsxx};
		String[] outputValue = new String[]{"xh","xm","xsbjmc","cwh"};
		return dao.getCwForQs(inputValue, outputValue);
	}

	public boolean updatSspy(SspysqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)&&!Constants.YW_YTH.equals(model.getShzt())){
			model.setSplc(dao.getShlcID());// ��ȡ��������
		}
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		boolean insertResult = super.runUpdate(model);
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//�����������
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"sspy_sh.do","sspy_sq.do");
			return result;
		}else{
			return insertResult;
		}
		
}

	public String[] deleteSspy(String[] ids) throws Exception {
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//��¼ɾ��id
			}else{
				HashMap<String, String> hm=dao.getSspyxx(str);
				noDel.append(hm.get("ldmc"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("qsh"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?sspyDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
}

	private int sspyDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
}

	public boolean submitSspy(SspysqForm model) throws Exception {
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			model.setSplc(dao.getShlcID());// ��ȡ��������
		}
		model.setShzt(Constants.YW_SHZ);
		boolean result1 = dao.updateSspy(model);
		boolean result = false;
		if(result1){
			//�����������
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"sspy_sh.do","sspy_sq.do");
		}
		return result;
}
//��������
	public boolean cancelSspy(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid,lcid);
	}

	public boolean updateSspysq(SspysqForm model) throws Exception {
		return dao.updateSspy(model);
	}

	public HashMap<String, String> getInfoBySqid(String sqid) {
		return dao.getInfoBySqid(sqid);
	}


	
	
	
	
}
