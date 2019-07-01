<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="comm/editor/kindeditor.js"></script>
		<script language="javascript" src="comm/editor/zh_CN.js"></script>
		<script language="javascript" src="comm/editor/editor.js"></script>
		<script language="javascript" src="xsgzgl/xtwh/general/news/js/newsAddUpdate.js"></script>
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
			//refreshForm('xtwh_newsAjax.do?method=saveNews&dotype=update');
			var url = 'xtwh_newsAjax.do?method=saveNews&dotype=update';
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
			var select=jQuery("input[name='toWho']:checked");
			var toWho=jQuery(select).val();
			var pindex=jQuery(select).parents("ul").attr("id").replace("selectDiv_","");
			toWhoTemp = toWho;
			autoSetText(toWho, pindex);
		});
		</script>
	</head>

	<body>
		<html:form action="/xtwh_newsAjax"  method="post" styleId="newsModel">
			<input type="hidden" name="searchTj" id="searchTj" value="${search.searchTj}" />
			<input type="hidden" name="searchTjz" id="searchTjz" value="${search.searchTjz}" />
			<input type="hidden" name="mhcx_lx" id="mhcx_lx" value="${search.mhcx_lx}" />
			<input type="hidden" name="searchLx" id="searchLx" value="${search.searchLx}" />
			<input type="hidden" name="qxfwid" id="qxfwid" value="${search.qxfwid}" />
			<input type="hidden" name="newsId" id="newsId" value="<bean:write name="newsId" />" />
			<div class="tab"
				style="width: 100%; height: 460px; overflow-x: hidden; overflow-y: auto;">
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
							<td>
								<input type="text" name="newsTitle" id="newsTitle"
									style="width: 650px" value="<bean:write name="newstit"/>"
									maxlength="100"
									onkeypress="if(pressEnter(event)){return false;}" />
							</td>
						</tr>
						<tr>
							<th>
								接收对象
							</th>
							<td>
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
															<html:radio styleId="all_${radioMap.toWho}" name="map" property="toWho" value="all_${radioMap.toWho}" onclick="selectDivAllClick('all_${radioMap.toWho}','${index }');" style="cursor:pointer;">全校${radioMap.cName}</html:radio>
														</label>
													</li>
													<li>
														<label style="cursor:pointer;">
															<html:radio styleId="some_${radioMap.toWho}" name="map" property="toWho" value="some_${radioMap.toWho}" onclick="selectDivSomeClick('some_${radioMap.toWho}','${index }');" style="cursor:pointer;">指定${radioMap.cName}</html:radio>
														</label>
													</li>
												</ul>
											</div>
										</div>
									</div>
								</logic:iterate>
								<div style="clear:both;"></div>
									<div class="search_advanced" id="sztj" style="width: 100%;float: left;margin-top:5px">
										<div id="yxtj_div" class="selected-attr">
												<h3 style="margin-top:5px;margin-left:0px;background:none;border:0px none;padding:0px 5px;">
													已选：
												</h3>
												<dl id="yxtj_dl">
										<logic:notEmpty name="selectTj">
												<logic:iterate name="selectTj" id="map">
														<dd>
															<a href="#"><h5>
																	${map.mc}
																</h5>${map.jtmc}</span>
															</a>
														</dd>
												</logic:iterate>
										</logic:notEmpty>
												</dl>
										</div>
									</div>
							</td>
						</tr>
						<tr>
							<th>
								通知类别
							</th>
							<td>
								<html:select property="typeid" styleId="typeid"
									onchange="searchRs();return false;">
									<html:options collection="typeList" property="typeid"
										labelProperty="typename" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>编辑内容
							</th>
							<td>
								<textarea id="editorid" name="editorid"
									style="width: 700px; height: 300px;">
							${editorid }
							</textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存"
									onclick=pubNews();return false;>
									保 存
								</button>
								<button type="button" name="关闭"
									onclick=iFClose();return false;>
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<logic:present name="message">
				<script defer="defer">
					showAlert('${message}', {}, {
						"clkFun" : function() {
							if (parent.window) {
								refershParent();
							}
						}
					});
				</script>
			</logic:present>
		</html:form>

	</body>


</html>