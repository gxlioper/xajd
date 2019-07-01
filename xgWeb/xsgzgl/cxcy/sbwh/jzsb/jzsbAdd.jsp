<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/cxcy/sbwh/jzsb/js/jzsb.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body style="width: 100%">
<html:form action="/cxcy_jzsb" method="post" styleId="form" onsubmit="return false;">
    <html:hidden property="xn" styleId="xn" value="${xn}"/>
    <html:hidden property="xq" styleId="xq" value="${xq}"/>
    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 400px' >
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>�����ϱ���Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="15%">
                    ѧ��ѧ��
                </th>
                <td width="35%">
                        ${xn}${xqmc}
                </td>
                <th width="15%">���</th>
                <td width="35%">
                    <logic:notEqual value="xx" name="user" property="userStatus">
                        <html:hidden property="xydm" styleId="xydm" value="${user.userDep}" />
                        ${user.userDepName}
                    </logic:notEqual>
                    <logic:equal value="xx" name="user"  property="userStatus">
                        <html:select property="xydm"  styleId="xydm"  style="width:150px"
                                     styleClass="select" >
                            <html:option value=""/>
                            <html:options collection="xyList" property="xydm"
                                          labelProperty="xymc" />
                        </html:select>
                    </logic:equal>
                </td>
            </tr>
            <tr>
                <th width="15%">
                    <span><font color="red">*</font>��������</span>
                </th>
                <td >
                    <html:text property="jzmc" styleId="jzmc" maxlength="50" style="width:80%;"/>
                </td>
                <th>
                    <span><font color="red">*</font>������</span>
                </th>
                <td>
                    <html:text property="zjr" styleId="zjr" maxlength="20"  />
                </td>
            </tr>
            <tr>
                <th width="15%">
                    <span><font color="red">*</font>����ʱ��</span>
                </th>
                <td >
                    <html:text property="jzsj" styleId="jzsj" onclick="return showCalendar('jzsj','yyyy-MM-dd HH:mm');"/>
                </td>
                <th>
                    <span><font color="red">*</font>�����ص�</span>
                </th>
                <td>
                    <html:text property="jzdd" styleId="jzdd" maxlength="20"  />
                </td>
            </tr>
            <tr>
                <th>
                    <font color="red">*</font>�ڿ�����
                    <br /><font color="red">&lt;��500��&gt;</font>
                </th>
                <td colspan="3">
                    <html:textarea property='sknr' style="width:98%" styleId="sknr" rows='4' onblur="checkLen(this,500);"/>
                </td>
            </tr>
            <tr>
                <th>
                    <font color="red">*</font>������
                    <br /><font color="red">&lt;��500��&gt;</font>
                </th>
                <td colspan="3">
                    <html:textarea property='tjr' style="width:98%" styleId="tjr" rows='4' onblur="checkLen(this,500);"/>
                </td>
            </tr>
            <%--<tr>
                <th>
                    <span>����</span>
                </th>
                <td colspan="3">
                    <html:hidden property="fj" styleId="fj"/>
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
            </tr>--%>

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
                        <button type="button" onclick="save();">
                            ����
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

