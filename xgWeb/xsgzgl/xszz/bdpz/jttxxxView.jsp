<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<tbody>
<tr>
    <th>��ϸͨѶ��ַ</th>
    <td width="30%" colspan="3">
        <html:hidden  property="jtszdssxdm" styleId="jtszdssxdm"/>
        ${mkxxForm.jtdz}
        <script type="text/javascript" src="js/provicecitylocal.js"></script>
        <script type="text/javascript">
            //��ͥ��ַʡ����
            proviceCiyyLocalMain({type:"view",id:"jtszdssxdm",flag:"yxxdz"});
        </script>
    </td>
</tr>
<tr>
    <th>��������</th>
    <td>
        ${mkxxForm.jtyb}
    </td>
    <th>�ҳ��ֻ�����</th>
    <td>
        ${mkxxForm.jtdh}
    </td>
</tr>
</tbody>