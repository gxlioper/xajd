<%@ page language="java" contentType="text/html; charset=GBK"%>
				<div id="wpImg" class="open_img" style="margin-left:0px;margin-top: 0px;height: 130px">
					<img style="width:160px;height:144px" id="axwptp"
						src="axcsWpsz.do?method=showPhoto&xmdm=${xmdm}&type=update"
						border="0">
				</div>
				<div id ="zpscbtn" align="center">
					<button  type="button"  onclick="showZpZjDiv();" >
					�ϴ���Ƭ
					</button>
				</div>
				
				
			<!-- �ϴ���Ƭ -->
			<div id="addPic" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ϴ���Ƭ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="file" id="xmtp" name="xmtp" style="width:90%"
										   onchange='uploadWpPic();closeWindown();'
									/>
									<br/>
									<span style="color:red">ע�����ϴ�jpg��gif��png��bmp ��ʽ���ļ����� 1 M ��</span>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>	