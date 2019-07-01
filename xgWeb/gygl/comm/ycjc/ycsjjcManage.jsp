<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglYcsjjc.js'></script>

		<script type="text/javascript">
			function getYcsjInfo(obj,yclx){
				gyglYcsjjc.getCountByType(yclx,function(data){
					jQuery('span:first',jQuery(obj)).attr('style','display:black');
					jQuery('b',jQuery(obj)).attr('innerHTML',data);
					
					if (data>0){
						jQuery(obj).attr('href','gyglYcjc.do?method=queryYcsj&yclx='+yclx);
					}
					
				});
			
				obj.onmouseover = null;
			}
			
			function showTbody(obj,objTbody){
				if(obj.className=="up"){
					obj.className="down";
					obj.parentNode.parentNode.className="cur-tr";
					document.getElementById(objTbody).style.display="none";
				}else{
					obj.className="up";
					obj.parentNode.parentNode.className="";
					document.getElementById(objTbody).style.display="";
				}
			}
			
			function yjjc(obj){
				var tr = jQuery(obj).parents('tbody');
				
				for (var i = 0 ; i < tr.length; i++){
					jQuery('a',tr[i]).mouseover();
				}
			}
			
			function checkAll(){
				var btns = jQuery('.btn_common');
				for (var i = 0 ; i < btns.length; i++){
					btns[i].click();
				}
			}
			
		</script>
		
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<body>
		<div class="tab">
			<div class="check_data">
				<img src="<%=stylePath%>/images/blue/ico_70.gif" />
				<div class="con">
					<h4>
						ȫ�����ݼ��
					</h4>
					<h5>
						�Ե��������ȫ�����һ���������ݼ�⣬����ָ�����ݼ����������ݡ�
					</h5>
				</div>
				<a class="check_all" href="#" onclick="checkAll()">ȫ����</a>
			</div>
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<td colspan="2">
							<span>ָ�����ݼ��</span>
						</td>
					</tr>
				</thead>
				<thead>
					<tr>
						<th colspan="2">
							<a href="#" class="up"
								onclick="showTbody(this,'myTbody2');return false">�������</a>
						</th>
					</tr>
				</thead>
				<tbody id="myTbody2">
					<tr>
						<td rowspan="4">
							<button class="btn_common" onclick="yjjc(this);">
								һ�����
							</button>
							<br />
							<p style="margin-top:5px;">
								��������ص�
								<br />
								��Ϣ���м��
							</p>
						</td>
						<td>
							¥�����з�������ᳬ�����¥��
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'fpxxbfjbsz')"> 
								<img src="<%=stylePath%>/images/blue/ico_59.gif" /> 
								<span class="show_data"  style="display:none;">
									�쳣����<font color="red"><b></b>
									</font>�� 
								</span> </a>
						</td>
					</tr>
					<tr>
						<td>
							������ı�������
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'BLQSBFP')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									�쳣����<font color="red"><b></b>
								</font>�� </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							����¥����߲���������
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'SSCGZGC')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									�쳣����<font color="red"><b></b>
								</font>�� </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							������¥���Ա�Ҫ�������
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'YXBFCSS')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									�쳣����<font color="red"><b></b>
								</font>�� </span> </a>
						</td>
					</tr>
				</tbody>

				<thead>
					<tr>
						<th colspan="2">
							<a href="#" class="up"
								onclick="showTbody(this,'myTbody3');return false">��λ���</a>
						</th>
					</tr>
				</thead>
				<tbody id="myTbody3">
					<tr>
						<td rowspan="2">
							<button class="btn_common" onclick="yjjc(this);">
								һ�����
							</button>
							<br />
							<p style="margin-top:5px;">
								�Դ�λ��ص�
								<br />
								��Ϣ���м��
							</p>
						</td>
						<td>
							����¥����߲����Ĵ�λ
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'CWCGZGC')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									�쳣����<font color="red"><b></b>
								</font>�� </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							����¥����߲�����������λ
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'QTCWCGZGC')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									�쳣����<font color="red"><b></b>
								</font>�� </span> </a>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="2">
							<a href="#" class="up"
								onclick="showTbody(this,'myTbody4');return false">ѧ�����</a>
						</th>
					</tr>
				</thead>
				<tbody id="myTbody4">
					<tr>
						<td rowspan="6">
							<button class="btn_common" onclick="yjjc(this);">
								һ�����
							</button>
							<br />
							<p style="margin-top:5px;">
								��ѧ����ص�
								<br />
								��Ϣ���м��
							</p>
						</td>
						<td>
							���˷�����ͬ�Ŵ���ѧ��
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'DRZTC')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									�쳣����<font color="red"><b></b>
								</font>�� </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							�ִ�����������ѧ��
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'FCBM')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									�쳣����<font color="red"><b></b>
								</font>�� </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							��ס�������ҵ�ѧ��
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'BLQSBZR')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									�쳣����<font color="red"><b></b>
								</font>�� </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							��ס���λ��ѧ��
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'XLCWBZR')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									�쳣����<font color="red"><b></b>
								</font>�� </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							���Ա𲻷��ִ������ѧ��
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'YXBFCSS')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									�쳣����<font color="red"><b></b>
								</font>�� </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							����ס����������λ����������ס��ѧ��
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'RZRSCGCWS')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									�쳣����<font color="red"><b></b>
								</font>�� </span> </a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<br />
	</body>
</html>
