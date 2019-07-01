<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function() {
		
				jQuery("#shlccx").load(
						"comm_spl.do?method=lccx&sqid=${model.sqid}&tt="
								+ new Date().getTime());
				autoSetZbnr();
			});
		
			function autoSetZbnr(){
				
				var nrids = jQuery("#nrids").val();
				var arr=nrids.split(",");
				for(var i=0;i<arr.length;i++){
					jQuery("table[name=knsrdzb]").find("tr[name=mytr]").each(function(){
						jQuery(this).find("tbody[name=tbody_knsrdzbnr]").find("tr").each(function(){
							if(jQuery(this).find("input[name=nrid]").val() == arr[i]){
								jQuery(this).find("input[name=zbnrid]").attr("checked","checked");
							}
						});
					});
				}
			}
		</script>
	</head>
	<body>

		<html:form action="/rcsw_xszbb_bbshgl" method="post"
			styleId="xszbbshForm">
			<input type="hidden" value="${nrids}" id="nrids"/>	
			<div style="overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
				</table>
				<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4">
						<span>困难生认定指标申请信息</span>
					</th>
				</tr>
			</thead>
				<tbody >
				<logic:notEmpty name="object">
					<logic:iterate name="object" id="s" indexId="i">
						<table width="100%" border="0" class="formlist" name="knsrdzb"
							style="margin: 2px auto;">
							<tr name="mytr">
								<input type='hidden' id='sxid' name='sxid'
									value="${s.key.sxid}" />
								<td width="20%">
									${s.key.sxmc}
								</td>
								<td width="55%">
									<table width="100%" border="0" class="datelist"
										id="knsrdzbnr" style="margin: 2px auto;">
										<thead>
											<tr>
												<td width="32px">

												</td>
												<td width="256px" style="text-align: center">
													指标内容
												</td>
												<td width="38px" style="text-align: center">
													分值
												</td>
												<td width="64px" style="text-align: center">
													审核分值
												</td>
											</tr>
											<tbody name="tbody_knsrdzbnr">
												<logic:iterate name="s" id="list" indexId="j"
													property="value">
													<tr id="knsrdzbxxnr">
														<td width="32px">
															<input type="hidden" name="nrid" value="${list.nrid}" />
															<logic:notEqual value="10052" name="xxdm" >
																<input type='checkbox' name="zbnrid" disabled="disabled"/>
															</logic:notEqual>
															<logic:equal value="10052" name="xxdm" >
																<input type='radio' name="zbnrid"  />
															</logic:equal>									
														</td>
														<td width="256px">
															${list.nrmc }
														</td>
														<td width="38px">
														${list.fz }
														</td>
														<td width="64px">
														${list.shfz }
														</td>
													</tr>
												</logic:iterate>
											</tbody>
										</thead>
									</table>
								</td>

								<td width="25%" colspan="2">
									<table width="100%" border="0" class="datelist"
										id="knsrdsqzbnr" style="margin: 2px auto;">
										<thead>
											<tr>
												<td>
													具体困难情况（学生录入）
												</td>
											</tr>
											<tbody>
												<tr >
													<td rowspan="5">
														${s.key.jtqk}
													</td>
												</tr>
											</tbody>
										</thead>
									</table>
								</td>

							</tr>
						</table>
					</logic:iterate>
				</logic:notEmpty>
			</tbody>
			</table>
				<logic:notEqual value="无需审核" name="shztmc">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>审核信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" id="shlccx">

								</td>
						    </tr>
						</tbody>
					</table>
				</logic:notEqual>

				</div>
				<div style="height: 30px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
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

