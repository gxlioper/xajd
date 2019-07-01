<%@ page language="java" contentType="text/html; charset=GBK"%>

<!-- 校区 -->
<logic:equal name="tjMap" property="tj" value="xqdm">
	<logic:notEqual name="czxq" value="否">
	<dl>
		<dt>${tjMap.mc }：</dt>
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
<!-- 校区 end-->

<!-- 园区 -->
<logic:equal name="tjMap" property="tj" value="yqdm">
	<logic:notEqual name="czyq" value="否">
	<dl>
		<dt>${tjMap.mc }：</dt>
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

<!-- 园区 end-->

<!-- 楼栋 -->
<logic:equal name="tjMap" property="tj" value="lddm">
	<dl>
		<dt>${tjMap.mc }：</dt>
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
			<a href="#" id="ld_more" class="more_down" onclick="showHiddenBm(this,'${tjMap.tj}','more_up','more_down','收起','更多');return false">更多</a>
			<%}%>
		</dd>
	</dl>
</logic:equal>
<!-- 楼栋 end-->

<!-- 层数 -->
<logic:equal name="tjMap" property="tj" value="cs">
	<dl>
		<dt>${tjMap.mc }：</dt>
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
<!-- 层数 end-->

<!-- 寝室 -->
<logic:equal name="tjMap" property="tj" value="qsh">
	<dl id="qsh_dl" style="display:none">
		<dt>${tjMap.mc }：</dt>
			<dd>
				<ul id="${tjMap.tj }_ul">
					<% int qsh_count=0; %>
					<% int qsh_py_num = 0; %>
					<logic:iterate name="gyglQshList" id="qs" indexId="qsh_num">	
						<li>
							<logic:notEmpty name="qs" property="qsList">
								<!-- 寝室拼音 -->
								<%if (qsh_count<12) {%>
									<h5 id="qsh_py_xs_<%=qsh_py_num %>"><img src="<%=stylePath%>images/num_${qs.py }.gif" /></h5>
								<%}else{ %>
									<h5 id="qsh_py_yc_<%=qsh_py_num %>" style="display: none"><img src="<%=stylePath%>images/num_${qs.py }.gif" /></h5>
								<%} %>
								<!-- 具体寝室 -->
								<logic:iterate name="qs" property="qsList" id="newQs">
									<% qsh_py_num++; %>
									<%if (qsh_count<12) {%>
										<!-- 寝室 -->
										<logic:notEqual name="newQs" property="qshmc" value="其他">
											<a href="#" class="" name="a_qsh_mc" id="qsh_mc_xs_${newQs.qshdm }" onclick="clickQsh(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${newQs.qshdm }','${newQs.qshmc }',this);return false;" title="${newQs.qshmc }">${newQs.qshmc }</a>
										</logic:notEqual>
										<!-- 其他 -->
										<logic:equal name="newQs" property="qshmc" value="其他">
											<a href="#" class="moreValue_click" id="qsh_qt_${newQs.qshdm }" onclick="clickQshQt('${qs.py }');return false;">更多</a>
										</logic:equal>
									<%}else{%>
										<!-- 寝室 -->
										<logic:notEqual name="newQs" property="qshmc" value="其他">
											<a href="#" class="" name="a_qsh_mc" id="qsh_mc_yc_${newQs.qshdm }" style="display: none;" onclick="clickQsh(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${newQs.qshdm }','${newQs.qshmc }',this);return false;" title="${newQs.qshmc }">${newQs.qshmc }</a>
											<input type="hidden" name="qsh_hidv_yc" id="qsh_hidv_yc_${newQs.qshdm }" value="${newQs.qshdm }"/>
										</logic:notEqual>
										<!-- 其他 -->
										<logic:equal name="newQs" property="qshmc" value="其他">
											<a href="#" class="moreValue_click" style="display: none;" id="qsh_qt_${newQs.qshdm }" onclick="clickQshQt('${qs.py }');return false;">更多</a>
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
					<a href="#" id="qsh_more" class="more_down" onclick="showHiddenBm(this,'${tjMap.tj}','more_up','more_down','收起','更多');return false">更多</a>
				<%}%>
			</dd>
		</dt>
	</dl>
</logic:equal>
<!-- 寝室 end-->

<!-- 可否混住 -->
<logic:equal name="tjMap" property="tj" value="kfhz">
	<dl>
		<dt>${tjMap.mc }：</dt>
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
<!-- 可否混住 end-->

<!-- 入住状态 -->
<logic:equal name="tjMap" property="tj" value="rzzt">
	<dl>
		<dt>${tjMap.mc }：</dt>
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
<!-- 入住状态 end-->

<!-- 床位分配状态 -->
<logic:equal name="tjMap" property="tj" value="cwfp">
	<dl>
		<dt>${tjMap.mc }：</dt>
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
<!-- 床位分配状态 end-->

<!-- 性别限定 -->
<logic:equal name="tjMap" property="tj" value="xbxd">
	<dl>
		<dt>${tjMap.mc }：</dt>
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
<!-- 性别限定 end-->

<!-- 入住状态 -->
<logic:equal name="tjMap" property="tj" value="rzqk">
	<dl>
		<dt>${tjMap.mc }：</dt>
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
<!-- 入住状态 end-->