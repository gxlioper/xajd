<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�cq -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		jQuery(document).ready(function(){ 
			searchRs();
		});

		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}


		//��ѯ
		function searchRs(){
			var url = "gyjl_gyjlglnew_ajax.do?method=gyjlxxsh"; 
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//ɾ��
		function del(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			var blog=true;
			if(len>=1){	
				if(blog){
					confirmInfo("�ò�������ɾ��������ѡ�����ݣ��Ƿ�ȷ������������",function(tag){
						if(tag=="ok"){
							var array = jQuery("[name=div_pkValue]:checked");
							var str = "";
							for (var i=0;i<array.length;i++) {
								var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
								str += pkValue+"!!@@";
							}
							var parameter={}
							var url="gyjl_gyjlglnew.do?method=gyjlSc";	
							parameter["str"]=str;							
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
				}
			}else{
				alertInfo("�빴ѡ��Ҫɾ�������ݣ�",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		
		//���
		function gyjlSh(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var pk_value=jQuery("[name=div_pkValue]:checked").eq(0).val();

				var xh=pk_value.split("!!shen!!")[0];

				var jlsj=pk_value.split("!!shen!!")[2];
				
				var gyjllbdm=pk_value.split("!!shen!!")[1];
				
				var url="gyglnew_gyjlgl_gyjlclsh.do";
				
				url+="?xh="+xh+"&jlsj="+jlsj+"&gyjllbdm="+gyjllbdm;

				//showTopWin(url,800,610);
				if(jQuery("#xxdm").val() == '11799'){
					showDialog("��˹�Ԣ������Ϣ", 760, 505, url);
				}else{
					showDialog("��˹�Ԣ������Ϣ", 760, 505, url);
				}
			
			}else if(len>1){
				//tipsWindown("","id:shDiv","450","250","true","","true","id");
				tipsWindownNew("","id:shDiv",550,250);
			}else{
				
				alertInfo("�빴ѡ��Ҫ��˵ļ�¼��");
				
				return false;
			}
		}
		
		//�������
		function gyjlPlsh(){
				var shzt = jQuery("#shzt").val();
			//	confirmInfo("ȷ��Ҫ����ѹ�ѡ�ļ�¼��?",function(tag){
			//		if(tag=="ok"){
						var array = document.getElementsByName("div_pkValue");
						var pk = "";
						for (var i=0;i<array.length;i++) {
							if (array[i].checked) {
								pk+= array[i].value;
								pk+="!!@@";
							}
						}
						var url="gyjl_gyjlglnew_ajax.do?method=gyjlxxPlsh";	
						var parameter={}
						parameter["pkValue"]=pk;
						parameter["shzt"]=shzt;	
						parameter["shyj"]=escape(jQuery("#shyj").val());		
						jQuery.ajaxSetup({async:false});	
						jQuery.post(url,
							parameter,
							function(result){
								alertInfo(result,function(tag){
									if(tag=="ok"){
										closeWindown();
										searchRs();
									}
								});
							}
						);
					//}else {
						return false;
					//}
				//});
			}
		
		//˫���鿴
		function ShowView(){
			
			var pk_value=curr_row.getElementsByTagName('input')[0].value;
			
			var xh=pk_value.split("!!shen!!")[0];
			
			var jlsj=pk_value.split("!!shen!!")[2];
				
			var gyjllbdm=pk_value.split("!!shen!!")[1];
			
			var url="gyglnew_gyjlgl.do?method=gyjlcxView&act=clview";
			
			url+="&xh="+xh+"&wjsj="+jlsj+"&gyjllbdm="+gyjllbdm;

			//showTopWin(url,800,600);
			showDialog("��Ԣ������Ϣ��˲鿴", 800,500,url);
		}
		function gyjltjDc(){
			var url = "gygl_gyjl_wjtj.do?method=getGyjltj";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}

		function gyjlxxshExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("gyjl_gyjlglnew_ajax.do", gyjlxxshExportData);
			}
			
		
			
		// ��������
		function gyjlxxshExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gyjl_gyjlglnew_ajax.do?method=gyjlxxshexportData&dcclbh=" + "gygl_gyjlglnew_gyjlxxsh.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		function cancelSh(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len == 1){	
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var temp = jQuery(array[i]).parent().parent().find("td");
					var shzt = temp.eq(8).text();
					if (shzt == 'δ���'){
						alertInfo("δ��˵ļ�¼���ܳ�����");
						return false;
					}else{
						var pkValue = temp.eq(0).find("input[type='checkbox']").val();
						str += pkValue+"!!@@";
					}
				}
				confirmInfo("��ȷ��Ҫ����������",function(tag){
					if(tag=="ok"){
						var parameter={}
						var url="gyjl_gyjlglnew.do?method=gyjlCancelSh";	
						parameter["str"]=str;							
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
				alertInfo("��ѡ��һ����Ҫ���������ݣ�",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/gyjl_gyjlglnew" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" id="xxdm" value="${xxdm}" />
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li><a href="#" onclick="gyjlSh();return false;" class="btn_sh"> ��� </a></li>
							<li><a href="javascript:void(0);" onclick="cancelSh();return false;" class="btn_qxsh">����</a></li>
							<%--��ý���Ի���ť  ֻ��zf01ӵ��ɾ��Ȩ��--%>
							<logic:equal name="xxdm" value="11647">
								<logic:equal name="userName" value="zf01">
									<li><a href="#" onclick="del();return false;" class="btn_sh"> ɾ�� </a></li>
								</logic:equal>
							</logic:equal>
						</logic:equal>
							<li><a href="#" onclick="gyjltjDc();return false;" class="btn_sh"> ���ɻ��ܵ��� </a></li>
							<li><a href="#" class="btn_dc" onclick="gyjlxxshExportConfig();return false;">����</a></li>
					</ul>
				</div>
				<!-- ��ť end-->
				
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
					<span> ��ѯ���&nbsp;&nbsp;<font color="blue">˫����¼�ɲ鿴��ϸ��Ϣ;</font></span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=gyjlxxglNewForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<div id="shDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>
										<logic:equal value="11799" name="xxdm">
											��˹�Ԣ������Ϣ
										</logic:equal>	
										<logic:notEqual value="11799" name="xxdm">						
											��˹�Ԣ������Ϣ
										</logic:notEqual>										
									</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									���״̬
								</th>
								<td width="70%">
									<html:select property="shzt" style="width:150px" styleId="shzt">
<%--										<html:option value="wsh">δ���</html:option>--%>
										<html:option value="tg">ͨ��</html:option>
										<html:option value="btg">��ͨ��</html:option>
										<html:option value="th">�˻�</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th width="30%">
									������<br/>(<font color="blue">��¼��500��</font>)
								</th>
								<td width="70%">
									<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=gyjlxx&id=shyj" />
									<textarea rows="4" id="shyj" name="shyj" style="word-break:break-all;width:95%;margin-top: 5px" onblur="chLeng(this,500);"></textarea>
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
										<button type="button" name="����" onclick="gyjlPlsh();return false;">
											����
										</button>
										<button type="button" name="ȡ��" onclick="Close();return false;">
											ȡ��
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