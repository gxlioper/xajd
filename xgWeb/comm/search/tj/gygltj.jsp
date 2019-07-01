<%@ page language="java" contentType="text/html; charset=GBK"%>

<!-- У�� -->
<logic:equal name="tjMap" property="tj" value="xqdm">
	<logic:notEqual name="czxq" value="��">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="gyglXqList" id="xqdm">
					<li>
						<a href="#" class=""
							onclick="clickXq(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${xqdm.dm }','${xqdm.mc }',this);return false;" 
							id="tj_${tjMap.tj }_${xqdm.dm }" name="tj_${tjMap.tj }">
							${xqdm.mc }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
	</logic:notEqual>
</logic:equal>
<!-- У�� end-->

<!-- ԰�� -->
<logic:equal name="tjMap" property="tj" value="yqdm">
	<logic:notEqual name="czyq" value="��">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul id="${tjMap.tj }_ul">
				<logic:iterate name="gyglYqList" id="yqdm">
					<li>
						<a href="#" class=""
							onclick="clickYq(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${yqdm.dm }','${yqdm.mc }',this);return false;" 
							id="tj_${tjMap.tj }_${yqdm.dm }" name="tj_${tjMap.tj }">
							${yqdm.mc }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
	</logic:notEqual>
</logic:equal>

<!-- ԰�� end-->

<!-- ¥�� -->
<logic:equal name="tjMap" property="tj" value="lddm">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul id="${tjMap.tj }_ul">
				<% int lddm_count=0; %>
				<logic:iterate name="gyglLdList" id="lddm">
					<li>
						<%if (lddm_count<8) {%>
							<a href="#" class=""
								onclick="clickLd(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${lddm.dm }','${lddm.mc }',this);return false;" 
								id="${tjMap.tj }_mc_xs_${lddm.dm }" name="a_lddm_mc">
								${lddm.mc }
							</a>
						<%}else{%>
							<a href="#" class="" style="display: none;"
								onclick="clickLd(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${lddm.dm }','${lddm.mc }',this);return false;" 
								id="${tjMap.tj }_mc_yc_${lddm.dm }" name="a_lddm_mc">
								${lddm.mc }
							</a>
						<%} %>
						<%lddm_count++; %>
					</li>
				</logic:iterate>
			</ul>
			<%if (lddm_count>7) {%>
			<a href="#" id="ld_more" class="more_down" onclick="showHiddenBm(this,'${tjMap.tj}','more_up','more_down','����','����');return false">����</a>
			<%}%>
		</dd>
	</dl>
</logic:equal>
<!-- ¥�� end-->

<!-- ���� -->
<logic:equal name="tjMap" property="tj" value="cs">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul id="${tjMap.tj }_ul">
				<logic:iterate name="gyglCsList" id="cs">
					<li>
						<a href="#" class=""
							onclick="clickCs(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${cs.dm }','${cs.mc }',this);return false;" 
							id="tj_${tjMap.tj }_${cs.dm }" name="tj_${tjMap.tj }">
							${cs.mc }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- ���� end-->

