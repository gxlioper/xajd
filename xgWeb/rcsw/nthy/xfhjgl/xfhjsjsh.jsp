<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" defer="defer">

	</script>	
</head>

<body onload="" >	
    <html:form action="/rcsw_nthy_xfhjgl.do">
    <input type="hidden" name="pk" value="${pk }"/>
    <input type="hidden" name="num" value="${num }"/>
    <input type="hidden" name="doType" value="${doType }"/>
    
    
		<div class="open_win">
			<logic:equal value="dg" name="doType">
			<table width="100%" border="0" class="formlist" >
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
								<logic:notEqual value="view" name="act">
									<button type="button" class="button2" id="btn_save" 
										onclick="refreshForm('rcsw_nthy_xfhjsjsh.do?act=save')">
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
								<span>学费缓交申请</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="34%">
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
							</td> 
							<th>
								申请时间
							</th>
							<td>
								${rs.sqsj }
							</td>
						</tr>
						<tr>
							<th>
							申请原因
							</th>
							<td colspan="3">
								<html:textarea property="sqyy" name="rs" styleId="sqyy" rows="4" style="width:500px" readonly="true"></html:textarea>
							</td>
						</tr>
						
						<logic:equal value="xy" name="userType">
						
							<logic:equal value="true" name="fdyQx">
										<tr>
							<th>
								<bean:message key="lable.xb" />审核意见
							</th>
							<td colspan="3">
								<html:textarea property="xyyj" name="rs" styleId="xyyj" rows="4" style="width:500px" readonly="true"></html:textarea>
							</td>
							
						</tr>
						<tr>
							<th>
								学校审核意见
							</th>
							<td colspan="3">
								<html:textarea property="xxyj" name="rs" styleId="xxyj" rows="4" style="width:500px" readonly="true"></html:textarea>
							</td>
							
						</tr>
									</logic:equal>
									<logic:notEqual value="true" name="fdyQx">
										<tr>
							<th>
								学校审核意见
							</th>
							<td colspan="3">
								<html:textarea property="xxyj" name="rs" styleId="xxyj" rows="4" style="width:500px" readonly="true"></html:textarea>
							</td>
							
						</tr>
									</logic:notEqual>
							
						
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
						<tr>
							<th>
								辅导员审核意见
							</th>
							<td colspan="3">
								<html:textarea property="fdyyj" name="rs" styleId="fdyyj" rows="4" style="width:500px" readonly="true"></html:textarea>
							</td>
							</tr>
							<tr>
						<th>
								<bean:message key="lable.xb" />审核意见
							</th>
							<td colspan="3">
								<html:textarea property="xyyj" name="rs" styleId="xyyj" rows="4" style="width:500px" readonly="true"></html:textarea>
							</td>
							</tr>
						</logic:notEqual>
						
						<tr>
							<th>
								<logic:equal value="xy" name="userType">
									<logic:equal value="true" name="fdyQx">
										辅导员审核
									</logic:equal>
									<logic:notEqual value="true" name="fdyQx">
										<bean:message key="lable.xb" />审核
									</logic:notEqual>
									</logic:equal>
								<logic:notEqual value="xy" name="userType">
									学校审核
								</logic:notEqual>
							</th>
							<td>
								<html:select property="shjg" style="width:90px" styleId="shjg">
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
									<html:option value="未审核">未审核</html:option>
								</html:select>
							</td>
							<th>
								
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
							审核意见
							</th>
							<td colspan="3">
								<html:textarea property="shyj"  styleId="shyj" rows="5" style="width:500px"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</logic:equal>
			<logic:notEqual value="dg" name="doType">
				<table width="100%" border="0" class="formlist" >
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									
									<button type="button" class="button2" id="btn_save" 
										onclick="refreshForm('rcsw_nthy_xfhjsjsh.do?act=save')">
										保 存
									</button>
						
									<button type="button" class="button2" onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="2">
								<span>学费缓交申请</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
							<th colspan="2">
							<div align="left"><font color="blue"><b>提示：当前审核的记录有${num }条</b></font></div>
							</th>
							
						</tr>
						<tr>
							<th width="30%">
								<logic:equal value="xy" name="userType">
									<logic:equal value="true" name="fdyQx">
										辅导员审核
									</logic:equal>
									<logic:notEqual value="true" name="fdyQx">
										<bean:message key="lable.xb" />审核
									</logic:notEqual>
									</logic:equal>
								<logic:notEqual value="xy" name="userType">
									学校审核
								</logic:notEqual>
							</th>
							<td width="70%">
								<html:select property="shjg" style="width:90px" styleId="shjg">
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
									<html:option value="未审核">未审核</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
							审核意见
							</th>
							<td >
								<html:textarea property="shyj"  styleId="shyj" rows="5" style="width:230px"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</logic:notEqual>
			
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
