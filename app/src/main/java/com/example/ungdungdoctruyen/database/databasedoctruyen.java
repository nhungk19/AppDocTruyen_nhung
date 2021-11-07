package com.example.ungdungdoctruyen.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ungdungdoctruyen.model.TaiKhoan;

public class databasedoctruyen extends SQLiteOpenHelper {


    private static String DATABASE_NAME = "doctruyen";
    private static String TABLE_TAIKHOAN = "taikhoan";
    private static String ID_TAI_KHOAN = "idtaikhoan";
    private static String TEN_TAI_KHOAN = "tentaikhoan";
    private static String MAT_KHAU = "matkhau";
    private static String PHAN_QUYEN = "phanquyen";
    private static String EMAIL = "email";
    private static int VERSION = 1;

    private static String TABLE_TRUYEN = "truyen";
    private static String ID_TRUYEN = "idtruyen";
    private static String TEN_TRUYEN = "tieude";
    private static String NOI_DUNG = "noidung";
    private static String IMAGE = "anh";


    private Context context;

    private String SQLQuery = "CREATE TABLE "+ TABLE_TAIKHOAN +" ( "+ID_TAI_KHOAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TEN_TAI_KHOAN+" TEXT UNIQUE, "
            +MAT_KHAU+" TEXT, "
            +EMAIL+" TEXT, "
            + PHAN_QUYEN+" INTEGER) ";

    private String SQLQuery1 = "CREATE TABLE "+ TABLE_TRUYEN +" ( "+ID_TRUYEN+" integer primary key AUTOINCREMENT, "
            +TEN_TRUYEN+" TEXT UNIQUE, "
            +NOI_DUNG+" TEXT, "
            +IMAGE+" TEXT, "+ID_TAI_KHOAN+" INTEGER , FOREIGN KEY ( "+ ID_TAI_KHOAN +" ) REFERENCES "+
            TABLE_TAIKHOAN+"("+ID_TAI_KHOAN+"))";

    private String SQLQuery2 = "INSERT INTO TaiKhoan VAlUES (null,'admin','admin','admin@gmail.com',2)";
    private String SQLQuery3 = "INSERT INTO TaiKhoan VAlUES (null,'nhung','nhung','nhung@gmail.com',1)";

