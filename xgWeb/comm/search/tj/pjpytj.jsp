<%@ page language="java" contentType="text/html; charset=GBK"%>

<!-- �Ƿ����� -->
<logic:equal name="tjMap" property="tj" value="sfpf">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="pjpySfpfList" id="sfpf">
					<li>
						<a href="#" class=""
							onclick="clickTj('sfpf','${sfpf.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${sfpf.en }','${sfpf.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${sfpf.en }" name="tj_${tjMap.tj }">
							${sfpf.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- �Ƿ����� end-->

<!-- �Ƿ�ȷ�� -->
<logic:equal name="tjMap" property="tj" value="sfqr">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="pjpySfqrList" id="sfqr">
					<li>
						<a href="#" class=""
							onclick="clickTj('sfqr','${sfqr.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${sfqr.en }','${sfqr.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${sfqr.en }" name="tj_${tjMap.tj }">
							${sfqr.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- �Ƿ�ȷ�� end-->

<!-- �Ƿ���� -->
<logic:equal name="tjMap" property="tj" value="sfsh">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="sfList" id="sfsh">
					<li>
						<a href="#" class=""
							onclick="clickTj('sfsh','${sfsh.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${sfsh.en }','${sfsh.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${sfsh.en }" name="tj_${tjMap.tj }">
							${sfsh.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- �Ƿ���� end-->

<!-- �Ƿ��ύ -->
<logic:equal name="tjMap" property="tj" value="sftj">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="sfList" id="sftj">
					<li>
						<a href="#" class=""
							onclick="clickTj('sftj','${sftj.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${sftj.en }','${sftj.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${sftj.en }" name="tj_${tjMap.tj }">
							${sftj.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- �Ƿ��ύ end-->