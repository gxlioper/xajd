<%@ page language="java" pageEncoding="GB18030"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript">
			function saveZxjs(){
				if(frames('eWebEditor1').getHTML()){
					$('jsnr').value = frames('eWebEditor1').getHTML();
				}
				saveUpdate('jywebUseCheckSession.do?method=zxjsSave','lx-jsnr');
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>中心介绍</a>
			</p>
		</div>
	
		<html:form action="/jyweb" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>中心介绍</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td>
								<div class="btn">
									<button name="提交" id="buttonSave"
										onclick="saveZxjs();">
										保存
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<td>
								<input type="hidden" name="lx" id="lx" value="1" />
								<input type="hidden" name="content1" value="<bean:write name="jsnr" />" />
								<input type="hidden" name="jsnr" id="jsnr" value="<bean:write name="jsnr" />" />
								<iframe id="eWebEditor1" src="BatEditor" frameborder="0"
									scrolling="no" width="96%" height="350">
									<bean:write name="jsnr" filter="false"/>
								</iframe>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		
		<logic:present name="message">
			<script>
				alert('${message}');
			</script>
		</logic:present>
		
	</body>
</html>
