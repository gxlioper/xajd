<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript">
		function modidata(url,w,h){
			if(curr_row == null){
				alert("请选择一行要修改的记录！");
				return false;
			}
			var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
			refreshForm(url+pkValue,w,h);
		}			
		
		function save(){
			var oldFlag = val("yesNoOld");
			var flag = val("yesNo");
			if(oldFlag == flag){
				alert("审核结果没有发生改变！");
				return false;
			}
			refreshForm('qgzxZgdzdx.do?method=saveQgzxsqsh')
		}
		
		function stringFormat(){
			if(ele('bzTd')){	
				ele('bzTd').innerHTML = formatContentWidth(ele('bzTd').innerText,30);
			}
			if(ele('sqlyTd')){
				ele('sqlyTd').innerHTML = formatContentWidth(ele('sqlyTd').innerText,30);
			}
			if(ele('yhtcTd')){
				ele('yhtcTd').innerHTML = formatContentWidth(ele('yhtcTd').innerText,30);
			}
		}
	</script>
</head>
	<body onload="stringFormat()">		
		<html:form action="/qgzxZgdzdx.do" method="post">	
			<input type="hidden" id="userType" name="userType" value="${userType }"/>
			<input type="hidden" name="mes" id="mes" value="${mes}"/>

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 审核 - 勤工助学申请审核</a>
				</p>
			</div>

			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<div class="tab">
					<table width="100%" id="rsTable" class="formlist">
						<thead>
						<tr>
							<th colspan="4">
								<span>学生勤工助学申请审核</span>
							</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<th width="20%">学号</th>
							<td width="30%">
								<bean:write name="rs" property="xh"/>
								<html:hidden property="xh" name="rs"/>
							</td>
							<th width="20%">学年</th>
							<td width="30%">
								<bean:write name="rs" property="xn"/>
								<html:hidden property="xn" name="rs"/>
							</td>
						</tr>
						<tr>
							<th>姓名</th>
							<td>
								<bean:write name="rs" property="xm"/>
							</td>
							<th>年度</th>
							<td>
								<bean:write name="rs" property="nd"/>
								<html:hidden property="nd" name="rs"/>
							</td>
						</tr>	
						<tr>
							<th>性别</th>
							<td>
								<bean:write name="rs" property="xb"/>
							</td>
							<th>学期</th>
							<td>
								<bean:write name="rs" property="xqmc"/>
								<html:hidden property="xq" name="rs"/>
							</td>
						</tr>	
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" />名称</th>
							<td>
								<bean:write name="rs" property="xymc"/>
							</td>
							<th>可参加勤工助学时间</th>
							<td>
								<bean:write name="rs" property="kcjqgzxsj"/>
							</td>
						</tr>	
						
						<tr>
							<th>班级名称</th>
							<td>
								<bean:write name="rs" property="bjmc"/>
							</td>
							<th>联系电话</th>
							<td>
								<bean:write name="rs" property="lxdh"/>
							</td>
						</tr>						
						<tr>
							<th>政治面貌</th>
							<td>
								<html:select property="zzmmdm" name="rs" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" labelProperty="zzmmmc" property="zzmmdm"/>
								</html:select>
							</td>
							<th>宿舍编号</th>
							<td>
								<bean:write name="rs" property="ssbh"/>
							</td>
						</tr>
						<tr>
							<th>有何特长</th>
							<td colspan="3" id="yhtcTd">
								<bean:write name="rs" property="yhtc"/>
							</td>	
						</tr>
						<tr>	
							<th>申请理由</th>
							<td colspan="3" id="sqlyTd">							
								<bean:write name="rs" property="sqly"/>
							</td>							
						</tr>	
						<tr>
							<th>备注</th>
							<td colspan="3" id="bzTd">
								<bean:write name="rs" property="bz"/>
							</td>							
						</tr>	
						<tr>					
							<th>审核</th>
							<td colspan="3">
								<html:select property="yesNo" name="rs" styleId="yesNo">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn" />
								</html:select>
								<input type="hidden" value="${rs.yesNo}" id="yesNoOld"/>
							</td>						
						</tr>
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="4">
					          <div class="btn">
<!--								<logic:notPresent name = "tp">-->
<!--								<logic:notEqual name="userType" value="xy">-->
									<!-- begin 骆嘉伟 2009/3/25 -->
									<button type="button" class="button2" id="btn_add"
										onclick="save();return false;"
										style="width:80px">
										保 存
									</button>
									<!-- end 骆嘉伟 2009/3/25 -->
<!--								</logic:notEqual>-->
<!--								</logic:notPresent>-->
								<button type="button" class="button2" id="btn_edit"
									onclick="Close();return false;"
									style="width:80px">
									关 闭
								</button>								
					          </div>
					        </td>
					      </tr>
					    </tfoot>				
					</table>
			</logic:notEmpty>

				<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
							alert('操作成功!');
							Close();
							window.dialogArguments.document.getElementById('search_go').click();
						</script>	
					</logic:equal>
					<logic:equal value="false" name="result">
					<logic:notEmpty name="hmdMember">
						<script>
							alert('该学生已经被列入勤工助学黑名单，不能审核通过！');
							Close();							
						</script>
					</logic:notEmpty>
					<logic:empty name="hmdMember">
					<logic:notEmpty name="mes">
					<script>
						alert(document.getElementById('mes').value);						
						Close();
					</script>
					</logic:notEmpty>
					<logic:empty name="mes">
					<script>
						alert("操作失败！");				
						Close();
					</script>
					</logic:empty>
					</logic:empty>
				</logic:equal>	
			</logic:present>	
		
		</html:form>	
	</body>
</html>
