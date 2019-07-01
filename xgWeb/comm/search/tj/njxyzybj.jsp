<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- 年级 -->
<logic:equal name="tjMap" property="tj" value="nj">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<logic:iterate name="njTjList" id="nj">
					<li>
						<a href="#" class=""
							onclick="clickNj(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${nj.nj }','${nj.nj }',this);return false;" 
							id="tj_${tjMap.tj }_${nj.nj }" name="tj_${tjMap.tj }">
							${nj.nj }
						</a>
					</li>
				</logic:iterate>	
			</ul>
		</dd>
	</dl>
</logic:equal>
<!-- 年级 end-->

<!-- 学院 -->
<logic:equal name="tjMap" property="tj" value="xy">
	<dl>
		<dt>${tjMap.mc }：</dt>
			<dd>
				<ul id="${tjMap.tj }_ul">
					<% int xy_count=0; %>
					<% int xy_py_num = 0; %>
					<logic:iterate name="xyTjList" id="xy">	
						<li>
							<logic:notEmpty name="xy" property="xyList">
								<!-- 学院拼音 -->
								<%if (xy_count<8) {%>
									<h5 id="xy_py_xs_<%=xy_py_num %>"><img src="<%=stylePath%>images/num_${xy.py }.gif" /></h5>
								<%}else{ %>
									<h5 id="xy_py_yc_<%=xy_py_num %>" style="display: none"><img src="<%=stylePath%>images/num_${xy.py }.gif" /></h5>
								<%} %>
								<!-- 具体学院 -->
								<logic:iterate name="xy" property="xyList" id="newXy">
									<% xy_py_num++; %>
									<%if (xy_count<8) {%>
										<a href="#" class="" name="a_xy_mc" id="xy_mc_xs_${newXy.xydm }" onclick="clickXy(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${newXy.xydm }','${newXy.xymc }',this);return false;" title="${newXy.xymc }">${newXy.xymc }</a>
									<%}else{%>
										<a href="#" class="" name="a_xy_mc" id="xy_mc_yc_${newXy.xydm }" style="display: none;" onclick="clickXy(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${newXy.xydm }','${newXy.xymc }',this);return false;" title="${newXy.xymc }">${newXy.xymc }</a>
										<input type="hidden" name="xy_hidv_yc" id="xy_hidv_yc_${newXy.xydm }" value="${newXy.xydm }"/>
									<%} %>	
									<%xy_count++; %>
								</logic:iterate>
							</logic:notEmpty>
						</li>
					</logic:iterate>
					<input type="hidden" id="xy_py_num" value="<%=xy_py_num %>"/>
					<input type="hidden" id="xy_num" value="<%=xy_count %>"/>
				</ul>
				<%if (xy_count>7) {%>
				<a href="#" class="more_down" onclick="showHiddenBm(this,'${tjMap.tj}','more_up','more_down','收起','更多');return false">更多</a>
				<%}%>
			</dd>
		</dt>
	</dl>
</logic:equal>
<!-- 学院 end-->

<!-- 专业 -->
<logic:equal name="tjMap" property="tj" value="zy">
	<dl>
		<dt>${tjMap.mc }：</dt>
			<dd>
				<ul id="${tjMap.tj }_ul">
					<% int zy_count=0; %>
					<% int zy_py_num = 0; %>
					<logic:iterate name="zyTjList" id="zy" indexId="zy_num">	
						<li>
							<logic:notEmpty name="zy" property="zyList">
								<!-- 专业拼音 -->
								<%if (zy_count<12) {%>
									<h5 id="zy_py_xs_<%=zy_py_num %>"><img src="<%=stylePath%>images/num_${zy.py }.gif" /></h5>
								<%}else{ %>
									<h5 id="zy_py_yc_<%=zy_py_num %>" style="display: none"><img src="<%=stylePath%>images/num_${zy.py }.gif" /></h5>
								<%} %>
								<!-- 具体专业 -->
								<logic:iterate name="zy" property="zyList" id="newZy">
								
									<% zy_py_num++; %>
									
									<%if (zy_count<12) {%>
										<!-- 专业 -->
										<logic:notEqual name="newZy" property="zymc" value="其他">
											<a href="#" class="" name="a_zy_mc" id="zy_mc_xs_${newZy.zydm }" onclick="clickZy(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${newZy.zydm }','${newZy.zymc }',this);return false;" title="${newZy.zymc }">${newZy.zymc }</a>
										</logic:notEqual>
										<!-- 其他 -->
										<logic:equal name="newZy" property="zymc" value="其他">
											<a href="#" class="moreValue_click" id="zy_qt_${newZy.zydm }" onclick="clickZyQt('${zy.py }');return false;">更多</a>
										</logic:equal>
									<%}else{%>
										<!-- 专业 -->
										<logic:notEqual name="newZy" property="zymc" value="其他">
											<a href="#" class="" name="a_zy_mc" id="zy_mc_yc_${newZy.zydm }" style="display: none;" onclick="clickZy(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${newZy.zydm }','${newZy.zymc }',this);return false;" title="${newZy.zymc }">${newZy.zymc }</a>
											<input type="hidden" name="zy_hidv_yc" id="zy_hidv_yc_${newZy.zydm }" value="${newZy.zydm }"/>
										</logic:notEqual>
										<!-- 其他 -->
										<logic:equal name="newZy" property="zymc" value="其他">
											<a href="#" class="moreValue_click" style="display: none;" id="zy_qt_${newZy.zydm }" onclick="clickZyQt('${zy.py }');return false;">更多</a>
											<input type="hidden" name="zy_hidv_qt" id="zy_hidv_qt_${newZy.zydm }" value="${newZy.zydm }"/>
										</logic:equal>
									<%} %>	
									<%zy_count++; %>
								</logic:iterate>
							</logic:notEmpty>
						</li>
					</logic:iterate>
					<input type="hidden" id="zy_py_num" value="<%=zy_py_num %>"/>
					<input type="hidden" id="zy_num" value="<%=zy_count %>"/>
				</ul>
				<%if (zy_count>11) {%>
				<a href="#" id="zy_more" class="more_down" onclick="showHiddenBm(this,'${tjMap.tj}','more_up','more_down','收起','更多');return false">更多</a>
				<%}%>
			</dd>
		</dt>
	</dl>
