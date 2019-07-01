<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/qgzx/cssz/cssz.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script>
		jQuery(function($){
//            $("#div_help").show();
		});
	</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /> </a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmouseover ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		<!-- 提示信息 -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					1、酬金发放时间截止后系统将<font color="blue">自动提交</font>酬金信息，提交后（除勤工管理员用户）<font color="blue">不能再次修改</font>酬金信息。
				</br>2、申请人数控制级别: 设定 <font color="blue">学生可获得岗位</font>进行控制的岗位级别。
				</br>3、用人单位审核级别: 设定用人单位审核时的数据范围。如果<font color="blue">勾选</font>，审核人为学生<font color="blue">申请岗位所在部门</font>的教师；如果<font color="blue">不勾选</font>，审核人为<font color="blue">负责申请学生</font>的教师(具体根据身份过滤可审核学生)。
				</br>4、所有数据都 <font color="blue">审核完成</font>后才可以更改审核流程配置。
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/qgzx_jcsz" method="post" styleId="qgzxCsszForm">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<%--<html:hidden property="rskzjb" styleId="rskzjb" value="${rs.rskzjb}"/>--%>
			<%--<html:hidden property="yjrskzjb" styleId="yjrskzjb" value="${rs.yjrskzjb}"/>--%>
			<%--<html:hidden property="qxfw" styleId="qxfw" value="${rs.qxfw}"/>--%>
			<%--<html:hidden property="sqfs" styleId="sqfs" value="${rs.sqfs}"/>--%>
			<%--<input type="hidden" name="cjffsh" id="cjffsh" value="${cjffsh }"/>--%>
			<html:hidden property="yjqxfw" styleId="yjqxfw" value="${rs.yjqxfw}"/>
			<html:hidden property="id" styleId="id" value="${rs.id}"/>
			<!-- 隐藏域 end-->
			<div class="tab">

				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>校内单位参数设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>岗位申请开关
							</td>
							<td align="left" style="width: 60%">
								<html:radio name="rs" property="sfkfgwsq" styleId="sfkfgwsq" value="on" onclick="changeGwsq();">开</html:radio>
								<html:radio name="rs" property="sfkfgwsq" styleId="sfkfgwsq" value="off" onclick="changeGwsq();">关</html:radio>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								岗位申请开放时间
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="gwsqkssj" styleId="gwsqkssj"   size="10"
									onclick="return showCalendar('gwsqkssj','y-mm-dd',true,'gwsqjssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								-
								<html:text name="rs" property="gwsqjssj" styleId="gwsqjssj"   size="10"
									onclick="return showCalendar('gwsqjssj','y-mm-dd',false,'gwsqkssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
									
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>岗位发布审批流程
							</td>
							<td align="left" style="width: 60%">
								<html:select name="rs" property="yrdwsplc"  styleId="yrdwsplc">
									<html:option value=""></html:option>
									<%--<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>--%>
									<html:optionsCollection name="splcList" value="splc" label="lcxx"/>
								</html:select>
							</td>
						</tr>
					</tbody>
					<%--<thead>
					<tr>
						<th colspan="2">
							<span>校外单位参数设置</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<td align="right" style="width: 35%">
							<font class="red">*</font>岗位申请开关
						</td>
						<td align="left" style="width: 60%">
							<html:radio name="rs" property="sfkfxwgwsq" styleId="sfkfxwgwsq" value="on" onclick="changeGwsq();">开</html:radio>
							<html:radio name="rs" property="sfkfxwgwsq" styleId="sfkfxwgwsq" value="off" onclick="changeGwsq();">关</html:radio>
						</td>
					</tr>
					<tr>
						<td align="right" style="width: 35%">
							岗位申请开放时间
						</td>
						<td align="left" style="width: 60%">
							<html:text name="rs" property="xwgwsqkssj" styleId="xwgwsqkssj"   size="10"
									   onclick="return showCalendar('xwgwsqkssj','y-mm-dd',true,'xwgwsqkssj');"
									   onblur="dateFormatChg(this)" readonly="true"></html:text>
							-
							<html:text name="rs" property="xwgwsqjssj" styleId="xwgwsqjssj"   size="10"
									   onclick="return showCalendar('xwgwsqjssj','y-mm-dd',false,'xwgwsqjssj');"
									   onblur="dateFormatChg(this)" readonly="true"></html:text>

						</td>
					</tr>
					<tr>
						<td align="right" style="width: 35%">
							<font class="red">*</font>岗位发布审批流程
						</td>
						<td align="left" style="width: 60%">
							<html:select name="rs" property="xwgwsplc"  styleId="xwgwsplc">
								<html:option value=""></html:option>
								&lt;%&ndash;<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>&ndash;%&gt;
								<html:optionsCollection name="splcList" value="splc" label="lcxx"/>
							</html:select>
						</td>
					</tr>
					</tbody>--%>
					<thead>
						<tr>
							<th colspan="2">
								<span>学生岗位参数设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>学生岗位申请开关
							</td>
							<td align="left" style="width: 60%">
								<html:radio name="rs" property="sfkfxsgwsq" styleId="sfkfxsgwsq" value="on" onclick="changexsGwsq();">开</html:radio>
								<html:radio name="rs" property="sfkfxsgwsq" styleId="sfkfxsgwsq" value="off" onclick="changexsGwsq();">关</html:radio>
