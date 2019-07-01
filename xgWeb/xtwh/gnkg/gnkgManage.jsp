<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="js/xgutil.js"></script>
<script language="javascript" src="js/xszz/xszzFunction.js"></script>
<script language="javascript">
function setKgzt(value,num){
	var id = "kgzt"+num;
	$(id).value = value;
}

function saveKgzt(){
	if (confirm('确定开关状态吗？')){
		saveUpdate('/xgxt/xtwhOther.do?method=gnkgManage&doType=save','');
	}
}
</script>
</head>
	<body onload="">
		<html:form action="/xtwhOther">
			<input type="hidden" id="queryequals_ssmk" name="queryequals_ssmk" value="${ssmk}"/>
			<input type="hidden" id="ssmk" name="ssmk" value="${ssmk}"/>
			<!-- 标题 -->
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<!-- 标题 end-->
			<!-- 错误信息 -->
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<!-- 错误信息 end-->
			
			<!-- 无错误 -->
			<logic:empty name="msg">
				<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--读写权-->
					  <logic:equal value="yes" name="writeAble">
					  		<li> <a href="#" onclick="saveKgzt();" class="btn_ccg">保存开关</a> </li>
					  </logic:equal>	
					  <li> <a href="#" onclick="wjcfDataExport('xtwhOther.do?method=gnkgManage&doType=exp');" class="btn_dc">导出数据</a> </li>
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/xtwhOther.do?method=gnkgManage');">
									查询
								</button>
								<button type="button" class="button2" id="cz"
									onclick="czSearchCond('gnmc-gnlb-kgzt');">
									重置
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>类别</th>
						<td>
							<html:select property="queryequals_gnlb" style="width:180px" styleId="gnlb" onchange="">
								<html:option value=""></html:option>
								<html:options collection="gnlbList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<th>开关状态</th>
						<td>	
							<html:select property="queryequals_kgzt" style="width:180px" styleId="kgzt" onchange="">
								<html:option value=""></html:option>
								<html:options collection="kgztList" property="en" labelProperty="cn" />
							</html:select>	
						</td>
						<th>名称</th>
						<td>
							<html:text property="querylike_gnmc" styleId="gnmc" style="width:180px" maxlength="20"/>
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
						<logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序！</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
<%--			        <td width="5%">--%>
<%--<!--						<input type="checkbox" id="selall" name="selall" onclick="selAll()"/>-->--%>
<%--					</td>--%>
					<logic:iterate id="tit" name="topTr" offset="1">
						<td id="<bean:write name="tit" property="en"/>" align="center"
							onclick="tableSort(this)" nowrap>
							<div align="center">
							<bean:write name="tit" property="cn" />
							</div>
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" 
							style="cursor:hand">
							<!-- checkbox -->								
							<logic:iterate id="v" name="s" offset="0" length="1">
								<td align="center">
									<input type="hidden" name="xmdm" value="${v }"/>
									<logic:iterate id="mrxm" name="s" offset="1" length="1">
<%--										<input type="checkbox" id="checkVal"  name="primarykey_checkVal" disabled="disabled"/>--%>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
														
									<bean:write name="v" />
								
							</logic:iterate>
								</td>
							</logic:iterate>
							<!-- 项目信息 -->		
							<logic:iterate id="v" name="s" offset="2" length="1">
								<td align="center">								
									<bean:write name="v" />
								</td>
							</logic:iterate>
							<!-- 开关 -->		
							<logic:iterate id="v" name="s" offset="3" length="1">
								<td align="center">
									<logic:iterate name="kgztList" id="kg">
										${kg.en }
										<logic:equal name="kg" property="en" value="${v }">
											<input type="radio" value="${kg.en }" name="kg${index }" checked="checked" onclick="setKgzt(this.value,${index })"/>
											&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:notEqual name="kg" property="en" value="${v }">
											<input type="radio" value="${kg.en }" name="kg${index }" onclick="setKgzt(this.value,${index })"/>
											&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:notEqual>
									</logic:iterate>
									<input type="hidden" name="xmkg" id="kgzt${index }" value="${v }"/>
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xtwhOtherForm"></jsp:include>
			 	<!--分页显示-->
					<script type="text/javascript">
						$('choose').className="hide";
					</script>
				</logic:notEmpty>
				</div>
				</div>
			</logic:empty>
			<!-- 无错误 end-->
		</html:form>
		<!-- 提示信息 -->
		<%@ include file="tsxx.jsp"%>
		<!-- 提示信息 end-->

	</body>
</html>