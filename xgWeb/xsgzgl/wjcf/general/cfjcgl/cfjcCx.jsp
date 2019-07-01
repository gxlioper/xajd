<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 
			searchRs();
		}

		//�鿴
		function viewCfjc(){
			var objs = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
			var cfId="";
			if(objs.length!=1){
				alertError("��ѡ��һ����¼��");
				return false;
			}else{
				cfId+=objs[0].value;
			}
			var url="general_wjcf_cfjc_ajax.do?method=ckCfjc&cfId="+cfId;
			//showTopWin(url,'780','660');
			showDialog("", 780, 525, url);
		}


		//ִ�в�ѯ����
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			var v4Path = stylePath;//v4��ʽ·��
			var url = "general_wjcf_cfjc_ajax.do?method=searchWjcfResult";
			var ie = "ie";
			
			var otherValue = [ie,v4Path];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);
			setTimeout("setDivHeight()","1000")
			
			jQuery.ajaxSetup({async:true});
		}

		//������
		function sqJc(cfId){
<%--			tipsWindown("���ֽ������","id:tmpdiv1","410","280","true","","true","id");--%>
<%--			jQuery("#cfId").val(cfId);--%>
<%--			jQuery('#sqly').val('');--%>
<%--			jQuery('#lx').val('zj');--%>
			var url="general_wjcf_cfjc_ajax.do?method=zjJcSq&cfId="+cfId;
			showDialog("", 400, 220, url);
			}

		//���� 
		function save(){
			var lx = $("lx").value;
			var sqly = $("sqly").value;
			 if(""==sqly){
					alertError("�뽫��*����Ŀ��д������");
					return false;
					}
				var v4Path = stylePath;//v4��ʽ·��
				var ie = "ie";	
			refreshForm('general_wjcf_cfjc_ajax.do?method=saveJcSq&lx='+lx);
		}

		//�޸Ľ��
		function xgJc(cfId){
<%--			jQuery("#cfId").val(cfId);--%>
<%--			jQuery.ajaxSetup({async:false});	--%>
<%--			jQuery('#lx').val('xg');			--%>
<%--			tipsWindown("���ֽ������","id:tmpdiv1","410","280","true","","true","id");--%>
<%--			jQuery.post("general_wjcf_cfjc_ajax.do?method=xgJcSq",--%>
<%--							{cfId:cfId},--%>
<%--							function(result){--%>
<%--								var json=eval(result);--%>
<%--									jQuery("#sqly").val(json[0].sqly);--%>
<%--							}--%>
<%--						);--%>
<%--			--%>
<%--			jQuery.ajaxSetup({async:true});	--%>
			
			var url="general_wjcf_cfjc_ajax.do?method=xgJcSq&cfId="+cfId;
			showDialog("", 400, 220, url);
			}

		//�������
		function cxJc(cfId){
			confirmInfo("�˲�������ɾ���������ļ�¼���Ƿ�ȷ����",function(tag){
				if(tag=="ok"){
					jQuery.ajaxSetup({async:false});
					var url = "general_wjcf_cfjc_ajax.do?method=cxJcSq";
					//����
				 	var parameter = {
				 			"cfId":cfId
					};
					
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,
						parameter,
						function(result){
							alertInfo(result);
							searchRs();
						}
					);
					jQuery.ajaxSetup({async:true});
				}
			});
			
			}
		
		jQuery(function(){
			onShow();
		})
		
		function cfjcwhExportConfig() {
		//DCCLBH�������ܱ��,ִ�е������� 
		customExport("general_wjcf_cfjc_ajax.do", cfjcwhExportData);
		}
			
		
			
		// ��������
		function cfjcwhExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "general_wjcf_cfjc_ajax.do?method=cfjcwhExportData&dcclbh=" + "general_wjcf_cfjc_ajax.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<html:form action="/general_wjcf_cfjc_ajax" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="message" id="message" value="${message }">	
			<html:hidden property="cfId" styleId="cfId"/>	
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="viewCfjc();return false;" class="btn_ck">
								�鿴
							</a>
						</li>
						<li><a href="#" class="btn_dc" onclick="cfjcwhExportConfig();return false;">����</a></li>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>

					--%></ul>
				</div>
				<!-- ��ť end-->
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
			<h3 class="datetitle_01">
					<span> ��ѯ���</span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

			<!--������ begin-->
		
				<input type="hidden" name="lx" id="lx" />
				<div id="tmpdiv1" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>���ֽ������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="">
								<td align="right" width="25%">
									<font color="red">*</font>�������ɣ�<br/>
								<font color="red"><B>(��1000��)</B></font>
								</td>
								<td align="left" style="width:80%" >
									<html:textarea  property='sqly' styleId="sqly" style="width:250px;height: 90%; margin: 1px;"
									rows='8' onblur="checkLen(this,1000)"/>
								</td>
							</tr>
							
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button"  onclick="save();return false;">
											�� ��
										</button>
										
										<button type="button"  onclick="closeWindown();">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
				
				
			<!--������ end-->

				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=wjcfGeneralForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>