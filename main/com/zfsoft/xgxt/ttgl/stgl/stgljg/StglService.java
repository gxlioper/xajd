/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.stgljg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

public class StglService extends SuperServiceImpl<StglForm, StglDao>{
	public static String _BCZSCID="-1";
	public boolean isExist(StglForm model) {
		boolean flag = false;
		if("save".equalsIgnoreCase(model.getType())) {
			 flag =  dao.isExist(model);
		}else{
			 flag = dao.isExistforUpdate(model);
		}
		return flag;
	}

	public List<HashMap<String, String>> getbmList() {
		return dao.getbmList();
	}

	public List<HashMap<String, String>> getStuCx(StglForm myForm, User user) throws Exception {
		return dao.getStuCx(myForm,user);
	}

	public boolean saveSt(StglForm model) throws Exception {
		boolean rs = true;
		String[] xhArray = model.getXhArray();
		String[] fzArray = model.getFzArray();
		model.setStzt("1");//默认结果菜单增加的就是正式的社团
		if(StringUtils.isNotNull(model.getJgid())){//修改保存
			rs = dao.delFzrb(model.getJgid());
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			rs = dao.delStcyb(model.getJgid());
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			rs = dao.runUpdate(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{//增加保存
			model.setJgid(UniqID.getInstance().getUniqIDHash());
			model.setSjly("0");
			rs = dao.runInsert(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		List<String[]> paraList = new ArrayList<String[]>();
		List<String[]> rxxList = new ArrayList<String[]>();
        //团支书插入数据
        List<String[]> tzsList = new ArrayList<String[]>();
		String[] tzsxh = model.getTzsxh();
		if(xhArray != null && xhArray.length > 0){
			for (int i = 0; i < xhArray.length; i++) {
					paraList.add(new String[]{xhArray[i],model.getJgid(),fzArray[i]});
					rxxList.add(new String[]{xhArray[i],model.getJgid(),"1",fzArray[i],"0"});
			}
			rs = dao.saveFzrb(paraList);//增加到负责人表
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
        if(tzsxh != null && tzsxh.length > 0){
		    for(int i=0;i<tzsxh.length;i++){
                tzsList.add(new String[]{tzsxh[i],model.getJgid()});
                rxxList.add(new String[]{tzsxh[i],model.getJgid(),"1","团支书","0"});
            }
            rs = dao.saveTzsb(tzsList);
            if(!rs){
                throw new SystemException(MessageKey.SYS_SAVE_FAIL);
            }
        }
        if(rxxList.size() > 0){
            rs = dao.saveStcyb(rxxList);//把负责人增加到社团成员表
            if(!rs){
                throw new SystemException(MessageKey.SYS_SAVE_FAIL);
            }
        }
		return rs;
	}

	public List<HashMap<String,String>> getFzrxx(StglForm myForm) {
		return dao.getFzrxx(myForm);
	}

    public List<HashMap<String,String>> getTzsxx(StglForm myForm) {
        return dao.getTzsxx(myForm);
    }

	public String getfdyxm(String stzdls) {
		return dao.getfdyxm(stzdls);
	}

	public boolean delFzrbInfo(String[] ids) throws Exception {
		return dao.delFzrbInfo(ids);
	}

	public HashMap<String, String> getStxxInfo(StglForm myForm) {
		return dao.getStxxInfo(myForm);
	}

	public String[] deleteStxxjg(String[] ids) throws Exception {
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//记录删除id
			}else{
				HashMap<String, String> hm=dao.getStxxjg(str);
				noDel.append(hm.get("stqc"));
				noDel.append("&nbsp;");
				noDel.append("</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?stxxDelete(delId.toArray(new String[]{})):0;
		if (i==delId.size()) {
			//删除负责人表
			dao.delFzrbInfo(delId.toArray(new String[]{}));
            dao.delTzsInfo(delId.toArray(new String[]{}));
			dao.delStcyInfo(delId.toArray(new String[]{}));
		}
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}

	private int stxxDelete(String[] ids) throws Exception {
		return runDelete(ids);
	}

	public boolean stzx(StglForm model) throws Exception {
		return dao.stzx(model);
	}

	public List<HashMap<String, String>> getcyList(StglForm myForm) {
		return dao.getcyList(myForm);
	}
	/**
	 * 获取学生组织经费来源集合
	 */
	public List<HashMap<String, String>> getXszzjflyList(){
		return dao.getXszzjflyList();
	}
    /**
     * 获取年度组织状态
     */
	public List<HashMap<String, String>> getNdzzztList(){
        return dao.getNdzzztList();
    }
    /**
     * 获取组织类别
     */
    public List<HashMap<String, String>> getZzlbList(){
        return dao.getZzlbList();
    }
}
