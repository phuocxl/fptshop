package fa.training.factory.doc;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;


@Service
public class MailMergeToPay {
    public static final Locale LOCALE = new Locale("id", "ID");
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMMM-yyyy", LOCALE);
    public static final NumberFormat NUMBER_FORMAT = NumberFormat.getCurrencyInstance(LOCALE);
    public String MailMergeData() throws Exception {

        String pdfFileName="D:/Receipt.pdf";
        String[] fieldNames = new String[]{
                "mahoso",
                "hotennguoitrinh",
                "bophan",
                "chucvu",
                "hinhthucthanhtoan",
                "tendonvithuhuong",
                "sotaikhoan",
                "tenchinhanh",
                "sotiengiaodich",
                "ngaytao",

        };
        String[] fieldValues = new String[]{
                "TTTT00001",
                "Nguyễn Văn A",
                "Phòng IT",
                "Trường Phòng",
                "Chuyển khoản",
                "Công Ty TNHH ABC",
                "1900267668222",
                "Ngân hàng BIDV Thử Đức",
                NUMBER_FORMAT.format(1000000000),
                DATE_FORMAT.format(new Date())
        };

        Document document = new Document();
        document.loadFromStream(MailMergeToPay.class.
                getResourceAsStream("/templates/docs/to-pay.docx"),
                FileFormat.Auto);
        document.getMailMerge().execute(fieldNames, fieldValues);
        document.saveToFile(pdfFileName, FileFormat.PDF);
        return pdfFileName;
    }
}
