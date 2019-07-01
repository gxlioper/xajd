<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_cjxx')">
	<tr><td colspan="4" style="cursor:hand"><span>成绩信息</span></td></tr>
</thead>
<tbody id="mk_cjxx" style="display: none">
	<tr>
	<td colspan="4">
		<div style="height: 150px;overflow:scroll;overflow-x:hidden" class="formbox">		
			<table class="dateline" width="100%">
				<logic:equal name="xmxx" value="nd" property="sqzq">
					<thead>
					<tr>
						<td>年度</td>
						<td>课程名称</td>
						<td>课程性质</td>
						<td>成绩</td>
						<td>学分</td>
						<td>绩点</td>
					</tr>
					</thead>
					<logic:present name="stuCjxx">
					<logic:iterate name="stuCjxx" id="cjxxMap">
						<tr>
						<td>${cjxxMap.nd }</td>
						<td>${cjxxMap.kcmc }</td>
						<td>${cjxxMap.kcxz }</td>
						<td>${cjxxMap.cj }</td>
						<td>${cjxxMap.xf }</td>
						<td>${cjxxMap.jd }</td>
						</tr>
					</logic:iterate>
					</logic:present>
				</logic:equal>
					
				<logic:notEqual name="xmxx" value="nd" property="sqzq">
					<thead>
						<tr>
						<td>学年</td>
						<td>学期</td>
						<td>课程名称</td>
						<td>课程性质</td>
						<td>成绩</td>
						<td>学分</td>
						<td>绩点</td>
						</tr>
					</thead>
					<logic:present name="stuCjxx">
					<logic:iterate name="stuCjxx" id="cjxxMap">
						<tr>
						<td>${cjxxMap.xn }</td>
						<td>${cjxxMap.xqmc }</td>
						<td>${cjxxMap.kcmc }</td>
						<td>${cjxxMap.kcxz }</td>
						<td>${cjxxMap.cj }</td>
						<td>${cjxxMap.xf }</td>
						<td>${cjxxMap.jd }</td>
						</tr>
					</logic:iterate>
					</logic:present>
				</logic:notEqual>
			</table>
		</div>
	</td>
	</tr>		
</tbody>
