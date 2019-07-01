<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>

		<script type="text/javascript">
<%--		function queryxxdm(){--%>
<%--		  var xxmc = document.getElementById("xxmc").value;--%>
<%--		  document.forms[0].action = "/xgxt/system_init.do?doType=search&xxmc="+xxmc;--%>
<%--	      document.forms[0].submit();--%>
<%--	    }--%>

	    function queryxxdm(){
		  var xxmc = document.getElementById("xxmc").value;
		  var url = "xtwhOther.do?method=getXxdm";
   			jQuery.ajax({
				type:'post',
				url:url,
				dataType:'json',
				contentType:"application/x-www-form-urlencoded; charset=UTF-8",
				data:'xxmc='+xxmc,
				async: false,
				success:function(result){
					if(result==null||result=="null"){
						//alertError('ѧУ��������Ӧ�Ĵ��벻���ڣ�');
						jQuery('#setxxdm').html('');
						return false;
					}else{
						jQuery('#setxxdm').html(result);
						if(result == "10346"){
							jQuery("#yktyjTr").show();
						}else{
							jQuery("#yktyjTr").hide();
						}
					}
				}
   			
			});
	    }
		
		function savextsz(){
		  var xxmc = document.getElementById("xxmc").value;
		  var setxxdm = jQuery('#setxxdm').html();
		  var xn = document.getElementById("xn").value;
		  var nd = document.getElementById("nd").value;
		  var xq = document.getElementById("xq").value;
		if(xxmc==""){
		  alertInfo("ѧУ���Ʋ���Ϊ�գ�");
		  return false;
		}
		if(setxxdm==""){
		  alertInfo("ѧУ���벻��Ϊ�գ�");
		  return false;
		}
		if(xn==""){
		  alertInfo("ѧ�겻��Ϊ�գ�");
		  return false;
		}
		if(nd==""){
		  alertInfo("��Ȳ���Ϊ�գ�");
		  return false;
		}
		if(xq==""){
		  alertInfo("ѧ�ڲ���Ϊ�գ�");
		  return false;
		}
		
		if($("edition")){
		
			var objName = "nowEdition";
			var obj_num = document.getElementsByName(objName).length;
			var edition = $("edition").value;
			var objId = "";
			
			//�汾				
			for(var i=0;i<obj_num;i++){
				var obj = document.getElementsByName(objName)[i];
				if(obj.checked){
					objId = obj.id;
				}
			}
			
			//�߼���ѯ
			var searchObj = "nowSuperSearch";
			var searchObj_num = document.getElementsByName(searchObj).length;
			var superSearch = $("superSearch").value;
			var superSearchId = "";
			
			for(var i=0;i<searchObj_num;i++){
				var obj = document.getElementsByName(searchObj)[i];
				if(obj.checked){
					superSearchId = obj.id;
				}
			}
			
			url = "/xgxt/system_init.do?act=save";
			url += "&nowSuperSearch="+$(superSearchId).value;
			url += "&setxxdm=" + setxxdm;
			if($(objId).value != edition){
				url += "&nowEdition="+$(objId).value;
				url += "&hadEdit=yes"
				
				var message = "���޸��˰汾������󽫻���ת����ѡ��汾����ҳ����ȷ���޸�";
				
				if (confirm(message)) {
					refreshForm(url);
		    	}
			}else{
			
				url += "&hadEdit=no"
				
				refreshForm(url);
			}
			
		}else{
	     	refreshForm("/xgxt/system_init.do?act=save&setxxdm="+setxxdm);
		}
	    }
	    
	    	function showMessage(divId,isbackDiv){
  if($('floatDiv')==null){
  			var simpleWindown_html = new String;
  			  var x_pixel = 100;
   			var y_pixel = 25;
   			var leftValue = (document.documentElement.scrollWidth/2 + document.documentElement.scrollLeft - x_pixel) +"px";
   			var topValue = (document.documentElement.scrollHeight/2 + document.documentElement.scrollTop - 10) +"px";
  			simpleWindown_html = "<div id=\"floatDiv\" style=\"position:absolute;height:"+document.body.clientWidth+";filter:alpha(opacity=0);zIndex:1001;left:"+leftValue +";top:"+topValue +";left:"+leftValue +"\">"
  			simpleWindown_html = "<div id=\"windownbg\" style=\"height:"+document.body.clientWidth+"px;filter:alpha(opacity=0);opacity:0;z-index: 9999\"></div>";
			/*simpleWindown_html += "<iframe style=\"display:none;_display:block;position:absolute;z-index:-1;width:100%;height:100%;top:0;left:0;scrolling:no;\" frameborder=\"0\" src=\"about:blank\"></iframe>";*/  //��ͼ���IE6�¸���ͼƬ��select��������
			simpleWindown_html += "<div id=\"windown-box\">";
			simpleWindown_html += "<div id=\"windown-title\"><h2></h2><span id=\"windown-close\">�ر�</span></div>";
			simpleWindown_html += "<div id=\"windown-content-border\"><div id=\"windown-content\"></div></div>"; 
			simpleWindown_html += "</div>";
			simpleWindown_html += "</div>";
			//$("body").append(simpleWindown_html);
   var floatDiv = document.createElement('div');
   floatDiv.innerHTML=simpleWindown_html;
   document.body.appendChild(floatDiv);
    $("windown-content").innerHTML=$(divId).innerHTML;
  }
  else{
   $('floatDiv').style.display = "block";
  }
  if($('backDiv')==null && isbackDiv ==true){
   var backDiv = document.createElement('div');
   backDiv.id = "backDiv";
   backDiv.style.backgroundColor = "Black";
   backDiv.style.filter = "alpha(opacity=70)";
   backDiv.style.MozOpacity = "0.70";
   backDiv.style.position = "absolute";
   backDiv.style.left = "0px";
   backDiv.style.top = "0px";
   backDiv.style.width = Math.max(document.body.scrollWidth, document.body.clientWidth) +"px";
   backDiv.style.height = Math.max(document.body.scrollHeight, document.body.clientHeight)+"px";
   document.body.appendChild(backDiv);
   $('backDiv').style.zIndex = 1000;
  }
  else if(isbackDiv ==true){
   $('backDiv').style.display = "block";
  }  
   alertInfo("!!");
 }

