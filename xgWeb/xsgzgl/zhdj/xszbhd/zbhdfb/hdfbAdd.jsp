
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
    <script type="text/javascript" src="xsgzgl/zhdj/xszbhd/zbhdfb/js/hdfbEdit.js"></script>

    <script type="text/javascript">

    </script>
    <style type="text/css">
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/xszbhd_hdfb" method="post"  styleId="form">
    <div style='width:100%; height:420px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr style="">
                <th width="30%">
                    ѧ��
                </th>
                <td width="70%">
                    ${xn}
                </td>


            </tr>
            <tr>
                <th >
                    ѧ��
                </th>
                <td  class="xm">
                        ${xq}
                </td>

            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span>����
                </th>
                <td  class="">
                    <input type="text" id="hdzt" name="hdzt" style="width: 80%"/>
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span> ��ʼʱ��
                </th>
                <td >
                    <html:text property="kssj" styleId="kssj" style="width:150px;" onfocus="showCalendar('kssj','yyyy-MM-dd HH:mm:ss',true,'jzsj');" />
                    ��
                    <html:text property="jzsj" styleId="jzsj" style="width:150px;" onfocus="showCalendar('jzsj','yyyy-MM-dd HH:mm:ss',false,'kssj');" />


                </td>
            </tr>
            <tr style="">
                <th >
                    ����֧��
                </th>
                <td style="text-align: left">
                   <label> <input type="checkbox" id="qtdzb"/> ȫ�嵳֧��</label>
                </td>
            </tr>
            <tr style="">
                <th>
                   <span style="color: red">*</span>����<br>
                    <span style="color: red">(��500��)</span>
                </th>
                <td    class="">
                     <textarea rows="3" cols="3" id="hdnr" name="hdnr" onblur="checkLen(this,500);" maxlength="500"></textarea>
                </td>
            </tr>
            <tr>
                <th>
                    ����
                </th>
               <td >
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

