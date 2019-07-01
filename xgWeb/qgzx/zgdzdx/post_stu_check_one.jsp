<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script type='text/javascript' src='js/xgutil.js'></script>
	<script language="javascript">
	function writeZwxz(){
		var yesNo = val('yesNo');
		if("通过" == yesNo){
			ele('zwxzdm').disabled = false;
		}else{
			setVal('zwxzdm','');
			ele('zwxzdm').disabled = true;
		}
	}
	
	function zgdzdxCommit(){
		var yesNo = document.getElementById("yesNo").value;		
		var pkString = document.getElementById("pk").value;
		refreshForm("/xgxt/postStuChkOne.do?act=save&yesNo=" + yesNo + "&pkVal=" + pkString);
		if($("buttonSave")){$("buttonSave").disabled=true;}
	}
	</script>
</head>	
<body onload="checkWinType();document.all('buttonClose').focus()">
		<html:form action="/data_search" method="post">
			<input type="hidden" name="pkVal" value="<bean:write name="rs" property="xh||gwdm||sqsj"/>" id="pkVal" />
			<input type="hidden" name="gwdm" value="<bean:write name="rs" property="gwdm"/>" />
			<input type="hidden" name="xh" value="<bean:write name="rs" property="xh"/>" />
			<input type="hidden" name="pk" value="<bean:write name="rs" property="pk"/>" id="pk"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName"/>" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 审核 - 学生申请审核 - 单个审核</a>
				</p>
			</div>
			
			<div class="tab">
			<table width="100%" align="center" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>单个学生申请审核</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>学号</th>
					<td>
						<bean:write name="rs" property="xh" />
					</td>
					<th>年度</th>
					<td>
						<bean:write name="rs" property="nd" />
					</td>
				</tr>

				<tr>
					<th>姓名</th>
					<td>
						<bean:write name="rs" property="xm" />
					</td>
					<th>学年</th>
					<td>
						<bean:write name="rs" property="xn" />
					</td>
				</tr>
				<tr>
					<th>性别</th>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
					<th>岗位名称</th>
					<td>
						<bean:write name="rs" property="gwdm" />
					</td>
				</tr>
				<tr>
					<th>年级</th>
					<td>
						<bean:write name="rs" property="nj" />
					</td>
					<th>申请时间</th>
					<td>
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
					<th>是否困难生</th>
					<td>
						<bean:write name="rs" property="sfpks" />
					</td>
				</tr>
				<tr>
					<th>专业</th>
					<td>
						<bean:write name="rs" property="zymc" />
					</td>
					<th>联系电话</th>
					<td>
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr>
					<th>班级</th>
					<td colspan="3">
						<bean:write name="rs" property="bjmc" />
					</td>						
				</tr>
				<tr>
					<th>民族</th>
					<td colspan="3">
						<bean:write name="rs" property="mzmc"/>
					</td>
					
                </tr>
				<tr>
					<th>政治面貌</th>
					<td colspan="3">
						<bean:write name="rs" property="zzmmmc"/>
					</td>					
                </tr>
				<tr>
					<th>有何特长</th>
					<td colspan="3">
						<bean:write name="rs" property="yhtc"/>
					</td>
                </tr>
				<tr>
					<th>申请理由</th>
					<td colspan="3">
						<bean:write name="rs" property="xssq"/>
					</td>
                </tr>
				<tr>
					<th>备注</th>
					<td colspan="3">
						<bean:write name="rs" property="bz"/>
					</td>
                </tr>
				<tr>
				<th>审核</th>
				<td>
					<html:select name="rs" property="yesNo" styleId="yesNo" onchange="writeZwxz()">
						<html:options collection="chkList" property="en"
							labelProperty="cn" />
					</html:select>
				</td>
				<th>职位性质</th>
				<td>
					<html:select name="rs" property="zwxzdm" styleId="zwxzdm">
						<html:option value=""></html:option>
						<html:options collection="zwxzdmList" property="dm"
							labelProperty="mc" />
					</html:select>
				</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			            <button type="button" class="button2" onclick="zgdzdxCommit();" style="width:80px"
							id="buttonSave">
							保 存
						</button>
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>		
			</table>
			</div>
		</html:form>

		<!--操作提示信息-->
		<logic:notEmpty name="flag">
			<logic:equal value="true" name="flag">
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}"/>
					<script>
						alert(document.getElementById('mes').value);
					</script>
				</logic:notEmpty>
				<logic:empty name="mes">
					<script language="javascript">
						alert("操作成功！");
					</script>
				</logic:empty>
				<script language="javascript">				
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>

			<logic:notEqual value="true" name="flag">
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}"/>
					<script>
						alert(document.getElementById('mes').value);
					</script>
				</logic:notEmpty>
			<logic:empty name="mes">
				<script language="javascript">
					alert("操作失败！");
				</script>
			</logic:empty>
			<script language="javascript">
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
			</logic:notEqual>
		</logic:notEmpty>
		<!--end操作提示信息-->
		
		<!--审核条件限制提示-->
		<logic:equal value="view" name="result">
			<logic:present name="hasSQ">
				<logic:match value="have" name="hasSQ">
					<script language="javascript">
			         	alert("该学生已有申请项目通过审核！");
			         	Close();
						window.dialogArguments.document.getElementById('search_go').click();
	         		</script>
				</logic:match>
				<logic:match value="notHave" name="hasSQ">
					<script language="javascript">
			         	alert("操作成功！");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();
	         		</script>
				</logic:match>
			</logic:present>
		</logic:equal>
		<!--end审核条件限制提示-->

		<!--人数限制信息提示-->
		<logic:equal value="full" name="result">
			<script>
				alert("人数已满！");
			</script>
		</logic:equal>
		<logic:equal value="knsfull" name="result">
			<script>
				alert("困难生人数已满！");
			</script>
		</logic:equal>
		<!--end人数限制信息提示-->
	</body>
</html>
