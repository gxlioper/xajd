<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
	</head>
	<body onload="viewXsCxTj()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="tips" scope="request" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zgdzdx_Gygl" method="post">
			<logic:notEmpty name="imageview">
			</logic:notEmpty>
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="dxq" name="dxq"
				value="<bean:write name="writeAble" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
			<input type="hidden" name="qshV" id="qshV" value="" />
			<input type="hidden" id="delPk" name="delPk" value="pk" />
			<input type="hidden" name="userName" id="userName"
				value="<bean:write name="userName"/>" />
			<div class="toolbox">
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="SsXXSearch();this.disabled=true">
											查 询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									校区
								</th>
								<td>
									<html:select property="xqdm"  styleId="xqdm"
										onchange="getLdLb();">
										<html:option value="">--请选择--</html:option>
										<html:options collection="xiaoqquList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									楼栋
								</th>
								<td>
									<html:select property="lddm"  styleId="lddm"
										onchange="getLcList()">									
										<html:options collection="ldList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									层数
								</th>
								<td>
									<html:select property="lc"  styleId="lc"
										onchange="getQshList2()">										
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									寝室号
								</th>
								<td>
									<html:select property="qsh"  styleId="qsh">										
										<html:options collection="ssList" property="dm"
											labelProperty="mc" />
									</html:select>	
								</td>
								<th>
								图表统计
								</th>
								<td colspan="3">
									<html:checkbox property="tb" styleId="tbview" value="tbxs"
									onclick="viewXsCxTj()" />
								</td>
							</tr>
							<!-- 第三行 -->
							<tr id="xsCxTj" style="display : none" >
								<th>
									年级
								</th>
								<td>
									<html:select property="imgNj" >
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:hidden property="imgXy"/>
										<html:select property="imgXy" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="imgXy" styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>&nbsp;&nbsp; 
							<logic:empty name="imageview">
								<font color="red">没有相应的数据！</font>
							</logic:empty>
							<logic:notEmpty name="imageview">
							颜色提示：
							<font color="#000000" style="font-style: italic">寝室号标志该房间已满；</font>
							<font color="red" style="font-style: italic">寝室号标志该房间空闲；</font>
							<font color="orange" style="font-style: italic">寝室号标志该房间有空闲床位；</font>
							<font color="green" style="font-style: italic">寝室号标志该房间为特殊房间。</font>
							</logic:notEmpty>
						</span>
					</h3>
					<logic:notEmpty name="imageview">
					<table summary="" class="dateline" align="" width="100%">
				<logic:iterate id="s1" name="imageview">
						<tr align="center">
							<td colspan="2">
								<font size="5" color="blue"> 
								<bean:write name="s1" property="info"/> 
								</font>
							</td>
						</tr>
						<logic:iterate id="s2" name="s1" property="lddms">                               
								<tr>	
									<td align="left" >									
									<span style="width:50px">									
									<font color="red" ><bean:write name="s2" property="css"/></font></span>
												<br/>
							房间数：<bean:write name="s1" property="fjs"/><br/>
				            床位数：<bean:write name="s1" property="cws"/> <br/>
				            空床位数：<bean:write name="s1" property="kcws"/><br/>
<%--				            收费标准：<bean:write name="s1" property="sfbz"/><br/> --%>				            
									</td>
									<td align="left" width="90%" >									
									<logic:iterate id="s3" name="s2" property="qshcolor">									
<%--										<logic:iterate id="s4" name="s3" property="qsh2">	--%>
										<span style="border: 1ex">																			
<%--										<a href="#" style="height: 15px;100px;font:bold"  style="  color: <bean:write name="s3" property="color"/>"  title="<bean:write name="s3" property="ms"/>"  onclick="sendvalue('<bean:write name="s3" property="ssbh"/>')"><bean:write name="s3" property="qsh"/></a>	--%>
										<a href="javascript:sendvalue('<bean:write name="s3" property="ssbh"/>')" style="height: 15px;100px;font:bold"  style=" color: <bean:write name="s3" property="color"/>"><bean:write name="s3" property="qsh"/></a>																																		       																																	       
									   </span>
<%--									    </logic:iterate>	--%>
									</logic:iterate>									
									</td>
								</tr>								
						</logic:iterate>
					</logic:iterate>
				</table>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>
		</html:form>
	</body>

	<script type="text/javascript">
function SsXXSearch(){   
    if($("tb")){
       if($("tb").checked){
       
         var xiaoqu = $("xqdm").value;
         var lddm   = $("lddm").value;
         var qsh    = $("qsh").value;
         
         url = "/xgxt/zgdzdx_Gygl.do?method=getDormImageWiew";
         url +="&xqdm="+xiaoqu;
         url += "&lddm="+lddm;
         url += "&qsh="+qsh;
         allNotEmpThenGo(url);
       }else{
         allNotEmpThenGo('/xgxt/ssxx_search.do?act=dormInfo');
       }
    }else{
       allNotEmpThenGo('/xgxt/ssxx_search.do?act=dormInfo');
    }
}
function viewsearch(){   
    if($("tb")){
       if($("tb").checked){
         var xiaoqu = $("xqdm").value;
         var lddm   = $("lddm").value;
         var qsh    = $("qsh").value;       
         url = "/xgxt/zgdzdx_Gygl.do?method=getDormImageWiew";
         url +="&xqdm="+xiaoqu;
         url += "&lddm="+lddm;
         url += "&qsh="+qsh;
         allNotEmpThenGo(url);
       }
    }else{
    	allNotEmpThenGo('/xgxt/ssxx_search.do?act=dormInfo');
    }
}

</script>
</html>

