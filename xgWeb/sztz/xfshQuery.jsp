<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript">
			function plsh(){
				tipsWindown("系统提示","id:shDiv","350","200","true","","true","id");
			}
			
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
				}
				jQuery('#hxnl').val('${sztzActionForm.hxnl}');
				jQuery.ajaxSetup({async:true});
			}
		</script>
		
	</head>

	<body onload="hiddenTr($('moreTerm'));xyDisabled('xy');initHxnl(jQuery('#kmdm'));">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/sztz" method="post">
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
		
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									id="btn_sh"
									onclick="showAuditingWindow('primarykey_cbv','sztz.do?method=sztzDgshView',750,550);"
									class="btn_sh">审核 </a>
							</li>
							<li>
								<a href="#"
									id="btn_cs"
									onclick="showUpdateWindow('primarykey_cbv','sztz.do?method=sztzLcgz','550','350',false,'请勾选一条记录!')"
									class="btn_cs">流程跟踪 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="bz">
										<label>
											<html:checkbox onclick="hiddenTr(this)" property="moreTerm"
												styleId="moreTerm" />
											<strong>更多条件</strong>
										</label>
									</div>
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('sztz.do?method=xfshQuery&doType=query')">
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
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<logic:equal value="stu" name="userType">
										<html:text property="xh" readonly="true" style="width:175px" value="${userName }"></html:text>
									</logic:equal>
									<logic:notEqual value="stu" name="userType">
										<html:text property="xh" style="width:175px"></html:text>
									</logic:notEqual>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" style="width:175px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:180px"
										styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" styleId="zy"
										onchange="initBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj"
										style="width:180px">
										<logic:notEqual value="yes" name="isBzr">
											<html:option value=""></html:option>
										</logic:notEqual>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						
							<tr>
								<th>学年</th>
								<td>
									<html:select property="xn" style="width:180px">
										<html:options collection="xnList" property="xn" labelProperty="xn"/>
									</html:select>
								</td>
								<th>学期</th>
								<td>
									<html:select property="xq" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
									</html:select>
								</td>
								<th>项目名称</th>
								<td>
									<html:text property="xmmc" style="width:175px"></html:text>
								</td>
							</tr>
							<tr>
								<th>所属科目</th>
								<td>
									<html:select property="kmdm" onchange="initHxnl(this)" styleId="kmdm" style="width:180px">
										<html:options collection="kmdmList" property="dm" labelProperty="mc"/>
									</html:select>
								</td>
								<th>核心能力</th>
								<td>
									<html:select property="hxnl" styleId="hxnl" style="width:180px">
									</html:select>
								</td>
								<th>项目类型</th>
								<td>
									<html:select property="xmlx" style="width:180px">
										<html:options collection="xmlxList" property="dm" labelProperty="mc"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>申报人</th>
								<td>
									<html:text property="sbr" style="width:175px"></html:text>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty>
					</span>
				</h3>
				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=sztzActionForm"></jsp:include>
			</div>
			
			
			
			
			<!-- 批量审核弹出层 -->
			<div id="shDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>请填写审核信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									审核状态
								</th>
								<td>
									<html:select property="shzt" style="width:120px" styleId="shzt">
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									审核意见<br/>
									<font color="red"><限500字></font>
								</th>
								<td>
									<html:textarea property="shyj" styleId="shyj" style="width:95%" rows="3" onblur="chLeng(this,500)"></html:textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button name="提 交" onclick="confirmInfo('您确定要批量提交吗?',function(t){if(t == 'ok'){refreshForm('sztz.do?method=sztzXfPlsh&shzt='+$('shzt').value+'&shyj='+$('shyj').value)}})">
											提 交
										</button>
										<button name="取消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<logic:present name="message">
				<script defer>
					alertInfo('${message}');
				</script>
			</logic:present>
			
		</html:form>
	</body>
</html>
