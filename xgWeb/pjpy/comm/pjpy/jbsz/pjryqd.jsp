<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/pjpy/comm/pjpy/jbsz/pjryqd.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/ryqdDWR.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		
		//验证可否调换
		function checkKfdh(){
			var flag= true;
			var userType= $("userType").value;
			var checkbox = jQuery('input[name=primarykey_cbv]:checked');
			
			if(userType != "admin"){
				for(var i=0;i<checkbox.length;i++){
					var obj = checkbox[i];
					var id = "yjdh_"+obj.id.replace("pkV_","");
					var color = $(id).value;
					if(color != ""){
						flag = false;
						alertError("已经被调换过部门的学生不可再被调换，请联系管理员");
						break;
					}
				}
			}
			
			return flag;
		}
		
		//显示名单保存DIV
		function showMdbc(){
			
			var flag = checkKfdh();
			
			//验证可否调换
			if(flag){
				var checkbox = jQuery('input[name=primarykey_cbv]:checked');
				
				if(checkbox.length > 0){
					tipsWindown("系统提示","id:ryqddiv","350","250","true","","true","id");
				}else{
					showTopWin('/xgxt/pjpyRyqd.do?method=pjztmdsz','600','400');
				}
			}
		}
		
		//保存评奖人员名单
		function saveMd(){
			var bjdm="";
			if($("select_save_bjdm")){
				bjdm=$("select_save_bjdm").value;
				if(bjdm!="" && bjdm!=null){
					var bjmc = jQuery('#select_save_bjdm option:selected').text();
					confirmInfo('您确定要把所选学生调整到"'+bjmc+'"吗?',function(t){
						if (t == 'ok'){
							refreshForm('/xgxt/pjpyRyqd.do?method=pjryqd&doType=bcmd&save_bjdm='+bjdm);	
						}
					})
				}else{
					alertInfo("班级不能为空!");
				}
				
			}
			
		}
		
		//评奖人员设置
		function qxpjrysz(){
			var sfysz = jQuery('input[name=save_sfysz]:checked').val();
			
			confirmInfo('您确定要把所选学生的是否参评设置为"'+sfysz+'"吗?',function(t){
				if (t == 'ok'){
					refreshForm('/xgxt/pjpyRyqd.do?method=pjryqd&doType=qxmdsz&save_sfysz='+sfysz);	
				}
			})
		}
		
		function showSzDiv(){
			
			var checkbox = jQuery('input[name=primarykey_cbv]:checked');
			
			if(checkbox.length > 0){
				tipsWindown("系统提示","id:qxszdiv","350","140","true","","true","id");
				//viewTempDiv('人员设置','',350,200);
			} else {
				alertInfo("请选择要操作的记录！");
				return false;
			}
			
		}
		
