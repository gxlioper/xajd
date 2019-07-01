<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/chgJxjlist.js'></script>
		<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script language="javascript">
	
	function chgJxjlists(xydms) {
		var xydm = document.getElementById(xydms).value;
		chgJxjlist.xyJxjList(xydm,function(data) {
					DWRUtil.removeAllOptions('jxjdm');			
					var o = [{id:'',labelText:''}];
					DWRUtil.addOptions('jxjdm',o,'id','labelText');
					for(var i=0;i<data.length;i++){
						o = [{id:data[i].jxjdm,labelText:data[i].jxjmc}];
					DWRUtil.addOptions('jxjdm',o,'id','labelText');
					}
		});
	}
	function querydata() {
		var xxdm = document.getElementById('xxdm').value;
		var isFdy = document.getElementById('isFdy').value;
		var xmdm = document.getElementById('xmdm').value;
				var bj = document.getElementById('bj').value;
		
			refreshForm('/xgxt/prise_check.do');
		
	}
	function ffbhz(typ) {
		if (typ=='0') {
			if (document.getElementById('jxjdm').value=='') {
				alert('请选择要汇总的奖学金!');
				return;
			}  
			window.open('pjpy_xcxy_jxjffb.do?jxjdm='+document.getElementById('jxjdm').value);	
		} else {
			window.open('pjpy_xcxy_yxxsjxjffb.do');
		}
	}
	function xcxydybb() {
		if($('tjblx').value==''){
			alert('请选择要统计的报表类型');
		}else{
			if($('tjblx').value=='myzyjxjmx'||$('tjblx').value=='myzyjxjhz'||$('tjblx').value=='yxxsjxjffhz'||$('tjblx').value=='yxxsjxjff'){
				if($('xy').value==''){
					alert('该类报表统计必需选择<bean:message key="lable.xsgzyxpzxy" />!');
					return;
				}
			}
			window.open('pjpy_xcxy_yxxsjxjffb.do?xydm='+$('xy').value+'&tjblx='+$('tjblx').value);
		}
	}

</script>
		<script language="javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="js/webPrint.js"></script>
		<script type="text/javascript">
	function chkPriseOnes(url, w, h) {
	var xxdm = "";
	if($('xxdm')){
		xxdm = document.getElementById("xxdm").value;
	}
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 500;
	}	
	if (curr_row == null) {
		alert("请选择要操作的行！");
		return false;
	} else {		
		var val = curr_row.cells[0].getElementsByTagName("input")[0].value;		
		url += val;
		var tab='';
		if ($('realTable')) {
				tab = document.getElementById('realTable').value;
			}
		if((xxdm=='11551' && tab=='xsjxjb') || ((xxdm=='11551') && tab=='xsrychb')){
			url += "&xh=";
			url += curr_row.cells[5].innerText;
		}
		
		//if ((xxdm=='13022' && tab=='xsjxjb') || ((xxdm=='13022') && tab=='xsrychb')) {
					
		//	url += '&jqfpm=';
		//	url += curr_row.cells[0].getElementsByTagName("input")[1].value;
		//	url += '&zhszcpzfpm=';
		//	url += curr_row.cells[0].getElementsByTagName("input")[2].value;
		//}
		if (xxdm=='10628#') {
			url = 'pjpy_xcxy_jxjshone.do?pkVal=';
			url += curr_row.cells[0].getElementsByTagName("input")[0].value;	
		}
		
		showTopWin(url, w, h);
   }
}

		
	function checkQhts() {
			var jxjmc = selText('jxjdm');
			var qhtsjxjmc = '清寒天使奖学金';
			if(qhtsjxjmc==jxjmc){
				refreshForm('/xgxt/nbty_qhtsjxj.do?method=qhtsjxjsh');
			}else{
				refreshForm('/xgxt/prise_check.do')
			}
		}
	</script>
	</head>
	<!-- 长沙民政辅导审核 -->
	<logic:present name="iscsmz">
		<body onload="xyDisabled('xy');chgDisp('dispFlag')">
			<script language="javascript" src="js/function.js"></script>
			<script language="javascript" src="js/pjpyFunction.js"></script>
			<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
			<html:form action="/prise_conf_rs" method="post">
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a><bean:write name="tips" scope="request" />
						</a>
					</p>
				</div>
				
