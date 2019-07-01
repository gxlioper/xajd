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
							${xn }学年国家励志奖学金结果
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
								<span>国家励志奖学金申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						
						<tr>
						<th align="right" width="20%">
								成绩排名<br/>（名次/总人数）
							</th>
							<td align="left" width="30%" >
								${map.cjpm}/${map.bjzrs }
							</td>
							<th align="right" width="20%">
								考试情况
							</th>
							<td align="left" width="30%" >
								必修课${map.bxkms}（门）
								<br/>及格以上${map.jgms}（门）
							</td>
							
						</tr>
						<tr>
							<th align="right" width="20%">
								实行综合考评排名
							</th>
							<td align="left" width="30%" >
							${map.sxzhkppm }
							</td>
							<th align="right" width="20%">
								如是，排名<br/>（名次/总人数）
							</th>
							<td align="left" width="30%" >
								${map.zhkppm}/${map.bjzrs }
							</td>
						</tr>
						
						<tr>
							<td colspan="4">
							<table width="100%" border="0" class="formlist">
						<!-- 学生基本信息 begin-->
						<thead>
							<tr>
								<th colspan="3">
									<span>大学期间主要获奖情况</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							<th style="width:20%">
								<div align="center">获奖日期</div>
							</th>
							<th style="width:40%">
								<div align="center">获奖名称</div> 
							</th>
							<th style="width:40%">
								<div align="center">颁奖单位</div>
							</th>
							</tr>
							<tr>
								<td align="center">
								${map.hjsj1}
								</td>
								<td align="center">
									${map.hjmc1}
								</td>
								<td align="center">
									${map.bjdw1}
								</td>
							</tr>
							<tr>
								<td align="center">
									${map.hjsj2}
								</td>
								<td align="center">
								${map.hjmc2}
								</td>
								<td align="center">
								${map.bjdw2}
								</td>
							</tr>
							<tr>
								<td align="center">
									${map.hjsj3}
								</td>
								<td align="center">
									${map.hjmc3}
								</td>
								<td align="center">
									${map.bjdw3}
								</td>
							</tr>
							<tr>
								<td align="center">
									${map.hjsj4}
								</td>
								<td align="center">
									${map.hjmc4}
								</td>
								<td align="center">
									${map.bjdw4}
								</td>
							</tr>
						</tbody>
						</table>
							</td>
						</tr>
						
						<tr>
							<th align="right" width="20%">
							申请理由
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
								<span>国家励志奖学金审核信息</span>
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
											<th>
												审核级别
											</th>
											<th>
												审核人
											</th>
											<th>
												审核结果
											</th>
											<th>
												审核时间
											</th>
											<th>
												审核意见
											</th>
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
												${map.bzrshyj }
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
												${map.fdyshyj }
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
												${map.xyshyj }
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
												${map.xxshyj }
											</td>
										</tr>
									</tbody>
								</table>
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
		</html:form>
	</body>
</html>