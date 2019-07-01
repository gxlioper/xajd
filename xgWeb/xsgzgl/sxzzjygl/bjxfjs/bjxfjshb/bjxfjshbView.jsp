<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script language="javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/bjxfjs/bjxfjshb/js/bjxfjshbEdit.js"></script>

    <script type="text/javascript">
        jQuery(function(){
            var dybl = 0;
            var dyrs = "${bjmap.dyrs}";
            var bjzrs = "${bjmap.bjzrs}";
            if(dyrs > 0 && bjzrs>0)
                dybl = (dyrs*100/bjzrs).toFixed(2)+"%";
            jQuery("#dyblTD").html(dybl);
            var sfNzhb="${sfNzhb}";
            var sfNzzj="${sfNzzj}";
            if("1" == sfNzhb){
                jQuery("#shlccx_nzhb").load("comm_spl.do?method=lccx&sqid="+jQuery("#nzhbsqid").val()+"&tt="+ new Date().getTime());
            }
            if("1" == sfNzzj){
                jQuery("#shlccx_nzzj").load("comm_spl.do?method=lccx&sqid="+jQuery("#nzzjsqid").val()+"&tt="+ new Date().getTime());
            }

        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/sxzzjy_bjxfjshb" method="post"  styleId="form">
    <input type="hidden" id="nzhbsqid" name="nzhbsqid" value="${nzhbMap.sqid}"/>
    <input type="hidden" id="nzzjsqid" name="nzzjsqid" value="${nzzjMap.sqid}"/>
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr>
                <th colspan="4" style="text-align: left">
                    �༶��Ϣ
                </th>
            </tr>
            <tr style="">
                <th width="14%">
                    �༶
                </th>
                <td colspan="3">
                        ${bjmap.bjmc}
                </td>
            </tr>
            <tr style="">
                <th width="14%">
                    ѧԺ
                </th>
                <td width="36%" id="xyTd">
                        ${bjmap.xymc}
                </td>
                <th width="14%">
                    �༶����
                </th>
                <td width="36%" id="bjrsTd">
                    ��${bjmap.bjzrs}�ˣ���${bjmap.nansrs}�ˣ�Ů${bjmap.nvsrs}�ˣ�
                </td>
            </tr>
            <tr>
                <th >
                    ��Ա��
                </th>
                <td  id="dysTD">
                        ${bjmap.dyrs}
                </td>
                <th >
                    ��Ա����
                </th>
                <td  id="dyblTD">
                </td>
            </tr>
            <tr>
                <th >
                    ����Ա
                </th>
                <td  id="fdyTD">
                        ${bjmap.fdyxm}
                </td>
                <th >
                    ������
                </th>
                <td  id="bzrTD">
                        ${bjmap.bzrxm}
                </td>
            </tr>
            <tr>
                <th >
                    �೤
                </th>
                <td  id="bzTD">
                        ${bjmap.bzxm}
                </td>
                <th >
                    ��֧��
                </th>
                <td  id="tzsTD">
                        ${bjmap.tzsxm}
                </td>
            </tr>
            </tbody>
        </table>
    <logic:equal value="1" name="sfNzhb">
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th colspan="4"  style="text-align: left">
                    ���ڻ㱨
                </th>
            </tr>
            <tr>
                <th width="21%">�༶ƽ��ѧ�ֻ�</th>
                <td width="5%">${nzhbMap.pjxfj}</td>
                <td colspan="2">�༶ѧ�ֻ����꼶��רҵ��
                        ${nzhbMap.njzy}��
                        ${nzhbMap.zyxbgs}��С����������
                        ${nzhbMap.pjxfjpm}��
                </td>
            </tr>
            <tr>
                <th width="21%">�༶Ӣ���ļ�ͨ����</th>
                <td width="5%">${nzhbMap.sjtgl}</td>
                <td colspan="2" >�༶Ӣ���ļ�ͨ�������꼶
                        ${nzhbMap.njxbgs}��С����������
                        ${nzhbMap.sjtglpm}��
                </td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist bxnmb" >
            <tbody>
            <tr>
                <th width="27%">�������Ŵ�</th>
                <td width="23%">${nzhbMap.bjgmc}��</td>
                <th width="27%">����������</th>
                <td width="23%">${nzhbMap.bjgrs}��</td>
            </tr>
            <tr>
                <th>�������˴�</th>
                <td>${nzhbMap.bjgrc}�˴�</td>
                <th>��ɲ�ѧϰ�ɼ�ǰ����</th>
                <td>${nzhbMap.bgbqwrs}��</td>

            </tr>
            <tr>
                <th>��ɲ�ѧϰ�ɼ�ǰʮ��</th>
                <td>${nzhbMap.bgbqsrs}��</td>
                <th>��ѧ��</th>
                <td>${nzhbMap.hjxsrs}��</td>
            </tr>
            <tr>
                <th>���影</th>
                <td>${nzhbMap.hjtjgs}��</td>
                <th>���ʵ����</th>
                <td>${nzhbMap.shsjhjrc}�˴�</td>
            </tr>
            <tr>
                <th>�����</th>
                <td>${nzhbMap.sshjcs}��</td>
                <th>��֯ȫ�༯��</th>
                <td>${nzhbMap.qbjthdcs}��</td>
            </tr>
            <tr>
                <th>�Ƽ�ѧ����</th>
                <td>${nzhbMap.kjxshjrc}�˴�</td>
                <th>��֯�༶ͬѧ�μ�УԺ�</th>
                <td>${nzhbMap.cjxyhdcs}��</td>
            </tr>
            <tr>
                <th>����ͬѧ</th>
                <td>${nzhbMap.jjtxrs}��</td>
                <th>�Զ�ѧ��</th>
                <td>${nzhbMap.sdxsrs}��</td>
            </tr>
            <tr>
                <th>��ѧ</th>
                <td colspan="3">${nzhbMap.txrs}��</td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th width="14%">
                    �����ܽ�
                </th>
                <td   colspan="3">
                        ${nzhbMap.zd1}
                </td>
            </tr>
            <tr style="">
                <th>
                    �༶��������������
                </th>
                <td   colspan="3">
                        ${nzhbMap.zd2}
                </td>
            </tr>
            <tr style="">
                <th>
                    �༶�������ķ���
                </th>
                <td   colspan="3">
                        ${nzhbMap.zd3}
                </td>
            </tr>
            </tbody>
        </table>
        <logic:notEqual value="�������" name="nzhbMap" property="shztmc" >
            <table width="100%" border="0" class="formlist">
                <thead>
                <tr>
                    <th colspan="4">
                        <span>�����Ϣ</span>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="4" id="shlccx_nzhb">
                    </td>
                </tr>
                </tbody>
            </table>
        </logic:notEqual>
    </logic:equal>
    <logic:equal value="1" name="sfNzzj">
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th colspan="4"  style="text-align: left">
                     ����ܽ�
                </th>
            </tr>
            <tr>
                <th width="21%">�༶ƽ��ѧ�ֻ�</th>
                <td width="5%">${nzzjMap.pjxfj}</td>
                <td colspan="2">�༶ѧ�ֻ����꼶��רҵ��
                        ${nzzjMap.njzy}��
                        ${nzzjMap.zyxbgs}��С����������
                        ${nzzjMap.pjxfjpm}��
                </td>
            </tr>
            <tr>
                <th width="21%">�༶Ӣ���ļ�ͨ����</th>
                <td width="5%">${nzzjMap.sjtgl}</td>
                <td colspan="2" >�༶Ӣ���ļ�ͨ�������꼶
                        ${nzzjMap.njxbgs}��С����������
                        ${nzzjMap.sjtglpm}��
                </td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist bxnmb" >
            <tbody>
            <tr>
                <th width="27%">�������Ŵ�</th>
                <td width="23%">${nzzjMap.bjgmc}��</td>
                <th width="27%">����������</th>
                <td width="23%">${nzzjMap.bjgrs}��</td>
            </tr>
            <tr>
                <th>�������˴�</th>
                <td>${nzzjMap.bjgrc}�˴�</td>
                <th>��ɲ�ѧϰ�ɼ�ǰ����</th>
                <td>${nzzjMap.bgbqwrs}��</td>

            </tr>
            <tr>
                <th>��ɲ�ѧϰ�ɼ�ǰʮ��</th>
                <td>${nzzjMap.bgbqsrs}��</td>
                <th>��ѧ��</th>
                <td>${nzzjMap.hjxsrs}��</td>
            </tr>
            <tr>
                <th>���影</th>
                <td>${nzzjMap.hjtjgs}��</td>
                <th>���ʵ����</th>
                <td>${nzzjMap.shsjhjrc}�˴�</td>
            </tr>
            <tr>
                <th>�����</th>
                <td>${nzzjMap.sshjcs}��</td>
                <th>��֯ȫ�༯��</th>
                <td>${nzzjMap.qbjthdcs}��</td>
            </tr>
            <tr>
                <th>�Ƽ�ѧ����</th>
                <td>${nzzjMap.kjxshjrc}�˴�</td>
                <th>��֯�༶ͬѧ�μ�УԺ�</th>
                <td>${nzzjMap.cjxyhdcs}��</td>
            </tr>
            <tr>
                <th>����ͬѧ</th>
                <td>${nzzjMap.jjtxrs}��</td>
                <th>�Զ�ѧ��</th>
                <td>${nzzjMap.sdxsrs}��</td>
            </tr>
            <tr>
                <th>��ѧ</th>
                <td colspan="3">${nzzjMap.txrs}��</td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th width="14%">
                    ��Ȱ༶�����ܽ�
                </th>
                <td   colspan="3">
                        ${nzzjMap.zd1}
                </td>
            </tr>
            <tr style="">
                <th>
                    �༶������̵����¼�
                </th>
                <td   colspan="3">
                        ${nzzjMap.zd2}
                </td>
            </tr>
            <tr style="">
                <th>
                    �༶����͸��˻���ϸ
                </th>
                <td   colspan="3">
                        ${nzzjMap.zd3}
                </td>
            </tr>
            </tbody>
        </table>
        <logic:notEqual value="�������" name="nzhbMap" property="shztmc" >
            <table width="100%" border="0" class="formlist">
                <thead>
                <tr>
                    <th colspan="4">
                        <span>�����Ϣ</span>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="4" id="shlccx_nzzj">
                    </td>
                </tr>
                </tbody>
            </table>
        </logic:notEqual>
    </logic:equal>
    </div>
</html:form>
<table width="100%" border="0" class="formlist">
    <tfoot>
    <tr>
        <td colspan="4">
            <div class="btn">
                <button type="button" type="button" onclick="iFClose();">
                    �� ��
                </button>
            </div>
        </td>
    </tr>
    </tfoot>
</table>
</body>
</html>

