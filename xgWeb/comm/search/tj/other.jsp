<%@ page language="java" contentType="text/html; charset=GBK"%>
<logic:present name="rs" property="qt">
<thead>
	<tr>
		<th colspan="2">
			<span><a href="#" onclick="clickOther()">其他（注：点击展开）</a></span>
		</th>
	</tr>
</thead>
<tbody id="otherTb" style="display: none">	

	<!-- 学年 -->
	<logic:present name="rs" property="xn">
		<tr>
			<th width="10%">
				学年
			</th>
			<td>
				<logic:iterate name="xnList" id="xn">
					<a style="color: black;cursor:hand;" onclick="clickTj('xn','${xn.xn }')" id="tj_xn_${xn.xn }" name="tj_xn">
						${xn.xn }
					</a>&nbsp;&nbsp;
				</logic:iterate>	
			</td>
		</tr>
	</logic:present>
	<!-- 学年 end-->
	
	<!-- 学期 -->
	<logic:present name="rs" property="xq">
		<tr>
			<th width="10%">
				学期
			</th>
			<td>
				<logic:iterate name="xqList" id="xq">
					&nbsp;&nbsp;
					<a style="color: black;cursor:hand;" onclick="clickTj('xq','${xq.xqdm }')" id="tj_xq_${xq.xqdm }" name="tj_xq">
						${xq.xqmc }
					</a>
				</logic:iterate>	
			</td>
		</tr>
	</logic:present>
	<!-- 学期 end-->
	
	<!-- 年度 -->
	<logic:present name="rs" property="nd">
		<tr>
			<th width="10%">
				年度
			</th>
			<td>
				<logic:iterate name="ndList" id="nd">
					<a style="color: black;cursor:hand;" onclick="clickTj('nd','${nd.nd }')" id="tj_nd_${nd.nd }" name="tj_nd">
						${nd.nd }
					</a>&nbsp;&nbsp;
				</logic:iterate>	
			</td>
		</tr>
	</logic:present>
	<!-- 年度 end-->
</tbody>	
</logic:present>