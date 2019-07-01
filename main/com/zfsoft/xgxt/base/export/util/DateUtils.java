package com.zfsoft.xgxt.base.export.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sf.json.JSONObject;

/**
 * Created by IntelliJ IDEA.
 * User: zhaosheng
 * Date: 12-8-7
 * Time: ����11:11
 * To change this template use File | Settings | File Templates.
 */
public class DateUtils {

    /**
     * yyyy-MM-dd HH:mm:ss��ʽ����
     * */
    private static final String FORMAT_ONE_REG = "^\\d{2,4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$";
    /**
     * yyyy-MM-dd HH:mm:ss��ʽSimpleDateFormat����
     * */
    private static final SimpleDateFormat FORMAT_ONE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * yyyy-MM-dd��ʽ����
     * */
    private static final String FORMAT_TWO_REG = "^\\d{2,4}\\-\\d{1,2}\\-\\d{1,2}$";

    /**
     * yyyy-MM-dd��ʽSimpleDateFormat����
     * */
    private static final SimpleDateFormat FORMAT_TWO = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * yyyy/MM/dd HH:mm:ss��ʽ����
     * */
    private static final String FORMAT_THREE_REG = "^\\d{2,4}\\/\\d{1,2}\\/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$";

    /**
     * yyyy/MM/dd HH:mm:ss��ʽSimpleDateFormat����
     * */
    private static final SimpleDateFormat FORMAT_THREE = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * yyyy/MM/dd��ʽ����
     * */
    private static final String FORMAT_FOUR_REG = "^\\d{2,4}\\/\\d{1,2}\\/\\d{1,2}$";

    /**
     * yyyy/MM/dd��ʽSimpleDateFormat����
     * */
    private static final SimpleDateFormat FORMAT_FOUR = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * yyyy-MM-dd HH:mm��ʽ����
     * */
    private static final String FORMAT_FIVE_REG = "^\\d{2,4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}$";

    /**
     * yyyy-MM-dd HH:mm��ʽSimpleDateFormat����
     * */
    private static final SimpleDateFormat FORMAT_FIVE = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private static HashMap<String,SimpleDateFormat> PRESET_FORMAT = new HashMap<String,SimpleDateFormat>();

    static {
        PRESET_FORMAT.put("1",FORMAT_ONE);
        PRESET_FORMAT.put("2",FORMAT_TWO);
        PRESET_FORMAT.put("3",FORMAT_THREE);
        PRESET_FORMAT.put("4",FORMAT_FOUR);
        PRESET_FORMAT.put("5",FORMAT_FIVE);
    }

