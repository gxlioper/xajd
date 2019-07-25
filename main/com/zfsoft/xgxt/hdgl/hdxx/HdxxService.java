/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.hdgl.hdxx;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

import com.zfsoft.xgxt.hdgl.hdbljg.HdbljgDao;
import org.apache.struts.upload.FormFile;
import xgxt.DAO.PicDAO;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

/**
 * @author ��������1282��
 * @version V1.0
 * @className �� HdxxService
 * @description �� TODO(��������������)
 * @date �� 2018-1-31 ����11:38:12
 */

public class HdxxService extends SuperServiceImpl<HdxxForm, HdxxDao> {

    /**
     * @param model
     * @return
     * @throws Exception
     * @description �� ���汨��
     * @author �� ������1282��
     * @date ��2018-1-31 ����11:46:51
     */
    public boolean saveBm(HdxxForm model) throws Exception {
        return dao.saveBm(model);
    }

    public boolean update(HdxxForm model) throws Exception {
        String[] btZd = model.getBtzd();
        if (btZd != null && btZd.length > 0) {
            dao.updateZd(btZd, model.getHdid());
        }

        return dao.runUpdate(model);
    }

    /**
     * @param model
     * @return
     * @description �� ��ȡ���Ϣ
     * @author �� ������1282��
     * @date ��2018-2-1 ����11:14:30
     */
    public HashMap<String, String> getHdxx(HdxxForm model) {
        return dao.getHdxx(model);
    }

    public List<HashMap<String, String>> zxHdxxList(HdxxForm model,User user) throws Exception{
        Pages pages = new Pages();
        pages.setPageSize(5);
        model.setPages(pages);
        return dao.zxHdxxList(model,user);
    }

    //��ȡ����������Ϣ
    public HashMap<String, String> getBmSqxx(String sqid, String bmlx) {
        return dao.getBmSqxx(sqid, bmlx);
    }
    //��ȡ����������Ϣ
    public HashMap<String, String> getBmSqxx(HdxxForm t) {
        return dao.getBmSqxx(t);
    }

    /**
     * ������ť�����֤
     *
     * @param hdid
     * @param user
     * @return
     * @throws Exception
     */
    public String yz(String hdid, User user) throws Exception {
        //1����֤�Ƿ��Ѿ�����
        if (dao.sfbm(hdid, user)) {
            return "���ѱ����������ظ�������";
        }
        //2����֤��������Ҫ��˵Ļ�������Ƿ񳬹�����
//        HdxxForm hdxx = dao.getModel(hdid);
//        if ("0".equals(hdxx.getBmsfsh())) {
//            int hdrssx = hdxx.getBmrs() == null ? 0 : Integer.parseInt(hdxx.getBmrs());//���������
//            int bmrs = dao.bmrs(hdid);//�ѱ�������
//            if ((bmrs + 1) > hdrssx) {
//                return "���������ѳ����޶";
//            }
//        }
        return "true";
    }

    /**
     * ��������ֶ���֤
     *
     * @param t
     * @return
     * @throws Exception
     */
    public String saveCheck(HdxxForm t) throws Exception {
        List<HashMap<String, String>> zdList = this.getBtZdList(t.getHdid());
        for (HashMap<String, String> zd : zdList) {
            if ("1".equals(zd.get("sfbt"))) {
                String zdName = zd.get("zd");
                String methodName = "get" + zdName.substring(0, 1).toUpperCase() + zdName.substring(1);
                Method method = t.getClass().getMethod(methodName);
                String s = (String) method.invoke(t);
                if (StringUtil.isNull(s)) {
                    return "�뽫�����ֶ���д��ɣ�";
                }
            }
        }
        return "true";
    }

    //��ȡ���������������ֶ�
    public List<HashMap<String, String>> getBtZdList(String hdid) {
        return dao.getBtZdList(hdid);
    }

