package ch03;

import java.sql.*;

public class ProductSelectByPrice {

    public static void main(String[] args) {
        // 상품이 1만원에서 10만원 사이의 상품을 출력하시오
        // shopDB사용 product Table사용
        // 가격 오름차순 정렬
        String url = "jdbc:mysql://localhost:3306/shop?severTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER");
        String pwd = System.getenv("DB_PASSWORD");

        int minPrice = 10000;
        int maxPrice = 100000;

        // INSERT 쿼리 구문을 미리 String으로 만들어두자
        // 텍스트블록 문법 JDK 13이후 사용 가능
        String sql = """
                SELECT * FROM product 
                WHERE price BETWEEN ? AND ?
                ORDER BY price
                """;

        try (Connection conn = DriverManager.getConnection(url, user, pwd);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("===== 1만원과 10만원 사이 상품 목록 조회 =====");
            pstmt.setInt(1, minPrice);
            pstmt.setInt(2, maxPrice);
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
                String output = """
                        ID: %3d | %-20s | %,7d원 | 재고: %3d개
                        """.formatted(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("stock")
                );
                // 참고로 텍스트블록 문법은 \n이 포함되어있다.
                System.out.print(output);
            }
            System.out.println(count + "행의 결과를 받았습니다.");
        } catch (SQLException e) {
            System.out.println("오류: " + e.getMessage());
        }
    } // end of main

} // end of class
