<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/hdkhgl/js/hdkhgl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/txhd_hdkhgl" method="post" styleId="hdkhForm">
		    <html:hidden property="hdxmbh" value="${hdxmbh}"/>
		    <html:hidden property="xn" value="${xn}"/>
		    <html:hidden property="xq" value="${xq}"/>
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>活动项目</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								项目名称
							</th>
							<td width="34%">
								${data.xmmc}
							</td>
							<th width="16%">
								活动时间
							</th>
							<td width="34%" >
								${data.hdkssj}至${data.hdjssj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								活动类别
							</th>
							<td width="34%">
								${hdlbmc}
							</td>
							<th width="16%">
								活动地点
							</th>
							<td width="34%" >
								${data.hddd}
							</td>
						</tr>
						<tr>
							<th width="16%">
								申请人数上限
							</th>
							<td width="34%" >
								${data.sqrssx}
							</td>
							<th width="16%">
								审核人数上限
							</th>
							<td width="34%" >
								${data.shrssx}
							</td>
							
						</tr>
						<tr>
						<th align="right" width="10%">
						承办单位
						</th>
						<td align="left" >
							${data.cbdw}
						</td>
						<th align="right" width="10%">
						活动规格
						</th>
						<td align="left" >
							${hdgg}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">活动主题</th>
						<td align="left" colspan="3" >
							${data.hdzt}
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							活动目的及意义
						</th>
						<td colspan="3">
							${data.hdmdyy}
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							活动方案
						</th>
						<td colspan="3">
							${data.hdfa}
						</td>
					</tr>
						<tr>
							<th width="16%">
								活动说明
							</th>
							<td width="34%" colspan="3">
								${data.hdsm}
							</td>
						</tr>
					</tbody>
					
				</table>
			</div>
			<div style="margin-top:1px;">
			  <table width="100%" border="0" class="formlist" >
				<thead>
						<tr>
							<th colspan="8">
								<span>活动成员信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_cjpd">
						<tr>
							<th width="10%" style="text-align:left">学号</th>
							<th width="10%" style="text-align:left">姓名</th>
							<th width="10%" style="text-align:left">性别</th>
							<th width="10%" style="text-align:left">学院</th>
							<th width="10%" style="text-align:left">专业</th>
							<th width="10%" style="text-align:left">班级</th>
							<th width="20%" style="text-align:left">获奖/成果情况</th>
							<th width="20%" style="text-align:left" >是否获得学分</th>
						</tr>
						<logic:iterate id="s" name="hdkhcjlist">
							<tr>
							  <input type="hidden" name="id" value="${s.id }"/>
							  <input type="hidden" name="xh" value="${s.xh}"/>
								<td>${s.xh}</td>
								<td>${s.xm}</td>
								<td>${s.xb}</td>
								<td>${s.xymc}</td>
								<td>${s.zymc}</td>
								<td>${s.bjmc}</td>
								<td><input type="text" maxlength="50" name="hjqk" value="${s.hjqk}" /></td>
								<td>
									<html:select property="sfhdxf" value="${s.sfhdxf}">
										<html:options collection="xflist" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
			<div style="height:33px;">
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="saveKhcj();">
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
		</html:form>
	</body>
	
</html>