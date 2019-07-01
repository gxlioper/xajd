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
			//searchRs();
			var num = jQuery("input[name=radio_gwid]").length;
			
			if(num == 1){
				jQuery("#btn_qh").attr("style","display:none");
				searchRs();
			} else if (jQuery("#spgw").val() != ""){
				jQuery("#hidden_gwid").val(jQuery("#spgw").val());
				searchRs();
			} else {
				showSpgwDiv();
			}
		}
		
		//��ʾѡ��������λDiv
		function showSpgwDiv(){
			var gwid = jQuery("#hidden_gwid").val();
			jQuery("input[name=radio_gwid]").each(function(){
				if(jQuery(this).val() == gwid){
					jQuery(this).attr("checked",true);
				}
			});
			
			tipsWindown(" ","id:div_spgw","300","170","true","","true","id");
		}
		
		//�ύ������λ
		function submitSpgw(){
		
			var spgw = jQuery("input[name=radio_gwid]:checked").eq(0).val();
			
			jQuery("#hidden_gwid").val(spgw);
			
			searchRs();
			
			closeWindown();
		}
		
		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "xsxx_xxxg_xgsh.do?method=searchXgsh";
			var ie = "ie";
			var splc = jQuery("#hidden_splc").val();
			var gwid = jQuery("#hidden_gwid").val();
			
			var otherValue = [ie,stylePath,splc,gwid];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//չʾ�����ϸҳ��
		function showXgshDetail(){
			var num = jQuery("input[name=primarykey_checkVal]:checked").length;
			
			if(num == 0){
				alertError("�빴ѡ��ϣ����˵ļ�¼");
				return false;
			}else if(num == 1){
			
				var sqid = jQuery("input[name=primarykey_checkVal]:checked")[0].value;
				var gwid = jQuery("#hidden_gwid").val();
				
				var url = "general_xsxx.do?method=xgshDetail";
					url+= "&sqid="+sqid;
					url+= "&gwid="+gwid;

				showTopWin(url,'800','620');
			}else{
				tipsWindown(" ","id:div_plsh","400","250","true","","true","id");
			}
		}
		
		//���挏�ˠ�B
		function saveShzt(shzt){
		
			var shyj = jQuery("#textarea_shyj").val();
			var gwid = jQuery("#hidden_gwid").val();
			
			if(shyj != ""){
				if(shyj.length > 500){
					alertError("���������ܳ���500�֣���ȷ��");
					return false;
				}
			}
			
			var msg = "����ȷ���Ƿ�";
			if(shzt == "tg"){
				msg += "<font color='blue'>ͨ��</font>";
			}else if(shzt == "th"){
				msg += "<font color='blue'>�˻�</font>";
			}
			msg += "����ѡѧ������Ϣ�޸�����";
				
			confirmInfo(msg,function(tag){
				if(tag=="ok"){
					var url = "xsxx_xxxg_xgsh.do?method=saveShzt";

					var gwid = jQuery("#hidden_gwid").val();
					var pkValue=new Array();	
					var i=0;
					
					jQuery("input[name=primarykey_checkVal]:checked").each(function(){
						pkValue[i]=jQuery(this).val();
						i++;
					});
			
					var parameter={
						"array_pkValue":pkValue.join("!!array!!"),
	 					"str_gwid":gwid,
	 					"str_shyj":escape(shyj),
	 					"str_shzt":shzt
					};
			
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.ajaxSetup({async:false});
					
					jQuery.post(url,
						parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							searchRs();
							alertInfo(result);
							closeWindown();
						}
					);
			
					jQuery.ajaxSetup({async:true});
				}
			});
			
			
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
		</div>
		<!-- ���� end-->
		
		<html:form action="/general_xsxx" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="splc" id="hidden_splc" value="${splc }"/>
			<input type="hidden" name="gwid" id="hidden_gwid" value="${gwid }"/>
			<input type="hidden" id="spgw" value="${xsxxGeneralForm.spgw }"/>
					
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��дȨ begin-->
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" class="btn_shtg" onclick="showXgshDetail();return false;">
									���
								</a>
							</li>
							<li>
								<a href="#" class="btn_sx" id="btn_qh" onclick="showSpgwDiv();return false;">
									�л���˸�λ
								</a>
							</li>
						</logic:equal>
						<!-- ��дȨ end-->
					</ul>
				</div>
				<!-- ��ť end-->
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
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
				<div id="div_rs" style="" class="con_overlfow">
				</div>
				
				<!--��ҳ��ʾ-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxGeneralForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			
			<!-- ���ˍ�λ�x��Div begin-->
			<div id="div_spgw" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th>
									<span>��˸�λѡ��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="spgwList" id="spgw">
								<tr>
									<td>
										<input type="radio" name="radio_gwid" value="${spgw.spgw }"/>${spgw.gwmc }
									</td>
								</tr>
							</logic:iterate>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<div class="btn">
										<button type="button" onclick="submitSpgw();">ȷ ��</button>
<!--										<button type="button" onclick="closeWindown();">�� ��</button>-->
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ���ˍ�λ�x��Div end-->
			
			<!-- ��������Div begin-->
			<div id="div_plsh" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist" width="99%">
						<thead>
							<tr>
								<th colspan="2">
									<span>ѧ����Ϣ�޸��������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									������
									<br/>
									<font color="blue">(��500��)</font>
								</th>
								<td>
									<textarea rows="5" id="textarea_shyj" style="word-break:break-all;width:90%"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="saveShzt('tg');">ͨ ��</button>
										<button type="button" onclick="saveShzt('th');">�� ��</button>
										<button type="button" onclick="closeWindown();">�� ��</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ��������Div end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>