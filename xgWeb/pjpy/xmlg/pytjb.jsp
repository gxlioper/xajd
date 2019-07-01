<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript">
   function gsdExp(){
        var xydm = $("xydm").value;
        var zydm = $("zydm").value;
        var bjdm = $("bjdm").value;
        if(xydm==""&&zydm==""&&bjdm==""){
           alert("请选择条件！");
           return false;
        }
    document.forms[0].action = "/xgxt/xmlgpjpy.do?method=pytjbdc";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
   }   
</script>
<body >
	<html:form action="/xmlgpjpy.do" method="post">
			<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置: 评奖评优 - 审核 - 名单输出-荣誉称号公示名单输出
			</div>
		</div>
		<fieldset>
			<legend>
				条件选择
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="right" >
							学年：
						</td>
						<td align="left" >
							<bean:write name="pjpyXmlgActionForm" property="xn"/>	
						</td>
					</tr>
					
					<tr>
						<td align="right" >
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left" >
							<html:select property="xydm" style="width:175px"
								styleClass="select" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>												
						</td>
					</tr>	
					<tr>
						<td align="right" >
							专业：
						</td>
						<td align="left" >
							<html:select property="zydm" style="width:175px"
								styleClass="select" styleId="zy"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>												
						</td>
					</tr>	
					<tr>
						<td align="right" >
							班级：
						</td>
						<td align="left" >
							<html:select property="bjdm" style="width:175px"
								styleClass="select" styleId="bj"
								>
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>												
						</td>
					</tr>				
				</thead>					
			</table>
			<center>
			<div class="buttontool" id="btn"	 width="100%">
				<button class="button2" onclick="gsdExp()">
					导出Excel
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;">
					关闭
				</button>
			</div>
		</center>
		</fieldset>
	</html:form>
</body>
