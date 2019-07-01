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
    <script type="text/javascript" src="xsgzgl/xlzx/xlwjga/zdgzxs/js/zdgzxs.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <script type="text/javascript">

    </script>
</head>

<body>
<html:form method="post" styleId="form" action="/xlzx_zdgzxs">
    <html:hidden property="id" styleId="id"/>
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
                            <html:hidden property="zxs" styleId="zxs"/>

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
                    <font color="red">*</font>Ŀǰ��ѯ����
                </th>
                <td >
                    <html:text property="zxcs" styleId="zxcs"/>
                </td>
                <th align="right">
                    <font color="red">*</font>�Ƿ�����ѧ��ͬ��
                </th>
                <td >
                    <html:radio property="sfxsty" value="1">��</html:radio>
                    <html:radio property="sfxsty" value="0">��</html:radio>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>�������
                </th>
                <td colspan="3">
                    <html:select property="wtlb" styleId="wtlb" style="width:120px">
                        <html:option value=""/>
                        <html:option value="A">A �ࣺ�м��̵���ɱ���˺������գ���24Сʱ�໤</html:option>
                        <html:option value="B">B �� ������ɱ���˺��������辭���˽�����</html:option>
                        <html:option value="C">C �� ���о��񼲲������븨��ԱЭ��ҽ��</html:option>
                        <html:option value="D">D �� ������ɱ���˺��������������븨��ԱЭ���������</html:option>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>��������
                    <br/>
                    <font color="red"><B>(��1000��)</B>
                    </font>
                </th>
                <td align="left" colspan="3">
                    <html:textarea property='wtms' styleId="wtms" style="width:95%" rows='5'
                                   onblur="checkLen(this,1000)"/>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>������
                    <br/>
                    <font color="red"><B>(��1000��)</B>
                    </font>
                </th>
                <td align="left" colspan="3">
                    <html:textarea property='cljy' styleId="cljy" style="width:95%" rows='5'
                                   onblur="checkLen(this,1000)"/>
                </td>
            </tr>
                <%--<tr>
                    <th>
                        <font color="red">*</font>����
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
                    <div class="bz">
                        "<span class="red">*</span>"Ϊ������
                    </div>
                    <div class="btn">
                        <button id="buttonSave" type="button" onclick="save('update');return false;">
                            �� ��
                        </button>
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