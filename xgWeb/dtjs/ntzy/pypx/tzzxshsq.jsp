<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<title><bean:message key="lable.title" /></title>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
</head>
<body>
	
	<html:form action="/ntzy_pypx.do?method=tytgbsq" method="post">
	<!-- 评选项目-->
	<input type="hidden" name="mk" value="${mk }"/>
	<input type="hidden" name="save_mk" value="${mk }"/>
	<input type="hidden" id="tableName" name="tableName" value="view_czxx_tyxx"/>
	<input type="hidden" id="zd" name="zd" value="sfty"/>
	<input type="hidden" name="save_xh" value="${rs.xh }"/>
	<input type="hidden" id="va" name="va" value="是"/>
	<input type="hidden" id="lx" name="lx" value="团员"/>
	<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm"/>
	<input type="hidden" id="url" name="url" value="/xgxt/ntzy_pypx.do?method=tzzxshsq" />
	<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xh-xm" />
			
	<div class="title">
		<div class="tab_cur">
				<p class="location">
					<logic:equal value="tzzgb" name="mk"><em>您的当前位置:</em><a>优秀团总支干部申请</a></logic:equal>
					<logic:equal value="xshgb" name="mk"><em>您的当前位置:</em><a>优秀学生会干部申请</a></logic:equal>
				</p>
		</div>
	</div>
		<div class="tab">
		<table width="100%" border="0" class="formlist">
			<thead>
				<tr style="height:22px">
					<td colspan="12" align="center">
						<b>填写申请表</b>
					</td>
				</tr>
			</thead>
			
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          <logic:equal name="writeAble" value="yes">
				          <button type="button" class="button2" onclick="saveData('ntzy_pypx.do?method=tzzxshsq&doType=save','xh');">
							提交申请
						  </button>
					  </logic:equal>
			          	<button type="button" class="button2" onclick="location='pypxsq.do'">
							返	回
						</button>
			          </div></td>
			      </tr>
			   </tfoot>
			
			<tbody>
			<tr>
				<logic:notEqual name="userOnLine" value="student" scope="session">
					<th>
						<font color="red">*</font>学号：
					</th>
					<td align="left" width="30%">
						<html:text property="xh" styleId="xh" readonly="true" value="${rs.xh}"/>
							<button type="button" onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
								选择
							</button>
					</td>
				</logic:notEqual>
				
				<logic:equal name="userOnLine" value="student" scope="session">
					<th>
						<font color="red">*</font>学号：
					</th>
					<td align="left" width="30%">
						<html:text styleId="xh" property="xh"
							style="width:100%;heigh:100%" value="${rs.xh}" readonly="true" />
					</td>
				</logic:equal>
				
				<th>
					<div >
						学年：
					</div>
				</th>
				<td width="30%" align="left">
					<input type="hidden" name="save_xn" value="${xn }"/>
					${xn }
				</td>
			</tr>
			<tr>
				<th>
					<div >
						姓名：
					</div>
				</th>
				<td width="30%">
					${rs.xm }
				</td>
				<th>
					<div >
						性别：
					</div>
				</th>
				<td width="30%">
					${rs.xb }
				</td>
			</tr>
			<tr>
				<th>
					<div >
						民族：
					</div>
				</th>
				<td width="30%">
					${rs.mzmc }
				</td>
				<th>
					<div >
						出生年月：
					</div>
				</th>
				<td width="30%">
					${rs.csrq }
				</td>
			</tr>
			<tr>
				<th>
					<div >
						籍贯：
					</div>
				</th>
				<td width="30%">
					${rs.jg }
				</td>
				<th>
					<div >
						政治面貌：
					</div>
				</th>
				<td width="30%">
					${rs.zzmmmc }
				</td>
			</tr>
			<tr>
				<th>
					<div >
						院系：
					</div>
				</th>
				<td width="30%">
					${rs.xymc }
				</td>
				<th>
					<div >
						班级：
					</div>
				</th>
				<td width="30%">
					${rs.bjmc }
				</td>
			</tr>
			
			<tr>
				<th>
					<div >
						入团时间：
					</div>
				</th>
				<td width="30%">
					${rs.rtrq }
				</td>
				<th>
					<div >
						入党时间：
					</div>
				</th>
				<td width="30%">
					${rs.rtsj }
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">
					<div >
						手机：
					</div>
				</td>
				<td width="30%">
					${rs.sjhm }
				</td>
				<td width="20%" align="right">
					<div >
						职务：
					</div>
				</td>
				<td width="30%">
					${zw }
				</td>
			</tr>	
			
			
			<tr align="left" style="height:22px">
							<th>
								简历：
								<br />
							<font color="red">(限制在400字内)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_grjl' style="width:99%"
									onblur="checkLen(this,800)" rows='8'/>
							</td>
			</tr>
			<tr align="left" style="height:22px">
							<th>
								奖惩情况：
								<br />
								<font color="red">(限制在400字内)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_hjqk' style="width:99%"
									rows='8' onblur="checkLen(this,800)"/>
							</td>
						</tr>
						
			<tr align="left" style="height:22px">
							<th>
								主要表现：
								<br />
								<font color="red">(限制在1000字内)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_sqsm' style="width:99%"
									rows='8' onblur="checkLen(this,800)"/>
							</td>
						</tr>
						
				<tr align="left" style="height:22px">
							<th>
								备注：
								<br />
								<font color="red">(限制在400字内)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_bz' style="width:99%"
									rows='8' onblur="checkLen(this,400)"/>
							</td>
						</tr>
			</tbody>
		</table>	
		</div>	
			
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
		</script>
	</logic:present>
</body>
</html>
