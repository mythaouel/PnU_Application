package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.adapter.BlogAdapter;
import com.example.model.Blog;
import com.example.model.BlogItemClick;
import com.example.model.HomeBlog;
import com.example.pnu_application.R;

import java.util.ArrayList;
import java.util.List;

public class BlogFragment extends Fragment {

    List<Blog> blogs;
    ListView lvBlog;
    BlogAdapter adapter;
    BlogItemClick blogItemClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog,container,false);
        lvBlog = view.findViewById(R.id.lvBlog);
        initData();
        addEvents();
        return view;
    }

    private void addEvents() {
        lvBlog.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                blogItemClick = (BlogItemClick) getActivity();
                if (blogItemClick != null)
                    blogItemClick.click( blogs.get( i ) );
            }
        } );
    }

    private void initData() {
        blogs = new ArrayList<>();
        blogs.add(new Blog("Chăm chó mang thai","Thông thường, kể từ khi thai hình thành và làm tổ ở sừng tử cung tới khi chó con ra đời là khoảng 58 – 68 ngày. Trung bình là 9 tuần. Vì vậy, thời gian mang thai của chó chỉ khoảng trong 2 – 3 tháng. Tuy nhiên, tùy từng giống chó mà thời gian mang thai của chúng sẽ khác nhau. Thường thì, chó càng ít thai như dưới 4 con với các giống chó Poodle , Tod, GSD, Rottweiler, Labrador, Golden… Hoặc dưới 2 con như giống Miniature Bull Terrier, Nhật, Bắc Kinh, Chihuahua … thì thời gian mang thai càng dài.\n" +
                "\n" +
                "Thời gian chó rất dễ bị sảy thai là ngày thứ 28 – 45. Chính vì vậy bạn nên bạn phải kỹ lưỡng trong cách chăm sóc chó mang thai. Khi chó có thai, bạn không nên cho chúng nhảy cao, chạy nhanh, đánh nhau hoặc ủ rũ buồn rầu. Bạn nên thường xuyên dắt chúng đi dạo và chơi với chúng các trò chơi nhẹ nhàng.\n" +
                "Chó thường mang thai trong 62 ngày, cộng hoặc trừ 2 ngày. \n" +
                "\n" +
                "Mang thai được chia thành tam cá nguyệt, và một con chó khỏe mạnh, được nuôi dưỡng tốt sẽ tăng khoảng 15-20% so với trọng lượng của cô khi sinh sản. Cho ăn quá nhiều có thể dẫn đến béo phì vào cuối thai kỳ, làm tăng nguy cơ chuyển dạ khó khăn hoặc kéo dài và gây thêm căng thẳng cho chó con. \n" +
                "\n" +
                "Ngược lại, ăn không đủ dinh dưỡng khi mang thai có thể dẫn đến mất phôi, sự phát triển của thai nhi bất thường, sảy thai tự nhiên hoặc thai chết lưu, số lượng con trong lứa nhỏ và chó con nhẹ cân không phát triển mạnh. Trong hai tam cá nguyệt đầu tiên của thai kỳ, các yêu cầu dinh dưỡng của cô ấy về cơ bản giống như đối với một con chó trưởng thành. Điều quan trọng là cô ấy không giảm cân hoặc ốm bệnh trong thời gian này, vì vậy hãy theo dõi cân nặng và tình trạng cơ thể của cô ấy, tăng thực phẩm khi cần thiết...."));

        blogs.add(new Blog("Chơi cùng thú cưng","Hãy chú ý quan sát để nhận biết được tính cách và sở thích cá nhân của từng thú cưng. Đừng cho rằng tất cả vẹt đều giống nhau hoặc tất cả chó lông vàng đều có chung một nhu cầu.\n" +
                "\n" +
                "Hãy tìm hiểu về tính cách và nhu cầu về thể chất cũng như tinh thần của thú cưng. Từ đó, bạn nên dành thời gian để nghiên cứu cách thức thích hợp để đảm bảo những nhu cầu này đều được đáp ứng. \n" +
                "\n" +
                "Ví dụ: một số vật nuôi có xu hướng hòa đồng và thích có bạn bè để chơi cùng, trong khi một số khác thì lại không cảm thấy thoải mái với điều này. Vì vậy, hãy dành thời gian để thấu hiểu bé nhé. Nếu bạn có ý định nuôi thêm một thú nuôi con trong khi đang chăm sóc cho một thú nuôi già, bạn hãy chú ý kết nối các bé lại với nhau. \n" +
                "\n" +
                "Đừng nên chỉ chơi đùa với thú nuôi con, mà hãy dành thời gian cho những hoạt động với vật nuôi già vì bé luôn muốn bạn gần gũi bé hơn. Đối với từng đối tượng, bạn hãy sắp xếp thời gian và đưa ra những hoạt động phù hợp để bạn luôn kết nối và tương tác với các bé.Không để bị ảnh hưởng quá nhiều bởi lời khuyên của những người không thấu hiểu bạn và việc chăm sóc vật nuôi của bạn. Hãy luôn tự tìm hiểu thông tin từ những nguồn đáng tin cậy và tự đưa ra quan điểm và quyết định của riêng mình. Nuôi thú cưng là công việc đòi hỏi tinh thần trách nhiệm cao giống như việc bạn đang chăm sóc cho đứa con của mình vậy.\n" +
                "\n" +
                "Khi bạn dắt thú cưng đi bộ, trong những khoảng thời gian nhất định, hãy để bé “cầm lái”, chỉ ra phương hướng bé muốn đi. Đừng cố gắng bắt bé luôn tuân theo sự điều khiển của mình, điều này chỉ khiến bé trở nên mệt mỏi và có xu hướng chống đối lại bạn."));

        blogs.add(new Blog("Chăm mèo con đúng","Mèo vốn dĩ là loài động vật độc lập. Bản tính này được hình thành ngay từ lúc sinh ra. Chúng chỉ cần chút ít sự chú ý. Điều này làm chúng là thú cưng hoàn hảo trong mắt những người bận rộn. Mọi thành phần dân cư: từ dân cư thành thị, người sống ở những khu căn hộ.\n" +
                "\n" +
                "Nuôi mèo trong nhà có nghĩa là bạn có thể thoải mái đi dạo. Có thể là một mình. Cũng có thể đồng hành cùng với chúng. Tất cả đều không có vấn đề gì cả. Khi chúng ở một mình, chúng cũng không gây ra tiếng ồn ào, không phá phách. Không bị trầm cảm hay trở nên nhút nhát như loài chó. Chúng chỉ cần bạn vuốt ve là cảm thấy vô cùng hạnh phúc rồi.\n" +
                "\n" +
                "Những chú mèo sẽ dành nhiều tiếng đồng hồ xem những việc đang xảy ra bên ngoài cửa sổ. Chúng ngắm nhìn thế giới một cách trầm lắng. Chúng được ví như là đang âm mưu thôn tính thế giới trong cái dáng vẻ khoan thai của mình. Tuy nhiên, đấy lại là những phút giây thư thái nhất của chúng.\n" +
                "\n" +
                "Một chú mèo dành rất nhiều giờ mỗi ngày để ngủ trên những chiếc ghế sofa. Chúng không hề phá phách hay gây tổn thất gì tới cuộc sống của bạn. Hơn nữa, chúng có thể tự vệ sinh cho chính mình mà không cần tắm thường xuyên khi nuôi chó.\n" +
                "\n" +
                "Những chú mèo bước vào cuộc sống của bạn gần như không cần phải huấn luyện. Bạn chỉ cần đưa ra một chiếc hộp bé cùng với lời chỉ dẫn rất nhỏ thôi. Chúng sẽ tìm cách làm thế nào để dùng nó gần như là tự nhiên. Nuôi mèo cảnh  nghĩa là không bao giờ lo lắng về việc về nhà muộn. Mèo có nhiều khả năng tự mua vui cho mình với đồ chơi, hộp, ngăn kéo hay những thứ đại loại thế."));

        blogs.add(new Blog("Cẩm nang nuôi mèo","Việc thay đổi môi trường sống, phải xa chủ cũ ít nhiều làm cho tâm lý mèo con hoang mang. Đây có thể giống như một thử thách đầu đời đầy khó khăn. Vậy, cách nuôi mèo con mới về nhà như thế nào là hợp lý? Phải làm gì để mèo con vượt qua được thử thách này.\n" +
                "\n" +
                "Đầu tiên đừng để mèo con lạc vào một không gian quá choáng ngợp như phòng khách. Trong vòng 1 tuần đầu tiên hãy để mèo tự khám phá mọi thứ. Sử dụng một không gian riêng cho mèo con là cách tốt nhất. Để một không gian mở để mèo con có thể đi tham quan mọi thứ xung quanh. Nếu trong gia đình bạn có các vật nuôi khác như chó mèo cần tránh những cuộc đối đầu căng thẳng. Có thể chúng sẽ tự tìm hiểu và làm thân với nhau từ từ.\n" +
                "\n" +
                "Mèo con là một thành viên mới trong gia đình của bạn. Những chú mèo con cũng cần sắm sửa rất nhiều đồ dùng. Đây là một trong những cách nuôi mèo con mới về nhà khoa học. Đảm bảo cho môi trường sống của bạn, đồng thời tạo cảm giác thoải mái cho mèo con nhất. \n" +
                "\n" +
                "Tiêm phòng là một trong những cách nuôi mèo khoa học và tiết kiệm nhất. Trước khi mang chú mèo về nhà bạn cần biết về tình hình sức khỏe của chúng. Thông thường chúng đã được tiêm đầy đủ các mũi. Tuy nhiên, trong trường hợp mèo con chưa được tiêm phòng, bạn cũng cần đưa chúng tới gặp bác sĩ thú y  để tiêm vacxin phòng bệnh care, parvo, dại… Việc này vừa giúp chúng có sức đề kháng tốt vừa ngăn chặn tỷ lệ mắc bệnh của mèo con.\n" +
                "\n" +
                "Lưu ý là nên tiến hành tiêm phòng cho mèo cưng càng sớm càng tốt. Thời gian tốt nhất là khi chú mèo đã quen và thích nghi với ngôi nhà mới là có thể tiến hành tiêm phòng. Không nên vừa đưa mèo con về nhà đã tiêm phòng ngay sẽ không có hiệu quả."));

        blogs.add(new Blog("Dắt chó đi dạo","Mỗi con chó đều nên có khoảng không gian an toàn để chơi đùa và nghỉ ngơi. Mặc dù khoảng sân rào chắn cũng cho chó yêu không gian để luyện tập và chạy nhảy, và cũng rất nhiều chủ nuôi cho rằng như vậy là chó đã luyện tập đủ trong phạm vi sân nhà. Tuy nhiên, chủ nuôi nên dắt chó đi dạo khoảng 1 – 2 lần 1 ngày vì chạy quanh sân nhà, hay lên xuống cầu thang thôi thật sự chưa đủ để chó cưng rèn luyện sức khỏe.\n" +
                "\n" +
                "Nếu bạn chưa bao gờ dắt chó yêu đi dạo, bạn sẽ không bao gờ tận hưởng trọn vẹn được niềm hạnh phúc khi ở bên chó cưng. Mọi người thường nghĩ rằng, chó cần không gian nghỉ ngơi rộng rãi, nhưng thật ra, những gì chúng cần là thời gian của bạn, là sự hiện diện của bạn, là sự quan tâm và chăm sóc của bạn.\n" +
                "\n" +
                "Ở lâu ngày tong sân nhà quen thuộc, chó sẽ bắt đầu chán; và nếu như thấy chó hàng xóm đi dạo bên ngoài, chó yêu sẽ sủa ầm ĩ, rên rỉ, hay nhào lộn vài vòng để thu hút sự chú ý của bạn. Hãy cùng chó cưng đến khu vui chơi, con hẻm nhỏ hay một bãi đất trống gần nhà mà bạn chưa có cơ hội khám phá.\n" +
                "\n" +
                "Nếu như bạn yêu sự mới mẻ và những hành trình khám phá đầy thú vị, chó yêu cũng thế. Ngoài ra, khi đi dạo cùng nhau, bạn sẽ phát hiện ra, dường như bạn và chó cưng hiểu nhau hơn, chỉ qua ánh mắt hay những động tác nhỏ. Đấy, bạn đang thắt chăt sợi dây tình cảm và sự giao tiếp chung không lời với chó cưng qua việc đi dạo đó bạn à.\n" +
                "\n" +
                "Thêm vào đó, trong khi đi dạo, chó cưng sẽ học cách giao tiếp với những con chó khác trong khu vực sinh sống. Điều này rất tốt cho chó con, vì chúng có thể học hỏi cách thức giao tiếp của loài chó khi tương tác với chó lớn hơn chúng. Nếu bạn và chó cưng có thể đi dạo hơn 1km 1 ngày, cả hai sẽ rèn luyện sức khỏe và sức chịu đựng, đốt cháy calori thừa, tận hưởng bầu không khí trong lành và cùng nhau tò mò chuyện hàng xóm."));

        blogs.add(new Blog("Giáng sinh bên thú cưng","Thú cưng sẽ biến Giáng Sinh vui hơn gấp mười lần với sự nghịch ngợm và phá hoại không lường được của các chú chó và chú mèo. Nhưng chúng cũng khiến chúng ta cảm thấy ấm áp và dễ thương trong mùa đông lạnh giá. \"Các gia đình sẽ đông hơn bình thường và nhiều thói quen của chó sẽ thay đổi. Âm nhạc thường được dùng để xoa dịu những chú chó trong thời điểm có nhiều thay đổi và căng thẳng, vì vậy không có gì ngạc nhiên khi âm nhạc sẽ đóng vai trò then chốt đối với những chú chó trong dịp Giáng sinh này\". Trong thời kỳ đại dịch, người dân Anh nuôi chó nhiều hơn. Số liệu từ tháng 3 cho thấy hơn 3,2 triệu thú cưng đã được các hộ gia đình ở Anh nhận nuôi trong thời gian phong tỏa. Tuy nhiên, kể từ khi các hạn chế phòng dịch được nới lỏng hoặc dỡ bỏ, diễn ra tình trạng một số chủ nuôi quay lại công ty làm việc và không còn dành nhiều thời gian cho thú cưng. Các tổ chức từ thiện cho biết nhiều thú cưng trong số đó được mua trực tuyến với nguồn gốc và tình trạng sức khỏe đôi khi không rõ ràng. Chúng thường có tỉ lệ mắc các vấn đề về hành vi và sức khỏe cao hơn, do đó gặp khó khăn trong việc tìm được gia đình phù hợp."));

        blogs.add(new Blog("Sữa tắm cho chó nào tốt nhất?","Ve ký sinh trên cơ thể không chỉ khiến các boss chó ngứa ngáy khó chịu mà còn có thể khiến da bị tổn thương, nhiễm trùng..."));
        blogs.add(new Blog("5 loại thức ăn cho mèo được tin dùng nhất","Hiện nay xu hướng nuôi mèo làm thú cưng càng ngày càng được yêu thích. Vì vậy các thương hiệu về đồ ăn, phụ kiện thú cưng ngày càng nhiều..."));
        blogs.add(new Blog("5 điều cần biết khi mua thức ăn cho mèo","Thức ăn cho mèo Max Power là thương hiệu thức ăn vật nuôi nổi tiếng của Anh...."));
        blogs.add(new Blog("6 điều bạn nên chú ý khi lựa chọn thức ăn cho mèo","Về cách nuôi mèo trong nhà, chắc chắn bạn không thể không quan tâm đến một điều: làm thế nào để lựa chọn được chế độ dinh dưỡng tốt nhất cho mèo..."));
        adapter = new BlogAdapter(getContext(), R.layout.notification_blog_item, blogs);
        lvBlog.setAdapter(adapter);
    }
}
