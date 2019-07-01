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
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/bjxfjs/bjxfjshb/js/bjxfjshbEdit.js"></script>

    <script type="text/javascript">
        jQuery(function(){
            var dybl = 0;
            var dyrs = "${bjmap.dyrs}";
            var bjzrs = "${bjmap.bjzrs}";
            if(dyrs > 0 && bjzrs>0)
                dybl = (dyrs*100/bjzrs).toFixed(2)+"%";
            jQuery("#dyblTD").html(dybl);
        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
        .formlist input{
            width: 50px;
        }
        .
    </style>
</head>
<body>
<html:form action="/sxzzjy_bjxfjshb" method="post"  styleId="form">
    <html:hidden property="hblx" styleId="hblx"/>
    <html:hidden property="jgid" styleId="jgid" />
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">
                <tr>
                    <th colspan="4" style="text-align: left">
                        �༶��Ϣ
                    </th>

                </tr>

                <tr style="">
                    <th width="14%">
                        �༶
                    </th>
                    <td colspan="3">
                            ${bjmap.bjmc}
                    </td>
                </tr>
                <tr style="">
                    <th width="14%">
                        ѧԺ
                    </th>
                    <td width="36%" id="xyTd">
                            ${bjmap.xymc}
                    </td>
                    <th width="14%">
                        �༶����
                    </th>
                    <td width="36%" id="bjrsTd">
                        ��${bjmap.bjzrs}�ˣ���${bjmap.nansrs}�ˣ�Ů${bjmap.nvsrs}�ˣ�

                    </td>
                </tr>
                <tr>
                    <th >
                        ��Ա��
                    </th>
                    <td  id="dysTD">
                            ${bjmap.dyrs}
                    </td>
                    <th >
                        ��Ա����
                    </th>
                    <td  id="dyblTD">

                    </td>

                </tr>
                <tr>
                    <th >
                        ����Ա
                    </th>
                    <td  id="fdyTD">
                            ${bjmap.fdyxm}
                    </td>
                    <th >
                        ������
                    </th>
                    <td  id="bzrTD">
                            ${bjmap.bzrxm}
                    </td>

                </tr>
                <tr>
                    <th >
                        �೤
                    </th>
                    <td  id="bzTD">
                            ${bjmap.bzxm}
                    </td>
                    <th >
                        ��֧��
                    </th>
                    <td  id="tzsTD">
                            ${bjmap.tzsxm}
                    </td>

                </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th colspan="4"  style="text-align: left">
                    <logic:equal name="hblx" value="nzhb">���ڻ㱨</logic:equal>
                    <logic:equal name="hblx" value="nzzj">����ܽ�</logic:equal>
                </th>
            </tr>
                <tr>
                    <th width="24%"><span style="color: red">*</span>�༶ƽ��ѧ�ֻ�</th>
                    <td width="13%"><html:text property="pjxfj" styleId="pjxfj" onblur="checkLen(this,10);"/></td>
                    <td colspan="2"><span style="color: red">*</span>�༶ѧ�ֻ����꼶��רҵ��
                        <html:text property="njzy" styleId="njzy" onblur="checkLen(this,50);"/>��
                        <html:text property="zyxbgs" styleId="zyxbgs" onblur="checkLen(this,10);"/>��С����������
                        <html:text property="pjxfjpm" styleId="pjxfjpm" onblur="checkLen(this,10);"/>��
                    </td>
                </tr>
                <tr>
                    <th ><span style="color: red">*</span>�༶Ӣ���ļ�ͨ����</th>
                    <td ><html:text property="sjtgl" styleId="sjtgl" onblur="checkLen(this,10);"/></td>
                    <td colspan="2" ><span style="color: red">*</span>�༶Ӣ���ļ�ͨ�������꼶
                        <html:text property="njxbgs" styleId="njxbgs" onblur="checkLen(this,10);"/>��С����������
                        <html:text property="sjtglpm" styleId="sjtglpm" onblur="checkLen(this,10);"/>��
                    </td>
                </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist bxnmb" >
            <tbody>
                <tr>
                    <th width="27%"><span style="color: red">*</span>�������Ŵ�</th>
                    <td width="23%"><html:text property="bjgmc" styleId="bjgmc" onblur="checkLen(this,10);"/>��</td>
                    <th width="27%"><span style="color: red">*</span>����������</th>
                    <td width="23%"><html:text property="bjgrs" styleId="bjgrs" onblur="checkLen(this,10);"/>��</td>
                </tr>
                <tr>
                    <th><span style="color: red">*</span>�������˴�</th>
                    <td><html:text property="bjgrc" styleId="bjgrc" onblur="checkLen(this,10);"/>�˴�</td>
                    <th><span style="color: red">*</span>��ɲ�ѧϰ�ɼ�ǰ����</th>
                    <td><html:text property="bgbqwrs" styleId="bgbqwrs" onblur="checkLen(this,10);"/>��</td>

                </tr>
                <tr>
                    <th><span style="color: red">*</span>��ɲ�ѧϰ�ɼ�ǰʮ��</th>
                    <td><html:text property="bgbqsrs" styleId="bgbqsrs" onblur="checkLen(this,10);"/>��</td>
                    <th><span style="color: red">*</span>��ѧ��</th>
                    <td><html:text property="hjxsrs" styleId="hjxsrs" onblur="checkLen(this,10);"/>��</td>
                </tr>
                <tr>
                    <th><span style="color: red">*</span>���影</th>
                    <td><html:text property="hjtjgs" styleId="hjtjgs" onblur="checkLen(this,10);"/>��</td>
                    <th><span style="color: red">*</span>���ʵ����</th>
                    <td><html:text property="shsjhjrc" styleId="shsjhjrc" onblur="checkLen(this,10);"/>�˴�</td>
                </tr>
                <tr>
                    <th><span style="color: red">*</span>�����</th>
                    <td><html:text property="sshjcs" styleId="sshjcs" onblur="checkLen(this,10);"/>��</td>
                    <th><span style="color: red">*</span>��֯ȫ�༯��</th>
                    <td><html:text property="qbjthdcs" styleId="qbjthdcs" onblur="checkLen(this,10);"/>��</td>
                </tr>
                <tr>
                    <th><span style="color: red">*</span>�Ƽ�ѧ����</th>
                    <td><html:text property="kjxshjrc" styleId="kjxshjrc" onblur="checkLen(this,10);"/>�˴�</td>
                    <th><span style="color: red">*</span>��֯�༶ͬѧ�μ�УԺ�</th>
                    <td><html:text property="cjxyhdcs" styleId="cjxyhdcs" onblur="checkLen(this,10);"/>��</td>
                </tr>
                <tr>
                    <th><span style="color: red">*</span>����ͬѧ</th>
                    <td><html:text property="jjtxrs" styleId="jjtxrs" onblur="checkLen(this,10);"/>��</td>
                    <th><span style="color: red">*</span>�Զ�ѧ��</th>
                    <td><html:text property="sdxsrs" styleId="sdxsrs" onblur="checkLen(this,10);"/>��</td>
                </tr>
                <tr>
                    <th><span style="color: red">*</span>��ѧ</th>
                    <td colspan="3"><html:text property="txrs" styleId="txrs" onblur="checkLen(this,10);"/>��</td>
                </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th width="14%">
                    <span style="color: red">*</span>
                    <logic:equal name="hblx" value="nzhb">�����ܽ�</logic:equal>
                    <logic:equal name="hblx" value="nzzj">��Ȱ༶�����ܽ�</logic:equal>
                    <br>
                    <span style="color: red">(��500��)</span>
                </th>
                <td   colspan="3">
                    <html:textarea property="zd1" style="width:98%;margin-top:5px" rows="5"
                                   onblur="checkLen(this,500);" styleId="zd1"
                    ></html:textarea>
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>
                    <logic:equal name="hblx" value="nzhb">�༶��������������</logic:equal>
                    <logic:equal name="hblx" value="nzzj">�༶������̵����¼�</logic:equal>
                    <br>
                    <span style="color: red">(��500��)</span>
                </th>
                <td   colspan="3">
                    <html:textarea property="zd2" style="width:98%;margin-top:5px" rows="5"
                                   onblur="checkLen(this,500);" styleId="zd2"
                    ></html:textarea>
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>
                    <logic:equal name="hblx" value="nzhb">�༶�������ķ���</logic:equal>
                    <logic:equal name="hblx" value="nzzj">�༶����͸��˻���ϸ</logic:equal>
                    <br>
                    <span style="color: red">(��500��)</span>
                </th>
                <td   colspan="3">
                    <html:textarea property="zd3" style="width:98%;margin-top:5px" rows="5"
                                   onblur="checkLen(this,500);" styleId="zd3"
                    ></html:textarea>
                </td>
            </tr>

            </tbody>


        </table>
    </div>
</html:form>
<table width="100%" border="0" class="formlist">
    <tfoot>
    <tr>
        <td colspan="4">
            <div class="bz">
                "<span class="red">*</span>"Ϊ������
            </div>
            <div class="btn">
                <button type="button" type="button" onclick="saveForm_add('save');">
                    ����ݸ�
                </button>

                <button type="button" type="button" onclick="saveForm_add('submit');">
                    �ύ����
                </button>

                <button type="button" type="button" onclick="iFClose();">
                    �� ��
                </button>
            </div>
        </td>
    </tr>
    </tfoot>
</table>
</body>
</html>