</logic:equal>
<!-- 专业 end-->

<!-- 班级 -->
<logic:equal name="tjMap" property="tj" value="bj">
	<dl>
		<dt>${tjMap.mc }：</dt>
			<dd>
				<ul id="${tjMap.tj }_ul">
					<% int bj_count=0; %>
					<% int bj_py_num = 0; %>
					<logic:iterate name="bjTjList" id="bj" indexId="bj_num">	
						<li>
							<logic:notEmpty name="bj" property="bjList">
								<!-- 班级拼音 -->
								<%if (bj_count<12) {%>
									<h5 id="bj_py_xs_<%=bj_py_num %>"><img src="<%=stylePath%>images/num_${bj.py }.gif" /></h5>
								<%}else{ %>
									<h5 id="bj_py_yc_<%=bj_py_num %>" style="display: none"><img src="<%=stylePath%>images/num_${bj.py }.gif" /></h5>
								<%} %>
								<!-- 具体班级 -->
								<logic:iterate name="bj" property="bjList" id="newBj">
								
									<% bj_py_num++; %>
									
									<%if (bj_count<12) {%>
										<!-- 班级 -->
										<logic:notEqual name="newBj" property="bjmc" value="其他">
											<a href="#" class="" name="a_bj_mc" id="bj_mc_xs_${newBj.bjdm }" onclick="clickBj(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${newBj.bjdm }','${newBj.bjmc }',this);return false;" title="${newBj.bjmc }">${newBj.bjmc }</a>
										</logic:notEqual>
										<!-- 其他 -->
										<logic:equal name="newBj" property="bjmc" value="其他">
											<a href="#" class="moreValue_click" id="bj_qt_${newBj.bjdm }" onclick="clickBjQt('${bj.py }');return false;">更多</a>
										</logic:equal>
									<%}else{%>
										<!-- 班级 -->
										<logic:notEqual name="newBj" property="bjmc" value="其他">
											<a href="#" class="" name="a_bj_mc" id="bj_mc_yc_${newBj.bjdm }" style="display: none;" onclick="clickBj(this);creatClickedTj('${tjMap.tj }','${tjMap.mc }','${newBj.bjdm }','${newBj.bjmc }',this);return false;" title="${newBj.bjmc }">${newBj.bjmc }</a>
											<input type="hidden" name="bj_hidv_yc" id="bj_hidv_yc_${newBj.bjdm }" value="${newBj.bjdm }"/>
										</logic:notEqual>
										<!-- 其他 -->
										<logic:equal name="newBj" property="bjmc" value="其他">
											<a href="#" class="moreValue_click" style="display: none;" id="bj_qt_${newBj.bjdm }" onclick="clickBjQt('${bj.py }');return false;">更多</a>
											<input type="hidden" name="bj_hidv_qt" id="bj_hidv_qt_${newBj.bjdm }" value="${newBj.bjdm }"/>
										</logic:equal>
									<%} %>	
									<%bj_count++; %>
								</logic:iterate>
							</logic:notEmpty>
						</li>
					</logic:iterate>
					<input type="hidden" id="bj_py_num" value="<%=bj_py_num %>"/>
					<input type="hidden" id="bj_num" value="<%=bj_count %>"/>
				</ul>
				<%if (bj_count>11) {%>
				<a href="#" id="bj_more" class="more_down" onclick="showHiddenBm(this,'${tjMap.tj}','more_up','more_down','收起','更多');return false">更多</a>
				<%}%>
			</dd>
		</dt>
	</dl>
</logic:equal>
<!-- 班级 end-->