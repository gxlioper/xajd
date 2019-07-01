<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	</head>
	<script>
	function saveorUpdate(type){
		var bynd = jQuery('#save_bynd').val();
		if(bynd==null||bynd==""){
			alert("请先填写毕业年度！");
			return false;
			}
		
		if(type=="update"){
			saveUpdate('jyglTables.do?method=njjsJytjbUpdate&doType=update','')
		}else{
			saveUpdate('jyglTables.do?method=njjsJytjbUpdate&doType=save','')
		}
		}

	</script>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jyglTables" method="post">
			<input type="hidden" name="pkValue" value="${stu.xh }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:notEmpty name="rs">
										<button id="buttonSave" onclick="saveorUpdate('update')">
											保存
										</button>
									</logic:notEmpty>
									<logic:empty name="rs">
										<button id="buttonSave" onclick="saveorUpdate('save')">
											保存
										</button>
									</logic:empty>
									<button id="buttonSave" onclick="window.open('jyglTables.do?method=printNjjsJytjb&xh=${stu.xh }')">
										打印
									</button>
									<button id="buttonSave" onclick="refreshForm('jyglTables.do?method=showStudents&doType=query')">
										返回
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="34%">
								<html:hidden property="save_xh" value="${stu.xh }"/>
								${stu.xh }
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								${stu.xm }
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								${stu.xb }
							</td>
							<th>
								民族
							</th>
							<td>
								${stu.mzmc }
							</td>
						</tr>
						<tr>
							<th>
								出生年月
							</th>
							<td>
								${stu.csrq }
							</td>
							<th>
								政治面貌
							</th>
							<td>
								${stu.zzmmmc }
							</td>
						</tr>
						<tr>
							<th>
								家庭地址
							</th>
							<td colspan="3">
								${jtcy.jtszd}
							</td>
						</tr>
						<tr>
							<th>
								邮政编码
							</th>
							<td>
								${jtcy.jtyb }
							</td>
							<th>
								健康状况
							</th>
							<td>
								${stu.jkzk }
							</td>
						</tr>
						<tr>
							<th>
								特长
							</th>
							<td colspan="3">
								${stu.tc }
							</td>
						</tr>
						<tr>
							<th>
								联系电话
							</th>
							<td>
								${stu.lxdh }
							</td>
							<th>
								在学校任何职务
							</th>
							<td>
								${zw }
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>毕业年度
							</th>
							<td colspan="3">
								<html:select property="save_bynd" styleId="save_bynd" value="${rs.bynd }">
    							<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								奖惩情况<br/>
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="save_jcqk" style="width:95%" rows="3" onblur="checkLen(this,200)" value="${rs.jcqk }"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								毕业鉴定<br/>
								<font color="red">(限400字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="save_byjd" style="width:95%" rows="6" onblur="checkLen(this,200)" value="${rs.byjd }"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								职业技术等级
							</th>
							<td>
								<html:text property="save_zyjsdj" maxlength="20" value="${rs.zyjsdj }"></html:text>
							</td>
							<th>
								职业鉴定理论成绩
							</th>
							<td>
								<html:text property="save_zjjdllcj" maxlength="20" value="${rs.zjjdllcj }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								职业鉴定技能成绩
							</th>
							<td>
								<html:text property="save_zjjdjncj" maxlength="20" value="${rs.zjjdjncj }"></html:text>
							</td>
							<th>
								操作等第
							</th>
							<td>
								<html:text property="save_cxdd" maxlength="20" value="${rs.cxdd }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xb" />推荐意见<br/>
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="save_xytjyj" style="width:95%" rows="3" onblur="checkLen(this,200)" value="${rs.xytjyj }"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								接收单位意见<br/>
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="save_jsdwyj" style="width:95%" rows="3" onblur="checkLen(this,200)" value="${rs.jsdwyj }"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								备注<br/>
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="save_bz" style="width:95%" rows="3" onblur="checkLen(this,200)" value="${rs.bz }"></html:textarea>
							</td>
						</tr>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script defer="defer">
				alert('${message}');
			</script>
		</logic:present>
	</body>
</html>
