<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xszbhd/zbhdsb/js/hdsbEdit.js"></script>

    <script type="text/javascript">

    </script>
    <style type="text/css">
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/xszbhd_hdsb"  method="post"  styleId="form">
    <%--<html:hidden property="dzbid" styleId="dzbid"  />
    <html:hidden property="type" styleId="type"  />
    <html:hidden property="hdid" styleId="hdid"  />
    <html:hidden property="xn" styleId="xqdm"  />
    <html:hidden property="xqdm" styleId="xqdm"  />
    <html:hidden property="hdsbid" styleId="hdsbid"  />--%>
    <div style='width:100%; height:360px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr style="">
                <th width="16%">
                    ѧ��
                </th>
                <td width="34%">
                        ${map.xn}
                </td>
                <th  width="16%">
                    ֧��
                </th>
                <td width="34%">
                        ${map.dzbmc}
                </td>
            </tr>
            <tr style="">
                <th >
                    ѧ��
                </th>
                <td >
                        ${map.xqmc}
                </td>
                <th >
                    ����ѧԺ
                </th>
                <td >
                        ${map.xymc}
                </td>
            </tr>
            <tr style="">
                <th >
                    ʱ��
                </th>
                <td >
                        ${map.hdsj}
                </td>
                <th >
                     �ص�
                </th>
                <td >
                        ${map.hddd}
                </td>
            </tr>
            <tr style="">
                <th >
                     Ӧ������
                </th>
                <td >
                        ${map.ydrs}
                </td>
                <th >
                    ʵ������
                </th>
                <td >
                        ${map.sdrs}
                </td>
            </tr>
            <tr style="">
                <th >
                    �������
                </th>
                <td >
                        ${map.qjrs}
                </td>
                <th >
                     �޹ʲ�������
                </th>
                <td >
                        ${map.wgbdrs}
                </td>
            </tr>
            <tr style="">
                <th >
                     ����һ��/���ջ
                </th>
                <td >
                        ${map.shykdrhdmc}
                </td>
                <th >
                     �����
                </th>
                <td >
                        ${map.hdlxmc}
                </td>
            </tr>

            <tr style="">
                <th >
                        �����
                </th>
                <td >
                        ${map.hdzt}
                </td>
                <th >
                     ʱ��
                </th>
                <td >
                        ${map.hdsc}
                </td>
            </tr>
            <tr style="">
                <th >
                    �ύ˼�뱨����Ա��
                </th>
                <td   colspan="3">
                        ${map.tjsxbgrs}
                </td>
            </tr>
            <tr style="">
                <th>
                    �����¼
                </th>
                <td    colspan="3">
                        ${map.hyjl}
                </td>
            </tr>

            <tr>
                <th>
                    ����
                </th>
                <td  colspan="3">
                    <div id="commonfileupload-list-0" style="padding: 5px;"></div>
                    <script type="text/javascript">
                        //���ø���
                        jQuery(function(){
                            var gid = "${map.fjid}";
                            jQuery.MultiUploader_q({
                                gid : gid,
                                targetEl : 'commonfileupload-list-0'
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
                <%--<div class="bz">
                    "<span class="red">*</span>"Ϊ������
                </div>--%>
                <div class="btn">
                    <%--<button id="buttonSave" onclick="save('update');return false;">
                        �� ��
                    </button>--%>
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

