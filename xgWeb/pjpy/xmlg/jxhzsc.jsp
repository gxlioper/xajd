<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<script type="text/javascript">
<!--
	//加载奖学金选择框值
	function loadjxjxx() {
		var jxjmc = document.getElementById('jxjmc').value;
		if (jxjmc == null || jxjmc == '') {
			document.getElementById('jxjmc').value = "请点击选择奖学金信息!";
		} 
	}
	//显示奖学金选择列表层
	function showJxjxx() {
		var jxjmc = document.getElementById('jxjmc').value;
		if (jxjmc == '请点击选择奖学金信息!') {
			document.getElementById('jxjmc').value = "";
		} 
		var items = document.getElementById("items");
		items.style.left = (screen.availwidth)/6;
		items.style.top = ((screen.availheight)/6);
		items.style.display = "block";
	}
	//全选奖学金
	function chkxy(){
				var ischecked = document.getElementById("chkallxy").checked;
				var chkoneList = document.getElementsByName("chkonexy");
				for (var i=0;i<chkoneList.length;i++) {
					chkoneList[i].checked = ischecked;
				}
			}
	//点击确定事件
	function changeJxj() {
		//将选择的奖学金赋值到文本框
		var chkoneList = document.getElementsByName("chkonexy");
		var jxjdm = "";
		var jxjmc = "";
		var jdm = "";
		var jmc = "";
		for (var i=0;i<chkoneList.length;i++) {
			var value = chkoneList[i].value;
			if (chkoneList[i].checked==true) {
				jxjdm = jxjdm += value.substr(0,value.indexOf("!@")+2);
				jxjmc = jxjmc += value.substr(value.indexOf("!@")+2,value.length-value.indexOf("!@")-2) + ",";
				document.getElementById('jxjdm').value= jxjdm;
				document.getElementById('jxjmc').value= jxjmc;
			} else {
				jdm = value.substr(0,value.indexOf("!@")+2);
				jmc = value.substr(value.indexOf("!@")+2,value.length-value.indexOf("!@")-2) + ",";
				document.getElementById('jxjdm').value= document.getElementById("jxjdm").value.replace(jdm,'');
				document.getElementById('jxjmc').value= document.getElementById("jxjmc").value.replace(jmc,'');
			}
		}
		if (jxjmc == null || jxjmc == '') {
			document.getElementById('jxjmc').value = "请点击选择奖学金信息!";
		} 	
		var items = document.getElementById("items");
		items.style.left = (screen.availwidth)/6;
		items.style.top = ((screen.availheight)/6);
		items.style.display = "none";
	}
	//关闭事件
	function closeDiv() {
		var jxjmc = document.getElementById('jxjmc').value;
		if (jxjmc == null || jxjmc == '') {
			document.getElementById('jxjmc').value = "请点击选择奖学金信息!";
		} 
		document.getElementById('items').style.display='none'	
	}
	//工资单输出汇总
	function outputJxjje() {
		var jxjmc = document.getElementById('jxjmc').value;
		if (jxjmc == null || jxjmc == '' || jxjmc=='请点击选择奖学金信息!') {
			alert("请选择要汇总输出的奖学金!");
			return false;
		} 
		var xn = document.getElementById('xn').value;
		var jxjdm = document.getElementById('jxjdm').value;
		var xydm = document.getElementById('xydm').value;
		
		if (xydm == null || xydm == '') {
			if (confirm("未选择任何<bean:message key="lable.xsgzyxpzxy" />,将累计输出所有<bean:message key="lable.xsgzyxpzxy" />的奖学金工资单数据,要继续吗?")) {
				window.open('pjpy_xmlg_jxjjehzsc.do?xn=' + xn + '&jxjdm=' + jxjdm +'&xydm=' + xydm);
			}
		} else {
			window.open('pjpy_xmlg_jxjjehzsc.do?xn=' + xn + '&jxjdm=' + jxjdm +'&xydm=' + xydm);
		}
	}
//-->
</script>
<body onload="xyDisabled('xy');loadjxjxx();">
	<html:form action="/xmlgpjpy.do" method="post">
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" name="jxjdm" id="jxjdm" />
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置 - 评奖评优 - 审核 - 奖项汇总输出
			</div>
		</div>
		
		<!-- 选择奖学金的列表层 -->
		<div id="items" name="items" style="display:none; position: absolute;background-color: #D0E0EE; width: 330px" >
			<table class="tbstyle" style=" width: 330px">
				<tr>
					<td>
						<input type="checkbox" id="chkallxy" name="chkallxy" onclick="chkxy()" style="CURSOR: hand;"/><B> 全选:</B>
						<logic:notEmpty name="jxjList">
							<table border="0" cellpadding="0" cellspacing="0" >   
	                               <logic:iterate   name="jxjList"   id="xyV"   indexId="index">   
	                                     <%if((index.intValue()+1)%2==1){%>   
	                                        
	                                      <tr   class="alt">     
	                                      <%}%>                                         
	                                          <td nowrap="nowrap" width="165px">
	                                          		<input type="checkbox" id="chkonexy" name="chkonexy"
													value="${xyV.dm }!@${xyV.mc }" style="CURSOR: hand;" />${xyV.mc }
	                                          </td>   
	                              			<%if((index.intValue()+1)%2==0){%>   
	                                      </tr>     
	                                      <%}%>                 
	                                      </logic:iterate>   
	                                      </table>
						</logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td align="center">
						<button  style="height: 19px" class="button2" onclick="changeJxj()">确 定</button>
						&nbsp;&nbsp;
						<button  style="height: 19px" class="button2"
						onclick="closeDiv()">关 闭</button>
					</td>
				</tr>
			</table>
		</div>
		
		<fieldset>
			<legend>
				条件选择
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="" nowrap="nowrap">
							学年：
							<html:select property="xn" styleId="xn"
								styleClass="select">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;
							<bean:message key="lable.xsgzyxpzxy" />：
							<html:select property="xydm" style="width:175px"
								styleClass="select" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp; 奖学金类别：
							<html:select property="lbdm" styleId="lbdm"
								onchange="refreshForm('pjpy_xmlg_jxhzsc.do')">
								<html:option value=""></html:option>
								<html:options collection="jxjlbList" property="dm"
									labelProperty="mc" />
							</html:select>
							&nbsp; 奖学金：
							<html:text property="jxjmc" styleId="jxjmc" 
								onclick="showJxjxx()" readonly="true" style="width:270px;cursor:hand"></html:text>
						</td></tr>
				</thead>
			</table>
		</fieldset>
		<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
		<center>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<button class="button2" id="btn_sc" onclick="outputJxjje()">
					奖学金工资单输出
				</button>
			</div>
		</center>
		<div id="tmpdiv"></div>
	</html:form>
	<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
</body>