    private String SQLQuery4 = "INSERT INTO truyen VALUES (null,'Chú Chồn lười học','Chồn mướp sống ở khu rừng thông, vì là con một nên cậu được cha mẹ cưng chiều vô cùng. Tói tuổi đi học rồi nhưng Chồn mướp không chịu đến trường, chỉ rong chơi mà thôi. Vì được nuông chiều quá, Chồn mướp đâm ra bướng bỉnh, không chịu nghe lời ai. Ai khuyên gì cậu cũng không nghe mà còn cãi bướng. \n" +
            "\n" +
            "Một hôm, Chồn mải chơi, bị lạc vào sâu trong rừng mà không biết đường ra. Câuj ta lang thang mãi mới tìm được bảng chỉ đường. Nhưng khổ nỗi, không biết chữ nên Chồn không đọc được\n"+
            "\n" +
            "Cậu ngồi xuống vừa khóc vừa hối hận, nếu chịu khó đi học biết chữ thì bây giờ đâu phải như thế này. Đúng lúc đó thì bác Sư Tử xuất hiện, Chồn tưởng mình sắp bị ăn thịt nên quỳ lạy xin tha \n" +
            "\n" +
            "Bác Sư Tử bảo: Ta chỉ muốn giúp cháu thôi, vì cháu không biết chữ chứ gì?. Chồn gật đầu. Được bác Sư Tử khuyên răn và chỉ đường, Chồn đã tìm về được ngôi nhà của mình.Chú mừng lắm và nhát quyết từ nay phải đi học.','https://s.meta.com.vn/img/thumb.ashx/Data/image/2020/11/30/ke-chuyen-be-nghe-2.jpg',1)";
    private String SQLQuery5 = "INSERT INTO truyen VALUES (null,'Chú Thỏ thông minh ','Trong một khu rừng nọ có một chú Thỏ con sống cùng mẹ. Ngày nào Thỏ cũng chạy ra bờ sông uống nước. Trước khi đi, chú đượ mẹ nhắc:\n" +
            "\n" +
            "– Con phải cẩn thận nhé vì Cáo cũng hay ra sông dạo chơi lắm đấy!\n" +
            "\n" +
            "Một ngày nọ, vừa mới cúi xuống mặt sông chuẩn bị uống nước, Thỏ con bất ngờ thấy Cáo. Cáo ta tỏ vẻ thân thiện:\n" +
            "\n" +
            "– Chào Thỏ con, lên lưng anh cõng vào rừng hái nấm và hoa nào!\n" +
            "\n" +
            "Thỏ con hơi lo lắng, nhưng chú nhanh trí nghĩ ra mẹo. Chú trả lời Cáo:\n" +
            "\n" +
            "- Ôi thế thì thích quá anh Cáo ơi, chờ em về nhà lấy mũ đội che nắng đã nhé!\n" +
            "\n" +
            "Nói rồi Thỏ con nhanh nhẹn chạy ù về nhà. Thỏ con kể lại câu chuyện cho mẹ nghe. Thỏ mẹ ôm Thỏ con vào lòng, khen con thông minh và nhanh trí.','https://s.meta.com.vn/img/thumb.ashx/Data/image/2020/11/30/ke-chuyen-be-nghe-3.jpg',1)";
    private String SQLQuery6 = "INSERT INTO truyen VALUES (null,'Lợn con đi thăm bạn','Một hôm, Gấu con mời các bạn đến nhà chơi, Lợn con cũng nhận được giấy mời. Lợn con hí hửng đến nhà Gấu con. \n" +
            "\n" +
            "Cốc cốc cốc... . Lợn con gõ cửa. Gấu con ra mở cửa, ngạc nhiên hỏi:\n" +
            "\n" +
            "- Bạn Là ai vậy?\n" +
            "\n" +
            "Lợn con nói:\n" +
            "\n" +
            "– Tớ là Lợn con đây mà, bạn  tớ đến nhà chơi, không nhớ sao?\n" +
            "\n" +
            "Gấu con :\n" +
            "\n" +
            "– Tớ mời Lợn con cơ, đó là một chú lợn màu trắng rất xinh đẹp, tại sao người bạn lại đen sì thế?\n" +
            "\n" +
            "Nói xong, Gấu con lại hít một hơi thật dài rồi nói:\n" +
            "\n" +
            "– Trên người bạn có mùi hôi thật khó chịu, giống với mùi hôi trên người Cáo, có phải bạn là Cáo đóng giả Lợn con không?\n" +
            "\n" +
            "Thỏ con và Chó con cũng chạy tới, chúng cũng ngửi người Lợn con và nói:\n" +
            "\n" +
            "- Người bạn ấy hôi quá, nhất định là tên Cáo gian xảo đóng giả làm Lợn con đấy, chúng ta mau đuổi nó đi đi!\n" +
            "\n" +
            "Các bạn liền cầm gậy đuổi Lợn con đi. Lợn con sợ quá, vừa chạy vừa la hét lên:\n" +
            "\n" +
            "– Tớ không phải là Cáo, tớ là Lợn con đậy mà!\n" +
            "\n" +
            "Thế nhưng các bạn vẫn không tin và tiếp tục đuổi đánh Lợn con." +
            "\n" +
            "Lợn con chạy tới một cái ao nhỏ, nó chẳng may trượt chân, ngã tùm xuống ao. Nó liền nhân dịp đó vội vàng tắm rửa, kỳ cọ thật sạch sẽ.\n" +
            "\n" +
            "Sau khi tắm rửa sạch  sẽ, Lợn con bèn trèo lên bờ. Gấu con ngạc nhiên hỏi:\n" +
            "\n" +
            "– Lợn con ơi, lạ quá, vừa nãy rõ ràng chúng tớ nhìn thấy một con Cáo rơi xuống ao, tại sao bây giờ lại là bạn nhỉ?\n" +
            "\n" +
            "Lợn con ngượng ngùng nói:\n" +
            "\n" +
            "– Vừa nãy không phải là tên Cáo bị rơi xuống ao đâu, mà chính là tớ đây. Vì tớ lười tắm rửa nên người vừa bẩn vừa hôi, khiến cho các bạn hiểu \n" +
            "\n" +
            "Sau khi đã hiểu ra đầu đuôi câu chuyện, các bạn của Lợn con cười phá lên. Chúng kéo tay Lợn con về nhà của Gấu con và cùng nhau ăn uống vui vẻ.','https://s.meta.com.vn/img/thumb.ashx/Data/image/2020/11/30/ke-chuyen-be-nghe-4.jpg',1)";
    private String SQLQuery7 = "INSERT INTO truyen VALUES (null,'Chú bé chăn cừu','Một chú bé chăn cừu cho chủ thả cừu gần một khu rừng rậm cách làng không xa lắm. Chăn cừu được ít lâu, chú cảm thấy công việc chăn cừu thực là nhàm chán. Tất cả mọi việc chú có thể làm để giải khuây là nói chuyện với con chó hoặc thổi chiếc sáo chăn cừu của mình.\n" +
            "\n" +
            "Một hôm, trong lúc đang ngắm nhìn đàn cừu và cánh rừng yên tĩnh chú bé chợt nhớ tới lời chủ của chú từng dặn rằng khi sói tấn công cừu thì phải kêu cứu để dân làng nghe thấy và đánh đuổi nó đi.\n" +
            "\n" +
            "Thế là chú nghĩ ra một trò chơi cho đỡ buồn. Mặc dù chẳng thấy con sói nào, chú cứ chạy về làng và la to:\n" +
            "\n" +
            "– Sói ! Sói !\n" +
            "\n" +
            "Đúng như chú nghĩ, dân làng nghe thấy tiếng kêu bỏ cả việc làm và tức tốc chạy ra cánh đồng. Nhưng khi đến nơi, họ chẳng thấy sói đâu, chỉ thấy chú bé ôm bụng cười ngặt nghẽo vì đã lừa được họ.\n" +
            "\n" +
            "Ít ngày sau chú bé chăn cừu lần nữa lại la lên:\n" +
            "\n" +
            "– “Sói ! Sói !”.\n" +
            "\n" +
            "Và một lần nữa dân làng lại chạy ra giúp chú. Nhưng họ lại chẳng thấy con sói nào, chỉ thấy chú bé chăn cừu nghịch ngợm ôm bụng cười khoái chí.\n" +
            "\n" +
            "Thế rồi vào một buổi chiều nọ, khi mặt trời lặn xuống sau cánh rừng và bóng tối bắt đầu phủ đầy lên cánh đồng, một con sói thực sự xuất hiện. Nó nấp sau bụi cây rình bắt những con cừu béo non. Bỗng sói phóng vút ra chộp lấy một chú cừu tội nghiệp. Thấy sói cả đàn cừu sợ hãi chạy toán loạn, chú bé chăn cừu cũng hoảng loạn vô cùng.\n" +
            "\n" +
            "Quá hoảng sợ, chú bé chăn cừu vội chạy về làng và la to:\n" +
            "\n" +
            "– “Sói ! Sói !”.\n" +
            "\n" +
            "Nhưng mặc dù dân làng có nghe tiếng chú kêu, không một ai chạy ra để giúp chú như những lần trước cả. Vì mọi người nghĩ chú lại bày trò nói dối như trước.\n" +
            "\n" +
            "Thế là sói thỏa sức bắt mồi, giết chết rất nhiều cừu của chú bé. Sau khi đã chén no nê, nó biến mất vào rừng rậm. Chú bé buồn bã ngồi giữa đồng cỏ, lòng đầy hối hận về hành động nói dối của mình và hậu quả của trò đùa dại dột gây ra.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Nói dối là một tật xấu. Người hay nói dối ngay cả khi nói thật cũng không ai tin.','https://toplist.vn/images/800px/chu-be-chan-cuu-230183.jpg',1)";
    private String SQLQuery8 = "INSERT INTO truyen VALUES (null,'Cậu bé chăn cừu và cây đa cổ thụ','Ngày xửa ngày xưa, xưa lắm rồi khi mà muôn thú, cây cỏ, con người còn trò chuyện được với nhau. Trên đồng cỏ rậm ven khu làng có một loài cây gọi là cây đa. Đó là một thứ cây to, khỏe, lá của nó rậm rạp đến nỗi không một tia nắng nào có thể lọt qua được. Vào những ngày trời nắng nóng người ta thường nghỉ chân một lát và trò chuyện hàn huyên cùng cây dưới bóng cây mát rượi. Mọi người ai cũng biết rằng cây đa rất thông thái vì cây đã có tuổi, đã từng trải.\n" +
            "\n" +
            "\n" +
            "Một hôm, có một cậu bé chăn cừu ngồi nghỉ mát dưới gốc cây sau một ngày dài phơi mình dưới nắng cậu bé thấy người mệt mỏi và nóng bức. Một làn gió mơn man thổi thoa nhẹ lên tấm thân mỏi mệt của chú bé. Cậu bé bắt đầu thấy buồn ngủ. Vừa đặt mình xuống cậu bé bỗng ngước mắt nhìn lên những cành cây. Bấy giờ cậu bé bỗng thấy mình thật kiêu hãnh, cậu vẫn thường hay khoe với mọi người rằng cậu có tài chăn cừu và đàn cừu của cậu nhờ vậy mà lớn rất nhanh. Khi cậu bé phát hiện ra cây đa chỉ có những chùm quả rất nhỏ, nó bắt đầu thấy ngạc nhiên. Cậu bắt đầu chế giễu: hư, một cái cây to khỏe thế này mà làm sao chỉ có những bông hoa những chùm quả bé tí tẹo thế kia, mọi người vẫn bảo là cái cây này thông thái lắm kia mà nhưng làm sao nó có thể thông thái khi mà quả của nó chỉ toàn bé xíu như vậy. Dĩ nhiên là cây đa nghe hết những lời của cậu bé nhưng cây vẫn im lặng và cành lá chỉ khẽ rung rinh đủ để cho gió cất lên khúc hát ru êm dịu. Cậu bé bắt đầu ngủ, cậu ngáy o o…. Cốc.\n" +
            "Quả đa nhỏ rụng chính giữa trán của cậu bé, nó bừng tỉnh nhưng càu nhàu: “Gừm… người ta vừa mới chợp mắt được có một tí”, rồi nó nhặt quả đa lên chưa hết chưa biết định làm gì với quả đa này bỗng nhiên cậu bé nghe thấy có tiếng cười khúc khích, cậu nghe thấy cây hỏi:\n" +
            "– Có đau không ?.\n" +
            "– Không nhưng mà làm người ta mất cả giấc ngủ .\n" +
            "– Đó là bài học cho cậu bé to đầu đấy. Cậu chẳng vừa nhạo tôi là chỉ sinh ra toàn những quả nhỏ xíu là gì.\n" +
            "– Tôi nhạo đấy tại sao người đời lại bảo bác là thông thái được nhỉ? Phá giấc ngủ trưa của người khác! Thế cũng là thông minh chắc!.\n" +
            "Cây cười và nói: này này anh bạn anh hãy nghe đây những chiếc lá của tôi cho cậu bóng mát để cậu lấy chỗ nghỉ ngơi. Ừ thì cứ cho là quả của tôi nó bé đi chăng nữa nhưng chẳng lẽ cậu không thấy rằng tạo hóa hoạt động rất hoàn chỉnh đó sao. Cậu thử tưởng tượng xem, nếu quả của tôi to như quả dừa thì điều gì sẽ xảy ra khi nó rơi vào đầu cậu.\n" +
            "Cậu bé im thin thít: ừ nhở. Cậu chưa hề nghĩ đến điều này bao giờ cả.\n" +
            "Cây lại nhẹ nhàng tiếp lời:\n" +
            "– Những người khiêm tốn có thể học hỏi rất nhiều điều từ việc quan sát những vật xung quanh đấy cậu bé ạ.\n" +
            "– Vâng bác đa bác cứ nói tiếp đi.\n" +
            "– Cậu hãy bắt đầu làm bạn với những gì ở quanh cậu. Chúng ta tất cả đều cần tới nhau. Cậu cứ nhìn bầy ong kia mà xem. Nhờ có ong mà hoa của tôi mới có thể trở thành quả. Thế còn bầy chim kia thì sao. Chúng làm tổ ngay giữa tán lá của tôi đây này. Những con chim bố mẹ kia phải làm việc vất vả cả ngày để bắt sâu nuôi con và cậu có biết việc làm đó có ý nghĩa gì với tôi không?.\n" +
            "– Không, có ý nghĩa gì vậy hả bác?.\n" +
            "– Sâu ăn lá chính vậy loài chim kia chính là những người bạn của tôi. Chúng còn giúp cả cậu nữa đấy, sở dĩ cừu của cậu có đủ lá và cỏ để ăn là vì chim chóc đã tiêu diệt hết các loài côn trùng và sâu bọ. Và chưa hết đâu cậu bé ạ!.\n" +
            "– Còn gì thế nữa hả bác đa.\n" +
            "– Cậu hãy nhìn xuống chân mình mà xem, những chiếc lá rụng tạo thành lớp thảm mục, những con sâu đào đất ngoi lên để ăn lá, chúng đào đất thành những lỗ nhỏ, nhờ đó không khí có thể vào được trong đất. Có không khí trong đất nên bộ rễ của tôi mới khỏe thế nào đấy. Rễ khỏe nên tôi cũng khỏe hơn. Nào thế bây giờ cậu trẻ đã hiểu chưa?.\n" +
            "– Cháu hiểu rồi thưa bác. Bác tha lỗi cho cháu nhé vì đã cười nhạo bác bác đa ạ.\n" +
            "– Không sao bây giờ cháu hãy ra dắt cừu về đi.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Có thể cậu bé chăn cừu không phải ngay sau đó sẽ trở nên khiêm tốn, học hỏi luôn được nhưng rõ ràng là cậu đã nhận ra người ta không thể sống lẻ loi được.','https://toplist.vn/images/800px/cau-be-chan-cuu-va-cay-da-co-thu-230184.jpg',1)";

    public databasedoctruyen(@Nullable Context context) {
        super(context,DATABASE_NAME,null ,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
        db.execSQL(SQLQuery2);
        db.execSQL(SQLQuery3);
        db.execSQL(SQLQuery4);
        db.execSQL(SQLQuery5);
        db.execSQL(SQLQuery6);
        db.execSQL(SQLQuery7);
        db.execSQL(SQLQuery8);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_TAIKHOAN, null);
                return res;
    }


    public void AddTaiKhoan(TaiKhoan taiKhoan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_TAI_KHOAN,taiKhoan.getmTentaiKhoan());
        values.put(MAT_KHAU,taiKhoan.getmMatKhau());
        values.put(EMAIL,taiKhoan.getmEmail());
        values.put(PHAN_QUYEN,taiKhoan.getmPhanQuyen());

        db.insert(TABLE_TAIKHOAN, null,values);
        db.close();
        Log.e("ADD TK", "TC");
    }

    //lấy  truyện mới nhất
    public Cursor getData1(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from "+TABLE_TRUYEN+" ORDER BY "+ID_TRUYEN+" DESC LIMIT 5" , null );
        return res;
    }


}

