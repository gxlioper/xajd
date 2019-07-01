<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- 政治面貌 -->
<logic:equal name="tjMap" property="tj" value="zm">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<% int zm_count=0; %>
				<logic:iterate name="zzmmList" id="zm">
					<li>
						<%if (zm_count<3) {%>
							<a href="#" class=""
								onclick="clickOtherTj('zm','${zm.dm }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${zm.dm }','${zm.mc }',this);return false;" 
								id="${tjMap.tj }_mc_xs_${zm.dm }" name="tj_${tjMap.tj }">
								${zm.mc }
							</a>
						<%}else{%>
							<a href="#" class="" style="display: none;"
								onclick="clickOtherTj('zm','${zm.dm }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${zm.dm }','${zm.mc }',this);return false;" 
								id="${tjMap.tj }_mc_yc_${zm.dm }" name="tj_${tjMap.tj }">
								${zm.mc }
							</a>
						<%} %>
						<%zm_count++; %>
					</li>
				</logic:iterate>	
			</ul>
			<a href="#" class="more_down" onclick="showHiddenOther(this,'${tjMap.tj}','more_up','more_down','收起','更多');return false">更多</a>
		</dd>
	</dl>
</logic:equal>
<!-- 政治面貌 end-->

<!-- 是否 -->
<logic:equal name="tjMap" property="tj" value="sf">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<logic:iterate name="sfList" id="sf">
					<li>
						<a href="#" class=""
							onclick="clickTj('sf','${sf.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${sf.en }','${sf.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${sf.en }" name="tj_${tjMap.tj }">
							${sf.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- 是否 end-->

<!-- 审核状态 -->
<logic:equal name="tjMap" property="tj" value="shzt">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<logic:iterate name="shztList" id="shzt">
					<li>
						<a href="#" class=""
							onclick="clickTj('shzt','${shzt.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${shzt.en }','${shzt.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${shzt.en }" name="tj_${tjMap.tj }">
							${shzt.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- 审核状态 end-->

<!-- 审核结果 -->
<logic:equal name="tjMap" property="tj" value="shjg">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<logic:iterate name="shjgList" id="shjg">
					<li>
						<a href="#" class=""
							onclick="clickTj('shjg','${shjg.en }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${shjg.en }','${shjg.cn }',this);return false;" 
							id="tj_${tjMap.tj }_${shjg.en }" name="tj_${tjMap.tj }">
							${shjg.cn }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- 审核结果 end-->

<!-- 部门 -->
<logic:equal name="tjMap" property="tj" value="bm">
	<dl>
		<dt>${tjMap.mc }：</dt>
			<dd>
				<ul id="${tjMap.tj }_ul">
					<% int bm_count=0; %>
					<% int bm_py_num = 0; %>
					<logic:iterate name="commBmList" id="bm">	
						<li>
							<logic:notEmpty name="bm" property="bmList">
								<!-- 部门拼音 -->
								<%if (bm_count<8) {%>
									<h5 id="bm_py_xs_<%=bm_py_num %>"><img src="<%=stylePath%>images/num_${bm.py }.gif" /></h5>
								<%}else{ %>
									<h5 id="bm_py_yc_<%=bm_py_num %>" style="display: none"><img src="<%=stylePath%>images/num_${bm.py }.gif" /></h5>
								<%} %>
								<!-- 具体部门 -->
								<logic:iterate name="bm" property="bmList" id="newBm">
									<% bm_py_num++; %>
									<%if (bm_count<8) {%>
										<a href="#" class="" name="a_bm_mc" id="bm_mc_xs_${newBm.bmdm }" onclick="clickOtherTj('bm','${newBm.bmdm }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${newBm.bmdm }','${newBm.bmmc }',this);return false;" title="${newBm.bmmc }">${newBm.bmmc }</a>
									<%}else{%>
										<a href="#" class="" name="a_bm_mc" id="bm_mc_yc_${newBm.bmdm }" style="display: none;" onclick="clickOtherTj('bm','${newBm.bmdm }');creatClickedTj('${tjMap.tj }','${tjMap.mc }','${newBm.bmdm }','${newBm.bmmc }',this);return false;" title="${newBm.bmmc }">${newBm.bmmc }</a>
										<input type="hidden" name="bm_hidv_yc" id="bm_hidv_yc_${newBm.bmdm }" value="${newBm.bmdm }"/>
									<%} %>	
									<%bm_count++; %>
								</logic:iterate>
							</logic:notEmpty>
						</li>
					</logic:iterate>
					<input type="hidden" id="bm_py_num" value="<%=bm_py_num %>"/>
					<input type="hidden" id="bm_num" value="<%=bm_count %>"/>
				</ul>
				<%if (bm_count>7) {%>
				<a href="#" class="more_down" onclick="showHiddenBm(this,'${tjMap.tj}','more_up','more_down','收起','更多');return false">更多</a>
				<%}%>
			</dd>
		</dt>
	</dl>
</logic:equal>
<!-- 部门 end-->