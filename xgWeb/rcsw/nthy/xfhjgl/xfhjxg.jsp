<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			  function sub(){
				  	if (document.getElementById('xh').value==''||document.getElementById('sqyy').value=='') {
				  		alert("请将还*号的信息填写完整！");
				  		return false;
				  	}
					refreshForm('rcsw_nthy_xfhjxg.do?doType=save');
			  }
		</script>
	</head>

	<body onload="">
		<html:form action="/rcsw_nthy_xfhjgl.do" method="post">
			<input type="hidden" name="pkValue" value="${pkValue }"/>
			<div class="tab" >
				<table width="100%" border="0" class="formlist" >
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:notEqual value="view" name="act">
									<button type="button" class="button2" id="btn_save" 
										onclick="sub('rcsw_nthy_xfhjxg.do?doType=save','xh-xn-sqyy');">
										保 存
									</button>
									</logic:notEqual>
									<button type="button" class="button2" onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<logic:equal name="act" value="view" >
									<span>学费缓交信息</span>
								</logic:equal>
								<logic:notEqual name="act" value="view" >
									<span>学费缓交修改</span>
								</logic:notEqual>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td width="34%">
								<html:hidden property="xh" value="${rs.xh }"
									styleId="xh" />
								${rs.xh }
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								${rs.xm }
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
								年级
							</th>
							<td>
								${rs.nj }
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
							<th>
								专业
							</th>
							<td>
								${rs.zymc }
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
								
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								申请学年
							</th>
							<td>
								${rs.xn }
								<html:hidden property="xn" value="${rs.xn}"/>
							</td> 
							<th>
								申请时间
							</th>
							<td>
								${rs.sqsj }
								<html:hidden property="xn" value="${rs.sqsj}"/>
							</td>
						</tr>
						
						<tr>
							<th>
								辅导员审核
							</th>
							<td>
								${rs.fdysh }
							</td> 
							<th>
								<bean:message key="lable.xb" />审核
							</th>
							<td>
								${rs.xysh }
							</td>
						</tr>
						<tr>
							<th>
								学校审核
							</th>
							<td>
								${rs.xxsh }
							</td> 
							<th>
								
							</th>
							<td>
								
							</td>
						</tr>
						
						<tr>
							<th>
							<font color="red">*</font>申请原因
							</th>
							<td colspan="3">
								<html:textarea property="sqyy" styleId="sqyy" rows="5" style="width:500px"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				alert('${message}');
				if(window.dialogArguments){
			 		dialogArgumentsQueryChick();
			 		window.close();
			 	}
			</script>
		</logic:present>
	</body>
</html>
