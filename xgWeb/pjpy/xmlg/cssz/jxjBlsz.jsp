<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript"
	src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<script type="text/javascript">
<!--
		//控制对于页面只有一个文本框,回车提交事件 重写onkeydown事件,注意会覆盖所有的回车事件
		function document.onkeydown() {     
			  var e=event.srcElement;     
			  if(event.keyCode==13) {
				  	return false;     
			  }     
		} 
        //END
		function chkxy(){
				var ischecked = document.getElementById("chkallxy").checked;
				var chkoneList = document.getElementsByName("chkonexy");
				
				for (var i=0;i<chkoneList.length;i++) {
					chkoneList[i].checked = ischecked;
				}
			}
			function chknj() {
				var ischecked = document.getElementById("chkallnj").checked;
				var chkoneList = document.getElementsByName("chkonenj");
				
				for (var i=0;i<chkoneList.length;i++) {
					chkoneList[i].checked = ischecked;
				}
			}
			
			function addJxjblszxx() {
				var dm = document.getElementById("dm").value;
				var bl = document.getElementById("bl").value;

				var chkoneList = document.getElementsByName("chkonexy");				
				var chknjoneList = document.getElementsByName("chkonenj");	
				var xyLen = 0;
				var njLen = 0;
				for (var i=0;i<chkoneList.length;i++) {
					if (chkoneList[i].checked==true) {
						xyLen = xyLen + 1;
					}
				}
				for (var i=0;i<chknjoneList.length;i++) {
					if (chknjoneList[i].checked==true) {
						njLen = njLen + 1;
					}
				}
				if (dm==null||bl==null||dm==''||bl==''||xyLen <= 0 || njLen <= 0 ) {
					alert("带*号字段为必填项!");
					return false;
				} else {
					if (parseFloat(bl) == 0 ) {
						alert("比例设置不能为0!");
						return false;
					} else if (parseFloat(bl) >= 100) {
						alert("比例设置应控制在100以内!");
						return false;
					}
					if ($("pt")) {
						BatAlert.showTips('正在保存数据，请等待...');
					}
					saveinfo('pjpy_xmlg_jxjBlsz.do?operType=save','');
				}
			}	
//-->
</script>
<body onload="checkWinType();">
	<html:form action="/xmlgpjpy" method="post">
		<input type="hidden" name="pt" id="pt"/>
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置:评奖评优 - 参数设置 - 奖学金比例设置
			</div>
		</div>
		<table style="width:100%" class="tbstyle">
			<thead>
				<tr>
					<td colspan="2" align="center">
						批量设置比例
					</td>
				</tr>
			</thead>
			<tr style="width:22px">
				<td align="right" width="13%">
					学年：
				</td>
				<td align="left">
					${xn }
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					奖学金：
				</td>
				<td align="left">
					<html:select property="dm" styleId="dm">
								<html:option value=""></html:option>
								<html:options collection="jxjList" property="dm" labelProperty="mc"/>
							</html:select><font color="red">*</font>
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					<input type="checkbox" id="chkallxy" name="chkallxy" onclick="chkxy()" style="CURSOR: hand;"/><B> 全选:</B>&nbsp;<font color="red">*</font>
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
			<tr style="width:22px">
				<td align="right">
					年级：
				</td>
				<td align="left">
					<input type="checkbox" id="chkallnj" name="chkallnj" onclick="chknj()" style="CURSOR: hand;"/> <b>全选:</b>&nbsp;<font color="red">*</font><br>
					<logic:notEmpty name="njList">
						<logic:iterate id="njV" name="njList" >
							<input type="checkbox" id="chkonenj" name="chkonenj" value="${njV.nj }" style="CURSOR: hand;"/>${njV.nj }
						</logic:iterate>
					</logic:notEmpty>
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					专业：
				</td>
				<td align="left">
					全部
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					班级：
				</td>
				<td align="left">
					全部
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					获奖比例：
				</td>
				<td align="left">
					<html:text property="bl" styleId="bl" style="width:90px" onkeyup="ckinpdata(this)" maxlength="4" onkeypress="if(event.keyCode == 13){return}"></html:text>
					<font color="red">% (键入1-100之间的数字)</font><font color="red">*</font>
				</td>
			</tr>
		</table>

		<div class="buttontool" align="center">
				<button type="button" class="button2" id="btn_save"
					onclick="addJxjblszxx()"
					style="width:80px">
					保 存
				</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2" id="btn_close" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
				style="width:80px" id="buttonClose">
				关 闭
			</button>
		</div>
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert("操作成功!");
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert("操作失败,原因可能是数据库中已存在相同记录!");
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>
