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
						//alertError('学校名称所对应的代码不存在！');
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
		  alertInfo("学校名称不能为空！");
		  return false;
		}
		if(setxxdm==""){
		  alertInfo("学校代码不能为空！");
		  return false;
		}
		if(xn==""){
		  alertInfo("学年不能为空！");
		  return false;
		}
		if(nd==""){
		  alertInfo("年度不能为空！");
		  return false;
		}
		if(xq==""){
		  alertInfo("学期不能为空！");
		  return false;
		}
		
		if($("edition")){
		
			var objName = "nowEdition";
			var obj_num = document.getElementsByName(objName).length;
			var edition = $("edition").value;
			var objId = "";
			
			//版本				
			for(var i=0;i<obj_num;i++){
				var obj = document.getElementsByName(objName)[i];
				if(obj.checked){
					objId = obj.id;
				}
			}
			
			//高级查询
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
				
				var message = "你修改了版本，保存后将会跳转到所选择版本的首页，请确认修改";
				
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
			/*simpleWindown_html += "<iframe style=\"display:none;_display:block;position:absolute;z-index:-1;width:100%;height:100%;top:0;left:0;scrolling:no;\" frameborder=\"0\" src=\"about:blank\"></iframe>";*/  //试图解决IE6下浮动图片被select覆盖问题
			simpleWindown_html += "<div id=\"windown-box\">";
			simpleWindown_html += "<div id=\"windown-title\"><h2></h2><span id=\"windown-close\">关闭</span></div>";
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


	//加载模糊搜索提示信息
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
	 * 将obj中光标向右移动n个字符
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
		//取得数据表：dmk_xx; 字段：xxmc
		var autoSetting = {
			dataTable:"dmk_xx",
			dataField:"xxmc"
		}
		// 模糊搜索下拉【学校名称】
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
				<em>您的当前位置:</em><a>系统维护-系统设置-系统初始化</a>
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
								<span>系统设置</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="保存"
										onclick="queryxxdm();savextsz();">
										保 存
									</button>
									<button name="重置" type="reset">
										重 置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								学校名称
							</th>
							<td width="80%">
								<div class="pos" style="z-index:3">
								<!-- 防止IE8 下按Enter键  提交数据 -->
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
								学校代码
							</th>
							<td id="setxxdm">
								<bean:write name="setxxdm"/>
							</td>
						</tr>
						<tr>
							<th>
								当前年度
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
								当前学年
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
								当前学期
							</th>
							<td>
								<html:select property="xq" styleId="xq">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr id = "yktyjTr" style= "display: none;">
							<th>一卡通预警</th>
							<td>
								<label><input type="radio" name="yjztrad" value="1" onclick="changeYjzt();"/>开启</label>
								<label><input type="radio" name="yjztrad" value="0" onclick="changeYjzt();"/>关闭</label>
								<input name="yjzt" id="yjzt" type="hidden" value="${yjzt}"/>
							</td>
						</tr>
						<!-- 只开放特殊用户 -->
						<logic:equal name="userName" value="zhz">
							<tr>
								<th>
									版本选择
								</th>
								<td>
									<input type="radio" name="nowEdition" id="oldEdition"
										value="old" />
									标准版
									<input type="radio" name="nowEdition" id="newEdition"
										value="new" />
									2011版
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
									高级查询
								</th>
								<td>
									<input type="radio" name="nowSuperSearch" id="noSuperSearch"
										value="no" />
									不需要
									<input type="radio" name="nowSuperSearch" id="needSuperSearch"
										value="yes" />
									需要
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
									学期周数
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
									学期周数
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
									学期周数
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
									首周起始日期
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
											恭喜你！保存成功！
										</p>
									</th>
								</tr>
								<tr>
									<td colspan="2" align="center" class="btn01">
										<input name="确定 " type="button" class="button" value="确 定" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
	alertInfo("保存成功！");
</script>
					<%--					showMessage("testID",true);--%>
					</script>
				</logic:equal>
				<logic:equal value="no" name="commanForm" property="changed">
					<script>
	alertInfo("保存失败！");
</script>
				</logic:equal>
			</logic:notEmpty>
			<logic:equal name="nofind" value="nofind">
				<script>
	alertInfo("无法在代码库找到该学校对应代码！");
</script>
			</logic:equal>

		</html:form>
	</body>
</html>
