<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script type="text/javascript" defer="defer">
		function goDaxxInfo(){

			jQuery("#daxxTabId").addClass("ha");
			jQuery("#daclTabId").removeClass("ha");
			jQuery("#tbody_dajbxx").show();
			jQuery("#tbody_daclxx").hide();
		}
		
		function goDaclInfo(){
			
			var pk=jQuery("#xh").val()+jQuery("#dazrsj").val();
			jQuery("#daclTabId").addClass("ha");
			jQuery("#daxxTabId").removeClass("ha");
			jQuery("#tbody_dajbxx").hide();
			jQuery("#tbody_daclxx").show();
		}
		
		</script>
	</head>
	<body>
		<html:form action="/daxxgl" method="post"  onsubmit="return false;" styleId="form">
			<html:hidden  property="xh"  styleId="xh" value="${rs.xh}"/>
			<html:hidden  property="dazrsj"  styleId="dazrsj" value="${rs.dazrsj}"/>
			<div style='width:100%;height:500px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_xsjbxx">
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<tr><td colspan="4">
				    <div class="comp_title" id="comp_title" width="100%">
						<ul style="width:100%" id="tabUl">
					      	<li class="ha" id="daxxTabId"><a href="javascript:void(0);" onclick="goDaxxInfo();return false;"><span>档案基本信息</span></a></li>
							<li id="daclTabId"><a href="javascript:void(0);" onclick="goDaclInfo();return false;"><span>档案清单信息</span></a></li>
					    </ul>
					</div></td></tr>
					</tbody>
					<tbody id="tbody_dajbxx">
						<tr><td align="left" colspan="4" ><span style="color:blue;">档案转入信息</span></td></tr>
						<tr>
							<th width="18%">
								档案转入时间
							</th>
							<td width="32%">
								${rs.dazrsj}
							</td>
							<th width="18%">
								档案转入方式
							</th>
							<td width="32%">
								${rs.dazrfs}
							</td>
						</tr>
						<tr>
							<th >
								转入方式编号
							</th>
							<td  colspan="3" >
								${rs.zrfsbh}
							</td>
						</tr>
						<tr><td align="left" colspan="4"><span style="color:blue">档案转出信息</span></td></tr>
						<tr>
							<th >
								档案转出时间
							</th>
							<td  >
								${rs.dazcsj }
							</td>
							<th >
								档案转出方式
							</th>
							<td >
								${rs.dazcfs }
							</td>
						</tr>
						<tr>
							<th >
								<logic:equal name="xxdm" value="12869">机要编号</logic:equal>
							<logic:notEqual name="xxdm" value="12869">档案转出编号</logic:notEqual>
							</th>
							<td  colspan="3" >
								${rs.zcfsbh }
							</td>
						</tr>
						<logic:equal name="sfxsbyqx" value="1" >
							<tr><td align="left" colspan="4"><span style="color:blue">毕业去向信息</span></td></tr>
							<tr>
								<th >
									毕业去向
								</th>
								<td colspan="3">
									${rs.byqxmc }
								</td>
							</tr>
							<tr>
								<th >
									单位名称
								</th>
								<td >
									${rs.dwmc }
								</td> 
								<th >
									单位邮编
								</th>
								<td >
									${rs.dwyb }
								</td>
							</tr>
							<tr>
								<th >
									单位地址
								</th>
								<td  colspan="3" >
									${rs.dwdz }
								</td>
							</tr>
							<tr>
								<th >
									单位联系人
								</th>
								<td >
									${rs.dwlxr }
								</td>
								<th >
									单位联系电话
								</th>
								<td >
									${rs.dwlxdh }
								</td>
							</tr>
						</logic:equal>
						
						<logic:equal name="xxdm" value="12869">
						<tr><td align="left" colspan="4"><span style="color:blue">档案投递信息</span></td></tr>
						<tr>
							<th >
								档案投递单位
							</th>
							<td >
								${rs.dazjdw }
							</td>
							<th >
								档案投递邮编
							</th>
							<td >
								${rs.dazjyb}
							</td>
						</tr>
						<tr>
							<th >
								档案投递地址
							</th>
							<td   colspan="3">
								${rs.dazjdz }
							</td>
						</tr>
						<tr>
							<th >
								档案投递单位联系人
							</th>
							<td >
								${rs.dazjdwlxr}
							</td>
							<th >
								档案投递单位电话
							</th>
							<td >
								${rs.dazjdwdh}
							</td>
						</tr>
						</logic:equal>
						
						<logic:notEqual name="xxdm" value="12869">
						<tr><td align="left" colspan="4"><span style="color:blue">档案转寄信息</span></td></tr>
						<tr>
							<th >
								档案转寄单位
							</th>
							<td >
								${rs.dazjdw }
							</td>
							<th >
								档案转寄邮编
							</th>
							<td >
								${rs.dazjyb}
							</td>
						</tr>
						<tr>
							<th >
								档案转寄地址
							</th>
							<td   colspan="3">
								${rs.dazjdz }
							</td>
						</tr>
						<tr>
							<th >
								档案转寄单位联系人
							</th>
							<td >
								${rs.dazjdwlxr}
							</td>
							<th >
								档案转寄单位电话
							</th>
							<td >
								${rs.dazjdwdh}
							</td>
						</tr>
						</logic:notEqual>
						
						<tr><td align="left" colspan="4"><span style="color:blue">其它信息</span></td></tr>
						<tr>
							<th >
								备注
							</th>
							<td   colspan="3">
								${rs.bz }
							</td>
						</tr>
					</tbody>
					<tbody id="tbody_daclxx" style="display:none;">
						<!-- 显示已维护的档案清单模板 -->
						<logic:equal name="whzt" value="已维护">
							<tr name="mbwclTr">
								<th width="18%">
									档案清单模板
								</th>
								<td colspan="3">
									<font color="blue">${rs.daqdmb_mc}</font>
									<div style="float:right;">(<font color="blue">蓝色字体的材料为该档案清单模板内材料</font>; <font color="black">黑色字体为模板外材料</font>)</div>
								</td>
							</tr>
							<tr name="clTr">
								<td colspan="4" width="100%">
									<table id="clTable" width="100%" border="0">
										<tr width="100%" align="center">
											<td width="8%">顺序</td>
											<td width="17%">材料名称</td>
											<td width="14%">分数</td>
											<td width="14%">页数</td>
											<td width="17%">归档日期</td>
											<td width="30%">备注</td>
										</tr>
										<logic:notEmpty name="mbnclList">
											<logic:iterate name="mbnclList" id="a" indexId="index">
												<tr align="center">
												<html:hidden property="daqdcl_id"  value="${a.daqdcl_id }" />
												<td>${a.sx}</td>
												<td><font color="blue">${a.daqdcl_mc}</font></td>
												<td>${a.fs }</td>
												<td>${a.ys }</td>
												<td>${a.gdrq }</td>
												<td>${a.bz }</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
										<logic:notEmpty name="mbwclList">
											<logic:iterate name="mbwclList" id="b" indexId="index">
												<tr align="center">
												<html:hidden property="daqdcl_id"  value="${b.daqdcl_id }" />
												<td>${b.sx}</td>
												<td><font color="black">${b.daqdcl_mc}</font></td>
												<td>${b.fs }</td>
												<td>${b.ys }</td>
												<td>${b.gdrq }</td>
												<td>${b.bz }</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</table>
								</td>
							  </tr>
						  </logic:equal>
						<logic:notEqual name="whzt" value="已维护">
							<tr name="mbwclTr">
								<td colspan="4">
									<span style="padding-left:30px"><font color="blue">未维护档案清单</font></span>
								</td>
							</tr>
						</logic:notEqual>
					</tbody>				
				</table>
			</div>
			<table border="1" class="formlist" >	
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		
		</html:form>
	</body>
</html>
