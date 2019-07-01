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
    <script type="text/javascript" src="xsgzgl/sxzzjygl/grxfjs/grxfjshb/js/grxfjshbEdit.js"></script>

    <script type="text/javascript">
        jQuery(function(){

        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
        .formlist input{
            width: 50px;
        }
    </style>
</head>
<body>
<html:form action="/sxzzjy_grxfjshb" method="post"  styleId="form">
    <html:hidden property="hblx" styleId="hblx"/>
    <html:hidden property="jgid" styleId="jgid" />
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ��������Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
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
                <th width="27%"><span style="color: red">*</span>ƽ��ѧ�ֻ�</th>
                <td width="23%"><html:text property="pjxfj" styleId="pjxfj" onblur="checkLen(this,10);"/>��</td>
                <th width="27%"><span style="color: red">*</span>ƽ��ѧ�ֻ�С������</th>
                <td width="23%"><html:text property="pjxfjpm" styleId="pjxfjpm" onblur="checkLen(this,10);"/>��</td>
            </tr>
            <tr>
                <th width="27%"><span style="color: red">*</span>�ۺϲ����ɼ�</th>
                <td width="23%"><html:text property="zhcpcj" styleId="zhcpcj" onblur="checkLen(this,10);"/>��</td>
                <th width="27%"><span style="color: red">*</span>�ۺϲ����ɼ�����</th>
                <td width="23%"><html:text property="zhcpcjpm" styleId="zhcpcjpm" onblur="checkLen(this,10);"/>��</td>
            </tr>
            <tr>
                <th width="27%"><span style="color: red">*</span>�ۺϲ����ɼ��꼶��רҵ���ٷֱ�</th>
                <td colspan="3"><html:text property="njbfb" styleId="njbfb" onblur="checkLen(this,10);"/>%</td>
            </tr>
            <tr>
                <th width="27%">
                    <span style="color: red">*</span>����<br>
                    <span style="color: red">(��100��)</span>
                </th>
                <td colspan="3">
                    <html:textarea property="qt" style="width:98%;margin-top:5px" rows="3"
                                   onblur="checkLen(this,100);" styleId="qt"
                    ></html:textarea>
                </td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th width="14%">
                    <span style="color: red">*</span>
                    <logic:equal name="hblx" value="nzhb">��һѧ�ڿγ̳ɼ�</logic:equal>
                    <logic:equal name="hblx" value="nzzj">��ѧ��γ̳ɼ�</logic:equal>
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
                    <logic:equal name="hblx" value="nzhb">�����ܽ�</logic:equal>
                    <logic:equal name="hblx" value="nzzj">��ȸ��˽����ܽ�</logic:equal>
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
                    <logic:equal name="hblx" value="nzhb">���˽�������������</logic:equal>
                    <logic:equal name="hblx" value="nzzj">���˽�����̵����¼�</logic:equal>
                    <br>
                    <span style="color: red">(��500��)</span>
                </th>
                <td   colspan="3">
                    <html:textarea property="zd3" style="width:98%;margin-top:5px" rows="5"
                                   onblur="checkLen(this,500);" styleId="zd3"
                    ></html:textarea>
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>
                    <logic:equal name="hblx" value="nzhb">���˽������ķ���</logic:equal>
                    <logic:equal name="hblx" value="nzzj">���˻���ϸ</logic:equal>
                    <br>
                    <span style="color: red">(��500��)</span>
                </th>
                <td   colspan="3">
                    <html:textarea property="zd4" style="width:98%;margin-top:5px" rows="5"
                                   onblur="checkLen(this,500);" styleId="zd4"
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

