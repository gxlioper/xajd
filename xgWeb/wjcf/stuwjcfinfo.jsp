<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript" src="wjcf/zgkd/zgkdJs.js"></script>
<body onload="xyDisabled('xy');">
		<center>
			<html:form action="/wjcfzgkdwh" method="post">
			<input type="hidden" id="userType"  value="${userType }"/>
			<input type="hidden" id="zyV" value=""/>
			<input type="hidden" id="bjV" value=""/>
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="session"/>" />	
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置：违纪处分 - 信息查询 - 个人违纪信息
					</div>
				</div>
				<fieldset>
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
						<tr>
						<td align="left">
							年级：
							<html:select property="nj" styleId="nj" onchange="initZyList();initBjList()"
							 styleClass="select" style="width:70px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
								学年：
								<html:select property="xn" style="width:90px" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							年度：
								<html:select property="nd" style="width:70px" styleClass="select"
									styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							处分类别:
							<html:select property="cflb" styleId="cflb"  style="">
								<html:option value=""></html:option>
								<html:options collection="cflbList" property="cflbdm" labelProperty="cflbmc"/>
							</html:select>
							学号:
							<html:text property="xh" styleId="xh"
							style="width:95px" styleClass="inputtext"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="act" value="qry" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('wjcf_wjstuinfo.do');document.getElementById('search_go').disabled=true;">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:160px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								专业：
								<html:select property="zydm" onchange="initBjList()" style="width:160px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								班级：
								<html:select property="bjdm" style="width:160px" 
								styleClass="select" styleId="bj">
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
							<font color="blue">提示：双击一行可以选定；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1" >
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="sendstuwjinfo()">
									<input type="hidden"
										 value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									<logic:iterate id="v" name="s" offset="1">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
