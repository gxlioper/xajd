<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
			jQuery(function(){
				var ecdl = jQuery("#ecdl").val();
				if (ecdl == "fail"){
					showAlertDivLayer("���벻��ȷ��δ��ʼ�����룬��ȷ�ϡ�");
				}
			});
			
			function login(){
				var ecmm = jQuery("#ecmm").val();
				if (ecmm == ""){
					showAlertDivLayer("����д���ε�¼���롣");
					return false;
				}
				
				jQuery("form").submit();
			}
		</script>
  	</head>
  
  	<body>
  		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>������-���ε�¼</a>
			</p>
		</div>
  		<form action="">
  			<input type="hidden" id="ecdl" value="${ecdl }"/>
  			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ְ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>��������</th>
							<td><input type="password" name="ecmm" id="ecmm"/></td>
						</tr>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="2">
							<div class="btn">
								<button  type="button" onclick="login();">
									��¼
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
  		</form>
  	</body>
</html>
