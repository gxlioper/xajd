<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_cjxx')">
	<tr><td colspan="4" style="cursor:hand"><span>�ɼ���Ϣ</span></td></tr>
</thead>
<tbody id="mk_cjxx" style="display: none">
	<tr>
	<td colspan="4">
		<div style="height: 150px;overflow:scroll;overflow-x:hidden" class="formbox">		
			<table class="dateline" width="100%">
				<logic:equal name="wdpjXssqInfo" value="nd"  property="xmInfo.sqzq">
					<thead>
					<tr>
						<td>���</td>
						<td>�γ�����</td>
						<td>�γ�����</td>
						<td>�ɼ�</td>
						<td>ѧ��</td>
						<td>����</td>
					</tr>
					</thead>
					<logic:present name="wdpjXssqInfo" property="xscjInfo">
					<logic:iterate name="wdpjXssqInfo" property="xscjInfo" id="cjxxMap">
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
					
				<logic:notEqual name="wdpjXssqInfo"  value="nd" property="xmInfo.sqzq">
					<thead>
						<tr>
						<td>ѧ��</td>
						<td>ѧ��</td>
						<td>�γ�����</td>
						<td>�γ�����</td>
						<td>�ɼ�</td>
						<td>ѧ��</td>
						<td>����</td>
						</tr>
					</thead>
					<logic:present name="wdpjXssqInfo" property="xscjInfo">
					<logic:iterate name="wdpjXssqInfo" property="xscjInfo" id="cjxxMap">
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
