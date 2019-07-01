<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
        <html:form action="/xtwhSpjl" method="post">
        	<html:hidden property="id" styleId="id" />
            <html:hidden property="djh" styleId="djh"  />
            <html:hidden property="djlx" styleId="djlx" />
            <html:hidden property="spbz" styleId="spbz"/>
            <html:hidden property="nspgw" styleId="nspgw"/>
            <html:hidden property="actionName" styleId="actionName"/>
			<div class="tab">
				<h3>
					<em> 审批意见 </em>
				</h3>
				<!-- 
				<div id="xxk">
					<ul>
						<li class="current" id="libasic"
							onclick="setObjStatus(['basic','statistic'])">
							<a href="#" onfocus="this.blur()">审批信息</a>
						</li>
						<li id="listatistic" onclick="setObjStatus(['statistic','basic'])">
							<a href="#" onfocus="this.blur()">审批记录</a>
						</li>
					</ul>
				</div>
				 -->
				<div id="divbasic">
					<span class="opencon">
						<table width="100%" border="0" class="formlist">
							<tbody id="rsTable">
								<tr>
                                    <td id=meeting align="right" width="20%" >
                                    	[申请单号]
                                        <%--
                                        <bean:write name="rs" property="djlx" styleId="djlx" />
                                        <bean:write name="rs" property="name" styleId="name" />
                                        --%>
									</td>
									<td align="left">
									
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										是否通过
									</td>
									<td align="left">
									<%--
										<html:radio name="rs" property="sftg" styleId="sftg" onclick="getRadioValue(this.value)">
										</html:radio>
									 --%>
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										退回状态
										<span class="notnull">*</span>
									</td>
									<td align="left">
                                        <html:select  property="thgw"  styleId="thgw">
                                            <html:option value="">请选择退回岗位</html:option>
                                            <html:options collection="thbzList" property="spgw" labelProperty="spgwmc" />
                                        </html:select>
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										审批状态
									</td>
									<td>
										开始
                                        <logic:iterate name="spbzList" id="s" indexId="index">
                                            --> 
                                            <%--
                                            <script type="text/javascript">
                                            var spgw = "<%=request.getAttribute("spgw")%>";
											if(spgw == "<html:property value="spgw"/>"){
												document.write("<strong><html:property value="spgwmc"/></strong>");
											}else{
												document.write("<html:property value="spgwmc"/>");
											}
											</script>
											--%>
										</logic:iterate>
										-->&nbsp;结束
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										意 见(限510字)
									</td>
									<td>
									<%--
                                        <html:text name="rs" property="mc" styleId="mc" rows="9" cssStyle="width:95%"/>
                                    --%>
									</td>
								</tr>
							</tbody>
						</table> </span>
				</div>
				<div id="divstatistic" style="display: none; height: 384px;">
                <div class="formbox">
                  <h3 class="datetitle_01">
                        <span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
                                <font color="red">未找到任何记录！</font>
                            </logic:empty>  
                            <logic:notEmpty name="rs">
                            <font color="blue"></font>	
                            </logic:notEmpty>
                        </span>
                    </h3>
    
                  <logic:notEmpty name="rs">
                    <div class="con_overlfow" >
					<table summary="" class="dateline" align="" width="100%">
                    	<thead>
						<tr>
							<th>
								审核日期
							</th>
							<th>
								审核人
							</th>
							<th>
								是否通过
							</th>
							<th>
								退回岗位
							</th>
							<th>
								审核意见
							</th>
						</tr>
                        </thead>
						<tbody>
                        	<logic:iterate name="spjlList" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
									<td>
										<input type="checkbox" name="checkVal" id="pkV"
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
										<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>
										/>
									</td>
									<logic:iterate id="v" name="s" >
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
                        </tbody>
                    </table>
                    </div>
                  </logic:notEmpty>
                  </div>
                </div>
                <span class="btn" style="float:right">
                <!-- 关于审批方式，审批通过与不通过皆进入下一审批岗位，退会则退回到前一岗位，或者指定岗位（根据审批流程中的设置） -->
                    <button class="" type="submit">
                        审 批
                    </button>
                    <button type="button" class="btn_cz" onclick="Close();return false;">
                        关 闭
                    </button>
                </span>
			</div>
            <logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alert("操作失败！");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alert("操作成功！");
					</script>
				</logic:equal>
				<script language="javascript">			
					if(window.dialogArguments){
						if(window.dialogArguments.document.getElementById("search_go")){
							dialogArgumentsQueryChick();
						}
						window.close();
					}
				</script>
			</logic:notEmpty>
		</html:form>
		<script language="javascript">
			<%--var sftg = "<html:property value="sftg" />";--%>
			var sftg = "0";
			if(sftg == "0"){
				$("thgw").disabled = true;
			}else{
				$("thgw").disabled = false;
			}
		    function getRadioValue(radioValue){
		    	if(radioValue == "0"){
		    		$("thgw").disabled = true;
				}else{
		    		$("thgw").disabled = false;
				}
				sftg = radioValue;
		    }
		</script>
	</body>
</html>