<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/systemFunction.js"></script>
	<script	type="text/javascript">
		jQuery(function(){
		
			// ���水ť
			jQuery('#buttonSave').click(function(){
				saveData('njjs_jygl.do?method=xssbModi&doType=save','bynd-sfzh');
			});
			
			// �رհ�ť
			jQuery('#buttonClose').click(function(){
				window.close();
			});
			
			jQuery('#sfzh').blur(function(){
				checkSfzh(this);
			});
			
			if(!jQuery("#shi").val()){
				jQuery("#shi").hide();
			}
			
			// ʡ����
			jQuery("#shen").change(function(){
				if(jQuery(this).val()){
					jQuery.getJSON('njjs_jygl.do?method=loadShi',{shen:this.value},function(data){
						jQuery("#shi").empty().show();
						if(data != null && data.length>0){
							for(var i=0; i<data.length; i++){
								var option = jQuery("<option value='"+ data[i].dm+"'>"+ data[i].mc+"</option>");
								jQuery("#shi").append(option);
							}
						}else{
							jQuery("#shi").hide();
						}
					});
				}else{
					jQuery("#shi").empty().hide();
				}
			});
		});
	</script>
	<style type="text/css">
		table{
			border-collapse:collapse;
		}
		table th{
			width:20%;
		}
		
		table td{
			width:30%;
		}
		
		table span{
			color:red;
		}
		#shen{
			width:200px;
		}
		
		#shi{
			width:200px;
		}
		
		#jtszd,#lxfs{
			width:88%;
		}
	</style>

</head>
<body>
	<html:form action="/njjs_jygl" method="post">
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵ����Ϣ�޸�</a>
			</p>
		</div>
		<div class="tab">
		<table class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>��ҵ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>ѧ��</th>
					<td>
						${rs.xh }
						<input type="hidden" name="xh" value="${rs.xh }"/>
					</td>
					<th><span>*</span>���֤��</th>
					<td>
						<html:text styleId="sfzh" property="sfzh" value="${rs.sfzh }"></html:text>
					</td>
				</tr>
				<tr>
					<th>����</th>
					<td>
						${rs.xm }
						<input type="hidden" name="xm" value="${rs.xm }"/>
					</td>
					<th>�Ա�</th>
					<td>
						${rs.xb }
						<input type="hidden" name="xb" value="${rs.xb }"/>
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xb" /></th>
					<td>
						<input type="hidden" name="xydm" value="${rs.xydm }"/>
						<input type="hidden" name="xymc" value="${rs.xymc }"/>
						${rs.xymc }
					</td>
					<th>רҵ</th>
					<td>
						<input type="hidden" name="zydm" value="${rs.zydm }"/>
						<input type="hidden" name="zymc" value="${rs.zymc }"/>
						${rs.zymc }
					</td>
				</tr>
				<tr>
					<th>�༶</th>
					<td>
						<input type="hidden" name="bjdm" value="${rs.bjdm }"/>
						<input type="hidden" name="bjmc" value="${rs.bjmc }"/>
						${rs.bjmc }
					</td>
					<th>
						<span>*</span>���ܵȼ�
					</th>
					<td>
						<html:select property="jndj" value="${rs.jndj}">
							<html:option value=""></html:option>
							<html:options collection="pyccList" labelProperty="mc" property="dm"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>ѧ��</th>
					<td>
						<input type="hidden" name="xz" value="${rs.xz }"/>
						${rs.xz }
					</td>
					<th>�꼶</th>
					<td>
						<input type="hidden" name="nj" value="${rs.nj }"/>
						${rs.nj }
					</td>
				</tr>
				<tr>
					<th><span>*</span>��ҵ���</th>
					<td>
						<html:text property="bynd" styleId="bynd" value="${rs.bynd}"></html:text>
					</td>
					<th>������ʽ</th>
					<td>
						<html:select property="pyfs" value="${rs.pyfs}">
							<html:option value=""></html:option>
							<html:options collection="pyfsList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
				</tr>
				<tr>
				<th>
						<span></span>ѧ��
					</th>
					<td colspan="3">
						<html:select property="xl" value="${rs.xl}">
							<html:option value=""></html:option>
							<html:options collection="xlList" labelProperty="mc" property="dm"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>��Դ���ڵ�</th>
					<td colspan="3">
						<html:select property="shen" styleId="shen" value="${shen}">
							<html:option value=""></html:option>
							<html:options collection="shenList" property="dm" labelProperty="mc"/>
						</html:select>
						<html:select property="sydq" styleId="shi" value="${rs.sydq}">
							<html:option value=""></html:option>
							<html:options collection="shiList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>��ͥ��ַ</th>
					<td colspan="3">
						<html:text property="jtszd" styleId="jtszd" value="${rs.jtszd }" maxlength="40"></html:text>
					</td>
				</tr>
				<tr>
					<th>��ϵ��ʽ</th>
					<td colspan="3">
						<html:text property="lxfs" styleId="lxfs" value="${rs.lxfs }" maxlength="25"></html:text>
					</td>
				</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<logic:notEqual value="view" name="doType">
			          		<button id="buttonSave">����</button>
			          	</logic:notEqual>
			            <button id="buttonClose">�ر�</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			jQuery(function (){
				alertInfo(jQuery('#message').val(), function(){
					window.close();
					if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
						window.dialogArguments.document.getElementById('search_go').click();
					}
				});
			});
		</script>
	</logic:present>
</body>
</html>
