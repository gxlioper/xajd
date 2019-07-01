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
				changeSqkg();
				changeXzkg();

			});
		
			/**
			���뿪�غ���
			*/
			function changeSqkg(){
				var ksqkg = jQuery("[name=ksqkg]:checked").val();
				if("1"==ksqkg){
					jQuery('#kfsjTr, #splcTr').show();
				}else if("0"==ksqkg){
					jQuery('#kfsjTr, #splcTr').hide();
				}	
			}

			/**
			���ؿ��غ���
			*/
			function changeXzkg(){
				var xzkg = jQuery("#xzkg").val();
				if("1"==xzkg){
					jQuery('#xzkgSpan').show();
				}else if("0"==xzkg){
					jQuery('#xzkgSpan').hide();
				}	
			}
			
			/**
			�����������
			*/
			function saveCssz(){

				var ksqkg = jQuery("input[name='ksqkg']:checked").val();
				var ksqkssj = jQuery("input[name='ksqkssj']").val();
				var ksqjssj = jQuery("input[name='ksqjssj']").val();

				if(ksqkg == '1'){
					if(ksqkssj.length == 0 || ksqjssj.length == 0){
						showAlertDivLayer("�뽫��������д������");
						return false;
					}
				}
				
				var url = "rcsw_zdzm_csszgl.do?method=saveCssz";
				ajaxSubFormWithFun("rcswZdzmCsszForm",url,function(data){
					showAlertDivLayer(data["message"]);
				});
			}
			
		</script>
	</head>
	<body >
		<html:form action="/rcsw_zdzm_csszgl" method="post" styleId="rcswZdzmCsszForm" >
			<div class="tab_cur">
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
						���뿪�أ������Ƿ�����ѧ�������ڶ�֤��
						<br/>
						���ؿ��أ������Ƿ�����ѧ�������ڶ�֤�������
					</span>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
		<!-- ��ʾ��Ϣ end-->
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>���뿪������</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="40%"><span class="red">*</span>���뿪��</th>
						<td>
						   
						   <logic:present name="sqkgList">
									<logic:iterate id="o" name="sqkgList" >
										<html:radio property="ksqkg" onclick="changeSqkg();" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
							</logic:present>
						</td>
					</tr>
					<tr id="kfsjTr">
						<th>���뿪��ʱ��</th>
						<td>
							<html:text  property="ksqkssj" styleId="ksqkssj"   size="10"
									onclick="return showCalendar('ksqkssj','y-mm-dd',true,'ksqjssj');" 
									onblur="dateFormatChg(this)" readonly="true">
							</html:text>
								-
							<html:text  property="ksqjssj" styleId="ksqjssj"   size="10"
									onclick="return showCalendar('ksqjssj','y-mm-dd',false,'ksqkssj');" 
									onblur="dateFormatChg(this)" readonly="true">
							</html:text>
									
						</td>
					</tr>
					<tr  id="splcTr">
						<th><span class="red">*</span>�������</th>
						<td>
							<html:select property="splid" styleId="splid" disabled="false" >
								<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
							</html:select>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="2"><span>�ڶ�֤�� ��������</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th><span class="red">*</span>���ؿ���</th>
						<td>
							<html:select property="xzkg" styleId="xzkg"  onchange="changeXzkg();">
								<html:options collection="xzkgList" property="dm" labelProperty="mc" />
							</html:select>
							<span id="xzkgSpan">
							 <logic:present name="xzkzList">
									<logic:iterate id="o" name="xzkzList" >
										<html:radio property="kxzkz" onclick="changeSqkg();" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
							</logic:present>
							</span>
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
