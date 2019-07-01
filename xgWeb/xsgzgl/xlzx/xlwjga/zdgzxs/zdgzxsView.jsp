<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src='/xgxt/js/check.js'></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="xsgzgl/xlzx/xlwjga/xlwjgy/js/xlwjgy.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <script type="text/javascript">

    </script>
</head>

<body>
<html:form method="post" styleId="form" action="/xlzx_zdgzxs">
    <div style='width: 100%; height: 450px; overflow-x: hidden; overflow-y: auto;'>
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
                    <span>��ѯ���</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th align="right">
                    ��ѯʦ
                </th>
                <td >
                        ${map.zxsxm}

                </td>
                <th align="right">
                    ��ϵ�绰
                </th>
                <td>
                        ${map.zxslxdh}
                </td>
            </tr>
            <tr>
                <th align="right">
                    Ŀǰ��ѯ����
                </th>
                <td >
                        ${map.zxcs}
                </td>
                <th align="right">
                    �Ƿ�����ѧ��ͬ��
                </th>
                <td >
                        ${map.sfxsty == "1" ? "��":"��"}
                </td>
            </tr>
            <tr>
                <th align="right">
                    �������
                </th>
                <td colspan="3">
                        ${map.wtlbmc}
                </td>
            </tr>
            <tr>
                <th align="right">
                    ��������
                </th>
                <td align="left" colspan="3">
                        ${map.wtms}
                </td>
            </tr>
            <tr>
                <th align="right">
                    ������
                </th>
                <td align="left" colspan="3">
                        ${map.cljy}
                </td>
            </tr>
                <%--<tr>
                    <th>
                        ����
                    </th>
                    <td colspan="3">
                        <html:hidden property="filepath2" styleId="filepath2"/>
                        <input type="file" id="filepath_f2" name="filepath2" />
                        <script type="text/javascript">
                            //���ø���
                            jQuery(function(){
                                jQuery('#filepath_f2').multiUploader({
                                    maxcount : 1,
                                    //��׺
                                    accept : 'png|gif|jpg|zip|rar|doc|docx',
                                    //����ļ���С ��λM
                                    maxsize: 10,
                                    //��Ÿ������������id
                                    elementid : 'filepath2',
                                    eid : 'filepath_f2'
                                });
                            });
                        </script>
                    </td>
                </tr>--%>
            </tbody>
        </table>
    </div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td>
                    <div class="btn">
                        <button type="button" onclick="iFClose();return false;">
                            �� ��
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