/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-23 ����01:35:39
 */
package com.zfsoft.xgxt.xpjpy.zhcp.zcfs;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.views.utils.ArrayUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmDao;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmModel;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmService;
import common.Globals;
import jxl.CellFeatures;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.*;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.*;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.Boolean;
import java.sql.SQLException;
import java.util.*;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �۲����
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-23 ����01:35:39
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZcfsService extends
        SuperServiceImpl<ZcfsModel, ZcfsDao> {

    private ZcfsDao dao = new ZcfsDao();
    private CsszDao csszDao = new CsszDao();
    public static final String DEFAULT_PMFS = "bjpm";
    public static final String DEFAULT_QXTJ = "2";  //ȡ���ύ

    public static String _BCZSCID = "-1";

    public ZcfsService() {
        super.setDao(dao);
    }

    /**
     * @return boolean ��������
     * @����: �Ƿ��а༶�Ѿ��ύ���۲��
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-7-23 ����01:39:38
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean isHaveSubmitClass(String xn, String xq) {

        return Integer.valueOf(dao.getYtjZcfNum(xn, xq)) > 0;
    }

    /**
     * @param t
     * @param user
     * @return
     * @throws Exception List<HashMap<String,String>> ��������
     * @����: �۲�༶�ύ��ѯ
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-7-24 ����10:16:40
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getZcbjList(ZcfsModel t, User user)
            throws Exception {

        return dao.getZcbjList(t, user);
    }


    /*
	 *
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
    public List<HashMap<String, String>> getPageList(ZcfsModel t, User user)
            throws Exception {

        ZcxmDao zcxmDao = new ZcxmDao();
        //����IDȡ�ð༶���������
        List<HashMap<String, String>> bjxxMap = getBjxxByIds(t, user);

        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();
        List<HashMap<String, String>> zcxmList = null;

        if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())) {
            //�۲���Ŀ����Ϊ �꼶
            zcxmList = zcxmDao.getEditZcxmByNj(bjxxMap, t, user);
        } else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
            //�۲���Ŀ������Ϊ Ժϵ
            zcxmList = zcxmDao.getEditZcxmByXy(bjxxMap, t, user);
        } else {
            zcxmList = zcxmDao.getNoChildZcfxm();
        }
        return dao.getPageList(t, zcxmList, user);
    }

    public List<HashMap<String, String>> getPageListOfYf(ZcfsModel t, User user) throws Exception {

        ZcxmDao zcxmDao = new ZcxmDao();
        // ����IDȡ�ð༶���������
        List<HashMap<String, String>> bjxxMap = getBjxxByIds(t, user);

        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();
        List<HashMap<String, String>> zcxmList = null;

        if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())) {
            // �۲���Ŀ����Ϊ �꼶
            zcxmList = zcxmDao.getEditZcxmByNj(bjxxMap, t, user);
        } else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
            // �۲���Ŀ������Ϊ Ժϵ
            zcxmList = zcxmDao.getEditZcxmByXy(bjxxMap, t, user);
        } else {
            zcxmList = zcxmDao.getNoChildZcfxm();
        }
        return dao.getPageListOfYf(t, zcxmList, user);
    }


    /**
     * @param t
     * @param user
     * @return
     * @throws Exception boolean ��������
     * @����: �����۲����
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-7-24 ����03:07:41
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean saveZcfs(ZcfsModel t, User user) throws Exception {

        //�жϸ�ѧ�ꡢѧ�ڣ���ѧ���Ƿ��Ѿ�¼���˴���Ŀ�ķ���
        HashMap<String, String> zcfsMap = dao.getFsid(t);

        t.setLrr(user.getUserName());
        t.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss"));

        //�ޣ����� �У�����
        if (StringUtil.isNull(zcfsMap.get("id"))) {
            return dao.runInsert(t);
        } else {
            t.setId(zcfsMap.get("id"));
            return dao.runUpdate(t);
        }
    }

    public boolean saveZcfsYf(ZcfsModel t, User user) throws Exception {

        //�жϸ�ѧ�ꡢѧ�ڣ���ѧ���Ƿ��Ѿ�¼���˴���Ŀ�ķ���
        HashMap<String, String> zcfsMap = dao.getFsid(t);
        HashMap<String, String> zcfsYfMap = dao.getFsOfYf(t);
        String zcfs = "0";
        t.setLrr(user.getUserName());
        t.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss"));
        //���۲��¼��

        //�ޣ����� �У�����
        if (StringUtil.isNull(zcfsYfMap.get("guid"))) {
            dao.insertZcfsYf(t);

        } else {
            t.setId(zcfsYfMap.get("guid"));
            zcfs = zcfsYfMap.get("fs");//��¼�޸�ǰ����
            dao.updateZcfsYf(t);

        }

        if (null == t.getFs() || "".equals(t.getFs())) {
            t.setFs("0");
        }
        if (null == zcfs || "".equals(zcfs)) {
            zcfs = "0";
        }

        //�ޣ����� �У�����
        if (StringUtil.isNull(zcfsMap.get("id"))) {
            return dao.runInsert(t);
        } else {
            String fsSum = String.valueOf((Double.parseDouble(zcfsMap.get("fs")) - Double.parseDouble(zcfs) + Double.parseDouble(t.getFs())));
            t.setFs(fsSum);
            t.setId(zcfsMap.get("id"));
            return dao.runUpdate(t);
        }

    }

    public boolean saveZcfsYfPl(ZcfsModel t, User user) throws Exception {
        t.setLrr(user.getUserName());
        t.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss"));
        return dao.updateZcfs(t);
    }


    /**
     * @param model
     * @return boolean ��������
     * @throws Exception
     * @����: ���༶�۲���Ƿ�����ύ
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-7-24 ����04:57:03
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean isCanSubmit(ZcfsModel model) throws Exception {

        boolean isCanSubmit = false;

        //��Ĭ����Ŀ �� ͬ���ӿ���Ŀ�ķ����ӵ�������¼��
        initDefaultZcfs(model.getXn(), model.getXq(), model.getId());

        //��¼��Ŀ��*ѧ����  <= ������¼��
        isCanSubmit = Boolean.valueOf(dao.getSfyWlr(model));

        if (!isCanSubmit) {
            return false;
        }

        //����Ƿ���NULL��ֵ �ķ�����¼
        isCanSubmit = Integer.valueOf(dao.getNullZcf(model)) == 0;

        return isCanSubmit;
    }


    /**
     * @param model
     * @return
     * @throws Exception String[] ��������
     * @throws
     * @����:�����ύ
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2015-2-5 ����03:54:40
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public String[] isNotCanSubmit(ZcfsModel model, User user) throws Exception {

        String[] ids = model.getId().split(",");

        List<String> canId = new ArrayList<String>();//�����ύ��id����
        StringBuffer noSubmit = new StringBuffer();
        boolean isNotCanSubmit = false;
        int i = 0;  //��¼�ύ����

        if (null == ids || ids.length <= 0) {
            return null;
        }
        for (String str : ids) {
            model.setId(str);
            if (isCanSubmit(model)) {
                submitZcfs(model, user); //�����ύ
                canId.add(str);//��¼id
                i++;
            } else {
                HashMap<String, String> bjInfo = dao.getBjInfo(str);
                noSubmit.append(bjInfo.get("bjmc"));
                noSubmit.append(",</br>");
                isNotCanSubmit = true;
            }
        }


        String str = noSubmit.toString();
        //ȥ�������ය��
        str = isNotCanSubmit ? str : _BCZSCID;
        return new String[]{String.valueOf(i), str};

    }


    /**
     * @param model
     * @param user
     * @return boolean ��������
     * @����: �ύ�۲��
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-7-24 ����04:58:02
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean submitZcfs(ZcfsModel model, User user) {

        Map<String, String> map = getBjxxById(model.getId());

        try {

            //�����۲��
            Thread thread = new Thread(new ComputeZcpm(map.get("xn"), map.get("xq"), map.get("bjdm")));
            thread.start();

            boolean result = dao.submitBjzcf(model.getId(), user.getUserName());
//			�༶״̬�ύ��ɺ���²��������ڵ������趨���������ñ�0:������,1:���£�
            CsszService csszService = new CsszService();
            if (("1").equals(csszService.getCsz("szyf"))) {
                if (dao.checkIsNotExistsInYfTjb(model.getId(), model.getZcyf())) {
                    dao.submitBjzcfYf(model.getId(), model.getZcyf(), user.getUserName());
                } else {
                    dao.udpateYfTjb(model.getId(), model.getZcyf());
                }

            }
            if ("1".equals(csszService.getCsz("gxrssd")) && result) {
                // �����ύ��ɺ���ݱ���������Ŀ���õ��е�����
                updateRssz(map.get("bjdm"));
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * @return boolean ��������
     * @throws Exception
     * @throws
     * @����:�۲�����ύ�������Ŀ���õ��е��������䷽ʽ
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2014-7-22 ����05:23:52
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean updateRssz(String bjdm) throws Exception {

        List<HashMap<String, String>> map = dao.getDqxmInfo();

        boolean updateRssz = true;

        if (null != map && map.size() > 0) {

            for (int i = 0; i < map.size(); i++) {

                if (StringUtil.isNull(map.get(i).get("rsfpfs"))) {

                } else if (map.get(i).get("rsfpfs").equals(Constants.RSKZFW_BJ)) {
                    // �༶
                    updateRssz = dao.updateBjRssz(bjdm, map.get(i).get("xmdm"));

                } else if (map.get(i).get("rsfpfs").equals(Constants.RSKZFW_NJXY)) {
                    // �꼶ѧԺ
                    updateRssz = dao.updateNjxyRssz(bjdm, map.get(i).get("xmdm"));

                } else if (map.get(i).get("rsfpfs").equals(Constants.RSKZFW_NJZY)) {
                    // �꼶רҵ
                    updateRssz = dao.updateNjZyRssz(bjdm, map.get(i).get("xmdm"));

                } else if (map.get(i).get("rsfpfs").equals(Constants.RSKZFW_XY)) {
                    // ѧԺ
                    updateRssz = dao.updateXyRssz(bjdm, map.get(i).get("xmdm"));

                } else if (map.get(i).get("rsfpfs").equals(Constants.RSKZFW_CPZ)) {
                    // ������
                    updateRssz = dao.updateCpzRssz(bjdm, map.get(i).get("xmdm"));

                } else if (map.get(i).get("rsfpfs").equals(Constants.RSKZFW_XX)) {
                    // ѧУ
                    updateRssz = dao.updateXxRssz(map.get(i).get("xmdm"));

                }

                //�ж����ִ�в��ɹ�����ѭ��
                if (updateRssz == false) {
                    break;
                }

            }
        }

        System.out.println(updateRssz == true ? "�������������Զ����³ɹ�(�༶���룺" + bjdm + ")" : "�������������Զ�����ʧ��(�༶���룺" + bjdm + ")");

        return updateRssz;
    }

    /**
     * @throws Exception
     * @����: ��ʼ��Ĭ���������
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-7-26 ����01:36:26
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public void initDefaultZcfs(ZcfsModel model, User user) throws Exception {

        //����IDȡ�ð༶���������
        List<HashMap<String, String>> bjxxMap = getBjxxByIds(model, user);
        //Ĭ����Ŀ�۲��
        dao.insertDefaultZcxmf(model.getXn(), model.getXq(), bjxxMap, model, user);
        //�ӿ�ͬ����Ŀ����շ�ֵ
        dao.insertTbxmf(model.getXn(), model.getXq(), bjxxMap, model);
        //�����մ� ��ͬ������0
        if ("12930".equals(Base.xxdm) || "12688".equals(Base.xxdm) || "12942".equals(Base.xxdm)) {
            dao.insertFtbf(model.getXn(), model.getXq(), bjxxMap, model);
        }
        //������Ϊ�ٷ�֮��ĳ�ʼ������Ϊ0
        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();

        //��Ŀ����Ϊ���꼶 ��
        if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())) {
            dao.insertMrfsByNj(model.getXn(), model.getXq(), bjxxMap, model);
        }

        //��Ŀ����Ϊ��Ժϵ��
        if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
            dao.insertMrfsByXy(model.getXn(), model.getXq(), bjxxMap, model);
        }

    }


    /**
     * @param xn
     * @param xq
     * @param id void ��������
     * @throws Exception
     * @����: ��ʼ��Ĭ�����������Ϊ֧��������д
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-7-26 ����01:36:26
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public void initDefaultZcfs(String xn, String xq, String id) throws Exception {

        //����IDȡ�ð༶���������
        Map<String, String> map = getBjxxById(id);
        String bjdm = map.get("bjdm");
        //Ĭ����Ŀ�۲��
        dao.insertDefaultZcxmf(xn, xq, bjdm);
        //�ӿ�ͬ����Ŀ����շ�ֵ
        dao.insertTbxmf(xn, xq, bjdm);

        //������Ϊ�ٷ�֮��ĳ�ʼ������Ϊ0
        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();

        //��Ŀ����Ϊ���꼶 ��
        if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())) {
            dao.insertMrfsByNj(xn, xq, bjdm);
        }

        //��Ŀ����Ϊ��Ժϵ��
        if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
            dao.insertMrfsByXy(xn, xq, bjdm);
        }

    }


    /**
     * @param id
     * @return HashMap<String                                                               ,                                                               String> ��������
     * @����: ����ȡ�༶��Ϣ
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-7-26 ����01:51:20
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getBjxxByIds(ZcfsModel model, User user) throws Exception {

        return dao.getBjxxByIds(model, user);
    }


    /**
     * @param id
     * @return HashMap<String                                                               ,                                                               String> ��������
     * @����: ��IDȡ�༶��Ϣ
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-7-26 ����01:51:20
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public HashMap<String, String> getBjxxById(String id) {

        return dao.getBjxxById(id);
    }


    /**
     * @return File ��������
     * @����: ��������ģ��
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-7-29 ����08:51:31
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public File createImportTemplate(ZcfsModel model, User user) throws Exception {

        WritableWorkbook wwb = null;

        //�����ļ���� ����ʱĿ¼
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }

        List<HashMap<String, String>> bjxxMap = dao.getBjxxById(model, user);
        //���������ļ�
        File file = new File(tempDir.getPath() + "/" + "�۲�ֵ���ģ��.xls");
        file.setWritable(true);

        try {
            FileOutputStream stream = new FileOutputStream(file);
            //����excel������
            wwb = Workbook.createWorkbook(stream);

            WritableSheet ws = wwb.createSheet("�۲�ֵ���ģ��", 0);

            ZcxmDao zcxmDao = new ZcxmDao();
            //�ɱ༭�۲�ֵ��۲���Ŀ
            CsszDao csszDao = new CsszDao();
            CsszModel csszModel = csszDao.getModel();
            List<HashMap<String, String>> zcxmList = null;

            if (!"10364".equals(Base.xxdm)) {
                if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())) {
                    //�۲���Ŀ����Ϊ �꼶
                    zcxmList = zcxmDao.getAllowEditZcfxmByNj(bjxxMap, model, user);
                } else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
                    //�۲���Ŀ������Ϊ Ժϵ
                    zcxmList = zcxmDao.getAllowEditZcfxmByXy(bjxxMap, model, user);
                } else {
                    zcxmList = zcxmDao.getAllowEditZcfxm();
                }
            } else {
                if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
                    //�۲���Ŀ������Ϊ Ժϵ
                    zcxmList = zcxmDao.getAllowEditZcfxmByXy(bjxxMap, model, user);
                } else {
                    zcxmList = zcxmDao.getAllowEditZcfxm();
                }
            }


            //�̶���ͷѧ�š�����
            ws.addCell(new Label(0, 0, "ѧ��"));
            ws.addCell(new Label(1, 0, "����"));

            for (int i = 0, j = zcxmList.size(); i < j; i++) {
                Label label = new Label(2 + i, 0, zcxmList.get(i).get("xmmc"));
                WritableCellFeatures wcfeatures = new WritableCellFeatures();
                //����Ŀ������Ϊע�ͣ�����ʶ����
                wcfeatures.setComment(zcxmList.get(i).get("xmdm"));
                label.setCellFeatures(wcfeatures);
                ws.addCell(label);
            }
            /*�ɶ��м�ʦѧԺ�����۲�������*/
            if (!StringUtil.isNull(model.getZcyf())
                    && !"undefined".equals(model.getZcyf())) {
                Label label = new Label(2 + zcxmList.size(), 0, "�۲��·�");
                WritableCellFeatures wcfeatures = new WritableCellFeatures();
                //����Ŀ������Ϊע�ͣ�����ʶ����
                wcfeatures.setComment("�����۲��·�������ѡ����۲��·�һ��");
                label.setCellFeatures(wcfeatures);
                ws.addCell(label);
            }

            //����ҳ
            model.getPages().setPageSize(Integer.MAX_VALUE);
            //��ѯѧ�����Ѿ�¼��ķ���
            String szyf = csszDao.getCsz("szyf");
//            List<HashMap<String, String>> zcfList = new ArrayList<HashMap<String, String>>();
//            if ("1".equals(szyf)) {
//                zcfList = dao.getPageListOfYf(model, zcxmList, user);
//            } else {
//                zcfList = dao.getPageList(model, zcxmList, user);
//            }
//
//            for (int i = 0, j = zcfList.size(); i < j; i++) {
//                ws.addCell(new Label(0, i + 1, zcfList.get(i).get("xh")));
//                ws.addCell(new Label(1, i + 1, zcfList.get(i).get("xm")));
//                /*�ɶ��м�ʦѧԺ�����۲�����������*/
//                if (!StringUtil.isNull(model.getZcyf())
//                        && !"undefined".equals(model.getZcyf())) {
//                    ws.addCell(new Label(2 + zcxmList.size(), i + 1, model.getZcyf()));
//                }
//                for (int m = 0, n = zcxmList.size(); m < n; m++) {
//                    ws.addCell(new Label(m + 2, i + 1, zcfList.get(i).get("fs" + m)));
//                }
//            }
//
//            WritableSheet ws1 = wwb.createSheet("��Ŀ�ȼ���", 1);
//            //�ȼ�����
//            List<String> DjmcList = getDjmc();
//            //�ȼ�list
//            List<HashMap<String, String>> djList = getDj();
//
//            if (!DjmcList.isEmpty()) {
//                WritableCellFormat wcf = new WritableCellFormat();
//                WritableFont wf = new WritableFont(WritableFont.ARIAL);
//                wf.setBoldStyle(WritableFont.BOLD);
//                wf.setPointSize(10);
//                wf.setColour(Colour.BLUE);
//                wcf.setAlignment(Alignment.LEFT);
//                wcf.setWrap(true);
//                wcf.setFont(wf);
//
//                for (int i = 0; i < DjmcList.size(); i++) {
//                    ws1.addCell(new Label(i, 0, DjmcList.get(i), wcf));
//
//                    int z = 1;
//                    for (int j = 0; j < djList.size(); j++) {
//                        if (DjmcList.get(i).equals(djList.get(j).get("xmmc"))) {
//                            ws1.addCell(new Label(i, z, djList.get(j).get("mc")));
//                            z++;
//                        }
//
//                    }
//                }
//            }


            wwb.write();
            wwb.close();
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
        }

        file.setWritable(true);
        return file;
    }

    /**
     * �������ѧ���ĵ���ģ��
     *
     * @param model
     * @return
     * @throws Exception
     */

    public File createImportTemplateForAll(ZcfsModel model) throws Exception {
        WritableWorkbook wwb = null;

        //�����ļ���� ����ʱĿ¼
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }

        //���������ļ�
        File file = new File(tempDir.getPath() + "/" + "�۲�ֵ���ģ��.xls");
        file.setWritable(true);

        try {
            FileOutputStream stream = new FileOutputStream(file);
            //����excel������
            wwb = Workbook.createWorkbook(stream);

            WritableSheet ws = wwb.createSheet("�۲�ֵ���ģ��", 0);

            ZcxmDao zcxmDao = new ZcxmDao();
            //�ɱ༭�۲�ֵ��۲���Ŀ
            CsszDao csszDao = new CsszDao();
            CsszModel csszModel = csszDao.getModel();
            List<HashMap<String, String>> zcxmList = zcxmDao.getAllowEditZcfxm();
            ws.addCell(new Label(0, 0, "ѧ��"));
            ws.addCell(new Label(1, 0, "����"));

            for (int i = 0, j = zcxmList.size(); i < j; i++) {
                Label label = new Label(2 + i, 0, zcxmList.get(i).get("xmmc"));
                WritableCellFeatures wcfeatures = new WritableCellFeatures();
                //����Ŀ������Ϊע�ͣ�����ʶ����
                wcfeatures.setComment(zcxmList.get(i).get("xmdm"));
                label.setCellFeatures(wcfeatures);
                ws.addCell(label);
            }
            WritableSheet ws1 = wwb.createSheet("��Ŀ�ȼ���", 1);
            //�ȼ�����
            List<String> DjmcList = getDjmc();
            //�ȼ�list
            List<HashMap<String, String>> djList = getDj();

            if (!DjmcList.isEmpty()) {
                WritableCellFormat wcf = new WritableCellFormat();
                WritableFont wf = new WritableFont(WritableFont.ARIAL);
                wf.setBoldStyle(WritableFont.BOLD);
                wf.setPointSize(10);
                wf.setColour(Colour.BLUE);
                wcf.setAlignment(Alignment.LEFT);
                wcf.setWrap(true);
                wcf.setFont(wf);

                for (int i = 0; i < DjmcList.size(); i++) {
                    ws1.addCell(new Label(i, 0, DjmcList.get(i), wcf));

                    int z = 1;
                    for (int j = 0; j < djList.size(); j++) {
                        if (DjmcList.get(i).equals(djList.get(j).get("xmmc"))) {
                            ws1.addCell(new Label(i, z, djList.get(j).get("mc")));
                            z++;
                        }

                    }
                }
            }
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
        }

        file.setWritable(true);
        return file;

    }


    /**
     * @param model
     * @throws FileNotFoundException
     * @throws IOException           void ��������
     * @throws BiffException
     * @����: �����۲����
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-7-29 ����10:56:25
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public File importZcfs(ZcfsModel model, User user) throws Exception {

        FormFile importFile = model.getImportFile();
        //��FormFile ת��ΪFile ����
        File file = FileUtil.conversionFormFile(importFile);

        //��ȡExcel������
        Workbook book = Workbook.getWorkbook(file);
        //�༶��Ϣ
        List<HashMap<String, String>> bjxxMap = dao.getBjxxById(model, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        //�ɱ༭�۲�ֵ��۲���Ŀ
        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();
        List<HashMap<String, String>> zcxmList = null;

        if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())) {
            //�۲���Ŀ����Ϊ �꼶
            zcxmList = zcxmDao.getAllowEditZcfxmByNj(bjxxMap, model, user);
        } else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
            //�۲���Ŀ������Ϊ Ժϵ
            zcxmList = zcxmDao.getAllowEditZcfxmByXy(bjxxMap, model, user);
        } else {
            zcxmList = zcxmDao.getAllowEditZcfxm();
        }

        WritableWorkbook wwb = Workbook.createWorkbook(file, book);
        WritableSheet ws = wwb.getSheet(0);
        //���ô�����Ϣ��ʾ���п�
        ws.setColumnView(zcxmList.size() + 2, 30);

        //�����༶ѧ��
        //String[] stus = dao.getStuById(model, user);

        //����ģ�����۲�ṹ�Ƚ���֤
        for (int i = 0, j = zcxmList.size(); i < j; i++) {

            String xmdm = zcxmList.get(i).get("xmdm");
            String xmmc = zcxmList.get(i).get("xmmc");

            CellFeatures cellFeatures = ws.getCell(2 + i, 0).getCellFeatures();

            if (cellFeatures == null) {
                throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
            }

            String cellComment = cellFeatures.getComment();
            String cellContent = ws.getCell(2 + i, 0).getContents();

            if (cellComment == null || cellContent == null) {
                throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
            }

            //��֤�±�ͷ�Ƿ����۲�ṹ�ĵ���ģ��һ��
            if (!xmdm.equals(cellComment.trim()) || !xmmc.equals(cellContent.trim())) {
                throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
            }
        }

        /*
         * ��⵼������
         */
        int rows = ws.getRows();
        List<String[]> params = new ArrayList<String[]>();

        boolean checkResult = true;

        for (int i = 1; i < rows; i++) {
            StringBuilder errorMessage = new StringBuilder();

            String xh = ws.getCell(0, i).getContents();
            String xm = ws.getCell(1, i).getContents();
            String[] param = null;
            if (StringUtil.isNull(xh) || StringUtil.isNull(xm)) {
                //ѧ�ź�������ô����Ϊ���أ�
                errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_XHXM_NULL));