<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="tableName" name="tableName"
					value="<bean:write name="tableName" scope="request"/>" />
				<input type="hidden" id="act" name="act"
					value="<bean:write name="act" scope="request"/>" />
				<input type="hidden" id="realTable" name="realTable"
					value="<bean:write name="realTable" scope="request"/>" />
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="pk" scope="request"/>" />
				<input type="hidden" id="bmlb" name="bmlb" value="xy" />
				<input type="hidden" name="zyV" id="zyV" value="" />
				<input type="hidden" name="bjV" id="bjV" value="" />
				<input type="hidden" name="tableName" id="tableName"
					value="${tableName }" />
				<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
				<input type="hidden" name="userName" id="userName"
					value="<bean:write name="userName" scope="session"/>" />
				<fieldset>
					<legend>
						基本参数
					</legend>
					<
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									学年
									<html:select property="xn" style="width:90px" disabled="true"
										styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									<!-- 广州大学评奖是按学年 -->
									<logic:notEqual value="11078" name="xxdm">
										&nbsp;&nbsp;年度
										<html:select property="nd" style="width:70px" disabled="true"
											styleId="nd">
											<html:options collection="xnList" property="nd"
												labelProperty="nd" />
										</html:select>
									</logic:notEqual>
									<!-- end -->
									<logic:equal value="10827" name="xxdm">
										<logic:equal value="xy" name="userType">
										&nbsp;
										获奖类别
										<html:select property="jxjlb" styleId="jxjlb"
												onchange="refreshForm('/xgxt/prise_check.do')">
												<html:option value="1">院级</html:option>
												<html:option value="2">系级</html:option>
											</html:select>
										</logic:equal>
									</logic:equal>

									&nbsp;&nbsp;奖学金
									<html:select property="xmdm" style="width:170px"
										styleId="jxjdm">
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="jxjdm"
											labelProperty="jxjmc" />
									</html:select>
									&nbsp;&nbsp;显示方式
									<html:select property="dispFlag" style="width:50px"
										styleId="dispFalg" onchange="chgDisp('dispFlag');">
										<html:option value="xydm">系</html:option>
										<html:option value="zydm">专业</html:option>
										<html:option value="bjdm">班级</html:option>
									</html:select>

								</td>
								<logic:notPresent name="iscsmzFdy">
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<input type="hidden" name="tab" id="tab" value="qtjxj" />
										<!-- 长沙民政社会奖学金 -->
										<button type="button" class="button2" style="height:40px" id="search_go"
											onclick="refreshForm('/xgxt/prise_check.do')">
											查询
										</button>
									</td>
								</logic:notPresent>
								<logic:present name="iscsmzFdy">
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<input type="hidden" name="tab" id="tab" value="qtjxj" />
										<!-- 长沙民政社会奖学金 -->
										<button type="button" class="button2" style="height:40px" id="search_go"
											onclick="refreshForm('/xgxt/prise_check.do');">
											查询
										</button>
									</td>
								</logic:present>

							</tr>
							<tr>
								<td align="left" nowrap>
									系
									<html:select property="xydm" style="width:170px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value="">    全部    </html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									<span id="dispZy"> &nbsp;&nbsp;专业 <html:select
											property="zydm" style="width:170px;" styleId="zy"
											onchange="initBjList()">
											<html:option value="">    全部    </html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select> </span>
									<span id="dispBj"> &nbsp;&nbsp;班级 <html:select
											property="bjdm" style="width:150px" styleId="bj">
											<html:option value="">    全部    </html:option>
											<html:options collection="bjList" property="en"
												labelProperty="cn" />
										</html:select> </span> &nbsp;&nbsp;状态
									<html:select property="zt" styleId="zt">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="不通过">不通过</html:option>
										<html:option value="通过">通过</html:option>
									</html:select>
								</td>

							</tr>
							<logic:present name="showhzy">
								<tr>
									<td align="left" nowrap>
										<span id="dispShf">审核否 <html:select property="yesNo">
												<html:option value="">    全部    </html:option>
												<html:options collection="chkList" property="en"
													labelProperty="cn" />
											</html:select> </span>
									</td>
								</tr>
							</logic:present>
							<%--<logic:equal value="yes" name="isAHJG">
							<tr>
								<td align="left" nowrap="nowrap">
									自动审核条件&nbsp;平均分
									<html:text property="pjf" styleId="pjf" styleClass="inputtext;"
										maxlength="4" onblur="ckdata(this)"></html:text>
									&nbsp;&nbsp;&nbsp;&nbsp;所占班级比例
									<html:text property="bjbl" styleId="bjbl"
										styleClass="inputtext;" maxlength="4" 
										onblur="ckdata(this)" ></html:text>(%)
								</td>
							</tr>
						</logic:equal>
					--%>
						</thead>
					</table>
				</fieldset>

				<logic:empty name="rs">
					<p align="center">
						未找到任何记录！
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							记录数
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示双击一行可以查看详细信息，并可以改变审核状态；单击表头可以排序,获奖人数限制为
								${jxjrs } </font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" id="selall" name="selall"
											onclick="selAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="4">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap="nowrap">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" align="center"
									style="cursor:hand;background-color:
					    <logic:iterate id="v" name="s" offset="2" length="1">
					    <bean:write name="v"/>
					    </logic:iterate>
					    "
									ondblclick="chkPriseOnes('/xgxt/priseChkOne.do?act=view&pkVal=',550,550)">
									<td>
										<input type="checkbox" id="checkVal" name="checkVal"
											<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>
											value="<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v"/></logic:iterate>" />
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="3" length="1">
											<input type="hidden" id="keyval" name="keyval"
												value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="7" length="1">
											<input type="hidden" id="xhval" name="xhval"
												value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="4" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="5">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>

				<logic:match value="yes" name="rw" scope="request">
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:notPresent name="iscsmzFdy">
							<button type="button" class="button2" onclick="checkAll('pass')">
								审核通过
							</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="checkAll('nopass')">
								审核不通过
							</button>
						&nbsp;&nbsp;
						<button type="button" class="button2"
								onclick="priseAutoChk('/xgxt/priseAutoCheck.do')">
								自动审核
							</button>
							<logic:notPresent name="cqkjisFdy">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
									onclick="if(confirm('名单将以pdf格式显示，\n打开前请确认您的计算机上安装了可以查看pdf文件的相关软件。\n确定要打开吗？')){chgRight('/xgxt/nameList.do?typ=prise','_blank');document.forms[0].target = '_self';}return false;">
									名单生成
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
									onclick="document.all('tableName').value='view_jxjhz';dataExport()">
									输出汇总
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
									onclick="showTopWin('/xgxt/viewJxjHz.do',750,600)">
									奖金汇总
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
									onclick="document.all('tableName').value='tem_jxjlb';dataExport()">
									人数一览表
								</button>
							</logic:notPresent>
						</logic:notPresent>
						<logic:present name="iscsmzFdy">
							<button type="button" class="button2" onclick="checkAll('pass')">
								审核通过
							</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="checkAll('nopass')">
								审核不通过
							</button>
						</logic:present>
						<logic:present name="showxcxy">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="djbPrint()">
								授奖登记表打印
							</button>
						</logic:present>
					</div>
					<logic:present name="showxcxy">
						<script language="javascript">
				document.getElementById("btn").style.pixelTop = document.body.clientHeight - 55;
				document.getElementById("btn").style.width = "96%";
				window.setInterval("initBTNTool('btn')",1);
				</script>
					</logic:present>
					<logic:notPresent name="showxcxy">
						<script language="javascript">
				document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
				document.getElementById("btn").style.width = "96%";
				window.setInterval("initBTNTool('btn')",1);
				</script>
					</logic:notPresent>
				</logic:match>
				<div id="tmpdiv"></div>
				<logic:present name="autoChk">
					<logic:equal name="autoChk" value="ok" scope="request">
						<script language="javascript">
      alert("自动审核完成！");
	  </script>
					</logic:equal>
					<logic:equal name="autoChk" value="no" scope="request">
						<script language="javascript">
      alert("综合素质测评尚未完全导入，不能自动审核。\n请先检查综合素质测评数据！");
 	  </script>
					</logic:equal>
					<logic:equal name="autoChk" value="ov" scope="request">
						<script language="javascript">
      alert("您所选择审核单位的奖学金总金额超限。\n请先检查设置！");
 	  </script>
					</logic:equal>
				</logic:present>
			</html:form>
			<script language="javascript">
