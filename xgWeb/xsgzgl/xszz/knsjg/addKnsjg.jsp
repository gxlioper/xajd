<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsjg/js/addKnsjg.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
	</head>
	<body>
		
		<html:form action="/xszz_knsjg" method="post" styleId="knsjgForm" onsubmit="return false;">
			<input type="hidden" value="${xxdm}" id="xxdm"/>
			
			<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/selectStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>困难生认定信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<logic:equal value="xn" name="sqzq">
					    <tr>
							<th><span class="red">*</span>学年</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:130px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
								<html:hidden property="xq" styleId="xq" value="on"/>
							</td>
							<th>
							申请时间
							</th>
							<td>
								<html:hidden property="sqsj"/>
								<bean:write name="knsjgForm" property="sqsj"/>
							</td>
					    </tr>
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
									<html:select property="ylzd4" styleId="ylzd4" >
										<html:option value="">---请选择---</html:option>
										<html:options collection="gdxfplxList" labelProperty="mc" property="dm"/>
									</html:select>
								</td>
							</tr>
						</logic:equal>
					    <tr>
					    	<logic:equal value="13871" name="xxdm">
					    		<th><span class="red">*</span>认定档次</th>
								<td>
									<html:select property="rddc" styleId="rddc" style="width:130px">
										<html:options collection="knsdcList" property="dcdm"
											labelProperty="dcmc" />
									</html:select>
								</td>
								<th><span class="red">*</span>困难排序</th>
								<td>
									<html:text property="knpx" styleId="knpx" onkeyup="checkInput(this)" maxlength="10" />
								</td>
					    	</logic:equal>
					    	<logic:notEqual value="13871" name="xxdm">
					    		<th><span class="red">*</span>认定档次</th>
								<td>
									<html:select property="rddc" styleId="rddc" style="width:130px">
										<html:options collection="knsdcList" property="dcdm"
											labelProperty="dcmc" />
									</html:select>
								</td>
								<th>
									<span class="red">*</span>申请理由
								</th>
								<td>
									<select name="sqlyyy" id="sqlyyy">
										<option value="无" >无</option>
										<option value="单亲">单亲</option>
										<option value="孤儿" >孤儿</option>
										<option value="残疾" >残疾</option>
										<option value="低保" >低保</option>
										<option value="烈士子女" >烈士子女</option>
										<option value="农村五保" >农村五保</option>
										<option value="因病" >因病</option>
										<option value="因灾" >因灾</option>
										<option value="其他" >其他</option>
										<option value="涉农专业" >涉农专业</option>
									</select>
								</td>
					    	</logic:notEqual>
					    </tr>
					    </logic:equal>
					    <logic:notEqual value="xn" name="sqzq">
					    <tr>
							<th><span class="red">*</span>学年</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:130px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>学期</th>
							<td>
								<html:select  property="xq" styleId="xq" style="width:130px">
								<html:option value=""></html:option>
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>认定档次</th>
							<td>
								<html:select property="rddc" styleId="rddc" style="width:130px">
									<html:options collection="knsdcList" property="dcdm"
										labelProperty="dcmc" />
								</html:select>
							</td>
							<th>
							申请时间
							</th>
							<td>
								<html:hidden property="sqsj"/>
								<bean:write name="knsjgForm" property="sqsj"/>
							</td>
					    </tr>
					    <logic:equal value="13871" name="xxdm">
					    <tr>
				    		<th><span class="red">*</span>困难排序</th>
							<td colspan="3">
								<html:text property="knpx" styleId="knpx" onkeyup="checkInput(this)" maxlength="10"/>
							</td>
					    </tr>
					    </logic:equal>
					    </logic:notEqual>
					         <%-- 中国美术学院个性化  --%>
					<logic:equal value="10355" name="xxdm">
						
							<tr style="height:10px">
							<th align="right">
								家庭人均年收入<br />
							</th>
							<td colspan="3">
								<html:textarea  property='jtrjnsr' styleId="jtrjnsr" onkeyup="value=value.replace(/[^\d]/g,'')" style="word-break:break-all;width:20%" onblur="chLengs(this,450);"
									rows='1' value="${rs.bz }"  />
							</td>
						</tr>
						
					</logic:equal>
					    <tr>
					    	<th>
								<logic:equal value="12861" name="xxdm"><font color="red">*</font></logic:equal><logic:equal value="10335" name="xxdm"><font color="red">* </font></logic:equal>附件信息
							</th>
							<td colspan="3">
								<html:hidden property="ylzd2" styleId="ylzd2" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
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
	                            <logic:notEqual value="12861" name="xxdm">
	                            	<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//后缀
													accept : 'png|gif|jpg|zip|rar|doc|docx',
													//最大文件大小 单位M
													maxsize: 10,
													//存放附件的隐藏域的id
													elementid : 'ylzd2'
													});
											});											
									</script>
	                            </logic:notEqual>		
							</td>					    
					    </tr>
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
										<input type="checkbox" name="ylzd5" value="<%=map.get("yydm")%>" /><%=map.get("yymc")%><br />
									<%
									}
									%>
								</td>
							</tr>
						</tbody>
						</logic:equal>
					    <!--  
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
						-->
<%--						<logic:notEqual name="xxdm" value="10026">--%>
<%--					    	<tr>--%>
<%--								<th>--%>
<%--									认定理由--%>
<%--									<br /><font color="red">(限200字)</font>--%>
<%--								</th>--%>
<%--								<td colspan="3">--%>
<%--									<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='5' onblur="checkLen(this,200);"/>--%>
<%--								</td>--%>
<%--			      			</tr>--%>
<%--			      		</logic:notEqual>--%>
			     <!--北京中医药大学-->
<%--			      <logic:equal value="10026" name="xxdm">--%>
			       		<tr>
							<th>
								认定理由
								<br /><font color="red">(限30字)</font>
							</th>
							<td colspan="3">
								<html:textarea property='ylzd5' style="width:98%" styleId="ylzd5" rows='2' onblur="checkLen(this,30);"/>
							</td>
			      		</tr>
<%--			       </logic:equal>--%>
<%--			      <logic:equal value="10026" name="xxdm">--%>
			       		<tr>
							<th>
								认定补充理由
								<br /><font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='5' onblur="checkLen(this,200);"/>
							</td>
			     		</tr>
<%--			       </logic:equal>--%>
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
									<button type="button" type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
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