function nextDo(){

	var url = "/xgxt/"
	var hadEdit = "";
	
	if($("hadEdit")){
		hadEdit = $("hadEdit").value;
	}
	var edition = "";
	
	if($("edition")){
		edition = $("edition").value;
	}
	
	
	if(hadEdit == "yes"){
		if(edition == "new"){
			url+="teaPage.jsp";
		}else{
			url+="default.jsp";
		}
		
		parent.document.location = url;
	}
}   	


	//����ģ��������ʾ��Ϣ
	function loadtsxx(){
		loadSuggestion('xxmc','msg_xxmc','dmk_xx','xxmc','xxmc'); 
	}
	
	function loadSuggestion(id,divid,tablename,zdm,ppzdm){
		var value = document.getElementById(id).value;
		  var url = "xtwhOther.do?method=getXxmc";
		  if(null!=value&&""!=value){

			  jQuery.ajax({
					type:'post',
					url:url,
					//dataType:'text',
					contentType:"application/x-www-form-urlencoded; charset=UTF-8",
					data:'srz='+value,
					success:function(data){
	 				if(data!=""&&data!=null){
						var arr=data.split(",");
						var Msg=document.getElementById(divid);
						
						Msg.innerHTML="";
						Msg.style.visibility="visible";
						for(var i=0;i<arr.length;i++){
							var suggest="<div onclick=\"setTextValue('" + id + "','" + divid + "',this.innerHTML);\" style='cursor: hand;'>"+arr[i]+"</div>";
							Msg.innerHTML+=suggest;
						}
					}else{
						document.getElementById(divid).style.visibility="hidden"; 
					} 
							
				}
			});
		  }else{
			  document.getElementById(divid).style.visibility="hidden"; 
		}
    }

	function setTextValue(id,divid,text){
		if (text!=""){
			document.getElementById(id).value=text;
			document.getElementById(divid).style.visibility="hidden";
			moveAtCaret(document.getElementById(id),text.length);
		}
	//	queryxxdm()
	}
	
	/**
	 * ��obj�й�������ƶ�n���ַ�
	 * @param obj
	 * @param n
	 * @return
	 */
	function moveAtCaret(obj,n){    
		$(obj).focus();    
		var rng=document.selection.createRange();    
		rng.moveStart("character",n);    
		rng.select();    
	}  

	jQuery(function(){
		//ȡ�����ݱ�dmk_xx; �ֶΣ�xxmc
		var autoSetting = {
			dataTable:"dmk_xx",
			dataField:"xxmc"
		}
		// ģ������������ѧУ���ơ�
		jQuery("#xxmc").setAutocomplete(autoSetting);
		nextDo();
		yjzt();
	})
