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
    <script type="text/javascript" src="xsgzgl/sxzzjygl/grxfjs/grxfjsjg/js/grxfjsjgEdit.js"></script>
    <script type="text/javascript">
        jQuery(function(){

        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
        #bxnmbTable input{width: 50px;}
    </style>
</head>
<body>
<html:form action="/sxzzjy_grxfjsjg" method="post"  styleId="form">
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <html:hidden property="jgid" styleId="jgid" />
        <html:hidden property="xh" styleId="xh" />
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
                <tbody id="">
                <tr style="">
                    <th colspan="4"  style="text-align: left">
                        ����ѧ�罨��
                    </th>
                </tr>
                <tr style="">
                    <th >
                        <span style="color: red">*</span>�� ��
                    </th>
                    <td colspan="3">
                        <html:text property="xfjsmc" styleId="xfjsmc" style="width:60%;" />
                    </td>
                </tr>
                <tr style="">
                    <th >
                        <span style="color: red">*</span>�걨����
                    </th>
                    <td >
                        <html:select property="sblx" styleId="sblx" style="width:150px" styleClass="select">
                            <html:option value=""></html:option>
                            <html:options collection="sblxList" property="sblxdm"
                                          labelProperty="sblxmc" />
                        </html:select>
                    </td>
                    <th >
                        ѧ��ѧ��
                    </th>
                    <td>
                            ${map.xn}${map.xqmc}
                        <html:hidden property="xn" styleId="xn"/>
                        <html:hidden property="xq" styleId="xq"/>
                    </td>
                </tr>
                <tr style="">
                    <th>
                        <span style="color: red">*</span>��ѧ��Ŀ��
                    </th>
                    <td   colspan="3">
                        <table id="bxnmbTable">
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
                                <td ><html:text property="njbfb" styleId="njbfb" onblur="checkLen(this,10);"/>%</td>
                                <th width="27%"><span style="color: red">*</span>��������񽱴���</th>
                                <td ><html:text property="sshjcs" styleId="sshjcs" onblur="checkLen(this,10);"/>��</td>
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
                        </table>
                    </td>
                </tr>
                <tr style="">
                    <th>
                        <span style="color: red">*</span>����˼·�ͼƻ�<br>
                        <span style="color: red">(��500��)</span>
                    </th>
                    <td   colspan="3">
                        <html:textarea property="jssl" style="width:98%;margin-top:5px" rows="5"
                                       onblur="checkLen(this,500);" styleId="jssl"
                        ></html:textarea>
                    </td>
                </tr>
                <tr>
                    <th>
                        ����
                    </th>
                    <td colspan="3">
                        <html:hidden property="fjid" styleId="fjid" />
                        <input type="file" id="filepath_f" name="filepath" />
                        <script type="text/javascript">
                            //���ø���
                            jQuery(function(){
                                jQuery('#filepath_f').multiUploader({
                                    maxcount : 3,
                                    //��׺
                                    accept : 'png|gif|jpg|zip|rar|doc|docx',
                                    //����ļ���С ��λM
                                    maxsize: 10,
                                    //��Ÿ������������id
                                    elementid : 'fjid',

                                    eid : 'filepath_f'
                                });
                            });
                        </script>
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

                <button type="button" type="button" onclick="save('update');">
                    �� ��
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

