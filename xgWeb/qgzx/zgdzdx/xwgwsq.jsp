<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type="text/javascript">
	function getDw(){
		var pk = document.getElementById("xmdm").value;
		if(pk!=null && pk!=""){
		getOtherData.getDwmc(pk,function(data){
			document.getElementById("sqdw").value = data[0];
			document.getElementById("fzr").value = data[1];
			document.getElementById("gwlxdh").value = data[2];
		});
		}		
	}
	
	function chkisDataExist(obj){
		var value = obj.split("-");
		for(var i=0; i<value.length; i++){
			if(document.getElementById(value[i]).value==""){
				alert("请将带\*号的项目填写完整！");				
				return false;
			}
		}
		refreshForm('qgzxZgdzdx.do?method=saveGwsq');
	}
	</script>
</head>
	<body>
		<html:form action="/qgzxZgdzdx.co" method="post">
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/qgzx/zgdzdx/xwgwsq.jsp" />
			<input type="hidden" name="xh" id="xh" value="${rs.xh}"/>
			<input type="hidden" name="mes" id="mes" value="${hmdMember}"/>
			<input type="hidden" name="doType" id="doType" value="${doType}"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 校外勤工助学 - 申请岗位</a>
				</p>
			</div>
			<logic:present name="sjFlag">
				<script>
					alert('现在不在设置的申请时间之内，暂不开放申请！');
				</script>
			</logic:present>

			<logic:notPresent name="sjFlag">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<div class="tab">
				<table width="100%" id="rsT" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>填写申请表</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<th><span class="red">*</span>学号</th>
							<td>
								<logic:equal value="modi" name="doType">
								<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
								</logic:equal>
								<logic:notEqual value="modi" name="doType">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
								</logic:notEqual>
								
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<th><span class="red">*</span>学号</th>
							<td>								
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<th><span class="red">*</span>岗位名称</th>
						<logic:equal value="modi" name="doType">
							<td>
								<input type="hidden" id="isModi" name="isModi"
									value="<bean:write name="doType" scope="request"/>" />
								<input type="hidden" name="gwmc" id="gwmc"
									value="<bean:write name='rs' property='gwmc'/>"/>
								<html:select name="rs" property="gwmc" styleId="gwmc"
									style="width:150px" disabled="true" onchange="">
									<html:option value=""></html:option>
									<html:options collection="gwList" property="gwpk"
										labelProperty="gwmc" />
								</html:select>
							</td>
						</logic:equal>
						<logic:notEqual value="modi" name="doType">
							<td>
								<html:select name="rs" property="gwmc" styleId="gwmc"
									style="width:150px" onchange="">
									<html:option value=""></html:option>
									<html:options collection="gwList" property="gwpk"
										labelProperty="gwmc" />
								</html:select>
							</td>
						</logic:notEqual>
					</tr>
					<tr>
						<th>姓名</th>
						<td>
							<bean:write name='rs' property="xm" />
						</td>
						<th>年度</th>
						<td>
							<html:text name="rs" property="nd" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>性别</th>
						<td>
							<bean:write name='rs' property="xb" />
						</td>
						<th>学年</th>
						<td>
							<html:text name="rs" property="xn" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>年级</th>
						<td>
							<bean:write name='rs' property="nj" />
						</td>
						<th>学期</th>
						<td>
							<html:text name="rs" property="xq" readonly="true" />
						</td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<bean:write name='rs' property="xymc" />
						</td>
						<th><span class="red">*</span>联系电话</th>
						<td>
							<html:text name='rs' property="lxdh" styleId="lxdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</td>
					</tr>
					<tr>
						<th>专业</th>
						<td>
							<bean:write name='rs' property="zymc" />
						</td>
						<th><span class="red">*</span>可参加勤工助学时间</th>
						<td>
							<html:text name='rs' property="kcjqgzxsj" styleId="kcjqgzxsj" maxlength="100"/>
						</td>
					</tr>

					<tr>
						<th>班级</th>
						<td>
							<bean:write name='rs' property="bjmc" />
						</td>
						<th>是否贫困生</th>
						<td>
							<bean:write name='rs' property="sfpks" />
							<input type="hidden" name="sfpks" id="sfpks" />
						</td>
					</tr>					
					<tr>
						<th><span class="red">*</span>申请理由</th>
						<td colspan="3">
							<html:textarea name='rs' property='sqly' styleId="sqly"
								cols="80" rows='5' onblur="chLeng(this,250)"/>
						</td>
					</tr>
					<tr>
						<th>备注</th>
						<td colspan="3">
							<html:textarea name='rs' property='bz' styleId="bz"
								cols="80" rows='5' onblur="chLeng(this,150)"/>
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				            <logic:notEqual value="通过" property="xxsh" name="rs">
								<button type="button" class="button2" onclick="chkisDataExist('xh-gwmc-lxdh-kcjqgzxsj-sqly')">
									保 存 申 请
								</button>
							</logic:notEqual>
								<button type="button" class="button2"
									onclick="expAppTab('rsT','校外勤工助学岗位申请表','')">
									打 印 预 览
								</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
			</div>
			</logic:notEmpty>

			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
					<script>
				    	alert("申请成功！");
				    </script>
				</logic:equal>
				<logic:equal name="result" value="false">
				<logic:present name="hmdMember">
					<script>
				    	alert("你已经被列人勤工助学黑名单，无法申请岗位！");
				    </script>
				</logic:present>
				<logic:notPresent name="hmdMember">
					<script>
				    	alert("申请失败！");
				    </script>
				  </logic:notPresent>
				</logic:equal>
			</logic:notEmpty>
			</logic:notPresent>
		</html:form>
	</body>
</html>
