<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
    <script type='text/javascript'>
    </script>
</head>
<body style="width: 100%">
<html:form action="/gygl_zcgl_zcxxgl" method="post" styleId="demoForm" onsubmit="return false;">
    <html:hidden property="id" styleId="id"></html:hidden>
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>�ʲ���Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    ����
                </th>
                <td>
                    ${rs.mc}
                </td>
                </td>
                <th>����</th>
                <td>
                        ${rs.lxmc}
                </td>
            </tr>
            <tr>
                <th>
                    ����
                </th>
                <td>
                        ${rs.cjmc}
                </td>
                </td>
                <th>�ʲ�����(��)</th>
                <td>
                    ����${rs.cd}
                    ��${rs.kd}
                    �ߣ�${rs.gd}
                </td>
            </tr>
            <tr>
                <th>
                    ��������
                </th>
                <td>
                    ${rs.gxny}
                </td>
                <th>
                    �۸�(Ԫ)
                </th>
                <td>
                        ${rs.jg}
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height:35px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="btn">
                        <button type="button" onclick="iFClose();">
                            �ر�
                        </button>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
    <%@ include file="/comm/other/tsxx.jsp"%>
</html:form>
</body>
</html>

