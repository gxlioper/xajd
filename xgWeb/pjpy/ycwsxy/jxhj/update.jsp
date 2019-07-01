<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyycwswh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		当前所在位置：评奖评优 - 信息维护 - 军训获奖
       		</div>
    	</div>
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="act" name="act" value="save"/>
    	<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }"/>
    	<input type="hidden" id="userType" name="userType" value="${userType }"/>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							单个修改
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right">
						年级:
					</td>
					<td align="left">
						<html:select property="nj" styleId="nj" style="width:90px"
							onchange="initZyList();initBjList()" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />:
					</td>
					<td align="left">
						<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
						专业:
					</td>
					<td align="left">
						<html:select property="zydm" onchange="initBjList()" style="width:180px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>班级:
					</td>
					<td align="left">
						<html:select property="bjdm" style="width:180px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
						<font color="red">*</font>学年:
					</td>
					<td align="left">
						<html:select property="xn" style="width:90px" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>军训获奖项目:
					</td>
					<td align="left">
						<html:select property="jxhjxm" styleClass="select" style="width:80px" styleId="jxhjxm">
								<html:option value=""></html:option>
									<html:options collection="jxjxdmList" property="dm" labelProperty="mc"/>
								</html:select>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
						获奖明细:
					</td>
					<td align="left" colspan="3">
						<html:textarea property="hjmx" styleId="hjmx" style="width:95%" rows="5" ></html:textarea>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
						备注:
					</td >
					<td align="left" colspan="3">
						<html:textarea property="bz" styleId="bz" style="width:95%" rows="5"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
			<logic:notEqual value="view" name="act">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_ycws_modijxhjwh.do','bj-xn-jxhjxm');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>	
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>