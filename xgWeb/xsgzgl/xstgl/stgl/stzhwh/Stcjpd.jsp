<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/stgl/stzhwh/js/stzhwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/stglStzhwh" method="post" styleId="StzhwhForm">
		    <html:hidden property="stid" value="${rs.stid}"/>
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>社团基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								社团项目名称
							</th>
							<td width="30%">
								${rs.stxmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								社团类别
							</th>
							<td width="30%">
								${rs.stlbmc}
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
								${rs.gkdw}
							</td>
						</tr>
						<!--
						<tr>
							<th width="20%">
								社团开始时间
							</th>
							<td width="30%">
								${rs.kssj}
							</td>
							<th width="20%">
								社团结束时间
							</th>
							<td width="30%">
								${rs.jssj}
							</td>
						</tr>
						  -->
						<tr>
							<th width="20%">
								负责人类别
							</th>
							<td width="30%">
								${rs.fzrlb}
							</td>
							<th width="20%">
								社团负责人
							</th>
							<td width="30%">
								${rs.stfzrxm}
							</td>
						</tr>
					<thead>
					<tr>
						<th colspan="4">
							<span>指导老师</span>

						</th>
					</tr>
					</thead>
					<tbody>
					<tr colspan="4">
						<td width="100%" colspan="4">
							<div width="100%" id="autotable">
								<table width="100%" id="tablebody">
									<tr>
										<th width="30%" style="text-align:left;">指导老师姓名</th>
										<th width="20%" style="text-align:left;">所属部门</th>
										<th width="20%" style="text-align:left;">联系电话</th>
										<th width="20%" style="text-align:left;">职称</th>
									</tr>
									<logic:iterate id="i" name="ZdlsInfoList">
										<tr name="deltr">
											<td><input name="zgh" type="hidden" value="${i.zgh}" style="width:90%"/><label name = "xm">${i.xm}</label></td>
											<td><input name="bmdm" type="hidden" value="${i.bmdm}" style="width:90%"/><label name = "bmmc">${i.bmmc}</label></td>
											<td><label name = "lxdh">${i.lxdh}</label></td>
											<td><input name="zc" type="hidden" value="${i.zc}" style="width:90%"/><label name = "zcmc">${i.zcmc}</label></td>
										</tr>
									</logic:iterate>
								</table>
							</div>
						</td>
					</tr>
						</tr>
						<tr>
							<th width="20%">
								社团联系电话
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
								社团成立时间
							</th>
							<td width="30%" >
								${rs.stclsj}
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
							<th colspan="6">
								<span>社团成员信息</span>&nbsp;<lable style="line-height:20px">(${currxn}&nbsp;学年)</lable>
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
							<th width="15%" style="text-align:left">成员成绩</th>
						</tr>
						<logic:iterate id="s" name="zhpdList">
							<tr>
							  <input type="hidden" name="id" value="${s.id }"/>
							  <input type="hidden" name="xh" value="${s.xh}"/>
							  <input type="hidden" name="rtid" value="${s.rtid}"/>
								<td>${s.xh}</td>
								<td>${s.xm}</td>
								<td>${s.xb}</td>
								<td>${s.sqly}</td>
								<td>${s.tc}</td>
								<td>
									<html:select property="cjpd" value="${s.cjpd}">
										<html:options collection="cjpdList" property="dm" labelProperty="mc" />
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
									<button type="button" onclick="saveStCyCjpd();">
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