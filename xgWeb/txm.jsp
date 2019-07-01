<%@ page contentType="image/jpeg"
	import="java.awt.*,java.awt.image.*,javax.imageio.*"
	import="com.bokai.graphics"%>
<%@ page import="java.awt.Color"%>
<%!public int evalchar(char echar) {
	String tmp = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%*";
	int i = 0;
	while (i <= tmp.length()) {
	    if (tmp.charAt(i) == echar) {
		return i;
	    }
	    i++;
	}
	return 0;
    }%>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
String str = "12345678901234";//request.getParameter("").toUpperCase();
//String str = "1234567";
int w = 400;
int h = 200;
String[] strBarTable = new String[] { "000110100", "100100001",
	"001100001", "101100000", "000110001", "100110000",
	"001110000", "000100101", "100100100", "001100100",
	"100001001", "001001001", "101001000", "000011001",
	"100011000", "001011000", "000001101", "100001100",
	"001001100", "000011100", "100000011", "001000011",
	"101000010", "000010011", "100010010", "001010010",
	"000000111", "100000110", "001000110", "000010110",
	"110000001", "011000001", "111000000", "010010001",
	"110010000", "011010000", "010000101", "110000100",
	"011000100", "010101000", "010100010", "010001010",
	"000101010", "010010100" };
BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
Graphics g = image.getGraphics();
g.setColor(new Color(255, 255, 255));
g.fillRect(0, 0, w, h);
g.setColor(new Color(0, 0, 0));
int linetop = 0;
int leftside = 10;
int binarray;
int loop1;
int loop2;
String newbchar = "*" + str.toUpperCase() + "*";
for (loop1 = 0; loop1 < newbchar.length(); loop1++) {
	binarray = evalchar(newbchar.charAt(loop1));
	g.setColor(new Color(0, 0, 0));
	g.drawString(newbchar.substring(loop1, loop1 + 1), 38 + loop1 * 22, 170);
	for (loop2 = 0; loop2 < strBarTable[binarray].length(); loop2++) {
	    if (loop2 % 2 == 1) {
			g.setColor(new Color(255, 255, 255));
	    } else {
			g.setColor(new Color(0, 0, 0));
	    }
	    if (strBarTable[binarray].charAt(loop2) == '1') {
			g.fillRect(leftside, linetop, 3, 155);
			leftside += 3;
	    } else {
			g.fillRect(leftside, linetop, 1, 155);
			leftside += 1;
	    }
	    g.setColor(new Color(255, 255, 255));
	    g.fillRect(leftside, linetop, 1, 155);
	    leftside += 1;
	}
}
g.dispose();
ImageIO.write(image, "BMP", response.getOutputStream());
%>
