<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function displayInfo(obj){
				var tbody = jQuery(obj).parents('tbody').eq(0);
				var tr = jQuery('tr',tbody);
				var text = "";
				
				for (var i = 1 ; i < tr.length ; i++){
					var style = jQuery(tr[i]).attr('style');
					
					if (style.trim() == ''){
						style = 'display:none';
						text = "展开";
					} else {
						style = 'display:';
						text = "收起";
					}
					jQuery(tr[i]).attr('style',style);
				}
				jQuery(obj).text(text);
			}
			
			function saveXssq(obj,index){
				var tbody = jQuery(obj).parents('tbody').eq(0);
				var xmid = jQuery('input[name=xmid]',tbody).val();
				var xh = jQuery('#xh').val();
				showTopWin('sztz.do?method=sztzXmsq&xmid='+xmid+'&xh='+xh,600,400);
				
				
			}


			function ckXssq(obj,index){
				var tbody = jQuery(obj).parents('tbody').eq(0);
				var xmid = jQuery('input[name=xmid]',tbody).val();
				var xh = jQuery('#xh').val();
				showTopWin('sztz.do?method=ckXmsq&xmid='+xmid+'&xh='+xh,600,400);
				
				
			}
			
			<%--
			jQuery(function(){
				if (jQuery('#display0')){
					jQuery('#display0').click();
				}
			})
			
			confirmInfo("申请后无法撤销，确定申请吗？",function(t){
					if (t=='ok'){
						var tbody = jQuery(obj).parents('tbody').eq(0);
						var id = jQuery('input[name=id]',tbody).val();
						var xmid = jQuery('input[name=xmid]',tbody).val();
						var shlcid = jQuery('input[name=shlcid]',tbody).val();
						var cyjs = jQuery('input[type=radio]:checked[name=cyjs'+index+']',tbody).val();
						var sfcx = jQuery('input[type=radio]:checked[name=sfcx'+index+']',tbody).val();
						var cgms = jQuery('textarea[name=cgms]',tbody).val();
						var bz = jQuery('textarea[name=bz]',tbody).val();
						var shzt = jQuery('input[name=shzt]',tbody).val();
						
						var sztzModel = {
							id:id,
							xh:jQuery('#xh').val(),
							xmid:xmid,
							cyjs:encodeURI(cyjs),
							sfcx:encodeURI(sfcx),
							cgms:encodeURI(cgms),
							bz:encodeURI(bz),
							shlcid:shlcid,
							shzt:encodeURI(shzt)
						}
						
						jQuery.post('sztz.do?method=xmsqAjaxSave',sztzModel,function(data){
							jQuery('input[name=id]',tbody).val(data.id);
							alertInfo(data.message);
						},'json')
						
						if ('退回' == shzt){
							jQuery('input[name=shzt]',tbody).val('未审核');
							jQuery('#sm'+index).text('审核状态：未审核');
						}
					}
				});	
			
			--%>
			
			function initHxnl(obj){
				if ('' != jQuery(obj).val()){
					var params = {kmdm:jQuery(obj).val()};
					jQuery.ajaxSetup({async:false});
					jQuery.post('sztzAjax.do?method=initHxnl',params,function(data){
						jQuery('#hxnl').empty();
						
						jQuery('<option value=""></option>').appendTo('#hxnl');
						for (var i = 1 ; i < data.length; i++){
							var option="<option value='"+data[i].dm+"'>"+data[i].mc+"</option>";
								
							jQuery(option).appendTo('#hxnl');
						}
					},'json');
					
					jQuery('#hxnl').val('${sztzActionForm.queryequals_hxnl}');
					jQuery.ajaxSetup({async:true});
				}
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		
		<logic:equal value="1" name="sqsjTag" >
			<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					不在学分申报时间内,请于${cssz.sbkssj } 至 ${cssz.sbjssj } 内申报。
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none'"></a>
			</div>
			<script defer>
				jQuery('button[name=save]').attr('disabled',true);
				jQuery('button[name=save]').attr('class','disabled');
			</script>
		</logic:equal>
		<logic:equal value="2" name="sqsjTag" >
			<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					尚未设置学分申报时间，不能进行学分申请。
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none'"></a>
			</div>
			<script defer>
				jQuery('button[name=save]').attr('disabled',true);
				jQuery('button[name=save]').attr('class','disabled');
			</script>
		</logic:equal>
		<logic:equal value="0" name="sqsjTag">
		<html:form action="/sztz">
			<input type="hidden" id="url" name="url"
				value="/sztz.do?method=xssqManage" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-nj-xymc-zymc-bjmc" />
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
								<p class="floatright normal">
									学年：${xn } 学期：${xq } &nbsp;&nbsp;
								</p>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%">
								<font color="red">*</font>学号
							</th>
							<td width="35%">
								<logic:equal value="stu" name="userType">
									${stu.xh }
									<html:hidden property="xh" styleId="xh" value="${stu.xh}"/>
								</logic:equal>
								<logic:notEqual value="stu" name="userType">
									<html:text property="xh" styleId="xh" value="${stu.xh}"
										onkeypress="autoFillStuInfo(event.keyCode,this)"
										onblur="chkIsStu(this,'view_xsjbxx','xh')" />
									<logic:notEqual value="modi" name="oper">
										<button onclick="showTopWin('stu_info.do',800,600);"
											class="btn_01" id="buttonFindStu">
											选择
										</button>
									</logic:notEqual>
								</logic:notEqual>
							</td>
							<th width="15%">
								姓名
							</th>
							<td width="35%">
								${stu.xm }
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								${stu.nj }
							</td>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								${stu.xymc }
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td>
								${stu.zymc }
							</td>
							<th>
								班级
							</th>
							<td>
								${stu.bjmc }
							</td>
						</tr>
					</tbody>

					<logic:present name="xmList">
						<thead>
							<tr>
								<th colspan="4">
									<span>项目信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" class="tdbox">
									<!-- 过滤条件 -->
									<div class="searchtab">
										<table width="100%" border="0" id="searchTab">
											<tfoot>
												<tr>
													<td colspan="6">
														<div class="btn">
															<button class="btn_cx" id="search_go"
																onclick="allNotEmpThenGo('sztz.do?method=xssqManage&nj='+${nj}+'')">
																查 询
															</button>
															<button class="btn_cz" onclick="searchReset();return false;">
																重 置
															</button>
														</div>
													</td>
												</tr>
											</tfoot>
											<tbody>
												<tr>
													<th>年级</th>
													<td>
														<logic:empty name="nj">
															<html:select property="queryequals_nj" styleId="nj" style="width:120px">
																<html:option value="">全部</html:option>
																<html:options collection="njList" property="nj" labelProperty="nj" />
															</html:select>
														</logic:empty>
														<logic:notEmpty name="nj">
															<html:select property="queryequals_nj" styleId="nj" value="${nj }" disabled="true" style="width:120px">
																<html:option value="">全部</html:option>
																<html:options collection="njList" property="nj" labelProperty="nj" />
															</html:select>
														</logic:notEmpty>
									            	</td>
									            	<th>所属科目</th>
													<td>
														<html:select property="queryequals_kmdm" onchange="initHxnl(this)" styleId="kmdm" style="width:120px">
															<html:options collection="kmdmList" property="dm" labelProperty="mc"/>
														</html:select>
													</td>
													<th>核心能力</th>
													<td>
														<html:select property="queryequals_hxnl" styleId="hxnl" style="width:120px">
															
														</html:select>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<table width="100%" class="datelist">
										<logic:iterate id="x" name="xmList" indexId="i">
												<tbody>
												<tr class="tgsh">
													<td>
														编号：${i+1 }
													</td>
													<td>
														<input type="hidden" name="id" value="${x.id }" />
														<input type="hidden" name="xmid" value="${x.xmid }" />
														<input type="hidden" name="shlcid" value="${x.shlcid }" />
														<input type="hidden" name="shzt" value="${x.shzt}" />
														项目名称：
														<a
															href="javascript:showTopWin('sztz.do?method=xmsbView&doType=view&pkValue=${x.xmid }','750','550');"
															class="pointer" style="color:#0A63BF"> ${x.xmmc } </a>
													</td>
													<td width="30%">
														<div id="sm${i }" style="color:red">审核结果:${x.shzt}</div>
													</td>
													<td>
														<div class="btn">
															<button name="save" onclick="saveXssq(this,${i })" id="save${i }">
																申请
															</button>
															<%--
															<button onclick="displayInfo(this);" id="display${i}">
																展开
															</button>
															--%>
															<logic:notEqual name="x" property="shzt" value="">
																<logic:notEqual name="x" property="shzt" value="不通过">
																	<logic:notEqual name="x" property="shzt" value="退回">
																		<script defer="defer">
																			jQuery('#save${i}').attr('disabled',true);
																			jQuery('#save${i}').attr('class','disabled');
																		</script>
																		<button onclick="ckXssq(this,${i })" id="display${i}">
																			查看
																		</button>
																	</logic:notEqual>
																</logic:notEqual>
															</logic:notEqual>
															<logic:equal value="" name="x" property="shzt">
																<script defer="defer">
																	if (Number('${x.rssx}') <= Number('${x.ysqrs}')){
																		jQuery('#save${i}').attr('disabled',true);
																		jQuery('#save${i}').attr('class','disabled');
																		jQuery('#sm${i }').text('说明：申报人数已达上限!');
																	}
																</script>
															</logic:equal>
														</div>
													</td>
												</tr>
												
												<tr style="display:none">
													<td colspan="2">
														科目名称： ${x.kmmc }
													</td>
													<td>
														核心能力名称： ${x.hxnlmc }
													</td>
													<td>
														项目类型： ${x.xmlxmc }
													</td>
												</tr>
												<tr style="display:none">
													<td colspan="2">
														参与角色：
														<logic:equal value="组织" name="x" property="cyjs">
															<input type="radio" name="cyjs${i }" value="参与" />
															参与
															<input type="radio" name="cyjs${i }" value="组织"
																checked="true" />
															组织
														</logic:equal>
														<logic:notEqual value="组织" name="x" property="cyjs">
															<input type="radio" name="cyjs${i }" value="参与"
																checked="true" />
															参与
															<input type="radio" name="cyjs${i }" value="组织" />
															组织
														</logic:notEqual>
													</td>
													<td>
														是否重修：

														<logic:equal value="是" name="x" property="sfcx">
															<input type="radio" name="sfcx${i }" value="是"
																checked="true" />
															是
															<input type="radio" name="sfcx${i }" value="否" />
															否
														</logic:equal>

														<logic:notEqual value="是" name="x" property="sfcx">
															<input type="radio" name="sfcx${i }" value="是" />
															是
															<input type="radio" name="sfcx${i }" value="否"
																checked="true" />
															否
														</logic:notEqual>
													</td>
													<td>
														人数上限：${x.rssx }(人)
													</td>
													
												</tr>
												<tr style="display:none">
													<td align="right" width="10%">
														成果描述
													</td>
													<td style="word-break:break-all;" colspan="3">
														<html:textarea property="cgms" style="width:100%" rows="3"
															onblur="checkLen(this,400)" value="${x.cgms }"></html:textarea>
													</td>
												</tr>
												<tr style="display:none">
													<td align="right">
														备注
													</td>
													<td style="word-break:break-all;" colspan="3">
														<html:textarea property="bz" style="width:100%" rows="3"
															onblur="checkLen(this,400)" value="${x.bz }"></html:textarea>
													</td>
												</tr>
											</tbody>
										</logic:iterate>
									</table>
								</td>
							</tr>
						</tbody>
					</logic:present>
				</table>
			</div>
		</html:form>
		</logic:equal>
	</body>
</html>
