<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/gyjc_wsjc" method="post" styleId="WsjcForm" onsubmit="return false;">
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
				<span>寝室信息
				</span>
				</th>
			</tr>
			</thead>
			<tbody>
			    <tr>
					<th>楼栋</th>
					<td id="ldmc" width="30%">${qsxx.ldmc}</td>
					<th width="20%">层号</th>
					<td id="ch">${qsxx.ch}</td>
				</tr>
				<tr>
					<th width="20%">寝室</th>
					<td id="qshTd" width="30%">${qsxx.qsh}</td>
					<th width="20%">学院</th>
					<td id="xymc">${qsxx.xymc}</td>
				</tr>

				<tr>
					<th width="20%">检查时间</th>
					<td id="jcsj" width="30%">${jcxx.jcsj}</td>
					<th width="20%">评价等级</th>
					<td id="pjdj">${jcxx.pjdj}</td>
				</tr>
				<tr>
					<th width="20%">检查人</th>
					<td id="jcr" width="30%">${jcxx.xm}</td>
					<th width="20%"></th>
					<td ></td>
				</tr>

			</tbody>

				<thead>
				<tr>
					<th colspan="4">
					<span>整体卫生评价
					</span>
					</th>
				</tr>
				</thead>
		<tbody>
			<logic:iterate id="i" name="ztpjList">
				<tr class='jcxmTr'>
					<td colspan="4">
							${i.xsxh}、${i.mc}
					</td>

				</tr>
			</logic:iterate>
		</tbody>






			<thead>
			<tr>
				<th colspan="4">
					<span>个人卫生评价
					</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<logic:iterate id="i" name="grpjModelList">
			<tr>
			<th colspan="4"><div align="left">${i.cwh}号床 &nbsp;&nbsp;${i.xm}</div></th>
			</tr>
				<logic:iterate id="a" name="i" property="pjList">
				<tr class='jcxmTr'>
					<td colspan="4">
							${a.xsxh}、${a.mc}
					</td>

				</tr>
			</logic:iterate>

			</logic:iterate>
			</tbody>


			<thead>
			<tr>
				<th colspan="4">
					<span>其他信息
					</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th>
					附件信息
				</th>
				<td colspan="3">
					<html:hidden property="fjid" styleId="fjid" value="${qsxx.fjid}" />
					<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
					<script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            var gid = jQuery('#fjid').val();
                            jQuery.MultiUploader_q({
                                gid : gid
                            });
                        });
					</script>
				</td>
			</tr>
			</tbody>

			  </table>
				<div style="height:35px"></div>
			    <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="Close();return false;">
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