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
    <script type="text/javascript">
        jQuery(document).ready(function(){
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.gwdm}&tt="+new Date().getTime());
            jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
        });
        function shSave(){

            if(jQuery("#shyj").val() == ""){
                showAlertDivLayer("����д��������");
                return false;
            }
            var message;
            if(jQuery("#shjg").val() == "1"){
                message = "ͨ��";
            }
            if(jQuery("#shjg").val() == "2"){
                message = "��ͨ��";
            }
            if(jQuery("#shjg").val() == "3"){
                message = "�˻�";
            }
            showConfirmDivLayer("��ȷ��" + message + "��������",{"okFun":function(){
                var url = "qgzx_gwglnew.do?method=gwfbsh&type=save";
                ajaxSubFormWithFun("qgzxGwglNewForm",url,function(data){
                    showAlertDivLayer(data["message"],{},{"clkFun":function(){
                        if (parent.window){
                            refershParent();
                        }
                    }});
                });
            }});
        }
    </script>
</head>
<body >

<html:form styleId="qgzxGwglNewForm" action="/qgzx_gwglnew" method="post" onsubmit="return false;">
    <input type="hidden" name="gwdm" value="${model.gwdm}">
    <input type="hidden" name="yrdwid" value="${yrdwid}"/>
    <input type="hidden" name="splc" value="${model.splc}"/>
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
            <th width="7%">
                ѧ��ѧ��
            </th>
            <td>
                    ${model.xn}${model.xqmc}
            </td>
            <th width="7%">
                ���˵�λ
            </th>
            <td>
                ${yrdwmc}
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
                <select name="gwlb" id="gwlb" disabled>
                    <logic:iterate id="i" name="gwlblist">
                        <option value="${i.gwxzdm}" <logic:equal name="model" property="gwlb" value="${i.gwxzdm}">selected="selected"</logic:equal>>${i.gwxzmc}</option>
                    </logic:iterate>
                </select>
            </td>
            <th>
                ��������/��
            </th>
            <td>
                    ${gsgz.cjsx}Ԫ
            </td>
        </tr>
        <tr>
            <th>
                ��ʱ����/��
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
        <tr>
            <th>
                �Ƿ��ö�
            </th>
            <td>
                <input type="radio" name="sfzd" value="0" checked="checked">��
                <input type="radio" name="sfzd" value="1">��
            </td>
            <th>
                �Ƿ���ʾ����
            </th>
            <td>
                <input type="radio" name="sfxsgz" value="0" checked="checked">��
                <input type="radio" name="sfxsgz" value="1">��
            </td>
        </tr>
        <tr>
            <th>
                ��ƸҪ��
            </th>
            <td colspan="3">
                    ${model.gwryyq}
            </td>
        </tr>
        </tbody>
        <thead>
        <tr>
            <th colspan="4">
                <span>�����Ϣ</span>
            </th>
        </tr>
        </thead>
        <tr>
            <td colspan="4" id="shlccx">

            </td>
        </tr>
        <tbody>
        <tr>
            <th >
                ��˽��
            </th>
            <td id="shjgSpan" colspan="3">

            </td>
        </tr>

        <tr>
            <th >
                <font color="red">*&nbsp;</font> ������
                <br />
                <font color="red">(��200��)</font>
            </th>
            <td colspan="3">
                <jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cdgl&id=shyj" />
                <textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="btn">
                    <button type="button" id="btn_submit" type="button"
                            onclick="shSave();">
                        ����
                    </button>
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