<%--								(只有在起止时间段内才能进行申请)  2013.1.6 qlj 梅大哥说提示重复了 暂且修业 --%> 
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								学生岗位申请开放时间
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="xsgwsqkssj" styleId="xsgwsqkssj"   size="10"
									onclick="return showCalendar('xsgwsqkssj','y-mm-dd',true,'xsgwsqjssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								-
								<html:text name="rs" property="xsgwsqjssj" styleId="xsgwsqjssj"   size="10"
									onclick="return showCalendar('xsgwsqjssj','y-mm-dd',false,'xsgwsqkssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								（在该时间内学生才能申请岗位，但勤工办不受影响）
							</td>
						</tr>
						<tr id="hdgwpd">
							<td align="right" style="width: 35%">
								学生可获得岗位数
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="xsgws" styleId="xsgws" onblur="checkInputNum(this)" size="10" maxlength="5"></html:text>
								（学生可获得勤工岗位的上限值,"0" 表示无限制）
							</td>
						</tr>
						<tr id="sqgwpd">
							<td align="right" style="width: 35%">
								学生可申请岗位数
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="xsxsgws" styleId="xsxsgws" onblur="checkInputNum(this)" size="10" maxlength="5"></html:text>
								（学生可提交勤工岗位申请的上限值,"0" 表示无限制）
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>校内岗位申请审批流程
							</td>
							<td align="left" style="width: 60%">
								<html:select property="xsgwsqsplc" name="rs" styleId="xsgwsqsplc">
									<html:option value=""></html:option>
									<html:optionsCollection name="splcList" value="splc" label="lcxx"/>
								</html:select>
							</td>
						</tr>
						<tr>
						<tr>
							<td align="right">
								用人单位审核级别
							</td>
							<td bgcolor="#FFF5EE" id="yjqxfwTd">
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>校外岗位申请审批流程
							</td>
							<td align="left" style="width: 60%">
								<html:select property="xwxsgwsqsplc" name="rs" styleId="xwxsgwsqsplc">
									<html:option value=""></html:option>
									<html:optionsCollection name="splcList" value="splc" label="lcxx"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>工作时长维护审批流程
							</td>
							<td align="left" style="width: 60%">
								<html:select property="gzscwhsplc" name="rs" styleId="gzscwhsplc">
									<html:option value=""></html:option>
									<html:optionsCollection name="splcList" value="splc" label="lcxx"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>学生离职审批流程
							</td>
							<td align="left" style="width: 60%">
								<html:select property="xslzsplc" name="rs" styleId="xslzsplc">
									<html:option value=""></html:option>
									<html:optionsCollection name="splcList" value="splc" label="lcxx"/>
								</html:select>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="2">
								<span>酬金发放参数设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<td align="right" style="width: 35%">
							<font class="red">*</font>工资申报开关
						</td>
						<td align="left" style="width: 60%">
							<html:radio name="rs" property="gzsqkg" styleId="gzsqkg" value="on">开</html:radio>
							<html:radio name="rs" property="gzsqkg" styleId="gzsqkg" value="off">关</html:radio>
						</td>
					</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>设置工资上限
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="cjsx" styleId="cjsx" onblur="checkInputNum(this)" size="10" maxlength="6"></html:text>
								<font id="font_sxsz">（学生每月工资不得超过该值）</font>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>工作时长上限
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="gzscsx" styleId="gzscsx" onblur="checkInputNum(this)" size="10" maxlength="6"></html:text>
								<font id="font_sxsz">（学生每月工作时长不得40小时）</font>
							</td>
						</tr>
					<tr>
						<td align="right" style="width: 35%">
							工资申报开放时间
						</td>
						<td align="left" style="width: 60%">
							<html:text name="rs" property="gzsqkssj" styleId="gzsqkssj"   size="10"
									   onclick="return showCalendar('gzsqkssj','y-mm-dd',true,'gzsqkssj');"
									   onblur="dateFormatChg(this)" readonly="true"></html:text>
							-
							<html:text name="rs" property="gzsqjssj" styleId="gzsqjssj"   size="10"
									   onclick="return showCalendar('gzsqjssj','y-mm-dd',false,'gzsqjssj');"
									   onblur="dateFormatChg(this)" readonly="true"></html:text>
							（在该时间内单位才能进行工资申报，但勤工办不受影响）
						</td>
					</tr>
					<tr>
						<td align="right" style="width: 35%">
							<font class="red">*</font>工资申报审批流程
						</td>
						<td align="left" style="width: 60%">
							<html:select property="gzsqsplc" name="rs" styleId="gzsqsplc">
								<html:option value=""></html:option>
								<html:optionsCollection name="splcList" value="splc" label="lcxx"/>
							</html:select>
						</td>
					</tr>
					</tbody>
					<tfoot>
						<tr>
							<td align="center" colspan="2">							
			        			<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
								<logic:equal name="writeAble" value="yes">	
									<button type="button" onclick="Save();return false;" id="buttonSave">
										保 存
									</button>	
								</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>