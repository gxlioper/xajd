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
		<script language="javascript" src="js/comm/searchTj.js"></script>
		<script language="javascript" defer="defer">		
		//��ʼ���б�
		function onShow(){
			//��ʼ������
			defaultBmOption();
			//ִ�в�ѯ����
			setTimeout("searchRs()",1000);
		}
		
		//��ѯ�����
		function searchRs(){
	
			var url = "jxglJxbz.do?method=getBjfpList";	
			var xn = $("xn").value;
			var sjdm = $("sjdm").value;
			var nj = " ";
			if(jQuery('#nj').combobox('getValue')!=""){
				nj = jQuery('#nj').combobox('getValue');
			}
			var xydm = " ";
			if(jQuery('#xydm').combobox('getValue')!=""){
				xydm = jQuery('#xydm').combobox('getValue');
			}
			var zydm = " "; 
			if(jQuery('#zydm').combobox('getValue')!=""){
				zydm = jQuery('#zydm').combobox('getValue');
			}
			var bjdm = " ";
			if(jQuery('#bjdm').combobox('getValue')!=""){
				bjdm = jQuery('#bjdm').combobox('getValue');
			}
	
			var otherValue = [xn,sjdm,nj,xydm,zydm,bjdm];

			showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}	
		
		//�����ѵ����
		function saveBjfp(tag){
			if(tag == "ok"){
				var pk = new Array();
				var num =  document.getElementsByName("primarykey_checkVal").length;
				var n=0;
				
				var flag = false;
				
				for(var i=0;i<num;i++){
					var obj = document.getElementsByName("primarykey_checkVal")[i];
					if(obj.checked){
						flag = true;
						pk[n]=obj.value;
						n++;
					}
				}	
				
				if(flag){
					var url = "/xgxt/jxglJxbz.do?method=saveBjfp";
					var sjdm = $("sjdm").value;
					//����
				 	var parameter = {
				 		"sjdm":sjdm,
				 		"pk":pk.join("!!@@!!")
					};

				 	jQuery.ajaxSetup({async:false});
				 	
				 	$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					jQuery.post(url,parameter,function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						doSuccess(result);
						searchRs();
					});

					jQuery.ajaxSetup({async:true});
				}else{
					alertError("�������ٹ�ѡһ����Ҫ����İ༶");
				}
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
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				1.���г�<font color="blue">δ������</font>�İ༶��</br>
				<span id="div_help" style="display: none">
				2.��ѡ��ϣ������İ༶������<font color="blue">ȷ������</font>����ɲ�����
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/jxglJxbz">
		
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="czlx" id="czlx" value="${czlx }"/>
			<input type="hidden" id="xn" value="${rs.xn }" />
			<input type="hidden" id="sjdm" value="${rs.sjdm }" />
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
			
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_ccg"
								onclick="confirmInfo('��Ҫ����������ѡ�İ༶����ȷ��',saveBjfp);return false;"
								class="btn_ccg">
								ȷ������
							</a>
						</li>				
					</ul>
				</div>
				<!-- ��ť end-->
				
				<div class="searchtab">
					<table>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xn" style="width: 150px" disabled="true">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th>
									�ϼ�����
								</th>
								<td>
									<html:select name="rs" property="sjdm" disabled="true" style="width:150px">
										<html:option value="">���ϼ�����</html:option>
										<html:options collection="jxbzList" property="bzdm" labelProperty="bzmc" />
									</html:select>
								</td>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width: 150px" title="��¼��">
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" styleId="xydm" style="width: 150px" title="��¼��">
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" styleId="zydm" style="width: 150px" title="��¼��">
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bjdm" style="width: 150px" title="��¼��">
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
			        		<tr>
			          			<td colspan="6">
			            		<div class="btn">
				              		<input type="hidden" name="go" value="a" />
				              		<button type="button" class="btn_cx" id="search_go" 
				              		onclick="searchRs();return false;">
				              			�� ѯ
				              		</button>
				              		
				             		<button type="button" class="btn_cz" id="btn_cz" onclick="czSearchCond('nj-xydm-zydm-bjdm');return false;">
				              			�� ��
				             		</button>
			            		</div>
			            		<!-- �������� -->
								<div style="display:none">
									<%@ include file="/comm/search/superSearchArea.jsp"%>
								</div>
								<!-- �������� end-->
			          		</td>
			       			</tr>
			     		</tfoot>
					</table>
				</div>	
			</div>

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
				<table align="center" width="100%">
					<tr style="">
						<td width="100%" valign="top" style="position: relative;">
							<div id="div_rs">

							</div>
						</td>
					</tr>
				</table>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jxglJxbzForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->

		</html:form>
	</body>
</html>
			