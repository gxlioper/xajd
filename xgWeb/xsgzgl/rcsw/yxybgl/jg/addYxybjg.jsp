<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/yxybgl/jg/js/yxybjg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/yxybgl_jg" method="post" styleId="yxybjgForm" onsubmit="return false;">
		<html:hidden property="xn" styleId="xn"/>
		<html:hidden property="xq" styleId="xq"/>
		<html:hidden property="txr" styleId="txr"/>
		<html:hidden property="txsj" styleId="txsj"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>月报信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								${xn}
							</td>
							</td>
							<th>学期</th>
							<td>
								${xq}
							</td>
						</tr>
						<tr>
							<th>
								填写人
							</th>
							<td>
								${txrxm}
							</td>
							</td>
							<th><span class="red">*</span>月份</th>
							<td>
								
								<html:select property="yf" styleId="yf" style="width:200px">
									<html:option value=""></html:option>
									<html:options name="yfList"/>
								</html:select>
							</td>
						</tr>
						<tr>
					    	<th><span class="red">*</span>学院</th>
					    	<td>
								<html:select property="xydm" styleId="xydm" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="xyList" labelProperty="xymc" property="xydm"/>
								</html:select>
							</td>							
						</tr>
								<tr>
									<th><span><font color="red">*</font></span>
										本月工作开展情况
										<br /><font color="red">&lt;限2000字&gt;</font>
									</th>
									<td colspan="3">
										<html:textarea property='bygzkzqk' style="width:98%" styleId="bygzkzqk" rows='8' onblur="checkLen(this,2000);"/>
									</td>
								</tr>
						<tr>
							<th><span><font color="red">*</font></span>
								学生关注热点
								<br /><font color="red">&lt;限2000字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='xsgzrd' style="width:98%" styleId="xsgzrd" rows='8' onblur="checkLen(this,2000);"/>
							</td>
			      		</tr>
			      		<tr>
							<th><span><font color="red">*</font></span>
								学生思想动态
								<br /><font color="red">&lt;限2000字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='xssxdt' style="width:98%" styleId="xssxdt" rows='8' onblur="checkLen(this,2000);"/>
							</td>
			      		</tr>
			      		<tr>
							<th><span><font color="red">*</font></span>
								学生诉求及工作建议
								<br /><font color="red">&lt;限2000字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='xstsjgzjy' style="width:98%" styleId="xstsjgzjy" rows='8' onblur="checkLen(this,2000);"/>
							</td>
			      		</tr>						
					</tbody>
				 </table>			
				</div>
			  <div style="height:30px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								    <button type="button" onclick="saveYxybjg('save');">
										保存
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

