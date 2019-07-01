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
			var select=jQuery("input[name='toWho']:checked");
			var toWho=jQuery(select).val();
			var pindex=jQuery(select).parents("ul").attr("id").replace("selectDiv_","");
			//console.log(toWho +" "+ pindex)
			toWhoTemp = toWho;
			autoSetText(toWho, pindex);
		});
		</script>
	</head>
	<body onload="setFocus('filemc');">
		<html:form action="/xtwhSysz" enctype="multipart/form-data">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
				<input type="hidden" name="searchTj" id="searchTj" value="${search.searchTj}" />
				<input type="hidden" name="searchTjz" id="searchTjz" value="${search.searchTjz}" />
				<input type="hidden" name="mhcx_lx" id="mhcx_lx" value="${search.mhcx_lx}" />
				<input type="hidden" name="searchLx" id="searchLx" value="${search.searchLx}" />
				<input type="hidden" name="qxfwid" id="qxfwid" value="${search.qxfwid}" />
				<input type="hidden" name="newsId" id="newsId" value="${search.newsid}" />
			<div class="tab" style="width:100%;height:250px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>文件信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="">
							<th align="right" width="16%">
								文件名称
							</th>
							<td align="left"  colspan="3">
								${rs.filemc }
							</td>
							
						</tr>
						<tr style="height: 23px">
							<th align="right" width="16%">
								文件类型
							</th>
							<td align="left" width="30%">
								<html:select name="rs" property="filelx" style="" disabled="true"
									styleId="filelx">
									<html:options collection="filelxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							
							<th align="right">
								文件所属
							</th>
							<td align="left">
								<html:select name="rs" property="filess" style="" disabled="true"
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
									<logic:iterate id="radioMap" name="radioList" offset="0" indexId="index">
									<div  style="width:25px;float:left">							
										<input type="radio" disabled="disabled" name="selectRadio" onclick="selectRadioClick(${index });">&nbsp;</input>
									</div>	
									<div class="payment_04" style="width: 100px;float: left;margin:0px;">
										<div class="payment_04x1" style="margin:0px;">
											<div class="payment_04x3" style="width: 80px;">
												<span class="selectTextClass" pName="${radioMap.pName}" onclick="selectTextClick(${index });" id="selectText_${index }" style="background-position: 64px center;border-bottom-width: 1px;">${radioMap.pName}可见</span>
												<ul id="selectDiv_${index }" class="selectDivClass" style="display: none;width:200px;top:24px;">
													<li>
														<label style="cursor:pointer;">
															<html:radio disabled="true" styleId="all_${radioMap.toWho}" name="rs" property="toWho" value="all_${radioMap.toWho}" onclick="selectDivAllClick('all_${radioMap.toWho}','${index }');" style="cursor:pointer;">全校${radioMap.cName}</html:radio>
														</label>
													</li>
													<li>
														<label style="cursor:pointer;">
															<html:radio disabled="true" styleId="some_${radioMap.toWho}" name="rs" property="toWho" value="some_${radioMap.toWho}" onclick="selectDivSomeClick('some_${radioMap.toWho}','${index }');" style="cursor:pointer;">指定${radioMap.cName}</html:radio>
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
							<th align="right">
								下载附件
							</th>
							<td align=left colspan="3">
								<input type="hidden" name="filepath" value="${rs.filepath }" />
								<a
									href="czxxDtjsDyxx.do?method=downLoadFile&dir=${rs.filepath }"
									target="_blank" style="color:blue">点击下载</a>
							</td>
						</tr>
						<tr style="height: 23px">
							<th align="right">
								文件说明
							</th>
							<td align="left" colspan="3">
								${rs.filesm }
							</td>
						</tr>
						<tr style="height: 23px">
							<th align="right">
								备注
							</th>
							<td align="left" colspan="3">
								${rs.bz }
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
