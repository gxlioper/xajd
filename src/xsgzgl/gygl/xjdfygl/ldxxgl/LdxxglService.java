package xsgzgl.gygl.xjdfygl.ldxxgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import org.apache.commons.lang.StringUtils;
import xsgzgl.gygl.xjdfygl.cwxxgl.CwxxglForm;
import xsgzgl.gygl.xjdfygl.cwxxgl.CwxxglService;
import xsgzgl.gygl.xjdfygl.qsxxgl.QsxxglForm;
import xsgzgl.gygl.xjdfygl.qsxxgl.QsxxglService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class LdxxglService extends SuperServiceImpl<LdxxglForm,LdxxglDao> {
    private CwxxglService cwxxglService = new CwxxglService();
    private QsxxglService qsxxglService = new QsxxglService();
    //��ȡУ���б�
    public List<HashMap<String,String>> getXqList(){
        return dao.getXqList();
    }
    public HashMap<String,String> getXqdmByLddm(String lddm){
        return dao.getXqdmByLddm(lddm);
    }
    public List<HashMap<String,String>> getXslxList(){
        return dao.getXslxList();
    }
    //��ʼ��¥������������ֵ
    public List<String> cshcs(){
        List<String> list = new ArrayList<String>();
        for(int i=1;i<31;i++){
            String cs = String.valueOf(i);
            list.add(cs);
        }
        return list;
    }
    //��ʼ����ʼ���������
    public List<String> qsch(){
        List<String> list = new ArrayList<String>();
        for(int i=-4;i<6;i++){
            String cs = String.valueOf(i);
            list.add(cs);
        }
        return list;
    }
    //��ȡ��ӦУ����¥����Ϣ�б�
    public List<HashMap<String,String>> getLdxxByXq(String xqdm){
        return dao.getLdxxByXq(xqdm);
    }

    @Override
    public LdxxglForm getModel(String keyValue) throws Exception {
        return dao.getModel(keyValue);
    }

    public boolean qsscSave(LdxxglForm model) throws Exception {
        //����¥��
        boolean flag = runUpdate(model);
        if(flag){
            //��������
            flag = qssc(model);
        }
        return flag;
    }

    public boolean qssc(LdxxglForm ldglModel) throws SQLException {
        List<QsxxglForm> qsxxglForms = ldglModel.getQsxx();
        List<String[]> cwxxParams = new ArrayList<String[]>();
        List<String[]> qsxxParams = new ArrayList<String[]>();
        for(QsxxglForm qsglModel : qsxxglForms){
            int qss = Integer.parseInt(qsglModel.getQss());
            for(int i=0;i<qss;i++){

                String qsh = this.scqsh(ldglModel.getSzm(),qsglModel.getCh(),ldglModel.getWs(),i);
                String[] qsxx = {ldglModel.getLddm(),qsh,qsglModel.getCh(),qsglModel.getQsxb(),qsglModel.getCws()
                                ,qsglModel.getSfbz(),qsglModel.getSfykt(),qsglModel.getSfywsj()};
                qsxxParams.add(qsxx);
                int cws = Integer.valueOf(qsglModel.getCws());
                for(int j=0;j<cws;j++){
                    String cwh = sccwh(j,true);
                    String[] cwxx = {ldglModel.getLddm(),qsh,cwh};
                    cwxxParams.add(cwxx);
                }
            }
        }
        boolean flag = cwxxglService.batchInsert(cwxxParams);
        if(flag){
            flag = qsxxglService.batchInsert(qsxxParams);
        }
        return flag;
    }

    public boolean qsscBc(LdxxglForm ldglModel) throws Exception {
        //����¥��
        boolean flag = runUpdate(ldglModel);
        if(flag){
            flag = qsscCkUpdate(ldglModel);
        }
        return flag;
    }

    public boolean qsscCkUpdate(LdxxglForm ldglModel) throws Exception {
        return qsxxglService.batchUpdate(ldglModel.getQsxx(),ldglModel.getLddm());
    }

    public boolean isExistQs(String lddm){
        return dao.isExistQs(lddm);
    }

    public List<HashMap<String,String>> getQsxx(String lddm){
        return dao.getQsxx(lddm);
    }

    /**
     * �������Һ�
     * @param szm ����ĸ
     * @param ch ���
     * @param ws λ��
     * @param i
     */
    private String scqsh(String szm,String ch,String ws,int i){
        String qsh = "";
        if(StringUtils.isNotEmpty(szm)){
            qsh += szm;
        }
        qsh += ch;
        return String.format(qsh+"%0"+(Integer.valueOf(ws)-1)+"d",i+1);
    }

    /**
     * ���ɴ�λ��
     * @return
     */
    private String sccwh(int i,boolean isChar){
        if(isChar){
            return String.valueOf((char)('A'+(char)i));
        } else {
            return String.valueOf(1+i);
        }
    }
}
