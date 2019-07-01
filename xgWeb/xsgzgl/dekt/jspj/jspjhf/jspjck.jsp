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
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" defer="defer">
		function save(){
			
			var jssfty = jQuery("input:radio[name='jssfty']:checked").val();
			var jshfxx = jQuery("#jshfxx").val();
			if(jssfty == null || jssfty == '' || jshfxx == '' || jshfxx == null){
				return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			}
			var sqid = jQuery("#sqid").val();
			var url = "dekt_jspjglyyxx.do?method=szdwjssfty_10698";	
			jQuery.post(url,{"jssfty":jssfty, "jshfxx":jshfxx, "sqid":sqid},function(result){
			 	showAlert(result,{},{"clkFun":function(){
    				if (parent.window){
    					refershParent();
    				}
    			}});
			});					
		}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="jspjyyxxForm" action="/dekt_jspjglyyxx"
			enctype="multipart/form-data">
			<html:hidden property="sqid"  styleId="sqid"/>
			<input type="hidden" value="${jshf.jssfty}" id="jssfty"/>
			
			<input type="hidden" value="${jshf.sqid}" id="sqid"/>
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="15%">
								ѧ��
							</th>
							<td align="left" width="20%">
								${rs.xh}
							</td>
							<th align="right" width="20%">
								����
							</th>
							<td align="left" width="30%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th align="right">
								�Ա�
							</th>
							<td align="left">
							 ${rs.xb}
							</td>
							<th align="right">
								���֤��
							</th>
							<td align="left">
							 ${rs.sfzh}
							</td>
						</tr>
						<tr>
							<th align="right">
								�꼶
							</th>
							<td align="left">
							 ${rs.nj}
							</td>
							<th align="right">
								ѧԺ
							</th>
							<td align="left">
							 ${rs.xymc}
							</td>
						</tr>
						<tr>
							<th align="right">
								רҵ
							</th>
							<td align="left">
							 ${rs.zymc}
							</td>
							<th align="right">
								�༶
							</th>
							<td align="left">
							 ${rs.bjmc}
							</td>
						</tr>
						<tr>
							<th align="right">
								������ò
							</th>
							<td align="left">
							 ${rs.zzmmmc}
							</td>
							<th align="right">
								��ϵ�绰
							</th>
							<td align="left">
							 ${rs.sjhm}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>ԤԼ��Ϣ</span>
							</th>
						</tr>
					</thead>	
					<tbody>
						<tr>
							<th align="right" width="10%">
								ԤԼ��Ϣ&nbsp;
							</th>  
							<td colspan="3">
								 ${jshf.xsyyxx}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�ظ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" >
								<font color="red">*</font>�ظ����&nbsp;
							</th>  
							<td colspan="3">
								<html:radio property="jssfty" value="1">ͬ��</html:radio>
								<html:radio property="jssfty" value="0">��ͬ��</html:radio>
							</td>
						</tr>
						<tr>
							<th align="right" >
								<font color="red">*</font>�ظ�����
								<br />
								<font color="red"><B>(��200��)</B>
								</font>
							</th>
							<td align="left" colspan="3">
								<html:textarea property='jshfxx' styleId="jshfxx" style="width:500px" rows='5' value="${jshf.jshfxx}"
									onblur="checkLen(this,200)" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									</button>
										<button type="button" onclick="save();" id="buttonClose">
											�ύ
										</button>
									</button>
										<button type="button" onclick="iFClose();" id="buttonClose">
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
