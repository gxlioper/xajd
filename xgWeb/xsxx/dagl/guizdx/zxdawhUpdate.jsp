<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		
		function save(){
			

			refreshForm("/xgxt/guizdxDagl.do?method=zxdawhUpdate&doType=save");
		
		}
		
		
		function checkCb(){
			var xsdaxx=document.getElementsByName("xsdaxx");
			var xsdaInfo=document.getElementsByName("xsdaInfo");
			
			for(i=0;i<xsdaxx.length;i++){
				for(j=0;j<xsdaInfo.length;j++){
					if(xsdaxx[i].value==xsdaInfo[j].value){
						xsdaInfo[j].checked=true;
						
					}
				}
			}
			
			if($("doType").value=="view"){
				var inputArr=document.getElementsByTagName("input");
				for(i=0;i<inputArr.length;i++){
					inputArr[i].disabled=true;
				}
				
			}
		
		}
	</script>
	</head>
	<body onload="checkCb()">

		<html:form action="/guizdxDagl" method="post">
			<input type="hidden" id="doType" name="doType" value="${doType}" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<logic:iterate name="xsdadmInfo" id="xsdadm">
				<input type="hidden" name="xsdaxx" id="xsdaxx_${index}"
					value="${xsdadm.dm}" />
			</logic:iterate>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
			</div>

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<th style="width:16%">
								<logic:notEqual name="doType" value="view">
									<font color="red">*</font>
								</logic:notEqual>
								学号
							</th>
							<td style="width:34%">
								${rs.xh}
								<input type="hidden" name="xh" id="xh" value="${rs.xh}" />
							</td>

							<th style="width:16%">
								姓名
							</th>
							<td style="width:34%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								性别
							</th>
							<td style="width:34%">
								${rs.xb}
							</td>

							<th style="width:16%">
								年级
							</th>
							<td style="width:34%">
								${rs.nj}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td style="width:34%">
								${rs.xymc}
							</td>

							<th style="width:16%">
								专业
							</th>
							<td style="width:34%">
								${rs.zymc}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								班级
							</th>
							<td style="width:34%">
								${rs.bjmc}
							</td>

							<th style="width:16%">

							</th>
							<td style="width:34%">

							</td>
						</tr>
					<thead>
						<tr>
							<th colspan="4">
								<span>在校档案信息</span>
							</th>
						</tr>
					</thead>
					<logic:iterate name="lxList" id="lx" indexId="index">
						<tr>
							<th>
								<html:checkbox property="xsdaInfo" styleId="lx_${index}"
									value="${lx.dm}" />
							</th>
							<td colspan="3">
								${lx.mc }
							</td>
						</tr>
					</logic:iterate>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<logic:notEqual name="doType" value="view">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								</logic:notEqual>
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button type="button" onclick="save();return false;">
											保 存
										</button>
									</logic:equal>
									<button type="button" onclick="Close();return false;">
										关 闭
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
