<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/studetailFunction.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/dwjlgl.js'></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/prototype-1.6.0.3.js"></script>
		<script type="text/javascript">
			function deploy(id){
				document.getElementById(id).style.display = (document.getElementById(id).style.display == 'none') ? '' : 'none';  
			}	
			
			function loadchange(){
				var tab = document.getElementById("titName").value;
				if(tab != ""){
					changePage($(tab+'_a'));
				}
		    }
			 function changePage(obj){
		    	var id = obj.parentNode.id;
		    	obj.parentNode.className = 'ha';
		    
		    	var nodeId = obj.id.substring(0,obj.id.length-2);
		    	
		    	var elements = ele('ul1').children;
		    	for(var i=0; i<elements.length; i++){
		    		if(elements[i].id!= id){
		    			elements[i].className = '';
		    			var trId = elements[i].id.substring(0,elements[i].id.length-2);
		    			if(document.getElementById(trId)){
		    				document.getElementById(trId).style.display = "none";	    			
		    			}
		    		}
		    	}
		    	elements = ele('dropDownUl').children;
		    	for(var i=0; i<elements.length; i++){
		    		if(elements[i].id!= id){
		    			elements[i].className = '';
		    			var trId = elements[i].id.substring(0,elements[i].id.length-2);
		    			if(document.getElementById(trId)){
		    				document.getElementById(trId).style.display = "none";	    			
		    			}
		    		}
		    	}
		    	
				if(document.getElementById(nodeId)){
					document.getElementById(nodeId).style.display = "";
					loadData(nodeId);
				}    	
		    }
		    
		    function loadData(id){
		    	if(id == "xjyd"){
		    		getXjydxx();
		    	}
		    	if(id == "jtxx"){
		    		getJtxxInfo();
		    	}
		    	if(id == "dtjs"){
		    		getSxjyInfo();
		    	}
		    	if(id == "pjpy"){
		    		getPjpyInfo();
		    	}
		    	if(id == "dwjl"){
		    		getDwjlInfo();
		    	}
		    	if(id == "qgzx"){
		    		getQgzxInfo();
		    	}
		    	if(id == "xljk"){
		    		getXljkInfo();
		    	}
		    	if(id == "xsjx"){
		    		getXsjxInfo();
		    	}
		    	if(id == "qtsj"){
		    		getQtsjInfo();
		    	}
		    	if(id == "wjcf"){
		    		getWjcfInfo();
		    	}
		    	if(id == "xsgy"){
		    		getXsgyInfo();
		    	}
		    	if(id == "sztz"){
			    	getTzHd();
		    	}
		    	if(id == "bzrfdy"){
		    		getBDinfo();
		    	}
		    	if(id == "xnmzsztz"){
		    		getTzBjJy();
		    	}
		    	if(id == "xycjd"){
		    		getXycjdInfo();
		    	}
		    	if(id == "jsxxsztz"){
		    		getTzRzXx();
		    	}
		    	if(id == "lssfxscj"){
		    		getXskccjInfo();
		    	}
		    	if(id == "zgmyxscj"){
		    		getXskccjInfo();
		    	}
		    	if(id == "xssfxx"){
		    		getChargeInfo();
		    	}
		    	if(id == "xscj"){
		    		getXscjInfo();
		    	}
		    	if(id == "jfqk"){
		    		getXsjfqkInfo();
		    	}
		    	if(id == "xxjl"){
		    		getXsxxjlInfo();
		    	}
		    	if(id == "jygl"){
		    		getJyglInfo();
			    }
			    if(id == "zcqk"){
		    		getZcqkInfo();
			    }
			    if(id == "bzrpy"){
		    		getBzrpyInfo();
			    }
		    }
		    
		    jQuery(function(){
		    	jQuery('a',jQuery('li',jQuery('#ul1')).eq(0)).click();
		    })
		    
		</script>
	</head>
	<body onload="" >
		<html:form action="/general_xsxx" method="post">
			<input type="hidden" id="xh" name="xh" value="${rs.xh }" />
			<input type="hidden" name="zzxh" id="zzxh" value="${xh }" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<input type="hidden" id="titName" name="titName" value="${mrxs }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- style="width:100%;height:520px;overflow-x:hidden;overflow-y:auto;" -->
			<div class="tab" >
				<!-- 基本信息 -->
				<table width="100%" border="0" class="formlist">	
					<thead>
						<tr>
							<th colspan="5" style="cursor:hand" onclick="hiddenMk('tb_jbxx')">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_jbxx">
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="24%">
								${rs.xh }
							</td>
							<th width="15%">
								姓名
							</th>
							<td width="25%">
								${rs.xm }
							</td>
							<td rowspan="6">
								<div id="stuImg">
									<img style="width:120px;height:160px" 
										src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }"
										border="0">
								</div>
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								${rs.xb }
							</td>
							<th>
								出生日期
							</th>
							<td>
								${rs.csrq}
							</td>
						</tr>
						<tr>
							<th>
								民族
							</th>
							<td>
								${rs.mzmc }
							</td>
							<th>
								政治面貌
							</th>
							<td>
								${rs.zzmmmc }
							</td>
						</tr>
						<tr>
							<th>
								身份证号
							</th>
							<td>
								${rs.sfzh }
							</td>
							<th>
								
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								籍贯
							</th>
							<td colspan="3">
								${rs.jgmc }
							</td>
						</tr>
						<tr>
							<th>
								来源地区(生源地)
							</th>
							<td colspan="3">
								${rs.sydqmc }
							</td>
						</tr>
					</tbody>
			    </table>
		   		<!-- 基本信息  end-->		    
			    
			    <logic:present name="pages">
				<table width="97%" align="center" class="formlist breakword">
					<thead>
						<tr>
							<th colspan="5">
								<span>模块信息</span>
							</th>
						</tr>
					</thead>
				</table>
				<div class="menu" id="nav">
					<div class="compTab" id="card" style="position:relative">
						<div class="comp_title" id="div1">
							<ul id="ul1">
								<logic:notEmpty name="pages">
									<logic:iterate id="card" name="pages" scope="request"
										indexId="s" offset="0" length="6">
										<li id="${card.en}li">
											<a href="#" onclick="changePage(this)" id="${card.en}_a">
												<span>${card.cn}</span> </a>
										</li>
									</logic:iterate>
								</logic:notEmpty>
							</ul>
						</div>
						<div class="morediv" id="morediv"
							style="position:absolute;top:0px;right:30px;text-align:center"
							onmousemove="javascript:document.getElementById('morelist').style.display='block'"
							onmouseout="javascript:document.getElementById('morelist').style.display='none'">
							<p class="more"></p>
							<div class="morelist" id="morelist" style="display:none;">
								<!--[if lte IE 6]><iframe class=navbug></iframe><![endif]-->
								<ul id="dropDownUl">
									<logic:notEmpty name="pages">
										<logic:iterate id="card" name="pages" scope="request"
											indexId="s" offset="6" >
											<li id="${card.en}li">
												<a href="#" onclick="changePage(this)" id="${card.en}_a">
													<span>${card.cn}</span> </a>
											</li>
										</logic:iterate>
									</logic:notEmpty>
								</ul>
							</div>
						</div>
					</div>
				</div>


				<div class="tab" id="tab">
					<!--学生基本信息-->
					<%@ include file="/xsgzgl/xsxx/zjjtzyjsxy/zxxs/zjjtzy_xsxx.jsp"%>
					<!--党团建设-->
					<%@ include file="/xsxx/common/xsxx_dtjs.jsp"%>
					<!--评奖评优-->
					<%@ include file="/xsxx/common/xsxx_pjpy_zp.jsp"%>
					<!--对外交流-->
					<%@ include file="/xsxx/common/xsxx_dwjl.jsp"%>
					<!--学生资助-->
					<%@ include file="/xsxx/common/xsxx_xszz.jsp"%>
					<!--勤工助学-->
					<%@ include file="/xsxx/common/xsxx_qgzx.jsp"%>
					<!--心理健康-->
					<%@ include file="/xsxx/common/xsxx_xljk.jsp"%>
					<!--军训管理-->
					<%@ include file="/xsxx/common/xsxx_jxgl.jsp"%>
					<!--违纪处分-->
					<%@ include file="/xsxx/common/xsxx_wjcf.jsp"%>
					<!--学生成绩-->
					<%@ include file="/xsxx/common/xsxx_xscj.jsp"%>
					<!--学业成绩-->
					<%@ include file="/xsxx/common/xsxx_xscjd.jsp"%>
					<!--收费信息-->
					<%@ include file="/xsxx/common/xsxx_sfxx.jsp"%>
					<!--公寓管理-->
					<%@ include file="/xsxx/common/xsxx_gygl.jsp"%>
					<!--素质拓展-->
					<%@ include file="/xsxx/common/xsxx_sztz.jsp"%>
					<!--思政队伍-->
					<%@ include file="/xsxx/common/xsxx_szdw.jsp"%>
					<!--其它数据-->
					<%@ include file="/xsxx/common/xsxx_qtsj.jsp"%>
					<!--学生学习经历-->
					<%@ include file="/xsxx/common/xsxx_xxjl.jsp"%>
					<!--学生就业管理-->
					<%@ include file="/xsxx/common/xsxx_jygl.jsp"%>
					<!--学生注册情况-->
					<%@ include file="/xsxx/common/xsxx_zcqk.jsp"%>
					<!--班主任评语-->
					<%@ include file="/xsxx/common/xsxx_bzrpy.jsp"%>
					<!--学籍异动-->
					<%@ include file="/xsxx/common/xsxx_comm_xjyd.jsp"%>
				</logic:present>
			    
			    
			    
		    </div>
		    <div>
		    	<table width="100%" border="0" class="formlist">
			    	<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" name="关闭" onclick="Close();return false;">关 闭</button>
								</div>
							</td>
						</tr>
				    </tfoot>
			    </table>
			</div>
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>