    /**
     * ���ݴ���ĸ�ʽ����ʽ�Ե�ǰ�ռ���и�ʽ�󷵻�<br/>
     * ���formatΪnull����򷵻�yyyy-MM-dd HH:mm:ss��ʽ������<br/>
     * format�ɴ����Զ�������ڸ�ʽ,Ҳ��ʹ��Ԥ�ø�ʽ,����:<br/>
     *  <ul>
     *      <li>1    yyyy-MM-dd HH:mm:ss</li>
     *      <li>2    yyyy-MM-dd</li>
     *      <li>3   yyyy/MM/dd HH:mm:ss </li>
     *      <li>4   yyyy/MM/dd   </li>
     *      <li>5   yyyy-MM-dd HH:mm </li>
     *  </ul>
     * @param format ��ʽ
     * @return ��ǰ���ھ���ʽ������ַ�����ʽ
     * */
    public static String getCustomFomratCurrentDate(String format) {
        if(isNotNull(format)){
            SimpleDateFormat presetFormat = PRESET_FORMAT.get(format);
            if(presetFormat != null){
                return presetFormat.format(new Date());
            }
            SimpleDateFormat custom = new SimpleDateFormat(format);
            return custom.format(new Date());
        }
        return FORMAT_ONE.format(new Date());
    }
    /**
     * ���ݴ���ĸ�ʽ����ʽ�Դ�������ڶ�����и�ʽ�󷵻�<br/>
     * ���formatΪnull����򷵻�yyyy-MM-dd HH:mm:ss��ʽ������<br/>
     * format�ɴ����Զ�������ڸ�ʽ,Ҳ��ʹ��Ԥ�ø�ʽ,����:<br/>
     *  <ul>
     *      <li>1    yyyy-MM-dd HH:mm:ss</li>
     *      <li>2    yyyy-MM-dd</li>
     *      <li>3   yyyy/MM/dd HH:mm:ss </li>
     *      <li>4   yyyy/MM/dd   </li>
     *      <li>5   yyyy-MM-dd HH:mm </li>
     *  </ul>
     *  @param date ������ʽ��������
     * @param format ��ʽ
     * @return ��ǰ���ھ���ʽ������ַ�����ʽ
     * */
    public static String getCustomFomratCurrentDate(Date date,String format) {
        if(isNotNull(format)){
            SimpleDateFormat presetFormat = PRESET_FORMAT.get(format);
            if(presetFormat != null){
                return presetFormat.format(date);
            }
            SimpleDateFormat custom = new SimpleDateFormat(format);
            return custom.format(date);
        }
        return FORMAT_ONE.format(date);
    }
    /**
     * ���ݴ���ĸ�ʽ����ʽ�Դ���������ַ������и�ʽ�󷵻�<br/>
     * ���formatΪnull����򷵻�yyyy-MM-dd HH:mm:ss��ʽ������<br/>
     * format�ɴ����Զ�������ڸ�ʽ,Ҳ��ʹ��Ԥ�ø�ʽ,����:<br/>
     *  <ul>
     *      <li>1    yyyy-MM-dd HH:mm:ss</li>
     *      <li>2    yyyy-MM-dd</li>
     *      <li>3   yyyy/MM/dd HH:mm:ss </li>
     *      <li>4   yyyy/MM/dd   </li>
     *      <li>5   yyyy-MM-dd HH:mm </li>
     *  </ul>
     *  @param date ������ʽ��������
     * @param format ��ʽ
     * @return ��ǰ���ھ���ʽ������ַ�����ʽ
     * @throws Exception 
     * */
    public static String getCustomFomratCurrentDate(String date,String format) throws Exception {
        if(isNotNull(format)){
            SimpleDateFormat presetFormat = PRESET_FORMAT.get(format);
            if(presetFormat != null){
                return presetFormat.format(parse(date));
            }
            SimpleDateFormat custom = new SimpleDateFormat(format);
            return custom.format(parse(date));
        }
        return FORMAT_ONE.format(date);
    }

    /**
     * �������ַ������������ڶ���֧��һ�¸�ʽ
     * <li>yyyy-MM-dd HH:mm:ss
     * <li>yyyy-MM-dd
     * <li>yyyy/MM/dd HH:mm:ss
     * <li>yyyy/MM/dd
     * </p>
     *
     * @param dateString �ַ�����ʽ������
     * @return  �ַ�����ʽ���ھ���Ӧ�ĸ�ʽ��ʽ�����Date��ʽ<br/>
     * �����������ڸ��������ڸ�ʽ��ƥ��,�׳��쳣
     * @throws Exception 
     */
    public static Date parse(String dateString) throws Exception {
        Date date = null;
        try {
            Pattern p1 = Pattern.compile(FORMAT_ONE_REG);
            Matcher m1 = p1.matcher(dateString);
            if (m1.matches()) {
                date = FORMAT_ONE.parse(dateString);
            } else {
                Pattern p2 = Pattern.compile(FORMAT_TWO_REG);
                Matcher m2 = p2.matcher(dateString);
                if (m2.matches()) {
                    date = FORMAT_TWO.parse(dateString);
                } else {
                    Pattern p3 = Pattern.compile(FORMAT_THREE_REG);
                    Matcher m3 = p3.matcher(dateString);
                    if (m3.matches()) {
                        date = FORMAT_THREE.parse(dateString);
                    } else {
                        Pattern p4 = Pattern.compile(FORMAT_FOUR_REG);
                        Matcher m4 = p4.matcher(dateString);
                        if (m4.matches()) {
                            date = FORMAT_FOUR.parse(dateString);
                        } else {
                            Pattern p5 = Pattern.compile(FORMAT_FIVE_REG);
                            Matcher m5 = p5.matcher(dateString);
                            if (m5.matches()) {
                                date = FORMAT_FIVE.parse(dateString);
                            }
                        }
                    }
                }
            }
        } catch (ParseException e) {
            throw new Exception("�Ƿ������ַ���������ʧ�ܣ�" + dateString, e);
        }
        return date;
    }

