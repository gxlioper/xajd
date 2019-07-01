<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsh/js/txhdsh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${data.sqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${data.splc}&shid=${data.shid}");
		});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/rcsw_txhd_sh">
		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		 <html:hidden property="sqid"/>
		 <html:hidden property="xn"/>
		 <html:hidden property="xq"/>
		 <html:hidden property="xh"/>
		 <html:hidden property="splc" styleId="splc"/>
		 <html:hidden property="shid" styleId="shid"/>
		 <html:hidden property="shzt" styleId="shzt"/>
		 <html:hidden property="xmdm"/>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
				<thead>
					<tr>
						<th colspan="4">
							<span>团学信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							项目名称
						</th>
						<td align="left">
							${xmxx.xmmc}
						</td>
						<th align="right">
							活动时间
						</th>
						<td align="left">
							${xmxx.hdkssj}&nbsp;至&nbsp;${xmxx.hdjssj}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							限制人数
						</th>
						<td align="left">
							${xmxx.shrssx}
						</td>
						<th align="right">
							活动地点
						</th>
						<td align="left">
							${xmxx.hddd}
						</td>
					</tr>
					<% if("13023".equals(xxdm)){ %>
					<tr>
						<th align="right" width="10%">
							授予学分
						</th>
						<td align="left">
							${xmxx.syxf}
						</td>
						<th align="right">
						</th>
						<td align="left">
						</td>
					</tr>
					<% } %>
					<tr>
						<th align="right" width="10%" >
							活动说明
						</th>
						<td align="left" colspan="3">
							${xmxx.hdsm}
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>审核信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<font color="red">*</font>审核结果
						</th>
						<td colspan="3" id="shjgSpan">
							
						</td>
					</tr>
					<tr>
					<th width="20%">
							<font color="red">*&nbsp;</font> 审核意见&nbsp;
							<br />
							<font color="red">(限200字)</font>
						</th>
						<td colspan="3">
							<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=qqgl&id=shyj" />
							<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);">${rs.shyj}</textarea>
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
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" id="btqd" onclick="save_sh();">
									保 存
								</button>
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
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
			<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
</html>
