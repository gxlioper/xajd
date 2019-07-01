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
			/**
			�����������
			*/
			function saveCssz(){
				
				var sqkg = jQuery(":radio[name=sqkg]:checked");
				var shkg = jQuery(":radio[name=shkg]:checked");
				var shlc = jQuery("#shlc").val();
				
				if (shlc == "" || sqkg.size() == 0 || shkg.size() == 0){
					showAlertDivLayer("�뽫��������д������");
					return false;
				}
				
				var url = "spl_cssz.do?method=save";
				ajaxSubFormWithFun("form",url,function(data){
					showAlertDivLayer(data["message"]);
				});
			}
			
		</script>
	</head>
	<body >
		<html:form action="/spl_cssz" method="post" styleId="form" >
			<html:hidden property="id" />
			<html:hidden property="ssmk" />
		
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2"><span>��������</span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="40%"><span class="red">*</span>���뿪��</th>
							<td>
								<logic:iterate id="o" name="kgList" >
									<html:radio property="sqkg" value="${o.dm}">${o.mc}</html:radio>
								</logic:iterate>								
							</td>
						</tr>
						<tr>
							<th>���뿪��ʱ��</th>
							<td>
								<html:text  property="sqkssj" styleId="sqkssj"   size="10"
										onclick="return showCalendar('sqkssj','y-mm-dd',true,'sqjssj');" 
										onblur="dateFormatChg(this)" readonly="true">
								</html:text>
									-
								<html:text  property="sqjssj" styleId="sqjssj"   size="10"
										onclick="return showCalendar('sqjssj','y-mm-dd',false,'sqkssj');" 
										onblur="dateFormatChg(this)" readonly="true">
								</html:text>
										
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>�������</th>
							<td>
								<html:select property="shlc" styleId="shlc">
									<html:option value=""></html:option>
									<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="40%"><span class="red">*</span>��˿���</th>
							<td>
								<logic:iterate id="o" name="kgList" >
									<html:radio property="shkg" value="${o.dm}">${o.mc}</html:radio>
								</logic:iterate>								
							</td>
						</tr>
						<tr>
							<th>��˿���ʱ��</th>
							<td>
								<html:text  property="shkssj" styleId="shkssj"   size="10"
										onclick="return showCalendar('shkssj','y-mm-dd',true,'shjssj');" 
										onblur="dateFormatChg(this)" readonly="true">
								</html:text>
									-
								<html:text  property="shjssj" styleId="shjssj"   size="10"
										onclick="return showCalendar('shjssj','y-mm-dd',false,'shkssj');" 
										onblur="dateFormatChg(this)" readonly="true">
								</html:text>
							</td>
						</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">	
				          	<logic:equal name="writeAble" value="yes">			            
								<button type="button" class="button2" onclick="saveCssz();return false;"
									id="btn_save">
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
