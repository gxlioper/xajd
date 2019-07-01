<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<tbody>
<tr>
    <th>详细通讯地址</th>
    <td width="30%" colspan="3">
        <html:hidden  property="jtszdssxdm" styleId="jtszdssxdm"/>
        ${mkxxForm.jtdz}
        <script type="text/javascript" src="js/provicecitylocal.js"></script>
        <script type="text/javascript">
            //家庭地址省市县
            proviceCiyyLocalMain({type:"view",id:"jtszdssxdm",flag:"yxxdz"});
        </script>
    </td>
</tr>
<tr>
    <th>邮政编码</th>
    <td>
        ${mkxxForm.jtyb}
    </td>
    <th>家长手机号码</th>
    <td>
        ${mkxxForm.jtdh}
    </td>
</tr>
</tbody>