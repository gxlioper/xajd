<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/wjsc.js"></script>
		<script language="javascript" src="js/String.js"></script>
	</head>
	<body  onload="initPage();viewyh('first','');">
		<logic:equal value="ok" name="inserted">
				<script type="text/javascript">
					alert('发布成功!');
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script type="text/javascript">
					alert('发布失败!');
				</script>
			</logic:equal>
			<logic:equal value="reinsert" name="inserted">
				<script type="text/javascript">
					alert('文件上传失败！请检查文件号是否重复');
				</script>
			</logic:equal>
			<logic:equal name="alert" value="cannot">
				<script type="text/javascript">
					alert('文件上传失败！文件大小应控制在10M以下!');
				</script>
			</logic:equal>
	
			<div class="prompt" style="display:none" id="message">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					对不起，该文件已有人阅读过不能修改！
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none'"></a>
			</div>
	
		<html:form action="/wjffManage" method="post"
			enctype="multipart/form-data">
				<input type="hidden" value="" id="yczdm"/>
				<input type="hidden" value="${rs.jsr}" id="yhms" name="yhms"/>
				<input type="hidden" value="" id="zdms" name="zdms"/>
				<input type="hidden" value="" id="zmcs" name="zmcs"/>
				<input type="hidden" id="pk" name="pkValue" value="${pkValue }" />
				<input type="hidden" id="ydrxm"  value="${rs.ydrxm}" />
			
				<div class="tab">
					<table width="100%"  border="0" class="formlist">
					<thead>
			    	<tr>
			        	<th colspan="4"><span>文件发放</span></th>
			        </tr>
			   		</thead>
			   		 <tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				          	<input type="hidden" name="act" value="save" />
				            <button id="saveButton" onclick="getyhs();if(IsNoEmpty('wjh-wjm-wjscsm-yhms')){showTips('数据保存中，请稍候...');refreshForm('fileSave.do')};">保存</button>
				            <button onclick="reset();"> 重置 </button>
				          </div>
				          </td>
				      </tr>
				    </tfoot>
			   		
					<tr>
						<th width="10%" align="center">
							<font color="red">*</font>文件号:
						</th>
						<td colspan="2">
							<input type="text" name="wjh" id="wjh" size="50" maxlength="20" value="${rs.wjh }" readonly="readonly"/>
							<font color="red">(长度请控制在20个字以内)</font>
						</td>
					</tr>
					<tr>
						<th align="center">
							<font color="red">*</font>文件名:
						</th>
						<td colspan="2">
							<input type="text" name="wjm" id="wjm" size="50" maxlength="50" value="${rs.wjm }"/>
							<font color="red">(长度请控制在50个字以内)</font>
						</td>
					</tr>
					<tr>
						<th align="center" valign="top">
							<font color="red">*</font>文件说明:
							<p>
								<font color="red">(限800字)</font>
							</p>
						</th>
						<td colspan="2">
							<textarea rows="10" cols="95" name="wjscsm" id="wjscsm"
								onblur="chLeng(this,800)"
								type="_moz">${rs.wjscsm }</textarea>
						</td>
					</tr>
					<tr>
						<th valign="middle" rowspan="2" align="center">
							<font color="red">*</font>面向对象:
							
						</th>
						<th align="center">
							<div align="center">
							<font color="red">接收人选定</font>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--							<input type="checkbox" value="" onclick="lock(this)" id="alluser" name="alluser"/>&nbsp;所有用户--%>
							</div>
						</th>
						<th >
							<div align="center">
							<font color="red">接收人</font>
							</div>
						</th>
						<logic:present name="jsrlist">
							<html:select property="jsr" styleId="wjjsr" style="display:none">
								<html:options collection="jsrlist" property="yhm" labelProperty="xm"/>
							</html:select>
						</logic:present>
					</tr>
				
					<tr>
						<td>	
							<table >
								<tr>
									<td style="cursor:hand;">
										<div style="overflow: auto;height:250px;width:150px" id="div1">					
											<table  id="yhzinfo">
												<thead align="center" >
													<td style="width:140px">用 户 组</td>
												</thead>
												<tr>
													<td>
														<logic:iterate id="s" name="yhzList">
															<logic:notEqual value="" name="rs" property="wjjszmc">
															<logic:match value="${s.zmc}" name="rs" property="wjjszmc">
																<input type="checkbox" value="${s.zdm }" onclick="selectall(this)" checked="checked" name="temp_yhz"/>&nbsp;
															</logic:match>
															
															<logic:notMatch value="${s.zmc}" name="rs" property="wjjszmc">
																<input type="checkbox" value="${s.zdm }" onclick="selectall(this)"/>&nbsp;
															</logic:notMatch>
															</logic:notEqual>
															<logic:equal value="" name="rs" property="wjjszmc">
																<input type="checkbox" value="${s.zdm }" onclick="selectall(this)"/>&nbsp;
															</logic:equal>
															<span style="font-size: 13;color: #003366" onclick="viewyh(this,'${s.zdm}');">${s.zmc }</span><br/>
														</logic:iterate>
													</td>
												</tr>
											</table>
										</div>
									</td>
									<td width="15px"></td>
									<td>
										<div style="overflow: auto;height:250px" id="div2">					
											<table id="yhinfo">										
												<thead align="center">
													<td style="width:140px">用 户</td>
												</thead>
												<tr id="yhlist">
													<logic:iterate id="s" name="allyhlist">
													<td id = "<bean:write name="s" property="zdm"/>" style="display: none">
														<logic:iterate id="s" name="s" property="yhzyhmap">
															<logic:notEqual value="" name="s" property="xm">
																<logic:match value="${s.xm}" name="rs" property="jsrxm">
																	<logic:match value="${s.yhm}" name="rs" property="jsr">
																		<input type="checkbox" value="${s.yhm }" name="temp_yhm" onclick="selectjsr(this)" checked="checked"/>&nbsp;
																	</logic:match>
																</logic:match>
																
																<logic:match value="${s.xm}" name="rs" property="jsrxm">
																	<logic:notMatch value="${s.yhm}" name="rs" property="jsr">
																		<input type="checkbox" value="${s.yhm }" name="temp_yhm" onclick="selectjsr(this)" />&nbsp;
																	</logic:notMatch>
																</logic:match>
																
																<logic:notMatch value="${s.xm}" name="rs" property="jsrxm">
																	<input type="checkbox" value="${s.yhm }" name="temp_yhm" onclick="selectjsr(this)" />&nbsp;
																</logic:notMatch>
																
															</logic:notEqual>
															<logic:equal value="" name="s" property="xm">
																<input type="checkbox" value="${s.yhm }" name="temp_yhm" onclick="selectjsr(this)" />&nbsp;
															</logic:equal>
															<span id="xm" >${s.xm }</span><br/>
														</logic:iterate>
													</td>
													</logic:iterate>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>					
						<td >
							<textarea name="jsrxm" rows="16" cols="40" readonly="true"  id="jsrxm" type="_moz">${rs.jsrxm}</textarea>
						</td>
					</tr>				
					<tr>
						<th align="center">
							<font color="red">*</font>请选择文件：
						</th>
						<td align="left" colspan="2">
							<div>
								 <logic:notEmpty name="rs" property="wjsclj">
									 <a	target="_self"
										href="fileDownload.do?wjsclj=${rs.wjsclj }&wjhID=${rs.wjh}">
										点击下载
									 </a>
									 <span style="color:blue;">(提示：若不再上传文件将会保留原来上传的文件，否则原来上传的文件将被覆盖或删除)</span>&nbsp;&nbsp;
								</logic:notEmpty>
								<br/>
								<input type="file" name="uploadFile" style="width:60%" id="kk" contenteditable="false" class="text_nor"/>
								&nbsp;&nbsp;
								<font color="red">(文件大小&lt;10M&gt;)</font>
							</div>
						</td>
					</tr>
				</table>
				</div>
				<logic:present name="inserted">
					<script defer="defer">
					    if (window.dialogArguments) {
							window.close();
							dialogArgumentsQueryChick();
						}
					 </script>
				</logic:present>
				<script defer="defer">
					if ($('ydrxm').value.trim() != "" ){
						$('message').style.display="";
						$('saveButton').disabled=true;
					}
				</script>
		</html:form>
	</body>
</html>
