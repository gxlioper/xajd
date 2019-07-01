<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type="text/javascript">
		
		function save(){
			var kssj=document.getElementById("kssj").value;
			var jssj=document.getElementById("jssj").value;
			if(kssj>jssj){
				alertInfo("����ʱ�䲻��С�ڿ�ʼʱ�䣡");
				return false;
			}
			var url="gyglnew_cssz.do?method=czcsszManage&doType=save";
			refreshForm(url);
		}


		

		</script>
	</head>
	<body >
		<html:form action="/gyglnew_cssz" method="post">
			<div class="tab_cur" >
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>${title }</a>
					</p>
			</div>

		<div class="prompt" id="pts">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>

			<div class="tab">
				<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>����Ա��λ����ʱ��</span>
							</th>
						</tr>
					</thead>
					<tbody>

					<tr>
						<th style="width:40%">
							��ʼʱ�䣺
						</th>
						<td>
							<html:text name="rs" property="kssj" styleId="kssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('kssj','y-mm-dd');" />
						</td>
					</tr>
					<tr>
						<th style="width:40%">
							��ֹʱ�䣺
						</th>
						<td>
							<html:text name="rs" property="jssj" styleId="jssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('jssj','y-mm-dd');" />
						</td>
					</tr>
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									��"<font color="red">*</font>"����Ϣ����¼��
								</div>
								<div class="btn">
									<button type="button" class="button2" id="btn_bc"  onclick="save();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("����ʧ�ܣ�")
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("�����ɹ���")
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
