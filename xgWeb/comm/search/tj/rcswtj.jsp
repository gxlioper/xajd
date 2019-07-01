<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- 是否通知 -->
<logic:equal name="tjMap" property="tj" value="sftz">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<logic:iterate name="rcswSftzList" id="sftz">
					<li>
						<a href="#" class=""
							onclick="clickTj('sftz','${sftz.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${sftz.en }','${sftz.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${sftz.en }" name="tj_${tjMap.tj }">
							${sftz.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- 是否通知 end-->