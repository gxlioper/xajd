<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="comm/editor/kindeditor.js"></script>
		<script language="javascript" src="comm/editor/zh_CN.js"></script>
		<script language="javascript" src="comm/editor/editor.js"></script>
		<script language="javascript" src="xsgzgl/xtwh/general/news/js/newsAddUpdate.js"></script>
		<link rel="stylesheet" href="<%= stylePath%>/css/public.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<%=stylePath %>/css/module.css" type="text/css" media="all" />
		<script language="javascript">
		function pubNews(){
			editor.sync();
			if(document.getElementById("newsTitle").value.replace(/(\s*)/g, "") == ""){
				showAlert("请填写新闻标题！");
				document.getElementById("newsTitle").focus();
				return false;
			}
			var select=jQuery("input[name='toWho']:checked");
			var selectV=jQuery(select).val();
			var text=jQuery(select).parent().text();
			if(selectV.indexOf("some") >= 0 && jQuery("#yxtj_dl").html() == ""){
				showAlert("请选择"+text+"！");
				return false;
			}
			var html=editor.html();
			if(html==null||html==""){
				showAlert("请填写新闻正文！");
				return false;
			}
			//refreshForm('xtwh_newsAjax.do?method=saveNews&dotype=add');
			var url = 'xtwh_newsAjax.do?method=saveNews&dotype=add';
			ajaxSubFormWithFun("newsModel",url,function(data){
		    	 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }
				});
		}
		jQuery(function($){
			// 初始化
			toWhoTemp = "";
			var selectRadio = jQuery("input[name='selectRadio']").eq(0);
			selectRadio.attr("checked","checked");
			selectRadio.click();
		});
		</script>
		</head>
	<body>
		<html:form action="/xtwh_newsAjax"  method="post" styleId="newsModel">
		<input type="hidden" name="searchTj" id="searchTj"/>
		<input type="hidden" name="searchTjz" id="searchTjz"/>
		<input type="hidden" name="mhcx_lx" id="mhcx_lx"/>
		<input type="hidden" name="searchLx" id="searchLx"/>
		<div class="tab" style="width:100%;height:474px;overflow-x:hidden;overflow-y:auto;">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>通知公告</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="16%">
							<font color="red">*</font>新闻标题
						</th>
						<td colspan="3">
							<input type="text" name="newsTitle" id="newsTitle"
								style="width:100%" value=""
								maxlength="100" onkeypress="if(pressEnter(event)){return false;}"/>
						</td>
					</tr>
					<tr>
						<th>接收对象</th>
						<td colspan="3">
								<logic:iterate id="radioMap" name="radioList" offset="0" indexId="index">
									<div  style="width:25px;float:left">							
										<input type="radio" name="selectRadio" onclick="selectRadioClick(${index });">&nbsp;</input>
									</div>	
									<div class="payment_04" style="width: 100px;float: left;margin:0px;">
										<div class="payment_04x1" style="margin:0px;">
											<div class="payment_04x3" style="width: 80px;">
												<span class="selectTextClass" pName="${radioMap.pName}" onclick="selectTextClick(${index });" id="selectText_${index }" style="background-position: 64px center;border-bottom-width: 1px;">${radioMap.pName}可见</span>
												<ul id="selectDiv_${index }" class="selectDivClass" style="display: none;width:200px;top:24px;">
													<li>
														<label style="cursor:pointer;">
															<input id="all_${radioMap.toWho}" name="toWho" type="radio" value="all_${radioMap.toWho}" onclick="selectDivAllClick('all_${radioMap.toWho}','${index }');" style="cursor:pointer;">全校${radioMap.cName}</input>
														</label>
													</li>
													<li>
														<label style="cursor:pointer;">
															<input id="some_${radioMap.toWho}" name="toWho" type="radio" value="some_${radioMap.toWho}" onclick="selectDivSomeClick('some_${radioMap.toWho}','${index }');" style="cursor:pointer;">指定${radioMap.cName}</input>
														</label>
													</li>
												</ul>
											</div>
										</div>
									</div>
								</logic:iterate>
								<div style="clear:both;"></div>
								<div class="search_advanced" id="sztj" style="display: none;width: 100%;float: left;margin-top:5px">
									<div id="yxtj_div" class="selected-attr">
												<h3 style="margin-top:5px; margin-left:0px;background:none;border:0px none;padding:0px 5px;">
													已选：
												</h3>
												<dl id="yxtj_dl">
												</dl>
										</div>
								</div>
						</td>
					</tr>
					<tr>
						<th>通知类别</th>
						<td colspan="3">
							<html:select property="typeid" styleId="typeid" onchange="searchRs();return false;">
								<html:options collection="typeList" property="typeid" labelProperty="typename" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>编辑内容
						</th>
						<td colspan="3">
						<textarea id="editorid" name="editorid" style="width:700px;height:280px;">
						</textarea>
						<%--
							<FCK:editor instanceName="content1" toolbarSet="Default" inputName="content1"
								width="100%" height="400px" >
								<jsp:attribute name="value">
								</jsp:attribute>
							</FCK:editor>
						--%></td>
					</tr>
					<tr>
						<th width="15%">
							是否直接发布
						</th>
						<td width="35%">
							<input type="radio" name="sffb" value="是" checked="true"/>&nbsp;是
							<input type="radio" name="sffb" value="否"/>&nbsp;否
						</td>
						<th  width="15%">
							是否置顶
						</th>
						<td  width="35%">
							<input type="radio" name="sfzd" value="是" />&nbsp;是
							<input type="radio" name="sfzd" value="否" checked="true"/>&nbsp;否
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<table width="100%" border="0" class="formlist" style=" margin-bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存 " onclick="pubNews();return false;">
									保 存
								</button>
								<button type="button" name="关闭" onclick="iFClose();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<logic:present name="message">
			<script defer="defer">
			alertInfo('${message}',function(t){
				if (t=="ok") {
					window.close();
					//if(window.opener == undefined){
					//	window.dialogArguments.document.getElementById('search_go').click();
					//}else{
					//	window.opener.document.getElementById('search_go').click();
					//}
					if (parent.window){
    					refershParent();
    				}
				}
			});
			</script>
		</logic:present>
		</html:form>
	</body>


</html>