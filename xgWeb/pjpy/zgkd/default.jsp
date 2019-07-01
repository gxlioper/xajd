<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript"
	src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zgkd/zgkd.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
<!--  <script type="text/javascript">
	var v = 0;//用于判断用户是否是第一次点击排序,第一次为升序,第二次为降序
	var col = -1;//辅助参数,用于判断用户是否是第一次点击排序
	//id:表ID, flag:是否带表头排序, cellposition: 行号位置, obj:this
	function keepRowNumber(id,flag,cellposition,obj){
		if(obj.cellIndex != col){//第一次排序
			col = obj.cellIndex;
			v = 1;
		}else{//非第一次排序
			col = -1;
			v = 0;
		}
		if(cellposition != obj.cellIndex){
			var trobj = $(id).getElementsByTagName('tr');//获取所有的行对象
			var num;
			if(flag){//带表头排序
				num = trobj.length-1;
				for(var i=1;i<trobj.length;i++){
					if(v == 1){	//第一次排序为升序,显示行号为降序				
						trobj[i].cells[cellposition].innerText = num;
						num--;	
					}else{					
						trobj[i].cells[cellposition].innerText = i;
					}
									
				}
			}else{//不带表头排序
				num = trobj.length;
				for(var i=0;i<trobj.length;i++){
					trobj[i].cells[cellposition].innerText = i+1;
				}
			}
		}	
	}
</script>   -->
<base target="_self">
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyzgkdwh" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 - 信息维护 - 素质测评
			</div>
		</div>
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="userType" name="userType"
			value="${userType }" />
		<input type="hidden" id="tableName" name="tableName"
			value="${tableName}" />
		<input type="hidden" id="realTable" name="realTable"
			value="${realTable}" />
		<input type="hidden" id="failInfo" name="failInfo"
			value="${failinfo }" />
		<input type="hidden" id="isFdy" name="isFdy" value="${isFdy}"/>
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<!-- 批量删除信息提示 -->
		<input type="hidden" id="pt" />
		<fieldset>
			<legend>
				条件选择
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							年级：
							<html:select property="nj" styleId="nj" style="width:90px"
								onchange="initZyList();initBjList()" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp; 学年：
							<html:select property="xn" style="width:90px" styleClass="select"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp; 学号:
							<html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:110px"></html:text>
							&nbsp;&nbsp; 姓名:
							<html:text property="xm" styleId="xm" styleClass="inputtext"
								style="width:110px"></html:text>
							&nbsp;&nbsp; 素质等级:
							<html:select property="szdj" styleId="szdj" styleClass="select"
								style="width:80px">
								<html:option value=""></html:option>
								<html:options collection="szdjList" property="szdjdm"
									labelProperty="szdjmc" />
							</html:select>
						</td>
						<td width="10" rowspan="2" align="center" valign="middle">
							<input type="hidden" name="act" value="go" />
							<button class="button2" style="height:40px" id="search_go"
								onclick="dataQryChk('pjpy_zgkd_zhszcpwh.do');">
								查询
							</button>
							
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							<bean:message key="lable.xsgzyxpzxy" />：
							<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:150px" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							专业：
							<html:select property="zydm" onchange="initBjList()"
								style="width:170px" styleClass="select" styleId="zy">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							班级：
							<html:select property="bjdm" style="width:170px"
								styleClass="select" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
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
							记录数： ${rsNum} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：双击一行可以查看详细信息;单击表头可以进行排序;${pm }</font>
						</legend>
						<table width="99%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this);keepRowNumber('rsTable',true,1,this);" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;"
									ondblclick="modiAndDel('pjpy_zgkd_zhszcpview.do?act=view&pkValue=','modi','630','350')">
									<td align=center nowrap>
										<input type="checkbox" id="cbv" name="cbv"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align=center nowrap>
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
												page="/sjcz/turnpage.jsp?form=pjpyZgkdZhszcpActionForm"></jsp:include>
										</TD>
									</TR>
									<TR>
										<TD height=3></TD>
									</TR>
								</TABLE>
					</fieldset>
				</logic:notEmpty>
				<logic:equal value="yes" name="writeAble" scope="request">
					<div class="buttontool" align="center" id="btn"
						style="position:absolute;width:95%;top:100px">
						<button class="button2" style="" id="search_go"
								onclick="showTopWin('pjpy_zgkd_pmfssz.do',440,220)">
								排名方式设置
							</button>
						&nbsp;&nbsp;&nbsp;	
						<button class="button2" id="btn_add" style="width:80px"
							onclick="showTopWin('pjpy_zgkd_szcpadd.do','630','350')">
							增加
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2" id="btn_modi" style="width:80px"
							onclick="modiAndDel('pjpy_zgkd_zhszcpview.do?pkValue=','modi','630','350')">
							修改
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2" id="btn_del" style="width:80px"
							onclick="deldata('pjpy_zgkd_zhszcpwh.do?act=del')">
							删除
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="impAndChkData();"
							style="width:80px">
							导入数据
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="dataExport()" style="width:80px">
							导出数据
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="szcptj()" style="width:80px">
							汇总统计
						</button>
						&nbsp;&nbsp;&nbsp;
						<a href="xlsDown/zgkd_zhszcpb.xls" target="_blank">模板下载</a>
					</div>
				</logic:equal>
				<div id="tmpdiv"></div>
			</div>
		</div>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
	<logic:present name="deleted">
		<logic:equal value="yes" name="deleted">
			<script>
	 			alert('操作成功！');
	 			document.getElementById('search_go').onclick();
	 		</script>
		</logic:equal>
		<logic:equal value="no" name="deleted">
			<script>
	 			alert('' + document.getElementById('failInfo').value());
	 			document.getElementById('search_go').onclick();
	 		</script>
		</logic:equal>
	</logic:present>
</body>
