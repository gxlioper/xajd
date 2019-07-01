<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
</head>
<body>
	<html:form action="/studentMessage_conf" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生信息 - 参数设置</a>
			</p>
		</div>	
		<div class="tab">
			<table width="80%" class="formlist">
				<thead>
					<tr>
						<th colspan="2">
							<span>学生信息参数设置</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>当前年度</th>
					<td>
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
					<th><span class="red">*</span>是否需要时间设置</td>
					<td>
						<html:select name="rs" property="isSz">
							<html:option value="no">否</html:option>
							<html:option value="yes">是</html:option>
						</html:select>
					</td>						
				</tr>
				<tr>
					<th><span class="red">*</span>学生修改信息开始时间</th>
					<td>
						<html:text name="rs" property="kssj" onclick="return showCalendar('kssj','y-mm-dd');" readonly="true" size="10" styleId="kssj"></html:text>
						&nbsp;<html:text name="rs" property="kssjH" size="2"  styleId="kssjH" maxlength="2"></html:text>:
						<html:text name="rs" property="kssjM" size="2"  styleId="kssjM" maxlength="2"></html:text>:
						<html:text name="rs" property="kssjS" size="2" styleId="kssjS" maxlength="2"></html:text>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>学生修改信息结束时间</th>
					<td>
					 	<html:text name="rs" property="jssj" onclick="return showCalendar('jssj','y-mm-dd');" readonly="true" size="10" styleId="jssj"></html:text>
						&nbsp;<html:text name="rs" property="jssjH" size="2" styleId="jssjH" maxlength="2"></html:text>:
						<html:text name="rs" property="jssjM" size="2"   styleId="jssjM" maxlength="2"></html:text>:
						<html:text name="rs" property="jssjS" size="2"  styleId="jssjS" maxlength="2"></html:text>
					</td>
				</tr>	
				<tr>
					<th>成绩报告单发放时间</th>
					<td>
					 	<html:text name="rs" property="cjbgdffsj" onclick="return showCalendar('cjbgdffsj','y-mm-dd');" readonly="true" styleId="cjbgdffsj"></html:text>
					</td>
				</tr>
				<tr>
					<th>开学时间</th>
					<td>
					 	<html:text name="rs" property="kxsj" onclick="return showCalendar('kxsj','y-mm-dd');" readonly="true" styleId="kxsj"></html:text>
					</td>
				</tr>
				<tr>
					<th>报到时间</th>
					<td>
					 	<html:text name="rs" property="bdsj" onclick="return showCalendar('bdsj','y-mm-dd');" readonly="true" styleId="bdsj"></html:text>
					</td>
				</tr>
				<tr>
					<th>补考开始时间</th>
					<td>
					 	<html:text name="rs" property="bkkssj" onclick="return showCalendar('bkkssj','y-mm-dd');" readonly="true" styleId="bkkssj"></html:text>
					</td>
				</tr> 
				<tr>
					<th>补考结束时间</thd>
					<td>
					 	<html:text name="rs" property="bkjssj" onclick="return showCalendar('bkjssj','y-mm-dd');" readonly="true" styleId="bkjssj"></html:text>
					</td>
				</tr>                                 
				<tr>
					<th>应交学杂费</th>
					<td>
					 	<html:text name="rs" property="xzf" styleId="xzf" maxlength="5" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>元
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="2"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <button name="Submit2" class="button2"
							onclick="doSaveConf('/xgxt/studentMessage_conf.do?doType=save','nd-xn-xq-kssj-kssjH-kssjM-kssjS-jssj-jssjH-jssjM-jssjS')">
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
