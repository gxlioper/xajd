<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){
			var pjzq = jQuery("#pjzq_checked").val();
			if(pjzq == "xn"){

			}else if(pjzq == "xq"){
				$('tr_pjxq').style.display='';
			}
			
			var cpz = jQuery("#cpz_checked").val();
			clickCpz(cpz,"onshow");
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.您需要在本页面对本次评奖的一些<font color="blue">基本属性</font>进行设置，这些设置将影响<font color="blue">整个评奖流程</font>。<br/>
				2.<font color="blue">评奖周期</font>意味着学校评奖是以学年为单位还是以学期为单位的，该设置同时影响<font color="blue">综测的周期</font>以及<font color="blue">评奖条件</font>的获取。<br/>
				3.如果评奖周期为<font color="blue">学年</font>，请设置本次<font color="blue">评奖学年</font>，该设置影响整个评奖流程中<font color="blue">学年的取值</font>。<br/>
				4.如果评奖周期为<font color="blue">学期</font>，请设置本次<font color="blue">评奖学年及学期</font>，该设置影响整个评奖流程中<font color="blue">学年、学期的取值</font>。<br/>
				5.<font color="blue">综测排名和智育排名</font>的选择，主要影响综合素质测评总分<font color="blue">计算</font>时的排名以什么为准。<br/>
				6.当<font color="blue">评奖周期或者评奖学年、评奖学期发生变化</font>的话，系统认为学校已经进入<font color="blue">新的评奖周期</font>。<br/>
				7.每此进入<font color="blue">新的评奖周期</font>，系统将执行相关数据的初始化操作，具体详见<font color="blue">页面备注</font>。<br/>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="hidden_start" value="no"/>
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">	
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>评奖周期设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="30%">
								评奖周期				
							</th>
							<td width="">
								<html:radio property="pjzq" styleId="pjzq_xn" 
									value="xn" 
									onclick="checkRadio(this);$('tr_pjxq').style.display='none';setStartBz();"/>按学年评奖
<%--								<html:radio property="pjzq" styleId="pjzq_xq" --%>
<%--									value="xq"  disabled="true"--%>
<%--									onclick="checkRadio(this);$('tr_pjxq').style.display='';setStartBz();"/>按学期评奖--%>
								<html:hidden property="pjzq" styleId="pjzq_checked"/>
								<input type="hidden" id="hidden_pjzq" value="${pjzq }"/>
							</td>
						</tr>
						<tr>
							<th width="">
								<font color="red">*</font>评奖学年		
							</th>
							<td width="">
								<html:select property="pjxn" styleId="pjxn" onchange="setStartBz()">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
								<input type="hidden" id="hidden_pjxn" value="${pjxn }"/>
							</td>
						</tr>
						<tr id="tr_pjxq" style="display:none">
							<th width="">
								<font color="red">*</font>评奖学期
							</th>
							<td width="">
								<html:select property="pjxq" styleId="pjxq" onchange="setStartBz()">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
								<input type="hidden" id="hidden_pjxq" value="${pjxq }"/>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>其他设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="">
							<th width="">
								参评组
							</th>
							<td width="">
								<html:radio property="cpz" styleId="cpz_no" 
									value="no" 
									onclick="checkRadio(this);clickCpz(this.value,'');"
								/>无需参评组
								<html:radio property="cpz" styleId="cpz_yes" 
									value="yes" 
									onclick="checkRadio(this);clickCpz(this.value,'');"
								/>需要参评组
								<html:hidden property="cpz" styleId="cpz_checked"/>
							</td>
						</tr>
						<tr id="tr_zcpm">
							<th width="">
								综测排名
							</th>
							<td width="">
								<html:radio property="zcpm" styleId="zcpm_njxy" value="1" onclick="checkRadio(this)"/>年级+院系排名
								<html:radio property="zcpm" styleId="zcpm_njzy" value="2" onclick="checkRadio(this)"/>年级+专业排名
								<html:radio property="zcpm" styleId="zcpm_bj" value="3" onclick="checkRadio(this)"/>班级排名
								<html:radio property="zcpm" styleId="zcpm_cpz" value="0" onclick="checkRadio(this)"/>参评组排名
								<html:hidden property="zcpm" styleId="zcpm_checked"/>
							</td>
						</tr>
						<tr id="tr_zypm">
							<th width="">
								智育排名
							</th>
							<td width="">
								<html:radio property="zypm" styleId="zypm_njxy" value="1" onclick="checkRadio(this)"/>年级+院系排名
								<html:radio property="zypm" styleId="zypm_njzy" value="2" onclick="checkRadio(this)"/>年级+专业排名
								<html:radio property="zypm" styleId="zypm_bj" value="3" onclick="checkRadio(this)"/>班级排名
								<html:radio property="zypm" styleId="zypm_cpz" value="0" onclick="checkRadio(this)"/>参评组排名
								<html:hidden property="zypm" styleId="zypm_checked"/>
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<td colspan="2">
							<!--备注 begin-->
							<div class="readme"  id="div_csh_no" style="height: 220px">
								<h2>周期未发生变化</h2>
								<div class="readcon">
									<ul>
										<li>系统不会执行相关初始化操作，如需修改相关设置，请前往相关模块进行处理</li>
									</ul>
								</div>
							</div>	
							<div class="readme"  id="div_csh_yes" style="height: 220px" style="display: none">
								<h2>周期发生变化，保存后系统将会自动执行下列功能</h2>
								<div class="readcon">
									<ul>
										<li id="li_001">将非本周期的评奖记录复制到评奖历史库，并清空这些信息</li>
										<li id="li_002">将初始化评奖人员库，如需调整，请前往"评奖人员设置"调整</li>
										<li id="li_003">将初始化上一评奖周期的参评小组设置，本周期如需要参评组的话，请前往"参评小组设置"维护</li>
										<li id="li_004">将要复制前一次评奖项目，如有变更，请前往"评奖项目维护"进行调整</li>
										<li id="li_008">将要复制前一次综测项目，如有变更，请前往"综测项目维护"进行调整</li>
										<li id="li_005">将要复制前一次评奖条件设置，如有变更，请前往"评奖项目维护"进行调整</li>
										<li id="li_006">将要复制前一次评奖兼得设置，如有变更，请前往"评奖项目维护"进行调整</li>
										<li id="li_007">将要复制前一次班级大类设置，如有变更，请前往"班级大类设置"进行调整</li>
									</ul>
								</div>
							</div>	
							<!--备注 end-->
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" name="保存" onclick="checkSaveStart();return false;">保 存</button>
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