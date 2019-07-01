<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gywp/js/gywp.js"></script>
		<script type="text/javascript" >
			jQuery(
				function(){
					var sfwh=jQuery("#sfwh").val();
					if(sfwh=="是"){
						var hhyy=jQuery("#hhyy");
						hhyy.attr("readonly",true);
					}
				}
			);
		</script>
	</head>
	<body>
		<html:form action="gygl_qywpxx.do?method=updateGywpxx" styleId="gywpxxForm" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="id"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>公寓物品信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width: 20%">物品名称</th>
							<td style="width: 30%">
								<bean:write name="gywpxxForm" property="wpmc"/>
							</td>
							<th style="width: 20%"><font color="red">*</font>所属大类</th>
							<td style="width: 30%">
								<html:select property="wpdldm" styleId="wpdldm" style="width:90%">
									<html:options collection="wpdlList" property="wpdldm" labelProperty="wpdlmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>物品类别</th>
							<td>
								<html:select property="wplbdm" styleId="wplbdm" style="width:90%">
									<html:options collection="wplbList" property="wplbdm" labelProperty="wplbmc"/>
								</html:select>
							</td>
							<th><font color="red">*</font>数量</th>
							<td>
								<html:text onkeyup="checkInputData(this)" maxlength="5" property="sl" styleId="sl" style="width:90%"></html:text>
							</td>
						</tr>
						<tr>
							<th>是否在合格期</th>
							<td>
								<html:select property="sfzhgq" styleId="sfzhgq" style="width:90%">
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
							<th>是否完好</th>
							<td>
								<html:select property="sfwh" styleId="sfwh" style="width:90%" onchange="changeSfwh()">
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>所属楼栋</th>
							<td><bean:write name="gywpxxForm" property="ldmc"/></td>
							<th>所属寝室</th>
							<td><bean:write name="gywpxxForm" property="qsh"/></td>
						</tr>
						<tr>
							<th>
								毁坏原因
								<br/>
								<font color="red">限制输入200字</font>
							</th>
							<td colspan="3">
								<html:textarea onblur="chLengs(this,200);" property="hhyy" styleId="hhyy" rows="4" style="width:98%" title="是否完好字段为否时可填"></html:textarea>
							</td>
						</tr>
						<tr>
							<th rowspan="4">
								备注
								<br/>
								<font color="red">限制输入200字</font>
							</th>
							<td rowspan="4" colspan="3">
								<html:textarea onblur="chLengs(this,200);" property="bz" styleId="bz" rows="4" style="width:98%" title="在此输入具体毁坏的物品"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz" align="left">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveUpdate();">
										保存
									</button>
									<button type="button" onclick="refreshParent2();">
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