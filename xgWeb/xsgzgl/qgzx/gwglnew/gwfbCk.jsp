<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type='text/javascript' src="js/comm/message.js"></script>
    <script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
    <script type='text/javascript' src='/xgxt/dwr/util.js'></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src="js/uicomm.js"></script>
    <script type='text/javascript' src="js/String.js"></script>
    <script type='text/javascript' src="js/xgutil.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="comm/editor/kindeditor-min.js"></script>
    <script type="text/javascript" src="comm/editor/zh_CN.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
</head>
<body >

<html:form styleId="qgzxGwglNewForm" action="/qgzx_gwglnew" method="post" onsubmit="return false;">
    <input type="hidden" name="gwdm" value="${model.gwdm}">
    <table align="center" class="formlist">
        <thead>
        <tr>
            <th colspan="4" style="width: 25%">
                <span>��λ������Ϣ</span>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>
                ѧ��ѧ��
            </th>
            <td>
                ${model.xn}${model.xqmc}
            </td>
            <th>
                ���˵�λ
            </th>
            <td>
                ${yrdw.yrdwmc}
            </td>
        </tr>
        </tbody>
        <tbody>
        <tr>
            <th>
                ��λ����
            </th>
            <td>
                ${model.gwmc}
            </td>
            <th>��������</th>
            <td>
                <logic:equal name="model" property="gwxzdm" value="0"> ��ʱ</logic:equal>
                <logic:equal name="model" property="gwxzdm" value="1">��ʽ</logic:equal>
            </td>
        </tr>
        <tr>
            <th>
                ��Ƹ����
            </th>
            <td>
                ${model.xqrs}��
            </td>
            <th>
                ��λ����
            </th>
            <td>
                <logic:equal name="model" property="gwlx" value="0">��ʱ</logic:equal>
                <logic:equal name="model" property="gwlx" value="1">����</logic:equal>
            </td>
        </tr>
        <tr>
            <th>
                ��λ���
            </th>
            <td >
                ${model.gwxzmc}
            </td>
            <th>
                ��λн������
            </th>
            <td>
                ${gsgz.cjsx}Ԫ
            </td>
        </tr>
        <tr>
            <th>
                ��ʱ����
            </th>
            <td colspan="3">
                ${gsgz.gzscsx}Сʱ
                <span id="label"></span>
            </td>
        </tr>
        <tr>
            <th>
                ��Ƹ��ʼʱ��
            </th>
            <td>
                ${model.zpkssj}
            </td>
            <th>
                ��Ƹ����ʱ��
            </th>
            <td>
                <logic:equal name="model" property="cq" value="1">����</logic:equal>
                <logic:notEqual name="model" property="cq" value="1">${model.zpjssj}</logic:notEqual>
            </td>
        </tr>
        <%--<tr>
            <th>
                �Ƿ��ö�
            </th>
            <td>
                <logic:equal name="model" property="sfzd" value="1">��</logic:equal>
                <logic:notEqual name="model" property="sfzd" value="1">��</logic:notEqual>
            </td>
            <th>
                �Ƿ���ʾ����
            </th>
            <td>
                <logic:equal name="model" property="sfxsgz" value="1">��</logic:equal>
                <logic:notEqual name="model" property="sfxsgz" value="1">��</logic:notEqual>
            </td>
        </tr>--%>
        <th>
            Э����
        </th>
        <td colspan="3">
            <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
            <script type="text/javascript">
                //���ø���
                jQuery(function(){
                    var gid = "${model.fjid}";
                    jQuery.MultiUploader_q({
                        gid : gid
                    });
                });
            </script>
        </td>
        <tr>
            <th>
                ��ƸҪ��
            </th>
            <td colspan="3">
                ${model.gwryyq}
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    <span style="font-size: larger; color: purple;">������ʾ��</br>1.У�ڵ�λ�ĸ�λ����Ĭ��Ϊ</span>
                    <span style="font-size: larger; color: red;">����</span>
                    <span style="font-size: larger; color: purple;">����</span>
                    <span style="font-size: larger; color: red;">����</span>
                    <span style="font-size: larger; color: purple;">����淶��д</br>2.��ְλ�ѹ�����û���������������ӳ���ֹ����</span>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div class="btn">
                    <button type="button" name="�ر�" onclick="Close();return false;">
                        �� ��
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</html:form>
</body>
</html>
