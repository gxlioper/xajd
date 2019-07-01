package com.zfsoft.xgxt.dycjgl.dycjwh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.views.utils.ArrayUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import jxl.CellFeatures;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.*;
import org.apache.struts.upload.FormFile;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DycjglService extends SuperServiceImpl<DycjglForm,DycjglDao> {
    public List<HashMap<String,String>> getStudentList(DycjglForm model) throws Exception {
        List<HashMap<String,String>>xmList =getXmList();
        return dao.getStudentList(model,xmList);
    }

    public List<HashMap<String,String>> getXmList() {
        return dao.getXmList();
    }

    public List<HashMap<String,String>> getBkList(DycjglForm model) throws Exception {
        List<HashMap<String,String>>xmList =getXmList();
        return dao.getBkList(model,xmList);
    }

    public List<HashMap<String,String>> getSfhg() {
        List<HashMap<String,String>> sfheList = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> map2 = new HashMap<String, String>();
        map2.put("xxmc","�ϸ�");
        HashMap<String,String> map3 = new HashMap<String, String>();
        map3.put("xxmc","���ϸ�");
        sfheList.add(map2);
        sfheList.add(map3);
        return sfheList;
    }

    public boolean savedycj(DycjglForm t, User user) throws Exception {
        //�жϸ�ѧ�ꡢѧ�ڣ���ѧ���Ƿ��Ѿ�¼���˴���Ŀ�ķ���
        HashMap<String,String> fsMap  = dao.getFsid(t);
        t.setZgh(user.getUserName());
        t.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss"));

        //�ޣ����� �У�����
        if (StringUtil.isNull(fsMap.get("guid"))){
            return dao.runInsert(t);
        } else {
            t.setGuid(fsMap.get("guid"));
            return dao.runUpdate(t);
        }
    }

    public HashMap<String,String> getXnXqInfo() {
        return dao.getXnXqInfo();
    }

    public List<HashMap<String,String>> getStudentByBjdm(DycjglForm model) {
        List<HashMap<String,String>>xmList =getXmList();
        return dao.getrStudentBuBjdm(model,xmList);
    }

    public List<HashMap<String,String>> getBkBhg(DycjglForm model) {
        List<HashMap<String,String>>xmList =getXmList();
        return dao.getBkBhg(model,xmList);
    }




    public File createImportTemplate(DycjglForm model, User user) {
        WritableWorkbook wwb = null;

        //�����ļ���� ����ʱĿ¼
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        if (!tempDir.exists()){
            tempDir.mkdir();
        }

        //���������ļ�
        File file = new File( tempDir.getPath() + "/" +"�����ɼ�����ģ��.xls");
        file.setWritable(true);

        try{
            FileOutputStream stream = new FileOutputStream(file);
            //����excel������
            wwb = Workbook.createWorkbook(stream);

            WritableSheet ws = wwb.createSheet("�����ɼ�����ģ��", 0);


            //����۲����Ŀ
            List<HashMap<String, String>> xmList = dao.getXmList();


            //�̶���ͷѧ�š�����
            ws.addCell(new Label(0, 0, "ѧ��"));
            ws.addCell(new Label(1, 0, "����"));

            for (int i = 0 , j = xmList.size() ; i < j ; i++){
                Label label = new Label(2+i, 0, xmList.get(i).get("xmmc"));
                WritableCellFeatures wcfeatures = new WritableCellFeatures();
                //����Ŀ������Ϊע�ͣ�����ʶ����
                wcfeatures.setComment(xmList.get(i).get("xmdm"));
                label.setCellFeatures(wcfeatures);
                ws.addCell(label);
            }

            //����ҳ
            model.getPages().setPageSize(Integer.MAX_VALUE);
            //��ѯѧ�����Ѿ�¼��ķ���
            List<HashMap<String,String>> zcfList = dao.getrStudentBuBjdm(model,xmList);
            for (int i = 0 , j = zcfList.size() ; i < j ; i++){
                ws.addCell(new Label(0, i+1, zcfList.get(i).get("xh")));
                ws.addCell(new Label(1, i+1, zcfList.get(i).get("xm")));
                ws.addCell(new Label(2, i+1, zcfList.get(i).get("fs0")));
                ws.addCell(new Label(3, i+1, zcfList.get(i).get("fs1")));
                ws.addCell(new Label(4, i+1, zcfList.get(i).get("fs2")));
            }
            wwb.write();
            wwb.close();
        }catch (Exception e) {
            throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
        }

        file.setWritable(true);
        return file;
    }

    public File importDycj(DycjglForm model, User user) throws Exception {
        FormFile importFile = model.getImportFile();
        //��FormFile ת��ΪFile ����
        File file = FileUtil.conversionFormFile(importFile);

        //��ȡExcel������
        Workbook book = Workbook.getWorkbook(file);

        List<HashMap<String, String>> xmList = dao.getXmList();

        WritableWorkbook wwb  =  Workbook.createWorkbook(file, book);
        WritableSheet ws = wwb.getSheet(0);
        //���ô�����Ϣ��ʾ���п�
        ws.setColumnView(xmList.size()+2, 30);



        //����ģ�����۲�ṹ�Ƚ���֤
        for (int i = 0 , j = xmList.size() ; i < j ; i++){

            String xmdm = xmList.get(i).get("xmdm");
            String xmmc = xmList.get(i).get("xmmc");

            CellFeatures cellFeatures = ws.getCell(2+i, 0).getCellFeatures();

            if (cellFeatures == null){
                throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
            }

            String cellComment = cellFeatures.getComment();
            String cellContent = ws.getCell(2+i, 0).getContents();

//            //��֤�±�ͷ�Ƿ��뵼��ģ��һ��
//            if (!xmdm.equals(cellComment.trim()) || !xmmc.equals(cellContent.trim())){
//                throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
//            }
        }

		/*
		 * ��⵼������
		 */
        int rows = ws.getRows();
        List<String[]> params = new ArrayList<String[]>();

        boolean checkResult = true;

        for (int i = 1 ; i < rows ; i++){
            StringBuilder errorMessage = new StringBuilder();

            String xh = ws.getCell(0, i).getContents();
            String xm = ws.getCell(1, i).getContents();

            if (StringUtil.isNull(xh) || StringUtil.isNull(xm)){
                //ѧ�ź�������ô����Ϊ���أ�
                errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_XHXM_NULL));
            }else{
                HashMap<String,String>xqxn =  getXnXqInfo();
                for (int m = 0 ; m < xmList.size() ; m++){
                    String xmfs = ws.getCell(m+2, i).getContents().trim();
                    //�ռ�����
                    String[] param = null;
                    try {//�ų�����пհ׵õط�
                        int a = Integer.valueOf(xmfs);
                        if(a>=0){
                            param = new String[]{xh,xqxn.get("xn"),xqxn.get("xqdm"),xmList.get(m).get("xmdm"),xmfs,user.getUserName(),xh,xmList.get(m).get("xmdm")};
                        }
                    }catch (Exception e)
                    {
                        continue;
                    }


                    params.add(param);
                }
            }


            //������Ϣ׷��
            if (errorMessage.length() > 0){
                WritableCellFormat wcf = new WritableCellFormat();
                WritableFont wf = new WritableFont(WritableFont.ARIAL);
                wf.setColour(Colour.RED);
                wcf.setFont(wf);
                wcf.setAlignment(Alignment.CENTRE);
                ws.addCell(new Label(xmList.size()+2, i, errorMessage.toString(),wcf));
                checkResult = false;
            }
        }


        //��֤OK�ĵ��룬ʧ�ܵĵ���
        if(!params.isEmpty()){
            System.out.println("action========"+System.currentTimeMillis());
            dao.batchInsert(params);//��������
            System.out.println("end=========="+System.currentTimeMillis());
        }


        if (!checkResult){
            WritableSheet ws1 = wwb.createSheet("��������", 1);
            ws1.addCell(new Label(0,0,ws.getCell(0,0).getContents()));
            ws1.addCell(new Label(1,0,ws.getCell(1,0).getContents()));
            ws1.addCell(new Label(2,0,ws.getCell(2,0).getContents()));
            ws1.addCell(new Label(3,0,ws.getCell(3,0).getContents()));
            ws1.addCell(new Label(4,0,ws.getCell(4,0).getContents()));
            ws1.addCell(new Label(5,0,ws.getCell(5,0).getContents()));
            for (int i = 0 , j = xmList.size() ; i < j ; i++){
                Label label = new Label(2+i, 0, xmList.get(i).get("xmmc"));
                WritableCellFeatures wcfeatures = new WritableCellFeatures();
                //����Ŀ������Ϊע�ͣ�����ʶ����
                wcfeatures.setComment(xmList.get(i).get("xmdm"));
                label.setCellFeatures(wcfeatures);
                ws1.addCell(label);
            }
            wwb.removeSheet(0);
            wwb.write();
            wwb.close();
            return file;
        }
        return null;

    }

    public File createImportTemplateBhg(DycjglForm model, User user) {

        WritableWorkbook wwb = null;

        //�����ļ���� ����ʱĿ¼
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        if (!tempDir.exists()){
            tempDir.mkdir();
        }

        //���������ļ�
        File file = new File( tempDir.getPath() + "/" +"�����ɼ������������ģ��.xls");
        file.setWritable(true);
        try{
            FileOutputStream stream = new FileOutputStream(file);
            //����excel������
            wwb = Workbook.createWorkbook(stream);

            WritableSheet ws = wwb.createSheet("�����ɼ������������ģ��", 0);
            //����۲����Ŀ
            List<HashMap<String, String>> xmList = dao.getXmList();
            //�̶���ͷѧ�š�����
            ws.addCell(new Label(0, 0, "ѧ��"));
            ws.addCell(new Label(1, 0, "����"));
            ws.addCell(new Label(2, 0, "���ϸ�ѧ���������"));
            ws.addCell(new Label(3, 0, "�Ƿ�ϸ�"));
            //����ҳ
            model.getPages().setPageSize(Integer.MAX_VALUE);
            //��ѯѧ�����Ѿ�¼��ķ���
            List<HashMap<String,String>> bkBhgList = dao.getBkBhg(model,xmList);
            for (int i = 0 , j = bkBhgList.size() ; i < j ; i++){
                ws.addCell(new Label(0, i+1, bkBhgList.get(i).get("xh")));
                ws.addCell(new Label(1, i+1, bkBhgList.get(i).get("xm")));
                ws.addCell(new Label(2, i+1, bkBhgList.get(i).get("bkqk")));
                ws.addCell(new Label(3, i+1, bkBhgList.get(i).get("bksfhg")));
            }
            wwb.write();
            wwb.close();
        }catch (Exception e) {
            throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
        }
        file.setWritable(true);
        return file;
    }



    public File importDycjBhg(DycjglForm model, User user) throws Exception {
        FormFile importFile = model.getImportFile();
        //��FormFile ת��ΪFile ����
        File file = FileUtil.conversionFormFile(importFile);

        //��ȡExcel������
        Workbook book = Workbook.getWorkbook(file);
        List<HashMap<String, String>> xmList = dao.getXmList();
        WritableWorkbook wwb  =  Workbook.createWorkbook(file, book);
        WritableSheet ws = wwb.getSheet(0);
        //���ô�����Ϣ��ʾ���п�
        ws.setColumnView(2+2, 30);
		/*
		 * ��⵼������
		 */
        int rows = ws.getRows();
        List<String[]> params = new ArrayList<String[]>();

        boolean checkResult = true;

        for (int i = 1 ; i < rows ; i++){
            StringBuilder errorMessage = new StringBuilder();

            String xh = ws.getCell(0, i).getContents();
            String xm = ws.getCell(1, i).getContents();

            if (StringUtil.isNull(xh) || StringUtil.isNull(xm)){
                //ѧ�ź�������ô����Ϊ���أ�
                errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_XHXM_NULL));
            }else{
                HashMap<String,String>xqxn =  getXnXqInfo();

                    String bkqk = ws.getCell(2, i).getContents().trim();
                    String bksfhg = ws.getCell(3, i).getContents().trim();
                    //�ռ�����
                    String[] param = new String[]{xh,xqxn.get("xn"),xqxn.get("xqdm"),bkqk,bksfhg,user.getUserName(),xh,xqxn.get("xqdm"),xqxn.get("xn"),};
                    params.add(param);
            }
            //������Ϣ׷��
            if (errorMessage.length() > 0){
                WritableCellFormat wcf = new WritableCellFormat();
                WritableFont wf = new WritableFont(WritableFont.ARIAL);
                wf.setColour(Colour.RED);
                wcf.setFont(wf);
                wcf.setAlignment(Alignment.CENTRE);
                ws.addCell(new Label(2+2, i, errorMessage.toString(),wcf));
                checkResult = false;
            }
        }


        //��֤OK�ĵ��룬ʧ�ܵĵ���
        if(!params.isEmpty()){
            System.out.println("action========"+System.currentTimeMillis());
            dao.batchInsertBhg(params);//��������
            System.out.println("end=========="+System.currentTimeMillis());
        }


        if (!checkResult){
            WritableSheet ws1 = wwb.createSheet("��������", 1);
            ws1.addCell(new Label(0,0,ws.getCell(0,0).getContents()));
            ws1.addCell(new Label(1,0,ws.getCell(1,0).getContents()));
            ws1.addCell(new Label(2,0,ws.getCell(2,0).getContents()));
            ws1.addCell(new Label(3,0,ws.getCell(3,0).getContents()));
            wwb.removeSheet(0);
            wwb.write();
            wwb.close();
            return file;
        }
        return null;

    }

    public List<HashMap<String,String>> getbjQuery(DycjglForm model, User user) throws Exception {
        List<HashMap<String, String>> xmList = dao.getXmList();
        return dao.getbjQuery(model,xmList);
    }
}
