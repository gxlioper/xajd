<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/studetailFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/dwjlgl.js'></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript">
		function showMk(){
			var mk = document.getElementsByName('hidden_mk');
			for(var i=0; i<mk.length; i++){
				xsid = "mk_" + mk[i].value;
				divid = "div_" + mk[i].value;
				
				if($(xsid)){
					$(xsid).style.display = "";
				}

				if($(divid)){
					$(divid).click();
				}
			}
		}

		function expand(){
			getJtxxInfo();
		}
		
		function deploy(id){
			document.getElementById(id).style.display = (document.getElementById(id).style.display == 'none') ? '' : 'none';  
		}
	</script>
	
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
		.noPrin{
			display:none;
		}
	</style>
</head>
	<body onload="showMk();">		
		<input type="hidden" id="xh" name="xh" value="${xh}" />
		<input type="hidden" id="zzxh" name="zzxh" value="${xh}" />
		<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
		
		<logic:iterate id="mk" name="printMk">
			<input type="hidden" name="hidden_mk" value="${mk }"/>
		</logic:iterate>
		
		<div class="tab">
		<table width="100%" align="center" class="formlist breakword">
			<thead>
			    	<tr>
			        	<th colspan="5"><span>学生信息</span></th>
			        </tr>
			    </thead>
			    <tbody>
			      <tr>
			        <th width="16%">学号</th>
			      	<td width="20%">${rs.xh}</td>
			      	<th width="16%">姓名</th>
					<td width="20%">${rs.xm}</td>
					<td rowspan="6" class="nohover" style="vertical-align:top; text-align:center;">
						<div class="open_img">
						<!--浙江商业职业技术学院-->
						<logic:equal value="12865" name="xxdm">
							<img border="0" style="height:133px;width:100px;"
								src="/xgxt/pictures/${rs.sfzh}.jpg"/>
						</logic:equal>
						<!--end浙江商业职业技术学院-->
						<%--浙江机电职业技术学院--%>
						<logic:equal value="12861" name="xxdm">
							<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh}"
									border="0" align="absmiddle" style="width:140px;height:160px"/>
						</logic:equal>
						<%--end浙江机电职业技术学院--%>
						<%--江苏信息职业技术学院--%>
						<logic:equal value="13108" name="xxdm">
							<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh}"
									border="0" align="absmiddle" style="width:140px;height:160px" />
						</logic:equal>
						<%--end江苏信息职业技术学院--%>

						<!--其它学校-->
						<logic:notEqual value="12865" name="xxdm">
						<logic:notEqual value="12861" name="xxdm">
						<logic:notEqual value="13108" name="xxdm">
							<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}" width="96" height="128"/>
						</logic:notEqual>
						</logic:notEqual>
						</logic:notEqual>
						<!--end其它学校-->
