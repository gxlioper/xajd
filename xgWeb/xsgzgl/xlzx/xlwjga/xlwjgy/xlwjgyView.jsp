<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src='/xgxt/js/check.js'></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="xsgzgl/xlzx/xlwjga/xlwjgy/js/xlwjgy.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <script type="text/javascript">

    </script>
</head>

<body>
<html:form method="post" styleId="form" action="/xlzx_xlwjgy">
    <div style='width: 100%; height: 450px; overflow-x: hidden; overflow-y: auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ��������Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>Σ������</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th align="right">
                    ����ʱ��
                </th>
                <td >
                        ${model.bgsj} 
                </td>
                <th align="right">
                    ������
                </th>
                <td>
                        ${model.bgr}
                </td>
            </tr>
            <tr>
                <th align="right">
                    ����;��
                </th>
                <td >
                        ${model.fxtjmc}
                </td>
                <th align="right">
                    Σ���̶�
                </th>
                <td >
                        ${model.wjcdmc}
                </td>
            </tr>
            <tr>
                <th align="right">
                    Σ����չ���̼�����
                </th>
                <td colspan="3">
                        ${model.wjfzgc}
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>Σ����Ԥ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th align="right">
                    Σ����Ԥʱ��
                </th>
                <td>
                        ${model.wjgysj}
                </td>
                <th align="right">
                    ��Ԥ��Ա
                </th>
                <td>
                        ${model.wjgyry}
                </td>
            </tr>
            <tr>
                <th align="right">
                    ��Ԥ��ʽ
                </th>
                <td >
                        ${model.wjgyfsmc}
                </td>
                <th align="right">
                    Эͬ����
                </th>
                <td >
                        ${model.xtbmmc}
                </td>
            </tr>
            <tr>
                <th align="right">
                    ���
                </th>
                <td align="left" colspan="3">
                        ${model.wjgyjgmc}
                </td>
            </tr>
            <tr>
                <th align="right">
                    Σ��������̼���ʩ
                </th>
                <td align="left" colspan="3">
                        ${model.wjclgc}
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td>
                    <div class="btn">
                        <button type="button" onclick="iFClose();return false;">
                            �� ��
                        </button>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</html:form>
</body>
</html>