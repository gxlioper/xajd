<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/dtjs/shsjjl/jg/js/shsjjlJg.js"></script>
    <script type="text/javascript" src="xsgzgl/xstgl/comm/js/comm.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
    <script type="text/javascript" src="js/provicecitylocal.js"></script>
    <link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
    <script type='text/javascript'>
        jQuery(function(){
            proviceCiyyLocalMain({type:"edit",id:"ddssx",flag:"yxxdz"});
        })
    </script>
</head>
<body style="width: 100%">
<html:form action="/shsjjl_jg" method="post" styleId="form" onsubmit="return false;">
    <html:hidden property="xh" styleId="xh"/>
    <html:hidden property="jgid" styleId="jgid"/>
    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ��������Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span><font id="gnmkmc_prompt_span"></font></span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">
                    ѧ��
                </th>
                <td width="30%">
                    <html:select property="xn" styleId="xn">
                        <html:options collection="xnList" property="xn" labelProperty="xn"/>
                    </html:select>
                </td>
                <th>ѧ��</th>
                <td>
                    <html:select  property="xq" styleId="xq" style="width:130px">
                        <html:option value=""/>
                        <html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>�����
                </th>
                <td colspan="3">
                    <html:text property="hdmc" styleId="hdmc" style="width:85%" onblur="checkLen(this,50);"/>
                </td>

            </tr>
            <tr>
                <th ><span class="red">*</span>ʱ��</th>
                <td >
                    <input type="hidden" id="nowDate" name="nowDate" value="${nowDate}">
                    <html:text property="sj"  styleId="sj"
                               onclick="return showCalendar('sj','yyyy-MM-dd HH:mm',true,'nowDate');" />
                </td>
                <th>
                    <font color="red">*</font>���쵥λ
                </th>
                <td>
                    <html:text property="zbdw" styleId="zbdw" style="width:95%" onblur="checkLen(this,50);"/>
                </td>

            </tr>
            <tr>
                <th>
                    <font color="red">*</font>�ص�
                </th>
                <td colspan="3">
                    <html:hidden  property="ddssx" styleId="ddssx"/>
                    <html:text property="dd" styleId="dd"  onblur="checkLen(this,50);"/>
                </td>
            </tr>
            <tr>
                <th>����</th>
                <td colspan="3">
                    <html:hidden property="fjid" styleId="fjid" />
                    <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
                    <script type="text/javascript">
                        //���ø���
                        jQuery(function(){
                            jQuery.MultiUploader({
                                maxcount : 3,
                                //��׺
                                accept : 'png|gif|jpg|zip|rar|doc|docx',
                                //����ļ���С ��λM
                                maxsize: 10,
                                //��Ÿ������������id
                                elementid : 'fjid'
                            });
                        });
                    </script>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height:35px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"Ϊ������
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save('update');">
                            �� ��
                        </button>
                        <button type="button" onclick="iFClose();">
                            �� ��
                        </button>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
    <%@ include file="/comm/other/tsxx.jsp"%>
</html:form>
</body>
</html>

