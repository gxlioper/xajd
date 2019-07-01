 <%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type="text/javascript" src="xsgzgl/xszz/xgzdsz/js/xgzdsz.js"></script>
		
		<style>.bold{font-weight: bold!important;}</style>
		<script type="text/javascript">
		jQuery(function(){
			initData();
		})
		</script>
		
	</head>
	<body >
		<html:form action="/jtqkdc_xgzdsz" method="post" styleId="XgzdForm">
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
				</p>
			</div>
			<!-- ��ʾ��Ϣ end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						���ѡ��"����"������ֶ����޸ļ�ͥ���������Ϣ����ʱ����֤��������޷����棡
					</span>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<table width="100%" border="0" class="formlist" id="content">
				<thead id="jtcyxx_thead">
					<th colspan="6">
							<span>��ͥ��Ա��Ϣ</span>
							<label style="margin-left:98px;">
								<input type="checkbox" value="yes" name="jtcyxx" onclick = "changePlbt('jtcyxx','yes')" id ="check2"/>
								<label for="check2">ȫ������</label>
								<input type="checkbox" value="no" name="jtcyxx" onclick = "changePlbt('jtcyxx','no')" id ="check1"  />
								<label for="check1">ȫ���Ǳ��� </label>
							</label>
					</th>
				</thead>
				<tbody id="jtcyxx">
					
				</tbody>
				<thead id="jtqkxx_thead">
					<th colspan="6">
							<span>��ͥ���</span>
							<label style="margin-left:122px;">
								<input type="checkbox" value="yes" name="jtqkxx" onclick = "changePlbt('jtqkxx','yes')" id="check4" />
								<label for="check4">ȫ������</label>
								<input type="checkbox" value="no" name="jtqkxx" onclick = "changePlbt('jtqkxx','no')" id="check3" />
								<label for="check3">ȫ���Ǳ���</label>
							</label>  
					</th>
				</thead>
				<tbody id="jtqkxx">
					
				</tbody>
				
				<thead id="fjxx_thead">
					<th colspan="6">
							<span>������Ϣ</span>
					</th>
				</thead>
				<tbody id="fjxx">
					
				</tbody>
			</table>
			<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" class="button2" id="btn_bc"  onclick="saveData();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>
 