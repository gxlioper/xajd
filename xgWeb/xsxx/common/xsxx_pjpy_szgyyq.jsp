<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<div class="formbox" id="pjpy" style="display:none">
	<table width="100%" class="dateline">
		<thead>
			<tr>
				<td>学年</td>
				<td>学期</td>
				<td>5S模块分</td>
				<td>语言表达分</td>
				<td>读书活动分</td>
				<td>IVT论坛分</td>
				<td>文体活动分</td>
				<td>组织能力分</td>
				<td>社会实践分</td>
				<td>综合素质分</td>
				<td>综合素质分排名</td>
			</tr>
		</thead>
		<tbody id="zhcp">
			<logic:iterate id="zhcp" name="zhcpList">
			<tr>
				<td>${zhcp.xn}</td>
				<td>${zhcp.xqmc}</td>
				<td>${zhcp.wsmkf}</td>
				<td>${zhcp.yybdf}</td>
				<td>${zhcp.dshdf}</td>
				<td>${zhcp.ivtlt}</td>
				<td>${zhcp.wthd}</td>
				<td>${zhcp.zznl}</td>
				<td>${zhcp.shsj}</td>
				<td>${zhcp.zhszf}</td>
				<td>${zhcp.zhszfpm}</td>
			</tr>
			</logic:iterate>
		</tbody>
	</table>
	<br></br>
</div>
