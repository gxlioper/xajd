<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/sjxmgl/fysb/js/fysb.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			jQuery("#xh").attr({readonly:"readonly"});
			jQuery("#jhcyrs").attr({readonly:"readonly"});
		});
		function initXmxx() {
			var xmid = jQuery("#xmid").val();
			var gotoPath = jQuery("#gotoPath").val();
			location.href=gotoPath+"&xmid="+xmid;
		}

		function ffcx() {
			var xmid = jQuery("#xmid").val();
			var url = "qgzx_kycxsjxmfysb.do?method=ffcx&xmid="+xmid;
			showDialog("历史发放查询", 800, 500, url);
		}
	
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/qgzx_kycxsjxmfysb" method="post" styleId="SjxmFysbForm" onsubmit="return false;">
		<input type="hidden" id="sbxxStr" name="sbxxStr"/>
		<input type="hidden" id="cjbz" name="cjbz" value='${csszMap.kzzd1 }'/>
		<input type="hidden" value="${gotoPath}" id="gotoPath"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;' id="xmDiv">
				<table width="100%" border="0" class="formlist" id ="tab_xmxx">
					<thead>
						<tr>
							<th colspan="4">
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						    <th width="20%">
								<font color="red">*</font>项目名称
							</th>
							<td width="30%">
								<html:select property="xmid" styleId="xmid" style="width:130px" onchange="initXmxx()">
									<html:options collection="sbxmList" property="xmid"
										labelProperty="xmmc" />
								</html:select>
							</td>
							<th width="20%">
								项目编号
							</th>
							<td width="30%">
								${rs.xmbh}
							</td>
						</tr>
						<tr>
							<th width="20%">
								项目属性
							</th>
							<td width="30%">
								${rs.xmsxmc}
							</td>
							<th width="20%">
								项目所属单位
							</th>
							<td width="30%">
								${rs.ssdwmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								项目负责人学号
							</th>
							<td width="30%">
								${rs.xh}
							</td>
							<th width="20%">
								项目负责人姓名
							</th>
							<td width="30%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								联系方式
							</th>
							<td width="30%">
								${rs.lxfs}
							</td>
							<th width="20%">
								计划参与人数
							</th>
							<td width="30%">
								${rs.jhcyrs}
							</td>
							
						</tr>
						<tr>
							<th width="20%">
								项目开始时间
							</th>
							<td width="30%">
								${rs.kssj}
							</td>
							<th width="20%">
								项目结束时间
							</th>
							<td width="30%">
								${rs.jssj}
							</td>																			
						</tr>
						<tr>
							<th width="20%">
								经费预算
							</th>
							<td width="30%" >
								${rs.jfys}
							</td>
							<th width="20%">
								批准经费
							</th>
							<td width="30%" >
								${rs.pzjf}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>发放汇总</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						    <th width="20%">
								已发放酬金
							</th>
							<td width="30%">
								${rs.yffje}
							</td>
							<th width="20%">
								剩余经费
							</th>
							<td width="30%">
								${rs.syjf}
							</td>
						</tr>
						</tbody>
						<thead>
						<tr>
							<th colspan="4">
								<span ><div>小组成员信息&nbsp;&nbsp;&nbsp;&nbsp;
								<font color='#0255B9'>发放年月</font>
								<html:select property="sbyf" styleId="sbyf" >
									<html:options collection="ffnyList" property="nydm"
										labelProperty="nymc" />
								</html:select>
								<button type="button" style="margin-left: 400px; font-weight:blod;" onclick="ffcx();">
										历史发放查询
									</button>
										</div>
								</span>
							</th>
						</tr>
					</thead>
				<tbody>
				<tr>
					<td colspan="4">
					<div class="con_overlfow">
					<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						
						<tr>
							<td width='15%'>学号</td>
							<td width='15%'>姓名</td>
							<td width='5%'>性别</td>
							<td width='15%'>学院</td>
							<td width='15%'>班级</td>
							<td width='8%'>项目中的分工</td>
							<td width='8%'>联系电话</td>
							<td width='10%'>工时</td>
							<td width='10%'>金额（元）</td>
						</tr>
					</thead>
					<tbody id="tbody_sbxx">
						<logic:iterate id="i" name="cyList" indexId="index01">
						<tr>
						<td name="xh">${i.xh}</td>
						<td>${i.xm}</td>
						<td>${i.xb}</td>
						<td>${i.xymc}</td>
						<td>${i.bjmc}</td>
						<td>${i.xmfg}</td>
						<td>
						${i.lxdh}
						</td>
						<td>
						<input type='text' name='gs' id='gs' maxlength='10' onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d)?(?:\d{2})?/ig,'$1$2$3');getCjje(this)"/>
						</td>
						<td>
						<input type='text' style="border:0px;" name='cjje' id='cjje' maxlength='10' readonly="readonly"/>
						</td>
						</tr>
						</logic:iterate>
						</tbody>
						</table>
						</div>
						</td>
						</tr>
						</tbody>
						<tbody>
				<tr>
					<td colspan="4">
				<div style=" width:100%; margin:0 auto; overflow-x:auto; height:35px; *padding-bottom:20px;">
				</div>
				</td>
				</tr>
				</tbody>
				 </table>
				 </div>
			 
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveFysb('save');">
										保存草稿
									</button>
									<button type="button" onclick="saveFysb('submit');">
										提交申请
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

