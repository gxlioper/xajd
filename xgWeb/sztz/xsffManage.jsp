<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
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
			function checkValue(obj,ind){
				checkInputNum(obj);
				if(obj.value==""){
					obj.value="0";
				}
				var xf = document.getElementById("xf_"+ind);
				var maxjxsf = document.getElementById("maxjxsf_"+ind);
				if(obj.value !="0" &&( eval(obj.value)<eval(xf.value) || eval(obj.value)>eval(xf.value)+eval(maxjxsf.value))){
					obj.value="";
					if(maxjxsf.value != "0"){						
						alert("输入的数值必须在"+xf.value+"和"+eval(eval(xf.value)+eval(maxjxsf.value))+"之间");						
					}else{
						alert("输入的数值可以为0或者"+xf.value);
					}
					obj.focus();
					return false;
				}
			}
		</script>
	</head>

	<body onload="initHxnl(jQuery('#kmdm'));hiddenTr($('moreTerm'));xyDisabled('xy');">
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		<input type="hidden" name="userName" value="${userName }" />
		<input type="hidden" id="userType" name="userType" value="${userType }" />
		<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/sztz" method="post">
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href='#'
								   class="btn_ccg"
								   onclick="confirmInfo('您确定要保存吗?',function(t){if(t=='ok'){refreshForm('sztz.do?method=xsffSave')}})"
								   id="btn_ccg">保存学分</a>
							</li>
							<li>
								<a href="#"
								   onclick="showPrintWindow('primarykey','sztz.do?method=sztzMxqCjdPrint','请选择一条学生数据!')"
								   class="btn_dy"
								>
									打印每学期成绩单
								</a>
							</li>
							<li>
								<a href="#"
								   onclick="showPrintWindow('primarykey','sztz.do?method=sztzCjdPrint','请选择一条学生数据!')"
								   class="btn_dy"
								>
									学生毕业成绩单
								</a>
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
											onclick="allNotEmpThenGo('sztz.do?method=xsffManage&doType=query')">
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
								<td>
								</td>
								<td>
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
								<td>
									<input type="checkbox" disabled="true"/>
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
								<logic:iterate name="rs" id="s" indexId="ind">
									<tr>
										<td>
											<input name="primarykey" type="checkbox" value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>,<logic:iterate id="v" name="s" offset="3" length="1">${v }</logic:iterate>,<logic:iterate id="v" name="s" offset="4" length="1">${v }</logic:iterate>"/>
										</td>
										<td>
											<input type="hidden" name="primarykey_cbv" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
											<a
												href="javascript:showTopWin('sztz.do?method=sztzUpdate&doType=view&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>','750','550');"
												class="pointer" style="color:#0A63BF"> 
													<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>
											</a>
										</td>
										<logic:iterate id="v" name="s" offset="2" length="6">
											<td>
												${v }
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="8" length="1">
											<td>
												<span id = "xf_${ind}" value="${v }">${v }<span>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="9" length="1">
											<td>
												<span id = "maxjxsf_${ind}" value="${v }">${v }<span>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="10" length="1">
											<td>
												<input type="text" name="sdxf" value="${v }" maxlength="5" onblur="checkValue(this,${ind})" style="width:80px" id="pageno"/>
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
										<input type="checkbox" disabled="true"/>
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
										<input type="checkbox" disabled="true"/>
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
			
			<logic:present name="message">
				<script defer>
					alertInfo('${message}');
				</script>
			</logic:present>
			
		</html:form>
	</body>
</html>
