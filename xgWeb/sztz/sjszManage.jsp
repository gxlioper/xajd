<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			function checkSjsz(){
				var sbkssj = jQuery('#sbkssj').val();
				var sbjssj = jQuery('#sbjssj').val();
			
				if ('' != sbkssj && '' != sbjssj){
					var kssj = new Date(Date.parse(sbkssj.replace(/-/g,"/")));   
					var jssj = new Date(Date.parse(sbjssj.replace(/-/g,"/")));   
					
					if (kssj > jssj){
						alertInfo('��ʼʱ�������ڽ���ʱ��!');
						return false;
					}
					saveUpdate('sztz.do?method=sjszUpdate','')
				} else {
					alertInfo('��*���Ϊ��!');
				}
			}
		</script>
	</head>
	<body>
		<html:form action="/sztz" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="3">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="3">
								<div class="btn">
									<button id="buttonSave" onclick="checkSjsz();">
										����
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th align="right">
								<font color="red">*</font>ѧ���걨��ʼʱ��
							</th>
							<td align="left" colspan="2">
								<html:text property="sbkssj" styleId="sbkssj" value="${rs.sbkssj }"
									onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss');"></html:text>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>ѧ���걨����ʱ��
							</th>
							<td align="left" colspan="2">
								<html:text property="sbjssj" styleId="sbjssj" value="${rs.sbjssj }"
									onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss');"></html:text>
							</td>
						</tr>
					</tbody>
				</table>
				<div style="height:200px"></div>
		</html:form>
		<logic:present name="message">
			<script defer="defer">
				alertInfo('${message}');
			</script>
		</logic:present>
	</body>
</html>
