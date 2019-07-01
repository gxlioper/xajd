<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type="text/javascript">

			jQuery(function(){
				if(jQuery("input[name='code']") == '0'){
					
				}
			});
			
			/**
			�����������
			*/
			function saveCssz(){
				var url = "xljk_xlwygl_jcszwh.do?method=bcJcsz";
				ajaxSubFormWithFun("xlwygljcszForm",url,function(data){
					showAlertDivLayer(data["message"]);
				});
			}
			
		</script>
	</head>
	<body >
		<html:form action="/xljk_xlwygl_jcszwh" method="post" styleId="xlwygljcszForm" >
			<html:hidden property="code" value="${code}"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>������������</span></th>
					</tr>
				</thead>
				<tbody>
					<tr  id="splcTr1">
						<th width="35%"><span class="red">*</span><span style="font-weight: bold">�༶�ܱ��ճ�  </span>�������</th>
						<td>
							<html:select property="bjzbrcSplcid" styleId="bjzbrcSplcid" disabled="false" >
								<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
							</html:select>
						</td>
					</tr>
					<tr  id="splcTr2">
						<th><span class="red">*</span><span style="font-weight: bold">��Ԣ�ܱ��ճ�  </span>�������</th>
						<td>
							<html:select property="gyzbrcSplcid" styleId="gyzbrcSplcid" disabled="false" >
								<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
							</html:select>
						</td>
					</tr>
					<tr  id="splcTr3">
						<th><span class="red">*</span><span style="font-weight: bold">ƽʱ��Ϣ�ϱ�  </span>�������</th>
						<td>
							<html:select property="psxxsbSplcid" styleId="psxxsbSplcid" disabled="false" >
								<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
							</html:select>
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">	
			          <logic:equal name="writeAble" value="yes">			            
						<button type="button" class="button2" onclick="saveCssz();return false;" id="btn_save">
							�� ��
						</button>
						</logic:equal>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>
