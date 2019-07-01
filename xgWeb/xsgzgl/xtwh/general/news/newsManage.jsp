<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript">
		//����
		
		//��ʼ��
		function onShow(){ 		
			searchRs()
		}
		
		//Ԥ��
		function newsyl(){
			var pkValues=document.getElementsByName("primarykey_checkVal");
			var pkValue="";
			var n = 0;
			//alert(n);
			for(i=0;i<pkValues.length;i++){
				if(pkValues[i].checked){
					n++;
					if(pkValue==""){
						pkValue=pkValues[i].value;
					}else{
						alertInfo('��ѡ��һ��');
						return false;
					}
				}
			}
			if(n==1 && pkValue!=""){
				//var pkValue = curr_row.getElementsByTagName('input')[0].value;
				var url = 'xtwh_news.do?method=newsInfo&newsId='+pkValue;
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";	
			}else{
				alertInfo('��ѡ��һ��');
				return false;
			}
		}
		//ɾ��
	function delNews(){
	
	var url = "xtwh_newsAjax.do?method=newsDelete";
	
	var n = jQuery("[name=primarykey_checkVal]:checked").length;
	
	
	if(n>0){
		var i = 0;
		var parameter = {};
		var array = new Array();
		var tArray = new Array();
		jQuery("[name=primarykey_checkVal]:checked").each(function(i){			
				array[i] = escape(jQuery(this).val());
				tArray[i] = escape(jQuery(this).parent().children().eq(-1).val());
		});
		parameter["array_primarykey_checkVal"]=array.join('!!array!!');
		parameter["tableArr"]= tArray.join(',');
			confirmInfo("ȷ��Ҫɾ��ѡ�еļ�¼��?",function(ok){
				if(ok=="ok"){		
					jQuery.post(url,parameter,function(result){
						alertInfo(result);
						searchRs();		
					});					
				}
			});
	}else{				
		alertInfo("�빴ѡ��Ҫɾ�������ݣ�");
	}
	}
		//��ѯ���
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			
			var url = "xtwh_newsAjax.do?method=newsSearch";
			var ie = "ie";
		
			var parameter = {"ie":ie}
			var typeid=jQuery("#typeid option:checked").val();
			parameter["typeid"]=typeid;
			jQuery("select,input",jQuery("#tbody_search_query")).each(function(){				
				parameter["str_"+jQuery(this).attr("name")]=escape(jQuery(this).val());
			})
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			searchGo(url,parameter);
			
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		function fb(){
			var url = "xtwh_newsAjax.do?method=newsFb";
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){
				var flag=false;
				jQuery("[name=primarykey_checkVal]:checked").each(function(){
					if(jQuery(this).parents().children("td").eq(5).html()=="��"){
						flag=true;
					}
				});
				if(flag){
					alertInfo("�����ѷ��������ܷ���");
					return false;
				}else{
					var i = 0;
					var parameter = {};
					var array = new Array();
					jQuery("[name=primarykey_checkVal]:checked").each(function(i){			
							array[i] = escape(jQuery(this).val());
					});
					parameter["array_primarykey_checkVal"]=array.join('!!array!!');
						confirmInfo("ȷ��Ҫ������?",function(ok){
							if(ok=="ok"){		
								jQuery.post(url,parameter,function(result){
									alertInfo(result);
									searchRs();		
								});					
							}
					});
				}
			}else{				
				alertInfo("�빴ѡ��Ҫ���������ݣ�");
			}
		}
		function qxfb(){
			var url = "xtwh_newsAjax.do?method=newsQxfb";
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){
				var flag=false;
				jQuery("[name=primarykey_checkVal]:checked").each(function(){
					if(jQuery(this).parents().children("td").eq(5).html()=="��"){
						flag=true;
					}
				});
				if(flag){
					alertInfo("����δ����������ȡ������");
					return false;
				}else{
					var i = 0;
					var parameter = {};
					var array = new Array();
					jQuery("[name=primarykey_checkVal]:checked").each(function(i){			
							array[i] = escape(jQuery(this).val());
					});
					parameter["array_primarykey_checkVal"]=array.join('!!array!!');
						confirmInfo("ȷ��Ҫȡ��������?",function(ok){
							if(ok=="ok"){		
								jQuery.post(url,parameter,function(result){
									alertInfo(result);
									searchRs();		
								});					
							}
					});
				}
			}else{				
				alertInfo("�빴ѡ��Ҫȡ�����������ݣ�");
			}
		}
		function zd(){
			var url = "xtwh_newsAjax.do?method=newsZd";
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){
				var flag=false;
				jQuery("[name=primarykey_checkVal]:checked").each(function(){
					if(jQuery(this).parents().children("td").eq(5).html()=="��"){
						flag=true;
					}
				});
				if(flag){
					alertInfo("δ�������Ų����ö���");
					return false;
				}else{
					var i = 0;
					var parameter = {};
					var array = new Array();
					jQuery("[name=primarykey_checkVal]:checked").each(function(i){			
							array[i] = escape(jQuery(this).val());
					});
					parameter["array_primarykey_checkVal"]=array.join('!!array!!');
						confirmInfo("ȷ��Ҫ�ö���?",function(ok){
							if(ok=="ok"){		
								jQuery.post(url,parameter,function(result){
									alertInfo(result);
									searchRs();		
								});					
							}
						});
				}
			}else{				
				alertInfo("�빴ѡ��Ҫ�ö������ݣ�");
			}
		}
		function qxzd(){
			var url = "xtwh_newsAjax.do?method=newsQxzd";
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){

				var i = 0;
				var parameter = {};
				var array = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){			
						array[i] = escape(jQuery(this).val());
				});
				parameter["array_primarykey_checkVal"]=array.join('!!array!!');
					confirmInfo("ȷ��Ҫȡ���ö���?",function(ok){
						if(ok=="ok"){		
							jQuery.post(url,parameter,function(result){
								alertInfo(result);
								searchRs();		
							});					
						}
					});
			}else{				
				alertInfo("�빴ѡ��Ҫȡ���ö������ݣ�");
			}
		}
		function modNews(){
			var typeid=jQuery("#typeid").val();
			var url="xtwh_newsAjax.do?method=newsUpdate&typeid="+typeid;
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			if(n==0){
				alertInfo("�빴ѡ��Ҫ�޸ĵ����ݣ�");
				return false;
			}else if(n>1){
				alertInfo("�빴ѡһ�����ݣ�");
				return false;
			}else{
				var i = 0;
				var array = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){			
						array[i] = escape(jQuery(this).val());
				});
				var pk = array[0];
				url+="&newsId="+pk;
				//showTopWin(url,'800','600');
				showDialog('',900,505,url);
				
			}
		}
		function addNews(){
			var typeid=jQuery("#typeid").val();
			var url="xtwh_newsAjax.do?method=newsAdd&typeid="+typeid;
			//showTopWin(url,'900','650');
			showDialog('',900,519,url);
		}
		function yydmdView(newsid,tablename){
			var url="xtwh_news.do?method=yydmdView&newsid="+newsid+"&tablename="+tablename;
			showDialog('���Ķ���Ա����',700,450,url);
			}

		
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body >
		<html:form action="/xtwh_news" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<!-- ��ʾ��Ϣ end-->	
			<!-- ģ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="addNews();return false;" class="btn_zj"> ���� </a></li>
						<li><a href="#" onclick="modNews();return false;" class="btn_xg"> �޸� </a></li>
						<li><a href="#" onclick="delNews();return false;" class="btn_sc"> ɾ�� </a></li>
						<li><a href="#" class="btn_yl" onclick="newsyl();return false;">Ԥ��</a></li>
						<li><a href="#" class="btn_shtg" onclick="fb();return false;">����</a></li>
						<li><a href="#" onclick="qxfb();return false;" class="btn_shbtg"> ȡ������ </a></li>
						<li><a href="#" onclick="zd();return false;" class="btn_sy"> �ö�</a></li>
						<li><a href="#" onclick="qxzd();return false;" class="btn_xy"> ȡ���ö� </a></li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� 
				include file="/comm/search/superSearchArea.jsp"
				 �������� end-->
				<div id="tbody_search_query" class="searchtab">
				<table width="100%" class="">
					<tbody>
						<tr>
							<td>����</td>
							<td><input type="text" name="bt" id="bt" onkeypress="if(pressEnter(event)){searchRs();return false;}" style="width: 220px" maxlength="50"/></td>
							<td>
								�Ƿ񷢲�
							</td>
							<td>
							<select name="sffb" id="sffb" style="width:100px"  onchange="">
								<option value=""></option>
								<option value="��">��</option>
								<option value="��">��</option>
							</select>
							</td>
							<td>
								�Ƿ��ö�
							</td>
							<td >
								<select name="sfzd" id="sfzd" style="width:100px"  onchange="">
									<option value=""></option>
									<option value="��">��</option>
									<option value="��">��</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>����ʱ��</<td>
							<td>
								<input type="text" name="kssj"  id="kssj"  onkeypress="if(pressEnter(event)){searchRs();return false;}" 
									onclick="return showCalendar('kssj','y-mm-dd');" 
									readonly="readonly"  style="width:100px;" />&nbsp;��
								<input type="text" name="jssj" id="jssj"  onkeypress="if(pressEnter(event)){searchRs();return false;}" 
									onclick="return showCalendar('jssj','y-mm-dd');" 
									readonly="readonly" style="width:100px;"  /></td>
							<td>֪ͨ���</td>
							<td >
								<html:select property="typeid" styleId="typeid" onchange="searchRs();return false;" style="width:100px">
									<option value="">ȫ��</option>
									<html:options collection="typeList" property="typeid" labelProperty="typename" />
								</html:select>
							</td>
							<td colspan="2" align="center">
								<div class="btn" style="padding-right: 40px;">
				              		<button type="button" id="search_go" 
				              		onclick="searchRs();return false;">
				              		�� ѯ
				              		</button>
				              		<button type="button" class="btn_cz" id="btn_cz" 
									onclick="searchReset();return false;">
									�� ��
									</button>
				            	</div>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
			<div class="formbox" id="cxjg"><%--
				<h3 class="datetitle_01">
					<span>
						��ѯ���&nbsp;&nbsp;
					</span>
				</h3>


					--%><div id="div_rs"
						style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
					</div>

					<!--��ҳ��ʾ-->
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=newsManageForm"></jsp:include>
					<!--��ҳ��ʾ-->
				<%--<logic:empty name="rsList">
					<div class="con_overlfow" style="text-align: center; color: red;" >
						��ǰ������������ݡ�
					</div>
				</logic:empty>--%>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>

	</body>
</html>
