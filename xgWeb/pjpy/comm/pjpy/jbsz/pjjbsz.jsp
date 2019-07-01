<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//保存基本设置
		function saveJbsz(){
			saveUpdate("/xgxt/pjpyJbsz.do?method=pjjbsz&doType=save","pjxn-pjxq-pjnd");
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优-评奖综合设置-评奖周期设置</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
			<!-- 相关功能 -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;" 
					onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">相关功能</a>
			</p>
			<!-- 相关功能 end-->
			
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				1.本设置用来标记本次评奖是时间范围，影响到之后的<font color="blue">所有流程</font>，请谨慎设置。</br>
				2.假设当前为2011-2012学年，但是所评的奖项为2010-2011学年，那么，请在这里将学年设置为<font color="blue">2010-2011</font>。</br>
				3.综测的相关时间抽取自本设置。</br>
				4.用于项目条件设置的相关时间（比如某学年的成绩情况）抽取自本设置。</br>
				5.如果已经完成了本操作，那么不可再修改，除非进行<font color="blue">开始新评奖</font>操作。
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->	
		
		<!-- 快捷方式 -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none;">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_zhsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function19.png" />
							<p>评奖基本设置</p>
						</a>   	
					</div>
				</div>
			</div>
		</div>
		<!-- 快捷方式 end-->
		
		<html:form action="/pjpyJbsz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>		
			<div class="tab" style="height:768px">		
				<!-- 评奖时间设置 -->
				<table class="formlist" border="0" align="center" style="width: 100%;display: none">
					<!-- 人数分配方式 -->
					<thead>
						<tr>
							<th colspan="2">
								<span>人数分配方式</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<logic:iterate name="rsfpfsList" id="fpfs" indexId="index">
						<tr>
							<logic:equal name="index" value="0">
							<th align="right" width="30%" rowspan="${fpfsNum }">
								<font color="red">*</font>分配方式
							</th>
							</logic:equal>
							<td align="left">							
								<html:radio name="rs" property="rsszfs" value="${fpfs.en }"/>${fpfs.cn }
							</td>
						</tr>
						</logic:iterate>
					</tbody>
					<!-- 评奖条件库 -->
					<thead>
						<tr>
							<th colspan="2">
								<span>评奖条件库</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<logic:notEmpty name="tjkNum">
							<tr>
								<th align="right" width="30%">
									条件库选择
								</th>
								<td align="left">
									<div style="height: 200px;overflow:scroll;overflow-x:hidden">			
									<table>
										<tr>
											<logic:iterate name="tjkList" id="tjk" indexId="index">
												<td width="25%" height="35px">
													<logic:notEmpty name="tjk" property="tjdm">
														<input type="checkbox" name="tjdm" id="${tjk.tjdm }" value="${tjk.tjdm }"
														<logic:equal name="tjk" property="sfqy" value="yes">checked="true"</logic:equal>
														/>${tjk.tjmc }</br>
														<logic:notEqual name="tjk" property="tjms" value="无">
														（注：${tjk.tjms }）
														</logic:notEqual>
													</logic:notEmpty>
												</td>
												<%if((index.intValue()+1)%4==0){%>
													<% out.print("</tr>"); %>
												<%}%> 
											</logic:iterate>
										</tr>
									</table>
									</div>		
								</td>
							</tr>
						</logic:notEmpty>
						<logic:empty name="tjkNum">
							<tr>
								<th align="right" width="30%">
									条件库选择
								</th>
								<td align="left">
									条件库无预设条件，请联系相关责任人
								</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>评奖周期设置</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<th align="right" width="30%">
								<font color="red">*</font>评奖学年
							</th>
							<td align="left" width="70%">
								<html:select name="rs" property="pjxn" styleId="pjxn">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>评奖学期
							</th>
							<td align="left">
								<html:select name="rs" property="pjxq" styleId="pjxq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>评奖年度
							</th>
							<td align="left">
								<html:select name="rs" property="pjnd" styleId="pjnd">
									<html:options collection="ndList" property="nd" labelProperty="nd" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveJbsz()" id="buttonSave">
										保 存
									</button>
									
									<button type="button" style="display:none" onclick="refreshForm('pjpyJbsz.do?method=pjpyZhsz')" id="buttonBack">
										返 回
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