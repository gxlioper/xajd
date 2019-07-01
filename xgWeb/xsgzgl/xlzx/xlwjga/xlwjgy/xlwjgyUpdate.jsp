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
<html:form method="post" styleId="form" action="/xlzx_xlwjgy">
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
                    <span>Σ������</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th align="right">
                    <font color="red">*</font>����ʱ��
                </th>
                <td >
                    <html:text property="bgsj" styleId="bgsj" style="width:150px;"
                               onclick="return showCalendar('bgsj','yyyy-MM-dd HH:mm');"/>
                </td>
                <th align="right">
                    <font color="red">*</font>������
                </th>
                <td>
                    <html:text property="bgr" styleId="bgr" style="width:120px;"
                               onblur="checkLen(this,8);"/>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>����;��
                </th>
                <td >
                    <html:select property="fxtj" styleId="fxtj" style="width:120px">
                        <html:option value=""/>
                        <html:option value="fdy">����Ա</html:option>
                        <html:option value="stu">ѧ��</html:option>
                        <html:option value="tel">�绰����</html:option>
                        <html:option value="bbs">BBS</html:option>
                        <html:option value="zx">��ѯ</html:option>
                        <html:option value="qq">QQ</html:option>
                        <html:option value="qt">����</html:option>

                    </html:select>
                </td>
                <th align="right">
                    <font color="red">*</font>Σ���̶�
                </th>
                <td >
                    <html:select property="wjcd" styleId="wjcd" style="width:120px">
                        <html:option value=""/>
                        <html:option value="fcjj">�ǳ�����</html:option>
                        <html:option value="jj">����</html:option>
                        <html:option value="yb">һ��</html:option>
                        <html:option value="bj">����</html:option>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>Σ����չ���̼�����
                    <br/>
                    <font color="red"><B>(��1000��)</B>
                    </font>
                </th>
                <td colspan="3">
                    <html:textarea property='wjfzgc' styleId="wjfzgc" style="width:95%" rows='5'
                                   onblur="checkLen(this,1000)"/>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>Σ����Ԥ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th align="right">
                    <font color="red">*</font>Σ����Ԥʱ��
                </th>
                <td>
                    <html:text property="wjgysj" styleId="wjgysj" style="width:150px;"
                               onclick="return showCalendar('wjgysj','yyyy-MM-dd HH:mm');"/>
                </td>
                <th align="right">
                    <font color="red">*</font>��Ԥ��Ա
                </th>
                <td>
                    <html:text property="wjgyry" styleId="wjgyry" style="width:120px;"
                               onblur="checkLen(this,8);"/>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>��Ԥ��ʽ
                </th>
                <td >
                    <html:select property="wjgyfs" styleId="wjgyfs" style="width:120px">
                        <html:option value=""/>
                        <html:option value="xcgy">�ֳ���Ԥ</html:option>
                        <html:option value="zdxy">ָ����Ժ/ѧԺ</html:option>
                        <html:option value="yjzx">����ԤԼ��ѯ</html:option>
                        <html:option value="jyyl">����ҽ��</html:option>
                    </html:select>
                </td>
                <th align="right">
                    <font color="red">*</font>Эͬ����
                </th>
                <td >
                    <html:select property="xtbm" styleId="xtbm" style="width:120px">
                        <html:option value=""/>
                        <html:option value="sy">��Ժ/ѧԺ</html:option>
                        <html:option value="gac">������</html:option>
                        <html:option value="wlzx">��������</html:option>
                        <html:option value="xyy">УҽԺ</html:option>
                        <html:option value="qt">����</html:option>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>���
                </th>
                <td align="left" colspan="3">
                    <html:select property="wjgyjg" styleId="wjgyjg" style="width:120px">
                        <html:option value=""/>
                        <html:option value="sljj">˳�����</html:option>
                        <html:option value="ydgj">�д�����</html:option>
                        <html:option value="jyyl">����ҽ��</html:option>
                        <html:option value="xx">��ѧ</html:option>
                        <html:option value="tx">��ѧ</html:option>
                        <html:option value="qt">����</html:option>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>Σ��������̼���ʩ
                    <br/>
                    <font color="red"><B>(��1000��)</B>
                    </font>
                </th>
                <td align="left" colspan="3">
                    <html:textarea property='wjclgc' styleId="wjclgc" style="width:95%" rows='5'
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