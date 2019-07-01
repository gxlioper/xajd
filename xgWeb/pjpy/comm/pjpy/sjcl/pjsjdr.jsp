<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
			function pjsjdr(){
				if($("sjdr").value==""){
					alert("请先选择需要导入的表！");
					return false;
				}
				impAndChkData();
			}
			
			function chanageDrb(){
				$("tableName").value=$("sjdr").value;
				$("realTable").value=$("sjdr").value;
			}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /> </a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/pjpy_comm_sjdr">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab">
				<!-- 评奖时间设置 -->
				<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>评奖数据导入</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center">
								导入表&nbsp;&nbsp;&nbsp;
								<html:select property="sjdr" styleId="sjdr" onchange="chanageDrb()"
											style="width:150px" >
											<html:option value="">----请选择----</html:option>
											<html:options collection="drbArr" property="en"
												labelProperty="cn" />
								</html:select>
								
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="pjsjdr()" id="dr">
										导 入
									</button>
									
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>
