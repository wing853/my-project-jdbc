package ch02;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class Step1Statement {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER"); // 환경변수에 저장되어 있음
        String password = System.getenv("DB_PASSWORD"); // 환경변수에 저장되어 있음

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // 1. Connection 객체 필요 - 생성 - 세션 생성(논리적으로 연결된 상태를 의미함)
            // 2. Statement 객체 필요(문자열을 쿼리 객체로 변경 해 줌)
            // 3. ResultSet객체가 필요(쿼리가 실행되면 결과 집합을 담고 있는 녀석)
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product");
            System.out.println("===== 상품 목록 출력 =====");
            // rs.next() --> 다음 행이 존재 하는가? --> true, false
            while(rs.next() ) {
                int id = rs.getInt("id");
                int categoryId = rs.getInt("category_id");
                String name = rs.getString("name");
                double price = rs. getDouble("price");
                int stock = rs.getInt("stock");
                String description = rs.getString("description");
                //String createdAt = rs.getString("created_at");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                System.out.println(
                             "상품ID: " + id
                        + " | 카테고리ID: " + categoryId
                        + " | 상품명: " + name
                        + " | 가격: " + price
                        + " | 수량: " + stock
                        + " | 설명: " + description
                        + " | 등록날짜: " + createdAt
                );
            }

        } catch (SQLException e) {
            System.out.println("오류발생: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
        }
    } // end of main

} // end of class

