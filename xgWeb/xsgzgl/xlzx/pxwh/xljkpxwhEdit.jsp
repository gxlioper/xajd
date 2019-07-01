<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/pxwh/js/xljkpxwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form method="post" styleId="form"	action="/xlzx_pxwh">
		<input type="hidden" name="pxid" id="pxid" value="${pxwhForm.pxid}" />
		<input type="hidden" name="pxzt" id="pxzt" value="${pxwhForm.pxzt}" />
			<div	style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>培训信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>培训主题
							</th>
							<td colspan="3">
								${pxwhForm.pxzt}
							</td>
						</tr>
						<tr>
						<tr>
							<th>
								<span class="red">*</span>培训地点
							</th>
							<td colspan="3">
								<html:text property="pxdd" maxlength="30" styleId="pxdd" style="width:96%"></html:text>
							</td>
						</tr>
						<tr>
							<th  width="20%">
								<span class="red">*</span>培训时间
							</th>
							<td width="30%">
								<html:text property="pxsj" styleId="pxsj" onfocus="return showCalendar('pxsj','yyyy-MM-dd');" ></html:text>
							</td>
							<th  width="20%">
								<span class="red">*</span>讲师
							</th>
							<td width="30%">
								<html:text property="js" maxlength="20" styleId="js"></html:text>
							</td>
						</tr>
						<tr>
							<th  width="20%">
								<span class="red">*</span>培训上限人数
							</th>
							<td width="30%">
								<html:text property="sxrs" maxlength="4" styleId="sxrs" onblur="checkInt(this);"></html:text>
							</td>
							<th  width="20%">
								已报名人数
							</th>
							<td width="30%" id="ybmrs">
								${pxwhForm.ybmrs}
							</td>
						</tr>
						<tr>
							<th  width="20%">
								<span class="red">*</span>培训开关
							</th>
							<td width="30%" colspan="3">
								<div>
									<html:radio property="bmkg" value="开" styleId="bmkg">开</html:radio>
									<html:radio property="bmkg" value="关" styleId="bmkg">关</html:radio>
								</div>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>报名开放时间</th>
							<td colspan="3"> 
								<html:text property="kssj" styleId="kssj" onfocus="return showCalendar('kssj','yyyy-MM-dd',true,'jssj');" ></html:text>
							至
								<html:text property="jssj" styleId="jssj" onfocus="return showCalendar('jssj','yyyy-MM-dd',false,'kssj');" ></html:text>
							</td>
						</tr>
						<tr>
							<th>
								培训内容
								<br><font color="red">限500字以内</br></font>
							</th>
							<td colspan="3" >
								<html:textarea property="pxnr" style="width:94%;height:150px"  styleId="pxnr" onblur="chLengs(this,500);" ></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
									<button type="button" onclick="save('xlzx_pxwh.do?method=pxwhEdit&type=save','pxzt-pxdd-pxsj-js-sxrs-bmkg-kssj-jssj');" id="buttonSave">
										保 存
									</button>
									<button type="button" onclick="iFClose();"  id="buttonClose">
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