    public String saveStuPic(HdxxForm model) {
        PicDAO picDao = new PicDAO();

        // ѧ��
        String xh = model.getXh();

        // �ļ�
        FormFile file = model.getStuPic();
        String fileName = file.getFileName();

        boolean isAllowType = fileName.endsWith("jpg") || fileName.endsWith("jpeg")
                || fileName.endsWith("gif")
                || fileName.endsWith("png")
                || fileName.endsWith("bmp") || fileName.endsWith("JPG") || fileName.endsWith("GIF") || fileName.endsWith("PNG") || fileName.endsWith("BMP");

        if (!isAllowType) {
            return "�ϴ�ʧ�ܣ��Ƿ����ļ���ʽ��";
        }

        if (file.getFileSize() > 1024 * 1024) {
            return "�ϴ�ʧ�ܣ��ļ���С����1M��";
        } else {
            try {
                picDao.savePic(file.getInputStream(), xh, "stu");
                return "true";
            } catch (Exception e) {
                e.printStackTrace();
                return "�ϴ�ʧ�ܣ��������ϴ���";
            }
        }
    }

    /**
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @description �� ������б�
     * @author �� ������1282��
     * @date ��2018-2-1 ����05:30:19
     */
    public List<HashMap<String, String>> getHdxqList(HdxxForm model, User user) throws Exception {
        return dao.getHdxqPageList(model, user);
    }

    /**
     * ��ȡ������б�����.
     *
     * @param model
     * @param user
     * @return java.util.List<java.util.HashMap                               <                               java.lang.String                               ,                               java.lang.String>>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-05 14:55
     * @throw
     */
    public List<HashMap<String, String>> getHdglList(HdxxForm model, User user) throws Exception {

        return dao.getHdglPageList(model, user);
    }

    /**
     * @return
     * @description �� ��ȡ��Ա�б�
     * @author �� ������1282��
     * @date ��2018-2-2 ����09:21:20
     */
    public List<HashMap<String, String>> getCyList(HdxxForm model, User user) throws Exception {
        return dao.getCyList(model, user);
    }

    public List<HashMap<String, String>> gethasPCyList(HdxxForm model, User user) throws Exception {
        return dao.gethasPCyList(model,user);
    }

    /**
     * @param t
     * @return
     * @throws Exception
     * @description �� �������״̬
     * @author �� ������1282��
     * @date ��2018-2-5 ����11:40:32
     */
    public boolean updateShzt(HdxxForm t) throws Exception {
        return dao.updateShzt(t);
    }

