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
    <script type="text/javascript" src="xsgzgl/sxzzjygl/bjxfjs/bjxfjsjg/js/bjxfjsjgEdit.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var dybl = 0;
            var dyrs = "${bjmap.dyrs}";
            var bjzrs = "${bjmap.bjzrs}";
            if(dyrs > 0 && bjzrs>0)
                dybl = (dyrs*100/bjzrs).toFixed(2)+"%";
            jQuery("#dyblTD").html(dybl);

        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/sxzzjy_bjxfjsjg" method="post"  styleId="form">
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <html:hidden property="jgid" styleId="jgid" />
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
            <tr style="">
                <th colspan="4"  style="text-align: left">
                    �༶ѧ�罨��
                </th>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span>�� ��
                </th>
                <td colspan="3">
                        ${map.xfjsmc}
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span>�걨����
                </th>
                <td >
                        ${map.sblxmc}
                </td>
                <th >
                    ѧ��ѧ��
                </th>
                <td>
                        ${map.xn}${map.xqmc}

                </td>
            </tr>
            <tr style="">
                <th>
                    ��ѧ��Ŀ��
                </th>
                <td   colspan="3">
                    <table id="bxnmbTable">
                        <tr>
                            <th>�༶ƽ��ѧ�ֻ�</th>
                            <td width="20%">${map.pjxfj}</td>
                            <td colspan="2">�༶ѧ�ֻ����꼶��רҵ��
                                    ${map.njzy}��
                                    ${map.zyxbgs}��С����������
                                    ${map.pjxfjpm}��
                            </td>
                        </tr>
                        <tr>
                            <th>�༶Ӣ���ļ�ͨ����</th>
                            <td>${map.sjtgl}</td>
                            <td colspan="2">�༶Ӣ���ļ�ͨ�������꼶
                                    ${map.njxbgs}��С����������
                                    ${map.sjtglpm}��
                            </td>
                        </tr>
                        <tr>
                            <th>�������Ŵ�</th>
                            <td>${map.bjgmc}��</td>
                            <th>����������</th>
                            <td>${map.bjgrs}��</td>
                        </tr>
                        <tr>
                            <th>�������˴�</th>
                            <td>${map.bjgrc}�˴�</td>
                            <th>��ɲ�ѧϰ�ɼ�ǰ����</th>
                            <td>${map.bgbqwrs}��</td>

                        </tr>
                        <tr>
                            <th>��ɲ�ѧϰ�ɼ�ǰʮ��</th>
                            <td>${map.bgbqsrs}��</td>
                            <th>��ѧ��</th>
                            <td>${map.hjxsrs}��</td>
                        </tr>
                        <tr>
                            <th>���影</th>
                            <td>${map.hjtjgs}��</td>
                            <th>���ʵ����</th>
                            <td>${map.shsjhjrc}�˴�</td>
                        </tr>
                        <tr>
                            <th>�����</th>
                            <td>${map.sshjcs}��</td>
                            <th>��֯ȫ�༯��</th>
                            <td>${map.qbjthdcs}��</td>
                        </tr>
                        <tr>
                            <th>�Ƽ�ѧ����</th>
                            <td>${map.kjxshjrc}�˴�</td>
                            <th>��֯�༶ͬѧ�μ�УԺ�</th>
                            <td>${map.cjxyhdcs}��</td>
                        </tr>
                        <tr>
                            <th>����ͬѧ</th>
                            <td>${map.jjtxrs}��</td>
                            <th>�Զ�ѧ��</th>
                            <td>${map.sdxsrs}��</td>
                        </tr>
                        <tr>
                            <th>��ѧ</th>
                            <td colspan="3">${map.txrs}��</td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr style="">
                <th>
                    ����˼·�ͼƻ�

                </th>
                <td   colspan="3">
                        ${map.jssl}
                </td>
            </tr>
            <tr>
                <th>
                    ����
                </th>
                <td colspan="3">
                    <html:hidden property="fjid" styleId="fjid" />
                    <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                    <script type="text/javascript">
                        //���ø���
                        jQuery(function(){
                            var gid = jQuery('#fjid').val();
                            jQuery.MultiUploader_q({
                                gid : gid
                            });
                        });
                    </script>
                </td>
            </tr>
            </tbody>
        </table>
        <logic:notEmpty name="nzhbMap">
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
        </logic:notEmpty>
        <logic:notEmpty name="nzzjMap">
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
        </logic:notEmpty>
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

