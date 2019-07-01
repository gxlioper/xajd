<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/stzhwh/js/stzhwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/stglStzhwh" method="post" styleId="StzhwhForm">
		<html:hidden property="ystid" value="${rs.ystid}"/>
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>艺术团基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								艺术团项目名称
							</th>
							<td width="30%">
								${rs.ystxmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								艺术团类别
							</th>
							<td width="30%">
								${rs.ystlbmc}
							</td>
							<th width="20%">
								项目类别
							</th>
							<td width="30%">
								${rs.xmlbmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								有效学年
							</th>
							<td width="30%">
								${rs.xn}
							</td>
							<th width="20%">
								挂靠单位
							</th>
							<td width="30%">
								${rs.gkdwmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								负责人类别
							</th>
							<td width="30%">
								${rs.fzrlb}
							</td>
							<th width="20%">
								艺术团负责人
							</th>
							<td width="30%">
								${rs.fzrxm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								指导老师
							</th>
							<td width="30%" >
								${rs.zdlsxm}
							</td>
							<th width="20%">
								指导老师职称
							</th>
							<td width="30%" >
								${rs.zcmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								指导老师联系方式
							</th>
							<td width="30%" >
								${rs.zdlslxfs}
							</td>
							<th width="20%">
								所属部门
							</th>
							<td width="30%" >
								${rs.ssbmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								艺术团联系电话
							</th>
							<td width="30%" >
								${rs.lxdh}
							</td>
							<th width="20%">
								建团人
							</th>
							<td width="30%">
								${rs.jtrxm}
							</td>	
						</tr>
						<tr>
							<th width="20%">
								艺术团成立时间
							</th>
							<td width="30%" >
								${rs.ystclsj}
							</td>
							<th width="20%">
								申请时间
							</th>
							<td width="30%">
								${rs.sqsj}
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
								<span>艺术团成员信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_ztwh">
						<tr>
							<th width="15%" style="text-align:left">学号</th>
							<th width="15%" style="text-align:left">姓名</th>
							<th width="15%" style="text-align:left">性别</th>
							<th width="20%" style="text-align:left">申请理由</th>
							<th width="20%" style="text-align:left">特长</th>
							<th width="15%" style="text-align:left">团内状态</th>
						</tr>
						<logic:iterate id="s" name="ztwhList">
							<tr>
							   <input type="hidden" name="rtid" value="${s.rtid}">
								<td>${s.xh}</td>
								<td>${s.xm}</td>
								<td>${s.xb}</td>
								<td>${s.sqly}</td>
								<td>${s.tc}</td>
								<td>
								    <html:select property="tnzt" value="${s.tnzt}">
										<html:options collection="ztwh" property="dm" labelProperty="mc" />
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
								<div class="bz">
								</div>
								<div class="btn">
						           <button type="button" onclick="saveStCyZtwh();">
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