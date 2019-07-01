<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
            function dclxChange(target){
                if(target.value == "01"){
                    jQuery("#yhxx").hide();
                } else {
                    jQuery("#yhxx").show();
                }
            }
            jQuery(function(){
                dclxChange(document.getElementById("dclb"));
            });
		</script>
	</head>
	<body>
		<html:form action="/dkbc_bcjg" method="post" styleId="bcjgForm">
			<html:hidden property="id" />
			<div style='tab;width:100%;height:460px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>贷款补偿</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>申请学年</th>
							<td>
								${dkbcSqjgForm.xn}
							</td>
							<th>资助类型</th>
							<td>
								<html:select property="dclb" styleId="dclb" onchange="dclxChange(this);" disabled="true" >
									<html:options collection="dclblist" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr id="yhxx">
							<th>经办银行</th>
							<td>
								${dkbcSqjgForm.yhmc }
							</td>
							<th>贷款合同号</th>
							<td>
									${dkbcSqjgForm.dkhth }
							</td>
						</tr>
						<tr>
							<th>申请代偿金额(元)</th>
							<td colspan="3">
									${dkbcSqjgForm.dcje}
							</td>
						</tr>
						<tr>
							<th>就业单位名称</th>
							<td>
								${dkbcSqjgForm.dwmc }
							</td>
							<th>人力资源部电话</th>
							<td>
								${dkbcSqjgForm.dwdh }
							</td>
						</tr>
						<tr>
							<th>就业单位地址</th>
							<td>
								${dkbcSqjgForm.dwdz}
							</td>
							<th>服务年限</th>
							<td>
									${dkbcSqjgForm.fwnx}
							</td>
						</tr>
						<tr>
							<th>行业性质(类型)</th>
							<td>
									${dkbcSqjgForm.hyxz}
							</td>
							<th>职务名称</th>
							<td>
									${dkbcSqjgForm.zwmc}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="filepath" styleId="filepath"/>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									var gid = jQuery('#filepath').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								});
							</script>
						</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>拨付明细</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table style="width:100%;text-align: center;">
									<tbody>
										<tr>
											<th style="text-align: center;">代偿金额</th>
											<th style="text-align: center;">拨付时间</th>
											<th style="text-align: center;">拨付去向</th>
											<th style="text-align: center;">利息补偿</th>
											<th style="text-align: center;">补偿时间</th>
										</tr>
										<logic:present name="bfxxList">
											<logic:iterate id="b" name="bfxxList">
												<tr>
													<td>${b.dcje }</td>
													<td>${b.bfsj }</td>
													<td>${b.bfqxmc }</td>
													<td>${b.lxbc }</td>
													<td>${b.bcsj }</td>
												</tr>
											</logic:iterate>
										</logic:present>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 5px;"></div>
			<table  width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" type="button" onclick="iFClose();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>

