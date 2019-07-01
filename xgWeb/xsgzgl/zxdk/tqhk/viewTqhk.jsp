<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
	    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
	</head>
	<body>
		<html:form action="/zxdkHkjg" method="post" styleId="hkjgForm">
			<html:hidden property="id" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<logic:present name="khkList">
						<thead>
							<tr>
								<th colspan="4">
									<span>贷款信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4">
									<logic:equal value="10511" name="xxdm">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;">学年</th>
										<th style="text-align: center;">合同编号</th>
										<th style="text-align: center;">贷款总金额(元)</th>
										<th style="text-align: center;">累计放款金额(元)</th>
										<th style="text-align: center;">贷款期数</th>
										<th style="text-align: center;">贷款账号</th>
									</tr>
									<logic:iterate id="dkxx" name="khkList">
										<tr>
											<td>${dkxx.xn }</td>
											<td>${dkxx.htbh }</td>
											<td>${dkxx.dkje }</td>
											<td>${dkxx.fkze }</td>
											<td>${dkxx.dkqx }</td>
											<td>${dkxx.dkzh }</td>
										</tr>
									</logic:iterate>
								</table>
							</logic:equal>
							<logic:notEqual value="10511" name="xxdm">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;">学年</th>
										<th style="text-align: center;">合同编号</th>
										<th style="text-align: center;">贷款总金额</th>
										<th style="text-align: center;">累计放款金额</th>
										<th style="text-align: center;">贷款期限</th>
									</tr>
									<logic:iterate id="dkxx" name="khkList">
										<tr>
											<td>${dkxx.xn }</td>
											<td>${dkxx.htbh }</td>
											<td>${dkxx.dkje }</td>
											<td>${dkxx.fkze }</td>
											<td>${dkxx.dkqx }</td>
										</tr>
									</logic:iterate>
								</td>
							</tr>
						</tbody>
						</logic:notEqual>
					</logic:present>
					<thead>
						<tr>
							<th colspan="4">
								<span>提前还款申请</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>还款账号</th>
							<td>
								${zxdkHkjgForm.hkzh }
							</td>
							<th>还款金额</th>
							<td>
								${zxdkHkjgForm.hkje }
							</td>
						</tr>
						<tr>
							<th>还款标记</th>
							<td>
								${zxdkHkjgForm.hkbj }
							</td>
							<th>还款状态</th>
							<td>
								${zxdkHkjgForm.hkztmc }
							</td>
						</tr>
						<tr>
							<th>提前还款理由</th>
							<td colspan="3">
								${zxdkHkjgForm.hkly }
							</td>
						</tr>
						<tr>
							<th>备注</th>
							<td colspan="3">
								${zxdkHkjgForm.bz }
							</td>
						</tr>
						<logic:equal value="10511" name="xxdm">
						  <tr>
							<th>附件信息
								
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<html:hidden property="filepath" styleId="fjid"/>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid =  jQuery('#fjid').val();
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
						  </tr>
						</logic:equal>
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="saveDksq('zxdkHkjg.do?method=update');">
										保    存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
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