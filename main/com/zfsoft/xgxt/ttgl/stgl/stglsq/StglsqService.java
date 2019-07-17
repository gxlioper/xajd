/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.stglsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

public class StglsqService extends SuperServiceImpl<StglsqForm, StglsqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";
	public boolean isExist(StglsqForm model) {
		boolean flag = false;
		if("save".equalsIgnoreCase(model.getType())) {
			 flag =  dao.isExist(model);
		}else{
			 flag = dao.isExistforUpdate(model);
		}
		return flag;
	}

	public boolean saveStsq(StglsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		String[] xhArray = model.getXhArray();
		String[] fzArray = model.getFzArray();
		String[] tzsxh = model.getTzsxh();
		String splc = dao.getShlcID();// ��ȡ��������
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model); 
		if(insertResult){
			//ssa
			List<String[]> paraList = new ArrayList<String[]>();
			//��֧���������
            List<String[]> tzsList = new ArrayList<String[]>();
			List<String[]> rxxList = new ArrayList<String[]>();
			List<String[]> zwList = new ArrayList<>();
			if(xhArray != null && xhArray.length > 0){
				for (int i = 0; i < xhArray.length; i++) {
						paraList.add(new String[]{xhArray[i],model.getSqid(),fzArray[i]});
						rxxList.add(new String[]{xhArray[i],model.getSqid(),"0",fzArray[i],"1"});
						zwList.add(new String[]{model.getSqid(),fzArray[i],"",Integer.toString(xhArray.length),""});
				}
				insertResult = dao.saveFzrb(paraList);
				if(!insertResult){
					throw new SystemException(MessageKey.SYS_SAVE_FAIL);
				}
			}
			if(tzsxh != null && tzsxh.length > 0){
                for (int i = 0; i < tzsxh.length; i++) {
                    tzsList.add(new String[]{tzsxh[i],model.getSqid()});
                    rxxList.add(new String[]{tzsxh[i],model.getSqid(),"0","��֧��","1"});
					zwList.add(new String[]{model.getSqid(),"��֧��","",Integer.toString(tzsxh.length),""});
                }
                insertResult = dao.saveTzsb(tzsList);
                if(!insertResult){
                    throw new SystemException(MessageKey.SYS_SAVE_FAIL);
                }
			}
			if(rxxList.size() > 0){
				insertResult = dao.saveStcyb(rxxList);
				if(!insertResult){
					throw new SystemException(MessageKey.SYS_SAVE_FAIL);
				}
			}
		}
		
		if( SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//�����������
			result = shlc.runSubmit(model.getSqid(),splc,model.getXh(),"xg_ttgl_stglsh.do","xg_ttgl_stglsq.do");
		}
		return result;
	}

	public boolean updateStsq(StglsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)&&!Constants.YW_YTH.equals(model.getShzt())){
			// ��ȡ��������
			model.setSplc(dao.getShlcID());
		}
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		boolean rs  = dao.delFzrb(model.getSqid());
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		rs = dao.delTzs(model.getSqid());
        if(!rs){
            throw new SystemException(MessageKey.SYS_SAVE_FAIL);
        }
		rs  = dao.delStcyb(model.getSqid());
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}

		String[] xhArray = model.getXhArray();
		String[] fzArray = model.getFzArray();
        String[] tzsxh = model.getTzsxh();
		boolean insertResult = super.runUpdate(model);
		if(insertResult){
			List<String[]> paraList = new ArrayList<String[]>();
            //��֧���������
            List<String[]> tzsList = new ArrayList<String[]>();
			List<String[]> rxxList = new ArrayList<String[]>();
			if(xhArray != null && xhArray.length > 0){
				for (int i = 0; i < xhArray.length; i++) {
						paraList.add(new String[]{xhArray[i],model.getSqid(),fzArray[i]});
						rxxList.add(new String[]{xhArray[i],model.getSqid(),"0",fzArray[i],"1"});
				}
				insertResult = dao.saveFzrb(paraList);
				if(!insertResult){
					throw new SystemException(MessageKey.SYS_SAVE_FAIL);
				}
			}
			if(tzsxh != null && tzsxh.length > 0){
                for (int i = 0; i < tzsxh.length; i++) {
                    tzsList.add(new String[]{tzsxh[i],model.getSqid()});
                    rxxList.add(new String[]{tzsxh[i],model.getSqid(),"0","��֧��","1"});
                }
                insertResult = dao.saveTzsb(tzsList);
                if(!insertResult){
                    throw new SystemException(MessageKey.SYS_SAVE_FAIL);
                }
            }
            if(rxxList.size() > 0){
                insertResult = dao.saveStcyb(rxxList);
                if(!insertResult){
                    throw new SystemException(MessageKey.SYS_SAVE_FAIL);
                }
            }
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//�����������
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"xg_ttgl_stglsh.do","xg_ttgl_stglsq.do");
			return result;
		}else{
			return insertResult;
		}
		
}

	public String[] deleteStsq(String[] ids) throws Exception {
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
				HashMap<String, String> hm=dao.getStsqxx(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?stsqDelete(delId.toArray(new String[]{})):0;
		if (i==delId.size()) {
			//ɾ�������˱�
			dao.delFzrbInfo(delId.toArray(new String[]{}));
			dao.delTzsbIfo(delId.toArray(new String[]{}));
			dao.delStcyInfo(delId.toArray(new String[]{}));
		}
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
}

	private int stsqDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}

	public boolean submitStsq(StglsqForm model) throws Exception {
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// ��ȡ��������
			model.setSplc(dao.getShlcID());
		}
		model.setShzt(Constants.YW_SHZ);
		boolean flag= dao.updateStsq(model);
		boolean result = false;
		if(flag){
			//�����������
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"xg_ttgl_stglsh.do","xg_ttgl_stglsq.do");
		}
		return result;
}

	public boolean cancelStsq(String sqid, String lcid) throws Exception {
		return shlc.firstStepCancle(sqid,lcid);
	}

	public boolean updateCxStsq(StglsqForm model) throws Exception {
		return dao.updateStsq(model);
	}

	public HashMap<String, String> getStsqInfo(StglsqForm model) {
		return dao.getStsqInfo(model);
	}

	public HashMap<String, String> getXsxx(String xh, String[] xhs) {
		return dao.getXsxx(xh,xhs);
	}

	public List<HashMap<String,String>> getFzrxx(String sqid) {
		return dao.getFzrxx(sqid);
	}

	public HashMap<String, String> getXsjbxxMore(String xh) {
		return dao.getXsjbxxMore(xh);
	}

    public List<HashMap<String, String>> getTzsxx(String sqid){
        return dao.getTzsxx(sqid);
    }
}
