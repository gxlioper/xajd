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
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xszbb/comm/js/comm.js"></script>
		<script type="text/javascript">
	jQuery(function() {

		jQuery("#shlccx").load(
				"comm_spl.do?method=lccx&sqid=${model.bbsqid}&tt="
						+ new Date().getTime());
		displayBbyhk();
		displayHcDdAndSj();
	});

	function displayBbyhk(){
		var bbyhk = jQuery("#xszbblxdm").val();
		//补办优惠卡开启
		if (bbyhk == "001"){
			jQuery('.bbyhk').css("display","");
		} else {
			//关闭
			jQuery('.bbyhk').css("display","none");
		}
	}
</script>
	</head>
	<body>

		<html:form action="/rcsw_xszbb_bbshgl" method="post"
			styleId="xszbbshForm">
			<html:hidden property="bbsqid" styleId="bbsqid" />
			<input type="hidden" name="xszbblxdm" id="xszbblxdm" value="${rs.xszbblxdm}" />
			<input type="hidden" name="sfbbhcyhk" id="sfbbhcyhk" value="${rs.sfbbhcyhk}" />

			<div style="height:440px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>

					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>申请补办类型</th>
							<td>
								${rs.xszbblxmc}
							</td>
							<logic:equal value="11400" name="xxdm">
								<th>
							   </th>
							   <td>
							   </td>
							</logic:equal>
							<logic:notEqual value="11400" name="xxdm">
								<th>
									<font class="bbyhk" style="display: none">是否补办火车优惠卡</font>
								</th>
								<td>
									&nbsp;&nbsp;<font class="bbyhk" style="display: none">${rs.sfbbhcyhk}</font>
								</td>
							</logic:notEqual>
							
					    </tr>
					    <logic:equal value="12684" name="xxdm">
					     <tr class="bbyhk bbyhk_sjdd" style="display:none">
					     	<th>时间</th>
							<td>
								${rs.sj}
							</td>
							<th>地点</th>
							<td>
							${rs.dd}	
							</td>
					     </tr>
					     </logic:equal>
					     <logic:equal value="13011" name="xxdm">
						     <logic:equal name="rs" property="sfbbhcyhkmc" value="y">					     
							    <tr id="qj">						     	
							     	<th>
							     		乘车区间
							     	</th>					     	
							     	<td colspan="3">
							     		${rs.ccqdz}～${rs.cczdz}
							     	</td>						     							     	
							    </tr>
						     </logic:equal>
						  </logic:equal>
						  <logic:equal value="10695" name="xxdm">
						     <logic:equal name="rs" property="sfbbhcyhkmc" value="y">					     
							    <tr id="qj">						     	
							     	<th>
							     		乘车区间
							     	</th>					     	
							     	<td colspan="3">
							     		${rs.ccqdz}～${rs.cczdz}
							     	</td>						     							     	
							    </tr>
						     </logic:equal>
						  </logic:equal>
					    <tr>
							<th>申请时间</th>
							<td>
								${rs.sqsj}
							</td>
							<th>
							   	审核状态
							</th>
							<td >
								${rs.shztmc}
							</td>
					    </tr>
			      		 <tr>
							<th>申请理由</th>
							</th>
							<td colspan="3"> 
								${rs.sqly}
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${rs.filepath}";
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
				<table width="100%" border="0" class="formlist">

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

