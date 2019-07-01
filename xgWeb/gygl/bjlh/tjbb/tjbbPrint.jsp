<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cpzZjcmDAO.js'></script>
	<script type="text/javascript">	
		function printBb(){
			var bblx = $("bblx").value;
			//var xydm = $("xydm").value;
			var lddm = $("lddm").value;
			
			var bynd = $("byny").value;
			
			if(bblx == ""){
				alert("请确定报表类型！！");
				return false;
			}
			
			var chkoneList = document.getElementsByName("chkonexy");	
			var xyLen = 0;
				for (var i=0;i<chkoneList.length;i++) {
					if (chkoneList[i].checked==true) {
						xyLen = xyLen + 1;
					}
				}
			if (xyLen <= 0 ) {
				alert("请至少选择一个<bean:message key="lable.xsgzyxpzxy" />(部门)!");
				return false;
			}
			if ((bblx=='cwfp表' && bynd == null) || (bblx=='cwfp' && bynd == '')) {
				alert("请选择毕业年度!");
				return false;
			}
			
			var url = "/xgxt/bjlh_gybb.do?method=tjbbPrint&doType=print";
			
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			//window.open(url);
		}
		
		function chLx(value){
			if(value == "cwfp"){
				document.getElementById('bytr').style.display = "";
			}else{
				document.getElementById('bytr').style.display = "none";
			}
		}
		
		function chkxy(){
				var ischecked = document.getElementById("chkallxy").checked;
				var chkoneList = document.getElementsByName("chkonexy");
				
				for (var i=0;i<chkoneList.length;i++) {
					chkoneList[i].checked = ischecked;
				}
			}
	</script>
	<body onload="">
		<html:form action="/bjlh_gybb">
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="ndV" id="ndV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="right"
				style="width: 100%">
				<thead>
					<tr>
						<td colspan="2" align="center">
							基本信息统计
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
<%--						<html:select property="xydm"--%>
<%--						styleClass="select" style="" styleId="xy">--%>
<%--							<html:option value=""></html:option>--%>
<%--							<html:options collection="xyList" property="xydm"--%>
<%--								labelProperty="xymc" />--%>
<%--						</html:select>--%>
						<input type="checkbox" id="chkallxy" name="chkallxy" onclick="chkxy()" style="CURSOR: hand;"/><B> 全选:</B>
					<logic:notEmpty name="xyList">
						<table border="0" cellpadding="0" cellspacing="0" >   
                               <logic:iterate   name="xyList"   id="xyV"   indexId="index">   
                                     <%if((index.intValue()+1)%5==1){%>   
                                        
                                      <tr   class="alt">     
                                      <%}%>                                         
                                          <td nowrap="nowrap">
                                          		<input type="checkbox" id="chkonexy" name="chkonexy"
												value="${xyV.xydm }" style="CURSOR: hand;" />${xyV.xymc }
                                          </td>   
                              			<%if((index.intValue()+1)%5==0){%>   
                                      </tr>     
                                      <%}%>                 
                                      </logic:iterate>   
                                      </table>
					</logic:notEmpty>
		
					</td>
				</tr>
				<tr>
					<td align="right">
					 	楼栋名:
					</td>
					<td>
						<html:select property="lddm" onchange="" styleId="lddm">
							<html:option value=""></html:option>
							<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
					 	报表类型：
					</td>
					<td>
						<html:select property="bblx" style="" onchange="chLx(this.value)" styleId="bblx">
<%--							<html:option value=""></html:option>--%>
							<html:option value="ssqk">宿舍基本情况统计表</html:option>
							<html:option value="cwfp">可分配床位数统计表</html:option>
						</html:select>
					</td>
				</tr>
				<tr style="display:none" id="bytr">
					<td align="right">
					 	毕业年度：
					</td>
					<td>
						<html:select property="byny" style="" styleId="byny">
							<html:option value=""></html:option>
							<html:options collection="byndList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="2">
					<button class="button2" id="buttonSave" onclick="printBb()"
						style="width: 80px">
						导出Excel
					</button>
				</tr>
			</table>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