    /**
     * @param t
     * @return
     * @description �� �ж�ͨ�������Ƿ��ѳ�����
     * @author �� ������1282��
     * @date ��2018-2-6 ����09:22:35
     */
    public boolean beyondMaximum(HdxxForm t) {
        String hdid = t.getHdid();
        Integer rs = dao.getRs(hdid);
        Integer peoplePermitted = dao.getPeoplePermitted(hdid);
        String[] ids = t.getIds();
        int tgrs = ids.length;
        if (rs == 0) {
            return false;
        } else {
            if (peoplePermitted + tgrs > rs) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * @param t
     * @param user
     * @return
     * @description �� ��ȡ��׶���Ϣ
     * @author �� ������1282��
     * @date ��2018-2-6 ����04:35:19
     */
    public HashMap<String, String> getHdjdInfo(HdxxForm t, User user) {
        return dao.getHdjdInfo(t, user);
    }

    /**
     * ��ʦ��ȡ��׶���Ϣ.
     *
     * @param t
     * @return java.util.HashMap<java.lang.String                               ,                               java.lang.String>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-10 15:05
     * @throw
     */
    public HashMap<String, String> getHdjdInfo(HdxxForm t) {

        return dao.getHdjdInfo(t);
    }


    /**
     * @param t
     * @return
     * @description �� ��ȡ��׶�list
     * @author �� ������1282��
     * @date ��2018-2-7 ����08:57:47
     */
    public List<HashMap<String, String>> getHdjdList(HdxxForm t) {
        return dao.getHdjdList(t);
    }

    /**
     * ��¼�.
     *
     * @param hdid
     * @return boolean
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-06 15:29
     * @throw Exception
     */
    public boolean hdxjById(String hdid) throws Exception {

        return dao.hdxjById(hdid);
    }

    /**
     * ��ȡר���ų�Ա�б�.
     *
     * @param sffp
     * @return java.util.List<java.util.HashMap                               <                               java.lang.String                               ,                               java.lang.String>>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-07 14:09
     * @throw Exception
     */
    public List<HashMap<String, String>> getZjtcyList(HdxxForm hdxxForm, String sffp) throws Exception {

        return dao.getZjtcyList(hdxxForm, sffp);
    }

    /**
     * ���������ר���ų�Ա����.
     *
     * @param model
     * @return boolean
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-08 09:55
     * @throw Exception
     */
    public boolean zjtcyFp(HdxxForm model) throws Exception {

        String[] ids = model.getIds();    //�id����
        String[] zghs = model.getZghs();    //ר���ų�Աzgh����
        String jdid = model.getJdid();
        if (ids == null || zghs == null) {
            return false;
        }

        return dao.zjtcyFp(ids, zghs, jdid);
    }

    /**
     * �����ȡ��ר���ų�Ա����.
     *
     * @param model
     * @return boolean
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-08 09:55
     * @throw Exception
     */
    public boolean zjtcyQxfp(HdxxForm model) throws Exception {

        String hdid = model.getHdid();    //�id
        String[] zghs = model.getZghs();    //ר���ų�Աzgh����
        String jdid = model.getJdid();
        if (hdid == null || zghs == null) {
            return false;
        }
        return dao.zjtcyQxfp(hdid, zghs, jdid);
    }

    public String yz(HdxxForm t) {
        if ("��".equals(t.getSflx())) {
            if (StringUtil.isNull(t.getMc()) || StringUtil.isNull(t.getJb())
                    || StringUtil.isNull(t.getZddw()) || StringUtil.isNull(t.getZdjs())
                    || StringUtil.isNull(t.getNrjs()) || StringUtil.isNull(t.getFjid())) {
                return "�뽫��������д����!";
            }
        }
        return "true";
    }

    /**
     * @param t
     * @return
     * @throws Exception
     * @description �� ����ѧ���׶���д
     * @author �� lj��1282��
     * @date ��2018-2-7 ����02:45:38
     */
    public boolean updateStudentStage(HdxxForm t) throws Exception {
        return dao.updateStudentStage(t);
    }

    /**
     * ��֤���������
     *
     * @param t
     * @return
     * @throws Exception
     */
    public String checkZrs(HdxxForm t) throws Exception {
        HdxxForm hdxx = dao.getModel(t.getHdid());
        String dwrs = hdxx.getDwrs();//����Լ������
        String hdzrs = hdxx.getBmrs();//�����������
        int dws = dao.getDws(t.getHdid());//����ж�����
        if ((dws + 1) * Integer.parseInt(dwrs) > Integer.parseInt(hdzrs)) {
            return "��������������ޣ�";
        }
        return "true";
    }

    /**
     * ��֤������������
     *
     * @param t
     * @return
     * @throws Exception
     */
    public String checkDwrs(HdxxForm t) throws Exception {
        HdxxForm hdxx = dao.getModel(t.getHdid());
        String dwrs = hdxx.getDwrs();//��������������
        int currentDwrs = dao.getDwrs(t.getHdid(), t.getDwid());
        if (currentDwrs + 1 > Integer.parseInt(dwrs)) {
            return "��������������";
        }
        return "true";
    }

    /**
     * @param t
     * @return
     * @throws Exception
     * @description �� �������
     * @author �� lj��1282��
     * @date ��2018-2-9 ����02:40:52
     */
    public synchronized boolean createGroup(HdxxForm t, String[] xhs) throws Exception {
        String dwid = String.valueOf(dao.getDws(t.getHdid()) + 1);
        t.setDwid(dwid);
        return dao.insertZdCy(t, xhs);
    }

    /**
     * @param hdid TODO
     * @return
     * @description �� ��ȡ�����б�
     * @author �� lj��1282��
     * @date ��2018-2-10 ����03:21:58
     */
    public List<HashMap<String, String>> getDwList(String userName, String hdid) {
        return dao.getDwList(userName, hdid);
    }

    /**
     * @param t
     * @return
     * @throws Exception
     * @description �� �������
     * @author �� lj��1282��
     * @date ��2018-2-10 ����03:50:36
     */
    public boolean jrzd(HdxxForm t) throws Exception {
        //����Ϊ��Ա
        t.setDwzw("0");
        return dao.insertZdCy(t);
    }

    /**
     * @param t
     * @param user
     * @return
     * @description �� ��ȡ��׶���Ϣ����ӣ�
     * @author �� lj��1282��
     * @date ��2018-2-28 ����11:57:14
     */
    public HashMap<String, String> getHdjdInfoForZd(HdxxForm t, User user) {
        return dao.getHdjdInfoForZd(t, user);
    }

    public String getDwid(HdxxForm form,String xh) throws Exception{
        return dao.getDwid(form,xh);
    }
    /**
     * @param t
     * @param user
     * @return
     * @throws Exception
     * @description �� ��������
     * @author �� lj��1282��
     * @date ��2018-3-5 ����11:46:54
     */
    public boolean insertPl(HdxxForm t, User user) throws Exception {
        return dao.insertPl(t, user);
    }

    /**
     * @param t
     * @param user
     * @return
     * @description �� ��ȡ��������
     * @author �� lj��1282��
     * @date ��2018-3-5 ����03:48:44
     */
    public HashMap<String, String> getPlnrByUser(HdxxForm t, User user) throws Exception {
        return dao.getPlnrByUser(t, user);
    }

    /**
     * @param t
     * @return
     * @throws Exception
     * @description �� ������������
     * @author �� lj��1282��
     * @date ��2018-3-5 ����04:22:45
     */
    public boolean updatePlnr(HdxxForm t) throws Exception {
        return dao.updatePlnr(t);
    }

    /**
     * @return
     * @throws Exception
     * @description �� ��ȡ�����list
     * @author �� lj��1282��
     * @date ��2018-3-8 ����10:42:13
     */
    public List<HashMap<String, String>> getHdplList(HdxxForm t) throws Exception {
        return dao.getHdplList(t);
    }

    /**
     * @param plid
     * @return
     * @description �� ��ȡ�������Ϣ
     * @author �� lj��1282��
     * @date ��2018-3-9 ����09:16:19
     */
    public HashMap<String, String> getHdPlInfo(String plid) {
        return dao.getHdPlInfo(plid);
    }

    /**
     * @param t
     * @param user
     * @return
     * @throws Exception
     * @description �� �ظ�����
     * @author �� lj��1282��
     * @date ��2018-3-9 ����11:56:44
     */
    public boolean replyPl(HdxxForm t, User user) throws Exception {
        return dao.replyPl(t, user);
    }

    /**
     * @param plid
     * @return
     * @throws Exception
     * @description �� ɾ������
     * @author �� lj��1282��
     * @date ��2018-3-9 ����03:43:02
     */
    public boolean delPl(String plid) throws Exception {
        return dao.delPl(plid);
    }

    /**
     * ����dwid��ȡ�����б�.
     *
     * @param hdxxForm
     * @return java.util.List<java.util.HashMap                               <                               java.lang.String                               ,                               java.lang.String>>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-08 17:46
     * @throw
     */
    public List<HashMap<String, String>> getDwList(HdxxForm hdxxForm) {

        return dao.getDwList(hdxxForm);
    }

    /**
     * ��ȡ���˻������Ϣ.
     *
     * @param model
     * @return java.util.HashMap<java.lang.String                               ,                               java.lang.String>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-09 16:38
     * @throw
     */
    public HashMap<String, String> getGrbmxx(HdxxForm model) {

        return dao.getGrbmxx(model);
    }

    /**
     * @param model
     * @return
     * @throws Exception
     * @description �� ����
     * @author �� lj��1282��
     * @date ��2018-3-13 ����05:29:46
     */
    public boolean dz(HdxxForm model) throws Exception {
        return dao.dz(model);
    }

    /**
     * @param t
     * @return
     * @throws Exception
     * @description �� ��ȡ��һ�׶λ��Ϣ
     * @author �� lj��1282��
     * @date ��2018-3-15 ����03:56:21
     */
    public HashMap<String, String> getNextHdInfo(HdxxForm t) throws Exception {
        return dao.getNextHdInfo(t);
    }

    /**
     * @param t
     * @return
     * @throws Exception
     * @description �� ��ȡ�����һ�׶λ��Ϣ
     * @author �� lj��1282��
     * @date ��2018-3-26 ����03:38:53
     */
    public HashMap<String, String> getNextHdInfoForZd(HdxxForm t) throws Exception {
        return dao.getNextHdInfoForZd(t);
    }

    /**
     * @param hdid
     * @return
     * @throws SQLException
     * @description �� ��ȡ���ǩ
     * @author �� lj��1282��
     * @date ��2018-4-2 ����05:07:22
     */
    public List<String> getHdbq(String hdid) throws SQLException {
        return dao.getHdbq(hdid);
    }

    /**
     * @return
     * @description �� ���û�׶�
     * @author �� lj��1282��
     * @date ��2018-4-4 ����04:16:53
     */
    public boolean szHdjd(HdxxForm t) throws Exception {
        boolean result = dao.runUpdate(t);
        if (result) {
            String[] jdlxs = t.getJdlxs();
            if (null != jdlxs) {
                String[] jdids = new String[jdlxs.length];
                dao.deleteJds(t);
                for (int i = 0; i < jdlxs.length; i++) {
                    String jdid = UniqID.getInstance().getUniqIDHash();
                    jdids[i] = jdid;
                }
                t.setJdids(jdids);
                result = dao.insertJdglByHdId(t);
            }
            result = szHdLxAndBq(t);
        }
        return result;
    }

    /**
     * @param t
     * @return
     * @description �� �Ƿ���Խ׶�����
     * @author �� lj��1282��
     * @date ��2018-4-8 ����10:30:28
     */
    public boolean sfJdsz(HdxxForm t) {
        //����׶��������Ա��������ɾ��
        boolean result = dao.countJdrs(t);
        return result == false ? true : false;
    }

    /**
     * @param form
     * @return
     * @throws Exception
     * @description �� ��ȡ��׶���
     * @author �� lj��1282��
     * @date ��2018-4-8 ����03:15:02
     */
    public List<HashMap<String, String>> getHdjdLists(HdxxForm form) throws Exception {
        return dao.getHdjdLists(form);
    }

    /**
     * @param t
     * @param user
     * @return
     * @throws Exception
     * @description �� ��ȡ�����б�
     * @author �� lj��1282��
     * @date ��2018-4-12 ����04:01:35
     */
    public List<HashMap<String, String>> getPageListForResult(HdxxForm t, User user) throws Exception {
        return dao.getPageListForResult(t, user);
    }

    public List<HashMap<String, String>> getPageListForHdxjg(HdxxForm t) throws Exception {
        return dao.getPageListForHdxjg(t);
    }

    public List<HashMap<String, String>> getPageListForHdxjgByStu(HdxxForm t, User user) throws Exception {
        if ("stu".equals(user.getUserType())) {
            return dao.getPageListForHdxjgForStu(t, user);
        } else {
            return dao.getPageListForHdxjgByStu(t, user);
        }
    }

    public List<HashMap<String, String>> getPageListForJzjg(HdxxForm t) throws Exception {
        return dao.getPageListForJzjg(t);
    }

    public List<HashMap<String, String>> getPageListForJzjgByStu(HdxxForm t, User user) throws Exception {
        if ("stu".equals(user.getUserType())) {
            return dao.getPageListForJzjgForStu(t, user);
        } else {
            return dao.getPageListForJzjgByStu(t, user);
        }
    }

    /**
     * @param form
     * @return
     * @description �� �������ͺͱ�ǩ
     * @author �� lj��1282��
     * @date ��2018-4-12 ����05:15:01
     */
    public boolean szHdLxAndBq(HdxxForm form) throws Exception {
        boolean result = dao.szHdLx(form);
        if (result) {
            HdbljgDao jgDao = new HdbljgDao();
            String[] nlbqs = form.getNlbqs();/*��ȡ������ǩ*/
            //ɾ�����ǩ
            result = dao.deleteHdbq(form);
            boolean result1 = jgDao.deleteNlbq(new String[]{form.getHdid()});
            if (result && result1) {
                //������ǩ
                result = dao.insertHdbq(form);
                /*����������ǩ*/
                result = jgDao.BatchInsertNlbqx(form.getHdid(), nlbqs);
            }
            dao.deleteHdjx(form);
            String[] jxmcs = form.getJxmcs();
            if (null != jxmcs && jxmcs.length > 0) {
                result = dao.insertHdjx(form);
            }
        }
        return result;

    }

    public String getdsCount(String xh) {
        return dao.getdsCount(xh);
    }

    public List<HashMap<String, String>> getHdlistByXh(String xh) {
        return dao.getHdlistByXh(xh);
    }

    public String getjsCount(String xh) {
        return dao.getjsCount(xh);
    }

    public String getjzCount(String xh) {
        return dao.getjzCount(xh);
    }

    public String gethdCount(String xh) {
        return dao.gethdCount(xh);
    }

    public List<HashMap<String, String>> getHdXflistByXh(String xh) {
        return dao.getHdXflistByXh(xh);
    }

    public HashMap<String, String> getNjPmByXh(String xh) {
        return dao.getNjPmByXh(xh);
    }

    public List<HashMap<String, String>> getHdslistByXh(String xh) {
        return dao.getHdslistByXh(xh);
    }

    /**
     * @param form
     * @return
     * @throws Exception
     * @description �� ��ȡ�����
     * @author �� lj��1282��
     * @date ��2018-4-23 ����03:32:36
     */
    public List<HashMap<String, String>> getHdjxs(HdxxForm form) throws Exception {
        return dao.getHdjxs(form);
    }

    /**
     * ��ȡ��Ʊ��Ա����
     *
     * @return
     */
    public List<HashMap<String, String>> getPpPersonList(HdxxForm form) {

        if (HdxxDao.randomAndByTime.equals(form.getPptype())) {
            return dao.getPpPersonList2(form);
        } else {
            return dao.getPpPersonList(form);
        }
    }

    public HdxxForm getModel(HdxxForm form) throws Exception {
        return dao.getModel(form.getHdid());
    }

    public String checkPpRs(HdxxForm form) throws Exception {
        String shtgrs = dao.getYshrs(form);//���ͨ������
        String pprs = dao.getYpprs(form);//����Ʊ����
        HdxxForm hdxx = dao.getModel(form.getHdid());

        if (Integer.parseInt(pprs)>=Integer.parseInt(hdxx.getBmrs())) {
            return "�����������޷�����Ʊ��";
        }
        String ppnum = form.getPpnum();
        if (Integer.parseInt(ppnum) > (Integer.parseInt(hdxx.getBmrs()) - Integer.parseInt(pprs))) {
            return "����" + pprs + "�˵�Ʊ��ʣ��" + String.valueOf(Integer.parseInt(hdxx.getBmrs()) - Integer.parseInt(pprs)) + "Ʊ������Ʊ��";
        }

        return "true";
    }

    public boolean update(List<String> p, String bmlx) throws SQLException {
        return dao.setPp(p, bmlx);
    }

    /**
     * @param hdid
     * @return
     * @throws Exception HashMap<String,String> ��������
     * @throws
     * @����: ���ݻid��ȡ���Ϣ
     * @���ߣ� MengWei[���ţ�1186]
     * @���ڣ� 2018-6-11 ����04:58:01
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public HashMap<String, String> getHdxxByHdid(String hdid) throws Exception {
        return dao.getHdxxByHdid(hdid);
    }

    /**
     * @param hdid
     * @return
     * @throws Exception HashMap<String,String> ��������
     * @throws
     * @����: �׶γ�Ա��Ϣ
     * @���ߣ� MengWei[���ţ�1186]
     * @���ڣ� 2018-6-11 ����06:07:12
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getJdcyxxList(String hdid) throws Exception {
        return dao.getJdcyxxList(hdid);
    }

    /**
     * ��ȡ�����list����������������
     *
     * @return
     */
    public List<HashMap<String, String>> getHdlx() {
        return dao.getHdlx();
    }

    public List<HashMap<String, String>> getJdxx(String hdid) {
        return dao.getJdxx(hdid);
    }

    public String checkBm(HdxxForm t, String[] xhs) throws Exception {
        List<String> xhlist = Arrays.asList(xhs);
        List<String> xhlists = new ArrayList<>(xhlist);
        xhlists.add(t.getXh());
        xhs = xhlists.toArray(new String[xhlists.size()]);
        StringBuilder msg = new StringBuilder();
        boolean result = false;
        for (int i = 0; i < xhs.length; i++) {
            if (dao.sfzdbm(t.getHdid(), xhs[i])) {
                msg.append("��" + xhs[i] + "��");
                result = true;
            }
        }
        if (result) {
            msg.append("�Ѽ�����������");
        }else {
            msg.append("true");
        }
        return msg.toString();
    }

    /**
     * ���������Ч�Ĳ���Ҫ������˻���б�
     */
    public List<HashMap<String, String>> getHdxxList() {
        return dao.getHdxxList();
    }

    /**
     * ������ͨ������Ա
     *
     * @param hdId
     * @return
     */
    public List<String> getHdryList(String hdId) {
        try {
            return dao.getHdryList(hdId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * ��Ʊ
     *
     * @param
     * @return
     */
    public void pp() {
        //����δ��Ʊ�Ļ
        List<HashMap<String, String>> hdList = getHdxxList();
        for (HashMap<String, String> hashMap : hdList) {
            //��������
            List<String> ryList = getHdryList(hashMap.get("hdid"));
            if (StringUtils.isNull(hashMap.get("bmrs"))) {
                continue;
            }
            int bmrs = Integer.parseInt(hashMap.get("bmrs"));
            Date jssj;
            try {
                jssj = DateUtils.parse(hashMap.get("bmjssj"));
            } catch (Exception e) {
                continue;
            }
            if ("1".equals(hashMap.get("ppfs")) || "2".equals(hashMap.get("ppfs"))) {
                if ("1".equals(hashMap.get("ppfs"))) {
                    Collections.shuffle(ryList);
                }
                if (ryList.size() > bmrs) {
                    ryList = ryList.subList(0, bmrs);
                }
            }

            if ("3".equals(hashMap.get("ppfs"))) {
                int zzbmrs = Integer.parseInt(hashMap.get("zzbmrs"));
                if (ryList.size() > bmrs) {
                    List<String> randomList = ryList.subList(zzbmrs, ryList.size());
                    Collections.shuffle(randomList);
                    ryList = ryList.subList(0, zzbmrs);
                    int randomCount = Math.abs(bmrs - zzbmrs);
                    ryList.addAll(randomList.subList(0, randomCount));
                }
            }
            if (jssj.before(new Date())) {
                List<String[]> params = new ArrayList<>();
                for (String id : ryList) {
                    params.add(new String[]{id});
                }
                try {
                    dao.pp(params);

                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }

        }
    }
    public List<HashMap<String,String>> getBmRys(String dthdid){
        return dao.getBmRys(dthdid);
    }
}
