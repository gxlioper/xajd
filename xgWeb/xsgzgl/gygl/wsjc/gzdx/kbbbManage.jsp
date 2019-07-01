<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/gygl/gzdx/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript" src="js/xsgzgl/gygl/gzdx/wsjc.js"></script>
		<script language="javascript" src="js/pclUtil.js"></script>
		<script language="javascript">
		//导出空白报表
		function expKbbb(){
			var url = "/xgxt/gzdxWsjc.do?method=kbbbManage&doType=exp";

			var xy = $("xy").value;
			var xqdm = $("xqdm").value;
			var lddm = $("lddm").value;
			var cs = $("cs").value;
			var qsh = $("qsh").value;
			var zc = "";
			
			if($("zc")){
			 	zc = $("zc").value;
			}
			
			if(xy == "" && lddm == "" && lddm == "" && cs == "" && qsh == "" && zc == "" ){
				if (confirm("未选择任何条件，数据量过大，可能造成导出失败，确认导出？")) {
					document.forms[0].action = url;
					document.forms[0].target = "_blank";
					document.forms[0].submit();
					document.forms[0].target = "_self";	
				}
			}else{
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";	
			}
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/commWsjc">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>		
			<div class="tab">		
				<!-- 项目基本情况 -->
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>导出条件</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr style="">
							<th align="right" width="35%">
								所属<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left" width="65%">
								<logic:equal value="true" name="isxy">
									<html:hidden property="xydm"/>
									<html:select property="xydm" style="width: 200px" styleId="xy" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="true" name="isxy">
									<html:select property="xydm" style="width: 200px" styleId="xy" onchange="">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th align="right">
								校区
							</th>
							<td align="left">
								<html:select property="xqdm" style="width: 200px" styleId="xqdm" onchange="setLdList()">
									<html:options collection="xqdmList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								楼栋
							</th>
							<td align="left">
								<!-- 公寓老版本 -->
								<logic:equal name="gyglEdition" value="old">
									<html:select property="lddm" style="width: 200px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
										<html:options collection="ldList" property="dm" labelProperty="mc" />
									</html:select>
								</logic:equal>
								<!-- 公寓新版本 -->
								<logic:equal name="gyglEdition" value="new">
									<html:select property="lddm" style="width: 200px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
										<html:option value="">----请选择----</html:option>
										<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
									</html:select>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th align="right">
								层数
							</th>
							<td align="left">
								<html:select property="cs" style="width: 200px" styleId="cs" onchange="setQsList();">
									<html:options collection="csList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								寝室号
							</th>
							<td align="left">
								<html:select property="qsh" style="width: 200px" styleId="qsh" onchange="">
									<html:options collection="qsList" property="dm" labelProperty="mc" />
								</html:select>	
							</td>
						</tr>
						<!-- 卫生检查 检查周期为周的情况 -->
						<logic:equal name="jczq" value="周">
						<tr>
							<th align="right">
								周次
							</th>
							<td align="left">
								<html:select property="zc" style="width: 200px" styleId="zc" onchange="">
									<html:option value="">----请选择----</html:option>
									<html:options collection="zcList" property="dm" labelProperty="mc" />
								</html:select>	
							</td>
						</tr>
						</logic:equal>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">	
								<div class="bz">
									<font color="blue">
									注：若导出信息的<bean:message key="lable.xb" />为空，请检查该寝室是否已在宿舍分配模块被分配。
										</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										若未选择任何条件，可能由于数据量的问题导致导出失败。
									</font>
								</div>						
								<div class="btn">
									<button type="button" onclick="expKbbb()" id="buttonSave">
										导 出
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>