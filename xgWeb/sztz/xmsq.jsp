<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
		<script type="text/javascript">
			jQuery(function(){
				jQuery('#xmid').combogrid({
					panelWidth:255,
					value:'${rs.id}',
					idField:'id',
					textField:'xmmc',
					url:'sztzAjax.do?method=initXmList',
					columns:[[
						{field:'xmmc',title:'项目名称',width:85},
						{field:'kmmc',title:'所属科目',width:85},
						{field:'hxnlmc',title:'核心能力',width:85}
					]],
					onChange:function(n,o){
						var xh = jQuery('#xh').val();
						var xmid = jQuery(this).combogrid('getValue');
						
						refreshForm('sztz.do?method=xmsq&doType=${sztzActionForm.doType}&xh='+xh+'&xmid='+xmid);
					}
				})
			});
		</script>
	</head>
	<body>
		<html:form action="/sztz" method="post">
			<input type="hidden" id="url" name="url" value="/sztz.do?method=xmsq&doType=${sztzActionForm.doType }" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-nj-xymc-zymc-bjmc" />	
			<html:hidden property="shlcid" value="${rs.shlcid }"/>	
			<input type="hidden" name="temp_xmid" value="${rs.id }" id="temp_xmid"/>
		
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button onclick="saveUpdate('sztz.do?method=xmsqSave','xh-temp_xmid')">
										保 存
									</button>
									<logic:notEqual value="add" name="sztzActionForm" property="doType">
										<button onclick="saveUpdate('sztz.do?method=xmsqSave&sftj=是','xh-temp_xmid')">
											提 交 
										</button>
										<button type="reset">
											重 置
										</button>
										<html:hidden property="shzt" value="未审核"/>
									</logic:notEqual>
									<logic:equal value="add" name="sztzActionForm" property="doType">
										<html:hidden property="shzt" value="通过"/>
										<button onclick="window.close();return false;">
											关 闭
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									申请信息
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td width="34%">
								<logic:equal value="stu" name="userType">
									<html:text property="xh" styleId="xh" value="${stu.xh}" readonly="true"/>
								</logic:equal>
								<logic:notEqual value="stu" name="userType">
									<html:text property="xh" styleId="xh" value="${stu.xh}" 
										onkeypress="autoFillStuInfo(event.keyCode,this)"
										onblur="chkIsStu(this,'view_xsjbxx','xh')"/>	
									<logic:notEqual value="modi" name="oper">
										<button onclick="showTopWin('stu_info.do',800,600);"
											class="btn_01" id="buttonFindStu">
											选择
										</button>
									</logic:notEqual>						
								</logic:notEqual>		
			            	</td>
			            	<th width="16%">
			            		<font color="red">*</font>项目名称
							</th>
							<td width="34%">
								<html:select property="xmid" styleId="xmid">
									
								</html:select>
			            	</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td>
								${stu.xm }
							</td>
							<th>
								学年
							</th>
							<td>
								${rs.xn }
							</td>
						</tr>
						
						<tr>
							<th>
								性别
							</th>
							<td>
								${stu.xb }
							</td>
							<th>
								学期
							</th>
							<td>
								${rs.xqmc }
							</td>
						</tr>
						
						<tr>
							<th>
								年级
							</th>
							<td>
								${stu.nj }
							</td>
							<th>
								所属科目
							</th>
							<td>
								${rs.kmmc }
							</td>
						</tr>
						
						<tr>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								${stu.xymc }
							</td>
							<th>
								核心能力
							</th>
							<td>
								${rs.hxnlmc }
							</td>
						</tr>
						
						<tr>
							<th>
								专业
							</th>
							<td>
								${stu.zymc }
							</td>
							<th>
								主办方
							</th>
							<td>
								${rs.zbf }
							</td>
						</tr>
						
						<tr>
							<th>
								班级
							</th>
							<td>
								${stu.bjmc }
							</td>
							<th>
								负责人
							</th>
							<td>
								${rs.fzr }
							</td>
						</tr>
						<tr>
							<th>
								举办时间
							</th>
							<td>
								${rs.jbkssj } 至 ${rs.jbjssj }
							</td>
							<th>
								基础分
							</th>
							<td>
								${rs.jcf }
							</td>
						</tr>
						<tr>
							<th>
								参与角色
							</th>
							<td>
								<html:radio value="参与" property="cyjs">参与</html:radio>
								<html:radio value="组织" property="cyjs">组织</html:radio>
							</td>
							<th>
								是否重修
							</th>
							<td>
								<html:radio value="是" property="sfcx">是</html:radio>
								<html:radio value="否" property="sfcx">否</html:radio>
							</td>
						</tr>
						
						<tr>
							<th>
								成果描述<br/>
								<font color="red">&lt;限400字&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="cgms" style="width:85%" rows="5" onblur="checkLen(this,400)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								备注<br/>
								<font color="red">&lt;限400字&gt;</font>
							</th>
							<td colspan="3"  style="word-break:break-all;">
								<html:textarea property="bz" style="width:85%" rows="5" onblur="checkLen(this,400)"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<logic:present name="message">
				<script defer>
					alert('${message}');
					if (window.dialogArguments) {
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					}
				</script>
			</logic:present>
			
		</html:form>
	</body>
</html>
