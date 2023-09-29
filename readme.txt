本系统是《软件开发综合实践》作业
系统名称：影院管理系统
包括登录、注册、修改密码、退出登录
展示所有电影、展示电影放映时间、购票历史、买票等功能。
这是一个控制台小程序
包含Movie
    public String plot;
    public String title;
    public String director;
    public String mainActor;
    public String synopsis;
    public int duration;
	
	
Ticket
    String id;
    double count;
    Date time;


User：
    String userID;
    String username;
    String level;
	
Showtime
    Movie movie;
    String hall;
    String time;
    double price;
	
	
等类，用来描述类的相关属性。


CinemaManagementSystem类来控制整体输入输出和相关逻辑。
操作视频也传上来了。


