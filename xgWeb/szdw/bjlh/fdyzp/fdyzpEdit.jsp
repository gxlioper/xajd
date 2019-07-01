<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>

		<script type="text/javascript"
			src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script language="javascript">
			function addTr(isMore){
				var tr = "<tr><td><input type='checkbox'/></td>";
					tr+="<td> <input type='text' name='khxm' maxlength='50' style='width:95%'/> </td>";
					tr+="<td> <input type='text' name='xmbz' maxlength='50' style='width:95%'/> </td>";
					tr+="<td> <select name='xmlx'><option value='0'>必选项</option>";
					tr+="<option value='1'>参选项</option>";
					tr+="<option value='2'>加分项</option>";
					tr+="</select></td></tr>";
					jQuery('#sljxTab').append(tr);
			}
			
			function delTr(){
				var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
				
				if (checkbox.length > 0){
					for (var i = 0 ; i < checkbox.length ; i++){
						jQuery(checkbox[i]).parents("tr").eq(0).remove();
					}
				} else {
					alertInfo('请选择您要删除的行!');
				}
			}
			
			function selectAll(obj){ 
				jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
			}
		</script>
	</head>
	<body>
		<html:form action="/bjlh_fdyzp" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody >					
						<tr>
							<td align="left" colspan="2" width="30%">
								<span class="red">*</span>学年:
								<logic:equal value="add" name="doType">
									<html:select property="xn">
										<html:options collection="addxnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</logic:equal>								
								<logic:notEqual value="add" name="doType">
									<html:hidden property="zpbid" />
									${rs.xn}
								</logic:notEqual>
							</td>
							<td align="left" colspan="2" width="70%">
									<span class="red">*</span>名称：<html:text name="rs" property="mc"
										style="width:80%" />
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>项目信息</span>
							</th>
						</tr>						
						<tr>
							<td colspan="4">
								<logic:notEqual value="view" name="doType">
									<logic:notEqual value="copy" name="doType">
									<button type="button" onclick="addTr();" class="btn_01">
										增加项目
									</button>
									<button type="button" onclick="delTr();" class="btn_01">
										删除项目
									</button>
									</logic:notEqual>
								</logic:notEqual>
							</td>
						</tr>
					</thead>
				</table>					
				<table width="100%" border="0" class="dateline">				
					<thead id="sljxTab">
						<tr>
							<td width="17.5px">
								<logic:notEqual name="doType" value="view">
									<input type="checkbox" name="th" onclick="selectAll(this)" />
								</logic:notEqual>
							</td>
							<td width="330px">
								考核项目
							</td>
							<td width="330">
								备注
							</td>
							<td>
								项目类型
							</td>
						</tr>
					</thead>
					<tbody>					
					<logic:present name="xmList">
						<logic:iterate id="j" name="xmList">
							<tr>
								<td>
									<logic:notEqual name="doType" value="view">
										<input type="checkbox" />
									</logic:notEqual>
								</td>
								<td>
									<input type="text" name="khxm" value="${j.khxm }"
										maxlength="50" style="width:95%"/>
								</td>
								<td>
									<input type="text" name="xmbz" value="${j.bz }"
										maxlength="50" style="width:95%"/>
								</td>
								<td>
									<html:select name="j" property="xmlx">
										<html:option value="0">必选项</html:option>
										<html:option value="1">参选项</html:option>
										<html:option value="2">加分项</html:option>
									</html:select>
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:notEqual value="view" name="doType">
											<button type="button" name="保存" onclick="saveUpdate(	'/xgxt/bjlh_fdyzp.do?method=fdyzpEdit&doType=save', 'xn-mc');" id="buttonSave">
												保 存
											</button>
									</logic:notEqual>
									<button type="button" name="关闭" onclick="Close();return false;"; id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<logic:present name="message">
				<script defer>
	alertInfo('${message}',
			function() {
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go')
							.click();
				}
			});
</script>
			</logic:present>
			<logic:present name="error">
				<script defer>
	alert('${error}');
</script>
			</logic:present>

		</html:form>

	</body>
</html>
