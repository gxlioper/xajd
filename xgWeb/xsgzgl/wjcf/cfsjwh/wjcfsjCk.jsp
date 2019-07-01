<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>	
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		//下载
		function fjxz(type){
			if(type=="sbfj"){
				var sbfj = $("sbfj").value;
				$("fjmc").value=sbfj;
				}
			if(type=="ssfj"){
				var ssfj = $("ssfj").value;
				$("fjmc").value=ssfj;
				}
			var url="wjcfCfshwh_cfsjwh.do?method=fjxz";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		</script>
	</head>
	<body >
		<html:form action="/wjcfCfshwh_cfsjwh" method="post" enctype='multipart/form-data'>	
		<input type="hidden" id="fjmc" name="fjmc" value=""/>
		<input type="hidden" id="sbfj" name="sbfj" value="${rs.fjmc}"/>
		<input type="hidden" id="ssfj" name="ssfj" value="${rs.ssfjmc}"/>
		<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
		<html:hidden property="cfid" value="${rs.cfid}" />	
		<div>	
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>学生信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
						学号
						</th>
						<td align="left" width="30%">
							${rs.xh }
						</td>
						<th align="right" width="20%">
							姓名
						</th>
						<td align="left" width="30%">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<th align="right">
							性别
						</th>
							<td align="left">
							${rs.xb}
						</td>
						<th align="right">
							年级
						</th>
							<td align="left">
							${rs.nj}
						</td>
					</tr>
					<tr>
						<th align="right">
							<bean:message key="lable.xb" />
						</th>
							<td align="left">
							${rs.xymc}
						</td>
						<th align="right">
							专业
						</th>
							<td align="left">
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<th align="right">
							班级
						</th>
						<td align="left">
							${rs.bjmc}
						</td>
						<th align="right">
							政治面貌
						</th>
							<td align="left">
							${rs.zzmmmc}
						</td>
						
					</tr>
					
					</tbody>
					</table>
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>处分上报信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
							处分学年
						</th>
						<td align="left" width="30%">
							${rs.xn }
						</td>
						<th align="right" width="20%">
							处分学期
						</th>
						<td align="left" width="30%">
							${rs.xqmc }
						</td>
					</tr>
					<tr>
						<th align="right">
							处分原因
						</th>
						<td align="left">
							${rs.cfyymc }
						</td>
						<th align="right">
							处分类别
						</th>
						<td align="left">
							${rs.cflbmc }&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqx" style="color: red">${cfqx }</span>
						</td>
					</tr>
					

					
					<tr>
						<th align="right">
							违纪时间
						</th>
						<td align="left">
							${rs.wjsj }
						</td>
						<th align="right">
							处理决定书面材料或附件
						</th>
						<td align="left">
							<logic:notEmpty name="rs" property="fjmc">
								<a  href="#" onclick="fjxz('sbfj');return false;" class="name">下载附件</a>
							</logic:notEmpty>
							<logic:empty name="rs" property="fjmc">
							无上传附件
							</logic:empty>
						</td>
					</tr>
					<tr>
						<th align="right">
							违纪事实经过
						</th>
						<td colspan="9" style="word-break:break-all;width:100%">
								${rs.wjssjg }
						</td>
					</tr>
										<tr>
						<th align="right">
							处分文号
						</th>
						<td align="left">
							${rs.cfwh }
						</td>
						<th align="right">
							处分时间
						</th>
						<td align="left">
							${rs.cfsj }
						</td>
					</tr>
					
						<tr>
						<th align="right">
							备注
						</th>
						<td colspan="9" style="word-break:break-all;width:100%" >
								${rs.bz }
						</td>
					</tr>
					</tbody>
					</table>
					<logic:notEmpty name="rs" property="sswh">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>处分申诉信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
							申诉文号
						</th>
						<td align="left" width="30%">
							${rs.sswh }
						</td>
						<th align="right" width="20%">
							申诉时间
						</th>
						<td align="left" width="30%">
							${rs.sssj }
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							申诉结果
						</th>
						<td align="left" width="30%">
							${rs.ssjg }
						</td>
						<logic:notEmpty name="rs" property="cfggw">
						<th align="right" width="20%">
							处分更改为
						</th>
						<td align="left" width="30%">
							${rs.cfggw }
						</td>
		
						</logic:notEmpty>
						<logic:empty name="rs" property="cfggw">
						<th align="right" width="20%">
						
						</th>
						<td align="left" width="30%">
		
						</td>
						</logic:empty>
					</tr>
					<tr>
					<th align="right">
							申述材料或附件
						</th>
						<td align="left" colspan="3">
							<logic:notEmpty name="rs" property="ssfjmc">
								<a  href="#" onclick="fjxz('ssfj');return false;" class="name">下载附件</a>
							</logic:notEmpty>
							<logic:empty name="rs" property="ssfjmc">
							无上传附件
							</logic:empty>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							申诉意见
						</th>
						<td colspan="9" style="word-break:break-all;width:100%">
							${rs.ssyj }
						</td>
				
					</tr>
					</tbody>
					</table>
					</logic:notEmpty>
					<logic:notEmpty name="rs" property="zzwh">
						<table width="100%" border="0" class="formlist">
							<thead>
								<tr>
									<th colspan="4">
										<span>处分终止信息</span>
									</th>
								</tr>
							</thead>
							<tbody>
						<tr>
							<th align="right" width="20%">
								终止文号
							</th>
							<td align="left" width="30%">
								${rs.zzwh }
							</td>
							<th align="right" width="20%">
								终止时间
							</th>
							<td align="left" width="30%">
								${rs.zzsj }
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								终止意见
							</th>
							<td colspan="9" style="word-break:break-all;width:100%">
								${rs.zzyj }
							</td>
						</tr>
						</tbody>
						</table>
					</logic:notEmpty>
					<logic:notEmpty name="rs" property="jcwh">
						<table width="100%" border="0" class="formlist">
							<thead>
								<tr>
									<th colspan="4">
										<span>处分解除信息</span>
									</th>
								</tr>
							</thead>
							<tbody>
						<tr>
							<th align="right" width="20%">
								<bean:message key="wjcf.text" />文号
							</th>
							<td align="left" width="30%">
								${rs.jcwh }
							</td>
							<th align="right" width="20%">
								<bean:message key="wjcf.text" />时间
							</th>
							<td align="left" width="30%">
								${rs.jcsj }
							</td>
						</tr>
						
						<tr>
							<th align="right" width="20%">
								<bean:message key="wjcf.text" />意见
							</th>
							<td colspan="9" style="word-break:break-all;width:100%">
								${rs.jcyj }
							</td>
					
						</tr>
						</tbody>
						</table>
					</logic:notEmpty>
					
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>已受处分情况</span>
								</th>
							</tr>
						</thead>
						
						<tr>
						<td colspan="4">
							<table class="formList" width="100%">
								<thead align="left">
									<tr align="left">
										<td ><b>学年</b></td>
										<td ><b>学期</b></td>
										<td><b>处分类别</b></td>
										<td ><b>处分原因</b></td>
										<td ><b>处分时间</b></td>
										<td ><b>处分文号</b></td>
									</tr>
								</thead>
								<tbody align="left">
							<logic:notEmpty name="yscfqkList">
							<logic:iterate name="yscfqkList" id="s">
										<tr  style="cursor:hand">
										<td >
												${s.xn}
											</td>
											<td >
												${s.xqmc}
											</td>
											<td >
												${s.cflbmc}
											</td>
											<td >
												${s.cfyymc}
											</td>
											<td>
												${s.cfsj}
											</td>
											<td>
												${s.cfwh}
											</td>
										</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="yscfqkList">
									<tr style="height:22px">
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
										</tr>
							</logic:empty>
							</tbody>
							</table>
							</td>
				</tr>
						</table>
						
			</div>
			<div style="height: 30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" id="below" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button"  onclick="Close();return false;" id="buttonClose">
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
