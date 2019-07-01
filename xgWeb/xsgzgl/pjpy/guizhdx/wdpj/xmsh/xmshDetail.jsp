<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
		
		//�鿴����ʱ����������ı�����Ϊֻ��
		jQuery(function(){
			if(jQuery("#doType") && jQuery("#doType").val()=="view"){
				jQuery("input,textArea").attr("readonly",true);
			}
			
			if(jQuery("#userType") && jQuery("#userType").val()=="xy"){
				jQuery("#xxshyj").attr("readonly",true);
				
				jQuery("#sqly").attr("readonly",true);
			}else{
				jQuery("#sqly").attr("readonly",true);
				
				jQuery("#xyshyj").attr("readonly",true);
			}
		});
		
		//����˳����Ŀ��Ϣ
		function saveSyxm(){
			url = "/xgxt/pjpyXmsh.do?method=xmshDetail&doType=xmsy";
			saveUpdate(url,'');
		}
		
		//�������״̬
		function saveShzt(shzt){
			
			
			var xh=jQuery("#xh").val();
			
			var xmdm=jQuery("#xmdm").val();
			
			var message="";
			
			if(shzt=="ͨ��"){
				var xhArr=new Array();
				xhArr[0]=xh;
				message=checkShxz(xhArr,xmdm);
			}
			
			if(message!="" && message!="null"){
				
				flag=false;
				
			}else{
				flag=true;
			}
			
			if(flag){
			
				var parameter={}
				var shyj="";
				var userType=jQuery("#userType").val();
				
				if(userType=="xy"){
					shyj=jQuery("#xyshyj").val();
				}else{
					shyj=jQuery("#xxshyj").val();
				}
				parameter["xh"]=xh;
				parameter["xmdm"]=jQuery("#xmdm").val();
				parameter["shzt"]=escape(shzt);
				parameter["shyj"]=escape(shyj);
				var url = "general_wdpj_xmsh_ajax.do?method=savePlShzt";
				
				jQuery.post(url,
					parameter,
					function(result){
						
						alertInfo(result);
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
			
			}else{
			
				confirmInfo(message+"�Ƿ�Ҫ����˳�Ӳ�����",function(tag){
					if(tag=="ok"){
						showXmsyDiv();
					}
				
				});
				
			}
		}
		
		//�ж����������Ϣ����������ã�
		function checkShxz(xhArr,xmdm){
			
			var parameter={}
			
			parameter["array_xh"]=xhArr.join(" !!@@!! ");;
			
			parameter["str_xmdm"]=xmdm;
			
			//����URL
			jQuery.ajaxSetup({async:false});
			
			var url = "general_pjpy_xmsz_ajax.do?method=checkShxz";
			
			var message="";
			
			//------------�����ж� begin -------------
			jQuery.ajaxSetup({async:false});
				jQuery.post(url,
				parameter,
				function(result){
					
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					message=result;
					
				}
			);
			jQuery.ajaxSetup({async:true});
			
			return  message;
		}
		
		//��ʾ��Ŀ˳��DIV
		function showXmsyDiv(){
			
			var url="general_pjpy_xmsz_ajax.do?method=showXmsyDiv";
			
			var xmdm=jQuery("#xmdm").val();
			
			var xh=jQuery("#xh").val();
			//��������
		 	var parameter = {
				"str_xmdm":xmdm,
				"array_xh":xh
			};
		  	
		  	
		  	jQuery.ajaxSetup({async:false});
		  	
			jQuery.post(url,
				parameter,
				function(result){
					
					if(result!="" && result!=null){
						
						jQuery("#div_xmsy").html(result);
						tipsWindown("ϵͳ��ʾ","id:div_xmsy","350","250","true","","true","id");
					}
					
				}
			);
			
			jQuery.ajaxSetup({async:true});
			
		}
		
		function saveXmsy(xh){
			
			var url="general_pjpy_xmsz_ajax.do?method=saveXmsy";
			var xmdm=jQuery("input[name=xmsyArr]:checked").val();
			//��������
		 	var parameter={}
			
			parameter["array_xh"]=xh;
			
			parameter["str_xmdm"]=xmdm;
			
			jQuery.ajaxSetup({async:false});
			jQuery.post(url,
				parameter,
				function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					alertInfo(result);
					closeWindown();	
				}
			);
			jQuery.ajaxSetup({async:true});
		}
		
		</script>
	</head>
	<body style="overflow-x:hidden">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �ҵ����� - ѧ��������Ϣ</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/pjpyXmsh">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- �����Ŀ -->
			<input type="hidden" name="xmdm" id="xmdm"
				value="${wdpjXssqInfo.xmInfo.xmdm }" />
			<!-- ��˼��� -->
			<input type="hidden" name="shjb" id="shjb" value="${shjb }" />
			<!-- ���״̬ -->
			<input type="hidden" name="shzt" id="shzt" value="" />
			<!-- ���״̬ -->
			<input type="hidden" name="xh" id="xh"
				value="${wdpjXssqInfo.stuInfo.xh }" />
			<!-- ��ϸ��Ϣ -->
			<div class="tab" style="width:100%;height:450px;overflow-x:hidden;overflow-y:auto;">

				<table class="formlist" width="">
					<%@include file="../xssq/xmxx.jsp"%>
					<%@include file="../xssq/xsxx.jsp"%>
					<%@include file="../xssq/zcxx.jsp"%>
					<%@include file="../xssq/cjxx.jsp"%>
					<%@include file="../xssq/sqxx.jsp"%>
					<%@include file="../xssq/qtxx.jsp"%>
					<thead onclick="hiddenMk('tb_shinfo')">
						<tr style="cursor:hand">
							<th colspan="4">
								<span> ��Ŀ��� </span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_shinfo">
						<tr>
							<th widht="30%">
								<bean:message key="lable.xb" />���
							</th>
							<td colspan="3">
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xysh"
									value="δ���">
									<p>
										<img src="<%=stylePath%>images/ico_dsh.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xysh"
									value="ͨ��">
									<p>
										<img src="<%=stylePath%>images/ico_shtg.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xysh"
									value="��ͨ��">
									<p>
										<img src="<%=stylePath%>images/ico_shbtg.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xysh"
									value="�˻�">
									<p>
										<img src="<%=stylePath%>images/ico_shth.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xysh"
									value="������">
									<p>
										<img src="<%=stylePath%>images/ico_shxcs.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xb" />������
							</th>
							<td colspan="3">
								<textarea name='xyshyj' id="xyshyj"
									style="word-break:break-all;width:85%"
									onblur="chLeng(this,500);" rows='4' type="_moz">${wdpjXssqInfo.xmshInfo.xyshyj }</textarea>
							</td>
						</tr>

						<tr>
							<th>
								ѧУ���
							</th>
							<td colspan="3">
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xxsh"
									value="δ���">
									<p>
										<img src="<%=stylePath%>images/ico_dsh.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xxsh"
									value="ͨ��">
									<p>
										<img src="<%=stylePath%>images/ico_shtg.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xxsh"
									value="��ͨ��">
									<p>
										<img src="<%=stylePath%>images/ico_shbtg.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xxsh"
									value="�˻�">
									<p>
										<img src="<%=stylePath%>images/ico_shth.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xxsh"
									value="������">
									<p>
										<img src="<%=stylePath%>images/ico_shxcs.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								ѧУ���
							</th>
							<td colspan="3">
								<textarea name='xxshyj' id="xxshyj"
									style="word-break:break-all;width:85%"
									onblur="chLeng(this,500);" rows='4' type="_moz">${wdpjXssqInfo.xmshInfo.xxshyj }</textarea>
							</td>
						</tr>
					</tbody>
					<!-- �����Ϣ end-->
				</table>
				<!-- ���� end-->
			</div>
			<div>
				<table class="formlist" width="">	
				<!-- ���� -->
					<tfoot>
						<tr>
							<td colspan='4'>
								<div class="bz">

								</div>
								<div class="btn">
									<!-- �ǲ鿴 -->
									<logic:notEqual name="doType" value="view">
										<button type="button" onclick="saveShzt('ͨ��')">
											ͨ ��
										</button>
										<button type="button" onclick="saveShzt('��ͨ��')">
											��ͨ��
										</button>
										<!-- ��һ�������˻� -->
										<logic:notEqual name="userType" value="xy">
											<button type="button" onclick="saveShzt('�˻�')">
												�� ��
											</button>
										</logic:notEqual>
									</logic:notEqual>
									<button type="button" onclick="Close();return false;" id="btn_gb">
										<bean:message key="lable.btn_gb_space" />
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- ��ϸ��Ϣ end-->

			
			<logic:empty name="xmsy">
				<!-- ��ʾ��Ϣ -->
				<%@ include file="/comm/other/tsxx.jsp"%>
			</logic:empty>
			
			<div id="div_xmsy">
			</div>
		</html:form>
	</body>
</html>
