package com.lanswon.estate.service.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.lanswon.estate.constant.PDFConstant;

import static com.lanswon.estate.constant.PDFConstant.*;

import java.io.IOException;



/**
 * @Description
 * @Author zsw
 * @Date 2020/1/6 20:41
 * @Version V1.0
 **/

public class HeaderFooter extends PdfPageEventHelper {

    private PdfTemplate pdfTemplate = null;

    private String version ="版本号: 2019  V1.1";

    BaseFont stxiheiFont;

    BaseFont songFont;

    private String htNo;

    public HeaderFooter(String htNo) {
        this.htNo ="合同编号："+htNo;

    }

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {

        try {
            stxiheiFont = BaseFont.createFont(PDFConstant.FONT_PATH+"stxihei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            songFont = BaseFont.createFont(PDFConstant.FONT_PATH+"simsun.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfTemplate = writer.getDirectContent().createTemplate(67, 12); //创建模板
        //2.4cm  0.43cm

    }

    @Override
    public void onStartPage(PdfWriter writer, Document document) {
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        

        PdfContentByte under = writer.getDirectContentUnder();
        under.setRGBColorFill(0, 0, 0);//纯黑

        //页眉
        Font head = new Font(stxiheiFont, PDFConstant.FONT_SIZE_NO5);
        head.setColor(new BaseColor(0, 0, 0));
        Phrase headPhrase = new Phrase(version, head);


        Phrase headPhrase1 = new Phrase(htNo, head);



        //页眉顶端 1.5cm
        ColumnText.showTextAligned(under, Element.ALIGN_RIGHT, headPhrase, document.right(), document.top() + 20, 0);

        ColumnText.showTextAligned(under, Element.ALIGN_RIGHT, headPhrase1, document.right(), document.top() + 10, 0);





        //页脚
        String pagination = "第 " + writer.getCurrentPageNumber() + " 页 ";
        float len = songFont.getWidthPoint(pagination, FONT_SIZE_SMALL5);
        Font font = new Font(songFont, FONT_SIZE_SMALL5);
        font.setColor(new BaseColor(0, 0, 0));
        Phrase footPhrase = new Phrase(pagination, font);
        //计算位置

        ColumnText.showTextAligned(under, Element.ALIGN_LEFT, footPhrase, (document.rightMargin() + document.right() + document.leftMargin() - document.left() - len) / 2.0F, document.bottom()-22, 0);
        under.addTemplate(pdfTemplate, (document.rightMargin() + document.right() + document.leftMargin() - document.left()) / 2.0F + 20F, document.bottom()-22); // 调节模版显示的位置


    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {

        //          页码
        pdfTemplate.beginText();
        pdfTemplate.setFontAndSize(songFont, FONT_SIZE_SMALL5);
        PDFConstant.PAGE_NUM = writer.getPageNumber();
        pdfTemplate.showText("共 " + writer.getPageNumber() + " 页");

        pdfTemplate.endText();
        pdfTemplate.closePath();

    }

}