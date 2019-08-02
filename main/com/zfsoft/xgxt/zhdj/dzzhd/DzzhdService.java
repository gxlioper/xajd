package com.zfsoft.xgxt.zhdj.dzzhd;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.UniqID;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���-����֯�������-����֯�����
 * @�๦������: ���Ž�������service
 * @����: ��ˬ [����:1730]
 * @ʱ��: 2019/7/19 16:02
 */
public class DzzhdService extends SuperServiceImpl<DzzhdForm,DzzhdDao> {

    public DzzhdService(){}
    public static volatile DzzhdService dzzhdService = null;
    public static DzzhdService getDzzhdService(){
        if(dzzhdService == null){
            synchronized (DzzhdService.class){
                if(dzzhdService == null){
                    dzzhdService = new DzzhdService();
                }
            }
        }
        return dzzhdService;
    }

    private DzzhdDao dzzhdDao = DzzhdDao.getDzzhdDao();
    
    /**
     * @����: ���ݻid��ȡ���Ϣ
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/19 16:41
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param hdId 
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     **/
    public HashMap<String,String> getById(String hdId){
        HashMap<String,String> hdInfo = dzzhdDao.getById(hdId);
        if(hdInfo != null && hdInfo.size() > 0
        && StringUtils.isNotNull(hdInfo.get("mxdx"))){
            hdInfo.put("mxdxmc",getMxdxmc(hdInfo.get("mxdx")));
        }
        return hdInfo;
    }

    /**
     * @����: ��ȡ�ҷ����Ļ��Ϣ�б�
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/19 17:21
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param model
	* @param user
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     **/
    public List<HashMap<String,String>> getFbPageList(DzzhdForm model, User user){
        List<HashMap<String,String>> fbPageList =  dzzhdDao.getFbPageList(model,user);
        return joinZzmmmcList(fbPageList);
    }


    /**
     * @����: ��ȡ������ò��Ϣ�б�
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/19 17:22
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     **/
    public HashMap<String,String> getZzmmInfo(){
        HashMap<String,String> zzmmInfo = new HashMap<>();
        List<HashMap<String,String>> zzmmList = dzzhdDao.getZzmmList();
        if(zzmmList == null || zzmmList.size() == 0){
            return null;
        }
        for(HashMap<String,String> item : zzmmList){
            zzmmInfo.put(item.get("zzmmdm"),item.get("zzmmmc"));
        }
        return zzmmInfo;
    }
    //��ȡ������ò�б�
    public List<HashMap<String,String>> getZzmmList(){
        return dzzhdDao.getZzmmList();
    }
    //����������ò��Ϣ
    private  List<HashMap<String,String>> joinZzmmmcList( List<HashMap<String,String>> list){
        if(list!= null && list.size() > 0){
            StringBuilder mxdxmc;
            HashMap<String,String> zzmmInfo = getZzmmInfo();
            for(HashMap<String,String> item : list){
                if(StringUtils.isNotNull(item.get("mxdx"))){
                    String[] mxdxs = item.get("mxdx").split(",");
                    mxdxmc = new StringBuilder();
                    for(String zzmmdm : mxdxs){
                        mxdxmc.append(zzmmInfo.get(zzmmdm)+",");
                    }
                    if(mxdxmc.length() > 0 ){
                        item.put("mxdxmc",mxdxmc.substring(0,mxdxmc.length()-1));
                    }
                }
            }
        }
        return list;
    }

    private String getMxdxmc(String mxdxdms){
        HashMap<String,String> zzmmInfo = getZzmmInfo();
        String[] mxdxs = mxdxdms.split(",");
        StringBuilder mxdxmc = new StringBuilder();
        for(String zzmmdm : mxdxs){
            mxdxmc.append(zzmmInfo.get(zzmmdm)+",");
        }
        if(mxdxmc.length() > 0 ){
            return mxdxmc.substring(0,mxdxmc.length()-1);
        }
        return "";
    }

