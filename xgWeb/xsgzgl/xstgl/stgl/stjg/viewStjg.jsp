<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			xsbjfj();
			jQuery("#shlccx").load(
					"comm_spl.do?method=lccx&sqid=$${model.stid}&tt="
							+ new Date().getTime());
		});
		//选择学生显示班级
		function xsbjfj(){
			var fzrlb='${rs.fzrlb}';
			if(fzrlb=="学生"){
				document.getElementById("bjmctr").style.display = "";
			}else{
				document.getElementById("bjmctr").style.display = "none";
			}
		}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/stglStjg" method="post" styleId="StjgForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>社团项目</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								社团项目名称
							</th>
							<td width="30%">
								${rs.stxmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								社团类别
							</th>
							<td width="30%">
								${rs.stlbmc}
							</td>
							<th width="20%">
								项目类别
							</th>
							<td width="30%">
								${rs.xmlbmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								有效学年
							</th>
							<td width="30%">
								${rs.xn}
							</td>
							<th width="20%">
								挂靠单位
							</th>
							<td width="30%">
								${rs.gkdw}
							</td>
						</tr>
						<!--
						<tr>
							<th width="20%">
								社团开始时间
							</th>
							<td width="30%">
								${rs.kssj}
							</td>
							<th width="20%">
								社团结束时间
							</th>
							<td width="30%">
								${rs.jssj}
							</td>
						</tr>
						  -->
						<tr>
							<th width="20%">
								负责人类别
							</th>
							<td width="30%">
								${rs.fzrlb}
							</td>
							<th width="20%">
								社团负责人
							</th>
							<td width="30%">
								${rs.stfzrxm}
							</td>
						</tr>
						<tr id='bjmctr'>
							<th width="20%">
								负责人所在学院
							</th>
							<td width="30%">
								${rs.fzrxy}
							</td>
							<th width="20%">
								负责人所在班级
							</th>
							<td width="30%">
								${rs.fzrbj}
							</td>
						</tr>
					<thead>
					<tr>
						<th colspan="4">
							<span>指导老师</span>

						</th>
					</tr>
					</thead>
					<tbody>
					<tr colspan="4">
						<td width="100%" colspan="4">
							<div width="100%" id="autotable">
								<table width="100%" id="tablebody">
									<tr>
										<th width="30%" style="text-align:left;">指导老师姓名</th>
										<th width="20%" style="text-align:left;">所属部门</th>
										<th width="20%" style="text-align:left;">联系电话</th>
										<th width="20%" style="text-align:left;">职称</th>
									</tr>
									<logic:iterate id="i" name="ZdlsInfoList">
										<tr name="deltr">
											<td><input name="zgh" type="hidden" value="${i.zgh}" style="width:90%"/><label name = "xm">${i.xm}</label></td>
											<td><input name="bmdm" type="hidden" value="${i.bmdm}" style="width:90%"/><label name = "bmmc">${i.bmmc}</label></td>
											<td><label name = "lxdh">${i.lxdh}</label></td>
											<td><input name="zc" type="hidden" value="${i.zc}" style="width:90%"/><label name = "zcmc">${i.zcmc}</label></td>
										</tr>
									</logic:iterate>
								</table>
							</div>
						</td>
					</tr>
						<tr>
							<th width="20%">
								社团联系电话
							</th>
							<td width="30%" >
								${rs.lxdh}
							</td>
							<th width="20%">
								建团人
							</th>
							<td width="30%">
								${rs.jtrxm}
							</td>	
						</tr>
						<tr>
							<th width="20%">
								社团成立时间
							</th>
							<td width="30%" >
								${rs.stclsj}
							</td>
							<th width="20%">
								申请时间
							</th>
							<td width="30%">
								${rs.sqsj}
							</td>	
						</tr>
					<logic:equal value="12872" name = "xxdm">
						<tr>
							<th width="20%">
								星级
							</th>
							<td width="30%">
									${rs.stxj}
							</td>
							<th width="20%">
							</th>
							<td width="30%">
							</td>
						</tr>
					</logic:equal>
						<tr>
							<th width="20%">
								社团简介
							</th>
							<td colspan="3">
								${rs.stsm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								社团获奖情况
							</th>
							<td colspan="3">
								${rs.sthjqk}
							</td>
						</tr>
						<tr>
							<th>
								申请计划书
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="fj" styleId="fjid" value="${rs.fj}"/>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = jQuery('#fjid').val();
										jQuery.MultiUploader_q({
											gid : gid
											});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th width="20%">
								入团审核流程
							</th>
							<td colspan="3">
								${splcname}
							</td>
						</tr>
					</tbody>
				 </table>
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
				
				</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