function yjzt(){   
		var xxdm = jQuery("#xxdm").val();
		if(xxdm == "10346"){
			jQuery("#yktyjTr").show();
			var yjzt = jQuery("#yjzt").val();
			if(yjzt == "1"){
				jQuery("input[name='yjztrad'][value='1']").prop("checked", "checked")
			}
			if(yjzt == "0"){
				jQuery("input[name='yjztrad'][value='0']").prop("checked", "checked")
			}
			
		}
	}  
	function changeYjzt(){   
			var yjzt = jQuery('input[name=yjztrad]:checked ').val();
			jQuery("#yjzt").val(yjzt);
	}
	

</script>


	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ϵͳά��-ϵͳ����-ϵͳ��ʼ��</a>
			</p>
		</div>

		<html:form action="/chgPass" method="post">
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
			<input type="hidden" id="hadEdit" name="hadEdit" value="${hadEdit }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ϵͳ����</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="����"
										onclick="queryxxdm();savextsz();">
										�� ��
									</button>
									<button name="����" type="reset">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								ѧУ����
							</th>
							<td width="80%">
								<div class="pos" style="z-index:3">
								<!-- ��ֹIE8 �°�Enter��  �ύ���� -->
								<input type="text" style="display: none;"/>
								<logic:equal value="1" name="devMode" scope="request">
								<input type="text" id="xxmc" name="xxmc" style="width:250px"
										value="<bean:write name="xxmc"/>"
										onblur="queryxxdm();return false;"/>
									 <br/>
								</logic:equal>
								<logic:notEqual value="1" name="devMode" scope="request">
								<bean:write name="xxmc"/>
								<input type="hidden" id="xxmc" name="xxmc" value="<bean:write name="xxmc"/>"/>
								</logic:notEqual>
								</div>
							</td>
							
						</tr>
						<tr>
							<th>
								ѧУ����
							</th>
							<td id="setxxdm">
								<bean:write name="setxxdm"/>
							</td>
						</tr>
						<tr>
							<th>
								��ǰ���
							</th>
							<td>
								<html:select property="nd" styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��ǰѧ��
							</th>
							<td>
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��ǰѧ��
							</th>
							<td>
								<html:select property="xq" styleId="xq">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr id = "yktyjTr" style= "display: none;">
							<th>һ��ͨԤ��</th>
							<td>
								<label><input type="radio" name="yjztrad" value="1" onclick="changeYjzt();"/>����</label>
								<label><input type="radio" name="yjztrad" value="0" onclick="changeYjzt();"/>�ر�</label>
								<input name="yjzt" id="yjzt" type="hidden" value="${yjzt}"/>
							</td>
						</tr>
						<!-- ֻ���������û� -->
						<logic:equal name="userName" value="zhz">
							<tr>
								<th>
									�汾ѡ��
								</th>
								<td>
									<input type="radio" name="nowEdition" id="oldEdition"
										value="old" />
									��׼��
									<input type="radio" name="nowEdition" id="newEdition"
										value="new" />
									2011��
									<input type="hidden" id="edition" name="edition"
										value="${edition }" />
									<script language="javascript" defer="defer">
									var objName = "nowEdition";
									var obj_num = document.getElementsByName(objName).length;
									var edition = $("edition").value;
									
									for(var i=0;i<obj_num;i++){
										var obj = document.getElementsByName(objName)[i];
										if(obj.value == edition){
											obj.checked = true;
										}
									}
								</script>
								</td>
							</tr>

							<tr>
								<th>
									�߼���ѯ
								</th>
								<td>
									<input type="radio" name="nowSuperSearch" id="noSuperSearch"
										value="no" />
									����Ҫ
									<input type="radio" name="nowSuperSearch" id="needSuperSearch"
										value="yes" />
									��Ҫ
									<input type="hidden" id="superSearch" name="superSearch"
										value="${superSearch }" />
									<script language="javascript" defer="defer">
									var objName = "nowSuperSearch";
									var obj_num = document.getElementsByName(objName).length;
									var superSearch = $("superSearch").value;
									
									for(var i=0;i<obj_num;i++){
										var obj = document.getElementsByName(objName)[i];
										if(obj.value == superSearch){
											obj.checked = true;
										}
									}
								</script>
								</td>
							</tr>

						</logic:equal>

						<logic:equal value="11641" name="xxdm" scope="request">
							<tr>
								<th>
									ѧ������
								</th>
								<td>
									<input type="text" name="xqzs"
										value="<bean:write name="xqzs"/>" maxlength="2"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
							</tr>
						</logic:equal>
						<logic:present name="showZs">
							<tr>
								<th>
									ѧ������
								</th>
								<td>
									<input type="text" name="xqzs"
										value="<bean:write name="xqzs"/>" maxlength="2"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
							</tr>
						</logic:present>
						<logic:present name="qsrqxqzs">
							<tr>
								<th>
									ѧ������
								</th>
								<td>
									<input type="text" name="xqzs"
										value="<bean:write name="xqzs"/>" maxlength="2"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
							</tr>
							<tr>
								<th>
									������ʼ����
								</th>
								<td>
									<input type="text" name="qsrq" id="qsrq"
										value="<bean:write name="qsrq"/>"
										style="cursor: hand;"
										onclick="return showCalendar('qsrq', 'y-mm-dd');"/>
								</td>
							</tr>
						</logic:present>
					</tbody>
				</table>
			</div>


			<logic:notEmpty name="commanForm" property="changed" scope="request">
				<logic:equal value="yes" name="commanForm" property="changed">
					<div id="testID" style="display: none;">
						<div class="open_prompt">
							<table width="100%" border="0" class="table01">
								<tr>
									<td width="109">
										<div class="img img_smile"></div>
									</td>
									<th width="197">
										<p>
											��ϲ�㣡����ɹ���
										</p>
									</th>
								</tr>
								<tr>
									<td colspan="2" align="center" class="btn01">
										<input name="ȷ�� " type="button" class="button" value="ȷ ��" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
	alertInfo("����ɹ���");
</script>
					<%--					showMessage("testID",true);--%>
					</script>
				</logic:equal>
				<logic:equal value="no" name="commanForm" property="changed">
					<script>
	alertInfo("����ʧ�ܣ�");
</script>
				</logic:equal>
			</logic:notEmpty>
			<logic:equal name="nofind" value="nofind">
				<script>
	alertInfo("�޷��ڴ�����ҵ���ѧУ��Ӧ���룡");
</script>
			</logic:equal>

		</html:form>
	</body>
</html>
