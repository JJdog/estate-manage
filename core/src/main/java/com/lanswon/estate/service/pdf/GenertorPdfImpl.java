package com.lanswon.estate.service.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.lanswon.commons.core.poi.ChineseNumToArabicNumUtil;
import com.lanswon.commons.core.time.DateFormatEnum;
import com.lanswon.commons.core.time.DateTimeUtil;
import com.lanswon.estate.bean.vo.doc.DealTemp;
import com.lanswon.estate.constant.PDFConstant;

import java.io.*;

/**
 * @Description
 * @Author zsw
 * @Date 2020/1/6 20:01
 * @Version V1.0
 **/

public class GenertorPdfImpl  {


    public static void createPdf(OutputStream os, DealTemp dealTemp) throws FileNotFoundException, DocumentException {


        //String version = "2019  V1.1";

        Document document = creatDocument();
        PdfWriter pdfWriter = PdfWriter.getInstance(document,os);
        pdfWriter.setPageEvent(new HeaderFooter(dealTemp.getDealSerial()));

        document.open();
        setTitle(document);
        setText(document, dealTemp);
        document.close();

        pdfWriter.close();
    }

    private static Document creatDocument() {
        Document document = new Document(PageSize.A4, PDFConstant.PAGE_MARGIN_LEFT, PDFConstant.PAGE_MARGIN_RIGHT, PDFConstant.PAGE_MARGIN_TOP, PDFConstant.PAGE_MARGIN_BOTTOM); // 指定页面大小为A4,且自定义页边距(marginLeft、marginRight、marginTop、marginBottom)
        return document;
    }


