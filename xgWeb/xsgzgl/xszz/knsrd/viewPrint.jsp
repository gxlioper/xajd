<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%@ include file="/syscommon/head.ini"%>
    <title>浙江同济科技职业学院家庭经济困难学生认定申请表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
span {
	font-family: 新宋体;
}
.dataTable{
 	border-collapse: collapse;
 	
 	}
.dataTable td {
	border: solid windowtext 1.0pt;
	height: 22pt;
	align: center;
}

.dataTable td p {
	text-align: center;
}
td .pshu{
	margin-right:5.65pt !important;
	margin-left:5.65pt !important;
	text-align:center !important;
}
.content{
	text-align: left !important; 
	margin: 10px 0 0 10px !important; 
	min-height: 130px !important;
}
.nyr{
	text-align: right !important;
	margin: 10px 30px 10px 0 !important;
}
.fk{
	text-align: left !important;
	margin-left:10px !important;
	
}
</style>
<script type="text/javascript">
jQuery(function(){
	var rddc = jQuery("#rddc").val()
	var rddcid = "tjdc_" + rddc;
	var i = jQuery("span[name=tjdc]");
	jQuery("span[name=tjdc]").each(function(){
		var id = jQuery(this).attr("id");
		if(id == rddcid){
			jQuery(this).html("■");
		}
	});
	var yxyj = jQuery("#yxyj").val();
	if(rddc == yxyj && rddc != "" && rddc != null){
		jQuery("#yxyj_ty").html("■");
	}else if(rddc != yxyj && yxyj != "" && yxyj != null ){
		jQuery("#yxyj_bty").html("■");
		alert(jQuery("#xxtz_mc").val());
		jQuery("#xxtz").html(jQuery("#xxtz_mc").val());
	}
	
	var xxyj = jQuery("#xxyj").val();
	if(rddc == xxyj && rddc != "" && rddc != null){
		jQuery("#xxyj_ty").html("■");
	}else if(rddc != xxyj && xxyj != "" && xxyj != null ){
		jQuery("#xxyj_bty").html("■");
		jQuery("#yxtz").html(jQuery("#yxtz_mc").val());
	}
})
	
