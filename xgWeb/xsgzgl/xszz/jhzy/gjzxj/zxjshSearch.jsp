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
			
			var url = "jhzy_gjzxj.do?method=searchZxjsh";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//��⹴ѡ��
		function checkLineNum(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len==0){	
				alertError("��<font color='blue'>��ѡ</font>��ϣ����˵ļ�¼��");	
				return false;
			}else if(len==1){	
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="jhzy_gjzxj.do?method=zxjshUpdate";
					url+="&pkValue="+pkValue;
					showTopWin(url,800,600);
			}else{	
				tipsWindown("ϵͳ��ʾ","id:div_plsh","500","300","true","","true","id");
			}
		}
		
		//���������ѧ�����
		function saveGjzxjSh(shzt){
			var message = "";
			if(shzt == "tg"){
				message = "��ȷ�����<font color='blue'>ͨ��</font>����ѡѧ���Ĺ�����ѧ��������";
			}else if(shzt == "btg"){
				message = "��ȷ�����<font color='blue'>��ͨ��</font>����ѡѧ���Ĺ�����ѧ��������";
			}
			
			confirmInfo(message,function(tag){
				if(tag=="ok"){
					
					//·��
					var url = "jhzy_gjzxj.do?method=saveGjzxjSh";
					
					var tjdc = jQuery("[name=tjdc]:checked").eq(0).val();
			
					var pkValue=new Array();
					var i=0;
					
					jQuery("input[name=primarykey_checkVal]:checked").each(function(){
						pkValue[i]=escape(jQuery(this).val());
						i++;
					});
					
					//����
				 	var parameter = {
						"array_pkValue":pkValue.join("!!array!!"),
						"str_shzt":escape(shzt),
						"str_shyj":escape(jQuery("#shyj").val()),
						"str_tjdc":escape(tjdc)
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
	<body  >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" 
					onmouseover ="showHelpDiv()"
					onmouseout="showHelpDiv()"
				>
				ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="checkLineNum();return false;" id="btn_shtg" class="btn_shtg">
									���
								</a>
							</li>
						</logic:equal>
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
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:auto;">
				</div>
				
				<!--��ҳ��ʾ-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jhzyXszzGjzxjForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			
			<!-- ������˵����� -->
			<div id="div_plsh" style="display:none">
				<div class="open_win01">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									<font color="red">*</font>�Ƽ�����
								</th>
								<td colspan="3">
									<input type="radio" name="tjdc" value="һ��" checked="checked"/>һ�ȣ���4000��
									<input type="radio" name="tjdc" value="����"/>���ȣ���2500��				
								</td>
							</tr>
							<tr>
								<th width="25%">
									������
									<br/><font color="red">(��������100)</font>
								</th>
								<td>
									<textarea rows="3" id="shyj" cols="" 
										onblur="chLeng(this,100);"
										style="word-break:break-all;width:99%" ></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="saveGjzxjSh('tg')">ͨ ��</button>
										<button type="button" onclick="saveGjzxjSh('btg')">��ͨ��</button>
										<button type="button" onclick="closeWindown();return false;">�ر�</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ���̸��ٵ����� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>