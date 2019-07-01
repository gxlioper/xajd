<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<link rel="stylesheet" href="<%=stylePath%>css/public.css" type="text/css"
			media="all" />
		<link rel="stylesheet" href="<%=stylePath%>css/job.css" type="text/css"
			media="all" />
		<%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/embed_index.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/register.data.js"></script>
		<script type="text/javascript">
	$().ready(function() {
		onloadlst("index", "info-list1", 12, "", "全职", "");//版位标示，列表层ID，条数，职位类别，职位性质，单位性质
			onloadlst("index", "info-list2", 12, "", "实习", "");
		});
</script>
	</head>
	<body>
	<div class="con">
		<table width="490" border="0" cellspacing="0" cellpadding="0"
			class="ncssjoblist">
			<thead>
				<tr>
					<td colspan="2">
						<img src="<%=stylePath%>images/jyzs_ico08.jpg" width="490" height="49"
							border="0" usemap="#Map" />
					</td>
				</tr>
			</thead>
			<tr>
				<td width="50%" valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<td>
									<div class="jobtitle">
										招聘信息
									</div>
									<span class="more"><a
										href="http://lnjy.ncss.org.cn/job/index" class="hui21"
										target="_blank">MORE</a> </span>
								</td>
							</tr>
						</thead>
						<tbody id="info-list1">
						</tbody>
					</table>
				</td>
				<td width="50%" valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<td>
									<div class="jobtitle">
										实习信息
									</div>
									<span class="more"><a
										href="http://lnjy.ncss.org.cn/job/index" class="hui21"
										target="_blank">MORE</a> </span>
								</td>
							</tr>
						</thead>
						<tbody id="info-list2">
						</tbody>
					</table>
				</td>
			</tr>
		</table>
		<map name="Map" id="Map">
			<area shape="rect" coords="290,7,360,43"
				href="http://lnjy.ncss.org.cn/login" target="_blank" />
			<area shape="rect" coords="385,7,455,43"
				href="http://lnjy.ncss.org.cn/rec/login" target="_blank" />
		</map></div>
	</body>
</html>