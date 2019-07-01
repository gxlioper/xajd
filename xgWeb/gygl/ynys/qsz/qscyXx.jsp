<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script type="text/javascript">
		var url=""
		function saveQsz(url){
			if (confirm("确定进行该操作吗?")) {
				document.forms[0].action=url;
	  			document.forms[0].submit();
			}
		}
		
		</script>
	</head>


	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理-信息维护-寝室长</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="ynysQsz" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="realTable" id="realTable" value="view_sjxy_dekthdqh" />
			<input type="hidden" name="rsNum" ud="rsNum" value="${rsNum}" styleId="rsNum"/>
			<div class="toolbox">
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">该寝室没有入住的学生！</font>
							</logic:empty>
							<logic:notEmpty name="rs">
								<font color="blue">提示：单击表头可以排序</font>
							</logic:notEmpty>	
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()"/>									
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" >
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);"
									 style="cursor:hand">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="primarykey_cbv" id="pkV" 
												   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
												   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>/>
												   <input type="hidden" name="checkVal" id="checkVal" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									
								</tr>
							</logic:iterate>
							<!--内容 end-->
							<tfoot>
								<td colspan="7" align="right">
									<div class="btn">
										<logic:notEqual name="rsNum" value="0">
											<button id="buttonSave" 
												onclick="saveQsz('ynysQsz.do?method=qscyXx&doType=save')"
												style="width: 80px">
												保	存
											</button>
										</logic:notEqual>
										&nbsp;&nbsp;
										<button id="buttonClose" onclick="Close();return false;"
											style="width: 80px">
											关	闭
										</button>
									</div>
								</td>
							</tfoot>
						</table>
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	
			

		<logic:present name="result">
		<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:notEqual>
		<script>
				
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
		</html:form>
	</body>
</html>
