<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/xszz/lstd/js/lstd.js"></script>
    <script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
            //��������ѡ��
            loadViewJtcySelectOptions();
        })
    </script>
</head>
<body>
<html:form action="/xszz_lstd" method="post" styleId="lstdForm" onsubmit="return false">
    <html:hidden name="model" property="sqid" styleId="sqid"/>
    <html:hidden name="model" property="xh" styleId="xh"/>
    <html:hidden name="model" property="shlc" styleId="shlc"/>
    <div style='tab;width:100%;height:650px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ����Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
            <thead>
                <tr>
                    <th colspan="4">
                        <span>���ܹ������������������</span>
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th width="25%">�Ƿ�������Դ����ѧ����</th>
                    <td width="10%">
                        <logic:equal value="01" name="data" property="hjfs">
                            ��
                        </logic:equal>
                        <logic:notEqual value="01" name="data" property="hjfs">
                            ��
                        </logic:notEqual>
                    </td>
                    <th width="20%">������</th>
                    <td width="25%">${data.dkje}����λ��Ԫ��</td>
                </tr>
                <tr><%--todo ���ݴ�����--%>
                    <th>��������</th>
                    <td colspan="3"></td>
                </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>��ͥ���</span>
                </th>
            </tr>
            </thead>
            <tr>
                <th>��ͥ����</th>
                <td>${jtqkModel.jthk}</td>
                <th>��ͥ�˿�����</th>
                <td>${jtqkModel.jtrs}</td>
            </tr>
            <tr>
                <th>��ͥ�˾�������</th>
                <td>${jtqkModel.jtrjysr}����λ��Ԫ��</td>
                <th>��ͥ������Դ</th>
                <td>${jtqkModel.jtsrly}</td>
            </tr>
            <tr>
                <th>��ͥ��ϵ�绰</th>
                <td>${jtqkModel.jtdh}</td>
                <th>��ͥ��ַ</th>
                <td>${jtqkModel.jtdz}</td>
            </tr>
            <tr>
                <th>��������</th>
                <td colspan="3">${jtqkModel.jtyb}</td>
            </tr>
            <thead>
            <thead>
            <tr>
                <th colspan="4">
                    <span>��ͥ��Ա</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/xszz/bdpz/jtcyView.jsp" %>
            </thead>
            <tr>
            </tr>
            <thead>
            <tr>
                <th colspan="4">
                    <span>���뻺������</span>
                </th>
            </tr>
            </thead>
            <tr>
                <th>�������뻺������</th>
                <td colspan="3">
                    ${data.sqly}
                </td>
            </tr>
            <tr>
                <th>��ϸ˵��������ȡ���ַ�ʽ�����Ƿ������</th>
                <td colspan="3">
                    <input type="checkbox" disabled <logic:equal value="01" name="data" property="hjfs">checked="checked"</logic:equal>/>��Դ�ع�����ѧ����
                    <input type="checkbox" disabled <logic:equal value="02" name="data" property="hjfs">checked="checked"</logic:equal>/>У԰�ع�����ѧ����(������:8000Ԫ)
                    <input type="checkbox" disabled <logic:equal value="03" name="data" property="hjfs">checked="checked"</logic:equal>/>�ڹ���ѧ
                    <input type="checkbox" disabled <logic:equal value="04" name="data" property="hjfs">checked="checked"</logic:equal>/>������ѧ��
                    <input type="checkbox" disabled <logic:equal value="05" name="data" property="hjfs">checked="checked"</logic:equal>/>��ͥ�������
                    <input type="checkbox" disabled <logic:equal value="06" name="data" property="hjfs">checked="checked"</logic:equal>/>����__________________
                </td>
            </tr>
            <thead>
            <tr>
                <th colspan="4">
                    <span>���뻺����ʱ��</span>
                </th>
            </tr>
            </thead>
            <tr>
                <th>��ͨ���ý��</th>
                <td>${data.jtfyje}����λ��Ԫ��</td>
                <th>�������</th>
                <td>${data.sqhjje}����λ��Ԫ��</td>
            </tr>
            <tr>
                <th>������ֹʱ��</th>
                <td colspan="3">${jssj}</td>
            </tr>
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
            <tr>
                <th><span class="red">*</span>���</th>
                <td colspan="3">
                    <select name="shzt" id="shzt">
                        <option value="1">ͨ��</option>
                        <option value="2">��ͨ��</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>������<br/><font color="red">(��200��)</font></th>
                <td colspan="3">
                    <html:textarea property="shyj" style="width:98%;margin-top:5px" rows="6"
                                   onblur="checkLen(this,200);"  styleId="shyj"
                    ></html:textarea>
                </td>
            </tr>
        </table>
    </div>
    <div style="height:35px;" ></div>
    <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"Ϊ������
                </div>
                <div class="btn">
                    <button type="button" id="btn_submit" type="button"
                            onclick="lstdshSave();">
                        ����
                    </button>
                    <button type="button" name="�� ��" onclick="iFClose();">
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

