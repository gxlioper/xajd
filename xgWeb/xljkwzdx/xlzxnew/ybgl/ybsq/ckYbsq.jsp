<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xljkwzdx/xlzxnew/ybgl/comm/js/comm.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript" defer="defer">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${YbsbForm.sbid}&tt="+new Date().getTime());
		})
		</script>
	</head>
	<body>
		<html:form action="/xlzxnew_ybsb" method="post" styleId="YbsbForm">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>上报信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
								<tr>
								<th width="15%">
									学院
								</th>
								<td width="35%">
									${xymc}
								</td>
								<th width="15%">
									填写人
								</th>
								<td width="35%">
									${txr}
								</td>
							</tr>
							<tr>
								<th>
									学年
								</th>
								<td>
									${YbsbForm.xn}
								</td>
								<th>
									月度
								</th>
								<td>
									${yf}
								</td>
							</tr>
							<logic:equal value="10704" name="xxdm">
								<tr>
									<th>
										是否有问题
									</th>
									<td>
										${YbsbForm.sfywt}
									</td>
								</tr>
							</logic:equal>
						<tr>
							<th>
								学院整体情况及<br />
								重大事件<br />
							</th>
							<td colspan="3" style="word-break: break-all;">
								${YbsbForm.ztqk}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>个别情况描述</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th colspan="6">
								<table width="100%" >
									<thead>
										<logic:notEqual value="10704" name="xxdm">
											<tr>
												<th width='15%' style="text-align:center">学号</th>
												<th width='10%' style="text-align:center">姓名</th>
												<th width='10%' style="text-align:center">班级</th>
												<th width='20%' style="text-align:center">情况说明</th>
												<th width='20%' style="text-align:center">干预措施</th>
												<th width='25%' style="text-align:center">干预后效果</th>
											</tr>
										</logic:notEqual>
										<logic:equal value="10704" name="xxdm">
											<tr>
												<th width='15%' style="text-align:center">学号</th>
												<th width='10%' style="text-align:center">姓名</th>
												<th width='18%' style="text-align:center">班级</th>
												<th width='10%' style="text-align:center">联系方式</th>
												<th width='12%' style="text-align:center">发生日期</th>
												<th width='10%' style="text-align:center">危机程度</th>
												<th width='20%' style="text-align:center">情况进程</th>
											</tr>
										</logic:equal>
									</thead>
									<tbody id="tablebody">
									<logic:iterate id="i" name="wtryInfo">
										<logic:notEqual value="10704" name="xxdm">
										<tr name='deltr'>
											<td style='text-align:center'><input name='xh' type='hidden' value='xh'/><label name = 'xhname'>${i.xh}</label></td>
											<td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
											<td style='text-align:center'><label name = 'bjmc'>${i.bjmc}</label></td>
											<td style='text-align:center'>${i.ybwtms}</td>
											<td style='text-align:center'>${i.ybgycs}</td>
											<td style='text-align:center'>${i.ybgyhjg}</td>
										</tr>
									</logic:notEqual>
									<logic:equal value="10704" name="xxdm">
										<tr name='deltr'>
											<td style='text-align:center'><input name='xh' type='hidden' value='xh'/><label name = 'xhname'>${i.xh}</label></td>
											<td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
											<td style='text-align:center'><label name = 'bjmc'>${i.bjmc}</label></td>
											<td style='text-align:center'>${i.sjhm}</td>
											<td style='text-align:center'>${i.wtfsrq}</td>
											<td style='text-align:center'>${i.ybwtms}</td>
											<td style='text-align:center'>${i.ybgyhjg}</td>
										</tr>
									</logic:equal>
									</logic:iterate>
									</tbody>
								</table>
							</th>
						</tr>
					</tbody>
					<thead>
										<tr>
											<th colspan="6">
												<span>审批信息</span>
											</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="6" id="shlccx">
											
											</td>
										</tr>
									</tbody>
					
					<logic:equal value="10704" name="xxdm">
					<tbody>
						<tr>
							<th align="right">
								附件信息
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${YbsbForm.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
						</tr>
						</tbody>
						</logic:equal>
					
				</table>
			</div>
			<div style="height:40px;"></div>
			<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="关 闭" onclick="iFClose();">
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

