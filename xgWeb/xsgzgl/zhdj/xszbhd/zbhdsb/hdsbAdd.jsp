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
    <script type="text/javascript" src="xsgzgl/zhdj/xszbhd/zbhdsb/js/hdsbEdit.js"></script>

    <script type="text/javascript">

    </script>
    <style type="text/css">
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/xszbhd_hdsb"  method="post"  styleId="form">
    <html:hidden property="dzbid" styleId="dzbid"  value="${map.dzbid}"/>
    <html:hidden property="type" styleId="type"  value="${type}"/>
    <html:hidden property="hdid" styleId="hdid"  value="${model.hdid}"/>
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr style="">
                <th width="16%">
                    ѧ��
                </th>
                <td width="34%">
                        ${xn}
                </td>
                <th width="16%">
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
                    ${xqmc}
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
                    <span style="color: red">*</span> ʱ��
                </th>
                <td >
                    <html:text property="hdsj" styleId="hdsj" style="width:150px;" onfocus="showCalendar('hdsj','yyyy-MM-dd HH:mm:ss');" />
                </td>
                <th >
                    <span style="color: red">*</span> �ص�
                </th>
                <td >
                    <html:text property="hddd" styleId="hddd" style="width:150px;" />
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span> Ӧ������
                </th>
                <td >
                    <html:text property="ydrs" styleId="ydrs" style="width:150px;" onblur="checkInt(this);"/>
                </td>
                <th >
                    <span style="color: red">*</span> ʵ������
                </th>
                <td >
                    <html:text property="sdrs" styleId="sdrs" style="width:150px;" onblur="checkInt(this);"/>
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span> �������
                </th>
                <td >
                    <html:text property="qjrs" styleId="qjrs" style="width:150px;" onblur="checkInt(this);"/>
                </td>
                <th >
                    <span style="color: red">*</span> �޹ʲ�������
                </th>
                <td >
                    <html:text property="wgbdrs" styleId="wgbdrs" style="width:150px;" onblur="checkInt(this);"/>
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span> ����һ��/���ջ
                </th>
                <td >
                    <html:select  property="shykdrhd" style="width:150px" styleId="shykdrhd">
                        <html:option value=""></html:option>
                        <html:options collection="drhdList" property="dm"
                                      labelProperty="mc" />
                    </html:select>
                </td>
                <th >
                    <span style="color: red">*</span> �����
                </th>
                <td >
                    <html:select property="hdlx" style="width:150px" styleId="hdlx">
                        <html:option value=""></html:option>
                        <html:options collection="hdlxList" property="dm"
                                      labelProperty="mc" />
                    </html:select>
                </td>
            </tr>

            <tr style="">
                <logic:equal value="xssb" name="type">
                    <th >
                        �����
                    </th>
                    <td >
                        ${map.hdzt}
                        <html:hidden property="hdzt" styleId="hdzt" style="width:150px;"/>
                    </td>
                </logic:equal>
                <logic:notEqual value="xssb" name="type">
                    <th >
                        <span style="color: red">*</span> �����
                    </th>
                    <td >
                        <html:text property="hdzt" styleId="hdzt" style="width:150px;"/>
                    </td>
                </logic:notEqual>
                <th >
                    <span style="color: red">*</span> ʱ��
                </th>
                <td >
                    <html:text property="hdsc" styleId="hdsc" style="width:150px;" />
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span> �ύ˼�뱨����Ա��
                </th>
                <td   colspan="3">
                    <html:text property="tjsxbgrs" styleId="tjsxbgrs" style="width:150px;" onblur="checkInt(this);"/>
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>�����¼<br>
                    <span style="color: red">(��500��)</span>
                </th>
                <td    colspan="3">
                    <textarea rows="3" cols="3" id="hyjl" name="hyjl" onblur="checkLen(this,500);" maxlength="500"></textarea>
                </td>
            </tr>

            <tr>
                <th>
                    ����
                </th>
                <td  colspan="3">
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