if(document.getElementById("jxjtmp") != null){
	document.getElementById("jxjtmp").style.top = -435;
}
</script>
			<script language="javascript">
     function djbPrint(){
        if(curr_row == null){
            alert('请选择要打印的数据！\n（单击相应的行）');
            return false;
        }else{
            var pkV =  curr_row.getElementsByTagName("input")[1].value;
            var xh = curr_row.getElementsByTagName("input")[2].value;
            var jxjdm = pkV.substring(pkV.indexOf(xh)+xh.length,pkV.length);
            window.open('jxjpsdjb.do?xh='+xh+'&jxjdm='+jxjdm);
        }
     } 
</script>
		</body>
	</logic:present>


	<logic:notPresent name="iscsmz">
		<body onload="xyDisabled('xy');chgDisp('dispFlag')">
			<script language="javascript" src="js/function.js"></script>
			<script language="javascript" src="js/pjpyFunction.js"></script>
			<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
			<html:form action="/prise_conf_rs" method="post">
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a><bean:write name="tips" scope="request" />
						</a>
					</p>
				</div>

				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />

				<logic:equal value="12872" name="xxdm">
					<input type="hidden" id="tableName" name="tableName" value="xsjxjb" />
					<input type="hidden" id="realTable" name="realTable" value="xsjxjb" />
				</logic:equal>
				<logic:notEqual value="12872" name="xxdm">
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
				</logic:notEqual>
				<input type="hidden" id="act" name="act"
					value="<bean:write name="act" scope="request"/>" />

				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="pk" scope="request"/>" />
				<input type="hidden" id="bmlb" name="bmlb" value="xy" />
				<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />
				<input type="hidden" id="jmc" name="jmc" value="${jmc }" />
				<input type="hidden" id="shresult" name="shresult"
					value="${shresult}" />
				<input type="hidden" id="sJxjtg" name="sJxjtg" value="${sJxjtg}" />
				<%--			<input type="hidden" id="isFdy" name="isFdy" value="${isFdy}" />--%>
				<input type="hidden" name="zyV" id="zyV" value="" />
				<input type="hidden" name="bjV" id="bjV" value="" />
				<input type="hidden" name="tableName" id="tableName"
					value="${tableName }" />
				<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
				<input type="hidden" name="userName" id="userName"
					value="<bean:write name="userName" scope="session"/>" />

				<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<logic:notPresent name="showzjjd">
								<logic:match value="yes" name="rw" scope="request">
									<logic:notPresent name="iscsmzFdy">
										<%--武汉理工大学--%>
										<!-- 杭职院 -->
										<logic:equal value="12872" name="xxdm">
											<logic:notEqual value="true" name="isFdy">
												<!-- 辅导员不显示审核按钮 -->
												<li>
													<a href="#" class="btn_shtg" onclick="checkAll('pass')">审核通过</a>
												</li>
												<li>
													<a href="#" class="btn_shbtg" onclick="checkAll('nopass')">审核不通过</a>
												</li>

											</logic:notEqual>
										</logic:equal>

										<!-- 非杭职院 -->
										<logic:notEqual value="12872" name="xxdm">
											<li>
												<a href="#" class="btn_shtg" onclick="shdata()">审核通过</a>
											</li>
											<li>
												<a href="#" class="btn_shbtg" onclick="checkAll('nopass')">审核不通过</a>
											</li>
										</logic:notEqual>

										<logic:equal value="11647" name="xxdm">
											<li>
												<a href="#" class="btn_dy" onclick="pjpyprint()">打印报表</a>
											</li>
										</logic:equal>
										<logic:equal value="13108" name="xxdm">
											<li>
												<a href="#" class="btn_sh"
													onclick="priseAutoChk('/xgxt/priseAutoCheck.do')">自动审核</a>
											</li>
										</logic:equal>
										<logic:equal value="10878" name="xxdm">
											<li>
												<a href="#" class="btn_sh" onclick="ahjgqutosh()">自动审核</a>
											</li>
										</logic:equal>

										<logic:notPresent name="cqkjisFdy">
											<%--武汉理工大学--%>
											<logic:equal value="10497" name="xxdm">
												<li>
													<a href="#" class="btn_dr" onclick="impAndChkData()">导入审核结果</a>
												</li>
												<li>
													<a href="#" class="btn_dr"
														onclick="if(document.getElementById('jxjdm').value==null || document.getElementById('jxjdm').value==''){alert('请选择奖学金!')}else{document.forms[0].target = '_blank';refreshForm('pjpy_whlgdx.do?method=priseExport');document.forms[0].target = '_self';}">导出样式表</a>
												</li>
											</logic:equal>
										</logic:notPresent>
									</logic:notPresent>

									<!-- 长沙民政辅导员 -->
									<logic:present name="iscsmzFdy">
										<li>
											<a href="#" class="btn_shtg" onclick="checkAll('pass')">审核通过</a>
										</li>
										<li>
											<a href="#" class="btn_shbtg" onclick="checkAll('nopass')">审核不通过</a>
										</li>
									</logic:present>

									<!-- 西昌<bean:message key="lable.xsgzyxpzxy" /> -->
									<logic:present name="showxcxy">
										<li>
											<a href="#" class="btn_dy" onclick="djbPrint()">授奖登记表打印</a>
										</li>
										<li>
											<a href="#" class="btn_dy" onclick="pjpytjbdy()">统计表打印</a>
										</li>
									</logic:present>

									<!-- 杭职院 -->
									<logic:present name="showhzy">
										<li>
											<a href="#" class="btn_zj" onclick="plqm('xsjxjb')">批量签名</a>
										</li>
										<li>
											<a href="#" class="btn_dy" onclick="hzyprint()">打印报表</a>
										</li>
										<li>
											<a href="#" class="btn_dy" onclick="zsldprint()">报表连打</a>
										</li>
									</logic:present>
								</logic:match>
							</logic:notPresent>


							<logic:present name="showzjjd">
								<logic:match value="yes" name="rw" scope="request">
									<li>
										<a href="#" class="btn_shtg" onclick="checkAll('pass')">审核通过</a>
									</li>
									<li>
										<a href="#" class="btn_shbtg" onclick="checkAll('nopass')">审核不通过</a>
									</li>
									<li>
										<a href="#" class="btn_zj"
											onclick="if(confirm('名单将以pdf格式显示，\n打开前请确认您的计算机上安装了可以查看pdf文件的相关软件。\n确定要打开吗？')){chgRight('/xgxt/nameList.do?typ=prise','_blank');document.forms[0].target = '_self';}return false;">名单生成</a>
									</li>
									<li>
										<a href="#" class="btn_sh"
											onclick="document.all('tableName').value='view_jxjhz';dataExport()">输出汇总</a>
									</li>
									<logic:notEqual name="xxdm" value="12861">
										<li>
											<a href="#" class="btn_shtg"
												onclick="showTopWin('/xgxt/viewJxjHz.do',750,600)">奖金汇总</a>
										</li>
									</logic:notEqual>
								</logic:match>
							</logic:present>

						</ul>
					</div>
				</div>

				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<logic:present name="showhzy">

											<input type="hidden" name="go" value="a" />
											<button type="button" id="search_go"
												onclick="refreshForm('/xgxt/prise_check.do')">
												查询
											</button>

										</logic:present>
										<logic:notPresent name="showhzy">
											<logic:present name="shownblg">

												<input type="hidden" name="go" value="a" />
												<button type="button" id="search_go"
													onclick="if (document.getElementById('jxjdm').value=='' || document.getElementById('nj').value=='') {alert('查询条件中年级与奖学金为必选，请确认！');return;} else refreshForm('/xgxt/prise_check.do');">
													查询
												</button>

											</logic:present>
											<logic:notPresent name="shownblg">
												<logic:present name="isAHJG">

													<input type="hidden" name="go" value="a" />
													<button type="button" id="search_go"
														onclick="refreshForm('/xgxt/prise_check.do')">
														查询
													</button>

												</logic:present>
												<logic:notPresent name="isAHJG">

													<input type="hidden" name="go" value="a" />
													<input type="hidden" name="tab" id="tab" value="qtjxj" />
													<!-- 长沙民政社会奖学金 -->
													<button type="button" id="search_go" onclick="querydata()">
														查询
													</button>

												</logic:notPresent>
											</logic:notPresent>
										</logic:notPresent>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>

						<tbody>
							<tr>
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" style="width:95px" disabled="true"
										styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>

								<logic:notEqual value="13108" name="xxdm">
									<!-- 广州大学评奖是按学年 -->
									<logic:notEqual value="11078" name="xxdm">

										<th>
											年度
										</th>
										<td>
											<html:select property="nd" style="width:70px" disabled="true"
												styleId="nd">
												<html:options collection="xnList" property="nd"
													labelProperty="nd" />
											</html:select>
										</td>
									</logic:notEqual>
								</logic:notEqual>

								<th>
									年级
								</th>
								<td>
									<html:select property="nj" styleId="nj"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>

								<th>
									系
								</th>
								<logic:equal value="10827" name="xxdm">
									<td>
										<html:select property="xydm" style="width:200px" styleId="xy"
											onchange="initZyList();initBjList();chgJxjlists('xy');">
											<html:option value="">    全部    </html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
								</logic:equal>
								<logic:notEqual value="10827" name="xxdm">
									<td>
										<html:select property="xydm" style="width:170px" styleId="xy"
											onchange="initZyList();initBjList()">
											<html:option value="">    全部    </html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
								</logic:notEqual>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" style="width:150px;" styleId="zy"
										onchange="initBjList()">
										<html:option value="">    全部    </html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" style="width:120px" styleId="bj"
										onchange="refreshForm('/xgxt/prise_check.do');">
										<html:option value="">    全部    </html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<logic:equal value="10827" name="xxdm">
									<logic:equal value="xy" name="userType">
										<th>
											获奖类别
										</th>
										<td>
											<html:select property="jxjlb" styleId="jxjlb"
												onchange="refreshForm('/xgxt/prise_check.do')">
												<html:option value="1">院级</html:option>
												<html:option value="2">系级</html:option>
											</html:select>
										</td>
									</logic:equal>
								</logic:equal>
								<th>
									奖学金
								</th>
								<logic:equal value="10827" name="xxdm">
									<td>
										<html:select property="xmdm" style="width:160px"
											styleId="jxjdm">
											<html:option value=""></html:option>
											<html:options collection="jxjList" property="jxjdm"
												labelProperty="jxjmc" />
										</html:select>
									</td>
								</logic:equal>
								<logic:notEqual value="10827" name="xxdm">
									<td>
										<html:select property="xmdm" style="width:160px"
											onchange="checkQhts();" styleId="jxjdm">
											<html:option value=""></html:option>
											<html:options collection="jxjList" property="jxjdm"
												labelProperty="jxjmc" />
										</html:select>
									</td>
								</logic:notEqual>
								<th>
									显示方式
								</th>
								<td>
									<html:select property="dispFlag" style="width:50px"
										styleId="dispFlag" onchange="chgDisp('dispFlag');">
										<html:option value="xydm">系</html:option>
										<html:option value="zydm">专业</html:option>
										<html:option value="bjdm">班级</html:option>
									</html:select>
								</td>
								<logic:present name="showhzy">
									<span id="dispShf">审核否 <html:select property="yesNo">
											<html:option value="">    全部    </html:option>
											<html:options collection="chkList" property="en"
												labelProperty="cn" />
										</html:select> </span>
								</logic:present>
								<logic:notEqual value="12872" name="xxdm">
									<th>
										状态
									</th>
									<td>
										<html:select property="zt" styleId="zt">
											<html:option value=""></html:option>
											<html:option value="未审核">未审核</html:option>
											<html:option value="不通过">不通过</html:option>
											<html:option value="通过">通过</html:option>
										</html:select>
									</td>
								</logic:notEqual>
							</tr>

						</tbody>
					</table>
				</div>

				<div class="formbox">
					<logic:empty name="rs">
						<h3 class="datetitle_01">
							<span> 查询结果&nbsp;&nbsp; <font color="red">未找到任何记录！</font>
							</span>
						</h3>
					</logic:empty>
					<logic:notEmpty name="rs">

						<span> <logic:equal value="12764" name="xxdm">
								<logic:equal value="true" name="isFdy">
									<font color="blue">提示双击一行可以查看详细信息，本班可申请奖学金&nbsp;${bjsqrs
										}&nbsp;名；已申请&nbsp;${bjysqrs }&nbsp;名；未审核&nbsp;${bjwshrs
										}&nbsp;名； </font>
								</logic:equal>
								<logic:notEqual value="true" name="isFdy">
									<font color="blue">提示双击一行可以查看详细信息，并可以改变审核状态；单击表头可以排序 </font>
								</logic:notEqual>
							</logic:equal> <logic:notEqual value="12764" name="xxdm">
								<h3 class="datetitle_01">
									<span> 查询结果&nbsp;&nbsp;<font color="blue">记录数: <bean:write
												name="rsNum" /> 双击一行可以查看详细信息，并可以改变审核状态；单击表头可以排序</font> </span>
								</h3>

							</logic:notEqual> </span>
						<!-- <bean:message key="lable.xsgzyxpzxy" /> -->
						<logic:equal value="xy" name="userType">
							<!-- 长沙民政 -->
							<logic:equal value="10827" name="xxdm">
								<table width="100%" id="rsTable" class="dateline">
									<thead>
										<tr align="left" style="cursor:hand">
											<td>
												<input type="checkbox" id="selall" name="selall"
													onclick="selAll()" />
											</td>
											<logic:iterate id="tit" name="topTr" offset="4">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)">
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)" align="left"
											style="cursor:hand;background-color:
					    <logic:iterate id="v" name="s" offset="2" length="1">
					    <bean:write name="v"/>
					    </logic:iterate>
					    "
											ondblclick="chkPriseOnes('/xgxt/priseChkOne.do?act=view&pkVal=',780,650)">
											<td>
												<input type="checkbox" id="checkVal" name="checkVal"
													<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>
													value="<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v"/></logic:iterate>" />
												<logic:present name="shownblg">
													<input type="hidden"
														value="<logic:iterate id="v" name="s" offset="11" length="1"><bean:write name="v" /></logic:iterate>" />
													<input type="hidden"
														value="<logic:iterate id="v" name="s" offset="12" length="1"><bean:write name="v" /></logic:iterate>" />
												</logic:present>
											</td>
											<td>
												<logic:iterate id="v" name="s" offset="3" length="1">
													<input type="hidden" id="keyval" name="keyval"
														value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="7" length="1">
													<input type="hidden" id="xhval" name="xhval"
														value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="4" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="5">
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
							</logic:equal>
							<!-- 非长沙民政 -->
							<logic:notEqual value="10827" name="xxdm">
								<table width="100%" id="rsTable" class="dateline">
									<thead>
										<tr align="left" style="cursor:hand">
											<td>
												<input type="checkbox" id="selall" name="selall"
													onclick="selAll()" />
											</td>
											<logic:iterate id="tit" name="topTr" offset="3">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)">
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)" align="center"
											style="cursor:hand;background-color:
					    <logic:iterate id="v" name="s" offset="1" length="1">
					    <bean:write name="v"/>
					    </logic:iterate>
					    "
											<logic:equal value="10690" name="xxdm">ondblclick="shJxj('ynys_jxjsh.do?pkValue=')"</logic:equal>
											<logic:notEqual value="10690" name="xxdm"> ondblclick="chkPriseOnes('/xgxt/priseChkOne.do?act=view&pkVal=',780,650)"</logic:notEqual>>
											<td>
												<input type="checkbox" id="checkVal" name="checkVal"
													value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />
												<logic:present name="shownblg">
													<input type="hidden"
														value="<logic:iterate id="v" name="s" offset="10" length="1"><bean:write name="v" /></logic:iterate>" />
													<input type="hidden"
														value="<logic:iterate id="v" name="s" offset="11" length="1"><bean:write name="v" /></logic:iterate>" />
												</logic:present>
											</td>
											<td>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<input type="hidden" id="keyval" name="keyval"
														value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="6" length="1">
													<input type="hidden" id="xhval" name="xhval"
														value="<bean:write name="v"/>" />
												</logic:iterate>

												<logic:iterate id="v" name="s" offset="3" length="1">
													<bean:write name="v" />
												</logic:iterate>

											</td>

											<logic:iterate id="v" name="s" offset="4" length="1">
												<td>
													<bean:write name="v" />
													<input type="hidden" value="<bean:write name="v"/>" />
												</td>
											</logic:iterate>


											<logic:iterate id="v" name="s" offset="5">
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>

										</tr>
									</logic:iterate>
								</table>
							</logic:notEqual>
						</logic:equal>
						<!-- 非<bean:message key="lable.xsgzyxpzxy" /> -->
						<logic:notEqual value="xy" name="userType">
							<table width="100%" id="rsTable" class="dateline">
								<thead>
									<tr align="left" style="cursor:hand">
										<td>
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="3">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" align="left"
										style="cursor:hand;background-color:
					    <logic:iterate id="v" name="s" offset="1" length="1">
					    <bean:write name="v"/>
					    </logic:iterate>
					    "
										<logic:equal value="10690" name="xxdm">ondblclick="shJxj('ynys_jxjsh.do?pkValue=')"</logic:equal>
										<logic:notEqual value="10690" name="xxdm">ondblclick="chkPriseOnes('/xgxt/priseChkOne.do?act=view&pkVal=',780,650)"</logic:notEqual>>
										<td>
											<input type="checkbox" id="checkVal" name="checkVal"
												value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />
											<logic:present name="shownblg">
												<input type="hidden"
													value="<logic:iterate id="v" name="s" offset="10" length="1"><bean:write name="v" /></logic:iterate>" />
												<input type="hidden"
													value="<logic:iterate id="v" name="s" offset="11" length="1"><bean:write name="v" /></logic:iterate>" />
											</logic:present>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="2" length="1">
												<input type="hidden" id="keyval" name="keyval"
													value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="6" length="1">
												<input type="hidden" id="xhval" name="xhval"
													value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="3" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="4" length="1">
											<td>
												<a href="javascript:showXsxx('<bean:write name="v" />')"><bean:write
														name="v" /> </a>
												<input type="hidden" value="<bean:write name="v"/>" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="5">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</logic:notEqual>

					</logic:notEmpty>
				</div>


				<logic:present name="autoChk">
					<logic:equal name="autoChk" value="ok" scope="request">
						<script language="javascript">
		      alert("自动审核完成！");
			  </script>
					</logic:equal>
					<logic:equal name="autoChk" value="no" scope="request">
						<script language="javascript">
      alert("综合素质测评尚未完全导入，不能自动审核。\n请先检查综合素质测评数据！");
 	  </script>
					</logic:equal>
					<logic:equal name="autoChk" value="ov" scope="request">
						<script language="javascript">
      alert("您所选择审核单位的奖学金总金额超限。\n请先检查设置！");
 	  </script>
					</logic:equal>
				</logic:present>

				<!-- 宁波理工审核不通过 -->
				<logic:present name="shresult">
					<script>
					alert(''+document.getElementById('shresult').value);
				</script>
				</logic:present>
				<logic:present name="sJxjtg">
					<script>
					alert(''+document.getElementById('sJxjtg').value);
				</script>
				</logic:present>
				<logic:present name="mes">
					<logic:notEmpty name="mes">
						<input id="mes" name="mes" value="${mes}" type="hidden" />
						<script>
				alert(document.getElementById('mes').value);
			</script>
					</logic:notEmpty>
				</logic:present>
				<div id="tmpdiv1"></div>
			</html:form>
			<script language="javascript">
