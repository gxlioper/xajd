<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/commanFunction.js"></script>
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getOtherData.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getGyglWsjcDAO.js"></script>
	<script type="text/javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="js/gygl/xsfslrUpdate.js" defer="defer"></script>
	<script type="text/javascript">
		function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = $('url').value;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}
		
		function tipsAndSave(){
			var zgh = $('zgh').value;
			if(zgh != ""){			
				BatAlert.showTips("���ڱ��棬���Ժ�");
				saveData('xysf_dyjsgl.do?method=addDyjs&doType=save','zgh');
			}else{
				alert("�뽫��\"*\"����Ŀ����������");
			}
		}
		
		jQuery(function(){
			onShow();
		})
	</script>
</head>
<body >
	<html:form action="/commWsjc" method="post">
		<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
		
		<input type="hidden" name="xh" value="${rs.xh }"/>
		<input type="hidden" name="xn" value="${xn }"/>
		<input type="hidden" name="xq" value="${xq }"/>
		<input type="hidden" name="nd" value="${nd }"/>
		<input type="hidden" name="lrr" value="${userName }"/>
		<input type="hidden" name="lrsj" value="${lrsj }"/>
		<input type="hidden" name="jczq" value="${jczq }"/>
		
		<div class="tab">
		<table class="formlist" width="93%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>ѧ��������������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					ѧ��
				</th>
				<td align="left" width="30%">
				  	${rs.xh }
				</td>
				<th width="20%">
					����
				</th>
				<td width="30%">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th>ѧ��</th>
				<td>${xn }</td>
				<th>ѧ��</th>
				<td>${xqmc }</td>
			</tr>
			<tr>
				<th>���</th>
				<td>${nd }</td>
				<logic:equal name="jczq" value="��">
					<th>����ܴ�</th>
					<td><input type="text" readonly="readonly" name="jczc" value="${jczc }" /></td>
				</logic:equal>
				<logic:equal name="jczq" value="��">
					<th>�������</th>
					<td><input type="text" readonly="readonly" name="jcsj" value="${jcsj }" /></td>
				</logic:equal>
			</tr>
			<tr>
				<th>
					<bean:message key="lable.xb" />
				</th>
				<td>
					${rs.xymc }
				</td>
				
				<th>
					רҵ
				</th>
				<td>
					${rs.zymc }
				</td>
			</tr>
			
			<tr>
				<th>
					�༶
				</th>
				<td>
					${rs.bjmc }
				</td>
				
				<th>
					¥��
				</th>
				<td>
					${rs.ldmc}
				</td>
				
			</tr>
			<tr>
				<th>
					���Һ�
				</th>
				<td>
					${rs.qsh }
				</td>
				<th>
					��λ��
				</th>
				<td>
					${rs.cwh }
				</td>
			</tr>
			</tbody>
			
		</table>
		<br/>
		<div align="right">
			<p>
				<input  value="+"
					onclick="trAdd('flag1','add','numAdd1','rzqk');"
					style="width: 20px" />
				<input  value="-" onclick="trDel('flag1', 'delRow1');" style="width: 20px" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
				<input type="text" name="numAdd" id="numAdd1" style="width: 20px"
					onblur="trAdd('flag1','madd','numAdd1','rzqk')"/>
				&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
				<input type="text" name="numDel" id="numDel1" style="width: 20px"
					onblur="trDelAll('flag1','numDel1')"/>
				&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
			</div>
			<table align="center" style="width: 100%" id="rsT"
				class="formlist">
				<!-- ��ӡʱ��һ�в���ʾ- -->
				<thead>
				 <tr>
				 	<th colspan="6"><span>����Ա��ѵ��Ϣ</span></th>
				 </tr>
				</thead>
				<thead style="height: 10px">
					<tr>
						<td align="center" nowrap="nowrap" style='width: 10%'>
							ѡ��ɾ����
						</td>
						<td align="center" nowrap="nowrap" style='width: 10%'>
							����
						</td>
						<td align="center" nowrap="nowrap" style='width: 70%'>
							ԭ��
						</td>
						<td align="center" nowrap="nowrap" style='width: 10%'>
							����
						</td>
					</tr>
				</thead>
				<tbody align="center" id="flag1">
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="6">
			          <div class="btn">
			          	<logic:notEqual value="view" name="doType">
			          		<button type="button" name="����" onclick="save('/xgxt/commWsjc.do?method=xsfslrUpdate&doType=save');">����</button>
			          	</logic:notEqual>
			          		<button type="button" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
		</div>
		
		<div style="display: none">
			<select id="add_select">
				<logic:iterate id="map" name="addXmList">
					<option value="${map.xmdm}">${map.xmmc}</option>
				</logic:iterate>
			</select>
			<select id="reduce_select">
				<logic:iterate id="map" name="reduceXmList">
					<option value="${map.xmdm}">${map.xmmc}</option>
				</logic:iterate>
			</select>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				window.dialogArguments.document.getElementById('search_go').click();
			}
		</script>
	</logic:present>
</body>
</html>
