package xsgzgl.gygl.cwfpgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import org.springframework.util.CollectionUtils;
import xgxt.form.User;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class CwfpglNewService extends SuperServiceImpl<CwfpglForm,CwfpglNewDao> {

    public List<HashMap<String,String>> searchList(CwfpglForm t, User user) throws Exception {
        if("xy".equals(t.getFpfs())){
            return dao.getPageList(t,user);
        } else {
            return dao.getSybjList(t,user);
        }
    }
    //�꼶������
    public List<HashMap<String,String>> getNjList(){
        return dao.getNjList();
    }
    //�꼶��������Ժ�����ã�
    public List<HashMap<String,String>> getNjListForSy(){
        return dao.getNjListForSy();
    }
    //ѧԺ������
    public List<HashMap<String,String>> getXyListByNj(String nj){
        return dao.getXyListByNj(nj);
    }
    //��ȡ��Ժ������
    public List<HashMap<String,String>> getSyListByNj(String nj){
        return dao.getSyListByNj(nj);
    }
    //רҵ������
    public List<HashMap<String,String>> getZyListByNjXy(String nj,String xy){
        return dao.getZyListByNjXy(nj,xy);
    }
    //�༶������
    public List<HashMap<String,String>> getBjListByNjXyZy(String nj,String xy,String zy){
        return dao.getBjListByNjXyZy(nj, xy, zy);
    }
    //��ȡ�����༶��������Ժ�����ã�
    public List<HashMap<String,String>> getBjListByNjSy(String nj,String sy){
        return dao.getBjListByNjSy(nj, sy);
    }
    //��ȡ��ǰ�༶�ѷ���ͳ�Ʊ����Ϣ
    public List<HashMap<String,String>> getYfpTjXx(String nj,String xy,String zy,String bj){
        return dao.getYfpTjXx(nj, xy, zy, bj);
    }
    //��ȡ��ǰ�༶�ѷ���ͳ�Ʊ����Ϣ����Ժ�����ã�
    public List<HashMap<String,String>> getYfpTjXxForSy(String nj,String sy,String bj){
        return dao.getYfpTjXxForSy(nj, sy, bj);
    }
    //��ȡ��ǰ�༶�ѷ���ͳ����Ϣ
    public HashMap<String,String> getBjTjXx(String nj,String xydm,String zydm,String zybj){
        return dao.getBjTjXx(nj,xydm,zydm,zybj);
    }
    //��ȡ��ǰ�༶�ѷ���ͳ����Ϣ����Ժ�����ã�
    public HashMap<String,String> getBjTjXxForSy(String nj,String sydm,String bjdm){
        return dao.getBjTjXxForSy(nj,sydm,bjdm);
    }
    //��ȡ¥��
    public List<HashMap<String,String>> getLdLc(String lddm,String qsxb,User user){
        return dao.getLcxx(lddm, qsxb, user);
    }
    //����¥���ȡ���Ҵ�λ��Ϣ
    public Map<String,List<HashMap<String,String>>> getQscw(String key,User user){
        String[] lddmAndCh = key.split("@!!!");
        //��λ
        List<HashMap<String,String>> cwList = dao.getQsCw(lddmAndCh[0], lddmAndCh[1], user);

        return this.groupBy(cwList);
    }
    //ѧԺ���䱣��
    public boolean save(CwfpglForm t,User user) throws Exception {

        List<String> cwList = this.cwcl(t,user);

        return dao.save(t,cwList);
    }

    //��Ժ���䱣��
    public boolean saveForSy(CwfpglForm t,User user) throws Exception {

        List<String> cwList = this.cwcl(t,user);

        return dao.saveForSy(t,cwList);
    }
    //��շ���
    public boolean qkFp(CwfpglForm t) throws Exception {
        return dao.qkFp(t);
    }

    private List<String> cwcl(CwfpglForm t,User user) throws Exception {
        String lddmkey = t.getLddmkey();
        String[] lcKey = t.getLcKey();
        String[] cwKey = t.getCwIds();

        //��Ϊ��λ���ǻ���򿪾ͼ��صģ����������ݿ���ť����û�д�λ��
        //�����ֳ�����������case:1.ֻ��¥��key��2.ֻ�д�λkey��3.ͬʱ����
        List<String> cwList;
        if(lcKey != null && cwKey == null){
            cwList = dao.getCw(lddmkey,lcKey,user);

        } else if(lcKey == null && cwKey != null){
            cwList = Arrays.asList(cwKey);

        } else {
            List<String> lcs = new ArrayList<String>();
            //�ҳ�ѡ��¥����û��ѡ�д�λ��¥��
            boolean mark;
            for(String lc : lcKey){
                mark = true;
                for(String cw : cwKey){//cw : lc@!!!qsh@!!!cwh
                    String[] s = cw.split("@!!!");
                    if(s[0].equals(lc)){
                        mark = false;
                        break;
                    }
                }
                if(mark){
                    lcs.add(lc);
                }
            }
            //����¥���ѯ��λ
            cwList = dao.getCw(lddmkey,lcs.toArray(new String[lcs.size()]),user);
            //��ѯ�Ĵ�λ��ҳ���ύ�����ݺϲ�
            cwList.addAll(Arrays.asList(cwKey));
        }

        return cwList;
    }

    private Map<String,List<HashMap<String,String>>> groupBy(List<HashMap<String,String>> cwList){
        //�����ҷ���
        Map<String,List<HashMap<String,String>>> qsMap = new LinkedHashMap<String, List<HashMap<String, String>>>();
        List<HashMap<String,String>> listCw;
        for(HashMap<String,String> cw : cwList){
            String qskey = cw.get("ch") + cw.get("qsh");
            if(qsMap.containsKey(qskey)){
                qsMap.get(qskey).add(cw);
            } else {
                listCw = new ArrayList<HashMap<String, String>>();
                listCw.add(cw);
                qsMap.put(qskey,listCw);
            }
        }
        return qsMap;
    }
}
