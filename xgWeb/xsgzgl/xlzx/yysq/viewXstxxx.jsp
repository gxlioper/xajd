<%@ page language="java" contentType="text/html; charset=GBK"%>
<tbody>
<tr>
    <th>���</th>
    <td >${xstxxx.hfmc}</td>
    <th>�Ƿ�����Ů</th>
    <td>${xstxxx.sfyznmc}</td>
</tr>
<tr>
    <th>������Ů</th>
    <td>${xstxxx.sfdsznmc}
        <logic:notEmpty name="xstxxx" property="zmr">
            <div style="float: right;margin-right: 40%;">����ˣ�${xstxxx.zmr}</div>
        </logic:notEmpty>
    </td>
    <th>��������</th>
    <td>${xstxxx.dzyx}</td>
</tr>
<tr>
    <th>����ְҵ</th>
    <td>${xstxxx.fqzy}</td>
    <th>����ѧ��</th>
    <td>${xstxxx.fxl}</td>
</tr>
<tr>
    <th>ĸ��ְҵ</th>
    <td>${xstxxx.mqzy}</td>
    <th>ĸ��ѧ��</th>
    <td>${xstxxx.mxl}</td>
</tr>
<tr>
    <th>��ͥ��ַ</th>
    <td>${xstxxx.jtdz}</td>
    <th>��Դ��</th>
    <td>${xstxxx.sydmc}</td>
</tr>
<tr>
    <th>��ѯĿ��</th>
    <td colspan="3">${xstxxx.jtdz}</td>
</tr>
</tbody>
