<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<tbody>
    <tr>
        <th>��ϸͨѶ��ַ</th>
        <td width="30%" colspan="3">
            <html:hidden  property="jtszdssxdm" styleId="jtszdssxdm"/>
            <html:text property="jtdz" styleId="jtdz"  onblur="checkLen(this,50);"/>
            <script type="text/javascript" src="js/provicecitylocal.js"></script>
            <script type="text/javascript">
                //��ͥ��ַʡ����
                proviceCiyyLocalMain({type:"edit",id:"jtszdssxdm",flag:"yxxdz"});
            </script>
        </td>
    </tr>
    <tr>
        <th>��������</th>
        <td>
            <html:text property="jtyb" styleId="jtyb" onblur="checkLen(this,6);"/>
        </td>
        <th>�ҳ��ֻ�����</th>
        <td>
            <html:text property="jtdh" styleId="jtdh" maxlength="11" style="" onkeyup="checkInputData(this);"/>
        </td>
    </tr>
</tbody>