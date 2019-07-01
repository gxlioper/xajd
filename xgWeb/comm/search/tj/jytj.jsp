<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- 性别 -->
<logic:equal name="tjMap" property="tj" value="xb">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<logic:iterate name="xbList" id="xb">
					<li>
						<a href="#" class=""
							onclick="clickTj('xb','${xb.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${xb.en }','${xb.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${xb.en }" name="tj_${tjMap.tj }">
							${xb.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- 性别 end-->

<!-- 年度 -->
<logic:equal name="tjMap" property="tj" value="nd">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<logic:iterate name="ndList" id="nd" indexId="list_num">
					<logic:notEqual name="list_num" value="0">
						<li>
							<a href="#" class=""
								onclick="clickTj('nd','${nd.nd }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${nd.nd }','${nd.nd }',this);return false;" 
								id="tj_${tjMap.tj }_${nd.nd }" name="tj_${tjMap.tj }">
								${nd.nd }
							</a>
						</li>
					</logic:notEqual>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- 年度 end-->

<!-- 学年 -->
<logic:equal name="tjMap" property="tj" value="xn">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<logic:iterate name="xnList" id="xn" indexId="list_num">
					<logic:notEqual name="list_num" value="0">
						<li>
							<a href="#" class=""
								onclick="clickTj('xn','${xn.xn }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${xn.xn }','${xn.xn }',this);return false;" 
								id="tj_${tjMap.tj }_${xn.xn }" name="tj_${tjMap.tj }">
								${xn.xn }
							</a>
						</li>
					</logic:notEqual>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- 学年 end-->

<!-- 学期 -->
<logic:equal name="tjMap" property="tj" value="xq">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<logic:iterate name="xqList" id="xq" indexId="list_num">
					<logic:notEqual name="list_num" value="0">
						<li>
							<a href="#" class=""
								onclick="clickTj('xq','${xq.xqdm }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${xq.xqdm }','${xq.xqmc }',this);return false;" 
								id="tj_${tjMap.tj }_${xq.xqdm }" name="tj_${tjMap.tj }">
								${xq.xqmc }
							</a>
						</li>
					</logic:notEqual>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- 学期 end-->