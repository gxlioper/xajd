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
    <script type="text/javascript">
        function save(){
            var url = "gygl_fygl_qsxxgl10698.do?method=plxgqs&type=save";
            ajaxSubFormWithFun("demoForm", url, function(data) {
                if(data["message"]=="����ɹ���"){
                    showAlert(data["message"],{},{"clkFun":function(){
                        if (parent.window){
                            refershParent();
                        }
                    }});
                }else{
                    showAlert(data["message"]);
                }
            });
        }
    </script>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_fygl_qsxxgl10698">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <input type="hidden" value="${pks}" name="pks"/>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    ��λ��
                </th>
                <td>
                    <html:text property="cws" styleId="cws"></html:text>
                </td>
                <th>
                    �շѱ�׼
                </th>
                <td >
                    <html:text property="sfbz" styleId="sfbz"></html:text>
                </td>
            </tr>
            <tr>
                <th>
                    ��������
                </th>
                <td >
                    <html:select property="fjlx" styleId="fjlx" style="width:100px;">
                        <html:option value="01">����</html:option>
                        <html:option value="02">ֵ����</html:option>
                        <html:option value="03">����</html:option>
                    </html:select>
                </td>
                <th>
                    ��������
                </th>
                <td>
                    <html:radio property="fjzx" value="1">��</html:radio>
                    <html:radio property="fjzx" value="2">��</html:radio>
                    <html:radio property="fjzx" value="3">��</html:radio>
                    <html:radio property="fjzx" value="4">��</html:radio>
                </td>
            </tr>
            <tr>
                <th>
                    �Ƿ��пյ�
                </th>
                <td >
                    <html:radio property="sfykt" value="1">��</html:radio>
                    <html:radio property="sfykt" value="0">��</html:radio>
                </td>
                <th>
                    �Ƿ���������
                </th>
                <td>
                    <html:radio property="sfywsj" value="1">��</html:radio>
                    <html:radio property="sfywsj" value="0">��</html:radio>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height:30px;"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save();" id="buttonSave">
                            �� ��
                        </button>
                        <button type="button" onclick="iFClose();"  id="buttonClose">
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