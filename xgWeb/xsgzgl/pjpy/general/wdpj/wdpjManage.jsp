<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//ҳ���ʼ��
		function onShow(){
			var userType = jQuery("#userType").val();
			if(userType == "stu"){
				$("span_stu").style.display="";
				$("span_tea").style.display="none";
			}else{
				$("span_stu").style.display="none";
				$("span_tea").style.display="";
			}
			
			searchRs();	
		}

		//��ѯ�����
		function searchRs(){

			jQuery.ajaxSetup({async:false});
			
			var yhlx = $("yhlx").value;
			var url = "general_pjpy_wdpj_ajax.do?method=searchPjpyWdpj";	
			var otherValue = [yhlx];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
			
			if($("operation")){
				if($("operation").value=="no"){
					if($("btn_lssb")){
						$("btn_lssb").disabled=true;
					}
					
					if($("btn_xssq")){
						$("btn_xssq").disabled=true;
					}
					
					jQuery(".a_lssh").each(function(){
						jQuery(this).attr("disabled",true);
					});
				}else{
					if($("btn_lssb")){
						$("btn_lssb").disabled=false;
						
					}
					
					if($("btn_xssq")){
						$("btn_xssq").disabled=false;
					}
					
					jQuery(".a_lssh").each(function(){
						jQuery(this).attr("disabled",false);
					});
				}
			}
		}

		//��ʾ��ʦ�ϱ�Div
		function showLssbDiv(){
			tipsWindown("ϵͳ��ʾ","id:div_lssb","400","300","true","","true","id");
		}
		
		//��ʾѧ������(�޸�)div
		function showWdpjView(xmdm){
		
			var ie = "ie";
			
			var url="general_pjpy_wdpj_ajax.do?method=showWdpjView";
			
			//��������
		 	var parameter = {
		 		"ie":ie,
		 		"stylePath":stylePath,
				"xmdm":xmdm
			};
			jQuery("#div_detail").load(url,parameter,function(){
			
				tipsWindown("ϵͳ��ʾ","id:div_detail","600","380","true","","true","id");
			});
		}
		
		//ǰ����Ŀ���
		function showSpgw(xmdm){
		
			var url="general_wdpj_xmsh_ajax.do?method=showShgwDiv";
			
			//��������
		 	var parameter = {
				"xmdm":xmdm
			};
		  	
			jQuery("#div_spgw").load(url,parameter,function(){
			
				var len=jQuery("[name=spgw]").length;
			
				if(len==1){
					var spgw=jQuery("[name=spgw]:checked").val();
					goWdpjXmsh(xmdm,spgw);
					
				}else{
					tipsWindown("ϵͳ��ʾ","id:div_spgw","300","170","true","","true","id");
				}
			});
		}
		
		function checkSpgw(){
		
			var xmdm=jQuery("#text_xmdm").val();
			var spgw=jQuery("[name=spgw]:checked").val();
			
			goWdpjXmsh(xmdm,spgw);
		}
		
		function checkLssb(){
		
			var bjdm=jQuery("#bj").val();
			var xmdm=jQuery("#xmdm").val();
			
			var flag=true;
			if(xmdm==""){
				alertInfo("�ϱ���Ŀ����Ϊ��!");
					
				flag= false;
				
			}
			if(bjdm=="" && flag){
				alertInfo("�༶����Ϊ��!");
				flag= false;
			}
			
			if(flag){
				goWdpjLssb();
			}
		}
		
		function checkItsDis(obj){
			
			if(obj.disabled){
				
				return false;
			}else{
				
				return true;
			}		
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
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
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					<span id="span_stu" style="display: none">
					1.��ҳ��չʾ��Ϊ<font color="blue">����������</font>����<font color="blue">����/���ϱ�</font>����Ŀ��չ�����<br/>
					2.���<font color="blue">�鿴��ϸ��Ϣ</font>���Բ鿴����Ŀ�ľ�����������<br/>
					3.������������µ�������Ŀ������<font color="blue">��Ŀ����</font>��<br/>
					4.�������鿴�Լ����������������<font color="blue">�������</font>��<br/>
					5.������������¼�ڴ˴���ѯ�����������Ǳ�������<font color="blue">�Ѿ�����</font>������<font color="blue">�������</font>���鿴���顣<br/>
					6.������޷�����<font color="blue">��Ŀ����</font>�������ǹ���Ա��<font color="blue">δ��������</font>��<br/>
					</span>
					
					<span id="span_tea" style="display: none">
					1.��ҳ��չʾ��Ϊ<font color="blue">����������</font>����<font color="blue">��Ҫ���</font>����Ŀ��<br/>
					2.���������������<font color="blue">��ʾ������</font>����־�����ڸ�λĿǰ�ж���������<font color="blue">��Ҫ�������</font>��<br/>
					3.���������������<font color="blue">��ʾ������</font>����־�����ڸ�λĿǰ�Ѿ�<font color="blue">��˹���</font>���������ˡ�<br/>
					4.�������ָ�����¼Ϊ<font color="blue">δ���</font>��<font color="blue">������</font>���������ָ�����¼Ϊ<font color="blue">ͨ��</font>��<font color="blue">��ͨ��</font>��<br />
					5.��������ĳ��Ŀ�ϱ�ѧ���Ļ�������<font color="blue">��ʦ�ϱ�</font>��<br />
					6.�������鿴�����������������������<font color="blue">�����������</font>��<br />
					7.�������鿴��ʷ�������������������<font color="blue">��ʷ�������</font>��<br />
					8.�������ִ����˲���������ÿ����Ŀ�����е�<font color="blue">���</font>��<br />
					9.���������ִ��<font color="blue">�ϱ�</font>��<font color="blue">���</font>�Ĺ��������������ڹ���Ա<font color="blue">�Ѿ���ֹ</font>�˸���������ϵ����Ա��<br />
					</span>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="operation" id="operation" value="${operation}"/>
<%--			<input type="hidden" name="xmdm" id="xmdm" value="" />--%>
			<button type="button"  id="forward" onclick="goWdpjLssb()" style="display: none">��ת</button>
			<!-- �๦�ܲ����� -->
			<div class="toolbox">				
				<div style="display:none">
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				</div>
			</div>
			
			<!-- ��ݷ�ʽ -->
			<div class="liucheng_xg_pj" style="">
			
				<!-- ѧ���汾 -->
				<logic:equal name="userType" value="stu">
					<div class="liucheng_bar">
						<h3>��<br/>��<br/>��<br/>��</h3>
						<div class="con">
						    <div class="liucheng_font floatleft">
						    	<a href="#" onclick="if(checkItsDis(this)){goWdpjXssq();};return false;" disabled="true" id="btn_xssq">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
									<p>��Ŀ����</p>
								</a>   	
							</div>
							<div class="liucheng_font floatleft">
								<a href="#" onclick="goWdpjXsjg('zcjg');return false;">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function61.png" />
									<p>�۲���</p>
								</a>
							</div>
							<div class="liucheng_font floatleft">
								<a href="#" onclick="goWdpjXsjg('pjjg');return false;">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
									<p>�������</p>
								</a>
							</div>
						</div>
					</div>
				</logic:equal>
				<!-- ѧ���汾  end-->
				
				<!-- ��ѧ���汾 -->
				<logic:notEqual name="userType" value="stu">
					<div class="liucheng_bar">
						<h3>��<br/>��<br/>��<br/>��</h3>
						<div class="con">
						   
							<div class="liucheng_font floatleft">
								<a href="#" onclick="if(checkItsDis(this)){showLssbDiv();};return false;" disabled="true" id="btn_lssb">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function46.png" />
									<p>��ʦ�ϱ�</p>
								</a>
							</div>
							
							<div class="liucheng_font floatleft">
								<a href="#" onclick="goWdpjBcpj();return false;">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
									<p>�����������</p>
								</a>
							</div>
							
							<div class="liucheng_font floatleft">
								<a href="#" onclick="goWdpjLspj();return false;">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function15.png" />
									<p>��ʷ�������</p>
								</a>
							</div>
							
						</div>
					</div>
				</logic:notEqual>
				<!-- ��ѧ���汾end -->
				
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
					<table align="center" width="100%" id="wdpjTable">
						<tr style="">
							<td width="100%" valign="top" style="position: relative;">
								<div id="div_rs">
								
								</div>
							</td>
						</tr>
					</table>
					<!--��ҳ��ʾ-->
					<script type="text/javascript" defer="defer">
						setTimeout("$('choose').className='hide'",100);
					</script>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</div>
				<!-- ������ʾ����ʼ end-->
			</div>	
			
			<div id="div_detail" style="display:none">
				
			</div>
			<!-- ��ʦ�ϱ������� begin-->
			<div id="div_lssb" style="display:none">
				<div class="open_win01" style="">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ϱ����ѡ��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									<font color="red">*</font>�ϱ���Ŀ
								</th>
								<td>
									<html:select property="xmdm" styleId="xmdm" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="xmList" property="xmdm"
											labelProperty="xmmc" />
									</html:select>
									<font color="blue">(��ѡ)</font>
								</td>
							</tr>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:200px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									Ժϵ
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:select property="xydm" styleId="xy" disabled="true"
											value="${userDep }" style="width:200px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy" style="width:200px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" styleId="zy" style="width:200px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									<font color="blue">(��ѡ)</font>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button"  onclick="checkLssb()">ȷ ��</button>
										<button type="button"  onclick="closeWindown();return false;">�� ��</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ��ʦ�ϱ������� end-->
				
			<!-- ��λ��Ϣ begin -->
			<div id="div_gwxx" style="display:none">
				<div class="open_win01">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��˸�λѡ��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									��λѡ��
								</th>
								<td>
									<input type="radio" name="shgw" checked="checked"/>Ժϵ<br />
									<input type="radio" name="shgw"/>ѧУ
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button"  onclick="closeWindown();return false;">ȷ��</button>
										<button type="button"  onclick="closeWindown();return false;">�ر�</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ��λ��Ϣ end-->
			
			<!-- ��λ��Ϣ end-->
			
			<div id="div_spgw" style="display:none">

			</div>
				
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>	
		</html:form>
	</body>
</html>