package test;

import java.util.*;

public class CinemaManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, String> userPasswords = new HashMap<>();
    private static final Map<String, User> users = new HashMap<>();
    private static final List<Movie> movies = new ArrayList<>();
    private static final List<Showtime> showtimes = new ArrayList<>();
    private static final Map<String, Seat[][]> seats = new HashMap<>();

    private static void adminMenu() {
        while (true) {
            System.out.println("====管理员界面====");
            System.out.println("1. 密码管理");
            System.out.println("2. 用户管理");
            System.out.println("3. 退出登录");
            System.out.print("请选择: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    passwordManagement();
                    break;
                case 2:
                    userManagement();
                    break;
                case 3:
                    System.out.println("管理员用户成功退出");
                    return;
                default:
                    System.out.println("输入有误，重新输入");
            }
        }
    }

    private static void passwordManagement() {
        System.out.println("====密码管理界面====");
        System.out.println("1. 修改管理员密码");
        System.out.println("2. 重置用户密码");
        System.out.println("3. 返回上级");
        System.out.print("请选择: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                changeAdminPassword();
                break;
            case 2:
                resetUserPassword();
                break;
            case 3:
                return;
            default:
                System.out.println("输入有误，重新输入");
        }
    }

    private static void changeAdminPassword() {
        System.out.print("输入原密码: ");
        String currentPassword = scanner.nextLine();

        if (currentPassword.equals(userPasswords.get("admin"))) {
            System.out.print("输入新密码: ");
            String newPassword = scanner.nextLine();
            userPasswords.put("admin", newPassword);
            System.out.println("密码修改成功");
        } else {
            System.out.println("原密码输入有误");
        }
    }

    private static void resetUserPassword() {
        System.out.print("输入要重置密码的用户名: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.print("输入新密码: ");
            String newPassword = scanner.nextLine();
            userPasswords.put(username, newPassword);
            System.out.println(username + " 密码已经重置. 新密码是: " + newPassword);
        } else {
            System.out.println(username + " 不存在");
        }
    }


    private static void managerMenu() {
        while (true) {
            System.out.println("====经理界面====");
            System.out.println("1. 电影管理");
            System.out.println("2. 排期管理");
            System.out.println("3. 退出登录");
            System.out.print("请选择: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    movieManagement();
                    break;
                case 2:
                    showtimeManagement();
                    break;
                case 3:
                    System.out.println("经理账户已经退出");
                    return;
                default:
                    System.out.println("输入有误，重新输入");
            }
        }
    }


    private static void showtimeManagement() {
        while (true) {
            System.out.println("====排期管理====");
            System.out.println("1. 展示所有排期");
            System.out.println("2. 添加排期");
            System.out.println("3. 修改排期");
            System.out.println("4. 删除排期");
            System.out.println("5. 返回上级");
            System.out.print("请选择: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listShowtimes();
                    break;
                case 2:
                    addShowtime();
                    break;
                case 3:
                    modifyShowtime();
                    break;
                case 4:
                    deleteShowtime();
                    break;
                case 5:
                    System.out.println("返回上级.");
                    return;
                default:
                    System.out.println("输入有误，重新输入");
            }
        }
    }

    private static void listShowtimes() {
        System.out.println("所有排期:");
        int index = 0;
        for (Showtime showtime : showtimes) {
            System.out.println(index + ". " + showtime.movie.title + " | " + showtime.time + " | " + showtime.hall);
            index++;
        }
    }

    private static void addShowtime() {
        System.out.print("输入电影id：");
        int movieIndex = scanner.nextInt();
        scanner.nextLine();
        if (movieIndex >= 0 && movieIndex < movies.size()) {
            Movie selectedMovie = movies.get(movieIndex);

            System.out.print("输入排期时间(e.g., 15:30): ");
            String time = scanner.nextLine();

            System.out.print("输入影厅: ");
            String hall = scanner.nextLine();

            Showtime newShowtime = new Showtime(selectedMovie, time, hall);
            showtimes.add(newShowtime);

            System.out.println("添加成功" + selectedMovie.title + " 时间：" + time + " 地点：" + hall);
        } else {
            System.out.println("电影id输入有误");
        }
    }

    private static void modifyShowtime() {
        System.out.print("输入要修改的排期id: ");
        int showtimeIndex = scanner.nextInt();
        scanner.nextLine();
        if (showtimeIndex >= 0 && showtimeIndex < showtimes.size()) {
            Showtime selectedShowtime = showtimes.get(showtimeIndex);

            System.out.print("输入新的时间 (e.g., 15:30): ");
            String newTime = scanner.nextLine();

            System.out.print("输入新的影厅: ");
            String newHall = scanner.nextLine();

            selectedShowtime.time = newTime;
            selectedShowtime.hall = newHall;

            System.out.println("修改成功" + selectedShowtime.movie.title);
        } else {
            System.out.println("排期id输入有误");
        }
    }

    private static void deleteShowtime() {
        System.out.print("输入要删除的排期id: ");
        int showtimeIndex = scanner.nextInt();
        scanner.nextLine();
        if (showtimeIndex >= 0 && showtimeIndex < showtimes.size()) {
            Showtime deletedShowtime = showtimes.remove(showtimeIndex);
            System.out.println("删除成功 " + deletedShowtime.movie.title + " 时间：" + deletedShowtime.time + " 地点：" + deletedShowtime.hall + " has been deleted.");
        } else {
            System.out.println("排期id输入有误");
        }
    }

    private static void addMovie() {
        System.out.print("输入电影标题: ");
        String title = scanner.nextLine();

        System.out.print("输入导演：");
        String director = scanner.nextLine();

        System.out.print("输入主角: ");
        String actor = scanner.nextLine();

        System.out.print("输入剧情摘要: ");
        String plot = scanner.nextLine();

        System.out.print("输入电影时长: ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        Movie newMovie = new Movie(title, director, actor, plot, duration);
        movies.add(newMovie);
        System.out.println("电影添加成功.");
    }

    private static void updateMovie() {
        System.out.print("输入要修改的电影id: ");
        int movieIndex = scanner.nextInt();
        scanner.nextLine();

        if (movieIndex >= 0 && movieIndex < movies.size()) {
            Movie selectedMovie = movies.get(movieIndex);

            System.out.print("输入新的标题: ");
            String newTitle = scanner.nextLine();

            System.out.print("输入新的导演: ");
            String newDirector = scanner.nextLine();

            System.out.print("输入新的主角: ");
            String newActor = scanner.nextLine();

            System.out.print("输入新的剧情摘要: ");
            String newPlot = scanner.nextLine();

            System.out.print("输入新的时长: ");
            int newDuration = scanner.nextInt();
            scanner.nextLine();

            selectedMovie.title = newTitle;
            selectedMovie.director = newDirector;
            selectedMovie.mainActor = newActor;
            selectedMovie.plot = newPlot;
            selectedMovie.duration = newDuration;

            System.out.println("更新成功.");
        } else {
            System.out.println("电影id输入有误");
        }
    }

    private static void deleteMovie() {
        System.out.print("输入要删除的电影id: ");
        int movieIndex = scanner.nextInt();
        scanner.nextLine();

        if (movieIndex >= 0 && movieIndex < movies.size()) {
            Movie deletedMovie = movies.remove(movieIndex);
            System.out.println("电影 " + deletedMovie.title + " 删除成功.");
        } else {
            System.out.println("电影id输入有误.");
        }
    }


    private static void movieManagement() {
        while (true) {
            System.out.println("====电影管理界面====");
            System.out.println("1. 展示所有电影");
            System.out.println("2. 添加电影");
            System.out.println("3. 修改电影");
            System.out.println("4. 删除电影");
            System.out.println("5. 返回");
            System.out.print("请选择: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listMovies();
                    break;
                case 2:
                    addMovie();
                    break;
                case 3:
                    updateMovie();
                    break;
                case 4:
                    deleteMovie();
                    break;
                case 5:
                    System.out.println("返回上级.");
                    return;
                default:
                    System.out.println("输入有误，重新输入");
            }
        }
    }

    private static void listMovies() {
        System.out.println("电影列表:");
        for (Movie movie : movies) {
            System.out.println("标题: " + movie.title);
            System.out.println("导演: " + movie.director);
            System.out.println("主角: " + movie.mainActor);
            System.out.println("时长：" + movie.duration);
            System.out.println();
        }
    }


    private static void userManagement() {
        while (true) {
            System.out.println("====用户管理界面====");
            System.out.println("1. 所有用户");
            System.out.println("2. 重置用户密码");
            System.out.println("3. 删除用户");
            System.out.println("4. 返回");
            System.out.print("请选择: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listUsers();
                    break;
                case 2:
                    resetUserPassword();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    System.out.println("返回上级.");
                    return;
                default:
                    System.out.println("输入有误，重新输入");
            }
        }
    }


    private static void deleteUser() {
        System.out.print("输入要删除的用户名: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            users.remove(username);
            userPasswords.remove(username);
            System.out.println("用户 " + username + " 成功删除.");
        } else {
            System.out.println(username + " 不存在");
        }
    }

    private static void listUsers() {
        System.out.println("所有用户:");
        for (User user : users.values()) {
            System.out.println("用户ID: " + user.userID);
            System.out.println("用户名: " + user.username);
            System.out.println("用户等级: " + user.level);
            System.out.println();
        }
    }


    private static void listSeatsForShowtime(Showtime showtime) {
        Seat[][] seatLayout = seats.get(showtime.hall);
        System.out.println("演出时间的座位布局 " + showtime.time);
        for (int i = 0; i < seatLayout.length; i++) {
            for (int j = 0; j < seatLayout[i].length; j++) {
                System.out.print(seatLayout[i][j].isOccupied ? "X " : "O ");
            }
            System.out.println();
        }
    }

    private static void sellTicket(User user, Showtime showtime) {
        listSeatsForShowtime(showtime);
        System.out.println("输入用户名：");
        String username = scanner.next();
        System.out.print("输入行和列的座位号 (e.g. 1 2): ");
        int row = scanner.nextInt();
        int seatNumber = scanner.nextInt();
        scanner.nextLine();

        Seat[][] seatLayout = seats.get(showtime.hall);
        if (!seatLayout[row - 1][seatNumber - 1].isOccupied) {
            seatLayout[row - 1][seatNumber - 1].isOccupied = true;
            System.out.print("输入支付账户: ");
            double paymentAmount = scanner.nextDouble();
            scanner.nextLine();

            double discountedAmount = applyUserDiscount(user, paymentAmount);

            String ticketID = generateTicketID();
            System.out.println("购票成功!");
            System.out.println("影票ID: " + ticketID);
            User user1 = new User();
            user1.username = username;
            user1.addPurchasedTicket(new Ticket(ticketID, discountedAmount, new Date()));
        } else {
            System.out.println("座位已被占用.");
        }
    }

    private static double applyUserDiscount(User user, double amount) {
        double discountedAmount = amount;
        if (user.level.equals("Gold")) {
            discountedAmount *= 0.88;
        } else if (user.level.equals("Silver")) {
            discountedAmount *= 0.95;
        }
        return discountedAmount;
    }

    private static String generateTicketID() {
        return UUID.randomUUID().toString();
    }


    private static User loginUser() {
        System.out.print("输入用户名: ");
        String username = scanner.nextLine();
        System.out.print("输入密码: ");
        String password = scanner.nextLine();

        if (userPasswords.containsKey(username) && userPasswords.get(username).equals(password)) {
            return users.get(username);
        } else {
            System.out.println("用户名或密码不正确.");
            return null;
        }
    }

    private static void registerUser() {
        System.out.print("输入用户名: ");
        String username = scanner.nextLine();
        System.out.print("输入密码: ");
        String password = scanner.nextLine();


        if (!users.containsKey(username)) {
            User newUser = new User(username, username, "user");
            users.put(username, newUser);
            userPasswords.put(username, password);
            System.out.println("注册成功!");
        } else {
            System.out.println("用户名已经存在，失败.");
        }
    }

    private static void changePassword(User user) {
        System.out.print("输入新的密码: ");
        String newPassword = scanner.nextLine();


        userPasswords.put(user.username, newPassword);
        System.out.println("密码更新成功.");
    }


    private static void buyTicket(User user) {
        System.out.println("选择放映时间购买门票:");
        listShowtimes();

        System.out.print("输入排期时间id: ");
        int showtimeIndex = scanner.nextInt();
        scanner.nextLine();

        if (showtimeIndex >= 0 && showtimeIndex < showtimes.size()) {
            Showtime selectedShowtime = showtimes.get(showtimeIndex);
            listSeatsForShowtime(selectedShowtime);

            System.out.print("输入行和列的座位号 (e.g. 1 2): ");
            int row = scanner.nextInt();
            int seatNumber = scanner.nextInt();
            scanner.nextLine();

            Seat[][] seatLayout = seats.get(selectedShowtime.hall);
            if (!seatLayout[row - 1][seatNumber - 1].isOccupied) {

                seatLayout[row - 1][seatNumber - 1].isOccupied = true;

                System.out.print("输入支付账号: ");
                double paymentAmount = scanner.nextDouble();
                scanner.nextLine();

                double discountedAmount = applyUserDiscount(user, paymentAmount);

                String ticketID = generateTicketID();
                System.out.println("购票成功!");
                System.out.println("影票ID: " + ticketID);
                System.out.println("总付款: " + discountedAmount);
                user.addPurchasedTicket(new Ticket(ticketID, discountedAmount, new Date()));
            } else {
                System.out.println("座位已被占用.");
            }
        } else {
            System.out.println("排期时间id输入有误");
        }
    }


    private static void userMenu(User user) {
        while (true) {
            System.out.println("====用户界面====");
            System.out.println("1. 展示所有电影");
            System.out.println("2. 展示放映时间");
            System.out.println("3. 购票历史");
            System.out.println("4. 买票");
            System.out.println("5. 修改密码");
            System.out.println("6. 退出登录");
            System.out.print("请选择: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listMovies();
                    break;
                case 2:
                    listShowtimes();
                    break;
                case 3:
                    listPurchasedTickets(user);
                    break;
                case 4:
                    buyTicket(user);
                    break;
                case 5:
                    changePassword(user);
                    break;
                case 6:
                    System.out.println("成功退出, " + user.username + "!");
                    return;
                default:
                    System.out.println("输入有误，重新输入");
            }
        }
    }

    private static void listPurchasedTickets(User user) {
        System.out.println(user.username + "的影票如下 :");

        List<Ticket> purchasedTickets = user.getPurchasedTickets();

        if (purchasedTickets.isEmpty()) {
            System.out.println("空空如也.");
        } else {
            for (Ticket ticket : purchasedTickets) {
                System.out.println("ID: " + ticket.id);
                System.out.println("日期: " + ticket.time);
                System.out.println("价格: " + ticket.count);
                System.out.println();
            }
        }
    }


    private static void receptionistMenu() {
        while (true) {
            System.out.println("====前台界面====");
            System.out.println("1. 展示所有电影");
            System.out.println("2. 展示所有排期");
            System.out.println("3. 展示某一排期的座位号");
            System.out.println("4. 卖票");
            System.out.println("5. 退出登录");
            System.out.print("请选择: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listMovies();
                    break;
                case 2:
                    listShowtimes();
                    break;
                case 3:
                    listShowtimes();
                    System.out.print("输入排期id: ");
                    int showtimeIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (showtimeIndex >= 0 && showtimeIndex < showtimes.size()) {
                        listSeatsForShowtime(showtimes.get(showtimeIndex));
                    } else {
                        System.out.println("排期id输入有误.");
                    }
                    break;
                case 4:
                    listShowtimes();
                    System.out.print("输入排期id: ");
                    int sellShowtimeIndex = scanner.nextInt();
                    System.out.println("输入用户名");
                    String username = scanner.next();
                    scanner.nextLine();
                    if (sellShowtimeIndex >= 0 && sellShowtimeIndex < showtimes.size()) {
                        User user = new User(username, username, username);
                        sellTicket(user, showtimes.get(sellShowtimeIndex));
                    } else {
                        System.out.println("排期id输入有误.");
                    }
                    break;
                case 5:
                    System.out.println("成功退出!");
                    return;
                default:
                    System.out.println("输入有误，重新输入");
            }
        }
    }

    private static void init() {
        users.put("admin", new User("admin", "Admin", "Admin"));
        users.put("manager", new User("manager", "Manager", "Manager"));
        users.put("receptionist", new User("receptionist", "Receptionist", "Receptionist"));
        users.put("user1", new User("user1", "User1", "Gold"));
        users.put("user2", new User("user2", "User2", "Silver"));

        userPasswords.put("admin", "ynuinfo#777");
        userPasswords.put("manager", "manager123");
        userPasswords.put("receptionist", "receptionist123");
        userPasswords.put("user1", "user1123");
        userPasswords.put("user2", "user2123");

        movies.add(new Movie("复仇者联盟", "乔斯·韦登", "小罗伯特·唐尼", "一群超级英雄联合起来保护地球免受邪恶势力的威胁", 180));
        movies.add(new Movie("阿凡达", "詹姆斯·卡梅隆", "萨姆·沃辛顿", "一位残疾前海军陆战队员在一个遥远的星球上遇到了一个神秘而危险的世界", 162));
        movies.add(new Movie("星际穿越", "克里斯托弗·诺兰", "马修·麦康纳", "一群探险家利用一个虫洞进行星际旅行，以寻找可供人类居住的替代星球", 169));

        showtimes.add(new Showtime(movies.get(0), "16:00", "Hall 1"));
        showtimes.add(new Showtime(movies.get(0), "18:30", "Hall 2"));
        showtimes.add(new Showtime(movies.get(1), "16:30", "Hall 1"));
        showtimes.add(new Showtime(movies.get(2), "20:00", "Hall 3"));

        Seat[][] hall1Seats = createSeatLayout();
        Seat[][] hall2Seats = createSeatLayout();
        Seat[][] hall3Seats = createSeatLayout();

        seats.put("Hall 1", hall1Seats);
        seats.put("Hall 2", hall2Seats);
        seats.put("Hall 3", hall3Seats);

    }

    private static Seat[][] createSeatLayout() {
        Seat[][] seatLayout = new Seat[7][12];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 12; j++) {
                seatLayout[i][j] = new Seat();
            }
        }
        return seatLayout;
    }


    public static void main(String[] args) {
        init();

        while (true) {
            System.out.println("欢迎来到影院管理系统!");
            System.out.println("1. 登录");
            System.out.println("2. 注册");
            System.out.println("3. 退出");
            System.out.print("请选择: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    User loggedInUser = loginUser();
                    if (loggedInUser != null) {

                        switch (loggedInUser.level) {
                            case "Admin":
                                adminMenu();
                                break;
                            case "Manager":
                                managerMenu();
                                break;
                            case "Receptionist":
                                receptionistMenu();
                                break;
                            default:
                                userMenu(loggedInUser);
                                break;
                        }
                    }
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    System.out.println("退出系统!");
                    System.exit(0);
                default:
                    System.out.println("输入有误，重新输入");
            }
        }
    }

}
