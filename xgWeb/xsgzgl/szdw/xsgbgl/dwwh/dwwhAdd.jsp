<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xsgbgl/js/dwwh.js"></script>
		<link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
		<script type="text/javascript">
			jQuery(function(){
				//Ϊbuttonע���¼�
				jQuery("#but_save").click(function(){save("dwwhAdd");});
//				jQuery("input[name='zzzt']").click(on_zzxx);
				jQuery("#but_close").click(go_back);
				jQuery("#tbody_zwxx").load("szdw_xsgb_zwwh.do?method=zwView");
                getZwxx();
			});
			
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_xsxxgl" method="post" styleId="demoForm">
			<div style='width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���Ӱ�ί����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th width="20%">
							<font color="red">*</font>�����༶
						</th>
						<td width="30%">
							<input type="text" name="bjmc" value="" id="bjmc" style="width:159px;" readonly="readonly"
								   class="text_nor"  >
							<input type="hidden" name="bjdm" id="bjdm" value=""/>
							<button class="btn_01" type="button"
									onclick="showDialog('��ѡ��һ���༶',800,500,'ztbhgl_ztbhjg.do?method=getBj');">ѡ��
							</button>
						</td>
						<th width="20%">
							������Ժ
						</th>
						<td width="30%" id="syTd">

						</td>
					</tr>
					<tr>
						<th width="20%">
							�꼶
						</th>
						<td width="30%" id="njTd">

						</td>
						<th width="20%">
							ѧ��
						</th>
						<td width="30%" id="xnTd">
							${xn}
						</td>
					</tr>
					<tr>
						<th width="20%">
							����Ա����
						</th>
						<td width="30%" id="fdyTd">

						</td>
						<th width="20%">
							��ϵ�绰
						</th>
						<td width="30%" id="lxdhTd">

						</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ί���Ա��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_cyxx">

					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4" >
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" id = "but_save">
										�� ��
									</button>
									<button type="button" type="button" id= "but_close" onclick="iFClose();">
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
</html>

