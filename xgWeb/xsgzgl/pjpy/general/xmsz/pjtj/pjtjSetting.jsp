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
			
			jQuery.ajaxSetup({async:false});

			var xmdm = jQuery("#xmdm").val();
			
			//路径
			var url = "general_xmsz_pjtj_ajax.do?method=defaultPjtjSetting";
			//参数
		 	var parameter = {
				"xmdm":xmdm
			};
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			jQuery("#div_tjsz").load(
				url,
				parameter,
				function(){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";

					if($("hidden_num")){
						jQuery("#num").val($("hidden_num").value);
					}
				}
			);

			jQuery.ajaxSetup({async:true});
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.如果您想为该项目增加条件，请点击<font color="blue">增加条件</font>。<br/>
				2.如果您想为该项目删除条件，请<font color="blue">勾选</font>相应的条件，并点击<font color="blue">删除条件</font>。<br/>
				3.维护条件的时候，条件、关系、条件值、范围都<font color="blue">不能为空</font>。<br/>
				4.启用范围默认为<font color="blue">全体</font>，即全校所有学生，如果您有维护过班级大类的话，在此处可以选择具体的<font color="blue">班级大类</font>。<br/>
				5.不同范围，可以设置同一条件，但是不能有范围条件都一致的条件。<br/>
				6.假设某项目：<font color="blue">综合分(全体)大于80，综合分(理科班)大于70</font>。则理科班的学生只需要综合分<font color="blue">大于70</font>即可申请该项目。<br/>
				7.所有条件都出自<font color="blue">评奖条件库</font>，如果<font color="blue">没有您需要的条件</font>，请联系<font color="blue">管理员</font>。<br/>
				8.如果该项目已经有<font color="blue">学生申请过了</font>，则此处所有的条件都不可再进行<font color="blue">修改或删除</font>。<br/>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="str_xmdm" id="xmdm" value="${xmdm }"/>
			<input type="hidden" name="num" id="num" value="0"/>
			<!-- 评奖条件 -->
			<select id="select_pjtj" style="display:none">
				<option value=""></option>
				<logic:iterate name="pjtjList" id="pjtj_rs">
					<option value="${pjtj_rs.tjdm }">${pjtj_rs.tjmc }</option>
				</logic:iterate>
			</select>
			<!-- 范围等级 -->
			<select id="select_tjfw" style="display:none">
				<option value="all">全体</option>
				<logic:iterate name="bjdlList" id="bjdl_rs">
					<option value="${bjdl_rs.dm }">${bjdl_rs.mc }</option>
				</logic:iterate>
			</select>
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<td>
							<span>评奖条件设置</span>
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<logic:equal name="checkXssq" value="true">
							<button type="button"  onclick="addTjsz();">增加条件</button>
							<button type="button"  onclick="delTjsz();">删除条件</button>
							</logic:equal>
							<logic:equal name="checkXssq" value="false" >
							<button type="button"  onclick="addTjsz();" disabled="true">增加条件</button>
							<button type="button"  onclick="delTjsz();" disabled="true">删除条件</button>
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td>
							<div id="div_tjsz_top">
								<table width="100%" border="0">
									<tr>
										<td width="5%">
											<input type="checkbox" name="selall" onclick="selAll()"/>
										</td>
										<td width="40%">
											条件
										</td>
										<td width="10%">
											关系
										</td>
										<td width="10%">
											条件值
										</td>
										<td>
											启用范围
										</td>
									</tr>
								</table>
							</div>
							<div id="div_tjsz">
								
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<div class="btn">
								<logic:equal name="checkXssq" value="true">
								<button type="button"  name="保存" onclick="checkSavePjtj();return false;">保 存</button>
								</logic:equal>
								<logic:equal name="checkXssq" value="false">
								<button type="button"  name="保存" onclick="checkSavePjtj();return false;" disabled="true">保 存</button>
								</logic:equal>
								<button type="button"  name="关闭" onclick="Close();return false;">关 闭</button>
							</div>
						</td>
					</tr>
			    </tfoot>
			</table>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>