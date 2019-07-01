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
        function save(){
            var checkIds = "mc-lxdm-cd-kd-gd-cjmc-gxny";
            if(!checkNotNull(checkIds)){
                showAlert("�뽫��������д������");
                return false;
            }
            var url = "gygl_zcgl_zcxxgl.do?method=add&type=save";
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
<body style="width: 100%">
<html:form action="/gygl_zcgl_zcxxgl" method="post" styleId="demoForm" onsubmit="return false;">
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
                    <span class="red">*</span>����
                </th>
                <td>
                    <html:text property="mc" styleId="mc"></html:text>
                </td>
                </td>
                <th><span class="red">*</span>����</th>
                <td>
                    <html:select property="lxdm" styleId="lxdm">
                        <html:options labelProperty="mc" property="dm" collection="lxList"></html:options>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>����
                </th>
                <td>
                    <html:text property="cjmc" styleId="cjmc" ></html:text>
                </td>
                </td>
                <th><span class="red">*</span>�ʲ�����(��)</th>
                <td>
                    ����<html:text property="cd" styleId="cd" style="width:50px;"></html:text>
                    ��<html:text property="kd" styleId="kd" style="width:50px;"></html:text>
                    �ߣ�<html:text property="gd" styleId="gd" style="width:50px;"></html:text>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>��������
                </th>
                <td>
                    <html:text property="gxny" styleId="gxny" readonly="true" onfocus="showCalendar('gxny','y-mm-dd');"></html:text>
                </td>
                <th>
                    �۸�(Ԫ)
                </th>
                <td>
                    <html:text property="jg" styleId="jg" onblur="checkInputNum(this)"></html:text>
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
                    <div class="bz">
                        "<span class="red">*</span>"Ϊ������
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save();">
                            ����
                        </button>
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

