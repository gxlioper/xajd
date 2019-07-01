<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/qtsjFunction.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>违纪处分 - 数据维护 - 违纪处分降级</a>
			</p>
		</div>
		
		<html:form action="wjcf_bjqn.do" method="post">
		<input type="hidden" name="pkValue" value="${param.pkValue}"/>
		<input type="hidden" name="xh" value="${rs.xh }"/>
		<input type="hidden" name="cfsj" value="${rs.cfsj }"/>
		<div class="tab">
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th colspan="4"><span>学生处分降级</span></th>
					</tr>
				</thead>
			
				<tbody>
					<tr>
						<th>学号</th>
						<td>${rs.xh }</td>
						<th>姓名</th>
						<td>${rs.xm }</td>
					</tr>
					<tr>
						<th>性别</th>
						<td>${rs.xb }</td>
						<th>年级</th>
						<td>${rs.nj }</td>
					</tr>
					<tr>
						<th><bean:message key="lable.xb" />名称</th>
						<td>${rs.xymc }</td>
						<th>专业名称</th>
						<td>${rs.zymc}</td>
					</tr>
					<tr>
						<th>班级名称</th>
						<td>${rs.bjmc }</td>
						<th>政治面貌</th>
						<td>${rs.zzmmmc }</td>
					</tr>
					<tr>
						<th>原处分文号</th>
						<td>${rs.cfwh }</td>
						<th><font color="red">*</font>降级后处分文号</th>
						<td><html:text property="cfwh" styleId="cfwh" maxlength="100"></html:text></td>
					</tr>
					<tr>
						<th>处分时间</th>
						<td>${rs.cfsj }</td>
						<th><font color="red">*</font>降级时间</th>
						<td><html:text property="jjsj" styleId="jjsj" onclick="showCalendar('jjsj','y-mm-dd');" onblur="dateFormatChg(this);"></html:text></td>
					</tr>
					<tr>
						<th>原处分类别</th>
						<td>${rs.cflbmc }</td>
						<th><font color="red">*</font>降级后处分类别</th>
						<td>
							<logic:notPresent name="isCf">
								<html:select property="cflb">
									<html:option value=""></html:option>
									<html:options collection="cflbList" labelProperty="cflbmc" property="cflbdm"/>
								</html:select>
							</logic:notPresent>
							<logic:present name="isCf">
								该学生已是最低处分级别，不能降级！
							</logic:present>
						</td>
					</tr>
					<tr>
						<th>处分原因</th>
						<td colspan="3">${rs.cfyymc }</td>
					</tr>
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave"
			          	onclick="saveDataShowTips('wjcf_bjqn.do?method=wjcfjjSave','jjsj-cfwh-cflb','正在处理数据，请稍候！')">提 交</button>
			            <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
		</div>
		</html:form>
		<logic:present name="isCf">
			<script type="text/javascript">
				$('buttonSave').disabled = true;
				$('jjsj').disabled = true;
				$('cfwh').disabled = true;
			</script>
		</logic:present>
		
		<logic:present name="message">
			<input type="hidden" name="message" value="${message }"/>
			<script>
				alert($('message').value);
				Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					window.dialogArguments.document.getElementById('search_go').click();	
				}
			</script>
		</logic:present>
	</body>
</html>