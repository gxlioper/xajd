<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zyzgk/js/zyzgk.js"></script>
		
		<style type="text/css">
			.zgktable td{border:none;}
		</style>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /> </a>
		   </p>
			<p class="help">
				<a href="#" onclick="return false;" onmouseover ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		</div>
		<html:form action="/xpj_zyzgk" method="post" styleId="ZyzgkModel">
			<div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="3">
								<span>רҵ���ɿ�����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="3">
								<table width="100%" >
								<th width="10%">
									<font color="red">*</font>����ѧ�� 
								</th>
								<td width="20%" >
									<html:select property="pjxn" styleId="pjxn" style="width:120px" onchange="getZgkList();">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th width="10%">
									ѧԺ 
								</th>
								<td width="25%" >
									<html:select property="xydm" styleId="xydm" style="width:96%;"  onchange="changeXy();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</td>
								<th width="10%">
									רҵ
								</th>
								<td width="25%" >
									<select name="zyCond" id="zyCond" style="width:96%;" onchange="getZgkList();">
								</td>
								</table>
							</td>						
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="3">
								<span>רҵ�γ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="3">
								<table width="100%" id="zykxx"></table>
							</td>						
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td align="center" colspan="3">							
			        			<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveForm();return false;" id="buttonSave">
										�� ��
									</button>	
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
	<script language="javascript">
		jQuery(function(){
			var pjxnObj = jQuery("#pjxn");
			var pjxn = jQuery("#pjxn").val();
			getZgkList(pjxnObj);
			getYszZgk(pjxn);
		})
	</script>
</html>

