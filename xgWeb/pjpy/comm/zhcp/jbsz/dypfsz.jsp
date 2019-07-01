<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//保存基本设置
		function saveJbsz(){
			if (confirm("确定你所选择的开关状态？")) {
				saveUpdate("/xgxt/zhcpJbsz.do?method=dypfsz&doType=save","");
			}
		}
		
		//显示批量设置层
		function showPlszDiv(){
			viewTempDiv("请确定所有班级的设置情况","plszDiv",300,100);
		}
		
		//批量设置
		function plsz(){
			
			var kgzt = $("plsz_kgzt").value;
			var num =  document.getElementsByName("bjdm").length;

			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("bjdm")[i];
				var bjdm = obj.value;	
				var yes_id = "rad_kgzt_yes_"+bjdm;
				var no_id = "rad_kgzt_no_"+bjdm;
				var kgzt_id = "hid_kgzt_"+bjdm;
				
				if(kgzt == "yes"){
					$(yes_id).checked=true;
					$(no_id).checked=false;
				}else{
					$(yes_id).checked=false;
					$(no_id).checked=true;
				}
				$(kgzt_id).value = kgzt;
			}
			
			hiddenMessage(true,true);
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zhcpJbsz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>		
			<div class="tab">		
				<table class="formlist" border="0" align="center">
				
					<!-- 评奖基本信息 -->
					<thead>
						<tr>
							<th colspan="4">
								<span>综测基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<th width="16%">
								评奖学年
							</th>
							<td width="34%">							
								${pjxn }
							</td>
							<th>
								综测周期
							</th>
							<td>
								<logic:equal name="zczq" value="xn">
									学年
								</logic:equal>
								<logic:equal name="zczq" value="xq">
									学期
								</logic:equal>		
							</td>
						</tr>
					</tbody>
					<!-- 评奖基本信息 end-->
					
					<!-- 教师基本信息 -->
					<thead>
						<tr>
							<th colspan="4">
								<span>用户基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<th width="16%">
								职工号
							</th>
							<td width="34%">							
								${rs.zgh }
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
								所在部门
							</th>
							<td>							
								${rs.bmmc }
							</td>
						</tr>
					</tbody>
					<!-- 教师基本信息 end-->
					
					<!-- 班级开关设置-->
					<thead>
						<tr>
							<th colspan="4">
								<span>评分设置</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<td colspan="4">							
								<div style="width:100%;height:200px;overflow-x:hidden;overflow-y:auto;">
									<table width="100%">
										<tr>
											<td>
												年级
											</td>
											<td>
												班级名称
											</td>
											<td>
												开关状态
											</td>
										</tr>
										<logic:iterate name="rs" property="bjList" id="bjInfo">
											<tr>
												<td>
													${bjInfo.nj }&nbsp;
												</td>
												<td>
													${bjInfo.bjmc }&nbsp;
													<logic:notEmpty name="bjInfo" property="bjdm">
														<input type="hidden" name="bjdm" value="${bjInfo.bjdm }"/>
													</logic:notEmpty>
												</td>
												<td>
													<!-- 学生申请 -->
													<logic:equal name="bjInfo" property="kgzt" value="yes">
														<input type="radio" name="rad_kgzt_${bjInfo.bjdm }" 
															id="rad_kgzt_yes_${bjInfo.bjdm }"
															onclick="$('hid_kgzt_${bjInfo.bjdm }').value=this.value"
															value="yes" checked="checked"/>
														学生申请
														<input type="radio" name="rad_kgzt_${bjInfo.bjdm }" 
															id="rad_kgzt_no_${bjInfo.bjdm }"
															onclick="$('hid_kgzt_${bjInfo.bjdm }').value=this.value"
															value="no"/>
														老师确认
													</logic:equal>
													<!-- 老师确认 -->
													<logic:equal name="bjInfo" property="kgzt" value="no">
														<input type="radio" name="rad_kgzt_${bjInfo.bjdm }" 
															id="rad_kgzt_yes_${bjInfo.bjdm }"
															onclick="$('hid_kgzt_${bjInfo.bjdm }').value=this.value"
															value="yes"/>
														学生申请
														<input type="radio" name="rad_kgzt_${bjInfo.bjdm }" 
															id="rad_kgzt_no_${bjInfo.bjdm }"
															onclick="$('hid_kgzt_${bjInfo.bjdm }').value=this.value"
															value="no" checked="checked"/>
														老师确认
													</logic:equal>
													<logic:notEmpty name="bjInfo" property="bjdm">
														<input type="hidden" name="kgzt" id="hid_kgzt_${bjInfo.bjdm }" value="${bjInfo.kgzt }"/>
													</logic:notEmpty>
												</td>
											</tr>
										</logic:iterate>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					<!-- 班级开关设置 end-->
					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="showPlszDiv();" id="buttonSetup" style="width: 80px">
										批量设置
									</button>
									<button type="button" onclick="saveJbsz()" id="buttonSave" style="width: 80px">
										保 存
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>	
			
			<!-- 自动分配Div-->
			<div id="plszDiv" style="display: none">
				<table class="formlist">
					<tbody>
						<tr>
							<td>
								<input type="radio" 
									onclick="$('plsz_kgzt').value=this.value"
									name="rad_kgzt" 
									value="yes" 
									checked="checked"/>
								学生申请
								<input type="radio" 
									onclick="$('plsz_kgzt').value=this.value"
									name="rad_kgzt" 
									value="no"/>
								老师确认
								<input type="hidden" id="plsz_kgzt" value="yes"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td>
								<div class="btn">
									<button type="button" onclick="plsz();">
										确 定
									</button>
									<button type="button" onclick="hiddenMessage(true,true);return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- 自动分配Div end-->
					
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>