    /**
     * @����: ����֯���Ϣ����
     * @����: ��ˬ[����:1730]
     * @����: 2019/8/1 10:40
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param model
	* @param user
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     **/
    public HashMap<String,Object> hdSave(DzzhdForm model,User user){
        HashMap<String,Object> result = new HashMap<>();
        if(StringUtils.isNull(model.getHdmc())){
            result.put("code",0);
            result.put("msg","����Ʋ���Ϊ��!");
        }
        if(StringUtils.isNotNull(model.getId())){
            //���ڻid�޸�
            HashMap<String,String> dzzhdInfo = dzzhdDao.getById(model.getId());
            if(dzzhdInfo != null && model.getId().equals(dzzhdInfo.get("id"))){
                boolean flag = dzzhdDao.update(model);
                if(flag){
                    result.put("code",1);
                    result.put("msg","����ɹ�!");
                    result.put("data",model.getId());
                }else{
                    result.put("code",0);
                    result.put("msg","����ʧ��!");
                }
            }else{
                result.put("code",0);
                result.put("msg","���Ϣ������!");
            }
        }else{
            //����
            String id = UniqID.getInstance().getUniqIDHash().toUpperCase();
            model.setCjsj(DateTranCnDate.timeStampToDate(System.currentTimeMillis()));
            model.setCjr(user.getUserName());
            model.setCjrxm(user.getRealName());
            model.setId(id);
            boolean flag = dzzhdDao.add(model);
            if(flag){
                result.put("code",1);
                result.put("msg","����ɹ�!");
                result.put("data",id);
            }else{
                result.put("code",0);
                result.put("msg","����ʧ��!");
            }
        }
        return result;
    }

    public HashMap<String,Object> removeById(String hdid){
        HashMap<String,Object> result = new HashMap<>();
        //�ж��Ƿ���ڻ��Ա��Ϣ
        Integer hdryNums = dzzhdDao.getHdryCount(hdid);
        if(hdryNums != null && hdryNums.intValue() > 0){
            result.put("code",0);
            result.put("msg","�˻������Ա�����������Ƴ���Ա��Ϣ��");
            return result;
        }
        boolean flag = dzzhdDao.removeById(hdid);
        if(flag){
            result.put("code",1);
            result.put("msg","ɾ���ɹ�!");
        }else{
            result.put("code",0);
            result.put("msg","ɾ��ʧ��!");
        }
        return result;
    }

    /**
     * @����: ��ȡ������Ա��Ϣ
     * @����: ��ˬ[����:1730]
     * @����: 2019/8/1 16:53
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param model
	* @param user
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     **/
    public List<HashMap<String,String>> getHdRyxxList(DzzhdForm model,User user){
        List<HashMap<String,String>> list = null;
        if("1".equals(model.getJoinStatus())){
            //�Ѳ�����Ա�б�
            list = dzzhdDao.getDzzRyList(model,user);
        }else{
            //�������Ա�б�(��ѯ���ݷ�ΧΪ��Ӧ���õ�������ò��Ա)
            HashMap<String,String> hdInfo = dzzhdDao.getById(model.getId());
            String mxdx = hdInfo.get("mxdx");
            if(StringUtils.isNotNull(mxdx)){
                model.setMxdx(mxdx);
            }
            list = dzzhdDao.getStuxxList(model,user);
        }
        return list;
    }

    /**
     * @����: ������ӵ��Ż��Ϣ
     * @����: ��ˬ[����:1730]
     * @����: 2019/8/2 11:18
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param hdid
	* @param xhs
     * @return boolean
     **/
    public boolean batchAdd(String hdid,String xhs){
        return dzzhdDao.batchAdd(hdid,xhs);
    }
    /**
     * @����: �����Ƴ����Ż��Ϣ
     * @����: ��ˬ[����:1730]
     * @����: 2019/8/2 11:18
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param hdid
	* @param xhs
     * @return boolean
     **/
    public boolean batchRemove(String hdid,String xhs){
        return dzzhdDao.batchRemove(hdid,xhs);
    }

    /**
     * @����: ���ݻid��ѧ�Ż�ȡ���Ϣ
     * @����: ��ˬ[����:1730]
     * @����: 2019/8/2 11:17
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param hdid �id
	* @param xh ѧ��
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     **/
    public HashMap<String,String> getHdxdInfo(String hdid,String xh){
        return dzzhdDao.getHdxdInfo(hdid,xh);
    }

    /**
     * @����: ��ĵñ���
     * @����: ��ˬ[����:1730]
     * @����: 2019/8/2 11:18
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param data
     * @return boolean
     **/
    public boolean saveHdxd(HashMap<String,String> data){
        return dzzhdDao.saveHdxd(data);
    }


}
