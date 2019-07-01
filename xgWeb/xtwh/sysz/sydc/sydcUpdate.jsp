<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/pclUtil.js"></script>
		<script language="javascript">
		
		//首页调查
		function saveSydc(){
			
			if($("dcnr") && $("dcnr").value==""){
				alertInfo("调查内容不能为空!",function(){});
				return false;
			}
			
			if($("sfqy") && $("sfqy").value==""){
				alertInfo("是否启用不能为空!",function(){});
				return false;
			}
			
			var xxnr=document.getElementsByName("xxnr");
			var flag=false;
			for(i=0;i<xxnr.length;i++){
				if(xxnr[i].value!=""){
					flag=true;
				}
			}
			
			if(!flag){
				alertInfo("选项内容不能为空!",function(){});
				return false;
			}
			
			var msg = "确认保存信息？";
			confirmInfo(msg,function(t) {
				saveUpdate('/xgxt/xtwhSysz.do?method=sydcUpdate&doType=save','dcnr');
			});
		}
		
		//显示
		function showSydc(){
			
			var doType = $("doType").value;
			
			if(doType != ""){
				var tableName = "xg_xtwh_sydcxxb";
				var pk = "dcid";
				var pkValue = $("dcid").value;
				var query = " order by xxid "
				var tbId = "nr";
				var btnId = "nrAdd";
				var lx = "xtwh_sysz_sydc";
				
				setNr(tableName,pk,pkValue,query,tbId,btnId,lx);
			}else{
				$("nrAdd").value = "3";
				trAdd('nr','madd','nrAdd','xtwh_sysz_sydc');
			}
		}
		</script> 
	</head>
	<body onload="setFocus('dcnr');showSydc()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/xtwhSysz">
			<!-- 隐藏域 -->	
			<input type="hidden" name="dcid" id="dcid" value="${rs.dcid }"/>
			<input type="hidden" name="gd_num" id="gd_num" value="3"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<div class="tab">		
			<!-- 项目基本情况 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>首页调查信息</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr style="">
						<th align="right" width="20%">
							<font color="red">*</font>调查内容<br/><font color="red">(限100字)</font>
						</th>
						<td align="left" colspan="3">
							<html:textarea name="rs" styleId="dcnr" property="dcnr" rows="5" onblur="chLeng(this,100)" style="word-break:break-all;width:90%"></html:textarea>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							备注<br/><font color="red">(限250字)</font>
						</th>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="bz" rows="5" onblur="chLeng(this,250)" style="word-break:break-all;width:90%"></html:textarea>
						</td>					
					</tr>
					<tr style="height: 23px">
						<th align="right">
							<font color="red">*</font>是否启用
						</th>
						<td align="left" colspan="3">
							<logic:iterate name="sfqyList" id="sfqy">
								<html:radio name="rs" property="sfqy" value="${sfqy.en }">${sfqy.cn }</html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:iterate>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">注：如果本调查启用的话，其他调查将被关闭</font>
						</td>					
					</tr>
				</tbody>
				<!-- 调查选项 -->
				<thead>
					<tr>
						<th colspan="4">
							<span>调查选项（最多可以维护3个选项,可以为空）</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="4">	
							<input  value="+" onclick="if(count > 3){alert('最多只能维护3个选项');return false}else{trAdd('nr','add','nrAdd','xtwh_sysz_sydc')};" style="width: 20px;display: none"/>
							<input  value="-" onclick="trDel('nr','delRow')" style="width: 20px;display: none"/>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;--%>
							<input type="text" name="nrAdd" id="nrAdd" style="width: 20px;display: none" onblur="if(this.value > 3){alert('最多只能维护3个选项');return false}else{trAdd('nr','madd','nrAdd','xtwh_sysz_sydc')}"/>
<%--							&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;--%>
							<input type="text" name="nrDel" id="nrDel" style="width: 20px;display: none" onblur="trDelAll('nr','nrDel')"/>
<%--							&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							
							<table class="formlist" align="center" width="100%" id="tTb">
								<tr>
									<td>
										<div class="mid_box">
											<table align="center" style="width: 100%" id="rsT" class="tbstyle">
												<thead style="height: 23px">
													<tr>
														<td align="center" nowrap="nowrap" style='width:5%'>
															选项
														</td>
														<td align="center" nowrap="nowrap" style=''>
															选项内容（限50字）
														</td>
													</tr>		
												<tbody width="100%" class="tbstyle" id="nr">
												
												</tbody>	
											</table>
										</div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">"<span class="red">*</span>"为必填项</div>
							<div class="btn">
								<!-- 非查看 -->
								<logic:notEqual name="doType" value="view">
									<button type="button" onclick="saveSydc()" id="buttonSave">
										保 存
									</button>
								</logic:notEqual>
								<button type="button" onclick="window.close();return false;" id="buttonClose">
									关 闭
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