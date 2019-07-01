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
            var checkIds = "xqdm-lddm-ch-qsh-cws-sfbz";
            if(!checkNotNull(checkIds)){
                showAlert("�뽫��������д������");
                return false;
            }
            var url = "gygl_fygl_qsxxgl10698.do?method=add&type=save";
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
        function xqChange(){
            var v = jQuery("#xqdm").val();
            jQuery.post("gygl_fygl_qsxxgl10698.do?method=getLddxxList",{xqdm:v},function(data){
                jQuery("#lddm").empty();
                var option;
                for(var i=0;i<data.length;i++){
                    option += "<option value='"+data[i]["lddm"]+"'>"+data[i]["ldmc"]+"</option>";
                }
                jQuery("#lddm").append(option);

                ldChange();
            },'json');
        }
        function ldChange(){
            var v = jQuery("#lddm").val();
            jQuery.post("gygl_fygl_qsxxgl10698.do?method=getLddcs",{lddm:v},function(data){
                jQuery("#ch").empty();
                var option;
                for(var i=0;i<data.length;i++){
                    option += "<option value='"+data[i]+"'>"+data[i]+"</option>";
                }
                jQuery("#ch").append(option);
            },'json');
        }
        jQuery(function(){
            xqChange();
        })
    </script>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_fygl_qsxxgl10698">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
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
                    <span class="red">*</span>У��
                </th>
                <td>
                    <html:select property="xqdm" styleId="xqdm" style="width:100px;" onchange="xqChange()">
                        <html:options collection="xqList" property="dm" labelProperty="xqmc" />
                    </html:select>
                </td>
                <th>
                    <span class="red">*</span>¥��
                </th>
                <td >
                    <html:select property="lddm" styleId="lddm" style="width:100px;" onchange="ldChange()">
                        <%--<html:options collection="ldList" property="lddm" labelProperty="ldmc" />--%>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>����¥��
                </th>
                <td >
                    <html:select property="ch" styleId="ch" style="width:100px;">

                    </html:select>
                </td>
                <th>
                    <span class="red">*</span>���Һ�
                </th>
                <td >
                    <html:text property="qsh" styleId="qsh" maxlength="4" />
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>��λ��
                </th>
                <td >
                    <html:text property="cws" styleId="cws" maxlength="2" />
                </td>
                <th>
                    <span class="red">*</span>�շѱ�׼
                </th>
                <td >
                    <html:text property="sfbz" styleId="sfbz" maxlength="4" />
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
                    <span class="red">*</span>�����Ա�
                </th>
                <td >
                    <html:radio property="qsxb" value="1">��</html:radio>
                    <html:radio property="qsxb" value="2">Ů</html:radio>
                    <html:radio property="qsxb" value="3">��ס</html:radio>
                </td>
                <th>
                    ���ҵ绰
                </th>
                <td>
                    <html:text property="qsdh" styleId="qsdh" maxlength="20" />
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>�Ƿ��пյ�
                </th>
                <td >
                    <html:radio property="sfykt" value="1">��</html:radio>
                    <html:radio property="sfykt" value="0">��</html:radio>
                </td>
                <th>
                    <span class="red">*</span>�Ƿ���������
                </th>
                <td>
                    <html:radio property="sfywsj" value="1">��</html:radio>
                    <html:radio property="sfywsj" value="0">��</html:radio>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>������λ��
                </th>
                <td>
                    <html:radio property="wsjwz" value="01">�����ڲ�</html:radio>
                    <html:radio property="wsjwz" value="02">��̨</html:radio>
                </td>
                <th>
                    ����������
                </th>
                <td>
                    <html:radio property="wsjzx" value="1">���Ͻ�</html:radio>
                    <html:radio property="wsjzx" value="2">���Ͻ�</html:radio>
                    <html:radio property="wsjzx" value="3">������</html:radio>
                    <html:radio property="wsjzx" value="4">������</html:radio>
                </td>
            </tr>
            <tr>
                <th>
                    ��ע<br />
                    <span class="red">(��1000��)</span>
                </th>
                <td colspan="3">
                    <html:textarea property="bz" style="width:94%;height:80px"  styleId="bz" onblur="chLengs(this,1000);" ></html:textarea>
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