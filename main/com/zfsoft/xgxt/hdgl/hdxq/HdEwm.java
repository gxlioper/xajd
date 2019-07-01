package com.zfsoft.xgxt.hdgl.hdxq;

import com.swetake.util.Qrcode;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 *��ά��
 * Created by llf on 2019/6/13.
 */
public class HdEwm {
    public String getEwm(String hdid) throws Exception{
        String base64_png = "";
        Qrcode x=new Qrcode();
        x.setQrcodeErrorCorrect('M');//����ȼ�
        x.setQrcodeEncodeMode('B');//N ��������; A ����a-A; B ���������ַ�
        x.setQrcodeVersion(7);//�汾

        String qrData = hdid;

        int width = 67 + 12*(7-1);
        int height = 67 + 12*(7-1);
        int pixoff = 2;//ƫ����

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D gs = bufferedImage.createGraphics();
        gs.setBackground(Color.WHITE);
        gs.setColor(Color.BLACK);
        gs.clearRect(0, 0, width, height);

        byte[] d =qrData.getBytes("utf-8");
        if (d.length>0 && d.length <120){
            boolean[][] s = x.calQrcode(d);

            for (int i=0;i<s.length;i++){
                for (int j=0;j<s.length;j++){
                    if (s[j][i]) {
                        gs.fillRect(j*3+pixoff,i*3+pixoff,3,3);
                    }
                }
            }
        }

        gs.dispose();
        bufferedImage.flush();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] bytes = baos.toByteArray();//ת�����ֽ�
        BASE64Encoder encoder = new BASE64Encoder();
        base64_png = encoder.encodeBuffer(bytes).trim();
        base64_png = base64_png.replaceAll("\n", "").replaceAll("\r", "");//ɾ�� \r\n
        return base64_png;
    }
}
