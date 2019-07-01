<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>	
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="xsgzgl/xtwh/general/news/js/newsAddUpdate.js"></script>
		<script language="javascript">
		jQuery(function($){
			var doType = jQuery("#doType").val();
			if (doType == "update"){//修改
				var select=jQuery("input[name='toWho']:checked");
				var toWho=jQuery(select).val();
				var pindex=jQuery(select).parents("ul").attr("id").replace("selectDiv_","");
				//console.log(toWho +" "+ pindex)
				toWhoTemp = toWho;
				autoSetText(toWho, pindex);
			}else{//增加
				toWhoTemp = "";
				var selectRadio = jQuery("input[name='selectRadio']").eq(0);
				selectRadio.attr("checked","checked");
				selectRadio.click();
			}
            changeFilelx();
		});
		//文件上传
		function fileUpload(){
			var doType = jQuery("#doType").val();
			var filemc = jQuery("#filemc").val();
			if(jQuery.trim(filemc) == ''){
				showAlert("文件名称不能为空！");
				return false;
			}
			var filelx = jQuery("#filelx").val();
			if(jQuery.trim(filelx) == ''){
				showAlert("请选择文件类型！");
				return false;
			}
			var filess = jQuery("#filess").val();
			if(jQuery.trim(filess) == ''){
				showAlert("请选择文件所属！");
				return false;
			}
			var filesm = jQuery("#filesm").val();
			if(filesm.length > 250){
				showAlert("文件说明不能超过250字！");
				return false;
			}
			var bz = jQuery("#bz").val();
			if(bz.length > 250){
				showAlert("备注不能超过250字！");
				return false;
			}
			var select=jQuery("input[name='toWho']:checked");
			var selectV=jQuery(select).val();
			var text=jQuery.trim(jQuery(select).parent().text());
			if(selectV.indexOf("some") >= 0 && jQuery("#yxtj_dl").html() == ""){
				showAlert("请选择"+text+"！");
				return false;
			}
			if (doType == "update"){//修改
				saveUpdate('/xgxt/xtwhSysz.do?method=xzzqUpdate&doType=modi','filemc-filelx-filess');
			}else{//增加
				var filepath =jQuery("#kk").val();
				
				if(filepath != ""){
					saveUpdate('/xgxt/xtwhSysz.do?method=xzzqUpdate&doType=save','filemc-filelx-filess');
				}else{
					showAlert("请选择需要上传的文件");
					return false;
				}
			}
		}
		
		function changeFile(){
			jQuery("#updateFile").show();
			jQuery("#xgfj").hide();
            jQuery("#isUpdate").val("1");
            changeFilelx();
		}



		function changeFilelx() {
            var djzt = jQuery("#filelx").val();
            var isUpdate = jQuery("#isUpdate").val();
            if (djzt == "003") {
                if(isUpdate == "1")
				{
                    document.getElementById("updatesp").style.display = "";
                    document.getElementById("updatept").style.display = "none";
                    document.getElementById("updatesp2").style.display = "";
                    document.getElementById("updatept2").style.display = "none";
                }
                if(isUpdate == "0")
				{
                document.getElementById("sp").style.display = "";
                document.getElementById("pt").style.display = "none";
                }


            } else {
                if(isUpdate =="1")
				{
                    document.getElementById("updatept").style.display = "";
                    document.getElementById("updatesp").style.display = "none";
                    document.getElementById("updatept2").style.display = "";
                    document.getElementById("updatesp2").style.display = "none";
				}
                if(isUpdate == "0") {
                    document.getElementById("pt").style.display = "";
                    document.getElementById("sp").style.display = "none";
                }

            }
        }
		</script>
	</head>
	<body onload="setFocus('filemc');">
		<!-- 标题 -->
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" />
				</a>
			</p>
		</div>--%>
		<!-- 标题 end-->
		<html:form action="/xtwhSysz" enctype="multipart/form-data">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<logic:empty name="rs" property="filepath">
				<input type="hidden" name="searchTj" id="searchTj"/>
				<input type="hidden" name="searchTjz" id="searchTjz"/>
				<input type="hidden" name="mhcx_lx" id="mhcx_lx"/>
				<input type="hidden" name="searchLx" id="searchLx"/>
			</logic:empty>
			<logic:notEmpty name="rs" property="filepath">
				<input type="hidden" name="searchTj" id="searchTj" value="${search.searchTj}" />
				<input type="hidden" name="searchTjz" id="searchTjz" value="${search.searchTjz}" />
				<input type="hidden" name="mhcx_lx" id="mhcx_lx" value="${search.mhcx_lx}" />
				<input type="hidden" name="searchLx" id="searchLx" value="${search.searchLx}" />
				<input type="hidden" name="qxfwid" id="qxfwid" value="${search.qxfwid}" />
				<input type="hidden" name="newsId" id="newsId" value="${rs.newsid}" />
			</logic:notEmpty>
			<div class="tab" style="width:100%;height:435px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>文件信息</span>
							</th>
						</tr>
					</thead>
					<input type="hidden" id="isUpdate" value="0">
					<tbody>
						<tr style="">
							<th align="right" width="16%">
								<font color="red">*</font>文件名称
							</th>
							<td align="left"  colspan="3">
								<html:text name="rs" property="filemc" styleId="filemc"
									 maxlength="50" style="width: 95%"/>
							</td>
							
						</tr>
						<tr style="height: 23px">
							<th align="right" width="16%">
								<font color="red">*</font>文件类型
							</th>
							<td align="left" width="30%">
								<html:select name="rs" property="filelx" style=""
									styleId="filelx" onchange="changeFilelx();">
									<html:options collection="filelxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							
							<th align="right">
								<font color="red">*</font>文件所属
							</th>
							<td align="left">
								<html:select name="rs" property="filess" style=""
									styleId="filess">
									<html:options collection="sslxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								下载对象
							</th>
							
							<td colspan="3">
								<logic:empty name="rs" property="filepath">
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
								</logic:empty>
								<logic:notEmpty name="rs" property="filepath">
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
															<html:radio styleId="all_${radioMap.toWho}" name="rs" property="toWho" value="all_${radioMap.toWho}" onclick="selectDivAllClick('all_${radioMap.toWho}','${index }');" style="cursor:pointer;">全校${radioMap.cName}</html:radio>
														</label>
													</li>
													<li>
														<label style="cursor:pointer;">
															<html:radio styleId="some_${radioMap.toWho}" name="rs" property="toWho" value="some_${radioMap.toWho}" onclick="selectDivSomeClick('some_${radioMap.toWho}','${index }');" style="cursor:pointer;">指定${radioMap.cName}</html:radio>
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
								</logic:notEmpty>
							</td>
						</tr>
						
							<logic:notEmpty name="rs" property="filepath">
							<tr>
								<th align="right">
									下载附件
								</th>
								<td align=left colspan="3">
									<input type="hidden" name="filepath" value="${rs.filepath }" />
									<a
										href="czxxDtjsDyxx.do?method=downLoadFile&dir=${rs.filepath }"
										target="_blank" style="color:blue">点击下载</a>
									<%--								<logic:notEqual name="doType" value="view">--%>
									<%--								&nbsp;&nbsp;--%>
									<%--								<a href="javascript:refreshForm('/xgxt/czxxDtjsDyxx.do?method=fileDel')" >点击删除</a>--%>
									<%--								</logic:notEqual>--%>
								</td>
							</tr>
							<tr id="xgfj" style="height: 43px">
								<th align="right" >
									修改附件
								</th>
								<td align=left colspan="3">
									<input type="hidden" name="filepath" value="${rs.filepath }" />
									<a href="#" onclick="changeFile();" style="color:blue">点击修改</a>
									<%--								<logic:notEqual name="doType" value="view">--%>
									<%--								&nbsp;&nbsp;--%>
									<%--								<a href="javascript:refreshForm('/xgxt/czxxDtjsDyxx.do?method=fileDel')" >点击删除</a>--%>
									<%--								</logic:notEqual>--%>
								</td>
							</tr>
							</logic:notEmpty>
							<tr id="updateFile" style="display:none">
							<th  align="right" >
								<font color="red">*</font>附件上传
								<br /><font color="red"><span id="updatept">&lt;限20M&gt;</span></font><font color="red"><span id="updatesp">&lt;限500M&gt;</span></font>
							</th>
							<td align=left colspan="3">
								<input type="file" name="updateFile" style="width:59%" 
									contenteditable="false" />
								&nbsp;&nbsp;
								<font color="blue"><%-- (文件大小小于&lt;20M&gt;)  --%><span id="updatept2">（限20M）</span><span id="updatesp2">（限500M）</span></font>
							</td>
						</tr>
						
							<logic:empty name="rs" property="filepath">
							<tr>
								<th align="right">
									<font color="red">*</font>附件上传
									<br /><font color="red"><span id="pt">&lt;限20M&gt;</span></font><font color="red"><span id="sp">&lt;限500M&gt;</span></font>
								</th>
								<td align=left colspan="3">
									<input type="file" name="uploadFile" style="width:59%" id="kk"
										contenteditable="false" />
								</td>
							</tr>
							</logic:empty>
						
						<tr style="height: 23px">
							<th align="right">
								文件说明
								<br /><font color="red">&lt;限250字&gt;</font>
							</th>
							<td align="left" colspan="3">
								<pre><html:textarea name="rs" property="filesm" rows="5" styleId="filesm"
									style="word-break:break-all;word-wrap: break-word;width:95%"></html:textarea>
								</pre>
							</td>
						</tr>
						<tr style="height: 23px">
							<th align="right">
								备注
								<br /><font color="red">&lt;限250字&gt;</font>
							</th>
							<td align="left" colspan="3">
								<pre><html:textarea name="rs" property="bz" rows="5" styleId="bz"
									style="word-break:break-all;word-wrap: break-word;width:95%"></html:textarea>
								</pre>
							</td>
						</tr>
					</tbody>
			</table>
		</div>
		<table width="100%" border="0" class="formlist" style=" margin-bottom: 0;">
				<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<!-- 非查看 -->
									
									<button type="button" onclick="fileUpload();return false;" id="buttonSave">
										保 存
									</button>
									
									<button type="button" onclick="iFClose();" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>

	</body>
</html>