<!--						<p style="color:#0F5DC1">类型：jpg<br />-->
<!--       						容量：小于100k<br />大小：96*128像素</p>-->
					    </div>
					</td>
			      </tr>
				  <tr>
					<th>性别</th>
					<td>
						${rs.xb}
					</td>
					<th>出生日期</th>
					<td>
						${rs.csrq}
					</td>
				</tr>
				<tr>
					<th>民族</th>
					<td>
						${rs.mzmc}
					</td>
					<th>政治面貌</th>
					<td>
						${rs.zzmmmc}
					</td>
				</tr>
				<tr>
					<th>身份证号</th>
					<td align="left">
						${rs.sfzh}
					</td>
					<th>培养层次</th>
					<td align="left">
						<logic:equal value="10491" name="xxdm">
							${rs.pycc}
						</logic:equal>
						<logic:notEqual value="10491" name="xxdm">
							<logic:equal value="new" name="edition">
								${rs.pyccmc}
							</logic:equal>
							<logic:notEqual value="new" name="edition">
								${rs.pycc}
							</logic:notEqual>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>籍贯</th>
					<td colspan="3">
						${rs.jg}			
					</td>
				</tr>
				<tr>
					<th>来源地区(生源地)</th>
					<td colspan="3">
						${rs.lydq}						
					</td>
				</tr>
			</tbody>
		</table>
		
		<table width="100%" class="formlist">
			<tbody style="display: none" id="mk_xsxx">
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td  bgcolor="#CCCCCC">
								<div id="div_xsxx" style="color:blue;cursor:hand"
									onclick="document.all.xsxx.style.display=(document.all.xsxx.style.display =='none')?'':'none'">
									<div align="center" class="style1 style3">
										<strong>学生基本信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td>
			<!--学生基本信息-->
				<%@ include file="/xsxx/common/xsxx_jbxx.jsp"%>
			</td></tr>
			
			</tbody>
		
			<tbody style="display: none" id="mk_jtxx">
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="div_jtxx" style="color:blue;cursor:hand"
									onclick="document.all.jtxx.style.display=(document.all.jtxx.style.display =='none')?'':'none';getJtxxInfo();">
									<div align="center" class="style1 style3">
										<strong>家庭信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<!--家庭信息	-->
			<tr>
				<td>
					<%@ include file="/xsxx/common/xsxx_jtxx.jsp"%>
				</td>
			</tr>
			</tbody>
			
			<tbody style="display: none" id="mk_dtjs">
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="div_dtjs" style="color:blue;cursor:hand"
									onclick="document.all.dtjs.style.display=(document.all.dtjs.style.display =='none')?'':'none';getSxjyInfo();">
									<div align="center" class="style1 style3">
										<strong>党团建设</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>			
			<!--党团建设-->
			<tr><td>
				<%@ include file="/xsxx/common/xsxx_dtjs.jsp"%>
			</td></tr>
			</tbody>
			
			<tbody style="display: none" id="mk_pjpy">
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="div_pjpy" style="color:blue;cursor:hand"
									onclick="document.all.pjpy.style.display=(document.all.pjpy.style.display =='none')?'':'none';getPjpyInfo();">
									<div align="center" class="style1 style3">
										<strong>评奖评优</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<!--评奖评优-->
			<tr>
				<td>
					<%@ include file="/xsxx/common/xsxx_pjpy.jsp"%>
				</td>
			</tr>
			</tbody>
			
			<tbody style="display: none" id="mk_dwjl">
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="div_dwjl" style="color:blue;cursor:hand"
									onclick="if(document.all.dwjl){document.all.dwjl.style.display=(document.all.dwjl.style.display =='none')?'':'none';getDwjlInfo();}">
									<div align="center" class="style1 style3">
										<strong>对外交流</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<!--对外交流-->
			<tr>
				<td>
					<%@ include file="/xsxx/common/xsxx_dwjl.jsp"%>	
				</td>
			</tr>
			
			</tbody>
			
			<tbody style="display: none" id="mk_xszz">
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<logic:equal value="isXcxy" name="isXcxy">
									<div id="div_xszz" style="color:blue;cursor:hand"
										onclick="if(document.all.xszz){document.all.xszz.style.display=(document.all.xszz.style.display =='none')?'':'none';getXcxyXszzInfo();}">
										<div align="center" class="style1 style3">
											<strong>学生资助</strong>
										</div>
									</div>
								</logic:equal>
								<logic:notEqual value="isXcxy" name="isXcxy">
									<div id="div_xszz" style="color:blue;cursor:hand"
										onclick="if(document.all.xszz){document.all.xszz.style.display=(document.all.xszz.style.display =='none')?'':'none';}">
										<div align="center" class="style1 style3">
											<strong>学生资助</strong>
										</div>
									</div>
								</logic:notEqual>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<!--学生资助-->
			<tr>
				<td>
					<%@ include file="/xsxx/common/xsxx_xszz.jsp"%>
				</td>
			</tr>
			</tbody>
			
			<tbody style="display: none" id="mk_qgzx">
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="div_qgzx" style="color:blue;cursor:hand"
									onclick="if(document.all.qgzx){document.all.qgzx.style.display=(document.all.qgzx.style.display =='none')?'':'none';getQgzxInfo();}">
									<div align="center" class="style1 style3">
										<strong>勤工助学</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<!--勤工助学-->
			<tr>
				<td>
					<%@ include file="/xsxx/common/xsxx_qgzx.jsp"%>
				</td>
			</tr>
			</tbody>
			
			<tbody style="display: none" id="mk_xljk">
			<logic:notEqual value="stu" name="userType" scope="session">
				<tr>
					<td>
						<table width="100%" class="formlist">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="div_xljk" style="color:blue;cursor:hand"
										onclick="if(document.all.xljk){document.all.xljk.style.display=(document.all.xljk.style.display =='none')?'':'none';getXljkInfo();}">
										<div align="center" class="style1 style3">
											<strong>心理健康</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<!--心理健康-->
				<tr><td>
					<%@ include file="/xsxx/common/xsxx_xljk.jsp"%>
				</td></tr>
				
			</logic:notEqual>
			</tbody>
			
			<tbody style="display: none" id="mk_jygl">
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="div_jygl" style="color:blue;cursor:hand"
									onclick="document.all.jygl.style.display=(document.all.jygl.style.display =='none')?'':'none';getJyglInfo();">
									<div align="center" class="style1 style3">
										<strong>就业管理</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<!--家庭信息	-->
			<tr>
				<td>
					<%@ include file="/xsxx/common/xsxx_jygl.jsp"%>
				</td>
			</tr>
			</tbody>
			
			<tbody style="display: none" id="mk_xsjx">
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="div_xsjx" style="color:blue;cursor:hand"
									onclick="if(document.all.xsjx){document.all.xsjx.style.display=(document.all.xsjx.style.display =='none')?'':'none';getXsjxInfo();}">
									<div align="center" class="style1 style3">
										<strong>学生军训</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<!--军训管理-->
			<tr><td>
				<%@ include file="/xsxx/common/xsxx_jxgl.jsp"%>
			</td></tr>
			</tbody>
			
			<tbody style="display: none" id="mk_wjcf">
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="div_wjcf" style="color:blue;cursor:hand"
									onclick="if(document.all.wjcf){document.all.wjcf.style.display=(document.all.wjcf.style.display =='none')?'':'none';getWjcfInfo();}">
									<div align="center" class="style1 style3">
										<strong>违纪处分</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<!--违纪处分-->
			<tr><td>
				<%@ include file="/xsxx/common/xsxx_wjcf.jsp"%>
			</td></tr>
			</tbody>
			
			<tbody style="display: none" id="mk_xscj">
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="div_xscj" style="color:blue;cursor:hand"
									onclick="if(document.all.xscj){document.all.xscj.style.display=(document.all.xscj.style.display =='none')?'':'none';getXycjdInfo();}">
									<div align="center" class="style1 style3">
										<strong>学生成绩</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<!--学生成绩-->
			<tr><td>
				<%@ include file="/xsxx/common/xsxx_xscjd.jsp"%>
			</td></tr>
			</tbody>
			
			<tbody style="display: none" id="mk_bzrpy">
				<tr>
					<td>
						<table width="100%" class="formlist">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="div_bzrpy" style="color:blue;cursor:hand"
										onclick="if(document.all.bzrpy){document.all.bzrpy.style.display=(document.all.bzrpy.style.display =='none')?'':'none';getBzrpyInfo();}">
										<div align="center" class="style1 style3">
											<strong>学生评语</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<!--学生成绩-->
				<tr>
					<td>
						<%@ include file="/xsxx/common/xsxx_bzrpy.jsp"%>
					</td>
				</tr>
			</tbody>
			
			<logic:present name="showhzy">
				<tr>
					<td>
						<table width="100%" class="formlist">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="div_xscj" style="color:blue;cursor:hand"
										onclick="if(document.all.xscjd){document.all.xscjd.style.display=(document.all.xscjd.style.display =='none')?'':'none';getXycjdInfo()}">
										<div align="center" class="style1 style3">
											<strong>学业成绩单</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<!--学业成绩-->
				<tr><td>
					<%@ include file="/xsxx/common/xsxx_xscjd.jsp"%>
				</td></tr>
				<tr>
					<td>
						<table width="100%" class="formlist">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main11" style="color:blue;cursor:hand"
										onclick="if(document.all.xssfxx){document.all.xssfxx.style.display=(document.all.xssfxx.style.display =='none')?'':'none';getChargeInfo()}">
										<div align="center" class="style1 style3">
											<strong>收费信息</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<!--收费信息-->
			<tr><td>
				<%@ include file="/xsxx/common/xsxx_sfxx.jsp"%>
			</td></tr>
			</logic:present>

			<logic:present name="showXsgy">
				<%--		======================			--%>
				<tbody style="display: none" id="mk_gygl">
					<tr>
						<td>
							<table width="100%" class="formlist">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="div_gygl" style="color:blue;cursor:hand"
											onclick="if(document.all.gygl){document.all.gygl.style.display=(document.all.gygl.style.display =='none')?'':'none';getXsgyInfo()}">
											<div align="center" class="style1 style3">
												<strong>公寓管理</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<%@ include file="/xsxx/common/xsxx_gygl.jsp"%>
						</td>
					</tr>
			</tbody>
				<%--		======================		--%>
			</logic:present>

			<%--中国美院--%>
			<logic:equal value="10355" name="xxdm">
				<tr>
					<td>
						<table width="100%" class="formlist">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main24" style="color:blue;cursor:hand"
										onclick="if(document.all.zgmyxscj){document.all.zgmyxscj.style.display=(document.all.zgmyxscj.style.display =='none')?'':'none';getXskccjInfo();}">
										<div align="center" class="style1 style3">
											<strong>学生成绩</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<!--学生成绩-->
				<tr><td>
					<%@ include file="/xsxx/common/xsxx_xscj.jsp"%>
				</td></tr>
			</logic:equal>
			<%--end中国美院--%>

			<%--乐山师范学院--%>
			<logic:equal value="10649" name="xxdm">
				<tr>
					<td>
						<table width="100%" class="formlist">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main24" style="color:blue;cursor:hand"
										onclick="if(document.all.lssfxscj){document.all.lssfxscj.style.display=(document.all.lssfxscj.style.display =='none')?'':'none';getXskccjInfo();}">
										<div align="center" class="style1 style3">
											<strong>学生成绩</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<!--学生成绩-->
				<tr><td>
					<%@ include file="/xsxx/common/xsxx_xscj.jsp"%>
				</td></tr>
			</logic:equal>
			<%--end乐山师范学院--%>
			<%--江苏信息职业技术学院--%>
			<logic:equal value="13108" name="xxdm">
				<tr>
					<td>
						<table width="100%" class="formlist">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main27" style="color:blue;cursor:hand"
										onclick="if(document.all.jsxxsztz){document.all.jsxxsztz.style.display=(document.all.jsxxsztz.style.display =='none')?'':'none';getTzRzXx();}">
										<div align="center" class="style1 style3">
											<strong>素质拓展</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<!--素质拓展-->
				<%@ include file="/xsxx/common/xsxx_sztz.jsp"%>
			</logic:equal>
			<%--西南民族大学--%>
			<logic:present name="isXNMZ">
				<tr>
					<td>
						<table width="100%" class="formlist">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main26" style="color:blue;cursor:hand"
										onclick="if(document.all.xnmzsztz){document.all.xnmzsztz.style.display=(document.all.xnmzsztz.style.display =='none')?'':'none';getTzBjJy();}">
										<div align="center" class="style1 style3">
											<strong>素质拓展</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<!--素质拓展-->
				<%@ include file="/xsxx/common/xsxx_sztz.jsp"%>
			</logic:present>

			<%--宁波职业技术学院--%>
			<logic:present name="isNbzyjsxy">
				<tr>
					<td>
						<table width="100%" class="formlist">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main28" style="color:blue;cursor:hand"
										onclick="if(document.all.sztz){document.all.sztz.style.display=(document.all.sztz.style.display =='none')?'':'none';getTzHd();}">
										<div align="center" class="style1 style3">
											<strong>素质拓展</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<!--素质拓展-->
				<%@ include file="/xsxx/common/xsxx_sztz.jsp"%>
			</logic:present>

			<%--西昌学院--%>
			<logic:present name="isXcxy">
				<tr>
					<td>
						<table width="100%" class="formlist">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main27" style="color:blue;cursor:hand"
										onclick="if(document.all.bzrfdy){document.all.bzrfdy.style.display=(document.all.bzrfdy.style.display =='none')?'':'none';getBDinfo();}">
										<div align="center" class="style1 style3">
											<strong>班主任/辅导员</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
				</tr>
				<!--思政队伍-->
				<%@ include file="/xsxx/common/xsxx_szdw.jsp"%>
			</logic:present>
			<tbody id="mk_qtsj" style="display: none">
				<tr>
					<td>
						<table width="100%" class="formlist">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="div_qtsj" style="color:blue;cursor:hand"
										onclick="if(document.all.qtsj){document.all.qtsj.style.display=(document.all.qtsj.style.display =='none')?'':'none';getQtsjInfo();}">
										<div align="center" class="style1 style3">
											<logic:equal value="12872" name="xxdm">
												<strong>保险理赔</strong>
											</logic:equal>
											<logic:notEqual value="12872" name="xxdm">
												<strong>其他数据</strong>
											</logic:notEqual>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<!--其它数据-->
				<tr>
				<td>
					<%@ include file="/xsxx/common/xsxx_qtsj.jsp"%>
				</td>
				</tr>
			</tbody>
			
			<logic:equal name="xxdm" value="10333">
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main30" style="color:blue;cursor:hand"
									onclick="if(document.all.xxjl){document.all.xxjl.style.display=(document.all.xxjl.style.display =='none')?'':'none';getXsxxjlInfo();}">
									<div align="center" class="style1 style3">
											<strong>学习经历</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<!--学习经历-->
			<tr><td>
				<%@ include file="/xsxx/common/xsxx_xxjl.jsp"%>
			</td></tr>
			</logic:equal>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4">
		          <div class="btn noPrin">
		            <input type='button' value='页面设置' onclick="try{WebBrowser.ExecWB(8,1)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}">
					<input type='button' value='打印预览' onclick="try{WebBrowser.ExecWB(7,1)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}">
					<input type='button' value='直接打印' onclick="try{WebBrowser.ExecWB(6,6)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}">
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		</div>
	</body>
</html>
