<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript">	  
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
			
	    function clearData() {
	    	document.getElementById('cj').value='';
				var chkoneList = document.getElementsByName("chkonexy");
				
				for (var i=0;i<chkoneList.length;i++) {
					chkoneList[i].checked = false;
				}
	    }
	</script>
<body>
	<html:form action="/czxxPjpyCssz" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 - 参数设置 - 荣誉获得条件设置
			</div>
		</div>
		<div class="rightcontent">
			<fieldset>
				<legend>
					获荣誉称号的基础条件设置
				</legend>
				<table width="95%" class="tbstyle" align="center">
					<thead>
						<tr align="center">
							<td align="right" width="30%">
								德育成绩：
							</td>
							<td align="left" width="65%">
								<html:text property="cj" styleId="cj" maxlength="4"
									style="width:60px" onkeyup="ckinpdata(this)"></html:text>
								分以上
							</td>
						</tr>
						<tr>
							<td align="right">
								本学期所获奖学金（或关系）：
							</td>
							<td align="left">
								<input type="checkbox" id="chkallxy" name="chkallxy"
									onclick="chkxy()" style="CURSOR: hand;" />
								<B> 全选:</B>
								<logic:notEmpty name="dmList">
									<table border="0" cellpadding="0" cellspacing="0">
										<logic:iterate name="dmList" id="xyV" indexId="index">
											<%
											if ((index.intValue() + 1) % 3 == 1) {
											%>

											<tr class="alt">
												<%
												}
												%>
												<td nowrap="nowrap">
													<input type="checkbox" id="chkonexy" name="chkonexy"
														value="${xyV.dm }" style="CURSOR: hand;" ${xyV.chk } />
													${xyV.mc }
												</td>
												<%
												if ((index.intValue() + 1) % 3 == 0) {
												%>
											</tr>
											<%
											}
											%>
										</logic:iterate>
									</table>
								</logic:notEmpty>
							</td>
						</tr>
					</thead>
					<logic:equal value="yes" name="writeAble">
						<tr height="35">
							<td align="center" colspan="2">
								<button type="button" class="button2" onclick="refreshForm('pjpy_czxx_rytjsz.do?act=save')"
									style="width: 60px">
									保 存
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2" onclick="clearData()" style="width: 60px">
									重 置
								</button>
							</td>
						</tr>
					</logic:equal>
				</table>
			</fieldset>

		</div>
	</html:form>
	<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
	<center>

	</center>
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
</body>
