<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript">
		function printZm(url){
		var xh = document.getElementById("xh").value;
		var lxfs = document.getElementById("lxfs").value;
		var blfs = document.getElementById("blfs").value;
		var sqly = document.getElementById("sqly").value;
		
		url +="&xh="+xh;
		url +="&lxfs="+lxfs;
		url +="&blfs="+blfs;
		url +="&sqly="+sqly;
		window.open(url);
		}
	</script>
</head>
	<body>		
		<html:form action="/data_search" method="post">
			<input type="hidden" id="disableEle" name="disableEle" value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="sh" name="sh" value="<bean:write name="sh"/>" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
			<logic:notEqual name="xxdm" value="13022">
				<input type="hidden" id="url" name="url" value="/attend_school_prove.do?sfcg=0" />
			</logic:notEqual>
			<logic:equal name="xxdm" value="13022">
				<logic:equal name="sh" value="no">
				<input type="hidden" id="url" name="url" value="/attend_school_prove.do?sfcg=0&sh=no" />
				</logic:equal>
				<logic:equal name="sh" value="yes">
				<input type="hidden" id="url" name="url" value="/attend_school_prove.do?sfcg=0&sh=yes" />
				</logic:equal>
			</logic:equal>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>学生信息 - 在读证明申请 - 填写申请表</a>
				</p>
			</div>
				
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("您输入的学号无效!");
    				</script>
				</logic:equal>
				<logic:equal name="dataSaved" value="ok" scope="request">
					<script>
    					alert("保存成功！");
    				</script>
				</logic:equal>
			</logic:notEmpty>
			
			<div class="tab">
				<table width="100%" id="rsT" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:write name="xxmc"/>办理在读证明申请</span>
							</th>
						</tr>
					</thead>	
					<tbody>					
						<tr>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<th><span class="red">*</span>学号</th>
								<td>
									<logic:equal value="view" name="doType">
										<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
									</logic:equal>
									<logic:notEqual value="view" name="doType">
										<html:text name='rs' property="xh" styleId="xh"
										onblur="autoFillStuInfo2(this)" onkeypress="if(event.keyCode == 13) return false;" />
										<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu">
											选择
										</button>
									</logic:notEqual>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<th><span class="red">*</span>学号</th>
								<td>
									<input type="text" id="xh" name="xh" value="<bean:write name='rs' property="xh" />" readonly="readonly" />
									<input type="hidden" id="xh" name="xh" value="<bean:write name='rs' property="xh" />" />
								</td>
							</logic:equal>
							<th>专业</th>
							<td>
								<input type="text" value="<bean:write name="rs" property="zymc"/>" readonly="readonly"/>								
							</td>
						</tr>
						<tr>
							<th>姓名</th>
							<td>
								<input type="text" value="<bean:write name='rs' property="xm" />" readonly="readonly"/>																
							</td>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td>
								<input type="text" value="<bean:write name='rs' property="xymc" />" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th>性别</th>
							<td>
								<input type="text" value="<bean:write name='rs' property="xb" />" readonly="readonly"/>
							</td>
							<th>班级</th>
							<td>
								<input type="text" value="<bean:write name='rs' property="bjmc" />"readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th>联系方式(手机)</th>
							<td>
								<html:text name="rs" property="lxfs" styleId="lxfs" maxlength="32"/>
							</td>
							<th><span class="red">*</span>证明类型</th>
							<td>
								<html:select property="zmlx" name="rs" styleId="zmlx">
								<html:option value=""></html:option>
								<html:options collection="typeList" labelProperty="cn" property="en"/>	
								</html:select>								
							</td>	
						</tr>						
						<tr>
							<th><span class="red">*</span>办理份数</th>
							<td>
								<html:text name="rs" property="blfs" styleId="blfs" style="width:90%" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'') "/>份
							</td>
							<th></th>						
							<td></td>
						</tr>						
						<tr>
							<th><span class="red">*</span>申请原因</th>
							<td colspan="3">
								<html:textarea name="rs" property="sqly" style="height:100px;width:100%" styleId="sqly"/>
							</td>
						</tr>	
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
					            <logic:equal name="sh" value="no">
									<button
										onclick="commSave('/xgxt/attend_school_prove.do?doType=save&sh=no','xh-sqly-blfs-zmlx')">
										提 交 申 请
									</button>
									</logic:equal>
									<logic:notEqual name="sh" value="no">
									<button
										onclick="commSave('/xgxt/attend_school_prove.do?doType=save','xh-sqly-blfs-zmlx')">
										提 交 申 请
									</button>
									</logic:notEqual>
									<button onclick="printZm('business.do?method=printZmreporter')">
										打 印 报 表
									</button>
									<button onclick="printCertificate()">
										证 明 打 印
									</button>
					          </div>
					        </td>
					      </tr>
					    </tfoot>					
					</table>
				</div>

				<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<logic:present name="msg">
						<input type="hidden" id="msg" value="${msg}"/>
						<script>
							alert(document.getElementById('msg').value);
						</script>
					</logic:present>
					<logic:notPresent name="msg">
						<script>
							alert("申请成功！");
						</script>
					</logic:notPresent>
				</logic:equal>
				
				<logic:equal value="false" name="result">
					<logic:present name="msg">
						<input type="hidden" id="msg" value="${msg}"/>
						<script>
							alert(document.getElementById('msg').value);
						</script>
					</logic:present>
					<logic:notPresent name="msg">
						<script>
							alert("申请失败！");
						</script>
					</logic:notPresent>
				</logic:equal>
				</logic:notEmpty>
		</html:form>
	</body>
</html>
