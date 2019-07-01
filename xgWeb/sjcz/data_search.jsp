<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/chgRychlist.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/chgJxjlist.js'></script>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" /></title>

		<script language="javascript">
		function queryOne(value) {
			var xh = "";
			if(value == null || value == ""){
				if((curr_row == null) || (curr_row == "")){
					return false;
				}
				xh = curr_row.getElementsByTagName("input")[2].value;
			}else{
				xh = value;
			}
			var url = "/xgxt/stu_info_details.do?xh="+xh;
			showTopWin(url, 800, 600);
		}
			
			var Rows=new Array();	//所有选中的行对象
			var ShiftStartRow="";		//Shift多选时存储开始行对象
			var cur_bgc = "#ffdead";//选中行背景（字符串）
			
			function rowOver(objTr) {//
				curr_row = objTr;
			}
			
			function rowOut(objTr) {//
				curr_row = null;
			}
			
			function xz_viewMore(curr_row)
			{
				var xxdm=document.all['xxdm2'].value;
				var xg_xxdm = document.getElementById("xxdm").value;
				var tn = '';
				if ($('tableName')) {
					tn = document.getElementById('tableName').value;
				}
				if(xg_xxdm=="10402"){//漳州师范
					viewMore('modi');
				} else if("no"==xxdm)
				{	
					if (xg_xxdm=='13108' && tn=='view_zhszcp') {
						var url = "/xgxt/stu_cj_details.do?xh=" + curr_row.cells[3].innerText;
						var xn=curr_row.cells[1].innerText;
						var xq=curr_row.cells[2].innerText;
						url +='&xn='+xn;
						url +='&xq='+xq;
						showTopWin(url, 800, 600);
					}else {
						viewMore('view');
					}				
				} else if("10110"==xxdm)
				{
					if("zhszcp"==document.all['realTable'].value)
					{
					var xn=curr_row.cells[1].innerText;
					var nd=curr_row.cells[0].innerText;
					var xh=curr_row.cells[3].innerText;
				    var url='/xgxt/pjpy_zbdx_weihu_one.do?doType=view';
				    url=url+"&xn="+xn+"&nd="+nd+"&xh="+xh;
				    showTopWin(url,'550','500');
				} else {
				    viewMore('view');
				}
			}
			}
			function getRqVal(name)
			{
			var rq=document.getElementById(name).value;
			if (rq!="")
			{
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++)
				{
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
			}
			function chgrychlists(xydms) {
				var xydm = document.getElementById(xydms).value;
				chgRychlist.xyRychList(xydm,function(data) {
							DWRUtil.removeAllOptions('rychdm');			
							var o = [{id:'',labelText:''}];
							DWRUtil.addOptions('rychdm',o,'id','labelText');
							for(var i=0;i<data.length;i++){
								o = [{id:data[i].rychdm,labelText:data[i].rychmc}];
							DWRUtil.addOptions('rychdm',o,'id','labelText');
							}
				});
			}  		
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
		function bgdgl() {
			if (curr_row==null) {
				alert('请选择要操作的数据行!');
				return;
			} else {
				var count=0;
				var chklist =document.getElementsByName("cbv");
				for (var i=0;i<chklist.length;i++) {
					if (chklist[i].checked) {
						count = count+1;
					}
				}
				if (count>1) {
					alert('只能操作单条数据!');
					return;
				} else {
					var xh = curr_row.cells[0].innerText;
					chgJxjlist.chkStuIsBys(xh,function(data) {
						if (data) {
							showTopWin('wjcf_BgdLoad.do?tz=true&pk='+curr_row.cells[0].getElementsByTagName("input")[0].value,620,540)	
						} else {
							alert('该生不符合不归档审批条件,只有毕业生才符合此条件,请重新核对!');
							return;
						}
					});
				}
			}
		}
		
		function chSj(){
			if($("gzkssj") && $("gzjssj")){
				var kssj = $("gzkssj").value;
				var jssj = $("gzjssj").value;
				if(kssj !="" && jssj !=""){
					if(kssj > jssj){
						alert("开始时间大于结束时间，请确认");
						return false;
					}
				}
			}
			return true;		
		}
		function jsxxzcPrint() {
			var xn = document.getElementById('xn').value;
			var xq = document.getElementById('xq').value;
			var bjdm = document.getElementById('bjdm').value;
			if (xn=='' ||xq==''||bjdm=='') {
				alert("综合素质汇总表将按学年,学期,班级为单位进行汇总输出,请选择相应条件!");
				return false;
			}
			window.open('zhszcp_print.do?xydm=' + document.getElementById('xy').value + '&bjdm=' + document.getElementById('bj').value + '&xn=' + document.getElementById('xn').value + '&xq=' + document.getElementById('xq').value, '', '');
		}
		function jsxxzcCount() {
			var xn = document.getElementById('xn').value;
			var xq = document.getElementById('xq').value;
			var bjdm = document.getElementById('bjdm').value;
			if (xn=='' ||xq==''||bjdm=='') {
				alert("综合素质自动计算将按学年,学期,班级为单位进行汇总,请选择相应条件!");
				return false;
			}
			AutoAccountCj('/xgxt/AutoAccount.do')
		}
		function refreshSubForm() {
			if ($('wjcfflag') && $('wjcfflag').value=='true') {
				alert("操作成功!");
				document.getElementById('search_go').click();
			}	
		}
		function hhgxyTj(){
			if($("bjdm").value==""){
				alert("请选择班级！");
				return false;
			}
			if($("xn").value==""){
				alert("请选择学年！");
				return false;
			}
			if($("xq").value==""){
				alert("请选择学期！");
				return false;
			}
			var url="/xgxt/pjpyHhgxyPjtj.do?method=pjtj";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		function wjcfjj(){
			if (curr_row==null || curr_row=='') {
	     		alert('请选择要操作的数据行.');
	     		return;
	     	} else {
	     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
	     		var url = "wjcf_bjqn_wjcfjj.do?pkValue=" + pkValue; 
	  			showTopWin(url,800,600);
	     	}
		}
		
		<!-- 违纪处分汇总 -->
		function wjcfhz(){
			showOpenWindow("/xgxt/wjcfHztj.do?method=showWindow",400,200);
		}
		
		//td数量不对造成的问题解决
		jQuery(function(){
		
			jQuery(".searchtab").children("table").find("tr").each(function(){
				
				var len=jQuery(this).find("td").length;
				var nulls=false;
				var sum=0;
				if(len<6 && len!=0){
					
					
					jQuery(this).find("td").each(function(){
						
						var colspan=0;
						
						if(jQuery(this).html()!=""){
						
							nulls=true;
						}
						colspan=eval(jQuery(this).attr("colspan"));
						sum+=colspan;
						sum++;
						
					});
					
					
					for(i=0;i<6-sum;i++){
						jQuery(this).append("<td></td>")
					}
				
				}
				
				if(len==0){
					
					jQuery(this).attr("style","display:none");
				
				}
				
				
				if(!nulls){
					
					jQuery(this).attr("style","display:none");
				
				}
				
			});
			
		});

		//同济大学浙江分院个性化导出功能
		function bbdcExport(){
			
			var url = "bbdcExport.do?";

			var nj = jQuery("select[id=nj]").val();//年级
			if(nj != null && nj.length>0){
				url+= "&nj="+nj;
			}
			var xh = jQuery("input[name=xh]").val(); //学号
			if(xh != null && xh.length>0){
				url+= "&xh="+xh
			}
			var xm = jQuery("input[name=xm]").val(); //姓名
			if(xm != null && xm.length>0){
				url+= "&xm="+xm
			}
			var xn = jQuery("select[id=xn]").val(); //学年
			if(xn != null && xn.length>0){
				url+= "&xn="+xn
			}
			var nd = jQuery("select[id=nd]").val(); //年度
			if(nd != null && nd.length>0){
				url+= "&nd="+nd
			}
			var xq = jQuery("select[id=xq]").val(); //学期
			if(xq != null && xq.length>0){
				url+= "&xq="+xq
			}
			var xy = jQuery("select[id=xy]").val(); //学院
			if(xy != null && xy.length>0){
				url+= "&xydm="+xy
			}
			var zy = jQuery("select[id=zy]").val(); //专业
			if(zy != null && zy.length>0){
				url+= "&zydm="+zy
			}
			var bj = jQuery("select[id=bj]").val(); //班级
			if(bj != null && bj.length>0){
				url+= "&bjdm="+bj
			}
			var lydm = jQuery("select[id=lydm]").val(); //特别学生来源
			if(lydm != null && lydm.length>0){
				url+= "&lydm="+lydm
			}

			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		</script>


	</head>
	<body
		onload="xyDisabled('xy');removeXnXq();bzrLoad();refreshSubForm();">

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a id="title_m">${tips}</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">
			<input type="hidden" id="tzurl" name="tzurl"
				onclick="refreshForm('wjcf_BgdGl.do?go=go');" />


			<logic:equal value="view_xslxfszsxx" name="tableName">
				<logic:equal value="stu" name="userType">
					此页面只有学校和<bean:message key="lable.xsgzyxpzxy" />用户可以访问
				</logic:equal>
			</logic:equal>

			<logic:notEqual value="stu" name="userType">
				<div class="rightcontent">
					<jsp:include page="for_data_searchli.jsp"></jsp:include>

					<logic:present name="showhzyjx">
						<table width="100%" class="tbstyle">
							<html:radio property="grhj" value="grhj" styleId="grhj"
								onclick="refreshForm('/xgxt/data_search.do?act=trainPrise')">个人获奖</html:radio>
							<html:radio property="grhj" value="bjhj" styleId="bjhj"
								onclick="refreshForm('/xgxt/data_search.do?act=trainPrise')">班级获奖</html:radio>
							<html:radio property="grhj" value="yxhj" styleId="yxhj"
								onclick="refreshForm('/xgxt/data_search.do?act=trainPrise')">
								<bean:message key="lable.xsgzyxpzxy" />获奖</html:radio>
						</table>
					</logic:present>
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />
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
					<input type="hidden" id="xxdm" name="xxdm"
						value="<bean:write name="xxdm" scope="session"/>" />
					<input type="hidden" id="dxq" name="dxq"
						value="<bean:write name="writeAble" scope="request"/>" />
					<input type="hidden" id="isBzr" name="isBzr"
						value="<bean:write name="isBzr" scope="request"/>" />
					<input type="hidden" id="stab" name="stab" value="stab" />
					<input type="hidden" id="userName" name="userName"
						value="<bean:write name="userName" scope="session"/>" />
					<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />

					<div class="searchtab">
						<table width="100%" border="0">
							<logic:present name="showzbdx_xx">
								<input type="hidden" id="xxdm2" name="xxdm2"
									value="<bean:write name="xxdm" scope="request"/>" />
							</logic:present>
							<logic:notPresent name="showzbdx_xx">
								<input type="hidden" id="xxdm2" name="xxdm2" value="no" />
							</logic:notPresent>
							<logic:present name="sfxfzrx">
								<input type="hidden" id="sfxfzrx" name="sfxfzrx"
									value="<bean:write name="sfxfzrx" scope="request"/>" />
							</logic:present>

							<tfoot>
								<tr>
									<td colspan="6">
										<div class="btn">
											<input type="hidden" name="go" value="a" />
											<input type="hidden" name="tab" id="tab" value="qtjxj" />
											<button type="button" id="search_go"
												onclick="if(chSj()){allNotEmpThenGo('/xgxt/data_search.do');}">
												查询
											</button>
											<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
												重置
											</button>
											<logic:present name="showblsz">
												<button type="button" onclick="showTopWin('/xgxt/zhszcpblsz.do',750,250)">
													比例
													<br />
													设置
												</button>
											</logic:present>
										</div>
									</td>
								</tr>
							</tfoot>

							<tbody>
								<logic:notEqual value="bjhj" name="bjhjType">
									<tr>
										<th>
											年级
										</th>
										<td>
											<html:select property="nj"
												onchange="initZyList();initBjList()" styleId="nj">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</td>
										<th>
											学号
										</th>
										<td>
											<html:text property="xh" style="width:120px" maxlength="20"></html:text>
										</td>
										<th>
											姓名
										</th>
										<td>
											<html:text property="xm" style="width:85px" maxlength="20"></html:text>
										</td>
									</tr>
								</logic:notEqual>
								<tr>
									<logic:equal value="bjhj" name="bjhjType">
										<th>
											年级
										</th>
										<td colspan="5">
											<html:select property="nj"
												onchange="initZyList();initBjList()" styleId="nj">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</td>
									</logic:equal>
								</tr>
								<logic:equal value="showXbemy" name="showXbemy">
									<tr>
										<th>
											处分时间&nbsp;起始&nbsp;
										</th>
										<td>
											<input type="text" name="qssj" id="qssj"
												onclick="return showCalendar('qssj','y-mm-dd');"
												onblur="getRqVal('qssj')" />
										</td>
										<th>
											终止&nbsp;
										</th>
										<td colspan="3">
											<input type="text" name="zzsj" id="zzsj"
												onclick="return showCalendar('zzsj','y-mm-dd');"
												onblur="getRqVal('zzsj')" />
										</td>
									</tr>
								</logic:equal>

								<logic:equal value="xshsxfb" name="realTable">
									<tr>
										<th>
											年份
										</th>
										<td colspan="5">
											<html:select property="nf" style="width:70px" styleId="nf">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</td>
									</tr>
								</logic:equal>
								
								<tr>
									<logic:notEqual value="view_xslxfszsxx" name="tableName">
										<logic:notEqual value="xshsxfb" name="realTable">
											<!--非投保信息查询-->
											<logic:notEqual value="xsbxb" name="realTable">
												<!--非社会实践查询-->
												<logic:notEqual name="tableName" value="view_knsshhdxx">
													<th>
														学年
													</th>
													<td>
														<html:select property="xn" style="width:90px" styleId="xn"
															onchange="">
															<html:options collection="xnList" property="xn"
																labelProperty="xn" />
														</html:select>
													</td>
												</logic:notEqual>
											</logic:notEqual>
											<!--end非投保信息查询-->
										</logic:notEqual>
									</logic:notEqual>

									<%--学生信息维护中无年度--%>
									<logic:notEqual value="view_xslxfszsxx" name="tableName">
										<logic:notEqual value="13108" name="xxdm">
											<!--非社会实践查询-->
											<logic:notEqual name="tableName" value="view_knsshhdxx">
												<th>
													年度
												</th>
												<td>
													<html:select property="nd" styleId="nd" style="width:70px">
														<html:options collection="ndList" property="nd"
															labelProperty="nd" />
													</html:select>
												</td>

											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
									<!--投保信息查询-->
									<logic:equal value="view_xsbxxx" name="tableName">
										<th>
											是否已毕业
										</th>
										<td>
											<html:select property="sfyby">
												<html:option value=""></html:option>
												<html:option value="是">是</html:option>
												<html:option value="否">否</html:option>
											</html:select>
										</td>
									</logic:equal>
									<logic:notPresent name="zjujxjrych">
										<logic:equal value="xshsxfb" name="realTable">
											<th>
												月份
											</th>
											<td>
												<html:select property="yf" style="width:40px" styleId="yf">
													<html:option value=""></html:option>
													<html:options collection="yfList" property="yf"
														labelProperty="yf" />
												</html:select>
											</td>
										</logic:equal>

										<logic:notEqual value="view_xslxfszsxx" name="tableName">
											<logic:notEqual value="xshsxfb" name="realTable">
												<!--非投保信息查询-->
												<logic:notEqual value="xsbxb" name="realTable">
													<logic:notEqual value="no" name="view">
														<!--非社会实践查询-->
														<logic:notEqual name="tableName" value="view_knsshhdxx">
															<th>
																学期
															</th>
															<td>
																<html:select property="xq" style="width:60px"
																	styleId="xq">
																	<html:option value=""></html:option>
																	<html:options collection="xqList" property="xqdm"
																		labelProperty="xqmc" />
																</html:select>
															</td>
														</logic:notEqual>
													</logic:notEqual>
												</logic:notEqual>
												<!--end非投保信息查询-->
											</logic:notEqual>
										</logic:notEqual>
									</logic:notPresent>
								</tr>
								<%--										<logic:present name="zjujxjrych">--%>
								<tr>
									<!--投保信息查询-->
									<logic:equal value="10290" name="xxdm">
										<logic:equal value="view_xlcsjg" name="tableName">
											<th>
												测试结果
											</th>
											<td>
												<html:select name="cxrs" property="csjg" style="width:90px">
													<html:option value=""></html:option>
													<html:option value="良好"></html:option>
													<html:option value="超标"></html:option>
												</html:select>
											</td>

											<th>
												测验时间
											</th>
											<td>
												<html:select name="cxrs" property="csnj">
													<html:option value=""></html:option>
													<html:option value="一年级"></html:option>
													<html:option value="二年级"></html:option>
													<html:option value="三年级"></html:option>
													<html:option value="四年级"></html:option>
													<html:option value="五年级"></html:option>
													<html:option value="六年级"></html:option>
												</html:select>
											</td>
										</logic:equal>
									</logic:equal>
								</tr>
								<tr>
									<logic:equal value="10827" name="xxdm">
										<logic:equal value="xsjxjb" name="realTable">
											<th>
												审核状态
											</th>
											<td>
												<html:select property="shzt" styleId="shzt">
													<html:option value=""></html:option>
													<html:option value="0">通过</html:option>
													<html:option value="1">不通过</html:option>
													<html:option value="2">未审核</html:option>
												</html:select>
											</td>
										</logic:equal>
										<logic:equal value="xsrychb" name="realTable">
											<th>
												审核状态
											</th>
											<td>
												<html:select property="shzt" styleId="shzt">
													<html:option value=""></html:option>
													<html:option value="0">通过</html:option>
													<html:option value="1">不通过</html:option>
													<html:option value="2">未审核</html:option>
												</html:select>
											</td>
										</logic:equal>
									</logic:equal>
								</tr>
								<tr>
									<logic:equal value="wjcf" name="wjcf">
										<logic:notEqual value="11078" name="xxdm">
											<th>
												身份证号
											</th>
											<td>
												<html:text property="sfzh" style="width:110px"></html:text>
											</td>
										</logic:notEqual>
										<th>
											处分类别
										</th>
										<td>
											<html:select property="cflb" style="width:150px"
												styleId="cflb">
												<html:option value=""></html:option>
												<html:options collection="cflbList" property="cflbdm"
													labelProperty="cflbmc" />
											</html:select>
										</td>
										<th>
											处分原因
										</th>
										<td>
											<html:select property="cfyy" style="width:150px"
												styleId="cfyy">
												<html:option value=""></html:option>
												<html:options collection="cfyyList" property="cfyydm"
													labelProperty="cfyymc" />
											</html:select>
										</td>


										<!-- 广州大学单独 -->
										<logic:equal value="11078" name="xxdm">
											<th>
												是否教务处数据
											</th>
											<td>
												<html:select property="sfjw" styleId="sfjw"
													style="width:50px">
													<html:option value=""></html:option>
													<html:option value="是">是</html:option>
													<html:option value="否">否</html:option>
												</html:select>
											</td>
										</logic:equal>
										<!-- END -->
									</logic:equal>
								</tr>
								<tr>
									<!-- liang : 浙江机电职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
									<logic:equal value="12861" name="xxdm">
										<logic:equal value="view_xsgwxx" name="tableName">
											<th>
												用人单位
											</th>
											<td>
												<html:select property="yrdwdm" style="width:90px">
													<html:option value=""></html:option>
													<html:options collection="yrdwList" property="yrdwdm"
														labelProperty="yrdwmc" />
												</html:select>
											</td>
										</logic:equal>
									</logic:equal>
									<!-- 党团建设学生层次 -->
									<logic:present name="xsccList">
										<th>
											学生层次
										</th>
										<td>
											<html:select property="xsccdm" style="width:90px">
												<html:option value=""></html:option>
												<html:options collection="xsccList" property="xsccdm"
													labelProperty="xsccmc" />
											</html:select>
										</td>
									</logic:present>
									<logic:equal name="tableName" value="view_rdjjfzxx">
										<logic:equal name="xxdm" value="12061">
											<th>
												获得时间段
											</th>
											<td>
												<html:text property="gzkssj" styleId="gzkssj"
													onblur="dateFormatChg(this);" style="cursor:hand;width:10%"
													onclick="return showCalendar('gzkssj','y-mm-dd');" />
												--
												<html:text property="gzjssj" styleId="gzjssj"
													onblur="dateFormatChg(this);" style="cursor:hand;width:10%"
													onclick="return showCalendar('gzjssj','y-mm-dd');" />
											</td>
										</logic:equal>
									</logic:equal>
								</tr>

								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<logic:equal value="10827" name="xxdm">
										<logic:equal value="xsjxjb" name="realTable">
											<td>
												<html:select property="xydm" style="width:200px"
													styleId="xy"
													onchange="initZyList();initBjList();chgJxjlists('xy');">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
												</html:select>
											</td>
										</logic:equal>
										<logic:notEqual value="xsjxjb" name="realTable">
											<logic:equal value="xsrychb" name="realTable">
												<td>
													<html:select property="xydm" style="width:200px"
														styleId="xy"
														onchange="initZyList();initBjList();chgrychlists('xy');">
														<html:option value=""></html:option>
														<html:options collection="xyList" property="xydm"
															labelProperty="xymc" />
													</html:select>
												</td>
											</logic:equal>
											<logic:notEqual value="xsrychb" name="realTable">
												<td>
													<html:select property="xydm" style="width:200px"
														styleId="xy" onchange="initZyList();initBjList();">
														<html:option value=""></html:option>
														<html:options collection="xyList" property="xydm"
															labelProperty="xymc" />
													</html:select>
												</td>
											</logic:notEqual>
										</logic:notEqual>
									</logic:equal>
									<logic:notEqual value="10827" name="xxdm">
										<td>
											<html:select property="xydm" style="width:150px" styleId="xy"
												onchange="initZyList();initBjList()">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</td>
									</logic:notEqual>
									<logic:notEqual value="xyhj" name="xyhjType">
										<th>
											专业
										</th>
										<td>
											<html:select property="zydm" style="width:150px" styleId="zy"
												onchange="initBjList()">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</td>
										<th>
											班级
										</th>
										<td>
											<html:select property="bjdm" style="width:150px" styleId="bj">
												<logic:notEqual value="yes" name="isBzr">
													<html:option value=""></html:option>
												</logic:notEqual>
												<html:options collection="bjList" property="bjdm"
													labelProperty="bjmc" />
											</html:select>
										</td>

									</logic:notEqual>
								</tr>
								<%--								<logic:equal value="10338" name="xxdm" scope="session">--%>
								<%--									<tr>--%>
								<%--										<th>--%>
								<%--											心理测试类别--%>
								<%--										</th>--%>
								<%--										<td>--%>
								<%--											<html:text property="xlcslb"></html:text>--%>
								<%--										</td>--%>
								<%--										<th>--%>
								<%--											心理问题类型--%>
								<%--										</th>--%>
								<%--										<td>--%>
								<%--											<html:text property="xlwtlx"></html:text>--%>
								<%--										</td>--%>
								<%--										<th>--%>
								<%--											是否困难生--%>
								<%--										</th>--%>
								<%--										<td>--%>
								<%--											<html:select property="sfkns">--%>
								<%--												<html:option value=""></html:option>--%>
								<%--												<html:option value="是">是</html:option>--%>
								<%--												<html:option value="否">否</html:option>--%>
								<%--											</html:select>--%>
								<%--										</td>--%>
								<%--									</tr>--%>
								<%--									<tr>--%>
								<%--										<th>--%>
								<%--											是否单亲--%>
								<%--										</th>--%>
								<%--										<td>--%>
								<%--											<html:select property="sfdq">--%>
								<%--												<html:option value=""></html:option>--%>
								<%--												<html:option value="是">是</html:option>--%>
								<%--												<html:option value="否">否</html:option>--%>
								<%--											</html:select>--%>
								<%--										</td>--%>
								<%--									</tr>--%>
								<%--								</logic:equal>--%>
								<tr>
									<logic:equal value="10827" name="xxdm">
										<logic:equal value="xy" name="userType">
											<th>
												获奖类别
											</th>
											<td>
												<html:select property="jxjlb" styleId="jxjlb"
													onchange="refreshForm('/xgxt/data_search.do')">
													<html:option value="1">院级</html:option>
													<html:option value="2">系级</html:option>
												</html:select>
											</td>
										</logic:equal>
									</logic:equal>
									<logic:equal value="10827" name="xxdm">
										<logic:present name="xsjxjb">
											<th>
												奖学金
											</th>

											<td>
												<html:select property="jxjdm" style="width:170px"
													styleId="jxjdm">
													<html:option value=""></html:option>
													<html:options collection="xmlist" property="jxjdm"
														labelProperty="jxjmc" />
												</html:select>
											</td>
										</logic:present>
									</logic:equal>
									<logic:notEqual value="10827" name="xxdm">
										<logic:present name="xsjxjb">
											<th>
												奖学金
											</th>
											<td>
												<html:select property="jxjdm" style="width:170px"
													styleId="jxjdm">
													<html:option value=""></html:option>
													<html:options collection="xmlist" property="jxjdm"
														labelProperty="jxjmc" />
												</html:select>
											</td>
											<th>
												院系审核
											</th>
											<td>
												<html:select property="xysh" style="width:170px"
													styleId="xysh">
													<html:option value=""></html:option>
													<html:options collection="shList" property="en"
														labelProperty="cn" />
												</html:select>
											</td>
											<th>
												学校审核
											</th>
											<td>
												<html:select property="xxsh" style="width:170px"
													styleId="xxsh">
													<html:option value=""></html:option>
													<html:options collection="shList" property="en"
														labelProperty="cn" />
												</html:select>
											</td>
										</logic:present>
									</logic:notEqual>
									<logic:present name="xsrychb">
										<th>
											荣誉称号
										</th>
										<td>
											<html:select property="rychdm" style="width:160px"
												styleId="rychdm">
												<html:option value=""></html:option>
												<html:options collection="xmlist" property="rychdm"
													labelProperty="rychmc" />
											</html:select>
										</td>
										<th>
											院系审核
										</th>
										<td>
											<html:select property="xysh" style="width:170px"
												styleId="xysh">
												<html:option value=""></html:option>
												<html:options collection="shList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
										<th>
											学校审核
										</th>
										<td>
											<html:select property="xxsh" style="width:170px"
												styleId="xxsh">
												<html:option value=""></html:option>
												<html:options collection="shList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
									</logic:present>
									<%--										</logic:present>--%>
								</tr>
								<tr>
									<!-- 宁波城市单独 -->
									<logic:equal value="12645" name="xxdm">
										<logic:equal value="wjcfb" name="realTable">
											<th>
												处分期限
											</th>
											<td>
												<html:text property="cfnx" styleId="cfnx" maxlength="20"></html:text>
											</td>
										</logic:equal>
									</logic:equal>

									<logic:equal value="wjcfb" name="realTable">
										<th>
											学生处分确认
										</th>
										<td>
											<html:select property="xsqr" styleId="xsqr">
												<html:option value=""></html:option>
												<html:option value="未确认">未确认</html:option>
												<html:option value="已确认">已确认</html:option>
											</html:select>
										</td>
										<th>
											处分次数(或以上)
										</th>
										<td>
											<html:text property="cs" styleId="cs" maxlength="2"
												style="width:50px" onkeyup="chkIsNum(this)"></html:text>
										</td>
										<th>
											违纪时间
										</th>
										<td>
											<html:text property="wjkssj" style="width:80px"
												onclick="return showCalendar('wjkssj','ymmdd');"
												styleId="wjkssj" />
											-
											<html:text property="wjjssj" style="width:80px"
												onclick="return showCalendar('wjjssj','ymmdd');"
												styleId="wjjssj" />
										</td>
									</logic:equal>

									<logic:equal value="13022" name="xxdm">
										<logic:equal name="wjcf" value="wjcf">
											<th>
												有无申诉处理
											</th>
											<td>
												<html:select property="ywss" style="width:50px"
													styleId="ywss">
													<html:option value=""></html:option>
													<html:option value="0">有</html:option>
													<html:option value="1">无</html:option>
												</html:select>
											</td>
										</logic:equal>
									</logic:equal>
									<logic:notEqual name="xxdm" value="13022">
										<logic:notEqual name="wjcf" value="wjcf">
											<th></th>
											<td></td>
										</logic:notEqual>
									</logic:notEqual>
								</tr>
								<logic:equal value="13022" name="xxdm">
									<logic:equal name="wjcf" value="wjcf">
										<tr>
											<th>
												有无解除处理
											</th>
											<td>
												<html:select property="ywcx" style="width:50px"
													styleId="ywcx">
													<html:option value=""></html:option>
													<html:option value="0">有</html:option>
													<html:option value="1">无</html:option>
												</html:select>
											</td>
											<th></th>
											<td></td>
											<th></th>
											<td></td>
										</tr>
									</logic:equal>
								</logic:equal>
								<logic:equal value="wjcfb" name="realTable">
									<th>
										处分时间
									</th>
									<td>
										<html:text property="cfkssj" style="width:80px"
											onclick="return showCalendar('cfkssj','ymmdd');"
											styleId="cfkssj" />
										-
										<html:text property="cfjssj" style="width:80px"
											onclick="return showCalendar('cfjssj','ymmdd');"
											styleId="cfjssj" />
									</td>
									
									<logic:equal value="12759" name="xxdm">
									<th>
										是否撤消
									</th>
									<td>
										<select name="sfcx" id="sfcx" style="width:50px">
											<option value=""></option>
											<option value="是" <logic:equal name="sfcx" value="是">selected="selected"</logic:equal>>是</option>
											<option value="否" <logic:equal name="sfcx" value="否">selected="selected"</logic:equal>>否</option>
										</select>
									</td>
									</logic:equal>
									<logic:notEqual value="12759" name="xxdm">
									
									<th>

									</th>
									<td>

									</td>
									</logic:notEqual>
									<th>

									</th>
									<td>

									</td>
								</logic:equal>
								<logic:present name="specialStu">
									<th>
										特别学生来源
									</th>
									<td>
										<html:select  property="lydm" style="width:90px"
											styleId="lydm">
											<html:option value=""></html:option>
											<html:options collection="tsxslyList" property="lydm"
												labelProperty="lymc" />
										</html:select>
									</td>
									<th>

									</th>
									<td>

									</td>
									<th>

									</th>
									<td>

									</td>
								</logic:present>
							</tbody>
						</table>
					</div>

					<font color="blue"></font>

					<div class="formbox">
						<logic:empty name="rs">
							<h3 class="datetitle_01">
								<span> 查询结果&nbsp;&nbsp; <font color="red">未找到任何记录！</font>
								</span>
							</h3>
						</logic:empty>

						<logic:notEmpty name="rs">
							<h3 class="datetitle_01">
								<span> 查询结果&nbsp;&nbsp; <font color="blue"><logic:present
											name="qssj">(<bean:write name="qssj" />--</logic:present> <logic:present
											name="zzsj">
											<bean:write name="zzsj" /> 违纪名单)</logic:present>
										提示：双击一行可以查看详细信息；单击表头可以排序;按住Ctrl可以多选</font> </span>
							</h3>
							<table width="100%" id="rsTable" class="dateline">
								<thead>
									<tr style="cursor: hand">
										<logic:notPresent name="xsjxjb">
											<logic:notEqual value="10290" name="xxdm">
											
												<logic:equal name="realTable" value="wjcfb">
												<logic:iterate id="tit" name="topTr" offset="2">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)">
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
												</logic:equal>
												<logic:notEqual name="realTable" value="wjcfb">
												<logic:iterate id="tit" name="topTr" offset="1">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)">
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
												</logic:notEqual>
												
											</logic:notEqual>
											<logic:equal value="10290" name="xxdm">
												<logic:iterate id="tit" name="topTr" offset="1" length="2">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)">
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
												<logic:iterate id="tit" name="topTr" offset="3" length="4">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)">
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>

												<logic:iterate id="tit" name="topTr" offset="8" length="1">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)">
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>

											</logic:equal>
										</logic:notPresent>
										<logic:present name="xsjxjb">
											<logic:iterate id="tit" name="topTr" offset="2">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)">
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</logic:present>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<logic:notPresent name="xsjxjb">
										<logic:equal value="12872" name="xxdm">
											<logic:equal value="xsrychb" name="realTable">
												<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand"
													ondblclick="hzzyrychmodi()">
													<td>
														<logic:iterate id="v" name="s" offset="0" length="1">
															<input type="hidden" value="<bean:write name="v"/>" />
														</logic:iterate>
														<logic:iterate id="v" name="s" offset="1" length="1">
															<bean:write name="v" />
														</logic:iterate>
													</td>
													<logic:iterate id="v" name="s" offset="2">
														<td>
															<bean:write name="v" />
														</td>
													</logic:iterate>
												</tr>
											</logic:equal>
											<logic:notEqual value="xsrychb" name="realTable">
												<tr align="center" onclick="rowMoreClick(this,'',event);"
													style="cursor: hand" ondblclick="xz_viewMore(this)">
													<td>
														<logic:iterate id="v" name="s" offset="0" length="1">
															<input type="hidden" value="<bean:write name="v"/>" />
														</logic:iterate>
														<logic:iterate id="v" name="s" offset="1" length="1">
															<bean:write name="v" />
														</logic:iterate>
													</td>
													<logic:iterate id="v" name="s" offset="2">
														<td>
															<bean:write name="v" />
														</td>
													</logic:iterate>
												</tr>
											</logic:notEqual>
										</logic:equal>

										<logic:notEqual value="12872" name="xxdm">
											<logic:notEqual value="10290" name="xxdm">

												<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand"
													ondblclick="xz_viewMore(this)">

													<logic:equal name="realTable" value="wjcfb">
														<td>
															<logic:iterate id="v" name="s" offset="0" length="1">
																<input type="hidden" value="<bean:write name="v"/>" />
															</logic:iterate>
															<logic:iterate id="v" name="s" offset="1" length="1">
																<input type="hidden" value="<bean:write name="v"/>" />
															</logic:iterate>
															<logic:iterate id="v" name="s" offset="2" length="1">
																<bean:write name="v" />
															</logic:iterate>
														</td>
														<logic:iterate id="v" name="s" offset="3">
															<td>
																<bean:write name="v" />
															</td>
														</logic:iterate>
													</logic:equal>
													<logic:notEqual name="realTable" value="wjcfb">
													<td>
														<logic:iterate id="v" name="s" offset="0" length="1">
															<input type="hidden" value="<bean:write name="v"/>" />
														</logic:iterate>
														<logic:iterate id="v" name="s" offset="1" length="1">
															<bean:write name="v" />
														</logic:iterate>
													</td>
													<logic:iterate id="v" name="s" offset="2">
														<td>
															<bean:write name="v" />
														</td>
													</logic:iterate>
													</logic:notEqual>
												</tr>
											</logic:notEqual>

											<logic:equal value="10290" name="xxdm">
												<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand"
													ondblclick="xz_viewMore(this)">
													<td>
														<logic:iterate id="v" name="s" offset="0" length="1">
															<input type="hidden" value="<bean:write name="v"/>" />
														</logic:iterate>
														<logic:iterate id="v" name="s" offset="1" length="1">
															<bean:write name="v" />
														</logic:iterate>
													</td>
													<logic:iterate id="v" name="s" offset="2" length="5">
														<td>
															<bean:write name="v" />
														</td>
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="8" length="1">
														<td>
															<bean:write name="v" />
														</td>
													</logic:iterate>
												</tr>
											</logic:equal>
										</logic:notEqual>
									</logic:notPresent>

									<%--									</logic:notPresent>--%>
									<!-- 非江苏信息 END -->
									<!-- 奖学金 -->
									<logic:present name="xsjxjb">
										<logic:equal value="10290" name="xxdm">
											<tr onclick="rowMoreClick(this,'',event);"
												onmouseover="rowOver(this);" onmouseout="rowOut();"
												ondblclick="<logic:equal value="12872" name="xxdm">hzyjxjmodi()</logic:equal><logic:notEqual value="12872" name="xxdm">viewMore2('modi')</logic:notEqual>">

												<td>
													<logic:iterate id="v" name="s" offset="0" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="1" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>
													<a href="#" onclick="queryOne()"> <logic:iterate id="v"
															name="s" offset="2" length="1">
															<bean:write name="v" />
														</logic:iterate> <input type="hidden" value="<bean:write name="v"/>" /> </a>
												</td>
												<logic:iterate id="v" name="s" offset="3">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:equal>
										<logic:notEqual value="10290" name="xxdm">
											<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand"
												ondblclick="<logic:equal value="12872" name="xxdm">hzyjxjmodi()</logic:equal><logic:notEqual value="12872" name="xxdm">viewMore2('modi')</logic:notEqual>">

												<td>
													<logic:iterate id="v" name="s" offset="0" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="1" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>

													<logic:iterate id="v" name="s" offset="2" length="1">
														<bean:write name="v" />
													</logic:iterate>

												</td>
												<logic:iterate id="v" name="s" offset="3" length="1">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="4" length="1">
													<td>
														<a href="#" onclick="queryOne('<bean:write name="v" />')"><bean:write
																name="v" /> </a>
													</td>
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="5">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:notEqual>
									</logic:present>
								</logic:iterate>
							</table>
					</div>
					<logic:notEqual value="12872" name="xxdm">
						<table width="99%" id="rsTable1" class="tbstyle">
							<tr>
								<td height=3></td>
							</tr>
							<tr>
								<td>
									<jsp:include flush="true"
										page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>

									<script type="text/javascript">
														$('choose').className="hide";
												</script>
								</td>
							</tr>
							<tr>
								<td height=3></td>
							</tr>
						</table>
						<br />
						<br />
					</logic:notEqual>
					<logic:equal value="12872" name="xxdm">
						<logic:notEqual value="xsjxjb" name="realTable">
							<logic:notEqual value="xsrychb" name="realTable">
								<table width="99%" id="rsTable1" class="tbstyle">
									<tr>
										<td height=3></td>
									</tr>
									<tr>
										<td>
											<jsp:include flush="true"
												page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
										</td>
									</tr>
									<tr>
										<td height=3></td>
									</tr>
								</table>
							</logic:notEqual>
						</logic:notEqual>
					</logic:equal>
					</logic:notEmpty>
				</div>
				<br />
				<br />

				<div id="toolTipLayer"
					style="position: absolute; visibility: hidden">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute; left: 1%; top: 100px">
						</div>
					</center>
				</div>
			</logic:notEqual>

			<div id="tmpdiv"></div>
			<%--浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
			<logic:present name="dydate">
				<logic:equal name="dydate" value="no">
					<script language="javascript">
      					alert("该学生未过预备期");
	  				</script>
				</logic:equal>
				<logic:equal name="dydate" value="yes">
					<script language="javascript">
      					alert("转正成功");
	  				</script>
				</logic:equal>
			</logic:present>
			<%--end浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
			<%--杭州职业技术<bean:message key="lable.xsgzyxpzxy" />--%>
			<logic:present name="autoCj">
				<logic:equal name="autoCj" value="ok">
					<script language="javascript">
      						alert("自动计算完成！");
	  					</script>
				</logic:equal>
				<logic:equal name="autoCj" value="no">
					<script language="javascript">
	  						alert("自动计算失败! ");
	  					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<logic:equal name="result" value="ok">
			<script language="javascript">
      				alert("操作成功！");
      				document.getElementById('search_go').click();
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="no">
			<script language="javascript">
	  				alert("操作失败! ");
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="NoStu">
			<script language="javascript">
	  				alert("此操作只针对学生个人获奖!");
	  		</script>
		</logic:equal>
		<!-- 	<script type="text/javascript" src="js/bottomButton.js"></script>  -->
		<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);

					function hzyrychprint(){
						if (curr_row==null || curr_row=='') {
							alert('请选择要打印的数据行！');
							return;
						} else 
							window.open('hzyrychprint.do?pkValue='+curr_row.cells[0].getElementsByTagName("input")[0].value);
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
				     function hzyjxjmodi(){
				     	if (curr_row==null || curr_row=='') {
				     		alert('请选择要操作的数据行.');
				     		return;
				     	} else {
				     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				     		
				     		showTopWin('hzzyjxjmodi.do?pkValue='+pkValue,'670','550');
				     	}
				     } 
				     function hzzyrychmodi(){
				     	if (curr_row==null || curr_row=='') {
				     		alert('请选择要操作的数据行.');
				     		return;
				     	} else {
				     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				     		
				     		showTopWin('hzzyrychmodi.do?pkValue='+pkValue,'610','510');
				     	}
				     }
				     function wjsjzy(url) {
				     	var RowsStr="!!SplitOneSplit!!";   
				     	if (Rows.length==0) {
				     		alert('请选择要操作的数据行,按住Ctrl键可以多选!');
				     		return;
				     	}
				     	if (confirm('确定要将选择的数据转入历史信息库吗?')) {
				     		for (i=0; i<Rows.length; i++){ 										//连接字符串
    							RowsStr+=Rows[i].getElementsByTagName("input")[0].value+"!!SplitOneSplit!!";
							}
							showTips('处理数据中，请等待......');
							refreshForm(url+"?pkValue="+RowsStr);
							
				     	}
				     	return;
				     }
				     
				     function kftj(url){
				     	var xn = document.getElementById('xn').value;
				     	var xq = document.getElementById('xq').value;
				     	
				     	url += "?xn=" + xn;
				     	url += "&xq=" + xq;
				     	
				     	if(xn == "" && xq == ""){
				     		if(confirm('不选择学年学期将统计全部的信息,是否确定？')){
				     			window.open(url);
				     			return true;
				     		}
				     	}
				     	window.open(url);
				     }
		</script>
		<!--		<logic:equal value="10290" name="xxdm">-->
		<!--			<SCRIPT LANGUAGE="JavaScript">-->
		<!--				setPageSize();-->
		<!--			</SCRIPT>-->
		<!--		</logic:equal>-->


	</body>
</html>
