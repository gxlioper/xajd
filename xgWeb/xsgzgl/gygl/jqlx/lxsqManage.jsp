<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//��ʼ��
		function onShow(){ 
			searchRs();
		}

		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "gyglnew_jqlx_ajax.do?method=jqlxsqSearch";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		
		function deleteLxsq(){
			
			var n=jQuery("[name=div_pkValue]:checked").length;
			
			if(n>0){
				confirmInfo("�ò�������<font color='blue'>ȡ���ѹ�ѡ����У����</font>���Ƿ����ִ�иò�����",function(tag){
					
					if(tag=="ok"){
						
						var pkValue=new Array();
						
						var xh=new Array();
						
						jQuery("[name=div_pkValue]:checked").each(function(i){
							
							pkValue[i]=jQuery(this).val();
						
						});
						
						var parameter={}
						
						
						parameter["pkValue"]=escape(pkValue.join("!!array!!"));
						
						var url= "gyglnew_jqlx_ajax.do?method=delete";
						
						jQuery.ajaxSetup({async:false});	
						
						jQuery.post(url,
							parameter,
							function(result){
							
								alertInfo(result,function(tag){
									
									if(tag=="ok"){
										searchRs();
									}
								
								});
								
							}
						);
						
						jQuery.ajaxSetup({async:true});
					}
				});
			}else{
				
				alertInfo("�빴ѡ��Ҫȡ������ļ�¼��",function(tag){
					
					if(tag=="ok"){
						return false;
					}
				
				});
			}
		}
		
		
		//��ʾ�ҵ�������ϸ
		function showLxsqDiv(){
		
			tipsWindown("ϵͳ��ʾ","id:div_lxsq","400","300","true","","true","id");
		}
		
		function saveLxsq(){
			
			
			if($("kssj") && $("kssj").value==""){
				alertInfo("סУ��ʼʱ�䲻��Ϊ�գ�");
				return false;
			}
			
			if($("jssj") && $("jssj").value==""){
				alertInfo("סУ����ʱ�䲻��Ϊ�գ�");
				return false;
			}
			
			if(checkSjTj('kssj',"סУ��ʼʱ��","jssj","סУ����ʱ��")){
				
				confirmInfo("�ò������ᱣ�������У������Ϣ���Ƿ�ȷ����",saveLxsqInfo);
			}
	     }
		
		function saveLxsqInfo(tag){
     		
     	
			if(tag=="ok"){
				
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
				
				parameter["xh"]=jQuery("#xh").val();
				
				parameter["kssj"]=escape(jQuery("#kssj").val());
				
				parameter["jssj"]=escape(jQuery("#jssj").val());
				
				parameter["sqly"]=escape(jQuery("#sqly").val());
				
				parameter["lcid"]=escape(jQuery("#lcid").val());
				
				var url = "gyglnew_jqlx_ajax.do?method=save";
	          	
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				closeWindown()
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						
						alertInfo(result,function(){
						
							searchRs()
						});
						
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body >

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/gyglnew_jqlx" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<html:hidden property="lcid" styleId="lcid"  />
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="showLxsqDiv();return false;" class="btn_zj">������У</a>
						</li>
						<li>
							<a href="#" onclick="deleteLxsq();return false;" class="btn_sc">ȡ������</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �๦�ܲ����� end-->

			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jqlxForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--						$('choose').className="hide";--%>
<%--				</script>--%>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			
			<div id="div_lxsq" style="display:none">
				<table class="formlist">
					<thead>
					<tr>
					<th colspan="2">
					<span>ѧ������</span>
					</th>
					</tr>
					</thead>

					<tbody>
					<tr>
					<th width='25%'>
						<font color="red">*</font>Ԥ��סУ<br/>��ʼʱ��
					</th>
					<td style='word-break:break-all;width:96%' >
						 <html:text property="kssj" styleId="kssj" 
							onclick="return showCalendar('kssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" readonly="true" />
					</td>
					</tr>
					<tr>
					<th width='25%'>
						<font color="red">*</font>Ԥ��סУ<br/>����ʱ��
					</th>
					<td style='word-break:break-all;width:96%'>
						 <html:text property="jssj" styleId="jssj" 
							onclick="return showCalendar('jssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" readonly="true" />
					</td>
					</tr>
					<tr>
					<th width='25%'>
						��������<br/>
						<font color="blue">(������500��)</font>
					</th>
					<td style='word-break:break-all;width:96%'>
						 <html:textarea  property="sqly" rows="4" 
						 style="word-break:break-all;width:99%" styleId="sqly" 
									onblur="chLeng(this,500);"></html:textarea>
					</td>
					</tr>
					</tbody>
			
					<tfoot>
					<tr>
					<td  colspan="2">
					<div class="btn">
					<button type="button" id="btn_bc" onclick="saveLxsq()">
					�� ��
					</button>
			
					<button type="button" id="btn_gb" onclick="closeWindown();return false;">
					�� ��
					</button>
					</div>
					</td>
					</tr>
					</tfoot>
				</table>
			</div>		
				
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
