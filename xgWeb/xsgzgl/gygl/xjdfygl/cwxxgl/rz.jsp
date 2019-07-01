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
            if(jQuery("#rzsj").val() == "" || jQuery("#xh").val() == ""){
                showAlert("�뽫��������д������");
                return false;
            }
            var url = "gygl_fygl_cwxxgl10698.do?method=rz&type=save";
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
        jQuery(function(){

        })

    </script>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_fygl_cwxxgl10698">
    <input type="hidden" name="pk" value="${pk}">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>��λ��Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="25%">
                    ¥������
                </th>
                <td width="35%">
                    ${model.ldmc}
                </td>
                <th>
                    ���Һ�
                </th>
                <td>
                    ${model.qsh}
                </td>
            </tr>
            <tr>
                <th>
                    ��λ��
                </th>
                <td>
                    ${model.cwh}
                </td>
                <th>
                    �����Ա�
                </th>
                <td>
                    ${model.qsxbmc}
                </td>
            </tr>
            <tr>
                <th>
                    ����ѧԺ
                </th>
                <td>
                    ${model.xymc}
                </td>
                <th>
                    �����꼶
                </th>
                <td>
                    ${model.nj}
                </td>
            </tr>
            </tbody>
            <thead>
                <tr>
                    <th colspan="4">
                        <span>ѧ����Ϣ</span>
                    </th>
                </tr>
            </thead>
            <%@include file="/xsgzgl/comm/bdpz/selectStudent.jsp"%>
            <thead>
                <tr>
                    <th colspan="4">
                        <span>��ס��Ϣ</span>
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th><span class="red">*</span>��סʱ��</th>
                    <td>
                        <html:text property="rzsj" styleId="rzsj" readonly="true"
                                   onclick="return showCalendar('rzsj','yyyy-MM-dd');"></html:text>
                    </td>
                    <th>��סԭ��</th>
                    <td>
                        <html:select property="rzyy">
                            <html:options collection="rzyyList" property="rzyydm" labelProperty="rzyymc"></html:options>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <th>��ע<br><span class="red">(��500��)</span></th>
                    <td colspan="3">
                        <html:textarea property="bz" styleId="bz" rows="4" style="width:98%"></html:textarea>
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