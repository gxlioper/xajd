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
		
		function del(url){
			var RowsStr="!!";		
			for (i=0; i<document.getElementsByName("cbv").length; i++){
		    	if(document.getElementsByName("cbv")[i].checked){
		    		RowsStr+=document.getElementsByName("cbv")[i].value+"!!";
		    	}
			}
			
			if (RowsStr=="!!"){
				alert("请选择要批量设置的记录！");
				return false;
			}
			
			if (!confirm("确定要删除所选记录？")){
				return false;
			}
			refreshForm(url);
		}
	</script>
</head>
	<body onload="stringFormat(['zwjd'],50)">
		<html:form action="/qgzxZgdzdx.do" method="post">
			<input type="hidden" id="userType" name="userType" value="${userType }"/>
			<input type="hidden" name="mes" id="mes" value="${mes}"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 校外勤工助学 - 岗位申请审核</a>
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
								<span>学生勤工助学之星审核</span>
							</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<th>学号</th>
							<td>
								<bean:write name="rs" property="xh"/>
								<html:hidden property="xh" name="rs"/>
							</td>
							<th>学年</th>
							<td>
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
							<th>班级名称</th>
							<td>
								<bean:write name="rs" property="bjmc"/>
							</td>
							<th>学期</th>
							<td>
								${rs.xqmc}
								<html:hidden property="xq" name="rs"/>
							</td>
						</tr>	
						<tr>
							<th>自我鉴定</th>
							<td colspan="3" id="zwjd">
								<bean:write name="rs" property="zwjd"/>
							</td>							
						</tr>	
						<tr>
							<th>用人单位意见</th>
							<td colspan="3">
								<html:textarea property="szbmyj" name="rs" cols="80" onblur="chLeng(this,300)"/>
							</td>							
						</tr>	
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" />意见</th>
							<td colspan="3">
								<logic:equal value="xy" name="userType">
									<logic:equal value="true" name="yrdwUser">
										<html:textarea property="xyyj" name="rs" readonly="true" cols="80" />
									</logic:equal>		
									<logic:equal value="false" name="yrdwUser">
										<html:textarea property="xyyj" name="rs" cols="80" onblur="chLeng(this,300)"/>
									</logic:equal>	
								</logic:equal>
								<logic:notEqual value="xy" name="userType">
									<html:textarea property="xyyj" name="rs" readonly="true" cols="80" />
								</logic:notEqual>
							</td>					
						</tr>	
						<tr>
						  <th>学校意见</th>
						  <td colspan="3">
							  <logic:equal value="xx" name="userType">
									<html:textarea property="xxyj" name="rs" cols="80" onblur="chLeng(this,300)"/>
								</logic:equal>
							  <logic:notEqual value="xx" name="userType">
								<html:textarea property="xxyj" name="rs" readonly="true" cols="80"/>
							  </logic:notEqual>						  
						  </td>
					    </tr>
						<tr>
							<th>审核</th>
							<td colspan="3">
								<html:select property="yesNo" name="rs" styleId="yesNo">
									<html:option value=""></html:option>
									<html:options collection="chkList" property="en" labelProperty="cn" />
								</html:select>
							</td>						
						</tr>
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="4">
					          <div class="btn">
					            <button type="button" class="button2" id="btn_add"
									onclick="refreshForm('qgzxZgdzdx.do?method=qgzxzxshSave')"
									style="width:80px">
									保 存
								</button>
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
				</div>
				<div id="tmpdiv"></div>
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
					<script>
						alert(document.getElementById('mes').value);				
					</script>
				</logic:equal>	
			</logic:present>	
		
		</html:form>		
	</body>
</html>
