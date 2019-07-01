package xsgzgl.gyjc.bbtj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import xgxt.form.User;
import xgxt.utils.date.MoneyFormat;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BbscService extends SuperServiceImpl<BbscForm,BbscDao> {

    public List<HashMap<String,String>> getResultList(BbscForm model, User user) throws Exception {
        return dao.getPageList(model,user);
    }

    public List<HashMap<String,String>> getAll(BbscForm model, User user) throws Exception {
        return dao.getAll(model,user);
    }

    public File getBbscFile(List<HashMap<String, String>> resultList) throws Exception {
        //画Excel
        String fileName = System.currentTimeMillis() + ".xls";
        File file = new File(System.getProperty("java.io.tmpdir"), fileName);

        if (!file.exists()) {
            file.createNewFile();
        }
        //创建工作簿
        WritableWorkbook wwb = Workbook.createWorkbook(file);

        //设置相关格式
        WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);//设置标题字体
        WritableFont headFont = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);//设置表头字体
        WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);//设置正文字体


        WritableCellFormat title_cf = new WritableCellFormat(tileFont);//设置标题单元格字体
        WritableCellFormat head_cf = new WritableCellFormat(headFont);//设置正文表头字体
        WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体
        WritableCellFormat body_bz_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体
        body_cf.setWrap(true);
        title_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置标题单元格对齐

        //创建工作表
        WritableSheet sheet = wwb.createSheet("卫生检查统计报表", 0);
        //设置各列列宽
        sheet.setColumnView(0, 10);
        sheet.setColumnView(1, 15);
        sheet.setColumnView(2, 25);
        sheet.setColumnView(3, 15);
        sheet.setColumnView(4, 25);
        sheet.setColumnView(5, 15);
        sheet.setColumnView(6, 10);
        sheet.setRowView(3, 500, false);


        Label h_0_2 = new Label(0, 0, "序号", head_cf);
        Label h_1_2 = new Label(1, 0, "楼栋号", head_cf);
        Label h_2_2 = new Label(2, 0, "寝室号", head_cf);
        Label h_3_2 = new Label(3, 0, "所属学院", head_cf);
        Label h_3_3 = new Label(4, 0, "检查时间", head_cf);
        Label h_4_3 = new Label(5, 0, "检查类别", head_cf);
        Label h_5_3 = new Label(6, 0, "评价等级", head_cf);
        Label h_6_1 = new Label(7, 0, "观测点1", head_cf);
        Label h_6_2 = new Label(8, 0, "观测点2", head_cf);
        Label h_6_3 = new Label(9, 0, "观测点3", head_cf);
        Label h_6_4 = new Label(10, 0, "观测点4", head_cf);
        Label h_6_5 = new Label(11, 0, "观测点5", head_cf);
        Label h_6_6 = new Label(12, 0, "观测点6", head_cf);
        Label h_6_7 = new Label(13, 0, "观测点7", head_cf);
        Label h_6_8 = new Label(14, 0, "观测点8", head_cf);
        Label h_6_9 = new Label(15, 0, "观测点9", head_cf);
        Label h_6_10 = new Label(16, 0, "观测点10", head_cf);
        Label h_6_11 = new Label(17, 0, "观测点11", head_cf);
        Label h_6_12 = new Label(18, 0, "观测点12", head_cf);
        Label h_6_13 = new Label(19, 0, "观测点13", head_cf);
        Label h_6_14 = new Label(20, 0, "观测点14", head_cf);
        Label h_6_15 = new Label(21, 0, "观测点15", head_cf);
        Label h_6_16 = new Label(22, 0, "观测点16", head_cf);
        Label h_6_17 = new Label(23, 0, "观测点17", head_cf);
        Label h_6_18 = new Label(24, 0, "观测点18", head_cf);
        Label h_6_19 = new Label(24, 0, "观测点19", head_cf);


        sheet.addCell(h_0_2);
        sheet.addCell(h_1_2);
        sheet.addCell(h_2_2);
        sheet.addCell(h_3_2);
        sheet.addCell(h_3_3);
        sheet.addCell(h_4_3);
        sheet.addCell(h_5_3);
        sheet.addCell(h_6_1);
        sheet.addCell(h_6_2);
        sheet.addCell(h_6_3);
        sheet.addCell(h_6_4);
        sheet.addCell(h_6_5);
        sheet.addCell(h_6_6);
        sheet.addCell(h_6_7);
        sheet.addCell(h_6_8);
        sheet.addCell(h_6_9);
        sheet.addCell(h_6_10);
        sheet.addCell(h_6_11);
        sheet.addCell(h_6_12);
        sheet.addCell(h_6_13);
        sheet.addCell(h_6_14);
        sheet.addCell(h_6_15);
        sheet.addCell(h_6_16);
        sheet.addCell(h_6_17);
        sheet.addCell(h_6_18);
        sheet.addCell(h_6_19);


        //遍历创建单元格
        int size = resultList.size();
        if (size > 0) {
            for (int j = 0; j < size; j++) {
                Map<String, String> map = resultList.get(j);
                //基本信息
                Label xuhao = new Label(0, j + 1,""+(j+1)+"", body_cf);    //序号
                Label ldmc = new Label(1, j + 1, map.get("ldmc"), body_cf);        //楼栋号
                Label qsh = new Label(2, j + 1, map.get("qsh"), body_cf);        //寝室号
                Label bmmc = new Label(3, j + 1, map.get("bmmc"), body_cf);        //所属学院
                Label jcsj = new Label(4, j + 1, map.get("jcsj"), body_cf);        //检查时间
                Label jclx = new Label(5, j + 1, map.get("jclx"), body_cf);        //检查类别
                Label pjdj = new Label(6, j + 1, map.get("pjdj"), body_cf);        //评价等级
                sheet.addCell(xuhao);
                sheet.addCell(ldmc);
                sheet.addCell(qsh);
                sheet.addCell(bmmc);
                sheet.addCell(jcsj);
                sheet.addCell(jclx);
                sheet.addCell(pjdj);


                String type = map.get("jclx");//检查类型
                String lddm = map.get("lddm");//楼栋代码
                String qshm = map.get("qsh");//寝室号
                String pid = map.get("pid");//jcrq 或者 ccid
                if ("卫生检查".equals(type)) {
                    //整体评价
                    List<HashMap<String, String>> wsjcztpjList = getwsjcZtpj(pid, lddm, qshm);
                    for (int i = 0; i < wsjcztpjList.size(); i++) {
                        HashMap<String, String> ztpjMap = wsjcztpjList.get(i);
                        String pjdm = ztpjMap.get("pjdm");
                        String num = pjdm.substring(2);
                        int a = Integer.parseInt(num);
                        Label ztpj = new Label(a + 6, j + 1, "√", body_cf);
                        sheet.addCell(ztpj);
                    }
                    //个人评价
                    List<HashMap<String, String>> wsjcgrpjList = getwsjcGrpj(pid, lddm, qshm);
                    if(wsjcgrpjList.size()>0) {
                        HashMap<String, String> grpjResult = new HashMap<String, String>();//key 习惯编号 value 床位号+名字
                        for (int i = 0; i < wsjcgrpjList.size(); i++) {
                            HashMap<String, String> grpjMap = wsjcgrpjList.get(i);
                            String pjdm = grpjMap.get("pjdm");
                            if(pjdm == null)
                            {
                                pjdm = "0001";
                            }
                            String num = pjdm.substring(3);
                            String cwh = grpjMap.get("cwh");
                            String xm = grpjMap.get("xm");
                            if (grpjResult.get(num) == null) {
                                grpjResult.put(num, cwh + xm);
                            } else {
                                String cwhXm = grpjResult.get(num);
                                cwhXm = cwhXm + "、" + cwh + xm;
                                grpjResult.put(num, cwhXm);
                            }
                        }
                        //遍历grpjResult
                        for (Map.Entry<String, String> entry : grpjResult.entrySet()) {
                            String num = entry.getKey();
                            String content = entry.getValue();
                            int a = Integer.parseInt(num);
                            Label grpj = new Label(a + 17, j + 1, content, body_cf);
                            sheet.addCell(grpj);
                        }
                    }
                }
                else if ("卫生抽查".equals(type)) {
                        //整体评价
                        List<HashMap<String, String>> wsccztpjList = wsccZtpj(pid, lddm, qshm);
                        for (int i = 0; i < wsccztpjList.size(); i++) {
                            HashMap<String, String> ztpjMap = wsccztpjList.get(i);
                            String pjdm = ztpjMap.get("pjdm");
                            String num = pjdm.substring(2);
                            int a = Integer.parseInt(num);
                            Label ztpj = new Label(a + 6, j + 1, "√", body_cf);
                            sheet.addCell(ztpj);
                        }
                        //个人评价
                        List<HashMap<String, String>> wsccgrpjList = getwsccGrpj(pid, lddm, qshm);

                    if(wsccgrpjList.size()>0) {
                        HashMap<String, String> grpjResult = new HashMap<String, String>();//key 习惯编号 value 床位号+名字
                        for (int i = 0; i < wsccgrpjList.size(); i++) {
                            HashMap<String, String> grpjMap = wsccgrpjList.get(i);
                            String pjdm = grpjMap.get("pjdm");
                            String num = pjdm.substring(3);
                            String cwh = grpjMap.get("cwh");
                            String xm = grpjMap.get("xm");
                            if (grpjResult.get(num) == null) {
                                grpjResult.put(num, cwh + xm);
                            } else {
                                String cwhXm = grpjResult.get(num);
                                cwhXm = cwhXm + "、" + cwh + xm;
                                grpjResult.put(num, cwhXm);
                            }
                        }
                        //遍历grpjResult
                        for (Map.Entry<String, String> entry : grpjResult.entrySet()) {
                            String num = entry.getKey();
                            String content = entry.getValue();
                            int a = Integer.parseInt(num);
                            Label grpj = new Label(a + 17, j + 1, content, body_cf);
                            sheet.addCell(grpj);
                        }
                    }

                    }
                    sheet.setRowView(j + 1, 500, false);

                }
            }

            //如果数据为空
            if (resultList == null || resultList.size() == 0) {
                //创建工作表
                WritableSheet sheetNull = wwb.createSheet("本次导出数据为空", 0);
                sheetNull.setColumnView(0, 15);
                Label msg = new Label(0, 0, "暂无数据！");
                sheetNull.addCell(msg);
            }
            wwb.write();
            wwb.close();
            return file;
        }


    private List<HashMap<String,String>> getwsccGrpj(String pid, String lddm, String qshm) {
        return dao.getwsccGrpj(pid,lddm,qshm);
    }

    private List<HashMap<String,String>> getwsjcGrpj(String pid, String lddm, String qshm) {
        return dao.getwsjcGrpj(pid,lddm,qshm);
    }

    private List<HashMap<String,String>> wsccZtpj(String pid, String lddm, String qshm) {
        return dao.getwsccZtpj(pid,lddm,qshm);
    }

    //卫生检查寝室整体评价
    private List<HashMap<String,String>> getwsjcZtpj(String pid, String lddm, String qshm) {
        return dao.getwsjcZtpj(pid,lddm,qshm);
    }


}
