<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//��ѯ�����
		function searchRs(){
			var url = "zhcpSjdr.do?method=getZhcpInfoList";
			var czxm = $("czxm").value;
			var ie = "10.0";
			var lyb = $("lyb").value;
			
			var otherValue = [czxm,ie,lyb];

			showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}
		
		//������ط���
		function saveZhcpf(tag){

			if(tag=='ok'){
				var czxm = $("czxm").value;
				var xm_name = "czxm_"+czxm;
				var num =  document.getElementsByName(xm_name).length;
				
				var pk = new Array();
				var fs = new Array();
				var n=0;
				
				for(var i=0;i<num;i++){
					var obj = document.getElementsByName(xm_name)[i];
					fs[n] = obj.value;
					pk[n]=escape(obj.id.replace(xm_name+"_",""));
					n++;
				}
				
				var url="zhcpSjdr.do?method=saveZhcpf";
				
				//����
			 	var parameter = {
			 		"czxm":czxm,
			 		"pk":pk.join("!!@@!!"),
					"fs":fs.join("!!@@!!")
				};
	
				jQuery.post(url,parameter,function(result){alertInfo(result);$("had_edit").value="no"});
			}
		}
		
		//������Ŀ����
		function ctrlXmcz(lyb){
			$("lyb").value = lyb;
			if(lyb == ""){
				$("lyb").value = "noLyb";
			}
			if(lyb!=""){
				$("btn_ccg").style.display="none";
				$("btn_down").style.display="none";
				$("btn_dr").style.display="none";
				$("btn_sx").style.display="";			
			}else{
				$("btn_ccg").style.display="";
				$("btn_down").style.display="";
				$("btn_dr").style.display="";
				$("btn_sx").style.display="none";
			}
		}
		
		//ѯ����Ϣ�ص�����(ͬ��)
		function tbZhcpf(tag){

			if(tag=='ok'){
			
				var url="zhcpSjdr.do?method=tbZhcpf";
				var lyb=$("lyb").value;
				var czxm=$("czxm").value;
				
				//����
			 	var parameter = {
			 		"czxm":czxm,
			 		"lyb":lyb
				};
	
				jQuery.post(url,parameter,function(result){tbResult(result);});
			}
		}
		
		//ͬ�����
		function tbResult(result){
			if(result == "ִ�гɹ�"){
				alertInfo(result);
				searchRs();
			}else{
				alertError(result);
			}
		}
		
		//��ʾģ������ҳ��
		function showExpInfo(){
			var url = "/xgxt/zhcpSjdr.do?method=sjdrExp";
				url+= "&czxm="+$("czxm").value;
				url+= "&lyb="+$("lyb").value;
				
			showOpenWindow(url,700,550);
		}
		
		//����Ƿ��޸�
		function saveMethod(){
			confirmInfo('�����Ƿ񱣴������޸ĵ�����?',saveZhcpf);
		}
		
		function onShow(){
			var id = "left_a_0";
			if($(id)){
				$(id).click();
			}
		}
		</script>
	</head>
	<body onload="onShow()" >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
			<!-- ��ع��� -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;" 
					onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">��ع���</a>
			</p>
			<!-- ��ع��� end-->
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				¼�����(��<font color="blue">����</font>)��ɺ���ִ��<font color="blue">����</font>������
				����������ʹ��<font color="blue">ģ������</font>���ṩ��excelģ�崦�����ݡ�
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<!-- ��ݷ�ʽ -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_zhsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function19.png" />
							<p>������������</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('zhcp_zczf_zccx.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function30.png" />
							<p>�۲�ֲ�ѯ(����)</p>
						</a>   	
					</div>
					
				</div>
			</div>
		</div>
		<!-- ��ݷ�ʽ end-->
		
		<html:form action="/zhcpSjdr" enctype='multipart/form-data' method="post">
		
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="czxm" id="shxm" value="${czxm }"/>
			<input type="hidden" name="lyb" id="lyb" value="noLyb"/>
			<input type="hidden" id="had_edit" value="no"/>
				
			<button type="button" id="createTj" onclick="setSearchTj()" style="display:none">���ɹ�������</button>
			<!-- ������ end-->
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
			
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_ccg"
								onclick="confirmInfo('��ȷ���Ƿ�Ҫִ�б������?',saveZhcpf);return false;"
								class="btn_ccg">
								����
							</a>
						</li>
						<li>
							<a href="#" id="btn_down"
								onclick="showExpInfo();return false;"
								class="btn_down">
								ģ������
							</a>
						</li>
						<li>
							<a href="#" id="btn_dr"
								onclick="showTopWin('/xgxt/zhcpSjdr.do?method=sjdrImp',500,250);return false;"
								class="btn_dr">
								����
							</a>
						</li>
						<li>
							<a href="#" id="btn_sx" style="display:none"
								onclick="confirmInfo('����ȷ���Ƿ���Ҫִ��ͬ�����ݲ���?ע�����Ḳ���������ݣ������������',tbZhcpf);return false;"
								class="btn_sx">
								ͬ������
							</a>
						</li>
						
					</ul>
				</div>
				<!-- ��ť end-->
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
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
				<!-- From���� -->
				<table width="100%" align="left">
					<tr>
						<td width="20%" valign="top" style="overflow-x: auto;">
							<!-- ����� -->
							<div class="listbox">	
								<div class="titlelist" style="height: 352px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">
												<logic:equal name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child" style="background:#dce8f8">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="if(checkHadEdit()){setLiClick('${index}');ctrlXmcz('${xmnr.lyb}');searchRs()};return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:equal>
												<logic:notEqual name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="if(checkHadEdit()){setLiClick('${index}');ctrlXmcz('${xmnr.lyb}');searchRs()};return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:notEqual>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- ����� end-->
						</td>
						<td width="80%" valign="top" style="position: relative;">
							<div id="div_rs" style="width:650px;height:376px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				<!--��ҳ��ʾ-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zhcpSjdrForm"></jsp:include>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->	
			
			<!-- ģ�嵼��Div-->
			<div id="div_mbdc" style="display: none">
				<table class="formlist">
					<thead>
						<tr>
							<td colspan="2">
						
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								������ʽ
							</th>
							<td>
								<input type="radio" name="rad_dcxs" value="xy" onclick="$('dcxs').value=this.value"/><bean:message key="lable.xsgzyxpzxy" />
								<input type="radio" name="rad_dcxs" value="bj" checked="checked" onclick="$('dcxs').value=this.value"/>�༶
								<input type="hidden" name="dcxs" id="dcxs" value="bj"/>
							</td>
						</tr>
						<tr>
							<th>
								·��ѡ��
							</th>
							<td>
								<input type="text" name="filePath" id="filePath" readonly="readonly" style="width:400px"/>
								&nbsp;&nbsp;
								<button type="button" onclick="browseFolder(filePath)">ѡ ��</button>
							</td>
						</tr>
						<tr>
							<th>
								˵��
							</th>
							<td height="250px">
								����ǰҳ����ѡ��Ĺ����������ģ�壬���δѡ�������Ļ�������ȫУ���а༶����Ϣ��������Ӱ��
								����Ч�ʣ���������ѡ��һ��������<br/>
								�����ļ��԰༶λ��λ��Excel��ʽ������������ѡ��ı���λ�á�
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<!-- ȷ�� -->
									<button type="button" onclick="showDcInfo()">
										ȷ ��
									</button>
									
									<!-- �ر� -->
									<button type="button" onclick="hiddenMessage(true,true);return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- ģ�嵼��Div end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>