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
		refreshForm('qgzxZgdzdx.do?method=saveQgzxzx');
	}
	function print() {
		var xh = $('xh').value;
		var xn = $('xn').value;
		var nd = $('nd').value;
		var xq = $('xq').value;
		
		wjcfDataExport('qgzxZgdzdx.do?method=qgzxzxPrint&xh=' + xh+'&xn=' + xn + '&nd=' + nd+'&xq='+xq );
	}
	</script>
</head>
	<body>
		<html:form action="/qgzxZgdzdx.do" method="post">
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/qgzx/zgdzdx/qgzxzxsq.jsp" />
			<input type="hidden" name="xh" id="xh" value="<bean:write name='rs' property="xh" />"/>
			<input type="hidden" name="mes" id="mes" value="${hmdMember}"/>
			<input type="hidden" name="doType" id="doType" value="${doType}"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 勤工助学之星 - 勤工助学之星申请</a>
				</p>
			</div>
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
								<logic:notEqual value="modi" name="doType">
									<html:text name='rs' property="xh" styleId="xh" 
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</logic:notEqual>
								<logic:equal value="modi" name="doType">
									<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
								</logic:equal>								
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<th><span class="red">*</span>学号</th>
							<td>								
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>				
						<th><span class="red">*</span>年度</th>
						<td>
							<logic:notEqual value="modi" name="doType">
								<html:select property="nd" name="rs" styleId="nd">
								<html:options collection="xnList" property="nd" labelProperty="nd"/>
								</html:select>	
							</logic:notEqual>
							<logic:equal value="modi" name="doType">
								<html:select property="nd" name="rs" styleId="nd" disabled="true">
								<html:options collection="xnList" property="nd" labelProperty="nd"/>
								</html:select>
								<html:hidden property="nd" name="rs"/>
							</logic:equal>
							
						</td>		
					</tr>
					<tr>
						<th>姓名</th>
						<td>
							<bean:write name='rs' property="xm" />
						</td>
						<th><span class="red">*</span>学年</th>
						<td>
							<logic:notEqual value="modi" name="doType">
								<html:select property="xn" name="rs" styleId="xn">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</logic:notEqual>
							<logic:equal value="modi" name="doType">
								<html:select property="xn" name="rs" styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
								<html:hidden property="xn" name="rs"/>
							</logic:equal>							
						</td>
					</tr>
					<tr>
						<th>性别</th>
						<td>
							<bean:write name='rs' property="xb" />
						</td>
						<th><span class="red">*</span>学期</th>
						<td>
							<logic:notEqual value="modi" name="doType">
								<html:select property="xq" name="rs" styleId="xq">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>	
							</logic:notEqual>
							<logic:equal value="modi" name="doType">
								<html:select property="xq" name="rs" styleId="xq" disabled="true">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>	
								<html:hidden property="xq" name="rs"/>
							</logic:equal>
							
						</td>
					</tr>
					<tr>
						<th>班级</th>
						<td colspan="3">
							<bean:write name='rs' property="bjmc" />
						</td>						
					</tr>					
					<tr>
						<th>自我鉴定(0~300字)</th>
						<td colspan="3">
							<html:textarea name='rs' property='zwjd' styleId="zwjd" 
								  cols="80" rows='5' />
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				            <button type="button" class="button2" onclick="chkisDataExist('xh-xn-nd-xq')">
								保 存 申 请
							</button>
							<button type="button" class="button2"
								onclick="print()">
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
				<logic:present name="noPass">
					<script>
				    	alert("你没有参加校内勤工助学，暂时不能申请勤工助学之星！");
				    </script>
				</logic:present>
				<logic:notPresent name="noPass">
					<script>
				    	alert("申请失败！");
				    </script>
				  </logic:notPresent>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
