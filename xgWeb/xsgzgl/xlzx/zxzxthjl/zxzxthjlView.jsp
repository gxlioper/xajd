<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='js/calendar/calendar-setup.js'></script>
		<script type="text/javascript" src='xsgzgl/xlzx/zxzxthjl/js/zxzxthjlList.js'></script>
		<script type="text/javascript">
			jQuery(function(){
				var cbpgdm = jQuery('#cbpgdm').val();
				if(cbpgdm=="1"){
					jQuery("#ybwtlb1").hide();
					jQuery("#zajb1").hide();
				}else if(cbpgdm=="2"){
					jQuery("#ybwtlb1").show();
					jQuery("#zajb1").hide();
				}else if(cbpgdm=="3"){
					jQuery("#ybwtlb1").hide();
					jQuery("#zajb1").show();
				}
				jQuery("#zajbsmzx1").hide();
				jQuery("#cbpgjg1").hide();
				jQuery("#sfzj1").hide();
				
			var ybwtlb = jQuery("#ybwtlb").val();
				if(ybwtlb.length>0){
					var ybwtlbmc = ybwtlb.split(",");
					for ( var i = 0; i < ybwtlbmc.length; i++) {
								if( ybwtlbmc[i] == "建议咨询"){
									jQuery("#zajbsmzx1").show();
								}
					}
				}
				
			var zajb = jQuery("#zajb").val();
			if(zajb.length>0){
				var zajbmc = zajb.split(",");
				for ( var i = 0; i < zajbmc.length; i++) {
							if( zajbmc[i] == "初步评估"){
								jQuery("#cbpgjg1").show();
							}		
							if( zajbmc[i] == "建议咨询"){
								jQuery("#zajbsmzx1").show();
							}
							if( zajbmc[i] == "其他建议"){
								jQuery("#sfzj1").show();
							}									
						}
				}
			});
		</script>
	</head>
	<body>
		<html:form action="/xlzx_thjl_zxzxthjlgl.do" styleId="zxzxthjlForm" method="post" >
		<input type="hidden" name="ytr" id="ytr" value="${jsInfo.zgh}" />
		<input type="hidden" name="cbpgdm" id="cbpgdm" value="${thjlxx.cbpgdm}" />
		<input type="hidden" name="ybwtlb" id="ybwtlb" value="${thjlxx.ybwtlb}" />
		<input type="hidden" name="zajb" id="zajb" value="${thjlxx.zajb}" />
		<input type="hidden" name="zajbsmzx" id="zajbsmzx" value="${thjlxx.zajbsmzx}" />
		<input type="hidden" name="cbpgjg" id="cbpgjg" value="${thjlxx.cbpgjg}" />
		<input type="hidden" name="sfzj" id="sfzj" value="${thjlxx.sfzj}" />
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div style='tab;width:100%;height:480px;overflow-x:hidden;overflow-y:auto;'>
		<html:hidden property="id"/>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr> 
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%">学号</th>
							<td style="width:35%">
								<html:hidden property="xh" styleId="xh"/>
								<bean:write name="jbxx" property="xh"/>
							</td>
							<th style="width:15%">姓名</th>
							<td style="width:35%">
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xm"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>性别</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xb"/>
								</logic:present>
							</td>
							<th>班级</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="bjmc"/>
								</logic:present>
							</td>
						</tr>
					<tr>
						<th>年级</th>
						<td>
							<logic:present name="jbxx">
									<bean:write name="jbxx" property="nj"/>
							</logic:present>
						</td>
						<th>学院</th>
						<td>
							<logic:present name="jbxx">
									<bean:write name="jbxx" property="xymc"/>
							</logic:present>
						</td>
					</tr>
					<tr>
						<th>专业</th>
						<td>
							<logic:present name="jbxx">
									<bean:write name="jbxx" property="zymc"/>
							</logic:present>
						</td>
						<th>联系电话</th>
						<td>
							<logic:present name="jbxx">
									<bean:write name="jbxx" property="sjhm"/>
							</logic:present>
						</td>
					</tr>
					</tbody>
				<thead>
						<tr >
							<th colspan="4">
								<span>谈话教师信息</span>
							</th>
						</tr>
				</thead>
				<tbody id="thjlxx">
					<tr style="height:10px">
						<th>谈话老师</th>
						<td  width="34%">
							${thjlxx.zgxm}
						</td>
						<th>职工号</th>
						<td>
							${thjlxx.ytr}
						</td>
					</tr>
					<tr>
						<th>性别</th>
						<td>
							${thjlxx.zgxb}
						</td>
						<th>部门</th>
						<td >
							${thjlxx.zgbmmc}
						</td>
					</tr>
					</tbody>
				<thead>
					<tr> 
						<th colspan="4">
							<span>谈话详情</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>谈话时间</th>
						<td width="34%" colspan="3">
							<bean:write name="thjlxx" property="thsj"/>
						</td>
					</tr>
					<tr>
						<th>基本情况描述</th>
						<td colspan="3">
						<bean:write name="thjlxx" property="jbqkms"/>
						</td>
					</tr>
					<tr>
						<th width="20%">初步评估</th>
						<td colspan="3">
							<bean:write name="thjlxx" property="cbpgdmmc"/>
						</td>
					</tr>
					<tr id="ybwtlb1">
						<th >一般心理问题类别</th>
						<td colspan="3" >
							<bean:write name="thjlxx" property="ybwtlb"/>
						</td>
				    </tr>
					<tr id="zajb1">
						<th>心理障碍和</br>精神疾病</th>
						<td colspan="3" >
							<bean:write name="thjlxx" property="zajb"/>
						</td>
				    </tr>
				    <tr id="cbpgjg1">
						<th width="16%">初步评估结果</th>
						<td width="34%" colspan="3">
							<bean:write name="thjlxx" property="cbpgjg"/>
						</td>
					</tr>
					<tr id="zajbsmzx1">
						<th>是否预约咨询时间</th>
						<td colspan="3">
							<bean:write name="thjlxx" property="zajbsmzx"/>
						</td>
					</tr>
					<tr id="sfzj1">
						<th>是否转介</th>
						<td colspan="3">
							<bean:write name="thjlxx" property="sfzj"/>
						</td>
					</tr>
					<tr>
						<th>备注<br/>是否需要进一步诊断</th>
						<td colspan="3">
							<bean:write name="thjlxx" property="bz"/>
						</td>
					</tr>
				</tbody>
				</table>
				</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="refreshParent2();">
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

