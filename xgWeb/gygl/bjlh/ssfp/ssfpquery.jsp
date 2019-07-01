<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type='text/javascript'
	src='/xgxt/dwr/interface/getBjlhGyglDAO.js'></script>
<script language="javascript" src="js/gygl/bjlh/bjlhFunction.js"></script>
<script type="text/javascript" src="gygl/bjlh/ssfp/ssfp.js"></script>
<script type="text/javascript">
<!--
function dispXylist() {
    	var fpbj = document.getElementById('fbbj').value;
    	if (fpbj!='qrz' && fpbj != '') {
    		document.getElementById('xy').selectedIndex = 0;
    		document.getElementById('xy').disabled = true;
    	} else {
    		document.getElementById('xy').disabled = false;
    	}
    }
    
    function bjlhLdList(){
	var xqdm = $("xqdm").value;
	var objId = "lddm";
	var xbxd = $("xb").value;
	getBjlhGyglDAO.getxbXqLdList("ld",xqdm,"",xbxd,function(data){
		if(data !=null && data.length >0){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value="";
		}
	});
}
//-->
</script>
<body onload="xyDisabled('xy');dispXylist();">
	<html:form action="/bjlh_ssfp.do" method="post">
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<input type="hidden" name="operType" value="query"/>
		<input type="hidden" name="tableName" value="${tableName }"/>
		<input type="hidden" name="realTable" value="${realTable }"/>
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<input type="hidden" name="pt" id="pt"/>
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置: 北京联合公寓管理 - 宿舍划分结果查询
			</div>
		</div>
		<fieldset>
			<legend>
				条件选择
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="" nowrap="nowrap">
							校&nbsp;&nbsp;区&nbsp;&nbsp;名：
								<html:select property="xqdm" style="" styleId="xqdm"
								onchange="setLdList()">
								<html:options collection="xqList" property="dm"
									labelProperty="mc" />
							</html:select>
								&nbsp;性别限定：
								<html:select property="xb" styleId="xb" 
									onchange="if($('xqdm').value==''){}else{bjlhLdList()}">
									<html:option value="">--请选择--</html:option>
									<html:option value="男">男</html:option>
									<html:option value="女">女</html:option>
									<html:option value="混合">混合</html:option>
								</html:select>
						</td>
						<td width="10" rowspan="3" align="center" valign="middle">
							<button class="button2" style="height:40px" id="search_go"
								onclick="refreshForm('')">
								查询
							</button>
						</td>
					</tr>
					<tr>
						<td>
							楼&nbsp;&nbsp;栋&nbsp;&nbsp;名：
								<html:select property="lddm" style="" styleId="lddm"
								onchange="setXqList();setCsList();setQsList();">
								<html:options collection="ldList" property="dm"
									labelProperty="mc" />
							</html:select>
								&nbsp;	
								层号：
						           <html:select property="cs" style="" styleId="cs"
								onchange="setQsList();">
								<html:options collection="csList" property="dm"
									labelProperty="mc" />
							</html:select>
							&nbsp;
							寝室号：
							<html:select property="qsh" style="" styleId="qsh" onchange="">
								<html:options collection="qsList" property="dm"
									labelProperty="mc" />
							</html:select>		
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
						分配标记：
								<html:select property="fbbj" styleId="fbbj"
								onchange="dispXylist();">
								<html:option value=""></html:option>
								<html:options collection="fpbjList" property="en"
									labelProperty="cn" />
							</html:select>
								&nbsp;
							<bean:message key="lable.xsgzyxpzxy" />：
							<html:select property="xydm" style="width:175px"
								styleClass="select" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp;&nbsp;检测数据异常：
							<html:checkbox property="sfyc"></html:checkbox>
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
						${rsNum}
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td nowrap>
									<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand;"
								align="center" >
								<td>
									<input type="checkbox" id="cbv" name="cbv"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					<TABLE width="99%" id="rsTable1" class="tbstyle">
											<TR>
												<TD height=3></TD>
											</TR>
											<TR>
												<TD>
													<jsp:include flush="true"
														page="/sjcz/turnpage.jsp?form=bjlhGyglForm"></jsp:include>
												</TD>
											</TR>
											<TR>
												<TD height=3></TD>
											</TR>
										</TABLE>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button class="button2" id="btn_sc"
								onclick="deldata('bjlh_gygl_deleteSsfp.do')"
								style="width:80px">
								删除
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="btn_dr"
								onclick="impAndChkData()"
								style="width:80px">
								导入
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="btn_dc"
								onclick="dataExport()"
								style="width:80px">
								导出
							</button>
						</div>
					</center>
				</logic:equal>
			<div id="tmpdiv"></div>
	</html:form>
		<logic:equal value="yes" name="writeAble" scope="request">
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
		</logic:equal>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert('操作成功!');
				</script>
			</logic:equal>
		</logic:present>
</body>
