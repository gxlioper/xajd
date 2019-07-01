<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script language="javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/sgygc/dyssgl/js/dyssglEdit.js"></script>
    <script type="text/javascript">

    </script>
    <style type="text/css">
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/zhdj_dyssgl" method="post"  styleId="form">
    <div style='width:100%; height:350px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr>
                <th colspan="4" style="text-align: left">��Ա��Ϣ</th>

            </tr>
            <tr style="">
                <th width="16%">
                    <span style="color: red">*</span>ѧ��
                </th>
                <td  colspan="3">
                    <logic:equal value="stu" name="userType">
                        ${map.dyxh}
                        <html:hidden property="dyxh" styleId="dyxh" value="${map.dyxh}"/>
                    </logic:equal>
                    <logic:notEqual value="stu" name="userType">
                        <html:text property="dyxh" styleId="dyxh" readonly="true" style="width:150px;" />
                        <button id="btn_xh" onclick="selectDy();return false;">
                            ѡ ��
                        </button>
                    </logic:notEqual>

                </td>
            </tr>
            <tr>
                <th width="16%">
                    ����
                </th>
                <td  id="dyxm" width="34%">
                    <logic:equal value="stu" name="userType">
                        ${map.dyxm}
                    </logic:equal>
                </td>
                <th width="16%">
                    �Ա�
                </th>
                <td id="xb"  width="34%">
                    <logic:equal value="stu" name="userType">
                        ${map.xb}
                    </logic:equal>
                </td>
            </tr>
            <tr>
                <th >
                    ��ϵ�̻�
                </th>
                <td  id="lxgh">
                    <logic:equal value="stu" name="userType">
                        ${map.lxdh}
                    </logic:equal>
                </td>
                <th >
                    �ֻ�����
                </th>
                <td id="sjhm" >
                    <logic:equal value="stu" name="userType">
                        ${map.sjhm}
                    </logic:equal>
                </td>
            </tr>
            <tr>
                <th >
                    ֧��
                </th>
                <td  id="dzbmc" >
                    <logic:equal value="stu" name="userType">
                        ${map.dzbmc}
                    </logic:equal>
                </td>
                <th >
                    ֧�����
                </th>
                <td id="dzbsjxm">
                    <logic:equal value="stu" name="userType">
                        ${map.dzbsjxm}
                    </logic:equal>
                </td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist" style="border-top: none;">
            <tr>
                <th colspan="4" style="text-align: left">��ϵ����</th>

            </tr>
            <tr style="" id="qsTr">
                <th width="16%">
                    <span style="color: red">*</span> ����
                </th>
                <td colspan="3">
                    <input type="text" name="qsxx" id="qsxx" readonly="readonly"/>
                    <button id="btn_bj" onclick="selectQs();return false;">
                        ѡ ��
                    </button>
                    <html:hidden property="lddm" styleId="lddm" />
                    <html:hidden property="qsh" styleId="qsh" />
                </td>
            </tr>
            <tbody id="qscyTbody">
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist"  style="border-top: none !important;">
            <tbody>
            <tr>
                <th colspan="4" style="text-align: left">
                    �����������
                </th>
            </tr>
            <tr>
                <th width="16%">
                    <span style="color: red">*</span>�������
                </th>
                <td  colspan="3">
                    <html:radio property="wsqk" value="01">��</html:radio>
                    <html:radio property="wsqk" value="02">�Ϻ�</html:radio>
                    <html:radio property="wsqk" value="03">һ��</html:radio>
                    <html:radio property="wsqk" value="04">��</html:radio>
                </td>
            </tr>
            <tr>
                <th >
                    <span style="color: red">*</span>�õ簲ȫ
                </th>
                <td  colspan="3">
                    <html:radio property="ydaq" value="01">��</html:radio>
                    <html:radio property="ydaq" value="02">�Ϻ�</html:radio>
                    <html:radio property="ydaq" value="03">һ��</html:radio>
                    <html:radio property="ydaq" value="04">��</html:radio>
                </td>
            </tr>
            <tr>
                <th >
                    <span style="color: red">*</span>����ѧ��
                </th>
                <td  colspan="3">
                    <html:radio property="ssxf" value="01">��</html:radio>
                    <html:radio property="ssxf" value="02">�Ϻ�</html:radio>
                    <html:radio property="ssxf" value="03">һ��</html:radio>
                    <html:radio property="ssxf" value="04">��</html:radio>
                </td>
            </tr>
            <tr>
                <th >
                    <span style="color: red">*</span>���޶Ĳ��������
                </th>
                <td  colspan="3">
                    <html:radio property="ywdbxjxx" value="01">��</html:radio>
                    <html:radio property="ywdbxjxx" value="02">û��</html:radio>
                </td>
            </tr>
            <tr>
                <th >
                    <span style="color: red">*</span>������������
                </th>
                <td  colspan="3">
                    <html:radio property="ywxyxx" value="01">��</html:radio>
                    <html:radio property="ywxyxx" value="02">û��</html:radio>
                </td>
            </tr>
            <tr>
                <th >
                    <span style="color: red">*</span>������������������Ĳ��
                    <br>
                    <span style="color: red">(��500��)</span>
                </th>
                <td  colspan="3">
                    <html:textarea property="ywmsscj" style="width:98%;margin-top:5px" rows="3"
                                   onblur="checkLen(this,500);" styleId="ywmsscj"
                    />
                </td>
            </tr>
            <tr>
                <th >
                    <span style="color: red">*</span>���ļƻ�����ʩ
                    <br>
                    <span style="color: red">(��500��)</span>
                </th>
                <td  colspan="3">
                    <html:textarea property="zgjhcs" style="width:98%;margin-top:5px" rows="3"
                                   onblur="checkLen(this,500);" styleId="zgjhcs"
                   />
                </td>
            </tr>
            </tbody>


        </table>
    </div>
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"Ϊ������
                </div>
                <div class="btn">
                    <button id="buttonSave" onclick="save('add');return false;">
                        �� ��
                    </button>
                    <button onclick="Close();return false;">
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