if(document.getElementById("jxjtmp") != null){
	document.getElementById("jxjtmp").style.top = -435;
}
</script>
			<script language="javascript">
     function djbPrint(){
        if(curr_row == null){
            alert('请选择要打印的数据！\n（单击相应的行）');
            return false;
        }else{
            //var pkV =  curr_row.getElementsByTagName("input")[1].value;
            var xh = curr_row.cells[2].innerText;
            //var jxjdm = pkV.substring(pkV.indexOf(xh)+xh.length,pkV.length);
            var pkValue = curr_row.getElementsByTagName("input")[0].value;
            window.open('jxjpsdjb.do?pkValue='+pkValue+'&xh='+xh);
        }
     }
     function hzyprint() {
     	if (curr_row==null || curr_row=='') 
     	{
     		alert('请选择要打印的行数据，单击一行即可!');
     		return;
     	}
     	 else {
     	 	var url = 'dxjxjsp.do?method=dxjxjsp&pk=';
     	 	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
     	 	window.open(url);
     	 }
     }
     function zsldprint() {
     	var url = 'hzzyzsld.do?pkValue=';
     	var jxjdm = document.getElementById('jxjdm').value;
     	if (jxjdm == null || jxjdm == '') {
     		alert('请选择要打印的奖学金!');
     	} else {
     		
			var boxes = document.getElementsByName("checkVal");
			var canModi = false;
			for(var i = 0; i < boxes.length; i++){
				if(boxes[i].checked){
					canModi = true;
					break;
				}
			}
			if(canModi){
			    BatAlert.MyAlert("确定要做此操作吗？此操作将连续打印选定的学生数据","",function(){
			 	   BatAlert.showTips("数据操作中，请稍候...");
			    	var pk;
					if($("rsTable").rows.length > 1){
						//for(i = 1;i<$("tabPri").rows.length;i++){
							rowOnClick($("rsTable").rows[1]);
							var temp = kshTaoPrint();
							var tempArray = temp.split(',');
							pk=tempArray[0];
							tempArray.splice(0,1);
							window.open(url+pk+'&pks='+tempArray.join(','));
						
						//}
						BatAlert.closeTips();
					 } else{
					    BatAlert.MyAlert("没有可打印的数据！");
						return false;
					 }
				},true);
				return false;  
			}else{
				BatAlert.MyAlert("此操作需要有选中的复选框，请选择！");
				return false;
			} 
		 }
     } 
     function ahjgqutosh() {
     	var xxdm = document.getElementById('xxdm').value;
     	var isFdy = document.getElementById('isFdy').value;
     	if (xxdm=='10878' && isFdy=='true') {//辅导员审核
     		var jxjdm = document.getElementById('jxjdm').value;
     		var bjdm = document.getElementById('bjdm').value;
     		if (jxjdm==''||bjdm==''||bjdm=='全部') {
     			alert('查询条件中奖学金与班级为必选!');
     			return;
     		} else {
     			priseAutoChk('/xgxt/priseAutoCheck.do');
     		}
     	} else {//<bean:message key="lable.xsgzyxpzxy" />,学校审核
     		var jxjdm = document.getElementById('jxjdm').value;
     		var xydm = document.getElementById('xydm').value;
     		if (jxjdm==''||xydm==''||xydm=='全部') {
     			alert('查询条件中奖学金与<bean:message key="lable.xsgzyxpzxy" />为必选!');
     			return;
     		} else {
     			priseAutoChk('/xgxt/priseAutoCheck.do');
     		}
     	}
     }
     function pjpyprint() {
     	var url = 'pjpy_zjcm_jxjprint.do?pkValue=';
     	if (curr_row==''||curr_row==null) {
     		if (confirm('没有选择任何数据,单击一行即可,要继续打印吗?')) {
     			window.open(url);
     		}
     		return;
     	} else {
     		var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
     		url += pk;
     		window.open(url);
     	}
     }
     var b = false;;
