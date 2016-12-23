package model;

import java.util.ArrayList;

/**
 * Created by Nhozip on 6/15/2016.
 */
public class Page {


    public ArrayList<ItemTT> getVnExpress() {
        ArrayList<ItemTT> list = new ArrayList<>();
        list.add(new ItemTT("Trang Chủ", "http://vnexpress.net/rss/tin-moi-nhat.rss"));
        list.add(new ItemTT("Thời sự", "http://vnexpress.net/rss/thoi-su.rss"));
        list.add(new ItemTT("Thế giới", "http://vnexpress.net/rss/the-gioi.rss"));
        list.add(new ItemTT("Kinh doanh", "http://vnexpress.net/rss/kinh-doanh.rss"));
        list.add(new ItemTT("Giải Trí", "http://vnexpress.net/rss/giai-tri.rss"));
        list.add(new ItemTT("Pháp Luật", "http://vnexpress.net/rss/phap-luat.rss"));
        list.add(new ItemTT("Sức khỏe", "http://vnexpress.net/rss/suc-khoe.rss"));
        list.add(new ItemTT("Gia Đình", "http://vnexpress.net/rss/gia-dinh.rss"));
        list.add(new ItemTT("Du Lịch", "http://vnexpress.net/rss/du-lich.rss"));
        list.add(new ItemTT("Khoa Học", "http://vnexpress.net/rss/khoa-hoc.rss"));
        list.add(new ItemTT("Số Hóa", "http://vnexpress.net/rss/so-hoa.rss"));
        list.add(new ItemTT("Ô tô-Xe Máy", "http://vnexpress.net/rss/oto-xe-may.rss"));
        list.add(new ItemTT("Cộng Đồng", "http://vnexpress.net/rss/cong-dong.rss"));
        list.add(new ItemTT("Tâm Sự", "http://vnexpress.net/rss/tam-su.rss"));
        list.add(new ItemTT("Giải Trí", "http://vnexpress.net/rss/cuoi.rss"));
        return list;
    }

    public ArrayList<ItemTT> getVietNamNet() {
        ArrayList<ItemTT> list = new ArrayList<>();
        list.add(new ItemTT("Trang Chủ", "http://vietnamnet.vn/rss/home.rss"));
        list.add(new ItemTT("Pháp Luật", "http://vietnamnet.vn/rss/phap-luat.rss"));
        list.add(new ItemTT("Công Nghệ", "http://vietnamnet.vn/rss/cong-nghe.rss"));
        list.add(new ItemTT("Kinh Doanh", "http://vietnamnet.vn/rss/kinh-doanh.rss"));
        list.add(new ItemTT("Giáo Dục", "http://vietnamnet.vn/rss/giao-duc.rss"));
        list.add(new ItemTT("Thời Sự", "http://vietnamnet.vn/rss/thoi-su.rss"));
        list.add(new ItemTT("Giải Trí", "http://vietnamnet.vn/rss/giai-tri.rss"));
        list.add(new ItemTT("Sức Khỏe", "http://vietnamnet.vn/rss/suc-khoe.rss"));
        list.add(new ItemTT("Thể Thao", "http://vietnamnet.vn/rss/the-thao.rss"));
        list.add(new ItemTT("Đời Sống", "http://vietnamnet.vn/rss/doi-song.rss"));
        list.add(new ItemTT("Thế Giới", "http://vietnamnet.vn/rss/the-gioi.rss"));
        list.add(new ItemTT("Bất Động Sản", "http://vietnamnet.vn/rss/bat-dong-san.rss"));
        list.add(new ItemTT("Bạn Đọc", "http://vietnamnet.vn/rss/ban-doc.rss"));
        list.add(new ItemTT("Tin Mới Nóng", "http://vietnamnet.vn/rss/moi-nong.rss"));
        list.add(new ItemTT("Tin Nổi Bật", "http://vietnamnet.vn/rss/tin-noi-bat.rss"));
        list.add(new ItemTT("Tuần Việt Nam", "http://vietnamnet.vn/rss/tuanvietnam.rss"));
        list.add(new ItemTT("Góc Nhìn Thẳng", "http://vietnamnet.vn/rss/goc-nhin-thang.rss"));

        return list;
    }

