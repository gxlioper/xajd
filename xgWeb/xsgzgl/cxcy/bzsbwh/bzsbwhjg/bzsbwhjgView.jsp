<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body style="width: 100%">
<html:form action="/cxcy_bzsbwhjg" method="post" styleId="form" onsubmit="return false;">
    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 400px' >
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ��������Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
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
                    <span>��Ŀ����</span>
                </th>
                <td >
                        ${map.xmmc}
                </td>
                <th>
                    ������Ԫ��
                </th>
                <td>
                        ${map.bzje}
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
                        ${map.lrsj}
                </td>
            </tr>
            <tr>
                <th>
                    <span>����</span>
                </th>
                <td colspan="3">
                    <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                    <script type="text/javascript">
                        //���ø���
                        jQuery(function(){
                            var gid = "${map.fj}";
                            jQuery.MultiUploader_q({
                                gid : gid
                            });
                        });
                    </script>
                </td>
            </tr>
            <tr>
                <th>
                    ��������
                </th>
                <td colspan="3">
                        ${map.sqly}
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
                    <div class="btn">
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

