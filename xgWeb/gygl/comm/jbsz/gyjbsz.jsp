<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsxx/comm/jbsz/ymsz.js"></script>
		<script language="javascript">
		
		//保存字段设置
		function saveJbsz(){
			if (confirm("将要保存所设置的各项参数，请确认！")) {
				saveUpdate('/xgxt/gyglJbsz.do?method=gyjbsz&doType=save','');
			}
		}
		
		//页面初始化
		function onShow(){
		
			//设置校区园区值
			setXqYqValue();
			
			//设置从属关系
			setCsgx();		
		}
		
		//设置校区园区值
		function setXqYqValue(){
		
			//校区
			if($("czxq_yes").checked){
				$("hid_czxq").value = $("czxq_yes").value;
			}else{
				$("hid_czxq").value = $("czxq_no").value;
			}
			
			//园区
			if($("czyq_yes").checked){
				$("hid_czyq").value = $("czyq_yes").value;
			}else{
				$("hid_czyq").value = $("czyq_no").value;
			}
		}
		
		//设置从属关系
		function setCsgx(){
		
			//存在校区
			var czxq = $("hid_czxq").value;
			//存在园区
			var czyq = $("hid_czyq").value;
			
			if(czxq == "是" && czyq == "是"){
				$("p_xyl").style.display = "";
				$("p_xl").style.display = "";
				$("p_yl").style.display = "";
			}else if(czxq == "是" && czyq == "否"){
				$("p_xyl").style.display = "none";
				$("p_xl").style.display = "";
				$("p_yl").style.display = "none";
			}else if(czxq == "否" && czyq == "是"){
				$("p_xyl").style.display = "none";
				$("p_xl").style.display = "none";
				$("p_yl").style.display = "";
			}else{
				$("p_xyl").style.display = "none";
				$("p_xl").style.display = "none";
				$("p_yl").style.display = "none";
			}
		}
		
		//点击校区
		function clickXq(obj){
			$('hid_czxq').value = obj.value;
			setCsgx();
			$("csgx_l").checked = true;
		}
		
		//点击园区
		function clickYq(obj){
			$('hid_czyq').value = obj.value;
			setCsgx();
			$("csgx_l").checked = true;
		}
		</script>
	</head>
	
	<body onload="onShow()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/gyglJbsz">
		
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			
			<div class="tab">		
				<!-- 页面基本情况 -->
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>公寓基本设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<!-- 校区 -->
						<tr>
							<th width="30%">
								存在<bean:message key="lable.xiaoqu" />
							</th>
							<td>
								<html:radio property="czxq" styleId="czxq_yes" value="是" 
									onclick="clickXq(this)"/>是
								<html:radio property="czxq" styleId="czxq_no" value="否"
									onclick="clickXq(this)"/>否
								<input type="hidden" id="hid_czxq" value=""/>
								
								<!-- 提示 -->
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span>
									<font color="blue">
										用于控制从属关系以及过滤条件中是否需要出现<bean:message key="lable.xiaoqu" />项目
									</font>
								</span>
							</td>
						</tr>
						<!-- 园区 -->
						<tr>
							<th>
								存在<bean:message key="lable.yuanqu" />
							</th>
							<td>
								<html:radio property="czyq" styleId="czyq_yes" value="是"
									onclick="clickYq(this)"/>是
								<html:radio property="czyq" styleId="czyq_no" value="否"
									onclick="clickYq(this)"/>否
								<input type="hidden" id="hid_czyq" value=""/>
								
								<!-- 提示 -->
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span>
									<font color="blue">
										用于控制从属关系以及过滤条件中是否需要出现<bean:message key="lable.yuanqu" />项目
									</font>
								</span>
								
							</td>
						</tr>
						<!-- 楼栋从属关系 -->
						<tr>
							<th>
								<bean:message key="lable.ld" />从属关系
							</th>
							<td>
								
								<!-- 校区,园区,楼栋 -->
								<span id="p_xyl" style="display:none">
									<html:radio property="csgx" styleId="csgx_xyl" value="1"/>
									<bean:message key="lable.xiaoqu" /> -->
									<bean:message key="lable.yuanqu" /> -->
									<bean:message key="lable.ld" /></br>
								</span>
								
								<!-- 校区,楼栋 -->
								<span id="p_xl" style="display:none">
								<html:radio property="csgx" styleId="csgx_xl" value="2"/>
								<bean:message key="lable.xiaoqu" />-->
								<bean:message key="lable.ld" /></br>	
								</span>
								
								<!-- 园区,楼栋 -->
								<span id="p_yl" style="display:none">
								<html:radio property="csgx" styleId="csgx_yl" value="3"/>
								<bean:message key="lable.yuanqu" />-->
								<bean:message key="lable.ld" /></br>
								</span>
								
								<!-- 楼栋 -->
								<span id="p_l">
								<html:radio property="csgx" styleId="csgx_l" value="4"/>
								仅<bean:message key="lable.ld" />
								</span>
								
								<!-- 提示 -->
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span>
									<font color="blue">
										用于控制寝室分配，床位分配等功能模块的寝室从属关系显示
									</font>
								</span>
							</td>
						</tr>
						<!-- 楼栋从属关系 -->
						<tr>
							<th>
								<bean:message key="lable.qs" />分配对象
							</th>
							<td>
								<!-- 学院 -->
								<html:radio property="fpdx" value="xy"/>
								<bean:message key="lable.xy" /></br>
								<!-- 年级+学院 -->
								<html:radio property="fpdx" value="njxy"/>
								年级+<bean:message key="lable.xy" /></br>
								<!-- 年级+专业 -->
								<html:radio property="fpdx" value="njzy"/>
								年级+<bean:message key="lable.zy" /></br>
								<!-- 班级 -->
								<html:radio property="fpdx" value="bj"/>
								<bean:message key="lable.bj" />
								
								<!-- 提示 -->
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span>
									<font color="blue">
										用于控制寝室分配的被分配对象，以及各个模块过滤条件的显示
									</font>
								</span>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									
								</div>
								<div class="btn">
									<!-- 保存操作 -->
									<button id="btn_bc" onclick="saveJbsz()" style="width: 80px">
										<bean:message key="lable.btn_bc_space" />
									</button>
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