<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/wjsc.js"></script>
		<script language="javascript">
		function initJd(){
			if($("jd")){
				$("jd").focus();
			}
		}
		</script>
	</head>
	<body  onload="initPage();viewyh('first','');initJd()">
		<logic:equal value="ok" name="inserted">
				<script type="text/javascript">
					alert('发布成功!');
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script type="text/javascript">
					alert('${message}!');
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
	
	
		<html:form action="/wjffManage" method="post"
			enctype="multipart/form-data">
				<input type="hidden" value="" id="yczdm"/>
				<input type="hidden" value="" id="yhms" name="yhms"/>
				<input type="hidden" value="" id="zdms" name="zdms"/>
				<input type="hidden" value="" id="zmcs" name="zmcs"/>
				<input type="hidden" id="pk" name="pkValue" value="${pkValue }" />

				<div class="tab_cur" id="jd">
					<p class="location">
						<em>您的当前位置:</em><a>文件管理-发放-文件发放</a>
					</p>
				</div>


				<div class="tab">
					<table width="100%"  border="0" class="formlist">
					<thead>
				    	<tr>
				        	<th colspan="3"><span>文件发放</span></th>
				        </tr>
			   		</thead>
			   		 <tfoot>
				      <tr>
				        <td colspan="3"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				          	<input type="hidden" name="act" value="save" />
				            <button id="buttonSave" onclick="getyhs();if(IsNoEmpty('wjh-wjm-wjscsm-yhms-kk')){showTips('数据保存中，请稍候...');refreshForm('fileSave.do')};">保存</button>
				            <button onclick="reset();"> 重置</button>
				          </div>
				          </td>
				      </tr>
				    </tfoot>
			   		
					<tr>
						<th width="10%" align="center">
							<font color="red">*</font>文件号:
						</th>
						<td colspan="2">
							<input type="text" name="wjh" id="wjh" size="50" maxlength="20" value="${rs.wjh }"/>
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
							<textarea rows="10"  name="wjscsm" id="wjscsm"
								onblur="chLeng(this,800)" style="width:95%"
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
										<div style="overflow: auto;height:250px;width:200px;" id="div1">					
											<table  id="yhzinfo">
												<thead align="center" >
													<td style="width:140px">用 户 组</td>
												</thead>
												<tr>
													<td>
														<logic:iterate id="s" name="yhzList">
															<input type="checkbox" value="<bean:write name="s" property="zdm"/>" onclick="selectall(this)"/>&nbsp;
															<span  style="font-size: 13;color: #003366" onclick="viewyh(this,'<bean:write name="s" property="zdm"/>');">
															<bean:write name="s" property="zmc"/>
															</span><br>
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
															
															<input type="checkbox" value="<bean:write name="s" property="yhm"/>" onclick="selectjsr(this)"/>&nbsp;<span id="xm"><bean:write name="s" property="xm" /></span><br>
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
							<textarea name="jsrxm" rows="16" cols="30" readonly="true"  id="jsrxm" type="_moz">${jsrxm}</textarea>
						</td>
					</tr>				
					<tr>
						<th align="center">
							<font color="red">*</font>请选择文件：
						</th>
						<td align="left" colspan="2">
							<div>	
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
		</html:form>
	</body>
</html>
