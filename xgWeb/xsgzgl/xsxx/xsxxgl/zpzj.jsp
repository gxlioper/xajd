<%@ page language="java" contentType="text/html; charset=GBK"%>
				<div id="stuImg" class="open_img" style="margin-left:0px;margin-right:5px;margin-top: 0px;height: 130px">
					<img style="width:100px;height:130px" id="xszp"
						src="xsxx_xsgl.do?method=showPhoto&xh=${xh}"
						border="0">
				</div>
				<div id ="zpscbtn" align="center">
					<button  type="button"  onclick="showZpscZjDiv();" >
					�ϴ���Ƭ
					</button>
				</div>
				
				<div id="stuGkImg" class="open_img" style="margin-left:0px;margin-right:5px;margin-top: 5px;height: 130px">
					<img style="width:100px;height:130px" id="xsgkzp"
						src="xsxx_xsgl.do?method=showGkPhoto&xh=${xh}"
						border="0">
				</div>
				<div id ="gkzpscbtn" align="center">
					<button  type="button"  onclick="showgkZpscZjDiv();" >
					�߿���Ƭ
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
									<input type="file" id="stuPic" name="stuPic" style="width:90%"
										   onchange='uploadStuPic();closeWindown();'
									/>
									<br/>
									<span style="color:red">ע�����ϴ�jpg��gif��png��bmp ��ʽ���ļ����� 1 M ��</span>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>	
			
				
											<!-- �ϴ��߿���Ƭ -->
			<div id="addGkPic" style="display:none">
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
									<input type="file" id="stuGkPic" name="stuGkPic" style="width:90%"
										   onchange='uploadStuGkPic();closeWindown();'
									/>
									<br/>
									<span style="color:red">ע�����ϴ�jpg��gif��png��bmp ��ʽ���ļ����� 1 M ��</span>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>	