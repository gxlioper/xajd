<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- ������� -->
<logic:equal name="tjMap" property="tj" value="pycc">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="xsxxPyccList" id="pycc">
					<li>
						<a href="#" class=""
							onclick="clickTj('pycc','${pycc.dm }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${pycc.dm }','${pycc.mc }',this);return false;" 
							id="tj_${tjMap.tj }_${pycc.dm }" name="tj_${tjMap.tj }">
							${pycc.mc }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- ������� end-->

<!-- ѧ�� -->
<logic:equal name="tjMap" property="tj" value="xz">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="xsxxXzList" id="xz">
					<li>
						<a href="#" class=""
							onclick="clickTj('xz','${xz.dm }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${xz.dm }','${xz.mc }',this);return false;" 
							id="tj_${tjMap.tj }_${xz.dm }" name="tj_${tjMap.tj }">
							${xz.mc }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- ѧ�� end-->

<!-- ѧ�� -->
<logic:equal name="tjMap" property="tj" value="xj">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="xsxxXjList" id="xj">
					<li>
						<a href="#" class=""
							onclick="clickTj('xj','${xj.dm }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${xj.dm }','${xj.mc }',this);return false;" 
							id="tj_${tjMap.tj }_${xj.dm }" name="tj_${tjMap.tj }">
							${xj.mc }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- ѧ�� end-->

<!-- �Ƿ���У -->
<logic:equal name="tjMap" property="tj" value="sfzx">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="xsxxSfzxList" id="sfzx">
					<li>
						<a href="#" class=""
							onclick="clickTj('sfzx','${sfzx.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${sfzx.en }','${sfzx.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${sfzx.en }" name="tj_${tjMap.tj }">
							${sfzx.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- �Ƿ���У end-->

<!-- �Ƿ��ҵ -->
<logic:equal name="tjMap" property="tj" value="sfyby">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="sfList" id="sfyby">
					<li>
						<a href="#" class=""
							onclick="clickTj('sfyby','${sfyby.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${sfyby.en }','${sfyby.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${sfyby.en }" name="tj_${tjMap.tj }">
							${sfyby.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- �Ƿ��ҵ end-->

<!-- ���� -->
<logic:equal name="tjMap" property="tj" value="mz">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<% int mz_count=0; %>
				<logic:iterate name="xsxxMzList" id="mz">
					<li>
						<%if (mz_count<8) {%>
							<a href="#" class=""
								onclick="clickOtherTj('mz','${mz.dm }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${mz.dm }','${mz.mc }',this);return false;" 
								id="${tjMap.tj }_mc_xs_${mz.dm }" name="tj_${tjMap.tj }">
								${mz.mc }
							</a>
						<%}else{%>
							<a href="#" class="" style="display: none;"
								onclick="clickOtherTj('mz','${mz.dm }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${mz.dm }','${mz.mc }',this);return false;" 
								id="${tjMap.tj }_mc_yc_${mz.dm }" name="tj_${tjMap.tj }">
								${mz.mc }
							</a>
						<%} %>
						<%mz_count++; %>
					</li>
				</logic:iterate>	
			</ul>
			<a href="#" class="more_down" onclick="showHiddenOther(this,'${tjMap.tj}','more_up','more_down','����','����');return false">����</a>
		</dd>
	</dl>
</logic:equal>
<!-- ���� end-->

<!-- ����״̬ -->
<logic:equal name="tjMap" property="tj" value="hk">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="xsxxHkztList" id="hk">
					<li>
						<a href="#" class=""
							onclick="clickTj('hk','${hk.dm }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${hk.dm }','${hk.mc }',this);return false;" 
							id="tj_${tjMap.tj }_${hk.dm }" name="tj_${tjMap.tj }">
							${hk.mc }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- ����״̬ end-->

<!-- �Ƿ�ɷ� -->
<logic:equal name="tjMap" property="tj" value="sfjf">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="xsxxSfjfList" id="sfjf">
					<li>
						<a href="#" class=""
							onclick="clickTj('sfjf','${sfjf.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${sfjf.en }','${sfjf.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${sfjf.en }" name="tj_${tjMap.tj }">
							${sfjf.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- �Ƿ�ɷ� end-->

<!-- ת����� -->
<logic:equal name="tjMap" property="tj" value="zdlb">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="xsxxZdlbList" id="zdlb">
					<li>
						<a href="#" class=""
							onclick="clickTj('zdlb','${zdlb.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${zdlb.en }','${zdlb.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${zdlb.en }" name="tj_${tjMap.tj }">
							${zdlb.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- ת����� end-->

<!-- �춯��� -->
<logic:equal name="tjMap" property="tj" value="ydlb">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="xsxxYdlbList" id="ydlb">
					<li>
						<a href="#" class=""
							onclick="clickTj('ydlb','${ydlb.dm }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${ydlb.dm }','${ydlb.mc }',this);return false;" 
							id="tj_${tjMap.tj }_${ydlb.dm }" name="tj_${tjMap.tj }">
							${ydlb.mc }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- �춯��� end-->

<!-- ʡ�� -->
<logic:equal name="tjMap" property="tj" value="prov">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<% int prov_count=0; %>
				<logic:iterate name="xsxxProvList" id="prov">
					<li>
						<%if (prov_count<8) {%>
							<a href="#" class=""
								onclick="clickOtherTj('prov','${prov.dm }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${prov.dm }','${prov.mc }',this);return false;" 
								id="${tjMap.tj }_mc_xs_${prov.dm }" name="tj_${tjMap.tj }">
								${prov.mc }
							</a>
						<%}else{%>
							<a href="#" class="" style="display: none;"
								onclick="clickOtherTj('prov','${prov.dm }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${prov.dm }','${prov.mc }',this);return false;" 
								id="${tjMap.tj }_mc_yc_${prov.dm }" name="tj_${tjMap.tj }">
								${prov.mc }
							</a>
						<%} %>
						<%prov_count++; %>
					</li>
				</logic:iterate>	
			</ul>
			<a href="#" class="more_down" onclick="showHiddenOther(this,'${tjMap.tj}','more_up','more_down','����','����');return false">����</a>
		</dd>
	</dl>
</logic:equal>
<!-- ʡ�� end-->