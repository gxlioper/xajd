<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
<script type='text/javascript' src='/xgxt/dwr/interface/jxglJhzyDAO.js'></script>
<script type="text/javascript" src="js/jxglFunction.js"></script>
<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
<script type="text/javascript">
	function chBzList(jxnd){
		jxglJhzyDAO.getLdList(jxnd,function(data){
		if (data != null && typeof data == 'object') {
			var objId = "lddm";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"bzdm","bzmc");		
				$(objId).options[0].value = "";
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
	}
	function delZz(){
		if(curr_row == null){
			alert('请选择要删除的数据');
			return false;
		}
		showTips('处理数据中，请等待......');
		refreshForm('jxgl_jhzy.do?method=zzldManage&doType=del&pkValue='+curr_row.getElementsByTagName('input')[0].value);
	}
	
	function printZz(){
		document.forms[0].action = "/xgxt/jxgl_jhzy.do?method=zzldUpdate&doType=print";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
</script>
<body>
	<html:form action="/jxgl_jhzy" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		当前位置：<bean:write name="title"/>
       		</div>
    	</div>
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="userType" name="userType" value="${userType}"/>
    	<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
    	<input type="hidden" id="realTable" name="realTable" value="${realTable}"/>
    	<input type="hidden" id="tmp" name="tmp" value="${tmp }"/>
    	<input type="hidden" name="pkstring" value="" />
    	<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
    	<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>" />
    	<!-- 批量删除信息提示 -->
    	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo}"/>
    	<fieldset>
				<legend>
					条件选择
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								&nbsp;军训年级：
									<html:select property="nj" styleId="nj" onchange="chBzList(this.value)">
									<html:options collection="njList" labelProperty="njmc" property="njdm"/>
									</html:select>
								&nbsp;已建制连队：
									<html:select property="lddm" style="" styleId="lddm">
										<html:options collection="ldList" property="bzdm"
											labelProperty="bzmc" />
									</html:select>
								&nbsp;组织名称：
									<html:text property="zzmc" styleId="zzmc" maxlength="10"/>					
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="" id="search_go"
									onclick="refreshForm('jxgl_jhzy.do?method=zzldManage&go=go');">
									查询
								</button>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<div id="result">
				<div class="searchcontent">
					<logic:empty name="rs">
						<p align="center">
							未找到任何记录！
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								记录数：
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：单击表头可以进行排序;双击可查看详细</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											序号
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this)" style="cursor: hand;"
									ondblclick="modiTab('jxgl_jhzy.do?method=zzldUpdate&doType=view&pkValue=', '650', '500')">
									<td align=center>
										${index+1}
										<input type = "hidden" id="pk" name="pk" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
										<input type="checkbox" id="checkVal" name="checkVal" style="display:none"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble" scope="request">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
							<button type="button" class="button2" id="btn_add" style="width:80px"
								onclick="addTab('jxgl_jhzy.do?method=zzldUpdate&doType=add', '650', '500')">
								增加
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_modi" style="width:80px"
								onclick="if(curr_row == null){alert('请选择要修改的数据');return false;}modiTab('jxgl_jhzy.do?method=zzldUpdate&doType=update&pkValue=', '650', '500')">
								修改
							</button>		
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_modi" style="width:80px"
								onclick="delZz()">
								删除
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_modi" style="width:80px"
								onclick="printZz()">
								打印
							</button>					
						</div>
					</logic:equal>
					<div id="tmpdiv"></div>
				</div>
			</div>
	</html:form>
	 <script type="text/javascript" src="js/bottomButton.js"></script>
	 <!-- 操作提示 -->
	 <jsp:include flush="true" page="/syscommon/deleteprompt.jsp"></jsp:include>
</body>