    public ArrayList<ItemTT> getTienPhong() {
        ArrayList<ItemTT> list = new ArrayList<>();
        list.add(new ItemTT("Xã Hội", "http://www.tienphong.vn/rss/xa-hoi-2.rss"));
        list.add(new ItemTT("Kinh Tế", "http://www.tienphong.vn/rss/kinh-te-3.rss"));
        list.add(new ItemTT("Thế Giới", "http://www.tienphong.vn/rss/the-gioi-5.rss"));
        list.add(new ItemTT("Giới Trẻ", "http://www.tienphong.vn/rss/gioi-tre-4.rss"));
        list.add(new ItemTT("Pháp Luật", "http://www.tienphong.vn/rss/phap-luat-12.rss"));
        list.add(new ItemTT("Thể Thao", "http://www.tienphong.vn/rss/the-thao-11.rss"));
        list.add(new ItemTT("Văn Nghệ", "http://www.tienphong.vn/rss/van-nghe-7.rss"));
        list.add(new ItemTT("Giải Trí", "http://www.tienphong.vn/rss/giai-tri-36.rss"));
        list.add(new ItemTT("Giáo Dục", "http://www.tienphong.vn/rss/giao-duc-71.rss"));
        list.add(new ItemTT("Công Nghệ", "http://www.tienphong.vn/rss/cong-nghe-45.rss"));
        return list;
    }

    public ArrayList<ItemTT> getNgoiSao() {
        ArrayList<ItemTT> list = new ArrayList<>();
        list.add(new ItemTT("Hậu Trường", "http://ngoisao.net/rss/hau-truong.rss"));
        list.add(new ItemTT("Bên Lề", " http://ngoisao.net/rss/ben-le.rss"));
        list.add(new ItemTT(" Thời cuộc", "http://ngoisao.net/rss/thoi-cuoc.rss"));
        list.add(new ItemTT("Phong cách", "http://ngoisao.net/rss/phong-cach.rss"));
        list.add(new ItemTT(" Thư giãn", "http://ngoisao.net/rss/thu-gian.rss"));
        list.add(new ItemTT(" Cưới", "http://ngoisao.net/rss/cuoi-hoi.rss"));
        list.add(new ItemTT(" Showbiz Việt", "http://ngoisao.net/rss/showbiz-viet.rss"));
        list.add(new ItemTT(" Châu Á", "http://ngoisao.net/rss/chau-a.rss"));
        list.add(new ItemTT("Hollywood", "http://ngoisao.net/rss/hollywood.rss"));
        list.add(new ItemTT(" Clip", "http://ngoisao.net/rss/clip.rss"));
        list.add(new ItemTT("Khỏe Đẹp", "http://ngoisao.net/rss/khoe-dep.rss"));
        list.add(new ItemTT("24h", "http://ngoisao.net/rss/24h.rss"));
        list.add(new ItemTT("Chuyện Lạ", "http://ngoisao.net/rss/chuyen-la.rss"));
        list.add(new ItemTT(" Thương Truờng", "http://ngoisao.net/rss/thuong-truong.rss"));
        list.add(new ItemTT(" Hình Sự", "http://ngoisao.net/rss/hinh-su.rss"));
        list.add(new ItemTT(" Thời Trang", "http://ngoisao.net/rss/thoi-trang.rss"));
        list.add(new ItemTT("Làm Đẹp", "http://ngoisao.net/rss/lam-dep.rss"));
        list.add(new ItemTT("Trắc Nghiệm", "http://ngoisao.net/rss/trac-nghiem.rss"));
        list.add(new ItemTT("Ăn Chơi", "http://ngoisao.net/rss/an-choi.rss"));
        list.add(new ItemTT("Dân Chơi", "http://ngoisao.net/rss/dan-choi.rss"));
        list.add(new ItemTT("Cười", "http://ngoisao.net/rss/cuoi.rss"));
        list.add(new ItemTT("Game", "http://ngoisao.net/rss/game.rss"));

        return list;
    }
}
