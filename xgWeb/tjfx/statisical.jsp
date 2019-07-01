<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	</head>

	<body onload="loadConditionSql();InitCols()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" scope="request" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<center>
			<html:form action="/statisical">
				<html:hidden name="commanForm" property="conditionSqlText" styleId="conditionSqlText" />
				<html:hidden name="commanForm" property="conditionSqlValue" styleId="conditionSqlValue" />
				<html:hidden name="commanForm" property="cols" styleId="cols" />
				<html:hidden name="commanForm" property="tjxmdyb" styleId="tjxmdyb" />
				<input type="hidden" name="mkV" id="mkV"
					value="<bean:write name="mkV" scope="request"/>"></input>
				<input type="hidden" name="xmV" id="xmV"
					value="<bean:write name="xmV" scope="request"/>"></input>
				<input type="hidden" name="title" id="title"
					value="<bean:write name="title" scope="request"/>"></input>
					
					<table class="formlist" border="0" align="center" style="width: 100%">	
						<thead>
							<tr>
								<td align="center">
									统计项目
								</td>
								<td colspan="2" align="center">
									具体项目
								</td>
								<td align="center">
									运算符
								</td>
								<td align="center">
									查询字段的值
								</td>
							</tr>
						</thead>
						<tbody>
						<tr>
							<td>
								<html:select property="tjxm" styleId="tjxm" 
									onchange="statisicalSubloader('prise','${title }')">
									<html:option value="">-----请选择-----</html:option>
									<html:options collection="tjxmList" labelProperty="tjxm"
										property="tjxm" />
								</html:select>
							</td>
							<td>
								<html:select property="jtxm" styleId="jtxm" 
									onchange="statisicalSubloader('prise','${title }')">
									<html:option value="">-----请选择----- </html:option>
									<logic:notEmpty name="jtxmList">
										<html:options collection="jtxmList" labelProperty="jtxmzdmcn"
											property="jtxmzdm" />
									</logic:notEmpty>
								</html:select>
							</td>
							<td>
								<logic:empty name="zxmList">
									<html:select property="zxm" styleId="zxm" style="width:130px"
										disabled="true">
										<html:option value="">-----请选择-----</html:option>
									</html:select>
								</logic:empty>
								<logic:notEmpty name="zxmList">
									<html:select property="zxm" styleId="zxm" 
										onchange="statisicalSubload('prise')">
										<html:option value="">-----请选择-----</html:option>
										<html:options collection="zxmList" labelProperty="zxmzdmcn"
											property="zxmzdm" />
									</html:select>
								</logic:notEmpty>
							</td>
							<td>
								<select name="relation" id="relation">
									<option value=" = ">
										等于
									</option>
									<option value=" &lt;&gt; ">
										不等于
									</option>
									<option value=" &gt;= ">
										大于等于
									</option>
									<option value=" &lt;= ">
										小于等于
									</option>
									<option value=" like ">
										包含
									</option>
								</select>
							</td>
							<td>
								<logic:empty name="tjtjzList">
									<html:text property="tjtjz" styleId="tjtjz" value=""
										style="width:112px;font-size:10pt;"></html:text>
								</logic:empty>
								<logic:notEmpty name="tjtjzList">
									<html:select property="tjtjz" styleId="tjtjz"
										style="width:130px;">
										<html:option value=""></html:option>
										<html:options collection="tjtjzList" labelProperty="tjtjzmc"
											property="tjtjzdm" />
									</html:select>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td align="center">
								<button name="button" onclick="addStatisicalCondition()">
									增加条件
								</button>
							</td>
							<td align="center" colspan="2">
								逻辑运算符
								<button name="button" value="与"
									onclick="addStatisicalAnd()">与</button>
								<button name="button" value="或"
									onclick="addStatisicalOr()">或</button>
								<button name="button" value="("
									onclick="addStatisicalZk()">(</button>
								<button name="button" value=")"
									onclick="addStatisicalYk()">)</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="checkbox" name="dybj" value="yes"/>
								&nbsp;党员
							</td>
							<td align="center" colspan="2">
								选择显示字段
								<font color="red">反选</font>
								<input type="checkbox" name="xszd" id="xszd" onclick="fx()" />
								<button name="indexUp"
									onclick="moveIndexV('Vup')">&uarr;</button>
								<button name="indexDown"
									onclick="moveIndexV('Vdown')">&darr;</button>
							</td>
						</tr>
						<tr>
							<td height="173" colspan="3">
								<select name="sql" size="13" style="width:100%" id="sql">
								</select>
							</td>
							<td align="center" colspan="2">
								<html:select property="titList" styleId="titList" size="16"
									style="width:190px" ondblclick="chk1(this)">
									<logic:notEmpty name="jtxmListTit">
										<html:options collection="jtxmListTit"
											labelProperty="jtxmzdmcnTit" property="jtxmzdmTit" />
										<html:option value="rs">×数量</html:option>
										<html:option value="bl">×比例</html:option>
									</logic:notEmpty>
								</html:select>
							</td>
						</tr>
						<tfoot>
						<tr>
							<td align="center" height="33" colspan="5">
								<div class="btn">
								<button name="Submit"
									id="buttonStat" value="&nbsp;统 计&nbsp;"
									onclick="subStatisical()" >统 计</button>
								<button name="Submit3"
									onclick="delcondition()">删除条件</button>
								<button name="Submit4"
									onclick="clearcondition()">清空条件</button>
								</div>
							</td>
						</tr>
						</tfoot>
						</tbody>
					</table>
			</html:form>
		</center>
	</body>
</html>
