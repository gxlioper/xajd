<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${XjsqshForm.ywid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${XjsqshForm.splc}&shid=${XjsqshForm.shid}");
			});
			//保存审核
			function saveSh(){
				var shzt = jQuery("#shjg").val();
				if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
					showAlert("请将必填项填写完整！");
					return false;
				}
				var url = "qjgl_xjsh.do?method=saveDgsh";
				ajaxSubFormWithFun("form",url,function(data){
					 if(data["message"]=="保存成功！"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
			    	 }else{
			    		 showAlert(data["message"]);
			    		}
					});
			}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/qjgl_xjsh">
		 <html:hidden property="ywid"/>
		 <input type="hidden" name="splc" id="splc" value="${XjsqshForm.splc}" />
		 <input type="hidden" name="xh" id="xh" value="${XjsqshForm.xjr}" />
		<div style="tab;width:100%;overflow-x:hidden;overflow-y:auto;">
		<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
				<thead>
						<tr>
							<th colspan="4">
								<span>请假信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="16%">
							学年
						</th>
						<td align="left"  width="34%">
							${data.xn}
						</td>
						<th align="right"  width="16%">
							学期
						</th>
						<td align="left"  width="34%">
							${data.xqmc}
						</td>
					</tr>
					<tr>
						<th align="right">
							请假天数
						</th>
						<td align="left">
							${data.qjts}&nbsp;&nbsp;天&nbsp;&nbsp;
						</td>
						<th align="right">
							请假类型
						</th>
						<td align="left">
							${qjlxmc}
						</td>
					</tr>
					<tr>
						<th align="right">
							请假开始时间
						</th>
						<td align="left">
							${data.kssj }
						</td>
						<th align="right">
							请假结束时间
						</th>
						<td align="left">
							${data.jssj }
						</td>
					</tr>
					<logic:equal name="xxdm" value="12727">
						<tr>
							<th align="right" width="10%">
								请假节次
							</th>
							<td colspan="3">
								${data.mdd }
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="12688">
						<tr>
							<th align="right" width="10%">
								请假节次
							</th>
							<td colspan="3">
								${data.mdd }
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="70002">
						<tr>
							<th align="right">
								校内校外
							</th>
							 <td align="left">
							 	${data.xnxw }
							 </td>
							 <th></th>
							 <td></td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="12866">
						<tr>
							<th align="right" width="10%">
								家长电话
								
							</th>
							<td colspan="3">
								${data.jzdh }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								备注&nbsp;
							</th>
							<td colspan="3">
								${data.bz}
							</td>
						</tr>
						</logic:equal>
					<tr>
						<th align="right">
							请假事由
						</th>
						<td colspan="3">
							${data.qjsy}
						</td>
					</tr>
					
<%-- 						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<a href="czxxDtjsDyxx.do?method=downLoadFile&dir=${data.filepath}" target="_blank" style="color:blue">点击下载查看</a>
							</td>
						</tr> --%>
					<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
							<div id="commonfileupload-list-0" style="padding: 5px;"></div>
							<html:hidden property="filepath" styleId="fjid" value="${data.filepath}"/>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									var gid = jQuery('#fjid').val();
									jQuery.MultiUploader_q({
										gid : gid,
										targetEl : 'commonfileupload-list-0'
										});
								});
							</script>
						</td>
						</tr>
				</tbody>
			</table>
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>销假信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="16%">
							实际请假天数
						</th>
						<td align="left"  width="34%">
							${XjsqshForm.sjqjts}天
						</td>
						<th align="right" width="16%">
							实际请假时间
						</th>
					   <td align="left"  width="34%">
						   		${XjsqshForm.sjkssj}
								~
								${XjsqshForm.sjjssj}
					   </td>
					</tr>
					<tr id="fjtr">
							<th>
								销假附件
							</th>
							<td colspan="3">
							<div id="commonfileupload-list-1" style="padding: 5px;"></div>
							<html:hidden property="xjfilepath" styleId="xjfilepath" />
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									var gid = jQuery('#xjfilepath').val();
									jQuery.MultiUploader_q({
										gid : gid,
										targetEl : 'commonfileupload-list-1'
										});
								});
							</script>
							</td>
						</tr>
					<tr>
						<th align="right"  width="16%">
							销假信息&nbsp;
						</th>
						<td colspan="3"  width="34%">
							${XjsqshForm.xjbz}
						</td>
					</tr>
					
				</tbody>
								<thead>
										<tr>
											<th colspan="4">
												<span>审批信息</span>
											</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="4" id="shlccx">
											
											</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th colspan="6">
												<span>审核信息</span>
											</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th >
												审核结果
											</th>
											<td id="shjgSpan" colspan="3" style="text-align:left">
												
											</td>
										</tr>
										
										<tr>
											<th width="20%">
												<font color="red">*&nbsp;</font> 审核意见
												<br />
												<font color="red">(限200字)</font>
											</th>
											<td colspan="3" style="text-align:left">
												<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xjsh&id=shyj" />
												<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
											</td>
										</tr>
									</tbody>	
			</table>
		</div>
		<div style="height:50px"></div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存"  onclick="saveSh();return false;">
									保 存
								 </button>
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
