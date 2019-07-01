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
			jQuery("#shlccx").load(
					"comm_spl.do?method=lccx&sqid=${model.xmdm}&tt="
							+ new Date().getTime());
		});
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xmsbXmsb" method="post" styleId="XmsbForm" onsubmit="return false;">
		
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>项目申报</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								${rs.xn}
							</td>
							</td>
							<th>学期</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								项目名称
							</th>
							<td width="30%">
								${rs.xmmc}
							</td>
							<th width="20%">
								项目级别
							</th>
							<td width="30%">
								${rs.xmjbmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								申报部门
							</th>
							<td width="30%">
								${rs.sbbmmc}
							</td>
							<th width="20%">
								所属科目
							</th>
							<td width="30%">
								${rs.sskmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								可参与人/团数
							</th>
							<td width="30%">
								<logic:equal name="rs" property="csms" value="1">个人   ${rs.kcyrs}人</logic:equal> 
								<logic:equal name="rs" property="csms" value="2">团体   ${rs.kcyrs}个</logic:equal> 
								
							</td>
							<th width="20%">
								项目开始时间
							</th>
							<td width="30%">
								${rs.xmkssj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								申报人
							</th>
							<td width="30%" >
								${rs.sbrxm}
							</td>
							<th width="20%">
								申报人联系方式
							</th>
							<td width="30%">
								${rs.lxdh}
							</td>																			
						</tr>
						<tr>
							<th width="20%">
								是否设立奖项
							</th>
							<td width="30%" >
								${rs.sfsljxmc}
							</td>
							<th width="20%">
								基础学分
							</th>
							<td width="30%" >
								${rs.jcxf}
							</td>
						</tr>
						<logic:equal value="13627" name="xxdm">
						<tr>
							<th width="20%">
								项目场地
							</th>
							<td width="30%" >
								${rs.xmcd}
							</td>
							<th width="20%">
								板块归属
							</th>
							<td width="30%" >
								${rs.bkgsmc}
							</td>
						</tr>
						</logic:equal>
				<tr>
							<th width="20%">
								项目奖项信息
							</th>
							<td width="30%"  colspan="3">
								<logic:notEmpty name="xmjxList">
								 <div style="overflow-y:auto;" id="jxDiv">
								 <table width="100%" border="0" class="formlist">
									<thead>
										<tr>
											<td width='15%'>奖项名称</td>
											<td width='15%'>附加学分</td>
											<td width='15%'>奖项顺序</td>
										</tr>
									</thead>
									<tbody id="tbody_xmjxxx">
									<logic:iterate id="i" name="xmjxList" indexId="index01">
										<tr>
										<input type="hidden" name="jxid" value='${i.jgid}'/>
										<td name="jxArr">${i.jxmc}</td>
										<td>${i.fjxf}</td>
										<td>${i.xssx}</td>
										</tr>
										</logic:iterate>
								</tbody>
								</table>
								</div>
								</logic:notEmpty>
								<logic:empty name="xmjxList">
								无
								</logic:empty>
							</td>
						</tr>
					<logic:equal value="11032" name="xxdm">
					<tr>
					<th>
						可参与学院
					</th>
					<td colspan="3">
						<logic:iterate id="t" name="xyList" indexId="index">
						<font style="width:100px;">
						${t.xymc}
						<%if((index+1)%5==0){%> </br> <%}%>
						</font>
						</logic:iterate>
					</td>
					</tr>
					</logic:equal>
					<tr><th width="20%">项目描述
								</th>
							<td colspan="3">
								${rs.xmms}
							</td>
					</tr>
					<tr><th width="20%">得/扣分依据
								</th>
							<td colspan="3">
								${rs.dkfyj}
							</td>
					</tr>
					<tr><th width="20%">参与要求
								</th>
							<td colspan="3">
								${rs.cyyq}
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

