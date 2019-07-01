<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/zyfwgl/zyfwsh/js/zyfwSh.js"></script>
	<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.hdid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${zyfwShForm.splc}&shid=${zyfwShForm.shid}");
			proviceCiyyLocalMain({type:"view",id:"fwddssx",flag:"yxxdz"});
		});
		
	</script>
</head>
<body>
	<html:form action="/xsxx_zyfwgl_sh" method="post" styleId="zyfwShForm">
		<html:hidden  property="fwid" styleId="fwid"/>
		<html:hidden  property="xh" styleId="xh"/>		
		<html:hidden  property="splc" styleId="splc"/>
		<html:hidden  property="shid" styleId="shid"/>
		<html:hidden  property="gwid" styleId="gwid"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
			<table width="100%" border="0" class="formlist">			
			<thead>
				<tr>
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
			<thead>
				<tr>
					<th colspan="4">
						<span>志愿服务登记信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="20%">
						学年
					</th>
					<td width="30%">
						${zyfwShForm.xn}
					</td>
					</td>
					<th width="20%">学期</th>
					<td width="30%">
						${zyfwShForm.xqmc}
					</td>
				</tr>
				<tr>
					<th width="20%">
						服务开始时间
					</th>
					<td width="30%">
						${zyfwShForm.fwkssj}
					</td>
					<th width="20%">
						服务结束时间
					</th>
					<td width="30%">
						${zyfwShForm.fwjssj}
					</td>
				</tr>
				<tr>
					<th width="20%">
						见证人
					</th>
					<td width="30%">
						${zyfwShForm.jzr}
					</td>
					<th width="20%">服务小时数</th>
					<td width="30%">
						${zyfwShForm.fwxss}
					</td>
				</tr>
				<tr>
					<th width="20%">
						服务地点
					</th>
					<td colspan="3">
						<input type="hidden" id="fwddssx" value="${zyfwShForm.fwddssx}"/>
						${zyfwShForm.fwdd}
					</td>
				</tr>
				<tr>
					<th width="20%" >服务内容 </th>
					<td colspan="3">
						${zyfwShForm.fwnr}
					</td>
				</tr>
			</tbody>
			<thead>
					<tr>
						<th colspan="4">
							<span>志愿服务审核情况</span>
						</th>
					</tr>
			</thead>				
			<tbody>
				<tr>
					<td colspan="4" id="shlccx">
					
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
					<tr>
						<th >
							审核结果
						</th>
						<td id="shjgSpan" colspan="3">
							
						</td>
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> 审核意见
					<br />
					<font color="red">(限200字)</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=zyfwgl&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存"  onclick="saveForDgsh();return false;">
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
		</html:form>
</body>
</html>