<%--		function removeOption(){--%>
<%--			$("pjxn").options[0]=null;--%>
<%--			$("pjxq").options[0]=null;--%>
<%--			$("pjnd").options[0]=null;--%>
<%--		}--%>
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优-评奖综合设置-评奖人员确定
				</a>
			</p>

			<!-- 在线帮助 -->
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
			<!-- 在线帮助 end -->
			
			<!-- 相关功能 -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;" 
					onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">相关功能</a>
			</p>
			<!-- 相关功能 end-->
			
		</div>
		<!-- 标题 end-->	
		
		<div class="prompt">
			<h3>
				<span>评奖周期：</span>
			</h3>
			<p>
				评奖学年(${pjxn})&nbsp;&nbsp;
				评奖学期(${pjxqmc})&nbsp;&nbsp;
				评奖年度(${pjnd})&nbsp;&nbsp;
			</p>
		</div>

		<!-- 提示信息 start-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				1.本功能主要用于学生当前所在班级与参与评奖班级<font color="blue">不一致</font>时的所在班级确定。<br/>
				2.当<font color="blue">勾选</font>学生的情况下，可以为所勾选学生的调整参评班级。<br/>
				3.当<font color="blue">没有勾选</font>学生的情况下，可以进行班对班的调整。<br/>
			    4.如果有部分学生没有资格参与本次评奖，请执行<font color="blue">参评设置</font>，将其设置为不可参评。<br/>
			    5.查询数据底色为绿色说明该数据是由评奖人员班级调整过来的数据。
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->	
		
		<!-- 快捷方式 -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
		
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_zhsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function19.png" />
							<p>评奖基本设置</p>
						</a>   	
					</div>
					
				</div>
			</div>
		</div>
		<!-- 快捷方式 end-->
		
		<html:form action="/pjpyRyqd">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="pjxyV" value="" />
			<input type="hidden" name="pjzyV" value="" />
			<input type="hidden" name="pjbjV" value="" />
			<input type="hidden" name="select_save_xydmV" value="" />
			<input type="hidden" name="select_save_zydmV" value="" />
			<input type="hidden" name="select_save_bjdmV" value="" />
			
			<!-- 隐藏域 -->
			<div class="toolbox">
		
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="showMdbc();return false;" class="btn_sz">评奖人员班级调整</a>
							</li>
							<li>
								<a href="#" onclick="showSzDiv();return false;" class="btn_qx">参评设置</a>
							</li>
						</ul>
					</div>
	

					<div class="searchtab">
						<table width="100%" border="0">
							<tfoot>
								<tr>
									<td colspan="6">
										<input type="hidden" name="go" value="a" />
										<div class="btn">
											<button type="button" class="btn_cx" id="search_go"
												onclick="document.forms[0].go.value='go';refreshForm('/xgxt/pjpyRyqd.do?method=pjryqd&doType=query')">
												查 询
											</button>
											<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
												重 置
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
							<tbody>
								<tr>
									<th align="right">
										年级
									</th>
									<td align="left">
										<html:select property="queryequals_nj" styleId="nj" onmouseover=""
											 onchange="initZyList();initBjList();" style="width: 150px">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th align="right">
										<bean:message key="lable.xb" />
									</th>
									<td align="left">
										<html:select property="queryequals_xydm" styleId="xy" onmouseover=""
											style="width:150px" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th align="right">
										专业
									</th>
									<td align="left">
										<html:select property="queryequals_zydm" styleId="zy" onmouseover=""
											style="width:150px" onchange="initBjList();">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th align="right">
										班级
									</th>
									<td align="left">
										<html:select property="queryequals_bjdm" styleId="bj" onmouseover=""
											style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
									<th align="right">
										学号
									</th>
									<td align="left">
										<html:text property="querylike_xh" styleId="xh" style="width: 150px"/>
									</td>
									<th align="right">
										姓名
									</th>
									<td align="left">
										<html:text property="querylike_xm" styleId="xm" style="width: 150px"/>
									</td>
								</tr>
								<tr>
									<th align="right">
										评奖年级
									</th>
									<td align="left">
										<html:select property="queryequals_pjnj" styleId="pjnj" onmouseover=""
											style="width:150px" onchange="initPjZyList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjnj');
											initPjBjList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjbj','pjnj')">
											<html:option value=""></html:option>
											<html:options collection="pjnjList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th align="right">
										评奖<bean:message key="lable.xb" />
									</th>
									<td align="left">
										<html:select property="queryequals_pjxydm" styleId="pjxy" onmouseover=""
											style="width:150px" onchange="initPjZyList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjnj');
											initPjBjList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjbj','pjnj')">
											<html:option value=""></html:option>
											<html:options collection="pjxyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th align="right">
										评奖专业
									</th>
									<td align="left">
										<html:select property="queryequals_pjzydm" styleId="pjzy" onmouseover=""
											style="width:150px" onchange="initPjBjList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjbj','pjnj')">
											<html:option value=""></html:option>
											<html:options collection="pjzyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th align="right">
										评奖班级
									</th>
									<td align="left">
										<html:select property="queryequals_pjbjdm" styleId="pjbj" onmouseover=""
											style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="pjbjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
									<th align="right">
										是否参评
									</th>
									<td align="left">
										<html:select property="queryequals_sfysz" style="width: 150px">
											<html:option value=""></html:option>
											<html:option value="是">是</html:option>
											<html:option value="否">否</html:option>
										</html:select>
									</td>
									<th align="right">
										是否已调整
									</th>
									<td align="left">
										<html:select property="queryequals_sftz" style="width: 150px">
											<html:option value=""></html:option>
											<html:option value="是">是</html:option>
											<html:option value="否">否</html:option>
										</html:select>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="formbox">
			    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
				 		 </logic:empty>
				 		 <html:hidden property="queryequals_pjxn" styleId="xn" value="${pjxn}"/> 
				 		 <html:hidden property="queryequals_pjxq" styleId="xq" value="${pjxq}"/> 
				 		 <html:hidden property="queryequals_pjnd" styleId="nd" value="${pjnd}"/> 
				    </span>
				    </h3>
					<logic:notEmpty name="rs">
						<div class="con_overlfow">
							<table summary="" id="rsTable" class="dateline tablenowrap" width="100%">
								<!-- 表头 -->
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" disabled="true" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="2">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<!-- 表头 end-->
								<!--内容 -->
								<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="background-color:<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>">
										<td>
											<input type="hidden" name="yjdh" id="yjdh_${index }" value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
											<input type="checkbox" name="primarykey_cbv" id="pkV_${index }"
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>" />
											<input type="hidden" name="save_xh" id="save_xh" 
												value="<logic:iterate id="v" name="s" offset="2" length="1">${v}</logic:iterate>"/>
											<input type="hidden" name="save_xm" id="save_xm" 
												value="<logic:iterate id="v" name="s" offset="3" length="1">${v}</logic:iterate>"/>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								</tbody>
								<!--内容 end-->
							</table>
							<!--分页-->
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=pjpyRyqdForm"></jsp:include>

							<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>
			
			<div id="ryqddiv" style="display: none;width:230px">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<td colspan="2">
									<span>评奖部门</span>
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th align="right">
									年级
								</th>
								<td align="left">
									<html:select property="save_nj" styleId="select_save_nj"
										style="width:150px" onchange="initPjZyList('view_xg_pjpy_njxyzybj','select_save_xydm','select_save_zydm','select_save_nj');
											initPjBjList('view_xg_pjpy_njxyzybj','select_save_xydm','select_save_zydm','select_save_bjdm','select_save_nj')">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="right">
									<bean:message key="lable.xb" />
								</th>
								<td align="left">
									<html:select property="save_xydm" styleId="select_save_xydm"
										style="width:150px" onchange="initPjZyList('view_xg_pjpy_njxyzybj','select_save_xydm','select_save_zydm','select_save_nj');
											initPjBjList('view_xg_pjpy_njxyzybj','select_save_xydm','select_save_zydm','select_save_bjdm','select_save_nj')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="right">
									专业
								</th>
								<td align="left">
									<html:select property="save_zydm" styleId="select_save_zydm"
										style="width:150px" onchange="initPjBjList('view_xg_pjpy_njxyzybj','select_save_xydm','select_save_zydm','select_save_bjdm','select_save_nj');">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="right">
									<font color="red">*</font>班级
								</th>
								<td align="left">
									<html:select property="save_bjdm" styleId="select_save_bjdm"
										style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan='2' align='right'>
									<div class="bz">"<font color="red">*</font>"为必填项</div>
									<button type="button" onclick='saveMd()'>
										保存
									</button>
									<button type="button" onclick='closeWindown()'>
										取消
									</button>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- 隐藏域 -->
			<div id="qxszdiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<td colspan="2">
									注：该功能用于设置参与评奖的人员。勾选人员后，<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设置为"是"则参与本次评奖，"否"则不参与本次评奖。
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									是否参评
								</th>
								<td>
									<input type="radio" name="save_sfysz" id="save_sfysz"  value="是"/>是
									<input type="radio" name="save_sfysz" id="save_sfysz" checked="checked"  value="否"/>否
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan='2' align='right'>
									<button type="button" onclick='qxpjrysz()'>
										保存
									</button>
									<button type="button" onclick='closeWindown()'>
										取消
									</button>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>		
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
