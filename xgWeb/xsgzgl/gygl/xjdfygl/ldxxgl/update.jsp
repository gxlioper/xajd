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
            var checkIds = "ldmc-ldjc-ldcs-qsch-xslx";
            if(!checkNotNull(checkIds)){
                showAlert("�뽫��������д������");
                return false;
            }
            var url = "gygl_fygl_ldxxgl10698.do?method=update&type=save";
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
        function qschChange(){
            var v = jQuery("#qsch").val();
            if(Number(v) > 0){
                jQuery("#sfhlcTh").hide();
                jQuery("#sfhlcTd").hide();
                jQuery("input[name='sfhlc']").val("0");
            } else {
                jQuery("input[name='sfhlc']").val("1");
                jQuery("#sfhlcTh").show();
                jQuery("#sfhlcTd").show();

            }
        }
        jQuery(function(){
            qschChange();
//            jQuery("#xqdm").attr("disabled","disabled");
//            jQuery("#lddm").attr("readonly","readonly");
        })
    </script>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_fygl_ldxxgl10698">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>¥����Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    <span class="red">*</span>У��
                </th>
                <td>
                    ${xqmc}
                </td>
                <th>
                    <span class="red">*</span>¥������
                </th>
                <td>
                    <html:hidden property="lddm"/>
                    ${lddm}
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>¥������
                </th>
                <td >
                    <html:text property="ldmc" styleId="ldmc" maxlength="100" />
                </td>
                <th>
                    <span class="red">*</span>¥�����
                </th>
                <td >
                    <html:text property="ldjc" styleId="ldjc" maxlength="100" />
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>¥������
                </th>
                <td >
                    <html:radio property="ldzx" value="1">��</html:radio>
                    <html:radio property="ldzx" value="2">��</html:radio>
                    <html:radio property="ldzx" value="3">��</html:radio>
                    <html:radio property="ldzx" value="4">��</html:radio>
                </td>
                <th>
                    <span class="red">*</span>¥���Ա�
                </th>
                <td >
                    <html:radio property="ldxb" value="1">��</html:radio>
                    <html:radio property="ldxb" value="2">Ů</html:radio>
                    <html:radio property="ldxb" value="3">��ס</html:radio>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>¥������
                </th>
                <td >
                    <html:select property="ldcs" styleId="ldcs" style="width:100px;">
                        <logic:iterate id="i" name="cshcs">
                            <html:option value="${i}">${i}��</html:option>
                        </logic:iterate>
                    </html:select>
                </td>
                <th>
                    <span class="red">*</span>¥����ʼ���
                </th>
                <td >
                    <html:select property="qsch" styleId="qsch" style="width:100px;" onchange="qschChange()">
                        <logic:iterate id="i" name="qschList">
                            <html:option value="${i}">${i}��</html:option>
                        </logic:iterate>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>ѧ������
                </th>
                <td >
                    <html:select property="xslx" styleId="xslx" style="width:100px;" >
                        <html:options collection="xslxList" property="pyccdm" labelProperty="pyccmc"></html:options>
                    </html:select>
                </td>
                <th>
                    <span id="sfhlcTh">�Ƿ�0��</span>
                </th>
                <td id="sfhlcTd">
                    <html:radio property="sfhlc" value="1">��</html:radio>
                    <html:radio property="sfhlc" value="0">��</html:radio>
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