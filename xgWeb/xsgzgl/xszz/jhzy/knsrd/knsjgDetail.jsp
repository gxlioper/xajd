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
		//保存审核状态
		function saveKnsrdSh(shzt){
			var message = "";
			if(shzt == "tg"){
				message = "您确认审核<font color='blue'>通过</font>该学生的困难生申请吗？";
			}else if(shzt == "btg"){
				message = "您确认审核<font color='blue'>不通过</font>该学生的困难生申请吗？";
			}
			
			confirmInfo(message,function(tag){
				if(tag=="ok"){
					
					//路径
					var url = "jhzy_knsrd.do?method=saveKnsrdSh";
					
					var tjdc = jQuery("[name=tjdc]:checked").eq(0).val();
					
					//参数
				 	var parameter = {
						"str_xh":escape(jQuery("#xh").val()),
						"str_xn":escape(jQuery("#xn").val()),
						"str_shzt":escape(shzt),
						"str_shyj":escape(jQuery("#shyj").val()),
						"str_tjdc":escape(tjdc)
					};
			
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.ajaxSetup({async:false});
					
					jQuery.post(url,
						parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result);
							closeWindown();		
						}
					);
			
					jQuery.ajaxSetup({async:true});
				}
			});
		}
		</script>
	</head>
	<body onload="" >
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>	
			<input type="hidden" id="xh" value="${xh }"/>
			<input type="hidden" id="xn" value="${xn }"/>
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tr style="height: 23px">
					<td align="center" colspan="4">
						<font size="5">
							${xn }学年困难生认定
						</font>
					</td>
				</tr>
			</table>
			
			<div style="width:100%;height:500px;overflow-x:hidden;overflow-y:auto;">
				<!-- 家庭情况调查 -->	
				<%@ include file="/xsgzgl/xszz/jhzy/xsjtknxx.jsp"%>
				<!-- 困难生申请信息 -->
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>困难生申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="20%">
								<font color="red">*</font>申请类别
							</th>
							<td align="left" width="" colspan="3">
								<logic:present name="knslbList">
									<logic:iterate name="knslbList" id="knslb">
										${knslb.num}：${knslb.mc }<br/>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th align="right" width="">
								<font color="red">*</font>申请理由
							</th>
							<td align="left" width="" colspan="3" style="word-break:break-all;width:99%">
								${map.sqly }
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 困难生审核信息 -->
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>困难生审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								各级审核情况
							</th>
							<td colspan="3">
								<table width="100%" border="0">
									<tbody>
										<tr>
											<td>
												审核级别
											</td>
											<td>
												审核人
											</td>
											<td>
												审核结果
											</td>
											<td>
												审核时间
											</td>
											<td>
												推荐档次
											</td>
											<td>
												审核意见
											</td>
										</tr>
										<tr>
											<td>
												班主任
											</td>
											<td>
												${map.bzrxm }
											</td>
											<td>
												${map.bzrsh }
											</td>
											<td>
												${map.bzrshsj }
											</td>
											<td>
												${map.bzrtjdc }
											</td>
											<td title="${map.bzrshyj }">
												${map.bzrshyjxs }
											</td>
										</tr>
										<tr>
											<td>
												辅导员
											</td>
											<td>
												${map.fdyxm }
											</td>
											<td>
												${map.fdysh }
											</td>
											<td>
												${map.fdyshsj }
											</td>
											<td>
												${map.fdytjdc }
											</td>
											<td title="${map.fdyshyj }">
												${map.fdyshyjxs }
											</td>
										</tr>
										<tr>
											<td>
												<bean:message key="lable.xb" />
											</td>
											<td>
												${map.xyxm }
											</td>
											<td>
												${map.xysh }
											</td>
											<td>
												${map.xyshsj }
											</td>
											<td>
												${map.xytjdc }
											</td>
											<td title="${map.xyshyj }">
												${map.xyshyjxs }
											</td>
										</tr>
										<tr>
											<td>
												学校
											</td>
											<td>
												${map.xxxm }
											</td>
											<td>
												${map.xxsh }
											</td>
											<td>
												${map.xxshsj }
											</td>
											<td>
												${map.xxtjdc }
											</td>
											<td title="${map.xxshyj }">
												${map.xxshyjxs }
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th width="">
								<font color="red">*</font>推荐档次
							</th>
							<td colspan="3">
								${map.xxtjdc }
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="Close();return false;">关 闭</button>					           
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