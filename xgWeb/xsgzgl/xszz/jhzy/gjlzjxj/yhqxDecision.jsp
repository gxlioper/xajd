<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//保存用户类型
		function saveUserType(){		
			var userType=jQuery("[name=yhqx]:checked").eq(0).val();				
			var url = "jhzy_knsrd.do?method=knsshSearch&userType="+userType;
			refreshForm(url);
		}
		</script>
	</head>
	<body onload="" >
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4"> 
								<span>您身兼辅导员和班主任两个岗位，请选择以何岗位进行接下去的审核工作</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr >
							<td>
								<input type="radio" name="yhqx" id="bzr" value="bzr" checked="checked"/>
								班主任【该岗位对学生申请记录进行第一级审核】
							</td>
						</tr>
						<tr >
							<td>
								<input type="radio" name="yhqx" id="fdy" value="fdy"/>
								辅导员【该岗位对学生申请记录进行第二级审核】
							</td>
						</tr>
						<tr >
							<td>
								注：切换过一次之后，就将固定住您所选择的身份，如果想进行另一身份的工作，请选择系统LOGO旁的身份切换
							</td>
						</tr>
					</tbody>		
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="saveUserType()" id="buttonSave">
										确定
									</button>			           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>