    private static void setTitle(Document document) {

        BaseFont baseFont = null;

        try {
            baseFont = BaseFont.createFont(PDFConstant.FONT_PATH + "simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Font font = new Font(baseFont, PDFConstant.FONT_SIZE_NO2, Font.BOLD);
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        String title = "房屋租赁合同";
        paragraph.add(new Chunk(title, font));

        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }


    /**
     * 该方法  调用   段落里 无动态值  直接调用
     *
     * @param document
     * @param paragraph
     * @param font
     * @param value
     * @param flag
     * @return
     */

    private static Paragraph getParagraph2(Document document, Paragraph paragraph, Font font, String value, boolean flag) {
        paragraph.clear();
        paragraph = getParagraph(paragraph, font, value, flag);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return paragraph;
    }


    private static Paragraph getParagraph(Paragraph paragraph, Font font, String value, boolean flag) {

        if (paragraph == null) {
            paragraph = new Paragraph();

            paragraph.setLeading(PDFConstant.LINE_SPACE_18);
        }
        if (font != null) {
            paragraph.setFont(font);
        }


        if (flag) { //是否首行缩进2个字符
            paragraph.setFirstLineIndent(PDFConstant.FIRST_LINE_INDENT_2);
        } else {
            paragraph.setFirstLineIndent(PDFConstant.FIRST_LINE_INDENT_0);
        }

        if (value != null) {
            paragraph.add(value);
        }


        return paragraph;
    }

    private static Phrase getPhrase(Phrase phrase, Font font, String value) {

        if (phrase == null) {
            phrase = new Phrase();
            phrase.setFont(font);

        }

        phrase.add(spaceString(value));
        return phrase;
    }

    private static String spaceString(String value) {
        return " " + value + " ";
    }


    /**
     * _年_月_日
     */
    private static Paragraph getLineTime(Paragraph paragraph, String value, String content, Font lineFont) {
        Phrase phrase = getPhrase(null, lineFont, spaceString(value));//值
        paragraph.add(phrase);
        paragraph.add(content);//年
        return paragraph;
    }

    private static void setText(Document document, DealTemp dealTemp) {

        String a = dealTemp.getLessor();
        String b = dealTemp.getRenter();

        String address = dealTemp.getLocation();

        String area = String.valueOf(dealTemp.getArea());

        String work = dealTemp.getResourceUsage();

        String limit = String.valueOf(dealTemp.getRentYear());

        String startDate = dealTemp.getRentStart();
        String endDate = dealTemp.getRentEnd();

        String startDateYear = startDate.substring(0, startDate.indexOf("年"));
        String startDateMonth = startDate.substring(startDate.indexOf("年") + 1, startDate.indexOf("月"));

        String startDateDay = startDate.substring(startDate.indexOf("月") + 1, startDate.indexOf("日"));

        String endDateYear = endDate.substring(0, endDate.indexOf("年"));
        String endDateMonth = endDate.substring(endDate.indexOf("年") + 1, endDate.indexOf("月"));

        String endDateDay = endDate.substring(endDate.indexOf("月") + 1, endDate.indexOf("日"));





        String monthRMB = dealTemp.getMonthMoney();

        String first = dealTemp.getPayType();

        //保证金
        String bail = dealTemp.getDeposit();


        String[] other = dealTemp.getExtraInfo().split("\n");


        BaseFont baseFont = null;
        try {
            baseFont = BaseFont.createFont(PDFConstant.FONT_PATH + "simsun.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        //正文字体
        Font textFont = new Font(baseFont, PDFConstant.FONT_SIZE_SMALL4, Font.NORMAL);

        //加粗正文
        Font blodFont = new Font(baseFont, PDFConstant.FONT_SIZE_SMALL4, Font.BOLD);

        //下划线  值 的字体
        Font lineFont = new Font(textFont);
        lineFont.setStyle(Font.UNDERLINE);


        Paragraph paragraph = getParagraph(null, textFont, "出租方:(下称甲方)", false);
        Phrase phrase = getPhrase(null, lineFont, a);
        paragraph.add(phrase);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        paragraph.clear();
        phrase.clear();
        paragraph = getParagraph(paragraph, textFont, "承租方:(下称乙方)", false);
        phrase = getPhrase(phrase, lineFont, b);
        paragraph.add(phrase);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        String content = "根据《中华人民共和国合同法》及其他有关法律、法规规定,在平等、自愿、协商一致的基础上，甲、乙双方就下列房屋的租赁达成如下协议：";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);


        content = "一、租赁房屋的坐落、面积、租赁期限";
        paragraph = getParagraph2(document, paragraph, blodFont, content, true);


        paragraph.clear();
        phrase.clear();

        content = "甲方将座落于";
        paragraph = getParagraph(paragraph, textFont, content, true);

        phrase = getPhrase(phrase, lineFont, address);
        paragraph.add(phrase);

        paragraph.add("房屋(建筑面积");

        Phrase phrase1 = getPhrase(null, lineFont, area);
        paragraph.add(phrase1);

        String content1 = "平方米),租给乙方作";
        paragraph.add(content1);

        Phrase phrase2 = getPhrase(null, lineFont, work);
        paragraph.add(phrase2);


        String content2 = "使用,该房屋性质为非住宅。租赁期限为";
        paragraph.add(content2);
        Phrase phrase3 = getPhrase(null, lineFont, limit);
        paragraph.add(phrase3);


        String content3 = "年,从";
        paragraph.add(content3);


        paragraph = getLineTime(paragraph,startDateYear,"年",lineFont);
//
//        Phrase phrase4 = getPhrase(null, lineFont, spaceString(startDateYear));
//        paragraph.add(phrase4);
//        String content31 = "年";
//        paragraph.add(content31);

        paragraph = getLineTime(paragraph,startDateMonth,"月",lineFont);

//        Phrase phrase41 = getPhrase(null, lineFont, spaceString(startDateMonth));
//        paragraph.add(phrase41);
//        String content32 = "月";
//        paragraph.add(content32);

        paragraph = getLineTime(paragraph,startDateDay,"日",lineFont);


//        Phrase phrase42 = getPhrase(null, lineFont, spaceString(startDateDay));
//        paragraph.add(phrase42);
//        String content33 = "日";
//        paragraph.add(content33);


        String content4 = "起至";
        paragraph.add(content4);


        paragraph = getLineTime(paragraph,endDateYear,"年",lineFont);

        paragraph = getLineTime(paragraph,endDateMonth,"月",lineFont);
        paragraph = getLineTime(paragraph,endDateDay,"日",lineFont);

//        Phrase phrase5 = getPhrase(null, lineFont, endDateYear);
//        paragraph.add(phrase5);
//        String content41 = "年";
//        paragraph.add(content41);
//
//
//        Phrase phrase51 = getPhrase(null, lineFont, endDateMonth);
//        paragraph.add(phrase51);
//        String content42 = "月";
//        paragraph.add(content42);
//
//        Phrase phrase52 = getPhrase(null, lineFont, endDateDay);
//        paragraph.add(phrase52);
//        String content43 = "日";
//        paragraph.add(content43);

        String content5 = "止。";

        paragraph.add(content5);

        //免租内容

        if (!"0".equals(dealTemp.getFreeRent())) {
            String qwe = "其中";
            paragraph.add(qwe);
            String startTime = DateTimeUtil.format(DateTimeUtil.convertDate2LocalDateTime(dealTemp.getStartTime()), DateFormatEnum.YYYY_MM_DD_CN);
            String endTime = DateTimeUtil.format(DateTimeUtil.convertDate2LocalDate(dealTemp.getStartTime()).plusMonths(Long.valueOf(dealTemp.getFreeRent())).plusDays(-1), DateFormatEnum.YYYY_MM_DD_CN);


            String startTimeYear = startTime.substring(0, startTime.indexOf("年"));
            String startTimeMonth = startTime.substring(startTime.indexOf("年") + 1, startTime.indexOf("月"));

            String startTimeDay = startTime.substring(startTime.indexOf("月") + 1, startTime.indexOf("日"));

            String endTimeYear = endTime.substring(0, endTime.indexOf("年"));
            String endTimeMonth = endTime.substring(endTime.indexOf("年") + 1, endTime.indexOf("月"));

            String endTimeDay = endTime.substring(endTime.indexOf("月") + 1, endTime.indexOf("日"));



            paragraph = getLineTime(paragraph,startTimeYear,"年",lineFont);
            paragraph = getLineTime(paragraph,startTimeMonth,"月",lineFont);

            paragraph = getLineTime(paragraph,startTimeDay,"日",lineFont);

            paragraph = getLineTime(paragraph,endTimeYear,"年",lineFont);

            paragraph = getLineTime(paragraph,endTimeMonth,"月",lineFont);

            paragraph = getLineTime(paragraph,endTimeDay,"日",lineFont);


            paragraph.add("为房屋装修期,免收房租。");

//            Phrase phrase6 = getPhrase(null, lineFont, spaceString(startTimeYear));
//            paragraph.add(phrase6);
//            String content51 = "年";
//            paragraph.add(content51);
//            Phrase phrase6 = getPhrase(null, lineFont, spaceString(startTimeYear));
//            paragraph.add(phrase6);
//            String content52 = "月";
//            String content53 = "日";
//            String content54 = "年";
//            String content55 = "年";
//            String content56 = "年";



        }


        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        content = "二、房屋的租金";
        paragraph = getParagraph2(document, paragraph, blodFont, content, true);


        paragraph.clear();
        content = "（一）租赁房屋每月租金为";
        paragraph = getParagraph(paragraph, textFont, content, true);
        phrase.clear();
        phrase = getPhrase(phrase, lineFont, monthRMB);
        paragraph.add(phrase);
        content1 = "元，乙方先支付租金再使用房屋，乙方应当在下一个周期开始前5天支付租金，第一期租金应当在本合同签署之日同时支付，甲方收到房屋租金和履约保证金后再向乙方交付租赁房屋。";
        paragraph.add(content1);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        paragraph.clear();
        content = "（二）乙方按照以下第";
        paragraph = getParagraph(paragraph, textFont, content, true);
        phrase.clear();
        phrase = getPhrase(phrase, lineFont, first);
        paragraph.add(phrase);
        content1 = "项方式支付租金：";
        paragraph.add(content1);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        content = "（1） 以十二个月为一周期支付； （3）以三个月为一周期支付；";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "（2） 以六个月为一周期支付；   （4）以一个月为周期支付。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);


        content = "三、履约保证金";
        paragraph = getParagraph2(document, paragraph, blodFont, content, true);


        paragraph.clear();
        content = "（一） 为保证乙方全面履行本合同的义务，乙方同意于本合同签订之日向甲方交纳履约保证金";
        paragraph = getParagraph(paragraph, textFont, content, true);
        phrase.clear();
        phrase = getPhrase(phrase, textFont, bail);
        paragraph.add(phrase);
        content1 = "元。甲方无须向乙方支付履约保证金的利息。租赁期内，乙方不得以保证金抵付租金等任何应付费用。该保证金将随本合同规定之租金的增加而相应地追加，乙方应于租金增加后3日内追加保证金；否则，视为乙方对甲方违约。";
        paragraph.add(content1);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        content = "（二）如乙方违反本合同的任何约定或条款，拖欠支付本合同规定的任何款项包括但不限于租金等费用，甲方有权以保证金抵付任何欠款或甲方因乙方的违约而根据本合同规定及法律规定可以要求其承担的任何款项或甲方的任何损失。甲方根据本合同抵扣保证金后，乙方必须于3日内把甲方扣除部分保证金额补足。否则，视为乙方对甲方违约。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "（三）在不影响上述规定的前提下，在租赁期结束后（或按本合同规定提前终止时）及乙方按合同约定返还该房屋予甲方，该保证金在扣除本合同上条规定之款项后，将由甲方无息退还乙方。但根据本合同约定甲方有权不予退还的除外。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "（四）如乙方按期续租，则履约保证金不退给乙方，自动转为下一期合同履约保证金。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "四、房屋交付和装修改造";
        paragraph = getParagraph2(document, paragraph, blodFont, content, true);


        paragraph.clear();
        content = "（一）甲方应在";
        paragraph = getParagraph(paragraph, textFont, content, true);

        phrase = getPhrase(null, lineFont, spaceString(startDateYear));
        paragraph.add(phrase);
        content2 = "年";
        paragraph.add(content2);

        phrase1 = getPhrase(null, lineFont, spaceString(startDateMonth));
        paragraph.add(phrase1);
        content3 = "月";
        paragraph.add(content3);

        phrase2 = getPhrase(null, lineFont, spaceString(startDateDay));
        paragraph.add(phrase2);
        content4 = "日";
        paragraph.add(content4);


        content1 = "前按照合同约定，将出租的房屋全部交给乙方使用。";
        paragraph.add(content1);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        content = "（二）乙方未能按期接受房屋的，甲方将通知乙方接受房屋且甲方通知的交房时间即视为乙方接受房屋的时间；同时，乙方接到甲方交房通知后5日内未来交接房屋的，甲方有权解除本合同，甲方已经收取的履约保证金不予返还。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "（三）租赁期间，如果甲方将财产所有权转移给第三方时，租赁合同对受让方继续有效。如甲方在房屋上设置他项权益的，将不影响乙方对房屋的使用。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "（四）乙方对租赁房屋进行装修改造前，应当将装修改造方案书面汇报给甲方，经甲方书面同意后，方可对租赁房屋进行装修改造。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "五、物业费、水电费等费用的支付";
        paragraph = getParagraph2(document, paragraph, blodFont, content, true);

        content = "（一）出租房屋的水费、电费、空调使用等费用由乙方支付。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "（二）乙方同意自房屋交付之日起，将房屋纳入甲方委托的物业管理机构进行统一管理，并遵守有关管理规定，由乙方与物业公司另签协议，乙方自行承担相应的物管费用等所有费用。乙方拒绝签订物业管理协议或合同的，视为乙方对甲方的违约行为。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "六、合同的解除和补偿";
        paragraph = getParagraph2(document, paragraph, blodFont, content, true);

        content = "乙方有下列情形之一的，甲方有权在情形发生后的任何时间，解除本合同，提前收回出租房屋。在此情形下，甲方无需对乙方承担包括但不限于装修费、设备添加费等任何补偿，且甲方已收取的履约保证金不予退还，同时甲方还有权要求乙方再支付相当于6个月租金的违约金；如违约金不足以弥补甲方损失的，乙方还应承担赔偿责任。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "(一)乙方发生无正当理由拖欠租金、水费、电费、物业费、综合服务管理费等违反、不履行本合同及附件规定之任何条款、条件规定的行为，并在甲方发出书面通知后30天内未予纠正的及具有本合同其他条款约定的违约行为的；";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "(二)乙方未经甲方书面同意擅自改变出租房屋租赁用途的；";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "(三)乙方未经甲方书面同意擅自转租、分租、抵押、出借、转让出租房屋的；";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);


        content = "(四)因乙方的原因造成出租房屋结构损坏，影响房屋安全、擅自拆改、损坏房屋且不予修复或不予赔偿的；";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);


        content = "(五)乙方中途擅自退租的；";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "(六)乙方被宣告破产的；";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "(七)乙方利用出租房屋进行违法犯罪活动的；";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "(八）乙方违反第八条第二款的规定,拒不改正的。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "七、租赁房屋的维护";
        paragraph = getParagraph2(document, paragraph, blodFont, content, true);

        content = "租赁期间，租用房屋和配套设施损坏损毁，由乙方负责修缮恢复原状，并需要赔偿甲方的经济损失。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "八、双方的权利和义务";
        paragraph = getParagraph2(document, paragraph, blodFont, content, true);

        content = "（一）甲方有权督促乙方按约使用房屋，保障使用的安全；乙方应当爱护房屋并按时交纳租金，乙方逾期交付房屋租金的，每逾期一日乙方应当向甲方支付应付房屋租金千分之一的滞纳金。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "（二）乙方对甲方正常的房屋检查给予协助，乙方不得擅自改变房屋结构和用途，不得贮存任何违禁品、易燃品、爆炸品等物，不得擅自转租、转让、转借房屋，不得以承租房屋设定抵押等他项权利，合同终止时主动将房屋和配套设施完好地交还给甲方。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "九、违约责任：";
        paragraph = getParagraph2(document, paragraph, blodFont, content, true);

        content = "(一)乙方的责任";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "1、由于使用不当或者人为因素造成租赁财产损坏、灭失的，负责修复、赔偿。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "2、乙方擅自拆改房屋、设备、机具等财产，负责赔偿由此而造成的损失。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "3、无正当理由影响房屋修缮工期，乙方应当赔偿甲方因此而造成的损失。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "4、合同终止后，乙方逾期不返还租赁房屋的，按照租金的2倍标准支付房屋占用费。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "(二)甲方的责任";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "1、未按合同规定的面积、标准提供出租房屋及配套设施，负责赔偿由此给乙方造成的损失。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "2、房屋倒塌，因甲方的责任发生的，赔偿因此而致使乙方遭受的财产损失。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "本合同其他条款对违约责任另有约定的，将与本第九条的约定同时适用。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "十、合同解除或终止后的处理";
        paragraph = getParagraph2(document, paragraph, blodFont, content, true);

        content = "无论因任何原因导致本合同解除或终止的，乙方必须在5日内搬出属于乙方所有的全部物件，逾期未搬离的，则视为乙方放弃对余物的所有权且甲方或甲方聘请第三方予以清理且因此发生的损失及费用均由乙方承担，乙方对此保证不提出任何异议，并不主张任何权利。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "十一、优先承租权";
        paragraph = getParagraph2(document, paragraph, blodFont, content, true);

        content = "合同期满，如甲方的房屋继续出租，在同等条件下，乙方享有优先权,但乙方必须在三个月之前提出书面续租申请，否则，视为乙方不继续承租。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "十二、通知条款";
        paragraph = getParagraph2(document, paragraph, blodFont, content, true);

        content = "（一）涉及本合同权利义务变化的或其他必要通知，应以书面形式传递，收到方应签收。如无法向另一方直接送达或另一方不予签收，可邮寄送达，邮件寄至本合同记载之地址时，即视为送达。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "（二）本合同下述的地址、电话为双方通知送达的地址、电话，如果任何一方变更，应在变更后3日内书面通知对方，否则任何一方通知送达前述地址，即视为被送达方收到，由此引发的法律后果由被送达人承担。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);


        content = "甲方：";
        paragraph = getParagraph(null, null, null, false);

        Chunk chunk = new Chunk(content, blodFont);
        paragraph.add(chunk);


        phrase.clear();
        phrase = getPhrase(phrase, lineFont, a);
        paragraph.add(phrase);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        content = "联系人：                       联系电话：";
        paragraph = getParagraph2(document, paragraph, textFont, content, false);


        content = "联系地址：";
        paragraph = getParagraph2(document, paragraph, textFont, content, false);

        paragraph.clear();
        content = "乙方：";
        paragraph = getParagraph(null, null, null, false);
        paragraph.add(new Chunk(content, blodFont));

        phrase.clear();
        phrase = getPhrase(phrase, lineFont, b);
        paragraph.add(phrase);

        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        content = "联系人：                       联系电话：";
        paragraph = getParagraph2(document, paragraph, textFont, content, false);


        content = "联系地址：";
        paragraph = getParagraph2(document, paragraph, textFont, content, false);


        content = "十三、争议解决条款：";
        paragraph = getParagraph2(document, paragraph, blodFont, content, true);


        content = "本合同如发生纠纷，甲、乙双方应通过友好协调解决，不能解决时向租赁房屋所在地人民法院起诉。因此所发生的案件受理费、保全费、保全保险费及差旅费和律师费均由败诉方承担。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);


        content = "十四、合同的签署和生效";
        paragraph = getParagraph2(document, paragraph, blodFont, content, true);


        content = "本合同共四页，为一式四份，甲方执三份、乙方执一份，经双方签字或盖章之日起生效。";
        paragraph = getParagraph2(document, paragraph, textFont, content, true);

        content = "十五、其它条款:";
        paragraph = getParagraph2(document, paragraph, blodFont, content, true);

        if ("".equals(dealTemp.getExtraInfo())) {
            content = "无";
            paragraph = getParagraph2(document, paragraph, textFont, content, true);

        } else {

            for (int i = 0; i < other.length; i++) {
                content = other[i];
                paragraph = getParagraph2(document, paragraph, textFont, content, true);

            }

        }


        for (int i = 0; i < 10; i++) {
            Paragraph paragraph1 = new Paragraph(PDFConstant.FONT_SIZE_SMALL4, new Chunk("\n", textFont));
            try {
                document.add(paragraph1);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }

        content = "出租方（盖章）:                         承租方（盖章）:";
        paragraph = getParagraph2(document, paragraph, textFont, content, false);

        content = "法定代表人或授权代表（签字）:           法定代表人或授权代表（签字）:";
        paragraph = getParagraph2(document, paragraph, textFont, content, false);


        for (int i = 0; i < 5; i++) {
            Paragraph paragraph1 = new Paragraph(PDFConstant.FONT_SIZE_SMALL4, new Chunk("\n", textFont));
            try {
                document.add(paragraph1);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        content = "本合同签订时间    \t年   月   日";

        paragraph.clear();
        paragraph = getParagraph(paragraph, textFont, content, false);

        paragraph.setAlignment(Element.ALIGN_RIGHT);

        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
}