<%@ page language="java" contentType="text/html; charset=GBK"%>
<tbody>
<tr>
    <th>婚否</th>
    <td >${xstxxx.hfmc}</td>
    <th>是否有子女</th>
    <td>${xstxxx.sfyznmc}</td>
</tr>
<tr>
    <th>独生子女</th>
    <td>${xstxxx.sfdsznmc}
        <logic:notEmpty name="xstxxx" property="zmr">
            <div style="float: right;margin-right: 40%;">姊妹人：${xstxxx.zmr}</div>
        </logic:notEmpty>
    </td>
    <th>电子邮箱</th>
    <td>${xstxxx.dzyx}</td>
</tr>
<tr>
    <th>父亲职业</th>
    <td>${xstxxx.fqzy}</td>
    <th>父亲学历</th>
    <td>${xstxxx.fxl}</td>
</tr>
<tr>
    <th>母亲职业</th>
    <td>${xstxxx.mqzy}</td>
    <th>母亲学历</th>
    <td>${xstxxx.mxl}</td>
</tr>
<tr>
    <th>家庭地址</th>
    <td>${xstxxx.jtdz}</td>
    <th>生源地</th>
    <td>${xstxxx.sydmc}</td>
</tr>
<tr>
    <th>咨询目的</th>
    <td colspan="3">${xstxxx.jtdz}</td>
</tr>
</tbody>
