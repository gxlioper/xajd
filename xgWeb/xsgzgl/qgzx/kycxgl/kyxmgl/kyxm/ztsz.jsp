<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/kyxmgl/kyxm/js/kyxm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			initFyxj();
			});
			function initFyxj(){
			var bxfy='0';//报销经费
			var zjfy='0';//追加经费
			var zjf = '0';//总经费
			jQuery.each(jQuery("#tbody_xmfy tr"),function(i,n){
					var fylb= jQuery(n).find("select[name=fylb] option:selected").val();;
					var fyje = jQuery(n).find("td").find("input[name=fyje]").val();
					if('1'==fylb){
						zjfy=parseFloat(bxfy)+parseFloat(fyje);
					}else{
						bxfy=parseFloat(zjfy)+parseFloat(fyje);
						}
			});
			zjf = parseFloat(bxfy)+parseFloat(zjfy);
			jQuery("#ybxjf").val(bxfy);
			jQuery("#zjjf").val(zjfy);
			jQuery("#xmhjzjf").val(zjf);
			}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/qgzx_kycxkyxmgl" method="post" styleId="KyxmglForm" onsubmit="return false;">
			<html:hidden property="xmid" styleId="xmid" value="${rs.xmid}"/>
			<input type="hidden" id="fyxxStr" name="fyxxStr"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
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
								项目编号
							</th>
							<td width="30%">
								${rs.xmbh}
							</td>
							<th width="20%">
								项目名称
							</th>
							<td width="30%">
								${rs.xmmc}
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
								依托实验室
							</th>
							<td width="30%">
								${rs.ytsys}
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
							<th>
								研究周期
							</th>
							<td width="30%" >
								${rs.yjzq}
							</td>
							<th width="20%">
								立项等级
							</th>
							<td width="30%" >
								${rs.lxdjmc}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>运行状态变更记录</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="99%" style="text-align: center;">
									<tr>
										<th style="text-align: center;">变更时间</th>
										<th style="text-align: center;">运行状态</th>
									</tr>
									<logic:present name="ztbgList">
										<logic:iterate id="z" name="ztbgList">
											<tr>
												<td>${z.bgsj }</td>
												<td>${z.mc }</td>
											</tr>
										</logic:iterate>
										<logic:empty name="ztbgList">
											<tr>
												<td colspan="5">未找到任何记录！</td>
											</tr>
										</logic:empty>
									</logic:present>
								</table>
							</td>
						</tr>
					</tbody>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>项目运行状态</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<th>运行状态</th>
							<td colspan="3">
							 <html:select property="yxzt" styleId="yxzt" style="width:150px">
									<html:options collection="yxztList" property="dm"
										labelProperty="mc" />
								</html:select>
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
							<button type="button" onclick="saveZtsz('save');">
										保存
									</button>
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

