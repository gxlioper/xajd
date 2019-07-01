<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>	
	<script>
		function initXgzd(){
			//已经修改过的字段标为红色
			var yxgzd = val('yxgzd');
			var yxgzdArr = yxgzd.split('@!!@');
			for(var i=0; i<yxgzdArr.length; i++){
				if(yxgzdArr[i] != null && yxgzdArr[i] != ''){					
					document.getElementById("f_"+yxgzdArr[i]).className = "red";					
				}
			}
		}
	</script>
</head>
	<body onload="initXgzd()">
		<html:form action="/xsxx_zgkd.do" method="post">
			<input type="hidden" id="yxgzd" value="${xgzd}"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>		
			<div class="tab">	
			<table class="formlist" id="rsTable">
				<thead>			
					<tr>
						<th colspan="6" onclick="document.getElementById('jbxxB').style.display=(document.getElementById('jbxxB').style.display==''?'none':'');document.getElementById('jbxxT').style.display=(document.getElementById('jbxxT').style.display==''?'none':'')">
							<span>基本信息修改</span>
						</th>
					</tr>			
					<tr id="jbxxT">
						<th width="15%">
							字段名
						</th>
						<th>
							修改后
						</th>
						<th>
							修改前
						</th>
						<th width="15%">
							字段名
						</th>
						<th>
							修改后
						</th>
						<th>
							修改前
						</th>
					</tr>
				</thead>
				<tbody id="jbxxB">
					<tr>
						<th id="f_xh">学号</th>
						<td>
							<bean:write name="newValue" property="xh"/>		 
							<html:hidden property="xh" name="oldValue"/>							
						</td>
						<td>
							<bean:write name="oldValue" property="xh"/>
						</td>
						<th id="f_xm">姓名</th>
						<td>
							<bean:write name="newValue" property="xm"/>
						</td>
						<td>
							<bean:write name="oldValue" property="xm"/>
						</td>
					</tr>
					<tr>
						<th id="f_xb">性别</th>
						<td>
							<bean:write name="newValue" property="xb"/>
						</td>
						<td>
							<bean:write name="oldValue" property="xb"/>
						</td>
						<th id="f_csrq">出生日期</th>
						<td>
							<bean:write name="newValue" property="csrq"/>
						</td>
						<td>
							<bean:write name="oldValue" property="csrq"/>
						</td>
					</tr>
					<tr>
						<th id="f_mz">民族</th>
						<td>
							${newValue.mzmc}							
						</td>
						<td>
							${oldValue.mzmc}
						</td>
						<th id="f_zzmm">政治面貌</th>
						<td>
							${newValue.zzmmmc}
						</td>
						<td>
							${oldValue.zzmmmc}
						</td>
					</tr>
					<tr>
						<th id="f_sfzh">身份证号</th>
						<td>
							<bean:write name="newValue" property="sfzh"/>
						</td>
						<td>
							<bean:write name="oldValue" property="sfzh"/>
						</td>
						<th id="f_pycc">
							培养层次
						</th>
						<logic:equal value="new" name="edition">
							<td>
								<bean:write name="newValue" property="pyccmc"/>
							</td>
							<td>
								<bean:write name="oldValue" property="pyccmc"/>
							</td>
						</logic:equal>
						<logic:notEqual value="new" name="edition">
							<td>
								<bean:write name="newValue" property="pycc"/>
							</td>
							<td>
								<bean:write name="oldValue" property="pycc"/>
							</td>
						</logic:notEqual>									
					</tr>
					<tr>
						<th id="f_jg">
							籍贯
						</th>			
						<td>
							<bean:write name="newValue" property="jg"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jg"/>
						</td>
						<th id="f_syd">
							来源地区
						</th>
						<td>
							<bean:write name="newValue" property="syd"/>
						</td>
						<td>
							<bean:write name="oldValue" property="syd"/>
						</td>				
					</tr>
				</tbody>

				<thead>			
				<tr>
					<th colspan="6" onclick="document.getElementById('xjxxB').style.display=(document.getElementById('xjxxB').style.display==''?'none':'');document.getElementById('xjxxT').style.display=(document.getElementById('xjxxT').style.display==''?'none':'')">
						<span>学籍信息</span>
					</th>
				</tr>			
				<tr id="xjxxT">
					<th>
						字段名
					</th>
					<th>
						修改后
					</th>
					<th>
						修改前
					</th>
					<th>
						字段名
					</th>
					<th>
						修改后
					</th>
					<th>
						修改前
					</th>
				</tr>
				</thead>

				<tbody id="xjxxB">
					<tr>
						<th id="f_nj">年级</th>
						<td>
							<bean:write name="newValue" property="nj"/>
						</td>			
						<td>
							<bean:write name="oldValue" property="nj"/>
						</td>
						<th id="f_xz">学制(年)</th>
						<td>
							<bean:write name="newValue" property="xz"/>
						</td>
						<td>
							<bean:write name="oldValue" property="xz"/>
						</td>				
					</tr>
		
					<tr>
						<th id="f_xydm"><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<bean:write name="newValue" property="xymc"/>
						</td>
						<td>
							<bean:write name="oldValue" property="xymc"/>
						</td>
						<th id="f_sfzc">是否注册</th>
						<td>
							<bean:write name="newValue" property="sfzc"/>
						</td>
						<td>
							<bean:write name="oldValue" property="sfzc"/>
						</td>				
					</tr>
					
					<tr>
						<th id="f_zydm">专业</th>
						<td>
							<bean:write name="newValue" property="zymc"/>
						</td>
						<td>
							<bean:write name="oldValue" property="zymc"/>
						</td>
						<th id="f_sfzd">是否走读</th>
						<td>
							<bean:write name="newValue" property="sfzd"/>
						</td>
						<td>
							<bean:write name="oldValue" property="sfzd"/>
						</td>
					</tr>
					<tr>
						<th id="f_bjdm">班级</th>
						<td>
							<bean:write name="newValue" property="bjmc"/>
						</td>
						<td>
							<bean:write name="oldValue" property="bjmc"/>
						</td>
						<th id="f_sfzx">是否在校</th>
						<td>
							<bean:write name="newValue" property="sfzx"/>
						</td>
						<td>
							<bean:write name="oldValue" property="sfzx"/>
						</td>
					</tr>	
					<tr>
						<th id="f_xjztm">学籍状态</th>
						<td>
							<bean:write name="newValue" property="xjztm"/>
						</td> 
						<td>
							<bean:write name="oldValue" property="xjztm"/>
						</td>
					    <th id="f_sfbys">是否毕业生</th>
						<td>
							<bean:write name="newValue" property="sfbys"/>
						</td> 
						<td>
							<bean:write name="oldValue" property="sfbys"/>
						</td>
					</tr>
					<tr>
						<th id="f_rxrq">入学时间</th>
						<td>
							<bean:write name="newValue" property="rxsj"/>
							${newValue.rxrq }
						</td> 
						<td>
							<bean:write name="oldValue" property="rxsj"/>
							${oldValue.rxrq }
						</td>
					    <th id="f_sfyby">是否毕业</th>
						<td>
							<bean:write name="newValue" property="sfyby"/>
						</td> 
						<td>
							<bean:write name="oldValue" property="sfyby"/>
						</td>
					</tr>
					<tr>
						<th id="f_byny">毕业时间</th>
						<td>
							<bean:write name="newValue" property="byny"/>
						</td> 
						<td>
							<bean:write name="oldValue" property="byny"/>
						</td>
					    <th></th>
						<td>
							
						</td> 
						<td>
							
						</td>
					</tr>
				</tbody>

				<thead>			
					<tr>
						<th colspan="6" onclick="document.getElementById('lxfsxxB').style.display=(document.getElementById('lxfsxxB').style.display==''?'none':'');document.getElementById('lxfsxxT').style.display=(document.getElementById('lxfsxxT').style.display==''?'none':'')">
							<span>联系方式及证件</span>
						</th>
					</tr>			
					<tr id="lxfsxxT" style="display: none">
						<th>
							字段名
						</th>
						<th>
							修改后
						</th>
						<th>
							修改前
						</th>
						<th>
							字段名
						</th>
						<th>
							修改后
						</th>
						<th>
							修改前
						</th>
					</tr>
				</thead>

				<tbody id="lxfsxxB" style="display: none">
					<tr>
						<th id="f_lxdh">
							联系电话
						</th>
						<td>
							<bean:write name="newValue" property="lxdh"/>
						</td>
						<td>
							<bean:write name="oldValue" property="lxdh"/>
						</td>
						<th id="f_sjhm">
							手机号码
						</th>			
						<td>
							<bean:write name="newValue" property="sjhm"/>
						</td>
						<td>
							<bean:write name="oldValue" property="sjhm"/>
						</td>
					</tr>
					<tr>
						<th id="f_qqhm">
							QQ号码
						</th>
						<td>
							<bean:write name="newValue" property="qqhm"/>
						</td>
						<td>
							<bean:write name="oldValue" property="qqhm"/>
						</td>
						<th id="f_dzyx">
							电子邮箱
						</th>			
						<td>
							<bean:write name="newValue" property="dzyx"/>
						</td>
						<td>
							<bean:write name="oldValue" property="dzyx"/>
						</td>
					</tr>
					<tr>
						<th id="f_yhdm">
							银行名称
						</th>
						<td>
							<bean:write name="newValue" property="yhmc"/>
						</td>
						<td>
							<bean:write name="oldValue" property="yhmc"/>
						</td>
						<th id="f_yhkh">
							银行卡号
						</th>			
						<td>
							<bean:write name="newValue" property="yhkh"/>
						</td>
						<td>
							<bean:write name="oldValue" property="yhkh"/>
						</td>
					</tr>
					<tr>
						<th id="f_kh">
							一卡通号
						</th>
						<td>
							<bean:write name="newValue" property="kh"/>
						</td>
						<td>
							<bean:write name="oldValue" property="kh"/>
						</td>
						<th>
							
						</th>			
						<td>
							
						</td>
						<td>
							
						</td>
					</tr>
				</tbody>

				<thead>			
					<tr>
						<th colspan="6" onclick="document.getElementById('qtxxB').style.display=(document.getElementById('qtxxB').style.display==''?'none':'');document.getElementById('qtxxT').style.display=(document.getElementById('qtxxT').style.display==''?'none':'')">
							<span>其它信息</span>
						</th>
					</tr>			
					<tr id="qtxxT" style="display: none">
						<th>
							字段名
						</th>
						<th>
							修改后
						</th>
						<th>
							修改前
						</th>
						<th>
							字段名
						</th>
						<th>
							修改后
						</th>
						<th>
							修改前
						</th>
					</tr>
				</thead>

				<tbody id="qtxxB" style="display: none">
					<tr>
						<th id="f_xmpy">姓名拼音</th>
						<td>
							<bean:write name="newValue" property="xmpy"/>				
						</td>			
						<td>
							<bean:write name="oldValue" property="xmpy"/>
						</td>
						<th id="f_cym">曾用名</th>
						<td>
							<bean:write name="newValue" property="cym"/>
						</td>
						<td>
							<bean:write name="oldValue" property="cym"/>
						</td>
					</tr>
					
					<tr>
						<th id="f_sg">身高(cm)</th>
						<td>
							<bean:write name="newValue" property="sg"/>
						</td>
						<td>
							<bean:write name="oldValue" property="sg"/>
						</td>
						<th id="f_tz">体重(kg)</th>
						<td>
							<bean:write name="newValue" property="tz"/>
						</td>
						<td>
							<bean:write name="oldValue" property="tz"/>
						</td>
					</tr>
					<tr>
						<th id="f_tc">特长</th>
						<td>
							<bean:write name="newValue" property="tc"/>
						</td>
						<td>
							<bean:write name="oldValue" property="tc"/>
						</td>
						<th id="f_kslb">
							考生类别
						</th>
						<logic:equal value="new" name="edition">
							<td>
								<bean:write name="newValue" property="kslbmc"/>
							</td>
							<td>
								<bean:write name="oldValue" property="kslbmc"/>
							</td>
						</logic:equal>
						<logic:notEqual value="new" name="edition">
							<td>
								<bean:write name="newValue" property="kslb"/>
							</td>
							<td>
								<bean:write name="oldValue" property="kslb"/>
							</td>
						</logic:notEqual>						
					</tr>			
					<tr>
						<th id="f_rxfs">
							入学方式
						</th>
						<logic:equal value="new" name="edition">
							<td>
								<bean:write name="newValue" property="rxfsmc"/>
							</td>
							<td>
								<bean:write name="oldValue" property="rxfsmc"/>
							</td>
						</logic:equal>
						<logic:notEqual value="new" name="edition">
							<td>
								<bean:write name="newValue" property="rxfs"/>
							</td>
							<td>
								<bean:write name="oldValue" property="rxfs"/>
							</td>
						</logic:notEqual>
						
						<th id="f_pyfs">
							培养方式
						</th>
						<logic:equal value="new" name="edition">
							<td>
								<bean:write name="newValue" property="pyfsmc"/>
							</td>
							<td>
								<bean:write name="oldValue" property="pyfsmc"/>
							</td>
						</logic:equal>
						<logic:notEqual value="new" name="edition">
							<td>
								<bean:write name="newValue" property="pyfs"/>
							</td>
							<td>
								<bean:write name="oldValue" property="pyfs"/>
							</td>
						</logic:notEqual>						
					</tr>
					<tr>
						<th id="f_hkszd">
							户口所在地
						</th>
						<td>
							<bean:write name="newValue" property="hkszd"/>
						</td>
						<td>
							<bean:write name="oldValue" property="hkszd"/>
						</td>
						<th>
							
						</th>
						<td>
							
						</td>
						<td>
							
						</td>
					</tr>
				</tbody>

				<thead>			
					<tr>
						<th colspan="6" onclick="document.getElementById('jtjbxxB').style.display=(document.getElementById('jtjbxxB').style.display==''?'none':'');document.getElementById('jtjbxxT').style.display=(document.getElementById('jtjbxxT').style.display==''?'none':'')">
							<span>家庭信息</span>
						</th>
					</tr>	
				</thead>

				<tbody id="jtjbxxB" style="display: none">
					<tr ><td colspan="6" width="100%">
						<table class="datalist" width="100%">	
							<thead>	
								<tr id="jtjbxxT" style="display: none">
									<th width="15%">
										字段名
									</th>
									<th>
										修改后
									</th>
									<th>
										修改前
									</th>
									<th width="15%">
										字段名
									</th>
									<th>
										修改后
									</th>
									<th>
										修改前
									</th>
								</tr>
							</thead>
							<tbody>		
								<tr>
									<th id="f_jtszd">
									家庭地址
									</th>
									<td>
										<bean:write name="newValue" property="jtszd"/>
									</td>
									<td>
										<bean:write name="oldValue" property="jtszd"/>
									</td>
									<th>
									</th>
									<td>
									</td>
									<td>
									</td>
								</tr>		
								<tr>
									<th id="f_lxdh1">
										家庭电话
									</th>
									<td>
										<bean:write name="newValue" property="lxdh1"/>
									</td>
									<td>
										<bean:write name="oldValue" property="lxdh1"/>
									</td>
									<th id="f_yb">
										家庭邮编
									</th>
									<td>
										<bean:write name="newValue" property="yb"/>
									</td>
									<td>
										<bean:write name="oldValue" property="yb"/>
									</td>			
								</tr>
								<tr>
									<th id="f_jjzk">
										家庭经济情况
									</th>
									<td>
										<bean:write name="newValue" property="jjzk"/>
									</td>
									<td>
										<bean:write name="oldValue" property="jjzk"/>
									</td>
									<th>
										
									</th>
									<td>
										
									</td>
									<td>
										
									</td>			
								</tr>		
							</tbody>
						</table>
	
						<table class="datalist" width="100%">
							<thead>
								<tr title="点击展开或收缩">
									<td colspan="6" style="cursor:hand" align="center"
										onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
											学生家庭成员信息1
									</td>
								</tr>				
							</thead>
							<tbody>
								<tr id="jt1" style="display: none">
									<td colspan="6">					
										<table width="100%" class="tbstyle">
											<thead>	
												<tr>
													<th width="15%">
														字段名
													</th>
													<th>
														修改后
													</th>
													<th>
														修改前
													</th>
													<th width="15%">
														字段名
													</th>
													<th>
														修改后
													</th>
													<th>
														修改前
													</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<th id="f_jtcy1_xm">
														家庭成员1姓名
													</th>
													<td>
														<bean:write name="newValue" property="jtcy1_xm"/>
													</td>
													<td>
														<bean:write name="oldValue" property="jtcy1_xm"/>
													</td>
													<th id="f_jtcy1_gx">
														本人与家庭成员1关系
													</th>
													<td>
														<bean:write name="newValue" property="jtcy1_gx"/>
													</td>
													<td>
														<bean:write name="oldValue" property="jtcy1_gx"/>
													</td>
												</tr>
											
												<tr>
													<th id="f_jtcy1_nl">
														家庭成员1出生日期
													</th>
													<td>
														<bean:write name="newValue" property="jtcy1_nl"/>
													</td>
													<td>
														<bean:write name="oldValue" property="jtcy1_nl"/>
													</td>
													<th id="f_jtcy1_sfzh">
														家庭成员1身份证号
													</th>
													<td>
														<bean:write name="newValue" property="jtcy1_sfzh"/>
													</td>
													<td>
														<bean:write name="oldValue" property="jtcy1_sfzh"/>
													</td>
												</tr>
											
												<tr>
													<th id="f_jtcy1_mz">
														家庭成员1民族
													</th>
													<td>
														${newValue.jtcy1_mzmc}
													</td>
													<td>
														${oldValue.jtcy1_mzmc}
													</td>
													<th id="f_jtcy1_zzmm">
														家庭成员1政治面貌
													</th>
													<td>
														${newValue.jtcy1_zzmmmc}
													</td>
													<td>
														${oldValue.jtcy1_zzmmmc}
													</td>
												</tr>
											
												<tr>
												<th id="f_jtcy1_zy">
													家庭成员1职业
												</th>
												<td>
													<bean:write name="newValue" property="jtcy1_zy"/>
												</td>
												<td>
													<bean:write name="oldValue" property="jtcy1_zy"/>
												</td>
												<th id="f_jtcy1_zw">
													家庭成员1职务
												</th>
												<td>
													<bean:write name="newValue" property="jtcy1_zw"/>
												</td>
												<td>
													<bean:write name="oldValue" property="jtcy1_zw"/>
												</td>
												</tr>
												
												<tr>
													<th id="f_jtcy1_lxdh1">
														家庭成员1工作单位电话
													</th>
													<td>
														<bean:write name="newValue" property="jtcy1_lxdh1"/>
													</td>
													<td>
														<bean:write name="oldValue" property="jtcy1_lxdh1"/>
													</td>
													<th id="f_jtcy1_lxdh2">
														家庭成员1个人电话
													</th>
													<td>
														<bean:write name="newValue" property="jtcy1_lxdh2"/>
													</td>
													<td>
														<bean:write name="oldValue" property="jtcy1_lxdh2"/>
													</td>			
												</tr>
											
												<tr>
													<th id="f_jtcy1_gzdz">
														家庭成员1工作地址
													</th>
													<td>
														<bean:write name="newValue" property="jtcy1_gzdz"/>
													</td>	
													<td>
														<bean:write name="oldValue" property="jtcy1_gzdz"/>
													</td>	
													<td></td>	
													<td></td>	
													<td></td>
												</tr>	
										</tbody>
									</table>
								</td>
							</tr>
						</table>

						<table class="datalist" width="100%">
							<thead>
								<tr title="点击展开或收缩">
									<td colspan="6" style="cursor:hand" align="center"
										onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
											学生家庭成员信息2
									</td>
								</tr>				
							</thead>
							<tbody>
								<tr id="jt2" style="display: none">
								<td colspan="6">					
								<table width="100%" class="tbstyle">	
								<thead>	
									<tr>
										<th width="15%">
											字段名
										</th>
										<th>
											修改后
										</th>
										<th>
											修改前
										</th>
										<th width="15%">
											字段名
										</th>
										<th>
											修改后
										</th>
										<th>
											修改前
										</th>
									</tr>
								</thead>		
								<tbody>		
								<tr>
								<th id="f_jtcy2_xm">
									家庭成员2姓名
								</th>
								<td>
									<bean:write name="newValue" property="jtcy2_xm"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_xm"/>
								</td>
								<th id="f_jtcy2_gx">
									本人与家庭成员2关系
								</th>

								<td>
									<bean:write name="newValue" property="jtcy2_gx"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_gx"/>
								</td>
								</tr>
								
								<tr>
								<th id="f_jtcy2_nl">
									家庭成员2出生日期
								</th>
								<td>
									<bean:write name="newValue" property="jtcy2_nl"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_nl"/>
								</td>
								<th id="f_jtcy2_sfzh">
									家庭成员2身份证号
								</th>
								<td>
									<bean:write name="newValue" property="jtcy2_sfzh"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_sfzh"/>
								</td>
								</tr>
								
								<tr>
								<th id="f_jtcy2_mz">
									家庭成员2民族
								</th>
								<td>
									${newValue.jtcy2_mzmc}
								</td>
								<td>
									${oldValue.jtcy2_mzmc}
								</td>
								<th id="f_jtcy2_zzmm">
									家庭成员2政治面貌
								</th>
								<td>
									${newValue.jtcy2_zzmmmc}
								</td>
								<td>
									${oldValue.jtcy2_zzmmmc}
								</td>
								</tr>
								
								<tr>
								<th id="f_jtcy2_zy">
									家庭成员2职业
								</th>
								<td>
									<bean:write name="newValue" property="jtcy2_zy"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_zy"/>
								</td>
								<th id="f_jtcy2_zw">
									家庭成员2职务
								</th>
								<td>
									<bean:write name="newValue" property="jtcy2_zw"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_zw"/>
								</td>
								</tr>
									
								<tr>
								<th id="f_jtcy2_lxdh1">
									家庭成员2工作单位电话
								</th>
								<td>
									<bean:write name="newValue" property="jtcy2_lxdh1"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_lxdh1"/>
								</td>
								<th id="f_jtcy2_lxdh2">
									家庭成员2个人电话
								</th>
								<td>
									<bean:write name="newValue" property="jtcy2_lxdh2"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_lxdh2"/>
								</td>
								</tr>
								
								<tr>
								<th id="f_jtcy2_gzdz">
									家庭成员2工作地址
								</th>	
								<td>
									<bean:write name="newValue" property="jtcy2_gzdz"/>
								</td>		
								<td>
									<bean:write name="oldValue" property="jtcy2_gzdz"/>
								</td>	
								<td>		
								</td>
								<td>
								</td>
								<td>
								</td>
								</tr>
								</tbody>
								</table>
								</td>
								</tr>
							</tbody>
						</table>
					<table class="datalist" width="100%">
						<thead>
							<tr title="点击展开或收缩">
								<td colspan="6" style="cursor:hand" align="center"
									onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
									学生家庭成员信息3
								</td>
							</tr>				
						</thead>
						<tbody>
						<tr id="jt3" style="display: none">
						<td colspan="6">					
						<table width="100%" class="tbstyle">
						<thead>	
							<tr>
								<th width="15%">
									字段名
								</th>
								<th>
									修改后
								</th>
								<th>
									修改前
								</th>
								<th width="15%">
									字段名
								</th>
								<th>
									修改后
								</th>
								<th>
									修改前
								</th>
							</tr>
						</thead>			
						<tbody>									
						<tr>
							<th id="f_jtcy3_xm">
								家庭成员3姓名
							</th>
							<td>
								<bean:write name="newValue" property="jtcy3_xm"/>
							</td>
							<td>
								<bean:write name="oldValue" property="jtcy3_xm"/>
							</td>
							<th id="f_jtcy3_gx">
								本人与家庭成员3关系
							</th>
							<td>
								<bean:write name="newValue" property="jtcy3_gx"/>
							</td>
							<td>
								<bean:write name="oldValue" property="jtcy3_gx"/>
							</td>
						</tr>
						
						<tr>
						<th id="f_jtcy3_nl">
							家庭成员3出生日期
						</th>
						<td>
							<bean:write name="newValue" property="jtcy3_nl"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jtcy3_nl"/>
						</td>
						<th id="f_jtcy3_sfzh">
							家庭成员3身份证号
						</th>
						<td>
							<bean:write name="newValue" property="jtcy3_sfzh"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jtcy3_sfzh"/>
						</td>
						</tr>
						
						<tr>
						<th id="f_jtcy3_mz">
							家庭成员3民族
						</th>
						<td>
							${newValue.jtcy3_mzmc}
						</td>
						<td>
							${oldValue.jtcy3_mzmc}
						</td>
						<th id="f_jtcy3_zzmm">
							家庭成员3政治面貌
						</th>
						<td>
							${newValue.jtcy3_zzmmmc}
						</td>
						<td>
							${oldValue.jtcy3_zzmmmc}
						</td>
						</tr>
						
						<tr>
						<th id="f_jtcy3_zy">
							家庭成员3职业
						</th>
						<td>
							<bean:write name="newValue" property="jtcy3_zy"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jtcy3_zy"/>
						</td>
						<th id="f_jtcy3_zw">
							家庭成员3职务
						</th>
						<td>
							<bean:write name="newValue" property="jtcy3_zw"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jtcy3_zw"/>
						</td>			
						</tr>
							
						<tr>
						<th id="f_jtcy3_lxdh1">
						    家庭成员3工作单位电话
						</th>
						<td>
							<bean:write name="newValue" property="jtcy3_lxdh1"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jtcy3_lxdh1"/>
						</td>
						<th id="f_jtcy3_lxdh2">
							家庭成员3个人电话
						</th>
						<td>
							<bean:write name="newValue" property="jtcy3_lxdh2"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jtcy3_lxdh2"/>
						</td>
						</tr>
						
						<tr>
						<th id="f_jtcy3_gzdz">
							家庭成员3工作地址
						</th>
						<td>
							<bean:write name="newValue" property="jtcy3_gzdz"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jtcy3_gzdz"/>
						</td>	
						<td>		
						</td>
						<td>
						</td>
						<td>
						</td>
						</tr>	
						</tbody>
						</table>
						</td>
						</tr>
						</tbody>
					</table>
					</td></tr>	
				</tbody>
			
			
			
			<tfoot>
				
		      <tr>
		        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项; 红色字体标志该字段修改过</div>
		          <div class="btn">
		            <logic:equal value="yes" name="writeAble">
					<button type="button"						
						onclick="refreshForm('xsxx_zgkd.do?method=saveAutidting&yesNo=通过')">
						通 过 
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button"						
						onclick="refreshForm('xsxx_zgkd.do?method=saveAutidting&yesNo=不通过')">
						不通过 
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button"						
						onclick="Close();return false;">
						关 闭 
					</button>
					</logic:equal>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
			</table>
		</div>	
			<logic:equal value="true" name="result">
				<script>
					alert("操作成功!");		
					Close();		
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("操作失败!");				
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>