    /**
     * �õ��������ڵĸ�ʽ<br/>
     * <b>�õ�����������:<br/></b>
     * <ul>
     *     <li>0->yyyy-MM-dd</li>
     *     <li>1->yyyy��MM��dd��</li>
     *     <li>2->yyyy��MM��</li>
     *     <li>3->MM��dd��</li>
     *     <li>7->yyyy-MM-dd</li>
     *     <li>8->EEE(�ܴ� ��  ��һ)</li>
     * </ul>
     * @return �������ڸ�ʽ��map����
     * */
    public static Map<String, String> getDateFormatMap() {
        Map<String, String> dateFormatMap = new HashMap<String, String>();
        dateFormatMap.put("0", "yyyy-MM-dd");
        dateFormatMap.put("1", "yyyy��MM��dd��");
        dateFormatMap.put("2", "yyyy��MM��");
        dateFormatMap.put("3", "MM��dd��");
        dateFormatMap.put("7", "yyyy-MM-dd");
        dateFormatMap.put("8", "EEE");
        return dateFormatMap;
    }

    /**
     * �õ�����ʱ��ĸ�ʽ <br/>
     * <b>�õ�����������:<br/></b>
     * <ul>
     *     <li>0->HH:mm:ss</li>
     *     <li>1->HH:mm:ss.SSS</li>
     *     <li>2->HH:mm</li>
     *     <li>3->h:m a</li>
     *     <li>4->h:m:s a</li>
     *     <li>5->HHʱmm��ss��</li>
     *     <li>6->HHʱmm��</li>
     *     <li>7->a hʱm��</li>
     *     <li>8->a hʱm��s��</li>
     * </ul>
     * @return ����ʱ���ʽ��map����
     * */
    public static Map<String, String> getTimeFormatMap() {
        Map<String, String> timeFormatMap = new HashMap<String, String>();
        timeFormatMap.put("0", "HH:mm:ss");
        timeFormatMap.put("1", "HH:mm:ss.SSS");
        timeFormatMap.put("2", "HH:mm");
        timeFormatMap.put("3", "h:m a");
        timeFormatMap.put("4", "h:m:s a");
        timeFormatMap.put("5", "HHʱmm��ss��");
        timeFormatMap.put("6", "HHʱmm��");
        timeFormatMap.put("7", "a hʱm��");
        timeFormatMap.put("8", "a hʱm��s��");
        return timeFormatMap;
    }

    /**
    * <p/>�����ڸ�ʽ�����ַ�����yyyy-MM-dd</p>
    *
    * @param date ������ʽ��Date����
    * @return ����ʽ����������ַ�����ʽ
    */
    public static String format(Date date) {
        return FORMAT_TWO.format(date);
    }

    /**
    * �����ڸ�ʽ�����ַ���,���ʱ���� 00:00:00��ȥ��ʱ��
    *
    * @param date ������ʽ��Date����
    * @return ��ʽ������ַ���
    */
    public static String formatBySituation(Date date) {
        String ds = FORMAT_ONE.format(date);
        return ds.endsWith("00:00:00") ? ds.substring(0, 10) : ds;
    }
    /**
     * ��long���͵����ڸ�ʽ����yyyy-MM-dd HH:mm:ss��ʽ�������ַ�����ʽ
     * @param date ������ʽ����long��������
     * @return ��ʽ������ַ�����ʽ����
     * */
    public static String format(long date) {
        return FORMAT_ONE.format(date);
    }

