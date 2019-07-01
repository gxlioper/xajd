<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			/**
			�����������
			*/
			function saveCssz(){
				var ids = "splc1-splc2";
				if(!checkNotNull(ids)){
					return showAlertDivLayer("�뽫��<font class='red'>*</font>����Ŀ��д������");
				}
				var url = "xlzxnew_cssz.do?method=saveCssz";
				ajaxSubFormWithFun("XlzxSbJcszForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						document.location.href = "xg_xlzxnew_cssz.do";
					}});
				});
			}
			
		</script>
	</head>
	<body >
		<html:form action="/xlzxnew_cssz" method="post" styleId="XlzxSbJcszForm" >
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
					<tr >
						<th width="35%"><span class="red">*</span><span style="font-weight: bold">�༶�ܱ��ճ�  </span>�������</th>
						<td>
							<html:select property="splc" styleId="splc1" disabled="false" value="${zbsplc}" >
								<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
							</html:select>
							<input type="hidden" name="lx" value="zb"/>
						</td>
					</tr>
					<tr >
						<th><span class="red">*</span><span style="font-weight: bold">�±��ճ�  </span>�������</th>
						<td>
							<html:select property="splc" styleId="splc2" disabled="false" value="${ybsplc}" >
								<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
							</html:select>
							<input type="hidden" name="lx" value="yb"/>
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
