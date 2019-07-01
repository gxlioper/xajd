<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script type="text/javascript">
<!--
	function tjExport() {
			document.forms[0].action = "/xgxt/jyydbb.do?doType=expData";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
	}
	
	function setTj(text) {
		if ('nj'==text) {
			$('xy').disabled=true;
			$('zy').disabled=true;
			$('bj').disabled=true;
			$('nj').disabled=false;
		}if ('xy'==text){
			$('xy').disabled=false;
			$('zy').disabled=true;
			$('bj').disabled=true;
			$('nj').disabled=true;
		} else if ('zy'==text){
			$('xy').disabled=false;
			$('zy').disabled=false;
			$('bj').disabled=true;
			$('nj').disabled=true;
		} else {
			$('xy').disabled=false;
			$('zy').disabled=false;
			$('bj').disabled=false;
			$('nj').disabled=false;
		}
	}
//-->
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/jyydbb" method="post">
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value=""/>
		<input type="hidden" name="bjV" value=""/>
		
		
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：${title }
				</div>
			</div>
			<fieldset>
				<legend>
					查 询
				</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr style="cursor:hand">
								<td>
									
									年级：
								    <html:select property="nj" styleId="nj" style="width:100px" onchange="initZyList();initBjList()">
								    		<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
									</html:select>
									 &nbsp;&nbsp;年度：
								    <html:select property="nd" style="width:100px" styleId="nd">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
									</html:select>
									&nbsp;&nbsp;月份：
									<html:select property="month"  style="width:100px">
										<html:options collection="monthList" property="en" labelProperty="en"/>
									</html:select>
									&nbsp;&nbsp;统计方式：
									<html:select property="tjfs" style="width:100px" onchange="setTj(this.value);">
										<html:option value=""></html:option>
										<html:option value="nj">年级</html:option>
										<html:option value="xy"><bean:message key="lable.xsgzyxpzxy" /></html:option>
										<html:option value="zy">专业</html:option>
										<html:option value="bj">班级</html:option>
									</html:select>
									
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<button class="button2" style="height:40px"
										onclick="allNotEmpThenGo('/xgxt/jyydbb.do?doType=query')" id="search_go">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td>
									<bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="xydm" style="width:150px" styleId="xy" onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
								 		</html:select>
									&nbsp;&nbsp;
									专业：
								   	<html:select property="zydm" style="width:200px" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
								 	</html:select>
								 	&nbsp;&nbsp;班级：
								   	<html:select property="bjdm" style="width:220px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
								 	</html:select>
								</td>
							</tr>
						</thead>
					</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0">
									<td align="center">
										${v}
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
				<div class="buttontool" id="btn"
					style="position: absolute;left:0px;top:100px" width="100%">
					<button class="button2" onclick="tjExport()"
							style="width:80px">
							导出数据
					</button>			
				</div>
	</html:form>
<script type="text/javascript" src="js/bottomButton.js"></script>
</body>