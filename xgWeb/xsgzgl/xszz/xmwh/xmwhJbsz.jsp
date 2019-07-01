<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/xmwh/js/xmwhJbsz.js"></script>
	</head>
	<body>
		<html:form action="/xszz_xmwh" method="post" styleId="form1">
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>--%>
		<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					当前设置项目为：<font color="red">${xmmc} &nbsp;	
					<span id="spztTip" style="display:none;">
					当前项目已有学生申请，部分项不允许修改
					</span>
				</font>
					只有在申请开关开启状态，并且申请时间有效范围内进行设置后，才为“已设置”状态，其余均为“未设置”状态
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
				<html:hidden property="xmdm" styleId="xmdm" />
			<html:hidden property="jdkzjb" styleId="jdkzjb" />
			<html:hidden property="rskzjb" styleId="rskzjb" />
			<html:hidden property="knsbddc" styleId="knsbddc" />
			<html:hidden property="sqxn" styleId="sqxn" />
			<html:hidden property="sqxq" styleId="sqxq" />
			<input type="hidden" name="rskzfwOld" id="rskzfwOld" value="${rskzfw}"/>
			<input type="hidden" name="jekzfwOld" id="jekzfwOld" value="${xmwhForm.jekzfw}"/>
			<input type="hidden" name="spzt" id="spzt" value="${spzt}"/>
			<input type="hidden" id="xxdm" value="${xxdm}"/>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>时间设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">

						<tr>
							<th width="120px">
								<font color="red">*</font>申请开关
							</th>
							<td colspan="3">	
								<logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList" >
										<label>
											<html:radio property="sqkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>	
						<tr>
							<th>
								开放申请时间
							</th>
							<td>
								<html:text property="sqkssj" styleId="sqkssj"
								onfocus="showCalendar('sqkssj','ymmdd');" />
								&nbsp;至
								<html:text property="sqjssj" styleId="sqjssj"
								onfocus="showCalendar('sqjssj','ymmdd');" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>审核流程
							</th>
							<td>
								<html:select property="splc" styleId="splc">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
									labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>				
						<logic:equal name="xxdm" value="14008">
						<div>
							<tr>
								<th width="120px">
									<font color="red">*</font>审核开关
								</th>
								<td>
									<logic:present name="onoffList">
										<logic:iterate id="o" name="onoffList">
											<label>
												<html:radio property="shkg" value="${o.dm}">${o.mc}</html:radio>
											</label>
										</logic:iterate>
									</logic:present>
								</td>
							</tr>
							<tr>
								<th>
									审核起止时间
								</th>
								<td>
									<html:text  property="shkssj" styleId="shkssj"   size="10"
										onclick="return showCalendar('shkssj','y-mm-dd',true,'shjssj');" 
										onblur="dateFormatChg(this)" readonly="true"></html:text>
									&nbsp;至
									<html:text  property="shjssj" styleId="shjssj"   size="10"
										onclick="return showCalendar('shjssj','y-mm-dd',false,'shkssj');" 
										onblur="dateFormatChg(this)" readonly="true"></html:text>
								</td>
							</tr>
						</div>
						</logic:equal>					
						<tr>
							<th>
								申请周期
							</th>
							<td>
							${sqzq}
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>项目评定周期
							</th>
							<td>
								<html:select  property="pdxn" styleId="pdxn" style="width:90px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
								<html:select onchange="changeJgzq()"  property="pdxq" styleId="pdxq" style="width:90px">
								<html:option value="">学年</html:option>
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
				
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>其他设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">	
						<tr>
							<th width="120px">
								<font color="red">*</font>人数控制级别
							</th>
							<td id="rskzjbTd"  colspan="3" >
							</td>

						</tr>
						<tr>
							<th>
								<font color="red">*</font>学生培养类型
							</th>
							<td colspan="3">
								<html:select property="pycc" styleId="pycc">
									<html:options collection="pyccList" property="pyccdm"
												  labelProperty="pyccmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>人数控制范围
							</th>
							<td  colspan="3" >					
								<html:select property="rskzfw" styleId="rskzfw" style="width:140px">
									<html:option value=""></html:option>
									<html:option value="bj">班级</html:option>
									<html:option value="njxy">年级+<bean:message key="lable.xb" /></html:option>
									<html:option value="njzy">年级+专业</html:option>
									<html:option value="xy"><bean:message key="lable.xb" /></html:option>
									<html:option value="zy">专业</html:option>
									<html:option value="sy">书院</html:option>
								</html:select>
								<html:radio property="xslb" value="1">在校生</html:radio>
								<html:radio property="xslb" value="0">困难生</html:radio>
							</td>
						</tr>
						<tr>
							<th width="120px">
								<font color="red">*</font>经费控制级别
							</th>
							<td id="jfkzjbTd"  colspan="3" >
							</td>

						</tr>
						<tr>
							<th>
								<font color="red">*</font>间隔申请周期
							</th>
							<td  colspan="3" >					
								<html:select property="jgsqzq" styleId="jgsqzq" style="width:140px">
									<html:option value="0">一学年</html:option>
									<html:option value="1">一学期</html:option>
									<html:option value="2">一月</html:option>
									<html:option value="3">一日</html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
				<dir style="height: 10px"></dir>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

