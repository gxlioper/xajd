package com.zfsoft.xgxt.xszz.xfjm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.xgsz.CsszModel;
import common.newp.StringUtil;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:
 * @�๦������:
 * @����: ��ˬ [����:1730]
 * @ʱ��: 2019/7/3 10:49
 */
public class XfjmService extends SuperServiceImpl<XfjmForm,XfjmDao> {
    public XfjmService(){}
    public static volatile XfjmService xfjmService = null;
    public String url = "";
    public static XfjmService getXfjmService(){
        if(xfjmService == null){
            synchronized (XfjmService.class){
                if(xfjmService == null){
                    xfjmService = new XfjmService();
                }
            }
        }
        return xfjmService;
    }

    private XfjmDao xfjmDao = XfjmDao.getXfjmDao();
    private XfjmCsszService csszService = new XfjmCsszService();
    private ShlcInterface shlc = new CommShlcImpl();
    private ShlcDao shlcDao = new ShlcDao();
    
    /**
     * @����: ��������id��ȡѧ�Ѽ���������Ϣ
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/3 14:14
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param sqid 
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     **/
    public HashMap<String,String> getById(String sqid){
        return dao.getById(sqid);
    }

    /**
     * @����: ����ѧ�Ѽ���������Ϣ
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/4 13:34
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param model
	* @param user
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String,Object> sqSave(XfjmForm model, User user){
        Map<String, Object> result = new HashMap<>();
        try {
            if (StringUtils.isNull(model.getXh()) && "stu".equals(user.getUserType())) {
                model.setXh(user.getUserName());
            }
            //�ж�ʱ����Ƿ���
            CsszModel cssz = csszService.getModel();
            if (cssz != null && !"1".equals(cssz.getSqkg())) {
                result.put("code", 0);
                result.put("msg", "��δ����ѧ�Ѽ�������!");
                return result;
            }
            //�ж�ѧ���Ƿ�����������϶�
            HashMap<String, String> knsrdjg = getKnsrdjg(model.getXh(), Base.currXn);
            if (knsrdjg == null || StringUtils.isNull(knsrdjg.get("guid"))) {
                result.put("code", 0);
                result.put("msg", "��������������϶�����д˲���!");
                return result;
            }
            if (StringUtils.isNotNull(model.getId())) {
                //�޸�
                //�ж����״̬���ύ���״ֻ̬��Ϊδ�ύ 5����ˣ�
                if(!"0".equals(model.getShzt()) && !"5".equals(model.getShzt())){
                    result.put("code", 0);
                    result.put("msg", "�ύ��������������!");
                    return result;
                }
                HashMap<String,String> xfjm = xfjmDao.getById(model.getId());
                if(xfjm != null && StringUtils.isNotNull(xfjm.get("shzt"))){
                    //�ж�֮ǰ״̬
                    String oldShzt = xfjm.get("shzt");
                    //�����
                    if(!"0".equals(oldShzt)){
                        result.put("code", 0);
                        result.put("msg", "ֻ���޸�δ�ύ��������Ϣ!");
                        return result;
                    }
                    //���֮ǰΪδ�ύ�������ύ���������������
                    if("0".equals(oldShzt) && "5".equals(model.getShzt())){
                        //���������
                        try {
                             shlc.runSubmit(model.getId(), xfjm.get("shlc"), model.getXh(), "xszz_new_xfjmsh.do", "xszz_new_xfjmsq.do");
                        }catch (Exception e){
                            e.printStackTrace();
                            result.put("code", 0);
                            result.put("msg", "�����ظ��ύ����!");
                            return result;
                        }
                    }
                    //�޸�������Ϣ
                    boolean flag = xfjmDao.updateById(model);
                    if(flag){
                        result.put("code", 1);
                        result.put("msg", "�ύ�ɹ�!");
                        result.put("id",model.getId());
                        return result;
                    }else{
                        result.put("code", 0);
                        result.put("msg", "�ύʧ��!");
                        return result;
                    }
                }else{
                    result.put("code", 0);
                    result.put("msg", "������Ϣ�����ύʧ��!");
                    return result;
                }
            } else {
                //����(�����)
                //�жϱ�ѧ���Ƿ��Ѿ��������
                HashMap<String,String> xfjms = xfjmDao.getCurrXfjm(model.getXh(),Base.currXn);
                if(xfjms != null && StringUtils.isNotNull(xfjms.get("id"))){
                    result.put("code", 0);
                    result.put("msg", "��ѧ�����������¼�������ظ�����!");
                    return result;
                }
                //����������Ϣ
                model.setSqsj(DateTranCnDate.timeStampToDate(System.currentTimeMillis()));
                model.setShlc(cssz.getSplc());
                String guid = UniqID.getInstance().getUniqIDHash();
                model.setId(guid.toUpperCase());
                if(StringUtils.isNull(model.getXn())){
                    model.setXn(Base.currXn);
                }
                boolean flag = xfjmDao.insert(model);
                //�ύ����
                if (flag && "5".equals(model.getShzt())) {
                    //���������
                    try {
                        flag = shlc.runSubmit(model.getId(), model.getShlc(), model.getXh(), "xszz_new_xfjmsh.do", "xszz_new_xfjmsq.do");
                    }catch (Exception e){
                        e.printStackTrace();
                        result.put("code", 0);
                        result.put("msg", "�����ظ��ύ����!");
                    }
                }
                if(flag){
                    result.put("code", 1);
                    result.put("msg", "�����ɹ�!");
                    result.put("id",model.getId());
                    return result;
                }else{
                    result.put("code", 0);
                    result.put("msg", "����ʧ��!");
                    return result;
                }
            }
            //�����������Ϣ
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", -1);
            result.put("msg", e.getMessage());

        }
        return result;
    }


    public Map<String,Object> jgSave(XfjmForm model, User user){
        Map<String, Object> result = new HashMap<>();
        try {
            //�ж�ʱ����Ƿ���
            CsszModel cssz = csszService.getModel();
            if (cssz != null && !"1".equals(cssz.getSqkg())) {
                result.put("code", 0);
                result.put("msg", "��δ����ѧ�Ѽ�������!");
                return result;
            }
            //�ж�ѧ���Ƿ�����������϶�
            HashMap<String, String> knsrdjg = getKnsrdjg(model.getXh(), Base.currXn);
            if (knsrdjg == null || StringUtils.isNull(knsrdjg.get("guid"))) {
                result.put("code", 0);
                result.put("msg", "��������������϶�����д˲���!");
                return result;
            }
            if (StringUtils.isNotNull(model.getId())) {
                //�޸�
                boolean flag = xfjmDao.updateJgId(model);
                if(flag){
                    result.put("code", 1);
                    result.put("msg", "�ύ�ɹ�!");
                    result.put("id",model.getId());
                    return result;
                }else{
                    result.put("code", 0);
                    result.put("msg", "�ύʧ��!");
                    return result;
                }
            } else {
                //����
                //�жϱ�ѧ���Ƿ��Ѿ��������
                HashMap<String,String> xfjms = xfjmDao.getCurrXfjm(model.getXh(),Base.currXn);
                if(xfjms != null && StringUtils.isNotNull(xfjms.get("id"))){
                    result.put("code", 0);
                    result.put("msg", "��ѧ�����������¼�������ظ�����!");
                    return result;
                }
                //����������Ϣ
                model.setSqsj(DateTranCnDate.timeStampToDate(System.currentTimeMillis()));
                String guid = UniqID.getInstance().getUniqIDHash();
                model.setId(guid.toUpperCase());
                if(StringUtils.isNull(model.getXn())){
                    model.setXn(Base.currXn);
                }
                boolean flag = xfjmDao.insert(model);
                if(flag){
                    result.put("code", 1);
                    result.put("msg", "�����ɹ�!");
                    result.put("id",model.getId());
                    return result;
                }else{
                    result.put("code", 0);
                    result.put("msg", "����ʧ��!");
                    return result;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", -1);
            result.put("msg", e.getMessage());

        }
        return result;
    }

    /**
     * @����: ����idɾ��������Ϣ��δ�ύ/�˻�����ɾ����
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/3 19:54
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param sqid ����id
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String,Object> removeById(String sqid){
        Map<String,Object> result = new HashMap<>();
        HashMap<String,String> xfjmInfo = xfjmDao.getById(sqid);
        if(xfjmInfo != null && xfjmInfo.containsKey("id")){
            //ֻ��ɾ���˻صĺ�δ�ύ������
            if(!"0".equalsIgnoreCase(xfjmInfo.get("shzt"))){
                result.put("code",0);
                result.put("msg","ֻ��ɾ��δ�ύ��������Ϣ!");
            }else{
                try {
                    shlc.deleteShjl(sqid);
                }catch (Exception e){
                    e.printStackTrace();
                }
                boolean flag = xfjmDao.removeById(sqid);
                if(flag){
                    result.put("code",1);
                    result.put("msg","ɾ���ɹ�!");
                }else{
                    result.put("code",0);
                    result.put("msg","ɾ��ʧ��!");
                }
            }
        }else{
            result.put("code",0);
            result.put("msg","ɾ����Ϣ����!");
        }
        return result;
    }

    public Map<String,Object> removeJg(String sqid){
        Map<String,Object> result = new HashMap<>();
        HashMap<String,String> xfjmInfo = xfjmDao.getById(sqid);
        if(xfjmInfo != null && xfjmInfo.containsKey("id")){
            //ֻ��ɾ���˻صĺ�δ�ύ������
            if(StringUtils.isNotNull(xfjmInfo.get("shlc"))){
                //����������̣���ɾ���������
                try {
                    shlc.deleteShjl(sqid);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            boolean flag = xfjmDao.removeById(sqid);
            if(flag){
                result.put("code",1);
                result.put("msg","ɾ���ɹ�!");
            }else{
                result.put("code",0);
                result.put("msg","ɾ��ʧ��!");
            }

        }else{
            result.put("code",0);
            result.put("msg","ɾ����Ϣ����!");
        }
        return result;
    }

    /**
     * @����: �޸����״̬
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/3 20:20
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param id ����id
     * @param shzt ���״̬ 0δ�ύ 1ͨ�� 2δͨ�� 3�˻� 5�����
     * @return boolean
     **/
    public Map<String,Object> sqztxg(String id,String shzt){
        Map<String,Object> result = new HashMap<>();
        try {
            //�����������
            CsszModel cssz = csszService.getModel();
            if(!"1".equals(cssz.getSqkg())){
                result.put("code",0);
                result.put("msg","��δ����ѧ�Ѽ�������!");
            }else{
                HashMap<String,String> xfjmInfo = xfjmDao.getById(id);
                if(xfjmInfo == null || !xfjmInfo.containsKey("id")){
                    result.put("code",0);
                    result.put("msg","������Ϣ����!");
                }else{
                    if(!"5".equals(shzt) && !"3".equals(shzt)){
                        result.put("code",0);
                        result.put("msg","�ύ��Ϣ����!");
                        return result;
                    }else{
                        if("5".equals(shzt)){
                            //�ύ���� ���������
                            //���������
                            shlc.runSubmit(xfjmInfo.get("id"), xfjmInfo.get("shlc"), xfjmInfo.get("xh"), "xszz_new_xfjmsh.do", "xszz_new_xfjmsq.do");
                        }else{
                            //����ɾ���������
                            shzt = Constants.YW_WTJ;//δ�ύ
                            //ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
                            try {
                                //��������
                                boolean sfysh = shlc.firstStepCancle(id, xfjmInfo.get("shlc"));
                            }catch (Exception e){
                                //�����������Ϣ�����������
                                result.put("code", 0);
                                result.put("msg", "����������޷�����!");
                                e.printStackTrace();
                                return result;
                            }
                        }
                        //�޸����״̬
                        boolean flag = xfjmDao.sqztxg(id,shzt);
                        if(flag){
                            result.put("code",1);
                            result.put("msg","�����ɹ�!");
                        }else{
                            result.put("code",0);
                            result.put("msg","����ʧ��!");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @����: ����ѧ��ѧ�꣬��ȡ�������϶������Ϣ
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/4 10:41
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param xh ѧ��
     * @param xn ѧ��
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     **/
    public HashMap<String,String> getKnsrdjg(String xh,String xn){
        return xfjmDao.getKnsrdjg(xh,xn);
    }

    //�����
    public List<HashMap<String,String>> getShPageList(XfjmForm model,User user){
        try {
            return xfjmDao.getDshPageList(model,user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //��˽��
    public List<HashMap<String,String>> getJgPageList(XfjmForm model,User user){
        try {
            return xfjmDao.getJgPageList(model,user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HashMap<String,String>> getJgList(XfjmForm model,User user){
        try {
            return xfjmDao.getJgList(model,user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @����: ѧ�Ѽ�����Ϣ���
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/8 10:28
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param t
	* @param user
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String,Object> shSave(XfjmForm t,User user){
        Map<String,Object> result = new HashMap<>();
        ShxxModel shlModel = new ShxxModel();
        shlModel.setYwid(t.getId());//ҵ��id
        shlModel.setShlc(t.getShlc());//�������
        shlModel.setShr(user.getUserName());//�����
        shlModel.setShyj(t.getShyj());//������
        shlModel.setShzt(t.getShjg());//���״̬
        shlModel.setThgw(t.getThgw());//�˻ظ�λ
        shlModel.setGwid(t.getXtgwid());//��λid
        shlModel.setSqrid(t.getXh());//������ѧ��
        shlModel.setTzlj("xszz_new_xfjmsh.do");
        shlModel.setTzljsq("xszz_new_xfjmsq.do");
        //�������Ϣ�洢
        try{
            String zhzt = shlc.runAuditing(shlModel);
            XfjmForm xfjmForm = new XfjmForm();
            xfjmForm.setId(t.getId());
            xfjmForm.setShzt(zhzt);//�����״̬ ͨ�� ��ͨ�� �˻� �����
            // ���һ�����ͨ��ʱ���Ѽ��������ѧ�Ѽ����
            if(zhzt.equals(Constants.SH_TG)){
                xfjmForm.setXfjmje(t.getXfjmje());
                //�޸����״̬
            }
            boolean flag = xfjmDao.updateShById(xfjmForm);
            if(flag){
                result.put("code",1);
                result.put("msg","�����ɹ�!");
            }else {
                result.put("code",0);
                result.put("msg","����ʧ��");
            }
        }catch (Exception e){
            //����쳣
            e.printStackTrace();
            result.put("code",-1);
            result.put("msg",e.getMessage());
            return result;
        }
        return result;
    }

    public boolean cancel(XfjmForm myForm) throws Exception {
        myForm.setShzt(Constants.YW_SHZ);
        boolean result = dao.qxsh(myForm.getId());
        return result;
    }

    /**
     * @����: ����������̼�����˸�λ�ж��Ƿ������һ�����
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/8 11:35
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param shlc �������
	* @param dshGwid ����˸�λ
     * @return boolean
     **/
    public boolean isLastGw(String shlc,String dshGwid){
        // ��ѯ��һ��������λ

        String xjgw = shlcDao.getNextGwid(shlc, dshGwid);
        //�жϵ�ǰ��˸�λ�Ƿ�Ϊ���һ��
        String lastGwid = shlcDao.getLastGwid(shlc);
        if (lastGwid.equalsIgnoreCase(dshGwid) && StringUtils.isNull(xjgw)) {
            return true;
        }
        return false;
    }

    /**
     * @����: ѧ�Ѽ�����Ϣ����
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/11 9:31
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param request
	* @param response 
     * @return java.lang.String
     **/
    public String importXfjmjg(HttpServletRequest request, HttpServletResponse response){
        try{
            //1.��ȡ����������
            List<HashMap<String,String>> jmxxList = new ArrayList<>();
            String path = request.getAttribute("filePath").toString();
            Sheet sheet = Workbook.getWorkbook(new File(path)).getSheet(0);
            //��ȡ����������
            int rowCount = sheet.getRows();
            //�ж�ģ���Ƿ���ȷ
            String modelTitle = ExcelMethods.getOneCellContent(sheet, 0, 0);
            if(modelTitle == null || "".equals(modelTitle)
                    || modelTitle.trim().indexOf("ѧ��")==-1){
                return "ģ���������������ص��룡";
            }
            HashMap<String,String> item;
            List<String[]> failGSList = new ArrayList<String[]>();
            int dataNums = rowCount - 1;
            for(int rowIndex =1;rowIndex < rowCount;rowIndex++){
                String xh = ExcelMethods.getOneCellContent(sheet, 0, rowIndex);
                String jmje = ExcelMethods.getOneCellContent(sheet, 1, rowIndex);
                String jmxn = ExcelMethods.getOneCellContent(sheet, 1, rowIndex);
                if(xh != null && xh.trim().length() > 0){
                    item = new HashMap<String, String>();
                    //�ж�ѧ���Ƿ�����������϶�����
                    if(jmxn == null || jmxn.trim().length()!=9){
                        jmxn = Base.currXn;//������ѧ����Ĭ��ʹ�õ�ǰѧ��
                    }
                    HashMap<String,String> knsrdInfo = dao.knsrdinfo(xh,jmxn);
                    if(knsrdInfo == null || !knsrdInfo.containsKey("xh") ||StringUtils.isNull(knsrdInfo.get("xh"))){
                        //��ѧ�겻�����������϶���Ϣ
                        String[] faillxs = {xh,jmje,jmxn,"������ѧ�겻�����������϶���Ϣ!"};
                        failGSList.add(faillxs);
                    }else{
                        if(knsrdInfo.containsKey("id") && StringUtils.isNotNull(knsrdInfo.get("id"))){
                            //������������Ϣ���Ҹ������м����¼
                            String[] faillxs = {xh,jmje,jmxn,"������ѧ��ѧ�Ѽ�����Ϣ�Ѵ���!"};
                            failGSList.add(faillxs);
                        }else{
                            //�����϶���Ϣ�Ҳ�����ѧ�Ѽ�����У������Ϣ
                            item.put("xh",xh.trim());//ѧ��
                            item.put("xn",jmxn.trim());//����ѧ��
                            item.put("jmje",jmje);//������
                            jmxxList.add(item);
                        }
                    }
                }else {
                    String[] faillxs = {xh,jmje,jmxn,"ѧ�Ų���Ϊ��!"};
                    failGSList.add(faillxs);
                }
            }
            int successCount = 0;
            //2.�ж�������Ч�� ����ѧ�� ���� ������ѧ�� ѧ���Ѵ���
            if(jmxxList.size()> 0){
                //3.��������
                int[] resultArray = dao.batchImport(jmxxList);
                if(resultArray != null && resultArray.length == jmxxList.size()){
                    for (int i = 0; i < resultArray.length; i++) {
                        switch (resultArray[i]) {
                            case 1:
                                successCount++;
                                break;
                            case 0:
                            case -1:
                                String[] failXhs = {jmxxList.get(i).get("xh"),jmxxList.get(i).get("jmje"),jmxxList.get(i).get("jmxn"),"������Ϣ������Ѵ���"};
                                failGSList.add(failXhs);
                                break;
                        }
                    }
                }
            }
            if(failGSList.size() > 0){
                //���ڵ���ʧ������
                response.reset();
                response.setContentType("application/vnd.ms-excel");
                String[] colCN = {"ѧ��","������","����ѧ��","������Ϣ"};
                String[] info = {"����"+dataNums+"������","�ɹ�����"+(dataNums-failGSList.size())+"��","ʧ��"+failGSList.size()+"��","��������"+failGSList.size()+"��"};
                failGSList.add(info);
                Excel2Oracle.exportData(failGSList,colCN ,colCN, response.getOutputStream());
                return "���ݵ���ɹ�������"+failGSList.size()+"������������Ѵ���!";
            }
            if(successCount>0){
                return "����ɹ����ɹ���������"+successCount+"����";
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return "����ʧ�ܣ�";
    }


    public HashMap<String,String> getZypmInfo(String xh,String xn){
        //��ȡרҵ�༶����
        HashMap<String,String> zybjpmInfo =  xfjmDao.getZybjpm(xh,xn);
        //��ȡרҵ����
        HashMap<String,String> zypmInfo = xfjmDao.getZypm(xh,xn);
        HashMap<String,String> pmInfo = new HashMap<>();
        String zybjpm = "";
        String zypm = "";
        if(zybjpmInfo != null && zybjpmInfo.containsKey("zybjpm")){
            zybjpm = zybjpmInfo.get("zybjpm");
        }
        if(zypmInfo != null && zypmInfo.containsKey("zypm")){
            zypm = zypmInfo.get("zypm");
        }
        pmInfo.put("zybjpm",zybjpm);
        pmInfo.put("zypm",zypm);
        return pmInfo;
    }
 }
