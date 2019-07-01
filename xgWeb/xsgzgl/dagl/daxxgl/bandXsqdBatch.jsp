<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/dagl/daxxgl/bandXsqdBatch.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
	</head>
	<body >
		<html:form action="/daxxgl" method="post"  onsubmit="return false;" styleId="form">
			<div id="allDiv" style='width:100%;height:500px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr width="100%">
							<th colspan="4">
								<span>档案材料批量维护</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx" width="100%">
						<input type="hidden" id="searchTj" name="searchTj" value="${daxxglForm.searchTj }"/>
						<input type="hidden" id="searchTjz" name="searchTjz" value="${daxxglForm.searchTjz }"/>
						<input type="hidden" id="mhcx_lx" name="mhcx_lx" value="${daxxglForm.mhcx_lx }"/>
						<input type="hidden" id="searchLx" name="searchLx" value="${daxxglForm.searchLx }"/>
						<input type="hidden" id="input_mhcx" name="input_mhcx" value="${daxxglForm.input_mhcx }"/>
						<input type="hidden" id="path" name="path" value="${daxxglForm.path }"/>
						
						<html:hidden property="selected" styleId="selected" value="${selected}" />
						<html:hidden property="values" styleId="values" value="${values}" />
						<html:hidden property="yxzxss" styleId="yxzxss" value="${yxzxss}" />
						<html:hidden property="sysdate" styleId="sysdate" value="${sysdate}" />
						<!-- 
						<logic:equal name="selected" value="all">
							<tr id="xsfwSelect">
								<th width="16%">
									已选学生范围
								</th>
								<td colspan="3">
									<div class="search_advanced" id="sztj" style="width: 100%;float: left;margin-top:1px">
										<div id="yxtj_div" class="selected-attr">
											<dl id="yxtj_dl" style="width:100%; padding: 10px 0;">
											</dl>
										</div>
									</div>
								</td>
							<tr>
						</logic:equal>
						 -->
						<tr id="xsslSelect">
							<th width="18%">
								已选择学生
							</th>
							<td  colspan="3">
								<span style="color:red;">${yxzxss }</span>个
								<logic:notEmpty name="ywhdaqdxss">
									<logic:notEqual name="ywhdaqdxss" value="0">
									    &nbsp;&nbsp;<span style="color:blue;">(${ywhdaqdxss }个已维护，批量操作后会覆盖学生原档案材料信息！)</span>
									</logic:notEqual>
								</logic:notEmpty>
							</td>
						<tr>
							<th width="18%">
								<font color="blue">档案清单模板</font>
							</th>
							<td width="32%" >
								<html:select property="daqdmb_id" style="width:120px" styleId="daqdmb_id" value="${daqdmb_id}" onchange="czmy();return false;">
									<html:option value="">--请选择--</html:option>
									<html:options collection="mbList" property="daqdmb_id"
										labelProperty="daqdmb_mc" />
								</html:select></td>
							</td>
							<th width="18%">
								统一归档日期
							</th>
							<td width="32%" >
								<html:text property="dazrsj" styleId="dazrsj" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
							</td>
						</tr>
						<tr><td colspan="4">						
							<table id="mbnTable" width="100%">
							<tr width="100%" align="center"><td width="8%">顺序</td><td width="17%">材料名称</td><td width="14%">份数</td><td width="14%">页数</td><td width="17%">归档日期</td><td width="30%">备注</td></tr>
							<logic:notEmpty name="mbclList">
								<logic:iterate name="mbclList" id="c" indexId="index">
									<logic:equal name="c" property="zt" value="1">
										<tr align="center">
												<html:hidden property="sx"  value="${c.sx }" />
												<html:hidden property="bdzt"  value="${c.bdzt }" />
												<html:hidden property="daqdcl_id"  value="${c.daqdcl_id }" />
												<td>${c.sx }</td>
												<td>${c.daqdcl_mc }</td>
												<td><html:text property="fs"  value="${c.fs }" style="width:50%;"  maxlength="3" readonly="false" onkeyup="checkInputData(this);"/></td>
												<td><html:text property="ys"  value="${c.ys }" style="width:50%;" maxlength="3" readonly="false" onkeyup="checkInputData(this);"/></td>
												<td><html:text property="gdrq" value="${c.gdrq }" style="width:80%;"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
												<td><html:text property="bz"  value="${c.bz }" style="width:80%;"  maxlength="100" readonly="false" /></td>
										</tr>
									</logic:equal>
								</logic:iterate>
							</logic:notEmpty>
							</table>
							
						</td></tr>
						<tr>
							<th >
								是否追加模板外材料
							</th>
							<td colspan="3">
								<input type="radio" name="mbwclFlag" value="1" onclick="sfzjMbwcl();">是
								<input type="radio" name="mbwclFlag" value="0" checked="checked" onclick="sfzjMbwcl();">否
							</td>
						</tr>
						 <tr name="mbwclTr" >
							<th width="18%">
								<font color="blue">档案清单模板外材料</font>
							</th>
							<td colspan="3">
									<div style="float:left;">
										<a href="#" class="ico_add_sub1" onclick="delAllMbwcl();return false;"></a>
										<a href="#" class="ico_add_sub2" onclick="downMbwcl();return false;"></a>
										<a href="#" class="ico_add_sub3" onclick="upMbwcl();return false;"></a>
										<a href="#" class="ico_add_sub4" onclick="delMbwcl();return false;" ></a>
										<a href="#" class="ico_add_sub5" onclick="addMbwcl();return false;" ></a>
									</div>
									<div style="float:right;"><font color="red">材料不能和[档案清单模板]内的材料重复</font></div>
							</td>
						</tr>
						<tr name="mbwclTr" >
							<td colspan="4">
								<div id ="mbwclDiv">
									<table id="mbwTable" width="100%" border="0">
										<tr align="center">
											<td width="8%"></td>
											<td width="17%">材料名称</td>
											<td width="14%">份数</td>
											<td width="14%">页数</td>
											<td width="17%">归档日期</td>
											<td width="30%">备注</td>
										</tr>
										<tbody id="xmInfo" name="se">
											<logic:notEmpty name="mbwclList">
												<input type="hidden" id="mbwclList"  value="${mbwclList}" />
												<logic:iterate name="mbwclList" id="d" indexId="index">
													<tr onclick='clickTr(this);return false;'>
														<html:hidden property="daqdcl_id"  value="${d.daqdcl_id }" />
														<td><input type="checkbox" name ="oldMbwclCheckBox" /></td>
														<td>${d.daqdcl_mc}</td>
														<td><html:text property="fs"  value="${d.fs }" style="width:50%;"  maxlength="3" readonly="false" onkeyup="checkInputData(this);"/></td>
														<td><html:text property="ys"  value="${d.ys }" style="width:50%;" maxlength="3" readonly="false" onkeyup="checkInputData(this);"/></td>
														<td><html:text property="gdrq" value="${d.gdrq }" style="width:80%;"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
														<td><html:text property="bz"  value="${d.bz }" style="width:80%;"  maxlength="100" readonly="false" /></td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</tbody>
										 <tr align="center" index="1" id="mbwSpan" style="display:none">
										 	<td><input type="checkbox" name ="newMbwclCheckBox" /></td>
											<td>
												<select name="clid"  style="width:90px" styleId="clid"  onchange="selectCl(this);">
													<option value="">请选择</option>
													<logic:iterate name="clList" id="cl" >
														<option value="${cl.clid }">${cl.clmc }</option>
													</logic:iterate>
												</select>
											</td>
											<td><input type="text" name="_fs"   value="1"   maxlength="3" style="width:50%;" onkeyup="checkInputData(this);"/></td>
											<td><input type="text" name="_ys"     maxlength="3" style="width:50%;"  onkeyup="checkInputData(this);"/></td>
											<td><input type="text" name="_gdrq"  style="width:80%;"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
											<td><input type="text" name="_bz"    maxlength="100" style="width:80%;" /></td>
										</tr>
									</table>
								</div>
								
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table border="0" class="formlist" >	
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();return false;">
										保 存
									</button>
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
