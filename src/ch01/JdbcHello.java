package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcHello {

    public static void main(String[] args) {

        // 연결 정보
        String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=Asia/Seoul";
        String user = "";
        String password = "";
        // 실무에서는 비밀번호를 코드 직접 작성 X
        // 환경변수나 설정파일 사용(.env, application.properties,application.ymal)

        try (Connection connection = DriverManager
                .getConnection(url, user, password)) {
            System.out.println("연결 성공됨");
            System.out.println("DB 제품명: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB 버전: " + connection.getMetaData().getDatabaseProductVersion());

        } catch (SQLException e) {
            System.out.println("연결 성공 오류 발생 함");
            e.printStackTrace();
        }

    }

}
