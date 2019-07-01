<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsrd/js/knsrd.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				
				//加载下拉选项
				loadViewMkxxSelectOptions();
				//加载radio选项
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh},function(){
					})
				}

				//前一级认定档次
				//var beforeRddc = jQuery("input[name=tjdc][value !=]").last().val();
				//jQuery("#rddc").val(beforeRddc);

				//非第一级审核有退回按钮
				//var firstGwid = jQuery("input[name=gwid]").first().val();
				//if (firstGwid != jQuery("#xtgwid").val()){
				//	jQuery("#btn_th").css("display","");
				//}

				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#guid").val()+"&tt="+new Date().getTime());				
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+jQuery("#shlc").val()+"&shid="+jQuery("#shid").val()
				,function () {
                        if(jQuery("#xxdm").val()=="12866"){
                            hideShyj();
                        }
                    });

			});
			
			function hideRddc(){
				if("1"!=jQuery("#shjg").val()){
					jQuery("#tjdctr").hide();
				}else{
					jQuery("#tjdctr").show();
				}

                if(jQuery("#xxdm").val()=="12866"){
                    hideShyj();
                }
			}

            function  hideShyj() {
                if("1"==jQuery("#shjg").val()){
                    jQuery("#shyjtr").hide();
                    jQuery("#shyj").val("通过");
                }else{
                    jQuery("#shyjtr").show();
                    jQuery("#shyj").val("");
                }
            }

		</script>
	</head>
	<body>
		<html:form action="/xszz_knsrd" method="post" styleId="knsrdForm">
			<html:hidden property="guid" styleId="guid"/>
			<html:hidden property="xtgwid" styleId="xtgwid"/>
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="shzt" styleId="shzt"/>
			<html:hidden property="shlc" styleId="shlc"/>
			<html:hidden property="shid" styleId="shid"/>
			<input type="hidden" value="${xxdm}" id="xxdm"/>
			<div style='tab;width:100%;height:460px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>认定历史记录
									<a onclick="showLsjl(this);" class="down" 
									   href="javascript:void(0);">
									   <font color="blue">点击展开/收起</font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_rdlsjl" style="display: table-row-group;">
						<tr>
							<td colspan="6">
							<table class="dateline" width="100%">
								<thead align="left">
									<tr align="left">
										<td ><b>学年</b></td>
										<td ><b>学期</b></td>
										<td><b>认定档次</b></td>
										<td ><b>认定时间</b></td>
										<logic:equal value="10335" name="xxdm" scope="request">
											<td ><b>认定状态</b></td>
										</logic:equal>	
									</tr>
								</thead>
								<tbody align="left">
							<logic:notEmpty name="rdlsjlList">
							<logic:iterate name="rdlsjlList" id="s">
										<tr  style="cursor:hand">
											<td >
												${s.xn}
											</td>
											<td >
												${s.xqmc}
											</td>
											<td>
												${s.dcmc}
											</td>
											<td >
												${s.sqsj}
											</td>
										<logic:equal value="10335" name="xxdm" scope="request">
											<td >
												${s.sfqxrd}
											</td>	
										</logic:equal>	
																			
										</tr>
										
								</logic:iterate>
							</logic:notEmpty>
							</tbody>
							</table>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>家庭情况
									<a onclick="showJtqk(this);" class="up" 
									   href="javascript:void(0);">
									   <font color="blue">点击展开/收起</font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_jtqk" style="display: none;">
						<tr>
							<td colspan="4">
								<div id="div_jtqk">
								
								</div>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>困难生认定申请信息</span>
							</th>
						</tr>
					</thead>
					<logic:equal value="10346" name="xxdm">
					<tr>
						<th>
							<span>
								家庭困难类型
							</span>
						</th>
						<td>
							${mkxxForm.jtknlxmc}
						</td>
						<th>
							<span>
								高档消费品类型
							</span>
						</th>
						<td>
							${mkxxForm.gdxfplxmc}
						</td>
					</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="11998">
						<th>
							调查结果分数
						</th>
						<td colspan="3">
							${zf}
						</td>
					</logic:equal>
							<%-- 中国美术学院个性化  --%>
					<logic:equal value="10355" name="xxdm">
						<tbody>
							<tr style="height:10px">
							<th align="right">
								家庭人均年收入
							</th>
							<td colspan="3">
								${mkxxForm.jtrjnsr }
							</td>
						</tr>
						</tbody>
					</logic:equal>
					<tr>
						<th>
							附件信息
						</th>
						<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="ylzd2" styleId="fjid"/>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									var gid = jQuery('#fjid').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								});
							</script>
						</td>
					</tr>
				
					<logic:equal value="1" name="sqsftxdc">
						<tbody>
							<tr>
								<th align="right">申请档次</th>
								<td colspan="3">
									${mkxxForm.sqdcmc }
								</td>
							</tr>
						</tbody>
					</logic:equal>

					<logic:equal value="12389" name="xxdm">
						<tbody>
						<tr>
							<th align="right">申请理由</th>
							<td colspan="3">
								<logic:notEmpty name="sqlyList">
									<logic:iterate name="sqlyList" id="s"  indexId="i" >
										${s}
										<br/>
									</logic:iterate>
									${mkxxForm.ylzd10}
								</logic:notEmpty>
							</td>
						</tr>
						</tbody>
					</logic:equal>
					
					<logic:equal value="10742" name="xxdm">
						<tbody>
							<tr>
								<th align="right">申请理由</th>
								<td colspan="3">
									${mkxxForm.sqlydm }
								</td>
							</tr>
						</tbody>
					</logic:equal>
					
					<logic:equal value="10277" name="xxdm">
					<tbody>
							<tr>
								<th align="right">困难原因</th>
								<td colspan="3">
									${mkxxForm.yymc }
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<tbody>
						<tr>
							<th align="right">申请理由</th>
							<td colspan="3">
									${mkxxForm.sqlyyy}
							</td>
						</tr>
						<tr>
							<th name="th_sqly">
								具体理由
							</th>
							<td colspan="3" style="word-break:break-all;">
									${mkxxForm.sqly}
							</td>
						</tr>
					</tbody>
					<%--<%@ include file="/xsgzgl/xszz/bdpz/mkxxView.jsp" %>--%>
					<thead>
						<tr>
							<th colspan="4">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
						
						<!-- 浙江经济职业技术学院   审核页面增加民主评议结果 -->
						<logic:equal value="12866" name="xxdm">
							<tr>
								<th align="right">
									<font color="red">*</font>民主评议结果
								</th>
								<td colspan="3">
									<html:select property="ylzd4" styleId="ylzd4" >
										<html:option value=""></html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</td>
							</tr>
							
						</logic:equal>
						<logic:equal value="13871" name="xxdm">
							<tr>
								<th align="right">
									<font color="red">*</font>困难排序
								</th>
								<td colspan="3">
									<html:text property="knpx" styleId="knpx" onkeyup="checkInput(this)" maxlength="10"/>
								</td>
							</tr>
						</logic:equal>
						<tr id="tjdctr">
							<th align="right"><font color="red">*</font>推荐档次</th>
							<td colspan="3">
								<html:select property="rddc" styleId="rddc" >
									<html:option value=""></html:option>
									<html:options collection="knsdcList" property="dcdm" labelProperty="dcmc"/>
								</html:select>
							</td>
						</tr>
						<%-- <logic:equal value="10277" name="xxdm">
						<tbody>
								<tr>
									<th align="right">推荐原因</th>
									<td colspan="3">
										<%
										List<HashMap<String, String>> list=(List<HashMap<String, String>>)request.getAttribute("knyyList");
										for(int i=0;i<list.size();i++){
											HashMap<String, String> map=list.get(i);%>
											<input type="checkbox" name="ylzd5" value="<%=map.get("yydm")%>" 
											<%if(("1").equals(map.get("checked"))){%>
													checked
											<%}%> 
											/><%=map.get("yymc")%><br />
										<%
										}
										%>
									</td>
								</tr>
							</tbody>
						</logic:equal> --%>
						<%--  浙大需求改动，该字段去除，by yxy 2015-11-20
						<logic:equal value="10335" name="xxdm">
						<tr>
							<th align="right"><font color="red">*</font>无偿资助金额</th>
							<td colspan="3">
								<html:select property="ylzd3" styleId="ylzd3">
									<html:option value=""></html:option>
									<html:options collection="wczzjeList" property="zzjedm" labelProperty="zzjemc"/>
								</html:select>
							</td>
							
						</tr>
						
						</logic:equal>
						--%>
						<tr>
							<th>
								<font color="red">*</font>审核结果
							</th>
							<td colspan="3" id="shjgSpan" onchange="hideRddc();">
								
							</td>
						</tr>
						<tr id="shyjtr">
							<th>
								<font color="red">*</font>
								审核意见
								<br/>
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=knsrd&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px" rows="5"
											   onblur="checkLen(this,200);" styleId="shyj">
								</html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
							<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
			
								<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" id="btqd" onclick="saveKnssh();">
										保 存
									</button>
<%--									<button type="button" onclick="saveKnssh('1','通过');">--%>
<%--										通过--%>
<%--									</button>--%>
<%--									<button type="button" onclick="saveKnssh('2','不通过');">--%>
<%--										不通过--%>
<%--									</button>--%>
<%--									<button type="button" onclick="saveKnssh('3','退回');" id="btn_th" style="display:none;">--%>
<%--										退回--%>
<%--									</button>--%>
									<button type="button" name="关 闭" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

