
<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/sztz" method="post">
			<html:hidden property="shlcid" value="${rs.shlcid }"/>	
			<html:hidden property="id" value="${rs.id }"/>	
		
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<logic:notEqual value="view" name="sztzActionForm" property="doType">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
								</logic:notEqual>
								<div class="btn">
									<logic:notEqual value="view" name="sztzActionForm" property="doType">
										<button onclick="saveUpdate('sztz.do?method=xmsqSave&sftj=是','')">
											提 交 
										</button>
									</logic:notEqual>
									<button onclick="window.close();return false;">
										关 闭
									</button>
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
								学号
							</th>
							<td width="34%">
								<html:text property="xh" styleId="xh" value="${rs.xh}" readonly="true"/>
			            	</td>
			            	<th width="16%">
			            		项目名称
							</th>
							<td width="34%">
								${rs.xmmc }
								<html:hidden property="xmid" value="${rs.xmid }"/>
			            	</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td>
								${rs.xm }
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
								${rs.xb }
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
								${rs.nj }
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
								${rs.xymc }
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
								${rs.zymc }
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
								${rs.bjmc }
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
								<html:radio value="参与" property="cyjs" name="rs">参与</html:radio>
								<html:radio value="组织" property="cyjs" name="rs">组织</html:radio>
							</td>
							<th>
								是否重修
							</th>
							<td>
								<html:radio value="是" property="sfcx" name="rs">是</html:radio>
								<html:radio value="否" property="sfcx" name="rs">否</html:radio>
							</td>
						</tr>
						
						<tr>
							<th>
								成果描述<br/>
								<font color="red">&lt;限400字&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="cgms" style="width:85%" rows="5" onblur="checkLen(this,400)" value="${rs.cgms }"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								备注<br/>
								<font color="red">&lt;限400字&gt;</font>
							</th>
							<td colspan="3"  style="word-break:break-all;">
								<html:textarea property="bz" style="width:85%" rows="5" onblur="checkLen(this,400)" value="${rs.bz }"></html:textarea>
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
