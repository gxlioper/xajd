<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/comm/js/comm.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/sq/js/tttzxm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
	
			jQuery(function(){
				calculateRs();
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${TttzxmForm.ttsqid}&tt="+new Date().getTime());
			});
		</script>
		<style type = "text/css">
			#xxdz{width:250px;}
			.fontstyl{float:left}
			.fontstyl1{margin-left:5px}
			#autotable table th{text-align:left} 
			#autotable table td{text-align:left} 
		</style>
	</head>
	<body>
		<html:form action="/ttxm_sq" method="post" styleId="TttzxmForm">
			<input type="hidden" id="xmdm" name="xmdm"/>
			<input type="hidden" name="xn"/>
			<input type="hidden" name="xq"/>
			<input type="hidden" id="usertype" value="${usertype}"/>
			<logic:equal name="usertype" value="stu" >
				<input type="hidden" name="dzxh" id="dzxh" value="${xsmap.xh}"/>
			</logic:equal>
			<logic:notEqual name="usertype" value="stu" >
				<input type="hidden" id="dzxh" name="dzxh"/>
			</logic:notEqual>
			<input type="hidden" name="splc" id="splc"/>
			<input type="hidden" name="xmdm" id="xmdm"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>项目信息</span>
                               <a  style="text-align: left;" onclick="showPfzmx(this,'orign');" class="up"
									href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
								</a>
							</th>
						</tr>
					</thead>
					<tbody id="autotbody">
						<tr>
							<th width="20%">项目名称</th>
							<td width="30%">
								${xmxxmap.xmmc}
							</td>
							<th width="20%">项目级别</th>
							<td width="30%" id="xmjbmc" >
							  	${xmxxmap.xmjbmc}
                             </td>
						</tr>
						<tr>
							<th>学年</th>
							<td id="xn" >
								${xmxxmap.xn}
							</td>
							<th>学期</th>
							<td id="xq" >
							    ${xmxxmap.xqmc}
							</td>
						</tr>
						<tr>
							<th>申报部门</th>
							<td id="sbbmmc" >
								${xmxxmap.bmmc}
							</td>
							<th>联系方式</th>
							<td id="lxdh" name="lxdh">
								${xmxxmap.lxdh}
							</td>
						</tr>
						<tr>
							<th>所属科目</th>
							<td id="sskmmc" name="sskmmc">
								${xmxxmap.sskmmc}
							</td>
							<th>基础学分</th>
							<td id="jcxf" name="jcxf">
								${xmxxmap.jcxf}
							</td>
						</tr>
						<tr>
							<th>可参与团队数</th>
							<td id="kcyrs" name="kcyrs">
								${xmxxmap.kcyrs}
							</td>
							<th>已申请团队数</th>
							<td id="sqrs" name="sqrs">
								${xmxxmap.sqrs}
							</td>
						</tr>
						<tr>
							<th>已通过团队数</th>
							<td id="tgrs" name="tgrs">
								${xmxxmap.tgrs}
							</td>
							<th>活动开始时间</th>
							<td id="xmkssj" name="xmkssj">
								${xmxxmap.xmkssj}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>成员信息 </span> 
									<label  id="cyrs" style="margin-left: 15px;margin-top: 4px;display: block;float: left;"></label>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr colspan="4">
							<td width="100%" colspan="4">
								<div width="100%" id="autotable">
								<table width="100%" id="tablebody">
									<tr>
										<th width="10%">角色</th>
									    <th width="20%">学号</th>
									    <th width="20%">姓名</th>
									    <th width="20%">学院</th>
									    <th width="30%">班级</th>
									</tr>
									<tr>
										<td>队长</td>
									    <td width="20%">${dzxxmap.xh}</td>
									    <td width="20%">${dzxxmap.xm}</td>
									    <td width="20%">${dzxxmap.xymc}</td>
									    <td width="20%">${dzxxmap.bjmc}</td>
									</tr>
									<logic:iterate id="i" name="dyzzlist">
										<tr>
										    <td>队员</td>
										    <td >${i.xh}</td>
										    <td >${i.xm}</td>
										    <td >${i.xymc}</td>
										    <td >${i.bjmc}</td>
										</tr>
									</logic:iterate>
								</table>
							</div>
							</td>
							
						</tr>
						<tr>
							
						</tr>
						<tr>
							<th width="20%">团队名称</th>
							<td colspan="3">
								${TttzxmForm.tdmc}
							</td>
						</tr>
						<tr>
							<th width="20%">申请理由</th>
							<td colspan="3">
								${TttzxmForm.sqly}
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
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
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