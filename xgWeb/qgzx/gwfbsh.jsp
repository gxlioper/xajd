<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script>
		function save(){
			if(filedNotNull(['spbcbz','sqsyrs','sqsyknss'])){
				refreshForm('gwfb.do?method=gwfbsh&act=save');
			}else{
				alert('请将带*号的项目填写完整！');
				return false;
			}
		}
	</script>
</head>
<body>
	<html:form action="/gwfb.do?method=gwxx" method="post">
		<input type="hidden" id="path" name="path" value="${path}"/>
		<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>
		<div class="tab">
		  <table width="100%" class="formlist" id="rsT">
				<thead>
					<tr>
						<th colspan="4">
							<span>岗位信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>校区</th>
					<td>
						${rs.xqmc}
					</td>
					<th>岗位名称</th>
					<td>
						${rs.gwdm}
						<input type="hidden" id="gwdm" name="save_gwdm" value="${rs.gwdm}"/>
					</td>
				</tr>
				<tr>
					<th>岗位性质</th>
					<td>
						${rs.gwxzmc}
					</td>
					<th>申请单位</th>
					<td>
						${rs.yrdwmc}
					</td>
				</tr>
				<tr>
					<th>学年</th>
					<td>
						${rs.xn}
					</td>
					<th>年度</th>
					<td>
						${rs.nd}
					</td>
				</tr>
				<tr>
					<th>工作开始日期</th>
					<td>
						${rs.gzksrq}
					</td>
					<th>工作结束日期</th>
					<td>
						${rs.gzjsrq}
					</td>
				</tr>
				<tr>
					<th>需求人数</th>
					<td height="22" align="left">
						${rs.xyrs}
					</td>
					<th>审批使用人数</th>
					<td>
						${rs.sqsyrs}
					</td>
				</tr>
				<tr>
					<th>使用困难生数</th>
					<td>
						${rs.syknss}
					</td>
					<th>审批使用困难生数</th>
					<td>
						${rs.sqsyknss}
					</td>					
				</tr>
				<tr>
					<th>计酬方式</th>
					<td>
						${rs.jcfsmc}
					</td>
					<th>建议报酬标准</th>
					<td>
						${rs.jybcbz}
						 元(${rs.jcfsmc})
					</td>
				</tr>
				<tr>
					<th>联系电话</th>
					<td>
						${rs.lxdh}
					</td>
					<th>审批报酬标准</th>
					<td>
						${rs.spbcbz}
						 元(${rs.jcfsmc})
					</td>
				</tr>
				<tr>
					<th>工作时间</th>
					<td colspan="3">
						${rs.gzsj}						
						<span id="gzsjDw"></span>
					</td>							
											
				</tr>
				<tr>
					<th>工作内容</th>
					<td colspan="3">
						${rs.gznr}
					</td>
				</tr>		
				<tr>
					<th>岗位要求</th>
					<td colspan="3">
						${rs.gwtsyq}
					</td>
				</tr>
				<tr>
					<th>人员要求</th>
					<td colspan="3">
						${rs.ryyq}
					</td>
				</tr>
				<tr>
					<th>申请报告</th>
					<td colspan="3">
						${rs.sqbg}
					</td>
				</tr>
				<tr>
					<th>申请单位意见</th>
					<td colspan="3">
						${rs.sqdwyj}
					</td>
				</tr>				
				<tr>
					<th>备注</th>
					<td colspan="3">
						${rs.bz}
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>审批报酬标准</th>
					<td>
						<html:text name="rs" property="save_spbcbz" styleId="spbcbz" style="width:130px" maxlength="20" onkeyup="value=value.replace(/[^(\d|.)]/g,'') "></html:text>
						元(<bean:write name="rs" property="jcfsmc" />)
					</td>
					<th><span class="red">*</span>审批使用人数</th>
					<td>
						<html:text name="rs" property="save_sqsyrs" styleId="sqsyrs" style="width:130px" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						人
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>审批困难生数</th>
					<td>
						<html:text name="rs" property="save_sqsyknss" styleId="sqsyknss" style="width:130px" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						人
					</td>
					<th>审核</th>
					<td>
						<html:select name="rs" property="save_shjg" styleId="shjg">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>学生处意见</th>
					<td colspan="3">
						<html:textarea name="rs" property="save_xgbyj" cols="60" rows="3" styleId="xscyj" onblur="chLeng(this,'150')"></html:textarea>
					</td>
				</tr>
				</tbody>
				
			      <tr>
			        <td colspan="4">
			          <div class="btn">
							<logic:equal value="yes" name="writeAble">
							<button type="button"  id="buttonSave" onclick="save();return false;">
								保存
							</button>
							</logic:equal>
							<button type="button"  id="buttonClose" onclick="Close();return false;">
								关闭
							</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</div>
	</html:form>

	<logic:present name="result">
		<intput type="hidden" id="message" value="${message}"/>
		<script>
			alert(document.getElementById('message').value);
			if(window.dialogArguments){
		 		window.dialogArguments.document.getElementById('search_go').onclick();
		 	}
			Close();
		</script>
	</logic:present>
</body>
</html>
