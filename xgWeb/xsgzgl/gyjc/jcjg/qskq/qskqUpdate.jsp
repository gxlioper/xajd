<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/gyjc/jcjg/qskq/js/qskqList.js"></script>

</head>
<body>
<html:form action="/gyjc_qskq" method="post" styleId="qskqForm">
<html:hidden property="xh" styleId="xh" />
<html:hidden property="jcrq" styleId="jcrq" />
<table width="100%" border="0" class="formlist">
    <thead>
    <tr>
        <th colspan="4">
            <span>ѧ����Ϣ</span>
        </th>
    </tr>
    </thead>
    <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
    <thead>
    <tr>
        <th colspan="4">
            <span><logic:equal name="xxdm" value="12061">��ҹ��Ϣ</logic:equal>
                <logic:notEqual name="xxdm" value="12061">������Ϣ</logic:notEqual></span>
        </th>
    </tr>
    </thead>
    <tbody>
        <tr >
            <th>�����</th>
            <td>
                    ${map.jcrxm}
            </td>
            <th>���ʱ��</th>
            <td>
                    ${map.jcsj}
            </td>
        </tr>
        <tr>

            <th><logic:equal name="xxdm" value="12061">��ҹ����</logic:equal>
                <logic:notEqual name="xxdm" value="12061">��������</logic:notEqual></th>
            <td colspan="3">
                <html:select property="kqlbdm" styleId="kqlbdm" disabled="false" style="width:125px;">
                    <option value=''></option>
                    <html:options collection="kqlbList" property="dm"
                                  labelProperty="mc" />
                </html:select>
            </td>
        </tr>
        </tbody>
    </table>
</html:form>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="btn">
                        <button type="button" onclick="save();return false;">
                            �� ��
                        </button>
                        <button type="button" onclick="Close();return false;">
                            �� ��
                        </button>

                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</body>
</html>