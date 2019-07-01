<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/axcs/wpsz/js/wptjsz.js"></script>
		<script language="javascript">
		jQuery(function(){
			setInit();
		  });
		</script>
	</head>
	<body>
		<html:form action="/axcsWpsz" method="post" styleId="WpszForm">
			<html:hidden property="xmdm" styleId="xmdm" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="8">
								<span>申请条件设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_tjszxx">
						<logic:iterate id="i" name="knsdcList" indexId="index01">
						<tr id="tr_${index01}">
						<td style="background:#e4f0fe;height:30px;">
						<input type="checkbox" name="tjsz" id="td_${i.dcdm}_lv1" value="${i.dcdm}" onclick="parentTjClick(this);"/>${i.dcmc}学生才能申请
						<logic:iterate id="t" name="tjpzList" indexId="index02">
						<td>
						<input type="checkbox" name="tjz_${i.dcdm}" id="td_${i.dcdm}_${index02}_lv2" value="${t.tjz}" onclick="childTjClick(this);"/>${t.tjmc}
						</td>
						</logic:iterate>
						</tr>
						</logic:iterate>
					</tbody>
				</table>
				</div>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" onclick="iFClose();">
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