//            } else if (!ArrayUtil.contains(stus, xh)) {//��֤ѧ����Ч��
//                errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_FAIL));
            } else {

                for (int m = 0; m < zcxmList.size(); m++) {
                    String xmfz = ws.getCell(m + 2, i).getContents().trim();

                    if (StringUtil.isNull(xmfz)) {
                        //��Ŀ��������Ϊ��
                        //errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_NULL));
                        //break;
                    }

                    //�۲���Ŀ����  4 Ϊ�ȼ�������¼�������
                    String xmlx = zcxmList.get(m).get("xmlx");

                    //�۲���Ŀ����
                    String drxmmc = zcxmList.get(m).get("xmmc");

                    if (!"4".equals(xmlx)) {

                        //��֤��Ŀ������ ������Ч��
                        Double xmf = 0.0;

                        try {
                            if (StringUtils.isNotEmpty(xmfz)) {
                                xmf = Double.valueOf(xmfz.trim());
                            }
                        } catch (Exception e) {
                            errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_NOTNUMBER,
                                    new Object[]{zcxmList.get(m).get("xmmc")}));
                            break;
                        }

                        //��֤����
                        if (xmfz.length() > 10) {
                            errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_MAXLENGTH));
                            break;
                        }

                        //��֤�����С����
                        Double max = Double.valueOf(zcxmList.get(m).get("zdfs"));
                        Double min = Double.valueOf(zcxmList.get(m).get("zxfs"));

                        if (xmf > max || xmf < min) {
                            errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_ZDZX,
                                    new Object[]{zcxmList.get(m).get("xmmc"), max, min}));
                            break;
                        }
                    } else {
                        boolean checkFlag = false;
                        //��֤¼��ĵȼ��Ƿ����Ҫ��
                        List<HashMap<String, String>> djList = dao.getDj();
                        for (int j = 0; j < djList.size(); j++) {
                            if (djList.get(j).get("mc").equals(xmfz) && djList.get(j).get("xmmc").equals(drxmmc)) {
                                xmfz = djList.get(j).get("dm");
                                checkFlag = true;
                                break;
                            }
                        }

                        if (!checkFlag) {
                            errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_ZDDJ,
                                    new Object[]{xmfz}));
                            break;
                        }
                    }
                    param = null;
                    if (!StringUtil.isNull(model.getZcyf())
                            && !"undefined".equals(model.getZcyf())) {//���µ����۲��
                        param = new String[]{xh, zcxmList.get(m).get("xn"),
                                zcxmList.get(m).get("xq"),
                                model.getZcyf(),
                                zcxmList.get(m).get("xmdm"),
                                xmfz, user.getUserName(),
                                xh, zcxmList.get(m).get("xmdm"), model.getZcyf()};//��������(�·�)
                    } else {
                        param = new String[]{xh, zcxmList.get(m).get("xn"),
                                zcxmList.get(m).get("xq"),
                                zcxmList.get(m).get("xmdm"),
                                xmfz, user.getUserName(),
                                xh, zcxmList.get(m).get("xmdm")};
                    }

                    //���µ����۲��
                    if (!StringUtil.isNull(model.getZcyf())
                            && !"undefined".equals(model.getZcyf())) {

                        boolean pdcs = true;

                        String zcyf = ws.getCell(zcxmList.size() + 2, i).getContents();
                        if (!zcyf.equals(model.getZcyf())) {
                            //errorMessage.append(",ֻ�ܵ���"+model.getZcyf()+"�����ݣ�");
                            pdcs = false;
                        }

                        if (pdcs) {
                            params.add(param);
                        }
                    } else {
                        params.add(param);
                    }
                }
            }

            //���µ����۲��
            if (!StringUtil.isNull(model.getZcyf())
                    && !"undefined".equals(model.getZcyf())) {
                String zcyf = ws.getCell(zcxmList.size() + 2, i).getContents();
                if (!zcyf.equals(model.getZcyf())) {
                    errorMessage.append(",ֻ�ܵ���" + model.getZcyf() + "�����ݣ�");
                }
            }


            //������Ϣ׷��
            if (errorMessage.length() > 0) {

                WritableCellFormat wcf = new WritableCellFormat();
                WritableFont wf = new WritableFont(WritableFont.ARIAL);
                wf.setColour(Colour.RED);
                wcf.setFont(wf);
                wcf.setAlignment(Alignment.CENTRE);

                int y = 2;

                /**
                 * �ɶ��м�ʦѧԺ���µ����۲�֣������۲��·���
                 */
                if (!StringUtil.isNull(model.getZcyf())
                        && !"undefined".equals(model.getZcyf())) {
                    y = 3;
                }
                ws.addCell(new Label(zcxmList.size() + y, i, errorMessage.toString(), wcf));
                checkResult = false;
            }
        }


        //��֤OK�ĵ��룬ʧ�ܵĵ���
        if (!params.isEmpty()) {
            System.out.println("action========" + System.currentTimeMillis());
            if (!StringUtil.isNull(model.getZcyf())
                    && !"undefined".equals(model.getZcyf())) {//���µ����۲��
                dao.batchInsertZcfsYf(params);//��������(�·�)
                ZcfsModel form = new ZcfsModel();
                for (int j = 0; j < params.size(); j++) {
                    form.setXh(params.get(j)[0]);
                    form.setXn(params.get(j)[1]);
                    form.setXq(params.get(j)[2]);
                    form.setZcyf(params.get(j)[3]);
                    form.setXmdm(params.get(j)[4]);
                    form.setFs(params.get(j)[5]);
                    saveZcfsYfPl(form, user);
                }
            } else {
                dao.batchInsertZcfs(params);//��������
            }
            System.out.println("end==========" + System.currentTimeMillis());
        }

        //TODO


        if (!checkResult) {
            WritableSheet ws1 = wwb.createSheet("��������", 1);
            ws1.addCell(new Label(0, 0, ws.getCell(0, 0).getContents()));
            ws1.addCell(new Label(1, 0, ws.getCell(1, 0).getContents()));
            for (int i = 0, j = zcxmList.size(); i < j; i++) {
                Label label = new Label(2 + i, 0, zcxmList.get(i).get("xmmc"));
                WritableCellFeatures wcfeatures = new WritableCellFeatures();
                //����Ŀ������Ϊע�ͣ�����ʶ����
                wcfeatures.setComment(zcxmList.get(i).get("xmdm"));
                label.setCellFeatures(wcfeatures);
                ws1.addCell(label);
            }

            int z = 1;//�Ѵ�ӡ�к�
            int t = 2;
            int x = 1;
            if (!StringUtil.isNull(model.getZcyf())
                    && !"undefined".equals(model.getZcyf())) {
                t = 3;
                x = 2;
            }
            for (int i = 0; i < rows; i++) {
                if (!StringUtils.isBlank((ws.getCell(zcxmList.size() + t, i).getContents()).trim())) {
                    ws1.addCell(new Label(0, z, ws.getCell(0, i).getContents()));
                    ws1.addCell(new Label(1, z, ws.getCell(1, i).getContents()));
                    for (int j = 0; j < zcxmList.size() + x; j++) {
                        ws1.addCell(new Label(j + 2, z, ws.getCell(j + 2, i).getContents()));
                    }
                    z++;
                }
            }

            wwb.removeSheet(0);
            wwb.write();
            wwb.close();
            return file;
        }
        return null;
    }


    /**
     * @param id
     * @param xmdm
     * @return
     * @throws Exception
     * @throws Exception boolean ��������
     * @����: ͬ���ӿ�����
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-7-29 ����06:50:22
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean getIntefaceData(ZcfsModel form, String xmdm, User user) throws Exception {

        // ������ð༶��Ϣ
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        boolean bool = false;

        try {
            ZcxmModel model = zcxmDao.getModel(xmdm);

            for (int i = 0; i < bjList.size(); i++) {
                bool = dao.getIntefaceData(xmdm, bjList.get(i).get("bjdm"), model.getJktb());
                if (!bool) {
                    break;
                }
            }
            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    /**
     * @param form
     * @param xmdm
     * @param user
     * @return
     * @throws Exception boolean ��������
     * @throws
     * @���������ɹŵ����ճ���Ϊ��ͬ�����۲����
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2018-3-2 ����10:05:48
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean getIntefaceDataNmgdz(ZcfsModel form, User user) throws Exception {

        // ������ð༶��Ϣ
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        boolean bool = false;

        try {

            for (int i = 0; i < bjList.size(); i++) {
                bool = dao.getIntefaceDataZdxm(bjList.get(i).get("bjdm"), " pro_zhcp_zhcj_12673");
                if (!bool) {
                    break;
                }
            }
            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    public boolean getIntefaceDataNmgzc(ZcfsModel form, User user) throws Exception {

        // ������ð༶��Ϣ
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        boolean bool = false;

        try {

            for (int i = 0; i < bjList.size(); i++) {
                bool = dao.getIntefaceDataZdxm(bjList.get(i).get("bjdm"), " pro_zhcp_zhcj_12942");
                if (!bool) {
                    break;
                }
            }
            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    /**
     * @param form
     * @param xmdms
     * @param user
     * @return
     * @throws Exception boolean ��������
     * @throws
     * @����: ͬ���ӿ����ݣ��Ϻ�Ϸ����Ի���
     * @���ߣ�������[���ţ�1123]
     * @���ڣ�2015-11-25 ����03:28:43
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean getIntefaceDataS(ZcfsModel form, String xmdms, User user) throws Exception {

        // ������ð༶��Ϣ
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        boolean bool = false;

        try {

            List<HashMap<String, String>> jktbs = zcxmDao.getJktbS(xmdms, "00");

            for (int j = 0; j < jktbs.size(); j++) {
                for (int i = 0; i < bjList.size(); i++) {
                    bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), jktbs.get(j).get("jktb"));
                    if (!bool) {
                        break;
                    }
                }
            }

            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }


    public boolean getIntefaceDataAll(ZcfsModel form, String xmdms, User user) throws Exception {
        List<HashMap<String, String>> jktbs = null;
        // ������ð༶��Ϣ
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        boolean bool = false;

        try {

            jktbs = zcxmDao.getJktbSHHYAll();
            for (int j = 0; j < jktbs.size(); j++) {
                for (int i = 0; i < bjList.size(); i++) {
                    bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), jktbs.get(j).get("jktb"));
                    if (!bool) {
                        break;
                    }
                }
            }

            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    /**
     * @param t
     * @param user
     * @return
     * @throws Exception List<HashMap<String,String>> ��������
     * @throws
     * @����:�۲��ȡ����ѯ
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2013-7-30 ����10:42:44
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getZcfqxList(ZcfsModel t, User user)
            throws Exception {

        return dao.getZcfqxList(t, user);
    }

    /**
     * @param values
     * @param user
     * @return boolean ��������
     * @throws Exception
     * @throws
     * @����:�ı��۲�֣�ȡ���ύ
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2013-7-30 ����01:39:42
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean cancelTj(User user, ZcfsModel zcfsForm) throws Exception {

        String id = zcfsForm.getId();

        boolean cancelTj = false;
        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();

        String xn = csszModel.getXn();
        String xq = csszModel.getXq();


        //����ȡ����¼
        cancelTj = dao.insertTzjl(id, user, zcfsForm, xn, xq);

        if (!cancelTj) {
            return false;
        }

        //����������Ա��
        cancelTj = dao.updateCpmd(id, user, DEFAULT_QXTJ);


        if (cancelTj) {
            Map<String, String> map = getBjxxById(id);

            //�����۲��
            Thread thread = new Thread(new ComputeZcpm(map.get("xn"), map.get("xq"), map.get("bjdm")));
            thread.start();

            //	��������ѧ���Ի��жϣ��༶״̬�ύ��ɺ���²��������ڵ������趨
            if (Globals.XXDM_CQLGDX.equals(Base.xxdm)) {
                // �����ύ��ɺ���ݱ���������Ŀ���õ��е�����
                updateRssz(map.get("bjdm"));
            }

        }

        return cancelTj;
    }

    /**
     * @param zcfsForm
     * @param user
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> ��������
     * @throws
     * @����:�۲�����ѯ_������
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2013-7-31 ����04:09:13
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */

    public List<HashMap<String, String>> getZcfjgList(ZcfsModel t, User user) throws Exception {

        //������ʽ
        String pmfs = t.getPmfs();
        if (StringUtil.isNull(pmfs)) {
            pmfs = DEFAULT_PMFS;
        }

        ZcxmDao zcxmDao = new ZcxmDao();

        //ǰ�����۲���Ŀ
        List<HashMap<String, String>> zcxmList = zcxmDao.getFirstAndSecondZcxm(t);

        return dao.getZcfjgList(t, user, pmfs, zcxmList);
    }


    public List<HashMap<String, String>> getZcfjgList(String xn, String xq, String bjdms) throws Exception {

        String[] bjdm = bjdms.split(",");
        String params = "";
        for (int i = 0; i < bjdm.length; i++) {
            params += "'" + bjdm[i] + "',";
        }
        if (params.length() > 0) {
            params = params.substring(0, params.length() - 1);
        }

        ZcxmDao zcxmDao = new ZcxmDao();
        //ǰ�����۲���Ŀ
        List<HashMap<String, String>> zcxmList = zcxmDao.getFirstAndSecondZcxm();

        return dao.getZcfjgList(params, xn, xq, DEFAULT_PMFS, zcxmList);
    }

    /**
     * @param zcfsForm
     * @param user
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> ��������
     * @throws Exception
     * @throws
     * @����:��ѯ�۲�ȡ����¼
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2013-8-1 ����03:06:21
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getZcfqxjlList(ZcfsModel zcfsForm,
                                                        User user) throws Exception {

        return dao.getZcfqxjlList(zcfsForm, user);
    }


    /**
     * @param xh
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> ��������
     * @����: ��ѧ�Ų�ѯָ�����������۲��
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-8-5 ����10:16:31
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getZcfListByXh(String xh, String xn, String xq) {
        return dao.getZcfListByXh(xh, xn, xq);
    }

    /**
     * ��ѧ�ꡢѧ�ڡ�ѧ�Ų�ѯ�����۲���Ŀ��
     */
    public List<HashMap<String, String>> getZcfListAllByXh(String xh, String xn, String xq) {
        return dao.getZcfListAllByXh(xh, xn, xq);
    }

    /**
     * ��ѧ�ꡢѧ�ڡ�(�༶��ѧ��)��ѯ�����۲���Ŀ�֣�������Ϣְҵ����ѧԺ��
     */
    public List<HashMap<String, String>> getZcfListAllByBjdm_13108(String bjdm, String xh, String xn, String xq) {
        return dao.getZcfListAllByBjdm_13108(bjdm, xh, xn, xq);
    }

    /**
     * ��ѧ�ꡢѧ�ڡ��༶�����ѯ�۲���Ŀƽ���֣�������Ϣְҵ����ѧԺ��
     */
    public HashMap<String, String> getZcfAvgByBjdm_13108(String bjdm, String xn, String xq) {
        return dao.getZcfAvgByBjdm_13108(bjdm, xn, xq);
    }

    /**
     * ��ѧ�ꡢѧ�ڡ��༶�����ѯ�༶��Ϣ
     */
    public HashMap<String, String> getBjxx(String bjdm, String xn, String xq) {
        return dao.getBjxx(bjdm, xn, xq);
    }

    /**
     * ��ѧ�ꡢѧ�Ų�ѯ�������ϡ���ѧ���۲��
     */
    public List<HashMap<String, String>> getZcfListByXnXh(String xh, String xn) {
        return dao.getZcfListByXnXh(xh, xn);
    }

    /**
     * ���ѧ���ɼ�
     *
     * @param kcxz ѡ��/����
     */
    public List<HashMap<String, String>> getCjListByXhXnXq(String xh, String xn, String xq, String kcxz) {
        return dao.getCjListByXhXnXq(xh, xn, xq, kcxz);
    }

    /**
     * ���ѧ���ɼ�
     *
     * @param kcxz ѡ��/����
     */
    public List<HashMap<String, String>> getCjListByXhXn(String xh, String xn, String kcxz) {
        return dao.getCjListByXhXn(xh, xn, kcxz);
    }

    /**
     * ���ѧ���ɼ�
     *
     * @param kcxz ѡ��/����
     */
    public List<HashMap<String, String>> getCjListByXh(String xh) {
        return dao.getCjListByXh(xh);
    }

    /**
     * @param xh
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> ��������
     * @����: ��ѧ�Ų�ѯָ����������������
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-8-5 ����10:16:31
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getZyfListByXh(String xh, String xn, String xq) {
        return dao.getZyfListByXh(xh, xn, xq);
    }

    /**
     * @param xh
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> ��������
     * @����: ��ѧ�Ų�ѯȫ���۲��
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-8-5 ����10:17:06
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getAllZcfListByXh(String xh) {
        return dao.getAllZcfListByXh(xh);
    }


    /**
     * @param zcfsForm
     * @param user
     * @return
     * @throws Exception File ��������
     * @����: ��ȡ�༶�۲�ֵĵ����ļ�
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-8-6 ����09:08:47
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public File getBjZcfFile(ZcfsModel zcfsForm, User user) throws Exception {

        ZcxmDao zcxmDao = new ZcxmDao();

        List<HashMap<String, String>> zcxmList = zcxmDao.getNoChildZcfxm();
        //����������ͷ
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("xh", "ѧ��");
        map.put("xm", "����");

        for (int i = 0, j = zcxmList.size(); i < j; i++) {
            map.put("fs" + i, zcxmList.get(i).get("xmmc"));
        }
        //��������
        zcfsForm.getPages().setPageSize(Integer.MAX_VALUE);
        List<HashMap<String, String>> dataList = dao.getPageList(zcfsForm, zcxmList, user);

        IExportService export = new ExportExcelImpl();
        return export.getExportFile(map, dataList);
    }

    public File getBjZcfFileOfYf(ZcfsModel zcfsForm, User user) throws Exception {

        ZcxmDao zcxmDao = new ZcxmDao();

        List<HashMap<String, String>> zcxmList = zcxmDao.getNoChildZcfxm();
        //����������ͷ
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("xh", "ѧ��");
        map.put("xm", "����");

        for (int i = 0, j = zcxmList.size(); i < j; i++) {
            map.put("fs" + i, zcxmList.get(i).get("xmmc"));
        }
        //��������
        zcfsForm.getPages().setPageSize(Integer.MAX_VALUE);
        List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
        if ("all".equals(zcfsForm.getZcyf())) {
            dataList = dao.getPageList(zcfsForm, zcxmList, user);
        } else {
            dataList = dao.getPageListOfYf(zcfsForm, zcxmList, user);
        }


        IExportService export = new ExportExcelImpl();
        return export.getExportFile(map, dataList);
    }

    /**
     * @param zcfsForm
     * @return
     * @throws Exception File ��������
     * @throws
     * @����:�۲�ֽ������
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2013-8-6 ����11:34:28
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public File getZcfjgFile(ZcfsModel zcfsForm, User user) throws Exception {

        ZcxmDao zcxmDao = new ZcxmDao();

        List<HashMap<String, String>> zcxmList = zcxmDao.getFirstAndSecondZcxm(zcfsForm);

        //������ʽ
        String pmfs = zcfsForm.getPmfs();
        if (StringUtil.isNull(pmfs)) {
            pmfs = DEFAULT_PMFS;
        }

        //����������ͷ
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("xh", "ѧ��");
        map.put("xm", "����");
        map.put("bjmc", "�༶");

        for (int i = 0, j = zcxmList.size(); i < j; i++) {
            map.put("fs" + i, zcxmList.get(i).get("xmmc"));
            if (!"10335".equals(Base.xxdm)) {
                map.put("pm" + i, "����");
            }
        }
        //��������
        zcfsForm.getPages().setPageSize(Integer.MAX_VALUE);

        List<HashMap<String, String>> dataList = dao.getZcfjgList(zcfsForm, user, pmfs, zcxmList);

        IExportService export = new ExportExcelImpl();
        return export.getExportFile(map, dataList);
    }

    /**
     * �㽭����ְҵ����ѧԺ �����۲����
     */
    public File getZcfjgFile_12861(ZcfsModel zcfsForm, User user) throws Exception {
        // ����,14,CENTRE,�߿�
        WritableCellFormat s14CentreFormat = new WritableCellFormat();
        WritableFont s14CentreFont = new WritableFont(WritableFont.createFont("����"), 14);
        s14CentreFormat.setFont(s14CentreFont);
        s14CentreFormat.setAlignment(Alignment.CENTRE);
        s14CentreFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        // ����,12,CENTRE,�߿�
        WritableCellFormat s12CentreFormat = new WritableCellFormat();
        WritableFont s12CentreFont = new WritableFont(WritableFont.createFont("����"), 12);
        s12CentreFormat.setFont(s12CentreFont);
        s12CentreFormat.setAlignment(Alignment.CENTRE);
        s12CentreFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        // ����,12,Left,�߿�
        WritableCellFormat s12LeftFormat = new WritableCellFormat();
        WritableFont s12LeftFont = new WritableFont(WritableFont.createFont("����"), 12);
        s12LeftFormat.setFont(s12LeftFont);
        s12LeftFormat.setAlignment(Alignment.LEFT);
        s12LeftFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        // ����,12,Right,�߿�
        WritableCellFormat s12RightFormat = new WritableCellFormat();
        WritableFont s12RightFont = new WritableFont(WritableFont.createFont("����"), 12);
        s12RightFormat.setFont(s12RightFont);
        s12RightFormat.setAlignment(Alignment.RIGHT);
        s12RightFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        // ��߿�
        WritableCellFormat leftBorderFormat = new WritableCellFormat();
        leftBorderFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
        // ѧ��
        String xn = zcfsForm.getSearchModel().getSearch_tj_xn()[0];
        String[] xqArr = zcfsForm.getSearchModel().getSearch_tj_xq();
        String xq = "";
        if (xqArr != null && xqArr.length > 0) {
            xq = xqArr[0];
        }
        //ѧ������
        List<HashMap<String, String>> xqList = Base.getXqList();
        String xqmc = "";
        for (HashMap<String, String> map : xqList) {
            if (map.get("xqdm").equals(xq)) {
                xqmc = map.get("xqmc");
                break;
            }
        }
        zcfsForm.setXn(xn);
        zcfsForm.setXn(xq);
        ZcxmDao zcxmDao = new ZcxmDao();
        List<HashMap<String, String>> zcxmList = zcxmDao.getZcxm_12861(zcfsForm);
        //������ʽ
        String pmfs = zcfsForm.getPmfs();
        if (StringUtil.isNull(pmfs)) {
            pmfs = DEFAULT_PMFS;
        }
        //��������
        zcfsForm.getPages().setPageSize(Integer.MAX_VALUE);
        List<HashMap<String, String>> dataList = dao.getZcfjgList_12861(zcfsForm, user, pmfs, zcxmList);
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }
        // ���������ļ�
        File file = new File(tempDir.getPath() + "/" + String.valueOf(System.currentTimeMillis()) + ".xls");
        file.setWritable(true);
        FileOutputStream stream = new FileOutputStream(file);
        // ����excel������
        WritableWorkbook wwb = Workbook.createWorkbook(stream);
        WritableSheet ws = wwb.createSheet("ѧ���ۺϲ�����", 0);
        String[] xnArrTemp = xn.split("-");
        String xnTemp = xnArrTemp[0].substring(2) + "/" + xnArrTemp[1].substring(2);
        // ============ �����������㽭����ְҵ����ѧԺ�� begin ==========
        HashMap<String, String> jqcjMap = zcxmDao.getGxhxm_searchTj_xnxq_12861(zcfsForm);
        String[] xg_zhcp_zypdb_zjjdzyjsxy_zdmc_arr = new String[]{"�ܳɼ�", "�γ�����", jqcjMap.get("xmmc"), "����", "����������", "Ӣ�Ｐ����"};
        String[] xg_zhcp_zypdb_zjjdzyjsxy_zd_arr = new String[]{"zcj", "kcms", "pjcj", "mc", "bjgms", "yyjqt"};
        // ============ �����������㽭����ְҵ����ѧԺ�� end ==========
        int col = 7 + zcxmList.size() + xg_zhcp_zypdb_zjjdzyjsxy_zdmc_arr.length;
        // ��1��
        ws.addCell(new Label(0, 0, "                             " + xnTemp + "ѧ��" + xqmc + "ѧ���ۺϲ�����", s14CentreFormat));
        ws.mergeCells(0, 0, col, 0);
        ws.addCell(new Label(0, 1, "ѧ��", s12LeftFormat));
        ws.mergeCells(0, 1, 0, 2);
        ws.addCell(new Label(1, 1, "����", s12LeftFormat));
        ws.mergeCells(1, 1, 1, 2);
        ws.addCell(new Label(2, 1, "�༶", s12LeftFormat));
        ws.mergeCells(2, 1, 2, 2);
        int colTemp = 3;
        int colMergeStart = colTemp;
        int zfflagCount = 1;
        for (int i = 0, j = zcxmList.size(); i < j; i++) {
            String xmmc = zcxmList.get(i).get("xmmc");
            String qzbl = zcxmList.get(i).get("qzbl");
            String zfflag = zcxmList.get(i).get("zfflag");
            if ("N1".equals(zfflag)) {
                ws.addCell(new Label(colTemp, 2, "�ܷ�", s12LeftFormat));
                colTemp++;
                ws.addCell(new Label(colTemp, 2, "С��", s12LeftFormat));
                colTemp++;
                // ============== ����ϲ� begin ==============
                ws.addCell(new Label(colMergeStart, 1, xmmc + "��" + qzbl + "%��", s12CentreFormat));
                ws.mergeCells(colMergeStart, 1, (colTemp - 1), 1);
//				System.out.println("==="+colMergeStart+"  " + (colTemp - 1));
                colMergeStart = colTemp;
                // ============== ����ϲ� end ==============
                zfflagCount++;
                if (zfflagCount == 2) {
                    // ============ �����������㽭����ְҵ����ѧԺ�� begin ==========
                    for (int k = 0; k < xg_zhcp_zypdb_zjjdzyjsxy_zdmc_arr.length; k++) {
                        ws.addCell(new Label(colTemp, 2, xg_zhcp_zypdb_zjjdzyjsxy_zdmc_arr[k], s12LeftFormat));
                        colTemp++;
                    }
                    // ============ �����������㽭����ְҵ����ѧԺ�� end ==========
                }
            } else if ("N0".equals(zfflag)) {
                ws.addCell(new Label(colTemp, 1, "�ܷ�", s12LeftFormat));
                ws.mergeCells(colTemp, 1, colTemp, 2);
                colTemp++;
                ws.addCell(new Label(colTemp, 1, "����", s12LeftFormat));
                ws.mergeCells(colTemp, 1, colTemp, 2);
                colTemp++;
                ws.addCell(new Label(colTemp, 1, "��ע", s12LeftFormat));
                ws.mergeCells(colTemp, 1, colTemp, 2);
                colTemp++;
            } else {
                ws.addCell(new Label(colTemp, 2, xmmc, s12LeftFormat));
                colTemp++;
            }
        }
        int row = 3;
        // ���ݼ�д��
        for (int m = 0; m < dataList.size(); m++) {
            HashMap<String, String> dataMap = dataList.get(m);
            ws.addCell(new Label(0, row, dataMap.get("xh"), s12LeftFormat));
            ws.addCell(new Label(1, row, dataMap.get("xm"), s12LeftFormat));
            ws.addCell(new Label(2, row, dataMap.get("bjmc"), s12LeftFormat));
            colTemp = 3; // ������
            zfflagCount = 1;
            for (int i = 0, j = zcxmList.size(); i < j; i++) {
                String fs = dataMap.get("fs" + i);
                String zfflag = zcxmList.get(i).get("zfflag");
                if ("N1".equals(zfflag)) {
                    ws.addCell(new Label(colTemp, row, fs, s12RightFormat));
                    colTemp++;
                    String fsn = dataMap.get("fsn" + i);
                    ws.addCell(new Label(colTemp, row, fsn, s12RightFormat));
                    colTemp++;
                    zfflagCount++;
                    if (zfflagCount == 2) {
                        // ============ �����������㽭����ְҵ����ѧԺ�� begin ==========
                        for (int k = 0; k < xg_zhcp_zypdb_zjjdzyjsxy_zd_arr.length; k++) {
                            String xg_zhcp_zypdb_zjjdzyjsxy_zd_v = dataMap.get(xg_zhcp_zypdb_zjjdzyjsxy_zd_arr[k]);
                            ws.addCell(new Label(colTemp, row, xg_zhcp_zypdb_zjjdzyjsxy_zd_v, s12RightFormat));
                            colTemp++;
                        }
                        // ============ �����������㽭����ְҵ����ѧԺ�� end ==========
                    }
                } else if ("N0".equals(zfflag)) {
                    ws.addCell(new Label(colTemp, row, fs, s12RightFormat));
                    colTemp++;
                    String bjpm = dataMap.get("bjpm");
                    ws.addCell(new Label(colTemp, row, bjpm, s12RightFormat));
                    colTemp++;
                    ws.addCell(new Label(colTemp, row, "", s12RightFormat));
                    colTemp++;
                } else {
                    ws.addCell(new Label(colTemp, row, fs, s12RightFormat));
                    colTemp++;
                }
            }
            row++;
        }
        wwb.write();
        wwb.close();
        return file;
    }

    /**
     * @param id
     * @return HashMap<String                                                               ,                                                               String> ��������
     * @throws
     * @����:����ID��ѯȡ����¼
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2013-8-15 ����09:31:28
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public HashMap<String, String> getQxjl(String id) {

        return dao.getQxjl(id);
    }


    /**
     * @param xh
     * @param xn
     * @param xq
     * @return HashMap<String                                                               ,                                                               String> ��������
     * @throws
     * @����: �۲��ܷ���Ϣ
     * @���ߣ������[���ţ�445]
     * @���ڣ�2013-8-29 ����11:07:23
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public HashMap<String, String> getZczfByXh(String xh, String xn, String xq) {
        return dao.getZczfByXh(xh, xn, xq);
    }


    /**
     * @param xh
     * @param xn
     * @param xq
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> ��������
     * @throws
     * @����:����ѧ�Ų�ѯѧ�������༶
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2013-11-10 ����11:26:33
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public HashMap<String, String> getCpbjListByXh(String xh, String xn, String xq) {
        return dao.getCpbjListByXh(xh, xn, xq);
    }


    /**
     * @param xh
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> ��������
     * @throws
     * @����:ͨ��ѧ�Ų�ѯ�ղ����
     * @���ߣ�ligl
     * @���ڣ�2013-11-30 ����03:07:04
     * @�޸ļ�¼:
     */
    public List<HashMap<String, String>> getZcfsList(String xh) {
        return dao.getZcfsList(xh);
    }

    /**
     * @����:��У����Ϣ��ӡѧ����ʱ��ȡ�۲���Ϣ��������ͨ��ѧ��ѧԺ��
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/8/22 11:48
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [xh, rownum]
     * @return: java.util.List<java.util.HashMap                               <                               java.lang.String                               ,                               java.lang.String>>
     */
    public List<HashMap<String, String>> getZcfsListForWord(String xh, int rownum) {
        List<HashMap<String, String>> list = dao.getZcfsListForWord(xh, String.valueOf(rownum));
        int m = rownum - list.size();
        for (int i = 0; i < m; i++) {
            list.add(new HashMap<String, String>());
        }
        return list;
    }

    /**
     * @����:ͨ��ѧ�Ų�ѯ�۲����
     */
    public List<HashMap<String, String>> getZcfsNList(String xh) {
        return dao.getZcfsNList(xh);
    }

    /**
     * @param xh
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> ��������
     * @throws
     * @����:ͨ��ѧ�Ų�ѯ�ղ����(�ϰ汾)
     * @���ߣ�ligl
     * @���ڣ�2013-11-30 ����03:07:04
     * @�޸ļ�¼:
     */
    public List<HashMap<String, String>> getZcfsListOld(String xh) {
        return dao.getZcfsListOld(xh);
    }

    /**
     * @param zcfsForm
     * @param user
     * @return boolean ��������
     * @throws Exception
     * @throws
     * @����:�鿴�Ƿ���δ�ύ��¼
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2015-2-9 ����03:55:46
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean isSubmitInfo(ZcfsModel zcfsForm, User user) throws Exception {

        String num = dao.isSubmitInfo(zcfsForm, user);

        return Integer.valueOf(num) > 0;

    }

    /**
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> ��������
     * @throws
     * @����:��õȼ�list
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2015-2-13 ����03:39:06
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getDj() {

        return dao.getDj();
    }

    /**
     * ��õȼ�����list
     *
     * @throws SQLException
     */
    public List<String> getDjmc() throws SQLException {
        return dao.getDjmc();
    }

    /**
     * @param zcfsForm
     * @param user
     * @return boolean ��������
     * @throws Exception
     * @throws
     * @����:��֤��������
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2015-3-4 ����11:33:26
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean isCanDownload(ZcfsModel model, User user, String num) throws Exception {

        List<HashMap<String, String>> bjxxMap = dao.getBjxxById(model, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        //�ɱ༭�۲�ֵ��۲���Ŀ
        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();
        List<HashMap<String, String>> zcxmList = null;

        if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())) {
            //�۲���Ŀ����Ϊ �꼶
            zcxmList = zcxmDao.getAllowEditZcfxmByNj(bjxxMap, model, user);
        } else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
            //�۲���Ŀ������Ϊ Ժϵ
            zcxmList = zcxmDao.getAllowEditZcfxmByXy(bjxxMap, model, user);
        } else {
            zcxmList = zcxmDao.getAllowEditZcfxm();
        }

        //��ѯѧ�����Ѿ�¼��ķ���
        List<HashMap<String, String>> zcfList = dao.getPageList(model, zcxmList, user);

        boolean boo = false;
        //�ж��Ƿ��Ƿ��н����
        if (zcfList != null && 0 != zcfList.size()) {
            boo = Integer.parseInt(zcfList.get(0).get("total")) > Integer.parseInt(num) ? false : true;
        }

        return boo;

    }

    /**
     * @param xh
     * @return
     * @throws SQLException String ��������
     * @throws
     * @����:�༶����
     * @���ߣ�����[���ţ�1104]
     * @���ڣ�2015-6-9 ����09:06:48
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public String getBjrs(String xh) throws SQLException {

        return dao.getBjrs(xh);
    }

    /**
     * @param xh
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> ��������
     * @throws
     * @����:��ȡѧ���������۲�����
     * @���ߣ�ChenQ[���ţ�856]
     * @���ڣ�2015-6-29 ����03:21:44
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getAllZcpmWithXh(String xh) {
        return dao.getAllZcpmWithXh(xh);
    }

    public File createImportTemplateDc(List<HashMap<String, String>> zcfList, List<HashMap<String, String>> zcxmList, User user) throws Exception {

        WritableWorkbook wwb = null;

        //�����ļ���� ����ʱĿ¼
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }

//		List<HashMap<String,String>> bjxxMap = dao.getBjxxById(model,user);
        //���������ļ�
        File file = new File(tempDir.getPath() + "/" + "�۲�ֵ���.xls");
        file.setWritable(true);

        try {
            FileOutputStream stream = new FileOutputStream(file);
            //����excel������
            wwb = Workbook.createWorkbook(stream);

            WritableSheet ws = wwb.createSheet("�۲�ֵ���", 0);

            ZcxmDao zcxmDao = new ZcxmDao();
            //�ɱ༭�۲�ֵ��۲���Ŀ
            CsszDao csszDao = new CsszDao();
            CsszModel csszModel = csszDao.getModel();

//			if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())){
//				//�۲���Ŀ����Ϊ �꼶
//				zcxmList = zcxmDao.getAllowEditZcfxmByNj(bjxxMap,model,user);
//			} else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())){
//				//�۲���Ŀ������Ϊ Ժϵ
//				zcxmList = zcxmDao.getAllowEditZcfxmByXy(bjxxMap,model,user);
//			} else {
//				zcxmList = zcxmDao.getAllowEditZcfxm();
//			}

            //�̶���ͷѧ�š�����
            ws.addCell(new Label(0, 0, "ѧ��"));
            ws.addCell(new Label(1, 0, "����"));//TODO
            ws.addCell(new Label(2, 0, "�༶����"));

            for (int i = 0, j = zcxmList.size(); i < j; i++) {
                Label label = new Label(3 + i, 0, zcxmList.get(i).get("xmmc"));
                WritableCellFeatures wcfeatures = new WritableCellFeatures();
                //����Ŀ������Ϊע�ͣ�����ʶ����
                wcfeatures.setComment(zcxmList.get(i).get("xmdm"));
                label.setCellFeatures(wcfeatures);
                ws.addCell(label);
            }


            for (int i = 0, j = zcfList.size(); i < j; i++) {
                ws.addCell(new Label(0, i + 1, zcfList.get(i).get("xh")));
                ws.addCell(new Label(1, i + 1, zcfList.get(i).get("xm")));
                ws.addCell(new Label(2, i + 1, zcfList.get(i).get("bjmc")));
                for (int m = 0, n = zcxmList.size(); m < n; m++) {
                    ws.addCell(new Label(m + 3, i + 1, zcfList.get(i).get("fs" + m)));
                }
            }


            wwb.write();
            wwb.close();
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
        }

        file.setWritable(true);
        return file;
    }

    /**
     * @param form
     * @param xmdms
     * @param user
     * @return
     * @throws Exception boolean ��������
     * @throws
     * @����: ѧҵˮƽ�����۲�֣������������
     * @���ߣ�������[���ţ�1123]
     * @���ڣ�2016-5-19 ����10:51:26
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean getIntefaceDataXysp(ZcfsModel form, String xmdms, User user) throws Exception {

        // ������ð༶��Ϣ
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        boolean bool = false;

        try {

            List<HashMap<String, String>> jktbs = zcxmDao.getJktbS(xmdms, "01");

            for (int j = 0; j < jktbs.size(); j++) {
                for (int i = 0; i < bjList.size(); i++) {
                    bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), jktbs.get(j).get("jktb"));
                    if (!bool) {
                        break;
                    }
                }
            }

            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    /**
     * @param form
     * @param xmdms
     * @param user
     * @return
     * @throws Exception boolean ��������
     * @throws
     * @����: ˼����������۲�֣������������
     * @���ߣ�������[���ţ�1123]
     * @���ڣ�2016-5-19 ����10:51:26
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean getIntefaceDataSxdd(ZcfsModel form, String xmdms, User user) throws Exception {

        // ������ð༶��Ϣ
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        boolean bool = false;

        try {

            List<HashMap<String, String>> jktbs = zcxmDao.getJktbS(xmdms, "02");

            for (int j = 0; j < jktbs.size(); j++) {
                for (int i = 0; i < bjList.size(); i++) {
                    bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), jktbs.get(j).get("jktb"));
                    if (!bool) {
                        break;
                    }
                }
            }

            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    /**
     * @param form
     * @param xmdms
     * @param user
     * @return
     * @throws Exception boolean ��������
     * @������ͬ���۲��_ֻ����bjdm
     * @���ߣ�׿��[����:1391]
     * @���ڣ�2017��2��28��
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean getIntefaceDatazcf(ZcfsModel form, User user) throws Exception {
        // ������ð༶��Ϣ
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);
        boolean bool = false;
        //ִ��ѧУ��Ӧ�Ĵ洢����
        String proName = "";
        if ("13011".equals(Base.xxdm)) {
            proName = "pro_zhcp_qdjdzctb";
        }
        for (HashMap<String, String> map : bjList) {
            bool = dao.getIntefaceData(map.get("bjdm"), proName);
            if (!bool) {
                break;
            }
        }
        return bool;

    }

    /**
     * @param bjdm
     * @param xn
     * @param xq
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> ��������
     * @throws
     * @����:�·��ύ״̬
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-9-5 ����03:23:45
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getYfTjzt(String bjdm, String xn, String xq) {
        return dao.getYfTjzt(bjdm, xn, xq);
    }

    /**
     * @param model
     * @param user
     * @return
     * @throws Exception String[] ��������
     * @throws
     * @����: �ɶ��м�ʦѧԺ�����ύ
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-9-6 ����10:38:38
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public String[] isNotCanSubmitYf(ZcfsModel model, User user) throws Exception {

        String[] ids = model.getId().split(",");

        List<String> canId = new ArrayList<String>();//�����ύ��id����
        StringBuffer noSubmit = new StringBuffer();
        boolean isNotCanSubmit = false;
        int i = 0;  //��¼�ύ����

        if (null == ids || ids.length <= 0) {
            return null;
        }
        for (String str : ids) {
            model.setId(str);
            if (isCanSubmitYf(model)) {
                submitZcfs(model, user); //�����ύ
                canId.add(str);//��¼id
                i++;
            } else {
                HashMap<String, String> bjInfo = dao.getBjInfo(str);
                noSubmit.append(bjInfo.get("bjmc"));
                noSubmit.append(",</br>");
                isNotCanSubmit = true;
            }
        }


        String str = noSubmit.toString();
        //ȥ�������ය��
        str = isNotCanSubmit ? str : _BCZSCID;
        return new String[]{String.valueOf(i), str};

    }

    /**
     * @param model
     * @return
     * @throws Exception boolean ��������
     * @throws
     * @����: �ɶ��м���ѧԺ�����۲���Ƿ�����ύ
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-9-6 ����10:44:30
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean isCanSubmitYf(ZcfsModel model) throws Exception {

        boolean isCanSubmit = false;

        //��Ĭ����Ŀ �� ͬ���ӿ���Ŀ�ķ����ӵ�������¼��
        initDefaultZcfs(model.getXn(), model.getXq(), model.getId());

        //��¼��Ŀ��*ѧ����  <= ������¼��
        isCanSubmit = Boolean.valueOf(dao.getSfyWlrYf(model));

        if (!isCanSubmit) {
            return false;
        }

        //����Ƿ���NULL��ֵ �ķ�����¼
        isCanSubmit = Integer.valueOf(dao.getNullZcfYf(model)) == 0;

        return isCanSubmit;
    }

    /**
     * @param t
     * @param user
     * @return
     * @throws Exception List<HashMap<String,String>> ��������
     * @throws
     * @����: �ɶ��м�ʦѧԺ�����ύ�۲��-�۲��ȡ����ѯ
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-9-6 ����02:05:18
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getZcfqxListYf(ZcfsModel t, User user)
            throws Exception {
        return dao.getZcfqxListYf(t, user);
    }

    /**
     * @param user
     * @param zcfsForm
     * @return
     * @throws Exception boolean ��������
     * @throws
     * @����: ȡ�������ύ�۲��
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-9-6 ����03:47:13
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean cancelTjOfYf(User user, ZcfsModel zcfsForm) throws Exception {

        String id = zcfsForm.getId();

        boolean cancelTj = false;
        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();

        String xn = csszModel.getXn();
        String xq = csszModel.getXq();


        //����ȡ����¼
        cancelTj = dao.insertTzjlOfYf(id, user, zcfsForm, xn, xq);

        if (!cancelTj) {
            return false;
        }

        //����������Ա��
        cancelTj = dao.updateCpmdOfYf(id, user, DEFAULT_QXTJ);


        if (cancelTj) {
            //�ɶ��м�ʦѧԺ��ĳ�༶��λ�ڸ��۲������������·ݼ�¼��ȡ���ύ������xg_zhcp_fstjjlb��
            dao.updateAytjzcfLastOne(id, xn, xq);
            List<HashMap<String, String>> list = getBjxxByIdOfYf(id);

            //�����۲��
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                Thread thread = new Thread(new ComputeZcpm(map.get("xn"), map.get("xq"), map.get("bjdm")));
                thread.start();
            }


            //	��������ѧ���Ի��жϣ��༶״̬�ύ��ɺ���²��������ڵ������趨
//			if(Globals.XXDM_CQLGDX.equals(Base.xxdm)){
//				// �����ύ��ɺ���ݱ���������Ŀ���õ��е�����
//				updateRssz(map.get("bjdm"));
//			}
//
        }

        return cancelTj;
    }

    /**
     * @param id
     * @return HashMap<String                                                               ,                                                               String> ��������
     * @throws
     * @����: �ɶ��м�ʦѧԺȡ���۲�ֻ�ȡ�༶��Ϣ
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-9-6 ����04:30:27
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getBjxxByIdOfYf(String id) {
        return dao.getBjxxByIdOfYf(id);
    }

    /**
     * @param xn
     * @param xq
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> ��������
     * @throws
     * @����: �ɶ��м�ʦѧԺ�����ύͳ�Ʋ�ѯ
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-9-7 ����11:33:19
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getZcfAyTjTjcxList(String xn, String xq) {
        return dao.getZcfAyTjTjcxList(xn, xq);

    }

    /**
     * @param t
     * @param user
     * @return
     * @throws Exception List<HashMap<String,String>> ��������
     * @����: �۲�༶�ύ��ѯ
     * @���ߣ�Penghui.Qu [���ţ�445]
     * @���ڣ�2013-7-24 ����10:16:40
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getZcbjListCk(ZcfsModel t, User user, String tjzt, String yf)
            throws Exception {
        return dao.getZcbjListCk(t, user, tjzt, yf);
    }

    /**
     * @param xmmc
     * @param xn
     * @param xq
     * @return HashMap<String                                                               ,                                                               String> ��������
     * @throws
     * @����: ������Ŀ����ѧ��ѧ�ڻ�ȡ�۲����[ͨ��]
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2017-11-15 ����11:37:19
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public HashMap<String, String> getZcfsByXmXnXqXh(String xmmc, String xn, String xq, String xh) {
        return dao.getZcfsByXmXnXqXh(xmmc, xn, xq, xh);
    }

    /**
     * ��ȡ���Ƴɼ����ּܷ�������Ϣ�б�.
     *
     * @param zcfsForm
     * @param user
     * @return java.util.List<java.util.HashMap                                                               <                                                               java.lang.String                                                               ,                                                               java.lang.String>>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-12-06 16:08
     * @throw Exception
     */
    public List<HashMap<String, String>> getXscjList(ZcfsModel zcfsForm, User user) throws Exception {

        return dao.getXscjList(zcfsForm, user);
    }

    /**
     * ���ɳɼ����ܱ�Excel�ļ�.
     *
     * @param bjcjhzModel
     * @return java.io.File
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-12-06 16:43
     * @throw Exception
     */
    public File getCjhzbFile(BjcjhzModel bjcjhzModel) throws Exception {

        Map<String, XscjhzModel> xscjhzModelMap = bjcjhzModel.getXscjhzModelMap();
        Collection<XscjhzModel> collection = xscjhzModelMap.values();
        File file = null;

        if (collection != null && !collection.isEmpty()) {
            XscjhzModel[] xscjhzModelArr = collection.toArray(new XscjhzModel[]{});

            //һ����������
            WritableFont titleFont_level1 = new WritableFont(WritableFont.ARIAL, 20, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
            //������������
            WritableFont titleFont_level2 = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
            //��ͷ����
            WritableFont headFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
            //��������
            WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);

            //һ�����ⵥԪ���ʽ
            WritableCellFormat titleCf_level1 = new WritableCellFormat(titleFont_level1);
            //�������ⵥԪ���ʽ
            WritableCellFormat titleCf_level2 = new WritableCellFormat(titleFont_level2);
            //��ͷ��Ԫ���ʽ
            WritableCellFormat headCf_oh = new WritableCellFormat(headFont);
            WritableCellFormat headCf_ov = new WritableCellFormat(headFont);
            //���ĵ�Ԫ���ʽ
            WritableCellFormat bodyCf = new WritableCellFormat(bodyFont);

            titleCf_level1.setAlignment(Alignment.CENTRE);
            titleCf_level1.setVerticalAlignment(VerticalAlignment.CENTRE);
            titleCf_level1.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            titleCf_level2.setAlignment(Alignment.CENTRE);
            titleCf_level2.setVerticalAlignment(VerticalAlignment.CENTRE);
            titleCf_level2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            headCf_oh.setAlignment(Alignment.CENTRE);
            headCf_oh.setVerticalAlignment(VerticalAlignment.CENTRE);
            headCf_oh.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            headCf_ov.setAlignment(Alignment.CENTRE);
            headCf_ov.setVerticalAlignment(VerticalAlignment.CENTRE);
            headCf_ov.setOrientation(Orientation.VERTICAL);
            headCf_ov.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            bodyCf.setAlignment(Alignment.CENTRE);
            bodyCf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            String xn = bjcjhzModel.getXn() == null ? "" : bjcjhzModel.getXn();
            String xqmc = bjcjhzModel.getXqmc() == null ? "" : bjcjhzModel.getXqmc();
            String bjmc = bjcjhzModel.getBjmc() == null ? "" : bjcjhzModel.getBjmc();
            String bzr = bjcjhzModel.getBzr() == null ? "" : bjcjhzModel.getBzr();
            int bjrs = xscjhzModelArr.length;
            Map<String, Set<String>> bjkcMap = bjcjhzModel.getBjkcMap();

            String title_level1 = xn + "ѧ��" + xqmc + "ѧ���ɼ����ܱ�";
            String title_level2 = "�༶��" + bjmc + "    " + "�����Σ�" + bzr + "    " + "�༶������" + bjrs;

            String fileName = bjmc + ".xls";
            file = new File(System.getProperty("java.io.tmpdir"), fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            //����������
            WritableWorkbook wwb = Workbook.createWorkbook(file);
            //����������
            WritableSheet sheet = wwb.createSheet(bjmc, 0);

            //���ø����п�
            sheet.setColumnView(0, 12);
            sheet.setColumnView(1, 12);
            sheet.setColumnView(2, 6);

            int s = 0;
            for (Set<String> kcSet : bjkcMap.values()) {
                int size = kcSet.size();
                //�ϲ���Ԫ��
                sheet.mergeCells(3 + s, 3, 3 + s + size - 1, 3);    //�γ�����
                s += size;
            }

            for (int n = 0; n < s; n++) {
                sheet.setColumnView(3 + n, 6);
            }

            sheet.setColumnView(3 + s, 6);
            sheet.setColumnView(4 + s, 6);
            sheet.setColumnView(5 + s, 6);
            sheet.setColumnView(6 + s, 6);
            sheet.setColumnView(7 + s, 6);
            sheet.setColumnView(8 + s, 6);

            //�����и�
//			sheet.setRowView(4, true);
            sheet.setRowView(4, 1600);

            //�ϲ���Ԫ��
            sheet.mergeCells(0, 0, 8 + s, 0);    //xxѧ��xxѧ��ѧ���ɼ����ܱ�
            sheet.mergeCells(0, 1, 8 + s, 1);    //�༶ ������ ѧ������
            sheet.mergeCells(0, 2, 0, 4);    //ѧ��
            sheet.mergeCells(1, 2, 1, 4);    //����
            sheet.mergeCells(2, 2, 2, 4);    //�Ա�
            sheet.mergeCells(3, 2, 3 + s, 2);    //ѧϰ�ɼ�
            sheet.mergeCells(3 + s, 3, 3 + s, 4);        //ƽ����
            sheet.mergeCells(4 + s, 2, 4 + s, 4);        //ѧ�ڵ���ѧ��
            sheet.mergeCells(5 + s, 2, 5 + s, 4);        //�ۺϳɼ�
            sheet.mergeCells(6 + s, 2, 8 + s, 2);        //����
            sheet.mergeCells(6 + s, 3, 6 + s, 4);        //�Ļ�����
            sheet.mergeCells(7 + s, 3, 7 + s, 4);        //����ѧ������
            sheet.mergeCells(8 + s, 3, 8 + s, 4);        //�ۺ�����

            //�������⼰��ͷ
            Label title_1 = new Label(0, 0, title_level1, titleCf_level1);
            Label title_2 = new Label(0, 1, title_level2, titleCf_level2);

            Label head_xh = new Label(0, 2, "ѧ��", headCf_ov);
            Label head_xm = new Label(1, 2, "����", headCf_ov);
            Label head_xb = new Label(2, 2, "�Ա�", headCf_ov);
            Label head_xxcj = new Label(3, 2, "ѧϰ�ɼ�", headCf_oh);
            Label head_pjf = new Label(3 + s, 3, "ƽ����", headCf_ov);
            Label head_xqdyxf = new Label(4 + s, 2, "ѧ�ڵ���ѧ��", headCf_ov);
            Label head_zhcj = new Label(5 + s, 2, "�ۺϳɼ�", headCf_ov);
            Label head_mc = new Label(6 + s, 2, "����", headCf_oh);
            Label head_whmc = new Label(6 + s, 3, "�Ļ�����", headCf_ov);
            Label head_dyxfmc = new Label(7 + s, 3, "����ѧ������", headCf_ov);
            Label head_zhpm = new Label(8 + s, 3, "�ۺ�����", headCf_ov);


            sheet.addCell(title_1);
            sheet.addCell(title_2);

            sheet.addCell(head_xh);
            sheet.addCell(head_xm);
            sheet.addCell(head_xb);
            sheet.addCell(head_xxcj);
            sheet.addCell(head_pjf);

            sheet.addCell(head_xqdyxf);
            sheet.addCell(head_zhcj);
            sheet.addCell(head_mc);
            sheet.addCell(head_whmc);
            sheet.addCell(head_dyxfmc);
            sheet.addCell(head_zhpm);

            int k = 0;
            for (Map.Entry<String, Set<String>> entry : bjkcMap.entrySet()) {
                String kclx = entry.getKey();
                Set<String> kcSet = entry.getValue();
                int size = kcSet.size();

                Label label = new Label(3 + k, 3, kclx, headCf_oh);
                sheet.addCell(label);
                int j = 0;
                for (String kcmc : kcSet) {
                    Label label_kcmc = new Label(3 + k + j, 4, kcmc, headCf_ov);
                    sheet.addCell(label_kcmc);
                    j++;
                }
                k += size;
            }

            //����������Ԫ��
            for (int j = 0; j < xscjhzModelArr.length; j++) {
                XscjhzModel xscjhzModel = xscjhzModelArr[j];
                Label body_xh = new Label(0, j + 5, xscjhzModel.getXh(), bodyCf);    //ѧ��
                Label body_xm = new Label(1, j + 5, xscjhzModel.getXm(), bodyCf);    //����
                Label body_xb = new Label(2, j + 5, xscjhzModel.getXb(), bodyCf);    //�Ա�

                sheet.addCell(body_xh);
                sheet.addCell(body_xm);
                sheet.addCell(body_xb);

                Map<String, String> cjMap = xscjhzModel.getCjMap();
                int m = 0;
                for (Set<String> kcSet : bjkcMap.values()) {
                    int size = kcSet.size();
                    int h = 0;
                    for (String kcmc : kcSet) {
                        Label label_kccj = new Label(3 + m + h, j + 5, cjMap.get(kcmc), bodyCf);
                        sheet.addCell(label_kccj);
                        h++;
                    }
                    m += size;
                }

                Label body_pjf = new Label(3 + m, j + 5, xscjhzModel.getPjf(), bodyCf);    //ƽ����
                Label body_xqdyxf = new Label(4 + m, j + 5, xscjhzModel.getDyf(), bodyCf);    //ѧ�ڵ���ѧ��
                Label body_zhcj = new Label(5 + m, j + 5, xscjhzModel.getZhf(), bodyCf);    //�ۺϳɼ�
                Label body_whmc = new Label(6 + m, j + 5, xscjhzModel.getPjfpm(), bodyCf);    //�Ļ�����
                Label body_dyxfmc = new Label(7 + m, j + 5, xscjhzModel.getDyfpm(), bodyCf);    //����ѧ������
                Label body_zhpm = new Label(8 + m, j + 5, xscjhzModel.getZhfpm(), bodyCf);    //�ۺ�����

                sheet.addCell(body_pjf);
                sheet.addCell(body_xqdyxf);
                sheet.addCell(body_zhcj);
                sheet.addCell(body_whmc);
                sheet.addCell(body_dyxfmc);
                sheet.addCell(body_zhpm);
            }
            wwb.write();
            wwb.close();
        }

        return file;
    }


    /**
     * ��ȡ�ܳɼ�����.
     *
     * @param zcfsForm
     * @param user
     * @return java.util.List<java.util.HashMap                                                               <                                                               java.lang.String                                                               ,                                                               java.lang.String>>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-12-06 16:08
     * @throw Exception
     */
    public String getCjpm(String xn, String xh, String bjmc) {
        return dao.getCjpm(xn, xh, bjmc);
    }

    /**
     * ��ȡ���޿���.
     *
     * @param zcfsForm
     * @param user
     * @return java.util.List<java.util.HashMap                                                               <                                                               java.lang.String                                                               ,                                                               java.lang.String>>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-12-06 16:08
     * @throw Exception
     */
    public String getBxks(String xn, String xh) {
        return dao.getBxks(xn, xh);
    }

    /**
     * ��ȡ���޿μ�����.
     *
     * @param zcfsForm
     * @param user
     * @return java.util.List<java.util.HashMap                                                               <                                                               java.lang.String                                                               ,                                                               java.lang.String>>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-12-06 16:08
     * @throw Exception
     */
    public String getBxkjgs(String xn, String xh) {
        return dao.getBxkjgs(xn, xh);
    }

    /**
     * @param zcfsForm
     * @param xmdms
     * @param user
     * @return boolean ��������
     * @throws Exception
     * @throws
     * @����:TODO(������һ�仰�����������������)
     * @���ߣ��Ų�·[���ţ�982]
     * @���ڣ�2018-5-18 ����01:43:07
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public boolean getIntefaceData_12688(String type, ZcfsModel zcfsForm, String xmdms, User user) throws Exception {

        // ������ð༶��Ϣ
        List<HashMap<String, String>> bjList = getBjxxByIds(zcfsForm, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        boolean bool = false;

        try {

            List<HashMap<String, String>> jktbs = zcxmDao.getJktbS(xmdms, "12688");
            String proName = "";
            String xmmc = "";
            if ("zy".equals(type)) {
                proName = "pro_zhcpzyftb_12688";
                xmmc = "������";
            } else if ("ty".equals(type)) {
                proName = "pro_zhcptyftb_12688";
                xmmc = "������";
            } else {
                proName = "pro_zhcpdyftb_12688";
                xmmc = "������";
            }

            for (int j = 0; j < jktbs.size(); j++) {
                if (xmmc.equals(jktbs.get(j).get("xmmc"))) {
                    for (int i = 0; i < bjList.size(); i++) {
                        bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), proName);
                        if (!bool) {
                            break;
                        }
                    }
                }
            }

            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    public boolean getIntefaceData_13431(ZcfsModel form, User user) throws Exception {
        ZcxmDao zcxmDao = new ZcxmDao();
        // ������ð༶��Ϣ
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        boolean bool = false;

        try {

            List<HashMap<String, String>> jktbs = zcxmDao.getJktbS(form.getXmdms(), "13431");

            for (int j = 0; j < jktbs.size(); j++) {
                if ("������".equals(jktbs.get(j).get("xmmc"))) {
                    for (int i = 0; i < bjList.size(); i++) {
                        bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), "pro_zhcptyftb_13431");
                        if (!bool) {
                            break;
                        }
                    }
                }
                if ("������".equals(jktbs.get(j).get("xmmc"))) {
                    for (int i = 0; i < bjList.size(); i++) {
                        bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), "pro_zhcpzyftb_13431");
                        if (!bool) {
                            break;
                        }
                    }
                }
                if ("�ӷ�".equals(jktbs.get(j).get("xmmc"))) {
                    for (int i = 0; i < bjList.size(); i++) {
                        bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), "pro_zhcpjiaftb_13431");
                        if (!bool) {
                            break;
                        }
                    }
                }
                if ("����".equals(jktbs.get(j).get("xmmc"))) {
                    for (int i = 0; i < bjList.size(); i++) {
                        bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), "pro_zhcpjianftb_13431");
                        if (!bool) {
                            break;
                        }
                    }
                }
            }

            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    /**
     * ͬ��ѧ�ֳɼ����۲�������
     *
     * @return
     */
    public boolean tbXfcj(User user) throws Exception {
        try {
            CsszModel csszModel = csszDao.getModel();
            String zcxmId = dao.getZcxmIdByMc("�γ̿��˳ɼ�", csszModel.getXn());
            List<HashMap<String, String>> xfcjList = dao.getXfcj(csszModel.getXn(), null);
            List<String[]> result=new ArrayList<>();

            for (HashMap<String, String> hashMap : xfcjList) {
                String[] param={hashMap.get("xh"),hashMap.get("xn"),"on",zcxmId,
                hashMap.get("xfcj"),user.getUserName(),hashMap.get("xh"),zcxmId};
                result.add(param);
            }
            dao.batchInsertZcfs(result);
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
        return true;

    }

}
