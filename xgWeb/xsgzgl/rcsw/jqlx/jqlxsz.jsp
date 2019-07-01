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
		jQuery(document).ready(function(){ 
			changeSqkg();
		});

		//���¸�λ����
		function changeSqkg(){
			var sqkg = jQuery("[name=sqkg]:checked").val();
			if("1"==sqkg){
				jQuery("table select,input:not(input:radio[name=sqkg])").attr("disabled",false);
				
			}else if("0"==sqkg){
				jQuery("table select,input:not(input:radio[name=sqkg])").attr("disabled","disabled");
			}
		}

		function saveJcsz(){
			
			var sqkglength = jQuery("[name=sqkg]:checked").length;
			if(sqkglength==0){
				showAlertDivLayer("���������뿪��!");
				return false;
			}
			var splc = jQuery("#splc").val();
			var sqkg = jQuery("[name=sqkg]:checked").val();
			
			if ("1"==sqkg && (splc == "" || splc == null)){
				showAlertDivLayer("��ѡ���������!");
				return false;
			}
			
<%--			if("1"==sqkg && (jQuery("#sqjssj").val()=="" || jQuery("#sqkssj").val()=="")){--%>
<%--				showAlertDivLayer("����ʱ��ͽ���ʱ�������д!");		--%>
<%--				return false;--%>
<%--			}--%>

			if("1"==sqkg && (jQuery("#lxkssj").val()=="" || jQuery("#lxjssj").val()=="")){
				showAlertDivLayer("ѧ����У��ֹʱ�䲻��Ϊ��!");		
				return false;
			}

			var url = "rcsw_jqlx_jqlxsz.do?method=saveJqlxsz";
			ajaxSubFormWithFun("jqlxszModel",url,function(data){
				showAlertDivLayer(data["message"]);
			});
			
		}
		</script>
	</head>
	<body >
		<html:form action="/rcsw_jqlx_jqlxsz" method="post" styleId="jqlxszModel" >
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
						   
						   <logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList" >
										<html:radio property="sqkg" onclick="changeSqkg();" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
						</td>
					</tr>
					<tr>
						<th>���뿪��ʱ��</th>
						<td>
							<html:text  property="sqkssj" styleId="sqkssj"   size="10"
									onclick="return showCalendar('sqkssj','y-mm-dd',true,'sqjssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								-
								<html:text  property="sqjssj" styleId="sqjssj"   size="10"
									onclick="return showCalendar('sqjssj','y-mm-dd',false,'sqkssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
									
						</td>
					</tr>
					<% String xxdm = (String) request.getAttribute("xxdm"); %>
					<% if("11458".equals(xxdm) || "10351".equals(xxdm) || "10277".equals(xxdm) || "10530".equals(xxdm)){ %>
					<tr>
						<th><span class="red">*</span>��������</th>
						<td>
							<html:select property="jqlx" styleId="jqlx"  style="width: 150px" value="${jqlxV}">
								<html:options collection="jqlxList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<% } %>
					<tr>
						<th><span class="red">*</span>�������</th>
						<td>
							<html:select property="splc" styleId="splc" disabled="false" >
							<html:options collection="shlcList" property="splc"
								labelProperty="lcxx" />
						</html:select>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>ѧ����У��ֹʱ��</th>
						<td>
							<html:text  property="lxkssj" styleId="lxkssj"   size="10"
									onclick="return showCalendar('lxkssj','y-mm-dd',true,'lxjssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								-
								<html:text  property="lxjssj" styleId="lxjssj"   size="10"
									onclick="return showCalendar('lxjssj','y-mm-dd',false,'lxkssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
									
						</td>
					</tr>
					<tr>
						<th>��УЭ����</th>
						<td>
							<html:hidden property="fjid" styleId="fjid" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									jQuery.MultiUploader({
										maxcount : 1,
										//��׺
										accept : 'doc| docx| jpg| gif| png| bmp',
										//����ļ���С ��λM
										maxsize: 5,
										//��Ÿ������������id
										elementid : 'fjid'
										});
								});
							</script>  
						</td>
					</tr>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">			            
						<logic:equal name="writeAble" value="yes">
						<button type="button" class="button2" onclick="saveJcsz();return false;" id="btn_save">
							�� ��
						</button>
						</logic:equal>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
				</tbody>
			</table>
			</div>
		</html:form>
	</body>
</html>
