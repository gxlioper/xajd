<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xsgbgl/js/zwsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			function initShow(){
				var xxdm = jQuery("#xxdm").val();
				var xjlbdm = jQuery("#ydlbdm").val();
				if(xjlbdm == ""){
					jQuery("#xjlbmc").html("");
					jQuery("#sfyxj").html("");
					jQuery("#sfzx").html("");
					jQuery("#tzbj").hide();
					jQuery("#lrqzsj").hide();
					jQuery("#xzpd").hide();
					jQuery("#xxpd").hide();
				}else{
					jQuery.post("xjyd.do?method=xjydlbShpzGet",{values:xjlbdm},function(data){
						if(data != null){
							if(data["sftjbj"]=='0'){
								jQuery("#tzbj").show();
							}else{
								jQuery("#tzbj").hide();
							}

							if(data["lrqzsj"]=='1'){
								jQuery("#lrqzsj").show();
							}else{
								jQuery("#lrqzsj").hide();
							}
							if("10511" == xxdm) {
								if(data["xzsfkq"]=='1'){
									jQuery("#xzpd").show();
								}else{
									jQuery("#xzpd").hide();			
								}
								if(data["xxsfkq"]=='1') {
									jQuery("#xxpd").show();
								}else{
									jQuery("#xxpd").hide();				
								}
							}
						}
					},'json');
				}
			}
			jQuery(function(){
				initShow();
			});
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/xjydsq" method="post" styleId="xjydsqForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="ydlbdm" styleId="ydlbdm"/>
			<html:hidden property="shzt" styleId="shzt"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>学籍异动申请信息</span> 
							</th>
						</tr>
					</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							学籍异动类别
						</th>
						<td align="left">
							${xjydsqForm.ydlbmc }
						</td>
						<th align="right">
							学年/学期
						</th>
						<td align="left">
							${xjydsqForm.xn} ${xjydsqForm.xqmc}
						</td>
					</tr>
					<tr>
						<th>学籍状态[异动]</th>
						<td colspan="3">
							<div >
								<table border="0" style="float:left">
									<tr>
										<th style="width:70px">原学籍类别</th>
										<td style="width:180px">${xjydsqForm.ydqxjlbmc }</td>
									</tr>
									<tr>
										<th>是否有学籍</th>
										<td>&nbsp;${xjydsqForm.ydqsfyxjmc }</td>
									</tr>
									<tr>
										<th>是否在校</th>
										<td>&nbsp;${xjydsqForm.ydqsfzxmc }</td>
									</tr>
								</table>
								<img style="float:left;margin:30px 10px" src='images/ssyd/to.gif' ></img>
								<table border="0"  style="float:left">
									<tr>
										<th style="width:90px">异动后学籍类别</th>
										<td style="width:180px" id="xjlbmc">${xjydsqForm.ydlbmc }</td>
									</tr>
									<tr>
										<th>是否有学籍</th>
										<td id="sfyxj">${xjydsqForm.ydhsfyxjmc }</td>
									</tr>
									<tr>
										<th>是否在校</th>
										<td id="sfzx">${xjydsqForm.ydhsfzxmc }</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr id="tzbj">
						<th>调整专业/班级</th>
						<td colspan="3">
							<div >
								<table border="0"  style="float:left">
									<tr>
										<th style="width:70px;height:20px;">原年级</th>
										<td style="width:180px" id="ydqnj">&nbsp;${xjydsqForm.ydqnj }</td>
									</tr>
									<tr>
										<th style="height:20px;">原<bean:message key="lable.xb" /></th>
										<td id="ydqxy">&nbsp;${xjydsqForm.ydqxymc }</td>
									</tr>
									<tr>
										<th style="height:20px;">原专业</th>
										<td id="ydqzy">&nbsp;${xjydsqForm.ydqzymc }</td>
									</tr>
									<tr>
										<th style="height:20px;">原班级</th>
										<td id="ydqbj">&nbsp;${xjydsqForm.ydqbjmc }</td>
									</tr>
								</table>
								<img style="float:left;margin:55px 10px" src='images/ssyd/to.gif' ></img>
								<table border="0" style="float:left">
									<tr>
										<th style="width:90px;height:20px;">异动后年级</th>
										<td style="width:180px">${xjydsqForm.ydhnj }
										</td>
									</tr>
									<tr>
										<th style="height:20px;">异动后<bean:message key="lable.xb" /></th>
										<td>${xjydsqForm.ydhxymc }
										</td>
									</tr>
									<tr>
										<th style="height:20px;">异动后专业</th>
										<td>${xjydsqForm.ydhzymc }
										</td>
									</tr>
									<logic:notEmpty name="xjydsqForm" property="ydhbjmc">
										<tr>
											<th style="height:20px;">异动后班级</th>
											<td>${xjydsqForm.ydhbjmc }
											</td>
										</tr>
									</logic:notEmpty>
								</table>
							</div>
						</td>
					</tr>
					<tr id="lrqzsj">
						<th>申请异动起止时间</th>
						<td colspan="3">
							${xjydsqForm.sqkssj }&nbsp;至&nbsp; ${xjydsqForm.sqjssj }
						</td>
					</tr>
					
					<logic:equal name="xxdm" value="10511">					
						<tr id="xzpd">
							<th align="right" width="10%">
								学制
							</th>
							<td align="left" colspan="3">
								${xjydsqForm.xz }
							</td>
						</tr>
						<tr id="xxpd">
							<th align="right">
								来源学校/去向学校
							</th>
							<td align="left" colspan="3">
								${xjydsqForm.xxmc }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								异动原因
							</th>
							<td align="left" colspan="3">
								${xjydsqForm.ydyymc }
							</td>
						</tr>
					</logic:equal>		
					<tr>
						<th align="right" width="10%">
							附件信息
						</th>
						<td colspan="3">
							<div id="commonfileupload-list-0" style="padding: 5px;"></div>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									var gid = "${xjydsqForm.filepath}";
									jQuery.MultiUploader_q({
										gid : gid,
										targetEl : 'commonfileupload-list-0'
										});
								});
							</script>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							申请理由&nbsp;					
						</th>
						<td colspan="3">
							${xjydsqForm.sqly }
						</td>
					</tr>
				</tbody>
				<logic:equal name="shzt" value="1">
					<thead>
						<tr>
							<th colspan="4">
								<span>学籍异动结果信息</span> 
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								学籍异动文号
							</th>
							<td>
								${xjydjg.xjydwh }
							</td>
							<logic:notEqual name="xxdm" value="10511">
								<th>
									学籍异动时间
								</th>
								<td>
									${xjydjg.xjydsj }							
								</td>
							</logic:notEqual>
							<logic:equal name="xxdm" value="10511">
								<th>
									学籍异动审核时间
								</th>
								<td>
									${xjydjg.xjydsj }							
								</td>
							</logic:equal>
						</tr>
						<logic:notEmpty name="xjydjg" property="ydhbjdm">
							<tr>
								<th>
									调整班级
								</th>
								<td colspan="3">
									<table border="0" style="float:left">
											<tr>
												<th style="width:90px">异动后年级</th>
												<td style="width:180px">${xjydjg.ydhnj }
												</td>
											</tr>
											<tr>
												<th style="height:20px;">异动后<bean:message key="lable.xb" /></th>
												<td>${xjydjg.ydhxymc }
												</td>
											</tr>
											<tr>
												<th style="height:20px;">异动后专业</th>
												<td>${xjydjg.ydhzymc }
												</td>
											</tr>
											<tr>
												<th style="height:20px;">异动后班级</th>
												<td>${xjydjg.ydhbjmc }
												</td>
											</tr>
										</table>
								</td>
							</tr>
						</logic:notEmpty>
						
						<logic:equal name="xxdm" value="10511">
						<logic:notEmpty name="xjydjg" property="xz">					
							<tr>
								<th align="right" width="10%">
									学制
								</th>
								<td align="left" colspan="3">
									${xjydjg.xz }
								</td>
							</tr>
						</logic:notEmpty>
						<logic:notEmpty name="xjydjg" property="lyqxxxdm">
							<tr>
								<th align="right">
									来源学校/去向学校
								</th>
								<td align="left" colspan="3">
									${xjydjg.xxmc }
								</td>
							</tr>
						</logic:notEmpty>
							<tr>
								<th align="right" width="10%">
									是否师范生
								</th>
								<td align="left">
									${xjydjg.sfsfs }
								</td>
								<th align="right">
									当前状态	
								</th>
								<td align="left">
									${xjydjg.dqztmc }	
								</td>
							</tr>
							<tr>
								<th align="right" width="10%">
									异动原因
								</th>
								<td align="left" colspan="3">
									${xjydjg.ydyymc }
								</td>
							</tr>
						</logic:equal>	
						
						<logic:notEmpty name="xjydjg" property="sqkssj">
							<tr>
								<th>
									异动起止时间
								</th>
								<td colspan="3">
									${xjydjg.sqkssj }&nbsp;至&nbsp; ${xjydjg.sqjssj }
								</td>
							</tr>
						</logic:notEmpty>
					</tbody>
				</logic:equal>					
				</table>
			</div>
			<div style="height:35px"></div>	
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
									关 闭
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

