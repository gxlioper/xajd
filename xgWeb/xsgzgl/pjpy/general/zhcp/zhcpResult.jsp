<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
			<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/xgservice.js"></script>
		<script type='text/javascript' src="js/moveDiv.js"></script>
		<script type="text/javascript" src="js/comm/ymPrompt.js"></script>
		<link rel="stylesheet" type="text/css" href="comm/skin/zfstyle/ymPrompt.css" media="all"/> 
		<script type='text/javascript' src="js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>	
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script language="javascript">
		//��ʼ��
		function onShow(){
			//
			searchRs();
		}
		
		//��ѯ�����
		function searchRs(){
			//��ť����

			//controlBtn();
			var url = "general_zhcp_ajax.do?method=searchZhcpResult";
		
			var ie = "ie";

			var otherValue = [ie];
			
			var zczq_num =  jQuery("a[name=a_name_zczq]").length;
			
			if(zczq_num==1 && jQuery("#checks").val()=="no" || jQuery("#checks").val()=="yes"){
				searchRsByAjax(url,otherValue);
				jQuery("#checks").val("no");
			}else{
				alertInfo("������ֻ��ѡ��һ��ѧ�꣡");
				return false;
			}
			
			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","2000");
			
			if($("operation")){
				if($("operation").value=="no"){
					$("btn_xg").disabled=true;
					$("a_btn_dr").disabled=true;
				}else{
					$("btn_xg").disabled=false;
					$("a_btn_dr").disabled=false;
				}
			}
			
		}	
		
		
		function c(){
			
			jQuery("[name=tj_zczq]").each(function(){
			
				jQuery(this).click( function (){
				
					var len=jQuery(".selectedValue[name=tj_zczq]").length;
					
					if(len!=1){
						disabledBtn();
					}else{
					
						var value=jQuery(".selectedValue[name=tj_zczq]").val();
						
						var array=new Array();
						
						array=(jQuery(".selectedValue[name=tj_zczq]").attr("id")).split("tj_zczq_");
						
						value=array[1];
						if($("bcpjzq").value!=value){
							disabledBtn();
						}else{
							showBtn();
						}
					}
				}); 
			})
			
			jQuery("[name=a_name_zczq]").each(function(){
			
				jQuery(this).click( function (){
					
					var zczq_num=jQuery("a[name=a_name_zczq]").length;
					if(zczq_num==0){
						disabledBtn();
					}
				}); 
			})
		}
		
		function disabledBtn(){
			
			$("a_btn_dc").disabled=true;
			$("a_btn_dcsz").disabled=true;
			$("a_btn_lx").disabled=true;
			$("a_btn_dr").disabled=true;
			
			if($("operation")){
				if($("operation").value=="no"){
					$("btn_xg").disabled=true;
					$("a_btn_dr").disabled=true;
				}else{
					$("btn_xg").disabled=false;
					$("a_btn_dr").disabled=false;
				}
			}
			
			
		}
		
		function showBtn(){
			
			$("a_btn_dc").disabled=false;
			$("a_btn_dcsz").disabled=false;
			$("a_btn_lx").disabled=false;
			$("a_btn_dr").disabled=false;
			
			if($("operation")){
				
				if($("operation").value=="no"){
					$("btn_xg").disabled=true;
					$("a_btn_dr").disabled=true;
				}
			}
			
		}
		
		setTimeout("c()","3000");

		function lxgn(){
			showTopWin("general_pjpy.do?method=kindChoose",800,500);
		}
		
		
		//��ʾ����ҳ��
		function showLoadPage(){
			//�๦�ܲ���
			$("dgncz").style.display="none";
			//��ѯ���
			$("cxjg").style.display="none";
			//��ʾ
			$("page_loading").style.display="";
			
		}
		
		function showDiv(){
			viewTempDiv("���㷽ʽѡ��","zcfjsDiv",350,200);
		}
		
		function showPm(){
			//ѡ������
			if($("zcpm").value!="1" && $("zcpm").value!="2" && $("zcpm").value!="3"){
			if($("jslx_pm").checked){
				$("pmjs").style.display="";
			}else{
				$("pmjs").style.display="none";
			}
			}
		}
		
		function showZypm(){
			//ѡ������
			if($("zypm").value!="1" && $("zypm").value!="2" && $("zypm").value!="3"){
				if($("jslx_zypm").checked){
					$("zypmjs").style.display="";
				}else{
					$("zypmjs").style.display="none";
				}
			}
		}
		
		jQuery(function(){
		
			if($("zd30")){
				var zd30=$("zd30").name;
			
				jQuery("."+zd30).each(function(){
					var text=jQuery(this).text();
						
					var title=jQuery(this).attr("title");
					
					var title=text;
					if(text.length>10){
						title=text;
						text=text.substr(0,10)+"...";
					}
					jQuery(this).attr("title",title);
					jQuery(this).text(text)
				});
			}
			

			onShow();

		});
		
		function expZccj(){
			jQuery('#select_xy').attr('id','xy');
			jQuery('#select_zy').attr('id','zy');
			jQuery('#select_bj').attr('id','bj');
			viewTempDiv("ѡ���ӡ�༶","expZccj",350,200);
		}
		
		function showBm(){
			tipsWindown("ϵͳ��ʾ","id:div_bm","350","300","true","","true","id");
		}
		
		function save(){
			
			var xy=jQuery("#xy option:selected").text();
			var zy=jQuery("#zy option:selected").text();
			var bj=jQuery("#bj option:selected").text();
			var nj=jQuery("#nj option:selected").text();
			var message="";
			if(jQuery("#nj").val()!=""){
				message+=nj+"��";
			}
			
			if(jQuery("#xy").val()!=""){
				message+=xy+jQuery("#xbmc").val();
			}
			
			if(jQuery("#zy").val()!=""){
				message+=zy+"רҵ";
			}
			
			if(jQuery("#bj").val()!=""){
				message+=bj+"��";
			}
			
			confirmInfo(" ����Ҫ����"+message+"����ѧ�����ֺܷ������Ƿ������",function(ok){
				
				if(ok=="ok"){
				
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					closeWindown()
				
					setTimeout("zhcpAccount()","1000");
					
				}
			});
		}
		
		function zhcpAccount(){
		
			jQuery.ajaxSetup({async:false});
		
			//����һ��json����
			var parameter={};
			
			var array=new Array();
			
			//ָ����ȡ�Ŀؼ����ͣ�����ѭ��
			jQuery("[name=xszdArr]:checked").each(function(i){
				if(jQuery(this).val()!="xh" &&jQuery(this).val()!="xm" ){
					//��ȡ���ؼ�name
					array[i]=jQuery(this).val();
				}
			});
			
			//����json����
			parameter["str_xydm"]=escape(jQuery("#xy").val());
			
			parameter["str_zydm"]=escape(jQuery("#zy").val());
			
			parameter["str_bjdm"]=escape(jQuery("#bj").val());
			
			parameter["str_nj"]=escape(jQuery("#nj").val());
			
			//����URL
			var url = "general_zhcp_ajax.do?method=zhcpAccount";
			
			
			//------------AJAX���� begin -------------
			
			jQuery.post(url,
				parameter,
				function(result){
					
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
				
					alertInfo(result,function(tag){
				
						if(tag=="ok"){
					
							
							searchRs();
							
						}
					});
				}
			);
			
			jQuery.ajaxSetup({async:true});
			//------------AJAX���� end -------------
			
		}
		
		function checkItsDis(obj){
			
			if(obj.disabled){
				
				return false;
			}else{
				
				return true;
			}		
		}
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

		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					1.������Ĭ��չʾ����<font color="blue">����������</font>����Ϣ�� <br/>
					2.<font color="blue">���㹦��</font>���ṩ<font color="blue">ѧУ(����Ա)����</font>�û����в����� <br/>
					3.���㹦�ܽ��ܼ���<font color="blue">����������</font>���۲�ּ����������<br/>
					4.��������ѯ����ʾ���в�����������Ҫ������<font color="blue">��ѡ</font>��<br/>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden"  name="zypm" id="zypm" value="${zypm }" />
			<input type="hidden" name="zcpm" id="zcpm" value="${zcpm }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="checks" name="checks" value="yes" />
			<input type="hidden" id="bcpjzq" name="bcpjzq" value="${zczq }" />
			<input type="hidden" name="operation" id="operation" value="${operation}"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ҳ����Դ -->
						<logic:equal name="forward" value="jbsz">
							<li>
								<a href="#" onclick="goPjpyJbsz();return false;" class="btn_fh">
									��������
								</a>
							</li>
						</logic:equal>
						<!-- ҳ����Դend -->
						
						<logic:equal name="writeAble" value="yes">
							<!-- ѧУ���� -->
							<logic:equal name="userType" value="xx">
								<li>
									<a href="#" onclick="if(checkItsDis(this)){showBm();};return false;" class="btn_xg" id="btn_xg">�ּܷ���</a>
								</li>
							</logic:equal>
							<!-- ����Ա���� -->
							<logic:equal name="userType" value="admin">
								<li>
									<a href="#" onclick="if(checkItsDis(this)){showBm();};return false;" class="btn_xg" id="btn_xg">�ּܷ���</a>
								</li>
							</logic:equal>
							<!-- ѧԺ����-->
							<logic:equal name="userType" value="xy">
								<li>
									<a href="#" onclick="if(checkItsDis(this)){showBm();};return false;" class="btn_xg" id="btn_xg">�ּܷ���</a>
								</li>
							</logic:equal>
						</logic:equal>
						<li>
							<a href="#" id="a_btn_lx" onclick="if(!$('a_btn_lx').disabled && checkItsDis(this)){lxgn();}return false;" class="btn_zt">��ѡ
							</a>
						</li>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" id="a_btn_dr" onclick="if(!$('a_btn_dr').disabled  && checkItsDis(this)){impAndChkData();}return false;"
								class="btn_dr">��������</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" class="btn_qx" id="a_btn_dcsz" onclick="if(!$('a_btn_dcsz').disabled){choiceFields();}return false;">��������</a>
						</li>
						<li>
							<a href="#" class="btn_dc" id="a_btn_dc"
								onclick="if(!$('a_btn_dc').disabled){configureExportData();}return false;">��������</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �๦�ܲ����� end-->

			<!-- ������ʾ����ʼ -->
			<div class="main_box">
<!--				<div class="mid_box">-->
<!--					<div class="title">-->
<!--						<p>-->
<!--							 ��ѯ�õ�����������ʾ���� -->
<!--						</p>-->
<!--					</div>-->
<!--				</div>-->
<!--				<h3 class="datetitle_01">-->
<!--					<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rsList">-->
<!--							<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ</font>-->
<!--						</logic:notEmpty> </span>-->
<!--				</h3>-->
				<div id="div_rs"
					style="width:100%;height:400px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->


			<!-- �����ּܷ���ѡ��DIV -->
			<div id="div_bm" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ּܷ���</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:150px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xb" />
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:select property="xydm" styleId="xy" disabled="true"
											value="${userDep }" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy" style="width:150px"
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
									<html:select property="zydm" styleId="zy" style="width:150px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button"  name="�� ��" onclick="save();return false;">
											ȷ ��
										</button>
										<button type="button"  name="ȡ ��" onclick="closeWindown();return false;">
											ȡ ��
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
	<%@ include file="/comm/loading.jsp"%>
	<%@ include file="/comm/other/tsxx.jsp"%>
</html>
