<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script>
		function initPage(){
			var isSz = val('isSz');
			if(isSz == "yes"){
				ele("xgsjksTr").style.display = "";
				ele("xgsjjsTr").style.display = "";
			}else {
				ele("xgsjksTr").style.display = "none";
				ele("xgsjjsTr").style.display = "none";
			}
		}
	</script>
	
	<%@ include file="/comm/other/backgroud.jsp" %>
</head>
<body onload="initPage()">
	<html:form action="/studentMessage_conf" method="post">
		<input type="hidden" id="xxdm" value="${xxdm}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生信息 - 参数设置</a>
			</p>
		</div>
		
		<div class="tab">
			<table width="80%" class="formlist">
				<thead>
					<tr align="center">
						<th colspan="2"><span>参数设置</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="40%"><span class="red">*</span>当前年度</th>
						<td width="60%">
							<html:select name="rs" property="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>						
					</tr>
					<tr>
						<th><span class="red">*</span>当前学年</th>
						<td>							
							<html:select name="rs" property="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>						
					</tr>
					<tr>
						<th><span class="red">*</span>当前学期</th>
						<td>
							<html:select name="rs" property="xq">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>						
					</tr>
					<tr>
						<th><span class="red">*</span>是否需要时间设置</th>
						<td>
							<html:select name="rs" property="isSz" styleId="isSz" onchange="initPage()">
								<html:option value="no">否</html:option>
								<html:option value="yes">是</html:option>
							</html:select>
						</td>						
					</tr>
					<tr id="xgsjksTr">
						<th><span class="red">*</span>学生修改信息开始时间</th>
						<td>
							<html:text name="rs" property="kssj" onclick="return showCalendar('kssj','y-mm-dd');" readonly="true" size="10" styleId="kssj"></html:text>
							&nbsp;<html:text name="rs" property="kssjH" size="2"  styleId="kssjH" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"></html:text>:
							<html:text name="rs" property="kssjM" size="2"  styleId="kssjM" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"></html:text>:
							<html:text name="rs" property="kssjS" size="2" styleId="kssjS" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"></html:text>
						</td>
					</tr>
					<tr id="xgsjjsTr">
						<th><span class="red">*</span>学生修改信息结束时间</th>
						<td>
						 	<html:text name="rs" property="jssj" onclick="return showCalendar('jssj','y-mm-dd');" readonly="true" size="10" styleId="jssj"></html:text>
							&nbsp;<html:text name="rs" property="jssjH" size="2" styleId="jssjH" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"></html:text>:
							<html:text name="rs" property="jssjM" size="2"   styleId="jssjM" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}" onblur=""></html:text>:
							<html:text name="rs" property="jssjS" size="2"  styleId="jssjS" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"></html:text>
						</td>
					</tr>	
					<%--南昌大学科学技术学院--%>
					<logic:equal value="13429" name="xxdm">
						<tr>
							<th><span class="red">*</span>班主任修改信息开始时间</th>
							<td>
								<html:text name="rs" property="bzrkssj" onclick="return showCalendar('bzrkssj','y-mm-dd');" readonly="true" size="10" styleId="bzrkssj"></html:text>
								&nbsp;<html:text name="rs" property="bzrkssjH" size="2"  styleId="bzrkssjH" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}" onblur=""></html:text>:
								<html:text name="rs" property="bzrkssjM" size="2"  styleId="bzrkssjM" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}" onblur=""></html:text>:
								<html:text name="rs" property="bzrkssjS" size="2" styleId="bzrkssjS" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59};if(this.value<0){this.value=0}" onblur=""></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>班主任修改信息结束时间</th>
							<td>
								<html:text name="rs" property="bzrjssj" onclick="return showCalendar('bzrjssj','y-mm-dd');" readonly="true" size="10" styleId="bzrjssj"></html:text>
								&nbsp;<html:text name="rs" property="bzrjssjH" size="2"  styleId="bzrjssjH" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}" ></html:text>:
								<html:text name="rs" property="bzrjssjM" size="2"  styleId="bzrjssjM" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"></html:text>:
								<html:text name="rs" property="bzrjssjS" size="2" styleId="bzrjssjS" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59};if(this.value<0){this.value=0}"></html:text>
							</td>
						</tr>
					</logic:equal>		
					<!--非中国地质大学-->
					<logic:notEqual value="10491" name="xxdm">
					<tr>
						<th><span class="red">*</span>是否启用'分配学生修改字段权限'功能</th>
						<td>
						 	<html:radio property="havexsqx" value="yes" name="rs">开</html:radio>
							<html:radio property="havexsqx" value="no" name="rs">关</html:radio>
						</td>
					</tr>
					<logic:equal value="yes" property="havexsqx" name="rs">
<!--					<tr>-->
<!--						<th><span class="red">*</span>是否启用'字段修改审核'功能</th>
<!--						<td>-->
<!--						 	<html:radio property="xsxgxxsh" value="yes" name="rs">开</html:radio>-->
<!--							<html:radio property="xsxgxxsh" value="no" name="rs">关</html:radio>-->
<!--						</td>-->
<!--					</tr>-->
					</logic:equal>	
					<!-- 新版本不需要该设置 edit by luojw 2011.4.26 -->
					<logic:equal value="old"  name="edition">				
					<tr>
						<th><span class="red">*</span>学生信息地址信息采用标准代码</th>
						<td>
						 	<html:radio property="dzxxqdm" value="yes" name="rs">开</html:radio>
							<html:radio property="dzxxqdm" value="no" name="rs">关</html:radio>
						</td>
					</tr>	
					</logic:equal>
					</logic:notEqual>
					<!--end非中国地质大学-->	
					<!--新版-->
					<logic:equal value="new" name="edition">
						<tr style="display: none">
							<th><span class="red">*</span>学生修改信息后是否要审核</th>
							<td>
							 	<html:radio property="xsxgxxsfsh" value="yes" name="rs">是</html:radio>
								<html:radio property="xsxgxxsfsh" value="no" name="rs">否</html:radio>
							</td>
						</tr>
					</logic:equal>
					</tbody>

					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				            <button name="Submit2"
								onclick="saveConfigInfo()">
								提 交
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>		
				</table>
			</div>

			<logic:notEmpty name="oper">
				<logic:equal value="true" name="result">
					<script>alert("保存成功！");</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>alert("保存失败！");</script>
				</logic:equal>	
			</logic:notEmpty>				
		</html:form>
	</body>
</html>
