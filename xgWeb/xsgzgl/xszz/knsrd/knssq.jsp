<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsrd/js/knsrd.js"></script>
		<script type="text/javascript" defer="defer">

			jQuery(function(){
				
				var xh = jQuery("#xh").val();
				loadJtqk(xh);
				
				jQuery("#xh").change(function(){
					whenXhChange(jQuery(this).val());
				});
				
				//加载下拉选项
				loadMkxxSelectOptions();
				//加载radio选项
				loadMkxxRadioOptions();
				
				var isopen = jQuery("#isopen").val();
				var shzt = jQuery("#shzt").val();
				if('3' != shzt && (isopen==null||isopen==''||"false" == isopen)){
					jQuery("#btn_submit").hide();
				}

				if("10742"==jQuery("#xxdm").val()) {
					var sqlydms = jQuery("#sqlydm").val();
					var sqlydmss;
	
					if(sqlydms.length>0){
						sqlydmss = sqlydms.split(",");
						for ( var i = 0; i < sqlydmss.length; i++) {
							jQuery("input[name=sqlydm]").each(function(){
								if(jQuery(this).val() == sqlydmss[i]){
									jQuery(this).attr("checked","checked");
								}
							});
						}
					}
				}
                if("12389"==jQuery("#xxdm").val()) {
                    var sqlydms = jQuery("#ylzd9").val();
                    var sqlydmss;

                    if(sqlydms.length>0){
                        sqlydmss = sqlydms.split(",");
                        for ( var i = 0; i < sqlydmss.length; i++) {
                            jQuery("input[name=sqly_ckb]").each(function(){
                                if(jQuery(this).val() == sqlydmss[i]){
                                    jQuery(this).attr("checked","checked");
                                }
                            });
                        }
                    }
                }
				
			});

			//当学号改变时
			function whenXhChange(xh){
				
				//验证是否重复申请
				jQuery.ajax({
					url:"xszz_knsrd.do?method=isHaveKnsrd&xh="+xh,
					dataType: "JSON",
					type: "POST",
					success: function(data){
						if(data["ishave"]){
							showAlertDivLayer("当前周期已有申请记录，无需重复申请");
							lock();
							return false;
						}else{
							unlock();
						}
					}
				});
				
				loadJtqk(xh);
			}
			//展开家庭情况时需要加载的内容
			function loadJtqk(xh){

				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh});
				}
			}

			
			//锁定【保存草稿】和【提交申请】按钮
			function lock(){
				jQuery(".btn").find("button").slice(0,2).each(function(){
					jQuery(this).attr("disabled","disabled");
					jQuery(this).css({color:"#cccccc"});
				});
			}
			
			//解锁【保存草稿】和【提交申请】按钮
			function unlock(){
				jQuery(".btn").find("button").slice(0,2).each(function(){
					jQuery(this).attr("disabled",false);
					jQuery(this).css({color:"#ffffff"});
				});
			}
			
		</script>
	</head>
	<body>
		<input type="hidden" id="openJtqk" value="${openJtqk }" />
		<input type="hidden" name="isopen" id="isopen" value="${isopen}" />
		<input type="hidden" name="sqsftxdc" id="sqsftxdc" value="${sqsftxdc}" />
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" name="shzt" id="shzt" value="${mkxxForm.shzt }" />
		<button id="search_go" type="button" style="display: none" onclick="reloadWindow();"></button>

		<html:form action="/xszz_knsrd" method="post" styleId="knsrdForm" onsubmit="return false">
			<html:hidden property="guid" />
			<input type="hidden" id="ishave" value="${ishave}" />
			<input type="hidden" id="type" value="${type }" />
			<html:hidden property="shzt" styleId="shzt" />
			<html:hidden property="sqlydm"  styleId="sqlydm"/>
			<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }" />

			<div style='tab;width:100%;height:650px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>

					<%@ include file="/xsgzgl/xszz/bdpz/selectStudentForKnsrd.jsp"%>

					<thead>
						<tr>
							<th colspan="4">
								<span>家庭情况
									 <%-- <logic:notEqual value="" property="xh" name="knsrdForm"> --%>
										<a id="showJtqk" onclick="showJtqk(this);" class="up"
											href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
										</a>
										|
										<a onclick="editJtqk();" class="btn_xg"
											href="javascript:void(0);"> <font color="blue">编辑家庭情况</font>
										</a>
									<%-- </logic:notEqual>  --%>
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
								<span>困难生认定申请</span>
							</th>
						</tr>
					</thead>
					<logic:equal value="10346" name="xxdm">
						<tr>
							<th>
								<span>
									<font color="red">*</font>家庭困难类型
								</span>
							</th>
							<td>
								<html:select property="ylzd5" styleId="ylzd5" >
									<html:option value="">---请选择---</html:option>
									<html:options collection="jtknlxList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
							<th>
								<span>
									<font color="red">*</font>高档消费品类型
								</span>
							</th>
							<td>
								<html:select property="ylzd9" styleId="ylzd9" >
									<html:option value="">---请选择---</html:option>
									<html:options collection="gdxfplxList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
						</tr>
					</logic:equal>
					
					<logic:equal value="1" name="sqsftxdc">
						<tbody>
							<tr>
								<th>
									<logic:equal value="11318" name="xxdm">
										<font color="red">* </font>
									</logic:equal>
									<logic:equal value="10718" name="xxdm">
										<font color="red">* </font>
									</logic:equal>
									<logic:equal value="12861" name="xxdm">
										<font color="red">*</font>
									</logic:equal>
									<logic:equal value="70002" name="xxdm">
										<font color="red">*</font>
									</logic:equal>困难档次
								</th>
								<td colspan="3">
									<html:select property="ylzd1" styleId="ylzd1">
										<html:option value=""></html:option>
										<html:options collection="knsdcList" property="dcdm"
											labelProperty="dcmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<%-- 中国美术学院个性化  --%>
					<logic:equal value="10355" name="xxdm">
						<tbody>
							<tr style="height:10px">
							<th align="right">
								家庭人均年收入<br />
							</th>
							<td colspan="3">
								<html:textarea  property='jtrjnsr' styleId="jtrjnsr" onkeyup="value=value.replace(/[^\d]/g,'')" style="word-break:break-all;width:20%" onblur="chLengs(this,450);"
									rows='1' value="${mkxxForm.jtrjnsr }"  />
							</td>
						</tr>
						</tbody>
					</logic:equal>
					<tr>
						<th>
							<logic:equal value="12861" name="xxdm"><font color="red">*</font></logic:equal>
							<logic:equal value="10335" name="xxdm"><font color="red">* </font></logic:equal>
							<logic:equal value="13431" name="xxdm"><font color="red">* </font></logic:equal>
							<logic:equal value="10355" name="xxdm"><font color="red">* </font></logic:equal>
							<logic:equal value="12389" name="xxdm"><font color="red">* </font></logic:equal>
							<logic:equal value="10344" name="xxdm"><font color="red">* </font></logic:equal>附件信息
						</th>
						<td colspan="3">
							<html:hidden property="ylzd2" styleId="ylzd2" />
							<%-- 中央美院,华东交大理工个性化附件要求页面 --%>
							<logic:equal value="13431" name="xxdm">
								<jsp:include page="/xsgzgl/comm/fileUpload/f_13431.jsp" flush="true"></jsp:include>
							</logic:equal>
								
							<logic:equal value="10355" name="xxdm">
								<jsp:include page="/xsgzgl/comm/fileUpload/f_10355.jsp" flush="true"></jsp:include>
							</logic:equal>
							<logic:notEqual value="13431" name="xxdm">
								<logic:notEqual value="10355" name="xxdm">
									<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								</logic:notEqual>
							</logic:notEqual>

								
							<logic:equal value="12861" name="xxdm">
							   		<script type="text/javascript">
										//调用附件 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//后缀
												accept : 'png|gif|jpg',
												//最大文件大小 单位M
												maxsize: 10,
												//存放附件的隐藏域的id
												elementid : 'ylzd2'
												});
										});
									</script>
                            </logic:equal>
                            <logic:equal value="13431" name="xxdm">
                            
                            	<script type="text/javascript">
										//调用附件 
										jQuery(function(){
											jQuery.MultiUploader_13431({
												maxcount : 3,
												//后缀
												accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
												//最大文件大小 单位M
												maxsize: 10,
												//存放附件的隐藏域的id
												elementid : 'ylzd2'
												});
										});
										
								</script>
                            </logic:equal>
                            <logic:notEqual value="12861" name="xxdm">
                            	<logic:notEqual value="13431" name="xxdm">
                              		 <script type="text/javascript">
										//调用附件 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//后缀
												accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
												//最大文件大小 单位M
												maxsize: 10,
												//存放附件的隐藏域的id
												elementid : 'ylzd2'
												});
										});
										
									</script>
								</logic:notEqual>
                            </logic:notEqual>
							
						</td>
					</tr>
					
					<%-- 北京中医药--%><%--
					<logic:equal value="10026" name="xxdm">
						<tbody>
							<th ><span class="red">*</span>申请理由<br /><span class="red">(限30字)</span></th>
							<td colspan="3" >
							<textarea rows="2" property="ylzd5" styleId="ylzd5" style="width:98%;" 
									name="ylzd5" onblur="checkLen(this,30);" sfbt="yes">${mkxxForm.ylzd5}</textarea>
							
							</td>
						</tbody>
					</logic:equal>
					
					--%>
					<logic:equal value="12389" name="xxdm">
						<tbody>
						<th ><span class="red">*</span>申请理由</th>
						<td colspan="3" >
							<html:hidden property="ylzd9"  styleId="ylzd9"/>
							<logic:notEmpty name="sqlyList">
								<logic:iterate name="sqlyList" id="s"  indexId="i" >
									<label><input type="checkbox" name="sqly_ckb" value="${s.dm}"/>${s.mc}</label>
									<br/>
								</logic:iterate>
								<html:textarea property="ylzd10" styleId="ylzd10" style="width: 98%; height: 40px;" onblur="checkLen(this,200);">
								</html:textarea>
							</logic:notEmpty>
						</td>
						</tbody>
					</logic:equal>
					<logic:equal value="10742" name="xxdm">
						<tbody>
							<th ><span class="red">*</span>申请理由</th>
							<td colspan="3" >
							<logic:notEmpty name="sqlymcList">
							<logic:iterate name="sqlymcList" id="s"  indexId="i" >
								<label><input type="checkbox" name="sqlydm" value="${s.sqlymc}"/>${s.sqlymc}</label>
								<br/>
							</logic:iterate>
							</logic:notEmpty>
							</td>
						</tbody>
					</logic:equal>
					<logic:equal value="10277" name="xxdm">
						<tbody>
								<tr>
									<th align="right">困难原因</th>
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
						</logic:equal>
					<tbody>
						<tr>
							<th align="right"><span class="red">*</span>申请理由</th>
							<td colspan="3">
								<select name="sqlyyy" id="sqlyyy">
									<option value="无" <logic:equal value="无" property="sqlyyy" name="mkxxForm">selected="selected"</logic:equal>>无</option>
									<option value="单亲" <logic:equal value="单亲" property="sqlyyy" name="mkxxForm">selected="selected"</logic:equal>>单亲</option>
									<option value="孤儿" <logic:equal value="孤儿" property="sqlyyy" name="mkxxForm">selected="selected"</logic:equal>>孤儿</option>
									<option value="残疾" <logic:equal value="残疾" property="sqlyyy" name="mkxxForm">selected="selected"</logic:equal>>残疾</option>
									<option value="低保" <logic:equal value="低保" property="sqlyyy" name="mkxxForm">selected="selected"</logic:equal>>低保</option>
									<option value="烈士子女" <logic:equal value="烈士子女" property="sqlyyy" name="mkxxForm">selected="selected"</logic:equal>>烈士子女</option>
									<option value="农村五保" <logic:equal value="农村五保" property="sqlyyy" name="mkxxForm">selected="selected"</logic:equal>>农村五保</option>
									<option value="因病" <logic:equal value="因病" property="sqlyyy" name="mkxxForm">selected="selected"</logic:equal>>因病</option>
									<option value="因灾" <logic:equal value="因灾" property="sqlyyy" name="mkxxForm">selected="selected"</logic:equal>>因灾</option>
									<option value="其他" <logic:equal value="其他" property="sqlyyy" name="mkxxForm">selected="selected"</logic:equal>>其他</option>
									<option value="涉农专业" <logic:equal value="涉农专业" property="sqlyyy" name="mkxxForm">selected="selected"</logic:equal>>涉农专业</option>
								</select>
							</td>
						</tr>
						<tr>
							<th name="th_sqly">
								<font color="red">*</font>具体理由
								<br>
								<font color="red">
									&lt;限30字&gt;
								</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<textarea oninput="chCount(this,0,30)" rows="5" style="width:98%;" name="sqly" onblur="checkLenBtw(this,0,30);" sfbt="yes">${mkxxForm.sqly}</textarea>
							</td>
						</tr>
					</tbody>
					<%--<%@ include file="/xsgzgl/xszz/bdpz/mkxxUpdate.jsp"%>--%>
				</table>
			</div>
			<div style="height:35px;" ></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" id="save_button" type="button"
										onclick="saveKnssq('save');">
										保存草稿
									</button>
									<button type="button" id="btn_submit" type="button"
										onclick="saveKnssq('submit');">
										提交申请
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

