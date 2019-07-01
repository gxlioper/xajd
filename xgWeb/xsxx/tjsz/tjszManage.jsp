<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		function saveTjsz(){
		
			var rsNum = $("rsNum").value;
			
			if(rsNum != "" && rsNum != null){
			
				if (confirm("是否保存所设置的统计项？")) {
				
					for(var i=0;i<rsNum;i++){
					
						var zdId = "zd"+i;
						var numId = "num"+i;
						var num = $(numId).value;
						
						for(var j=0;j<num;j++){
						
							var chId = "ch"+i+j;
							var mcId = "mc"+i+j;
							
							if($(chId) && $(chId).checked){
								var zd = document.createElement("input");
								zd.type = "hidden";
								zd.name = "tjzd";
								zd.value = $(zdId).value;
								document.forms[0].appendChild(zd);
								
								var zdz = document.createElement("input");
								zdz.type = "hidden";
								zdz.name = "tjzdz";
								zdz.value = $(chId).value;
								document.forms[0].appendChild(zdz);
								
								var mc = document.createElement("input");
								mc.type = "hidden";
								mc.name = "xsmc";
								mc.value = $(mcId).value;
								document.forms[0].appendChild(mc);
							}
						}
					}
				}
				saveUpdate('/xgxt/xsxx_tjsz.do?method=tjszManage&doType=save','');
			}
		}
		</script>
	</head>

	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/xsxx_tjsz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="rsNum" id="rsNum" value="${rsNum }"/>
			<!-- 隐藏域 end-->
			<!-- 卫生检查情况 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>设置情况</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<logic:iterate name="rsList" id="rs" indexId="rsI">
						<tr style="height: 23px">
							<th align="right" width="30%">
								${rs.zdm }
								<input type="hidden" id="zd${rsI }" value="${rs.zd }"/>
								<input type="hidden" id="num${rsI }" value="${rs.num }"/>
							</th>
							<td align="left" width="">
								<logic:iterate name="rs" property="zdzList" id="zd" indexId="zdzI">
									${zd.zdz }<input type="checkbox" id="ch${rsI }${zdzI }" value="${zd.zdz }"
									<logic:equal name="zd" property="isCheck" value="yes">checked</logic:equal>/>
									<input type="hidden" id="mc${rsI }${zdzI }" value="${zd.xsmc }"/>
								</logic:iterate>
							</td>
						</tr>
					</logic:iterate>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<logic:equal value="yes" name="writeAble">
									<button type="button" id="buttonSave" onclick="saveTjsz()"
										style="width: 80px">
										保	存
									</button>
								</logic:equal>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