<!-- ���� -->
<logic:equal name="tjMap" property="tj" value="qsh">
	<dl id="qsh_dl" style="display:none">
		<dt>${tjMap.mc }��</dt>
			<dd>
				<ul id="${tjMap.tj }_ul">
					<% int qsh_count=0; %>
					<% int qsh_py_num = 0; %>
					<logic:iterate name="gyglQshList" id="qs" indexId="qsh_num">	
						<li>
							<logic:notEmpty name="qs" property="qsList">
								<!-- ����ƴ�� -->
								<%if (qsh_count<12) {%>
									<h5 id="qsh_py_xs_<%=qsh_py_num %>"><img src="<%=stylePath%>images/num_${qs.py }.gif" /></h5>
								<%}else{ %>
									<h5 id="qsh_py_yc_<%=qsh_py_num %>" style="display: none"><img src="<%=stylePath%>images/num_${qs.py }.gif" /></h5>
								<%} %>
								<!-- �������� -->
								<logic:iterate name="qs" property="qsList" id="newQs">
									<% qsh_py_num++; %>
									<%if (qsh_count<12) {%>
										<!-- ���� -->
										<logic:notEqual name="newQs" property="qshmc" value="����">
											<a href="#" class="" name="a_qsh_mc" id="qsh_mc_xs_${newQs.qshdm }" onclick="clickQsh(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${newQs.qshdm }','${newQs.qshmc }',this);return false;" title="${newQs.qshmc }">${newQs.qshmc }</a>
										</logic:notEqual>
										<!-- ���� -->
										<logic:equal name="newQs" property="qshmc" value="����">
											<a href="#" class="moreValue_click" id="qsh_qt_${newQs.qshdm }" onclick="clickQshQt('${qs.py }');return false;">����</a>
										</logic:equal>
									<%}else{%>
										<!-- ���� -->
										<logic:notEqual name="newQs" property="qshmc" value="����">
											<a href="#" class="" name="a_qsh_mc" id="qsh_mc_yc_${newQs.qshdm }" style="display: none;" onclick="clickQsh(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${newQs.qshdm }','${newQs.qshmc }',this);return false;" title="${newQs.qshmc }">${newQs.qshmc }</a>
											<input type="hidden" name="qsh_hidv_yc" id="qsh_hidv_yc_${newQs.qshdm }" value="${newQs.qshdm }"/>
										</logic:notEqual>
										<!-- ���� -->
										<logic:equal name="newQs" property="qshmc" value="����">
											<a href="#" class="moreValue_click" style="display: none;" id="qsh_qt_${newQs.qshdm }" onclick="clickQshQt('${qs.py }');return false;">����</a>
											<input type="hidden" name="qsh_hidv_qt" id="qsh_hidv_qt_${newQs.qshdm }" value="${newQs.qshdm }"/>
										</logic:equal>
									<%} %>	
									<%qsh_count++; %>
								</logic:iterate>
							</logic:notEmpty>
						</li>
					</logic:iterate>
					<input type="hidden" id="qsh_py_num" value="<%=qsh_py_num %>"/>
					<input type="hidden" id="qsh_num" value="<%=qsh_count %>"/>
				</ul>
				<%if (qsh_count>11) {%>
					<a href="#" id="qsh_more" class="more_down" onclick="showHiddenBm(this,'${tjMap.tj}','more_up','more_down','����','����');return false">����</a>
				<%}%>
			</dd>
		</dt>
	</dl>
</logic:equal>
<!-- ���� end-->

<!-- �ɷ��ס -->
<logic:equal name="tjMap" property="tj" value="kfhz">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="gyglKfhzList" id="kfhz">
					<li>
						<a href="#" class=""
							onclick="clickTj('kfhz','${kfhz.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${kfhz.en }','${kfhz.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${kfhz.en }" name="tj_${tjMap.tj }">
							${kfhz.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- �ɷ��ס end-->

<!-- ��ס״̬ -->
<logic:equal name="tjMap" property="tj" value="rzzt">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="gyglRzztList" id="rzzt">
					<li>
						<a href="#" class=""
							onclick="clickTj('rzzt','${rzzt.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${rzzt.en }','${rzzt.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${rzzt.en }" name="tj_${tjMap.tj }">
							${rzzt.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- ��ס״̬ end-->

<!-- ��λ����״̬ -->
<logic:equal name="tjMap" property="tj" value="cwfp">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="gyglCwfpList" id="cwfp">
					<li>
						<a href="#" class=""
							onclick="clickTj('cwfp','${cwfp.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${cwfp.en }','${cwfp.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${cwfp.en }" name="tj_${tjMap.tj }">
							${cwfp.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- ��λ����״̬ end-->

<!-- �Ա��޶� -->
<logic:equal name="tjMap" property="tj" value="xbxd">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="gyglXbxdList" id="xbxd">
					<li>
						<a href="#" class=""
							onclick="clickTj('xbxd','${xbxd.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${xbxd.en }','${xbxd.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${xbxd.en }" name="tj_${tjMap.tj }">
							${xbxd.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- �Ա��޶� end-->

<!-- ��ס״̬ -->
<logic:equal name="tjMap" property="tj" value="rzqk">
	<dl>
		<dt>${tjMap.mc }��</dt>
		<dd>
			<ul>
				<logic:iterate name="gyglRzqkList" id="rzqk">
					<li>
						<a href="#" class=""
							onclick="clickTj('rzqk','${rzqk.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${rzqk.en }','${rzqk.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${rzqk.en }" name="tj_${tjMap.tj }">
							${rzqk.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- ��ס״̬ end-->