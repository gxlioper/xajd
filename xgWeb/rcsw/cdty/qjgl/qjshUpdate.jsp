<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript">
			function dataSave(url,mustFill){
				var eles = mustFill.split("-");
				for (i = 0; i < eles.length; i++) {
					if (document.getElementById(eles[i]).value == "") {
						alertInfo("必填字段未填完整！");
						return false;
					}
				}
				
				$('buttonSave').disabled = "disabled";
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			
		</script>
	</head>
	<body>
		<html:form action="/zjjj_rcsw_qjgl" method="post">				
				<input type="hidden" name="pkValue" value="${param.pkValue }"/>
				<div class="tab">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="4"><span>学生请假信息</span></th>
							</tr>
						</thead>
						<tr>
							<th width="20%">
								<font color="red">*</font>学号
							</th>
							<td width="30%">
								${rs.xh }
							</td>
							<th width="20%">
								姓名
							</th>
							<td width="30%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								学年
							</th>
							<td>
								${rs.xn }
							</td>
							
							<th>
								学期
							</th>
							<td>
								${xqmc }
							</td>
						</tr>
						<tr>
							<th>
								院系
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
								家庭地址
							</th>
							<td>
								${rs.jtdz }
							</td>
						</tr>
						<tr>
							<th>
								联系方式
							</th>
							<td>
								
								${rs.sjhm }
								<logic:notEqual value="" name="rs" property="lxdh">
									<logic:notEqual value="" name="rs" property="sjhm">
									/
									</logic:notEqual>
								</logic:notEqual>
								${rs.lxdh }
							</td>
							<th>
								家长联系方式 
							</th>
							<td>
								${rs.jzdh }
							</td>
						</tr>
						<tr>
							<th>
								请假开始时间
							</th>
							<td>
								${rs.qjkssj }
							</td>
							<th>
								请假结束时间
							</th>
							<td>
								${rs.qjjssj }
							</td>
						</tr>
						<tr>
							<th>
								辅导员审核
							</th>
							<td>
								${rs.sh1 }
							</td>
							<th>
								辅导员审核人
							</th>
							<td>
								${rs.shr1 }
							</td>
						</tr>
						<tr>
							<th>
								班主任审核
							</th>
							<td>
								${rs.sh2 }
							</td>
							<th>
								班主任审核人
							</th>
							<td>
								${rs.shr2 }
							</td>
						</tr>
						<tr>
							<th>
								系书记审核
							</th>
							<td>
								${rs.sh3 }
							</td>
							<th>
								系书记审核人
							</th>
							<td>
								${rs.shr3 }
							</td>
						</tr>
						<tr>
							<th>
								学生处审核
							</th>
							<td>
								${rs.sh4 }
							</td>
							<th>
								学生处审核人
							</th>
							<td>
								${rs.shr4 }
							</td>
						</tr>
						
						<tr>
							<th>
								<font color="red">*</font>请假天数
							</th>
							<td>
								${rs.qjts }天
							</td>
							<th>
								请假期间是否住校
							</th>
							<td>
								${rs.sfzx }
							</td>
						</tr>
						
						<tr>
							<th>
								请假事由<br/>
								<font color="red">(限制录入200字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="qjsy" style="width: 95%;word-break:break-all;" readonly="true"
								 onblur="chLeng(this,200);" rows='5' value="${rs.qjsy}"/>
							</td>
						</tr>
						<tr>
							<th>
								备注<br/>
								<font color="red">(限制录入100字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="bz" style="width: 95%;word-break:break-all;"  onblur="chLeng(this,100);" readonly="true" value="${rs.bz}"
									rows='5' />
							</td>
						</tr>
						
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
								  <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
					          </div></td>
					      </tr>
					    </tfoot>
					</table> 
			</div>
		</html:form>
		
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript" defer="defer">
				alertInfo($('message').value);
			</script>
		</logic:present>
	</body>
</html>
