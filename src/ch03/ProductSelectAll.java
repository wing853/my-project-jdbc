package ch03;

import java.sql.*;

public class ProductSelectAll {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/shop2?severTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER");
        String pwd = System.getenv("DB_PASSWORD");

        // INSERT 쿼리 구문을 미리 String으로 만들어두자
        // 텍스트블록 문법 JDK 13이후 사용 가능
        String sql = """
                SELECT * FROM product2
                """;

        try (Connection conn = DriverManager.getConnection(url, user, pwd);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("===== 전체 상품 목록 조히 =====");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
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

        } catch (SQLException e) {
            System.out.println("오류: " + e.getMessage());
        }
    } // end of main

} // end of class
