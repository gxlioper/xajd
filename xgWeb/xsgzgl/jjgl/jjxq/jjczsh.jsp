<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/jjgl/jjxq/js/jjczsh.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${jjxqForm.sqid}&tt="+new Date().getTime());
            jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${jjxqForm.splc}&shid=${jjxqForm.shid}");
        });

    </script>
</head>
<body>
<html:form action="/jjgl_jjxq" method="post" styleId="jjxqForm">
    <html:hidden  property="sqid" styleId="sqid"/>
    <html:hidden  property="xh" styleId="xh"/>
    <html:hidden  property="splc" styleId="splc"/>
    <html:hidden  property="shid" styleId="shid"/>
    <html:hidden  property="gwid" styleId="gwid"/>
    <html:hidden  property="jjcz" styleId="jjcz"/>
    <html:hidden  property="xqid" styleId="xqid"/>
    <div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="5">
                    <span>ѧ����Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr style="height: 45px;">
                <th width="15%">ѧ��</th>
                <td width="25%">${jbxx.xh }</td>
                <th width="15%">����</th>
                <td width="25%">${jbxx.xm }</td>
                <td rowspan="3" align="center">
                    <img src="xsxx_xsgl.do?method=showPhoto&xh=${jbxx.xh }" width="100" height="120" >
                </td>
            </tr>
            <tr style="height: 45px;">
                <th>�Ա�</th>
                <td>${jbxx.xb }</td>
                <th>�꼶</th>
                <td>${jbxx.nj }</td>
            </tr>
            <tr style="height: 45px;">
                <th>ѧԺ</th>
                <td>${jbxx.xymc }</td>
                <th>�༶</th>
                <td>${jbxx.bjmc }</td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="5">
                    <span>�ҽ���Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr style="height: 45px;">
                <th>�ó���Ŀ</th>
                <td colspan="4">
                        ${jjzgForm.sckmmcs }
                </td>
            </tr>
            <tr style="height: 45px;">
                <th>����꼶</th>
                <td>
                        ${jjzgForm.jjnjmc }
                </td>
                <th>��ϵ�绰</th>
                <td colspan="2">
                        ${jjzgForm.lxdh }
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="5">
                    <span>�ҽ̾���</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="5">
                    <table width="100%">
                        <thead>
                        <tr>
                            <td>
                                �ҽ̱��
                            </td>
                            <td>
                                �ҽ�ѧ��
                            </td>
                            <td>
                                ��ʼʱ��
                            </td>
                            <td>
                                ����ʱ��
                            </td>
                        </tr>
                        </thead>
                        <logic:empty name="jjjlList">
                            <tr>
                                <td colspan="5" style="text-align:center;">
                                    ����!
                                </td>
                            </tr>
                        </logic:empty>
                        <logic:notEmpty name="jjjlList">
                            <logic:iterate id="jjjj" name="jjjlList">
                                <tr>
                                    <td>${jjjj.xqid}</td>
                                    <td>[${jjjj.jjxkmc}]-[${jjjj.jjnjmc}]</td>
                                    <td>${jjjj.kssj}</td>
                                    <td>${jjjj.jssj}</td>
                                </tr>
                            </logic:iterate>
                        </logic:notEmpty>
                    </table>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="5">
                    <span>�ҽ�������Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr style="height: 45px;">
                <th>
                    �ҽ̲���
                </th>
                <td colspan="4">
                    ${jjxqForm.jjczmc}
                </td>
            </tr>
            <tr style="height: 45px;">
                <th>
                    ��������
                </th>
                <td colspan="4">
                    ${jjxqForm.sqly}
                </td>
            </tr>

            </tbody>
            <thead>
            <tr>
                <th colspan="5">
                    <span>�ҽ�Ͷ��������</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="5" id="shlccx">

                </td>
            </tr>

            </tbody>
            <thead>
            <tr>
                <th colspan="5">
                    <span>�����Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
            <tr>
                <th >
                    ��˽��
                </th>
                <td id="shjgSpan" colspan="4">

                </td>
            </tr>
            </tr>
            <tr>
                <th width="20%">
                    <font color="red">*&nbsp;</font> ������
                    <br />
                    <font color="red">(��200��)</font>
                </th>
                <td colspan="4">
                    <jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=jjgl&id=shyj" />
                    <textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height: 50px"></div>
    <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
        <tfoot>
        <tr>
            <td colspan="5">
                <div class="btn">
                    <button type="button" name="����"  onclick="jjczshSave();return false;">
                        �� ��
                    </button>
                    <button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
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
