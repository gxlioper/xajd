<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
<script type='text/javascript' src='/xgxt/dwr/interface/pjpyService.js'></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/pjpy/typj.js"></script>
</head>
<body onload="setSqsj();display();">
	<html:form action="/typj" method="post">
		<input type="hidden" id="url" name="url" value="/pjpyTybZhcpjxj.do?method=zhcpjxjSq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-zymc-xymc-bjmc-mzmc-zzmmmc-csrq-lxdh" />
		<input type="hidden" name="message" id="message" value="${message }" />
		<input type="hidden" name="yhInfo" id="yhInfo" value="${yhInfo }" />
		<input type="hidden" name="save_nd" value="${sqsjInfo.nd}"/>
		<input type="hidden" name="save_xq" value="${sqsjInfo.xq}"/>
		<input type="hidden" name="save_sqsj" value="${sqsjInfo.sqsj}" id="sqsj"/>
		<input type="hidden" id="xxdm" value="${xxdm }"/>
		<input type="hidden" name="now" value="${nowTime }" id="now"/>
		<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<div class="tab">
		<fieldset>
			<legend>
				基本信息
			</legend>
			<table class="formlist" width="100%">
				<tbody>
				<tr>
					<th>
						<font color="red">*</font>学号
					</th>
					<td>
						<html:text property="save_xh" styleId="xh" value="${rs.xh}" 
							onkeypress="autoFillStuInfo(event.keyCode,this)"
							onblur="chkIsStu(this,'view_xsjbxx','xh')"
						/>
						<logic:notEqual value="stu" name="userType">
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',800,600);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:notEqual>
					</td>
					<th>姓名</th>
					<td>
						${rs.xm }
					</td>
				</tr>
				<tr>
					<th>性别</th>
					<td>
						${rs.xb }
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td>
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<th>
						专业
					</th>
					<td>
						${rs.zymc }
					</td>
					<th>班级</th>
					<td>
						${rs.bjmc }
					</td>
				</tr>
				<tr>
					<th>民族</th>
					<td>${rs.mzmc }</td>
					<th>政治面貌</th>
					<td>${rs.zzmmmc }</td>
				</tr>
				<tr>
					<th>出生日期</th>
					<td>${rs.csrq }</td>
					<th>联系电话</th>
					<td>${rs.lxdh }</td>
				</tr>
				<tr>
					<th><font color="red">*</font>学年</th>
					<td>
						<html:text property="save_xn" readonly="true" value="${sqsjInfo.xn }" styleId="xn"></html:text>
					</td>
					<th><font color="red">*</font>奖学金名称</th>
					<td>
						<html:select property="save_jxjdm" styleId="jxjdm" onchange="setSqsj();">
							<html:options collection="jxjList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				</tbody>
			</table>
		</fieldset>
		
		<fieldset>
			<legend>
				综合素质测评
			</legend>
			<logic:empty  name="zcpm">
				<p style="height:120px">没有记录！</p>
			</logic:empty>
			<logic:notEmpty name="zcpm">
			<table width="99%" id="rsTable" class="formlist">
				<thead>
					<tr>
						<th align="center">学年</th>
						<th align="center">学期</th>
						<th align="center">名称</th>
						<th align="center">分数</th>
						<th align="center">排名</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="v" name="zcpm" offset="0" scope="request">
						<tr align="center" style="cursor:hand">
							<td>
								${v.xn }
							</td>
							<td>
								${v.xqmc }
							</td>
							<td>
								${v.mc }
							</td>
							<td>
								${v.fs }
							</td>
							<td>
								${v.pm }
							</td>
						</tr>
					</logic:iterate>
				</tbody>
			</table>
			</logic:notEmpty>
		</fieldset>
		<table width="100%" class="formlist">
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		            <button type="button" class="button2" style="width:80px" id="buttonSave"
						onclick="saveUpdate('/xgxt/pjpyTybZhcpjxj.do?method=zhcpjxjSq&doType=save','save_xh-save_xn-save_jxjdm');">
						保&nbsp;&nbsp;存
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
	</html:form>
	<logic:present name="yhInfo">
		<script>
				alert(''+$('yhInfo').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
	<logic:present name="result">
		<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
</body>
</html>