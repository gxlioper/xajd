<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script language="javascript" defer="defer">
		jQuery(function() {
			jQuery("#myTbody").css("display","none");
		});
		function showTbody(obj,objTbody){
			if(obj.className=="up"){
				obj.className="down";
				obj.parentNode.parentNode.className="cur-tr";
				document.getElementById(objTbody).style.display="none";
			}else{
				obj.className="up";
				obj.parentNode.parentNode.className="";
				document.getElementById(objTbody).style.display="";
			}
		}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/ydjg">
		<div style='tab;width:100%;height:100%;overflow-x:hidden;overflow-y:auto;'>
		<div style='tab;width:100%;height:300px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>床位信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							楼栋名称
						</th>
						<td align="left">
							${cwxxData.ldmc}
						</td>
						<th align="right">
							寝室号
						</th>
						<td align="left">
							${cwxxData.qsh}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							床位号
						</th>
						<td align="left">
							${cwxxData.cwh}
						</td>
						<th align="right">
							寝室电话
						</th>
						<td align="left">
							${cwxxData.qsdh}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							收费标准
						</th>
						<td align="left">
							${cwxxData.sfbz}
						</td>
						<th align="right">
							所属年级
						</th>
						<td align="left">
							${cwxxData.nj}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							所属<bean:message key="lable.xb" />
						</th>
						<td align="left">
							${cwxxData.xymc}
						</td>
						<th align="right">
							所属班级
						</th>
						<td align="left">
							${cwxxData.bjmc}
						</td>
					</tr>
				</tbody>
				<thead>
						<tr>
							<th colspan="4">
								<span>宿舍异动信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="4">
							<logic:notEmpty name="currQsYdList">
								<table width="100%" border="0" class="formlist">
									<thead>
										<th style="text-align: left;">学年学期</th>
										<th style="text-align: left;">学号</th>
										<th style="text-align: left;">姓名</th>
										<th style="text-align: left;">性别</th>
										<th style="text-align: left;">年级</th>
										<th style="text-align: left;"><bean:message key="lable.xb" /></th>
										<th style="text-align: left;">专业</th>
										<th style="text-align: left;">班级</th>
										<th style="text-align: left;">入住时间</th>
										<th style="text-align: left;">退宿时间</th>
									</thead>
									<tbody>
										<logic:iterate id="s" name="currQsYdList">
										  <tr>
											<td><bean:write name="s" property="xn"/>&nbsp;<bean:write name="s" property="xqmc"/></td>
											<td><bean:write name="s" property="xh"/></td>
											<td><bean:write name="s" property="xm"/></td>
											<td><bean:write name="s" property="xb"/></td>
											<td><bean:write name="s" property="nj"/></td>
											<td><bean:write name="s" property="xymc"/></td>
											<td><bean:write name="s" property="zymc"/></td>
											<td><bean:write name="s" property="bjmc"/></td>
											<td><bean:write name="s" property="ydqqsrzsj"/></td>
											<td><bean:write name="s" property="tstzsj"/></td>
										  </tr>
										</logic:iterate>
									</tbody>
								</table>
							</logic:notEmpty>
							<logic:empty name="currQsYdList">
								<span style="color:green; font-weight:bold; font-size:12px;padding-left:10px;"> 当前学期没有宿舍异动信息。</span>			
							</logic:empty>
						</td>
					</tr>
				</tbody>
			</table>
			
			<table width="100%"  border="0" class="formlist">
			    <thead>
			      <tr>
			      	<th colspan="2">
			      	  <a href="#" class="down" onclick="showTbody(this,'myTbody');return false">更多宿舍异动信息</a>
			   	    </th>
			      </tr>
			    </thead>
			</table>
			
			<div class="regform" style="padding-top:0px;">
				<div id="myTbody" style="padding-bottom:10px;">
					<logic:notEmpty name="notCurrQsYdList">
						<table width="100%" border="0" class="formlist">
							<thead>
								<th>学年学期</th>
								<th>学号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>年级</th>
								<th><bean:message key="lable.xb" /></th>
								<th>专业</th>
								<th>班级</th>
								<th>入住时间</th>
								<th>退宿时间</th>
							</thead>
							<tbody>
								<logic:iterate id="s" name="notCurrQsYdList">
									<tr>
									<td><bean:write name="s" property="xn"/>&nbsp;<bean:write name="s" property="xqmc"/></td>
									<td><bean:write name="s" property="xh"/></td>
									<td><bean:write name="s" property="xm"/></td>
									<td><bean:write name="s" property="xb"/></td>
									<td><bean:write name="s" property="nj"/></td>
									<td><bean:write name="s" property="xymc"/></td>
									<td><bean:write name="s" property="zymc"/></td>
									<td><bean:write name="s" property="bjmc"/></td>
									<td><bean:write name="s" property="ydqqsrzsj"/></td>
									<td><bean:write name="s" property="tstzsj"/></td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
					<logic:empty name="notCurrQsYdList">
						<span style="color:green; font-weight:bold; font-size:12px;padding-left:10px;"> 没有更多宿舍异动信息。</span>			
					</logic:empty>
				</div>
			</div>				
		</div>
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