</script>
  </head>
	<body>
	<input type="hidden" id="rddc" value="${knsmodel.rddc}">
	<input type="hidden" id="yxyj" value="${spxxInfo.ejtjdcdm}"><%--院系意见--%>
	<input type="hidden" id="xxyj" value="${spxxInfo.sjtjdcdm}"><%--学校意见--%>
	<input type="hidden" id="yxtz_mc" value="${spxxInfo.ertjdcmc}">
	<input type="hidden" id="xxtz_mc" value="${spxxInfo.sjtjdcmc}">
		<p style='text-align: center'>
			<b><span style='font-size: 18.0pt; font-family: 黑体;'>浙江同济科技职业学院家庭经济困难学生认定申请表
			<br><span>（${knsmodel.xn}）</span></span>
			</b>
		</p>
		<p>&nbsp;</p>
		<table style='width: 550pt;' class="dataTable" align="center">
			<tr>
				<td align="center" rowspan="4" style='width: 30pt; height: 140pt'>
					<p class="pshu"><b><span>学生本人基本情况</span></b></p>
				</td>
				<td>
					<p>
						<span style=''>姓&nbsp;名</span>
					</p>
				</td>
				<td colspan="3" style='width: 100pt;'>
					<p>
						<span>${xsxx.xm}</span>
					</p>
				</td>
				<td style='width: 40pt;'>
					<p>
						<span>性 别</span>
					</p>
				</td>
				<td colspan="2" style='width: 40pt;'>
					<p>
						<span>${xsxx.xb}</span>
					</p>
				</td>
				<td colspan="2" style='width: 55pt;'>
					<p>
						<span>出生年月</span>
					</p>
				</td>
				<td style='width: 65pt;'>
					<p>
						<span>${xsxx.csrq}</span>
					</p>
				</td>
				<td style='width: 40.8pt;'>
					<p>
						<span>民 族</span>
					</p>
				</td>
				<td style='width: 51.65pt;'>
					<p>
						<span>${xsxx.mzmc}</span>
					</p>
				</td>
			</tr>
			<tr style=''>
				<td style='width: 64.15pt;'>
					<p>
						<span>身份证号码</span>
					</p>
				</td>
				<td colspan=4 style='width: 139.05pt;'>
					<p>
						<span>${xsxx.sfzh}</span>
					</p>
				</td>
				<td colspan=2 style='width: 60.0pt;'>
					<p>
						<span>政治面貌</span>
					</p>
				</td>
				<td colspan=2 style='width: 53.6pt; height: 22.7pt'>
					<p>
						<span>${xsxx.zzmmmc}</span>
					</p>
				</td>
				<td style='width: 68.0pt;'>
					<p>
						<span>家庭人均年收入</span>
					</p>
				</td>
				<td colspan=2 style='width: 92.45pt;'>
					<p>
						<span>${jtrjsr}元</span>
					</p>
				</td>
			</tr>
			<tr>
				<td style='width: 65pt;'>
					<p>
						<span>院&nbsp;系</span>
					</p>
				</td>
				<td colspan=4 style='width: 140pt;'>
					<p>
						<span>${xsxx.xymc}</span>
					</p>
				</td>
				<td colspan=2 style='width: 60.0pt;'>
					<p>
						<span>专 业</span>
					</p>
				</td>
				<td colspan=5 style='width: 210pt;'>
					<p>
						<span>${xsxx.zymc}</span>
					</p>
				</td>
			</tr>
			<tr>
				<td style='width: 65pt;'>
					<p>
						<span>年&nbsp;级</span>
					</p>
				</td>
				<td style='width: 37.0pt;'>
					<p>
						<span>${xsxx.nj}</span>
					</p>
				</td>
				<td style='width: 27.05pt;'>
					<p>
						<span>班</span>
					</p>
				</td>
				<td colspan=2 style='width: 75.0pt;'>
					<p>
						<span>${xsxx.bjmc}</span>
					</p>
				</td>
				<td colspan=3 style='width: 86.0pt;'>
					<p>
						<span>在校联系电话</span>
					</p>
				</td>
				<td colspan=4 style='width: 188.05pt;'>
					<p>
						<span>${xsxx.sjhm}</span
					</p>
				</td>
			</tr>
			<tr>
				<td style='width: 33.15pt; height: 162.15pt'>
					<p  class="pshu">
						<b><span>学生陈述申请认定理由</span>
						</b>
					</p>
				</td>
				<td colspan="12" valign="top" style='width: 477.25pt; height: 160pt'>
					<p class="content" >
						<span>${sqrdly}</span>
					</p>
					<p class="nyr">
						<span style=''>学生签字：<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<u><span>&nbsp;&nbsp;</span></u>
						年<u><span>&nbsp;</span></u>
						月<u><span>&nbsp;</span></u>
						日</span>
					</p>
				</td>
			</tr>
			<tr>
				<td rowspan=4 style='width: 33.15pt; height: 39.0pt'>
					<p  class="pshu">
						<b><span>民主评议</span>
						</b>
					</p>
				</td>
				<td rowspan=4 style='width: 60pt; height: 40pt'>
					<p style="margin-right:25pt; margin-left:25pt; text-align:center;" >
						<span>推荐档次</span>
					</p>
				</td>
				<td colspan=3 style='width: 100.05pt; height: 39.0pt'>
					<p class="fk">
						<span><span name="tjdc"  id="tjdc_1">□</span> 家庭经济特别困难</span>
					</p>
				</td>
				<td rowspan=4 style='width: 40pt; '>
					<p style="margin-right:15pt; margin-left:15pt; text-align:center;">
						<span>陈述理由</span>
					</p>
				</td>
				<td colspan="7" rowspan="4" valign="top">
					<p class="content">
						<span>${spxxInfo.yjshyj}</span>
					</p>
					<p class="nyr" >
						<span>评议小组组长签字：<span>&nbsp;&nbsp; </span><u><span>&nbsp;&nbsp;</span>
						</u>年<u><span>&nbsp;</span>
						</u>月<u><span>&nbsp;</span>
						</u>日
						</span>
					</p>
				</td>
			</tr>
			<tr>
				<td  colspan=3 style='width: 100.05pt; height: 38.8pt'>
					<p class="fk" >
						<span><span name="tjdc" id="tjdc_2">□</span> 一般困难 </span>
					</p>
				</td>
			</tr>
			<tr>
				<td colspan=3 style='width: 100.05pt; height: 38.8pt'>
					<p class="fk">
						<span><span name="tjdc" id="tjdc_3">□</span> 不困难 </span>
					</p>
				</td>
			</tr>
			<tr>
				<td colspan=3 style='width: 100.05pt; height: 38.8pt'>
					<p class="fk">
						<span><span name="tjdc" id="tjdc_6">□</span> 家庭经济不困难 </span>
					</p>
				</td>
			</tr>
			<tr>
				<td style='width: 30pt; height: 100pt'>
					<p class="pshu">
						<b><span>认定决定</span>
						</b>
					</p>
				</td>
				<td style='width: 65pt; height: 100pt'>
					<p>
						<span>院（系）意见</span>
					</p>
				</td>
				<td colspan=4 valign=top style='width: 140pt; height: 120pt;text-align: left;'>
				
					<p style="text-align: left;margin: 10px 0 0 10px;">
						<span>经评议小组推荐、本院（系）认真审核后，</span>
						
					</p>
					<p style="text-align: left;margin-left: 10px;"><span><span id="yxyj_ty">□</span> 同意评议小组意见。</span></p>
					<p style="text-align: left;margin-left: 10px;"><span><span id="yxyj_bty">□</span> 不同意评议小组意见。调整为</span>
						<u>
							<span>&nbsp;<span id="yxtz"></span>&nbsp;</span>
						</u>。
					</p>
					<p style="text-align: left;margin-left: 10px;height: 50px">
						工作组组长签字：
					</p>
					<p class="nyr" >
						<u><span>&nbsp;&nbsp; </span>
						</u>年
						<u><span><span>&nbsp; </span>
						</span>
						</u>月
						<u><span><span>&nbsp; </span>
						</span>
						</u>日
					</p>
				</td>
				<td style='width: 46.55pt; height: 120pt'>
					<p>
						<span>学校学生资助管理机构意见</span>
					</p>
				</td>
				<td colspan=6 valign=top style='width: 219.85pt; height: 120pt'>
					<p style="text-align: left;margin: 10px 0 0 10px;">
						<span>经学生所在院（系）提请，本机构认真核实，</span>
					</p>
					<p class="fk" >
						<span><span id="xxyj_ty">□</span> 同意工作组和评议小组意见。</span>
					</p>
					<p class="fk">
						<span><span id="xxyj_bty">□</span> 不同意工作组和评议小组意见。调整为：</span>
					</p>
					<p class="fk">
						<u><span>&nbsp;<span id="xxtz"></span>&nbsp;</span>
						</u><span>。</span>
					</p>
					<p class="fk">
						<span>负责人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
					</p>
					<p  class="nyr">
						<u><span>&nbsp;&nbsp;&nbsp; </span>
						</u>年
						<u><span>&nbsp;</span>
						</u>月
						<u><span>&nbsp; </span>
						</u>日
					</p>
					<p style="text-align: right;margin: 10px 50px 10px 0;">
						<span>（加盖部门公章）</span>
					</p>
				</td>
			</tr>
		</table>
	</body>
</html>