if(document.getElementById('rsTable')){
	for (i=0; i<document.getElementById("rsTable").rows[0].cells.length; i++){
  		if(document.getElementById("rsTable").rows[0].cells[i].id == "xh"){
  			b = true;
  			break;
  		}
  	}
  	if (b) {
  		for (j=1; j<document.getElementById("rsTable").rows.length; j++){
  		    var xhTd = document.getElementById("rsTable").rows[j].cells[i];
  		    var xhStr = xhTd.innerText.replace(/^\s+/g,"").replace(/\s+$/g,"").replace(/\n/g,"");
  		     var html_content = "<a href=\"javascript:queryOne('"+xhStr+"');\">"+xhStr+"</a>";
  			xhTd.innerHTML = html_content;
  		}
  	}
}
function queryOne(xh) {
	var url = "/xgxt/stu_info_details.do?xh="+xh;
	showTopWin(url, 800, 600);
}
function shdata() {
	var xxdm = document.getElementById('xxdm').value;
	var isFdy = document.getElementById('isFdy').value;
	var xmdm = document.getElementById('xmdm').value;
	var bj = document.getElementById('bj').value;
	if (xxdm=='12764' && 'true'==isFdy) {
		if (xmdm==''||bj==''||bj=='全部') {
			alert('审核数据时，奖学金和班级为必选！');
			return;
		} else {
		checkAll('pass');
		}
	} else {
		checkAll('pass');
	}
}
</script>
		</body>
	</logic:notPresent>
</html>