    /**
     * <p/>
     * �����ڸ�ʽ������Ӧ��ʽ���ַ������磺
     * <li>yyyy-MM-dd HH:mm:ss
     * <li>yyyy-MM-dd
     * <li>yyyy/MM/dd HH:mm:ss
     * <li>yyyy/MM/dd
     * </p>
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null) return "";
        final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * ��ȡ�ַ��������е���
     * @param date Դ����
     * @return year Դ�����е������Ϣ
     * @throws Exception 
     */
    public static int getYear(String date) throws Exception {
        try {
            return getYear(FORMAT_TWO.parse(date));
        } catch (ParseException e) {
            throw new Exception("����ת������", e);
        }
    }

    /**
     * ȡ�����ڶ����е���
     *
     * @param date Դ���ڶ���
     * @return year Դ���ڶ����е������Ϣ
     */
    public static int getYear(Date date) {
        if (date == null)
            return 0;
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * ȡ�����ڶ����е���
     *
     * @param date Դ���ڶ���
     * @return month Դ���ڶ����е��·���Ϣ
     */
    public static int getMonth(Date date) {
        if (date == null)
            return 0;
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * ȡ���ַ��������е���
     *
     * @param date Դ����
     * @return month Դ�����е��·���Ϣ
     * @throws Exception 
     */
    public static int getMonth(String date) throws Exception {
        try {
            return getMonth(FORMAT_TWO.parse(date));
        } catch (ParseException e) {
            throw new Exception("����ת������", e);
        }
    }

    /**
     * ȡ�����ڶ����е���
     *
     * @param date Դ���ڶ���
     * @return month Դ���ڶ����е�����Ϣ
     */
    public static int getDate(Date date) {
        if (date == null)
            return 0;
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * ȡ���ַ��������е���
     *
     * @param date Դ����
     * @return month Դ�����е�����Ϣ
     * @throws Exception 
     */
    public static int getDate(String date) throws Exception {
        try {
            return getDate(FORMAT_TWO.parse(date));
        } catch (ParseException e) {
            throw new Exception("����ת������", e);
        }
    }

    /**
     * ȡ�����ڸ�ʽ�е�Сʱ��24Сʱ��
     *
     * @param date Դ���ڶ���
     * @return Դ���ڶ����е�Сʱ��Ϣ
     */
    public static int getHour(Date date) {
        if (date == null)
            return 0;
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * ȡ�����ڸ�ʽ�еķ��ӡ�
     *
     * @param date Դ���ڶ���
     * @return Դ���ڶ����еķ�����Ϣ
     */
    public static int getMinute(Date date) {
        if (date == null)
            return 0;
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * ȡ�����ڸ�ʽ�еķ��ӡ�
     *
     * @param date Դ���ڶ���
     * @return Դ���ڶ����е�����Ϣ
     */
    public static int getSecond(Date date) {
        if (date == null)
            return 0;
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * ȡ���ַ��������е�Сʱ��
     *
     * @param date Դ����
     * @return Դ�����е�Сʱ��Ϣ
     * @throws Exception 
     */
    public static int getHour(String date) throws Exception {
        try {
            return getHour(FORMAT_ONE.parse(date));
        } catch (ParseException e) {
            throw new Exception("����ת������", e);
        }
    }

    /**
     * ȡ��Calendarʵ��
     * @return Calendarʵ��
     */
    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    /**
     * ��������ƫ������ȡ�����ڡ�<br/>
     * offset > 0 ,�����ӳ�offset�죬 <br/>
     * offset < 0 ��ǰ�ƽ� offset��  <br/>
     *
     * @param date:������
     * @param offset:��������ƫ����
     * @return ƫ�ƺ������
     */
    public static Date getDate(Date date, int offset) {
        if (date == null)
            return date;
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, offset);
        return calendar.getTime();
    }

    /**
     * �õ��������ڵ�������ʽ ��0һ��-0��-0��
     * @param dateString ���ڵ��ַ�����ʽ
     * @return ת�����ĺ������
     * */
    public static String dealYearForChinese(String dateString) {
        StringBuffer dataChinese = new StringBuffer();
        String[] dateArray = dateString.split("");
        for (String s1 : dateArray) {
            if ("0".equals(s1)) {
                dataChinese.append("��");
            } else if ("1".equals(s1)) {
                dataChinese.append("һ");
            } else if ("2".equals(s1)) {
                dataChinese.append("��");
            } else if ("3".equals(s1)) {
                dataChinese.append("��");
            } else if ("4".equals(s1)) {
                dataChinese.append("��");
            } else if ("5".equals(s1)) {
                dataChinese.append("��");
            } else if ("6".equals(s1)) {
                dataChinese.append("��");
            } else if ("7".equals(s1)) {
                dataChinese.append("��");
            } else if ("8".equals(s1)) {
                dataChinese.append("��");
            } else if ("9".equals(s1)) {
                dataChinese.append("��");
            } else {
                dataChinese.append(s1);
            }
        }
        return dataChinese.toString();
    }

    /**
     * ���� dealYearForChinese(String dateString) ת����������� һ�� ��������ת�� һʮ��
     * @param monthChinese �·�����
     * @return ��ת������·ݷ���
     * */
    public static String dealMonthDayForChinese(String monthChinese) {
        String[] ss = monthChinese.split("");
        StringBuffer sb = new StringBuffer();
        sb.append(ss[1]).append("ʮ").append(ss[2]);
        return sb.toString().replace("0", "");
    }
    /**
     * �����ڵ�������ʽת�ɺ���   12ת��һʮ��
     * @param m ������ʽ����
     * @return ��������
     * */
    public static String dealMonthForChinese(int m) {
        if (m < 10)
            return dealYearForChinese(String.valueOf(m));
        else
            return dealMonthDayForChinese(dealYearForChinese(String.valueOf(m)));
    }

    public static String dealDayForChinese(int day) {
        if (day < 10)
            return dealYearForChinese(String.valueOf(day));
        else
            return dealMonthDayForChinese(dealYearForChinese(String.valueOf(day)));
    }

    /**
     * �����������ڰ��մ�����������ڸ�ʽ������ڽ���ת��,ת���ַ�����ʽ
     * @param date ����ת��������
     * @param formatCode �������ڸ�ʽ��,��������<br/>
     * <ul>
     *     <li>0->yyyy-MM-dd</li>
     *     <li>1->yyyy��MM��dd��</li>
     *     <li>2->yyyy��MM��</li>
     *     <li>3->MM��dd��</li>
     *     <li>7->yyyy-MM-dd</li>
     *     <li>8->EEE(�ܴ� ��  ��һ)</li>
     *     <li>4->��0һ������¶�ʮ����</li>
     *     <li>5->��0һ�������</li>
     *     <li>6->���¶�ʮ����</li>
     * </ul>
     *  @return ��ʽ���������
     *
     * */
    public static String getDateString(Date date, String formatCode) {
        Map<String, String> dateFormatMap = getDateFormatMap();
        if (!"4".equals(formatCode) && !"5".equals(formatCode) && !"6".equals(formatCode)) {
            SimpleDateFormat format = new SimpleDateFormat(dateFormatMap.get(formatCode));
            return format.format(date);
        } else {
            StringBuffer dateString = new StringBuffer();
            String y = dealYearForChinese(String.valueOf(getYear(date)));
            String m = dealMonthForChinese(getMonth(date));
            String day = dealMonthForChinese(getDate(date));
            if ("4".equals(formatCode)) {
                dateString.append(y).append("��").append(m).append("��").append(day).append("��");
            } else if ("5".equals(formatCode)) {
                dateString.append(y).append("��").append(m).append("��");
            } else if ("6".equals(formatCode)) {
                dateString.append(m).append("��").append(day).append("��");
            }
            return dateString.toString();
        }
    }

    /**
     * �õ�����ʱ�����Ӧ������ʱ���ʽ,�����������ڶ����մ˸�ʽ����ת��
     * @param date ����ת����ʱ��
     * @param timeCode ����ʱ���ʽ��,��������:<br/>
     * <ul>
     *     <li>0->HH:mm:ss</li>
     *     <li>1->HH:mm:ss.SSS</li>
     *     <li>2->HH:mm</li>
     *     <li>3->h:m a</li>
     *     <li>4->h:m:s a</li>
     *     <li>5->HHʱmm��ss��</li>
     *     <li>6->HHʱmm��</li>
     *     <li>7->a hʱm��</li>
     *     <li>8->a hʱm��s��</li>
     *     <li>9->һʮ��ʱ��ʮ����(24Сʱ��)</li>
     *     <li>10->����һʱ��ʮ����(12Сʱ��)</li>
     * </ul>
     * @return ��ʽ�����ʱ��
     * */
    public static String getTimeString(Date date, String timeCode) {
        Map<String, String> dateFormatMap = getTimeFormatMap();
        if (!"3".equals(timeCode) && !"4".equals(timeCode) && !"9".equals(timeCode) && !"10".equals(timeCode)) {
            SimpleDateFormat sdf_ = new SimpleDateFormat(dateFormatMap.get(timeCode));
            return sdf_.format(date);
        } else if ("3".equals(timeCode) || "4".equals(timeCode)) {
            SimpleDateFormat sdf_ = new SimpleDateFormat(dateFormatMap.get(timeCode), Locale.ENGLISH);
            return sdf_.format(date);
        } else {
            StringBuffer timeString = new StringBuffer();
            int hour1 = getHour(date);
            int hour2 = hour1 % 12;

            int minute = getMinute(date);
            if ("9".equals(timeCode)) {
                timeString.append(dealMonthForChinese(hour1)).append("ʱ").append(dealMonthForChinese(minute)).append("��");
            } else if ("10".equals(timeCode)) {
                if (hour1 < 12) {
                    timeString.append("����");
                } else {
                    timeString.append("����");
                }
                if (hour2 == 0) {
                    timeString.append("0");
                } else {
                    timeString.append(dealMonthForChinese(hour2));
                }
                timeString.append("ʱ").append(dealMonthForChinese(minute)).append("��");
            }
            return timeString.toString();
        }
    }
    /**
     * �����������ڰ��ո����ĸ�ʽ��ʽ�����ַ�������
     * @param date Ҫ����ʽ��������
     * @param formatJson ��ʽ���ĸ�ʽ<br/>
     * ��ʽ:{date:3,time:4},formatJson��ֵ����鿴getDateFormatMap��getDateString����������<br/>
     * <b><br/>������formatJson�����ڸ�ʽ��(date)��ֵ������ĸ�ʽ:<br/></b>
     * <ul>
     * <li>0 yyyy-MM-dd    </li>
     * <li>1 yyyy��MM��dd�� </li>
     * <li>2 yyyy��MM��   </li>
     * <li>3 MM��dd��   </li>
     * <li>7 yyyy-MM-dd  </li>
     * <li>8 EEE    ���ص�ǰ�ܼ�   e.g.����һ  </li>
     * <li>4 ���� ������ e.g.��0һ�������һʮ����  </li>
     * <li>5 ���� ����   e.g.��0һ�������  </li>
     * <li>6 ���� ����   e.g.����һʮ���� </li>
     * </ul>
     *
     * <b>formatJson��ʱ���ʽ��(time),��������:<br/></b>
     * <ul>
     *     <li>0->HH:mm:ss</li>
     *     <li>1->HH:mm:ss.SSS</li>
     *     <li>2->HH:mm</li>
     *     <li>3->h:m a</li>
     *     <li>4->h:m:s a</li>
     *     <li>5->HHʱmm��ss��</li>
     *     <li>6->HHʱmm��</li>
     *     <li>7->a hʱm��</li>
     *     <li>8->a hʱm��s��</li>
     *     <li>9->һʮ��ʱ��ʮ����(24Сʱ��)</li>
     *     <li>10->����һʱ��ʮ����(12Сʱ��)</li>
     * </ul>
     * @return ��ʽ������ַ�����ʽ������<br/>
     * e.g.formatJson Ϊ {date:0,time:3} ���� 2012-08-15 1:38 PM
     * */
    public static String getFormatDate(Date date, String formatJson) {
        StringBuffer dateString = new StringBuffer();
        JSONObject format = JSONObject.fromObject(formatJson);
        String date_ = format.getString("date");
        dateString.append(getDateString(date, date_));
        if (format.has("time")) {
            dateString.append(" ").append(getTimeString(date, format.getString("time")));
        }
        return dateString.toString();
    }

    /**��ȡ��ǰʱ��� ,��ͬ��DateUtils.getCustomFomratCurrentDate("yyyyMMddHHmmssSSS")
    * @return ��ǰʱ���ʱ���
    * */
    public static String getTimeSuffix() {
        return getTimeSuffix(new Date());
    }

    /**
   * ��ȡָ��ʱ���ʱ��� ��ͬ��
     * @param date ָ������ȡʱ�����ʱ�����
     * @return ָ��ʱ���ʱ���
   * */
    public static String getTimeSuffix(Date date) {
        StringBuffer sb = new StringBuffer();
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        sb.append(calendar.get(Calendar.YEAR));

        if (calendar.get(Calendar.MONTH) < 9) {
            sb.append(0);
            sb.append(calendar.get(Calendar.MONTH) + 1);
        } else
            sb.append(calendar.get(Calendar.MONTH) + 1);

        if (calendar.get(Calendar.DATE) < 10) {
            sb.append(0);
            sb.append(calendar.get(Calendar.DATE));
        } else
            sb.append(calendar.get(Calendar.DATE));

        if (calendar.get(Calendar.HOUR_OF_DAY) < 10) {
            sb.append(0);
            sb.append(calendar.get(Calendar.HOUR_OF_DAY));
        } else
            sb.append(calendar.get(Calendar.HOUR_OF_DAY));

        if (calendar.get(Calendar.MINUTE) < 10) {
            sb.append(0);
            sb.append(calendar.get(Calendar.MINUTE));
        } else {
            sb.append(calendar.get(Calendar.MINUTE));
        }
        if (calendar.get(Calendar.SECOND) < 10) {
            sb.append(0);
            sb.append(calendar.get(Calendar.SECOND));
        } else {
            sb.append(calendar.get(Calendar.SECOND));
        }
        sb.append(calendar.get(Calendar.MILLISECOND));
        return sb.toString();
    }

    /**
     * Parse a string and return the date value in the specified format<br/>
     * ����ָ���ĸ�ʽstrFormat,���������ַ�����ʽ������ת����date����<br/>
     * ���strFormatΪnull,��ʹ�� <b>yyyy-MM-dd HH:mm:ss</b> ��ʽ<br/>
     * ת��ʹ��SimpleDateFormat�����
     *
     *
     * @param strFormat ���ڸ�ʽ
     * @param dateValue Ҫת���������ַ���
     * @return ת���Ժ������
     */
    public static Date parseDate(String strFormat, String dateValue) {
        if (dateValue == null)
            return null;
        if (strFormat == null)
            strFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
        Date newDate = null;
        try {
            newDate = dateFormat.parse(dateValue);
        } catch (ParseException pe) {
            newDate = null;
        }
        return newDate;
    }

    public static Date parseDate(String dateValue) {
        return parseDate(null, dateValue);
    }

    /**
     * ȡ��ָ������N��������
     *
     * @param date ָ������
     * @param days ����
     * @return ���������������
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }
	public static boolean isNotNull(String str){
		return (str == null) || (str.trim().length()==0) ? false : true;
	}
	public static String defaultIfEmpty(String str, String defaultStr) {
		if (isNotNull(str)) {
			return str;
		}
		return defaultStr;
	}
}
