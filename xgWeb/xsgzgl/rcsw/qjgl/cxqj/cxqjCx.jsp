<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�wujian-->
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
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		function searchRs(){
			var url = "rcsw_cxqj_ajax.do?method=cxqjCx";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}
		
		function showView(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				
				var xysh=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(5).html();
				
				var url="rcsw_cxqj.do?method=cxqjCk";
				
				url+="&pkValue="+pkValue;
				
				showTopWin(url,800,600);
			}else{
				
				alertInfo("�빴ѡһ����Ҫ�鿴�ļ�¼��");
				
				return false;
			}
		}
		
		function cxqjDiv(topMsg){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){
				var array = jQuery("[name=div_pkValue]:checked");
				var sfcx = true;
				for (var i=0;i<array.length;i++) {
					if(jQuery(array[i]).parent().parent().find("td").eq(9).html()=="��"){
						sfcx = false;
						break;
					}
				}
				if(sfcx==true){
					tipsWindown(topMsg,"id:tempDiv","350","200","true","","true","id");
				}else{
					alertInfo("�Ѿ��������,�����ٽ��г�����");
				}
			}else{
				alertInfo("�빴ѡ��Ҫ�����ļ�¼��");
				return false;
			}
		}
		
		function cxyyDivSave(){
			var cxyy = jQuery("#cxyy").val();
			if($("cxyy") && $("cxyy").value==""){
	     		alertInfo("����ԭ����Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}else{
	     		confirmInfo("�ò������᳷�������Ϣ���Ƿ�ȷ������������",function(tag){
				if(tag=="ok"){
					var str="";
					var array = jQuery("[name=div_pkValue]:checked");
					for (var i=0;i<array.length;i++) {
						var id = jQuery(array[i]).val();
						str +=id+"!@";
					}
					var parameter={}
					var url="rcsw_cxqj.do?method=cxqjChx";	
					parameter["str"]=str;
					parameter["cxyy"]=escape(cxyy);
					jQuery.ajaxSetup({async:false});	
					jQuery.post(url,parameter,function(result){
						alertInfo(result,function(tag){
							if(tag=="ok"){
								onShow();
							}
						});
					});
					jQuery.ajaxSetup({async:true});
				}
			});
	     	}
		}
		
		function qxCx(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){
				var array = jQuery("[name=div_pkValue]:checked");
				var sfcx = false;
				for (var i=0;i<array.length;i++) {
					if(jQuery(array[i]).parent().parent().find("td").eq(9).html()==""){
						sfcx = true;
						break;
					}
				}
				if(sfcx==false){
					confirmInfo("�ò�������ȡ�����������Ϣ���Ƿ�ȷ������������",function(tag){
					if(tag=="ok"){
						var array = jQuery("[name=div_pkValue]:checked");
						var str = "";
						for (var i=0;i<array.length;i++) {
							var id = jQuery(array[i]).val();
							str +=id+"!@";
						}
						var parameter={}
						var url="rcsw_cxqj.do?method=cxqjQx";	
						parameter["str"]=str;							
						jQuery.ajaxSetup({async:false});	
						jQuery.post(url,parameter,function(result){
							alertInfo(result,function(tag){
								if(tag=="ok"){
									onShow();
								}
							});
						});
						jQuery.ajaxSetup({async:true});
					}
				});
				}else{
					alertInfo("��ѡ��¼δ������ٵ�����ȡ����");
				}
			}else{
				alertInfo("�빴ѡ��Ҫȡ�����������ݣ�",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
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
				<em>���ĵ�ǰλ�ã�</em><a>�ճ�����-��ٹ���-������� </a>
			</p>
		</div>


		<html:form action="/rcsw_cxqj" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<li id="li_js">
							<a href="#" onclick="refreshForm('rcsw_qjgl_mygz_tea.do');return false;" class="btn_fh">
								����
							</a>
						</li>
						<li>
							<a href="#" onclick="showView();return false;" class="btn_ck">�鿴����</a>
						</li>
						<li>
							<a href="#" onclick="cxqjDiv('�������');return false;" class="btn_zj">�������</a>
						</li>
						<li>
							<a href="#" onclick="qxCx();return false;" class="btn_sc">ȡ������</a>
						</li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">����</a></li>
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
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=cxqjForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_select_xn">
								<th width="30%">
									<span class="red">*</span>����ԭ����500�֣�
								</th>
								<td width="70%">
									<html:textarea  property='cxyy' styleId="cxyy" style="word-break:break-all;width:99%" onblur="chLeng(this,1000);"
									rows='6'/>
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
										<button type="button" name="����" onclick="cxyyDivSave();closeWindown()">
											�� ��
										</button>
										<button type="button" name="ȡ��" onclick="closeWindown();return false;">
											�ر�
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
	</body>
</html>