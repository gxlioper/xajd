<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/kyxmgl/kyxm/js/kyxmsq.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/qgzx_kycxkyxmsq" method="post" styleId="KyxmglForm" onsubmit="return false;">
		<html:hidden property="xmid" styleId="xmid" value="${rs.xmid}"/>
		<input type="hidden" id="ysxxStr" name="ysxxStr"/>
		<input type="hidden" id="jsxxStr" name="jsxxStr"/>
		<input type="hidden" id="xsxxStr" name="xsxxStr"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								项目编号
							</th>
							<td width="30%">
								${rs.xmbh}
							</td>
							<th width="20%">
								项目名称
							</th>
							<td width="30%">
								${rs.xmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								项目属性
							</th>
							<td width="30%">
								${rs.xmsxmc}
							</td>
							<th width="20%">
								项目所属单位
							</th>
							<td width="30%">
								${rs.ssdwmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								项目负责人学号
							</th>
							<td width="30%">
								${rs.xh}
							</td>
							<th width="20%">
								项目负责人姓名
							</th>
							<td width="30%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								联系方式
							</th>
							<td width="30%">
								${rs.lxfs}
							</td>
							<th width="20%">
								依托实验室
							</th>
							<td width="30%">
								${rs.ytsys}
							</td>
							
						</tr>
						<tr>
							<th width="20%">
								项目开始时间
							</th>
							<td width="30%">
								${rs.kssj}
							</td>
							<th width="20%">
								项目结束时间
							</th>
							<td width="30%">
								${rs.jssj}
							</td>																			
						</tr>
						<tr>
							<th>
								研究周期
							</th>
							<td width="30%" >
								${rs.yjzq}
							</td>
							<th width="20%">
								<font color="red">*</font>立项等级
							</th>
							<td width="30%" >
								<html:radio  property="lxdj"  value="1">重点项目</html:radio>
								<html:radio  property="lxdj"  value="0">一般项目</html:radio>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>小组成员信息
								<img src="xsxx/fbgl/images/add-icons-1.gif" alt="增加" onclick="addXs(this);return false;"/>
						    <img src="xsxx/fbgl/images/delete-icons-2.gif" alt="删除" onclick="delXs(this);return false;"/>
								</span>
							</th>
						</tr>
					</thead>
				<tbody>
				<tr>
					<td colspan="4">
					<div class="con_overlfow">
					<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						
						<tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'>学号</td>
							<td width='10%'>姓名</td>
							<td width='5%'>性别</td>
							<td width='20%'>学院</td>
							<td width='20%'>班级</td>
							<td width='8%'>项目中的分工</td>
							<td width='8%'>联系电话</td>
							<td width='10%'>是否免费</br>师范生</td>
						</tr>
					</thead>
					<tbody id="tbody_xsxx">
						<logic:iterate id="i" name="cyList" indexId="index01">
						<tr>
						<td>
						<input type="checkbox" id="checkbox_${index01}"/>
						</td>
						<td name="xh">${i.xh}</td>
						<td>${i.xm}</td>
						<td>${i.xb}</td>
						<td>${i.xymc}</td>
						<td>${i.bjmc}</td>
						<td>
						<html:text property="xmfg" styleId="xmfg_${index01}" value="${i.xmfg}" style="width:100px" maxlength='20'></html:text>
						</td>
						<td>
						${i.lxdh}
						</td>
						<td>
						<html:select property="sfsfs" styleId="sfsfs_${index01}" value="${i.sfsfs}">
								<html:options collection="sfsfsList" labelProperty="mc" property="dm"/>
						</html:select>
						</td>
						</tr>
						</logic:iterate>
						</tbody>
						</table>
						</div>
						</td>
						</tr>
						</tbody>
						
						
				 <thead>
						<tr>
							<th colspan="4">
								<span>指导老师
								<img src="xsxx/fbgl/images/add-icons-1.gif" alt="增加" onclick="addJs(this);return false;"/>
						    	<img src="xsxx/fbgl/images/delete-icons-2.gif" alt="删除" onclick="delJs(this);return false;"/>
								</span>
							</th>
						</tr>
					</thead>
				
				<tbody>
				<tr>
					<td colspan="4">
					<div class="con_overlfow">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'>工号</td>
							<td width='15%'>姓名</td>
							<td width='15%'>学院</td>
							<td width='15%'>职称</td>
							<td width='15%'>研究方向</td>
							<td width='15%'>联系电话</td>
						</tr>
					</thead>
					<tbody id="tbody_jsxx">
						<logic:iterate id="i" name="zdlsList" indexId="index01">
						<tr>
						<td>
						<input type="checkbox" id="checkbox_${index01}"/>
						</td>
						<td name="xh">${i.zgh}</td>
						<td>${i.xm}</td>
						<td>${i.xymc}</td>
						<td>
						<html:text property="zc" styleId="zc_${index01}" value="${i.zc}" maxlength='10'></html:text>
						</td>
						<td>
						<html:text property="yjfx" styleId="yjfx_${index01}" value="${i.yjfx}" maxlength='100'></html:text>
						</td>
						<td>
						${i.lxdh}
						</td>
						</tr>
						</logic:iterate>
						</tbody>
				</table>
				</div>
				</td>
				</tr>
				</tbody>
				
				
				<thead>
				<tr>
				<th colspan="4">
						<span>经费预算
							<img src="xsxx/fbgl/images/add-icons-1.gif" alt="增加" onclick="addJf(this);return false;"/>
						    <img src="xsxx/fbgl/images/delete-icons-2.gif" alt="删除" onclick="delJf(this);return false;"/>
							</span>
						</th>
				</tr>
					</thead>
				
				<tbody>
				<tr>
					<td colspan="4">
					<div id='jfysDiv' style="display:none">
						<table name='jfysTab'>
						<tbody name='jfysTbody'>
						<tr>
								<td>
								<input type="checkbox" id="checkbox"/>
								</td>
								<td>
								<input type='zclb' name="zclb" style="width:100px" maxlength='20'/>
								</td>
								<td>
								<input type='text' name="ysje" maxlength='10' onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d)?(?:\d{2})?/ig,'$1$2$3')"/>
								</td>
								<td>
								<input type='zyyt' name="zyyt" style="width:400px"  maxlength='100'/>
							
								</td>
								</tr>
								</tbody>
							</table>
						</div>
					<div class="con_overlfow">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
						
					<thead>
						<tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'>支出类别</td>
							<td width='15%'>预算金额</td>
							<td width='65%'>主要用途</td>
						</tr>
					</thead>
					<tbody id="tbody_ysxx">
					<logic:present name='ysxxList'>
						<logic:iterate id="i" name="ysxxList" indexId="index01">
						<tr>
						<td>
						<input type="checkbox" id="checkbox_${index01}"/>
						</td>
						<td>
						<input type='zclb' name="zclb" style="width:100px" maxlength='10' value="${i.zclb}"/>
						</td>
						<td>
						<html:text property="ysje" styleId="ysje_${index01}" value="${i.ysje}" maxlength='10' onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d)?(?:\d{2})?/ig,'$1$2$3')"></html:text>
						</td>
						<td>
						<html:text property="zyyt" styleId="zyyt_${index01}" value="${i.zyyt}" maxlength='100' style="width:400px"></html:text>
						</td>
						</tr>
						</logic:iterate>
					</logic:present>
						</tbody>
				</table>
				</div>
				</td>
				</tr>
				</tbody>
				
				<thead>
				<tr>
						<th colspan="4">
						<span>申请信息</span>
						</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td colspan="4">
					<div style=" width:100%; margin:0 auto; overflow-x:auto; height:520px; *padding-bottom:20px;">
				    <table width="100%" border="0" class="formlist">
					<tr><th width="20%"><font color="red">*</font>申请摘要
								</br><font color="red">(限300字)</font></th>
							<td colspan="3">
								<html:textarea property="sqzy" styleId="sqzy" 
											   onkeypress="checkLen(this,300);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
					</tr>
					<tr><th width="20%"><font color="red">*</font>立项依据（研究项目的意义、国内外研究现状和发展动态）
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="lxyj" styleId="lxyj" 
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
					</tr>
					<tr><th width="20%"><font color="red">*</font>项目的主要内容和创新点、拟解决的关键问题
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="gjwt" styleId="gjwt" 
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
					</tr>
					<tr><th width="20%"><font color="red">*</font>研究方案（技术路线）及可行性分析
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="yjfa" styleId="yjfa" 
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
					</tr>
					<tr><th width="20%"><font color="red">*</font>研究计划
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="yjjh" styleId="yjjh" 
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
					</tr>
					<tr><th width="20%"><font color="red">*</font>成果形式及预期结果
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="cgxsyqjg" styleId="cgxsyqjg" 
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
					</tr>
				</table>
				</div>
				</td>
				</tr>
				</tbody>
				
				</table>
				</div>
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
							<button type="button" onclick="saveXmwh('save');">
										保存草稿
									</button>
							<button type="button" onclick="saveXmwh('submit');">
										提交申请
									</button>
								<button type="button" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
				
				</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

