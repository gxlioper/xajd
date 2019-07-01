<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/cxcy/bzsbwh/bzsbwhsq/js/bzsbwhsqEdit.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body style="width: 100%">
<html:form action="/cxcy_bzsbwhsq" method="post" styleId="form" onsubmit="return false;">
    <html:hidden property="sqid" styleId="xq" value="${map.sqid}"/>
    <html:hidden property="xn" styleId="xn" value="${map.xn}"/>
    <html:hidden property="xq" styleId="xq" value="${map.xq}"/>
    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 500px' >
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
                    <span>���´�ҵ����������Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="15%">
                    ѧ��
                </th>
                <td width="35%">
                        ${map.xn}
                </td>
                <th width="15%">ѧ��</th>
                <td width="35%">
                        ${map.xqmc}
                </td>
            </tr>
            <tr>
                <th width="15%">
                    <span>���</span>
                </th>
                <td >
                        ${map.tbrmc}
                </td>
                <th>
                    �ʱ��
                </th>
                <td>
                        ${map.sqsj}
                </td>
            </tr>
            <tr>
                <th width="15%">
                    <span><font color="red">*</font>��Ŀ����</span>
                </th>
                <td >
                    <html:text property="xmmc" styleId="xmmc" maxlength="50" style="width:80%;" value="${map.xmmc}"/>
                </td>
                <th>
                    <span><font color="red">*</font>������Ԫ��</span>
                </th>
                <td>
                    <html:text property="bzje" styleId="bzje" style="width:100px;"
                               onblur="checkMoney(this);"  value="${map.bzje}"/>
                </td>
            </tr>
            <tr>
                <th>
                    <span>����</span>
                </th>
                <td colspan="3">
                    <html:hidden property="fj" styleId="fj"  value="${map.fj}"/>
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
                                elementid : 'fj'
                            });
                        });
                    </script>
                </td>
            </tr>
            <tr>
                <th>
                    <font color="red">*</font>��������
                    <br /><font color="red">&lt;��500��&gt;</font>
                </th>
                <td colspan="3">
                    <html:textarea property='sqly' style="width:98%" styleId="sqly" rows='4'
                                   onblur="checkLen(this,500);"  value="${map.sqly}"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height:25px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"Ϊ������
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save('save');">
                            ����ݸ�
                        </button>
                        <button type="button" onclick="save('submit');">
                            �ύ����
                        </button>
                        <button type="button" onclick="iFClose();">
                            �ر�
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

